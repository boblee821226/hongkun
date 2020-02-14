package nc.ui.arap.viewhandler.cardbefore;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.ui.arap.viewhandler.AbstractBillHandler;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.bd.ref.model.PsndocDefaultNCRefModel;
import nc.ui.bd.ref.model.PsndocDefaultRefModel;
import nc.ui.fipub.ref.FiRefModelUtil;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.arap.basebill.BaseBillVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFLiteralDate;

public class BodyPsnDocBeforeEditHandler extends AbstractBillHandler <CardBodyBeforeEditEvent> {

	public static final List<String> escapes = Arrays.asList(new String[] {
			IBillFieldGet.PU_PSNDOC, IBillFieldGet.SO_PSNDOC,IBillFieldGet.PK_PSNDOC,
			IBillFieldGet.PK_DEPTID,IBillFieldGet.PK_DEPTID_V,
			IBillFieldGet.SO_DEPTID,IBillFieldGet.SO_DEPTID_V,IBillFieldGet.PU_DEPTID,IBillFieldGet.PU_DEPTID_V
			/**
			 * HK 2020��2��14��12:57:39
			 * ������������֧��Ŀ�Ĺ��ˡ��� �տں�ͬ ��ͷ ��֧��Ŀ һ��
			 */
			,"pk_subjcode"
			,"def11"
			/***END***/
	});
	
	@Override
	protected Collection<String> getFilterKey() {
		return escapes;
	}

	@Override
	protected void handle() {
		String key =  getKey();
		BillCardPanel bcp = getBillCardPanel();
		List<String> busiorgFields = BaseBillVO.BUSIORG_FIELD_LIST;
		BillItem billItem = bcp.getBodyItem(key);
		BillItem billDateItem = bcp.getHeadItem(IBillFieldGet.BILLDATE);
		BillItem isinitItem = bcp.getHeadItem(IBillFieldGet.ISINIT);
		boolean isinit = (boolean)isinitItem.getValueObject();
		if(busiorgFields.contains(key)){
			AbstractRefModel filterModel = ((UIRefPane)billItem.getComponent()).getRefModel();
			FiRefModelUtil.setBusiFuncode(filterModel);
			if(IBillFieldGet.SO_PSNDOC.equals(key) 
					||IBillFieldGet.PU_PSNDOC.equals(key)){
				//������Ա�����������ְ��ԱcheckBox�������ʾ�����Ҹ��������õ�ǰ��������
				filterLeavePowerShowAndUI(billItem,billDateItem,isinit,false);
			}
		}else if(IBillFieldGet.PK_PSNDOC.equals(key)){
			//����ҵ��Ա���չ���ί�к�����֯
			UIRefPane refpane = (UIRefPane)billItem.getComponent();
			FiRefModelUtil.setFiRelation(refpane);
			refpane.getRefModel().setPk_org((String)getHeadValue(IBillFieldGet.PK_ORG));
			FiRefModelUtil.setFilter(refpane);
			//������Ա�����������ְ��ԱcheckBox�������ʾ�����Ҹ��������õ�ǰ�������ڣ��ڳ���������
			filterLeavePowerShowAndUI(billItem,billDateItem,isinit,false);
		}else if(IBillFieldGet.PK_DEPTID.equals(key) 
			||IBillFieldGet.PK_DEPTID_V.equals(key) ){
			UIRefPane refpane = (UIRefPane)billItem.getComponent();
			FiRefModelUtil.setFiRelation(refpane);
			refpane.getRefModel().setPk_org((String)getHeadValue(IBillFieldGet.PK_ORG));
			FiRefModelUtil.setFilter(refpane);
		}
		/**
		 * HK 2020��2��14��12:57:39
		 * ������������֧��Ŀ�Ĺ��ˡ��� �տں�ͬ ��ͷ ��֧��Ŀ һ��
		 * ֻ�� ������ �����á�
		 */
		else if(
		   "pk_subjcode".equals(key)
		|| "def11".equals(key)
		) {
			Object billType = bcp.getHeadItem("pk_tradetypeid").getValueObject();
			if ("1001N510000000BAUFLV".equals(billType)) {
				UIRefPane refpane = (UIRefPane)billItem.getComponent();
				refpane.setWhereString(" ((innercode like '866S__%' or innercode like 'YKGL__%') and enablestate = 2) ");
				refpane.getRefModel().setNotLeafSelectedEnabled(false);
			}
		}
		/***END***/
	}
	
	/**
	 * ��ְ��Ա��ʾ����Ƿ���ʾ
	 * @param billItem
	 * @param billDateItem
	 * @param isinit
	 * @param showLeavePowerAndUI
	 */
	public void filterLeavePowerShowAndUI(BillItem billItem , BillItem billDateItem,boolean isinit, boolean showLeavePowerAndUI){
		if(isinit){
			return;
		}
		if (billItem != null && billItem.getComponent() instanceof UIRefPane && ((UIRefPane) billItem.getComponent()).getRefModel() != null) {
			UIRefPane refPane = (UIRefPane) billItem.getComponent();
			if (refPane.getRefModel() instanceof PsndocDefaultNCRefModel) {
				PsndocDefaultRefModel psndocDefaultRefModel = (PsndocDefaultRefModel) refPane.getRefModel();
				psndocDefaultRefModel.setLeavePowerUI(showLeavePowerAndUI);
				psndocDefaultRefModel.setLeavePower(showLeavePowerAndUI);
				
				if (!(showLeavePowerAndUI)) {
					psndocDefaultRefModel.setUiControlComponentClassName(null);
				}
				if(billDateItem != null  ){
					if(billDateItem.getValueObject() !=  null && billDateItem.getValueObject() instanceof UFDate){
						UFDate billDate = (UFDate)billDateItem.getValueObject(); 
						UFLiteralDate nowDate = new UFLiteralDate(billDate.toDate());
						//����ҵ�����ڹ����ڴ����ڷ�Χ����ְ����Ա��
						psndocDefaultRefModel.setNowDate(nowDate);
					}
				}
				psndocDefaultRefModel.reset();
			}
		}
	}
}
