package nc.ui.hkjt.zulin.bkfyft.ace.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class Hk_zulin_yuebao_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.hkjt.zulin.bkfyft.ace.serviceproxy.AceHk_zulin_yuebaoMaintainProxy getBmModelModelService(){
 if(context.get("bmModelModelService")!=null)
 return (nc.ui.hkjt.zulin.bkfyft.ace.serviceproxy.AceHk_zulin_yuebaoMaintainProxy)context.get("bmModelModelService");
  nc.ui.hkjt.zulin.bkfyft.ace.serviceproxy.AceHk_zulin_yuebaoMaintainProxy bean = new nc.ui.hkjt.zulin.bkfyft.ace.serviceproxy.AceHk_zulin_yuebaoMaintainProxy();
  context.put("bmModelModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.bd.meta.GeneralBDObjectAdapterFactory getBOAdapterFactory(){
 if(context.get("BOAdapterFactory")!=null)
 return (nc.vo.bd.meta.GeneralBDObjectAdapterFactory)context.get("BOAdapterFactory");
  nc.vo.bd.meta.GeneralBDObjectAdapterFactory bean = new nc.vo.bd.meta.GeneralBDObjectAdapterFactory();
  context.put("BOAdapterFactory",bean);
  bean.setMode("MD");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BillManageModel getBmModel(){
 if(context.get("bmModel")!=null)
 return (nc.ui.pubapp.uif2app.model.BillManageModel)context.get("bmModel");
  nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
  context.put("bmModel",bean);
  bean.setContext(getContext());
  bean.setBusinessObjectAdapterFactory(getBOAdapterFactory());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getBmModelModelDataManager(){
 if(context.get("bmModelModelDataManager")!=null)
 return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager)context.get("bmModelModelDataManager");
  nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
  context.put("bmModelModelDataManager",bean);
  bean.setModel(getBmModel());
  bean.setService(getBmModelModelService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.pubapp.uif2app.view.TemplateContainer)context.get("templateContainer");
  nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.setNodeKeies(getManagedList0());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("bt");  return list;}

public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getViewa(){
 if(context.get("viewa")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("viewa");
  nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
  context.put("viewa",bean);
  bean.setQueryAreaCreator(getDefaultQueryAction());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.ShowUpableBillListView getBillListView(){
 if(context.get("billListView")!=null)
 return (nc.ui.pubapp.uif2app.view.ShowUpableBillListView)context.get("billListView");
  nc.ui.pubapp.uif2app.view.ShowUpableBillListView bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillListView();
  context.put("billListView",bean);
  bean.setModel(getBmModel());
  bean.setNodekey("bt");
  bean.setMultiSelectionEnable(false);
  bean.setTemplateContainer(getTemplateContainer());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getViewb(){
 if(context.get("viewb")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel)context.get("viewb");
  nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
  context.put("viewb",bean);
  bean.setModel(getBmModel());
  bean.setTitleAction(getReturnAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnAction(){
 if(context.get("returnAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.UEReturnAction)context.get("returnAction");
  nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
  context.put("returnAction",bean);
  bean.setGoComponent(getBillListView());
  bean.setSaveAction(getSaveScriptAction());
  bean.setModel(getBmModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getBillForm(){
 if(context.get("billForm")!=null)
 return (nc.ui.pubapp.uif2app.view.ShowUpableBillForm)context.get("billForm");
  nc.ui.pubapp.uif2app.view.ShowUpableBillForm bean = new nc.ui.pubapp.uif2app.view.ShowUpableBillForm();
  context.put("billForm",bean);
  bean.setModel(getBmModel());
  bean.setNodekey("bt");
  bean.setBodyLineActions(getManagedList1());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  return list;}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getTBNode_1d2198d());
  bean.setActions(getManagedList3());
  bean.setEditActions(getManagedList4());
  bean.setModel(getBmModel());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1d2198d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1d2198d")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1d2198d");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#1d2198d",bean);
  bean.setTabs(getManagedList2());
  bean.setName("cardLayout");
  bean.setShowMode("CardLayout");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getHSNode_12998ea());  list.add(getVSNode_71524c());  return list;}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_12998ea(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#12998ea")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#12998ea");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#12998ea",bean);
  bean.setLeft(getCNode_124d0ba());
  bean.setRight(getCNode_1ca4f80());
  bean.setDividerLocation(215.0f);
  bean.setName("¡–±Ì");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_124d0ba(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#124d0ba")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#124d0ba");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#124d0ba",bean);
  bean.setComponent(getViewa());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1ca4f80(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1ca4f80")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1ca4f80");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1ca4f80",bean);
  bean.setComponent(getBillListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_71524c(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#71524c")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#71524c");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#71524c",bean);
  bean.setUp(getCNode_1f645ea());
  bean.setDown(getCNode_168256d());
  bean.setDividerLocation(43.0f);
  bean.setName("ø®∆¨");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1f645ea(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1f645ea")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1f645ea");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1f645ea",bean);
  bean.setComponent(getViewb());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_168256d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#168256d")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#168256d");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#168256d",bean);
  bean.setComponent(getBillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getDeleteScriptAction());  list.add(getDefaultQueryAction());  list.add(getSeparatorAction());  list.add(getDefaultRefreshAction());  list.add(getSeparatorAction());  list.add(getCommitScriptAction());  list.add(getUnCommitScriptAction());  list.add(getApproveScriptAction());  list.add(getUNApproveScriptAction());  list.add(getSeparatorAction());  list.add(getVoucherMenuAction());  list.add(getSeparatorAction());  list.add(getMetaDataBasedPrintAction());  list.add(getSeparatorAction());  list.add(getPFApproveStatusInfoAction());  return list;}

private List getManagedList4(){  List list = new ArrayList();  list.add(getSaveScriptAction());  list.add(getCancelAction());  list.add(getSeparatorAction());  list.add(getQushuAction());  return list;}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setModel(getBmModel());
  bean.setContext(getContext());
  bean.setVoClassName("nc.vo.hkjt.zulin.yuebao.YuebaoBillVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.pubapp.common.validateservice.ClosingCheck)context.get("ClosingListener");
  nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
  context.put("ClosingListener",bean);
  bean.setModel(getBmModel());
  bean.setSaveAction(getSaveScriptAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getBmModelEventMediator(){
 if(context.get("bmModelEventMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("bmModelEventMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("bmModelEventMediator",bean);
  bean.setModel(getBmModel());
  bean.setHandlerGroup(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getEventHandlerGroup_1d6ad14());  list.add(getEventHandlerGroup_10f057b());  list.add(getEventHandlerGroup_17f71a1());  return list;}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_1d6ad14(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1d6ad14")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1d6ad14");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#1d6ad14",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.OrgChangedEvent");
  bean.setHandler(getAceOrgChangeHandler_8bac34());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.hkjt.zulin.bkfyft.ace.handler.AceOrgChangeHandler getAceOrgChangeHandler_8bac34(){
 if(context.get("nc.ui.hkjt.zulin.bkfyft.ace.handler.AceOrgChangeHandler#8bac34")!=null)
 return (nc.ui.hkjt.zulin.bkfyft.ace.handler.AceOrgChangeHandler)context.get("nc.ui.hkjt.zulin.bkfyft.ace.handler.AceOrgChangeHandler#8bac34");
  nc.ui.hkjt.zulin.bkfyft.ace.handler.AceOrgChangeHandler bean = new nc.ui.hkjt.zulin.bkfyft.ace.handler.AceOrgChangeHandler();
  context.put("nc.ui.hkjt.zulin.bkfyft.ace.handler.AceOrgChangeHandler#8bac34",bean);
  bean.setBillForm(getBillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_10f057b(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#10f057b")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#10f057b");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#10f057b",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.billform.AddEvent");
  bean.setHandler(getAceAddHandler_fd138e());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.hkjt.zulin.bkfyft.ace.handler.AceAddHandler getAceAddHandler_fd138e(){
 if(context.get("nc.ui.hkjt.zulin.bkfyft.ace.handler.AceAddHandler#fd138e")!=null)
 return (nc.ui.hkjt.zulin.bkfyft.ace.handler.AceAddHandler)context.get("nc.ui.hkjt.zulin.bkfyft.ace.handler.AceAddHandler#fd138e");
  nc.ui.hkjt.zulin.bkfyft.ace.handler.AceAddHandler bean = new nc.ui.hkjt.zulin.bkfyft.ace.handler.AceAddHandler();
  context.put("nc.ui.hkjt.zulin.bkfyft.ace.handler.AceAddHandler#fd138e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.event.EventHandlerGroup getEventHandlerGroup_17f71a1(){
 if(context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#17f71a1")!=null)
 return (nc.ui.pubapp.uif2app.event.EventHandlerGroup)context.get("nc.ui.pubapp.uif2app.event.EventHandlerGroup#17f71a1");
  nc.ui.pubapp.uif2app.event.EventHandlerGroup bean = new nc.ui.pubapp.uif2app.event.EventHandlerGroup();
  context.put("nc.ui.pubapp.uif2app.event.EventHandlerGroup#17f71a1",bean);
  bean.setEvent("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent");
  bean.setHandler(getYuebaoCardHeadAfterEditEvent_130ee94());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.handler.YuebaoCardHeadAfterEditEvent getYuebaoCardHeadAfterEditEvent_130ee94(){
 if(context.get("nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.handler.YuebaoCardHeadAfterEditEvent#130ee94")!=null)
 return (nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.handler.YuebaoCardHeadAfterEditEvent)context.get("nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.handler.YuebaoCardHeadAfterEditEvent#130ee94");
  nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.handler.YuebaoCardHeadAfterEditEvent bean = new nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.handler.YuebaoCardHeadAfterEditEvent();
  context.put("nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.handler.YuebaoCardHeadAfterEditEvent#130ee94",bean);
  bean.setCardForm(getBillForm());
  bean.setModel(getBmModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader(){
 if(context.get("billLazilyLoader")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)context.get("billLazilyLoader");
  nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
  context.put("billLazilyLoader",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getBmModelLasilyLodadMediator(){
 if(context.get("bmModelLasilyLodadMediator")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("bmModelLasilyLodadMediator");
  nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
  context.put("bmModelLasilyLodadMediator",bean);
  bean.setModel(getBmModel());
  bean.setLoader(getBillLazilyLoader());
  bean.setLazilyLoadSupporter(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getCardPanelLazilyLoad_1fd0a00());  list.add(getListPanelLazilyLoad_19ba9e5());  return list;}

private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_1fd0a00(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1fd0a00")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1fd0a00");
  nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#1fd0a00",bean);
  bean.setBillform(getBillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_19ba9e5(){
 if(context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#19ba9e5")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#19ba9e5");
  nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
  context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#19ba9e5",bean);
  bean.setListView(getBillListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator(){
 if(context.get("rowNoMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.RowNoMediator)context.get("rowNoMediator");
  nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
  context.put("rowNoMediator",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator(){
 if(context.get("mouseClickShowPanelMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)context.get("mouseClickShowPanelMediator");
  nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
  context.put("mouseClickShowPanelMediator",bean);
  bean.setListView(getBillListView());
  bean.setShowUpComponent(getBillForm());
  bean.setHyperLinkColumn("vbillcode");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.bill.BillCodeMediator getBillCodeMediator(){
 if(context.get("billCodeMediator")!=null)
 return (nc.ui.pubapp.bill.BillCodeMediator)context.get("billCodeMediator");
  nc.ui.pubapp.bill.BillCodeMediator bean = new nc.ui.pubapp.bill.BillCodeMediator();
  context.put("billCodeMediator",bean);
  bean.setBillForm(getBillForm());
  bean.setBillCodeKey("vbillcode");
  bean.setBillType("HK43");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getVoucherMenuAction(){
 if(context.get("VoucherMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("VoucherMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("VoucherMenuAction",bean);
  bean.setCode("VoucherMenuAction");
  bean.setName("∆æ÷§≤Ÿ◊˜");
  bean.setActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getGenNCVoucherAction());  list.add(getDelNCVoucherAction());  list.add(getLinkNCVoucherAction());  return list;}

public nc.ui.hkjt.zulin.bkfyft.ace.action.GenNCVoucherAction getGenNCVoucherAction(){
 if(context.get("genNCVoucherAction")!=null)
 return (nc.ui.hkjt.zulin.bkfyft.ace.action.GenNCVoucherAction)context.get("genNCVoucherAction");
  nc.ui.hkjt.zulin.bkfyft.ace.action.GenNCVoucherAction bean = new nc.ui.hkjt.zulin.bkfyft.ace.action.GenNCVoucherAction();
  context.put("genNCVoucherAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.hkjt.zulin.bkfyft.ace.action.DelNCVoucherAction getDelNCVoucherAction(){
 if(context.get("delNCVoucherAction")!=null)
 return (nc.ui.hkjt.zulin.bkfyft.ace.action.DelNCVoucherAction)context.get("delNCVoucherAction");
  nc.ui.hkjt.zulin.bkfyft.ace.action.DelNCVoucherAction bean = new nc.ui.hkjt.zulin.bkfyft.ace.action.DelNCVoucherAction();
  context.put("delNCVoucherAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.hkjt.zulin.bkfyft.ace.action.LinkNCVoucherAction getLinkNCVoucherAction(){
 if(context.get("linkNCVoucherAction")!=null)
 return (nc.ui.hkjt.zulin.bkfyft.ace.action.LinkNCVoucherAction)context.get("linkNCVoucherAction");
  nc.ui.hkjt.zulin.bkfyft.ace.action.LinkNCVoucherAction bean = new nc.ui.hkjt.zulin.bkfyft.ace.action.LinkNCVoucherAction();
  context.put("linkNCVoucherAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.AddAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.AddAction)context.get("addAction");
  nc.ui.pubapp.uif2app.actions.AddAction bean = new nc.ui.pubapp.uif2app.actions.AddAction();
  context.put("addAction",bean);
  bean.setModel(getBmModel());
  bean.setInterceptor(getCompositeActionInterceptor_117afc7());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_117afc7(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#117afc7")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#117afc7");
  nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#117afc7",bean);
  bean.setInterceptors(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getShowUpComponentInterceptor_af2e87());  return list;}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_af2e87(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#af2e87")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#af2e87");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#af2e87",bean);
  bean.setShowUpComponent(getBillForm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.EditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.EditAction)context.get("editAction");
  nc.ui.pubapp.uif2app.actions.EditAction bean = new nc.ui.pubapp.uif2app.actions.EditAction();
  context.put("editAction",bean);
  bean.setModel(getBmModel());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction getDeleteScriptAction(){
 if(context.get("deleteScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction)context.get("deleteScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction();
  context.put("deleteScriptAction",bean);
  bean.setModel(getBmModel());
  bean.setBillType("HK43");
  bean.setFilledUpInFlow(true);
  bean.setActionName("DELETE");
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.QueryTemplateContainer getDefaultQueryActionQueryTemplateContainer(){
 if(context.get("defaultQueryActionQueryTemplateContainer")!=null)
 return (nc.ui.uif2.editor.QueryTemplateContainer)context.get("defaultQueryActionQueryTemplateContainer");
  nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
  context.put("defaultQueryActionQueryTemplateContainer",bean);
  bean.setNodeKey("qt");
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getDefaultQueryAction(){
 if(context.get("defaultQueryAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)context.get("defaultQueryAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
  context.put("defaultQueryAction",bean);
  bean.setModel(getBmModel());
  bean.setTemplateContainer(getDefaultQueryActionQueryTemplateContainer());
  bean.setNodeKey("qt");
  bean.setDataManager(getBmModelModelDataManager());
  bean.setExceptionHandler(getExceptionHandler());
  bean.setBtnName("≤È—Ø");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getDefaultRefreshAction(){
 if(context.get("defaultRefreshAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("defaultRefreshAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("defaultRefreshAction",bean);
  bean.setModel(getBmModel());
  bean.setDataManager(getBmModelModelDataManager());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction getCommitScriptAction(){
 if(context.get("commitScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction)context.get("commitScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction();
  context.put("commitScriptAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setBillType("HK43");
  bean.setFilledUpInFlow(true);
  bean.setActionName("SAVE");
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction getUnCommitScriptAction(){
 if(context.get("unCommitScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction)context.get("unCommitScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction();
  context.put("unCommitScriptAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setBillType("HK43");
  bean.setFilledUpInFlow(true);
  bean.setActionName("UNSAVEBILL");
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction getApproveScriptAction(){
 if(context.get("approveScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction)context.get("approveScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction();
  context.put("approveScriptAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setBillType("HK43");
  bean.setFilledUpInFlow(true);
  bean.setActionName("APPROVE");
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction getUNApproveScriptAction(){
 if(context.get("uNApproveScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction)context.get("uNApproveScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction();
  context.put("uNApproveScriptAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setBillType("HK43");
  bean.setFilledUpInFlow(true);
  bean.setActionName("UNAPPROVE");
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getMetaDataBasedPrintAction(){
 if(context.get("metaDataBasedPrintAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("metaDataBasedPrintAction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("metaDataBasedPrintAction",bean);
  bean.setModel(getBmModel());
  bean.setActioncode("Preview");
  bean.setActionname("‘§¿¿");
  bean.setPreview(true);
  bean.setNodeKey("ot");
  bean.setExceptionHandler(getExceptionHandler());
  bean.setBtnName("‘§¿¿");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getMetaDataBasedPrintActiona(){
 if(context.get("metaDataBasedPrintActiona")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("metaDataBasedPrintActiona");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("metaDataBasedPrintActiona",bean);
  bean.setModel(getBmModel());
  bean.setActioncode("Print");
  bean.setActionname("¥Ú”°");
  bean.setPreview(false);
  bean.setNodeKey("ot");
  bean.setExceptionHandler(getExceptionHandler());
  bean.setBtnName("¥Ú”°");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
  nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
  context.put("outputAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setNodeKey("ot");
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getPFApproveStatusInfoAction(){
 if(context.get("pFApproveStatusInfoAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction)context.get("pFApproveStatusInfoAction");
  nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
  context.put("pFApproveStatusInfoAction",bean);
  bean.setModel(getBmModel());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction getSaveScriptAction(){
 if(context.get("saveScriptAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction)context.get("saveScriptAction");
  nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction();
  context.put("saveScriptAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setBillType("HK43");
  bean.setFilledUpInFlow(true);
  bean.setActionName("SAVEBASE");
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.CancelAction)context.get("cancelAction");
  nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.DefaultExceptionHanler getExceptionHandler(){
 if(context.get("exceptionHandler")!=null)
 return (nc.ui.uif2.DefaultExceptionHanler)context.get("exceptionHandler");
  nc.ui.uif2.DefaultExceptionHanler bean = new nc.ui.uif2.DefaultExceptionHanler(getContainer());  context.put("exceptionHandler",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.hkjt.zulin.bkfyft.ace.action.QushuAction getQushuAction(){
 if(context.get("qushuAction")!=null)
 return (nc.ui.hkjt.zulin.bkfyft.ace.action.QushuAction)context.get("qushuAction");
  nc.ui.hkjt.zulin.bkfyft.ace.action.QushuAction bean = new nc.ui.hkjt.zulin.bkfyft.ace.action.QushuAction();
  context.put("qushuAction",bean);
  bean.setModel(getBmModel());
  bean.setEditor(getBillForm());
  bean.setListview(getBillListView());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
