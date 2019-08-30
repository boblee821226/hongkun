package nc.ui.hkjt.hg_rsbaogao_tz.ace.view;

import nc.ui.pubapp.uif2app.event.AppUiStateChangeEvent;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.BaseOrgPanel;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.AppEventConst;
import nc.ui.uif2.model.IAppModelDataManager;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 组织选择面板
 * <p>
 * 很多单据界面上部都会有一个选择组织的参照，此面板实现了组织参照选择和 状态控制以及LoginContext中组织属性的设置
 * 
 * @author 蒲强华
 * @since 2009-5-6
 */
public class OrgPanel extends BaseOrgPanel {
  private static final long serialVersionUID = 5232628868540352261L;

  private IAppModelDataManager dataManager;

  public IAppModelDataManager getDataManager() {
    return this.dataManager;
  }

  @Override
  public void initUI() {
//    super.initUI();
//    this.initDefaultOrg();
    this.getRefPane().setEditable(false);
    this.getRefPane().setEnabled(false);	// 设置为不可编辑
  }

  public void setDataManager(IAppModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  @Override
  public void setModel(AbstractUIAppModel model) {
    super.setModel(model);

    // 对于档案型的节点，在编辑状态下组织面板是不可编辑的
    model.addAppEventListener(new AppEventListener() {
      @Override
      public void handleEvent(AppEvent event) {
        if (AppEventConst.UISTATE_CHANGED == event.getType()) {
          if (event instanceof AppUiStateChangeEvent) {
            OrgPanel.this.handleUIStateChangedEvent((AppUiStateChangeEvent) event);
          }
          else {
            // 不应该会发生，因为有AppModelExDelegate的处理
            ExceptionUtils.wrappBusinessException("event 不是 AppUiStateChangeEvent 类型");/*-=notranslate=-*/
          }
        }
      }
    });

    // 档案型节点，组织变化后需要根据当前组织重新初始化数据
    ((IAppModelEx) model).addAppEventListener(OrgChangedEvent.class, new IAppEventHandler<OrgChangedEvent>() {
      @Override
      public void handleAppEvent(OrgChangedEvent e) {
        OrgPanel.this.handleOrgChangedEvent(e);
      }

    });
  }

  /**
   * @param e
   */
  protected void handleOrgChangedEvent(OrgChangedEvent e) {
    String pk_org = this.getRefPane().getRefPK();
    this.getModel().getContext().setPk_org(pk_org);
    if (pk_org != null) {
      this.getDataManager().initModel();
    }
  }

  /**
   * @param event
   */
  protected void handleUIStateChangedEvent(AppUiStateChangeEvent event) {
    if (this.getModel().getUiState() == UIState.ADD || this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.DISABLE) {
      this.getRefPane().setEnabled(false);
    }
    else {
      this.getRefPane().setEnabled(true);
    }
  }

  /**
   * 初始化默认组织
   */
  protected void initDefaultOrg() {
    if (this.getModel().getContext().getPk_org() != null) {
      // 直接设置参照的值,无法发出组织改变事件,修改为setPkOrg方法
      // this.getRefPane().setPK(this.getModel().getContext().getPk_org());
      this.setPkOrg(this.getModel().getContext().getPk_org());
    }
  }

}
