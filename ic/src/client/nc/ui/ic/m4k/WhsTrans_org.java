package nc.ui.ic.m4k;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class WhsTrans_org extends AbstractJavaBeanDefinition {
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

	public nc.ui.ic.pub.env.ICUIContext getIcUIContext() {
		if (context.get("icUIContext") != null)
			return (nc.ui.ic.pub.env.ICUIContext) context.get("icUIContext");
		nc.ui.ic.pub.env.ICUIContext bean = new nc.ui.ic.pub.env.ICUIContext(
				getContext());
		context.put("icUIContext", bean);
		bean.setUiprocesorinfo(getUIProcesorInfo());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory() {
		if (context.get("boadatorfactory") != null)
			return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory) context
					.get("boadatorfactory");
		nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
		context.put("boadatorfactory", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.ICCloseingCheck getClosingListener() {
		if (context.get("ClosingListener") != null)
			return (nc.ui.ic.pub.handler.ICCloseingCheck) context
					.get("ClosingListener");
		nc.ui.ic.pub.handler.ICCloseingCheck bean = new nc.ui.ic.pub.handler.ICCloseingCheck();
		context.put("ClosingListener", bean);
		bean.setModel(getIcBizModel());
		bean.setEditorModel(getIcBizEditorModel());
		bean.setSaveAction(getSaveAction());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator getCardPanelOrgSetterForAllRefMediator() {
		if (context.get("cardPanelOrgSetterForAllRefMediator") != null)
			return (nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator) context
					.get("cardPanelOrgSetterForAllRefMediator");
		nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator bean = new nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator(
				getCard());
		context.put("cardPanelOrgSetterForAllRefMediator", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator() {
		if (context.get("mouseClickShowPanelMediator") != null)
			return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator) context
					.get("mouseClickShowPanelMediator");
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
		context.put("mouseClickShowPanelMediator", bean);
		bean.setListView(getList());
		bean.setShowUpComponent(getCard());
		bean.setHyperLinkColumn("vbillcode");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator() {
		if (context.get("rowNoMediator") != null)
			return (nc.ui.pubapp.uif2app.view.RowNoMediator) context
					.get("rowNoMediator");
		nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
		context.put("rowNoMediator", bean);
		bean.setModel(getIcBizModel());
		bean.setEditor(getCard());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getCardInterceptor() {
		if (context.get("cardInterceptor") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("cardInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("cardInterceptor", bean);
		bean.setShowUpComponent(getCard());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getListInterceptor() {
		if (context.get("listInterceptor") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor) context
					.get("listInterceptor");
		nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
		context.put("listInterceptor", bean);
		bean.setShowUpComponent(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.model.ICSpeBizEditorModel getIcBizEditorModel() {
		if (context.get("icBizEditorModel") != null)
			return (nc.ui.ic.special.model.ICSpeBizEditorModel) context
					.get("icBizEditorModel");
		nc.ui.ic.special.model.ICSpeBizEditorModel bean = new nc.ui.ic.special.model.ICSpeBizEditorModel(
				getCard(), getList());
		context.put("icBizEditorModel", bean);
		bean.setContext(getIcUIContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.deal.WhsTransUIProcessorInfo getUIProcesorInfo() {
		if (context.get("UIProcesorInfo") != null)
			return (nc.ui.ic.m4k.deal.WhsTransUIProcessorInfo) context
					.get("UIProcesorInfo");
		nc.ui.ic.m4k.deal.WhsTransUIProcessorInfo bean = new nc.ui.ic.m4k.deal.WhsTransUIProcessorInfo();
		context.put("UIProcesorInfo", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.deal.SpePrintProcessor getPrintProcessor() {
		if (context.get("printProcessor") != null)
			return (nc.ui.ic.special.deal.SpePrintProcessor) context
					.get("printProcessor");
		nc.ui.ic.special.deal.SpePrintProcessor bean = new nc.ui.ic.special.deal.SpePrintProcessor();
		context.put("printProcessor", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.deal.WhsTransQueryConditionDLGWrapper getQryDLGInitializer() {
		if (context.get("qryDLGInitializer") != null)
			return (nc.ui.ic.m4k.deal.WhsTransQueryConditionDLGWrapper) context
					.get("qryDLGInitializer");
		nc.ui.ic.m4k.deal.WhsTransQueryConditionDLGWrapper bean = new nc.ui.ic.m4k.deal.WhsTransQueryConditionDLGWrapper();
		context.put("qryDLGInitializer", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.deal.ICBillLazilyLoader getBillLazilyLoader() {
		if (context.get("billLazilyLoader") != null)
			return (nc.ui.ic.pub.deal.ICBillLazilyLoader) context
					.get("billLazilyLoader");
		nc.ui.ic.pub.deal.ICBillLazilyLoader bean = new nc.ui.ic.pub.deal.ICBillLazilyLoader();
		context.put("billLazilyLoader", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator() {
		if (context.get("LasilyLodadMediator") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager) context
					.get("LasilyLodadMediator");
		nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
		context.put("LasilyLodadMediator", bean);
		bean.setModel(getIcBizModel());
		bean.setLoader(getBillLazilyLoader());
		bean.setLazilyLoadSupporter(getManagedList0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add(getCardPanelLazilyLoad_18ee443());
		list.add(getListPanelLazilyLoad_2c127c());
		list.add(getActionLazilyLoad_171bc37());
		return list;
	}

	private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_18ee443() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#18ee443") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#18ee443");
		nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#18ee443",
				bean);
		bean.setBillform(getCard());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_2c127c() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#2c127c") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#2c127c");
		nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
		context.put(
				"nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#2c127c",
				bean);
		bean.setListView(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getActionLazilyLoad_171bc37() {
		if (context
				.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#171bc37") != null)
			return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad) context
					.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#171bc37");
		nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
		context.put("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#171bc37",
				bean);
		bean.setModel(getIcBizModel());
		bean.setActionList(getManagedList1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add(getTemplatePrintAction());
		list.add(getTemplatePreviewAction());
		return list;
	}

	public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer() {
		if (context.get("queryTemplateContainer") != null)
			return (nc.ui.uif2.editor.QueryTemplateContainer) context
					.get("queryTemplateContainer");
		nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
		context.put("queryTemplateContainer", bean);
		bean.setContext(getContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller() {
		if (context.get("remoteCallCombinatorCaller") != null)
			return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller) context
					.get("remoteCallCombinatorCaller");
		nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
		context.put("remoteCallCombinatorCaller", bean);
		bean.setRemoteCallers(getManagedList2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add(getQueryTemplateContainer());
		list.add(getTemplateContainer());
		list.add(getUserdefitemContainer());
		return list;
	}

	public nc.ui.ic.pub.model.ICBizDataManager getModelDataManager() {
		if (context.get("modelDataManager") != null)
			return (nc.ui.ic.pub.model.ICBizDataManager) context
					.get("modelDataManager");
		nc.ui.ic.pub.model.ICBizDataManager bean = new nc.ui.ic.pub.model.ICBizDataManager();
		context.put("modelDataManager", bean);
		bean.setModel(getIcBizModel());
		bean.setService(getManageModelService());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getTrantypeTempMender() {
		if (context.get("trantypeTempMender") != null)
			return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender) context
					.get("trantypeTempMender");
		nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(
				getContext());
		context.put("trantypeTempMender", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer() {
		if (context.get("templateContainer") != null)
			return (nc.ui.pubapp.uif2app.view.TemplateContainer) context
					.get("templateContainer");
		nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
		context.put("templateContainer", bean);
		bean.setContext(getContext());
		bean.setBillTemplateMender(getTrantypeTempMender());
		bean.load();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea() {
		if (context.get("queryArea") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell) context
					.get("queryArea");
		nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
		context.put("queryArea", bean);
		bean.setQueryAreaCreator(getQueryAction());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.TangramContainer getContainer() {
		if (context.get("container") != null)
			return (nc.ui.uif2.TangramContainer) context.get("container");
		nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
		context.put("container", bean);
		bean.setTangramLayoutRoot(getVsnode());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getQueryInfo() {
		if (context.get("queryInfo") != null)
			return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel) context
					.get("queryInfo");
		nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
		context.put("queryInfo", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardInfoPnl() {
		if (context.get("cardInfoPnl") != null)
			return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel) context
					.get("cardInfoPnl");
		nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
		context.put("cardInfoPnl", bean);
		bean.setActions(getManagedList3());
		bean.setRightExActions(getManagedList4());
		bean.setTitleAction(getReturnaction());
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add(getFirstLineAction());
		list.add(getPreLineAction());
		list.add(getNextLineAction());
		list.add(getLastLineAction());
		return list;
	}

	private nc.ui.uif2.actions.FirstLineAction getFirstLineAction() {
		if (context.get("firstLineAction") != null)
			return (nc.ui.uif2.actions.FirstLineAction) context
					.get("firstLineAction");
		nc.ui.uif2.actions.FirstLineAction bean = new nc.ui.uif2.actions.FirstLineAction();
		context.put("firstLineAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.actions.PreLineAction getPreLineAction() {
		if (context.get("preLineAction") != null)
			return (nc.ui.uif2.actions.PreLineAction) context
					.get("preLineAction");
		nc.ui.uif2.actions.PreLineAction bean = new nc.ui.uif2.actions.PreLineAction();
		context.put("preLineAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.actions.NextLineAction getNextLineAction() {
		if (context.get("nextLineAction") != null)
			return (nc.ui.uif2.actions.NextLineAction) context
					.get("nextLineAction");
		nc.ui.uif2.actions.NextLineAction bean = new nc.ui.uif2.actions.NextLineAction();
		context.put("nextLineAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.actions.LastLineAction getLastLineAction() {
		if (context.get("lastLineAction") != null)
			return (nc.ui.uif2.actions.LastLineAction) context
					.get("lastLineAction");
		nc.ui.uif2.actions.LastLineAction bean = new nc.ui.uif2.actions.LastLineAction();
		context.put("lastLineAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getActionsBar_ActionsBarSeparator_532633());
		list.add(getHeadZoomAction());
		return list;
	}

	private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_532633() {
		if (context
				.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#532633") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#532633");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#532633",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction() {
		if (context.get("headZoomAction") != null)
			return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction) context
					.get("headZoomAction");
		nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
		context.put("headZoomAction", bean);
		bean.setBillForm(getCard());
		bean.setModel(getIcBizModel());
		bean.setPos(0);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction() {
		if (context.get("returnaction") != null)
			return (nc.ui.pubapp.uif2app.actions.UEReturnAction) context
					.get("returnaction");
		nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
		context.put("returnaction", bean);
		bean.setGoComponent(getList());
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.view.ICBizBillListView getList() {
		if (context.get("list") != null)
			return (nc.ui.ic.pub.view.ICBizBillListView) context.get("list");
		nc.ui.ic.pub.view.ICBizBillListView bean = new nc.ui.ic.pub.view.ICBizBillListView();
		context.put("list", bean);
		bean.setModel(getIcBizModel());
		bean.setMultiSelectionMode(0);
		bean.setTemplateContainer(getTemplateContainer());
		bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.view.SpecialBizView getCard() {
		if (context.get("card") != null)
			return (nc.ui.ic.special.view.SpecialBizView) context.get("card");
		nc.ui.ic.special.view.SpecialBizView bean = new nc.ui.ic.special.view.SpecialBizView();
		context.put("card", bean);
		bean.setModel(getIcBizModel());
		bean.setTemplateContainer(getTemplateContainer());
		bean.setTangramContainer(getContainer());
		bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
		bean.setAutoAddLine(true);
		bean.setTemplateNotNullValidate(true);
		bean.setBlankChildrenFilter(getSingleFieldBlankChildrenFilter_f115d4());
		bean.setBodyLineActions(getManagedList5());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter getSingleFieldBlankChildrenFilter_f115d4() {
		if (context
				.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#f115d4") != null)
			return (nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter) context
					.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#f115d4");
		nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter();
		context.put(
				"nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#f115d4",
				bean);
		bean.setFieldName("cmaterialvid");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add(getBodyAddLineAction());
		list.add(getBodyInsertLineAction());
		list.add(getBodyDelLineAction());
		list.add(getBodyCopyLineAction());
		list.add(getBodyPasteLineAction());
		list.add(getBodyPasteToTailAction());
		list.add(getActionsBar_ActionsBarSeparator_1d035ae());
		list.add(getRearrangeRowNoBodyLineAction());
		list.add(getActionsBar_ActionsBarSeparator_747309());
		list.add(getDefaultBodyZoomAction_12c8d8c());
		return list;
	}

	private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1d035ae() {
		if (context
				.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1d035ae") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1d035ae");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1d035ae",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_747309() {
		if (context
				.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#747309") != null)
			return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator) context
					.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#747309");
		nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
		context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#747309",
				bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_12c8d8c() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#12c8d8c") != null)
			return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction) context
					.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#12c8d8c");
		nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
		context.put(
				"nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#12c8d8c",
				bean);
		bean.setPos(1);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.util.ICBillCodeMediator getBillCodeMediator() {
		if (context.get("billCodeMediator") != null)
			return (nc.ui.ic.pub.util.ICBillCodeMediator) context
					.get("billCodeMediator");
		nc.ui.ic.pub.util.ICBillCodeMediator bean = new nc.ui.ic.pub.util.ICBillCodeMediator();
		context.put("billCodeMediator", bean);
		bean.setBillCodeKey("vbillcode");
		bean.setIcBizModel(getIcBizModel());
		bean.setBillForm(getCard());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
		if (context.get("fractionFixMediator") != null)
			return (nc.ui.pubapp.uif2app.view.FractionFixMediator) context
					.get("fractionFixMediator");
		nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(
				getCard());
		context.put("fractionFixMediator", bean);
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator() {
		if (context.get("userdefAndMarAsstCardPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare) context
					.get("userdefAndMarAsstCardPreparator");
		nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
		context.put("userdefAndMarAsstCardPreparator", bean);
		bean.setBillDataPrepares(getManagedList6());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList6() {
		List list = new ArrayList();
		list.add(getUserdefitemPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator() {
		if (context.get("userdefAndMarAsstListPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare) context
					.get("userdefAndMarAsstListPreparator");
		nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
		context.put("userdefAndMarAsstListPreparator", bean);
		bean.setBillListDataPrepares(getManagedList7());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList7() {
		List list = new ArrayList();
		list.add(getUserdefitemlistPreparator());
		list.add(getMarAsstPreparator());
		return list;
	}

	public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer() {
		if (context.get("userdefitemContainer") != null)
			return (nc.ui.uif2.userdefitem.UserDefItemContainer) context
					.get("userdefitemContainer");
		nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
		context.put("userdefitemContainer", bean);
		bean.setContext(getContext());
		bean.setParams(getManagedList8());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList8() {
		List list = new ArrayList();
		list.add(getQueryParams1());
		list.add(getQueryParams2());
		list.add(getQueryParams3());
		list.add(getQueryParam_11a53());
		return list;
	}

	private nc.ui.uif2.userdefitem.QueryParam getQueryParam_11a53() {
		if (context.get("nc.ui.uif2.userdefitem.QueryParam#11a53") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("nc.ui.uif2.userdefitem.QueryParam#11a53");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("nc.ui.uif2.userdefitem.QueryParam#11a53", bean);
		bean.setRulecode("materialassistant");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
		if (context.get("userdefitemPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerPreparator) context
					.get("userdefitemPreparator");
		nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
		context.put("userdefitemPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList9());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList9() {
		List list = new ArrayList();
		list.add(getUserQueryParams1());
		list.add(getUserQueryParams2());
		list.add(getUserQueryParams3());
		return list;
	}

	public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator() {
		if (context.get("userdefitemlistPreparator") != null)
			return (nc.ui.uif2.editor.UserdefitemContainerListPreparator) context
					.get("userdefitemlistPreparator");
		nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
		context.put("userdefitemlistPreparator", bean);
		bean.setContainer(getUserdefitemContainer());
		bean.setParams(getManagedList10());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList10() {
		List list = new ArrayList();
		list.add(getUserQueryParams1());
		list.add(getUserQueryParams2());
		list.add(getUserQueryParams3());
		return list;
	}

	public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator() {
		if (context.get("marAsstPreparator") != null)
			return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator) context
					.get("marAsstPreparator");
		nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
		context.put("marAsstPreparator", bean);
		bean.setModel(getIcBizModel());
		bean.setContainer(getUserdefitemContainer());
		bean.setPrefix("vfree");
		bean.setMaterialField("cmaterialvid");
		bean.setStoreStateField("cstateid");
		bean.setProjectField("cprojectid");
		bean.setSupplierField("cvendorid");
		bean.setProductorField("cproductorid");
		bean.setSignatureField("cffileid");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.userdefitem.QueryParam getQueryParams3() {
		if (context.get("queryParams3") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("queryParams3");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("queryParams3", bean);
		bean.setRulecode("SCM_BATCHCODE");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams3() {
		if (context.get("userQueryParams3") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("userQueryParams3");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("userQueryParams3", bean);
		bean.setRulecode("SCM_BATCHCODE");
		bean.setPos(1);
		bean.setPrefix("vbcdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.general.view.BatchcodeMediator getBatchcodeMediator() {
		if (context.get("batchcodeMediator") != null)
			return (nc.ui.ic.general.view.BatchcodeMediator) context
					.get("batchcodeMediator");
		nc.ui.ic.general.view.BatchcodeMediator bean = new nc.ui.ic.general.view.BatchcodeMediator();
		context.put("batchcodeMediator", bean);
		bean.setModel(getIcBizModel());
		bean.setBillEditor(getCard());
		bean.setPk_calbodyKey("pk_org");
		bean.setCmaterialvidKey("cmaterialvid");
		bean.setBatchDefPrefix("vbcdef");
		bean.setBatchBillBodyKeys(getManagedList11());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList11() {
		List list = new ArrayList();
		list.add("vvendbatchcode");
		list.add("cqualitylevelid");
		list.add("tchecktime");
		list.add("vbatchcodenote");
		list.add("dinbounddate");
		return list;
	}

	public nc.ui.uif2.tangramlayout.node.TBNode getVsnode() {
		if (context.get("vsnode") != null)
			return (nc.ui.uif2.tangramlayout.node.TBNode) context.get("vsnode");
		nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
		context.put("vsnode", bean);
		bean.setShowMode("CardLayout");
		bean.setTabs(getManagedList12());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList12() {
		List list = new ArrayList();
		list.add(getHSNode_1d6211());
		list.add(getVscard());
		return list;
	}

	private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1d6211() {
		if (context.get("nc.ui.uif2.tangramlayout.node.HSNode#1d6211") != null)
			return (nc.ui.uif2.tangramlayout.node.HSNode) context
					.get("nc.ui.uif2.tangramlayout.node.HSNode#1d6211");
		nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
		context.put("nc.ui.uif2.tangramlayout.node.HSNode#1d6211", bean);
		bean.setLeft(getCqueryarea());
		bean.setRight(getVslist());
		bean.setDividerLocation(0.22f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.tangramlayout.node.CNode getCqueryarea() {
		if (context.get("cqueryarea") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("cqueryarea");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("cqueryarea", bean);
		bean.setComponent(getQueryArea());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.tangramlayout.node.VSNode getVscard() {
		if (context.get("vscard") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("vscard");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("vscard", bean);
		bean.setUp(getCNode_17cbf17());
		bean.setDown(getCNode_141f726());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_17cbf17() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#17cbf17") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#17cbf17");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#17cbf17", bean);
		bean.setComponent(getCardInfoPnl());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_141f726() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#141f726") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#141f726");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#141f726", bean);
		bean.setName(getI18nFB_e587f9());
		bean.setComponent(getCard());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_e587f9() {
		if (context.get("nc.ui.uif2.I18nFB#e587f9") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#e587f9");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#e587f9", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000106");
		bean.setDefaultValue("卡片");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#e587f9", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.uif2.tangramlayout.node.VSNode getVslist() {
		if (context.get("vslist") != null)
			return (nc.ui.uif2.tangramlayout.node.VSNode) context.get("vslist");
		nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
		context.put("vslist", bean);
		bean.setUp(getCNode_1c915c6());
		bean.setDown(getCNode_aa8b05());
		bean.setDividerLocation(30f);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_1c915c6() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#1c915c6") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#1c915c6");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#1c915c6", bean);
		bean.setComponent(getQueryInfo());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.tangramlayout.node.CNode getCNode_aa8b05() {
		if (context.get("nc.ui.uif2.tangramlayout.node.CNode#aa8b05") != null)
			return (nc.ui.uif2.tangramlayout.node.CNode) context
					.get("nc.ui.uif2.tangramlayout.node.CNode#aa8b05");
		nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
		context.put("nc.ui.uif2.tangramlayout.node.CNode#aa8b05", bean);
		bean.setName(getI18nFB_b4b06c());
		bean.setComponent(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_b4b06c() {
		if (context.get("nc.ui.uif2.I18nFB#b4b06c") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#b4b06c");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#b4b06c", bean);
		bean.setResDir("common");
		bean.setResId("UC001-0000107");
		bean.setDefaultValue("列表");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#b4b06c", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public nc.ui.ic.special.action.SpecialAddAction getAddAction() {
		if (context.get("addAction") != null)
			return (nc.ui.ic.special.action.SpecialAddAction) context
					.get("addAction");
		nc.ui.ic.special.action.SpecialAddAction bean = new nc.ui.ic.special.action.SpecialAddAction();
		context.put("addAction", bean);
		bean.setModel(getIcBizModel());
		bean.setEditorModel(getIcBizEditorModel());
		bean.setInterceptor(getCompositeActionInterceptor_1e82492());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_1e82492() {
		if (context
				.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1e82492") != null)
			return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor) context
					.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1e82492");
		nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
		context.put(
				"nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1e82492",
				bean);
		bean.setInterceptors(getManagedList13());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList13() {
		List list = new ArrayList();
		list.add(getCardInterceptor());
		return list;
	}

	public nc.ui.ic.pf.action.PFApproveStatusInfoActionForIC getApprovingStatusAction() {
		if (context.get("approvingStatusAction") != null)
			return (nc.ui.ic.pf.action.PFApproveStatusInfoActionForIC) context
					.get("approvingStatusAction");
		nc.ui.ic.pf.action.PFApproveStatusInfoActionForIC bean = new nc.ui.ic.pf.action.PFApproveStatusInfoActionForIC();
		context.put("approvingStatusAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialEditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.ic.special.action.SpecialEditAction) context
					.get("editAction");
		nc.ui.ic.special.action.SpecialEditAction bean = new nc.ui.ic.special.action.SpecialEditAction();
		context.put("editAction", bean);
		bean.setModel(getIcBizModel());
		bean.setEditorModel(getIcBizEditorModel());
		bean.setInterceptor(getCardInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialSaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.ic.special.action.SpecialSaveAction) context
					.get("saveAction");
		nc.ui.ic.special.action.SpecialSaveAction bean = new nc.ui.ic.special.action.SpecialSaveAction();
		context.put("saveAction", bean);
		bean.setModel(getIcBizModel());
		bean.setEditorModel(getIcBizEditorModel());
		bean.setEditor(getCard());
		bean.setActionName("WRITE");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialDeleteAction getDeleteAction() {
		if (context.get("deleteAction") != null)
			return (nc.ui.ic.special.action.SpecialDeleteAction) context
					.get("deleteAction");
		nc.ui.ic.special.action.SpecialDeleteAction bean = new nc.ui.ic.special.action.SpecialDeleteAction();
		context.put("deleteAction", bean);
		bean.setModel(getIcBizModel());
		bean.setEditorModel(getIcBizEditorModel());
		bean.setEditor(getCard());
		bean.setActionName("DELETE");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.action.WhsTransCopyAction getCopyAction() {
		if (context.get("copyAction") != null)
			return (nc.ui.ic.m4k.action.WhsTransCopyAction) context
					.get("copyAction");
		nc.ui.ic.m4k.action.WhsTransCopyAction bean = new nc.ui.ic.m4k.action.WhsTransCopyAction();
		context.put("copyAction", bean);
		bean.setModel(getIcBizModel());
		bean.setEditorModel(getIcBizEditorModel());
		bean.setEditor(getCard());
		bean.setInterceptor(getCardInterceptor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialQueryAction getQueryAction() {
		if (context.get("queryAction") != null)
			return (nc.ui.ic.special.action.SpecialQueryAction) context
					.get("queryAction");
		nc.ui.ic.special.action.SpecialQueryAction bean = new nc.ui.ic.special.action.SpecialQueryAction();
		context.put("queryAction", bean);
		bean.setModel(getIcBizModel());
		bean.setDataManager(getModelDataManager());
		bean.setQryCondDLGInitializer(getQryDLGInitializer());
		bean.setShowUpComponent(getList());
		bean.setTemplateContainer(getQueryTemplateContainer());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialRefreshAction getRefreshAction() {
		if (context.get("refreshAction") != null)
			return (nc.ui.ic.special.action.SpecialRefreshAction) context
					.get("refreshAction");
		nc.ui.ic.special.action.SpecialRefreshAction bean = new nc.ui.ic.special.action.SpecialRefreshAction();
		context.put("refreshAction", bean);
		bean.setModel(getIcBizModel());
		bean.setDataManager(getModelDataManager());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialRefreshCardAction getRefreshCardAction() {
		if (context.get("refreshCardAction") != null)
			return (nc.ui.ic.special.action.SpecialRefreshCardAction) context
					.get("refreshCardAction");
		nc.ui.ic.special.action.SpecialRefreshCardAction bean = new nc.ui.ic.special.action.SpecialRefreshCardAction();
		context.put("refreshCardAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialCancelAction getCancelAction() {
		if (context.get("cancelAction") != null)
			return (nc.ui.ic.special.action.SpecialCancelAction) context
					.get("cancelAction");
		nc.ui.ic.special.action.SpecialCancelAction bean = new nc.ui.ic.special.action.SpecialCancelAction();
		context.put("cancelAction", bean);
		bean.setModel(getIcBizModel());
		bean.setEditorModel(getIcBizEditorModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getCommitActionMenu() {
		if (context.get("commitActionMenu") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("commitActionMenu");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("commitActionMenu", bean);
		bean.setCode("ApproveMenu");
		bean.setActions(getManagedList14());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList14() {
		List list = new ArrayList();
		list.add(getCommitAction());
		list.add(getUnCommitAction());
		return list;
	}

	public nc.ui.ic.special.action.SpecialCommitAction getCommitAction() {
		if (context.get("commitAction") != null)
			return (nc.ui.ic.special.action.SpecialCommitAction) context
					.get("commitAction");
		nc.ui.ic.special.action.SpecialCommitAction bean = new nc.ui.ic.special.action.SpecialCommitAction();
		context.put("commitAction", bean);
		bean.setEditorModel(getIcBizEditorModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialSaveCommitAction getSavecommitAction() {
		if (context.get("savecommitAction") != null)
			return (nc.ui.ic.special.action.SpecialSaveCommitAction) context
					.get("savecommitAction");
		nc.ui.ic.special.action.SpecialSaveCommitAction bean = new nc.ui.ic.special.action.SpecialSaveCommitAction(
				getSaveAction(), getCommitAction());
		context.put("savecommitAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialUnCommitAction getUnCommitAction() {
		if (context.get("unCommitAction") != null)
			return (nc.ui.ic.special.action.SpecialUnCommitAction) context
					.get("unCommitAction");
		nc.ui.ic.special.action.SpecialUnCommitAction bean = new nc.ui.ic.special.action.SpecialUnCommitAction();
		context.put("unCommitAction", bean);
		bean.setModel(getIcBizModel());
		bean.setEditorModel(getIcBizEditorModel());
		bean.setActionName("UNSAVEBILL");
		bean.setBillType("4K");
		bean.setFilledUpInFlow(false);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getApproveActionMenu() {
		if (context.get("approveActionMenu") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("approveActionMenu");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("approveActionMenu", bean);
		bean.setCode("ApproveMenu");
		bean.setActions(getManagedList15());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList15() {
		List list = new ArrayList();
		list.add(getApproveAction());
		list.add(getUnApproveAction());
		list.add(getSeparatorAction_1b8fea2());
		list.add(getApprovingStatusAction());
		return list;
	}

	private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1b8fea2() {
		if (context.get("nc.funcnode.ui.action.SeparatorAction#1b8fea2") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("nc.funcnode.ui.action.SeparatorAction#1b8fea2");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("nc.funcnode.ui.action.SeparatorAction#1b8fea2", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialApproveAction getApproveAction() {
		if (context.get("approveAction") != null)
			return (nc.ui.ic.special.action.SpecialApproveAction) context
					.get("approveAction");
		nc.ui.ic.special.action.SpecialApproveAction bean = new nc.ui.ic.special.action.SpecialApproveAction();
		context.put("approveAction", bean);
		bean.setModel(getIcBizModel());
		bean.setActionName("APPROVE");
		bean.setBillType("4K");
		bean.setFilledUpInFlow(false);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialUnApproveAction getUnApproveAction() {
		if (context.get("unApproveAction") != null)
			return (nc.ui.ic.special.action.SpecialUnApproveAction) context
					.get("unApproveAction");
		nc.ui.ic.special.action.SpecialUnApproveAction bean = new nc.ui.ic.special.action.SpecialUnApproveAction();
		context.put("unApproveAction", bean);
		bean.setModel(getIcBizModel());
		bean.setActionName("UNAPPROVE");
		bean.setBillType("4K");
		bean.setFilledUpInFlow(false);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialLinkQueryAction getLinkQryAction() {
		if (context.get("linkQryAction") != null)
			return (nc.ui.ic.special.action.SpecialLinkQueryAction) context
					.get("linkQryAction");
		nc.ui.ic.special.action.SpecialLinkQueryAction bean = new nc.ui.ic.special.action.SpecialLinkQueryAction();
		context.put("linkQryAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialOnhandnumQueryAction getOnhandnumQryAction() {
		if (context.get("onhandnumQryAction") != null)
			return (nc.ui.ic.special.action.SpecialOnhandnumQueryAction) context
					.get("onhandnumQryAction");
		nc.ui.ic.special.action.SpecialOnhandnumQueryAction bean = new nc.ui.ic.special.action.SpecialOnhandnumQueryAction();
		context.put("onhandnumQryAction", bean);
		bean.setEditorModel(getIcBizEditorModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getLinkQryBrowseGroupAction() {
		if (context.get("linkQryBrowseGroupAction") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("linkQryBrowseGroupAction");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("linkQryBrowseGroupAction", bean);
		bean.setCode("linkQryAction");
		bean.setName(getI18nFB_88812());
		bean.setActions(getManagedList16());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_88812() {
		if (context.get("nc.ui.uif2.I18nFB#88812") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#88812");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#88812", bean);
		bean.setResDir("4008001_0");
		bean.setResId("04008001-0742");
		bean.setDefaultValue("联查");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#88812", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList16() {
		List list = new ArrayList();
		list.add(getLinkQryAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction() {
		if (context.get("outputAction") != null)
			return (nc.ui.pubapp.uif2app.actions.OutputAction) context
					.get("outputAction");
		nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
		context.put("outputAction", bean);
		bean.setModel(getIcBizModel());
		bean.setParent(getCard());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.GroupAction getPrintMngAction() {
		if (context.get("printMngAction") != null)
			return (nc.funcnode.ui.action.GroupAction) context
					.get("printMngAction");
		nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
		context.put("printMngAction", bean);
		bean.setCode("printMngAction");
		bean.setActions(getManagedList17());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList17() {
		List list = new ArrayList();
		list.add(getTemplatePrintAction());
		list.add(getTemplatePreviewAction());
		list.add(getOutputAction());
		list.add(getPrintCountQueryAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.BodyAddLineAction getBodyAddLineAction() {
		if (context.get("bodyAddLineAction") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyAddLineAction) context
					.get("bodyAddLineAction");
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyAddLineAction();
		context.put("bodyAddLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction() {
		if (context.get("bodyDelLineAction") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction) context
					.get("bodyDelLineAction");
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
		context.put("bodyDelLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction() {
		if (context.get("bodyInsertLineAction") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction) context
					.get("bodyInsertLineAction");
		nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
		context.put("bodyInsertLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction() {
		if (context.get("bodyCopyLineAction") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction) context
					.get("bodyCopyLineAction");
		nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
		context.put("bodyCopyLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction() {
		if (context.get("bodyPasteLineAction") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction) context
					.get("bodyPasteLineAction");
		nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
		context.put("bodyPasteLineAction", bean);
		bean.setClearItems(getManagedList18());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList18() {
		List list = new ArrayList();
		list.add("cspecialbid");
		list.add("ts");
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction() {
		if (context.get("bodyPasteToTailAction") != null)
			return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction) context
					.get("bodyPasteToTailAction");
		nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
		context.put("bodyPasteToTailAction", bean);
		bean.setClearItems(getManagedList19());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList19() {
		List list = new ArrayList();
		list.add("cgeneralbid");
		list.add("ts");
		return list;
	}

	public nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction() {
		if (context.get("rearrangeRowNoBodyLineAction") != null)
			return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction) context
					.get("rearrangeRowNoBodyLineAction");
		nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
		context.put("rearrangeRowNoBodyLineAction", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.QueryOnhandForSpecialAction getDisplayOrhideAction() {
		if (context.get("displayOrhideAction") != null)
			return (nc.ui.ic.special.action.QueryOnhandForSpecialAction) context
					.get("displayOrhideAction");
		nc.ui.ic.special.action.QueryOnhandForSpecialAction bean = new nc.ui.ic.special.action.QueryOnhandForSpecialAction();
		context.put("displayOrhideAction", bean);
		bean.setEditorModel(getIcBizEditorModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialAttachmentAction getAttachMentMngAction() {
		if (context.get("attachMentMngAction") != null)
			return (nc.ui.ic.special.action.SpecialAttachmentAction) context
					.get("attachMentMngAction");
		nc.ui.ic.special.action.SpecialAttachmentAction bean = new nc.ui.ic.special.action.SpecialAttachmentAction();
		context.put("attachMentMngAction", bean);
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialMDPreviewAction getTemplatePreviewAction() {
		if (context.get("templatePreviewAction") != null)
			return (nc.ui.ic.special.action.SpecialMDPreviewAction) context
					.get("templatePreviewAction");
		nc.ui.ic.special.action.SpecialMDPreviewAction bean = new nc.ui.ic.special.action.SpecialMDPreviewAction();
		context.put("templatePreviewAction", bean);
		bean.setPreview(true);
		bean.setModel(getIcBizModel());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.action.SpecialMDPrintAction getTemplatePrintAction() {
		if (context.get("templatePrintAction") != null)
			return (nc.ui.ic.special.action.SpecialMDPrintAction) context
					.get("templatePrintAction");
		nc.ui.ic.special.action.SpecialMDPrintAction bean = new nc.ui.ic.special.action.SpecialMDPrintAction();
		context.put("templatePrintAction", bean);
		bean.setPreview(false);
		bean.setModel(getIcBizModel());
		bean.setBeforePrintDataProcess(getPrintProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessor() {
		if (context.get("transferViewProcessor") != null)
			return (nc.ui.pubapp.billref.dest.TransferViewProcessor) context
					.get("transferViewProcessor");
		nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
		context.put("transferViewProcessor", bean);
		bean.setList(getList());
		bean.setBillForm(getCard());
		bean.setActionContainer(getActionsOfList());
		bean.setCardActionContainer(getActionsOfCard());
		bean.setSaveAction(getSaveAction());
		bean.setCommitAction(getCommitAction());
		bean.setCancelAction(getCancelAction());
		bean.setTransferLogic(getDefaultBillDataLogic_55dadd());
		bean.setQueryAreaShell(getQueryArea());
		bean.setQueryInfoToolbarPanel(getQueryInfo());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.pubapp.billref.dest.DefaultBillDataLogic getDefaultBillDataLogic_55dadd() {
		if (context
				.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#55dadd") != null)
			return (nc.ui.pubapp.billref.dest.DefaultBillDataLogic) context
					.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#55dadd");
		nc.ui.pubapp.billref.dest.DefaultBillDataLogic bean = new nc.ui.pubapp.billref.dest.DefaultBillDataLogic();
		context.put("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#55dadd",
				bean);
		bean.setBillForm(getCard());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.Pk_orgHandler getPk_orgHandler() {
		if (context.get("pk_orgHandler") != null)
			return (nc.ui.ic.pub.handler.Pk_orgHandler) context
					.get("pk_orgHandler");
		nc.ui.ic.pub.handler.Pk_orgHandler bean = new nc.ui.ic.pub.handler.Pk_orgHandler();
		context.put("pk_orgHandler", bean);
		bean.setEditorModel(getIcBizEditorModel());
		bean.setContext(getIcUIContext());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.CbizidHandler getCbizidHandler() {
		if (context.get("cbizidHandler") != null)
			return (nc.ui.ic.special.handler.CbizidHandler) context
					.get("cbizidHandler");
		nc.ui.ic.special.handler.CbizidHandler bean = new nc.ui.ic.special.handler.CbizidHandler();
		context.put("cbizidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.CwhsmanageridHandlerHandler getCwhsmanageridHandler() {
		if (context.get("cwhsmanageridHandler") != null)
			return (nc.ui.ic.pub.handler.CwhsmanageridHandlerHandler) context
					.get("cwhsmanageridHandler");
		nc.ui.ic.pub.handler.CwhsmanageridHandlerHandler bean = new nc.ui.ic.pub.handler.CwhsmanageridHandlerHandler();
		context.put("cwhsmanageridHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.CmaterialvidHandler getCmaterialvidHandler() {
		if (context.get("cmaterialvidHandler") != null)
			return (nc.ui.ic.special.handler.CmaterialvidHandler) context
					.get("cmaterialvidHandler");
		nc.ui.ic.special.handler.CmaterialvidHandler bean = new nc.ui.ic.special.handler.CmaterialvidHandler();
		context.put("cmaterialvidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.CastunitidHandler getCastunitidHandler() {
		if (context.get("castunitidHandler") != null)
			return (nc.ui.ic.special.handler.CastunitidHandler) context
					.get("castunitidHandler");
		nc.ui.ic.special.handler.CastunitidHandler bean = new nc.ui.ic.special.handler.CastunitidHandler();
		context.put("castunitidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.NumHandler getNumHandler() {
		if (context.get("numHandler") != null)
			return (nc.ui.ic.special.handler.NumHandler) context
					.get("numHandler");
		nc.ui.ic.special.handler.NumHandler bean = new nc.ui.ic.special.handler.NumHandler();
		context.put("numHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.MainNumHandler getMainNumHandler() {
		if (context.get("mainNumHandler") != null)
			return (nc.ui.ic.special.handler.MainNumHandler) context
					.get("mainNumHandler");
		nc.ui.ic.special.handler.MainNumHandler bean = new nc.ui.ic.special.handler.MainNumHandler();
		context.put("mainNumHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.NgrossNumHandler getNgrossNumHandler() {
		if (context.get("ngrossNumHandler") != null)
			return (nc.ui.ic.special.handler.NgrossNumHandler) context
					.get("ngrossNumHandler");
		nc.ui.ic.special.handler.NgrossNumHandler bean = new nc.ui.ic.special.handler.NgrossNumHandler();
		context.put("ngrossNumHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.CrownoHandler getCrownoHandler() {
		if (context.get("crownoHandler") != null)
			return (nc.ui.ic.pub.handler.CrownoHandler) context
					.get("crownoHandler");
		nc.ui.ic.pub.handler.CrownoHandler bean = new nc.ui.ic.pub.handler.CrownoHandler();
		context.put("crownoHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.VchangerateHandler getVchangerateHandler() {
		if (context.get("vchangerateHandler") != null)
			return (nc.ui.ic.special.handler.VchangerateHandler) context
					.get("vchangerateHandler");
		nc.ui.ic.special.handler.VchangerateHandler bean = new nc.ui.ic.special.handler.VchangerateHandler();
		context.put("vchangerateHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.NCostpriceHandler getNcostPriceHandler() {
		if (context.get("ncostPriceHandler") != null)
			return (nc.ui.ic.special.handler.NCostpriceHandler) context
					.get("ncostPriceHandler");
		nc.ui.ic.special.handler.NCostpriceHandler bean = new nc.ui.ic.special.handler.NCostpriceHandler();
		context.put("ncostPriceHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.NCostmnyHandler getNcostmnyHandler() {
		if (context.get("ncostmnyHandler") != null)
			return (nc.ui.ic.special.handler.NCostmnyHandler) context
					.get("ncostmnyHandler");
		nc.ui.ic.special.handler.NCostmnyHandler bean = new nc.ui.ic.special.handler.NCostmnyHandler();
		context.put("ncostmnyHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.handler.VbatchcodeHandlerForWhstrans getVbatchcodeHandler() {
		if (context.get("vbatchcodeHandler") != null)
			return (nc.ui.ic.m4k.handler.VbatchcodeHandlerForWhstrans) context
					.get("vbatchcodeHandler");
		nc.ui.ic.m4k.handler.VbatchcodeHandlerForWhstrans bean = new nc.ui.ic.m4k.handler.VbatchcodeHandlerForWhstrans();
		context.put("vbatchcodeHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.CstateidHandler getCstateidHandler() {
		if (context.get("cstateidHandler") != null)
			return (nc.ui.ic.special.handler.CstateidHandler) context
					.get("cstateidHandler");
		nc.ui.ic.special.handler.CstateidHandler bean = new nc.ui.ic.special.handler.CstateidHandler();
		context.put("cstateidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.CtrantypeidHandler getCtrantypeidHandler() {
		if (context.get("ctrantypeidHandler") != null)
			return (nc.ui.ic.special.handler.CtrantypeidHandler) context
					.get("ctrantypeidHandler");
		nc.ui.ic.special.handler.CtrantypeidHandler bean = new nc.ui.ic.special.handler.CtrantypeidHandler();
		context.put("ctrantypeidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.special.handler.CdptvidHandler getCdptvidHandler() {
		if (context.get("cdptvidHandler") != null)
			return (nc.ui.ic.special.handler.CdptvidHandler) context
					.get("cdptvidHandler");
		nc.ui.ic.special.handler.CdptvidHandler bean = new nc.ui.ic.special.handler.CdptvidHandler();
		context.put("cdptvidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap getParentCardEditHandlerMap() {
		if (context.get("parentCardEditHandlerMap") != null)
			return (nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap) context
					.get("parentCardEditHandlerMap");
		nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap bean = new nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap();
		context.put("parentCardEditHandlerMap", bean);
		bean.setHandlerMap(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("cbizid", getCbizidHandler());
		map.put("cwhsmanagerid", getCwhsmanageridHandler());
		map.put("cmaterialvid", getCmaterialvidHandler());
		map.put("castunitid", getCastunitidHandler());
		map.put("vchangerate", getVchangerateHandler());
		map.put("nassistnum", getNumHandler());
		map.put("nnum", getMainNumHandler());
		map.put("crowno", getCrownoHandler());
		map.put("ncostprice", getNcostPriceHandler());
		map.put("ncostmny", getNcostmnyHandler());
		map.put("vbatchcode", getVbatchcodeHandler());
		map.put("cstateid", getCstateidHandler());
		map.put("ctrantypeid", getCtrantypeidHandler());
		map.put("ngrossnum", getNgrossNumHandler());
		map.put("cdptvid", getCdptvidHandler());
		return map;
	}

	public nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap getChildCardEditHandlerMap() {
		if (context.get("childCardEditHandlerMap") != null)
			return (nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap) context
					.get("childCardEditHandlerMap");
		nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap bean = new nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap();
		context.put("childCardEditHandlerMap", bean);
		bean.setHandlerMap(getManagedMap1());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap1() {
		Map map = new HashMap();
		map.put("ngrossnum", getNgrossnumHandler());
		map.put("cotherwhid", getCotherwhidHandler());
		map.put("cffileid", getCffileidHandler());
		map.put("cotherdptvid", getCotherdptvidHandler());
		map.put("clocationid", getClocationidHandler());
		return map;
	}

	public nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMediator getCardEditEventHandlerMediator() {
		if (context.get("cardEditEventHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMediator) context
					.get("cardEditEventHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMediator();
		context.put("cardEditEventHandlerMediator", bean);
		bean.setEditorModel(getIcBizEditorModel());
		bean.setParentHandlerMap(getParentCardEditHandlerMap());
		bean.setChildHandlerMap(getChildCardEditHandlerMap());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodySortEventHandler getCardBodySortHandler() {
		if (context.get("cardBodySortHandler") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodySortEventHandler) context
					.get("cardBodySortHandler");
		nc.ui.ic.pub.handler.card.ICCardBodySortEventHandler bean = new nc.ui.ic.pub.handler.card.ICCardBodySortEventHandler();
		context.put("cardBodySortHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodySortEventHandlerMediator getCardBodySortEventHandlerMediator() {
		if (context.get("cardBodySortEventHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodySortEventHandlerMediator) context
					.get("cardBodySortEventHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardBodySortEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardBodySortEventHandlerMediator();
		context.put("cardBodySortEventHandlerMediator", bean);
		bean.setHandler(getCardBodySortHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyMenuActionEventHandler getCardBodyMenuActionHandler() {
		if (context.get("cardBodyMenuActionHandler") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyMenuActionEventHandler) context
					.get("cardBodyMenuActionHandler");
		nc.ui.ic.pub.handler.card.ICCardBodyMenuActionEventHandler bean = new nc.ui.ic.pub.handler.card.ICCardBodyMenuActionEventHandler();
		context.put("cardBodyMenuActionHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyMenuActionEventHandlerMediator getCardBodyMenuActionHandlerMediator() {
		if (context.get("cardBodyMenuActionHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyMenuActionEventHandlerMediator) context
					.get("cardBodyMenuActionHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardBodyMenuActionEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardBodyMenuActionEventHandlerMediator();
		context.put("cardBodyMenuActionHandlerMediator", bean);
		bean.setHandler(getCardBodyMenuActionHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyRowChangedEventHandler getCardBodyRowChangedHandler() {
		if (context.get("cardBodyRowChangedHandler") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyRowChangedEventHandler) context
					.get("cardBodyRowChangedHandler");
		nc.ui.ic.pub.handler.card.ICCardBodyRowChangedEventHandler bean = new nc.ui.ic.pub.handler.card.ICCardBodyRowChangedEventHandler();
		context.put("cardBodyRowChangedHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyRowChangedEventHandlerMediator getCardBodyRowChangedHandlerMediator() {
		if (context.get("cardBodyRowChangedHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyRowChangedEventHandlerMediator) context
					.get("cardBodyRowChangedHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardBodyRowChangedEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardBodyRowChangedEventHandlerMediator();
		context.put("cardBodyRowChangedHandlerMediator", bean);
		bean.setHandler(getCardBodyRowChangedHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyRowEditEventHandler getCardBodyRowEditHandler() {
		if (context.get("cardBodyRowEditHandler") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyRowEditEventHandler) context
					.get("cardBodyRowEditHandler");
		nc.ui.ic.pub.handler.card.ICCardBodyRowEditEventHandler bean = new nc.ui.ic.pub.handler.card.ICCardBodyRowEditEventHandler();
		context.put("cardBodyRowEditHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyRowEditEventHandlerMediator getCardBodyRowEditHandlerMediator() {
		if (context.get("cardBodyRowEditHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyRowEditEventHandlerMediator) context
					.get("cardBodyRowEditHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardBodyRowEditEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardBodyRowEditEventHandlerMediator();
		context.put("cardBodyRowEditHandlerMediator", bean);
		bean.setHandler(getCardBodyRowEditHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyTabChangedEventHandler getCardBodyTabChangedHandler() {
		if (context.get("cardBodyTabChangedHandler") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyTabChangedEventHandler) context
					.get("cardBodyTabChangedHandler");
		nc.ui.ic.pub.handler.card.ICCardBodyTabChangedEventHandler bean = new nc.ui.ic.pub.handler.card.ICCardBodyTabChangedEventHandler();
		context.put("cardBodyTabChangedHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyTabChangedEventHandlerMediator getCardBodyTabChangedHandlerMediator() {
		if (context.get("cardBodyTabChangedHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyTabChangedEventHandlerMediator) context
					.get("cardBodyTabChangedHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardBodyTabChangedEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardBodyTabChangedEventHandlerMediator();
		context.put("cardBodyTabChangedHandlerMediator", bean);
		bean.setHandler(getCardBodyTabChangedHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyTotalEventHandler getCardBodyTotalHandler() {
		if (context.get("cardBodyTotalHandler") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyTotalEventHandler) context
					.get("cardBodyTotalHandler");
		nc.ui.ic.pub.handler.card.ICCardBodyTotalEventHandler bean = new nc.ui.ic.pub.handler.card.ICCardBodyTotalEventHandler();
		context.put("cardBodyTotalHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardBodyTotalEventHandlerMediator getCardBodyTotalHandlerMediator() {
		if (context.get("cardBodyTotalHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardBodyTotalEventHandlerMediator) context
					.get("cardBodyTotalHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardBodyTotalEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardBodyTotalEventHandlerMediator();
		context.put("cardBodyTotalHandlerMediator", bean);
		bean.setHandler(getCardBodyTotalHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardPanelLoadEventHandler getCardPanelLoadHandler() {
		if (context.get("cardPanelLoadHandler") != null)
			return (nc.ui.ic.pub.handler.card.ICCardPanelLoadEventHandler) context
					.get("cardPanelLoadHandler");
		nc.ui.ic.pub.handler.card.ICCardPanelLoadEventHandler bean = new nc.ui.ic.pub.handler.card.ICCardPanelLoadEventHandler();
		context.put("cardPanelLoadHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardPanelLoadEventHandlerMediator getCardPanelLoadHandlerMediator() {
		if (context.get("cardPanelLoadHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardPanelLoadEventHandlerMediator) context
					.get("cardPanelLoadHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardPanelLoadEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardPanelLoadEventHandlerMediator();
		context.put("cardPanelLoadHandlerMediator", bean);
		bean.setHandler(getCardPanelLoadHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.card.ICCardLoadDataEventHandlerMediator getCardLoadDataHandlerMediator() {
		if (context.get("cardLoadDataHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.card.ICCardLoadDataEventHandlerMediator) context
					.get("cardLoadDataHandlerMediator");
		nc.ui.ic.pub.handler.card.ICCardLoadDataEventHandlerMediator bean = new nc.ui.ic.pub.handler.card.ICCardLoadDataEventHandlerMediator();
		context.put("cardLoadDataHandlerMediator", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListSortEventHandler getListSortHandler() {
		if (context.get("listSortHandler") != null)
			return (nc.ui.ic.pub.handler.list.ICListSortEventHandler) context
					.get("listSortHandler");
		nc.ui.ic.pub.handler.list.ICListSortEventHandler bean = new nc.ui.ic.pub.handler.list.ICListSortEventHandler();
		context.put("listSortHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListSortEventHandlerMediator getListSortHandlerMediator() {
		if (context.get("listSortHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.list.ICListSortEventHandlerMediator) context
					.get("listSortHandlerMediator");
		nc.ui.ic.pub.handler.list.ICListSortEventHandlerMediator bean = new nc.ui.ic.pub.handler.list.ICListSortEventHandlerMediator();
		context.put("listSortHandlerMediator", bean);
		bean.setHandler(getListSortHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListDoubleClickEventHandler getListDoubleClickHandler() {
		if (context.get("listDoubleClickHandler") != null)
			return (nc.ui.ic.pub.handler.list.ICListDoubleClickEventHandler) context
					.get("listDoubleClickHandler");
		nc.ui.ic.pub.handler.list.ICListDoubleClickEventHandler bean = new nc.ui.ic.pub.handler.list.ICListDoubleClickEventHandler();
		context.put("listDoubleClickHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListDoubleClickEventHandlerMediator getListDoubleClickHandlerMediator() {
		if (context.get("listDoubleClickHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.list.ICListDoubleClickEventHandlerMediator) context
					.get("listDoubleClickHandlerMediator");
		nc.ui.ic.pub.handler.list.ICListDoubleClickEventHandlerMediator bean = new nc.ui.ic.pub.handler.list.ICListDoubleClickEventHandlerMediator();
		context.put("listDoubleClickHandlerMediator", bean);
		bean.setHandler(getListDoubleClickHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListRowChangedEventHandler getListRowChangedHandler() {
		if (context.get("listRowChangedHandler") != null)
			return (nc.ui.ic.pub.handler.list.ICListRowChangedEventHandler) context
					.get("listRowChangedHandler");
		nc.ui.ic.pub.handler.list.ICListRowChangedEventHandler bean = new nc.ui.ic.pub.handler.list.ICListRowChangedEventHandler();
		context.put("listRowChangedHandler", bean);
		bean.setListView(getList());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListRowChangedEventHandlerMediator getListRowChangedHandlerMediator() {
		if (context.get("listRowChangedHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.list.ICListRowChangedEventHandlerMediator) context
					.get("listRowChangedHandlerMediator");
		nc.ui.ic.pub.handler.list.ICListRowChangedEventHandlerMediator bean = new nc.ui.ic.pub.handler.list.ICListRowChangedEventHandlerMediator();
		context.put("listRowChangedHandlerMediator", bean);
		bean.setHandler(getListRowChangedHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListBodyTotalEventHandler getListBodyTotalHandler() {
		if (context.get("listBodyTotalHandler") != null)
			return (nc.ui.ic.pub.handler.list.ICListBodyTotalEventHandler) context
					.get("listBodyTotalHandler");
		nc.ui.ic.pub.handler.list.ICListBodyTotalEventHandler bean = new nc.ui.ic.pub.handler.list.ICListBodyTotalEventHandler();
		context.put("listBodyTotalHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListBodyTotalEventHandlerMediator getListBodyTotalHandlerMediator() {
		if (context.get("listBodyTotalHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.list.ICListBodyTotalEventHandlerMediator) context
					.get("listBodyTotalHandlerMediator");
		nc.ui.ic.pub.handler.list.ICListBodyTotalEventHandlerMediator bean = new nc.ui.ic.pub.handler.list.ICListBodyTotalEventHandlerMediator();
		context.put("listBodyTotalHandlerMediator", bean);
		bean.setHandler(getListBodyTotalHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListPanelLoadEventHandler getListPanelLoadEventHandler() {
		if (context.get("listPanelLoadEventHandler") != null)
			return (nc.ui.ic.pub.handler.list.ICListPanelLoadEventHandler) context
					.get("listPanelLoadEventHandler");
		nc.ui.ic.pub.handler.list.ICListPanelLoadEventHandler bean = new nc.ui.ic.pub.handler.list.ICListPanelLoadEventHandler();
		context.put("listPanelLoadEventHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListPanelLoadEventHandlerMediator getListPanelLoadEventHandlerMediator() {
		if (context.get("listPanelLoadEventHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.list.ICListPanelLoadEventHandlerMediator) context
					.get("listPanelLoadEventHandlerMediator");
		nc.ui.ic.pub.handler.list.ICListPanelLoadEventHandlerMediator bean = new nc.ui.ic.pub.handler.list.ICListPanelLoadEventHandlerMediator();
		context.put("listPanelLoadEventHandlerMediator", bean);
		bean.setHandler(getListPanelLoadEventHandler());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.list.ICListLoadDataEventHandlerMediator getListLoadDataEventHandlerMediator() {
		if (context.get("listLoadDataEventHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.list.ICListLoadDataEventHandlerMediator) context
					.get("listLoadDataEventHandlerMediator");
		nc.ui.ic.pub.handler.list.ICListLoadDataEventHandlerMediator bean = new nc.ui.ic.pub.handler.list.ICListLoadDataEventHandlerMediator();
		context.put("listLoadDataEventHandlerMediator", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.ICUiStateChangeEventHandler getIcUiStateChangeEventHandler() {
		if (context.get("icUiStateChangeEventHandler") != null)
			return (nc.ui.ic.pub.handler.ICUiStateChangeEventHandler) context
					.get("icUiStateChangeEventHandler");
		nc.ui.ic.pub.handler.ICUiStateChangeEventHandler bean = new nc.ui.ic.pub.handler.ICUiStateChangeEventHandler();
		context.put("icUiStateChangeEventHandler", bean);
		bean.setEditorModel(getIcBizEditorModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.handler.ICUiStateChangeEventHandlerMediator getIcUiStateChangeEventHandlerMediator() {
		if (context.get("icUiStateChangeEventHandlerMediator") != null)
			return (nc.ui.ic.pub.handler.ICUiStateChangeEventHandlerMediator) context
					.get("icUiStateChangeEventHandlerMediator");
		nc.ui.ic.pub.handler.ICUiStateChangeEventHandlerMediator bean = new nc.ui.ic.pub.handler.ICUiStateChangeEventHandlerMediator();
		context.put("icUiStateChangeEventHandlerMediator", bean);
		bean.setHandler(getIcUiStateChangeEventHandler());
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
		bean.setModel(getIcBizModel());
		bean.setHandlerMap(getManagedMap2());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap2() {
		Map map = new HashMap();
		map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",
				getManagedList20());
		map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",
				getManagedList21());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",
				getManagedList22());
		map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",
				getManagedList23());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",
				getManagedList24());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeBatchEditEvent",
				getManagedList25());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeSortEvent",
				getManagedList26());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterSortEvent",
				getManagedList27());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyMenuActionEvent",
				getManagedList28());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent",
				getManagedList29());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyRowEditEvent",
				getManagedList30());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyTabChangedEvent",
				getManagedList31());
		map.put("nc.ui.pubapp.uif2app.event.card.CardBodyTotalEvent",
				getManagedList32());
		map.put("nc.ui.pubapp.uif2app.event.card.CardPanelLoadEvent",
				getManagedList33());
		map.put("nc.ui.pubapp.uif2app.event.list.ListHeadBeforeSortEvent",
				getManagedList34());
		map.put("nc.ui.pubapp.uif2app.event.list.ListHeadAfterSortEvent",
				getManagedList35());
		map.put("nc.ui.pubapp.uif2app.event.list.ListBodyBeforeSortEvent",
				getManagedList36());
		map.put("nc.ui.pubapp.uif2app.event.list.ListBodyAfterSortEvent",
				getManagedList37());
		map.put("nc.ui.pubapp.uif2app.event.list.ListHeadDoubleClickEvent",
				getManagedList38());
		map.put("nc.ui.pubapp.uif2app.event.list.ListBodyDoubleClickEvent",
				getManagedList39());
		map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",
				getManagedList40());
		map.put("nc.ui.pubapp.uif2app.event.list.ListBodyRowChangedEvent",
				getManagedList41());
		map.put("nc.ui.pubapp.uif2app.event.list.ListBodyTotalEvent",
				getManagedList42());
		map.put("nc.ui.pubapp.uif2app.event.list.ListPanelLoadEvent",
				getManagedList43());
		map.put("nc.ui.pubapp.uif2app.event.AppUiStateChangeEvent",
				getManagedList44());
		return map;
	}

	private List getManagedList20() {
		List list = new ArrayList();
		list.add(getPk_orgHandler());
		return list;
	}

	private List getManagedList21() {
		List list = new ArrayList();
		list.add(getCardEditEventHandlerMediator());
		return list;
	}

	private List getManagedList22() {
		List list = new ArrayList();
		list.add(getCardEditEventHandlerMediator());
		return list;
	}

	private List getManagedList23() {
		List list = new ArrayList();
		list.add(getCardEditEventHandlerMediator());
		return list;
	}

	private List getManagedList24() {
		List list = new ArrayList();
		list.add(getCardEditEventHandlerMediator());
		return list;
	}

	private List getManagedList25() {
		List list = new ArrayList();
		list.add(getCardEditEventHandlerMediator());
		return list;
	}

	private List getManagedList26() {
		List list = new ArrayList();
		list.add(getCardBodySortEventHandlerMediator());
		return list;
	}

	private List getManagedList27() {
		List list = new ArrayList();
		list.add(getCardBodySortEventHandlerMediator());
		return list;
	}

	private List getManagedList28() {
		List list = new ArrayList();
		list.add(getCardBodyMenuActionHandlerMediator());
		return list;
	}

	private List getManagedList29() {
		List list = new ArrayList();
		list.add(getCardBodyRowChangedHandlerMediator());
		return list;
	}

	private List getManagedList30() {
		List list = new ArrayList();
		list.add(getCardBodyRowEditHandlerMediator());
		return list;
	}

	private List getManagedList31() {
		List list = new ArrayList();
		list.add(getCardBodyTabChangedHandlerMediator());
		return list;
	}

	private List getManagedList32() {
		List list = new ArrayList();
		list.add(getCardBodyTotalHandlerMediator());
		return list;
	}

	private List getManagedList33() {
		List list = new ArrayList();
		list.add(getCardPanelLoadHandlerMediator());
		return list;
	}

	private List getManagedList34() {
		List list = new ArrayList();
		list.add(getListSortHandlerMediator());
		return list;
	}

	private List getManagedList35() {
		List list = new ArrayList();
		list.add(getListSortHandlerMediator());
		return list;
	}

	private List getManagedList36() {
		List list = new ArrayList();
		list.add(getListSortHandlerMediator());
		return list;
	}

	private List getManagedList37() {
		List list = new ArrayList();
		list.add(getListSortHandlerMediator());
		return list;
	}

	private List getManagedList38() {
		List list = new ArrayList();
		list.add(getListDoubleClickHandlerMediator());
		return list;
	}

	private List getManagedList39() {
		List list = new ArrayList();
		list.add(getListDoubleClickHandlerMediator());
		return list;
	}

	private List getManagedList40() {
		List list = new ArrayList();
		list.add(getListRowChangedHandlerMediator());
		return list;
	}

	private List getManagedList41() {
		List list = new ArrayList();
		list.add(getListRowChangedHandlerMediator());
		return list;
	}

	private List getManagedList42() {
		List list = new ArrayList();
		list.add(getListBodyTotalHandlerMediator());
		return list;
	}

	private List getManagedList43() {
		List list = new ArrayList();
		list.add(getListPanelLoadEventHandlerMediator());
		return list;
	}

	private List getManagedList44() {
		List list = new ArrayList();
		list.add(getIcUiStateChangeEventHandlerMediator());
		return list;
	}

	public nc.ui.ic.special.model.ICSpeBizModel getIcBizModel() {
		if (context.get("icBizModel") != null)
			return (nc.ui.ic.special.model.ICSpeBizModel) context
					.get("icBizModel");
		nc.ui.ic.special.model.ICSpeBizModel bean = new nc.ui.ic.special.model.ICSpeBizModel();
		context.put("icBizModel", bean);
		bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
		bean.setIcUIContext(getIcUIContext());
		bean.setBillType("4K");
		bean.setPowerValidate(true);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.model.WhsTransModelService getManageModelService() {
		if (context.get("manageModelService") != null)
			return (nc.ui.ic.m4k.model.WhsTransModelService) context
					.get("manageModelService");
		nc.ui.ic.m4k.model.WhsTransModelService bean = new nc.ui.ic.m4k.model.WhsTransModelService();
		context.put("manageModelService", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.handler.NgrossNumHandler getNgrossnumHandler() {
		if (context.get("ngrossnumHandler") != null)
			return (nc.ui.ic.m4k.handler.NgrossNumHandler) context
					.get("ngrossnumHandler");
		nc.ui.ic.m4k.handler.NgrossNumHandler bean = new nc.ui.ic.m4k.handler.NgrossNumHandler();
		context.put("ngrossnumHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.handler.CotherWhsHandler getCotherwhidHandler() {
		if (context.get("cotherwhidHandler") != null)
			return (nc.ui.ic.m4k.handler.CotherWhsHandler) context
					.get("cotherwhidHandler");
		nc.ui.ic.m4k.handler.CotherWhsHandler bean = new nc.ui.ic.m4k.handler.CotherWhsHandler();
		context.put("cotherwhidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.handler.CotherdptvidHandler getCotherdptvidHandler() {
		if (context.get("cotherdptvidHandler") != null)
			return (nc.ui.ic.m4k.handler.CotherdptvidHandler) context
					.get("cotherdptvidHandler");
		nc.ui.ic.m4k.handler.CotherdptvidHandler bean = new nc.ui.ic.m4k.handler.CotherdptvidHandler();
		context.put("cotherdptvidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.general.handler.CffileidHandler getCffileidHandler() {
		if (context.get("cffileidHandler") != null)
			return (nc.ui.ic.general.handler.CffileidHandler) context
					.get("cffileidHandler");
		nc.ui.ic.general.handler.CffileidHandler bean = new nc.ui.ic.general.handler.CffileidHandler();
		context.put("cffileidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.handler.CotherbizidHandler getCotherbizidHandler() {
		if (context.get("cotherbizidHandler") != null)
			return (nc.ui.ic.m4k.handler.CotherbizidHandler) context
					.get("cotherbizidHandler");
		nc.ui.ic.m4k.handler.CotherbizidHandler bean = new nc.ui.ic.m4k.handler.CotherbizidHandler();
		context.put("cotherbizidHandler", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.handler.ClocationidHandler getClocationidHandler() {
		if (context.get("clocationidHandler") != null)
			return (nc.ui.ic.m4k.handler.ClocationidHandler) context
					.get("clocationidHandler");
		nc.ui.ic.m4k.handler.ClocationidHandler bean = new nc.ui.ic.m4k.handler.ClocationidHandler();
		context.put("clocationidHandler", bean);
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

	public nc.ui.ic.m4k.action.DirectOutAction getDirectOutAction() {
		if (context.get("directOutAction") != null)
			return (nc.ui.ic.m4k.action.DirectOutAction) context
					.get("directOutAction");
		nc.ui.ic.m4k.action.DirectOutAction bean = new nc.ui.ic.m4k.action.DirectOutAction();
		context.put("directOutAction", bean);
		bean.setEditorModel(getIcBizEditorModel());
		bean.setBizModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.action.TransOutAction getTransOutAction() {
		if (context.get("transOutAction") != null)
			return (nc.ui.ic.m4k.action.TransOutAction) context
					.get("transOutAction");
		nc.ui.ic.m4k.action.TransOutAction bean = new nc.ui.ic.m4k.action.TransOutAction();
		context.put("transOutAction", bean);
		bean.setEditorModel(getIcBizEditorModel());
		bean.setBizModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.action.TransInAction getTransInAction() {
		if (context.get("transInAction") != null)
			return (nc.ui.ic.m4k.action.TransInAction) context
					.get("transInAction");
		nc.ui.ic.m4k.action.TransInAction bean = new nc.ui.ic.m4k.action.TransInAction();
		context.put("transInAction", bean);
		bean.setEditorModel(getIcBizEditorModel());
		bean.setBizModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getRealationGroupAction() {
		if (context.get("realationGroupAction") != null)
			return (nc.funcnode.ui.action.MenuAction) context
					.get("realationGroupAction");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("realationGroupAction", bean);
		bean.setCode("directOutAction");
		bean.setName(getI18nFB_fd4626());
		bean.setActions(getManagedList45());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_fd4626() {
		if (context.get("nc.ui.uif2.I18nFB#fd4626") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#fd4626");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#fd4626", bean);
		bean.setResDir("4008001_0");
		bean.setResId("04008001-0741");
		bean.setDefaultValue("辅助功能");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#fd4626", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList45() {
		List list = new ArrayList();
		list.add(getDirectOutAction());
		list.add(getTransOutAction());
		list.add(getTransInAction());
		return list;
	}

	public nc.ui.ic.m4k.action.RowQuyQtyAction getRowQuyQtyAction() {
		if (context.get("rowQuyQtyAction") != null)
			return (nc.ui.ic.m4k.action.RowQuyQtyAction) context
					.get("rowQuyQtyAction");
		nc.ui.ic.m4k.action.RowQuyQtyAction bean = new nc.ui.ic.m4k.action.RowQuyQtyAction();
		context.put("rowQuyQtyAction", bean);
		bean.setEditorModel(getIcBizEditorModel());
		bean.setBizModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
		if (context.get("toftpanelActionContributors") != null)
			return (nc.ui.uif2.actions.ActionContributors) context
					.get("toftpanelActionContributors");
		nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
		context.put("toftpanelActionContributors", bean);
		bean.setContributors(getManagedList46());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList46() {
		List list = new ArrayList();
		list.add(getActionsOfList());
		list.add(getActionsOfCard());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() {
		if (context.get("actionsOfList") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getList());
		context.put("actionsOfList", bean);
		bean.setActions(getManagedList47());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList47() {
		List list = new ArrayList();
		list.add(getAddMenu());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getRefreshAction());
		list.add(getSeparatorAction());
		list.add(getCommitActionMenu());
		list.add(getApproveActionMenu());
		list.add(getRealationGroupAction());
		list.add(getSeparatorAction());
		list.add(getLinkQryBrowseGroupAction());
		list.add(getSeparatorAction());
		list.add(getPrintMngAction());
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getCard());
		context.put("actionsOfCard", bean);
		bean.setModel(getIcBizModel());
		bean.setActions(getManagedList48());
		bean.setEditActions(getManagedList49());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList48() {
		List list = new ArrayList();
		list.add(getAddMenu());
		list.add(getEditAction());
		list.add(getDeleteAction());
		list.add(getCopyAction());
		list.add(getSeparatorAction());
		list.add(getQueryAction());
		list.add(getRefreshCardAction());
		list.add(getSeparatorAction());
		list.add(getCommitActionMenu());
		list.add(getApproveActionMenu());
		list.add(getRealationGroupAction());
		list.add(getSeparatorAction());
		list.add(getLinkQryBrowseGroupAction());
		list.add(getSeparatorAction());
		list.add(getPrintMngAction());
		return list;
	}

	private List getManagedList49() {
		List list = new ArrayList();
		list.add(getSaveAction());
		list.add(getSeparatorAction());
		list.add(getSavecommitAction());
		list.add(getSeparatorAction());
		list.add(getCancelAction());
		list.add(getSeparatorAction());
		list.add(getRowQuyQtyAction());
		list.add(getSeparatorAction());
		list.add(getLinkQryBrowseGroupAction());
		return list;
	}

	public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener() {
		if (context.get("InitDataListener") != null)
			return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener) context
					.get("InitDataListener");
		nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
		context.put("InitDataListener", bean);
		bean.setModel(getIcBizModel());
		bean.setVoClassName("nc.vo.ic.m4k.entity.WhsTransBillVO");
		bean.setAutoShowUpComponent(getCard());
		bean.setQueryAction(getQueryAction());
		bean.setProcessorMap(getManagedMap3());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap3() {
		Map map = new HashMap();
		map.put("89", getIcGenMutiPkLinkQuery());
		return map;
	}

	private nc.ui.ic.special.linkquery.ICSpecicalMutiPkLinkQuery getIcGenMutiPkLinkQuery() {
		if (context.get("icGenMutiPkLinkQuery") != null)
			return (nc.ui.ic.special.linkquery.ICSpecicalMutiPkLinkQuery) context
					.get("icGenMutiPkLinkQuery");
		nc.ui.ic.special.linkquery.ICSpecicalMutiPkLinkQuery bean = new nc.ui.ic.special.linkquery.ICSpecicalMutiPkLinkQuery();
		context.put("icGenMutiPkLinkQuery", bean);
		bean.setModel(getIcBizModel());
		bean.setAutoShowUpComponent(getList());
		bean.setVoClass("nc.vo.ic.m4k.entity.WhsTransBillVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.userdefitem.QueryParam getQueryParams1() {
		if (context.get("queryParams1") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("queryParams1");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("queryParams1", bean);
		bean.setMdfullname("ic.WhsTransBillHeaderVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.userdefitem.QueryParam getQueryParams2() {
		if (context.get("queryParams2") != null)
			return (nc.ui.uif2.userdefitem.QueryParam) context
					.get("queryParams2");
		nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
		context.put("queryParams2", bean);
		bean.setMdfullname("ic.WhsTransBillBodyVO");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams1() {
		if (context.get("userQueryParams1") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("userQueryParams1");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("userQueryParams1", bean);
		bean.setMdfullname("ic.WhsTransBillHeaderVO");
		bean.setPos(0);
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams2() {
		if (context.get("userQueryParams2") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("userQueryParams2");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("userQueryParams2", bean);
		bean.setMdfullname("ic.WhsTransBillBodyVO");
		bean.setPos(1);
		bean.setPrefix("vbdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator() {
		if (context.get("billBodySortMediator") != null)
			return (nc.ui.pubapp.uif2app.model.BillBodySortMediator) context
					.get("billBodySortMediator");
		nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(
				getIcBizModel(), getCard(), getList());
		context.put("billBodySortMediator", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.pub.action.ICPrintCountQueryAction getPrintCountQueryAction() {
		if (context.get("printCountQueryAction") != null)
			return (nc.ui.ic.pub.action.ICPrintCountQueryAction) context
					.get("printCountQueryAction");
		nc.ui.ic.pub.action.ICPrintCountQueryAction bean = new nc.ui.ic.pub.action.ICPrintCountQueryAction();
		context.put("printCountQueryAction", bean);
		bean.setBilldateFieldName("");
		bean.setModel(getIcBizModel());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.funcnode.ui.action.MenuAction getAddMenu() {
		if (context.get("addMenu") != null)
			return (nc.funcnode.ui.action.MenuAction) context.get("addMenu");
		nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
		context.put("addMenu", bean);
		bean.setCode("MaintainMenu");
		bean.setName(getI18nFB_1117748());
		bean.setActions(getManagedList50());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_1117748() {
		if (context.get("nc.ui.uif2.I18nFB#1117748") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#1117748");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#1117748", bean);
		bean.setResDir("4008001_0");
		bean.setResId("04008001-0739");
		bean.setDefaultValue("新增");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#1117748", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List getManagedList50() {
		List list = new ArrayList();
		list.add(getAddAction());
		list.add(getSeparatorAction_1ae51f6());
		list.add(getAddFrom4455Action());
		return list;
	}

	private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1ae51f6() {
		if (context.get("nc.funcnode.ui.action.SeparatorAction#1ae51f6") != null)
			return (nc.funcnode.ui.action.SeparatorAction) context
					.get("nc.funcnode.ui.action.SeparatorAction#1ae51f6");
		nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
		context.put("nc.funcnode.ui.action.SeparatorAction#1ae51f6", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ic.m4k.action.ZkAddFromSourceAction getAddFrom4455Action() {
		if (context.get("addFrom4455Action") != null)
			return (nc.ui.ic.m4k.action.ZkAddFromSourceAction) context
					.get("addFrom4455Action");
		nc.ui.ic.m4k.action.ZkAddFromSourceAction bean = new nc.ui.ic.m4k.action.ZkAddFromSourceAction();
		context.put("addFrom4455Action", bean);
		bean.setEditorModel(getIcBizEditorModel());
		bean.setSourceBillType("4455");
		bean.setSourceBillName(getI18nFB_18706f5());
		bean.setDestBillType("4K");
		bean.setPfButtonClickContext(1);
		bean.setTransferViewProcessor(getTransferViewProcessor());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private java.lang.String getI18nFB_18706f5() {
		if (context.get("nc.ui.uif2.I18nFB#18706f5") != null)
			return (java.lang.String) context.get("nc.ui.uif2.I18nFB#18706f5");
		nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
		context.put("&nc.ui.uif2.I18nFB#18706f5", bean);
		bean.setResDir("40080801");
		bean.setResId("1400808010002");
		bean.setDefaultValue("出库申请单");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		try {
			Object product = bean.getObject();
			context.put("nc.ui.uif2.I18nFB#18706f5", product);
			return (java.lang.String) product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
