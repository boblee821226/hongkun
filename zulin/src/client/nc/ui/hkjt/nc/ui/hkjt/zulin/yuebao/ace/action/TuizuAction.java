package nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

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
 * 退租查询，输入 合同号来查询，取 开始日期，不去 结束日期，来解决跨月的问题。 
 *
 */
public class TuizuAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public TuizuAction() {
		setBtnName("退租查询");
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
		
		this.getEditor().getBillCardPanel().getBillModel().clearBodyData();	// 清空表体
		
		String yearMonth = PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("yearmonth").getValueObject()
			);
		
		UFDate yb_ksrq = 
				PuPubVO.getUFDate(
						this.getEditor().getBillCardPanel().getHeadItem("begindate").getValueObject() );
		
		UFDate yb_jsrq = yb_ksrq.getDateAfter(180);	// 默认取 开始日期后的180天
		
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
					
//					yuebaoBVO.setVbmemo(
//							yuebaoBVO.getVbmemo() + 
//							"、" +
//							htVO.getVdef10()
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
						.append(" pk_customer ")
						.append(",pk_room ")
						.append(",sum(skje) skje ")
						.append(" from (")
						// 蓝字收款单
							.append(" select ")
							.append(" s.pk_customer ")			// 客户pk
							.append(",s.vdef16 pk_room ")		// 房号pk
							.append(",sum(gb.money_cr) skje ")	// 收款金额
							.append(" from ar_gatherbill g ")
							.append(" inner join ar_gatheritem gb on g.pk_gatherbill = gb.pk_gatherbill ")
							// 取 预收单 关联 合同 改为 通过顶层pk。而不是通过来源pk。 解决红冲的问题。(HK 2018年12月24日19:58:39)
	//						.append(" left join ct_sale s on gb.src_billid = s.pk_ct_sale ")
	//						.append(" left join ct_sale_b sb on gb.src_itemid = sb.pk_ct_sale_b ")
							.append(" left join ct_sale s on gb.top_billid = s.pk_ct_sale ")
							.append(" left join ct_sale_b sb on gb.top_itemid = sb.pk_ct_sale_b ")
							.append(" left join bd_defdoc srxm on sb.vbdef1  = srxm.pk_defdoc ")	// 收入项目
							.append(" where g.dr=0 and gb.dr=0 ")
	//						.append(" and gb.src_tradetype = 'Z3-01' ")		// 租赁合同
							.append(" and gb.top_tradetype = 'Z3-01' ")		// 租赁合同
//								.append(" and srxm.name  like '%押金%' ")		// 不取押金的收款
							.append(" and srxm.name not like '%押金%' ")		// 不取押金的收款
							.append(" and substr(gb.busidate,1,10) between '"+str_yb_ksrq+"' and '"+str_yb_jsrq+"' ")
							.append(" and s.pk_org = '"+pk_org+"' ")
							.append(" group by s.pk_customer,s.vdef16 ")	// Group By  客户+房号
						// 红冲收款单
						.append(" union all ")
							.append(" select ")
							.append(" s.pk_customer ")			// 客户pk
							.append(",s.vdef16 pk_room ")		// 房号pk
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
//								.append(" and srxm.name  like '%押金%' ")		// 不取押金的收款
							.append(" and srxm.name not like '%押金%' ")		// 不取押金的收款
							.append(" and substr(gb.busidate,1,10) between '"+str_yb_ksrq+"' and '"+str_yb_jsrq+"' ")
							.append(" and s.pk_org = '"+pk_org+"' ")
							.append(" group by s.pk_customer,s.vdef16 ")	// Group By  客户+房号
						.append(" ) ")
						.append(" group by pk_customer,pk_room  ")
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
				}
			}
		}
		/***END***/
		
		/**
		 * 查询 上期的期末余额,当做本期的期初余额
		 */
		{
			String syqj = QushuAction.getYYYYMM(yearMonth,-1);	// 上月的期间
			
			StringBuffer querySQL_QC = 
				new StringBuffer(" select ")
						.append(" yb.pk_cutomer ")
						.append(",yb.roomno ")
						.append(",sum(yb.qmyskye) ")
						.append(" from hk_zulin_yuebao y ")
						.append(" inner join hk_zulin_yuebao_b yb on y.pk_hk_zulin_yuebao = yb.pk_hk_zulin_yuebao ")
						.append(" where y.dr=0 and yb.dr=0 ")
						.append(" and y.yearmonth = '"+syqj+"' ")
						.append(" and y.pk_org = '"+pk_org+"' ")
						.append(" group by yb.pk_cutomer,yb.roomno ")	// Group By  客户+房号
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
					// 客户##房间号
					String key = obj[0]+"##"+obj[1];
					
					UFDouble qcye = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 期初余额
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setQcyskye(qcye);
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
			// 期末余额 = 期初余额+本期收款-本期收入
			UFDouble qmye = 
					 PuPubVO.getUFDouble_NullAsZero(bvo.getQcyskye())
				.add(PuPubVO.getUFDouble_NullAsZero(bvo.getBqskje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje())
			);
			bvo.setQmyskye(qmye);
			
			// 单价为空的，需要反算单价
			if(bvo.getDanjia()==null)
			{
				UFDouble danjia = // 当月确认收入/天数/面积
						  bvo.getDysrqrje()
					.div( bvo.getDysrqrts() )
					.div( bvo.getMianji() )
					.setScale(8, UFDouble.ROUND_HALF_UP)
				;
				bvo.setDanjia(danjia);
			}
		}
		/***END***/
		
		this.getEditor().getBillCardPanel().getBillModel().setBodyDataVO(bodyVOs);
		
	}
}
