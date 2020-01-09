package nc.impl.ct.purdaily.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ct.purdaily.update.rule.PurdailyUpdateWriteBackRule;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.ct.purdaily.action.rule.validate.ChkCanValidateRule;
import nc.impl.ct.purdaily.action.rule.validate.PurdailyStateChgRule;
import nc.impl.ct.purdaily.base.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ct.entity.CtAbstractBVO;
import nc.vo.ct.enumeration.CtFlowEnum;
import nc.vo.ct.pub.CtPuTableCode;
import nc.vo.ct.pub.ICTEventType;
import nc.vo.ct.purdaily.entity.AggCtPuVO;
import nc.vo.ct.purdaily.entity.CtPuBVO;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.ct.purdaily.entity.CtScopeVo;
import nc.vo.ct.purdaily.entity.PayPlanVO;
import nc.vo.ct.rule.PurdailyEventRule;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ITableMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.JavaType;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.scmpub.util.ArrayUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b> �ۻ���ͬ�����Ч�Լ��������������󣬷�1.0�汾��ͬ��Ч
 * <ul>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.3
 * @since 6.0
 * @author liangchen1
 * @time 2013-5-20 ����04:17:24
 */
public class PurdailyValidateSpAction {

  public AggCtPuVO[] validate(AggCtPuVO[] vos, AggCtPuVO[] originBills) throws Exception {
    // ����
    // BillTransferTool<AggCtPuVO> tool = new BillTransferTool<AggCtPuVO>(vos);
    // ���°汾����������°汾�����ݿ�vos
    AggCtPuVO[] cliVos = vos;
    AggCtPuVO[] newOrgVos = originBills;

    // �Ȳ�ѯԭʼ�汾vos����vos�������ٲ�ѯһ�飬��ʱ�ܱ�֤��ȫ��סvos����õ����ݿ�vos����ȷ��
    AggCtPuVO[] oldOrgVos = this.queryOldVosByNewVos(cliVos);
    BillConcurrentTool util = new BillConcurrentTool();
    util.lockBill(oldOrgVos);
    oldOrgVos = this.queryOldVosByNewVos(cliVos);
    // ������������ԭʼ�汾
    this.saveOldVersionWithNewPKs(oldOrgVos);
    // AggCtPuVO[] sdf = this.queryOldVosByNewVos(cliVos);
    // ����ԭʼ�������°汾
    AggCtPuVO[] updatedVos = this.saveNewVersionWithOrgPKs(cliVos, newOrgVos);

    return updatedVos;
  }

  /**
   * ����
   * 
   * @param processor
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 ����04:39:00
   */
  private void addRule(CompareAroundProcesser<AggCtPuVO> processer) {
    // ��鵥��״̬
    processer.addBeforeFinalRule(new ChkCanValidateRule());
    // ������Ϣ
    processer.addBeforeFinalRule(new PurdailyStateChgRule());

    processer.addBeforeRule(new PurdailyEventRule(
        ICTEventType.TYPE_VALIDATE_BEFORE));
    // --��Чʱ��������޼ۺ��ݲ����
    // ����޼ۿ���
    // processer.addAfterRule(new MaxPriceRule());
    // CtPuBVO.class, CtPuBVO.PK_CT_PU_B));
    // ���ǰ�ݲ����
    // processer.addAfterRule(new PUModifyToleranceRule(CtPuBVO.class, null));
    // ����������д��Դ����Ҫ�ǻ�д�ɹ���ͬ
    processer.addAfterRule(new PurdailyUpdateWriteBackRule());
    // ����Ҫ�����붩���Ļ�д
    processer.addAfterRule(new PurdailyEventRule(
        ICTEventType.TYPE_VALIDATE_AFTER));
  }

  private List<List<Object>> getForeignAddParam(List<List<Object>> datas,
      String pk_ct_pu, String pk_origct) {
    List<Object> list = new ArrayList<Object>();
    list.add(pk_ct_pu);
    list.add(pk_origct);
    datas.add(list);
    return datas;
  }

  private String getSql(String tableName, String fieldName) {
    StringBuilder builder = new StringBuilder();
    builder.append("update ");
    builder.append(tableName);
    builder.append(" set ");
    builder.append(fieldName);
    builder.append(" = ?  where ");
    builder.append(fieldName);
    builder.append(" = ? ");
    return builder.toString();
  }

