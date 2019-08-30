package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.dlg;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refregion.AbstractLinkageColumnListener;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.ui.querytemplate.value.RefValueObject;

/**
 * 查询模板上根据组织过滤档案的通用过滤器
 * 
 * @author xwq
 * 
 */
public class QueryTempMainOrgFilterBaseDoc_HZCX extends
		AbstractLinkageColumnListener {

	private String orgField = null;
	private String targetField = null;

	public QueryTempMainOrgFilterBaseDoc_HZCX(QueryConditionDLGDelegator dlg,
			String orgField, String deptField) {
		super(dlg);
		this.orgField = orgField;
		this.targetField = deptField;
	}

	public void addEditorListener() {
		this.setFatherPath(this.orgField);
		this.setChildPath(this.targetField);
		// 注册编辑事件
		this.qryCondDLGDelegator.registerCriteriaEditorListener(this);
	}

	@Override
	protected void processLinkageLogic(List<IFieldValueElement> fatherValues,
			IFilterEditor editor) {
		List<String> diffValues = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		for (IFieldValueElement fve : fatherValues) {
			if (diffValues.contains(fve.getSqlString())
					|| fve.getSqlString() == null) {
				continue;
			}
			diffValues.add(fve.getSqlString());
			Object valueObject = fve.getValueObject();
			if (valueObject != null) {
				if (valueObject instanceof RefValueObject) {
					RefValueObject refValue = (RefValueObject) valueObject;
					String value = null;
					value = refValue.getPk();
					String[] values = value.split(",");
					for (int i = 0; i < values.length; i++) {
						if (i == values.length - 1) {
							sql.append("'" + values[i] + "'");
						} else {
							sql.append("'" + values[i] + "',");
						}
					}
				}
			}
		}

		FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);

		// 判断是否是参照，如果是固定条件，则不再过滤，否则报转换类型异常 modify by aishm 2012-06-15
		if (!(wrapper.getFieldValueElemEditorComponent() instanceof UIRefPane)) {
			return;
		}

		UIRefPane refPane = (UIRefPane) wrapper
				.getFieldValueElemEditorComponent();
		if (refPane.getRefModel() == null) {
			return;
		}
		// 清空之前的值
		refPane.getRefModel().clearData();
		editor.getFilter().setFieldValue(null);
		// 组织
		// 如果选择单个组织，那么参照界面将直接选择，而不是通过左右选择框来实现
		if (diffValues.size() == 1) {
			refPane.setMultiOrgSelected(false);
			refPane.setMultiCorpRef(false);
			qryCondDLGDelegator.setRefMultiCorpFlag(targetField, false);
		} else {
			refPane.setMultiOrgSelected(true);
			refPane.setMultiCorpRef(true);
			qryCondDLGDelegator.setRefMultiCorpFlag(targetField, true);
		}

		if (diffValues.size() > 0) {
			String[] pk_orgs = diffValues.toArray(new String[0]);

			refPane.setPk_org(pk_orgs[0]);
			refPane.setMultiRefFilterPKs(pk_orgs);
		}
		// else {
		// 如果没有选择主组织，则取用户分配组织的基本档案数据
		// List<String> needShowOrgPks = new ArrayList<String>();
		// LoginContext context =
		// this.qryCondDLGDelegator.getLogincontext();
		// String[] permissionPkorgs =
		// context.getFuncInfo().getFuncPermissionPkorgs();
		// if(permissionPkorgs!=null&&permissionPkorgs.length>0){
		// for (String pkOrg : permissionPkorgs) {
		// needShowOrgPks.add(pkOrg);
		// }
		// }
		// String[] pk_orgs =needShowOrgPks.toArray(new String[0]);
		// if (pk_orgs!=null&&pk_orgs.length>0){
		// refPane.setPk_org(pk_orgs[0]);
		// refPane.setMultiRefFilterPKs(pk_orgs);
		// }
		// }
		refPane.setMultiSelectedEnabled(false);
		refPane.getRefModel().setAddEnvWherePart(false);
		if (sql.toString().split(",").length > 1) {
			refPane.getRefModel().setWherePart(" 1=2 ");
		} else {
			refPane.getRefModel().setWherePart("  pk_org in (" + sql + ")");
		}
	}
}
