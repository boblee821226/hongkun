package nc.ui.hkjt.zulin.znjjs.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
		
		// 表体有数据，不能取数
		{
			int rowCount = getEditor().getBillCardPanel().getBillModel().getRowCount();
			if(rowCount>0)
			{
				MessageDialog.showErrorDlg(this.getEditor(), "", "表体有数据，不能取数。请先删除表体数据。");
				return;
			}
		}
		
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
					.append(",ctb.pk_ct_sale_b ")	// 9合同子表pk
					.append(",ctb.pk_ct_sale ")		//10合同主表pk
					.append(" from ct_sale ct ")
					.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
					.append(" left join bd_defdoc srxm on ctb.vbdef1  = srxm.pk_defdoc ")
					.append(" where ct.dr = 0 and ctb.dr = 0 ")
					.append(" and ct.blatest = 'Y' ")
					.append(" and ct.pk_org = '"+pk_org+"' ")
					.append(" and substr(ctb.vbdef3,1,10) <= '"+(dbilldate.toString().substring(0, 10))+"' ")
					.append(" and (nvl(ctb.norigtaxmny,0)-nvl(ctb.noritotalgpmny,0))<>0 ")
					.append(" and srxm.name not like '%押金%' ")
					.append(" and srxm.name not like '%调整%' ")
//					.append(" and ct.vbillcode = '201811068053' ")	// 测试
		;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
	
		if(list!=null && list.size()>0)
		{
			UFDouble yq_mny_total = UFDouble.ZERO_DBL;	// 表头-滞纳金合计
			
			for(int row=0;row<list.size();row++)
			{
				Object[] obj = (Object[])list.get(row);
				
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
				UFDouble   jf_mny = yj_mny.sub(sj_mny);	// 应缴费金额 = 应缴房租 - 实缴房租
				
				UFDouble  yq_bl = new UFDouble(5);	// 比例(千分之‰)
				Integer  yq_num = dbilldate.getDaysAfter(jf_date) + 1;	// 逾期天数
				UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
						.setScale(2, UFDouble.ROUND_HALF_UP);	// 滞纳金=应缴费金额 * 5‰ * 逾期天数
				
				yq_mny_total = yq_mny_total.add(yq_mny);
				
				this.getAddLineAction().doAction();
				
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_cust,row,"pk_cust");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_area,row,"pk_area");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_room,row,"pk_room");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_code,row,"ht_code");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_rowno,row,"ht_rowno");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_sfxm,row,"pk_sfxm");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_date,row,"jf_date");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_mny,row,"jf_mny");
				
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_bl,row,"yq_bl");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_num,row,"yq_num");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_mny,row,"yq_mny");
				
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_id,row,"ht_id");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_bid,row,"ht_bid");
				
			}
			
			// 将 参照翻译过来
			getEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
			// 表头赋值
			getEditor().getBillCardPanel().getHeadItem("yq_mny_total").setValue(yq_mny_total);
		}
	}
}
