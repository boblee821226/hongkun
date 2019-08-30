package nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBVO;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class AceHg_cctzBeforeSaveAction implements IRule{
	@Override
	public void process(Object[] vos) {
		for (CctzBillVO cctzvo : (CctzBillVO[])vos) {
			String err="";
			for (CctzBVO bvo : (CctzBVO[])cctzvo.getChildrenVO()) {
				boolean group1=(bvo.getTz_km_jzfs_1()!=null||bvo.getTz_km_srxm_1()!=null)&&bvo.getTz_km_data_1()==null;
				boolean group2=(bvo.getTz_km_jzfs_2()!=null||bvo.getTz_km_srxm_2()!=null)&&bvo.getTz_km_data_2()==null;
				if(group1||group2){
					err+="行号["+bvo.getVrowno()+"]表体 调整-"+(group1?" A ":"")+(group2?" B  ":"")+"组中【数据】字段不能为空！\r\n";
				}
			}
			if(err.length()>0){
				ExceptionUtils.wrappBusinessException(err);
			}
			
		}
		
		
	}
	
}
