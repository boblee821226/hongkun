package nc.impl.hkjt.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class ZnjjsPlugin implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		
		long startTime = System.currentTimeMillis();
		// 组织为必输，所以肯定有值（多个组织进行处理）
		String[] pk_orgs = bgwc.getPk_orgs();
		// 计算日期
		UFDate date = PuPubVO.getUFDate(bgwc.getKeyMap().get("date"));
		
		boolean lock = PKLock.getInstance()
				.addBatchDynamicLock(
						new String[]{bgwc.getAlertTypeName() + bgwc.getPk_orgs()[0]});
		
		if(!lock){
			throw new BusinessException("事务正在处理中,不能重复操作！");
		}
		
		this.doAction(pk_orgs, date, null);
		
		System.out.println("处理完成,共耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
	
		
		PreAlertObject res = new PreAlertObject();
		res.setReturnType(PreAlertReturnType.RETURNNOTHING);
		return res;
	}

	/**
	 * 事件处理
	 */
	private Object doAction(String[] pk_orgs, UFDate dbilldate, HashMap<String, Object> param) throws BusinessException {
		
		if (dbilldate == null) {
			dbilldate = new UFDate();
		}
		
		String dbilldateStr = dbilldate.toString().substring(0, 10);
		
		BaseDAO dao = new BaseDAO();
		
		ArrayList<ZnjjsBillVO> billVOlist = new ArrayList<ZnjjsBillVO>();
		
		/**
		 * 查询org的版本
		 */
		HashMap<String, String> ORG_MAP = new HashMap<String, String>();
		{
			StringBuffer orgStr = new StringBuffer(" ('null'");
			for (String pk_org : pk_orgs) {
				orgStr.append(",'").append(pk_org).append("'");
			}
			orgStr.append(") ");
			StringBuffer querySQL = 
				new StringBuffer("select pk_salesorg, pk_vid from org_salesorg ")
						.append(" where pk_salesorg in ").append(orgStr)
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if (list != null && list.size() > 0) {
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String org   = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
					String org_v = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
					ORG_MAP.put(org, org_v);
				}
			}
		}
		/**
		 * 进行处理
		 */
		for (String pk_org : pk_orgs) {
			
			// 先查询，如果存在 则跳过
			{
				StringBuffer checkSQL = 
				new StringBuffer("select ")
						.append(" pk_hk_zulin_znjjs ")
						.append(" from hk_zulin_znjjs ")
						.append(" where dr = 0 ")
						.append(" and pk_org = '").append(pk_org).append("' ")
						.append(" and substr(dbilldate, 1, 10) = '").append(dbilldateStr).append("' ")
				;
				ArrayList list = (ArrayList)dao.executeQuery(checkSQL.toString(), new ArrayListProcessor());
				if (list == null || list.size() == 0) {
					continue;
				}
			}
			
			ZnjjsBillVO billVO = new ZnjjsBillVO();
			ZnjjsHVO hVO = new ZnjjsHVO();
			ArrayList<ZnjjsBVO> bVOlist = new ArrayList<ZnjjsBVO>();
			
			UFDouble yq_mny_total = UFDouble.ZERO_DBL;	// 表头-滞纳金合计
			int addRowIndex = -1;	// 当前新增的行数
			// key=htBid , value=null  用于快速统计 合同bid 是否存在。
			HashMap<String,Object> MAP_HT_INFO = new HashMap<String,Object>();
			/**
			 * 1、从 合同上取数，取 未缴费的
			 */
			{
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" ct.pk_customer ")	// 0客户
							.append(",ct.vdef15 ")		// 1区域
							.append(",ct.vdef16 ")		// 2房号
							.append(",ct.vbillcode ")	// 3合同号
							.append(",ctb.crowno ")		// 4合同行
							.append(",ctb.vbdef1 ")		// 5收费项目
							.append(",substr(ctb.vbdef3,1,10) ")	// 6开始日期
							.append(",ctb.norigtaxmny ")	// 7应缴金额
							.append(",ctb.noritotalgpmny ")	// 8实缴金额
							.append(",ctb.pk_ct_sale ")		// 9合同主表pk
							.append(",ctb.pk_ct_sale_b ")	//10合同子表pk
							.append(",ct.vdef19 ")			//11租金确认截至日
							.append(",substr(ctb.vbdef4,1,10) ")	//12结束日期
							.append(",jffs.name ")			//13缴费方式
							.append(" from ct_sale ct ")
							.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
							.append(" left join bd_defdoc srxm on ctb.vbdef1 = srxm.pk_defdoc ")	// 收入项目
							.append(" left join bd_defdoc jffs on ct.vdef7 = jffs.pk_defdoc ")		// 缴费方式
							.append(" where ct.dr = 0 and ctb.dr = 0 ")
							.append(" and ct.blatest = 'Y' ")
							.append(" and ct.pk_org = '"+pk_org+"' ")
							.append(" and substr(ctb.vbdef3,1,10) <= '"+dbilldateStr+"' ")
							.append(" and (nvl(ctb.norigtaxmny,0)-nvl(ctb.noritotalgpmny,0))<>0 ")
							.append(" and srxm.name not like '%押金%' ")
							.append(" and srxm.name not like '%调整%' ")
	//						.append(" and ct.vbillcode = '201806151-2-10061-2-10071-3#1' ")	// 测试-合同号
				;
				
				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				
				if(list!=null && list.size()>0)
				{
					for(int row=0;row<list.size();row++)
					{
						Object[] obj = (Object[])list.get(row);
						String	  pk_cust = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
						String 	  pk_area = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
						String 	  pk_room = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
						String 	  ht_code = PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
						String 	 ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
						String 	  pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
						UFDate    jf_date = PuPubVO.getUFDate(obj[6]);	// 默认为 合同行-开始日期 如果缴费方式=后付，则赋值为 合同行-结束日期
						UFDouble   yj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
						UFDouble   sj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[8]);
						String 		ht_id = PuPubVO.getString_TrimZeroLenAsNull(obj[9]);
						String     ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[10]);
						UFDate	  zjqrjzr = PuPubVO.getUFDate(obj[11]);	// 租金确认截至日期
						UFDouble   jf_mny = yj_mny.sub(sj_mny);			// 应缴费金额 = 应缴房租 - 实缴房租
						UFDate	 end_date = PuPubVO.getUFDate(obj[12]);	// 合同行-结束日期
						String       jffs = PuPubVO.getString_TrimZeroLenAsNull(obj[13]);	// 缴费方式
						
						if ("后付".equals(jffs)) {
							jf_date = end_date;
						}
						
						UFDate jisuanDate = dbilldate;	// 计算日期（如果租金确认截至日期，小于 当前日期， 那计算日期应该等于租金确认截至日期）
						String vbmemo = null;			// 行备注（如果按租金确认截至日期来计算的，体现到行备注上）
						if(zjqrjzr!=null && zjqrjzr.compareTo(dbilldate)<0) {
							jisuanDate = zjqrjzr;
							vbmemo = "租金确认截至日期"+zjqrjzr.toString().substring(0, 10);
							jisuanDate = jisuanDate.getDateAfter(1);	// 如果有租金确认截至日期，则天数加一。
						}
						
						Integer yq_num = jisuanDate.getDaysAfter(jf_date);	// 逾期天数 (19年9月27日，确定为不加一)
						
						if(yq_num <=0 ) {	// 逾期天数 <=0 , 不做处理
							continue;
						}
							
						UFDouble  yq_bl = new UFDouble(5);				// 比例(千分之‰)
						UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
								.setScale(2, UFDouble.ROUND_HALF_UP);	// 滞纳金=应缴费金额 * 5‰ * 逾期天数
						
						yq_mny_total = yq_mny_total.add(yq_mny);
						
						ZnjjsBVO bVO = new ZnjjsBVO();
						bVO.setPk_cust(pk_cust);
						bVO.setPk_area(pk_area);
						bVO.setPk_room(pk_room);
						bVO.setHt_code(ht_code);
						bVO.setHt_rowno(ht_rowno);
						bVO.setPk_sfxm(pk_sfxm);
						bVO.setJf_date(jf_date);
						bVO.setJf_mny(jf_mny);
						bVO.setYq_bl(yq_bl);
						bVO.setYq_num(PuPubVO.getUFDouble_ValueAsValue(yq_num));
						bVO.setYq_mny(yq_mny);
						bVO.setHt_id(ht_id);
						bVO.setHt_bid(ht_bid);
						bVO.setVbmemo(vbmemo);
						
						bVOlist.add(bVO);
						
						MAP_HT_INFO.put(ht_bid, null);	// 添加到缓存里 供 第三步 使用
					}
				}
			}
			
			/**
			 * 2、取 已收款，但是 逾期收款的 数据， 并且是 之前没有取过的。（确保只取一次，不要重复取）
			 */
			{
				StringBuffer querySQL_2 = 
				new StringBuffer("select ")
						.append(" ct.pk_customer ")		// 0客户pk
						.append(",ct.vdef15 ")			// 1区域pk
						.append(",ct.vdef16 ")			// 2房号pk
						.append(",ct.vbillcode ")		// 3合同号
						.append(",ctb.crowno ")			// 4合同行号
						.append(",skb.def1 ")			// 5收入项目pk
						.append(",substr(skb.def3,1,10) ")	// 6计算日期
						.append(",skb.money_cr ")		// 7应缴金额
						.append(",0 ")					// 8实缴金额
						.append(",ctb.pk_ct_sale ")		// 9合同主pk
						.append(",ctb.pk_ct_sale_b ")	//10合同子pk
						.append(",substr(skb.busidate,1,10) ")	//11收款日期 为 租金确认截至日期
						.append(",sk.billno ")			//12收款单号
						.append(",skb.pk_gatherbill ")	//13收款单bid
						.append(",substr(ctb.vbdef4,1,10) ")	//14结束日期
						.append(",jffs.name ")					//15缴费方式
						.append(" from ar_gatherbill sk ")
						.append(" inner join ar_gatheritem skb on sk.pk_gatherbill = skb.pk_gatherbill ")
						.append(" inner join ct_sale_b ctb on skb.src_itemid = ctb.pk_ct_sale_b ")
						.append(" inner join ct_sale ct on ctb.pk_ct_sale = ct.pk_ct_sale ")
						.append(" left join bd_defdoc srxm on skb.def1 = srxm.pk_defdoc ")	// 收入项目
						.append(" left join bd_defdoc jffs on ct.vdef7 = jffs.pk_defdoc ")	// 缴费方式
						.append(" left join hk_zulin_znjjs_b jsb on (skb.pk_gatherbill = jsb.vbdef04 and jsb.dr = 0) ")
						.append(" where sk.dr = 0 and skb.dr = 0 ")
						.append(" and skb.src_tradetype = 'Z3-01' ")
						.append(" and srxm.name not like '%押金%' ")
						.append(" and srxm.name not like '%调整%' ")
						.append(" and ct.pk_org = '"+pk_org+"' ")
						.append(" and jsb.pk_hk_zulin_znjjs_b is null ")
	//					.append(" and sk.billno = '' ")	// 测试-收款单号
	//					.append(" and ct.vbillcode = '201806151-2-10061-2-10071-3#1' ")	// 测试-合同号
				;
				ArrayList list_2 = (ArrayList)dao.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				
				if(list_2!=null && list_2.size()>0)
				{
					for(int row=0;row<list_2.size();row++)
					{
						Object[] obj = (Object[])list_2.get(row);
						String	  pk_cust = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
						String 	  pk_area = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
						String 	  pk_room = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
						String 	  ht_code = PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
						String 	 ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
						String 	  pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
						UFDate    jf_date = PuPubVO.getUFDate(obj[6]);
						UFDouble   yj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
						UFDouble   sj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[8]);
						String 		ht_id = PuPubVO.getString_TrimZeroLenAsNull(obj[9]);
						String     ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[10]);
						UFDate	  zjqrjzr = PuPubVO.getUFDate(obj[11]);	// 租金确认截至日期
						UFDouble   jf_mny = yj_mny.sub(sj_mny);	// 应缴费金额 = 应缴房租 - 实缴房租
						String     skCode = PuPubVO.getString_TrimZeroLenAsNull(obj[12]);	// 收款单号
						String     skBid = PuPubVO.getString_TrimZeroLenAsNull(obj[13]);	// 收款单bid
						UFDate	 end_date = PuPubVO.getUFDate(obj[14]);						// 合同行-结束日期
						String       jffs = PuPubVO.getString_TrimZeroLenAsNull(obj[15]);	// 缴费方式
						
						if ("后付".equals(jffs)) {
							jf_date = end_date;
						}
						
						UFDate jisuanDate = dbilldate;	// 计算日期（如果租金确认截至日期，小于 当前日期， 那计算日期应该等于租金确认截至日期）
						String vbmemo = null;			// 行备注（如果按租金确认截至日期来计算的，体现到行备注上）
						if(zjqrjzr!=null && zjqrjzr.compareTo(dbilldate)<0) {
							jisuanDate = zjqrjzr;
							vbmemo = "收款日期"+zjqrjzr.toString().substring(0, 10);
						}
						
						Integer yq_num = jisuanDate.getDaysAfter(jf_date);	// 逾期天数
						
						if(yq_num<=0) {	// 逾期天数 <=0 , 不做处理
							continue;
						}
							
						UFDouble  yq_bl = new UFDouble(5);				// 比例(千分之‰)
						UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
								.setScale(2, UFDouble.ROUND_HALF_UP);	// 滞纳金=应缴费金额 * 5‰ * 逾期天数
						
						yq_mny_total = yq_mny_total.add(yq_mny);
						
						ZnjjsBVO bVO = new ZnjjsBVO();
						bVO.setPk_cust(pk_cust);
						bVO.setPk_area(pk_area);
						bVO.setPk_room(pk_room);
						bVO.setHt_code(ht_code);
						bVO.setHt_rowno(ht_rowno);
						bVO.setPk_sfxm(pk_sfxm);
						bVO.setJf_date(jf_date);
						bVO.setJf_mny(jf_mny);
						bVO.setYq_bl(yq_bl);
						bVO.setYq_num(PuPubVO.getUFDouble_ValueAsValue(yq_num));
						bVO.setYq_mny(yq_mny);
						bVO.setHt_id(ht_id);
						bVO.setHt_bid(ht_bid);
						bVO.setVbmemo(vbmemo);
						bVO.setVbdef03(skCode);
						bVO.setVbdef04(skBid);
						
						bVOlist.add(bVO);		
					}
				}
			}
			
			/**
			 * 3、从上期的 计算单上 取数
			 */
			{
				StringBuffer querySQL_3 = 
				new StringBuffer("select ")
						.append(" jsb.pk_cust ")	// 0
						.append(",jsb.pk_area ")	// 1
						.append(",jsb.pk_room ")	// 2
						.append(",jsb.ht_code ")	// 3
						.append(",jsb.ht_rowno ")	// 4
						.append(",jsb.pk_sfxm ")	// 5
						.append(",jsb.jf_date ")	// 6
						.append(",jsb.jf_mny - to_number(nvl( replace(jsb.vbdef01,'~',''),'0' )) ")		// 7  滞纳金-减免金额
						.append(",jsb.yq_bl ")		// 8
						.append(",jsb.yq_num ")		// 9
						.append(",jsb.yq_mny ")		//10
						.append(",jsb.ht_id ")		//11
						.append(",jsb.ht_bid ")		//12
						.append(",jsb.vbmemo ")		//13
						.append(",jsb.vbdef03 ")	//14 收款单号
						.append(",jsb.vbdef04 ")	//15 收款单bid
						.append(" from hk_zulin_znjjs js ")
						.append(" inner join ( ")
						.append("	select js.pk_org,max(js.dbilldate) dbilldate ")
						.append("	from hk_zulin_znjjs js ")
						.append("	where dr=0 ")
						.append("	and js.pk_org = '"+pk_org+"' ")
						.append("	and substr(js.dbilldate,1,10) < '"+dbilldateStr+"' ")
						.append("	group by js.pk_org  ")
						.append(" ) js_new on (js.pk_org=js_new.pk_org and js.dbilldate=js_new.dbilldate) ")
						.append(" inner join hk_zulin_znjjs_b jsb on (js.pk_hk_zulin_znjjs = jsb.pk_hk_zulin_znjjs) ")	// 关联应收单
						.append(" left join ar_recitem ysb on (jsb.pk_hk_zulin_znjjs_b = ysb.def29 and ysb.dr=0) ")
						.append(" where js.dr=0 and jsb.dr=0 ")
						.append(" and ysb.pk_recitem is null ")	// 只取 未生成应收单的，生成应收单，就意味着 滞纳金模块 处理完毕
				;
				
				ArrayList list_3 = (ArrayList)dao.executeQuery(querySQL_3.toString(), new ArrayListProcessor());
				
				if(list_3!=null && list_3.size()>0)
				{
					for(int i=0;i<list_3.size();i++)
					{
						Object[] obj = (Object[])list_3.get(i);
						String ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[12]);
						String  skBid = PuPubVO.getString_TrimZeroLenAsNull(obj[15]);	// 收款单bid
						// 如果有 收款单bid不为空，或者 不再本期的第一步里，则添加
						if(skBid!=null || !MAP_HT_INFO.containsKey(ht_bid)) {
							String pk_cust 	= PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
							String pk_area 	= PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
							String pk_room 	= PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
							String ht_code 	= PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
							String ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
							String pk_sfxm 	= PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
							UFDate jf_date 	= PuPubVO.getUFDate((obj[6]));
							UFDouble jf_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
							UFDouble yq_bl 	= PuPubVO.getUFDouble_ValueAsValue(obj[8]);
							UFDouble yq_num = PuPubVO.getUFDouble_ValueAsValue(obj[9]);
							UFDouble yq_mny = PuPubVO.getUFDouble_ValueAsValue(obj[10]);
							String ht_id 	= PuPubVO.getString_TrimZeroLenAsNull(obj[11]);
							String vbmemo 	= PuPubVO.getString_TrimZeroLenAsNull(obj[13]);
							String   skCode = PuPubVO.getString_TrimZeroLenAsNull(obj[14]);	// 收款单号
							
							if(skBid==null) {	// 只有不是 逾期收款单的，才进行 备注的处理
								if(vbmemo==null){
									vbmemo = "";
								}
								vbmemo += "【截至到"+dbilldateStr+"，房租已交清，只欠滞纳金】";
							}
							
							yq_mny_total = yq_mny_total.add(yq_mny);
							
							ZnjjsBVO bVO = new ZnjjsBVO();
							bVO.setPk_cust(pk_cust);
							bVO.setPk_area(pk_area);
							bVO.setPk_room(pk_room);
							bVO.setHt_code(ht_code);
							bVO.setHt_rowno(ht_rowno);
							bVO.setPk_sfxm(pk_sfxm);
							bVO.setJf_date(jf_date);
							bVO.setJf_mny(jf_mny);
							bVO.setYq_bl(yq_bl);
							bVO.setYq_num(PuPubVO.getUFDouble_ValueAsValue(yq_num));
							bVO.setYq_mny(yq_mny);
							bVO.setHt_id(ht_id);
							bVO.setHt_bid(ht_bid);
							bVO.setVbmemo(vbmemo);
							bVO.setVbdef03(skCode);
							bVO.setVbdef04(skBid);
							
							bVOlist.add(bVO);
						}
					}
				}
			}
			
			// 表头赋值
			hVO.setPk_group("0001N510000000000EGY");
			hVO.setPk_org(pk_org);
			hVO.setPk_org_v(ORG_MAP.get(pk_org));
			hVO.setVbilltypecode("HK41");
			hVO.setIbillstatus(-1);
			hVO.setDbilldate(dbilldate);
			hVO.setYq_mny_total(yq_mny_total);
			// 表体处理
			ZnjjsBVO[] bVOs = bVOlist.toArray(new ZnjjsBVO[0]);
			for (int i = 0; i < bVOs.length; i++) {
				bVOs[0].setVrowno("" + ((i+1)*10));
			}
			// 聚合VO
			billVO.setParentVO(hVO);
			billVO.setChildrenVO(bVOs);
			billVOlist.add(billVO);
		}
		// 进行保存
		if (billVOlist.size() > 0) {
			IHk_zulin_znjjsMaintain itf = NCLocator.getInstance().lookup(IHk_zulin_znjjsMaintain.class);
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
			ZnjjsBillVO[] billVOs = billVOlist.toArray(new ZnjjsBillVO[0]);
			ZnjjsBillVO[] result = itf.insert(billVOs, null);
		}
		
		return null;
	}
}
