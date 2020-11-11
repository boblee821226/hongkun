package nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.zulin.yuebao.QueryHtVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoBVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 月报 上期取数
 *
 */
public class SqQushuAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public SqQushuAction() {
		setBtnName("上期取数");
		setCode("sqqushuAction");
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
		
		String input_hth = JOptionPane.showInputDialog(this.getEditor(), "请输入合同号（逗号分隔多个合同）");
		input_hth = PuPubVO.getString_TrimZeroLenAsNull(input_hth);
		if(input_hth==null)
		{
			return;
		}
		
		input_hth = input_hth.replace("，", ",");	// 全都都替换成 半角逗号
		String[] str_hth = input_hth.split(",");
		
		String str_hth_where = " (1=2) ";
		
		for(String hth : str_hth)
		{
			str_hth_where += " or ct.vbillcode like '%" + hth + "%' ";
		}
		
//		this.getEditor().getBillCardPanel().getBillModel().clearBodyData();	// 清空表体
		
//		String yearMonth = PuPubVO.getString_TrimZeroLenAsNull(
//				this.getEditor().getBillCardPanel().getHeadItem("yearmonth").getValueObject()
//			);
		
		UFDate temp_yb_ksrq = 
				PuPubVO.getUFDate(
						this.getEditor().getBillCardPanel().getHeadItem("begindate").getValueObject().toString().substring(0, 10) );
		
		UFDate temp_yb_jsrq = 
				PuPubVO.getUFDate(
						this.getEditor().getBillCardPanel().getHeadItem("enddate").getValueObject().toString().substring(0, 10) );
		
		UFDate yb_ksrq = getDateAddMM(temp_yb_ksrq,-1);
		UFDate yb_jsrq = getDateAddMM(temp_yb_jsrq,-1);
		
