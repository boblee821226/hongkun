package nc.ui.hkjt.pub.ace.handler;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;

public class QueryConditionInitializer implements IQueryConditionDLGInitializer {

	private nc.ui.pubapp.uif2app.model.BillManageModel model;
	@Override
	public void initQueryConditionDLG(
			QueryConditionDLGDelegator condDLGDelegator) {

		condDLGDelegator.registerNeedPermissionOrgFieldCode("pk_org");
	}

	public nc.ui.pubapp.uif2app.model.BillManageModel getModel() {
		return model;
	}

	public void setModel(nc.ui.pubapp.uif2app.model.BillManageModel model) {
		this.model = model;
	}
	
}
