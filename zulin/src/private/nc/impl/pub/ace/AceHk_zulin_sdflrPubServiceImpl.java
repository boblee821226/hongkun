package nc.impl.pub.ace;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrInsertBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrUpdateBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrDeleteBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrSendApproveBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrUnSendApproveBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrApproveBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.uap.pf.IplatFormEntry;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.arap.receivable.ReceivableBillItemVO;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.ct.saledaily.GenJftzdVO;
import nc.vo.hkjt.zulin.sdflr.SdflrBVO;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.vo.hkjt.zulin.sdflr.SdflrHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_sdflrPubServiceImpl {
	// ����
	public SdflrBillVO[] pubinsertBills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<SdflrBillVO> transferTool = new BillTransferTool<SdflrBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_zulin_sdflrInsertBP action = new AceHk_zulin_sdflrInsertBP();
			SdflrBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_zulin_sdflrDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public SdflrBillVO[] pubupdateBills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<SdflrBillVO> transferTool = new BillTransferTool<SdflrBillVO>(
					clientFullVOs);
			AceHk_zulin_sdflrUpdateBP bp = new AceHk_zulin_sdflrUpdateBP();
			SdflrBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public SdflrBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		SdflrBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<SdflrBillVO> query = new BillLazyQuery<SdflrBillVO>(
					SdflrBillVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

	// �ύ
	public SdflrBillVO[] pubsendapprovebills(
			SdflrBillVO[] clientFullVOs, SdflrBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_sdflrSendApproveBP bp = new AceHk_zulin_sdflrSendApproveBP();
		SdflrBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public SdflrBillVO[] pubunsendapprovebills(
			SdflrBillVO[] clientFullVOs, SdflrBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_sdflrUnSendApproveBP bp = new AceHk_zulin_sdflrUnSendApproveBP();
		SdflrBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public SdflrBillVO[] pubapprovebills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_sdflrApproveBP bp = new AceHk_zulin_sdflrApproveBP();
		SdflrBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		
		for(int i=0;i<retvos.length;i++)
		{// ����������������ֻ�е� ���״̬Ϊ1  �Ž����Ƶ� 
			SdflrBillVO billVO = retvos[i];
			Integer ibillstatus = billVO.getParentVO().getIbillstatus();
			if(ibillstatus==1)
			{
				this.genSdfYsd(billVO);	// �� ˮ���Ӧ�յ�
			}
		}
		
		return retvos;
	}

	// ����

	public SdflrBillVO[] pubunapprovebills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_sdflrUnApproveBP bp = new AceHk_zulin_sdflrUnApproveBP();
		SdflrBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

	/**
	 * ����� ˮ���Ӧ�յ�
	 */
	private void genSdfYsd(SdflrBillVO clientFullVO) throws BusinessException{
		
		SdflrBillVO billVO = clientFullVO;
		SdflrBVO[] 	  bVOs = (SdflrBVO[])billVO.getChildrenVO();
		SdflrHVO	   hVO = billVO.getParentVO();
		
		UFBoolean is_init = hVO.getIs_init();
		UFDate  busi_date = PuPubVO.getUFDate( hVO.getVdef03() );	// ��ͷ-Ӧ�ɷ�����
		
		if(is_init.booleanValue())
		{// ���Ϊ �ڳ������Ƶ�
			return;
		}
		
		// ���ݿͻ�����
		HashMap<String,ArrayList<SdflrBVO>> MAP = new HashMap<String,ArrayList<SdflrBVO>>();
		for(int i=0;i<bVOs.length;i++)
		{
			SdflrBVO bVO = bVOs[i];
			String key = bVO.getPk_cust();
			if(MAP.containsKey(key)){
				MAP.get(key).add(bVO);
			}else{
				ArrayList<SdflrBVO> value = new ArrayList<SdflrBVO>();
				value.add(bVO);
				MAP.put(key, value);
			}
		}
		
		IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		int totalNum = 0;	// �ɹ�������
		
		// ���ݿͻ��������  ����
		String[] keys = new String[MAP.size()];
		keys = MAP.keySet().toArray(keys);
		for(int keys_i=0;keys_i<keys.length;keys_i++)
		{
			UFDateTime now_dateTime = new UFDateTime();							// �Ƶ�ʱ�䣨�̶���
		    UFDate       login_date = AppContext.getInstance().getBusiDate();	// ��¼����
		    
		    String[] accperiod = getAccperiod( busi_date );
		    
		    String             year = accperiod[0];					// ����꣨����ҵ��������������
		    String           period = accperiod[1];					// ����£�����ҵ��������������
		    String          creator = InvocationInfoProxy.getInstance().getUserId();	// �Ƶ��ˣ��̶���
		    String      pk_busitype = "0001N510000000000SLQ";	// ҵ�����̣��̶���
		    String        billclass = "ys";						// ���ݴ��ࣨ�̶���
		    String      pk_billtype = "F0";						// �������ͱ��루�̶���
		    String     pk_tradetype = "F0-Cxx-01";				// Ӧ�����ͣ��̶���
		    String   pk_tradetypeid = "1001N51000000063ZZH4";	// �������ͣ��̶���
		    String      pk_currtype = "1002Z0100000000001K1";	// ���֣��̶���
		    String         pk_group = "0001N510000000000EGY";	// ���ţ��̶���
		    String           guojia = "0001Z010000000079UJJ";	// ���ң��̶���
		    String           pk_org = hVO.getPk_org();			// ��֯��ȡ����
		    String         pk_org_v = hVO.getPk_org_v();		// ��֯�汾��ȡ����
		    String      pk_customer = keys[keys_i];				// �ͻ���ȡ����
		    						    
		    AggReceivableBillVO aggvo = new AggReceivableBillVO();
		    ReceivableBillVO headVO = new ReceivableBillVO();
		    headVO.setAccessorynum(0);				// ��������
		    headVO.setBillclass( billclass );		// ���ݴ���
		    headVO.setPk_busitype( pk_busitype );	// ҵ������bd_busitype
		    headVO.setPk_billtype( pk_billtype );	// �������ͱ���
		    headVO.setPk_tradetype( pk_tradetype );	// Ӧ������code 
		    headVO.setPk_tradetypeid( pk_tradetypeid );	// ��������bd_billtype
		    
		    headVO.setApprovestatus(-1);		// ����״̬��-1=����̬��0=δͨ��̬��1=ͨ��̬��2=������̬��3=�ύ̬����
		    headVO.setBillstatus(-1);			// ����״̬��9=δȷ�ϣ�-1=���棬1=����ͨ����2=�����У�-99=�ݴ棬8=ǩ�֣���
		    headVO.setEffectstatus(0);			// ��Ч״̬��0=δ��Ч��10=����Ч����
		    
		    // ��Ҫ���� ����������  �����ݡ��ڼ�
		    headVO.setBillyear( year );			// ���ݻ�����
		    headVO.setBillperiod( period );		// ���ݻ���ڼ�
		    
		    headVO.setBilldate( login_date );		// ��������(��¼����)
		    headVO.setCreationtime( now_dateTime );	// ����ʱ��
		    headVO.setBillmaker( creator );			// �Ƶ���
		    headVO.setCreator( creator );			// ������
		    
		    headVO.setGloballocal(UFDouble.ZERO_DBL);	// ȫ�ֱ��ҽ��
		    headVO.setGrouplocal(UFDouble.ZERO_DBL);	// ���ű��ҽ��
		   
		    
		    headVO.setIsflowbill(UFBoolean.FALSE);		// �Ƿ����̵���
		    headVO.setIsinit(UFBoolean.FALSE);			// �ڳ���־
		    headVO.setIsreded(UFBoolean.FALSE);			// �Ƿ����
		    
		    headVO.setPk_currtype( pk_currtype );		// ����
		    headVO.setPk_group( pk_group );				// Pk_group
		    
		    headVO.setPk_fiorg( pk_org );		// ����������֯ 0001N510000000001SYX
		    headVO.setPk_fiorg_v( pk_org_v );	// ����������֯�汾 0001N510000000001SYW
		    headVO.setPk_org( pk_org );			// Ӧ�ղ�����֯
		    headVO.setPk_org_v( pk_org_v );		// Ӧ�ղ�����֯�汾
		    headVO.setSett_org( pk_org );		// ���������֯
		    headVO.setSett_org_v( pk_org_v );	// ���������֯�汾
		    
		    headVO.setSrc_syscode(0);		// ������Դϵͳ��0=Ӧ��ϵͳ��
		    headVO.setSyscode(0);			// ��������ϵͳ��0=Ӧ��ϵͳ��
		    
		    headVO.setSendcountryid( guojia );	// ������bd_countryzone
		    headVO.setTaxcountryid( guojia );	// ��˰��
		    
		    ArrayList<SdflrBVO> vo_list =  MAP.get(keys[keys_i]);
		    ReceivableBillItemVO[] itemVOs = new ReceivableBillItemVO[vo_list.size()];
		    
		    UFDouble total_jine = UFDouble.ZERO_DBL;	// �ϼƽ��
		    
		    for(int i=0;i<itemVOs.length;i++)
		    {
		    	SdflrBVO 		item_vo = vo_list.get(i);
			    UFDouble           jine = item_vo.getUse_mny();				// ���
			    String       pk_sdflr_b = item_vo.getPk_hk_zulin_sdflr_b();	// ¼�뵥 �ӱ�pk
			    String         pk_sdflr = item_vo.getPk_hk_zulin_sdflr();	// ¼�뵥 ����pk
			    // ժҪ = ��ͬ����+��������+��ʼ����
//			    String         scomment = item_vo.getVbillcode()+"��"+item_vo.getVdef01()+"��-"+item_vo.getJflx()+"-"+item_vo.getBusi_date();
		    	String scomment = "ˮ���Ӧ��";
			    
			    total_jine = total_jine.add(jine);
			    
		    	itemVOs[i] = new ReceivableBillItemVO();
		    	itemVOs[i].setBillclass( billclass );	// ���ݴ���
		    	itemVOs[i].setBilldate(login_date);		// ��������
		    	itemVOs[i].setBusidate(busi_date);		// ҵ������
		    	
		    	itemVOs[i].setBuysellflag(1);			// �������ͣ�1=�������ۣ�3=�������ۣ���
		    	itemVOs[i].setDirection(1);				// ����1=�跽��-1=��������
		    	itemVOs[i].setObjtype(0);				// ��������0=�ͻ���2=���ţ�3=ҵ��Ա�� ��
		    	
		    	itemVOs[i].setGlobalbalance(UFDouble.ZERO_DBL);		// ȫ�ֱ������ 
		    	itemVOs[i].setGlobaldebit(UFDouble.ZERO_DBL);		// ȫ�ֱ��ҽ��(�跽)
		    	itemVOs[i].setGlobalnotax_de(UFDouble.ZERO_DBL);	// ȫ�ֱ�����˰���(�跽)
		    	itemVOs[i].setGlobalrate(UFDouble.ZERO_DBL);		// ȫ�ֱ��һ���
		    	itemVOs[i].setGlobalnotax_de(UFDouble.ZERO_DBL);	// ȫ�ֱ�����˰���(�跽)
		    	itemVOs[i].setGroupbalance(UFDouble.ZERO_DBL);		// ���ű������
		    	itemVOs[i].setGroupdebit(UFDouble.ZERO_DBL);		// ���ű��ҽ��(�跽)
		    	itemVOs[i].setGroupnotax_de(UFDouble.ZERO_DBL);		// ���ű�����˰���(�跽)
		    	itemVOs[i].setGrouprate(UFDouble.ZERO_DBL);			// ���ű��һ���
		    	itemVOs[i].setGrouptax_de(UFDouble.ZERO_DBL);		// ??
		    	itemVOs[i].setLocal_tax_de(UFDouble.ZERO_DBL);		// ˰��-�跽
		    	itemVOs[i].setCaltaxmny( jine );			// ��˰���
		    	itemVOs[i].setLocal_money_bal( jine );		// ��֯�������
		    	itemVOs[i].setLocal_money_de( jine );		// ��֯���ҽ��-�跽
		    	itemVOs[i].setLocal_notax_de( jine );		// ��֯������˰���-�跽
		    	itemVOs[i].setMoney_bal( jine );			// ԭ�����
		    	itemVOs[i].setMoney_de( jine );				// �跽ԭ�ҽ�� 
		    	itemVOs[i].setNotax_de( jine );				// �跽ԭ����˰���
		    	itemVOs[i].setOccupationmny( jine );		// Ԥռ��ԭ�����
		    	
		    	itemVOs[i].setCustomer( pk_customer );			// �ͻ� 1001N510000000001UAQ
		    	itemVOs[i].setOrdercubasdoc( pk_customer );		// �����ͻ�
		    	
		    	itemVOs[i].setPausetransact(UFBoolean.FALSE);		// �����־
		    	itemVOs[i].setTriatradeflag(UFBoolean.FALSE);		// ����ó��
		    	
		    	itemVOs[i].setPk_billtype( pk_billtype );			// �������ͱ���
		    	itemVOs[i].setPk_currtype( pk_currtype );			// ����
		    	itemVOs[i].setPk_tradetype( pk_tradetype );			// Ӧ������code 
		    	itemVOs[i].setPk_tradetypeid( pk_tradetypeid );		// Ӧ������
		    	
		    	itemVOs[i].setPk_group( pk_group );		// pk_group
		    	itemVOs[i].setPk_fiorg( pk_org );		// ����������֯
		    	itemVOs[i].setPk_fiorg_v( pk_org_v );	// ����������֯�汾
		    	itemVOs[i].setPk_org( pk_org );			// Ӧ����֯
		    	itemVOs[i].setPk_org_v( pk_org_v );		// Ӧ����֯�汾
		    	itemVOs[i].setSett_org( pk_org );		// ������֯
		    	itemVOs[i].setSett_org_v( pk_org_v );	// ������֯�汾
		    	
		    	itemVOs[i].setRececountryid( guojia );				// �ջ���
		    	
		    	itemVOs[i].setRowno(-1);							// ���ݷ�¼��
		    	itemVOs[i].setTaxtype(1);							// ��˰���0=Ӧ˰�ں���1=Ӧ˰��ӣ���
		    	itemVOs[i].setQuantity_bal(UFDouble.ZERO_DBL);		// �������
		    	itemVOs[i].setRate(UFDouble.ONE_DBL);				// ��֯���һ���
		    	itemVOs[i].setTaxprice(item_vo.getPrice());			// ��˰����
		    	itemVOs[i].setTaxrate(UFDouble.ZERO_DBL);			// ˰��
		    	
		    	itemVOs[i].setDef9(item_vo.getPk_area());		// ����
		    	itemVOs[i].setDef8(item_vo.getPk_room());		// �����
		    	itemVOs[i].setDef1(item_vo.getPk_sfxm());		// �շ���Ŀ
		    	itemVOs[i].setDef11(item_vo.getPk_place());		// λ��
		    	itemVOs[i].setDef2(""+item_vo.getSccb_num());	// �ϴγ�����
		    	itemVOs[i].setDef6(""+item_vo.getBccb_num());	// ���γ�����
		    	itemVOs[i].setQuantity_de(item_vo.getUse_num());// ����������Ч��
		    	itemVOs[i].setDef5(""+item_vo.getUse_num());	// ����
		    	itemVOs[i].setDef7(""+item_vo.getTimes());		// ����
		    	itemVOs[i].setPrice(item_vo.getPrice());		// ����
		    	
		    	itemVOs[i].setDef3(hVO.getVdef01());		// ��ʼ����
		    	itemVOs[i].setDef4(hVO.getVdef02());		// ��ֹ����
		    	
		    	itemVOs[i].setDef30(pk_sdflr);		// ��ͬ����pk��¼�뵥����pk��
		    	itemVOs[i].setDef29(pk_sdflr_b);	// ��ͬ�ӱ�pk��¼�뵥�ӱ�pk��
		    	
		    	itemVOs[i].setScomment(scomment);		// ժҪ
		    }
		    
		    headVO.setLocal_money( total_jine );			// ��֯���ҽ��
		    headVO.setMoney( total_jine );					// ԭ�ҽ��
		    
		    aggvo.setParentVO(headVO);
		    aggvo.setChildrenVO(itemVOs);
		    
		    Object obj_return = iplatFormEntry.processAction("SAVE" , "F0" , null , aggvo , null , null);
		    
		    totalNum++;	// ����ɹ� +1
			
		}
		
	}
	
	/**
	   * �������� ���� ����ꡢ��
	   */
	  private String[] getAccperiod(UFDate date)
	  {
		  if(date==null) date = new UFDate();
		  
		  int  year = date.getYear();
		  int month = date.getMonth();
		  int   day = date.getDay();
		  
		  if(day>25)
		  {// ��� �� ����25 ��Ϊ�¸���
			  month++;
		  }
		  
		  if( month>12 )
		  {// ��� �� ����12 ����++����Ϊ1
			  year++;
			  month=1;
		  }
		  
		  return new String[]{
				  ""+year,
				  (month<10)?("0"+month):(""+month)
		  };
	  }
	
}