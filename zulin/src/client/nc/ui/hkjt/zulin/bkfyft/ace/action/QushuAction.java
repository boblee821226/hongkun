package nc.ui.hkjt.zulin.bkfyft.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IPub_data;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.zulin.yuebao.QueryHtVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoBVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 费用分摊 取数
 *
 */
public class QushuAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public QushuAction() {
		setBtnName("取数");
		setCode("qushuAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;

	public ShowUpableBillListView getListview() {
		return listview;
	}

	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		this.getEditor().getBillCardPanel().getBillModel().clearBodyData();	// 清空表体
		
		String yearMonth = PuPubVO.getString_TrimZeroLenAsNull(
					this.getEditor().getBillCardPanel().getHeadItem("yearmonth").getValueObject()
				);
		
		UFDate yb_ksrq = 
				PuPubVO.getUFDate(
						this.getEditor().getBillCardPanel().getHeadItem("begindate").getValueObject() );
		UFDate yb_jsrq = 
				PuPubVO.getUFDate(
						this.getEditor().getBillCardPanel().getHeadItem("enddate").getValueObject() );
		
		String str_yb_ksrq = yb_ksrq.toString().substring(0, 10);
		String str_yb_jsrq = yb_jsrq.toString().substring(0, 10);
		
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject()
		);
			
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" htb.vbdef1 pk_srxm ")	// 支出项目pk
				.append(",szxm.name vdef03 ")	// 支出项目name
				.append(",substr(htb.vbdef3,1,10) ksrq ")	// 合同开始日期
				.append(",substr(htb.vbdef4,1,10) jsrq ")	// 合同结束日期
				.append(",htb.norigmny vdef11 ")	// 无税金额
				.append(",htb.norigtaxmny vdef12 ")	// 含税金额
				.append(",ht.depid vdef05 ")	// 部门pk
				.append(",dept.name vdef04 ")	// 部门name
				.append(",fplx.name vdef02 ")	// 发票类型name
				.append(",ht.vbillcode vdef10 ")// 合同号
				.append(",to_number(nvl(replace(ht.vdef8,'~',''),'0.0')) + to_number(nvl(replace(ht.vdef11,'~',''),'0.0')) mianji ")// 面积
				.append(",ht.cvendorid pk_customer ")	// 对方pk
				.append(",gys.name vdef01 ")			// 对方name
				.append(",ht.ntotalorigmny vdef07 ")	// 合同总额
				.append(" from ct_pu ht ")
				.append(" inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")
				.append(" left join bd_defdoc fplx on ht.vdef3 = fplx.pk_defdoc ")
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")
				.append(" left join org_dept dept on ht.depid = dept.pk_dept ")
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.vtrantypecode = '").append(IPub_data.BKHT_type_code).append("' ")
				// 不取保证金
				.append(" and szxm.code not in ('2005', '2022') ")
				.append(" and ht.pk_org = '").append(pk_org).append("' ")
				.append(" and ( ")
				.append(" 	'").append(str_yb_ksrq).append("' between substr(htb.vbdef3,1,10) and substr(htb.vbdef4,1,10) ")
				.append(" 	or ")
				.append("  	'").append(str_yb_jsrq).append("' between substr(htb.vbdef3,1,10) and substr(htb.vbdef4,1,10) ")
				.append(" ) ")
