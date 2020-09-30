package nc.ui.ic.m4k.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.ic.general.util.GenUIUtil;
import nc.ui.ic.pub.util.CardPanelWrapper;
import nc.ui.ic.special.model.ICSpeBizEditorModel;
import nc.ui.ic.special.model.ICSpeBizModel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.vo.ic.general.deal.TransBillBaseProcess;
import nc.vo.ic.general.define.ICBillVO;
//import nc.vo.ic.general.define.ICBillBodyVO;
//import nc.vo.ic.general.define.ICBillHeadVO;
//import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.m4k.entity.WhsTransBillVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.util.VORowNoUtils;

/**
 * 参照上游单据，生成转库单
 */
public class ZkAddFromSourceAction extends AbstractReferenceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1687111454668005068L;

	private AddAction addAction;

	private String destBillType;

	private ICSpeBizEditorModel editorModel;

	private ShowUpableBillListView list;

	private int pfButtonClickContext = -1;

	/**
	 * 方法功能描述：为其它入其他出公共框服务，提出此部分
	 */
	public void addFromSource(AggregatedValueObject[] retvos) {
		// 恢复卡片界面状态到“刚打开节点界面”时的初始状态
		this.getEditorModel().getICBizView().resetCardEditFlag();
//		AggregatedValueObject[] bills = this.processRowNO(this
//				.processAfterVoChange(retvos));
		this.getTransferViewProcessor().processBillTransfer(retvos);
		this.afterProcessor();
	}

	/**
	 * 重新处理行号。原因：转单之后，注册了数据交换后处理类的场景，合单前按单补了行号，合单后导致行号重复
	 */
	protected AggregatedValueObject[] processRowNO(
			AggregatedValueObject[] retvos) {
		if (ValueCheckUtil.isNullORZeroLength(retvos))
			return retvos;

		for (AggregatedValueObject bill : retvos) {
			// 设置行号
			CircularlyAccessibleValueObject[] bodys = bill.getChildrenVO();
			if (ValueCheckUtil.isNullORZeroLength(bodys))
				continue;
			for (CircularlyAccessibleValueObject body : bodys) {
				body.setAttributeValue(ICPubMetaNameConst.CROWNO, null);
			}
			VORowNoUtils.setVOsRowNoByRule(bodys, ICPubMetaNameConst.CROWNO);
		}
		return retvos;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
//		this.getEditorModel().setTempBillPK(null);
		// 调用方法有改变
		PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());

		if (PfUtilClient.isCloseOK()) {
			// 清除单品信息
			this.getEditorModel().clearAllBackupData();
			AggregatedValueObject[] retvos = PfUtilClient.getRetVos();
			if (retvos == null || retvos.length <= 0) {
				return;
			}
			this.addFromSource(retvos);
		}
	}

	public AddAction getAddAction() {
		return this.addAction;
	}

	public String getDestBillType() {
		return this.destBillType;
	}

	public ICSpeBizEditorModel getEditorModel() {
		return this.editorModel;
	}

	public ShowUpableBillListView getList() {
		return this.list;
	}

	/**
	 * 标志在交换根据目的交易类型分组时，查找目的交易类型的依据, 1（根据接口定义）、 2（根据流程配置）、-1（不根据交易类型分组） 子类需复写
	 */
	public int getPfButtonClickContext() {
		return this.pfButtonClickContext;
	}

	public void setAddAction(AddAction addAction) {
		this.addAction = addAction;
	}

	public void setDestBillType(String destBillType) {
		this.destBillType = destBillType;
	}

	public void setEditorModel(ICSpeBizEditorModel editorModel) {
		this.editorModel = editorModel;
	}

	public void setList(ShowUpableBillListView list) {
		this.list = list;
	}

	public void setPfButtonClickContext(int pfButtonClickContext) {
		this.pfButtonClickContext = pfButtonClickContext;
	}

	private PfButtonClickContext createPfButtonClickContext() {
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		// 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
		context.setCurrBilltype(this.getCurrBilltype());
		context.setUserObj(this.getPfUserObj());
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
		// 上游的交易类型集合
		context.setTransTypes(this.getTranstypes());
		// 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
		// 2（根据流程配置）、-1（不根据交易类型分组）
		context.setClassifyMode(this.getPfButtonClickContext());
		return context;
	}

	/**
	 * 方法功能描述：转单将数据设置到界面后的一些UI处理。 子类可以处理自己的一些UI逻辑
	 */
	protected void afterProcessor() {
		// 主组织切换后，初始化默认数据,并设置精度
//		this.getEditorModel().getGenUIProcessorInfo().getAddProcessor()
//				.onAdd(this.editorModel);
//		// 设置卡片界面表头和表体字段的不可编辑性
//		this.getEditorModel().getGenUIProcessorInfo()
//				.getBillItemEditProcessor()
//				.procEditableWhenRefAdd(this.getEditorModel());

		CardPanelWrapper cpw = this.getEditorModel().getCardPanelWrapper();

		if (cpw.getRowCount(null) == 0) {
			return;
		}
	}

	protected String[] getAllHeadUnableItems() {
		return null;
	}

	/**
	 * 获得当前单据类型或交易类型
	 */
	protected String getCurrBilltype() {
		String transtypecode = TrantypeFuncUtils.getTrantype(this.getModel()
				.getContext());
		if (StringUtil.isSEmptyOrNull(transtypecode)) {
			return this.getDestBillType();
		}
		return transtypecode;
	}

	protected ICSpeBizModel getModel() {
		return (ICSpeBizModel) this.getEditorModel().getICBizView()
				.getICModel();
	}

	/**
	 * 方法功能描述：调用childButtonClicked时可设置的一个参数UserObj， 子类可以返回需要的参数，这里仅仅处理为空。
	 */
	protected Object getPfUserObj() {
		return null;
	}

	@Override
	protected boolean isActionEnable() {
		return this.getModel().getAppUiState() == AppUiState.NOT_EDIT;
	}

	/**
	 * 方法功能描述：vo交换后业务处理。子类处理
	 */
	protected AggregatedValueObject[] processAfterVoChange(
			AggregatedValueObject[] retvos) {
//		this.setClientDbilldate(retvos);
//		TransBillBaseProcess<ICBillVO, ICBillHeadVO, ICBillBodyVO> process = new TransBillBaseProcess<ICBillVO, ICBillHeadVO, ICBillBodyVO>(
//				false, false);
//		GenUIUtil.initTransBillBaseProcess(process, this.getEditorModel()
//				.getContext());
//		return process.processBillVOs((WhsTransBillVO[]) retvos);
		return null;
	}

	protected void setClientDbilldate(AggregatedValueObject[] retvos) {
		for (AggregatedValueObject retvo : retvos) {
			WhsTransBillVO billvo = (WhsTransBillVO) retvo;
			billvo.getHead().setDbilldate(
					this.getEditorModel().getContext().getBizDate());
		}
	}

	private static Set<String> arrayToSet(String[] stra) {
		Set<String> strs = new HashSet<String>();
		CollectionUtils.addArrayToSet(strs, stra);
		return strs;
	}

}
