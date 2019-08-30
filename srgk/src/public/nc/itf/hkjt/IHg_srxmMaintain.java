package nc.itf.hkjt;

import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.pub.BusinessException;

public interface IHg_srxmMaintain {

	public void delete(SrxmHVO vo) throws BusinessException;

	public SrxmHVO insert(SrxmHVO vo) throws BusinessException;

	public SrxmHVO update(SrxmHVO vo) throws BusinessException;

	public SrxmHVO[] query(String whereSql) throws BusinessException;
	/**
	 * 分配收入项目会计科目信息
	 * */
	public void fenpeiSrxmKjkmInfo(SrxmHVO vo)  throws Exception;
}
