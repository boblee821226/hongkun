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
 * <b>本类主要完成以下功能：</b> 港华合同变更生效以及重走审批流需求，非1.0版本合同生效
 * <ul>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.3
 * @since 6.0
 * @author liangchen1
 * @time 2013-5-20 下午04:17:24
 */
public class PurdailyValidateSpAction {

  public AggCtPuVO[] validate(AggCtPuVO[] vos, AggCtPuVO[] originBills) throws Exception {
    // 加锁
    // BillTransferTool<AggCtPuVO> tool = new BillTransferTool<AggCtPuVO>(vos);
    // 对新版本加锁，获得新版本的数据库vos
    AggCtPuVO[] cliVos = vos;
    AggCtPuVO[] newOrgVos = originBills;

    // 先查询原始版本vos，对vos加锁，再查询一遍，这时能保证完全锁住vos，获得的数据库vos是正确的
    AggCtPuVO[] oldOrgVos = this.queryOldVosByNewVos(cliVos);
    BillConcurrentTool util = new BillConcurrentTool();
    util.lockBill(oldOrgVos);
    oldOrgVos = this.queryOldVosByNewVos(cliVos);
    // 保存新主键的原始版本
    this.saveOldVersionWithNewPKs(oldOrgVos);
    // AggCtPuVO[] sdf = this.queryOldVosByNewVos(cliVos);
    // 保存原始主键的新版本
    AggCtPuVO[] updatedVos = this.saveNewVersionWithOrgPKs(cliVos, newOrgVos);

    return updatedVos;
  }

  /**
   * 规则
   * 
   * @param processor
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 下午04:39:00
   */
  private void addRule(CompareAroundProcesser<AggCtPuVO> processer) {
    // 检查单据状态
    processer.addBeforeFinalRule(new ChkCanValidateRule());
    // 更新信息
    processer.addBeforeFinalRule(new PurdailyStateChgRule());

    processer.addBeforeRule(new PurdailyEventRule(
        ICTEventType.TYPE_VALIDATE_BEFORE));
    // --生效时不做最高限价和容差控制
    // 最高限价控制
    // processer.addAfterRule(new MaxPriceRule());
    // CtPuBVO.class, CtPuBVO.PK_CT_PU_B));
    // 变更前容差控制
    // processer.addAfterRule(new PUModifyToleranceRule(CtPuBVO.class, null));
    // 总括订单回写来源，主要是回写采购合同
    processer.addAfterRule(new PurdailyUpdateWriteBackRule());
    // 不需要处理与订单的互写
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
   * 根据新版本VO数组查询原始版本VO数组
   * 
   * @param newVos
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 下午04:39:00
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
   * 根据新、老版本vo更新生成原始主键的新版本
   * 
   * @param cliVos
   *          , newOrgVos, oldOrgVos
   * @since 6.3
   * @author liangchen1
 * @throws Exception 
   * @time 2013-5-18 下午04:39:00
   */
  private AggCtPuVO[] saveNewVersionWithOrgPKs(AggCtPuVO[] cliVos,
      AggCtPuVO[] newOrgVos) throws Exception {

    // 删除新版本主表、B表，更新其他表外键为原始pk
    DataAccessUtils d = new DataAccessUtils();
    List<List<Object>> datas = new ArrayList<List<Object>>();
    IBillMeta billMeta = newOrgVos[0].getMetaData();
    Map<IVOMeta, IAttributeMeta> childIndex =
        new HashMap<IVOMeta, IAttributeMeta>();
    // 获得子表的所有外键
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
      // 更新控制范围外键
      d.update(this.getSql(CtPuTableCode.SCOPE_TABLE, CtScopeVo.PK_CT_PU),
          new JavaType[] {
            JavaType.String, JavaType.String
          }, datas);
    }

    new BillDelete<AggCtPuVO>().delete(newOrgVos);
    AggCtPuVO[] oldOrgVos = this.queryOldVosByNewVos(cliVos);
    /**
     * 根据pk将B表的累计执行量对到cliVos上，并设置单据状态为生效 设置cliVos主表和B表的主键和外键为原始版
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
        /** 新版本对于原始版本B表pk不存在的处理为增行，执行过程有一个new */
        if (superVOs[0] instanceof CtPuBVO) {
          for (SuperVO vo : superVOs) {
            if (bmap.containsKey(vo.getAttributeValue(CtPuBVO.PK_ORIGCTB))) {
              /** BVO替换为老版本的原始主键 */
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
    // cliVos和oldOrgVos进行update操作
    CompareAroundProcesser<AggCtPuVO> processer =
        new CompareAroundProcesser<AggCtPuVO>(ActionPlugInPoint.VALIDATESP);
    this.addRule(processer);
    processer.before(cliVos, oldOrgVos);
    AggCtPuVO[] updatedVOs =
        new BillUpdate<AggCtPuVO>().update(cliVos, oldOrgVos);
    processer.after(cliVos, oldOrgVos);
    
    /**
     * HK 2020年1月9日18:28:59
     * 生效时，将附件也同步过来
     */
    {
    	// 1、删除  原始版本pk 的数据
    	// 2、将 变更版本pk 的数据  更新为 原始版本
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
   * 根据原始版本vo插入生成新主键的原始版本
   * 
   * @param oldOrgVos
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 下午04:39:00
   */
  private void saveOldVersionWithNewPKs(AggCtPuVO[] oldOrgVos) {

    AggCtPuVO[] newOrgVos = new AggCtPuVO[oldOrgVos.length];
    for (int i = 0; i < oldOrgVos.length; ++i) {
      newOrgVos[i] = (AggCtPuVO) oldOrgVos[i].clone();
      // 合同表头
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
    // 插入原始版本记录，主表和B表，生成新主键
    AggCtPuVO[] insertedVos = new BillInsert<AggCtPuVO>().insert(newOrgVos);
    // 更新原始版本其他子表的外键为新生成的主键，不包括PayPlanVO
    DataAccessUtils d = new DataAccessUtils();
    List<List<Object>> datas = new ArrayList<List<Object>>();

    IBillMeta billMeta = oldOrgVos[0].getMetaData();
    Map<IVOMeta, IAttributeMeta> childIndex =
        new HashMap<IVOMeta, IAttributeMeta>();
    // 获得子表的所有外键
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
      // 更新控制范围外键
      d.update(this.getSql(CtPuTableCode.SCOPE_TABLE, CtScopeVo.PK_CT_PU),
          new JavaType[] {
            JavaType.String, JavaType.String
          }, datas);
    }

  }

}
