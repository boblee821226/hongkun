package nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
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
 * 月报 取数
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
			new StringBuffer()
			.append(" select ")
			.append("  substr(ctb.vbdef3,1,10) ksrq ")
			.append(" ,substr(ctb.vbdef4,1,10) jsrq ")
			.append(" ,to_number(ctb.vbdef5) danjia ")
			.append(" ,to_number(ctb.vbdef6) mianji ")	
			.append(" ,ct.pk_customer pk_customer ")	// 客户pk
			.append(" ,ct.vdef16 pk_room ")				// 房间号pk （vdef16）
			.append(" ,ctb.vbdef1 pk_srxm ")			// 收入项目pk
			.append(" ,ct.vdef15 pk_quyu ")		// 区域pk（vdef15）
			.append(" ,cust.name vdef01 ")		// 客户-name
			.append(" ,room.name vdef02 ")		// 房间-name
			.append(" ,srxm.name vdef03 ")		// 收入项目-name
			.append(" ,quyu.name vdef04 ")		// 区域-name
			.append(" ,ct.vbillcode vdef10")	// 合同号
//			.append(" ,substr(nvl(ct.actualinvalidate,'2099-12-31'),1,10) zzrq ")		// 合同终止日期
			.append(" ,substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) zzrq ")	// 退租日期
			.append(" ,ct.depid vdef05 ")		// 合同的部门pk
			.append(" ,st.实际合同金额 vdef07 ")	// 实际合同金额
			.append(" from ct_sale ct ")
			.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
			.append(" left join bd_customer cust on ct.pk_customer = cust.pk_customer ")	// 客户
			.append(" left join bd_defdoc srxm on ctb.vbdef1  = srxm.pk_defdoc ")	// 收入项目
			.append(" left join bd_defdoc room on ct.vdef16 = room.pk_defdoc ")	// 房号
			.append(" left join bd_defdoc quyu on ct.vdef15 = quyu.pk_defdoc ")	// 区域
			.append(" left join ( ")
			.append("	select ")
			.append("	 st.pk_ct_sale ")
			.append("	,sum( to_number(replace(nvl(st.vhkbdef6,'~'),'~','0')) ) 实际合同金额 ")
			.append("	from ct_sale_term st ")
			.append("	where st.dr=0 ")
			.append("	group by st.pk_ct_sale ")
			.append(" ) st on st.pk_ct_sale = ct.pk_ct_sale ")
			.append(" where ct.dr = 0 and ctb.dr = 0 ")
			.append(" and ct.pk_org = '"+pk_org+"' ")
			.append(" and ct.blatest = 'Y' ")
			.append(" and srxm.name not like '%押金%' ")	// 不取 押金 行
			.append(" and srxm.name not like '%调整%' ")	// 不取 调整 行
			.append(" and ( ")
			.append("		'"+str_yb_ksrq+"' between substr(ctb.vbdef3,1,10) and substr(ctb.vbdef4,1,10) ")
			.append("    or ")
			.append("		'"+str_yb_jsrq+"' between substr(ctb.vbdef3,1,10) and substr(ctb.vbdef4,1,10) ")
			.append("     ) ")
			
			.append(" and ct.fstatusflag in (1,6) ")	// 取 生效 和 终止
