package nc.impl.pub.ace;

import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_spflPubServiceImpl {

  //增加方法
			  public SpflHVO inserttreeinfo	(SpflHVO			 vo) throws BusinessException {
			    try {
			      // 添加BP规则
			      AroundProcesser<SpflHVO> processer =			new AroundProcesser<SpflHVO>(null);
			      processer.before(new SpflHVO[] { vo });
			      VOInsert<SpflHVO> ins = new VOInsert<SpflHVO>();
      SpflHVO[] superVOs = ins.insert(new SpflHVO[] { vo });
			      return superVOs[0];
			    }
			    catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			    return null;
			  }
  //删除方法
			  public void deletetreeinfo			(SpflHVO vo) throws BusinessException {
			    try {
			      // 添加BP规则	
			      AroundProcesser<SpflHVO> processer =new AroundProcesser<SpflHVO>(null);
			      processer.before(new SpflHVO[] { vo });
			      VODelete<SpflHVO> voDel = new VODelete<SpflHVO>();
      			voDel.delete(new SpflHVO[] { vo });
			    }
			    catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			
			  }
			  //修改方法
			  public SpflHVO 			updatetreeinfo	(SpflHVO vo) throws BusinessException {
			    try {
			      // 添加BP规则
			      AroundProcesser<SpflHVO> processer = new AroundProcesser<SpflHVO>(null);
		      	SpflHVO[] originVOs = this.getTreeCardVOs(new SpflHVO[] { vo });
			      processer.before(new SpflHVO[] { vo });
			      VOUpdate<SpflHVO> upd = new VOUpdate<SpflHVO>();
      SpflHVO[] superVOs = upd.update(new SpflHVO[] { vo },originVOs);
 			     return superVOs[0];
			    } catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			    return null;
			  }

			  private SpflHVO[] getTreeCardVOs(SpflHVO[] vos) {
			    String[] ids = this.getIDS(vos);
			    VOQuery<SpflHVO> query = new VOQuery<SpflHVO>(SpflHVO.class);
					    return query.query(ids);	
				  }

			  private String[] getIDS(SpflHVO[] vos) {
					    int size = vos.length;
					    String[] ids = new String[size];
					    for (int i = 0; i < size; i++) {
						       ids[i] = vos[i].getPrimaryKey();
					    }
					    return ids;
				  }
				  
				  //查询方法
				  public SpflHVO[] querytreeinfo(String whereSql)
			      throws BusinessException {
			    VOQuery<SpflHVO> query = new VOQuery<SpflHVO>(SpflHVO.class);
					    return query.query(whereSql, null);
				  }
}