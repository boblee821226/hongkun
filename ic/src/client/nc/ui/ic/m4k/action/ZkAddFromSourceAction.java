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
 * �������ε��ݣ�����ת�ⵥ
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
	 * ��������������Ϊ�������������������������˲���
	 */
	public void addFromSource(AggregatedValueObject[] retvos) {
		// �ָ���Ƭ����״̬�����մ򿪽ڵ���桱ʱ�ĳ�ʼ״̬
		this.getEditorModel().getICBizView().resetCardEditFlag();
//		AggregatedValueObject[] bills = this.processRowNO(this
//				.processAfterVoChange(retvos));
		this.getTransferViewProcessor().processBillTransfer(retvos);
		this.afterProcessor();
	}

	/**
	 * ���´����кš�ԭ��ת��֮��ע�������ݽ���������ĳ������ϵ�ǰ���������кţ��ϵ������к��ظ�
	 */
	protected AggregatedValueObject[] processRowNO(
			AggregatedValueObject[] retvos) {
		if (ValueCheckUtil.isNullORZeroLength(retvos))
			return retvos;

		for (AggregatedValueObject bill : retvos) {
			// �����к�
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
		// ���÷����иı�
		PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());

		if (PfUtilClient.isCloseOK()) {
			// �����Ʒ��Ϣ
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
	 * ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵�����, 1�����ݽӿڶ��壩�� 2�������������ã���-1�������ݽ������ͷ��飩 �����踴д
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
		// ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
		context.setCurrBilltype(this.getCurrBilltype());
		context.setUserObj(this.getPfUserObj());
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
		// ���εĽ������ͼ���
		context.setTransTypes(this.getTranstypes());
		// ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
		// 2�������������ã���-1�������ݽ������ͷ��飩
		context.setClassifyMode(this.getPfButtonClickContext());
		return context;
	}

	/**
	 * ��������������ת�����������õ�������һЩUI���� ������Դ����Լ���һЩUI�߼�
	 */
	protected void afterProcessor() {
		// ����֯�л��󣬳�ʼ��Ĭ������,�����þ���
//		this.getEditorModel().getGenUIProcessorInfo().getAddProcessor()
//				.onAdd(this.editorModel);
//		// ���ÿ�Ƭ�����ͷ�ͱ����ֶεĲ��ɱ༭��
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
	 * ��õ�ǰ�������ͻ�������
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
	 * ������������������childButtonClickedʱ�����õ�һ������UserObj�� ������Է�����Ҫ�Ĳ����������������Ϊ�ա�
	 */
	protected Object getPfUserObj() {
		return null;
	}

	@Override
	protected boolean isActionEnable() {
		return this.getModel().getAppUiState() == AppUiState.NOT_EDIT;
	}

	/**
	 * ��������������vo������ҵ�������ദ��
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