  /**
   * �����°汾VO�����ѯԭʼ�汾VO����
   * 
   * @param newVos
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 ����04:39:00
   */
  private AggCtPuVO[] queryOldVosByNewVos(AggCtPuVO[] newVos) {
    String[] ids = new String[newVos.length];
    for (int i = 0; i < newVos.length; i++) {
      ids[i] = newVos[i].getParentVO().getPk_origct();
    }
    AggCtPuVO[] hisVos = new BillQuery<AggCtPuVO>(AggCtPuVO.class).query(ids);
    return hisVos;
  }

  /**
   * �����¡��ϰ汾vo��������ԭʼ�������°汾
   * 
   * @param cliVos
   *          , newOrgVos, oldOrgVos
   * @since 6.3
   * @author liangchen1
 * @throws Exception 
   * @time 2013-5-18 ����04:39:00
   */
  private AggCtPuVO[] saveNewVersionWithOrgPKs(AggCtPuVO[] cliVos,
      AggCtPuVO[] newOrgVos) throws Exception {

    // ɾ���°汾����B���������������Ϊԭʼpk
    DataAccessUtils d = new DataAccessUtils();
    List<List<Object>> datas = new ArrayList<List<Object>>();
    IBillMeta billMeta = newOrgVos[0].getMetaData();
    Map<IVOMeta, IAttributeMeta> childIndex =
        new HashMap<IVOMeta, IAttributeMeta>();
    // ����ӱ���������
    IAttributeMeta[] childForeginKeys = billMeta.getChildForeignKeys();
    for (IAttributeMeta childMeta : childForeginKeys) {
      childIndex.put(childMeta.getVOMeta(), childMeta);
    }
    for (AggCtPuVO norgvo : newOrgVos) {
      CtPuVO head = norgvo.getParentVO();
      String pk_ct_pu = head.getPk_ct_pu();
      String pk_origct = head.getPk_origct();
      datas = this.getForeignAddParam(datas, pk_origct, pk_ct_pu);
      SuperVO[][] allChildren = norgvo.getAllChildren();
      for (SuperVO[] superVOs : allChildren) {
        if (ArrayUtil.isEmpty(superVOs) || superVOs[0] instanceof CtPuBVO) {
          continue;
        }
        ITableMeta tblMeta =
            superVOs[0].getMetaData().getStatisticInfo().getTables()[0];
        d.update(
            this.getSql(tblMeta.getName(),
                childIndex.get(superVOs[0].getMetaData()).getName()),
            new JavaType[] {
              JavaType.String, JavaType.String
            }, datas);
        norgvo.setChildren(superVOs[0].getClass(), null);
      }
      // ���¿��Ʒ�Χ���
      d.update(this.getSql(CtPuTableCode.SCOPE_TABLE, CtScopeVo.PK_CT_PU),
          new JavaType[] {
            JavaType.String, JavaType.String
          }, datas);
    }

    new BillDelete<AggCtPuVO>().delete(newOrgVos);
    AggCtPuVO[] oldOrgVos = this.queryOldVosByNewVos(cliVos);
    /**
     * ����pk��B����ۼ�ִ�����Ե�cliVos�ϣ������õ���״̬Ϊ��Ч ����cliVos�����B������������Ϊԭʼ��
     */
    Map<String, AggCtPuVO> oldmap = new HashMap<String, AggCtPuVO>();
    for (AggCtPuVO vo : oldOrgVos) {
      oldmap.put(vo.getParentVO().getPk_ct_pu(), vo);
    }
    for (AggCtPuVO aggVo : cliVos) {

      CtPuVO head = aggVo.getParentVO();
      String pk_ct_pu = head.getPk_ct_pu();
      head.setPk_ct_pu(head.getPk_origct());
      head.setPk_origct(pk_ct_pu);
      head.setBlatest(UFBoolean.TRUE);

      CtPuVO headold = oldmap.get(head.getPk_ct_pu()).getParentVO();
      head.setNorigpshamount(headold.getNorigpshamount());
      head.setNtotalgpamount(headold.getNtotalgpamount());

      SuperVO[][] allChildren = aggVo.getAllChildren();
      CtPuBVO[] oldBodys = oldmap.get(head.getPk_ct_pu()).getCtPuBVO();
      Map<String, CtPuBVO> bmap = new HashMap<String, CtPuBVO>();
      for (CtPuBVO b : oldBodys) {
        bmap.put(b.getPk_ct_pu_b(), b);
      }
      for (SuperVO[] superVOs : allChildren) {
        if (ArrayUtil.isEmpty(superVOs)) {
          continue;
        }
        /** �°汾����ԭʼ�汾B��pk�����ڵĴ���Ϊ���У�ִ�й�����һ��new */
        if (superVOs[0] instanceof CtPuBVO) {
          for (SuperVO vo : superVOs) {
            if (bmap.containsKey(vo.getAttributeValue(CtPuBVO.PK_ORIGCTB))) {
              /** BVO�滻Ϊ�ϰ汾��ԭʼ���� */
              String pk_ct_pu_b =
                  (String) vo.getAttributeValue(CtPuBVO.PK_CT_PU_B);
              vo.setAttributeValue(CtPuBVO.PK_CT_PU_B,
                  vo.getAttributeValue(CtPuBVO.PK_ORIGCTB));
              vo.setAttributeValue(CtPuBVO.PK_ORIGCTB, pk_ct_pu_b);
              vo.setAttributeValue(CtAbstractBVO.NORDNUM,
                  bmap.get(vo.getAttributeValue(CtPuBVO.PK_CT_PU_B))
                      .getNordnum());
              vo.setAttributeValue(CtAbstractBVO.NORDSUM,
                  bmap.get(vo.getAttributeValue(CtPuBVO.PK_CT_PU_B))
                      .getNordsum());
              vo.setAttributeValue(CtAbstractBVO.NORITOTALGPMNY,
                  bmap.get(vo.getAttributeValue(CtPuBVO.PK_CT_PU_B))
                      .getNoritotalgpmny());
              vo.setAttributeValue(CtAbstractBVO.NTOTALGPMNY,
                  bmap.get(vo.getAttributeValue(CtPuBVO.PK_CT_PU_B))
                      .getNtotalgpmny());
              vo.setAttributeValue(CtPuBVO.NSCHEDULERNUM,
                  bmap.get(vo.getAttributeValue(CtPuBVO.PK_CT_PU_B))
                      .getNschedulernum());
              vo.setStatus(VOStatus.UPDATED);
            }
            else {
              vo.setPrimaryKey(null);
              vo.setStatus(VOStatus.NEW);
            }
          }
        }
        for (SuperVO vo : superVOs) {
          vo.setAttributeValue(childIndex.get(superVOs[0].getMetaData())
              .getName(), head.getPk_ct_pu());
        }
      }
    }
    // cliVos��oldOrgVos����update����
    CompareAroundProcesser<AggCtPuVO> processer =
        new CompareAroundProcesser<AggCtPuVO>(ActionPlugInPoint.VALIDATESP);
    this.addRule(processer);
    processer.before(cliVos, oldOrgVos);
    AggCtPuVO[] updatedVOs =
        new BillUpdate<AggCtPuVO>().update(cliVos, oldOrgVos);
    processer.after(cliVos, oldOrgVos);
    
    /**
     * HK 2020��1��9��18:28:59
     * ��Чʱ��������Ҳͬ������
     */
    {
    	// 1��ɾ��  ԭʼ�汾pk ������
    	// 2���� ����汾pk ������  ����Ϊ ԭʼ�汾
//    	System.out.println(cliVos);
//    	System.out.println(newOrgVos);
    	BaseDAO dao = new BaseDAO();
    	for (int i = 0; i < cliVos.length; i++) {
    		String source_pk = cliVos[i].getPrimaryKey();
    		String validate_pk = newOrgVos[i].getPrimaryKey();
    		
    		StringBuffer updateSQL_source = 
    			new StringBuffer(" update ")
    				.append(" sm_pub_filesystem ")
    				.append(" set dr = 1 ")
    				.append(" where dr = 0 ")
    				.append(" and filepath like '").append(source_pk).append("/%' ")
    		;
    		dao.executeUpdate(updateSQL_source.toString());
    		
    		StringBuffer updateSQL_validate = 
    			new StringBuffer(" update ")
    				.append(" sm_pub_filesystem ")
    				.append(" set filepath = '").append(source_pk).append("' || substr(filepath,21) ")
    				.append(" where dr = 0 ")
    				.append(" and filepath like '").append(validate_pk).append("/%' ")
    		;
    		dao.executeUpdate(updateSQL_validate.toString());
    	}
    }
    /***END***/
    
    return updatedVOs;
  }

