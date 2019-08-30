package nc.impl.hkjt;

import nc.impl.pub.ace.AceHg_rsbaogao_tzPubServiceImpl;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO;
import nc.itf.hkjt.IHg_rsbaogao_tzMaintain;

public class Hg_rsbaogao_tzMaintainImpl extends AceHg_rsbaogao_tzPubServiceImpl
		implements IHg_rsbaogao_tzMaintain {

	@Override
	public RsbaogaoCVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybasedoc(queryScheme);
	}

	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
		BatchSaveAction<RsbaogaoCVO> saveAction = new BatchSaveAction<RsbaogaoCVO>();
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}
}
