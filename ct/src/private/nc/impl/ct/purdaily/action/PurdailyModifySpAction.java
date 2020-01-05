package nc.impl.ct.purdaily.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ct.purdaily.insert.rule.CtPuBracketOrderCheckRule;
import nc.bs.ct.purdaily.insert.rule.MaxPriceRule;
import nc.bs.ct.purdaily.insert.rule.PuNotNullChkRule;
import nc.bs.ct.purdaily.rule.PurdailyTypeChkRule;
import nc.bs.ct.rule.pub.CTRowNoCheckRule;
import nc.bs.ct.rule.pub.FillModifiedInfoRule;
import nc.bs.ct.rule.pub.IsNullChkRule;
import nc.bs.ct.rule.pub.NumValueChkRule;
import nc.bs.ct.rule.pub.SaveVOValidateRule;
import nc.bs.ct.rule.pub.SelfDefItemChkRule;
import nc.bs.dao.BaseDAO;
import nc.bs.pubapp.AppBsContext;
import nc.impl.ct.purdaily.PuControlImpl;
import nc.impl.ct.purdaily.action.rule.CheckChgReasonRule;
import nc.impl.ct.purdaily.action.rule.modify.CheckRowDeleteRule;
import nc.impl.ct.purdaily.action.rule.modify.PUModifyToleranceRule;
import nc.impl.ct.purdaily.action.rule.modify.PurdailyPayMnyCheckRule;
import nc.impl.ct.purdaily.action.rule.validate.InverseBlatestRule;
import nc.impl.ct.purdaily.base.ActionPlugInPoint;
import nc.impl.ct.rule.insert.CTCheckCTTypeRule;
import nc.impl.ct.rule.insert.CTNumAndOrigmnySum;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.ct.enumeration.CtFlowEnum;
import nc.vo.ct.enumeration.EnumModify;
import nc.vo.ct.pub.ICTEventType;
import nc.vo.ct.purdaily.entity.AggCtPuVO;
import nc.vo.ct.purdaily.entity.CtPuBVO;
import nc.vo.ct.purdaily.entity.CtPuExecVO;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.ct.purdaily.entity.CtScopeVo;
import nc.vo.ct.purdaily.entity.PayPlanVO;
import nc.vo.ct.rule.PurdailyEventCompareRule;
import nc.vo.hkjt.pub.SmPubFilesystem;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.util.ArrayUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b> 港华合同变更生效以及重走审批流需求
 * <ul>
 * <li>需求1：合同支持多次变更保存，变更保存的新版本合同为自由态，可以通过变更按钮进行修改，修改后仍为自由态。
 * 新版本合同未生效前，下游参照老版本生效合同进行拉单、回写和联查，新版本合同生效后则参照新版本合同进行拉单、回写和联查。
 * <li>需求2：新版本合同支持重走审批流。
 * <li>解决思路： 变更保存的场景分为两类: 一类是生效态的合同变更保存为自由态的新合同，这时需要向数据库插入一条新记录，单据状
 * 态为自由，变更状态为0（重走审批流），生成新主键，blatest为N，bshowlatest为Y，并对老记录加锁，设置bshowlatest为N。一类是
 * 自由态新合同变更保存，对这个新合同进行update处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.3
 * @since 6.0
 * @author liangchen1
 * @time 2013-5-20 下午04:17:24
 */
public class PurdailyModifySpAction {
  /**
   * 用户的确认信息
   */
  private PfUserObject userConfirm;