//				.append(" and ht.vbillcode like '20191112%' ")
		;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
		ArrayList<QueryHtVO> list = (ArrayList<QueryHtVO>)iUAPQueryBS.executeQuery(
				 querySQL.toString()
				,new BeanListProcessor(QueryHtVO.class)
		);
		
		HashMap<String,YuebaoBVO> MAP_yuebaoBVO = new HashMap<String,YuebaoBVO>();
		
		for(int i=0;i<list.size();i++)
		{
			QueryHtVO htVO = list.get(i);
			
			String fplx = htVO.getVdef02();		// 发票
			UFDouble ht_mny = htVO.getVdef12();	// 含税
			if ("专用发票".equals(fplx)) {			// 专票 用无税
				ht_mny = htVO.getVdef11();
			}
			
			String ht_code = htVO.getVdef10();	// 合同号
			UFDate ht_ksrq = htVO.getKsrq();	// 合同明细-开始日期
			UFDate ht_jsrq = htVO.getJsrq();	// 合同明细-结束日期
			Integer ht_days = ht_jsrq.getDaysAfter(ht_ksrq) + 1;	// 合同天数
			UFDouble ht_danjia = ht_mny.div(ht_days);	// 每天的单价 = 合同金额 / 合同天数
			
//			UFDate ht_zzrq = htVO.getZzrq();	// 合同表头-终止日期
			UFDouble ht_mianji = htVO.getMianji();	// 面积
			
			String ht_pk_customer = htVO.getPk_customer();	// 对方pk
			
			String srxm_name = htVO.getVdef03();	// 支出项目-名称
			
			Integer yb_days = 0;
			
			UFDate js_ksrq = null;		// 计算开始日期
			UFDate js_jsrq = null;		// 计算结束日期
			
			/**
			 *  分析 合同明细 与 费用单 开始结束日期  之间的 大小关系， 并计算出 天数
			 */
			if(
				ht_ksrq.compareTo(yb_ksrq)<=0
			 &&	ht_jsrq.compareTo(yb_jsrq)>=0
			)
			{// 如果 合同明细开始日期  小于等于  月报开始日期  并且  合同明细结束日期  大于等于  月报结束日期
			 // 说明 该月报范围  在 一行 合同明细 内， 月报的天数 即为 计费天数
				yb_days = yb_jsrq.getDaysAfter(yb_ksrq)+1;	// 月报结束日期-月报开始日期+1
				htVO.setYb_days(yb_days);	// 计费天数
				htVO.setYb_ksrq(yb_ksrq);	// 月报开始日期
				htVO.setYb_jsrq(yb_jsrq);	// 月报结束日期
				
				js_ksrq = yb_ksrq;
				js_jsrq = yb_jsrq;
			}
			else if(
				ht_ksrq.compareTo(yb_ksrq)<=0
			 &&	ht_jsrq.compareTo(yb_jsrq)<0
			)
			{// 如果 合同明细开始日期  小于等于  月报开始日期  并且  合同明细结束日期  小于  月报结束日期
			 // 说明 月报的 前半段 在 第一行合同明细， 计费天数 = 月报开始日期 到 合同明细结束日期
				yb_days = ht_jsrq.getDaysAfter(yb_ksrq)+1;	// 合同结束日期-月报开始日期+1
				htVO.setYb_days(yb_days);	// 计费天数
				htVO.setYb_ksrq(yb_ksrq);	// 月报开始日期
//				htVO.setYb_jsrq(yb_jsrq);	// 月报结束日期
				
				js_ksrq = yb_ksrq;
				js_jsrq = ht_jsrq;
			}
			else if(
				ht_ksrq.compareTo(yb_ksrq)>0
			 &&	ht_jsrq.compareTo(yb_jsrq)>=0	
			)
			{// 如果 如果 合同明细开始日期 大于 月报开始日期  并且  合同明细结束日期 大于等于 月报结束日期
			 // 说明 月报的 后半段 在 第二行合同明细， 计费天数 = 合同明细开始日期 到 月报结束日期
				yb_days = yb_jsrq.getDaysAfter(ht_ksrq)+1;	// 月报结束日期-合同开始日期+1
				htVO.setYb_days(yb_days);	// 计费天数
//				htVO.setYb_ksrq(yb_ksrq);	// 月报开始日期
				htVO.setYb_jsrq(yb_jsrq);	// 月报结束日期
				
				js_ksrq = ht_ksrq;
				js_jsrq = yb_jsrq;
			}
			
			// 如果 结束日期 大于 终止日期， 需要 将结束日期 赋值为终止日期， 并且重新计算 月报天数
//			if(js_jsrq.compareTo(ht_zzrq)>0)
//			{
//				js_jsrq = ht_zzrq;
//				htVO.setYb_jsrq(js_jsrq);
//				yb_days = js_jsrq.getDaysAfter(js_ksrq)+1;
//				htVO.setYb_days(yb_days);
//			}
			
			//计算  费用 = 每天单价 * 天数
			UFDouble yb_mny = ht_danjia.multiply(yb_days).setScale(2, UFDouble.ROUND_HALF_UP);
			htVO.setYb_mny(yb_mny);
			
			// 同时 对 YuebaoBVO 进行封装处理
			// 按 对方##合同号  汇总
			{
				String key = ht_pk_customer + "##" + ht_code;
				YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
				if(yuebaoBVO==null)
				{
					yuebaoBVO = new YuebaoBVO();
					yuebaoBVO.setPk_cutomer(ht_pk_customer);	// 客户
					yuebaoBVO.setJsksrq(js_ksrq);				// 计算开始日期
					yuebaoBVO.setJsjsrq(js_jsrq);				// 计算结束日期
					yuebaoBVO.setDysrqrts(PuPubVO.getUFDouble_NullAsZero(yb_days));	// 当月费用确认天数
					yuebaoBVO.setDysrqrje(yb_mny);				// 当月费用确认金额
					yuebaoBVO.setMianji(ht_mianji);				// 面积
					yuebaoBVO.setDanjia(ht_danjia);				// 单价
					
					yuebaoBVO.setSrxm(htVO.getPk_srxm());		// 收入项目
					yuebaoBVO.setVbdef01(htVO.getVdef01());		// 对方名称
					yuebaoBVO.setVbdef02(htVO.getVdef02());		// 发票类型-名称
					yuebaoBVO.setVbdef03(htVO.getVdef03());		// 支出项目-名称
					yuebaoBVO.setVbdef04(htVO.getVdef04());		// 部门名称
					yuebaoBVO.setVbdef05(htVO.getVdef05());		// 部门pk
					yuebaoBVO.setVbdef07(htVO.getVdef07());		// 实际合同金额
					yuebaoBVO.setVbdef10(htVO.getVdef10());		// 合同号
					
//					yuebaoBVO.setVbmemo(htVO.getVdef10());		// 备注存放合同号
//					yuebaoBVO.setVbdef06( ht_zzrq!=null ? ht_zzrq.toString().substring(0,10) : null );	// 合同终止日期  （HK 2018年12月26日17:39:31）
				}
				else
				{
					yuebaoBVO.setDysrqrts(
							yuebaoBVO.getDysrqrts().add(
							PuPubVO.getUFDouble_NullAsZero(yb_days) )
					);	// 当月费用确认天数
					yuebaoBVO.setDysrqrje(
							yuebaoBVO.getDysrqrje().add(
							yb_mny )
					);	// 当月费用确认金额
					yuebaoBVO.setDanjia(null);	// 将单价 赋值成null， 后面 重新计算单价
					
					
					if( js_ksrq.compareTo(yuebaoBVO.getJsksrq())<0 )
						yuebaoBVO.setJsksrq(js_ksrq);
					if( js_jsrq.compareTo(yuebaoBVO.getJsjsrq())>0 )
						yuebaoBVO.setJsjsrq(js_jsrq);
					
				}
				
				{// 备注的处理
					String vbmemo = "";
					
					vbmemo = 
						"【" +
						srxm_name + "、" +
						js_ksrq.toString().substring(0,10)   + "、" +
						js_jsrq.toString().substring(0,10)   + "、" +
						ht_danjia + "、" +
						yb_days   + "、" +
						yb_mny    + "" +
						"】 "
					;
					
					yuebaoBVO.setVbmemo(
						yuebaoBVO.getVbmemo()==null
						? vbmemo
						: yuebaoBVO.getVbmemo()+vbmemo
					);
				}
				
				MAP_yuebaoBVO.put(key,yuebaoBVO);
			}
		}
		
