package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDouble;

public class ZhuankaAction extends NCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6373782071509305284L;

	public ZhuankaAction() {
		setBtnName("转卡");
		setCode("zhuankaAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction bodyAddLineAction;

	public BodyAddLineAction getBodyAddLineAction() {
		return bodyAddLineAction;
	}

	public void setBodyAddLineAction(BodyAddLineAction bodyAddLineAction) {
		this.bodyAddLineAction = bodyAddLineAction;
	}
	
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
		
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		
		int selectRow = this.getEditor().getBillCardPanel().getBillTable().getSelectedRow();
		
		if( selectRow<0 )
		{
			MessageDialog.showErrorDlg(this.getEditor(), "", "请选中表体行。");
			return;
		}
		
		String ka_code = PuPubVO.getString_TrimZeroLenAsNull(
				MessageDialog.showInputDlg(this.getEditor(), "转卡", "请输入 对方卡号：", null)
		);
		
		if( ka_code!=null )
		{
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			// 查找 会员卡 相关信息。
			StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" ka.pk_hk_huiyuan_kadangan ")	// 卡 pk
							.append(",kx.kaxing_code ")				// 卡型code
							.append(",kx.kaxing_name ")				// 卡型名称
							.append(",kx.pk_hk_huiyuan_kaxing ")	// 卡型pk
							.append(",ka.ka_code ")					// 卡号
							.append(",nvl(ka.kkpze,0)-to_number( case when ka.vdef03='~' then '0' else nvl(ka.vdef03,'0') end )  ")	// 可开票总额
							.append(",nvl(ka.ykpje,0) ")			// 已开票总额
							.append(" from hk_huiyuan_kadangan ka ")
							.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
							.append(" where ka.dr=0  ")
							.append(" and NLS_UPPER(ka.ka_code) = NLS_UPPER('").append(ka_code).append("') ")	// 都转换成大写来比较， 目的是 不区分大小写
			;
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor());
			
			if(list.size()==0)
			{// 判断有无  会员卡
				MessageDialog.showErrorDlg(this.getEditor(), "", "系统里 没有该会员卡，请检查。");
				return;
			}
			
			Object[] ka_value = (Object[])list.get(0);
			
			this.getBodyAddLineAction().doAction(e);	// 增行
			
			this.getEditor().getBillCardPanel().setHeadItem("vdef01", "转卡");
			this.getEditor().getBillCardPanel().setHeadItem("fpje", UFDouble.ZERO_DBL);
			this.getEditor().getBillCardPanel().setHeadItem("fph", "转卡");
			
			int endRow = billModel.getRowCount() -1;
			
			billModel.setValueAt(ka_value[0], endRow, "ka_pk");	// 卡pk
			billModel.setValueAt(ka_value[1], endRow, "kaxing_code");	// 卡型code
			billModel.setValueAt(ka_value[2], endRow, "kaxing_name");	// 卡型名称
			billModel.setValueAt(ka_value[3], endRow, "kaxing_pk");	// 卡型pk
			billModel.setValueAt(ka_value[4], endRow, "ka_code");	// 卡号
			billModel.setValueAt(ka_value[5], endRow, "kkpze");	// 可开票总额
			billModel.setValueAt(ka_value[6], endRow, "zqkpje");	// 已开票总额
			
			// 互相 赋值  对方卡pk
			billModel.setValueAt( billModel.getValueAt(selectRow,"ka_pk") , endRow, "vsourcebillcode");	// 对方卡pk
			billModel.setValueAt( billModel.getValueAt(endRow,"ka_pk") , selectRow, "vsourcebillcode");	// 对方卡pk
			
			// 设置 转卡金额
			billModel.setValueAt( UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(selectRow,"fpje"))) , endRow, "fpje");
			
			
		}
		
	}

}