//			.append(" and '"+str_yb_ksrq+"' < substr(nvl(ct.actualinvalidate,'2099-12-31'),1,10) ")		// 用实际终止日期 来 判断计费时点
			.append(" and '"+str_yb_ksrq+"' <= substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// 用退租日期 来 判断计费时点（退租的那天 要算租金的）
			.append(" and substr(ctb.vbdef3,1,10)<=substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// 合同明细的开始日期 要小于等于 退租日期
			
//			.append(" and ct.vbillcode = '201809111-6-50011-6-50021-6-5003' ")		// 测试
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
			
			UFDate ht_ksrq = htVO.getKsrq();	// 合同明细-开始日期
			UFDate ht_jsrq = htVO.getJsrq();	// 合同明细-结束日期
			UFDate ht_zzrq = htVO.getZzrq();	// 合同表头-终止日期
			UFDouble ht_danjia = htVO.getDanjia();	// 单价
			UFDouble ht_mianji = htVO.getMianji();	// 面积
			
			String ht_pk_customer = htVO.getPk_customer();	// 客户pk
			String ht_pk_room = htVO.getPk_room();			// 房间号pk
			
			String srxm_name = htVO.getVdef03();	// 收入项目-名称
			
			Integer yb_days = 0;
			
			UFDate js_ksrq = null;		// 计算开始日期
			UFDate js_jsrq = null;		// 计算结束日期
			
			/**
			 *  分析 合同明细 与 月报的 开始结束日期  之间的 大小关系， 并计算出 天数
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
			if(js_jsrq.compareTo(ht_zzrq)>0)
			{
				js_jsrq = ht_zzrq;
				htVO.setYb_jsrq(js_jsrq);
				yb_days = js_jsrq.getDaysAfter(js_ksrq)+1;
				htVO.setYb_days(yb_days);
			}
			
			//计算  收入 = 单价 * 面积 * 天数
			UFDouble yb_mny = ht_danjia.multiply(ht_mianji).multiply(yb_days).setScale(2, UFDouble.ROUND_HALF_UP);
			htVO.setYb_mny(yb_mny);
			
			// 同时 对 YuebaoBVO 进行封装处理
			// 按 客户##房间号##单价  汇总
			// 改为  按 客户pk##房号pk  汇总
			{
//				String key = ht_pk_customer + "##" + ht_pk_room + "##" + ht_danjia.toString();
				String key = ht_pk_customer + "##" + ht_pk_room;
				YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
				if(yuebaoBVO==null)
				{
					yuebaoBVO = new YuebaoBVO();
					yuebaoBVO.setPk_cutomer(ht_pk_customer);	// 客户
					yuebaoBVO.setRoomno(ht_pk_room);			// 房间
					yuebaoBVO.setJsksrq(js_ksrq);				// 计算开始日期
					yuebaoBVO.setJsjsrq(js_jsrq);				// 计算结束日期
					yuebaoBVO.setDysrqrts(PuPubVO.getUFDouble_NullAsZero(yb_days));	// 当月收入确认天数
					yuebaoBVO.setDysrqrje(yb_mny);				// 当月收入确认金额
					yuebaoBVO.setMianji(ht_mianji);				// 面积
					yuebaoBVO.setDanjia(ht_danjia);				// 单价
					
					yuebaoBVO.setSrxm(htVO.getPk_srxm());		// 收入项目
					yuebaoBVO.setQuyu(htVO.getPk_quyu());		// 区域
					yuebaoBVO.setVbdef01(htVO.getVdef01());
					yuebaoBVO.setVbdef02(htVO.getVdef02());
					yuebaoBVO.setVbdef03(htVO.getVdef03());
					yuebaoBVO.setVbdef04(htVO.getVdef04());
					yuebaoBVO.setVbdef05(htVO.getVdef05());
//					yuebaoBVO.setVbmemo(htVO.getVdef10());		// 备注存放合同号
					yuebaoBVO.setVbdef06( ht_zzrq!=null ? ht_zzrq.toString().substring(0,10) : null );	// 合同终止日期  （HK 2018年12月26日17:39:31）
					yuebaoBVO.setVbdef07(htVO.getVdef07());		// 实际合同金额
				}
				else
				{
					yuebaoBVO.setDysrqrts(
							yuebaoBVO.getDysrqrts().add(
							PuPubVO.getUFDouble_NullAsZero(yb_days) )
					);	// 当月收入确认天数
					yuebaoBVO.setDysrqrje(
							yuebaoBVO.getDysrqrje().add(
							yb_mny )
					);	// 当月收入确认金额
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
		
		/**
		 * 查询 调整的数据
		 */
		{
			StringBuffer querySQL_TZ = 
				new StringBuffer()
				.append(" select ")
				.append("  substr(ctb.vbdef3,1,10) ksrq ")	// 开始日期
				.append(" ,ct.pk_customer pk_customer ")	// 客户pk
				.append(" ,ct.vdef16 pk_room ")				// 房间号pk （vdef16）
				.append(" ,ctb.vbdef1 pk_srxm ")			// 收入项目pk
				.append(" ,ctb.norigtaxmny danjia ")		// 价税合计 （用 单价字段 来暂存）
				.append(" ,srxm.name vdef03 ")				// 收入项目-name
				.append(" from ct_sale ct ")
				.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
				.append(" left join bd_customer cust on ct.pk_customer = cust.pk_customer ")	// 客户
				.append(" left join bd_defdoc srxm on ctb.vbdef1  = srxm.pk_defdoc ")			// 收入项目
				.append(" where ct.dr = 0 and ctb.dr = 0 ")
				.append(" and ct.pk_org = '"+pk_org+"' ")
				.append(" and ct.blatest = 'Y' ")
				.append(" and srxm.name like '%调整%' ")	// 只取 调整 行
				.append(" and ( ")
				.append("		'"+str_yb_ksrq+"' between substr(ctb.vbdef3,1,10) and substr(ctb.vbdef4,1,10) ")
				.append("    or ")
				.append("		'"+str_yb_jsrq+"' between substr(ctb.vbdef3,1,10) and substr(ctb.vbdef4,1,10) ")
				.append("     ) ")
				.append(" and ct.fstatusflag in (1,6) ")	// 取 生效 和 终止
				.append(" and '"+str_yb_ksrq+"' < substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// 用退租日期 来 判断计费时点
				.append(" and substr(ctb.vbdef3,1,10)<=substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// 合同明细的开始日期 要小于等于 退租日期
			;
			
			ArrayList<QueryHtVO> list_TZ = (ArrayList<QueryHtVO>)iUAPQueryBS.executeQuery(
					 querySQL_TZ.toString()
					,new BeanListProcessor(QueryHtVO.class)
			);
				
			if(list_TZ!=null&&list_TZ.size()>0)
			{
				for(int i=0;i<list_TZ.size();i++)
				{
					QueryHtVO queryHtVO = list_TZ.get(i);
					String key = queryHtVO.getPk_customer() + "##" + queryHtVO.getPk_room();
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						// 当月确认收入 叠加
						yuebaoBVO.setDysrqrje(
								 yuebaoBVO.getDysrqrje()
							.add(queryHtVO.getDanjia())
						);
						
						yuebaoBVO.setDanjia(null);	// 将单价 赋值成null， 后面 重新计算单价
						
						String vbmemo = 
								"【" +
								queryHtVO.getVdef03() + "、" +
								queryHtVO.getKsrq().toString().substring(0,10)   + "、" +
								queryHtVO.getDanjia() + "" +
								"】 "
							;
						
						yuebaoBVO.setVbmemo(
							yuebaoBVO.getVbmemo()==null
							? vbmemo
							: yuebaoBVO.getVbmemo()+vbmemo
						);
					}
				}
			}
		}
		
		/**
		 * 查询 本期收款金额。
		 */
		{
			StringBuffer querySQL_SK = 
				new StringBuffer(" select ")
						.append(" a.pk_customer ")				// 客户pk
						.append(",a.pk_room ")					// 房间pk
						.append(",sum(a.skje) skje ")			// 收款金额
						.append(",max(a.pk_quyu) pk_quyu ")		// 区域pk
						.append(",max(a.pk_dept) pk_dept ")		// 部门pk
						.append(",max(cust.name) vdef01 ")		// 客户-name
						.append(",max(room.name) vdef02 ")		// 房间-name
						.append(",max(quyu.name) vdef04 ")		// 区域-name
						.append(" from (")
						// 蓝字收款单
							.append(" select ")
							.append(" s.pk_customer ")			// 客户pk
							.append(",s.vdef16 pk_room ")		// 房号pk
							.append(",max(s.vdef15) pk_quyu ")	// 区域pk
							.append(",max(s.depid)  pk_dept ")	// 部门pk
							.append(",sum(gb.money_cr) skje ")	// 收款金额
							.append(" from ar_gatherbill g ")
							.append(" inner join ar_gatheritem gb on g.pk_gatherbill = gb.pk_gatherbill ")
							.append(" left join ct_sale s on gb.top_billid = s.pk_ct_sale ")
							.append(" left join ct_sale_b sb on gb.top_itemid = sb.pk_ct_sale_b ")
							.append(" left join bd_defdoc srxm on sb.vbdef1  = srxm.pk_defdoc ")	// 收入项目
							.append(" where g.dr=0 and gb.dr=0 ")
							.append(" and gb.top_tradetype = 'Z3-01' ")		// 租赁合同
							.append(" and srxm.name not like '%押金%' ")		// 不取押金的收款
							.append(" and substr(gb.busidate,1,10) between '"+str_yb_ksrq+"' and '"+str_yb_jsrq+"' ")
							.append(" and s.pk_org = '"+pk_org+"' ")
							.append(" group by s.pk_customer,s.vdef16 ")	// Group By  客户+房号
						// 红冲收款单
						.append(" union all ")
							.append(" select ")
							.append(" s.pk_customer ")			// 客户pk
							.append(",s.vdef16 pk_room ")		// 房号pk
							.append(",max(s.vdef15) pk_quyu ")	// 区域pk
							.append(",max(s.depid)  pk_dept ")	// 部门pk
							.append(",sum(gb.money_cr) skje ")	// 收款金额
							.append(" from ar_gatherbill g ")
							.append(" inner join ar_gatheritem gb on g.pk_gatherbill = gb.pk_gatherbill ")
							.append(" left join ar_gatheritem gb_lan ")
							.append(" on gb.top_itemid = gb_lan.pk_gatheritem and gb.top_billid = gb_lan.pk_gatherbill and gb_lan.top_tradetype = 'Z3-01' ")
							.append(" left join ct_sale s on gb_lan.top_billid = s.pk_ct_sale ")
							.append(" left join ct_sale_b sb on gb_lan.top_itemid = sb.pk_ct_sale_b ")
							.append(" left join bd_defdoc srxm on sb.vbdef1  = srxm.pk_defdoc ")	// 收入项目
							.append(" where g.dr=0 and gb.dr=0 ")
							.append(" and gb.top_tradetype = 'F2-Cxx-01' ")	// 预收单
							.append(" and srxm.name not like '%押金%' ")		// 不取押金的收款
							.append(" and substr(gb.busidate,1,10) between '"+str_yb_ksrq+"' and '"+str_yb_jsrq+"' ")
							.append(" and s.pk_org = '"+pk_org+"' ")
							.append(" group by s.pk_customer,s.vdef16 ")	// Group By  客户+房号
						.append(" ) a ")
						.append(" left join bd_customer cust on a.pk_customer = cust.pk_customer ")	// 客户
						.append(" left join bd_defdoc room on a.pk_room = room.pk_defdoc ")			// 房号
						.append(" left join bd_defdoc quyu on a.pk_quyu = quyu.pk_defdoc ")			// 区域
						.append(" group by a.pk_customer,a.pk_room  ")
			;
			ArrayList list_SK = (ArrayList)iUAPQueryBS.executeQuery(
					querySQL_SK.toString()
					,new ArrayListProcessor()
			);
			if(list_SK!=null&&list_SK.size()>0)
			{
				for( int i=0;i<list_SK.size();i++ )
				{
					Object[] obj = (Object[])list_SK.get(i);
					// 客户##房间号
					String key = obj[0]+"##"+obj[1];
					
					UFDouble skje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 收款金额
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setBqskje(skje);
					}
					else
					{ /**
					   * HK
					   * 2018年12月25日18:00:53
					   * 将 无当期收入数据的 收款单，插入到表体。
					   */
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setBqskje(skje);		// 本期收款金额
						yuebaoBVO.setQmyskye(skje);		// 期末预收款余额 = 本期收款金额
						
						yuebaoBVO.setPk_cutomer(PuPubVO.getString_TrimZeroLenAsNull(obj[0]));	// 客户pk
						yuebaoBVO.setRoomno(PuPubVO.getString_TrimZeroLenAsNull(obj[1]));		// 房间pk
						yuebaoBVO.setQuyu(PuPubVO.getString_TrimZeroLenAsNull(obj[3]));			// 区域pk
						yuebaoBVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(obj[5]));		// 客户-name
						yuebaoBVO.setVbdef02(PuPubVO.getString_TrimZeroLenAsNull(obj[6]));		// 房间-name
						yuebaoBVO.setVbdef04(PuPubVO.getString_TrimZeroLenAsNull(obj[7]));		// 区域-name
						yuebaoBVO.setVbdef05(PuPubVO.getString_TrimZeroLenAsNull(obj[4]));		// 部门pk
						
						MAP_yuebaoBVO.put(key, yuebaoBVO);	// 放到缓存里
					}
				}
			}
		}
		/***END***/
		
		/**
		 * 查询 上期的期末余额,当做本期的期初余额
		 */
		{
			String syqj = getYYYYMM(yearMonth,-1);	// 上月的期间
			
			StringBuffer querySQL_QC = 
				new StringBuffer(" select ")
						.append(" yb.pk_cutomer ")		// 客户pk
						.append(",yb.roomno ")			// 房间pk
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
						.append(" and y.yearmonth = '"+syqj+"' ")
						.append(" and y.pk_org = '"+pk_org+"' ")
						.append(" group by yb.pk_cutomer,yb.roomno ")	// Group By  客户+房号
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
					String roomno = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					
					// 客户##房间号
					String key = pk_cutomer+"##"+roomno;
					
					UFDouble qcye = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 期初余额
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setQcyskye(qcye);
					}
					else
					{
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setPk_cutomer(pk_cutomer);		// 客户
						yuebaoBVO.setRoomno(roomno);				// 房间
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
						yuebaoBVO.setSrxm(PuPubVO.getString_TrimZeroLenAsNull(obj[12]));			// 收入项目pk
						yuebaoBVO.setQuyu(PuPubVO.getString_TrimZeroLenAsNull(obj[9]));			// 区域
						yuebaoBVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(obj[3]));		// 客户名称
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
					.append(" tzb.vbdef11 ")
					.append(",tzb.vbdef12 ")
					.append(",tzb.dytzje ")
					.append(" from hk_zulin_tiaozheng tz ")
					.append(" inner join hk_zulin_tiaozheng_b tzb on tz.pk_hk_zulin_tiaozheng = tzb.pk_hk_zulin_tiaozheng ")
					.append(" where tz.dr=0 and tzb.dr=0 ")
					.append(" and tz.vbilltypecode = 'HK38' ")	// 单据类型过滤
					.append(" and tz.ibillstatus = 1 ")			// 只取 审核通过的
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
					
					// 客户##房间号
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
		
		YuebaoBVO[] bodyVOs = new YuebaoBVO[MAP_yuebaoBVO.size()];
		
		bodyVOs = MAP_yuebaoBVO.values().toArray(bodyVOs);
		
		/**
		 * 数据整理
		 */
		for(YuebaoBVO bvo : bodyVOs)
		{
			// 期末余额 = 期初余额+本期收款-本期收入-本期调整
			UFDouble qmye = 
					 PuPubVO.getUFDouble_NullAsZero(bvo.getQcyskye())
				.add(PuPubVO.getUFDouble_NullAsZero(bvo.getBqskje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getVbdef08()))
			;
			bvo.setQmyskye(qmye);
			
			// 单价为空的，需要反算单价
			if(bvo.getDanjia()==null && bvo.getDysrqrje()!=null)
			{
				UFDouble danjia = // 当月确认收入/天数/面积
						  bvo.getDysrqrje()
					.div( bvo.getDysrqrts() )
					.div( bvo.getMianji() )
					.setScale(8, UFDouble.ROUND_HALF_UP)
				;
				bvo.setDanjia(danjia);
			}
			
			/**
			 * 调差处理
			 * 如果 截止日期不为空,计算结束日期不为空,并且期末余额不为0,并且 计算结束日期 = 截止日期， 则 将差额 加到 当月收入确认金额。
			 */
			if( bvo.getVbdef06()!=null
			 && bvo.getJsjsrq()!=null
			 && qmye.compareTo(UFDouble.ZERO_DBL)!=0
			 && bvo.getVbdef06().equals(bvo.getJsjsrq().toString().substring(0,10))
			)
			{
				if(
					qmye.compareTo( PuPubVO.getUFDouble_NullAsZero(-1.0) ) >= 0
				 && qmye.compareTo( PuPubVO.getUFDouble_NullAsZero( 1.0) ) <= 0
				)
				{// 差额在 -1 到 1 期间， 就自动调整。（HK-2019年1月27日17:25:30）
					bvo.setDysrqrje( PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()).add(qmye) );
					bvo.setQmyskye(UFDouble.ZERO_DBL);
				}
			}
			
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
