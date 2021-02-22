package nc.ui.ic.m4i.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.ic.general.view.ICBizView;
import nc.ui.ic.pub.view.ICBizBillForm;
import nc.ui.ic.pub.view.ICBizBillListView;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.vo.am.common.TransportBillVO;
import nc.vo.ic.m4d.entity.MaterialOutBodyVO;
import nc.vo.ic.m4d.entity.MaterialOutHeadVO;
import nc.vo.ic.m4d.entity.MaterialOutVO;
import nc.vo.pub.BusinessException;

public class ZhuanGuAction extends NCAction {

	private static final long serialVersionUID = 3845952082528760982L;

	private ICBizBillListView listView = null;
	private BillManageModel model = null;
	private ICBizBillForm editor = null;
	
	private IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
	
	public ZhuanGuAction() {
		super();
		setBtnName("转固");
		setCode("zhuanGuAction");
	}

	@Override
	public void doAction(ActionEvent event) throws Exception {

		ICBizView card = (ICBizView)this.getEditor();
		int[] rows = card.getBillCardPanel().getBodyPanel().getTable().getSelectedRows();
		
		if (rows == null || rows.length == 0) {
			throw new BusinessException("请选择表体行。");
		}
		
		HkjtReportITF itf = NCLocator.getInstance().lookup(HkjtReportITF.class);
		MaterialOutVO billVO = (MaterialOutVO)this.getModel().getSelectedData();
		MaterialOutHeadVO hVO = billVO.getHead();
		MaterialOutBodyVO[] bVOs = billVO.getBodys();
		// 逐行生成转固单
		for (int row : rows) {
			String bVO_pk = PuPubVO.getString_TrimZeroLenAsNull(
					card.getBillCardPanel().getBodyValueAt(row, "cgeneralbid"));
			// 检查是否已经生成 转固单
			String querySQL = "select bill_code from v_ck_zhuangu where pk_bill_b_src = '"+bVO_pk+"'";
			List<Object[]> list = (List<Object[]>)iUAPQueryBS.executeQuery(querySQL, new ArrayListProcessor());
			if (list != null && !list.isEmpty()) continue;
			// 生成转固
			MaterialOutBodyVO bVO = getSelectBVO(bVOs, bVO_pk);
			MaterialOutVO sendBillVO = new MaterialOutVO();
			sendBillVO.setParentVO(hVO);
			sendBillVO.setChildrenVO(new MaterialOutBodyVO[]{bVO});
			Object res = itf.ckZhuanGu(sendBillVO, null);
			if (res != null) {
				TransportBillVO resVO = (TransportBillVO)res;
				String billCode = PuPubVO.getString_TrimZeroLenAsNull(
						resVO.getParentVO().getAttributeValue("bill_code"));
				card.getBillCardPanel().setBodyValueAt(billCode, row, "zgd");
			}
		}
	}

	private MaterialOutBodyVO getSelectBVO(MaterialOutBodyVO[] bVOs, String pk) {
		for (MaterialOutBodyVO bVO : bVOs) {
			if (bVO.getCgeneralbid().equals(pk)) return bVO;
		}
		return null;
	}
	
	public ICBizBillListView getListView() {
		return listView;
	}

	public void setListView(ICBizBillListView listView) {
		this.listView = listView;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	public ICBizBillForm getEditor() {
		return editor;
	}

	public void setEditor(ICBizBillForm editor) {
		this.editor = editor;
	}
	
}
