package nc.ui.hkjt.hg_spfl.ace.handler;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHg_spflMaintain;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.IEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.IQueryAndRefreshManager;
import nc.uif2.annoations.MethodType;
import nc.uif2.annoations.ModelMethod;
import nc.uif2.annoations.ModelType;
import nc.uif2.annoations.ViewMethod;
import nc.uif2.annoations.ViewType;
import nc.vo.pubapp.AppContext;

public class TongBuAction extends NCAction {
	private static final long serialVersionUID = 4985885705267518918L;
	private AbstractAppModel model;
	private IEditor editor;
	private IQueryAndRefreshManager dataManager = null;

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		IHg_spflMaintain spflItf = NCLocator.getInstance().lookup(
				IHg_spflMaintain.class);
		String pk_org = model.getContext().getPk_org();
		spflItf.tongBuSpfl(pk_org);// 同步商品分类数据
		getDataManager().refresh();
	}

	public TongBuAction() {
		this.setBtnName("金贵同步");
		this.setCode("金贵同步");
	}

	@ModelMethod(modelType = ModelType.AbstractAppModel, methodType = MethodType.GETTER)
	public AbstractAppModel getModel() {
		return model;
	}

	@ViewMethod(viewType = ViewType.IEditor, methodType = MethodType.GETTER)
	public IEditor getEditor() {
		return editor;
	}

	@ModelMethod(modelType = ModelType.AbstractAppModel, methodType = MethodType.SETTER)
	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@ViewMethod(viewType = ViewType.IEditor, methodType = MethodType.SETTER)
	public void setEditor(IEditor editor) {
		this.editor = editor;
	}

	public IQueryAndRefreshManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IQueryAndRefreshManager dataManager) {
		this.dataManager = dataManager;
	}

	@Override
	protected boolean isActionEnable() {
		return HKJT_PUB.PK_ORG_HUIGUAN_MAP.values().contains(model.getContext().getPk_org());
	}
}
