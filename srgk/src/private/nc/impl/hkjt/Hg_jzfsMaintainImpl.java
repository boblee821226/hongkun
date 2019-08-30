package nc.impl.hkjt;

import nc.impl.pub.ace.AceHg_jzfsPubServiceImpl;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;

public class Hg_jzfsMaintainImpl extends AceHg_jzfsPubServiceImpl implements nc.itf.hkjt.IHg_jzfsMaintain {

      @Override
    public void delete(JzfsHVO vos) throws BusinessException {
        super.deletetreeinfo(vos);
    }
  
      @Override
    public JzfsHVO insert(JzfsHVO vos) throws BusinessException {
        return super.inserttreeinfo(vos);
    }
    
      @Override
    public JzfsHVO update(JzfsHVO vos) throws BusinessException {
    	  vos.setModifiedtime(new UFDateTime());
        return super.updatetreeinfo(vos);    
    }
  
      @Override
    public JzfsHVO[] query(String whereSql)
        throws BusinessException {
        return super.querytreeinfo(whereSql);
    }

  
}
