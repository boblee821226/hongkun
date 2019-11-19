package nc.ui.hkjt.zulin.bkfyft.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.zulin.yuebao.QueryHtVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoBVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ���÷�̯ ȡ��
 *
 */
public class QushuAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public QushuAction() {
		setBtnName("ȡ��");
		setCode("qushuAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;

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

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		this.getEditor().getBillCardPanel().getBillModel().clearBodyData();	// ��ձ���
		
		String yearMonth = PuPubVO.getString_TrimZeroLenAsNull(
					this.getEditor().getBillCardPanel().getHeadItem("yearmonth").getValueObject()
				);
		
		UFDate yb_ksrq = 
				PuPubVO.getUFDate(
						this.getEditor().getBillCardPanel().getHeadItem("begindate").getValueObject() );
		UFDate yb_jsrq = 
				PuPubVO.getUFDate(
						this.getEditor().getBillCardPanel().getHeadItem("enddate").getValueObject() );
		
		String str_yb_ksrq = yb_ksrq.toString().substring(0, 10);
		String str_yb_jsrq = yb_jsrq.toString().substring(0, 10);
		
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject()
		);
			
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" htb.vbdef1 pk_srxm ")	// ֧����Ŀpk
				.append(",szxm.name vdef03 ")	// ֧����Ŀname
				.append(",substr(htb.vbdef3,1,10) ksrq ")	// ��ͬ��ʼ����
				.append(",substr(htb.vbdef4,1,10) jsrq ")	// ��ͬ��������
				.append(",htb.norigmny vdef11 ")	// ��˰���
				.append(",htb.norigtaxmny vdef12 ")	// ��˰���
				.append(",ht.depid vdef05 ")	// ����pk
				.append(",dept.name vdef04 ")	// ����name
				.append(",fplx.name vdef02 ")	// ��Ʊ����name
				.append(",ht.vbillcode vdef10 ")// ��ͬ��
				.append(",to_number(nvl(replace(ht.vdef8,'~',''),'0.0')) + to_number(nvl(replace(ht.vdef11,'~',''),'0.0')) mianji ")// ���
				.append(",ht.cvendorid pk_customer ")	// �Է�pk
				.append(",gys.name vdef01 ")			// �Է�name
				.append(",ht.ntotalorigmny vdef07 ")	// ��ͬ�ܶ�
				.append(" from ct_pu ht ")
				.append(" inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")
				.append(" left join bd_defdoc fplx on ht.vdef3 = fplx.pk_defdoc ")
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")
				.append(" left join org_dept dept on ht.depid = dept.pk_dept ")
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.ctrantypeid = '1001N510000000AGYP1K' ")
				.append(" and ht.pk_org = '").append(pk_org).append("' ")
				.append(" and ( ")
				.append(" 	'").append(str_yb_ksrq).append("' between substr(htb.vbdef3,1,10) and substr(htb.vbdef4,1,10) ")
				.append(" 	or ")
				.append("  	'").append(str_yb_jsrq).append("' between substr(htb.vbdef3,1,10) and substr(htb.vbdef4,1,10) ")
				.append(" ) ")
				.append(" and ht.vbillcode like '20191112%' ")
		;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
		ArrayList<QueryHtVO> list = (ArrayList<QueryHtVO>)iUAPQueryBS.executeQuery(
				 querySQL.toString()
				,new BeanListProcessor(QueryHtVO.class)
		);
		
		HashMap<String,YuebaoBVO> MAP_yuebaoBVO = new HashMap<String,YuebaoBVO>();
		
		for(int i=0;i<list.size();i++)
		{
			QueryHtVO htVO = list.get(i);
			
			String fplx = htVO.getVdef02();		// ��Ʊ
			UFDouble ht_mny = htVO.getVdef12();	// ��˰
			if ("ר�÷�Ʊ".equals(fplx)) {	// רƱ ����˰
				ht_mny = htVO.getVdef11();
			}
			
			String ht_code = htVO.getVdef10();	// ��ͬ��
			UFDate ht_ksrq = htVO.getKsrq();	// ��ͬ��ϸ-��ʼ����
			UFDate ht_jsrq = htVO.getJsrq();	// ��ͬ��ϸ-��������
			Integer ht_days = ht_jsrq.getDaysAfter(ht_ksrq) + 1;	// ��ͬ����
			UFDouble ht_danjia = ht_mny.div(ht_days);	// ÿ��ĵ��� = ��ͬ��� / ��ͬ����
			
//			UFDate ht_zzrq = htVO.getZzrq();	// ��ͬ��ͷ-��ֹ����
			UFDouble ht_mianji = htVO.getMianji();	// ���
			
			String ht_pk_customer = htVO.getPk_customer();	// �Է�pk
			
			String srxm_name = htVO.getVdef03();	// ֧����Ŀ-����
			
			Integer yb_days = 0;
			
			UFDate js_ksrq = null;		// ���㿪ʼ����
			UFDate js_jsrq = null;		// �����������
			
			/**
			 *  ���� ��ͬ��ϸ �� ���õ� ��ʼ��������  ֮��� ��С��ϵ�� ������� ����
			 */
			if(
				ht_ksrq.compareTo(yb_ksrq)<=0
			 &&	ht_jsrq.compareTo(yb_jsrq)>=0
			)
			{// ��� ��ͬ��ϸ��ʼ����  С�ڵ���  �±���ʼ����  ����  ��ͬ��ϸ��������  ���ڵ���  �±���������
			 // ˵�� ���±���Χ  �� һ�� ��ͬ��ϸ �ڣ� �±������� ��Ϊ �Ʒ�����
				yb_days = yb_jsrq.getDaysAfter(yb_ksrq)+1;	// �±���������-�±���ʼ����+1
				htVO.setYb_days(yb_days);	// �Ʒ�����
				htVO.setYb_ksrq(yb_ksrq);	// �±���ʼ����
				htVO.setYb_jsrq(yb_jsrq);	// �±���������
				
				js_ksrq = yb_ksrq;
				js_jsrq = yb_jsrq;
			}
			else if(
				ht_ksrq.compareTo(yb_ksrq)<=0
			 &&	ht_jsrq.compareTo(yb_jsrq)<0
			)
			{// ��� ��ͬ��ϸ��ʼ����  С�ڵ���  �±���ʼ����  ����  ��ͬ��ϸ��������  С��  �±���������
			 // ˵�� �±��� ǰ��� �� ��һ�к�ͬ��ϸ�� �Ʒ����� = �±���ʼ���� �� ��ͬ��ϸ��������
				yb_days = ht_jsrq.getDaysAfter(yb_ksrq)+1;	// ��ͬ��������-�±���ʼ����+1
				htVO.setYb_days(yb_days);	// �Ʒ�����
				htVO.setYb_ksrq(yb_ksrq);	// �±���ʼ����
//				htVO.setYb_jsrq(yb_jsrq);	// �±���������
				
				js_ksrq = yb_ksrq;
				js_jsrq = ht_jsrq;
			}
			else if(
				ht_ksrq.compareTo(yb_ksrq)>0
			 &&	ht_jsrq.compareTo(yb_jsrq)>=0	
			)
			{// ��� ��� ��ͬ��ϸ��ʼ���� ���� �±���ʼ����  ����  ��ͬ��ϸ�������� ���ڵ��� �±���������
			 // ˵�� �±��� ���� �� �ڶ��к�ͬ��ϸ�� �Ʒ����� = ��ͬ��ϸ��ʼ���� �� �±���������
				yb_days = yb_jsrq.getDaysAfter(ht_ksrq)+1;	// �±���������-��ͬ��ʼ����+1
				htVO.setYb_days(yb_days);	// �Ʒ�����
//				htVO.setYb_ksrq(yb_ksrq);	// �±���ʼ����
				htVO.setYb_jsrq(yb_jsrq);	// �±���������
				
				js_ksrq = ht_ksrq;
				js_jsrq = yb_jsrq;
			}
			
			// ��� �������� ���� ��ֹ���ڣ� ��Ҫ ���������� ��ֵΪ��ֹ���ڣ� �������¼��� �±�����
//			if(js_jsrq.compareTo(ht_zzrq)>0)
//			{
//				js_jsrq = ht_zzrq;
//				htVO.setYb_jsrq(js_jsrq);
//				yb_days = js_jsrq.getDaysAfter(js_ksrq)+1;
//				htVO.setYb_days(yb_days);
//			}
			
			//����  ���� = ÿ�쵥�� * ����
			UFDouble yb_mny = ht_danjia.multiply(yb_days).setScale(2, UFDouble.ROUND_HALF_UP);
			htVO.setYb_mny(yb_mny);
			
			// ͬʱ �� YuebaoBVO ���з�װ����
			// �� �Է�##��ͬ��  ����
			{
				String key = ht_pk_customer + "##" + ht_code;
				YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
				if(yuebaoBVO==null)
				{
					yuebaoBVO = new YuebaoBVO();
					yuebaoBVO.setPk_cutomer(ht_pk_customer);	// �ͻ�
					yuebaoBVO.setJsksrq(js_ksrq);				// ���㿪ʼ����
					yuebaoBVO.setJsjsrq(js_jsrq);				// �����������
					yuebaoBVO.setDysrqrts(PuPubVO.getUFDouble_NullAsZero(yb_days));	// ���·���ȷ������
					yuebaoBVO.setDysrqrje(yb_mny);				// ���·���ȷ�Ͻ��
					yuebaoBVO.setMianji(ht_mianji);				// ���
					yuebaoBVO.setDanjia(ht_danjia);				// ����
					
					yuebaoBVO.setSrxm(htVO.getPk_srxm());		// ������Ŀ
					yuebaoBVO.setVbdef01(htVO.getVdef01());		// �Է�����
					yuebaoBVO.setVbdef02(htVO.getVdef02());		// ��Ʊ����-����
					yuebaoBVO.setVbdef03(htVO.getVdef03());		// ֧����Ŀ-����
					yuebaoBVO.setVbdef04(htVO.getVdef04());		// ��������
					yuebaoBVO.setVbdef05(htVO.getVdef05());		// ����pk
					yuebaoBVO.setVbdef07(htVO.getVdef07());		// ʵ�ʺ�ͬ���
					yuebaoBVO.setVbdef10(htVO.getVdef10());		// ��ͬ��
					
//					yuebaoBVO.setVbmemo(htVO.getVdef10());		// ��ע��ź�ͬ��
//					yuebaoBVO.setVbdef06( ht_zzrq!=null ? ht_zzrq.toString().substring(0,10) : null );	// ��ͬ��ֹ����  ��HK 2018��12��26��17:39:31��
				}
				else
				{
					yuebaoBVO.setDysrqrts(
							yuebaoBVO.getDysrqrts().add(
							PuPubVO.getUFDouble_NullAsZero(yb_days) )
					);	// ���·���ȷ������
					yuebaoBVO.setDysrqrje(
							yuebaoBVO.getDysrqrje().add(
							yb_mny )
					);	// ���·���ȷ�Ͻ��
					yuebaoBVO.setDanjia(null);	// ������ ��ֵ��null�� ���� ���¼��㵥��
					
					
					if( js_ksrq.compareTo(yuebaoBVO.getJsksrq())<0 )
						yuebaoBVO.setJsksrq(js_ksrq);
					if( js_jsrq.compareTo(yuebaoBVO.getJsjsrq())>0 )
						yuebaoBVO.setJsjsrq(js_jsrq);
					
				}
				
				{// ��ע�Ĵ���
					String vbmemo = "";
					
					vbmemo = 
						"��" +
						srxm_name + "��" +
						js_ksrq.toString().substring(0,10)   + "��" +
						js_jsrq.toString().substring(0,10)   + "��" +
						ht_danjia + "��" +
						yb_days   + "��" +
						yb_mny    + "" +
						"�� "
					;
					
					yuebaoBVO.setVbmemo(
						yuebaoBVO.getVbmemo()==null
						? vbmemo
						: yuebaoBVO.getVbmemo()+vbmemo
					);
				}
				
				MAP_yuebaoBVO.put(key,yuebaoBVO);
			}
		}
		
		/**
		 * ��ѯ ���ڸ����
		 */
		{
			StringBuffer querySQL_FK = 
			new StringBuffer(" select ")
					.append(" a.pk_customer ")				// �ͻ�pk
					.append(",a.vdef10 ")					// ����pk
					.append(",sum(a.skje) fkje ")			// ������
//					.append(",max(a.pk_dept) pk_dept ")		// ����pk
//					.append(",max(cust.name) vdef01 ")		// �Է�-name
					.append(" from (")
					// �����տ
						.append(" select ")
						.append(" ht.cvendorid pk_customer ")	// �Է�pk
						.append(",ht.vbillcode vdef10 ")		// ��ͬ��
						.append(",sum(fkb.money_de) fkje ")		// ������
						.append(" from ap_paybill fk ")
						.append(" inner join ap_payitem fkb on fk.pk_paybill = fkb.pk_paybill ")
						.append(" inner join ct_pu ht on fkb.top_billid = ht.pk_ct_pu ")
						.append(" inner join ct_payplan fkjh on fkb.top_itemid = fkjh.pk_ct_payplan ")
						.append(" left join ct_pu_b htb on (ht.pk_ct_pu = htb.pk_ct_pu and htb.dr = 0 and fkjh.crowno = htb.crowno) ")
						.append(" where fk.dr = 0 and fkb.dr = 0 ")
						.append(" and ht.dr = 0 and fkjh.dr = 0 ")
						.append(" and ht.pk_org = '").append(pk_org).append("' ")
						.append(" and fk.billyear || '-' ||fk.billperiod = '").append(yearMonth).append("' ")
						.append(" group by ht.cvendorid, ht.vbillcode ")
					.append(" ) ")
			;
			// 
			ArrayList list_FK = (ArrayList)iUAPQueryBS.executeQuery(
					querySQL_FK.toString()
					,new ArrayListProcessor()
			);
			if(list_FK!=null&&list_FK.size()>0)
			{
				for( int i=0;i<list_FK.size();i++ )
				{
					Object[] obj = (Object[])list_FK.get(i);
					// �Է�##��ͬ��
					String key = obj[0]+"##"+obj[1];
					
					UFDouble skje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// ������
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setBqskje(skje);
					}
					else
					{ /**
					   * HK
					   * 2019��11��14��11��15��
					   * �� �޵����������ݵ� �տ�����뵽���塣
					   */
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setBqskje(skje);		// �����տ���
						yuebaoBVO.setQmyskye(skje);		// ��ĩԤ�տ���� = �����տ���
						
						yuebaoBVO.setPk_cutomer(PuPubVO.getString_TrimZeroLenAsNull(obj[0]));	// �ͻ�pk
						yuebaoBVO.setVbdef10(PuPubVO.getString_TrimZeroLenAsNull(obj[1]));		// ��ͬ��
						
						MAP_yuebaoBVO.put(key, yuebaoBVO);	// �ŵ�������
					}
				}
			}
		}
		/***END***/
		
		/**
		 * ��ѯ ���ڵ���ĩ���,�������ڵ��ڳ����
		 */
		{
			String syqj = getYYYYMM(yearMonth,-1);	// ���µ��ڼ�
			
			StringBuffer querySQL_QC = 
				new StringBuffer(" select ")
						.append(" yb.pk_cutomer ")		// �ͻ�pk
						.append(",yb.vbdef10 ")			// ��ͬ��
						.append(",sum(yb.qmyskye) ")	// ��ĩ���
						
						.append(",max(yb.vbdef01) ")	// vbdef01 �ͻ�����
						.append(",max(yb.vbdef02) ")	// vbdef02 �����
						.append(",max(yb.vbdef03) ")	// vbdef03 ������Ŀ
						.append(",max(yb.vbdef04) ")	// vbdef04 ��������
						
						.append(",max(yb.vbdef05) ")	// vbdef05 ����pk
						.append(",max(yb.vbdef07) ")	// vbdef07 ʵ�ʺ�ͬ���
						.append(",max(yb.quyu) ")		// ����pk
						.append(",max(yb.mianji) ")		// ���
						.append(",max(yb.danjia) ")		// ����
						.append(",max(yb.srxm) ")		// ������Ŀpk
						
						.append(" from hk_zulin_yuebao y ")
						.append(" inner join hk_zulin_yuebao_b yb on y.pk_hk_zulin_yuebao = yb.pk_hk_zulin_yuebao ")
						.append(" where y.dr=0 and yb.dr=0 ")
						.append(" and y.vbilltypecode = 'HK43' ")
						.append(" and y.yearmonth = '"+syqj+"' ")
						.append(" and y.pk_org = '"+pk_org+"' ")
						.append(" group by yb.pk_cutomer, yb.vbdef10 ")	// Group By  �ͻ�+����
						.append(" having sum(yb.qmyskye)<>0.00 ")		// ȡ ��Ϊ0��
			;
			ArrayList list_QC = (ArrayList)iUAPQueryBS.executeQuery(
					querySQL_QC.toString()
					,new ArrayListProcessor()
			);
			if(list_QC!=null&&list_QC.size()>0)
			{
				for( int i=0;i<list_QC.size();i++ )
				{
					Object[] obj = (Object[])list_QC.get(i);
					
					String pk_cutomer = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					String vbdef10 = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					
					// �Է�##��ͬ��
					String key = pk_cutomer + "##" + vbdef10;
					
					UFDouble qcye = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// �ڳ����
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setQcyskye(qcye);
					}
					else
					{
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setPk_cutomer(pk_cutomer);		// �Է�
//						yuebaoBVO.setRoomno(roomno);				// ����
						yuebaoBVO.setQcyskye(qcye);					// �ڳ����
						
						/**
						 *  .append(",max(yb.vbdef01) ")	// vbdef01 �ͻ�����	3
							.append(",max(yb.vbdef02) ")	// vbdef02 �����		4
							.append(",max(yb.vbdef03) ")	// vbdef03 ������Ŀ	5
							.append(",max(yb.vbdef04) ")	// vbdef04 ��������	6
							
							.append(",max(yb.vbdef05) ")	// vbdef05 ����pk		7
							.append(",max(yb.vbdef07) ")	// vbdef07 ʵ�ʺ�ͬ���	8
							.append(",max(yb.quyu) ")		// ����pk			9
							.append(",max(yb.mianji) ")		// ���				10
							.append(",max(yb.danjia) ")		// ����				11
							.append(",max(yb.srxm) ")		// ������Ŀpk			12
						 */
						
						yuebaoBVO.setMianji(PuPubVO.getUFDouble_NullAsZero(obj[10]));			// ���
						yuebaoBVO.setDanjia(PuPubVO.getUFDouble_NullAsZero(obj[11]));			// ����
						yuebaoBVO.setSrxm(PuPubVO.getString_TrimZeroLenAsNull(obj[12]));		// ������Ŀpk
						yuebaoBVO.setQuyu(PuPubVO.getString_TrimZeroLenAsNull(obj[9]));			// ����
						yuebaoBVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(obj[3]));		// �Է�����
						yuebaoBVO.setVbdef02(PuPubVO.getString_TrimZeroLenAsNull(obj[4]));		// �����
						yuebaoBVO.setVbdef03(PuPubVO.getString_TrimZeroLenAsNull(obj[5]));		// ������Ŀ
						yuebaoBVO.setVbdef04(PuPubVO.getString_TrimZeroLenAsNull(obj[6]));		// ��������
						yuebaoBVO.setVbdef05(PuPubVO.getString_TrimZeroLenAsNull(obj[7]));		// ����pk
						yuebaoBVO.setVbdef07(PuPubVO.getString_TrimZeroLenAsNull(obj[8]));		// ʵ�ʺ�ͬ���
						yuebaoBVO.setVbmemo("���������������޷�����");	// ��ע
						
						MAP_yuebaoBVO.put(key, yuebaoBVO);
					}
				}
			}
		}
		/***END***/
		
