package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.pub.BusinessException;

public interface IHg_rsbaogaoMaintain {

	public void delete(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException;

	public RsbaogaoBillVO[] insert(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException;

	public RsbaogaoBillVO[] update(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException;

	public RsbaogaoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public RsbaogaoBillVO[] save(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException;

	public RsbaogaoBillVO[] unsave(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException;

	public RsbaogaoBillVO[] approve(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException;

	public RsbaogaoBillVO[] unapprove(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException;
}
