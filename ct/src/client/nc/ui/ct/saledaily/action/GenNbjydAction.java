package nc.ui.ct.saledaily.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.IplatFormEntry;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.ct.action.HelpAction;
import nc.ui.ct.saledaily.view.SaledailyBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.tools.BannerDialog;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.arap.receivable.ReceivableBillItemVO;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.ct.saledaily.GenJftzdVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

public class GenNbjydAction extends HelpAction {

	/**
	 * �����ڲ����׵�
	 */
	private static final long serialVersionUID = 3201550356497809091L;
	
	private SaledailyBillForm cardForm = null;

	  public GenNbjydAction() {
	    this.setCode("GenNbjydAction");
	    this.setBtnName("�����ڲ����׵�");
	  }

	  @Override
	  public void doAction(ActionEvent e) throws Exception {
		
		// ��֯
		final BillItem zuzhi_item = new BillItem();	
		zuzhi_item.setName("��֯");
		zuzhi_item.setKey("zuzhi");
		zuzhi_item.setDataType(IBillItem.UFREF);
		zuzhi_item.setRefType("������֯");
		zuzhi_item.setEdit(true);
		zuzhi_item.setValue( OrgSettingAccessor.getDefaultOrgUnit() );	// Ĭ��ֵ
		zuzhi_item.setNull(true);	// �Ƿ�ǿ�  false ���Ƿǿ�
		// ��ʼ����
		final BillItem ksrq_item = new BillItem();	
		ksrq_item.setName("��ʼ����");
		ksrq_item.setKey("ksrq");
		ksrq_item.setDataType(IBillItem.UFREF);
		ksrq_item.setRefType("����");
		ksrq_item.setEdit(true);
		ksrq_item.setValue("");		// Ĭ��ֵ
		ksrq_item.setNull(false);	// �Ƿ�ǿ�  false ���Ƿǿ�
		// ��������
		final BillItem jsrq_item = new BillItem();	
		jsrq_item.setName("��������");
		jsrq_item.setKey("jsrq");
		jsrq_item.setDataType(IBillItem.UFREF);
		jsrq_item.setRefType("����");
		jsrq_item.setEdit(true);
		jsrq_item.setValue("");		// Ĭ��ֵ
		jsrq_item.setNull(false);	// �Ƿ�ǿ�  false ���Ƿǿ�
		// ��ͬ��
		final BillItem htcode_item = new BillItem();	
		htcode_item.setName("��ͬ��");
		htcode_item.setKey("htcode");
		htcode_item.setDataType(IBillItem.STRING);
		htcode_item.setEdit(true);
		htcode_item.setValue("");	// Ĭ��ֵ
		htcode_item.setNull(false);	// �Ƿ�ǿ�  false ���Ƿǿ�
		// ��ǰ����
		final BillItem tqts_item = new BillItem();
		tqts_item.setName("��ǰ����");
		tqts_item.setKey("tqts");
		tqts_item.setDataType(IBillItem.INTEGER);
		tqts_item.setEdit(true);
		tqts_item.setValue(10);		// Ĭ��ֵ-��ǰ10��
		tqts_item.setNull(true);	// �Ƿ�ǿ�  false ���Ƿǿ�
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				 this.getCardForm()
				,new BillItem[]{
					 zuzhi_item,
					 ksrq_item,
					 jsrq_item,
					 htcode_item,
					 tqts_item,
				});
		dlg.setTitle("ѡ��");
		
		if( UIDialog.ID_OK != dlg.showModal()) return;
		
