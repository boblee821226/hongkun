package nc.ui.hkjt.zulin.znjjs.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IplatFormEntry;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.RefreshSingleAction;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.arap.receivable.ReceivableBillItemVO;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

/**
 * �������ɽ�Ӧ�յ�
 *
 */
public class ScZnjysdAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public ScZnjysdAction() {
		setBtnName("�������ɽ�Ӧ�յ�");
		setCode("scZnjysdAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction addLineAction;
	private RefreshSingleAction refreshCardAction;

	public ShowUpableBillListView getListview() {
		return listview;
	}

	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	public BodyAddLineAction getAddLineAction() {
		return addLineAction;
	}

	public void setAddLineAction(BodyAddLineAction addLineAction) {
		this.addLineAction = addLineAction;
	}

	public RefreshSingleAction getRefreshCardAction() {
		return refreshCardAction;
	}

	public void setRefreshCardAction(RefreshSingleAction refreshCardAction) {
		this.refreshCardAction = refreshCardAction;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		if(this.getListview().isShowing()){
			MessageDialog.showErrorDlg(this.getListview(), "", "����뿨Ƭ���� ����");
			return;
		}
		
		int[] selectRows = this.getEditor().getBillCardPanel().getBillTable().getSelectedRows();
		
		if(selectRows==null || selectRows.length<=0){
			MessageDialog.showErrorDlg(this.getEditor(), "", "��ѡ���������");
			return;
		}
		
		// key��pk_cust
		HashMap<String,ArrayList<ZnjjsBVO>> MAP = new HashMap<String,ArrayList<ZnjjsBVO>>();
		
		for(int i=0;i<selectRows.length;i++){
			int row = selectRows[i];
			UFDouble ys_mny = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "vbdef02") );
			if(ys_mny!=null){
				// ��������� �Ѿ����� Ӧ�ս�˵���Ѿ��Ƶ�  ��������
				continue;
			}
			UFDouble yq_mny = PuPubVO.getUFDouble_NullAsZero( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_mny") );
			UFDouble jm_mny = PuPubVO.getUFDouble_NullAsZero( getEditor().getBillCardPanel().getBodyValueAt(row, "vbdef01") );
			ys_mny = yq_mny.sub(jm_mny);	// Ӧ�ս�� = ���ڽ��-������
			if(ys_mny.compareTo(UFDouble.ZERO_DBL)==0){
				// ���û�� Ӧ�ս��  ������
				continue;
			}
			
			String pk_cust  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_cust") );
			String pk_area  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_area") );
			String pk_room  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_room") );
			String ht_code  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "ht_code") );
			String ht_rowno = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "ht_rowno") );
			UFDate jf_date  = PuPubVO.getUFDate( getEditor().getBillCardPanel().getBodyValueAt(row, "jf_date") );
			UFDouble jf_mny = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "jf_mny") );
			UFDouble yq_num = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_num") );
			UFDouble yq_bl  = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_bl") );
			String pk_znjjs_b = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_hk_zulin_znjjs_b") );
			String pk_znjjs   = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_hk_zulin_znjjs") );
			
			ZnjjsBVO bvo = new ZnjjsBVO();
			bvo.setPk_cust(pk_cust);
			bvo.setPk_area(pk_area);
			bvo.setPk_room(pk_room);
			bvo.setHt_code(ht_code);
			bvo.setHt_rowno(ht_rowno);
			bvo.setJf_date(jf_date);
			bvo.setJf_mny(jf_mny);
			bvo.setYq_num(yq_num);
			bvo.setYq_bl(yq_bl);
			bvo.setYq_mny(yq_mny);
			bvo.setVbdef02(ys_mny.toString());	// Ӧ�ս��
			bvo.setPk_hk_zulin_znjjs(pk_znjjs);
			bvo.setPk_hk_zulin_znjjs_b(pk_znjjs_b);
			
			if(MAP.containsKey(pk_cust)){
				MAP.get(pk_cust).add(bvo);
			}else{
				ArrayList<ZnjjsBVO> value = new ArrayList<ZnjjsBVO>();
				value.add(bvo);
				MAP.put(pk_cust, value);
			}
		}
		
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject() );	// ��֯
	    String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel().getHeadItem("pk_org_v").getValueObject() );	// ��֯�汾
		
		IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		int totalNum = 0;	// �ɹ�������
		
		// ���ݿͻ��������  ����
		String[] keys = new String[MAP.size()];
		keys = MAP.keySet().toArray(keys);
		for(int keys_i=0;keys_i<keys.length;keys_i++)
		{
			UFDateTime now_dateTime = new UFDateTime();							// �Ƶ�ʱ�䣨�̶���
		    UFDate        busi_date = AppContext.getInstance().getBusiDate();	// ҵ������ ȡ ��¼����
		    
		    String[] accperiod = getAccperiod( busi_date );
		    
		    String             year = accperiod[0];					// ����꣨����ҵ��������������
		    String           period = accperiod[1];					// ����£�����ҵ��������������
		    String          creator = InvocationInfoProxy.getInstance().getUserId();	// �Ƶ��ˣ��̶���
		    String      pk_busitype = "0001N510000000000SLQ";	// ҵ�����̣��̶���
		    String        billclass = "ys";						// ���ݴ��ࣨ�̶���
		    String      pk_billtype = "F0";						// �������ͱ��루�̶���
		    String     pk_tradetype = "F0-Cxx-05";				// Ӧ�����ͣ��̶�������Ҫ���䣩
		    String   pk_tradetypeid = "1001N51000000086BKRS";	// �������ͣ��̶�������Ҫ���䣩
		    String      pk_currtype = "1002Z0100000000001K1";	// ���֣��̶���
		    String         pk_group = "0001N510000000000EGY";	// ���ţ��̶���
		    String           guojia = "0001Z010000000079UJJ";	// ���ң��̶���
		    String      pk_customer = keys[keys_i];				// �ͻ���ȡ����
		    						    
		    AggReceivableBillVO aggvo = new AggReceivableBillVO();
		    ReceivableBillVO headVO = new ReceivableBillVO();
		    headVO.setAccessorynum(0);					// ��������
		    headVO.setBillclass( billclass );			// ���ݴ���
		    headVO.setPk_busitype( pk_busitype );		// ҵ������bd_busitype
		    headVO.setPk_billtype( pk_billtype );		// �������ͱ���
		    headVO.setPk_tradetype( pk_tradetype );		// Ӧ������code 
		    headVO.setPk_tradetypeid( pk_tradetypeid );	// ��������bd_billtype
		    
		    headVO.setApprovestatus(-1);		// ����״̬��-1=����̬��0=δͨ��̬��1=ͨ��̬��2=������̬��3=�ύ̬����
		    headVO.setBillstatus(-1);			// ����״̬��9=δȷ�ϣ�-1=���棬1=����ͨ����2=�����У�-99=�ݴ棬8=ǩ�֣���
		    headVO.setEffectstatus(0);			// ��Ч״̬��0=δ��Ч��10=����Ч����
		    
		    // ��Ҫ���� ����������  �����ݡ��ڼ�
		    headVO.setBillyear( year );			// ���ݻ�����
		    headVO.setBillperiod( period );		// ���ݻ���ڼ�
		    
		    headVO.setBilldate( busi_date );		// ��������
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
		    
		    headVO.setPk_fiorg( pk_org );		// ����������֯
		    headVO.setPk_fiorg_v( pk_org_v );	// ����������֯�汾
		    headVO.setPk_org( pk_org );			// Ӧ�ղ�����֯
		    headVO.setPk_org_v( pk_org_v );		// Ӧ�ղ�����֯�汾
		    headVO.setSett_org( pk_org );		// ���������֯
		    headVO.setSett_org_v( pk_org_v );	// ���������֯�汾
		    
		    headVO.setSrc_syscode(0);		// ������Դϵͳ��0=Ӧ��ϵͳ��
		    headVO.setSyscode(0);			// ��������ϵͳ��0=Ӧ��ϵͳ��
		    
		    headVO.setSendcountryid( guojia );	// ������bd_countryzone
		    headVO.setTaxcountryid( guojia );	// ��˰��
		    
		    ArrayList<ZnjjsBVO> vo_list =  MAP.get(keys[keys_i]);
		    ReceivableBillItemVO[] itemVOs = new ReceivableBillItemVO[vo_list.size()];
		    
		    UFDouble total_jine = UFDouble.ZERO_DBL;	// �ϼƽ��
		    
		    for(int i=0;i<itemVOs.length;i++)
		    {
		    	ZnjjsBVO 	item_vo = vo_list.get(i);
		    	UFDouble       jine = PuPubVO.getUFDouble_NullAsZero(item_vo.getVbdef02());// Ӧ�ս��
			    String   pk_znjjs_b = item_vo.getPk_hk_zulin_znjjs_b();	// ���ɽ� �ӱ�pk
			    String     pk_znjjs = item_vo.getPk_hk_zulin_znjjs();	// ���ɽ� ����pk
			    // ժҪ = ��ͬ����+��������+��ʼ����
//			    String         scomment = item_vo.getVbillcode()+"��"+item_vo.getVdef01()+"��-"+item_vo.getJflx()+"-"+item_vo.getBusi_date();
		    	String scomment = "���ɽ�Ӧ��";
			    
			    total_jine = total_jine.add(jine);
			    
		    	itemVOs[i] = new ReceivableBillItemVO();
		    	itemVOs[i].setBillclass( billclass );	// ���ݴ���
		    	itemVOs[i].setBilldate(busi_date);		// ��������
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
		    	
		    	itemVOs[i].setCustomer( pk_customer );			// �ͻ�
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
		    	itemVOs[i].setTaxprice(UFDouble.ZERO_DBL);			// ��˰����
		    	itemVOs[i].setTaxrate(UFDouble.ZERO_DBL);			// ˰��
		    	
		    	itemVOs[i].setDef9(item_vo.getPk_area());		// ����
		    	itemVOs[i].setDef8(item_vo.getPk_room());		// �����
		    	itemVOs[i].setDef1(item_vo.getPk_sfxm());		// �շ���Ŀ
//		    	itemVOs[i].setDef11(item_vo.getPk_place());		// λ��
//		    	itemVOs[i].setDef2(""+item_vo.getSccb_num());	// �ϴγ�����
//		    	itemVOs[i].setDef6(""+item_vo.getBccb_num());	// ���γ�����
//		    	itemVOs[i].setQuantity_de(item_vo.getUse_num());// ����������Ч��
//		    	itemVOs[i].setDef5(""+item_vo.getUse_num());	// ����
//		    	itemVOs[i].setDef7(""+item_vo.getTimes());		// ����
		    	itemVOs[i].setPrice(null);		// ����
		    	
//		    	itemVOs[i].setDef3(hVO.getVdef01());		// ��ʼ����
//		    	itemVOs[i].setDef4(hVO.getVdef02());		// ��ֹ����
		    	
		    	itemVOs[i].setDef30(pk_znjjs);		// ��ͬ����pk��¼�뵥����pk�������ɽ�����pk��
		    	itemVOs[i].setDef29(pk_znjjs_b);	// ��ͬ�ӱ�pk��¼�뵥�ӱ�pk�������ɽ��ӱ�pk��
		    	
		    	itemVOs[i].setScomment(scomment);		// ժҪ
		    }
		    
		    headVO.setLocal_money( total_jine );			// ��֯���ҽ��
		    headVO.setMoney( total_jine );					// ԭ�ҽ��
		    
		    aggvo.setParentVO(headVO);
		    aggvo.setChildrenVO(itemVOs);
		    
		    Object obj_return = iplatFormEntry.processAction("SAVE" , "F0" , null , aggvo , null , null);
		    
		    totalNum++;	// ����ɹ� +1
			
		}
		
		MessageDialog.showWarningDlg(this.getEditor(), "", "�������ɽ�Ӧ�յ����ɹ���"+totalNum+"��");
		this.getRefreshCardAction().doAction(e);	// ������ ˢ�¿�Ƭ
		
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