		// 根据 表头的日期，取 上月的日期
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
			.append(" from ct_sale ct ")
			.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
			.append(" left join bd_customer cust on ct.pk_customer = cust.pk_customer ")	// 客户
			.append(" left join bd_defdoc srxm on ctb.vbdef1  = srxm.pk_defdoc ")	// 收入项目
			.append(" left join bd_defdoc room on ct.vdef16 = room.pk_defdoc ")	// 房号
			.append(" left join bd_defdoc quyu on ct.vdef15 = quyu.pk_defdoc ")	// 区域
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
//			.append(" and '"+str_yb_ksrq+"' < substr(nvl(ct.actualinvalidate,'2099-12-31'),1,10) ")			// 用实际终止日期 来 判断计费时点
			.append(" and '"+str_yb_ksrq+"' <= substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// 用退租日期 来 判断计费时点
			.append(" and substr(ctb.vbdef3,1,10)<=substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// 合同明细的开始日期 要小于等于 退租日期
			
			.append(" and ("+str_hth_where+") ")		// 退租的合同号
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
			
			Integer yb_days = 0;
			
			UFDate js_ksrq = null;		// 计算开始日期
			UFDate js_jsrq = null;		// 计算结束日期
			
			String srxm_name = htVO.getVdef03();	// 收入项目-名称
			
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
			// 按 客户+房间号  汇总
			{
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
					
					yuebaoBVO.setSrxm(htVO.getPk_srxm());	// 收入项目
					yuebaoBVO.setQuyu(htVO.getPk_quyu());	// 区域
					yuebaoBVO.setVbdef01(htVO.getVdef01());
					yuebaoBVO.setVbdef02(htVO.getVdef02());
					yuebaoBVO.setVbdef03(htVO.getVdef03());
					yuebaoBVO.setVbdef04(htVO.getVdef04());
					yuebaoBVO.setVbdef05(htVO.getVdef05());
//					yuebaoBVO.setVbmemo(htVO.getVdef10());	// 备注存放合同号
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
					
//					// 测试
//					yuebaoBVO.setVbmemo(
//							htVO.getVdef10() + "、" +
//							yuebaoBVO.getVbmemo() + "、"
//					);	// 备注存放合同号
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
				.append(" ,srxm.name vdef03 ")		// 收入项目-name
				.append(" from ct_sale ct ")
				.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
				.append(" left join bd_customer cust on ct.pk_customer = cust.pk_customer ")	// 客户
				.append(" left join bd_defdoc srxm on ctb.vbdef1  = srxm.pk_defdoc ")	// 收入项目
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
				.append(" and '"+str_yb_ksrq+"' <= substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// 用退租日期 来 判断计费时点
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
						
//						vbmemo = "";	// 测试
						
						yuebaoBVO.setVbmemo(
							yuebaoBVO.getVbmemo()==null
							? vbmemo
							: yuebaoBVO.getVbmemo()+vbmemo
						);
					}
				}
			}
		}
		
//		/**
//		 * 查询 本期收款金额。
//		 */
//		{
//			StringBuffer querySQL_SK = 
//				new StringBuffer(" select ")
//						.append(" pk_customer ")
//						.append(",pk_room ")
//						.append(",sum(skje) skje ")
//						.append(" from (")
//						// 蓝字收款单
//							.append(" select ")
//							.append(" s.pk_customer ")			// 客户pk
//							.append(",s.vdef16 pk_room ")		// 房号pk
//							.append(",sum(gb.money_cr) skje ")	// 收款金额
//							.append(" from ar_gatherbill g ")
//							.append(" inner join ar_gatheritem gb on g.pk_gatherbill = gb.pk_gatherbill ")
//							// 取 预收单 关联 合同 改为 通过顶层pk。而不是通过来源pk。 解决红冲的问题。(HK 2018年12月24日19:58:39)
//	//						.append(" left join ct_sale s on gb.src_billid = s.pk_ct_sale ")
//	//						.append(" left join ct_sale_b sb on gb.src_itemid = sb.pk_ct_sale_b ")
//							.append(" left join ct_sale s on gb.top_billid = s.pk_ct_sale ")
//							.append(" left join ct_sale_b sb on gb.top_itemid = sb.pk_ct_sale_b ")
//							.append(" left join bd_defdoc srxm on sb.vbdef1  = srxm.pk_defdoc ")	// 收入项目
//							.append(" where g.dr=0 and gb.dr=0 ")
//	//						.append(" and gb.src_tradetype = 'Z3-01' ")		// 租赁合同
//							.append(" and gb.top_tradetype = 'Z3-01' ")		// 租赁合同
////								.append(" and srxm.name  like '%押金%' ")		// 不取押金的收款
//							.append(" and srxm.name not like '%押金%' ")		// 不取押金的收款
//							.append(" and substr(gb.busidate,1,10) between '"+str_yb_ksrq+"' and '"+str_yb_jsrq+"' ")
//							.append(" and s.pk_org = '"+pk_org+"' ")
//							.append(" group by s.pk_customer,s.vdef16 ")	// Group By  客户+房号
//						// 红冲收款单
//						.append(" union all ")
//							.append(" select ")
//							.append(" s.pk_customer ")			// 客户pk
//							.append(",s.vdef16 pk_room ")		// 房号pk
//							.append(",sum(gb.money_cr) skje ")	// 收款金额
//							.append(" from ar_gatherbill g ")
//							.append(" inner join ar_gatheritem gb on g.pk_gatherbill = gb.pk_gatherbill ")
//							.append(" left join ar_gatheritem gb_lan ")
//							.append(" on gb.top_itemid = gb_lan.pk_gatheritem and gb.top_billid = gb_lan.pk_gatherbill and gb_lan.top_tradetype = 'Z3-01' ")
//							.append(" left join ct_sale s on gb_lan.top_billid = s.pk_ct_sale ")
//							.append(" left join ct_sale_b sb on gb_lan.top_itemid = sb.pk_ct_sale_b ")
//							.append(" left join bd_defdoc srxm on sb.vbdef1  = srxm.pk_defdoc ")	// 收入项目
//							.append(" where g.dr=0 and gb.dr=0 ")
//							.append(" and gb.top_tradetype = 'F2-Cxx-01' ")	// 预收单
////								.append(" and srxm.name  like '%押金%' ")		// 不取押金的收款
//							.append(" and srxm.name not like '%押金%' ")		// 不取押金的收款
//							.append(" and substr(gb.busidate,1,10) between '"+str_yb_ksrq+"' and '"+str_yb_jsrq+"' ")
//							.append(" and s.pk_org = '"+pk_org+"' ")
//							.append(" group by s.pk_customer,s.vdef16 ")	// Group By  客户+房号
//						.append(" ) ")
//						.append(" group by pk_customer,pk_room  ")
//			;
//			ArrayList list_SK = (ArrayList)iUAPQueryBS.executeQuery(
//					querySQL_SK.toString()
//					,new ArrayListProcessor()
//			);
//			if(list_SK!=null&&list_SK.size()>0)
//			{
//				for( int i=0;i<list_SK.size();i++ )
//				{
//					Object[] obj = (Object[])list_SK.get(i);
//					// 客户##房间号
//					String key = obj[0]+"##"+obj[1];
//					
//					UFDouble skje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 收款金额
//					
//					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
//					if(yuebaoBVO!=null)
//					{
//						yuebaoBVO.setBqskje(skje);
//					}
//				}
//			}
//		}
//		/***END***/
		
		YuebaoBVO[] bodyVOs = new YuebaoBVO[MAP_yuebaoBVO.size()];
		
