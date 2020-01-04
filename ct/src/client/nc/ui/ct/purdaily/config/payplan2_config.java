package nc.ui.ct.purdaily.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class payplan2_config extends AbstractJavaBeanDefinition {
	private Map<String, Object> context = new HashMap();

	public nc.vo.uif2.LoginContext getContext() {
		if (context.get("context") != null)
			return (nc.vo.uif2.LoginContext) context.get("context");
		nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
		context.put("context", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.model.PayPlanModelService getBatchModelService() {
		if (context.get("batchModelService") != null)
			return (nc.ui.ct.purdaily.model.PayPlanModelService) context
					.get("batchModelService");
		nc.ui.ct.purdaily.model.PayPlanModelService bean = new nc.ui.ct.purdaily.model.PayPlanModelService();
		context.put("batchModelService", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory getBoadatorfactory() {
		if (context.get("boadatorfactory") != null)
			return (nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory) context
					.get("boadatorfactory");
		nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.CAVO2BDObjectAdapterFactory();
		context.put("boadatorfactory", bean);
		bean.setId_field("pk_ct_payplan");
		bean.setCode_field("vbillcode");
		bean.setName_field("vbillcode");
		bean.setPid_field("pk_ct_pu");
		bean.setPk_org_field("pk_org");
		bean.setPk_group_field("pk_group");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.model.DefaultBatchValidationService getBatchValidateSerice() {
		if (context.get("batchValidateSerice") != null)
			return (nc.ui.uif2.model.DefaultBatchValidationService) context
					.get("batchValidateSerice");
		nc.ui.uif2.model.DefaultBatchValidationService bean = new nc.ui.uif2.model.DefaultBatchValidationService();
		context.put("batchValidateSerice", bean);
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.model.PayPlanModel getBatchBillTableModel() {
		if (context.get("batchBillTableModel") != null)
			return (nc.ui.scmf.payplan.model.PayPlanModel) context
					.get("batchBillTableModel");
		nc.ui.scmf.payplan.model.PayPlanModel bean = new nc.ui.scmf.payplan.model.PayPlanModel();
		context.put("batchBillTableModel", bean);
		bean.setContext(getContext());
		bean.setService(getBatchModelService());
		bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
		bean.setValidationService(getBatchValidateSerice());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager() {
		if (context.get("modelDataManager") != null)
			return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager) context
					.get("modelDataManager");
		nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
		context.put("modelDataManager", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setService(getBatchModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.view.PayPlanOrgPanel getOrgPanel() {
		if (context.get("orgPanel") != null)
			return (nc.ui.scmf.payplan.view.PayPlanOrgPanel) context
					.get("orgPanel");
		nc.ui.scmf.payplan.view.PayPlanOrgPanel bean = new nc.ui.scmf.payplan.view.PayPlanOrgPanel();
		context.put("orgPanel", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setDataManager(getModelDataManager());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.view.PayPlanVOValueAdapter getComponentValueManager() {
		if (context.get("componentValueManager") != null)
			return (nc.ui.scmf.payplan.view.PayPlanVOValueAdapter) context
					.get("componentValueManager");
		nc.ui.scmf.payplan.view.PayPlanVOValueAdapter bean = new nc.ui.scmf.payplan.view.PayPlanVOValueAdapter();
		context.put("componentValueManager", bean);
		bean.setBodyVOName("nc.vo.ct.purdaily.entity.PayPlanViewVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.view.PayPlanBillForm getList() {
		if (context.get("list") != null)
			return (nc.ui.scmf.payplan.view.PayPlanBillForm) context
					.get("list");
		nc.ui.scmf.payplan.view.PayPlanBillForm bean = new nc.ui.scmf.payplan.view.PayPlanBillForm();
		context.put("list", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setComponentValueManager(getComponentValueManager());
		bean.setVoClassName("nc.vo.ct.purdaily.entity.PayPlanViewVO");
		bean.setIsBodyAutoAddLine(false);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.FunNodeClosingHandler getClosingListener() {
		if (context.get("ClosingListener") != null)
			return (nc.ui.uif2.FunNodeClosingHandler) context
					.get("ClosingListener");
		nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
		context.put("ClosingListener", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setSaveaction(getSaveAction());
		bean.setCancelaction(getCancelAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.view.PayPlanInitData getInitDataListener() {
		if (context.get("InitDataListener") != null)
			return (nc.ui.scmf.payplan.view.PayPlanInitData) context
					.get("InitDataListener");
		nc.ui.scmf.payplan.view.PayPlanInitData bean = new nc.ui.scmf.payplan.view.PayPlanInitData();
		context.put("InitDataListener", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setList(getList());
		bean.setOrgPanel(getOrgPanel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.editor.PayPlanOrgChgEventHandler getPurhchaseOrganization() {
		if (context.get("purhchaseOrganization") != null)
			return (nc.ui.scmf.payplan.editor.PayPlanOrgChgEventHandler) context
					.get("purhchaseOrganization");
		nc.ui.scmf.payplan.editor.PayPlanOrgChgEventHandler bean = new nc.ui.scmf.payplan.editor.PayPlanOrgChgEventHandler();
		context.put("purhchaseOrganization", bean);
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.editor.before.PayPlanCardBodyBeforeEventHandler getCardBodyBeforeEdit() {
		if (context.get("cardBodyBeforeEdit") != null)
			return (nc.ui.ct.purdaily.editor.before.PayPlanCardBodyBeforeEventHandler) context
					.get("cardBodyBeforeEdit");
		nc.ui.ct.purdaily.editor.before.PayPlanCardBodyBeforeEventHandler bean = new nc.ui.ct.purdaily.editor.before.PayPlanCardBodyBeforeEventHandler();
		context.put("cardBodyBeforeEdit", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.editor.after.PayPlanCardBodyAfterEditEventHandler getCardBodyAfterEdit() {
		if (context.get("cardBodyAfterEdit") != null)
			return (nc.ui.ct.purdaily.editor.after.PayPlanCardBodyAfterEditEventHandler) context
					.get("cardBodyAfterEdit");
		nc.ui.ct.purdaily.editor.after.PayPlanCardBodyAfterEditEventHandler bean = new nc.ui.ct.purdaily.editor.after.PayPlanCardBodyAfterEditEventHandler();
		context.put("cardBodyAfterEdit", bean);
		bean.setList(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator() {
		if (context.get("appEventHandlerMediator") != null)
			return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator) context
					.get("appEventHandlerMediator");
		nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
		context.put("appEventHandlerMediator", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setHandlerMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent", getManagedList0());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",
				getManagedList1());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",
				getManagedList2());
		return map;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add(getPurhchaseOrganization());
		return list;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getCardBodyBeforeEdit());
		return list;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add(getCardBodyAfterEdit());
		return list;
	}

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setTangramLayoutRoot(getVSNode_11eb8d4());
		bean.setActions(getManagedList3());
		bean.setEditActions(getManagedList4());
		bean.setModel(getBatchBillTableModel());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_11eb8d4() {
		if (context.get("nc.ui.uif2.tangramlayout.node.VSNode#11eb8d4") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context
					.get("nc.ui.uif2.tangramlayout.node.VSNode#11eb8d4");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("nc.ui.uif2.tangramlayout.node.VSNode#11eb8d4", bean);
		bean.setDown(getCNode_b5639());
		bean.setUp(getCNode_49f3b4());
		bean.setDividerLocation(31f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_b5639() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#b5639") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#b5639");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#b5639", bean);
		bean.setComponent(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_49f3b4() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#49f3b4") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#49f3b4");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#49f3b4", bean);
		bean.setComponent(getOrgPanel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add(getEditAction());
		list.add(getSeparatorAction());
		list.add(getPayAction());
		list.add(getSeparatorAction());
		list.add(getLinkQueryAction());
		list.add(getSeparatorAction());
		list.add(getPrintMenuAction());
		return list;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getSeparatorAction());
		list.add(getSaveAction());
		list.add(getSeparatorAction());
		list.add(getCancelAction());
		list.add(getSeparatorAction());
		list.add(getAddAction());
		list.add(getSeparatorAction());
		list.add(getInsAction());
		list.add(getSeparatorAction());
		list.add(getDelAction());
		list.add(getSeparatorAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getPasteAction());
		return list;
	}

	public nc.funcnode.ui.action.GroupAction getPrintMenuAction() {
		if (context.get("printMenuAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("printMenuAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("printMenuAction", bean);
		bean.setCode("print");
		bean.setActions(getManagedList5());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add(getPrintAction());
		list.add(getPreviewAction());
		return list;
	}

	public nc.ui.scmf.payplan.action.PayPlanRowMenuAction getRowMenuAction() {
		if (context.get("rowMenuAction") != null)
			return (nc.ui.scmf.payplan.action.PayPlanRowMenuAction) context
					.get("rowMenuAction");
		nc.ui.scmf.payplan.action.PayPlanRowMenuAction bean = new nc.ui.scmf.payplan.action.PayPlanRowMenuAction();
		context.put("rowMenuAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setActions(getManagedList6());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add(getAddAction());
		list.add(getInsAction());
		list.add(getDelAction());
		list.add(getCopyAction());
		list.add(getPasteAction());
		return list;
	}

	public nc.ui.ct.purdaily.query.PurPayPlanQryCondDLGInitializer getOrderQryDLGInitializer() {
		if (context.get("orderQryDLGInitializer") != null)
			return (nc.ui.ct.purdaily.query.PurPayPlanQryCondDLGInitializer) context
					.get("orderQryDLGInitializer");
		nc.ui.ct.purdaily.query.PurPayPlanQryCondDLGInitializer bean = new nc.ui.ct.purdaily.query.PurPayPlanQryCondDLGInitializer();
		context.put("orderQryDLGInitializer", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
		if (context.get("queryAction") != null)
			return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction) context
					.get("queryAction");
		nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
		context.put("queryAction", bean);
		bean.setDataManager(getModelDataManager());
		bean.setQryCondDLGInitializer(getOrderQryDLGInitializer());
		bean.setModel(getBatchBillTableModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.action.PayPlanAddLineAction getAddAction() {
		if (context.get("addAction") != null)
			return (nc.ui.scmf.payplan.action.PayPlanAddLineAction) context
					.get("addAction");
		nc.ui.scmf.payplan.action.PayPlanAddLineAction bean = new nc.ui.scmf.payplan.action.PayPlanAddLineAction();
		context.put("addAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setVoClassName("nc.vo.ct.purdaily.entity.PayPlanViewVO");
		bean.setCopyItems(getManagedList7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add("corigcurrencyid");
		list.add("ccurrencyid");
		list.add("nexchangerate");
		list.add("pk_financeorg");
		list.add("pk_ct_pu");
		list.add("pk_financeorg_v");
		list.add("hts");
		list.add("fstatusflag");
		list.add("ntotalorigmny");
		list.add("pk_group");
		return list;
	}

	public nc.ui.scmf.payplan.action.PayPlanInsertLineAction getInsAction() {
		if (context.get("insAction") != null)
			return (nc.ui.scmf.payplan.action.PayPlanInsertLineAction) context
					.get("insAction");
		nc.ui.scmf.payplan.action.PayPlanInsertLineAction bean = new nc.ui.scmf.payplan.action.PayPlanInsertLineAction();
		context.put("insAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setVoClassName("nc.vo.ct.purdaily.entity.PayPlanViewVO");
		bean.setCopyItems(getManagedList8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add("corigcurrencyid");
		list.add("ccurrencyid");
		list.add("nexchangerate");
		list.add("pk_financeorg");
		list.add("pk_ct_pu");
		list.add("pk_financeorg_v");
		list.add("hts");
		list.add("fstatusflag");
		list.add("ntotalorigmny");
		list.add("pk_group");
		return list;
	}

	public nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanEditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanEditAction) context
					.get("editAction");
		nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanEditAction bean = new nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanEditAction();
		context.put("editAction", bean);
		bean.setModel(getBatchBillTableModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.action.payplan.PurdailyPayAction getPayAction() {
		if (context.get("payAction") != null)
			return (nc.ui.ct.purdaily.action.payplan.PurdailyPayAction) context
					.get("payAction");
		nc.ui.ct.purdaily.action.payplan.PurdailyPayAction bean = new nc.ui.ct.purdaily.action.payplan.PurdailyPayAction();
		context.put("payAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.action.payplan.PurdailyPayAppAction getPayreqAction() {
		if (context.get("payreqAction") != null)
			return (nc.ui.ct.purdaily.action.payplan.PurdailyPayAppAction) context
					.get("payreqAction");
		nc.ui.ct.purdaily.action.payplan.PurdailyPayAppAction bean = new nc.ui.ct.purdaily.action.payplan.PurdailyPayAppAction();
		context.put("payreqAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.action.payplan.PurdailyCancelPayAppAction getCancelpayreqAction() {
		if (context.get("cancelpayreqAction") != null)
			return (nc.ui.ct.purdaily.action.payplan.PurdailyCancelPayAppAction) context
					.get("cancelpayreqAction");
		nc.ui.ct.purdaily.action.payplan.PurdailyCancelPayAppAction bean = new nc.ui.ct.purdaily.action.payplan.PurdailyCancelPayAppAction();
		context.put("cancelpayreqAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.action.payplan.PayPlanLinkQueryAction getLinkQueryAction() {
		if (context.get("linkQueryAction") != null)
			return (nc.ui.ct.purdaily.action.payplan.PayPlanLinkQueryAction) context
					.get("linkQueryAction");
		nc.ui.ct.purdaily.action.payplan.PayPlanLinkQueryAction bean = new nc.ui.ct.purdaily.action.payplan.PayPlanLinkQueryAction();
		context.put("linkQueryAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setBillType("Z2");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.action.PayPlanDelLineAction getDelAction() {
		if (context.get("delAction") != null)
			return (nc.ui.scmf.payplan.action.PayPlanDelLineAction) context
					.get("delAction");
		nc.ui.scmf.payplan.action.PayPlanDelLineAction bean = new nc.ui.scmf.payplan.action.PayPlanDelLineAction();
		context.put("delAction", bean);
		bean.setModel(getBatchBillTableModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.batch.BatchCopyLineAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.pubapp.uif2app.actions.batch.BatchCopyLineAction) context
					.get("copyAction");
		nc.ui.pubapp.uif2app.actions.batch.BatchCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.batch.BatchCopyLineAction();
		context.put("copyAction", bean);
		bean.setModel(getBatchBillTableModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.action.PayPlanPasteLineAction getPasteAction() {
		if (context.get("pasteAction") != null)
			return (nc.ui.scmf.payplan.action.PayPlanPasteLineAction) context
					.get("pasteAction");
		nc.ui.scmf.payplan.action.PayPlanPasteLineAction bean = new nc.ui.scmf.payplan.action.PayPlanPasteLineAction();
		context.put("pasteAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setClearItems(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add("naccumpayorgmny");
		list.add("naccumpaymny");
		list.add("naccumpayapporgmny");
		list.add("naccumpayappmny");
		list.add("pk_ct_payplan");
		list.add("iaccounttermno");
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction) context
					.get("saveAction");
		nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction bean = new nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction();
		context.put("saveAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction) context
					.get("cancelAction");
		nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction bean = new nc.ui.pubapp.uif2app.actions.batch.BatchCancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setEditor(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() {
		if (context.get("separatorAction") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("separatorAction");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("separatorAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmf.payplan.action.PayPlanPrintProcesser getPrintProcessor() {
		if (context.get("printProcessor") != null)
			return (nc.ui.scmf.payplan.action.PayPlanPrintProcesser) context
					.get("printProcessor");
		nc.ui.scmf.payplan.action.PayPlanPrintProcesser bean = new nc.ui.scmf.payplan.action.PayPlanPrintProcesser();
		context.put("printProcessor", bean);
		bean.setModel(getBatchBillTableModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanPrintAction getPreviewAction() {
		if (context.get("previewAction") != null)
			return (nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanPrintAction) context
					.get("previewAction");
		nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanPrintAction bean = new nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanPrintAction();
		context.put("previewAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setPreview(true);
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanPrintAction getPrintAction() {
		if (context.get("printAction") != null)
			return (nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanPrintAction) context
					.get("printAction");
		nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanPrintAction bean = new nc.ui.ct.purdaily.action.payplan.PurdailyPayPlanPrintAction();
		context.put("printAction", bean);
		bean.setModel(getBatchBillTableModel());
		bean.setPreview(false);
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}
