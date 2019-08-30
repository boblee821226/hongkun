package nc.itf.hkjt;

import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.pub.BusinessException;

public interface IHg_spflMaintain {

    public void delete(SpflHVO vo) throws BusinessException ;
    
    public SpflHVO insert(SpflHVO vo) throws BusinessException;
  
    public SpflHVO update(SpflHVO vo) throws BusinessException ;

    public SpflHVO[] query(String whereSql)
        throws BusinessException;
    
    public void tongBuSpfl(String pk_org)throws BusinessException ;
}
