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
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
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
		// 是否 印花税
		UFBoolean isYhs = PuPubVO.getUFBoolean_NullAs(
				this.getEditor().getBillCardPanel().getHeadItem("vdef02").getValueObject()
				, UFBoolean.FALSE
		);
		// 印花税的处理
		if (isYhs.booleanValue()) {
			this.handleYhs(pk_org, str_yb_ksrq, str_yb_jsrq);
			return;
		}
		
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
//				.append(",ht.ntotalorigmny vdef07 ")	// 合同总额
				.append(",htze.htze vdef07 ")	// 合同总额
				.append(",substr(ht.valdate,1,10) vdef08 ")			// 整体合同开始日期	valdate
				.append(",substr(ht.invallidate,1,10) vdef09 ")		// 整体合同结束日期	invallidate
				.append(",to_number(nvl(replace(htb.vbdef5,'~',''),'0.0')) vdef13 ")	// 计算单价
				.append(",to_number(nvl(replace(replace(sl.name,'~',''),'%',''),'0.0')) vdef14 ")		// 税率
//				.append(",ht.vhkfield01 vdef21 ") // 生成责任凭证
				.append(",'N' vdef21 ") // 生成责任凭证
				.append(" from ct_pu ht ")		// 合同表头
				.append(" inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")	// 合同表体
				.append(" left join bd_defdoc fplx on ht.vdef3 = fplx.pk_defdoc ")	// 发票类型
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")	// 收支项目
				.append(" left join org_dept dept on ht.depid = dept.pk_dept ")		// 部门
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")	// 供应商
				.append(" left join bd_defdoc sl on ht.vdef4 = sl.pk_defdoc ")		// 税率
				// 关联 合同信息，不取 质保金的 合同总额
				.append(" left join (")
				.append("	select ")
				.append("	 sum(htb.norigtaxmny) htze ")
				.append("	,ht.pk_ct_pu ")
				.append("	from ct_pu ht ")
				.append("	inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")
				.append(" 	left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")
				.append("	where ht.dr = 0 and htb.dr = 0 ")
				.append(" 	and ht.vtrantypecode = '").append(IPub_data.BKHT_type_code).append("' ")
				.append(" 	and szxm.code not in ('2005', '2022') ")
				.append(" 	and ht.pk_org = '").append(pk_org).append("' ")
				.append(" 	and ht.blatest = 'Y' ")
				.append(" 	and ht.fstatusflag = 1 ")
				.append("	group by ht.pk_ct_pu ")
				.append(" ) htze on (htze.pk_ct_pu = ht.pk_ct_pu)")
				
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.vtrantypecode = '").append(IPub_data.BKHT_type_code).append("' ")
				// 不取保证金
				.append(" and szxm.code not in ('2005', '2022') ")
				.append(" and ht.pk_org = '").append(pk_org).append("' ")
				// 只取最新版
				.append(" and ht.blatest = 'Y' ")
				// 只取生效的
				.append(" and ht.fstatusflag = 1 ")
				// 时间范围
				.append(" and ( ")
				.append(" 	'").append(str_yb_ksrq).append("' between substr(htb.vbdef3,1,10) and substr(htb.vbdef4,1,10) ")
				.append(" 	or ")
				.append("  	'").append(str_yb_jsrq).append("' between substr(htb.vbdef3,1,10) and substr(htb.vbdef4,1,10) ")
				.append(" ) ")
