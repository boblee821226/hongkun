package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_kaipiaoinfoMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

/**
 * 检查 错误状态的发票
 * @author lb
 *
 */
public class CheckErrorAction extends NCAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5597537533027078884L;

	public CheckErrorAction() {
		setBtnName("检查错误发票");
		setCode("checkErrorAction");
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
	public void doAction(ActionEvent arg0) throws Exception {
		
		/**
		 * 只处理 错误状态的数据
		 */
		ArrayList<String> error_key = new ArrayList<String>();
		
		nc.ui.pub.bill.BillModel billModel = this.getListview().getBillListPanel().getHeadBillModel();
		
		for(int i = 0;i<billModel.getRowCount();i++)
		{
			if( ! "正常".equals( billModel.getValueAt(i, "vdef02") ) )
			{// 不为正常的  都需要处理
				error_key.add( PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "pk_hk_huiyuan_kaipiaoinfo") ) );
			}
		}
		
		if(error_key.size()<=0)
		{
			MessageDialog.showHintDlg(this.getListview()
					, "检查错误"
	        		, "没有要检查的数据。"
	        );
			return;
		}
		
		// 调用接口  后台处理
		IHy_kaipiaoinfoMaintain iHy_kaipiaoinfoMaintain = (IHy_kaipiaoinfoMaintain)NCLocator.getInstance().lookup(IHy_kaipiaoinfoMaintain.class.getName());
		Object result = iHy_kaipiaoinfoMaintain.checkErrorFP(error_key, null);
		
		Object[] obj = (Object[])result;
		Integer update_num = PuPubVO.getInteger_NullAs(obj[0],0);
		String errorMsg = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
		
		String showMsg = 
				"检查更新： "+update_num+"/"+error_key.size() + " \r\n请刷新数据。\r\n"
			   +( errorMsg==null
			     ?"所选数据已经全部检查通过。"
			     :"错误提示：\r\n"+errorMsg)
		;
		
		MessageDialog.showHintDlg(this.getListview()
				, "检查错误"
        		, showMsg
        );
		
	}

}
