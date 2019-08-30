package nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action;

import nc.bs.dao.DAOException;
import nc.bs.hkjt.srgk.huiguan.zhangdan.workplugin.ImpZhangDanBill;
import nc.bs.trade.business.HYSuperDMO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class ReWriteBaseAction extends ImpZhangDanBill implements IRule{

	@Override
	public void process(Object[] vos) {
		SgshujuBillVO[] aggvos=(SgshujuBillVO[])vos;
		try{
		for (SgshujuBillVO aggvo : aggvos) {
			execute(aggvo);
		}
		}catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
	}

	public abstract void execute(SgshujuBillVO aggvo) throws BusinessException;
	
	/**
	 * 判断结账方式是否为代金券
	 * @param bvo
	 * @return
	 * @throws DAOException 
	 */
	public boolean jzfs_djq(SgshujuBVO bvo){
		JzfsHVO jzfs;
		try {
			if(bvo.getTz_km_jzfs_1()==null){return false;}
			jzfs = (JzfsHVO)getSuperDMO().queryByPrimaryKey(JzfsHVO.class, bvo.getTz_km_jzfs_1());
		if(jzfs!=null&&"代金券".equals(jzfs.getName())){
			return true;
		}
		} catch (DAOException e) {
			e.printStackTrace();
			 ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return false;
	}
	
	private HYSuperDMO superdmo=null;
	public HYSuperDMO getSuperDMO(){
		if(superdmo==null)
			superdmo=new HYSuperDMO();
		return superdmo;
	}
}
