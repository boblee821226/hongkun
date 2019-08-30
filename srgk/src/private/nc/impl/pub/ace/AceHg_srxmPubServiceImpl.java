package nc.impl.pub.ace;

import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_srxmPubServiceImpl {

  //���ӷ���
			  public SrxmHVO inserttreeinfo	(SrxmHVO			 vo) throws BusinessException {
			    try {
			      // ���BP����
			      AroundProcesser<SrxmHVO> processer =			new AroundProcesser<SrxmHVO>(null);
			      processer.before(new SrxmHVO[] { vo });
			      VOInsert<SrxmHVO> ins = new VOInsert<SrxmHVO>();
      SrxmHVO[] superVOs = ins.insert(new SrxmHVO[] { vo });
			      return superVOs[0];
			    }
			    catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			    return null;
			  }
  //ɾ������
			  public void deletetreeinfo			(SrxmHVO vo) throws BusinessException {
			    try {
			      // ���BP����	
			      AroundProcesser<SrxmHVO> processer =new AroundProcesser<SrxmHVO>(null);
			      processer.before(new SrxmHVO[] { vo });
			      VODelete<SrxmHVO> voDel = new VODelete<SrxmHVO>();
      			voDel.delete(new SrxmHVO[] { vo });
			    }
			    catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			
			  }
			  //�޸ķ���
			  public SrxmHVO 			updatetreeinfo	(SrxmHVO vo) throws BusinessException {
			    try {
			      // ���BP����
			      AroundProcesser<SrxmHVO> processer = new AroundProcesser<SrxmHVO>(null);
		      	SrxmHVO[] originVOs = this.getTreeCardVOs(new SrxmHVO[] { vo });
			      processer.before(new SrxmHVO[] { vo });
			      VOUpdate<SrxmHVO> upd = new VOUpdate<SrxmHVO>();
      SrxmHVO[] superVOs = upd.update(new SrxmHVO[] { vo },originVOs);
 			     return superVOs[0];
			    } catch (Exception e) {
			      ExceptionUtils.marsh(e);
			    }
			    return null;
			  }

			  private SrxmHVO[] getTreeCardVOs(SrxmHVO[] vos) {
			    String[] ids = this.getIDS(vos);
			    VOQuery<SrxmHVO> query = new VOQuery<SrxmHVO>(SrxmHVO.class);
					    return query.query(ids);	
				  }

			  private String[] getIDS(SrxmHVO[] vos) {
					    int size = vos.length;
					    String[] ids = new String[size];
					    for (int i = 0; i < size; i++) {
						       ids[i] = vos[i].getPrimaryKey();
					    }
					    return ids;
				  }
				  
				  //��ѯ����
				  public SrxmHVO[] querytreeinfo(String whereSql)
			      throws BusinessException {
			    VOQuery<SrxmHVO> query = new VOQuery<SrxmHVO>(SrxmHVO.class);
					    return query.query(whereSql, null);
				  }
}