package nc.ui.hkjt.huiyuan.kainfo.ace.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_kainfoMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.tools.BannerDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pa.CurrEnvVO;
import nc.vo.pubapp.AppContext;

public class QushuAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5018670381058176398L;

	public QushuAction() {
		setBtnName("��Ա��ȡ��");
		setCode("qushuAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;

	public ShowUpableBillListView getListview() {
		return listview;
	}

	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	private IHy_kainfoMaintain ITF = (IHy_kainfoMaintain) NCLocator
			.getInstance().lookup(IHy_kainfoMaintain.class.getName());

	@Override
	public void doAction(ActionEvent e) throws Exception {
		ArrayList<String> corpList = new ArrayList<>();
		corpList.add("0001N510000000001SXV");	//����
		corpList.add("0001N510000000001SXX");	//ĵ��
		corpList.add("0001N510000000001SY7");	//��ɽ
		corpList.add("0001N510000000001SY1");	//"�Ƶ�"
		corpList.add("0001N510000000001SY3");	//"������"
		corpList.add("0001N510000000001SY5");	//"������"
//		UFDate busiDate = AppContext.getInstance().getBusiDate();
//		UFDate nowDate = new UFDate();
//		UFDate queryDate = busiDate;
//		if (nowDate.compareTo(busiDate) <= 0 ) {
//			// �����ǰ���� С�ڵ��� ��¼���ڣ��� ��ѯ���� ȡ ��ǰ���ڵ�ǰһ�졣
//			queryDate = nowDate.getDateBefore(1);
//		}
		// ������֯
		BillItem corpItem = new BillItem();
		corpItem.setName("��֯");
		corpItem.setKey("corp");
		corpItem.setDataType(IBillItem.UFREF);
		corpItem.setRefType("������֯");
		corpItem.setNull(true); // �Ƿ� �ǿ�
		corpItem.setEdit(true);
		UIRefPane corpRef = (UIRefPane)corpItem.getComponent();	// ��ÿؼ�������ת���ɲ���
		corpRef.setWhereString(" pk_financeorg in " + (PuPubVO.getSqlInByList(corpList)) + " ");// ��������
		corpRef.setMultiSelectedEnabled(true);
		// ��ʼ����
		BillItem bdateItem = new BillItem();
		bdateItem.setName("��ʼ����");
		bdateItem.setKey("bdate");
		bdateItem.setDataType(IBillItem.UFREF);
		bdateItem.setRefType("����");
		bdateItem.setNull(false);
//		bdateItem.setValue(queryDate);
		bdateItem.setEdit(true);
		// �������ڣ��Զ�ȡ��ǰ���ڵ�ǰһ�죩
		BillItem edateItem = new BillItem();
		edateItem.setName("��������");
		edateItem.setKey("edate");
		edateItem.setDataType(IBillItem.UFREF);
		edateItem.setRefType("����");
		edateItem.setNull(false);
//		edateItem.setValue(queryDate);
		edateItem.setEdit(true);
		// ͬ������
		BillItem iskxItem = new BillItem();
		iskxItem.setName("ͬ������");
		iskxItem.setKey("iskx");
		iskxItem.setDataType(IBillItem.BOOLEAN);
		iskxItem.setValue(true);
		iskxItem.setEdit(true);
		// ͬ������
		BillItem iskaItem = new BillItem();
		iskaItem.setName("ͬ������");
		iskaItem.setKey("iska");
		iskaItem.setDataType(IBillItem.BOOLEAN);
		iskaItem.setValue(true);
		iskaItem.setEdit(true);
		// ͬ������Ϣ
		BillItem iskainfoItem = new BillItem();
		iskainfoItem.setName("ͬ������Ϣ");
		iskainfoItem.setKey("iskainfo");
		iskainfoItem.setDataType(IBillItem.BOOLEAN);
		iskainfoItem.setValue(true);
		iskainfoItem.setEdit(true);
		// ͬ��������Ϣ
		BillItem ishuankaItem = new BillItem();
		ishuankaItem.setName("ͬ��������Ϣ");
		ishuankaItem.setKey("ishuanka");
		ishuankaItem.setDataType(IBillItem.BOOLEAN);
		ishuankaItem.setValue(true);
		ishuankaItem.setEdit(true);
		// ͬ���ο���Ϣ
		BillItem iscikainfoItem = new BillItem();
		iscikainfoItem.setName("ͬ���ο���Ϣ");
		iscikainfoItem.setKey("iscikainfo");
		iscikainfoItem.setDataType(IBillItem.BOOLEAN);
		iscikainfoItem.setValue(true);
		iscikainfoItem.setEdit(true);

		PubBatchEditDialog dlg = new PubBatchEditDialog(
				getEditor().getParent(), new BillItem[] { corpItem, bdateItem,
						edateItem, iskxItem, iskaItem, iskainfoItem,
						ishuankaItem, iscikainfoItem });
		dlg.setTitle("ѡ��");

		if (UIDialog.ID_OK != dlg.showModal())
			return;

		final CurrEnvVO context = new CurrEnvVO();
		context.getKeyMap().put("bdate", bdateItem.getValueObject());	// ��ʼ����
		context.getKeyMap().put("edate", edateItem.getValueObject());	// ��������
		context.getKeyMap().put("iskx", iskxItem.getValueObject());		// ͬ������
		context.getKeyMap().put("iska", iskaItem.getValueObject());		// ͬ������
		context.getKeyMap().put("iskainfo", iskainfoItem.getValueObject());	// ͬ������Ϣ
		context.getKeyMap().put("ishuanka", ishuankaItem.getValueObject());	// ͬ��������Ϣ
		context.getKeyMap().put("iscikainfo", iscikainfoItem.getValueObject());// ͬ���ο���Ϣ
		context.setPk_orgs(corpRef.getRefPKs());// ��֯
		
		Runnable checkRun = new Runnable() {
			public void run() {
				// �̶߳Ի���ϵͳ������ʾ��
				BannerDialog dialog = new BannerDialog(getEditor().getParent());
				dialog.start();
				try {
					// Thread.sleep(2000);
					// TODO
					ITF.execHuiyuanPlugin(context, null, null);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// ����ϵͳ������ʾ��
					dialog.end();
					MessageDialog.showWarningDlg(getEditor().getParent(), "",
							"��Ա��ȡ����ɣ����ѯ��");
				}
			}
		};
		// �����߳�
		new Thread(checkRun).start();
	}

}