//				.append(" and ht.vbillcode like 'aaabbb%' ")	// 测试
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
			if ("专用发票".equals(fplx)) {		// 专票 用无税
				ht_mny = htVO.getVdef11();
			}
			
			String ht_code = htVO.getVdef10();	// 合同号
			UFDate ht_ksrq = htVO.getKsrq();	// 合同明细-开始日期
			UFDate ht_jsrq = htVO.getJsrq();	// 合同明细-结束日期
			Integer ht_days = ht_jsrq.getDaysAfter(ht_ksrq) + 1;	// 合同天数
			UFDouble ht_mianji = htVO.getMianji();	// 面积
			UFDouble ht_mianji_js = UFDouble.ONE_DBL;	// 计算用到的面积
			if (PuPubVO.getUFDouble_ZeroAsNull(ht_mianji) != null) {
				ht_mianji_js = ht_mianji;
			}
			/**
			 *  判断一下：
			 *  1、如果计算单价 为空，则用之前的模式。
			 *  2、如果计算单价 不为空、则用新模式。
			 */
			UFDouble ht_danjia = ht_mny.div(ht_days);	// 每天的单价 = 合同金额 / 合同天数
			UFDouble ht_jsdj = PuPubVO.getUFDouble_ZeroAsNull(htVO.getVdef13());	// 计算单价
			UFDouble ht_sl 	= htVO.getVdef14();		// 税率
			if ( ht_jsdj != null) {
				ht_danjia = ht_jsdj.multiply(ht_mianji_js);// 每天的单价 = 计算单价 * 面积
				if ("专用发票".equals(fplx)) {				// 专票 用无税
					ht_danjia = ht_danjia.div( UFDouble.ONE_DBL.add(ht_sl.div(100.00)) );	// 去掉税
				}
			}
			
