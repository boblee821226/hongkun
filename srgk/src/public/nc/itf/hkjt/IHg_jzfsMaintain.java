package nc.itf.hkjt;

import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.pub.BusinessException;

public interface IHg_jzfsMaintain {

    public void delete(JzfsHVO vo) throws BusinessException ;
    
    public JzfsHVO insert(JzfsHVO vo) throws BusinessException;
  
    public JzfsHVO update(JzfsHVO vo) throws BusinessException ;

    public JzfsHVO[] query(String whereSql)
        throws BusinessException;
}