		Runnable checkRun =new Runnable(){
	        @SuppressWarnings("unchecked")
			public void run()
	        {
		        //�̶߳Ի���ϵͳ������ʾ��
	            BannerDialog dialog = new BannerDialog(getCardForm());
	            String message="";
	            dialog.start();
		
	            int totalNum = 0;	// �ɹ�������
	            
	            try
	            {
	            	
	            	Integer tqts = PuPubVO.getInteger_NullAs(tqts_item.getValueObject(),0);		 // ����-��ǰ����
					String  cs_ksrq = PuPubVO.getString_TrimZeroLenAsNull(ksrq_item.getValue()); // ����-��ʼ����
					String  cs_jsrq = PuPubVO.getString_TrimZeroLenAsNull(jsrq_item.getValue()); // ����-��������
					String htcode = htcode_item.getValue();
					String  zuzhi = zuzhi_item.getValue();
					
					UFDate nowDate = new UFDate();
					String ksrq = nowDate.toString().substring(0,10);
					String jsrq = nowDate.getDateAfter(tqts).toString().substring(0,10);
					
					if(cs_ksrq!=null) ksrq = cs_ksrq;	// �������-��ʼ���� ��Ϊ��  ��ֵ�� ��ʼ����
					if(cs_jsrq!=null) jsrq = cs_jsrq;	// �������-�������� ��Ϊ��  ��ֵ�� ��������
					
					IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
					IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
					
					/**
					 * �Ȳ�ѯ ���ں� Ϊ���ɹ� �ɷ�֪ͨ��������
					 */
					ArrayList<GenJftzdVO> list_1 = null;
					{
						StringBuffer querySQL_1 = 
						new StringBuffer(" select ")
								.append(" ctb.pk_ct_sale_b ")	// ��ͬ��pk
								.append(",ct.pk_ct_sale ")		// ��ͬ��pk
//								.append(",ctb.norigtaxmny ")	// ��ͬ���
								.append(",nvl(ctb.norigtaxmny,0)-nvl(ctb.noritotalgpmny,0) norigtaxmny ")	// ��ͬ��� ��ȥ ���տ���
								.append(",substr(ctb.vbdef10, 1, 10) busi_date ")	// ҵ������ = �����տ�����
//								.append(",ct.pk_customer ")		// �ͻ�
								.append(",cust.def4 pk_customer ")		// �ͻ�������def4������֯�Ŀͻ�
								.append(",cust.name vdef07 ")	// �ͻ�name
								.append(",ct.pk_org ")			// ��֯
								.append(",ct.pk_org_v ")		// ��֯�汾
								.append(",ct.vbillcode ")		// ��ͬ��
								.append(",doc.name jflx ")		// �շ���Ŀ
								.append(",case when instr(ct.vbillcode,'#')>0 then substr(ct.vbillcode,1,instr(ct.vbillcode,'#')-1) else ct.vbillcode end vbillcode2 ")	// ��ͬ��2
								.append(",room.name vdef01 ")	// �����
								.append(",substr(ctb.vbdef3, 1, 10) vdef02 ")		// ��ʼ����
								.append(",substr(ctb.vbdef4, 1, 10) vdef03 ")		// ��������
								.append(",ct.vdef16 vdef04 ")	// �����id
								.append(",ctb.vbdef1 vdef06 ")	// �շ���Ŀid
								.append(",ct.vdef15 vdef05 ")	// ����id
								.append(",ct.depid pk_deptid ")	// ����
								.append(",ct.depid_v pk_deptid_v ")	// ����v
								.append(" from ct_sale_b ctb ")
								.append(" inner join ct_sale ct on (ctb.pk_ct_sale = ct.pk_ct_sale) ")
								.append(" left join (" +
											" select ysb.def29,ysb.pk_recitem from ar_recitem ysb " +
											" inner join ar_recbill ysh on ysb.pk_recbill = ysh.pk_recbill " +
											" where ysb.dr = 0 and ysh.dr = 0 " +
											" and nvl(ysb.def29,'~') <> '~' " +
											" and ysh.pk_tradetype = 'F0-Cxx-90' " +
										") ysb on (ysb.def29 = ctb.pk_ct_sale_b) ")
//								.append(" left join ar_recitem ysb on (ysb.def29 = ctb.pk_ct_sale_b and ysb.dr = 0) ")	// Ӧ�յ��Զ���30������pk��29������pk
//								.append(" left join ar_recbill ysh on (ysb.pk_recbill = ysh.pk_recbill and ysh.dr = 0 and ysh.pk_tradetype = 'F0-Cxx-90') ")
								.append(" left join bd_defdoc doc on (doc.pk_defdoc = ctb.vbdef1) ")	// �շ���Ŀ
								.append(" left join bd_defdoc room on (room.pk_defdoc = ct.vdef16) ")	// ����
								.append(" left join bd_customer cust on (ct.pk_customer = cust.pk_customer) ")	// �ͻ�
								.append(" where ct.dr=0 and ctb.dr=0 ")
								.append(" and ct.blatest = 'Y' ")		// ��ͬ���°�
								.append(" and ct.fstatusflag = 1 ")		// ��ͬ״̬ = ��Ч
								.append(" and nvl(ct.vdef20,'~') = 'Y' ")	// �ڲ���ͬ = Y
								.append(" and ysb.pk_recitem is null ")	// û�������ڲ�����Ӧ�յ���
								.append(" and nvl(ctb.norigtaxmny,0)>nvl(ctb.noritotalgpmny,0) ")// ֻȡ ��ͬ��� ���� �տ��� ��
								.append(" and substr(ctb.vbdef10, 1, 10) between '"+ksrq+"' and '"+jsrq+"' ")	// ���� ���ڷ�Χ�������տ����ڣ�
								.append(" and substr(ctb.vbdef10, 1, 10) <= substr(nvl(ct.vdef19,'2099-12-31 23:59:59'), 1, 10) ")	// ֻȡ ����-�տ����� С�ڵ��� ����ֹ���� �����ݣ�HK 2019��1��23��17:04:07��
								.append(htcode==null?"":" and ct.vbillcode = '"+htcode+"' ")	// ���� ��ͬ��
								.append(" and ct.pk_org = '"+zuzhi+"' ")	// ���� ��֯
								.append(" and nvl(ctb.norigtaxmny,0) > 0 ")	// ��ͬ��� ���� 0
							.append(" union all ")
								.append(" select ")
								.append(" ctb.pk_ct_sale_b ")	// ��ͬ��pk
								.append(",ct.pk_ct_sale ")		// ��ͬ��pk
//								.append(",ctb.norigtaxmny ")	// ��ͬ���
								.append(",nvl(ctb.norigtaxmny,0)-nvl(ctb.noritotalgpmny,0) norigtaxmny ")	// ��ͬ��� ��ȥ ���տ���
								.append(",substr(ctb.vmemo, 1, 10) busi_date ")	// ҵ������
//								.append(",ct.pk_customer ")		// �ͻ�
								.append(",cust.def4 pk_customer ")		// �ͻ�������def4������֯�Ŀͻ�
								.append(",cust.name vdef07 ")	// �ͻ�name
								.append(",ct.pk_org ")			// ��֯
								.append(",ct.pk_org_v ")		// ��֯�汾
								.append(",ct.vbillcode ")		// ��ͬ��
								.append(",doc.name jflx ")		// �շ���Ŀ
								.append(",case when instr(ct.vbillcode,'#')>0 then substr(ct.vbillcode,1,instr(ct.vbillcode,'#')-1) else ct.vbillcode end vbillcode2 ")	// ��ͬ��2
								.append(",room.name vdef01 ")	// �����
								.append(",substr(ctb.vbdef3, 1, 10) vdef02 ")	// ��ʼ����
								.append(",substr(ctb.vbdef4, 1, 10) vdef03 ")	// ��������
								.append(",ct.vdef16 vdef04 ")	// �����id
								.append(",ctb.vbdef1 vdef06 ")	// �շ���Ŀid
								.append(",ct.vdef15 vdef05 ")	// ����id
								.append(",ct.depid pk_deptid ")	// ����
								.append(",ct.depid_v pk_deptid_v ")	// ����v
								.append(" from ct_sale_b ctb ")
								.append(" inner join ct_sale ct on (ctb.pk_ct_sale = ct.pk_ct_sale) ")
								.append(" left join (" +
											" select ysb.def29,ysb.pk_recitem from ar_recitem ysb " +
											" inner join ar_recbill ysh on ysb.pk_recbill = ysh.pk_recbill " +
											" where ysb.dr = 0 and ysh.dr = 0 " +
											" and nvl(ysb.def29,'~') <> '~' " +
											" and ysh.pk_tradetype = 'F0-Cxx-90' " +
										") ysb on (ysb.def29 = ctb.pk_ct_sale_b) ")
//								.append(" left join ar_recitem ysb on (ysb.def29 = ctb.pk_ct_sale_b and ysb.dr = 0) ")	// Ӧ�յ��Զ���30������pk��29������pk
//								.append(" left join ar_recbill ysh on (ysb.pk_recbill = ysh.pk_recbill and ysh.dr = 0 and ysh.pk_tradetype = 'F0-Cxx-90') ")
								.append(" left join bd_defdoc doc on (doc.pk_defdoc = ctb.vbdef1) ")	// �շ���Ŀ
								.append(" left join bd_defdoc room on (room.pk_defdoc = ct.vdef16) ")	// ����
								.append(" left join bd_customer cust on (ct.pk_customer = cust.pk_customer) ")	// �ͻ�
								.append(" where ct.dr=0 and ctb.dr=0 ")
								.append(" and ct.blatest = 'Y' ")		// ��ͬ���°�
								.append(" and ct.fstatusflag = 1 ")		// ��ͬ״̬ = ��Ч
								.append(" and nvl(ct.vdef20,'~') = 'Y' ")	// �ڲ���ͬ = Y
								.append(" and ysb.pk_recitem is null ")	// û�������ڲ�����Ӧ�յ���
								// �ñ���ı�ע�� ���洢 �ۼ��е� ��ʼ����
								.append(" and substr(ctb.vmemo, 1, 10) between '"+ksrq+"' and '"+jsrq+"' ")	// ���� ���ڷ�Χ
								.append(" and substr(ctb.vmemo, 1, 10) <= substr(nvl(ct.vdef19,'2099-12-31 23:59:59'), 1, 10) ")	// ֻȡ ����-��ʼ���� С�ڵ��� ����ֹ���� �����ݣ�HK 2019��1��23��17:04:07��
								.append(htcode==null?"":" and ct.vbillcode = '"+htcode+"' ")	// ���� ��ͬ��
								.append(" and ct.pk_org = '"+zuzhi+"' ")	// ���� ��֯
								.append(" and nvl(ctb.norigtaxmny,0) < 0 ")	// ��ͬ��� С�� 0
								
						;
						
						list_1 = (ArrayList<GenJftzdVO>)iUAPQueryBS.executeQuery(
								 querySQL_1.toString()
								,new BeanListProcessor(GenJftzdVO.class)
						);
					}
					
					/**
					 * �ٲ�ѯ  ���������У� ֮ǰ�·� û�нɷ���ȫ�����ݡ�
					 */
					ArrayList<GenJftzdVO> list_2 = null;
					{
						String where_pk_ct_sale   = "'null'";
						String where_pk_ct_sale_b = "'null'";
						
						for( GenJftzdVO vo : list_1 )
						{// ��ϳ� ��ͬpk��where
							String pk_ct_sale   = vo.getPk_ct_sale();
							String pk_ct_sale_b = vo.getPk_ct_sale_b();
							
							where_pk_ct_sale   += ",'"+pk_ct_sale+"'";
							where_pk_ct_sale_b += ",'"+pk_ct_sale_b+"'";
						}
						
						StringBuffer querySQL_2 = 
							new StringBuffer(" select ")
									.append(" ctb.pk_ct_sale_b ")	// ��ͬ��pk
									.append(",ct.pk_ct_sale ")		// ��ͬ��pk
	//								.append(",ctb.norigtaxmny ")	// ��ͬ���
									.append(",nvl(ctb.norigtaxmny,0) - nvl(ctb.noritotalgpmny,0) norigtaxmny ")	// ��ͬ��� ��ȥ �տ���
									.append(",substr(ctb.vbdef10, 1, 10) busi_date ")	// ҵ������ = �����տ�����
//									.append(",ct.pk_customer ")		// �ͻ�
									.append(",cust.def4 pk_customer ")		// �ͻ�������def4������֯�Ŀͻ�
									.append(",cust.name vdef07 ")	// �ͻ�name
									.append(",ct.pk_org ")			// ��֯
									.append(",ct.pk_org_v ")		// ��֯�汾
									.append(",ct.vbillcode ")		// ��ͬ��
									.append(",doc.name jflx ")		// �շ���Ŀ
									.append(",case when instr(ct.vbillcode,'#')>0 then substr(ct.vbillcode,1,instr(ct.vbillcode,'#')-1) else ct.vbillcode end vbillcode2 ")	// ��ͬ��2
									.append(",room.name vdef01 ")	// �����
									.append(",substr(ctb.vbdef3, 1, 10) vdef02 ")		// ��ʼ����
									.append(",substr(ctb.vbdef4, 1, 10) vdef03 ")		// ��������
									.append(",ct.vdef16 vdef04 ")	// �����id
									.append(",ctb.vbdef1 vdef06 ")	// �շ���Ŀid
									.append(",ct.vdef15 vdef05 ")	// ����id
									.append(",ct.depid pk_deptid ")	// ����
									.append(",ct.depid_v pk_deptid_v ")	// ����v
									.append(" from ct_sale_b ctb ")
									.append(" inner join ct_sale ct on (ctb.pk_ct_sale = ct.pk_ct_sale) ")
									.append(" left join (" +
												" select ysb.def29,ysb.pk_recitem from ar_recitem ysb " +
												" inner join ar_recbill ysh on ysb.pk_recbill = ysh.pk_recbill " +
												" where ysb.dr = 0 and ysh.dr = 0 " +
												" and nvl(ysb.def29,'~') <> '~' " +
												" and ysh.pk_tradetype = 'F0-Cxx-90' " +
										") ysb on (ysb.def29 = ctb.pk_ct_sale_b) ")
//									.append(" left join ar_recitem ysb on (ysb.def29 = ctb.pk_ct_sale_b and ysb.dr = 0) ")	// Ӧ�յ��Զ���30������pk��29������pk
//									.append(" left join ar_recbill ysh on (ysb.pk_recbill = ysh.pk_recbill and ysh.dr = 0 and ysh.pk_tradetype = 'F0-Cxx-90') ")
									.append(" left join bd_defdoc doc on (doc.pk_defdoc = ctb.vbdef1) ")	// �շ���Ŀ
									.append(" left join bd_defdoc room on (room.pk_defdoc = ct.vdef16) ")	// ����
									.append(" left join bd_customer cust on (ct.pk_customer = cust.pk_customer) ")	// �ͻ�
									.append(" where ct.dr=0 and ctb.dr=0 ")
									.append(" and ct.blatest = 'Y' ")		// ��ͬ���°�
									.append(" and ct.fstatusflag = 1 ")		// ��ͬ״̬ = ��Ч
									.append(" and nvl(ct.vdef20,'~') = 'Y' ")	// �ڲ���ͬ = Y
									.append(" and ysb.pk_recitem is null ")	// û������Ӧ�յ���
//									.append(" and nvl(ysh.pk_tradetype,'~') in ('~','F0-Cxx-90') ")	// ֻȡδ���ɵ�  ��  ֮ǰ���ɵ��뱾����ص�
									.append(" and nvl(ctb.norigtaxmny,0)>nvl(ctb.noritotalgpmny,0) ")// ֻȡ ��ͬ��� ���� �տ��� ��
									.append(" and substr(ctb.vbdef10, 1, 10) <= '"+jsrq+"' ")	// ���� �����տ�����<=��������
//									.append(htcode==null?"":" and ct.vbillcode = '"+htcode+"' ")	// ���� ��ͬ��
//									.append(" and ct.pk_org = '"+zuzhi+"' ")	// ���� ��֯
									.append(" and  ct.pk_ct_sale       in ( "+where_pk_ct_sale+" )")	// �� ��һ���ĺ�ͬpk ȥ��ѯ
									.append(" and ctb.pk_ct_sale_b not in ( "+where_pk_ct_sale_b+" )")	// ����� ��һ���ĺ�ͬ��pk
									.append(" and substr(ctb.vbdef10, 1, 10) <= substr(nvl(ct.vdef19,'2099-12-31 23:59:59'), 1, 10) ")	// ֻȡ ����-�տ����� С�ڵ��� ����ֹ���� �����ݣ�HK 2019��1��23��17:04:07��
							;
							
							list_2 = (ArrayList<GenJftzdVO>)iUAPQueryBS.executeQuery(
									 querySQL_2.toString()
									,new BeanListProcessor(GenJftzdVO.class)
							);
						
					}
					
					/**
					 *  �ϲ� 1 �� 2
					 */
					ArrayList<GenJftzdVO> list = new ArrayList<GenJftzdVO>();
					list.addAll(list_1);
					list.addAll(list_2);
					
				    /**
				     * ���� ��ͬ��2  ������
				     */
				    HashMap<String,ArrayList<GenJftzdVO>> MAP_list = new HashMap<String,ArrayList<GenJftzdVO>>();
				    for(GenJftzdVO vo : list)
				    {
				    	String key = vo.getVbillcode2();
				    	ArrayList<GenJftzdVO> value = MAP_list.get(key);
				    	if(value==null)
				    	{
				    		value = new ArrayList<GenJftzdVO>();
				    	}
				    	value.add(vo);
				    	MAP_list.put(key , value);
				    }
				    /***END***/
				    
				    /**
				     * HK 2019��1��9��19:51:23
				     * ���� �ͻ�  ������
				     */
//				    HashMap<String,ArrayList<GenJftzdVO>> MAP_list = new HashMap<String,ArrayList<GenJftzdVO>>();
//				    for(GenJftzdVO vo : list)
//				    {
//				    	String key = vo.getPk_customer();
//				    	ArrayList<GenJftzdVO> value = MAP_list.get(key);
//				    	if(value==null)
//				    	{
//				    		value = new ArrayList<GenJftzdVO>();
//				    	}
//				    	value.add(vo);
//				    	MAP_list.put(key , value);
//				    }
				    /***END***/
				    
				    String[] Keys = new String[MAP_list.size()];
				    Keys = MAP_list.keySet().toArray(Keys);
				    
//				    if (true) {
//				    	System.out.println(MAP_list);
//				    	return;
//				    }
				    
//					for( int list_i=0;list_i<list.size();list_i++ )
				    for( int Keys_i=0;Keys_i<Keys.length;Keys_i++ )
					{
						try
						{
							ArrayList<GenJftzdVO> vo_list = MAP_list.get(Keys[Keys_i]);
							
							GenJftzdVO ctSaleVO = vo_list.get(0);	// ȡ��һ��vo  ������ͷ����
							
						    /**
						     * �Ƶ�
						     */
						    UFDateTime now_dateTime = new UFDateTime();			// �Ƶ�ʱ�䣨�̶���
				//		    UFDate         now_date = now_dateTime.getDate();	// �Ƶ����ڣ��̶���
//						    UFDate        busi_date = PuPubVO.getUFDate(ctSaleVO.getBusi_date());	// ҵ�����ڣ�ȡ����
						    UFDate        busi_date = AppContext.getInstance().getBusiDate();		// ҵ������ ȡ ��¼����
						    
						    String[] accperiod = getAccperiod(busi_date, iUAPQueryBS);
						    
						    String             year = accperiod[0];					// ����꣨����ҵ��������������
						    String           period = accperiod[1];					// ����£�����ҵ��������������
						    String          creator = InvocationInfoProxy.getInstance().getUserId();	// �Ƶ��ˣ��̶���
						    String      pk_busitype = "0001N510000000000SLQ";	// ҵ�����̣��̶���
						    String        billclass = "ys";						// ���ݴ��ࣨ�̶���
						    String      pk_billtype = "F0";						// �������ͱ��루�̶���
						    String     pk_tradetype = "F0-Cxx-90";				// Ӧ�����ͣ��̶���
						    String   pk_tradetypeid = "1001N510000000BSKADN";	// �������ͣ��̶���
						    String      pk_currtype = "1002Z0100000000001K1";	// ���֣��̶���
						    String         pk_group = "0001N510000000000EGY";	// ���ţ��̶���
						    String           guojia = "0001Z010000000079UJJ";	// ���ң��̶���
						    String           pk_org = ctSaleVO.getPk_org();		// ��֯��ȡ����
						    String         pk_org_v = ctSaleVO.getPk_org_v();	// ��֯�汾��ȡ����
						    String      pk_customer = ctSaleVO.getPk_customer();// �ͻ���ȡ����
						    String		  pk_deptid = ctSaleVO.getPk_deptid();	// ����
						    String		pk_deptid_v = ctSaleVO.getPk_deptid_v();// ����v
//						    UFDouble           jine = ctSaleVO.getNorigtaxmny();	// ���
						    
//						    String     pk_ct_sale_b = ctSaleVO.getPk_ct_sale_b();	// ��ͬ�ӱ�pk
//						    String       pk_ct_sale = ctSaleVO.getPk_ct_sale();		// ��ͬ����pk
						    						    
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
						    
						    headVO.setPk_fiorg( pk_org );		// ����������֯ 0001N510000000001SYX
						    headVO.setPk_fiorg_v( pk_org_v );	// ����������֯�汾 0001N510000000001SYW
						    headVO.setPk_org( pk_org );			// Ӧ�ղ�����֯
						    headVO.setPk_org_v( pk_org_v );		// Ӧ�ղ�����֯�汾
						    headVO.setSett_org( pk_org );		// ���������֯
						    headVO.setSett_org_v( pk_org_v );	// ���������֯�汾
						    
						    headVO.setPk_deptid(pk_deptid);		// ����
						    headVO.setPk_deptid_v(pk_deptid_v);	// ����v
						    
						    headVO.setSrc_syscode(0);		// ������Դϵͳ��0=Ӧ��ϵͳ��
						    headVO.setSyscode(0);			// ��������ϵͳ��0=Ӧ��ϵͳ��
						    
						    headVO.setSendcountryid( guojia );	// ������bd_countryzone
						    headVO.setTaxcountryid( guojia );	// ��˰��
						    
						    headVO.setDef30("1001N5100000006P2IQA");	// �Ƿ�����ƾ֤ = ��
						    
						    ReceivableBillItemVO[] itemVOs = new ReceivableBillItemVO[vo_list.size()];
						    
						    UFDouble total_jine = UFDouble.ZERO_DBL;	// �ϼƽ��
						    
						    for(int i=0;i<itemVOs.length;i++)
						    {
						    	GenJftzdVO item_vo = vo_list.get(i);
							    UFDouble           jine = item_vo.getNorigtaxmny();		// ���
							    String     pk_ct_sale_b = item_vo.getPk_ct_sale_b();	// ��ͬ�ӱ�pk
							    String       pk_ct_sale = item_vo.getPk_ct_sale();		// ��ͬ����pk
							    
							    String room_no = item_vo.getVdef01();	// �����
							    String def_ksrq = item_vo.getVdef02();	// ��ʼ����
							    String def_jsrq = item_vo.getVdef03();	// ��������
							    String room_id = item_vo.getVdef04();	// �����id
							    String quyu_id = item_vo.getVdef05();	// ����id
							    String sfxm_id = item_vo.getVdef06();	// �շ���Ŀid
							    String cust_name = item_vo.getVdef07();	// �ͻ�����
							    String sfxm_name = item_vo.getJflx();	// �շ���Ŀ
							    // ժҪ = �ͻ�����+�����+��ʼ����+��ֹ����+�շ���Ŀ
							    String scomment = cust_name + "��"+room_no+"��" + def_ksrq + "��" + def_jsrq + sfxm_name;
//							    if (def_ksrq == null) {
//							    	scomment = item_vo.getVbillcode()+"��"+item_vo.getVdef01()+"��-"+item_vo.getJflx()+"-"+item_vo.getBusi_date();
//							    } else {
//							    	scomment = item_vo.getVbillcode()+"��"+item_vo.getVdef01()+"��-" +
//							    			item_vo.getJflx()+"-" +
//							    			def_ksrq + "��" + def_jsrq
//							    			;
//							    }
							    
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
						    	
						    	itemVOs[i].setPk_deptid(pk_deptid);		// ����
						    	itemVOs[i].setPk_deptid_v(pk_deptid_v);	// ����v
						    	
						    	itemVOs[i].setRececountryid( guojia );				// �ջ���
						    	
						    	itemVOs[i].setRowno(-1);							// ���ݷ�¼��
						    	itemVOs[i].setTaxtype(1);							// ��˰���0=Ӧ˰�ں���1=Ӧ˰��ӣ���
						    	itemVOs[i].setQuantity_bal(UFDouble.ZERO_DBL);		// �������
						    	itemVOs[i].setRate(UFDouble.ONE_DBL);				// ��֯���һ���
						    	itemVOs[i].setTaxprice(UFDouble.ZERO_DBL);			// ��˰����
						    	itemVOs[i].setTaxrate(UFDouble.ZERO_DBL);			// ˰��
						    	
						    	itemVOs[i].setDef30(pk_ct_sale);		// ��ͬ����pk
						    	itemVOs[i].setDef29(pk_ct_sale_b);		// ��ͬ�ӱ�pk
						    	itemVOs[i].setDef8(room_id);			// ����
						    	itemVOs[i].setDef1(sfxm_id);	// �շ���Ŀpk
						    	itemVOs[i].setDef9(quyu_id);	// ����pk
						    	itemVOs[i].setDef3(def_ksrq);	// ��ʼ����
						    	itemVOs[i].setDef4(def_jsrq);	// ��������
						    	
						    	itemVOs[i].setScomment(scomment);		// ժҪ
						    }
						    
						    headVO.setLocal_money( total_jine );			// ��֯���ҽ��
						    headVO.setMoney( total_jine );					// ԭ�ҽ��
						    
						    aggvo.setParentVO(headVO);
						    aggvo.setChildrenVO(itemVOs);
						    
						    Object obj_return = iplatFormEntry.processAction("SAVE" , "F0" , null , aggvo , null , null);
						    
						    totalNum++;	// ����ɹ� +1
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					    /***END***/
					}
					
	            } catch(Exception e) {
	                e.printStackTrace();
	            } finally {
	            	// ����ϵͳ������ʾ��
	                dialog.end(); 
	                MessageDialog.showWarningDlg(getCardForm(), "", "������ϡ��ܹ����ɡ�"+totalNum+"������");
	            }
	        }
	    };
	    //�����߳�
	    new Thread(checkRun).start();
	    
	  }

	  public SaledailyBillForm getCardForm() {
	    return this.cardForm;
	  }

	  public void setCardForm(SaledailyBillForm cardForm) {
	    this.cardForm = cardForm;
	  }
	
	  /**
	   * �������� ���� ����ꡢ��
	   */
	  static String[] getAccperiod(UFDate date, IUAPQueryBS iUAPQueryBS) throws BusinessException
	  {		  
		  StringBuffer querySQL = 
				  new StringBuffer("select a.yearmth ")
		  			.append(" from bd_accperiodmonth a ")
		  			.append(" where a.dr = 0 ")
		  			.append(" and a.pk_accperiodscheme = '0001Z000000000000001' ")
		  			.append(" and '").append(date.toString()).append("' between begindate and enddate ")
		  ;
		  ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(
					 querySQL.toString()
					,new ArrayListProcessor());
		  if (list !=null && !list.isEmpty()) {
			  String yearmth = PuPubVO.getString_TrimZeroLenAsNull(((Object[])list.get(0))[0]);
			  return yearmth.split("-");
		  }
		  throw new BusinessException("δ�ҵ�����ڼ䡣");
	  }
	  
	  @Override
	  protected boolean isActionEnable() {
		  return true;
	  }
}