//		/**
//		 * 查询 本期付款金额。
//		 */
//		{
//			StringBuffer querySQL_FK = 
//			new StringBuffer(" select ")
//					.append(" a.pk_customer ")				// 客户pk
//					.append(",a.vdef10 ")					// 房间pk
//					.append(",sum(a.skje) fkje ")			// 付款金额
////					.append(",max(a.pk_dept) pk_dept ")		// 部门pk
////					.append(",max(cust.name) vdef01 ")		// 对方-name
//					.append(" from (")
//					// 蓝字收款单
//						.append(" select ")
//						.append(" ht.cvendorid pk_customer ")	// 对方pk
//						.append(",ht.vbillcode vdef10 ")		// 合同号
//						.append(",sum(fkb.money_de) fkje ")		// 付款金额
//						.append(" from ap_paybill fk ")
//						.append(" inner join ap_payitem fkb on fk.pk_paybill = fkb.pk_paybill ")
//						.append(" inner join ct_pu ht on fkb.top_billid = ht.pk_ct_pu ")
//						.append(" inner join ct_payplan fkjh on fkb.top_itemid = fkjh.pk_ct_payplan ")
//						.append(" left join ct_pu_b htb on (ht.pk_ct_pu = htb.pk_ct_pu and htb.dr = 0 and fkjh.crowno = htb.crowno) ")
//						.append(" where fk.dr = 0 and fkb.dr = 0 ")
//						.append(" and ht.dr = 0 and fkjh.dr = 0 ")
//						.append(" and ht.pk_org = '").append(pk_org).append("' ")
//						.append(" and fk.billyear || '-' ||fk.billperiod = '").append(yearMonth).append("' ")
//						.append(" group by ht.cvendorid, ht.vbillcode ")
//					.append(" ) ")
//			;
//			// 
//			ArrayList list_FK = (ArrayList)iUAPQueryBS.executeQuery(
//					querySQL_FK.toString()
//					,new ArrayListProcessor()
//			);
//			if(list_FK!=null&&list_FK.size()>0)
//			{
//				for( int i=0;i<list_FK.size();i++ )
//				{
//					Object[] obj = (Object[])list_FK.get(i);
//					// 对方##合同号
//					String key = obj[0]+"##"+obj[1];
//					
//					UFDouble skje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 付款金额
//					
//					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
//					if(yuebaoBVO!=null)
//					{
//						yuebaoBVO.setBqskje(skje);
//					}
//					else
//					{ /**
//					   * HK
//					   * 2019年11月14日11点15分
//					   * 将 无当期收入数据的 收款单，插入到表体。
//					   */
//						yuebaoBVO = new YuebaoBVO();
//						yuebaoBVO.setBqskje(skje);		// 本期收款金额
//						yuebaoBVO.setQmyskye(skje);		// 期末预收款余额 = 本期收款金额
//						
//						yuebaoBVO.setPk_cutomer(PuPubVO.getString_TrimZeroLenAsNull(obj[0]));	// 客户pk
//						yuebaoBVO.setVbdef10(PuPubVO.getString_TrimZeroLenAsNull(obj[1]));		// 合同号
//						
//						MAP_yuebaoBVO.put(key, yuebaoBVO);	// 放到缓存里
//					}
//				}
//			}
//		}
//		/***END***/
		
		/**
		 * 查询 上期的期末余额,当做本期的期初余额
		 */
		{
			String syqj = getYYYYMM(yearMonth,-1);	// 上月的期间
			
			StringBuffer querySQL_QC = 
				new StringBuffer(" select ")
						.append(" yb.pk_cutomer ")		// 客户pk
						.append(",yb.vbdef10 ")			// 合同号
						.append(",sum(yb.qmyskye) ")	// 期末余额
						
						.append(",max(yb.vbdef01) ")	// vbdef01 客户名称
						.append(",max(yb.vbdef02) ")	// vbdef02 房间号
						.append(",max(yb.vbdef03) ")	// vbdef03 收入项目
						.append(",max(yb.vbdef04) ")	// vbdef04 区域名称
						
						.append(",max(yb.vbdef05) ")	// vbdef05 部门pk
						.append(",max(yb.vbdef07) ")	// vbdef07 实际合同金额
						.append(",max(yb.quyu) ")		// 区域pk
						.append(",max(yb.mianji) ")		// 面积
						.append(",max(yb.danjia) ")		// 单价
						.append(",max(yb.srxm) ")		// 收入项目pk
						
						.append(" from hk_zulin_yuebao y ")
						.append(" inner join hk_zulin_yuebao_b yb on y.pk_hk_zulin_yuebao = yb.pk_hk_zulin_yuebao ")
						.append(" where y.dr=0 and yb.dr=0 ")
						.append(" and y.vbilltypecode = 'HK43' ")
						.append(" and y.yearmonth = '"+syqj+"' ")
						.append(" and y.pk_org = '"+pk_org+"' ")
						.append(" group by yb.pk_cutomer, yb.vbdef10 ")	// Group By  客户+房号
						.append(" having sum(yb.qmyskye)<>0.00 ")		// 取 余额不为0的
			;
			ArrayList list_QC = (ArrayList)iUAPQueryBS.executeQuery(
					querySQL_QC.toString()
					,new ArrayListProcessor()
			);
			if(list_QC!=null&&list_QC.size()>0)
			{
				for( int i=0;i<list_QC.size();i++ )
				{
					Object[] obj = (Object[])list_QC.get(i);
					
					String pk_cutomer = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					String vbdef10 = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					
					// 对方##合同号
					String key = pk_cutomer + "##" + vbdef10;
					
					UFDouble qcye = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 期初余额
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setQcyskye(qcye);
					}
					else
					{
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setPk_cutomer(pk_cutomer);		// 对方
//						yuebaoBVO.setRoomno(roomno);				// 房间
						yuebaoBVO.setQcyskye(qcye);					// 期初余额
						
						/**
						 *  .append(",max(yb.vbdef01) ")	// vbdef01 客户名称	3
							.append(",max(yb.vbdef02) ")	// vbdef02 房间号		4
							.append(",max(yb.vbdef03) ")	// vbdef03 收入项目	5
							.append(",max(yb.vbdef04) ")	// vbdef04 区域名称	6
							
							.append(",max(yb.vbdef05) ")	// vbdef05 部门pk		7
							.append(",max(yb.vbdef07) ")	// vbdef07 实际合同金额	8
							.append(",max(yb.quyu) ")		// 区域pk			9
							.append(",max(yb.mianji) ")		// 面积				10
							.append(",max(yb.danjia) ")		// 单价				11
							.append(",max(yb.srxm) ")		// 收入项目pk			12
						 */
						
						yuebaoBVO.setMianji(PuPubVO.getUFDouble_NullAsZero(obj[10]));			// 面积
						yuebaoBVO.setDanjia(PuPubVO.getUFDouble_NullAsZero(obj[11]));			// 单价
						yuebaoBVO.setSrxm(PuPubVO.getString_TrimZeroLenAsNull(obj[12]));		// 收入项目pk
						yuebaoBVO.setQuyu(PuPubVO.getString_TrimZeroLenAsNull(obj[9]));			// 区域
						yuebaoBVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(obj[3]));		// 对方名称
						yuebaoBVO.setVbdef02(PuPubVO.getString_TrimZeroLenAsNull(obj[4]));		// 房间号
						yuebaoBVO.setVbdef03(PuPubVO.getString_TrimZeroLenAsNull(obj[5]));		// 收入项目
						yuebaoBVO.setVbdef04(PuPubVO.getString_TrimZeroLenAsNull(obj[6]));		// 区域名称
						yuebaoBVO.setVbdef05(PuPubVO.getString_TrimZeroLenAsNull(obj[7]));		// 部门pk
						yuebaoBVO.setVbdef07(PuPubVO.getString_TrimZeroLenAsNull(obj[8]));		// 实际合同金额
						yuebaoBVO.setVbmemo("【上期有余额，本期无发生】");	// 备注
						
						MAP_yuebaoBVO.put(key, yuebaoBVO);
					}
				}
			}
		}
		/***END***/
		
		/**
		 *查询 本月调整数据
		 */
		{
			StringBuffer querySQL_TZ = 
				new StringBuffer(" select ")
					.append(" tzb.vbdef11 ")	// 对方pk
					.append(",tzb.vbdef12 ")	// 合同号
					.append(",tzb.dytzje ")
					.append(" from hk_zulin_tiaozheng tz ")
					.append(" inner join hk_zulin_tiaozheng_b tzb on tz.pk_hk_zulin_tiaozheng = tzb.pk_hk_zulin_tiaozheng ")
					.append(" where tz.dr=0 and tzb.dr=0 ")
					.append(" and tz.vbilltypecode = 'HK44' ")	// 单据类型过滤
					.append(" and tz.ibillstatus = 1 ")		// 只取 审核通过的
					.append(" and tz.yearmonth = '"+yearMonth+"' ")
					.append(" and tz.pk_org = '"+pk_org+"' ")
			;
			
			ArrayList list_TZ = (ArrayList)iUAPQueryBS.executeQuery(
					querySQL_TZ.toString()
					,new ArrayListProcessor()
			);
			if(list_TZ!=null&&list_TZ.size()>0)
			{
				for( int i=0;i<list_TZ.size();i++ )
				{
					Object[] obj = (Object[])list_TZ.get(i);
					
					String pk_cutomer = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					String roomno = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					
					// 对方##合同号
					String key = pk_cutomer+"##"+roomno;
					
					UFDouble tzje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 调整金额
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{// 表体自定义08 存储 调整金额
						yuebaoBVO.setVbdef08(tzje.toString());
					}
					else
					{// 如果发生数据 没有  则不做处理
						
					}
				}
			}
		}
		/***END***/
		
		YuebaoBVO[] bodyVOs = MAP_yuebaoBVO.values().toArray(new YuebaoBVO[0]);
		
		/**
		 * 数据整理
		 */
		for(YuebaoBVO bvo : bodyVOs)
		{
			// 期末余额 = 期初余额+本期付款-本期费用-本期调整
			UFDouble qmye = 
					 PuPubVO.getUFDouble_NullAsZero(bvo.getQcyskye())
				.add(PuPubVO.getUFDouble_NullAsZero(bvo.getBqskje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getVbdef08()))
			;
			bvo.setQmyskye(qmye);
			
			// 反算单价 = 总额/天/面积/
			if(bvo.getDysrqrje() != null && bvo.getMianji() != null && bvo.getDysrqrts() != null
			&& !UFDouble.ZERO_DBL.equals(bvo.getDysrqrts())	
			&& !UFDouble.ZERO_DBL.equals(bvo.getMianji())	
			)
			{
				UFDouble danjia = // 当月确认费用/天数/面积
						  bvo.getDysrqrje()
					.div( bvo.getDysrqrts() )
					.div( bvo.getMianji() )
					.setScale(8, UFDouble.ROUND_HALF_UP)
				;
				bvo.setDanjia(danjia);
			}
			
//			/**
//			 * 调差处理
//			 * 如果 截止日期不为空,计算结束日期不为空,并且期末余额不为0,并且 计算结束日期 = 截止日期， 则 将差额 加到 当月收入确认金额。
//			 */
//			if( bvo.getVbdef06()!=null
//			 && bvo.getJsjsrq()!=null
//			 && qmye.compareTo(UFDouble.ZERO_DBL)!=0
//			 && bvo.getVbdef06().equals(bvo.getJsjsrq().toString().substring(0,10))
//			)
//			{
//				if(
//					qmye.compareTo( PuPubVO.getUFDouble_NullAsZero(-1.0) ) >= 0
//				 && qmye.compareTo( PuPubVO.getUFDouble_NullAsZero( 1.0) ) <= 0
//				)
//				{// 差额在 -1 到 1 期间， 就自动调整。（HK-2019年1月27日17:25:30）
//					bvo.setDysrqrje( PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()).add(qmye) );
//					bvo.setQmyskye(UFDouble.ZERO_DBL);
//				}
//			}
			
		}
		/***END***/
		
		this.getEditor().getBillCardPanel().getBillModel().setBodyDataVO(bodyVOs);
		
	}
	
	/**
	 * 根据 yyyymm 返回 addMonth的 yyyy-mm
	 * month 可正可负
	 */
	public static String getYYYYMM(String yyyymm,int addMonth)
	{
		String result = null;
		
		String[] ym = yyyymm.split("-");
		int year  = PuPubVO.getInteger_NullAs(ym[0], 0);
		int month = PuPubVO.getInteger_NullAs(ym[1], 0);
		
		month = month + addMonth;
		
		if(month<=0)
		{
			year--;
			month+=12;
		}
		else if(month>12)
		{
			year++;
			month-=12;
		}
		
		result = ""+year+"-"+(month<10?"0":"")+month;
		
		return result;
	}
}
