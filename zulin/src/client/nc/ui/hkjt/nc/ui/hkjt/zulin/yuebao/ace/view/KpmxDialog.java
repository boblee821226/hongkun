package nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBVO;

/**
 * 开票明细-收款单联查
 */
public class KpmxDialog extends UIDialog {

  private static final int LENGTH = 530;

  private static final long serialVersionUID = -3279462704332018554L;

  private static final int WIDTH1 = 800;

  private UIButton btn;

  private BillCardPanel cardPanel = new BillCardPanel();

  public KpmxDialog(Container container,Object data) {

    super(container, true);
    this.setResizable(true);
    this.setLayout(new BorderLayout());

    // 加载单据模板
    this.loadPanel();

    // 信息面板
    this.add(this.cardPanel, BorderLayout.CENTER);
    this.setSize(KpmxDialog.WIDTH1, KpmxDialog.LENGTH);
    this.setTitle("开票明细");

    // 按钮面板
    UIPanel btnPanel = new UIPanel();
    btnPanel.add(this.getUIButton1(), BorderLayout.EAST);
    this.add(btnPanel, BorderLayout.SOUTH);
    
    // 添加数据
    initDate(data);
  }

  /**
   * 初始化按钮
   * 
   * @return
   */
  private Component getUIButton1() {
    if (this.btn == null) {
      this.btn = new nc.ui.pub.beans.UIButton();
      this.btn.setName("UIButton");
      this.btn.setText("确定");
      this.btn.addActionListener(new ActionListener() {

        @SuppressWarnings("synthetic-access")
        @Override
        public void actionPerformed(ActionEvent e) {
          KpmxDialog.this.close();
        }
      });
    }
    return this.btn;
  }

  /**
   * 加载数据
   * 
   * @param bcq
   */
  private void initDate(Object data) {
    this.cardPanel.getBillData().setBodyValueVO((BillSkFpBVO[])data);
    this.cardPanel.getBillData().getBillModel().loadLoadRelationItemValue();	// 参照翻译
  }

  /**
   * 加载信用信息单据模板
   */
  private void loadPanel() {
    this.cardPanel.loadTemplet("1001ZZ100000006O2VIS");	// 开票明细-联查
    this.cardPanel.setEnabled(false);
    this.cardPanel.setTatolRowShow(true);	// 显示合计行
//    this.cardPanel.getBodyPanel().validate();	// 刷新模板
  }

}