//		/**
//		 *��ѯ ���µ�������
//		 */
//		{
//			StringBuffer querySQL_TZ = 
//				new StringBuffer(" select ")
//					.append(" tzb.vbdef11 ")
//					.append(",tzb.vbdef12 ")
//					.append(",tzb.dytzje ")
//					.append(" from hk_zulin_tiaozheng tz ")
//					.append(" inner join hk_zulin_tiaozheng_b tzb on tz.pk_hk_zulin_tiaozheng = tzb.pk_hk_zulin_tiaozheng ")
//					.append(" where tz.dr=0 and tzb.dr=0 ")
//					.append(" and tz.ibillstatus = 1 ")		// ֻȡ ���ͨ����
//					.append(" and tz.yearmonth = '"+yearMonth+"' ")
//					.append(" and tz.pk_org = '"+pk_org+"' ")
//			;
//			
//			ArrayList list_TZ = (ArrayList)iUAPQueryBS.executeQuery(
//					querySQL_TZ.toString()
//					,new ArrayListProcessor()
//			);
//			if(list_TZ!=null&&list_TZ.size()>0)
//			{
//				for( int i=0;i<list_TZ.size();i++ )
//				{
//					Object[] obj = (Object[])list_TZ.get(i);
//					
//					String pk_cutomer = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
//					String roomno = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
//					
//					// �ͻ�##�����
//					String key = pk_cutomer+"##"+roomno;
//					
//					UFDouble tzje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// �������
//					
//					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
//					if(yuebaoBVO!=null)
//					{// �����Զ���08 �洢 �������
//						yuebaoBVO.setVbdef08(tzje.toString());
//					}
//					else
//					{// ����������� û��  ��������
//						
//					}
//				}
//			}
//		}
//		/***END***/
		
		YuebaoBVO[] bodyVOs = MAP_yuebaoBVO.values().toArray(new YuebaoBVO[0]);
		
		/**
		 * ��������
		 */
		for(YuebaoBVO bvo : bodyVOs)
		{
			// ��ĩ��� = �ڳ����+���ڸ���-���ڷ���-���ڵ���
			UFDouble qmye = 
					 PuPubVO.getUFDouble_NullAsZero(bvo.getQcyskye())
				.add(PuPubVO.getUFDouble_NullAsZero(bvo.getBqskje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getVbdef08()))
			;
			bvo.setQmyskye(qmye);
			
			// ���㵥�� = �ܶ�/��/���/
			if(bvo.getDysrqrje() != null && bvo.getMianji() != null && bvo.getDysrqrts() != null)
			{
				UFDouble danjia = // ����ȷ�Ϸ���/����/���
						  bvo.getDysrqrje()
					.div( bvo.getDysrqrts() )
					.div( bvo.getMianji() )
					.setScale(8, UFDouble.ROUND_HALF_UP)
				;
				bvo.setDanjia(danjia);
			}
			
//			/**
//			 * �����
//			 * ��� ��ֹ���ڲ�Ϊ��,����������ڲ�Ϊ��,������ĩ��Ϊ0,���� ����������� = ��ֹ���ڣ� �� ����� �ӵ� ��������ȷ�Ͻ�
//			 */
//			if( bvo.getVbdef06()!=null
//			 && bvo.getJsjsrq()!=null
//			 && qmye.compareTo(UFDouble.ZERO_DBL)!=0
//			 && bvo.getVbdef06().equals(bvo.getJsjsrq().toString().substring(0,10))
//			)
//			{
//				if(
//					qmye.compareTo( PuPubVO.getUFDouble_NullAsZero(-1.0) ) >= 0
//				 && qmye.compareTo( PuPubVO.getUFDouble_NullAsZero( 1.0) ) <= 0
//				)
//				{// ����� -1 �� 1 �ڼ䣬 ���Զ���������HK-2019��1��27��17:25:30��
//					bvo.setDysrqrje( PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()).add(qmye) );
//					bvo.setQmyskye(UFDouble.ZERO_DBL);
//				}
//			}
			
		}
		/***END***/
		
		this.getEditor().getBillCardPanel().getBillModel().setBodyDataVO(bodyVOs);
		
	}
	
	/**
	 * ���� yyyymm ���� addMonth�� yyyy-mm
	 * month �����ɸ�
	 */
	public static String getYYYYMM(String yyyymm,int addMonth)
	{
		String result = null;
		
		String[] ym = yyyymm.split("-");
		int year  = PuPubVO.getInteger_NullAs(ym[0], 0);
		int month = PuPubVO.getInteger_NullAs(ym[1], 0);
		
		month = month + addMonth;
		
		if(month<=0)
		{
			year--;
			month+=12;
		}
		else if(month>12)
		{
			year++;
			month-=12;
		}
		
		result = ""+year+"-"+(month<10?"0":"")+month;
		
		return result;
	}
}
