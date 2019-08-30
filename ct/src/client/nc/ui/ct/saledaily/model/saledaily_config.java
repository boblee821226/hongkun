package nc.ui.ct.saledaily.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class saledaily_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  list.add(getActionsOfHistory());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());  context.put("actionsOfList",bean);
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getAddMenuAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getCommitMenuAction());  list.add(getApproveMenuAction());  list.add(getTransactMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction());  list.add(getAsstQueryMenuAction());  list.add(getSeparatorAction());  list.add(getReceiveMnyAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  list.add(getSeparatorAction());  list.add(getGenJftzdAction());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());  context.put("actionsOfCard",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList2());
  bean.setEditActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getAddMenuAction());  list.add(getEditAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getCardRefreshAction());  list.add(getSeparatorAction());  list.add(getCommitMenuAction());  list.add(getApproveMenuAction());  list.add(getTransactMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction());  list.add(getAsstQueryMenuAction());  list.add(getSeparatorAction());  list.add(getReceiveMnyAction());  list.add(getSeparatorAction());  list.add(getTuizuAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

private List getManagedList3(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSaveCommitAction());  list.add(getSeparatorAction());  list.add(getCancelAction());  list.add(getSeparatorAction());  list.add(getGenHttkAction());  list.add(getSeparatorAction());  list.add(getGenHtmxAction());  return list;}