  /**
   * ����ԭʼ�汾vo����������������ԭʼ�汾
   * 
   * @param oldOrgVos
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 ����04:39:00
   */
  private void saveOldVersionWithNewPKs(AggCtPuVO[] oldOrgVos) {

    AggCtPuVO[] newOrgVos = new AggCtPuVO[oldOrgVos.length];
    for (int i = 0; i < oldOrgVos.length; ++i) {
      newOrgVos[i] = (AggCtPuVO) oldOrgVos[i].clone();
      // ��ͬ��ͷ
      CtPuVO voHead = newOrgVos[i].getParentVO();
      voHead.setStatus(VOStatus.NEW);
      voHead.setBlatest(UFBoolean.FALSE);
      voHead.setBshowLatest(UFBoolean.FALSE);
      voHead.setPk_ct_pu(null);
      voHead.setTs(null);
      voHead.setFstatusflag((Integer) CtFlowEnum.TERMINATE.value());
      SuperVO[][] allChildren = newOrgVos[i].getAllChildren();
      for (SuperVO[] superVOs : allChildren) {
        if (ArrayUtil.isEmpty(superVOs)) {
          continue;
        }
        if (superVOs[0] instanceof CtPuBVO) {
          for (SuperVO bvo : superVOs) {
            bvo.setPrimaryKey(null);
            bvo.setAttributeValue("TS", null);
            bvo.setStatus(VOStatus.NEW);
          }
        }
        else {
          newOrgVos[i].setChildren(superVOs[0].getClass(), null);
        }
      }
    }
    // ����ԭʼ�汾��¼�������B������������
    AggCtPuVO[] insertedVos = new BillInsert<AggCtPuVO>().insert(newOrgVos);
    // ����ԭʼ�汾�����ӱ�����Ϊ�����ɵ�������������PayPlanVO
    DataAccessUtils d = new DataAccessUtils();
    List<List<Object>> datas = new ArrayList<List<Object>>();

    IBillMeta billMeta = oldOrgVos[0].getMetaData();
    Map<IVOMeta, IAttributeMeta> childIndex =
        new HashMap<IVOMeta, IAttributeMeta>();
    // ����ӱ���������
    IAttributeMeta[] childForeginKeys = billMeta.getChildForeignKeys();
    for (IAttributeMeta childMeta : childForeginKeys) {
      childIndex.put(childMeta.getVOMeta(), childMeta);
    }
    for (int i = 0; i < oldOrgVos.length; i++) {
      CtPuVO head = insertedVos[i].getParentVO();
      String pk_ct_pu = head.getPk_ct_pu();
      String pk_origct = head.getPk_origct();
      datas = this.getForeignAddParam(datas, pk_ct_pu, pk_origct);
      SuperVO[][] allChildren = oldOrgVos[i].getAllChildren();
      for (SuperVO[] superVOs : allChildren) {
        if (ArrayUtil.isEmpty(superVOs) || superVOs[0] instanceof CtPuBVO
            || superVOs[0] instanceof PayPlanVO) {
          continue;
        }
        ITableMeta tblMeta =
            superVOs[0].getMetaData().getStatisticInfo().getTables()[0];
        d.update(
            this.getSql(tblMeta.getName(),
                childIndex.get(superVOs[0].getMetaData()).getName()),
            new JavaType[] {
              JavaType.String, JavaType.String
            }, datas);
      }
      // ���¿��Ʒ�Χ���
      d.update(this.getSql(CtPuTableCode.SCOPE_TABLE, CtScopeVo.PK_CT_PU),
          new JavaType[] {
            JavaType.String, JavaType.String
          }, datas);
    }

  }

}
