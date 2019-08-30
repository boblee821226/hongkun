package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHG_hzshujuMaintain;
import nc.itf.hkjt.IJd_hzshujuMaintain;
import nc.ui.hkjt.srgk.huiguan.srdibiao.ace.dlg.QueryTempMainOrgFilterBaseDoc_HZCX;
import nc.ui.hkjt.srgk.huiguan.srdibiao.ace.view.ShowUpableBillForm;
import nc.ui.hkjt.srgk.huiguan.srdibiao.ace.view.ShowUpableBillListView;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.value.IFieldValue;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.ui.querytemplate.value.RefValueObject;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.querytemplate.TemplateInfo;

public class HZSelectAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915395509783382608L;

	public HZSelectAction() {
		setBtnName("生成");
		setCode("hzselectAction");
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

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// 弹出框选择
		QueryConditionDLGDelegator dlg = getDlgDelegator();

		if (1 == dlg.showModal()) {
			// 获取组织条件
			String pk_org = getValueForColumn(dlg, "pk_org", true, false);
			String pk_dept = getValueForColumn(dlg, "pk_dept", true, false);
			String hzdate = getValueForColumn(dlg, "hzdate", true, true);
			String isjd    = getValueForColumn(dlg, "isjd", false, false);	// 是否酒店 （HK 2018年11月5日20:46:42）
			// 获取当前登录信息
			String pk_group = getModel().getContext().getPk_group();
			String pk_user = getModel().getContext().getPk_loginUser();
			// 开始时间结束时间判断处理
			handlerDate(hzdate);
			checkHaveJdAndHgInfo(pk_org);
			
			if("Y".equals(isjd) && HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org.split(",")[0])){	// 是否酒店 （HK 2018年11月5日20:46:42）（目前的设计  只有国际店 才会选Y）
				handlerJiuDian(pk_org, pk_dept, hzdate, pk_group, pk_user,UFBoolean.TRUE);
			}
			else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org.split(",")[0])) {
				handlerHuiGuan(pk_org, pk_dept, hzdate, pk_group, pk_user);
			} 
			else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org.split(",")[0])) {
				handlerJiuDian(pk_org, pk_dept, hzdate, pk_group, pk_user,UFBoolean.FALSE);
			} else {
				throw new BusinessException("组织主键信息有误!");
			}
			// 这里只能通过这种设置把列表的表体字段排序功能去掉
			getListview().getBillListPanel().getBodyTable()
					.setSortEnabled(false);
			// 提示信息
			ShowStatusBarMsgUtil.showStatusBarMsg("生成完毕!", getEditor()
					.getModel().getContext());
		}
	}

	private void checkHaveJdAndHgInfo(String pk_org) throws BusinessException {
		String[] pk_orgs = pk_org.split(",");
		boolean isHuiGuan = false;
		boolean isJiuDian = false;
		for (int i = 0; i < pk_orgs.length; i++) {
			String org = pk_orgs[i];
			if (isHuiGuan && isJiuDian) {
				throw new BusinessException("不能同时选择酒店和会馆的组织!");
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
	 * 查询会馆汇总数据
	 * 
	 * @throws Exception
	 * */
	private SrdibiaoBillVO[] getHZShuJuInfo(String pk_org, String pk_dept,
			String hzdate, Map<String, String[]> map) throws Exception {
		String[] hzdates = hzdate.split(",");
		SrdibiaoBillVO[] srvos = null;
		if (hzdates.length > 1) {
			srvos = getHzsjmaintain().selectHZShuJuInfo(pk_org, pk_dept,
					hzdates[0], hzdates[1], map, false);
		} else {
			srvos = getHzsjmaintain().selectHZShuJuInfo(pk_org, pk_dept,
					hzdates[0], hzdates[0], map, false);
		}

		return srvos;
	}

	/**
	 * 查询酒店汇总数据
	 * */

	private SrdibiaoBillVO[] getJiuDianHZShuJuInfo(String pk_org,
			String pk_dept, String hzdate, Map<String, String[]> map,UFBoolean isjd)
			throws Exception {
		String[] hzdates = hzdate.split(",");
		SrdibiaoBillVO[] srvos = null;
		if (hzdates.length > 1) {
			srvos = getJiuDianMaintain().selectHZShuJuInfo(pk_org, pk_dept,
					hzdates[0], hzdates[1], map, false, "N",isjd);
		} else {
			srvos = getJiuDianMaintain().selectHZShuJuInfo(pk_org, pk_dept,
					hzdates[0], hzdates[0], map, false, "N",isjd);
		}

		return srvos;
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
							new_value = new_value + value + ",";
						}
					}
					new_value = new_value.substring(0, new_value.length() - 1);
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
			dlgDelegator.registerNeedPermissionOrgFieldCode(HZShuJuVO.PK_ORG);
			QueryTempMainOrgFilterBaseDoc_HZCX bankaccByOrgFileter = new QueryTempMainOrgFilterBaseDoc_HZCX(
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

	// 会馆的收入底表处理
	private void handlerHuiGuan(String pk_org, String pk_dept, String hzdate,
			String pk_group, String pk_user) throws Exception {
		Map<String, String[]> map = null;
		if (pk_dept != null && !"".equals(pk_dept)) {
			map = getHzsjmaintain().getDept_Vdef(pk_dept);
		}
		// 查询汇总表
		SrdibiaoBillVO[] vos = getHZShuJuInfo(pk_org, pk_dept, hzdate, map);
		// 赋值默认值处理
		if (vos != null && vos.length > 0) {
			for (int i = 0; i < vos.length; i++) {
				SrdibiaoBillVO srdibiaoBillVO = vos[i];
				SrdibiaoHVO srdbhvo = srdibiaoBillVO.getParentVO();
				srdbhvo.setPk_group(pk_group);
				srdbhvo.setCreator(pk_user);
				srdbhvo.setCreationtime(new UFDateTime());
			}
		}
		// 显示界面必须显示字段
		String[] showcolumns = new String[] { "youhui_zidong",
				"youhui_shougong", "youhui_kabili", "shouru" };
		for (int i = 0; i < showcolumns.length; i++) {
			String string = showcolumns[i];
			BillItem billetem = getEditor().getBillCardPanel().getBodyItem(
					string);
			if (billetem != null) {
				billetem.setShow(true);
			}

		}
		// 将获取到的值赋值到界面上,根据是否有下级部门进行列的动态显示
		if (map != null && !map.keySet().isEmpty()) {
			// 先将所有的隐藏然后重新显示，预防直接切换上级部门的情况
			for (int i = 1; i <= 20; i++) {
				String bh = "";
				if (i < 10) {
					bh = "0" + i;
				} else {
					bh = "" + i;
				}
				BillItem billetem = getEditor().getBillCardPanel().getBodyItem(
						"shouru_bm" + bh);
				if (billetem != null) {
					billetem.setShow(false);
				}
			}
			Set<String> key = map.keySet();
			Iterator<String> it = key.iterator();
			while (it.hasNext()) {
				String value = it.next();
				String strs[] = map.get(value);
				String vdef = strs[0];
				String deptname = strs[1];
				getEditor().getBillCardPanel().getBodyItem(vdef).setShow(true);
				getEditor().getBillCardPanel().getBodyItem(vdef)
						.setName(deptname);
			}
		} else {
			// 暂时先定义部门动态部门为20个
			for (int i = 1; i <= 20; i++) {
				String bh = "";
				if (i < 10) {
					bh = "0" + i;
				} else {
					bh = "" + i;
				}
				BillItem billetem = getEditor().getBillCardPanel().getBodyItem(
						"shouru_bm" + bh);
				if (billetem != null) {
					billetem.setShow(false);
				}

			}
		}
		getEditor().getBillCardPanel().setBillData(
				getEditor().getBillCardPanel().getBillData());
		getEditor().getModel().initModel(vos);
	}

	// 酒店的收入底表处理
	private void handlerJiuDian(String pk_org, String pk_dept, String hzdate,
			String pk_group, String pk_user,UFBoolean isjd) throws Exception {
		Map<String, String[]> map = null;
		// if (pk_dept != null && !"".equals(pk_dept)) {
		// map = getHzsjmaintain().getDept_Vdef(pk_dept);
		// }
		// 查询汇总表
		SrdibiaoBillVO[] vos = getJiuDianHZShuJuInfo(pk_org, pk_dept, hzdate,map,isjd);
		// 赋值默认值处理
		if (vos != null && vos.length > 0) {
			for (int i = 0; i < vos.length; i++) {
				SrdibiaoBillVO srdibiaoBillVO = vos[i];
				SrdibiaoHVO srdbhvo = srdibiaoBillVO.getParentVO();
				srdbhvo.setPk_group(pk_group);
				srdbhvo.setCreator(pk_user);
				srdbhvo.setCreationtime(new UFDateTime());
				srdbhvo.setVdef10(isjd.toString());		// 是否酒店 （HK 2018年11月6日20:37:45）
			}
		}
		// 显示界面不显示字段
		String[] showcolumns = new String[] { "youhui_zidong",
				"youhui_shougong", "youhui_kabili", "shouru" };
		for (int i = 0; i < showcolumns.length; i++) {
			String string = showcolumns[i];
			BillItem billetem = getEditor().getBillCardPanel().getBodyItem(
					string);
			if (billetem != null) {
				billetem.setShow(false);
			}

		}
		// 将获取到的值赋值到界面上,根据是否有下级部门进行列的动态显示
		// if (map != null && !map.keySet().isEmpty()) {
		// // 先将所有的隐藏然后重新显示，预防直接切换上级部门的情况
		// for (int i = 1; i <= 20; i++) {
		// String bh = "";
		// if (i < 10) {
		// bh = "0" + i;
		// } else {
		// bh = "" + i;
		// }
		// BillItem billetem = getEditor().getBillCardPanel().getBodyItem(
		// "shouru_bm" + bh);
		// if (billetem != null) {
		// billetem.setShow(false);
		// }
		// }
		// Set<String> key = map.keySet();
		// Iterator<String> it = key.iterator();
		// while (it.hasNext()) {
		// String value = it.next();
		// String strs[] = map.get(value);
		// String vdef = strs[0];
		// String deptname = strs[1];
		// getEditor().getBillCardPanel().getBodyItem(vdef).setShow(true);
		// getEditor().getBillCardPanel().getBodyItem(vdef)
		// .setName(deptname);
		// }
		// } else {
		// 暂时先定义部门动态部门为20个
		for (int i = 1; i <= 20; i++) {
			String bh = "";
			if (i < 10) {
				bh = "0" + i;
			} else {
				bh = "" + i;
			}
			BillItem billetem = getEditor().getBillCardPanel().getBodyItem(
					"shouru_bm" + bh);
			if (billetem != null) {
				billetem.setShow(false);
			}

		}
		// }
		getEditor().getBillCardPanel().setBillData(
				getEditor().getBillCardPanel().getBillData());
		if (vos != null && vos.length > 0 && vos[0].getChildrenVO() != null
				&& vos[0].getChildrenVO().length > 0) {
			getEditor().getModel().initModel(vos);
		} else {
			getEditor().getModel().initModel(null);
		}
	}

}