public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getFormInterceptor(){
 if(context.get("formInterceptor")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("formInterceptor");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("formInterceptor",bean);
  bean.setShowUpComponent(getBillFormEditor());
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

public nc.ui.ct.saledaily.action.SaleAddAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleAddAction)context.get("addAction");
  nc.ui.ct.saledaily.action.SaleAddAction bean = new nc.ui.ct.saledaily.action.SaleAddAction();
  context.put("addAction",bean);
  bean.setCardForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getCompositeActionInterceptor_eaacc0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_eaacc0(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#eaacc0")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#eaacc0");
  nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#eaacc0",bean);
  bean.setInterceptors(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getShowUpComponentInterceptor_102110());  return list;}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_102110(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#102110")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#102110");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#102110",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleEditAction)context.get("editAction");
  nc.ui.ct.saledaily.action.SaleEditAction bean = new nc.ui.ct.saledaily.action.SaleEditAction();
  context.put("editAction",bean);
  bean.setCardForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getShowUpComponentInterceptor_f3b152());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_f3b152(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#f3b152")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#f3b152");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#f3b152",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)context.get("queryAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
  context.put("queryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setDataManager(getModelDataManager());
  bean.setTemplateContainer(getQueryTemplateContainer());
  bean.setQryCondDLGInitializer(getSaleQryCondDLGInitializer());
  bean.setShowUpComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.query.SaleQryCondDLGInitializer getSaleQryCondDLGInitializer(){
 if(context.get("saleQryCondDLGInitializer")!=null)
 return (nc.ui.ct.saledaily.query.SaleQryCondDLGInitializer)context.get("saleQryCondDLGInitializer");
  nc.ui.ct.saledaily.query.SaleQryCondDLGInitializer bean = new nc.ui.ct.saledaily.query.SaleQryCondDLGInitializer();
  context.put("saleQryCondDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleDeleteAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleDeleteAction)context.get("deleteAction");
  nc.ui.ct.saledaily.action.SaleDeleteAction bean = new nc.ui.ct.saledaily.action.SaleDeleteAction();
  context.put("deleteAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("DELETE");
  bean.setBillType("Z3");
  bean.setValidationService(getDelpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleModiDeleteAction getModiDeleteAction(){
 if(context.get("modiDeleteAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleModiDeleteAction)context.get("modiDeleteAction");
  nc.ui.ct.saledaily.action.SaleModiDeleteAction bean = new nc.ui.ct.saledaily.action.SaleModiDeleteAction();
  context.put("modiDeleteAction",bean);
  bean.setSingleBillService(getSaleModiDeleteService_162bd08());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.saledaily.service.SaleModiDeleteService getSaleModiDeleteService_162bd08(){
 if(context.get("nc.ui.ct.saledaily.service.SaleModiDeleteService#162bd08")!=null)
 return (nc.ui.ct.saledaily.service.SaleModiDeleteService)context.get("nc.ui.ct.saledaily.service.SaleModiDeleteService#162bd08");
  nc.ui.ct.saledaily.service.SaleModiDeleteService bean = new nc.ui.ct.saledaily.service.SaleModiDeleteService();
  context.put("nc.ui.ct.saledaily.service.SaleModiDeleteService#162bd08",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleSaveAction)context.get("saveAction");
  nc.ui.ct.saledaily.action.SaleSaveAction bean = new nc.ui.ct.saledaily.action.SaleSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setCardForm(getBillFormEditor());
  bean.setActionName("SAVEBASE");
  bean.setBillType("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.CancelAction)context.get("cancelAction");
  nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("refreshAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("refreshAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction(){
 if(context.get("cardRefreshAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)context.get("cardRefreshAction");
  nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
  context.put("cardRefreshAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.processor.CtPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.ct.processor.CtPrintProcessor)context.get("printProcessor");
  nc.ui.ct.processor.CtPrintProcessor bean = new nc.ui.ct.processor.CtPrintProcessor();
  context.put("printProcessor",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)context.get("previewAction");
  nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
  context.put("previewAction",bean);
  bean.setPreview(true);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SalePrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.ct.saledaily.action.SalePrintAction)context.get("printAction");
  nc.ui.ct.saledaily.action.SalePrintAction bean = new nc.ui.ct.saledaily.action.SalePrintAction();
  context.put("printAction",bean);
  bean.setPreview(false);
  bean.setModel(getManageAppModel());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleCommitScriptAction getCommitAction(){
 if(context.get("commitAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleCommitScriptAction)context.get("commitAction");
  nc.ui.ct.saledaily.action.SaleCommitScriptAction bean = new nc.ui.ct.saledaily.action.SaleCommitScriptAction();
  context.put("commitAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("SAVE");
  bean.setBillType("Z3");
  bean.setPreActionNames(getManagedList5());
  bean.setFilledUpInFlow(true);
  bean.setValidationService(getComitpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add("SAVEBASE");  return list;}

public nc.ui.ct.saledaily.action.SaveAndCommitAction getSaveCommitAction(){
 if(context.get("saveCommitAction")!=null)
 return (nc.ui.ct.saledaily.action.SaveAndCommitAction)context.get("saveCommitAction");
  nc.ui.ct.saledaily.action.SaveAndCommitAction bean = new nc.ui.ct.saledaily.action.SaveAndCommitAction(getSaveAction(),getCommitAction());  context.put("saveCommitAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleApproveAction getApproveAction(){
 if(context.get("approveAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleApproveAction)context.get("approveAction");
  nc.ui.ct.saledaily.action.SaleApproveAction bean = new nc.ui.ct.saledaily.action.SaleApproveAction();
  context.put("approveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setActionName("APPROVE");
  bean.setEditor(getBillFormEditor());
  bean.setFilledUpInFlow(false);
  bean.setBillType("Z3");
  bean.setValidationService(getPowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleUnApproveAction getUnApproveAction(){
 if(context.get("unApproveAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleUnApproveAction)context.get("unApproveAction");
  nc.ui.ct.saledaily.action.SaleUnApproveAction bean = new nc.ui.ct.saledaily.action.SaleUnApproveAction();
  context.put("unApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("UNAPPROVE");
  bean.setFilledUpInFlow(false);
  bean.setBillType("Z3");
  bean.setValidationService(getUnapprovepowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.action.CtCopyAction getCopyAction(){
 if(context.get("copyAction")!=null)
 return (nc.ui.ct.action.CtCopyAction)context.get("copyAction");
  nc.ui.ct.action.CtCopyAction bean = new nc.ui.ct.action.CtCopyAction();
  context.put("copyAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setCopyActionProcessor(getCopyActionProcessor());
  bean.setInterceptor(getFormInterceptor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleCopyActionProcessor getCopyActionProcessor(){
 if(context.get("copyActionProcessor")!=null)
 return (nc.ui.ct.saledaily.action.SaleCopyActionProcessor)context.get("copyActionProcessor");
  nc.ui.ct.saledaily.action.SaleCopyActionProcessor bean = new nc.ui.ct.saledaily.action.SaleCopyActionProcessor();
  context.put("copyActionProcessor",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleFrozenAction getFrozenAction(){
 if(context.get("frozenAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleFrozenAction)context.get("frozenAction");
  nc.ui.ct.saledaily.action.SaleFrozenAction bean = new nc.ui.ct.saledaily.action.SaleFrozenAction();
  context.put("frozenAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("FREEZE");
  bean.setFilledUpInFlow(false);
  bean.setBillType("Z3");
  bean.setValidationService(getFrozenpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleUnFrozenAction getUnFrozenAction(){
 if(context.get("unFrozenAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleUnFrozenAction)context.get("unFrozenAction");
  nc.ui.ct.saledaily.action.SaleUnFrozenAction bean = new nc.ui.ct.saledaily.action.SaleUnFrozenAction();
  context.put("unFrozenAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("UNFREEZE");
  bean.setFilledUpInFlow(false);
  bean.setBillType("Z3");
  bean.setValidationService(getUnfrozenpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleReceiveMnyAction getReceiveMnyAction(){
 if(context.get("receiveMnyAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleReceiveMnyAction)context.get("receiveMnyAction");
  nc.ui.ct.saledaily.action.SaleReceiveMnyAction bean = new nc.ui.ct.saledaily.action.SaleReceiveMnyAction();
  context.put("receiveMnyAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCardForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.GenJftzdAction getGenJftzdAction(){
 if(context.get("genJftzdAction")!=null)
 return (nc.ui.ct.saledaily.action.GenJftzdAction)context.get("genJftzdAction");
  nc.ui.ct.saledaily.action.GenJftzdAction bean = new nc.ui.ct.saledaily.action.GenJftzdAction();
  context.put("genJftzdAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCardForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.GenHttkAction getGenHttkAction(){
 if(context.get("genHttkAction")!=null)
 return (nc.ui.ct.saledaily.action.GenHttkAction)context.get("genHttkAction");
  nc.ui.ct.saledaily.action.GenHttkAction bean = new nc.ui.ct.saledaily.action.GenHttkAction();
  context.put("genHttkAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCardForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.GenHtmxAction getGenHtmxAction(){
 if(context.get("genHtmxAction")!=null)
 return (nc.ui.ct.saledaily.action.GenHtmxAction)context.get("genHtmxAction");
  nc.ui.ct.saledaily.action.GenHtmxAction bean = new nc.ui.ct.saledaily.action.GenHtmxAction();
  context.put("genHtmxAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCardForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.TuizuAction getTuizuAction(){
 if(context.get("tuizuAction")!=null)
 return (nc.ui.ct.saledaily.action.TuizuAction)context.get("tuizuAction");
  nc.ui.ct.saledaily.action.TuizuAction bean = new nc.ui.ct.saledaily.action.TuizuAction();
  context.put("tuizuAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCardForm(getBillFormEditor());
  bean.setListForm(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleReceiveMnyStartDateAction getReceiveMnyStartDateAction(){
 if(context.get("receiveMnyStartDateAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleReceiveMnyStartDateAction)context.get("receiveMnyStartDateAction");
  nc.ui.ct.saledaily.action.SaleReceiveMnyStartDateAction bean = new nc.ui.ct.saledaily.action.SaleReceiveMnyStartDateAction();
  context.put("receiveMnyStartDateAction",bean);
  bean.setCardForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getShowUpComponentInterceptor_1686643());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_1686643(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1686643")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1686643");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#1686643",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleAppStateAction getAppStateAction(){
 if(context.get("appStateAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleAppStateAction)context.get("appStateAction");
  nc.ui.ct.saledaily.action.SaleAppStateAction bean = new nc.ui.ct.saledaily.action.SaleAppStateAction();
  context.put("appStateAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.action.CtChangeAction getChangeAction(){
 if(context.get("changeAction")!=null)
 return (nc.ui.ct.action.CtChangeAction)context.get("changeAction");
  nc.ui.ct.action.CtChangeAction bean = new nc.ui.ct.action.CtChangeAction();
  context.put("changeAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCardForm(getBillFormEditor());
  bean.setListForm(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleModifyAction getModifyAction(){
 if(context.get("modifyAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleModifyAction)context.get("modifyAction");
  nc.ui.ct.saledaily.action.SaleModifyAction bean = new nc.ui.ct.saledaily.action.SaleModifyAction();
  context.put("modifyAction",bean);
  bean.setModel(getManageAppModel());
  bean.setCardForm(getBillFormEditor());
  bean.setEditAction(getEditAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleTerminateAction getTerminateAction(){
 if(context.get("terminateAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleTerminateAction)context.get("terminateAction");
  nc.ui.ct.saledaily.action.SaleTerminateAction bean = new nc.ui.ct.saledaily.action.SaleTerminateAction();
  context.put("terminateAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setCardForm(getBillFormEditor());
  bean.setActionName("TERMINATE");
  bean.setFilledUpInFlow(false);
  bean.setBillType("Z3");
  bean.setValidationService(getTerminatepowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleUnTerminateAction getUnterminateAction(){
 if(context.get("unterminateAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleUnTerminateAction)context.get("unterminateAction");
  nc.ui.ct.saledaily.action.SaleUnTerminateAction bean = new nc.ui.ct.saledaily.action.SaleUnTerminateAction();
  context.put("unterminateAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setCardForm(getBillFormEditor());
  bean.setActionName("UNTERMINATE");
  bean.setFilledUpInFlow(false);
  bean.setBillType("Z3");
  bean.setValidationService(getUnterminatepowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getUnterminatepowervalidservice(){
 if(context.get("unterminatepowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("unterminatepowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("unterminatepowervalidservice",bean);
  bean.setActionCode("unterminate");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleValidateAction getValidateAction(){
 if(context.get("validateAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleValidateAction)context.get("validateAction");
  nc.ui.ct.saledaily.action.SaleValidateAction bean = new nc.ui.ct.saledaily.action.SaleValidateAction();
  context.put("validateAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setCardForm(getBillFormEditor());
  bean.setActionName("VALIDATE");
  bean.setFilledUpInFlow(false);
  bean.setBillType("Z3");
  bean.setValidationService(getValidatepowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.scmmm.ui.uif2.actions.SCMLinkQueryAction getLinkBillAction(){
 if(context.get("linkBillAction")!=null)
 return (nc.scmmm.ui.uif2.actions.SCMLinkQueryAction)context.get("linkBillAction");
  nc.scmmm.ui.uif2.actions.SCMLinkQueryAction bean = new nc.scmmm.ui.uif2.actions.SCMLinkQueryAction();
  context.put("linkBillAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleTakeBackAction getTakeBackAction(){
 if(context.get("takeBackAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleTakeBackAction)context.get("takeBackAction");
  nc.ui.ct.saledaily.action.SaleTakeBackAction bean = new nc.ui.ct.saledaily.action.SaleTakeBackAction();
  context.put("takeBackAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("UNSAVEBILL");
  bean.setFilledUpInFlow(true);
  bean.setBillType("Z3");
  bean.setValidationService(getUncomitpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleUnValidateAction getUnValidateAction(){
 if(context.get("unValidateAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleUnValidateAction)context.get("unValidateAction");
  nc.ui.ct.saledaily.action.SaleUnValidateAction bean = new nc.ui.ct.saledaily.action.SaleUnValidateAction();
  context.put("unValidateAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("UNVALIDATE");
  bean.setFilledUpInFlow(false);
  bean.setBillType("Z3");
  bean.setValidationService(getUnvalidatepowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.SaleAddFromSourceAction getBillAction(){
 if(context.get("billAction")!=null)
 return (nc.ui.ct.saledaily.action.SaleAddFromSourceAction)context.get("billAction");
  nc.ui.ct.saledaily.action.SaleAddFromSourceAction bean = new nc.ui.ct.saledaily.action.SaleAddFromSourceAction();
  context.put("billAction",bean);
  bean.setSourceBillType("4310");
  bean.setSourceBillName(getI18nFB_111f1d9());
  bean.setFlowBillType(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_111f1d9(){
 if(context.get("nc.ui.uif2.I18nFB#111f1d9")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#111f1d9");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#111f1d9",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0523");
  bean.setDefaultValue("销售报价单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#111f1d9",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenuAction(){
 if(context.get("addMenuAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.AddMenuAction)context.get("addMenuAction");
  nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
  context.put("addMenuAction",bean);
  bean.setBillType("Z3");
  bean.setActions(getManagedList6());
  bean.setModel(getManageAppModel());
  bean.setPfAddInfoLoader(getPfAddInfoLoader());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getAddAction());  list.add(getSeparatorAction());  list.add(getBillAction());  return list;}

public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader(){
 if(context.get("pfAddInfoLoader")!=null)
 return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader)context.get("pfAddInfoLoader");
  nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
  context.put("pfAddInfoLoader",bean);
  bean.setBillType("Z3");
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getCommitMenuAction(){
 if(context.get("commitMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("commitMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("commitMenuAction",bean);
  bean.setCode("commitMenuAction");
  bean.setActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getCommitAction());  list.add(getTakeBackAction());  return list;}

public nc.funcnode.ui.action.GroupAction getApproveMenuAction(){
 if(context.get("approveMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("approveMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("approveMenuAction",bean);
  bean.setCode("approveMenuAction");
  bean.setActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getApproveAction());  list.add(getUnApproveAction());  list.add(getSeparatorAction());  list.add(getAppStateAction());  return list;}

public nc.ui.ct.action.TransactMenuAction getTransactMenuAction(){
 if(context.get("transactMenuAction")!=null)
 return (nc.ui.ct.action.TransactMenuAction)context.get("transactMenuAction");
  nc.ui.ct.action.TransactMenuAction bean = new nc.ui.ct.action.TransactMenuAction();
  context.put("transactMenuAction",bean);
  bean.setCode("transactMenuAction");
  bean.setActions(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getValidateAction());  list.add(getUnValidateAction());  list.add(getTerminateAction());  list.add(getUnterminateAction());  list.add(getModifyAction());  list.add(getModiDeleteAction());  return list;}

public nc.ui.ct.action.AsstQueryMenuAction getAsstQueryMenuAction(){
 if(context.get("asstQueryMenuAction")!=null)
 return (nc.ui.ct.action.AsstQueryMenuAction)context.get("asstQueryMenuAction");
  nc.ui.ct.action.AsstQueryMenuAction bean = new nc.ui.ct.action.AsstQueryMenuAction();
  context.put("asstQueryMenuAction",bean);
  bean.setCode("asstQueryMenuAction");
  bean.setActions(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getLinkBillAction());  list.add(getSeparatorAction());  list.add(getSeparatorAction());  return list;}

public nc.ui.ct.action.AssistMenuAction getAssistMenuAction(){
 if(context.get("assistMenuAction")!=null)
 return (nc.ui.ct.action.AssistMenuAction)context.get("assistMenuAction");
  nc.ui.ct.action.AssistMenuAction bean = new nc.ui.ct.action.AssistMenuAction();
  context.put("assistMenuAction",bean);
  bean.setCode("assistMenuAction");
  bean.setActions(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getObadocmanageAction());  list.add(getSeparatorAction());  list.add(getAccessoriesAction());  list.add(getSeparatorAction());  list.add(getChangeAction());  list.add(getSeparatorAction());  list.add(getReceiveMnyStartDateAction());  return list;}

public nc.ui.pubapp.uif2app.actions.FileDocManageAction getAccessoriesAction(){
 if(context.get("accessoriesAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.FileDocManageAction)context.get("accessoriesAction");
  nc.ui.pubapp.uif2app.actions.FileDocManageAction bean = new nc.ui.pubapp.uif2app.actions.FileDocManageAction();
  context.put("accessoriesAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.oba.action.bill.OBADocManageBillAction getObadocmanageAction(){
 if(context.get("obadocmanageAction")!=null)
 return (nc.ui.oba.action.bill.OBADocManageBillAction)context.get("obadocmanageAction");
  nc.ui.oba.action.bill.OBADocManageBillAction bean = new nc.ui.oba.action.bill.OBADocManageBillAction();
  context.put("obadocmanageAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
  nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
  context.put("outputAction",bean);
  bean.setModel(getManageAppModel());
  bean.setParent(getBillFormEditor());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.action.GatherMnyAction getGatherMnyAction(){
 if(context.get("gatherMnyAction")!=null)
 return (nc.ui.ct.saledaily.action.GatherMnyAction)context.get("gatherMnyAction");
  nc.ui.ct.saledaily.action.GatherMnyAction bean = new nc.ui.ct.saledaily.action.GatherMnyAction();
  context.put("gatherMnyAction",bean);
  bean.setModel(getManageAppModel());
  bean.setListView(getListView());
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPrintMenuAction(){
 if(context.get("printMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMenuAction",bean);
  bean.setCode("print");
  bean.setActions(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  list.add(getPrintCountQueryAction());  return list;}

public nc.ui.pubapp.pub.power.PowerValidateService getComitpowervalidservice(){
 if(context.get("comitpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("comitpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("comitpowervalidservice",bean);
  bean.setActionCode("commit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getPowervalidservice(){
 if(context.get("powervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("powervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("powervalidservice",bean);
  bean.setActionCode("approve");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getUnapprovepowervalidservice(){
 if(context.get("unapprovepowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("unapprovepowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("unapprovepowervalidservice",bean);
  bean.setActionCode("unapprove");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getUncomitpowervalidservice(){
 if(context.get("uncomitpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("uncomitpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("uncomitpowervalidservice",bean);
  bean.setActionCode("uncommit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getDelpowervalidservice(){
 if(context.get("delpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("delpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("delpowervalidservice",bean);
  bean.setActionCode("delete");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getFrozenpowervalidservice(){
 if(context.get("frozenpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("frozenpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("frozenpowervalidservice",bean);
  bean.setActionCode("frozen");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getUnfrozenpowervalidservice(){
 if(context.get("unfrozenpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("unfrozenpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("unfrozenpowervalidservice",bean);
  bean.setActionCode("unfrozen");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getTerminatepowervalidservice(){
 if(context.get("terminatepowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("terminatepowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("terminatepowervalidservice",bean);
  bean.setActionCode("terminate");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getValidatepowervalidservice(){
 if(context.get("validatepowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("validatepowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("validatepowervalidservice",bean);
  bean.setActionCode("validate");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getUnvalidatepowervalidservice(){
 if(context.get("unvalidatepowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("unvalidatepowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("unvalidatepowervalidservice",bean);
  bean.setActionCode("unvalidate");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.action.SCMPrintCountQueryAction getPrintCountQueryAction(){
 if(context.get("printCountQueryAction")!=null)
 return (nc.ui.scmpub.action.SCMPrintCountQueryAction)context.get("printCountQueryAction");
  nc.ui.scmpub.action.SCMPrintCountQueryAction bean = new nc.ui.scmpub.action.SCMPrintCountQueryAction();
  context.put("printCountQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBilldateFieldName("dbilldate");
  bean.setBillType("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.action.CtCloseHistoryDlgAction getClosehistoryAction(){
 if(context.get("closehistoryAction")!=null)
 return (nc.ui.ct.action.CtCloseHistoryDlgAction)context.get("closehistoryAction");
  nc.ui.ct.action.CtCloseHistoryDlgAction bean = new nc.ui.ct.action.CtCloseHistoryDlgAction();
  context.put("closehistoryAction",bean);
  bean.setView(getListViewHistory());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfHistory(){
 if(context.get("actionsOfHistory")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfHistory");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListViewHistory());  context.put("actionsOfHistory",bean);
  bean.setActions(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList13(){  List list = new ArrayList();  list.add(getClosehistoryAction());  return list;}

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

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",getManagedList14());  map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",getManagedList15());  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",getManagedList16());  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",getManagedList17());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList18());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList19());  return map;}

private List getManagedList14(){  List list = new ArrayList();  list.add(getHeadrowchghandler());  return list;}

private List getManagedList15(){  List list = new ArrayList();  list.add(getMainorgchghandler());  return list;}

private List getManagedList16(){  List list = new ArrayList();  list.add(getBefore_headtail_edithandler());  return list;}

private List getManagedList17(){  List list = new ArrayList();  list.add(getAfter_headtail_edithandler());  return list;}

private List getManagedList18(){  List list = new ArrayList();  list.add(getBefore_body_edithandler());  return list;}

private List getManagedList19(){  List list = new ArrayList();  list.add(getAfter_body_edithandler());  list.add(getRelationCalculate());  return list;}

public nc.ui.ct.editor.rowchange.HeadRowChangeHandler getHeadrowchghandler(){
 if(context.get("headrowchghandler")!=null)
 return (nc.ui.ct.editor.rowchange.HeadRowChangeHandler)context.get("headrowchghandler");
  nc.ui.ct.editor.rowchange.HeadRowChangeHandler bean = new nc.ui.ct.editor.rowchange.HeadRowChangeHandler();
  context.put("headrowchghandler",bean);
  bean.setTableCode("pk_ct_sale_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.editor.org.SaleOrgChangedEventHandler getMainorgchghandler(){
 if(context.get("mainorgchghandler")!=null)
 return (nc.ui.ct.saledaily.editor.org.SaleOrgChangedEventHandler)context.get("mainorgchghandler");
  nc.ui.ct.saledaily.editor.org.SaleOrgChangedEventHandler bean = new nc.ui.ct.saledaily.editor.org.SaleOrgChangedEventHandler();
  context.put("mainorgchghandler",bean);
  bean.setCardForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.editor.before.SaleHeadTailBeforeEventHandler getBefore_headtail_edithandler(){
 if(context.get("before_headtail_edithandler")!=null)
 return (nc.ui.ct.saledaily.editor.before.SaleHeadTailBeforeEventHandler)context.get("before_headtail_edithandler");
  nc.ui.ct.saledaily.editor.before.SaleHeadTailBeforeEventHandler bean = new nc.ui.ct.saledaily.editor.before.SaleHeadTailBeforeEventHandler();
  context.put("before_headtail_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.editor.after.SaleHeadTailAfterEventHandler getAfter_headtail_edithandler(){
 if(context.get("after_headtail_edithandler")!=null)
 return (nc.ui.ct.saledaily.editor.after.SaleHeadTailAfterEventHandler)context.get("after_headtail_edithandler");
  nc.ui.ct.saledaily.editor.after.SaleHeadTailAfterEventHandler bean = new nc.ui.ct.saledaily.editor.after.SaleHeadTailAfterEventHandler();
  context.put("after_headtail_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.editor.before.SaleBodyBeforeEventHandler getBefore_body_edithandler(){
 if(context.get("before_body_edithandler")!=null)
 return (nc.ui.ct.saledaily.editor.before.SaleBodyBeforeEventHandler)context.get("before_body_edithandler");
  nc.ui.ct.saledaily.editor.before.SaleBodyBeforeEventHandler bean = new nc.ui.ct.saledaily.editor.before.SaleBodyBeforeEventHandler();
  context.put("before_body_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.editor.after.SaleBodyAfterEventHandler getAfter_body_edithandler(){
 if(context.get("after_body_edithandler")!=null)
 return (nc.ui.ct.saledaily.editor.after.SaleBodyAfterEventHandler)context.get("after_body_edithandler");
  nc.ui.ct.saledaily.editor.after.SaleBodyAfterEventHandler bean = new nc.ui.ct.saledaily.editor.after.SaleBodyAfterEventHandler();
  context.put("after_body_edithandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.util.RelationCalculate getRelationCalculate(){
 if(context.get("relationCalculate")!=null)
 return (nc.ui.ct.util.RelationCalculate)context.get("relationCalculate");
  nc.ui.ct.util.RelationCalculate bean = new nc.ui.ct.util.RelationCalculate();
  context.put("relationCalculate",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setQueryAction(getQueryAction());
  bean.setVoClassName("nc.vo.ct.saledaily.entity.AggCtSaleVO");
  bean.setAutoShowUpComponent(getBillFormEditor());
  bean.setProcessorMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("19",getCtSaleInitDataProcessor_8c6209());  map.put("3",getCTLinkQueryInitDataProcessor_78455b());  return map;}

private nc.ui.ct.saledaily.billref.CtSaleInitDataProcessor getCtSaleInitDataProcessor_8c6209(){
 if(context.get("nc.ui.ct.saledaily.billref.CtSaleInitDataProcessor#8c6209")!=null)
 return (nc.ui.ct.saledaily.billref.CtSaleInitDataProcessor)context.get("nc.ui.ct.saledaily.billref.CtSaleInitDataProcessor#8c6209");
  nc.ui.ct.saledaily.billref.CtSaleInitDataProcessor bean = new nc.ui.ct.saledaily.billref.CtSaleInitDataProcessor();
  context.put("nc.ui.ct.saledaily.billref.CtSaleInitDataProcessor#8c6209",bean);
  bean.setTransferProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.view.CTLinkQueryInitDataProcessor getCTLinkQueryInitDataProcessor_78455b(){
 if(context.get("nc.ui.ct.view.CTLinkQueryInitDataProcessor#78455b")!=null)
 return (nc.ui.ct.view.CTLinkQueryInitDataProcessor)context.get("nc.ui.ct.view.CTLinkQueryInitDataProcessor#78455b");
  nc.ui.ct.view.CTLinkQueryInitDataProcessor bean = new nc.ui.ct.view.CTLinkQueryInitDataProcessor();
  context.put("nc.ui.ct.view.CTLinkQueryInitDataProcessor#78455b",bean);
  bean.setFunNodeInitDataListener(getInitDataListener());
  bean.setView(getListViewHistory());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public java.util.ArrayList getPasteClearItem_ct_sale_b(){
 if(context.get("pasteClearItem_ct_sale_b")!=null)
 return (java.util.ArrayList)context.get("pasteClearItem_ct_sale_b");
  java.util.ArrayList bean = new java.util.ArrayList(getManagedList20());  context.put("pasteClearItem_ct_sale_b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList20(){  List list = new ArrayList();  list.add("nordnum");  list.add("nordsum");  list.add("pk_ct_sale_b");  list.add("noritotalgpmny");  list.add("ntotalgpmny");  list.add("pk_origctb");  list.add("pk_ecmct");  list.add("pk_ecmct_b");  list.add("vecmctbillcode");  list.add("ts");  return list;}

public java.util.ArrayList getPasteClearItem_ct_sale_term(){
 if(context.get("pasteClearItem_ct_sale_term")!=null)
 return (java.util.ArrayList)context.get("pasteClearItem_ct_sale_term");
  java.util.ArrayList bean = new java.util.ArrayList(getManagedList21());  context.put("pasteClearItem_ct_sale_term",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList21(){  List list = new ArrayList();  list.add("pk_ct_sale_term");  list.add("ts");  return list;}

public java.util.ArrayList getPasteClearItem_ct_sale_exp(){
 if(context.get("pasteClearItem_ct_sale_exp")!=null)
 return (java.util.ArrayList)context.get("pasteClearItem_ct_sale_exp");
  java.util.ArrayList bean = new java.util.ArrayList(getManagedList22());  context.put("pasteClearItem_ct_sale_exp",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList22(){  List list = new ArrayList();  list.add("pk_ct_sale_exp");  list.add("ts");  return list;}

public java.util.ArrayList getPasteClearItem_ct_sale_memora(){
 if(context.get("pasteClearItem_ct_sale_memora")!=null)
 return (java.util.ArrayList)context.get("pasteClearItem_ct_sale_memora");
  java.util.ArrayList bean = new java.util.ArrayList(getManagedList23());  context.put("pasteClearItem_ct_sale_memora",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList23(){  List list = new ArrayList();  list.add("pk_ct_sale_memora");  list.add("ts");  return list;}

public java.util.ArrayList getPasteClearItem_ct_sale_payterm(){
 if(context.get("pasteClearItem_ct_sale_payterm")!=null)
 return (java.util.ArrayList)context.get("pasteClearItem_ct_sale_payterm");
  java.util.ArrayList bean = new java.util.ArrayList(getManagedList24());  context.put("pasteClearItem_ct_sale_payterm",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList24(){  List list = new ArrayList();  list.add("pk_ct_sale_payterm");  list.add("ts");  return list;}

public nc.vo.uif2.LoginContext getContext(){
 if(context.get("context")!=null)
 return (nc.vo.uif2.LoginContext)context.get("context");
  nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.model.SaledailyModelService getManageModelService(){
 if(context.get("ManageModelService")!=null)
 return (nc.ui.ct.saledaily.model.SaledailyModelService)context.get("ManageModelService");
  nc.ui.ct.saledaily.model.SaledailyModelService bean = new nc.ui.ct.saledaily.model.SaledailyModelService();
  context.put("ManageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.model.CTModel getManageAppModel(){
 if(context.get("ManageAppModel")!=null)
 return (nc.ui.ct.model.CTModel)context.get("ManageAppModel");
  nc.ui.ct.model.CTModel bean = new nc.ui.ct.model.CTModel();
  context.put("ManageAppModel",bean);
  bean.setService(getManageModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
  bean.setBillType("Z3");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator(){
 if(context.get("billBodySortMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)context.get("billBodySortMediator");
  nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(),getBillFormEditor(),getListView());  context.put("billBodySortMediator",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageModelDataManager)context.get("modelDataManager");
  nc.ui.scmpub.page.model.SCMBillPageModelDataManager bean = new nc.ui.scmpub.page.model.SCMBillPageModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setPageQuery(getPageQuery());
  bean.setPageDelegator(getPageDelegator());
  bean.setPagePanel(getQueryInfo());
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
  bean.setBillTemplateMender(getBillTemplateMender());
  bean.setNodeKeies(getManagedList25());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList25(){  List list = new ArrayList();  list.add("Z306");  return list;}

public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getBillTemplateMender(){
 if(context.get("billTemplateMender")!=null)
 return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender)context.get("billTemplateMender");
  nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(getContext());  context.put("billTemplateMender",bean);
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
  bean.setType(getI18nFB_8611d7());
  bean.setOnlyLeafCanSelected(false);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_8611d7(){
 if(context.get("nc.ui.uif2.I18nFB#8611d7")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#8611d7");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#8611d7",bean);  bean.setResDir("4020003_0");
  bean.setResId("04020003-0431");
  bean.setDefaultValue("销售组织");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#8611d7",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ct.view.CTListView getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.ct.view.CTListView)context.get("listView");
  nc.ui.ct.view.CTListView bean = new nc.ui.ct.view.CTListView();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionEnable(true);
  bean.setShowTotalLineTabcodes(getManagedList26());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
  bean.setPaginationBar(getPageBar());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList26(){  List list = new ArrayList();  list.add("pk_ct_sale_b");  list.add("pk_ct_sale_exp");  list.add("pk_ct_sale_payterm");  return list;}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVsourcecodeMediator(){
 if(context.get("vsourcecodeMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("vsourcecodeMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("vsourcecodeMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("csrcid");
  bean.setSrcBillNOField("vsrccode");
  bean.setSrcBillTypeField("vsrctype");
  bean.setSrcBillTypeFieldPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.view.SaledailyBillForm getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.ct.saledaily.view.SaledailyBillForm)context.get("billFormEditor");
  nc.ui.ct.saledaily.view.SaledailyBillForm bean = new nc.ui.ct.saledaily.view.SaledailyBillForm();
  context.put("billFormEditor",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
  bean.setTemplateNotNullValidate(true);
  bean.setShowTotalLineTabcodes(getManagedList27());
  bean.setAutoAddLine(true);
  bean.setBlankChildrenFilter(getBlankitemfilter());
  bean.setBodyActionMap(getManagedMap2());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList27(){  List list = new ArrayList();  list.add("pk_ct_sale_b");  list.add("pk_ct_sale_exp");  list.add("pk_ct_sale_payterm");  return list;}

private Map getManagedMap2(){  Map map = new HashMap();  map.put("pk_ct_sale_b",getManagedList28());  map.put("pk_ct_sale_term",getManagedList29());  map.put("pk_ct_sale_exp",getManagedList30());  map.put("pk_ct_sale_memora",getManagedList31());  map.put("pk_ct_sale_payterm",getManagedList32());  return map;}

private List getManagedList28(){  List list = new ArrayList();  list.add(getCtSaleAddLineAction_1fd5845());  list.add(getCtSaleInsertLineAction_1ae36ba());  list.add(getManageBDelLineAction_8b1b55());  list.add(getManageBCopyLineAction_af8880());  list.add(getManageBPasteLineAction_2ab4e2());  list.add(getManageBPasteToTailLineAction_1c97c2f());  list.add(getActionsBar_ActionsBarSeparator_f822ca());  list.add(getManageRearrangeRowLineAction_b40ebc());  list.add(getBodyLineEditAction_1336863());  list.add(getActionsBar_ActionsBarSeparator_a5bff());  list.add(getDefaultBodyZoomAction_1de74c());  return list;}

private nc.ui.ct.saledaily.action.CtSaleAddLineAction getCtSaleAddLineAction_1fd5845(){
 if(context.get("nc.ui.ct.saledaily.action.CtSaleAddLineAction#1fd5845")!=null)
 return (nc.ui.ct.saledaily.action.CtSaleAddLineAction)context.get("nc.ui.ct.saledaily.action.CtSaleAddLineAction#1fd5845");
  nc.ui.ct.saledaily.action.CtSaleAddLineAction bean = new nc.ui.ct.saledaily.action.CtSaleAddLineAction();
  context.put("nc.ui.ct.saledaily.action.CtSaleAddLineAction#1fd5845",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.saledaily.action.CtSaleInsertLineAction getCtSaleInsertLineAction_1ae36ba(){
 if(context.get("nc.ui.ct.saledaily.action.CtSaleInsertLineAction#1ae36ba")!=null)
 return (nc.ui.ct.saledaily.action.CtSaleInsertLineAction)context.get("nc.ui.ct.saledaily.action.CtSaleInsertLineAction#1ae36ba");
  nc.ui.ct.saledaily.action.CtSaleInsertLineAction bean = new nc.ui.ct.saledaily.action.CtSaleInsertLineAction();
  context.put("nc.ui.ct.saledaily.action.CtSaleInsertLineAction#1ae36ba",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.manage.ManageBDelLineAction getManageBDelLineAction_8b1b55(){
 if(context.get("nc.ui.ct.bodyaction.manage.ManageBDelLineAction#8b1b55")!=null)
 return (nc.ui.ct.bodyaction.manage.ManageBDelLineAction)context.get("nc.ui.ct.bodyaction.manage.ManageBDelLineAction#8b1b55");
  nc.ui.ct.bodyaction.manage.ManageBDelLineAction bean = new nc.ui.ct.bodyaction.manage.ManageBDelLineAction();
  context.put("nc.ui.ct.bodyaction.manage.ManageBDelLineAction#8b1b55",bean);
  bean.setTableCode("pk_ct_sale_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.manage.ManageBCopyLineAction getManageBCopyLineAction_af8880(){
 if(context.get("nc.ui.ct.bodyaction.manage.ManageBCopyLineAction#af8880")!=null)
 return (nc.ui.ct.bodyaction.manage.ManageBCopyLineAction)context.get("nc.ui.ct.bodyaction.manage.ManageBCopyLineAction#af8880");
  nc.ui.ct.bodyaction.manage.ManageBCopyLineAction bean = new nc.ui.ct.bodyaction.manage.ManageBCopyLineAction();
  context.put("nc.ui.ct.bodyaction.manage.ManageBCopyLineAction#af8880",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.manage.ManageBPasteLineAction getManageBPasteLineAction_2ab4e2(){
 if(context.get("nc.ui.ct.bodyaction.manage.ManageBPasteLineAction#2ab4e2")!=null)
 return (nc.ui.ct.bodyaction.manage.ManageBPasteLineAction)context.get("nc.ui.ct.bodyaction.manage.ManageBPasteLineAction#2ab4e2");
  nc.ui.ct.bodyaction.manage.ManageBPasteLineAction bean = new nc.ui.ct.bodyaction.manage.ManageBPasteLineAction();
  context.put("nc.ui.ct.bodyaction.manage.ManageBPasteLineAction#2ab4e2",bean);
  bean.setClearItems(getPasteClearItem_ct_sale_b());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.manage.ManageBPasteToTailLineAction getManageBPasteToTailLineAction_1c97c2f(){
 if(context.get("nc.ui.ct.bodyaction.manage.ManageBPasteToTailLineAction#1c97c2f")!=null)
 return (nc.ui.ct.bodyaction.manage.ManageBPasteToTailLineAction)context.get("nc.ui.ct.bodyaction.manage.ManageBPasteToTailLineAction#1c97c2f");
  nc.ui.ct.bodyaction.manage.ManageBPasteToTailLineAction bean = new nc.ui.ct.bodyaction.manage.ManageBPasteToTailLineAction();
  context.put("nc.ui.ct.bodyaction.manage.ManageBPasteToTailLineAction#1c97c2f",bean);
  bean.setClearItems(getPasteClearItem_ct_sale_b());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_f822ca(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#f822ca")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#f822ca");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#f822ca",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.manage.ManageRearrangeRowLineAction getManageRearrangeRowLineAction_b40ebc(){
 if(context.get("nc.ui.ct.bodyaction.manage.ManageRearrangeRowLineAction#b40ebc")!=null)
 return (nc.ui.ct.bodyaction.manage.ManageRearrangeRowLineAction)context.get("nc.ui.ct.bodyaction.manage.ManageRearrangeRowLineAction#b40ebc");
  nc.ui.ct.bodyaction.manage.ManageRearrangeRowLineAction bean = new nc.ui.ct.bodyaction.manage.ManageRearrangeRowLineAction();
  context.put("nc.ui.ct.bodyaction.manage.ManageRearrangeRowLineAction#b40ebc",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_1336863(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1336863")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1336863");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#1336863",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_a5bff(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#a5bff")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#a5bff");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#a5bff",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_1de74c(){
 if(context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1de74c")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction)context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1de74c");
  nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
  context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1de74c",bean);
  bean.setPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList29(){  List list = new ArrayList();  list.add(getTermBAddLineAction_13c3f0d());  list.add(getTermBInsertLineAction_4f743e());  list.add(getTermBDelLineAction_58a304());  list.add(getTermBCopyLineAction_1367c9c());  list.add(getTermBPasteLineAction_2f24f6());  return list;}

private nc.ui.ct.bodyaction.term.TermBAddLineAction getTermBAddLineAction_13c3f0d(){
 if(context.get("nc.ui.ct.bodyaction.term.TermBAddLineAction#13c3f0d")!=null)
 return (nc.ui.ct.bodyaction.term.TermBAddLineAction)context.get("nc.ui.ct.bodyaction.term.TermBAddLineAction#13c3f0d");
  nc.ui.ct.bodyaction.term.TermBAddLineAction bean = new nc.ui.ct.bodyaction.term.TermBAddLineAction();
  context.put("nc.ui.ct.bodyaction.term.TermBAddLineAction#13c3f0d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.term.TermBInsertLineAction getTermBInsertLineAction_4f743e(){
 if(context.get("nc.ui.ct.bodyaction.term.TermBInsertLineAction#4f743e")!=null)
 return (nc.ui.ct.bodyaction.term.TermBInsertLineAction)context.get("nc.ui.ct.bodyaction.term.TermBInsertLineAction#4f743e");
  nc.ui.ct.bodyaction.term.TermBInsertLineAction bean = new nc.ui.ct.bodyaction.term.TermBInsertLineAction();
  context.put("nc.ui.ct.bodyaction.term.TermBInsertLineAction#4f743e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.term.TermBDelLineAction getTermBDelLineAction_58a304(){
 if(context.get("nc.ui.ct.bodyaction.term.TermBDelLineAction#58a304")!=null)
 return (nc.ui.ct.bodyaction.term.TermBDelLineAction)context.get("nc.ui.ct.bodyaction.term.TermBDelLineAction#58a304");
  nc.ui.ct.bodyaction.term.TermBDelLineAction bean = new nc.ui.ct.bodyaction.term.TermBDelLineAction();
  context.put("nc.ui.ct.bodyaction.term.TermBDelLineAction#58a304",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.term.TermBCopyLineAction getTermBCopyLineAction_1367c9c(){
 if(context.get("nc.ui.ct.bodyaction.term.TermBCopyLineAction#1367c9c")!=null)
 return (nc.ui.ct.bodyaction.term.TermBCopyLineAction)context.get("nc.ui.ct.bodyaction.term.TermBCopyLineAction#1367c9c");
  nc.ui.ct.bodyaction.term.TermBCopyLineAction bean = new nc.ui.ct.bodyaction.term.TermBCopyLineAction();
  context.put("nc.ui.ct.bodyaction.term.TermBCopyLineAction#1367c9c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.term.TermBPasteLineAction getTermBPasteLineAction_2f24f6(){
 if(context.get("nc.ui.ct.bodyaction.term.TermBPasteLineAction#2f24f6")!=null)
 return (nc.ui.ct.bodyaction.term.TermBPasteLineAction)context.get("nc.ui.ct.bodyaction.term.TermBPasteLineAction#2f24f6");
  nc.ui.ct.bodyaction.term.TermBPasteLineAction bean = new nc.ui.ct.bodyaction.term.TermBPasteLineAction();
  context.put("nc.ui.ct.bodyaction.term.TermBPasteLineAction#2f24f6",bean);
  bean.setClearItems(getPasteClearItem_ct_sale_term());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList30(){  List list = new ArrayList();  list.add(getExpBAddLineAction_123ae44());  list.add(getExpBInsertLineAction_1861bf9());  list.add(getExpBDelLineAction_196b8d0());  list.add(getExpBCopyLineAction_3f98ec());  list.add(getExpBPasteLineAction_150b649());  return list;}

private nc.ui.ct.bodyaction.exp.ExpBAddLineAction getExpBAddLineAction_123ae44(){
 if(context.get("nc.ui.ct.bodyaction.exp.ExpBAddLineAction#123ae44")!=null)
 return (nc.ui.ct.bodyaction.exp.ExpBAddLineAction)context.get("nc.ui.ct.bodyaction.exp.ExpBAddLineAction#123ae44");
  nc.ui.ct.bodyaction.exp.ExpBAddLineAction bean = new nc.ui.ct.bodyaction.exp.ExpBAddLineAction();
  context.put("nc.ui.ct.bodyaction.exp.ExpBAddLineAction#123ae44",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.exp.ExpBInsertLineAction getExpBInsertLineAction_1861bf9(){
 if(context.get("nc.ui.ct.bodyaction.exp.ExpBInsertLineAction#1861bf9")!=null)
 return (nc.ui.ct.bodyaction.exp.ExpBInsertLineAction)context.get("nc.ui.ct.bodyaction.exp.ExpBInsertLineAction#1861bf9");
  nc.ui.ct.bodyaction.exp.ExpBInsertLineAction bean = new nc.ui.ct.bodyaction.exp.ExpBInsertLineAction();
  context.put("nc.ui.ct.bodyaction.exp.ExpBInsertLineAction#1861bf9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.exp.ExpBDelLineAction getExpBDelLineAction_196b8d0(){
 if(context.get("nc.ui.ct.bodyaction.exp.ExpBDelLineAction#196b8d0")!=null)
 return (nc.ui.ct.bodyaction.exp.ExpBDelLineAction)context.get("nc.ui.ct.bodyaction.exp.ExpBDelLineAction#196b8d0");
  nc.ui.ct.bodyaction.exp.ExpBDelLineAction bean = new nc.ui.ct.bodyaction.exp.ExpBDelLineAction();
  context.put("nc.ui.ct.bodyaction.exp.ExpBDelLineAction#196b8d0",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.exp.ExpBCopyLineAction getExpBCopyLineAction_3f98ec(){
 if(context.get("nc.ui.ct.bodyaction.exp.ExpBCopyLineAction#3f98ec")!=null)
 return (nc.ui.ct.bodyaction.exp.ExpBCopyLineAction)context.get("nc.ui.ct.bodyaction.exp.ExpBCopyLineAction#3f98ec");
  nc.ui.ct.bodyaction.exp.ExpBCopyLineAction bean = new nc.ui.ct.bodyaction.exp.ExpBCopyLineAction();
  context.put("nc.ui.ct.bodyaction.exp.ExpBCopyLineAction#3f98ec",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.exp.ExpBPasteLineAction getExpBPasteLineAction_150b649(){
 if(context.get("nc.ui.ct.bodyaction.exp.ExpBPasteLineAction#150b649")!=null)
 return (nc.ui.ct.bodyaction.exp.ExpBPasteLineAction)context.get("nc.ui.ct.bodyaction.exp.ExpBPasteLineAction#150b649");
  nc.ui.ct.bodyaction.exp.ExpBPasteLineAction bean = new nc.ui.ct.bodyaction.exp.ExpBPasteLineAction();
  context.put("nc.ui.ct.bodyaction.exp.ExpBPasteLineAction#150b649",bean);
  bean.setClearItems(getPasteClearItem_ct_sale_exp());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList31(){  List list = new ArrayList();  list.add(getMemoraBAddLineAction_1ef9318());  list.add(getMemoraBInsertLineAction_1a2339a());  list.add(getMemoraBDelLineAction_1e9f37c());  list.add(getMemoraBCopyLineAction_7074b9());  list.add(getMemoraBPasteLineAction_ff3894());  return list;}

private nc.ui.ct.bodyaction.memora.MemoraBAddLineAction getMemoraBAddLineAction_1ef9318(){
 if(context.get("nc.ui.ct.bodyaction.memora.MemoraBAddLineAction#1ef9318")!=null)
 return (nc.ui.ct.bodyaction.memora.MemoraBAddLineAction)context.get("nc.ui.ct.bodyaction.memora.MemoraBAddLineAction#1ef9318");
  nc.ui.ct.bodyaction.memora.MemoraBAddLineAction bean = new nc.ui.ct.bodyaction.memora.MemoraBAddLineAction();
  context.put("nc.ui.ct.bodyaction.memora.MemoraBAddLineAction#1ef9318",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.memora.MemoraBInsertLineAction getMemoraBInsertLineAction_1a2339a(){
 if(context.get("nc.ui.ct.bodyaction.memora.MemoraBInsertLineAction#1a2339a")!=null)
 return (nc.ui.ct.bodyaction.memora.MemoraBInsertLineAction)context.get("nc.ui.ct.bodyaction.memora.MemoraBInsertLineAction#1a2339a");
  nc.ui.ct.bodyaction.memora.MemoraBInsertLineAction bean = new nc.ui.ct.bodyaction.memora.MemoraBInsertLineAction();
  context.put("nc.ui.ct.bodyaction.memora.MemoraBInsertLineAction#1a2339a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.memora.MemoraBDelLineAction getMemoraBDelLineAction_1e9f37c(){
 if(context.get("nc.ui.ct.bodyaction.memora.MemoraBDelLineAction#1e9f37c")!=null)
 return (nc.ui.ct.bodyaction.memora.MemoraBDelLineAction)context.get("nc.ui.ct.bodyaction.memora.MemoraBDelLineAction#1e9f37c");
  nc.ui.ct.bodyaction.memora.MemoraBDelLineAction bean = new nc.ui.ct.bodyaction.memora.MemoraBDelLineAction();
  context.put("nc.ui.ct.bodyaction.memora.MemoraBDelLineAction#1e9f37c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.memora.MemoraBCopyLineAction getMemoraBCopyLineAction_7074b9(){
 if(context.get("nc.ui.ct.bodyaction.memora.MemoraBCopyLineAction#7074b9")!=null)
 return (nc.ui.ct.bodyaction.memora.MemoraBCopyLineAction)context.get("nc.ui.ct.bodyaction.memora.MemoraBCopyLineAction#7074b9");
  nc.ui.ct.bodyaction.memora.MemoraBCopyLineAction bean = new nc.ui.ct.bodyaction.memora.MemoraBCopyLineAction();
  context.put("nc.ui.ct.bodyaction.memora.MemoraBCopyLineAction#7074b9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.memora.MemoraBPasteLineAction getMemoraBPasteLineAction_ff3894(){
 if(context.get("nc.ui.ct.bodyaction.memora.MemoraBPasteLineAction#ff3894")!=null)
 return (nc.ui.ct.bodyaction.memora.MemoraBPasteLineAction)context.get("nc.ui.ct.bodyaction.memora.MemoraBPasteLineAction#ff3894");
  nc.ui.ct.bodyaction.memora.MemoraBPasteLineAction bean = new nc.ui.ct.bodyaction.memora.MemoraBPasteLineAction();
  context.put("nc.ui.ct.bodyaction.memora.MemoraBPasteLineAction#ff3894",bean);
  bean.setClearItems(getPasteClearItem_ct_sale_memora());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList32(){  List list = new ArrayList();  list.add(getPayTermBAddLineAction_1db9ee9());  list.add(getPayTermBInsertLineAction_19dd3f2());  list.add(getPayTermBDelLineAction_c5f33e());  list.add(getPayTermBCopyLineAction_1e4a12f());  list.add(getPayTermBPasteLineAction_11222f7());  return list;}

private nc.ui.ct.bodyaction.payterm.PayTermBAddLineAction getPayTermBAddLineAction_1db9ee9(){
 if(context.get("nc.ui.ct.bodyaction.payterm.PayTermBAddLineAction#1db9ee9")!=null)
 return (nc.ui.ct.bodyaction.payterm.PayTermBAddLineAction)context.get("nc.ui.ct.bodyaction.payterm.PayTermBAddLineAction#1db9ee9");
  nc.ui.ct.bodyaction.payterm.PayTermBAddLineAction bean = new nc.ui.ct.bodyaction.payterm.PayTermBAddLineAction();
  context.put("nc.ui.ct.bodyaction.payterm.PayTermBAddLineAction#1db9ee9",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.payterm.PayTermBInsertLineAction getPayTermBInsertLineAction_19dd3f2(){
 if(context.get("nc.ui.ct.bodyaction.payterm.PayTermBInsertLineAction#19dd3f2")!=null)
 return (nc.ui.ct.bodyaction.payterm.PayTermBInsertLineAction)context.get("nc.ui.ct.bodyaction.payterm.PayTermBInsertLineAction#19dd3f2");
  nc.ui.ct.bodyaction.payterm.PayTermBInsertLineAction bean = new nc.ui.ct.bodyaction.payterm.PayTermBInsertLineAction();
  context.put("nc.ui.ct.bodyaction.payterm.PayTermBInsertLineAction#19dd3f2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.payterm.PayTermBDelLineAction getPayTermBDelLineAction_c5f33e(){
 if(context.get("nc.ui.ct.bodyaction.payterm.PayTermBDelLineAction#c5f33e")!=null)
 return (nc.ui.ct.bodyaction.payterm.PayTermBDelLineAction)context.get("nc.ui.ct.bodyaction.payterm.PayTermBDelLineAction#c5f33e");
  nc.ui.ct.bodyaction.payterm.PayTermBDelLineAction bean = new nc.ui.ct.bodyaction.payterm.PayTermBDelLineAction();
  context.put("nc.ui.ct.bodyaction.payterm.PayTermBDelLineAction#c5f33e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.payterm.PayTermBCopyLineAction getPayTermBCopyLineAction_1e4a12f(){
 if(context.get("nc.ui.ct.bodyaction.payterm.PayTermBCopyLineAction#1e4a12f")!=null)
 return (nc.ui.ct.bodyaction.payterm.PayTermBCopyLineAction)context.get("nc.ui.ct.bodyaction.payterm.PayTermBCopyLineAction#1e4a12f");
  nc.ui.ct.bodyaction.payterm.PayTermBCopyLineAction bean = new nc.ui.ct.bodyaction.payterm.PayTermBCopyLineAction();
  context.put("nc.ui.ct.bodyaction.payterm.PayTermBCopyLineAction#1e4a12f",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ct.bodyaction.payterm.PayTermBPasteLineAction getPayTermBPasteLineAction_11222f7(){
 if(context.get("nc.ui.ct.bodyaction.payterm.PayTermBPasteLineAction#11222f7")!=null)
 return (nc.ui.ct.bodyaction.payterm.PayTermBPasteLineAction)context.get("nc.ui.ct.bodyaction.payterm.PayTermBPasteLineAction#11222f7");
  nc.ui.ct.bodyaction.payterm.PayTermBPasteLineAction bean = new nc.ui.ct.bodyaction.payterm.PayTermBPasteLineAction();
  context.put("nc.ui.ct.bodyaction.payterm.PayTermBPasteLineAction#11222f7",bean);
  bean.setClearItems(getPasteClearItem_ct_sale_payterm());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.view.MouseClickShowPanel getMouseClickShowPanelMediator(){
 if(context.get("mouseClickShowPanelMediator")!=null)
 return (nc.ui.ct.saledaily.view.MouseClickShowPanel)context.get("mouseClickShowPanelMediator");
  nc.ui.ct.saledaily.view.MouseClickShowPanel bean = new nc.ui.ct.saledaily.view.MouseClickShowPanel();
  context.put("mouseClickShowPanelMediator",bean);
  bean.setListView(getListView());
  bean.setShowUpComponent(getBillFormEditor());
  bean.setHyperLinkColumn("vbillcode");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator getCardPanelOrgSetterForAllRefMediator(){
 if(context.get("cardPanelOrgSetterForAllRefMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator)context.get("cardPanelOrgSetterForAllRefMediator");
  nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator bean = new nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator(getBillFormEditor());  context.put("cardPanelOrgSetterForAllRefMediator",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.listener.BillCodeEditMediator getBillCodeMediator(){
 if(context.get("billCodeMediator")!=null)
 return (nc.ui.scmpub.listener.BillCodeEditMediator)context.get("billCodeMediator");
  nc.ui.scmpub.listener.BillCodeEditMediator bean = new nc.ui.scmpub.listener.BillCodeEditMediator();
  context.put("billCodeMediator",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setBillCodeKey("vbillcode");
  bean.setBillType("Z3");
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.QueryAreaShell getQueryArea(){
 if(context.get("queryArea")!=null)
 return (nc.ui.uif2.actions.QueryAreaShell)context.get("queryArea");
  nc.ui.uif2.actions.QueryAreaShell bean = new nc.ui.uif2.actions.QueryAreaShell();
  context.put("queryArea",bean);
  bean.setQueryAreaCreator(getQueryAction());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getQueryInfo(){
 if(context.get("queryInfo")!=null)
 return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("queryInfo");
  nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
  context.put("queryInfo",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

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

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList33());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList33(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  list.add(getPfAddInfoLoader());  return list;}

public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setContext(getContext());
  bean.setParams(getManagedList34());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList34(){  List list = new ArrayList();  list.add(getQueryParam_1b5a67a());  list.add(getQueryParam_c4b443());  list.add(getQueryParam_16b2b1());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1b5a67a(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#1b5a67a")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#1b5a67a");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#1b5a67a",bean);
  bean.setMdfullname("ct.ct_sale");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_c4b443(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#c4b443")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#c4b443");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#c4b443",bean);
  bean.setMdfullname("ct.ct_sale_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_16b2b1(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#16b2b1")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#16b2b1");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#16b2b1",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.view.SaledailyToolbarPanel getCardInfoPnl(){
 if(context.get("cardInfoPnl")!=null)
 return (nc.ui.ct.saledaily.view.SaledailyToolbarPanel)context.get("cardInfoPnl");
  nc.ui.ct.saledaily.view.SaledailyToolbarPanel bean = new nc.ui.ct.saledaily.view.SaledailyToolbarPanel();
  context.put("cardInfoPnl",bean);
  bean.setSaleBillForm(getBillFormEditor());
  bean.setTitleAction(getReturnaction());
  bean.setRightExActions(getManagedList35());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction(){
 if(context.get("returnaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.UEReturnAction)context.get("returnaction");
  nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
  context.put("returnaction",bean);
  bean.setGoComponent(getListView());
  bean.setSaveAction(getSaveAction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList35(){  List list = new ArrayList();  list.add(getActionsBar_ActionsBarSeparator_2dbe01());  list.add(getHeadZoomAction());  return list;}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_2dbe01(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2dbe01")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2dbe01");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2dbe01",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction(){
 if(context.get("headZoomAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction)context.get("headZoomAction");
  nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
  context.put("headZoomAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setPos(0);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setModel(getManageAppModel());
  bean.setTangramLayoutRoot(getTBNode_173319d());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_173319d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#173319d")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#173319d");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#173319d",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList36());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList36(){  List list = new ArrayList();  list.add(getHSNode_1300e28());  list.add(getVSNode_4e5fcb());  list.add(getListviewhistornode());  return list;}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1300e28(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#1300e28")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#1300e28");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#1300e28",bean);
  bean.setLeft(getCNode_115745e());
  bean.setRight(getVSNode_17782db());
  bean.setDividerLocation(0.22f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_115745e(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#115745e")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#115745e");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#115745e",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_17782db(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#17782db")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#17782db");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#17782db",bean);
  bean.setUp(getCNode_18aff45());
  bean.setDown(getCNode_fd6aef());
  bean.setDividerLocation(25f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_18aff45(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#18aff45")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#18aff45");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#18aff45",bean);
  bean.setComponent(getQueryInfo());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_fd6aef(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#fd6aef")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#fd6aef");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#fd6aef",bean);
  bean.setName(getI18nFB_7d1923());
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_7d1923(){
 if(context.get("nc.ui.uif2.I18nFB#7d1923")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#7d1923");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#7d1923",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000107");
  bean.setDefaultValue("列表");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#7d1923",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_4e5fcb(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#4e5fcb")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#4e5fcb");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#4e5fcb",bean);
  bean.setUp(getCNode_1f17e59());
  bean.setDown(getCNode_845e59());
  bean.setDividerLocation(25f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_1f17e59(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#1f17e59")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#1f17e59");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#1f17e59",bean);
  bean.setComponent(getCardInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_845e59(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#845e59")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#845e59");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#845e59",bean);
  bean.setName(getI18nFB_19e1156());
  bean.setComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_19e1156(){
 if(context.get("nc.ui.uif2.I18nFB#19e1156")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#19e1156");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#19e1156",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("卡片");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#19e1156",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.uif2.tangramlayout.node.CNode getCardviewnode(){
 if(context.get("cardviewnode")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("cardviewnode");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("cardviewnode",bean);
  bean.setName(getI18nFB_1be6017());
  bean.setComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1be6017(){
 if(context.get("nc.ui.uif2.I18nFB#1be6017")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1be6017");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1be6017",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("卡片");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1be6017",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator(){
 if(context.get("rowNoMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.RowNoMediator)context.get("rowNoMediator");
  nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
  context.put("rowNoMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setRowNoKey("crowno");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.common.validateservice.ClosingCheck getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.pubapp.common.validateservice.ClosingCheck)context.get("ClosingListener");
  nc.ui.pubapp.common.validateservice.ClosingCheck bean = new nc.ui.pubapp.common.validateservice.ClosingCheck();
  context.put("ClosingListener",bean);
  bean.setModel(getManageAppModel());
  bean.setSaveAction(getSaveAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.view.ChildrenFilter getBlankitemfilter(){
 if(context.get("blankitemfilter")!=null)
 return (nc.ui.ct.view.ChildrenFilter)context.get("blankitemfilter");
  nc.ui.ct.view.ChildrenFilter bean = new nc.ui.ct.view.ChildrenFilter();
  context.put("blankitemfilter",bean);
  bean.setFilterMap(getManagedMap3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap3(){  Map map = new HashMap();  map.put("pk_ct_sale_b",getManagedList37());  map.put("pk_ct_sale_term",getManagedList38());  map.put("pk_ct_sale_exp",getManagedList39());  map.put("pk_ct_sale_memora",getManagedList40());  map.put("pk_ct_sale_change",getManagedList41());  map.put("pk_ct_sale_payterm",getManagedList42());  return map;}

private List getManagedList37(){  List list = new ArrayList();  list.add("pk_material");  list.add("pk_marbasclass");  list.add("cunitid");  list.add("nnum");  list.add("nqtorigprice");  list.add("nqtorigtaxprice");  list.add("norigtaxmny");  return list;}

private List getManagedList38(){  List list = new ArrayList();  list.add("vtermcode");  list.add("vtermcontent");  list.add("votherinfo");  list.add("vmemo");  return list;}

private List getManagedList39(){  List list = new ArrayList();  list.add("vexpcode");  list.add("vexpsum");  list.add("vmemo");  return list;}

private List getManagedList40(){  List list = new ArrayList();  list.add("vmemoracode");  list.add("vmemo");  return list;}

private List getManagedList41(){  List list = new ArrayList();  list.add("vchangecode");  return list;}

private List getManagedList42(){  List list = new ArrayList();  list.add("accrate");  list.add("pk_incomeperiod");  list.add("pk_balatype");  return list;}

public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessor(){
 if(context.get("transferViewProcessor")!=null)
 return (nc.ui.pubapp.billref.dest.TransferViewProcessor)context.get("transferViewProcessor");
  nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
  context.put("transferViewProcessor",bean);
  bean.setList(getListView());
  bean.setActionContainer(getActionsOfList());
  bean.setCardActionContainer(getActionsOfCard());
  bean.setSaveAction(getSaveAction());
  bean.setCommitAction(getCommitAction());
  bean.setCancelAction(getCancelAction());
  bean.setBillForm(getBillFormEditor());
  bean.setTransferLogic(getDefaultBillDataLogic_1969b6a());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.billref.dest.DefaultBillDataLogic getDefaultBillDataLogic_1969b6a(){
 if(context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#1969b6a")!=null)
 return (nc.ui.pubapp.billref.dest.DefaultBillDataLogic)context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#1969b6a");
  nc.ui.pubapp.billref.dest.DefaultBillDataLogic bean = new nc.ui.pubapp.billref.dest.DefaultBillDataLogic();
  context.put("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#1969b6a",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getManagedList43(),getManagedList44());  context.put("fractionFixMediator",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList43(){  List list = new ArrayList();  list.add(getBillFormEditor());  return list;}

private List getManagedList44(){  List list = new ArrayList();  list.add(getListView());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator(){
 if(context.get("userdefAndMarAsstCardPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("userdefAndMarAsstCardPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("userdefAndMarAsstCardPreparator",bean);
  bean.setBillDataPrepares(getManagedList45());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList45(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator(){
 if(context.get("userdefAndMarAsstListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefAndMarAsstListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefAndMarAsstListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList46());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList46(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList47());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList47(){  List list = new ArrayList();  list.add(getUserdefQueryParam_b5552b());  list.add(getUserdefQueryParam_ea961c());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_b5552b(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#b5552b")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#b5552b");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#b5552b",bean);
  bean.setMdfullname("ct.ct_sale");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_ea961c(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#ea961c")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#ea961c");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#ea961c",bean);
  bean.setMdfullname("ct.ct_sale_b");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator(){
 if(context.get("userdefitemlistPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemlistPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList48());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList48(){  List list = new ArrayList();  list.add(getUserdefQueryParam_125267b());  list.add(getUserdefQueryParam_19673());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_125267b(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#125267b")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#125267b");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#125267b",bean);
  bean.setMdfullname("ct.ct_sale");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_19673(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#19673")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#19673");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#19673",bean);
  bean.setMdfullname("ct.ct_sale_b");
  bean.setPos(1);
  bean.setTabcode("pk_ct_sale_b");
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
  bean.setModel(getManageAppModel());
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("pk_material");
  bean.setProductorField("cproductorid");
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

public nc.ui.ct.lazilyload.CtCardPanelLazilyLoad getCardLazySupport(){
 if(context.get("cardLazySupport")!=null)
 return (nc.ui.ct.lazilyload.CtCardPanelLazilyLoad)context.get("cardLazySupport");
  nc.ui.ct.lazilyload.CtCardPanelLazilyLoad bean = new nc.ui.ct.lazilyload.CtCardPanelLazilyLoad();
  context.put("cardLazySupport",bean);
  bean.setBillform(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.lazilyload.CtListPanelLazilyLoad getListLazySupport(){
 if(context.get("listLazySupport")!=null)
 return (nc.ui.ct.lazilyload.CtListPanelLazilyLoad)context.get("listLazySupport");
  nc.ui.ct.lazilyload.CtListPanelLazilyLoad bean = new nc.ui.ct.lazilyload.CtListPanelLazilyLoad();
  context.put("listLazySupport",bean);
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator(){
 if(context.get("lasilyLodadMediator")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("lasilyLodadMediator");
  nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
  context.put("lasilyLodadMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setLoader(getBillLazilyLoader());
  bean.setLazilyLoadSupporter(getManagedList49());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList49(){  List list = new ArrayList();  list.add(getCardLazySupport());  list.add(getListLazySupport());  list.add(getLazyActions());  return list;}

public nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getLazyActions(){
 if(context.get("lazyActions")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad)context.get("lazyActions");
  nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
  context.put("lazyActions",bean);
  bean.setModel(getManageAppModel());
  bean.setActionList(getManagedList50());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList50(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  return list;}

public nc.ui.uif2.components.pagination.PaginationBar getPageBar(){
 if(context.get("pageBar")!=null)
 return (nc.ui.uif2.components.pagination.PaginationBar)context.get("pageBar");
  nc.ui.uif2.components.pagination.PaginationBar bean = new nc.ui.uif2.components.pagination.PaginationBar();
  context.put("pageBar",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator getPageDelegator(){
 if(context.get("pageDelegator")!=null)
 return (nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator)context.get("pageDelegator");
  nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator bean = new nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator(getManageAppModel());  context.put("pageDelegator",bean);
  bean.setPaginationQuery(getPageQuery());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.model.SaledailyModelPageService getPageQuery(){
 if(context.get("pageQuery")!=null)
 return (nc.ui.ct.saledaily.model.SaledailyModelPageService)context.get("pageQuery");
  nc.ui.ct.saledaily.model.SaledailyModelPageService bean = new nc.ui.ct.saledaily.model.SaledailyModelPageService();
  context.put("pageQuery",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageMediator getPageMediator(){
 if(context.get("pageMediator")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageMediator)context.get("pageMediator");
  nc.ui.scmpub.page.model.SCMBillPageMediator bean = new nc.ui.scmpub.page.model.SCMBillPageMediator();
  context.put("pageMediator",bean);
  bean.setListView(getListView());
  bean.setRecordInPage(10);
  bean.setCachePages(10);
  bean.setPageDelegator(getPageDelegator());
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ct.saledaily.view.SaleHistiryListForm getListViewHistory(){
 if(context.get("listViewHistory")!=null)
 return (nc.ui.ct.saledaily.view.SaleHistiryListForm)context.get("listViewHistory");
  nc.ui.ct.saledaily.view.SaleHistiryListForm bean = new nc.ui.ct.saledaily.view.SaleHistiryListForm();
  context.put("listViewHistory",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionEnable(true);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.tangramlayout.node.CNode getListviewhistornode(){
 if(context.get("listviewhistornode")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("listviewhistornode");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("listviewhistornode",bean);
  bean.setComponent(getListViewHistory());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.listener.crossrule.CrossRuleMediator getCrossRuleMediator(){
 if(context.get("crossRuleMediator")!=null)
 return (nc.ui.scmpub.listener.crossrule.CrossRuleMediator)context.get("crossRuleMediator");
  nc.ui.scmpub.listener.crossrule.CrossRuleMediator bean = new nc.ui.scmpub.listener.crossrule.CrossRuleMediator();
  context.put("crossRuleMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("Z3");
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