  /**
   * 方法功能描述：插入控制范围
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 上午10:55:38
   */
  public void insertCtCtrlScope(AggCtPuVO[] vos) {
    List<CtScopeVo> list = new ArrayList<CtScopeVo>();
    PuControlImpl conImpl = new PuControlImpl();
    for (int i = 0; i < vos.length; i++) {
      String id = vos[i].getParentVO().getPk_origct();
      CtScopeVo[] scopes = null;
      try {
        scopes = conImpl.queryCtScope(id);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
      if (!ArrayUtil.isEmpty(scopes)) {
        for (CtScopeVo o : scopes) {
          o.setPk_ct_pu(vos[i].getParentVO().getPk_ct_pu());
          o.setPk_ct_scope(null);
          list.add(o);
        }
      }
    }
    if (list.size() > 0) {
      conImpl.insertCtCtrlScope(list.toArray(new CtScopeVo[list.size()]));
    }
  }

  /**
   * 通过BillTransferTool工具获得界面的全vos和数据库中的原始vos,工具构造的过程中对vos的id进行了加锁
   * 判断变更保存是哪类场景，前面加锁对应两类场景都是加在了防止其他用户操作的id上 分别对两类场景进行处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          ,Confirm
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 下午04:39:00
   */
  public AggCtPuVO[] modify(AggCtPuVO[] vos, PfUserObject uConfirm,
      AggCtPuVO[] originBills) {
    this.userConfirm = uConfirm;
    // // 加锁 时间戳比较 使用工具类
    // BillTransferTool<AggCtPuVO> tool = new BillTransferTool<AggCtPuVO>(vos);
    AggCtPuVO[] cliVos = vos;
    AggCtPuVO[] orgVos = originBills;
    // 生效态的合同变更保存或者电商调价推式生成则是审批态的合同
    if (CtFlowEnum.VALIDATE.value().equals(
        cliVos[0].getParentVO().getFstatusflag())
        || CtFlowEnum.APPROVE.value().equals(
            cliVos[0].getParentVO().getFstatusflag())) {

      this.dealNewVos(cliVos);
      AggCtPuVO[] insertVos = this.saveNewVos(cliVos, orgVos);
      this.saveOriginVos(orgVos);
      
      /**
       * HK 2020年1月5日13:45:51
       * 获得变更 前、后的pk，将之前版本的附件信息，全部拷贝给新版本
       */
      try {
    	  
    	  String pk_1 = orgVos[0].getPrimaryKey();
    	  String pk_2 = insertVos[0].getPrimaryKey();
    	  Integer version = insertVos[0].getParentVO().getVersion().intValue();
    	  String versionStr = "XX";
    	  if (version < 10) 
    		  versionStr = "0" + version;
    	  else if (version < 100)
    		  versionStr = "" + version;
    	  
    	  BaseDAO dao = new BaseDAO();
    	  
    	  StringBuffer querySQL = 
    		new StringBuffer("select ")
    	  	.append(" f.creator ")
			.append(",f.dr ")
			.append(",f.filedesc ")
			.append(",f.filelength ")
			.append(",f.filepath ")
			.append(",f.filetype ")
			.append(",f.hashidx ")
			.append(",f.isdoc ")
			.append(",f.isfolder ")
			.append(",f.lasttime ")
			.append(",f.modifier ")
			.append(",f.modifytime ")
			.append(",f.pk ")
			.append(",f.pk_doc ")
			.append(",f.rootpath ")
			.append(",f.scantimes ")
			.append(",f.ts ")
			.append(" from sm_pub_filesystem f ")
			.append(" where ")
			.append(" f.filepath like '").append(pk_1).append("/%' ")
    	  ;
    	  
    	  List<SmPubFilesystem> list_1 = (ArrayList)dao.executeQuery(querySQL.toString(), new BeanListProcessor(SmPubFilesystem.class));
    	  List<SmPubFilesystem> list_2 = new ArrayList<>(list_1.size());
    	  
    	  for (SmPubFilesystem vo_1 : list_1) {
    		  SmPubFilesystem vo_2 = (SmPubFilesystem)vo_1.clone();
    		  
//    		 String file_pk_1 = vo_1.getPk();
//    		 String file_pk_2 = file_pk_1.substring(0,6) 
//    				 + versionStr
//    				 + file_pk_1.substring(8);
    		 
    		  String filePath_1 = vo_1.getFilepath();
    		  String filePath_2 = pk_2 
    				  + filePath_1.substring(20);
    		 
    		  vo_2.setPk(null);
    		  vo_2.setFilepath(filePath_2);
    		  
    		  list_2.add(vo_2);
    	  }
    	  
    	  String[] res = dao.insertVOList(list_2);
//    	  System.out.print(res);
    	  
      } catch (Exception ex) {
    	  ExceptionUtils.wrappBusinessException(ex.getMessage());
      }
      /***END***/
      
      // 使用伪列进行差异返回前台
      return insertVos;
    }
    // 自由态变更保存同修改一样，进行update就好，前面加锁也锁住了新版本的id
    AggCtPuVO[] updatedVos = this.saveUpdateVos(cliVos, orgVos);
    return updatedVos;
  }

  /**
   * 方法功能描述：查询最新生效的合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 上午10:55:38
   */
  public AggCtPuVO[] queryCtByVos(AggCtPuVO[] cliVos) {
    String[] ids = new String[cliVos.length];
    for (int i = 0; i < cliVos.length; i++) {
      ids[i] = cliVos[i].getParentVO().getPk_origct();
    }
    return new BillQuery<AggCtPuVO>(AggCtPuVO.class).query(ids);
  }

  /**
   * 方法功能描述：vo内部存的原始版本主键设置在主键位置 还原主键
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 上午10:55:38
   */
  public void replacePkAndOrig(AggCtPuVO[] cliVos) {
    for (int i = 0; i < cliVos.length; i++) {
      String pk_ct_pu = cliVos[i].getParentVO().getPk_ct_pu();
      String pk_origct = cliVos[i].getParentVO().getPk_origct();
      if (pk_ct_pu != pk_origct) {
        cliVos[i].getParentVO().setPk_ct_pu(pk_origct);
        cliVos[i].getParentVO().setPk_origct(pk_ct_pu);
        for (CtPuBVO bVo : cliVos[i].getCtPuBVO()) {
          // 变更保存容差控制bmap
          bVo.setPk_ct_pu(pk_origct);
          String pk_ct_pu_b = bVo.getPk_ct_pu_b();
          bVo.setPk_ct_pu_b(bVo.getPk_origctb());
          bVo.setPk_origctb(pk_ct_pu_b);
        }
      }
    }
  }

  /**
   * 方法功能描述：增加变更保存禁止删除物料行规则，增加反转最新版本规则，删除计划终止日期控制规则，删除回写规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 上午10:55:38
   */
  private void addRule(CompareAroundProcesser<AggCtPuVO> processer) {

    /** ********* 模板类调用的规则 开始 ********************** */
    // 检查主子表VO各个字段的长度
    processer.addBeforeRule(new SaveVOValidateRule<AggCtPuVO>());
    // 检查自定义项
    processer.addBeforeRule(new SelfDefItemChkRule<AggCtPuVO>());
    // 填充“修改人” “修改时间” 因为要保存到数据库，一定要放在保存前填充
    processer.addBeforeRule(new FillModifiedInfoRule<AggCtPuVO>());
    /** ********* 模板类调用的规则 结束 ********************** */

    /** *********** 采购合同修改规则 开始 ************** */
    // 变更保存禁止删除物料行（回写无法控制）
    processer.addBeforeRule(new CheckRowDeleteRule());
    // 合同这个规则在填充表头总数量总金额 、为空补0的同时，还会设置最新版本，所以在这之后得把最新版本重新设置为N
    processer.addBeforeRule(new CTNumAndOrigmnySum<AggCtPuVO>(CtPuBVO.class));
    // 抽出一个反转最新版本的规则
    processer.addBeforeRule(new InverseBlatestRule());
    // 检查行号
    processer.addBeforeRule(new CTRowNoCheckRule<AggCtPuVO>(CtPuBVO.class));
    // 非空检查
    processer.addBeforeRule(new PuNotNullChkRule());
    processer.addBeforeRule(new IsNullChkRule<AggCtPuVO>(CtPuBVO.class));
    // 交易类型
    processer.addBeforeRule(new PurdailyTypeChkRule());
    // 数值型检查
    processer.addBeforeRule(new NumValueChkRule<AggCtPuVO>());
    // 检查 “该合同所引用的类别已经被删除，不能保存”
    processer.addBeforeRule(new CTCheckCTTypeRule<AggCtPuVO>());
    // 总括订单检查规则
    processer.addBeforeRule(new CtPuBracketOrderCheckRule());
    // 原来是变更直接生效发送变更前事件，这里还是发送这些事件，具体事件触发的处理类与实际情况需要进一步测试
    processer.addBeforeRule(new PurdailyEventCompareRule(
        ICTEventType.TYPE_MODIFY_BEFORE));
    processer.addBeforeRule(new PurdailyEventCompareRule(
        ICTEventType.TYPE_MODIFYBYEC_BEFORE));
    // 原有合同规则，计划终止日期控制(只能变大，)根据需求，不再控制终止日期范围
    // processer.addBeforeRule(new InvalliDateChgChkRule<AggCtPuVO>());

    // 校验表体的变更原因-当前最新版本的原因非空
    processer.addBeforeRule(new CheckChgReasonRule());

    // 最高限价控制
    processer.addAfterRule(new MaxPriceRule());
    // 单价控制方式
    // processer.addAfterFinalRule(new ModifyPriceControlRule<AggCtPuVO>(
    // CtPuBVO.class, CtPuBVO.PK_CT_PU_B));
    // 变更前容差控制
    processer.addAfterRule(new PUModifyToleranceRule(CtPuBVO.class,
        this.userConfirm));
    // 变更时校验价税合计大于等于累计付款总额
    processer.addAfterRule(new PurdailyPayMnyCheckRule());
    // 原有合同规则，总括订单变更生效会回写来源采购合同，这里变更成自由态，去掉回写，生效处理的时候再回写总括订单
    // processer.addAfterRule(new PurdailyUpdateWriteBackRule());

    /** *********** 采购合同修改规则 结束 ************ */
    // 变更特殊规则 ****检查金额和数量是否超过已经执行的数量和金额****
    // processer.addAfterFinalRule(new CheckNumMnyRule(CtPuBVO.class));
    // 保留变更后事件处理
    processer.addAfterRule(new PurdailyEventCompareRule(
        ICTEventType.TYPE_MODIFY_AFTER));
    processer.addAfterRule(new PurdailyEventCompareRule(
        ICTEventType.TYPE_MODIFYBYEC_AFTER));
  }

  /**
   * 对cliVos进行处理，主要是清空主键，设置vostatus为new，设置单据状态为自由，变更状态为0，blatest为N
   * <p>
   * <b>参数说明</b>
   * 
   * @param cliVos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 下午04:39:00
   */
  private void dealNewVos(AggCtPuVO[] cliVos) {
    for (int i = 0; i < cliVos.length; i++) {
      // 合同表头
      CtPuVO voHead = cliVos[i].getParentVO();
      String userid = AppBsContext.getInstance().getPkUser();
      UFDate dateTime = AppBsContext.getInstance().getBusiDate();
      // 电商调价推式生成则是审批态的合同
      if (CtFlowEnum.APPROVE.value().equals(
          cliVos[0].getParentVO().getFstatusflag())) {
        voHead.setApprover(userid); // 重走审批流需要清空审批人、审批时间
        voHead.setTaudittime(dateTime);
        voHead.setFstatusflag(Integer.valueOf(CtFlowEnum.APPROVE.toIntValue()));
      }
      // 生效态合同变更时，审批人和审批时间清空
      else {
        voHead.setApprover(null);
        voHead.setTaudittime(null);
        voHead.setFstatusflag(Integer.valueOf(CtFlowEnum.Free.toIntValue()));
      }
      voHead.setStatus(VOStatus.NEW);
      voHead.setModifyStatus(Integer.valueOf(EnumModify.modify.toIntValue()));
      voHead.setPk_origct(voHead.getPk_ct_pu()); // 设置原始id，model更新时会用到
      voHead.setPk_ct_pu(null); // 清空主键、ts
      voHead.setTs(null);
      voHead.setActualvalidate(null);
      voHead.setBillmaker(userid);
      voHead.setDmakedate(dateTime);

      SuperVO[][] allChildren = cliVos[i].getAllChildren();
      for (SuperVO[] superVOs : allChildren) {
        if (ArrayUtil.isEmpty(superVOs)) {
          continue;
        }
        if (superVOs[0] instanceof CtPuBVO) {
          List<CtPuBVO> list = new ArrayList<CtPuBVO>();
          for (SuperVO vo : superVOs) {
            vo.setAttributeValue(CtPuBVO.PK_ORIGCTB,
                vo.getAttributeValue(CtPuBVO.PK_CT_PU_B)); // 表体记录原始版本pk，用于生效的时候把pk和累计执行量对过来
            // 将新版本插入数据库，所以对删除行进行过滤
            if (VOStatus.DELETED != vo.getStatus()) {
              list.add((CtPuBVO) vo);
            }
          }
          if (list.size() != superVOs.length) {
            cliVos[i].setCtPuBVO(list.toArray(new CtPuBVO[list.size()]));
          }
        }
        this.setBVO(superVOs);
      }
      // 新版本合同自由态不关联付款计划
      cliVos[i].setChildren(PayPlanVO.class, null);
    }
  }

  /**
   * 方法功能描述：对cliVos进行规则校验和填充，插入保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param cliVos
   *          , orgVos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 上午10:55:38
   */
  private AggCtPuVO[] saveNewVos(AggCtPuVO[] cliVos, AggCtPuVO[] orgVos) {
    CompareAroundProcesser<AggCtPuVO> processer =
        new CompareAroundProcesser<AggCtPuVO>(ActionPlugInPoint.MODIFYSP);
    /**
     * 比较规则，前规则会比较是否有删行，后规则会根据单价控制方式和容差比较单价等字段 生效态合同变更保存，orgVos为真实的老版本vo
     */
    this.replacePkAndOrig(cliVos);
    this.addRule(processer);
    processer.before(cliVos, orgVos);
    this.replacePkAndOrig(cliVos);
    AggCtPuVO[] insertVos = new BillInsert<AggCtPuVO>().insert(cliVos);
    this.replacePkAndOrig(cliVos);
    processer.after(cliVos, orgVos);
    this.replacePkAndOrig(insertVos);
    this.insertCtCtrlScope(insertVos);
    return insertVos;
  }

  /**
   * 方法功能描述：设置orgVos的bshowlatest为N，更新保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param orgVos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 上午10:55:38
   */
  private void saveOriginVos(AggCtPuVO[] orgVos) {
    AggCtPuVO[] orgVosClone = new AggCtPuVO[orgVos.length];
    for (int i = 0; i < orgVos.length; i++) {
      orgVosClone[i] = (AggCtPuVO) orgVos[i].clone();
      // 合同表头
      CtPuVO headVo = orgVosClone[i].getParentVO();
      headVo.setPk_origct(headVo.getPk_ct_pu());// 设置原始id
      headVo.setBshowLatest(UFBoolean.FALSE);
      headVo.setStatus(VOStatus.UPDATED);
      CtPuBVO[] bodyVos = orgVosClone[i].getCtPuBVO();
      for (CtPuBVO bVo : bodyVos) {
        bVo.setPk_origctb(bVo.getPk_ct_pu_b());
        bVo.setStatus(VOStatus.UPDATED);
      }
    }
    new BillUpdate<AggCtPuVO>().update(orgVosClone, orgVos);
  }

  /**
   * 方法功能描述：自由态合同变更保存，需要走规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param cliVos
   *          , orgVos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 上午10:55:38
   */
  private AggCtPuVO[] saveUpdateVos(AggCtPuVO[] cliVos, AggCtPuVO[] orgVos) {
    CompareAroundProcesser<AggCtPuVO> processer =
        new CompareAroundProcesser<AggCtPuVO>(null);
    /**
     * 比较规则，前规则会比较是否有删行，后规则会根据单价控制方式和容差比较单价等字段
     */
    // 需要处理取消生效-取消审批-变更保存的场景
    // 如果上一个执行过程是取消生效，需要查询新版本vo
    for (AggCtPuVO vo : cliVos) {
      // 变更后vo状态都是自由态，主要针对审批未通过的合同
      vo.getParentVO().setFstatusflag(
          Integer.valueOf(CtFlowEnum.Free.toIntValue()));
      CtPuExecVO[] execs = vo.getCtPuExecVO();
      if (!ArrayUtil.isEmpty(execs)) {
        // 得到上一次执行过程
        CtPuExecVO newest = execs[0];
        for (int i = 1; i < execs.length; i++) {
          if (execs[i].getStatus() != VOStatus.NEW
              && execs[i].getVexecdate().compareTo(newest.getVexecdate()) > 0) {
            newest = execs[i];
          }
        }
        // 最新的执行过程是否为取消生效
        if ("取消生效".equals(newest.getVexecflow())) {/* -=notranslate=- */
          vo.getParentVO().setPk_origct(vo.getPrimaryKey());
        }
      }
    }
    AggCtPuVO[] rlVos = this.queryCtByVos(cliVos);
    this.replacePkAndOrig(cliVos);

    this.addRule(processer);
    processer.before(cliVos, rlVos);
    this.replacePkAndOrig(cliVos);
    AggCtPuVO[] updatedVos = new BillUpdate<AggCtPuVO>().update(cliVos, orgVos);
    this.replacePkAndOrig(cliVos);
    processer.after(cliVos, rlVos);
    this.replacePkAndOrig(updatedVos);
    return updatedVos;
  }

  /**
   * 方法功能描述：清空表体每行的主键，设置状态为new
   * <p>
   * <b>参数说明</b>
   * 
   * @param voItems
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-1 上午10:55:38
   */
  private void setBVO(SuperVO[] voItems) {
    for (int j = 0; j < voItems.length; ++j) {
      voItems[j].setStatus(VOStatus.NEW);
      voItems[j].setPrimaryKey(null);
    }
  }
}
