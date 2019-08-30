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
 * ��ѯģ���ϸ�����֯���˵�����ͨ�ù�����
 * 
 * @author xwq
 * 
 */
public class QueryTempMainOrgFilterBaseDoc extends
		AbstractLinkageColumnListener {

	private String orgField = null;
	private String targetField = null;

	public QueryTempMainOrgFilterBaseDoc(QueryConditionDLGDelegator dlg,
			String orgField, String deptField) {
		super(dlg);
		this.orgField = orgField;
		this.targetField = deptField;
	}

	public void addEditorListener() {
		this.setFatherPath(this.orgField);
		this.setChildPath(this.targetField);
		// ע��༭�¼�
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

		// �ж��Ƿ��ǲ��գ�����ǹ̶����������ٹ��ˣ�����ת�������쳣 modify by aishm 2012-06-15
		if (!(wrapper.getFieldValueElemEditorComponent() instanceof UIRefPane)) {
			return;
		}

		UIRefPane refPane = (UIRefPane) wrapper
				.getFieldValueElemEditorComponent();
		if (refPane.getRefModel() == null) {
			return;
		}
		// ���֮ǰ��ֵ
		refPane.getRefModel().clearData();
		editor.getFilter().setFieldValue(null);
		// ��֯
		// ���ѡ�񵥸���֯����ô���ս��潫ֱ��ѡ�񣬶�����ͨ������ѡ�����ʵ��
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
		// ���û��ѡ������֯����ȡ�û�������֯�Ļ�����������
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
		refPane.getRefModel().setAddEnvWherePart(false);
		if (sql.toString().split(",").length > 1) {
			refPane.getRefModel().setWherePart(" 1=2 ");
		} else {
			refPane.getRefModel().setWherePart("  pk_org in (" + sql + ")");
		}
	}
}
