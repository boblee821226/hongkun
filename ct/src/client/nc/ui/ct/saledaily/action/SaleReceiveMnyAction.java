package nc.ui.ct.saledaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.ct.saledaily.ISaledailyMaintain;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.sfbase.client.ClientToolKit;
import nc.ui.ct.action.HelpAction;
import nc.ui.ct.saledaily.view.SaledailyBillForm;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.ct.rule.ActionStateRule;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.ct.uitl.ValueUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;

public class SaleReceiveMnyAction extends HelpAction {

  /**
   * 收款动作
   */
  private static final long serialVersionUID = 6677521014336967981L;

  private SaledailyBillForm cardForm = null;

  public SaleReceiveMnyAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.CT_RECEIVE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    AggCtSaleVO ctsaleVo = (AggCtSaleVO) this.getModel().getSelectedData();
    if (ctsaleVo == null || ValueUtil.isEmpty(ctsaleVo.getCtSaleBVO())) {
      return;
    }
    this.openGatherBillDlg(ctsaleVo);

  }

  public SaledailyBillForm getCardForm() {
    return this.cardForm;
  }

  public void setCardForm(SaledailyBillForm cardForm) {
    this.cardForm = cardForm;
  }

  /**
   * 方法功能描述：打开收款对话框。
   * 
   * @author wangfengd
   * @time 2010-7-14 下午02:08:32
   */
  private void openGatherBillDlg(AggCtSaleVO ctsaleVo) {
    // 收款单行的“贷方原币金额”=采销售合同行原币价税合计—累计原币收款金额
    try {

      if (!SysInitGroupQuery.isAREnabled()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0075")/*
                                                                     * @res
                                                                     * "应收模块未启用!"
                                                                     */);
      }
      else {
        AggregatedValueObject[] destVos =
            NCLocator.getInstance().lookup(ISaledailyMaintain.class)
                .makePaybill(new AggCtSaleVO[] {
                  ctsaleVo
                });
        
        /**
         * HK 2018年10月24日14:08:58
         * 获得 所选中的 合同明细数据,生成 收款单。
         */
        HashMap<String,String> MAP_row_pk = new HashMap<String,String>();
        int[] rows = this.getCardForm().getBillCardPanel().getBodyPanel().getTable().getSelectedRows();
        BillModel bModel = this.getCardForm().getBillCardPanel().getBillModel();
        if(rows!=null && rows.length>0)
        {
	        for(int i=0;i<rows.length;i++)
	        {
	        	String pk = PuPubVO.getString_TrimZeroLenAsNull( bModel.getValueAt(rows[i], "pk_ct_sale_b") );
	        	MAP_row_pk.put(pk, pk);
	        }
        }
        for( AggregatedValueObject billVO : destVos )
        {
        	GatheringBillItemVO[] bVOs_befor = (GatheringBillItemVO[])billVO.getChildrenVO();
        	Vector<GatheringBillItemVO> v_bVOs_after = new Vector<GatheringBillItemVO>();
        	for(GatheringBillItemVO bVO : bVOs_befor)
        	{
        		if(MAP_row_pk.containsKey(bVO.getSrc_itemid()))
        		{
        			v_bVOs_after.add(bVO);
        		}
        	}
        	GatheringBillItemVO[] bVOs_after = new GatheringBillItemVO[v_bVOs_after.size()];
        	bVOs_after = v_bVOs_after.toArray(bVOs_after);
        	
        	billVO.setChildrenVO(bVOs_after);
        }
        /***END***/
        
        FuncletInitData initData = null;
        initData = new FuncletInitData();
        initData.setInitType(ILinkType.LINK_TYPE_ADD);
        initData.setInitData(destVos);
        
        /**
         * 2018年8月28日17:18:50
         * 预收单 为 20060GBM404
         */
//        FuncRegisterVO funvo = WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060GBR");
//        FuncRegisterVO funvo = WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060GBM404");
        FuncRegisterVO funvo = WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060GBM403");
        
        // 修改单据类型为 预收单  （ select b.pk_billtypeid from bd_billtype b where b.billtypename = '预收单' ）
        IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 

        String pk_tradetypeid = "1001N5100000006E8MW5";
        String pk_tradetype = "F2-Cxx-01";
        
        for(int i=0;i<destVos.length;i++)
        {
//        	destVos[i].getParentVO().setAttributeValue("pk_tradetypeid", "1001N5100000006BNU3Q");
        	destVos[i].getParentVO().setAttributeValue("pk_tradetypeid", pk_tradetypeid);	// 新环境
        	destVos[i].getParentVO().setAttributeValue("pk_tradetype", pk_tradetype);		// 新环境
        	
        	/**
        	 * 赋值摘要 = 客户、期间、项目 （宏昆集团3月1日-31日房租）
        	 */
        	GatheringBillItemVO[] bVOs = (GatheringBillItemVO[])destVos[i].getChildrenVO();
        	for( GatheringBillItemVO bVO : bVOs )
        	{
        		bVO.setPk_tradetypeid(pk_tradetypeid);
        		bVO.setPk_tradetype(pk_tradetype);
        		
        		/**
        		 *  src_billid=1001ZZ100000006GIB1T
					src_billtype=Z3
					src_itemid=1001ZZ100000006GIB1Z
					src_tradetype=Z3-01
        		 */
        		String src_itemid = bVO.getSrc_itemid();	// 合同明细pk
        		
        		StringBuffer querySQL = 
        			new StringBuffer("select ")
        					.append(" cust.name||substr(sb.vbdef3,1,10)||'至'||substr(sb.vbdef4,1,10)||srxm.name zhaiyao ")
        					.append(",nvl(sb.norigtaxmny,0)-nvl(sb.noritotalgpmny,0) ye ")	// 价税合计 - 累计收款金额
        					.append(" from ct_sale s ")
        					.append(" inner join ct_sale_b sb on s.pk_ct_sale = sb.pk_ct_sale ")
        					.append(" left join bd_customer cust on s.pk_customer = cust.pk_customer ")
        					.append(" left join bd_defdoc srxm on sb.vbdef1 = srxm.pk_defdoc ")
        					.append(" where sb.pk_ct_sale_b = '"+src_itemid+"' ")
        		;
        		
        		List list = (List)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor());
        		if(list!=null&&list.size()>=1)
        		{
        			
        			Object[] obj = (Object[])list.get(0);
        			
        			String zhaiyao = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
        			bVO.setDef30(zhaiyao);	// 用表体自定义30来暂存 摘要，保存动作脚本时  正式存储（HK 2019年1月23日17:41:20）
        			
        			UFDouble ye = PuPubVO.getUFDouble_NullAsZero(obj[1]);	// 未收款余额
        			
        			bVO.setLocal_money_bal(ye);
        			bVO.setLocal_money_cr(ye);
        			bVO.setLocal_notax_cr(ye);
        			bVO.setMoney_bal(ye);
        			bVO.setMoney_cr(ye);
        			bVO.setNotax_cr(ye);
        			bVO.setOccupationmny(ye);
        		}
        	}
        }
        
        /***END***/
        
        java.awt.Dimension size = ClientToolKit.getUserClientSize();
        size.setSize(size.width * 0.9, size.height * 0.7);
        FuncletWindowLauncher.openFuncNodeDialog(WorkbenchEnvironment
            .getInstance().getWorkbench(), funvo, initData, null, true, true,
            size, true);

        // 对弹出框操作结束后，刷新界面当前操作的数据，其他数据保持不变
        ISaledailyMaintain service =
            NCLocator.getInstance().lookup(ISaledailyMaintain.class);
        String id = ctsaleVo.getParentVO().getPk_ct_sale();
        AggCtSaleVO[] vos = service.queryCtApVoByIds(new String[] {
          id
        });
        this.getModel().directlyUpdate(vos);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);

    }
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * 
   * @author wangfengd
   * @time 2010-6-30 下午02:12:49
   */
  // private AggCtSaleVO setGatherBillMoney(AggCtSaleVO ctsaleVo) {
  // AggCtSaleVO newctsaleVo = (AggCtSaleVO) ctsaleVo.clone();
  // CtSaleBVO[] pubVo = newctsaleVo.getCtSaleBVO();
  // if (pubVo == null || pubVo.length < 1) {
  // return null;
  // }
  // for (int i = 0; i < pubVo.length; i++) {
  // // 原币价税合计
  // UFDouble norigtaxmny = pubVo[i].getNorigtaxmny();
  // // 累计原币收款金额
  // UFDouble noritotalgpmny = pubVo[i].getNoritotalgpmny();
  // UFDouble payMoney = MathTool.sub(norigtaxmny, noritotalgpmny);
  // pubVo[i].setNorigtaxmny(payMoney);
  // }
  // newctsaleVo.setCtSaleBVO(pubVo);
  // return newctsaleVo;
  // }

  @Override
  protected boolean isActionEnable() {

    if (this.getModel().getSelectedOperaDatas() == null) {
      return false;
    }
    else if (this.getModel().getSelectedOperaDatas().length > 1) {
      return true;
    }
    else {
      ActionStateRule rule = new ActionStateRule();
      return rule.isHaveValidate(this.getModel().getSelectedData());
    }

  }

}
