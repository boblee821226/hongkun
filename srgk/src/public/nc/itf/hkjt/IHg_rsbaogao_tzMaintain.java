package nc.itf.hkjt;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO;

public interface IHg_rsbaogao_tzMaintain extends ISmartService{

	 public RsbaogaoCVO[] query(IQueryScheme queryScheme)
      throws BusinessException, Exception;
}