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
 * <b>������Ҫ������¹��ܣ�</b> �ۻ���ͬ�����Ч�Լ���������������
 * <ul>
 * <li>����1����֧ͬ�ֶ�α�����棬���������°汾��ͬΪ����̬������ͨ�������ť�����޸ģ��޸ĺ���Ϊ����̬��
 * �°汾��ͬδ��Чǰ�����β����ϰ汾��Ч��ͬ������������д�����飬�°汾��ͬ��Ч��������°汾��ͬ������������д�����顣
 * <li>����2���°汾��֧ͬ��������������
 * <li>���˼·�� �������ĳ�����Ϊ����: һ������Ч̬�ĺ�ͬ�������Ϊ����̬���º�ͬ����ʱ��Ҫ�����ݿ����һ���¼�¼������״
 * ̬Ϊ���ɣ����״̬Ϊ0����������������������������blatestΪN��bshowlatestΪY�������ϼ�¼����������bshowlatestΪN��һ����
 * ����̬�º�ͬ������棬������º�ͬ����update����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.3
 * @since 6.0
 * @author liangchen1
 * @time 2013-5-20 ����04:17:24
 */
public class PurdailyModifySpAction {
  /**
   * �û���ȷ����Ϣ
   */
  private PfUserObject userConfirm;

