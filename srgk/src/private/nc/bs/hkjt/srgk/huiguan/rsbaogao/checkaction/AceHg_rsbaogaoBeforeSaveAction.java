package nc.bs.hkjt.srgk.huiguan.rsbaogao.checkaction;

import nc.bs.trade.business.HYSuperDMO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoHVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class AceHg_rsbaogaoBeforeSaveAction implements IRule{
	@Override
	public void process(Object[] vos) {
		RsbaogaoBillVO[] aggvos=(RsbaogaoBillVO[])vos;
		try{
		for (RsbaogaoBillVO aggvo : aggvos) {
			if(aggvo.getParentVO().getDbilldate()==null){
				throw new BusinessException("业务日期不能为空！");
			}
			String dbilldate=aggvo.getParentVO().getDbilldate().toString().substring(0,10);
			RsbaogaoHVO[]hvo=(RsbaogaoHVO[])new HYSuperDMO().queryByWhereClause(RsbaogaoHVO .class, "nvl(dr,0)=0 and substr(dbilldate,0,10)='"+dbilldate+"' and pk_org='"+aggvo.getParentVO().getPk_org()+"' and pk_hk_srgk_hg_rsbaogao <>'"+aggvo.getParentVO().getPk_hk_srgk_hg_rsbaogao()+"'");
			if(hvo!=null&&hvo.length>0){
				throw new BusinessException("业务日期["+dbilldate+"]的单据已存在，不允许重复保存！");
			}
		}
		}catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		
	}
	
	
}
