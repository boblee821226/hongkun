package nc.ui.hkjt.zulin.znjjs.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 滞纳金计算取数
 *
 */
public class ZnjjsQushuAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public ZnjjsQushuAction() {
		setBtnName("取数");
		setCode("qushuAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction addLineAction;

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
	
	public BodyAddLineAction getAddLineAction() {
		return addLineAction;
	}

	public void setAddLineAction(BodyAddLineAction addLineAction) {
		this.addLineAction = addLineAction;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		String pk_org = 
			PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject()
			);
		
		if(pk_org==null)
		{
			MessageDialog.showErrorDlg(this.getEditor(), "", "请先输入组织");
			return;
		}
		
		UFDate dbilldate = 
			PuPubVO.getUFDate(
				this.getEditor().getBillCardPanel().getHeadItem("dbilldate").getValueObject()
			);
				
		if(dbilldate==null)
		{
			MessageDialog.showErrorDlg(this.getEditor(), "", "请先输入计算日期");
			return;
		}
		
		String dbilldateStr = dbilldate.toString().substring(0, 10);
		
		// 表体有数据，不能取数
		{
			int rowCount = getEditor().getBillCardPanel().getBillModel().getRowCount();
			if(rowCount>0)
			{
				MessageDialog.showErrorDlg(this.getEditor(), "", "表体有数据，不能取数。请先删除表体数据。");
				return;
			}
		}
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
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
			
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
			
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
					
					if(yq_num<=0) {	// 逾期天数 <=0 , 不做处理
						continue;
					}
						
					UFDouble  yq_bl = new UFDouble(5);				// 比例(千分之‰)
					UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
							.setScale(2, UFDouble.ROUND_HALF_UP);	// 滞纳金=应缴费金额 * 5‰ * 逾期天数
					
					yq_mny_total = yq_mny_total.add(yq_mny);
					
					this.getAddLineAction().doAction();
					addRowIndex++;
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_cust, addRowIndex, "pk_cust");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_area, addRowIndex, "pk_area");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_room, addRowIndex, "pk_room");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_code, addRowIndex, "ht_code");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_rowno, addRowIndex, "ht_rowno");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_sfxm, addRowIndex, "pk_sfxm");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_date, addRowIndex, "jf_date");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_mny, addRowIndex, "jf_mny");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_bl, addRowIndex, "yq_bl");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_num, addRowIndex, "yq_num");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_mny, addRowIndex, "yq_mny");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_id, addRowIndex, "ht_id");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_bid, addRowIndex, "ht_bid");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(vbmemo, addRowIndex, "vbmemo");	// 行备注
					
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
			ArrayList list_2 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
			
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
					
					this.getAddLineAction().doAction();
					addRowIndex++;
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_cust, addRowIndex, "pk_cust");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_area, addRowIndex, "pk_area");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_room, addRowIndex, "pk_room");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_code, addRowIndex, "ht_code");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_rowno, addRowIndex, "ht_rowno");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_sfxm, addRowIndex, "pk_sfxm");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_date, addRowIndex, "jf_date");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_mny, addRowIndex, "jf_mny");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_bl, addRowIndex, "yq_bl");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_num, addRowIndex, "yq_num");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_mny, addRowIndex, "yq_mny");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_id, addRowIndex, "ht_id");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_bid, addRowIndex, "ht_bid");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(vbmemo, addRowIndex, "vbmemo");	// 行备注
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(skCode, addRowIndex, "vbdef03");	// 收款单号
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(skBid, addRowIndex, "vbdef04");	// 收款单bid
					
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
			
			ArrayList list_3 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_3.toString(), new ArrayListProcessor());
			
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
						
						this.getAddLineAction().doAction();
						addRowIndex++;
						
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_cust, addRowIndex, "pk_cust");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_area, addRowIndex, "pk_area");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_room, addRowIndex, "pk_room");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_code, addRowIndex, "ht_code");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_rowno, addRowIndex, "ht_rowno");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_sfxm, addRowIndex, "pk_sfxm");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_date, addRowIndex, "jf_date");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_mny, addRowIndex, "jf_mny");
						
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_bl, addRowIndex, "yq_bl");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_num, addRowIndex, "yq_num");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_mny, addRowIndex, "yq_mny");
						
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_id, addRowIndex, "ht_id");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_bid, addRowIndex, "ht_bid");
						
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(vbmemo, addRowIndex, "vbmemo");
					
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(skCode, addRowIndex, "vbdef03");	// 收款单号
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(skBid, addRowIndex, "vbdef04");	// 收款单bid
						
					}
				}
			}
		}
		
		// 将 参照翻译过来
		getEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		// 表头赋值
		getEditor().getBillCardPanel().getHeadItem("yq_mny_total").setValue(yq_mny_total);
	}
}