  /**
   * ��������������������Ʒ�Χ
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 ����10:55:38
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
   * ͨ��BillTransferTool���߻�ý����ȫvos�����ݿ��е�ԭʼvos,���߹���Ĺ����ж�vos��id�����˼���
   * �жϱ�����������ೡ����ǰ�������Ӧ���ೡ�����Ǽ����˷�ֹ�����û�������id�� �ֱ�����ೡ�����д���
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          ,Confirm
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 ����04:39:00
   */
  public AggCtPuVO[] modify(AggCtPuVO[] vos, PfUserObject uConfirm,
      AggCtPuVO[] originBills) {
    this.userConfirm = uConfirm;
    // // ���� ʱ����Ƚ� ʹ�ù�����
    // BillTransferTool<AggCtPuVO> tool = new BillTransferTool<AggCtPuVO>(vos);
    AggCtPuVO[] cliVos = vos;
    AggCtPuVO[] orgVos = originBills;
    // ��Ч̬�ĺ�ͬ���������ߵ��̵�����ʽ������������̬�ĺ�ͬ
    if (CtFlowEnum.VALIDATE.value().equals(
        cliVos[0].getParentVO().getFstatusflag())
        || CtFlowEnum.APPROVE.value().equals(
            cliVos[0].getParentVO().getFstatusflag())) {

      this.dealNewVos(cliVos);
      AggCtPuVO[] insertVos = this.saveNewVos(cliVos, orgVos);
      this.saveOriginVos(orgVos);
      
      /**
       * HK 2020��1��5��13:45:51
       * ��ñ�� ǰ�����pk����֮ǰ�汾�ĸ�����Ϣ��ȫ���������°汾
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
      
      // ʹ��α�н��в��췵��ǰ̨
      return insertVos;
    }
    // ����̬�������ͬ�޸�һ��������update�ͺã�ǰ�����Ҳ��ס���°汾��id
    AggCtPuVO[] updatedVos = this.saveUpdateVos(cliVos, orgVos);
    return updatedVos;
  }

  /**
   * ����������������ѯ������Ч�ĺ�ͬ
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 ����10:55:38
   */
  public AggCtPuVO[] queryCtByVos(AggCtPuVO[] cliVos) {
    String[] ids = new String[cliVos.length];
    for (int i = 0; i < cliVos.length; i++) {
      ids[i] = cliVos[i].getParentVO().getPk_origct();
    }
    return new BillQuery<AggCtPuVO>(AggCtPuVO.class).query(ids);
  }

  /**
   * ��������������vo�ڲ����ԭʼ�汾��������������λ�� ��ԭ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 ����10:55:38
   */
  public void replacePkAndOrig(AggCtPuVO[] cliVos) {
    for (int i = 0; i < cliVos.length; i++) {
      String pk_ct_pu = cliVos[i].getParentVO().getPk_ct_pu();
      String pk_origct = cliVos[i].getParentVO().getPk_origct();
      if (pk_ct_pu != pk_origct) {
        cliVos[i].getParentVO().setPk_ct_pu(pk_origct);
        cliVos[i].getParentVO().setPk_origct(pk_ct_pu);
        for (CtPuBVO bVo : cliVos[i].getCtPuBVO()) {
          // ��������ݲ����bmap
          bVo.setPk_ct_pu(pk_origct);
          String pk_ct_pu_b = bVo.getPk_ct_pu_b();
          bVo.setPk_ct_pu_b(bVo.getPk_origctb());
          bVo.setPk_origctb(pk_ct_pu_b);
        }
      }
    }
  }

  /**
   * �����������������ӱ�������ֹɾ�������й������ӷ�ת���°汾����ɾ���ƻ���ֹ���ڿ��ƹ���ɾ����д����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 ����10:55:38
   */
  private void addRule(CompareAroundProcesser<AggCtPuVO> processer) {

    /** ********* ģ������õĹ��� ��ʼ ********************** */
    // ������ӱ�VO�����ֶεĳ���
    processer.addBeforeRule(new SaveVOValidateRule<AggCtPuVO>());
    // ����Զ�����
    processer.addBeforeRule(new SelfDefItemChkRule<AggCtPuVO>());
    // ��䡰�޸��ˡ� ���޸�ʱ�䡱 ��ΪҪ���浽���ݿ⣬һ��Ҫ���ڱ���ǰ���
    processer.addBeforeRule(new FillModifiedInfoRule<AggCtPuVO>());
    /** ********* ģ������õĹ��� ���� ********************** */

    /** *********** �ɹ���ͬ�޸Ĺ��� ��ʼ ************** */
    // ��������ֹɾ�������У���д�޷����ƣ�
    processer.addBeforeRule(new CheckRowDeleteRule());
    // ��ͬ�������������ͷ�������ܽ�� ��Ϊ�ղ�0��ͬʱ�������������°汾����������֮��ð����°汾��������ΪN
    processer.addBeforeRule(new CTNumAndOrigmnySum<AggCtPuVO>(CtPuBVO.class));
    // ���һ����ת���°汾�Ĺ���
    processer.addBeforeRule(new InverseBlatestRule());
    // ����к�
    processer.addBeforeRule(new CTRowNoCheckRule<AggCtPuVO>(CtPuBVO.class));
    // �ǿռ��
    processer.addBeforeRule(new PuNotNullChkRule());
    processer.addBeforeRule(new IsNullChkRule<AggCtPuVO>(CtPuBVO.class));
    // ��������
    processer.addBeforeRule(new PurdailyTypeChkRule());
    // ��ֵ�ͼ��
    processer.addBeforeRule(new NumValueChkRule<AggCtPuVO>());
    // ��� ���ú�ͬ�����õ�����Ѿ���ɾ�������ܱ��桱
    processer.addBeforeRule(new CTCheckCTTypeRule<AggCtPuVO>());
    // ��������������
    processer.addBeforeRule(new CtPuBracketOrderCheckRule());
    // ԭ���Ǳ��ֱ����Ч���ͱ��ǰ�¼������ﻹ�Ƿ�����Щ�¼��������¼������Ĵ�������ʵ�������Ҫ��һ������
    processer.addBeforeRule(new PurdailyEventCompareRule(
        ICTEventType.TYPE_MODIFY_BEFORE));
    processer.addBeforeRule(new PurdailyEventCompareRule(
        ICTEventType.TYPE_MODIFYBYEC_BEFORE));
    // ԭ�к�ͬ���򣬼ƻ���ֹ���ڿ���(ֻ�ܱ��)�������󣬲��ٿ�����ֹ���ڷ�Χ
    // processer.addBeforeRule(new InvalliDateChgChkRule<AggCtPuVO>());

    // У�����ı��ԭ��-��ǰ���°汾��ԭ��ǿ�
    processer.addBeforeRule(new CheckChgReasonRule());

    // ����޼ۿ���
    processer.addAfterRule(new MaxPriceRule());
    // ���ۿ��Ʒ�ʽ
    // processer.addAfterFinalRule(new ModifyPriceControlRule<AggCtPuVO>(
    // CtPuBVO.class, CtPuBVO.PK_CT_PU_B));
    // ���ǰ�ݲ����
    processer.addAfterRule(new PUModifyToleranceRule(CtPuBVO.class,
        this.userConfirm));
    // ���ʱУ���˰�ϼƴ��ڵ����ۼƸ����ܶ�
    processer.addAfterRule(new PurdailyPayMnyCheckRule());
    // ԭ�к�ͬ�����������������Ч���д��Դ�ɹ���ͬ��������������̬��ȥ����д����Ч�����ʱ���ٻ�д��������
    // processer.addAfterRule(new PurdailyUpdateWriteBackRule());

    /** *********** �ɹ���ͬ�޸Ĺ��� ���� ************ */
    // ���������� ****�����������Ƿ񳬹��Ѿ�ִ�е������ͽ��****
    // processer.addAfterFinalRule(new CheckNumMnyRule(CtPuBVO.class));
    // ����������¼�����
    processer.addAfterRule(new PurdailyEventCompareRule(
        ICTEventType.TYPE_MODIFY_AFTER));
    processer.addAfterRule(new PurdailyEventCompareRule(
        ICTEventType.TYPE_MODIFYBYEC_AFTER));
  }

  /**
   * ��cliVos���д�����Ҫ���������������vostatusΪnew�����õ���״̬Ϊ���ɣ����״̬Ϊ0��blatestΪN
   * <p>
   * <b>����˵��</b>
   * 
   * @param cliVos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-18 ����04:39:00
   */
  private void dealNewVos(AggCtPuVO[] cliVos) {
    for (int i = 0; i < cliVos.length; i++) {
      // ��ͬ��ͷ
      CtPuVO voHead = cliVos[i].getParentVO();
      String userid = AppBsContext.getInstance().getPkUser();
      UFDate dateTime = AppBsContext.getInstance().getBusiDate();
      // ���̵�����ʽ������������̬�ĺ�ͬ
      if (CtFlowEnum.APPROVE.value().equals(
          cliVos[0].getParentVO().getFstatusflag())) {
        voHead.setApprover(userid); // ������������Ҫ��������ˡ�����ʱ��
        voHead.setTaudittime(dateTime);
        voHead.setFstatusflag(Integer.valueOf(CtFlowEnum.APPROVE.toIntValue()));
      }
      // ��Ч̬��ͬ���ʱ�������˺�����ʱ�����
      else {
        voHead.setApprover(null);
        voHead.setTaudittime(null);
        voHead.setFstatusflag(Integer.valueOf(CtFlowEnum.Free.toIntValue()));
      }
      voHead.setStatus(VOStatus.NEW);
      voHead.setModifyStatus(Integer.valueOf(EnumModify.modify.toIntValue()));
      voHead.setPk_origct(voHead.getPk_ct_pu()); // ����ԭʼid��model����ʱ���õ�
      voHead.setPk_ct_pu(null); // ���������ts
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
                vo.getAttributeValue(CtPuBVO.PK_CT_PU_B)); // �����¼ԭʼ�汾pk��������Ч��ʱ���pk���ۼ�ִ�����Թ���
            // ���°汾�������ݿ⣬���Զ�ɾ���н��й���
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
      // �°汾��ͬ����̬����������ƻ�
      cliVos[i].setChildren(PayPlanVO.class, null);
    }
  }

  /**
   * ����������������cliVos���й���У�����䣬���뱣��
   * <p>
   * <b>����˵��</b>
   * 
   * @param cliVos
   *          , orgVos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 ����10:55:38
   */
  private AggCtPuVO[] saveNewVos(AggCtPuVO[] cliVos, AggCtPuVO[] orgVos) {
    CompareAroundProcesser<AggCtPuVO> processer =
        new CompareAroundProcesser<AggCtPuVO>(ActionPlugInPoint.MODIFYSP);
    /**
     * �ȽϹ���ǰ�����Ƚ��Ƿ���ɾ�У���������ݵ��ۿ��Ʒ�ʽ���ݲ�Ƚϵ��۵��ֶ� ��Ч̬��ͬ������棬orgVosΪ��ʵ���ϰ汾vo
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
   * ������������������orgVos��bshowlatestΪN�����±���
   * <p>
   * <b>����˵��</b>
   * 
   * @param orgVos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 ����10:55:38
   */
  private void saveOriginVos(AggCtPuVO[] orgVos) {
    AggCtPuVO[] orgVosClone = new AggCtPuVO[orgVos.length];
    for (int i = 0; i < orgVos.length; i++) {
      orgVosClone[i] = (AggCtPuVO) orgVos[i].clone();
      // ��ͬ��ͷ
      CtPuVO headVo = orgVosClone[i].getParentVO();
      headVo.setPk_origct(headVo.getPk_ct_pu());// ����ԭʼid
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
   * ������������������̬��ͬ������棬��Ҫ�߹���
   * <p>
   * <b>����˵��</b>
   * 
   * @param cliVos
   *          , orgVos
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-3 ����10:55:38
   */
  private AggCtPuVO[] saveUpdateVos(AggCtPuVO[] cliVos, AggCtPuVO[] orgVos) {
    CompareAroundProcesser<AggCtPuVO> processer =
        new CompareAroundProcesser<AggCtPuVO>(null);
    /**
     * �ȽϹ���ǰ�����Ƚ��Ƿ���ɾ�У���������ݵ��ۿ��Ʒ�ʽ���ݲ�Ƚϵ��۵��ֶ�
     */
    // ��Ҫ����ȡ����Ч-ȡ������-�������ĳ���
    // �����һ��ִ�й�����ȡ����Ч����Ҫ��ѯ�°汾vo
    for (AggCtPuVO vo : cliVos) {
      // �����vo״̬��������̬����Ҫ�������δͨ���ĺ�ͬ
      vo.getParentVO().setFstatusflag(
          Integer.valueOf(CtFlowEnum.Free.toIntValue()));
      CtPuExecVO[] execs = vo.getCtPuExecVO();
      if (!ArrayUtil.isEmpty(execs)) {
        // �õ���һ��ִ�й���
        CtPuExecVO newest = execs[0];
        for (int i = 1; i < execs.length; i++) {
          if (execs[i].getStatus() != VOStatus.NEW
              && execs[i].getVexecdate().compareTo(newest.getVexecdate()) > 0) {
            newest = execs[i];
          }
        }
        // ���µ�ִ�й����Ƿ�Ϊȡ����Ч
        if ("ȡ����Ч".equals(newest.getVexecflow())) {/* -=notranslate=- */
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
   * ����������������ձ���ÿ�е�����������״̬Ϊnew
   * <p>
   * <b>����˵��</b>
   * 
   * @param voItems
   *          <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-6-1 ����10:55:38
   */
  private void setBVO(SuperVO[] voItems) {
    for (int j = 0; j < voItems.length; ++j) {
      voItems[j].setStatus(VOStatus.NEW);
      voItems[j].setPrimaryKey(null);
    }
  }
}
