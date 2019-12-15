package nc.impl.pub.ace;

import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_jzfsPubServiceImpl {

  //增加方法
			  public JzfsHVO inserttreeinfo	(JzfsHVO			 vo) throws BusinessException {
			    try {
			      // 添加BP规则
			      AroundProcesser<JzfsHVO> processer =			new AroundProcesser<JzfsHVO>(null);
			      processer.before(new JzfsHVO[] { vo });
			      VOInsert<JzfsHVO> ins = new VOInsert<JzfsHVO>();
      JzfsHVO[] superVOs = ins.insert(new JzfsHVO[] { vo });
			      return superVOs[0];
			    }
			    catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			    return null;
			  }
  //删除方法
			  public void deletetreeinfo			(JzfsHVO vo) throws BusinessException {
			    try {
			      // 添加BP规则	
			      AroundProcesser<JzfsHVO> processer =new AroundProcesser<JzfsHVO>(null);
			      processer.before(new JzfsHVO[] { vo });
			      VODelete<JzfsHVO> voDel = new VODelete<JzfsHVO>();
      			voDel.delete(new JzfsHVO[] { vo });
			    }
			    catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			
			  }
			  //修改方法
			  public JzfsHVO 			updatetreeinfo	(JzfsHVO vo) throws BusinessException {
			    try {
			      // 添加BP规则
			      AroundProcesser<JzfsHVO> processer = new AroundProcesser<JzfsHVO>(null);
		      	JzfsHVO[] originVOs = this.getTreeCardVOs(new JzfsHVO[] { vo });
			      processer.before(new JzfsHVO[] { vo });
			      VOUpdate<JzfsHVO> upd = new VOUpdate<JzfsHVO>();
      JzfsHVO[] superVOs = upd.update(new JzfsHVO[] { vo },originVOs);
 			     return superVOs[0];
			    } catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			    return null;
			  }

			  private JzfsHVO[] getTreeCardVOs(JzfsHVO[] vos) {
			    String[] ids = this.getIDS(vos);
			    VOQuery<JzfsHVO> query = new VOQuery<JzfsHVO>(JzfsHVO.class);
					    return query.query(ids);	
				  }

			  private String[] getIDS(JzfsHVO[] vos) {
					    int size = vos.length;
					    String[] ids = new String[size];
					    for (int i = 0; i < size; i++) {
						       ids[i] = vos[i].getPrimaryKey();
					    }
					    return ids;
				  }
				  
				  //查询方法
				  public JzfsHVO[] querytreeinfo(String whereSql)
			      throws BusinessException {
			    VOQuery<JzfsHVO> query = new VOQuery<JzfsHVO>(JzfsHVO.class);
					    return query.query(whereSql, null);
				  }
}