package nc.ui.hkjt.hg_srxm.ace.maintain;

import nc.ui.pubapp.uif2app.model.BaseBillModelDataManager;
import nc.vo.pubapp.AppContext;

public class AceHg_srxmDataManager extends BaseBillModelDataManager {

	    @Override
	    public void initModel() {
		        String pk_group = AppContext.getInstance().getPkGroup();
		        String pk_org = getModel().getContext().getPk_org();
		        String sqlwhere = " and pk_group = '" + pk_group + "' and pk_org='"+pk_org+"' order by code ";
		        super.initModelBySqlWhere(sqlwhere);
	    }

}
