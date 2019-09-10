package nc.ui.ia.mi7.maintain.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class conf_init_i7 extends
		nc.ui.ia.bill.base.maintain.conf.BaseBillCommonSet {
	private Map<String, Object> context = new HashMap();

	public nc.ui.ia.mi7.maintain.model.I7ModelService getManageModelService() {
		if (context.get("manageModelService") != null)
			return (nc.ui.ia.mi7.maintain.model.I7ModelService) context
					.get("manageModelService");
		nc.ui.ia.mi7.maintain.model.I7ModelService bean = new nc.ui.ia.mi7.maintain.model.I7ModelService();
		context.put("manageModelService", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.bill.out.maintain.view.OutListView getListView() {
		if (context.get("listView") != null)
			return (nc.ui.ia.bill.out.maintain.view.OutListView) context
					.get("listView");
		nc.ui.ia.bill.out.maintain.view.OutListView bean = new nc.ui.ia.bill.out.maintain.view.OutListView();
		context.put("listView", bean);
		bean.setModel((nc.ui.uif2.model.BillManageModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setMultiSelectionMode(0);
		bean.setTemplateContainer((nc.ui.uif2.editor.TemplateContainer) findBeanInUIF2BeanFactory("templateContainer"));
		bean.setUserdefitemListPreparator((nc.ui.pub.bill.IBillListData) findBeanInUIF2BeanFactory("userdefitemListPreparatorForIA"));
		bean.setPaginationBar((nc.ui.uif2.components.pagination.PaginationBar) findBeanInUIF2BeanFactory("paginationBar"));
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.bill.out.maintain.view.OutBillForm getBillForm() {
		if (context.get("billForm") != null)
			return (nc.ui.ia.bill.out.maintain.view.OutBillForm) context
					.get("billForm");
		nc.ui.ia.bill.out.maintain.view.OutBillForm bean = new nc.ui.ia.bill.out.maintain.view.OutBillForm();
		context.put("billForm", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setTemplateContainer((nc.ui.uif2.editor.TemplateContainer) findBeanInUIF2BeanFactory("templateContainer"));
		bean.setAutoAddLine(true);
		bean.setTemplateNotNullValidate(true);
		bean.setBlankChildrenFilter((nc.ui.pubapp.uif2app.view.value.IBlankChildrenFilter) findBeanInUIF2BeanFactory("blankChildrenFilter"));
		bean.setUserdefitemPreparator((nc.ui.pub.bill.IBillData) findBeanInUIF2BeanFactory("userdefitemPreparatorForIA"));
		bean.setBodyLineActions(getManagedList0());
		bean.initUI();
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList0() {
		List list = new ArrayList();
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyAddLineAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyInsertLineAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyDelLineAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyCopyLineAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyPasteLineAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyPasteToTailAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyseparatorAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyReSortAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("bodyseparatorAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("defaultBodyZoomAction"));
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() {
		if (context.get("actionsOfList") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfList");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getListView());
		context.put("actionsOfList", bean);
		bean.setActions(getManagedList1());
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList1() {
		List list = new ArrayList();
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("addAction"));
		list.add(getEditAction());
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("deleteAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("copyAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("separatorAction"));
		list.add(getQueryAction());
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("refreshAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("separatorAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("linkQueryGroup"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("separatorAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("printGroup"));
		return list;
	}

	public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() {
		if (context.get("actionsOfCard") != null)
			return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer) context
					.get("actionsOfCard");
		nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(
				getBillForm());
		context.put("actionsOfCard", bean);
		bean.setActions(getManagedList2());
		bean.setEditActions(getManagedList3());
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList2() {
		List list = new ArrayList();
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("addAction"));
		list.add(getEditAction());
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("deleteAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("copyAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("separatorAction"));
		list.add(getQueryAction());
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("refreshCardAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("separatorAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("linkQueryGroup"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("separatorAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("printGroup"));
		return list;
	}

	private List getManagedList3() {
		List list = new ArrayList();
		list.add(getSaveAction());
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("separatorAction"));
		list.add((javax.swing.Action) findBeanInUIF2BeanFactory("cancelAction"));
		return list;
	}

	public java.lang.String getBilltype() {
		if (context.get("billtype") != null)
			return (java.lang.String) context.get("billtype");
		java.lang.String bean = new java.lang.String("I7");
		context.put("billtype", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public java.lang.String getHeadpath() {
		if (context.get("headpath") != null)
			return (java.lang.String) context.get("headpath");
		java.lang.String bean = new java.lang.String("ia.ia_i4bill");
		context.put("headpath", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public java.lang.String getItempath() {
		if (context.get("itempath") != null)
			return (java.lang.String) context.get("itempath");
		java.lang.String bean = new java.lang.String("ia.ia_i4bill_b");
		context.put("itempath", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.mi7.maintain.action.I7EditAction getEditAction() {
		if (context.get("editAction") != null)
			return (nc.ui.ia.mi7.maintain.action.I7EditAction) context
					.get("editAction");
		nc.ui.ia.mi7.maintain.action.I7EditAction bean = new nc.ui.ia.mi7.maintain.action.I7EditAction();
		context.put("editAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setBillForm(getBillForm());
		bean.setInterceptor((nc.ui.uif2.actions.ActionInterceptor) findBeanInUIF2BeanFactory("billFormShowUpComponentInterceptor"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.mi7.maintain.action.I7SaveAction getSaveAction() {
		if (context.get("saveAction") != null)
			return (nc.ui.ia.mi7.maintain.action.I7SaveAction) context
					.get("saveAction");
		nc.ui.ia.mi7.maintain.action.I7SaveAction bean = new nc.ui.ia.mi7.maintain.action.I7SaveAction();
		context.put("saveAction", bean);
		bean.setModel((nc.ui.uif2.model.AbstractAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setEditor(getBillForm());
		bean.setService(getManageModelService());
		bean.setValidationService((nc.bs.uif2.validation.IValidationService) findBeanInUIF2BeanFactory("validator"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.mi7.maintain.editor.I7HeadBeforeEditHandler getHeadBeforeEditor() {
		if (context.get("headBeforeEditor") != null)
			return (nc.ui.ia.mi7.maintain.editor.I7HeadBeforeEditHandler) context
					.get("headBeforeEditor");
		nc.ui.ia.mi7.maintain.editor.I7HeadBeforeEditHandler bean = new nc.ui.ia.mi7.maintain.editor.I7HeadBeforeEditHandler();
		context.put("headBeforeEditor", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.mi7.maintain.editor.I7HeadAfterEditHandler getHeadAfterEditor() {
		if (context.get("headAfterEditor") != null)
			return (nc.ui.ia.mi7.maintain.editor.I7HeadAfterEditHandler) context
					.get("headAfterEditor");
		nc.ui.ia.mi7.maintain.editor.I7HeadAfterEditHandler bean = new nc.ui.ia.mi7.maintain.editor.I7HeadAfterEditHandler();
		context.put("headAfterEditor", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.mi7.maintain.editor.I7BodyBeforeEditHandler getBodyBeforeEditor() {
		if (context.get("bodyBeforeEditor") != null)
			return (nc.ui.ia.mi7.maintain.editor.I7BodyBeforeEditHandler) context
					.get("bodyBeforeEditor");
		nc.ui.ia.mi7.maintain.editor.I7BodyBeforeEditHandler bean = new nc.ui.ia.mi7.maintain.editor.I7BodyBeforeEditHandler();
		context.put("bodyBeforeEditor", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.mi7.maintain.editor.I7BodyAfterEditHandler getBodyAfterEditor() {
		if (context.get("bodyAfterEditor") != null)
			return (nc.ui.ia.mi7.maintain.editor.I7BodyAfterEditHandler) context
					.get("bodyAfterEditor");
		nc.ui.ia.mi7.maintain.editor.I7BodyAfterEditHandler bean = new nc.ui.ia.mi7.maintain.editor.I7BodyAfterEditHandler();
		context.put("bodyAfterEditor", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.mi7.maintain.editor.I7OrgEditHandler getOrgChangeEditor() {
		if (context.get("orgChangeEditor") != null)
			return (nc.ui.ia.mi7.maintain.editor.I7OrgEditHandler) context
					.get("orgChangeEditor");
		nc.ui.ia.mi7.maintain.editor.I7OrgEditHandler bean = new nc.ui.ia.mi7.maintain.editor.I7OrgEditHandler(
				getBillForm());
		context.put("orgChangeEditor", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public java.lang.String getBillVO() {
		if (context.get("billVO") != null)
			return (java.lang.String) context.get("billVO");
		java.lang.String bean = new java.lang.String(
				"nc.vo.ia.mi7.entity.I7BillVO");
		context.put("billVO", bean);
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
		bean.setModel((nc.ui.uif2.model.AbstractUIAppModel) findBeanInUIF2BeanFactory("manageAppModel"));
		bean.setDataManager((nc.ui.pubapp.uif2app.query2.model.IModelDataManager) findBeanInUIF2BeanFactory("modelDataManager"));
		bean.setQryCondDLGInitializer(getQryCondDLGInitializer());
		bean.setShowUpComponent(getListView());
		bean.setTemplateContainer((nc.ui.uif2.editor.QueryTemplateContainer) findBeanInUIF2BeanFactory("queryTemplateContainer"));
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.ia.mi7.maintain.view.I7QryCondDLGInitializer getQryCondDLGInitializer() {
		if (context.get("qryCondDLGInitializer") != null)
			return (nc.ui.ia.mi7.maintain.view.I7QryCondDLGInitializer) context
					.get("qryCondDLGInitializer");
		nc.ui.ia.mi7.maintain.view.I7QryCondDLGInitializer bean = new nc.ui.ia.mi7.maintain.view.I7QryCondDLGInitializer();
		context.put("qryCondDLGInitializer", bean);
		bean.setQueryParams(getManagedList4());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private List getManagedList4() {
		List list = new ArrayList();
		list.add(getUserdefQueryParam_255e9c());
		list.add(getUserdefQueryParam_1665b05());
		return list;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_255e9c() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#255e9c") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#255e9c");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#255e9c", bean);
		bean.setMdfullname(getHeadpath());
		bean.setPrefix("vdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1665b05() {
		if (context.get("nc.ui.uif2.editor.UserdefQueryParam#1665b05") != null)
			return (nc.ui.uif2.editor.UserdefQueryParam) context
					.get("nc.ui.uif2.editor.UserdefQueryParam#1665b05");
		nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
		context.put("nc.ui.uif2.editor.UserdefQueryParam#1665b05", bean);
		bean.setMdfullname(getItempath());
		bean.setPrefix("vbdef");
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	public nc.ui.scmpub.listener.ScmCardItemsFillableHandler getScmCardLoadhandler() {
		if (context.get("scmCardLoadhandler") != null)
			return (nc.ui.scmpub.listener.ScmCardItemsFillableHandler) context
					.get("scmCardLoadhandler");
		nc.ui.scmpub.listener.ScmCardItemsFillableHandler bean = new nc.ui.scmpub.listener.ScmCardItemsFillableHandler();
		context.put("scmCardLoadhandler", bean);
		bean.setEnabledItems(getManagedMap0());
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

	private Map getManagedMap0() {
		Map map = new HashMap();
		map.put("", getManagedList5());
		return map;
	}

	private List getManagedList5() {
		List list = new ArrayList();
		list.add("bincometaxflag");
		list.add("nincometaxrate");
		list.add("nexpaybacktax");
		list.add("cprofitcenterid");
		list.add("cprofitcentervid");
		list.add("cprojectid");
		list.add("vbdef1");
		list.add("vbdef2");
		list.add("vbdef3");
		list.add("vbdef4");
		list.add("vbdef5");
		list.add("vbdef6");
		list.add("vbdef7");
		list.add("vbdef8");
		list.add("vbdef9");
		list.add("vbdef10");
		list.add("vbdef11");
		list.add("vbdef12");
		list.add("vbdef13");
		list.add("vbdef14");
		list.add("vbdef15");
		list.add("vbdef16");
		list.add("vbdef17");
		list.add("vbdef18");
		list.add("vbdef19");
		list.add("vbdef20");
		return list;
	}

	public nc.ui.ia.mi7.maintain.model.I7PageService getPaginationQueryService() {
		if (context.get("paginationQueryService") != null)
			return (nc.ui.ia.mi7.maintain.model.I7PageService) context
					.get("paginationQueryService");
		nc.ui.ia.mi7.maintain.model.I7PageService bean = new nc.ui.ia.mi7.maintain.model.I7PageService();
		context.put("paginationQueryService", bean);
		setBeanFacotryIfBeanFacatoryAware(bean);
		invokeInitializingBean(bean);
		return bean;
	}

}