//			UFDate ht_zzrq = htVO.getZzrq();	// 合同表头-终止日期
			
			String ht_pk_customer = htVO.getPk_customer();	// 对方pk
			
			String srxm_name = htVO.getVdef03();	// 支出项目-名称
			
			UFDate zthtKsrq = PuPubVO.getUFDate(htVO.getVdef08());	// 整体合同开始日期
			UFDate zthtJsrq = PuPubVO.getUFDate(htVO.getVdef09());	// 整体合同结束日期
			Integer zthtTs = zthtJsrq.getDaysAfter(zthtKsrq) + 1;	// 整体合同天数
			
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
				
				/**
				 * HK 2020年2月17日17:19:12
				 * 进行 天数的取反，如果金额为负数， 说明为 退款，需要扣减天数
				 */
				if (yb_mny.compareTo(UFDouble.ZERO_DBL) < 0) {
					yb_days = 0 - yb_days;
				}
				/***END***/
				
				if(yuebaoBVO == null)
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
					yuebaoBVO.setVbdef09(zthtTs.toString());	// 整体合同天数
					yuebaoBVO.setCsourcetypecode(htVO.getVdef21());	// 生成责任凭证 csourcetypecode
					
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
					
					// 如果 之前存的是 调整营业区租金，本次是 营业区租金， 则用 营业区租金 替换之前的。
					if ("调整营业区租金".equals(yuebaoBVO.getVbdef03())) {
						yuebaoBVO.setVbdef03(htVO.getVdef03());	// 支出项目-名称
						yuebaoBVO.setSrxm(htVO.getPk_srxm());	// 支出项目pk
					}
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
		 * 查询 本期付款金额。
		 */
		{
			// 本期付款金额加个条件，如果合同上面是专票且有税率的情况下，本期付款金额根据税率换算成不含 税的金额：
			// 也就是59621.4/1.05=56782.29
			StringBuffer querySQL_FK = 
			new StringBuffer(" select ")
					.append(" a.pk_customer ")				// 客户pk
					.append(",a.vdef10 ")					// 房间pk
					.append(",sum(a.fkje) fkje ")			// 付款金额
//					.append(",max(a.pk_dept) pk_dept ")		// 部门pk
//					.append(",max(cust.name) vdef01 ")		// 对方-name
					.append(",max(a.fplx) ")	// 发票类型
					.append(",max(a.sl) ") 		// 税率
					.append(" from (")
					// 蓝字付款单、红冲付款单，src字段都是合同，所以可以统一处理
						.append(" select ")
						.append(" ht.cvendorid pk_customer ")	// 对方pk
						.append(",ht.vbillcode vdef10 ")		// 合同号
						.append(",sum(fkb.money_de) fkje ")		// 付款金额
						.append(",max(fplx.name) fplx ")		// 发票类型name
						.append(",max(to_number(nvl(replace(replace(sl.name,'~',''),'%',''),'0.0'))) sl ")		// 税率
						.append(" from ap_paybill fk ")
						.append(" inner join ap_payitem fkb on fk.pk_paybill = fkb.pk_paybill ")
						.append(" inner join ct_pu ht on fkb.src_billid = ht.pk_ct_pu ")
						.append(" inner join ct_payplan fkjh on fkb.src_itemid = fkjh.pk_ct_payplan ")
						.append(" left join ct_pu_b htb on (ht.pk_ct_pu = htb.pk_ct_pu and htb.dr = 0 and fkjh.crowno = htb.crowno) ")
						.append(" left join bd_defdoc fplx on ht.vdef3 = fplx.pk_defdoc ")	// 发票类型
						.append(" left join bd_defdoc sl on ht.vdef4 = sl.pk_defdoc ")		// 税率
						.append(" left join bd_inoutbusiclass szxm on fkb.def13 = szxm.pk_inoutbusiclass ")// 收入项目
						.append(" where fk.dr = 0 and fkb.dr = 0 ")
						.append(" and ht.dr = 0 and fkjh.dr = 0 ")
						.append(" and fk.billstatus = 8 ") // 付款单 取 已签字 状态
						.append(" and nvl(szxm.name, 'NULL') not like '%保证金%' ")	// 不取 保证金 的付款
						.append(" and ht.pk_org = '").append(pk_org).append("' ")
//						.append(" and fk.billyear || '-' || fk.billperiod = '").append(yearMonth).append("' ")
						.append(" and fk.signdate between '").append(str_yb_ksrq).append(" 00:00:00' and '").append(str_yb_jsrq).append(" 23:59:59' ")
						.append(" group by ht.cvendorid, ht.vbillcode ")
					.append(" ) a ")
					.append(" group by a.pk_customer, a.vdef10 ")
			;			
			// 
			ArrayList list_FK = (ArrayList)iUAPQueryBS.executeQuery(
					querySQL_FK.toString()
					,new ArrayListProcessor()
			);
			if(list_FK!=null&&list_FK.size()>0)
			{
				for( int i=0;i<list_FK.size();i++ )
				{
					Object[] obj = (Object[])list_FK.get(i);
					// 对方##合同号
					String key = obj[0]+"##"+obj[1];
					
					UFDouble skje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 付款金额
					
					String fplx = PuPubVO.getString_TrimZeroLenAsNull(obj[3]);	// 发票类型
					UFDouble ht_sl = PuPubVO.getUFDouble_NullAsZero(obj[4]);	// 合同税率
					if ("专用发票".equals(fplx)) {// 专票 要去掉税
						skje = skje.div(UFDouble.ONE_DBL.add(ht_sl.div(100.00))).setScale(2, UFDouble.ROUND_HALF_UP);	// 去掉税
					}
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setBqskje(skje);
					}
					else
					{ /**
					   * HK
					   * 2020年3月19日13:40:26
					   * 将 无当期收入数据的 付款单，插入到表体。
					   */
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setBqskje(skje);		// 本期收款金额
						yuebaoBVO.setQmyskye(skje);		// 期末预收款余额 = 本期收款金额
						
						yuebaoBVO.setPk_cutomer(PuPubVO.getString_TrimZeroLenAsNull(obj[0]));	// 客户pk
						yuebaoBVO.setVbdef10(PuPubVO.getString_TrimZeroLenAsNull(obj[1]));		// 合同号
						
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
						.append(",max(yb.vbdef09) ")	// 未摊销天数
						
						.append(" from hk_zulin_yuebao y ")
						.append(" inner join hk_zulin_yuebao_b yb on y.pk_hk_zulin_yuebao = yb.pk_hk_zulin_yuebao ")
						.append(" where y.dr=0 and yb.dr=0 ")
						.append(" and y.vbilltypecode = 'HK43' ")
						.append(" and nvl(y.vdef02, 'N') in ('~', 'N', 'n') ")	// 只取 非印花税的
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
					String key = pk_cutomer + "##" + vbdef10;	// key
					
					UFDouble qcye = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// 期初余额
					
					Integer ls_wtxts = PuPubVO.getInteger_NullAs(obj[13], 0);	// 未摊销天数
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setQcyskye(qcye);
						// 如果存在历史单据，则取 历史的未摊销天数
						yuebaoBVO.setVbdef09(ls_wtxts.toString());
					}
					else
					{
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setPk_cutomer(pk_cutomer);		// 对方
//						yuebaoBVO.setRoomno(roomno);				// 房间
						yuebaoBVO.setQcyskye(qcye);					// 期初余额
						
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
						yuebaoBVO.setVbdef09(ls_wtxts.toString());	// 未摊销天数
						yuebaoBVO.setVbmemo("【上期有余额，本期无发生】");	// 备注
						
						MAP_yuebaoBVO.put(key, yuebaoBVO);
					}
				}
			}
		}
		/***END***/
		
		/**
		 * 查询 本月调整数据
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
			UFDouble mianji = PuPubVO.getUFDouble_ZeroAsNull(bvo.getMianji());
			if (mianji == null) {
				mianji = UFDouble.ONE_DBL;
			}
			// 反算单价 = 总额/天/面积/
			if(bvo.getDysrqrje() != null 
//			&& bvo.getMianji() != null 
			&& bvo.getDysrqrts() != null
			&& !UFDouble.ZERO_DBL.equals(bvo.getDysrqrts())	
//			&& !UFDouble.ZERO_DBL.equals(bvo.getMianji())	
			)
			{
				UFDouble danjia = // 当月确认费用/天数/面积
						  bvo.getDysrqrje()
					.div( bvo.getDysrqrts() )
					.div( mianji )
					.setScale(8, UFDouble.ROUND_HALF_UP)
				;
				bvo.setDanjia(danjia);
			}
			
			// 计算本次的历史未摊销天数
			Integer byWtsts =  PuPubVO.getInteger_NullAs(bvo.getVbdef09(), 0) - 
					PuPubVO.getInteger_NullAs(bvo.getDysrqrts(), 0)
					;
			bvo.setVbdef09(byWtsts.toString());
			
		}
		/***END***/
		
		this.getEditor().getBillCardPanel().getBillModel().setBodyDataVO(bodyVOs);
		
	}
	
	/**
	 * 印花税的处理
	 * @throws BusinessException 
	 */
	private void handleYhs(String pk_org, String str_yb_ksrq, String str_yb_jsrq) throws BusinessException {
		
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" max(yhs.pk_inoutbusiclass) pk_srxm ")		// 印花税-支出项目pk
				.append(",max(yhs.name) vdef03 ")			// 印花税-支出项目name
				.append(",sum(htb.norigtaxmny) vdef12 ")	// 含税金额
				.append(",max(ht.depid) vdef05 ")			// 部门pk
				.append(",max(dept.name) vdef04 ")			// 部门name
				.append(",max(ht.vbillcode) vdef10 ")		// 合同号
				.append(",max(ht.cvendorid) pk_customer ")	// 对方pk
				.append(",max(gys.name) vdef01 ")			// 对方name