		bodyVOs = MAP_yuebaoBVO.values().toArray(bodyVOs);
		
//		/**
//		 * 数据整理
//		 */
//		for(YuebaoBVO bvo : bodyVOs)
//		{
//			// 期末余额 = 期初余额+本期收款-本期收入
//			UFDouble qmye = 
//					 PuPubVO.getUFDouble_NullAsZero(bvo.getQcyskye())
//				.add(PuPubVO.getUFDouble_NullAsZero(bvo.getBqskje()))
//				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje())
//			);
//			bvo.setQmyskye(qmye);
//			
//			// 单价为空的，需要反算单价
//			if(bvo.getDanjia()==null)
//			{
//				UFDouble danjia = // 当月确认收入/天数/面积
//						  bvo.getDysrqrje()
//					.div( bvo.getDysrqrts() )
//					.div( bvo.getMianji() )
//					.setScale(8, UFDouble.ROUND_HALF_UP)
//				;
//				bvo.setDanjia(danjia);
//			}
//		}
//		/***END***/
		
//		this.getEditor().getBillCardPanel().getBillModel().setBodyDataVO(bodyVOs);
		
//		
//		/** 
//		 * 将数据 叠加到 表体上
//		 */
//		YuebaoBVO[] yuebaoBVOs = (YuebaoBVO[])this.getEditor().getBillCardPanel().getBillModel().getBodyValueVOs(YuebaoBVO.class.getName());
//		for(YuebaoBVO bodyVO : bodyVOs)
//		{
//			String key = bodyVO.getPk_cutomer() + "##" + bodyVO.getRoomno();	// 客户##房号
//			for(int i=0;yuebaoBVOs!=null&&i<yuebaoBVOs.length;i++)
//			{
//				String key_temp = yuebaoBVOs[i].getPk_cutomer() + "##" + yuebaoBVOs[i].getRoomno();
//				if(key.equals(key_temp))
//				{
//					yuebaoBVOs[i].setJsksrq(bodyVO.getJsksrq());
//					yuebaoBVOs[i].setVbmemo(bodyVO.getVbmemo()+yuebaoBVOs[i].getVbmemo());
//					yuebaoBVOs[i].setDysrqrts( bodyVO.getDysrqrts().add(yuebaoBVOs[i].getDysrqrts()) );
//					yuebaoBVOs[i].setDysrqrje( bodyVO.getDysrqrje().add(yuebaoBVOs[i].getDysrqrje()) );
//					// 重算 期末收款余额（将之前余额 减去 上期收入）
//					yuebaoBVOs[i].setQmyskye(yuebaoBVOs[i].getQmyskye().sub(bodyVO.getDysrqrje()));
//				}
//			}
//		}
		
		/** 
		 * 将数据 叠加到 表体上
		 */
		int rowCount = this.getEditor().getBillCardPanel().getBillModel().getRowCount();
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		
		for(YuebaoBVO bodyVO : bodyVOs)
		{
			String key = bodyVO.getPk_cutomer() + "##" + bodyVO.getRoomno();	// 客户##房号
			for(int i=0;i<rowCount;i++)
			{
				String key_temp = 
						         PuPubVO.getString_TrimZeroLenAsNull(billModel.getValueAt(i, "pk_cutomer"))
						+ "##" + PuPubVO.getString_TrimZeroLenAsNull(billModel.getValueAt(i, "roomno"));
				if(key.equals(key_temp))
				{
					billModel.setValueAt(bodyVO.getJsksrq(), i, "jsksrq");
//					yuebaoBVOs[i].setVbmemo(bodyVO.getVbmemo()+yuebaoBVOs[i].getVbmemo());
					billModel.setValueAt(bodyVO.getVbmemo()+billModel.getValueAt(i,"vbmemo"), i, "vbmemo");
//					yuebaoBVOs[i].setDysrqrts( bodyVO.getDysrqrts().add(yuebaoBVOs[i].getDysrqrts()) );
					billModel.setValueAt(bodyVO.getDysrqrts().add(PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i,"dysrqrts"))), i, "dysrqrts");
//					yuebaoBVOs[i].setDysrqrje( bodyVO.getDysrqrje().add(yuebaoBVOs[i].getDysrqrje()) );
					billModel.setValueAt(bodyVO.getDysrqrje().add(PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i,"dysrqrje"))), i, "dysrqrje");
//					// 重算 期末收款余额（将之前余额 减去 上期收入）
//					yuebaoBVOs[i].setQmyskye(yuebaoBVOs[i].getQmyskye().sub(bodyVO.getDysrqrje()));
					billModel.setValueAt(PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i,"qmyskye")).sub(bodyVO.getDysrqrje()), i, "qmyskye");
					
					break;
				}
			}
		}
		/***END***/
		
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
	
	/**
	 * 根据 UFDate 返回 addMonth的 strDate
	 * month 可正可负
	 */
	public static UFDate getDateAddMM(UFDate date,int addMonth)
	{
		UFDate result = null;
		
		int year   = date.getYear();
		int month  = date.getMonth();
		String day = date.getStrDay();
		
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
		
		result = new UFDate(""+year+"-"+(month<10?"0":"")+month+"-"+day);
		
		return result;
	}
}
