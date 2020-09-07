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
		setBtnName("会员卡取数");
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
		corpList.add("0001N510000000001SXV");	//国际
		corpList.add("0001N510000000001SXX");	//牡丹
		corpList.add("0001N510000000001SY7");	//西山
		corpList.add("0001N510000000001SY1");	//"酒店"
		corpList.add("0001N510000000001SY3");	//"朗丽兹"
		corpList.add("0001N510000000001SY5");	//"康福瑞"
//		UFDate busiDate = AppContext.getInstance().getBusiDate();
//		UFDate nowDate = new UFDate();
//		UFDate queryDate = busiDate;
//		if (nowDate.compareTo(busiDate) <= 0 ) {
//			// 如果当前日期 小于等于 登录日期，则 查询日期 取 当前日期的前一天。
//			queryDate = nowDate.getDateBefore(1);
//		}
		// 财务组织
		BillItem corpItem = new BillItem();
		corpItem.setName("组织");
		corpItem.setKey("corp");
		corpItem.setDataType(IBillItem.UFREF);
		corpItem.setRefType("财务组织");
		corpItem.setNull(true); // 是否 非空
		corpItem.setEdit(true);
		UIRefPane corpRef = (UIRefPane)corpItem.getComponent();	// 获得控件，并且转换成参照
		corpRef.setWhereString(" pk_financeorg in " + (PuPubVO.getSqlInByList(corpList)) + " ");// 过滤条件
		corpRef.setMultiSelectedEnabled(true);
		// 开始日期
		BillItem bdateItem = new BillItem();
		bdateItem.setName("开始日期");
		bdateItem.setKey("bdate");
		bdateItem.setDataType(IBillItem.UFREF);
		bdateItem.setRefType("日历");
		bdateItem.setNull(false);
//		bdateItem.setValue(queryDate);
		bdateItem.setEdit(true);
		// 结束日期（自动取当前日期的前一天）
		BillItem edateItem = new BillItem();
		edateItem.setName("结束日期");
		edateItem.setKey("edate");
		edateItem.setDataType(IBillItem.UFREF);
		edateItem.setRefType("日历");
		edateItem.setNull(false);
//		edateItem.setValue(queryDate);
		edateItem.setEdit(true);
		// 同步卡型
		BillItem iskxItem = new BillItem();
		iskxItem.setName("同步卡型");
		iskxItem.setKey("iskx");
		iskxItem.setDataType(IBillItem.BOOLEAN);
		iskxItem.setValue(true);
		iskxItem.setEdit(true);
		// 同步开卡
		BillItem iskaItem = new BillItem();
		iskaItem.setName("同步开卡");
		iskaItem.setKey("iska");
		iskaItem.setDataType(IBillItem.BOOLEAN);
		iskaItem.setValue(true);
		iskaItem.setEdit(true);
		// 同步卡信息
		BillItem iskainfoItem = new BillItem();
		iskainfoItem.setName("同步卡信息");
		iskainfoItem.setKey("iskainfo");
		iskainfoItem.setDataType(IBillItem.BOOLEAN);
		iskainfoItem.setValue(true);
		iskainfoItem.setEdit(true);
		// 同步换卡信息
		BillItem ishuankaItem = new BillItem();
		ishuankaItem.setName("同步换卡信息");
		ishuankaItem.setKey("ishuanka");
		ishuankaItem.setDataType(IBillItem.BOOLEAN);
		ishuankaItem.setValue(true);
		ishuankaItem.setEdit(true);
		// 同步次卡信息
		BillItem iscikainfoItem = new BillItem();
		iscikainfoItem.setName("同步次卡信息");
		iscikainfoItem.setKey("iscikainfo");
		iscikainfoItem.setDataType(IBillItem.BOOLEAN);
		iscikainfoItem.setValue(true);
		iscikainfoItem.setEdit(true);

		PubBatchEditDialog dlg = new PubBatchEditDialog(
				getEditor().getParent(), new BillItem[] { corpItem, bdateItem,
						edateItem, iskxItem, iskaItem, iskainfoItem,
						ishuankaItem, iscikainfoItem });
		dlg.setTitle("选择");

		if (UIDialog.ID_OK != dlg.showModal())
			return;

		final CurrEnvVO context = new CurrEnvVO();
		context.getKeyMap().put("bdate", bdateItem.getValueObject());	// 开始日期
		context.getKeyMap().put("edate", edateItem.getValueObject());	// 结束日期
		context.getKeyMap().put("iskx", iskxItem.getValueObject());		// 同步卡型
		context.getKeyMap().put("iska", iskaItem.getValueObject());		// 同步开卡
		context.getKeyMap().put("iskainfo", iskainfoItem.getValueObject());	// 同步卡信息
		context.getKeyMap().put("ishuanka", ishuankaItem.getValueObject());	// 同步换卡信息
		context.getKeyMap().put("iscikainfo", iscikainfoItem.getValueObject());// 同步次卡信息
		context.setPk_orgs(corpRef.getRefPKs());// 组织
		
		Runnable checkRun = new Runnable() {
			public void run() {
				// 线程对话框：系统运行提示框
				BannerDialog dialog = new BannerDialog(getEditor().getParent());
				dialog.start();
				try {
					// Thread.sleep(2000);
					// TODO
					ITF.execHuiyuanPlugin(context, null, null);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 销毁系统运行提示框
					dialog.end();
					MessageDialog.showWarningDlg(getEditor().getParent(), "",
							"会员卡取数完成，请查询。");
				}
			}
		};
		// 启用线程
		new Thread(checkRun).start();
	}

}