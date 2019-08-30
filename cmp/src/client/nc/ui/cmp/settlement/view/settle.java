package nc.ui.cmp.settlement.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class settle extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
  bean.setNodeType(nc.vo.bd.pub.NODE_TYPE.ORG_NODE);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.uif2.editor.TemplateContainer)context.get("templateContainer");
  nc.ui.uif2.editor.TemplateContainer bean = new nc.ui.uif2.editor.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.setNodeKeies(getManagedList0());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add("2201");  return list;}

public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer(){
 if(context.get("queryTemplateContainer")!=null)
 return (nc.ui.uif2.editor.QueryTemplateContainer)context.get("queryTemplateContainer");
  nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
  context.put("queryTemplateContainer",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.model.SettlementVOObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.cmp.settlement.model.SettlementVOObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.cmp.settlement.model.SettlementVOObjectAdapterFactory bean = new nc.ui.cmp.settlement.model.SettlementVOObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.model.SettleModel getManageAppModel(){
 if(context.get("ManageAppModel")!=null)
 return (nc.ui.cmp.settlement.model.SettleModel)context.get("ManageAppModel");
  nc.ui.cmp.settlement.model.SettleModel bean = new nc.ui.cmp.settlement.model.SettleModel();
  context.put("ManageAppModel",bean);
  bean.setService(getManageModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.model.SettleDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.cmp.settlement.model.SettleDataManager)context.get("modelDataManager");
  nc.ui.cmp.settlement.model.SettleDataManager bean = new nc.ui.cmp.settlement.model.SettleDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setPaginationDelegate(getPaginationDelegator());
  bean.setListInfoPnl(getListInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.BillOrgPanel getOrgPanel(){
 if(context.get("orgPanel")!=null)
 return (nc.ui.pubapp.uif2app.view.BillOrgPanel)context.get("orgPanel");
  nc.ui.pubapp.uif2app.view.BillOrgPanel bean = new nc.ui.pubapp.uif2app.view.BillOrgPanel();
  context.put("orgPanel",bean);
  bean.setModel(getManageAppModel());
  bean.setOrgGetter(getCMPOrgGetter_44bbd6());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.cmp.view.CMPOrgGetter getCMPOrgGetter_44bbd6(){
 if(context.get("nc.ui.cmp.view.CMPOrgGetter#44bbd6")!=null)
 return (nc.ui.cmp.view.CMPOrgGetter)context.get("nc.ui.cmp.view.CMPOrgGetter#44bbd6");
  nc.ui.cmp.view.CMPOrgGetter bean = new nc.ui.cmp.view.CMPOrgGetter();
  context.put("nc.ui.cmp.view.CMPOrgGetter#44bbd6",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.view.SettlementList getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.cmp.settlement.view.SettlementList)context.get("listView");
  nc.ui.cmp.settlement.view.SettlementList bean = new nc.ui.cmp.settlement.view.SettlementList();
  context.put("listView",bean);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setModel(getManageAppModel());
  bean.setNodekey("2201");
  bean.setMultiSelectionEnable(true);
  bean.setMultiSelectionMode(0);
  bean.setPaginationDelegate(getPaginationDelegator());
  bean.setUserdefitemListPreparator(getUserdefitemListPreparator());
  bean.setShowTotalLine(true);
  bean.setShowTotalLineTabcodes(getManagedList1());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add("items");  return list;}

public nc.ui.cmp.settlement.linkquery.SettlementFunNodeClosingListenerImpl getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.cmp.settlement.linkquery.SettlementFunNodeClosingListenerImpl)context.get("ClosingListener");
  nc.ui.cmp.settlement.linkquery.SettlementFunNodeClosingListenerImpl bean = new nc.ui.cmp.settlement.linkquery.SettlementFunNodeClosingListenerImpl();
  context.put("ClosingListener",bean);
  bean.setSettlementlistView(getListView());
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setSaveaction(getSaveAction());
  bean.setCancelaction(getCancelAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.view.SettlementCard getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.cmp.settlement.view.SettlementCard)context.get("billFormEditor");
  nc.ui.cmp.settlement.view.SettlementCard bean = new nc.ui.cmp.settlement.view.SettlementCard();
  context.put("billFormEditor",bean);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setModel(getManageAppModel());
  bean.setNodekey("2201");
  bean.setDataModel(getModelDataManager());
  bean.setComponentValueManager(getComponentValueManager());
  bean.setClosingListener(getClosingListener());
  bean.setUserdefitemPreparator(getUserdefitemPreparator());
  bean.setDefaultRefWherePartHandler(getDefaultRefWherePartHandler());
  bean.setClientSigner(getClientSigner());
  bean.setBillOrgPanel(getOrgPanel());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemListPreparator(){
 if(context.get("userdefitemListPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemListPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemListPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getUserdefQueryParam_881622());  list.add(getUserdefQueryParam_651cb5());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_881622(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#881622")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#881622");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#881622",bean);
  bean.setMdfullname("cmp.cmp_settlement");
  bean.setPos(0);
  bean.setPrefix("def");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_651cb5(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#651cb5")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#651cb5");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#651cb5",bean);
  bean.setMdfullname("cmp.cmp_detail");
  bean.setPos(1);
  bean.setPrefix("def");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getUserdefQueryParam_3fe9bf());  list.add(getUserdefQueryParam_b21b4());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_3fe9bf(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#3fe9bf")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#3fe9bf");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#3fe9bf",bean);
  bean.setMdfullname("cmp.cmp_settlement");
  bean.setPos(0);
  bean.setPrefix("def");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_b21b4(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#b21b4")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#b21b4");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#b21b4",bean);
  bean.setMdfullname("cmp.cmp_detail");
  bean.setPos(1);
  bean.setPrefix("def");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setContext(getContext());
  bean.setParams(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getQueryParam_d1edfe());  list.add(getQueryParam_5fd08e());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_d1edfe(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#d1edfe")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#d1edfe");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#d1edfe",bean);
  bean.setMdfullname("cmp.cmp_settlement");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_5fd08e(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#5fd08e")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#5fd08e");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#5fd08e",bean);
  bean.setMdfullname("cmp.cmp_detail");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.tmpub.security.DefCommonClientSign getClientSigner(){
 if(context.get("clientSigner")!=null)
 return (nc.ui.tmpub.security.DefCommonClientSign)context.get("clientSigner");
  nc.ui.tmpub.security.DefCommonClientSign bean = new nc.ui.tmpub.security.DefCommonClientSign(getContext());  context.put("clientSigner",bean);
  bean.setConstructClassName("nc.vo.cmp.settlement.SettlementEncryptVO");
  bean.setSignAttributeNameVO(getSignAttributeNameVO());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.tmpub.filter.DefaultRefWherePartHandler getDefaultRefWherePartHandler(){
 if(context.get("defaultRefWherePartHandler")!=null)
 return (nc.ui.tmpub.filter.DefaultRefWherePartHandler)context.get("defaultRefWherePartHandler");
  nc.ui.tmpub.filter.DefaultRefWherePartHandler bean = new nc.ui.tmpub.filter.DefaultRefWherePartHandler();
  context.put("defaultRefWherePartHandler",bean);
  bean.setUiAccessor(getSettleUIAccessor_751ce0());
  bean.setFilterList(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.cmp.settlement.filter.SettleUIAccessor getSettleUIAccessor_751ce0(){
 if(context.get("nc.ui.cmp.settlement.filter.SettleUIAccessor#751ce0")!=null)
 return (nc.ui.cmp.settlement.filter.SettleUIAccessor)context.get("nc.ui.cmp.settlement.filter.SettleUIAccessor#751ce0");
  nc.ui.cmp.settlement.filter.SettleUIAccessor bean = new nc.ui.cmp.settlement.filter.SettleUIAccessor();
  context.put("nc.ui.cmp.settlement.filter.SettleUIAccessor#751ce0",bean);
  bean.setBillCardPanelEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getFbmbilltypebodyfilter());  list.add(getFbmbilltypebodyfilter2());  return list;}

private nc.ui.cmp.settlement.filter.SettleFbmbillnoRefModelFilter getFbmbilltypebodyfilter(){
 if(context.get("fbmbilltypebodyfilter")!=null)
 return (nc.ui.cmp.settlement.filter.SettleFbmbillnoRefModelFilter)context.get("fbmbilltypebodyfilter");
  nc.ui.cmp.settlement.filter.SettleFbmbillnoRefModelFilter bean = new nc.ui.cmp.settlement.filter.SettleFbmbillnoRefModelFilter();
  context.put("fbmbilltypebodyfilter",bean);
  bean.setSrcKey("pk_notetype");
  bean.setSrcPos(1);
  bean.setSrcTabCode("items");
  bean.setDestKey("notenumber");
  bean.setDestPos(1);
  bean.setDestTabCode("items");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.cmp.settlement.filter.SettleFbmbillnoRefModelFilter getFbmbilltypebodyfilter2(){
 if(context.get("fbmbilltypebodyfilter2")!=null)
 return (nc.ui.cmp.settlement.filter.SettleFbmbillnoRefModelFilter)context.get("fbmbilltypebodyfilter2");
  nc.ui.cmp.settlement.filter.SettleFbmbillnoRefModelFilter bean = new nc.ui.cmp.settlement.filter.SettleFbmbillnoRefModelFilter();
  context.put("fbmbilltypebodyfilter2",bean);
  bean.setSrcKey("pk_currtype_last");
  bean.setSrcPos(1);
  bean.setSrcTabCode("items");
  bean.setDestKey("notenumber");
  bean.setDestPos(1);
  bean.setDestTabCode("items");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator(){
 if(context.get("appEventHandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appEventHandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appEventHandlerMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setHandlerMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList6());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList7());  return map;}

private List getManagedList6(){  List list = new ArrayList();  list.add(getBodyBankAccBeforeEditHandler());  list.add(getBodyLastMoneyBeforeEditHandler());  list.add(getBodyCheckNoBeforeEditHandler());  return list;}

private nc.ui.cmp.settlement.viewhandle.cardbefore.BodyBankAccBeforeEditHandler getBodyBankAccBeforeEditHandler(){
 if(context.get("BodyBankAccBeforeEditHandler")!=null)
 return (nc.ui.cmp.settlement.viewhandle.cardbefore.BodyBankAccBeforeEditHandler)context.get("BodyBankAccBeforeEditHandler");
  nc.ui.cmp.settlement.viewhandle.cardbefore.BodyBankAccBeforeEditHandler bean = new nc.ui.cmp.settlement.viewhandle.cardbefore.BodyBankAccBeforeEditHandler();
  context.put("BodyBankAccBeforeEditHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.cmp.settlement.viewhandle.cardbefore.BodyLastMoneyBeforeEditHandler getBodyLastMoneyBeforeEditHandler(){
 if(context.get("BodyLastMoneyBeforeEditHandler")!=null)
 return (nc.ui.cmp.settlement.viewhandle.cardbefore.BodyLastMoneyBeforeEditHandler)context.get("BodyLastMoneyBeforeEditHandler");
  nc.ui.cmp.settlement.viewhandle.cardbefore.BodyLastMoneyBeforeEditHandler bean = new nc.ui.cmp.settlement.viewhandle.cardbefore.BodyLastMoneyBeforeEditHandler();
  context.put("BodyLastMoneyBeforeEditHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.cmp.settlement.viewhandle.cardbefore.BodyCheckNoBeforeEditHandler getBodyCheckNoBeforeEditHandler(){
 if(context.get("BodyCheckNoBeforeEditHandler")!=null)
 return (nc.ui.cmp.settlement.viewhandle.cardbefore.BodyCheckNoBeforeEditHandler)context.get("BodyCheckNoBeforeEditHandler");
  nc.ui.cmp.settlement.viewhandle.cardbefore.BodyCheckNoBeforeEditHandler bean = new nc.ui.cmp.settlement.viewhandle.cardbefore.BodyCheckNoBeforeEditHandler();
  context.put("BodyCheckNoBeforeEditHandler",bean);
  bean.setCardeditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getBodyCardPKpcorgAfterEditHandler());  list.add(getPKNoteTypeAfterEditHandler());  list.add(getBodyCardPk_currtype_lastAfterEditHandler());  return list;}

private nc.ui.cmp.settlement.viewhandle.cardafter.BodyCardAccountAfterEditHandler getBodyCardPKpcorgAfterEditHandler(){
 if(context.get("BodyCardPKpcorgAfterEditHandler")!=null)
 return (nc.ui.cmp.settlement.viewhandle.cardafter.BodyCardAccountAfterEditHandler)context.get("BodyCardPKpcorgAfterEditHandler");
  nc.ui.cmp.settlement.viewhandle.cardafter.BodyCardAccountAfterEditHandler bean = new nc.ui.cmp.settlement.viewhandle.cardafter.BodyCardAccountAfterEditHandler();
  context.put("BodyCardPKpcorgAfterEditHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.cmp.settlement.viewhandle.cardafter.PKNoteTypeAfterEditHandler getPKNoteTypeAfterEditHandler(){
 if(context.get("PKNoteTypeAfterEditHandler")!=null)
 return (nc.ui.cmp.settlement.viewhandle.cardafter.PKNoteTypeAfterEditHandler)context.get("PKNoteTypeAfterEditHandler");
  nc.ui.cmp.settlement.viewhandle.cardafter.PKNoteTypeAfterEditHandler bean = new nc.ui.cmp.settlement.viewhandle.cardafter.PKNoteTypeAfterEditHandler();
  context.put("PKNoteTypeAfterEditHandler",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.cmp.settlement.viewhandle.cardafter.BodyCardPk_currtype_lastAfterEditHandler getBodyCardPk_currtype_lastAfterEditHandler(){
 if(context.get("BodyCardPk_currtype_lastAfterEditHandler")!=null)
 return (nc.ui.cmp.settlement.viewhandle.cardafter.BodyCardPk_currtype_lastAfterEditHandler)context.get("BodyCardPk_currtype_lastAfterEditHandler");
  nc.ui.cmp.settlement.viewhandle.cardafter.BodyCardPk_currtype_lastAfterEditHandler bean = new nc.ui.cmp.settlement.viewhandle.cardafter.BodyCardPk_currtype_lastAfterEditHandler();
  context.put("BodyCardPk_currtype_lastAfterEditHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.bill.commom.OppUIContainer getOppui(){
 if(context.get("oppui")!=null)
 return (nc.ui.cmp.bill.commom.OppUIContainer)context.get("oppui");
  nc.ui.cmp.bill.commom.OppUIContainer bean = new nc.ui.cmp.bill.commom.OppUIContainer();
  context.put("oppui",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getOppActions(){
 if(context.get("oppActions")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("oppActions");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getOppui());  context.put("oppActions",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.common.SettleFunletMediator getSettleMediator(){
 if(context.get("settleMediator")!=null)
 return (nc.ui.cmp.settlement.common.SettleFunletMediator)context.get("settleMediator");
  nc.ui.cmp.settlement.common.SettleFunletMediator bean = new nc.ui.cmp.settlement.common.SettleFunletMediator();
  context.put("settleMediator",bean);
  bean.setCardPanel(getBillFormEditor());
  bean.setOppui(getOppui());
  bean.setModel(getManageAppModel());
  bean.setListView(getListView());
  bean.setNodeType("settleNode");
  bean.setLineActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getFirstLineAction());  list.add(getPreLineAction());  list.add(getNextLineAction());  list.add(getLastLineAction());  return list;}

public nc.ui.pubapp.uif2app.view.BillCardAllDataValueStrategy getComponentValueManager(){
 if(context.get("componentValueManager")!=null)
 return (nc.ui.pubapp.uif2app.view.BillCardAllDataValueStrategy)context.get("componentValueManager");
  nc.ui.pubapp.uif2app.view.BillCardAllDataValueStrategy bean = new nc.ui.pubapp.uif2app.view.BillCardAllDataValueStrategy();
  context.put("componentValueManager",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.model.SettleAppModelService getManageModelService(){
 if(context.get("ManageModelService")!=null)
 return (nc.ui.cmp.settlement.model.SettleAppModelService)context.get("ManageModelService");
  nc.ui.cmp.settlement.model.SettleAppModelService bean = new nc.ui.cmp.settlement.model.SettleAppModelService();
  context.put("ManageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getListActions());  list.add(getCardActions());  list.add(getOppActions());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getListActions(){
 if(context.get("listActions")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("listActions");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());  context.put("listActions",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getEditAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getBillSignActionGroup());  list.add(getBillSettleActionGroup());  list.add(getCommitToFTSActionGroup());  list.add(getMadeBillAction());  list.add(getPayActionGroupList());  list.add(getSeparatorAction());  list.add(getEuropActionGroup());  list.add(getSeparatorAction());  list.add(getBillAssistantActionGroup());  list.add(getSeparatorAction());  list.add(getLinkQueryActionGroup());  list.add(getSeparatorAction());  list.add(getBillRelatedActionGroup());  list.add(getSeparatorAction());  list.add(getPrintActionGroup());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getCardActions(){
 if(context.get("cardActions")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("cardActions");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());  context.put("cardActions",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList11());
  bean.setEditActions(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getEditAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getSettleCardRefreshAction());  list.add(getSeparatorAction());  list.add(getBillSignActionGroup());  list.add(getCardBillSettleActionGroup());  list.add(getCommitToFTSActionGroup());  list.add(getPayActionGroup());  list.add(getSeparatorAction());  list.add(getCardEuropActionGroup());  list.add(getSeparatorAction());  list.add(getBillAssistantActionGroup());  list.add(getSeparatorAction());  list.add(getLinkQueryActionGroup());  list.add(getSeparatorAction());  list.add(getBillRelatedActionGroup());  list.add(getSeparatorAction());  list.add(getPrintActionGroup());  return list;}

private List getManagedList12(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSeparatorAction());  list.add(getCancelAction());  list.add(getSeparatorAction());  list.add(getSplitLineAction());  list.add(getCombineLineAction());  list.add(getBillAssistantActionGroup());  list.add(getSeparatorAction());  list.add(getLinkQueryActionGroup());  list.add(getBillRelatedActionGroup());  return list;}

public nc.ui.cmp.settlement.actions.interceptor.ChangeBillInterceptor getChangeBillInterceptor(){
 if(context.get("ChangeBillInterceptor")!=null)
 return (nc.ui.cmp.settlement.actions.interceptor.ChangeBillInterceptor)context.get("ChangeBillInterceptor");
  nc.ui.cmp.settlement.actions.interceptor.ChangeBillInterceptor bean = new nc.ui.cmp.settlement.actions.interceptor.ChangeBillInterceptor();
  context.put("ChangeBillInterceptor",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
  bean.setNcactions(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList13(){  List list = new ArrayList();  list.add(getEditAction());  list.add(getReverseSignAction());  list.add(getSettleAction());  list.add(getCombinSettleAction());  list.add(getReverseSettleAction());  list.add(getBodySettleAction());  list.add(getBodyUnSettleAction());  list.add(getCommitToFtsAction());  list.add(getCancelCommitToFtsAction());  list.add(getMadeBillAction());  list.add(getNetpaytransferListAction());  list.add(getCombinpayAction());  list.add(getPreparenetListAction());  list.add(getRedHandleAction());  list.add(getNetpaytransferAction());  list.add(getCombinpayAction());  list.add(getPreparenetAction());  list.add(getRedHandleAction());  list.add(getSettlePayChangeAction());  return list;}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot(getTBNode_1aaeadd());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1aaeadd(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1aaeadd")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1aaeadd");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#1aaeadd",bean);
  bean.setTabs(getManagedList14());
  bean.setShowMode("CardLayout");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList14(){  List list = new ArrayList();  list.add(getHSNode_1816a36());  list.add(getVSNode_6883e3());  return list;}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1816a36(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#1816a36")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#1816a36");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#1816a36",bean);
  bean.setName(getI18nFB_bacdf4());
  bean.setDividerLocation(0.2f);
  bean.setLeft(getCNode_2753ac());
  bean.setRight(getVSNode_bd0c86());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_bacdf4(){
 if(context.get("nc.ui.uif2.I18nFB#bacdf4")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#bacdf4");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#bacdf4",bean);  bean.setResDir("3607mng_0");
  bean.setDefaultValue("列表");
  bean.setResId("03607mng-0101");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#bacdf4",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_2753ac(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#2753ac")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#2753ac");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#2753ac",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_bd0c86(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#bd0c86")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#bd0c86");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#bd0c86",bean);
  bean.setUp(getCNode_1e679b3());
  bean.setDown(getCNode_4003da());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1e679b3(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1e679b3")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1e679b3");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1e679b3",bean);
  bean.setComponent(getListInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_4003da(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#4003da")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#4003da");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#4003da",bean);
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_6883e3(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#6883e3")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#6883e3");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#6883e3",bean);
  bean.setName(getI18nFB_1641ec5());
  bean.setUp(getCNode_2056cc());
  bean.setDown(getTBNode_1324945());
  bean.setDividerLocation(30f);
  bean.setShowMode("NoDivider");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1641ec5(){
 if(context.get("nc.ui.uif2.I18nFB#1641ec5")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1641ec5");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1641ec5",bean);  bean.setResDir("3607mng_0");
  bean.setDefaultValue("卡片");
  bean.setResId("03607mng-0399");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1641ec5",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_2056cc(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#2056cc")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#2056cc");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#2056cc",bean);
  bean.setComponent(getCardInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1324945(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#1324945")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#1324945");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#1324945",bean);
  bean.setName(getI18nFB_122a182());
  bean.setTabs(getManagedList15());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_122a182(){
 if(context.get("nc.ui.uif2.I18nFB#122a182")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#122a182");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#122a182",bean);  bean.setResDir("3607mng_0");
  bean.setDefaultValue("卡片");
  bean.setResId("03607mng-0399");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#122a182",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList15(){  List list = new ArrayList();  list.add(getCNode_1bf2d91());  list.add(getCNode_e4e3c());  return list;}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1bf2d91(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1bf2d91")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1bf2d91");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1bf2d91",bean);
  bean.setComponent(getBillFormEditor());
  bean.setName(getI18nFB_9997d5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_9997d5(){
 if(context.get("nc.ui.uif2.I18nFB#9997d5")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#9997d5");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#9997d5",bean);  bean.setResDir("3607mng_0");
  bean.setDefaultValue("结算信息");
  bean.setResId("03607mng-0400");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#9997d5",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_e4e3c(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#e4e3c")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#e4e3c");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#e4e3c",bean);
  bean.setComponent(getOppui());
  bean.setName(getI18nFB_48909d());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_48909d(){
 if(context.get("nc.ui.uif2.I18nFB#48909d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#48909d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#48909d",bean);  bean.setResDir("3607mng_0");
  bean.setDefaultValue("业务信息");
  bean.setResId("03607mng-0102");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#48909d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea(){
 if(context.get("queryArea")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("queryArea");
  nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
  context.put("queryArea",bean);
  bean.setQueryAreaCreator(getQueryAction());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getListInfoPnl(){
 if(context.get("listInfoPnl")!=null)
 return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("listInfoPnl");
  nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
  context.put("listInfoPnl",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getCardInfoPnl(){
 if(context.get("cardInfoPnl")!=null)
 return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("cardInfoPnl");
  nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
  context.put("cardInfoPnl",bean);
  bean.setActions(getManagedList16());
  bean.setTitleAction(getReturnaction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList16(){  List list = new ArrayList();  list.add(getFirstLineAction());  list.add(getPreLineAction());  list.add(getNextLineAction());  list.add(getLastLineAction());  return list;}

private nc.ui.uif2.actions.ShowMeUpAction getReturnaction(){
 if(context.get("returnaction")!=null)
 return (nc.ui.uif2.actions.ShowMeUpAction)context.get("returnaction");
  nc.ui.uif2.actions.ShowMeUpAction bean = new nc.ui.uif2.actions.ShowMeUpAction();
  context.put("returnaction",bean);
  bean.setGoComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.FirstLineAction getFirstLineAction(){
 if(context.get("firstLineAction")!=null)
 return (nc.ui.uif2.actions.FirstLineAction)context.get("firstLineAction");
  nc.ui.uif2.actions.FirstLineAction bean = new nc.ui.uif2.actions.FirstLineAction();
  context.put("firstLineAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.PreLineAction getPreLineAction(){
 if(context.get("preLineAction")!=null)
 return (nc.ui.uif2.actions.PreLineAction)context.get("preLineAction");
  nc.ui.uif2.actions.PreLineAction bean = new nc.ui.uif2.actions.PreLineAction();
  context.put("preLineAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.NextLineAction getNextLineAction(){
 if(context.get("nextLineAction")!=null)
 return (nc.ui.uif2.actions.NextLineAction)context.get("nextLineAction");
  nc.ui.uif2.actions.NextLineAction bean = new nc.ui.uif2.actions.NextLineAction();
  context.put("nextLineAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.LastLineAction getLastLineAction(){
 if(context.get("lastLineAction")!=null)
 return (nc.ui.uif2.actions.LastLineAction)context.get("lastLineAction");
  nc.ui.uif2.actions.LastLineAction bean = new nc.ui.uif2.actions.LastLineAction();
  context.put("lastLineAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator(){
 if(context.get("mouseClickShowPanelMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)context.get("mouseClickShowPanelMediator");
  nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
  context.put("mouseClickShowPanelMediator",bean);
  bean.setListView(getListView());
  bean.setShowUpComponent(getBillFormEditor());
  bean.setHyperLinkColumn("billcode");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSaveAction)context.get("saveAction");
  nc.ui.cmp.settlement.actions.SettleSaveAction bean = new nc.ui.cmp.settlement.actions.SettleSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.CancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.uif2.actions.CancelAction)context.get("cancelAction");
  nc.ui.uif2.actions.CancelAction bean = new nc.ui.uif2.actions.CancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.AddLineAction getAddline(){
 if(context.get("addline")!=null)
 return (nc.ui.uif2.actions.AddLineAction)context.get("addline");
  nc.ui.uif2.actions.AddLineAction bean = new nc.ui.uif2.actions.AddLineAction();
  context.put("addline",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.CommitToFtsAction getCommitToFtsAction(){
 if(context.get("CommitToFtsAction")!=null)
 return (nc.ui.cmp.settlement.actions.CommitToFtsAction)context.get("CommitToFtsAction");
  nc.ui.cmp.settlement.actions.CommitToFtsAction bean = new nc.ui.cmp.settlement.actions.CommitToFtsAction();
  context.put("CommitToFtsAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.CancelCommitToFtsAction getCancelCommitToFtsAction(){
 if(context.get("CancelCommitToFtsAction")!=null)
 return (nc.ui.cmp.settlement.actions.CancelCommitToFtsAction)context.get("CancelCommitToFtsAction");
  nc.ui.cmp.settlement.actions.CancelCommitToFtsAction bean = new nc.ui.cmp.settlement.actions.CancelCommitToFtsAction();
  context.put("CancelCommitToFtsAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettlementSettleUIAction getSettleAction(){
 if(context.get("settleAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettlementSettleUIAction)context.get("settleAction");
  nc.ui.cmp.settlement.actions.SettlementSettleUIAction bean = new nc.ui.cmp.settlement.actions.SettlementSettleUIAction();
  context.put("settleAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettlePayChangeAction getSettlePayChangeAction(){
 if(context.get("settlePayChangeAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettlePayChangeAction)context.get("settlePayChangeAction");
  nc.ui.cmp.settlement.actions.SettlePayChangeAction bean = new nc.ui.cmp.settlement.actions.SettlePayChangeAction();
  context.put("settlePayChangeAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleReverseSignUIAction getReverseSignAction(){
 if(context.get("reverseSignAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleReverseSignUIAction)context.get("reverseSignAction");
  nc.ui.cmp.settlement.actions.SettleReverseSignUIAction bean = new nc.ui.cmp.settlement.actions.SettleReverseSignUIAction();
  context.put("reverseSignAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleReverseSettleUIAction getReverseSettleAction(){
 if(context.get("reverseSettleAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleReverseSettleUIAction)context.get("reverseSettleAction");
  nc.ui.cmp.settlement.actions.SettleReverseSettleUIAction bean = new nc.ui.cmp.settlement.actions.SettleReverseSettleUIAction();
  context.put("reverseSettleAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.BodySettleAction getBodySettleAction(){
 if(context.get("BodySettleAction")!=null)
 return (nc.ui.cmp.settlement.actions.BodySettleAction)context.get("BodySettleAction");
  nc.ui.cmp.settlement.actions.BodySettleAction bean = new nc.ui.cmp.settlement.actions.BodySettleAction();
  context.put("BodySettleAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.BodyUnSettleAction getBodyUnSettleAction(){
 if(context.get("BodyUnSettleAction")!=null)
 return (nc.ui.cmp.settlement.actions.BodyUnSettleAction)context.get("BodyUnSettleAction");
  nc.ui.cmp.settlement.actions.BodyUnSettleAction bean = new nc.ui.cmp.settlement.actions.BodyUnSettleAction();
  context.put("BodyUnSettleAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSignAction getSignAction(){
 if(context.get("signAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSignAction)context.get("signAction");
  nc.ui.cmp.settlement.actions.SettleSignAction bean = new nc.ui.cmp.settlement.actions.SettleSignAction();
  context.put("signAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
  bean.setInterceptor(getChangeBillInterceptor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.bill.actions.CmpBaseQuerySchemeAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.cmp.bill.actions.CmpBaseQuerySchemeAction)context.get("queryAction");
  nc.ui.cmp.bill.actions.CmpBaseQuerySchemeAction bean = new nc.ui.cmp.bill.actions.CmpBaseQuerySchemeAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getQryCondDLGInitializer());
  bean.setShowUpComponent(getListView());
  bean.setTemplateContainer(getQueryTemplateContainer());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.view.SettleQryCondDLGInitializer getQryCondDLGInitializer(){
 if(context.get("qryCondDLGInitializer")!=null)
 return (nc.ui.cmp.settlement.view.SettleQryCondDLGInitializer)context.get("qryCondDLGInitializer");
  nc.ui.cmp.settlement.view.SettleQryCondDLGInitializer bean = new nc.ui.cmp.settlement.view.SettleQryCondDLGInitializer();
  context.put("qryCondDLGInitializer",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.bill.actions.CmpBillQueryDelege getQueryDelegator(){
 if(context.get("queryDelegator")!=null)
 return (nc.ui.cmp.bill.actions.CmpBillQueryDelege)context.get("queryDelegator");
  nc.ui.cmp.bill.actions.CmpBillQueryDelege bean = new nc.ui.cmp.bill.actions.CmpBillQueryDelege();
  context.put("queryDelegator",bean);
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setNodeKey("2201");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleRefreshAction)context.get("refreshAction");
  nc.ui.cmp.settlement.actions.SettleRefreshAction bean = new nc.ui.cmp.settlement.actions.SettleRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleCardRefreshAction getSettleCardRefreshAction(){
 if(context.get("settleCardRefreshAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleCardRefreshAction)context.get("settleCardRefreshAction");
  nc.ui.cmp.settlement.actions.SettleCardRefreshAction bean = new nc.ui.cmp.settlement.actions.SettleCardRefreshAction();
  context.put("settleCardRefreshAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator getPaginationDelegator(){
 if(context.get("paginationDelegator")!=null)
 return (nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator)context.get("paginationDelegator");
  nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator bean = new nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator(getManageAppModel());  context.put("paginationDelegator",bean);
  bean.setPaginationQuery(getPaginationQueryService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.common.SettleInitQuery getPaginationQueryService(){
 if(context.get("paginationQueryService")!=null)
 return (nc.ui.cmp.settlement.common.SettleInitQuery)context.get("paginationQueryService");
  nc.ui.cmp.settlement.common.SettleInitQuery bean = new nc.ui.cmp.settlement.common.SettleInitQuery();
  context.put("paginationQueryService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getBillRelatedActionGroup(){
 if(context.get("billRelatedActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("billRelatedActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("billRelatedActionGroup",bean);
  bean.setCode("billAssistant");
  bean.setName(getI18nFB_218649());
  bean.setActions(getManagedList17());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_218649(){
 if(context.get("nc.ui.uif2.I18nFB#218649")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#218649");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#218649",bean);  bean.setResDir("3607mng_0");
  bean.setDefaultValue("关联功能");
  bean.setResId("03607mng-0098");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#218649",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList17(){  List list = new ArrayList();  list.add(getNoteRegisterAction());  return list;}

public nc.funcnode.ui.action.GroupAction getBillSignActionGroup(){
 if(context.get("billSignActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("billSignActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("billSignActionGroup",bean);
  bean.setCode("billSign");
  bean.setName(getI18nFB_f7590e());
  bean.setActions(getManagedList18());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_f7590e(){
 if(context.get("nc.ui.uif2.I18nFB#f7590e")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#f7590e");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#f7590e",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("签字");
  bean.setResId("03607set-0069");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#f7590e",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList18(){  List list = new ArrayList();  list.add(getSignAction());  list.add(getReverseSignAction());  return list;}

public nc.funcnode.ui.action.GroupAction getBillSettleActionGroup(){
 if(context.get("billSettleActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("billSettleActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("billSettleActionGroup",bean);
  bean.setCode("billSettle");
  bean.setName(getI18nFB_f7c66d());
  bean.setActions(getManagedList19());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_f7c66d(){
 if(context.get("nc.ui.uif2.I18nFB#f7c66d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#f7c66d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#f7c66d",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("结算");
  bean.setResId("03607set-0070");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#f7c66d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList19(){  List list = new ArrayList();  list.add(getSettleAction());  list.add(getCombinSettleAction());  list.add(getReverseSettleAction());  return list;}

public nc.funcnode.ui.action.GroupAction getCardBillSettleActionGroup(){
 if(context.get("cardBillSettleActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("cardBillSettleActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("cardBillSettleActionGroup",bean);
  bean.setCode("cardBillSettle");
  bean.setName(getI18nFB_1044fd7());
  bean.setActions(getManagedList20());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1044fd7(){
 if(context.get("nc.ui.uif2.I18nFB#1044fd7")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1044fd7");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1044fd7",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("结算");
  bean.setResId("03607set-0070");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1044fd7",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList20(){  List list = new ArrayList();  list.add(getSettleAction());  list.add(getReverseSettleAction());  return list;}

public nc.funcnode.ui.action.GroupAction getCommitToFTSActionGroup(){
 if(context.get("commitToFTSActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("commitToFTSActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("commitToFTSActionGroup",bean);
  bean.setCode("billSettle");
  bean.setName(getI18nFB_15c251());
  bean.setActions(getManagedList21());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_15c251(){
 if(context.get("nc.ui.uif2.I18nFB#15c251")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#15c251");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#15c251",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("提交资金组织");
  bean.setResId("03607set-0071");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#15c251",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList21(){  List list = new ArrayList();  list.add(getCommitToFtsAction());  list.add(getCancelCommitToFtsAction());  return list;}

public nc.funcnode.ui.action.MenuAction getBillAssistantActionGroup(){
 if(context.get("billAssistantActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("billAssistantActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("billAssistantActionGroup",bean);
  bean.setCode("billAssistant");
  bean.setName(getI18nFB_886866());
  bean.setActions(getManagedList22());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_886866(){
 if(context.get("nc.ui.uif2.I18nFB#886866")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#886866");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#886866",bean);  bean.setResDir("3607mng_0");
  bean.setDefaultValue("辅助功能");
  bean.setResId("03607mng-0401");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#886866",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList22(){  List list = new ArrayList();  list.add(getDocManageAction());  list.add(getSeparatorAction());  list.add(getAccountSetAction());  list.add(getSendMailAction());  return list;}

public nc.funcnode.ui.action.MenuAction getPayActionGroup(){
 if(context.get("payActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("payActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("payActionGroup",bean);
  bean.setCode("payActionGroup");
  bean.setName(getI18nFB_17e1761());
  bean.setActions(getManagedList23());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_17e1761(){
 if(context.get("nc.ui.uif2.I18nFB#17e1761")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#17e1761");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#17e1761",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("支付");
  bean.setResId("03607set-0072");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#17e1761",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList23(){  List list = new ArrayList();  list.add(getNetpaytransferAction());  list.add(getCombinpayAction());  list.add(getPreparenetAction());  list.add(getRedHandleAction());  list.add(getSettlePayChangeAction());  return list;}

public nc.funcnode.ui.action.MenuAction getEuropActionGroup(){
 if(context.get("europActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("europActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("europActionGroup",bean);
  bean.setCode("europActionGroup");
  bean.setName(getI18nFB_130f9a2());
  bean.setActions(getManagedList24());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_130f9a2(){
 if(context.get("nc.ui.uif2.I18nFB#130f9a2")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#130f9a2");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#130f9a2",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("欧盟支付");
  bean.setResId("03607set-0917");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#130f9a2",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList24(){  List list = new ArrayList();  list.add(getBodySettleAction());  list.add(getBodyUnSettleAction());  list.add(getExportEuropeAction());  return list;}

public nc.funcnode.ui.action.MenuAction getCardEuropActionGroup(){
 if(context.get("cardEuropActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("cardEuropActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("cardEuropActionGroup",bean);
  bean.setCode("cardEuropActionGroup");
  bean.setName(getI18nFB_553bea());
  bean.setActions(getManagedList25());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_553bea(){
 if(context.get("nc.ui.uif2.I18nFB#553bea")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#553bea");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#553bea",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("欧盟支付");
  bean.setResId("03607set-0917");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#553bea",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList25(){  List list = new ArrayList();  list.add(getBodySettleAction());  list.add(getBodyUnSettleAction());  list.add(getExportEuropeAction());  return list;}

public nc.funcnode.ui.action.MenuAction getPayActionGroupList(){
 if(context.get("payActionGroupList")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("payActionGroupList");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("payActionGroupList",bean);
  bean.setCode("payActionGroup");
  bean.setName(getI18nFB_1d0b529());
  bean.setActions(getManagedList26());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1d0b529(){
 if(context.get("nc.ui.uif2.I18nFB#1d0b529")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1d0b529");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1d0b529",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("支付");
  bean.setResId("03607set-0072");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1d0b529",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList26(){  List list = new ArrayList();  list.add(getNetpaytransferListAction());  list.add(getCombinpayAction());  list.add(getPreparenetListAction());  list.add(getRedHandleAction());  return list;}

public nc.funcnode.ui.action.MenuAction getLinkQueryActionGroup(){
 if(context.get("linkQueryActionGroup")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("linkQueryActionGroup");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("linkQueryActionGroup",bean);
  bean.setCode("linkQueryActionGroup");
  bean.setName(getI18nFB_125b152());
  bean.setActions(getManagedList27());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_125b152(){
 if(context.get("nc.ui.uif2.I18nFB#125b152")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#125b152");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#125b152",bean);  bean.setResDir("3607set_0");
  bean.setDefaultValue("联查");
  bean.setResId("03607set-0073");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#125b152",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList27(){  List list = new ArrayList();  list.add(getLinkQueryAction());  list.add(getSearchAuditConditionAction());  list.add(getSearchVoucherAction());  list.add(getSearchNoteAction());  list.add(getSearchBalanceAction());  list.add(getSearchNetBankAction());  list.add(getSearchPayAffirmAction());  return list;}

public nc.ui.cmp.settlement.actions.SettleNoteRegisterAction getNoteRegisterAction(){
 if(context.get("noteRegisterAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleNoteRegisterAction)context.get("noteRegisterAction");
  nc.ui.cmp.settlement.actions.SettleNoteRegisterAction bean = new nc.ui.cmp.settlement.actions.SettleNoteRegisterAction();
  context.put("noteRegisterAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleNetPayTransferAction getNetpaytransferAction(){
 if(context.get("netpaytransferAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleNetPayTransferAction)context.get("netpaytransferAction");
  nc.ui.cmp.settlement.actions.SettleNetPayTransferAction bean = new nc.ui.cmp.settlement.actions.SettleNetPayTransferAction();
  context.put("netpaytransferAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
  bean.setLoginContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.ExportEuropeAction getExportEuropeAction(){
 if(context.get("exportEuropeAction")!=null)
 return (nc.ui.cmp.settlement.actions.ExportEuropeAction)context.get("exportEuropeAction");
  nc.ui.cmp.settlement.actions.ExportEuropeAction bean = new nc.ui.cmp.settlement.actions.ExportEuropeAction();
  context.put("exportEuropeAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
  bean.setLoginContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleListNetPayTransferAction getNetpaytransferListAction(){
 if(context.get("netpaytransferListAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleListNetPayTransferAction)context.get("netpaytransferListAction");
  nc.ui.cmp.settlement.actions.SettleListNetPayTransferAction bean = new nc.ui.cmp.settlement.actions.SettleListNetPayTransferAction();
  context.put("netpaytransferListAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
  bean.setLoginContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleCombinPayAction getCombinpayAction(){
 if(context.get("combinpayAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleCombinPayAction)context.get("combinpayAction");
  nc.ui.cmp.settlement.actions.SettleCombinPayAction bean = new nc.ui.cmp.settlement.actions.SettleCombinPayAction();
  context.put("combinpayAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettlePreparenetBankAction getPreparenetAction(){
 if(context.get("preparenetAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettlePreparenetBankAction)context.get("preparenetAction");
  nc.ui.cmp.settlement.actions.SettlePreparenetBankAction bean = new nc.ui.cmp.settlement.actions.SettlePreparenetBankAction();
  context.put("preparenetAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettlePreparenetBankListAction getPreparenetListAction(){
 if(context.get("preparenetListAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettlePreparenetBankListAction)context.get("preparenetListAction");
  nc.ui.cmp.settlement.actions.SettlePreparenetBankListAction bean = new nc.ui.cmp.settlement.actions.SettlePreparenetBankListAction();
  context.put("preparenetListAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleRedHandleAction getRedHandleAction(){
 if(context.get("redHandleAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleRedHandleAction)context.get("redHandleAction");
  nc.ui.cmp.settlement.actions.SettleRedHandleAction bean = new nc.ui.cmp.settlement.actions.SettleRedHandleAction();
  context.put("redHandleAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSearchAuditConditionAction getSearchAuditConditionAction(){
 if(context.get("searchAuditConditionAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSearchAuditConditionAction)context.get("searchAuditConditionAction");
  nc.ui.cmp.settlement.actions.SettleSearchAuditConditionAction bean = new nc.ui.cmp.settlement.actions.SettleSearchAuditConditionAction();
  context.put("searchAuditConditionAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSearchVoucherAction getSearchVoucherAction(){
 if(context.get("searchVoucherAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSearchVoucherAction)context.get("searchVoucherAction");
  nc.ui.cmp.settlement.actions.SettleSearchVoucherAction bean = new nc.ui.cmp.settlement.actions.SettleSearchVoucherAction();
  context.put("searchVoucherAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
  bean.setLoginContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSearchNoteAction getSearchNoteAction(){
 if(context.get("searchNoteAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSearchNoteAction)context.get("searchNoteAction");
  nc.ui.cmp.settlement.actions.SettleSearchNoteAction bean = new nc.ui.cmp.settlement.actions.SettleSearchNoteAction();
  context.put("searchNoteAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setLoginContext(getContext());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSearchBalanceAction getSearchBalanceAction(){
 if(context.get("searchBalanceAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSearchBalanceAction)context.get("searchBalanceAction");
  nc.ui.cmp.settlement.actions.SettleSearchBalanceAction bean = new nc.ui.cmp.settlement.actions.SettleSearchBalanceAction();
  context.put("searchBalanceAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSearchNetBankAction getSearchNetBankAction(){
 if(context.get("searchNetBankAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSearchNetBankAction)context.get("searchNetBankAction");
  nc.ui.cmp.settlement.actions.SettleSearchNetBankAction bean = new nc.ui.cmp.settlement.actions.SettleSearchNetBankAction();
  context.put("searchNetBankAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSearchPayAffirmAction getSearchPayAffirmAction(){
 if(context.get("searchPayAffirmAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSearchPayAffirmAction)context.get("searchPayAffirmAction");
  nc.ui.cmp.settlement.actions.SettleSearchPayAffirmAction bean = new nc.ui.cmp.settlement.actions.SettleSearchPayAffirmAction();
  context.put("searchPayAffirmAction",bean);
  bean.setModel(getManageAppModel());
  bean.setListView(getListView());
  bean.setEdit(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleSplitLineAction getSplitLineAction(){
 if(context.get("splitLineAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleSplitLineAction)context.get("splitLineAction");
  nc.ui.cmp.settlement.actions.SettleSplitLineAction bean = new nc.ui.cmp.settlement.actions.SettleSplitLineAction();
  context.put("splitLineAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleCombineLineAction getCombineLineAction(){
 if(context.get("combineLineAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleCombineLineAction)context.get("combineLineAction");
  nc.ui.cmp.settlement.actions.SettleCombineLineAction bean = new nc.ui.cmp.settlement.actions.SettleCombineLineAction();
  context.put("combineLineAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleEditAction)context.get("editAction");
  nc.ui.cmp.settlement.actions.SettleEditAction bean = new nc.ui.cmp.settlement.actions.SettleEditAction();
  context.put("editAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleMakeBillAction getMakeBillAction(){
 if(context.get("makeBillAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleMakeBillAction)context.get("makeBillAction");
  nc.ui.cmp.settlement.actions.SettleMakeBillAction bean = new nc.ui.cmp.settlement.actions.SettleMakeBillAction();
  context.put("makeBillAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.CombinSettleAction getCombinSettleAction(){
 if(context.get("combinSettleAction")!=null)
 return (nc.ui.cmp.settlement.actions.CombinSettleAction)context.get("combinSettleAction");
  nc.ui.cmp.settlement.actions.CombinSettleAction bean = new nc.ui.cmp.settlement.actions.CombinSettleAction();
  context.put("combinSettleAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleDocManageAction getDocManageAction(){
 if(context.get("docManageAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleDocManageAction)context.get("docManageAction");
  nc.ui.cmp.settlement.actions.SettleDocManageAction bean = new nc.ui.cmp.settlement.actions.SettleDocManageAction();
  context.put("docManageAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleLinkQueryAction getLinkQueryAction(){
 if(context.get("linkQueryAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleLinkQueryAction)context.get("linkQueryAction");
  nc.ui.cmp.settlement.actions.SettleLinkQueryAction bean = new nc.ui.cmp.settlement.actions.SettleLinkQueryAction();
  context.put("linkQueryAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.HkAccountSetAction getAccountSetAction(){
 if(context.get("accountSetAction")!=null)
 return (nc.ui.cmp.settlement.actions.HkAccountSetAction)context.get("accountSetAction");
  nc.ui.cmp.settlement.actions.HkAccountSetAction bean = new nc.ui.cmp.settlement.actions.HkAccountSetAction();
  context.put("accountSetAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.HkSendAction getSendMailAction(){
 if(context.get("sendMailAction")!=null)
 return (nc.ui.cmp.settlement.actions.HkSendAction)context.get("sendMailAction");
  nc.ui.cmp.settlement.actions.HkSendAction bean = new nc.ui.cmp.settlement.actions.HkSendAction();
  context.put("sendMailAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillForm(getBillFormEditor());
  bean.setDataManager(getModelDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.listener.SettlementRelationQueryDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.cmp.listener.SettlementRelationQueryDataListener)context.get("InitDataListener");
  nc.ui.cmp.listener.SettlementRelationQueryDataListener bean = new nc.ui.cmp.listener.SettlementRelationQueryDataListener();
  context.put("InitDataListener",bean);
  bean.setBillFormEditor(getBillFormEditor());
  bean.setListView(getListView());
  bean.setQueryAction(getQueryAction());
  bean.setDataManager(getModelDataManager());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.actions.SettleMakeBillAction getMadeBillAction(){
 if(context.get("madeBillAction")!=null)
 return (nc.ui.cmp.settlement.actions.SettleMakeBillAction)context.get("madeBillAction");
  nc.ui.cmp.settlement.actions.SettleMakeBillAction bean = new nc.ui.cmp.settlement.actions.SettleMakeBillAction();
  context.put("madeBillAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEdit(getBillFormEditor());
  bean.setListView(getListView());
  bean.setLoginContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPrintActionGroup(){
 if(context.get("printActionGroup")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printActionGroup");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printActionGroup",bean);
  bean.setActions(getManagedList28());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList28(){  List list = new ArrayList();  list.add(getPrintaction());  list.add(getPrintpreviewaction());  list.add(getOutputAction());  return list;}

private nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintaction(){
 if(context.get("printaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("printaction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("printaction",bean);
  bean.setPreview(false);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPrintpreviewaction(){
 if(context.get("printpreviewaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("printpreviewaction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("printpreviewaction",bean);
  bean.setPreview(true);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
  nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
  context.put("outputAction",bean);
  bean.setModel(getManageAppModel());
  bean.setParent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.tmpub.digit.print.DefaultPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.tmpub.digit.print.DefaultPrintProcessor)context.get("printProcessor");
  nc.ui.tmpub.digit.print.DefaultPrintProcessor bean = new nc.ui.tmpub.digit.print.DefaultPrintProcessor();
  context.put("printProcessor",bean);
  bean.setSrcDestItemCollection(getListSrcDestCollection());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.tmpub.digit.vo.SrcDestItemCollection getListSrcDestCollection(){
 if(context.get("listSrcDestCollection")!=null)
 return (nc.ui.tmpub.digit.vo.SrcDestItemCollection)context.get("listSrcDestCollection");
  nc.ui.tmpub.digit.vo.SrcDestItemCollection bean = new nc.ui.tmpub.digit.vo.SrcDestItemCollection();
  context.put("listSrcDestCollection",bean);
  bean.setSrcDestOrigMap(getManagedMap1());
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put(getManagedList29(),getManagedList34());  map.put(getManagedList39(),getManagedList47());  map.put(getManagedList55(),getManagedList63());  map.put(getManagedList77(),getManagedList85());  return map;}

private List getManagedList29(){  List list = new ArrayList();  list.add(getManagedList30());  list.add(getManagedList31());  list.add(getManagedList32());  list.add(getManagedList33());  return list;}

private List getManagedList30(){  List list = new ArrayList();  list.add("CURR");  list.add("pk_currtype");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList31(){  List list = new ArrayList();  list.add("ORG");  list.add("pk_org");  list.add("HEAD");  return list;}

private List getManagedList32(){  List list = new ArrayList();  list.add("GROUP");  list.add("pk_group");  list.add("HEAD");  return list;}

private List getManagedList33(){  List list = new ArrayList();  list.add("GLOBAL");  list.add("pk_org");  list.add("HEAD");  return list;}

private List getManagedList34(){  List list = new ArrayList();  list.add(getManagedList35());  list.add(getManagedList36());  list.add(getManagedList37());  list.add(getManagedList38());  return list;}

private List getManagedList35(){  List list = new ArrayList();  list.add("ORG_MONEY");  list.add("primal");  list.add("HEAD");  return list;}

private List getManagedList36(){  List list = new ArrayList();  list.add("ORG_MONEY");  list.add("orglocal");  list.add("HEAD");  return list;}

private List getManagedList37(){  List list = new ArrayList();  list.add("GROUP_MONEY");  list.add("grouplocal");  list.add("HEAD");  return list;}

private List getManagedList38(){  List list = new ArrayList();  list.add("GLOBAL_MONEY");  list.add("globaloacl");  list.add("HEAD");  return list;}

private List getManagedList39(){  List list = new ArrayList();  list.add(getManagedList40());  list.add(getManagedList41());  list.add(getManagedList42());  list.add(getManagedList43());  list.add(getManagedList44());  list.add(getManagedList45());  list.add(getManagedList46());  return list;}

private List getManagedList40(){  List list = new ArrayList();  list.add("ORG");  list.add("pk_org");  list.add("HEAD");  return list;}

private List getManagedList41(){  List list = new ArrayList();  list.add("GROUP");  list.add("pk_group");  list.add("HEAD");  return list;}

private List getManagedList42(){  List list = new ArrayList();  list.add("GLOBAL");  list.add("pk_org");  list.add("HEAD");  return list;}

private List getManagedList43(){  List list = new ArrayList();  list.add("CURR");  list.add("pk_currtype");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList44(){  List list = new ArrayList();  list.add("EXCHANGEDATE");  list.add("billdate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList45(){  List list = new ArrayList();  list.add("MONEY");  list.add("pay");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList46(){  List list = new ArrayList();  list.add("ORG_RATE");  list.add("localrate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList47(){  List list = new ArrayList();  list.add(getManagedList48());  list.add(getManagedList49());  list.add(getManagedList50());  list.add(getManagedList51());  list.add(getManagedList52());  list.add(getManagedList53());  list.add(getManagedList54());  return list;}

private List getManagedList48(){  List list = new ArrayList();  list.add("ORG_RATE");  list.add("localrate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList49(){  List list = new ArrayList();  list.add("ORG_MONEY");  list.add("paylocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList50(){  List list = new ArrayList();  list.add("GROUP_RATE");  list.add("grouprate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList51(){  List list = new ArrayList();  list.add("GROUP_MONEY");  list.add("grouppaylocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList52(){  List list = new ArrayList();  list.add("GLOBAL_RATE");  list.add("globalrate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList53(){  List list = new ArrayList();  list.add("GLOBAL_MONEY");  list.add("globalpaylocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList54(){  List list = new ArrayList();  list.add("CURR_MONEY");  list.add("pay");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList55(){  List list = new ArrayList();  list.add(getManagedList56());  list.add(getManagedList57());  list.add(getManagedList58());  list.add(getManagedList59());  list.add(getManagedList60());  list.add(getManagedList61());  list.add(getManagedList62());  return list;}

private List getManagedList56(){  List list = new ArrayList();  list.add("ORG");  list.add("pk_org");  list.add("HEAD");  return list;}

private List getManagedList57(){  List list = new ArrayList();  list.add("GROUP");  list.add("pk_group");  list.add("HEAD");  return list;}

private List getManagedList58(){  List list = new ArrayList();  list.add("GLOBAL");  list.add("pk_org");  list.add("HEAD");  return list;}

private List getManagedList59(){  List list = new ArrayList();  list.add("CURR");  list.add("pk_currtype_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList60(){  List list = new ArrayList();  list.add("MONEY");  list.add("pay_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList61(){  List list = new ArrayList();  list.add("ORG_RATE");  list.add("paylocalrate_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList62(){  List list = new ArrayList();  list.add("EXCHANGEDATE");  list.add("billdate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList63(){  List list = new ArrayList();  list.add(getManagedList64());  list.add(getManagedList65());  list.add(getManagedList66());  list.add(getManagedList67());  list.add(getManagedList68());  list.add(getManagedList69());  list.add(getManagedList70());  list.add(getManagedList71());  list.add(getManagedList72());  list.add(getManagedList73());  list.add(getManagedList74());  list.add(getManagedList75());  list.add(getManagedList76());  return list;}

private List getManagedList64(){  List list = new ArrayList();  list.add("ORG_RATE");  list.add("paylocalrate_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList65(){  List list = new ArrayList();  list.add("ORG_RATE");  list.add("changerate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList66(){  List list = new ArrayList();  list.add("ORG_MONEY");  list.add("paylocal_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList67(){  List list = new ArrayList();  list.add("ORG_MONEY");  list.add("changebalance");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList68(){  List list = new ArrayList();  list.add("GROUP_RATE");  list.add("grouppayrate_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList69(){  List list = new ArrayList();  list.add("GROUP_MONEY");  list.add("grouppaylocal_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList70(){  List list = new ArrayList();  list.add("GLOBAL_RATE");  list.add("globalpayrate_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList71(){  List list = new ArrayList();  list.add("GLOBAL_MONEY");  list.add("globalpaylocal_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList72(){  List list = new ArrayList();  list.add("CURR_MONEY");  list.add("pay_last");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList73(){  List list = new ArrayList();  list.add("CURR_MONEY");  list.add("agentreceiveprimal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList74(){  List list = new ArrayList();  list.add("ORG_MONEY");  list.add("agentreceivelocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList75(){  List list = new ArrayList();  list.add("GROUP_MONEY");  list.add("groupagentreceivelocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList76(){  List list = new ArrayList();  list.add("GLOBAL_MONEY");  list.add("globalagentreceivelocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList77(){  List list = new ArrayList();  list.add(getManagedList78());  list.add(getManagedList79());  list.add(getManagedList80());  list.add(getManagedList81());  list.add(getManagedList82());  list.add(getManagedList83());  list.add(getManagedList84());  return list;}

private List getManagedList78(){  List list = new ArrayList();  list.add("ORG");  list.add("pk_org");  list.add("HEAD");  return list;}

private List getManagedList79(){  List list = new ArrayList();  list.add("GROUP");  list.add("pk_group");  list.add("HEAD");  return list;}

private List getManagedList80(){  List list = new ArrayList();  list.add("GLOBAL");  list.add("pk_org");  list.add("HEAD");  return list;}

private List getManagedList81(){  List list = new ArrayList();  list.add("CURR");  list.add("pk_currtype");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList82(){  List list = new ArrayList();  list.add("MONEY");  list.add("receive");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList83(){  List list = new ArrayList();  list.add("EXCHANGEDATE");  list.add("billdate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList84(){  List list = new ArrayList();  list.add("ORG_RATE");  list.add("localrate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList85(){  List list = new ArrayList();  list.add(getManagedList86());  list.add(getManagedList87());  list.add(getManagedList88());  list.add(getManagedList89());  list.add(getManagedList90());  list.add(getManagedList91());  list.add(getManagedList92());  list.add(getManagedList93());  list.add(getManagedList94());  list.add(getManagedList95());  list.add(getManagedList96());  return list;}

private List getManagedList86(){  List list = new ArrayList();  list.add("ORG_RATE");  list.add("localrate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList87(){  List list = new ArrayList();  list.add("ORG_MONEY");  list.add("receivelocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList88(){  List list = new ArrayList();  list.add("GROUP_RATE");  list.add("grouprate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList89(){  List list = new ArrayList();  list.add("GROUP_MONEY");  list.add("groupreceivelocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList90(){  List list = new ArrayList();  list.add("GLOBAL_RATE");  list.add("globalrate");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList91(){  List list = new ArrayList();  list.add("GLOBAL_MONEY");  list.add("globalreceivelocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList92(){  List list = new ArrayList();  list.add("CURR_MONEY");  list.add("receive");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList93(){  List list = new ArrayList();  list.add("CURR_MONEY");  list.add("agentreceiveprimal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList94(){  List list = new ArrayList();  list.add("ORG_MONEY");  list.add("agentreceivelocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList95(){  List list = new ArrayList();  list.add("GROUP_MONEY");  list.add("groupagentreceivelocal");  list.add("BODY");  list.add("items");  return list;}

private List getManagedList96(){  List list = new ArrayList();  list.add("GLOBAL_MONEY");  list.add("globalagentreceivelocal");  list.add("BODY");  list.add("items");  return list;}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getDigitMediator(){
 if(context.get("digitMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("digitMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("digitMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setHandlerMap(getManagedMap2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap2(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.list.ListPanelLoadEvent",getManagedList97());  map.put("nc.ui.pubapp.uif2app.event.card.CardPanelLoadEvent",getManagedList98());  map.put("nc.ui.uif2.AppEvent",getManagedList99());  return map;}

private List getManagedList97(){  List list = new ArrayList();  list.add(getListLoadListener());  return list;}

private List getManagedList98(){  List list = new ArrayList();  list.add(getCardLoadListener());  return list;}

private List getManagedList99(){  List list = new ArrayList();  list.add(getCardPanelRowChangedListener());  return list;}

public nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener getListLoadListener(){
 if(context.get("listLoadListener")!=null)
 return (nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener)context.get("listLoadListener");
  nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener bean = new nc.ui.tmpub.digit.listener.list.ListPanelLoadDigitListener();
  context.put("listLoadListener",bean);
  bean.setSrcDestItemCollection(getListSrcDestCollection());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener getCardLoadListener(){
 if(context.get("cardLoadListener")!=null)
 return (nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener)context.get("cardLoadListener");
  nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener bean = new nc.ui.tmpub.digit.listener.card.CardPanelLoadDigitListener();
  context.put("cardLoadListener",bean);
  bean.setSrcDestItemCollection(getListSrcDestCollection());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.tmpub.digit.listener.card.CardPanelSelectionChangedListener getCardPanelRowChangedListener(){
 if(context.get("cardPanelRowChangedListener")!=null)
 return (nc.ui.tmpub.digit.listener.card.CardPanelSelectionChangedListener)context.get("cardPanelRowChangedListener");
  nc.ui.tmpub.digit.listener.card.CardPanelSelectionChangedListener bean = new nc.ui.tmpub.digit.listener.card.CardPanelSelectionChangedListener(getBillFormEditor(),getListSrcDestCollection());  context.put("cardPanelRowChangedListener",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.EditHandleMediator getCardPanelCurrEditDigitMediator(){
 if(context.get("cardPanelCurrEditDigitMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.EditHandleMediator)context.get("cardPanelCurrEditDigitMediator");
  nc.ui.pubapp.uif2app.view.EditHandleMediator bean = new nc.ui.pubapp.uif2app.view.EditHandleMediator(getBillFormEditor());  context.put("cardPanelCurrEditDigitMediator",bean);
  bean.setDispatcher(getMany2ManyDispatcher_19ca509());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher getMany2ManyDispatcher_19ca509(){
 if(context.get("nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher#19ca509")!=null)
 return (nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher)context.get("nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher#19ca509");
  nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher bean = new nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher();
  context.put("nc.ui.pubapp.uif2app.view.eventdispatch.Many2ManyDispatcher#19ca509",bean);
  bean.setMany2one(getManagedMap3());
  bean.setOne2many(getManagedMap4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap3(){  Map map = new HashMap();  map.put(getManagedList100(),getCardEditListener());  return map;}

private List getManagedList100(){  List list = new ArrayList();  list.add("pk_org");  list.add("pay");  list.add("receive");  list.add("pay_last");  list.add("changerate");  list.add("paylocal_last");  list.add("pk_currtype_last");  list.add("localrate");  list.add("paylocalrate_last");  return list;}

private Map getManagedMap4(){  Map map = new HashMap();  map.put("notenumber",getManagedList101());  return map;}

private List getManagedList101(){  List list = new ArrayList();  list.add(getNoteNumberListener());  return list;}

public nc.ui.tmpub.digit.listener.card.CardPanelEditExListener getCardEditListener(){
 if(context.get("cardEditListener")!=null)
 return (nc.ui.tmpub.digit.listener.card.CardPanelEditExListener)context.get("cardEditListener");
  nc.ui.tmpub.digit.listener.card.CardPanelEditExListener bean = new nc.ui.tmpub.digit.listener.card.CardPanelEditExListener();
  context.put("cardEditListener",bean);
  bean.setSrcDestItemCollection(getListSrcDestCollection());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.cmp.settlement.filter.NotenumberListener getNoteNumberListener(){
 if(context.get("noteNumberListener")!=null)
 return (nc.ui.cmp.settlement.filter.NotenumberListener)context.get("noteNumberListener");
  nc.ui.cmp.settlement.filter.NotenumberListener bean = new nc.ui.cmp.settlement.filter.NotenumberListener();
  context.put("noteNumberListener",bean);
  bean.setEdit(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList102());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList102(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  return list;}

public nc.ui.pubapp.uif2app.linkquery.bd.BDLinkQueryMediator getBdLinkQueryMediator(){
 if(context.get("bdLinkQueryMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.bd.BDLinkQueryMediator)context.get("bdLinkQueryMediator");
  nc.ui.pubapp.uif2app.linkquery.bd.BDLinkQueryMediator bean = new nc.ui.pubapp.uif2app.linkquery.bd.BDLinkQueryMediator();
  context.put("bdLinkQueryMediator",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.vo.tmpub.security.SignAttributeNameVO getSignAttributeNameVO(){
 if(context.get("signAttributeNameVO")!=null)
 return (nc.vo.tmpub.security.SignAttributeNameVO)context.get("signAttributeNameVO");
  nc.vo.tmpub.security.SignAttributeNameVO bean = new nc.vo.tmpub.security.SignAttributeNameVO();
  context.put("signAttributeNameVO",bean);
  bean.setChildAttributeName("code");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
