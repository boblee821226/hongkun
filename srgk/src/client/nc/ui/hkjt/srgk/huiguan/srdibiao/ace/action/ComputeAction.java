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
		setBtnName("计算");
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
		// 弹出框选择
		QueryConditionDLGDelegator dlg = getDlgDelegator();

		if (1 == dlg.showModal()) {
			// 获取组织条件
			String pk_org  = getValueForColumn(dlg, "pk_org", true, false);
			String pk_dept = getValueForColumn(dlg, "pk_dept", true, false);
			String hzdate  = getValueForColumn(dlg, "hzdate", true, true);
			String isjd    = getValueForColumn(dlg, "isjd", false, false);	// 是否酒店 （HK 2018年11月5日20:46:42）
			// 开始时间结束时间判断处理
			handlerDate(hzdate);
			// 校验如果即选择了会馆又选了酒店那么不能计算
			checkHaveJdAndHgInfo(pk_org);
			// 计算收入底表
			if("Y".equals(isjd) && HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org.split(",")[0])){	// 是否酒店 （HK 2018年11月5日20:46:42）（朗丽兹 也会用HK 2019年5月16日16:32:13）
				computeJiuDianSrdbInfo(pk_org, pk_dept, hzdate);
			}
			else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org.split(",")[0])) {
				computeSrdbInfo(pk_org, pk_dept, hzdate);
			}
			else if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org.split(",")[0])) {
				computeJiuDianSrdbInfo(pk_org, pk_dept, hzdate);
			}
			// 提示信息
			MessageDialog.showHintDlg(editor, "提示", "计算成功！");
		}
	}

	private void checkHaveJdAndHgInfo(String pk_org) throws BusinessException {
		String[] pk_orgs = pk_org.split(",");
		boolean isHuiGuan = false;
		boolean isJiuDian = false;
		for (int i = 0; i < pk_orgs.length; i++) {
			String org = pk_orgs[i];
			if (isHuiGuan && isJiuDian) {
				throw new BusinessException("不能同时选择酒店和会馆组织!");
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
	 * 界面获取的时间格式，参数中格式为 “时间1，时间2”字符串，需要解析
	 * */
	public List<String> handlerDate(String hzdate) throws BusinessException {
		// 处理日期
		String[] hzdates = hzdate.split(",");
		UFDate begindate = null;
		UFDate enddate = null;
		if (hzdates.length > 1) {
			// 如果日期是两个则需要进行时间段的处理
			String bdate = hzdates[0];
			String edate = hzdates[1];
			begindate = new UFDate(bdate);
			enddate = new UFDate(edate);
		} else {
			// 如果只有一个日期则认为是等于该日期
			begindate = new UFDate(hzdates[0]);
		}
		// 开始日期必须小于结束日期
		if (begindate != null && enddate != null) {
			if (begindate.compareTo(enddate) > 0) {
				throw new BusinessException("结束时间必须大于开始时间，请重新选择!");
			}
		}
		return null;
	}

	/**
	 * 计算生成收入底表
	 * 
	 * @throws Exception
	 * */
	private void computeSrdbInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws Exception {
		// 暂时先不处理部门信息
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
	 * 获取计算界面条件
	 * 
	 * @param dlg
	 *            查询模板元素
	 * @param column
	 *            界面字段信息
	 * @param isPK
	 *            参照返回是否PK
	 * @param isdate
	 *            是否日期格式
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
	 * 在查询模板中取得某列值
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
			dlgDelegator.setTitle("计算条件");
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
