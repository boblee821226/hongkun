package nc.impl.hkjt.report;

import java.util.HashMap;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;

/**
 * 
 */

public abstract class AbstractReportAction {

	public AbstractReportAction() {	
		super();
	}

	public DataSet DO(SmartContext context, HashMap<String, Object> param,
			String flag, Object other) throws Exception {
		return excute(context, param, flag, other);
	}

	public abstract DataSet excute(SmartContext context,
			HashMap<String, Object> param, String flag, Object other)
			throws Exception;
	

}