//				.append(",max(to_number(nvl(replace(ht.vdef20,'~',''),'0.0'))) vdef14 ")// 印花税率
				.append(",max(to_number(nvl(replace(yhssl.name,'‰',''),'0.0'))) vdef14 ")// 印花税率
				.append(" from ct_pu ht ")		// 合同表头
				.append(" inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")		// 合同表体
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")	// 收支项目
				.append(" left join org_dept dept on ht.depid = dept.pk_dept ")				// 部门
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")	// 供应商
				.append(" left join bd_defdoc yhssl on ht.vdef20 = yhssl.pk_defdoc ") 	// 印花税-税率
				.append(" left join bd_inoutbusiclass yhs on yhs.name = '印花税' and yhs.dr = 0 ")	// 印花税
				.append(" left join (")
					.append(" select distinct ")
					.append(" yb.pk_cutomer df ")	// 对方pk
					.append(",yb.vbdef10 hth ")		// 合同号
					.append(" from hk_zulin_yuebao y ")
					.append(" inner join hk_zulin_yuebao_b yb on y.pk_hk_zulin_yuebao = yb.pk_hk_zulin_yuebao ")
					.append(" where y.dr=0 and yb.dr=0 ")
					.append(" and y.vbilltypecode = 'HK43' ")
					.append(" and nvl(y.vdef02, 'N') in ('Y', 'y') ")	// 只取 印花税的
					.append(" and y.pk_org = '").append(pk_org).append("' ")
				.append(" ) yb on (ht.cvendorid = yb.df and ht.vbillcode = yb.hth) ")
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.vtrantypecode = '").append(IPub_data.BKHT_type_code).append("' ")
				// 不取之前已经做过的数据
				.append(" and yb.hth is null ")
				// 不取保证金
				.append(" and szxm.code not in ('2005', '2022') ")
				.append(" and ht.pk_org = '").append(pk_org).append("' ")
				// 只取最新版
				.append(" and ht.blatest = 'Y' ")
				// 只取生效的
				.append(" and ht.fstatusflag = 1 ")
				// 只取第一版(存在着 有变更，但是还没取过数的情况，所以不能限定于只取第一版)
