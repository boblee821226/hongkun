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
 * ��֯ѡ�����
 * <p>
 * �ܶ൥�ݽ����ϲ�������һ��ѡ����֯�Ĳ��գ������ʵ������֯����ѡ��� ״̬�����Լ�LoginContext����֯���Ե�����
 * 
 * @author ��ǿ��
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
    this.getRefPane().setEnabled(false);	// ����Ϊ���ɱ༭
  }

  public void setDataManager(IAppModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  @Override
  public void setModel(AbstractUIAppModel model) {
    super.setModel(model);

    // ���ڵ����͵Ľڵ㣬�ڱ༭״̬����֯����ǲ��ɱ༭��
    model.addAppEventListener(new AppEventListener() {
      @Override
      public void handleEvent(AppEvent event) {
        if (AppEventConst.UISTATE_CHANGED == event.getType()) {
          if (event instanceof AppUiStateChangeEvent) {
            OrgPanel.this.handleUIStateChangedEvent((AppUiStateChangeEvent) event);
          }
          else {
            // ��Ӧ�ûᷢ������Ϊ��AppModelExDelegate�Ĵ���
            ExceptionUtils.wrappBusinessException("event ���� AppUiStateChangeEvent ����");/*-=notranslate=-*/
          }
        }
      }
    });

    // �����ͽڵ㣬��֯�仯����Ҫ���ݵ�ǰ��֯���³�ʼ������
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
   * ��ʼ��Ĭ����֯
   */
  protected void initDefaultOrg() {
    if (this.getModel().getContext().getPk_org() != null) {
      // ֱ�����ò��յ�ֵ,�޷�������֯�ı��¼�,�޸�ΪsetPkOrg����
      // this.getRefPane().setPK(this.getModel().getContext().getPk_org());
      this.setPkOrg(this.getModel().getContext().getPk_org());
    }
  }

}
