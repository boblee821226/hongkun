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
   * �տ��
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
   * �����������������տ�Ի���
   * 
   * @author wangfengd
   * @time 2010-7-14 ����02:08:32
   */
  private void openGatherBillDlg(AggCtSaleVO ctsaleVo) {
    // �տ�еġ�����ԭ�ҽ�=�����ۺ�ͬ��ԭ�Ҽ�˰�ϼơ��ۼ�ԭ���տ���
    try {

      if (!SysInitGroupQuery.isAREnabled()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0075")/*
                                                                     * @res
                                                                     * "Ӧ��ģ��δ����!"
                                                                     */);
      }
      else {
        AggregatedValueObject[] destVos =
            NCLocator.getInstance().lookup(ISaledailyMaintain.class)
                .makePaybill(new AggCtSaleVO[] {
                  ctsaleVo
                });
        
        /**
         * HK 2018��10��24��14:08:58
         * ��� ��ѡ�е� ��ͬ��ϸ����,���� �տ��
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
         * 2018��8��28��17:18:50
         * Ԥ�յ� Ϊ 20060GBM404
         */
//        FuncRegisterVO funvo = WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060GBR");
//        FuncRegisterVO funvo = WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060GBM404");
        FuncRegisterVO funvo = WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060GBM403");
        
        // �޸ĵ�������Ϊ Ԥ�յ�  �� select b.pk_billtypeid from bd_billtype b where b.billtypename = 'Ԥ�յ�' ��
        IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 

        String pk_tradetypeid = "1001N5100000006E8MW5";
        String pk_tradetype = "F2-Cxx-01";
        
        for(int i=0;i<destVos.length;i++)
        {
//        	destVos[i].getParentVO().setAttributeValue("pk_tradetypeid", "1001N5100000006BNU3Q");
        	destVos[i].getParentVO().setAttributeValue("pk_tradetypeid", pk_tradetypeid);	// �»���
        	destVos[i].getParentVO().setAttributeValue("pk_tradetype", pk_tradetype);		// �»���
        	
        	/**
        	 * ��ֵժҪ = �ͻ����ڼ䡢��Ŀ ����������3��1��-31�շ��⣩
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
        		String src_itemid = bVO.getSrc_itemid();	// ��ͬ��ϸpk
        		
        		StringBuffer querySQL = 
        			new StringBuffer("select ")
        					.append(" cust.name||substr(sb.vbdef3,1,10)||'��'||substr(sb.vbdef4,1,10)||srxm.name zhaiyao ")
        					.append(",nvl(sb.norigtaxmny,0)-nvl(sb.noritotalgpmny,0) ye ")	// ��˰�ϼ� - �ۼ��տ���
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
        			bVO.setDef30(zhaiyao);	// �ñ����Զ���30���ݴ� ժҪ�����涯���ű�ʱ  ��ʽ�洢��HK 2019��1��23��17:41:20��
        			
        			UFDouble ye = PuPubVO.getUFDouble_NullAsZero(obj[1]);	// δ�տ����
        			
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

        // �Ե��������������ˢ�½��浱ǰ���������ݣ��������ݱ��ֲ���
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
   * ����������������Ҫ�����������Ĺ��ܡ�
   * 
   * @author wangfengd
   * @time 2010-6-30 ����02:12:49
   */
  // private AggCtSaleVO setGatherBillMoney(AggCtSaleVO ctsaleVo) {
  // AggCtSaleVO newctsaleVo = (AggCtSaleVO) ctsaleVo.clone();
  // CtSaleBVO[] pubVo = newctsaleVo.getCtSaleBVO();
  // if (pubVo == null || pubVo.length < 1) {
  // return null;
  // }
  // for (int i = 0; i < pubVo.length; i++) {
  // // ԭ�Ҽ�˰�ϼ�
  // UFDouble norigtaxmny = pubVo[i].getNorigtaxmny();
  // // �ۼ�ԭ���տ���
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