//				.append(" and ht.version = 1.0 ")
				// 合同开始日期（只按 合同开始日期 在期间范围内，就可以确保唯一取值）
//				.append(" and ht.valdate between '")
				// 用合同生效日期 去判断
				.append(" and ht.actualvalidate between '")
				.append(str_yb_ksrq).append("' and '")
				.append(str_yb_jsrq).append("' ")
				// 按合同号汇总
				.append(" group by ht.vbillcode ")
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
			
			UFDouble ht_mny = htVO.getVdef12();	// 合同金额（不含保证金）
			UFDouble yhsl = htVO.getVdef14();	// 印花税率(千分之)
			
			UFDouble yhs = ht_mny.multiply(yhsl).div(1000.00).setScale(2, UFDouble.ROUND_HALF_UP); // 印花税
			
			if (yhs.compareTo(UFDouble.ZERO_DBL) == 0){
				continue;
			}
			
			String ht_code = htVO.getVdef10();	// 合同号	
			String ht_pk_customer = htVO.getPk_customer();	// 对方pk
//			String srxm_name = htVO.getVdef03();	// 支出项目-名称
			
			// 同时 对 YuebaoBVO 进行封装处理
			// 按 对方##合同号  汇总
			{
				String key = ht_pk_customer + "##" + ht_code;

				YuebaoBVO yuebaoBVO = new YuebaoBVO();
				yuebaoBVO.setPk_cutomer(ht_pk_customer);	// 对方pk
//					yuebaoBVO.setJsksrq(js_ksrq);				// 计算开始日期
//					yuebaoBVO.setJsjsrq(js_jsrq);				// 计算结束日期
//					yuebaoBVO.setDysrqrts(PuPubVO.getUFDouble_NullAsZero(yb_days));	// 当月费用确认天数
				yuebaoBVO.setDysrqrje(yhs);				// 当月费用确认金额（印花税）
//					yuebaoBVO.setMianji(ht_mianji);				// 面积
//					yuebaoBVO.setDanjia(ht_danjia);				// 单价
				
				yuebaoBVO.setSrxm(htVO.getPk_srxm());		// 收入项目
				yuebaoBVO.setVbdef01(htVO.getVdef01());		// 对方名称
				yuebaoBVO.setVbdef02(htVO.getVdef02());		// 发票类型-名称
				yuebaoBVO.setVbdef03(htVO.getVdef03());		// 支出项目-名称
				yuebaoBVO.setVbdef04(htVO.getVdef04());		// 部门名称
				yuebaoBVO.setVbdef05(htVO.getVdef05());		// 部门pk
				yuebaoBVO.setVbdef07(htVO.getVdef07());		// 实际合同金额
				yuebaoBVO.setVbdef10(htVO.getVdef10());		// 合同号
//					yuebaoBVO.setVbdef09(zthtTs.toString());	// 整体合同天数

				MAP_yuebaoBVO.put(key, yuebaoBVO);
			}
		}
		
		YuebaoBVO[] bodyVOs = MAP_yuebaoBVO.values().toArray(new YuebaoBVO[0]);
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
