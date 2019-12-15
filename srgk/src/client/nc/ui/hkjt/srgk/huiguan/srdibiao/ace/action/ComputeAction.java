package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHG_hzshujuMaintain;
import nc.itf.hkjt.IJd_hzshujuMaintain;
import nc.ui.hkjt.srgk.huiguan.srdibiao.ace.dlg.QueryTempMainOrgFilterBaseDoc;
import nc.ui.hkjt.srgk.huiguan.srdibiao.ace.view.ShowUpableBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.value.IFieldValue;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.ui.querytemplate.value.RefValueObject;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.querytemplate.TemplateInfo;

public class ComputeAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915395509783382608L;

	public ComputeAction() {
		setBtnName("����");
		setCode("computeAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;

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
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// ������ѡ��
		QueryConditionDLGDelegator dlg = getDlgDelegator();

		if (1 == dlg.showModal()) {
			// ��ȡ��֯����
			String pk_org  = getValueForColumn(dlg, "pk_org", true, false);
			String pk_dept = getValueForColumn(dlg, "pk_dept", true, false);
			String hzdate  = getValueForColumn(dlg, "hzdate", true, true);
			String isjd    = getValueForColumn(dlg, "isjd", false, false);	// �Ƿ�Ƶ� ��HK 2018��11��5��20:46:42��
			// ��ʼʱ�����ʱ���жϴ���
			handlerDate(hzdate);
			// У�������ѡ���˻����ѡ�˾Ƶ���ô���ܼ���
			checkHaveJdAndHgInfo(pk_org);
			// ��������ױ�
			if("Y".equals(isjd) && HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org.split(",")[0])){	// �Ƿ�Ƶ� ��HK 2018��11��5��20:46:42���������� Ҳ����HK 2019��5��16��16:32:13��
				computeJiuDianSrdbInfo(pk_org, pk_dept, hzdate);
			}
			else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org.split(",")[0])) {
				computeSrdbInfo(pk_org, pk_dept, hzdate);
			}
			else if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org.split(",")[0])) {
				computeJiuDianSrdbInfo(pk_org, pk_dept, hzdate);
			}
			// ��ʾ��Ϣ
			MessageDialog.showHintDlg(editor, "��ʾ", "����ɹ���");
		}
	}

	private void checkHaveJdAndHgInfo(String pk_org) throws BusinessException {
		String[] pk_orgs = pk_org.split(",");
		boolean isHuiGuan = false;
		boolean isJiuDian = false;
		for (int i = 0; i < pk_orgs.length; i++) {
			String org = pk_orgs[i];
			if (isHuiGuan && isJiuDian) {
				throw new BusinessException("����ͬʱѡ��Ƶ�ͻ����֯!");
			}
			if (!isHuiGuan) {
				isHuiGuan = HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(org);
			}
			if (!isJiuDian) {
				isJiuDian = HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(org);
			}
		}
	}

	/**
	 * �����ȡ��ʱ���ʽ�������и�ʽΪ ��ʱ��1��ʱ��2���ַ�������Ҫ����
	 * */
	public List<String> handlerDate(String hzdate) throws BusinessException {
		// ��������
		String[] hzdates = hzdate.split(",");
		UFDate begindate = null;
		UFDate enddate = null;
		if (hzdates.length > 1) {
			// �����������������Ҫ����ʱ��εĴ���
			String bdate = hzdates[0];
			String edate = hzdates[1];
			begindate = new UFDate(bdate);
			enddate = new UFDate(edate);
		} else {
			// ���ֻ��һ����������Ϊ�ǵ��ڸ�����
			begindate = new UFDate(hzdates[0]);
		}
		// ��ʼ���ڱ���С�ڽ�������
		if (begindate != null && enddate != null) {
			if (begindate.compareTo(enddate) > 0) {
				throw new BusinessException("����ʱ�������ڿ�ʼʱ�䣬������ѡ��!");
			}
		}
		return null;
	}

	/**
	 * ������������ױ�
	 * 
	 * @throws Exception
	 * */
	private void computeSrdbInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws Exception {
		// ��ʱ�Ȳ���������Ϣ
		getHzsjmaintain().handlerHZShuJuInfo(pk_org, pk_dept, begindate,
				enddate);
	}

	private void computeSrdbInfo(String pk_org, String pk_dept, String date)
			throws Exception {
		String[] dates = date.split(",");
		if (dates.length > 1) {
			computeSrdbInfo(pk_org, pk_dept, dates[0], dates[1]);
		} else {
			computeSrdbInfo(pk_org, pk_dept, dates[0], dates[0]);
		}
	}

	private void computeJiuDianSrdbInfo(String pk_org, String pk_dept,
			String date) throws Exception {
		String[] dates = date.split(",");
		if (dates.length > 1) {
			getJiuDianMaintain().handlerHZShuJuInfo(pk_org, pk_dept, dates[0],
					dates[1]);
		} else {
			getJiuDianMaintain().handlerHZShuJuInfo(pk_org, pk_dept, dates[0],
					dates[0]);
		}
	}

	/**
	 * ��ȡ�����������
	 * 
	 * @param dlg
	 *            ��ѯģ��Ԫ��
	 * @param column
	 *            �����ֶ���Ϣ
	 * @param isPK
	 *            ���շ����Ƿ�PK
	 * @param isdate
	 *            �Ƿ����ڸ�ʽ
	 * */
	private String getValueForColumn(QueryConditionDLGDelegator dlg,
			String column, boolean isPK, boolean isdate) {
		List<IFilter> filtersByFieldCode = dlg.getQueryConditionDLG()
				.getFiltersByFieldCode(column);
		if (filtersByFieldCode != null && filtersByFieldCode.size() > 0) {
			IFilter filter = filtersByFieldCode.get(0);
			if (filter != null) {
				List<String> qryfields = getQryFields(filter, isPK);
				if (qryfields != null && qryfields.size() > 0) {
					String new_value = "";
					for (int i = 0; i < qryfields.size(); i++) {
						String value = qryfields.get(i);
						if (isdate) {
							new_value = new_value + value.substring(0, 10)
									+ ",";
						} else {
							new_value = new_value+value+",";
						}
					}
					new_value = new_value.substring(0, new_value.length()-1);
					return new_value;
				}
			}
		}
		return "";

	}

	/**
	 * �ڲ�ѯģ����ȡ��ĳ��ֵ
	 * 
	 * @param filter
	 * @return
	 */
	private List<String> getQryFields(IFilter filter, boolean isPk) {
		List<String> rtList = new ArrayList<String>();
		if (filter != null) {
			IFieldValue fieldValue = filter.getFieldValue();
			if (fieldValue != null) {

				List<IFieldValueElement> fieldValues = fieldValue
						.getFieldValues();
				if (fieldValues != null && fieldValues.size() > 0) {
					for (IFieldValueElement fieldValueElement : fieldValues) {
						Object valueObject = fieldValueElement.getValueObject();
						if (valueObject != null) {
							if (valueObject instanceof RefValueObject) {
								RefValueObject refValue = (RefValueObject) valueObject;
								String value = null;
								if (isPk) {
									value = refValue.getPk();
								} else {
									value = refValue.getCode();
								}
								rtList.add(value);
							} else if (valueObject instanceof DefaultConstEnum) {
								DefaultConstEnum constEnum = (DefaultConstEnum) valueObject;
								Object value = constEnum.getValue();
								if (value != null) {
									rtList.add(String.valueOf(value));
								}
							} else if (valueObject instanceof UFDate) {
								rtList.add(valueObject.toString());
							}
						}
					}
				}
			}
		}
		return rtList;
	}

	QueryConditionDLGDelegator dlgDelegator;

	private QueryConditionDLGDelegator getDlgDelegator() {
		if (dlgDelegator == null) {
			TemplateInfo tempinfo = new TemplateInfo();
			tempinfo.setPk_Org(this.getModel().getContext().getPk_group());
			tempinfo.setFunNode("HKJ20299");
			tempinfo.setUserid(this.getModel().getContext().getPk_loginUser());
			tempinfo.setNodekey("");
			dlgDelegator = new QueryConditionDLGDelegator(this.getModel()
					.getContext(), tempinfo);
			dlgDelegator.setTitle("��������");
			dlgDelegator.registerNeedPermissionOrgFieldCode(HZShuJuVO.PK_ORG);
			QueryTempMainOrgFilterBaseDoc bankaccByOrgFileter = new QueryTempMainOrgFilterBaseDoc(
					dlgDelegator, HZShuJuVO.PK_ORG, HZShuJuVO.PK_DEPT);
			bankaccByOrgFileter.addEditorListener();
		}
		return dlgDelegator;
	}

	IHG_hzshujuMaintain hzsjmaintain = null;

	public IHG_hzshujuMaintain getHzsjmaintain() {
		if (hzsjmaintain == null) {
			hzsjmaintain = NCLocator.getInstance().lookup(
					IHG_hzshujuMaintain.class);
		}
		return hzsjmaintain;
	}

	IJd_hzshujuMaintain jiudianmaintain = null;

	public IJd_hzshujuMaintain getJiuDianMaintain() {
		if (jiudianmaintain == null) {
			jiudianmaintain = NCLocator.getInstance().lookup(
					IJd_hzshujuMaintain.class);
		}
		return jiudianmaintain;
	}

}
