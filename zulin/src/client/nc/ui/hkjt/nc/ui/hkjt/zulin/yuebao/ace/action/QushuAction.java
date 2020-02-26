package nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.action;

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
 * �±� ȡ��
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
			new StringBuffer()
			.append(" select ")
			.append("  substr(ctb.vbdef3,1,10) ksrq ")
			.append(" ,substr(ctb.vbdef4,1,10) jsrq ")
			.append(" ,to_number(ctb.vbdef5) danjia ")
			.append(" ,to_number(ctb.vbdef6) mianji ")	
			.append(" ,ct.pk_customer pk_customer ")	// �ͻ�pk
			.append(" ,ct.vdef16 pk_room ")				// �����pk ��vdef16��
			.append(" ,ctb.vbdef1 pk_srxm ")			// ������Ŀpk
			.append(" ,ct.vdef15 pk_quyu ")		// ����pk��vdef15��
			.append(" ,cust.name vdef01 ")		// �ͻ�-name
			.append(" ,room.name vdef02 ")		// ����-name
			.append(" ,srxm.name vdef03 ")		// ������Ŀ-name
			.append(" ,quyu.name vdef04 ")		// ����-name
			.append(" ,ct.vbillcode vdef10")	// ��ͬ��
//			.append(" ,substr(nvl(ct.actualinvalidate,'2099-12-31'),1,10) zzrq ")		// ��ͬ��ֹ����
			.append(" ,substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) zzrq ")	// ��������
			.append(" ,ct.depid vdef05 ")		// ��ͬ�Ĳ���pk
			.append(" ,st.ʵ�ʺ�ͬ��� vdef07 ")	// ʵ�ʺ�ͬ���
			.append(" from ct_sale ct ")
			.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
			.append(" left join bd_customer cust on ct.pk_customer = cust.pk_customer ")	// �ͻ�
			.append(" left join bd_defdoc srxm on ctb.vbdef1  = srxm.pk_defdoc ")	// ������Ŀ
			.append(" left join bd_defdoc room on ct.vdef16 = room.pk_defdoc ")	// ����
			.append(" left join bd_defdoc quyu on ct.vdef15 = quyu.pk_defdoc ")	// ����
			.append(" left join ( ")
			.append("	select ")
			.append("	 st.pk_ct_sale ")
			.append("	,sum( to_number(replace(nvl(st.vhkbdef6,'~'),'~','0')) ) ʵ�ʺ�ͬ��� ")
			.append("	from ct_sale_term st ")
			.append("	where st.dr=0 ")
			.append("	group by st.pk_ct_sale ")
			.append(" ) st on st.pk_ct_sale = ct.pk_ct_sale ")
			.append(" where ct.dr = 0 and ctb.dr = 0 ")
			.append(" and ct.pk_org = '"+pk_org+"' ")
			.append(" and ct.blatest = 'Y' ")
			.append(" and srxm.name not like '%Ѻ��%' ")	// ��ȡ Ѻ�� ��
			.append(" and srxm.name not like '%����%' ")	// ��ȡ ���� ��
			.append(" and ( ")
			.append("		'"+str_yb_ksrq+"' between substr(ctb.vbdef3,1,10) and substr(ctb.vbdef4,1,10) ")
			.append("    or ")
			.append("		'"+str_yb_jsrq+"' between substr(ctb.vbdef3,1,10) and substr(ctb.vbdef4,1,10) ")
			.append("     ) ")
			
			.append(" and ct.fstatusflag in (1,6) ")	// ȡ ��Ч �� ��ֹ
//			.append(" and '"+str_yb_ksrq+"' < substr(nvl(ct.actualinvalidate,'2099-12-31'),1,10) ")		// ��ʵ����ֹ���� �� �жϼƷ�ʱ��
			.append(" and '"+str_yb_ksrq+"' <= substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// ���������� �� �жϼƷ�ʱ�㣨��������� Ҫ�����ģ�
			.append(" and substr(ctb.vbdef3,1,10)<=substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// ��ͬ��ϸ�Ŀ�ʼ���� ҪС�ڵ��� ��������
			
//			.append(" and ct.vbillcode = '201809111-6-50011-6-50021-6-5003' ")		// ����
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
			
			UFDate ht_ksrq = htVO.getKsrq();	// ��ͬ��ϸ-��ʼ����
			UFDate ht_jsrq = htVO.getJsrq();	// ��ͬ��ϸ-��������
			UFDate ht_zzrq = htVO.getZzrq();	// ��ͬ��ͷ-��ֹ����
			UFDouble ht_danjia = htVO.getDanjia();	// ����
			UFDouble ht_mianji = htVO.getMianji();	// ���
			
			String ht_pk_customer = htVO.getPk_customer();	// �ͻ�pk
			String ht_pk_room = htVO.getPk_room();			// �����pk
			
			String srxm_name = htVO.getVdef03();	// ������Ŀ-����
			
			Integer yb_days = 0;
			
			UFDate js_ksrq = null;		// ���㿪ʼ����
			UFDate js_jsrq = null;		// �����������
			
			/**
			 *  ���� ��ͬ��ϸ �� �±��� ��ʼ��������  ֮��� ��С��ϵ�� ������� ����
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
			if(js_jsrq.compareTo(ht_zzrq)>0)
			{
				js_jsrq = ht_zzrq;
				htVO.setYb_jsrq(js_jsrq);
				yb_days = js_jsrq.getDaysAfter(js_ksrq)+1;
				htVO.setYb_days(yb_days);
			}
			
			//����  ���� = ���� * ��� * ����
			UFDouble yb_mny = ht_danjia.multiply(ht_mianji).multiply(yb_days).setScale(2, UFDouble.ROUND_HALF_UP);
			htVO.setYb_mny(yb_mny);
			
			// ͬʱ �� YuebaoBVO ���з�װ����
			// �� �ͻ�##�����##����  ����
			// ��Ϊ  �� �ͻ�pk##����pk  ����
			{
//				String key = ht_pk_customer + "##" + ht_pk_room + "##" + ht_danjia.toString();
				String key = ht_pk_customer + "##" + ht_pk_room;
				YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
				if(yuebaoBVO==null)
				{
					yuebaoBVO = new YuebaoBVO();
					yuebaoBVO.setPk_cutomer(ht_pk_customer);	// �ͻ�
					yuebaoBVO.setRoomno(ht_pk_room);			// ����
					yuebaoBVO.setJsksrq(js_ksrq);				// ���㿪ʼ����
					yuebaoBVO.setJsjsrq(js_jsrq);				// �����������
					yuebaoBVO.setDysrqrts(PuPubVO.getUFDouble_NullAsZero(yb_days));	// ��������ȷ������
					yuebaoBVO.setDysrqrje(yb_mny);				// ��������ȷ�Ͻ��
					yuebaoBVO.setMianji(ht_mianji);				// ���
					yuebaoBVO.setDanjia(ht_danjia);				// ����
					
					yuebaoBVO.setSrxm(htVO.getPk_srxm());		// ������Ŀ
					yuebaoBVO.setQuyu(htVO.getPk_quyu());		// ����
					yuebaoBVO.setVbdef01(htVO.getVdef01());
					yuebaoBVO.setVbdef02(htVO.getVdef02());
					yuebaoBVO.setVbdef03(htVO.getVdef03());
					yuebaoBVO.setVbdef04(htVO.getVdef04());
					yuebaoBVO.setVbdef05(htVO.getVdef05());
//					yuebaoBVO.setVbmemo(htVO.getVdef10());		// ��ע��ź�ͬ��
					yuebaoBVO.setVbdef06( ht_zzrq!=null ? ht_zzrq.toString().substring(0,10) : null );	// ��ͬ��ֹ����  ��HK 2018��12��26��17:39:31��
					yuebaoBVO.setVbdef07(htVO.getVdef07());		// ʵ�ʺ�ͬ���
				}
				else
				{
					yuebaoBVO.setDysrqrts(
							yuebaoBVO.getDysrqrts().add(
							PuPubVO.getUFDouble_NullAsZero(yb_days) )
					);	// ��������ȷ������
					yuebaoBVO.setDysrqrje(
							yuebaoBVO.getDysrqrje().add(
							yb_mny )
					);	// ��������ȷ�Ͻ��
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
		 * ��ѯ ���������ݣ���ָ���� ��ͬ�����е� �������ݣ�
		 */
		{
			StringBuffer querySQL_TZ = 
				new StringBuffer()
				.append(" select ")
				.append("  substr(ctb.vbdef3,1,10) ksrq ")	// ��ʼ����
				.append(" ,ct.pk_customer pk_customer ")	// �ͻ�pk
				.append(" ,ct.vdef16 pk_room ")				// �����pk ��vdef16��
				.append(" ,ctb.vbdef1 pk_srxm ")			// ������Ŀpk
				.append(" ,ctb.norigtaxmny danjia ")		// ��˰�ϼ� ���� �����ֶ� ���ݴ棩
				.append(" ,srxm.name vdef03 ")				// ������Ŀ-name
				.append(" from ct_sale ct ")
				.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
				.append(" left join bd_customer cust on ct.pk_customer = cust.pk_customer ")	// �ͻ�
				.append(" left join bd_defdoc srxm on ctb.vbdef1  = srxm.pk_defdoc ")			// ������Ŀ
				.append(" where ct.dr = 0 and ctb.dr = 0 ")
				.append(" and ct.pk_org = '"+pk_org+"' ")
				.append(" and ct.blatest = 'Y' ")
				.append(" and srxm.name like '%����%' ")	// ֻȡ ���� ��
				.append(" and ( ")
				.append("		'"+str_yb_ksrq+"' between substr(ctb.vbdef3,1,10) and substr(ctb.vbdef4,1,10) ")
				.append("    or ")
				.append("		'"+str_yb_jsrq+"' between substr(ctb.vbdef3,1,10) and substr(ctb.vbdef4,1,10) ")
				.append("     ) ")
				.append(" and ct.fstatusflag in (1,6) ")	// ȡ ��Ч �� ��ֹ
				.append(" and '"+str_yb_ksrq+"' < substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// ���������� �� �жϼƷ�ʱ��
				.append(" and substr(ctb.vbdef3,1,10)<=substr(nvl(replace(ct.vdef19,'~',''),'2099-12-31'),1,10) ")	// ��ͬ��ϸ�Ŀ�ʼ���� ҪС�ڵ��� ��������
			;
			
			ArrayList<QueryHtVO> list_TZ = (ArrayList<QueryHtVO>)iUAPQueryBS.executeQuery(
					 querySQL_TZ.toString()
					,new BeanListProcessor(QueryHtVO.class)
			);
				
			if(list_TZ!=null&&list_TZ.size()>0)
			{
				for(int i=0;i<list_TZ.size();i++)
				{
					QueryHtVO queryHtVO = list_TZ.get(i);
					String key = queryHtVO.getPk_customer() + "##" + queryHtVO.getPk_room();
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						// ����ȷ������ ����
						yuebaoBVO.setDysrqrje(
								 yuebaoBVO.getDysrqrje()
							.add(queryHtVO.getDanjia())
						);
						
						yuebaoBVO.setDanjia(null);	// ������ ��ֵ��null�� ���� ���¼��㵥��
						
						String vbmemo = 
								"��" +
								queryHtVO.getVdef03() + "��" +
								queryHtVO.getKsrq().toString().substring(0,10)   + "��" +
								queryHtVO.getDanjia() + "" +
								"�� "
							;
						
						yuebaoBVO.setVbmemo(
							yuebaoBVO.getVbmemo()==null
							? vbmemo
							: yuebaoBVO.getVbmemo()+vbmemo
						);
					}
				}
			}
		}
		
		/**
		 * ��ѯ �����տ��
		 */
		{
			StringBuffer querySQL_SK = 
				new StringBuffer(" select ")
						.append(" a.pk_customer ")				// �ͻ�pk
						.append(",a.pk_room ")					// ����pk
						.append(",sum(a.skje) skje ")			// �տ���
						.append(",max(a.pk_quyu) pk_quyu ")		// ����pk
						.append(",max(a.pk_dept) pk_dept ")		// ����pk
						.append(",max(cust.name) vdef01 ")		// �ͻ�-name
						.append(",max(room.name) vdef02 ")		// ����-name
						.append(",max(quyu.name) vdef04 ")		// ����-name
						.append(" from (")
						// �����տ
							.append(" select ")
							.append(" s.pk_customer ")			// �ͻ�pk
							.append(",s.vdef16 pk_room ")		// ����pk
							.append(",max(s.vdef15) pk_quyu ")	// ����pk
							.append(",max(s.depid)  pk_dept ")	// ����pk
							.append(",sum(gb.money_cr) skje ")	// �տ���
							.append(" from ar_gatherbill g ")
							.append(" inner join ar_gatheritem gb on g.pk_gatherbill = gb.pk_gatherbill ")
							.append(" left join ct_sale s on gb.top_billid = s.pk_ct_sale ")
							.append(" left join ct_sale_b sb on gb.top_itemid = sb.pk_ct_sale_b ")
							.append(" left join bd_defdoc srxm on sb.vbdef1  = srxm.pk_defdoc ")	// ������Ŀ
							.append(" where g.dr=0 and gb.dr=0 ")
							.append(" and gb.top_tradetype = 'Z3-01' ")		// ���޺�ͬ
							.append(" and srxm.name not like '%Ѻ��%' ")		// ��ȡѺ����տ�
							.append(" and substr(gb.busidate,1,10) between '"+str_yb_ksrq+"' and '"+str_yb_jsrq+"' ")
							.append(" and s.pk_org = '"+pk_org+"' ")
							.append(" group by s.pk_customer,s.vdef16 ")	// Group By  �ͻ�+����
						// ����տ
						.append(" union all ")
							.append(" select ")
							.append(" s.pk_customer ")			// �ͻ�pk
							.append(",s.vdef16 pk_room ")		// ����pk
							.append(",max(s.vdef15) pk_quyu ")	// ����pk
							.append(",max(s.depid)  pk_dept ")	// ����pk
							.append(",sum(gb.money_cr) skje ")	// �տ���
							.append(" from ar_gatherbill g ")
							.append(" inner join ar_gatheritem gb on g.pk_gatherbill = gb.pk_gatherbill ")
							.append(" left join ar_gatheritem gb_lan ")
							.append(" on gb.top_itemid = gb_lan.pk_gatheritem and gb.top_billid = gb_lan.pk_gatherbill and gb_lan.top_tradetype = 'Z3-01' ")
							.append(" left join ct_sale s on gb_lan.top_billid = s.pk_ct_sale ")
							.append(" left join ct_sale_b sb on gb_lan.top_itemid = sb.pk_ct_sale_b ")
							.append(" left join bd_defdoc srxm on sb.vbdef1  = srxm.pk_defdoc ")	// ������Ŀ
							.append(" where g.dr=0 and gb.dr=0 ")
							.append(" and gb.top_tradetype = 'F2-Cxx-01' ")	// Ԥ�յ�
							.append(" and srxm.name not like '%Ѻ��%' ")		// ��ȡѺ����տ�
							.append(" and substr(gb.busidate,1,10) between '"+str_yb_ksrq+"' and '"+str_yb_jsrq+"' ")
							.append(" and s.pk_org = '"+pk_org+"' ")
							.append(" group by s.pk_customer,s.vdef16 ")	// Group By  �ͻ�+����
						.append(" ) a ")
						.append(" left join bd_customer cust on a.pk_customer = cust.pk_customer ")	// �ͻ�
						.append(" left join bd_defdoc room on a.pk_room = room.pk_defdoc ")			// ����
						.append(" left join bd_defdoc quyu on a.pk_quyu = quyu.pk_defdoc ")			// ����
						.append(" group by a.pk_customer,a.pk_room  ")
			;
			ArrayList list_SK = (ArrayList)iUAPQueryBS.executeQuery(
					querySQL_SK.toString()
					,new ArrayListProcessor()
			);
			if(list_SK!=null&&list_SK.size()>0)
			{
				for( int i=0;i<list_SK.size();i++ )
				{
					Object[] obj = (Object[])list_SK.get(i);
					// �ͻ�##�����
					String key = obj[0]+"##"+obj[1];
					
					UFDouble skje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// �տ���
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setBqskje(skje);
					}
					else
					{ /**
					   * HK
					   * 2018��12��25��18:00:53
					   * �� �޵����������ݵ� �տ�����뵽���塣
					   */
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setBqskje(skje);		// �����տ���
						yuebaoBVO.setQmyskye(skje);		// ��ĩԤ�տ���� = �����տ���
						
						yuebaoBVO.setPk_cutomer(PuPubVO.getString_TrimZeroLenAsNull(obj[0]));	// �ͻ�pk
						yuebaoBVO.setRoomno(PuPubVO.getString_TrimZeroLenAsNull(obj[1]));		// ����pk
						yuebaoBVO.setQuyu(PuPubVO.getString_TrimZeroLenAsNull(obj[3]));			// ����pk
						yuebaoBVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(obj[5]));		// �ͻ�-name
						yuebaoBVO.setVbdef02(PuPubVO.getString_TrimZeroLenAsNull(obj[6]));		// ����-name
						yuebaoBVO.setVbdef04(PuPubVO.getString_TrimZeroLenAsNull(obj[7]));		// ����-name
						yuebaoBVO.setVbdef05(PuPubVO.getString_TrimZeroLenAsNull(obj[4]));		// ����pk
						
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
						.append(",yb.roomno ")			// ����pk
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
						.append(" and y.vbilltypecode = 'HK37' ")
						.append(" and y.yearmonth = '"+syqj+"' ")
						.append(" and y.pk_org = '"+pk_org+"' ")
						.append(" group by yb.pk_cutomer,yb.roomno ")	// Group By  �ͻ�+����
						.append(" having sum(yb.qmyskye) <> 0.00 ")		// ȡ ��Ϊ0��
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
					String roomno = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					
					// �ͻ�##�����
					String key = pk_cutomer+"##"+roomno;
					
					UFDouble qcye = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// �ڳ����
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setQcyskye(qcye);
					}
					else
					{
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setPk_cutomer(pk_cutomer);		// �ͻ�
						yuebaoBVO.setRoomno(roomno);				// ����
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
						yuebaoBVO.setSrxm(PuPubVO.getString_TrimZeroLenAsNull(obj[12]));			// ������Ŀpk
						yuebaoBVO.setQuyu(PuPubVO.getString_TrimZeroLenAsNull(obj[9]));			// ����
						yuebaoBVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(obj[3]));		// �ͻ�����
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
		
		/**
		 *��ѯ ���µ�������
		 */
		{
			StringBuffer querySQL_TZ = 
				new StringBuffer(" select ")
					.append(" tzb.vbdef11 ")
					.append(",tzb.vbdef12 ")
					.append(",tzb.dytzje ")
					.append(" from hk_zulin_tiaozheng tz ")
					.append(" inner join hk_zulin_tiaozheng_b tzb on tz.pk_hk_zulin_tiaozheng = tzb.pk_hk_zulin_tiaozheng ")
					.append(" where tz.dr=0 and tzb.dr=0 ")
					.append(" and tz.vbilltypecode = 'HK38' ")	// �������͹���
					.append(" and tz.ibillstatus = 1 ")			// ֻȡ ���ͨ����
					.append(" and tz.yearmonth = '"+yearMonth+"' ")
					.append(" and tz.pk_org = '"+pk_org+"' ")
			;
			
			ArrayList list_TZ = (ArrayList)iUAPQueryBS.executeQuery(
					querySQL_TZ.toString()
					,new ArrayListProcessor()
			);
			if(list_TZ!=null&&list_TZ.size()>0)
			{
				for( int i=0;i<list_TZ.size();i++ )
				{
					Object[] obj = (Object[])list_TZ.get(i);
					
					String pk_cutomer = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					String roomno = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					
					// �ͻ�##�����
					String key = pk_cutomer+"##"+roomno;
					
					UFDouble tzje = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// �������
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{// �����Զ���08 �洢 �������
						yuebaoBVO.setVbdef08(tzje.toString());
					}
					else
					{// ����������� û��  ��������
						
					}
				}
			}
		}
		/***END***/
		
		YuebaoBVO[] bodyVOs = new YuebaoBVO[MAP_yuebaoBVO.size()];
		
		bodyVOs = MAP_yuebaoBVO.values().toArray(bodyVOs);
		
		/**
		 * ��������
		 */
		for(YuebaoBVO bvo : bodyVOs)
		{
			// ��ĩ��� = �ڳ����+�����տ�-��������-���ڵ���
			UFDouble qmye = 
					 PuPubVO.getUFDouble_NullAsZero(bvo.getQcyskye())
				.add(PuPubVO.getUFDouble_NullAsZero(bvo.getBqskje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getVbdef08()))
			;
			bvo.setQmyskye(qmye);
			
			// ����Ϊ�յģ���Ҫ���㵥��
			if(bvo.getDanjia()==null && bvo.getDysrqrje()!=null)
			{
				UFDouble danjia = // ����ȷ������/����/���
						  bvo.getDysrqrje()
					.div( bvo.getDysrqrts() )
					.div( bvo.getMianji() )
					.setScale(8, UFDouble.ROUND_HALF_UP)
				;
				bvo.setDanjia(danjia);
			}
			
			/**
			 * �����
			 * ��� ��ֹ���ڲ�Ϊ��,����������ڲ�Ϊ��,������ĩ��Ϊ0,���� ����������� = ��ֹ���ڣ� �� ����� �ӵ� ��������ȷ�Ͻ�
			 */
			if( bvo.getVbdef06()!=null
			 && bvo.getJsjsrq()!=null
			 && qmye.compareTo(UFDouble.ZERO_DBL)!=0
			 && bvo.getVbdef06().equals(bvo.getJsjsrq().toString().substring(0,10))
			)
			{
				if(
					qmye.compareTo( PuPubVO.getUFDouble_NullAsZero(-1.0) ) >= 0
				 && qmye.compareTo( PuPubVO.getUFDouble_NullAsZero( 1.0) ) <= 0
				)
				{// ����� -1 �� 1 �ڼ䣬 ���Զ���������HK-2019��1��27��17:25:30��
					bvo.setDysrqrje( PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()).add(qmye) );
					bvo.setQmyskye(UFDouble.ZERO_DBL);
				}
			}
			
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
