package nc.ui.hkjt.zulin.bkfyft.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IPub_data;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.zulin.yuebao.QueryHtVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
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
		// �Ƿ� ӡ��˰
		UFBoolean isYhs = PuPubVO.getUFBoolean_NullAs(
				this.getEditor().getBillCardPanel().getHeadItem("vdef02").getValueObject()
				, UFBoolean.FALSE
		);
		// ӡ��˰�Ĵ���
		if (isYhs.booleanValue()) {
			this.handleYhs(pk_org, str_yb_ksrq, str_yb_jsrq);
			return;
		}
		// �Ƿ�����ƾ֤
		UFBoolean isZeren = PuPubVO.getUFBoolean_NullAs(
				this.getEditor().getBillCardPanel().getHeadItem("vdef03").getValueObject()
				, UFBoolean.FALSE
		);
		// ����ƾ֤�Ĵ���
		String whereSql_zeren = " and nvl(ht.vhkfield01, 'N') in ('N', '~') ";
		String whereSql_zeren_sy = " and nvl(y.vdef03, 'N') in ('N', '~') "; // ��ѯ����
		String whereSql_zeren_tz = " and nvl(tz.vdef03, 'N') in ('N', '~') "; // ��ѯ����
		if (isZeren.booleanValue()) {
			whereSql_zeren = " and nvl(ht.vhkfield01, 'N') = 'Y' ";
			whereSql_zeren_sy = " and nvl(y.vdef03, 'N') = 'Y' ";	// ��ѯ����
			whereSql_zeren_tz = " and nvl(tz.vdef03, 'N') = 'Y' "; // ��ѯ����
		}
		StringBuffer querySQL = 
				// ��Ч�Ĵ���ȡ ��ͬ��������
		new StringBuffer("select ")
				.append(" htb.vbdef1 pk_srxm ")	// ֧����Ŀpk
				.append(",szxm.name vdef03 ")	// ֧����Ŀname
				.append(",substr(htb.vbdef3,1,10) ksrq ")	// ��ͬ��ʼ����
//				.append(",case when ht.actualinvalidate is null then substr(htb.vbdef4,1,10) else substr(ht.actualinvalidate,1,10) end jsrq ")	// ��ͬ��������
				.append(",case when nvl(ht.invallidate,'~') = '~' then substr(htb.vbdef4,1,10) " +
						" else " +
						/**
						 * HK 2020��9��21��19:55:02
						 * ����� ��̯��ֹ���ڣ����� С�� ����������ڣ����� ��̯��ֹ���� Ϊ׼��
						 */
//						" 	substr(ht.invallidate,1,10) " +
						"	(case when ht.invallidate < htb.vbdef4 then substr(ht.invallidate, 1, 10) else substr(htb.vbdef4, 1, 10) end) " +
						" end jsrq ")	// ��ͬ��������
				.append(",htb.norigmny vdef11 ")	// ��˰���
				.append(",htb.norigtaxmny vdef12 ")	// ��˰���
				.append(",ht.depid vdef05 ")	// ����pk
				.append(",dept.name vdef04 ")	// ����name
				.append(",fplx.name vdef02 ")	// ��Ʊ����name
				.append(",ht.vbillcode vdef10 ")// ��ͬ��
				.append(",to_number(nvl(replace(ht.vdef8,'~',''),'0.0')) + to_number(nvl(replace(ht.vdef11,'~',''),'0.0')) mianji ")// ���
				.append(",ht.cvendorid pk_customer ")	// �Է�pk
				.append(",gys.name vdef01 ")			// �Է�name
//				.append(",ht.ntotalorigmny vdef07 ")	// ��ͬ�ܶ�
				.append(",htze.htze vdef07 ")	// ��ͬ�ܶ�
				.append(",substr(ht.valdate,1,10) vdef08 ")			// �����ͬ��ʼ����	valdate
				.append(",substr(ht.invallidate,1,10) vdef09 ")		// �����ͬ��������	invallidate
				.append(",to_number(nvl(replace(htb.vbdef5,'~',''),'0.0')) vdef13 ")	// ���㵥��
				.append(",to_number(nvl(replace(replace(sl.name,'~',''),'%',''),'0.0')) vdef14 ")		// ˰��
//				.append(",ht.vhkfield01 vdef21 ") // ��������ƾ֤
//				.append(",'N' vdef21 ") // ��������ƾ֤
				.append(" from ct_pu ht ")		// ��ͬ��ͷ
				.append(" inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")	// ��ͬ����
				.append(" left join bd_defdoc fplx on ht.vdef3 = fplx.pk_defdoc ")	// ��Ʊ����
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")	// ��֧��Ŀ
				.append(" left join org_dept dept on ht.depid = dept.pk_dept ")		// ����
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")	// ��Ӧ��
				.append(" left join bd_defdoc sl on ht.vdef4 = sl.pk_defdoc ")		// ˰��
				// ���� ��ͬ��Ϣ����ȡ �ʱ���� ��ͬ�ܶ�
				.append(" left join (")
				.append("	select ")
				.append("	 sum(htb.norigtaxmny) htze ")
				.append("	,ht.pk_ct_pu ")
				.append("	from ct_pu ht ")
				.append("	inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")
				.append(" 	left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")
				.append("	where ht.dr = 0 and htb.dr = 0 ")
				.append(" 	and ht.vtrantypecode = '").append(IPub_data.BKHT_type_code).append("' ")
				.append(" 	and szxm.code not in ('2005', '2022') ")
				.append(" 	and ht.pk_org = '").append(pk_org).append("' ")
				.append(" 	and ht.blatest = 'Y' ")
				.append(" 	and ht.fstatusflag in (1, 6) ")
//				.append(" 	and ht.fstatusflag in (1) ")
				.append("	group by ht.pk_ct_pu ")
				.append(" ) htze on (htze.pk_ct_pu = ht.pk_ct_pu)")
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.vtrantypecode = '").append(IPub_data.BKHT_type_code).append("' ")
				// ��ȡ��֤��
				.append(" and szxm.code not in ('2005', '2022') ")
				.append(" and ht.pk_org = '").append(pk_org).append("' ")
				// ֻȡ���°�
				.append(" and ht.blatest = 'Y' ")
				// ֻȡ��Ч�� �� ��ֹ��
				.append(" and ht.fstatusflag in (1, 6) ")
				// ʱ�䷶Χ
				.append(" and ( ")
				.append(" 		'").append(str_yb_ksrq).append("' between substr(htb.vbdef3,1,10) and substr(htb.vbdef4,1,10) ")
				.append(" 	or ")
				.append("  		'").append(str_yb_jsrq).append("' between substr(htb.vbdef3,1,10) and substr(htb.vbdef4,1,10) ")
				.append("	or ")
				.append("		(substr(htb.vbdef3, 1, 10) > '"+str_yb_ksrq+"' and substr(htb.vbdef4, 1, 10) < '"+str_yb_jsrq+"') ")
				.append(" ) ")
//				.append(" and '"+str_yb_ksrq+"' <= substr(nvl(replace(ht.actualinvalidate, '~', ''), '2099-12-31'), 1, 10) ")	// ��ͬ��ֹ���� �� �жϼƷ�ʱ�㣨��ֹ������ Ҫ�����ģ�
//				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.actualinvalidate, '~', ''), '2099-12-31'), 1, 10) ")	// ��ͬ��ϸ�Ŀ�ʼ���� ҪС�ڵ��� ��ͬ��ֹ����
				.append(" and '"+str_yb_ksrq+"' <= substr(nvl(replace(ht.invallidate, '~', ''), '2099-12-31'), 1, 10) ")	// ��ͬ��ֹ���� �� �жϼƷ�ʱ�㣨��ֹ������ Ҫ�����ģ�
				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.invallidate, '~', ''), '2099-12-31'), 1, 10) ")	// ��ͬ��ϸ�Ŀ�ʼ���� ҪС�ڵ��� ��ͬ��ֹ����
				.append(whereSql_zeren) // ����ƾ֤��sql
//				.append(" and ht.vbillcode like '04162019023879%' ")	// ����
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
			if ("ר�÷�Ʊ".equals(fplx)) {		// רƱ ����˰
				ht_mny = htVO.getVdef11();
			}
			
			String ht_code = htVO.getVdef10();	// ��ͬ��
			UFDate ht_ksrq = htVO.getKsrq();	// ��ͬ��ϸ-��ʼ����
			UFDate ht_jsrq = htVO.getJsrq();	// ��ͬ��ϸ-��������
			Integer ht_days = ht_jsrq.getDaysAfter(ht_ksrq) + 1;	// ��ͬ����
			UFDouble ht_mianji = htVO.getMianji();	// ���
			UFDouble ht_mianji_js = UFDouble.ONE_DBL;	// �����õ������
			if (PuPubVO.getUFDouble_ZeroAsNull(ht_mianji) != null) {
				ht_mianji_js = ht_mianji;
			}
			/**
			 *  �ж�һ�£�
			 *  1��������㵥�� Ϊ�գ�����֮ǰ��ģʽ��
			 *  2��������㵥�� ��Ϊ�ա�������ģʽ��
			 */
			UFDouble ht_danjia = ht_mny.div(ht_days);	// ÿ��ĵ��� = ��ͬ��� / ��ͬ����
			UFDouble ht_jsdj = PuPubVO.getUFDouble_ZeroAsNull(htVO.getVdef13());	// ���㵥��
			UFDouble ht_sl 	= htVO.getVdef14();		// ˰��
			if ( ht_jsdj != null) {
				ht_danjia = ht_jsdj.multiply(ht_mianji_js);// ÿ��ĵ��� = ���㵥�� * ���
				if ("ר�÷�Ʊ".equals(fplx)) {				// רƱ ����˰
					ht_danjia = ht_danjia.div( UFDouble.ONE_DBL.add(ht_sl.div(100.00)) );	// ȥ��˰
				}
			}
			
//			UFDate ht_zzrq = htVO.getZzrq();	// ��ͬ��ͷ-��ֹ����
			
			String ht_pk_customer = htVO.getPk_customer();	// �Է�pk
			
			String srxm_name = htVO.getVdef03();	// ֧����Ŀ-����
			
			UFDate zthtKsrq = PuPubVO.getUFDate(htVO.getVdef08());	// �����ͬ��ʼ����
			UFDate zthtJsrq = PuPubVO.getUFDate(htVO.getVdef09());	// �����ͬ��������
			Integer zthtTs = zthtJsrq.getDaysAfter(zthtKsrq) + 1;	// �����ͬ����
			
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
			else if(
				ht_ksrq.compareTo(yb_ksrq)>0
			 &&	ht_jsrq.compareTo(yb_jsrq)<=0	
			)
			{ // ��� ��ͬ��ϸ��ʼ���� ���� �±���ʼ����  ����  ��ͬ��ϸ�������� С�� �±���������
			  // ˵�������£��Ʒ����� = ��ͬ��ϸ��ʼ���� �� ��ͬ��ϸ��������
			  // 2020��6��16��23:45:07
				yb_days = ht_jsrq.getDaysAfter(ht_ksrq)+1;	// ��ͬ��ϸ��������-��ͬ��ϸ��ʼ����+1
				htVO.setYb_days(yb_days);	// �Ʒ�����
				
				js_ksrq = ht_ksrq;
				js_jsrq = ht_jsrq;
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
				
				/**
				 * HK 2020��2��17��17:19:12
				 * ���� ������ȡ����������Ϊ������ ˵��Ϊ �˿��Ҫ�ۼ�����
				 */
				if (yb_mny.compareTo(UFDouble.ZERO_DBL) < 0) {
					yb_days = 0 - yb_days;
				}
				/***END***/
				
				if(yuebaoBVO == null)
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
					yuebaoBVO.setVbdef09(zthtTs.toString());	// �����ͬ����
//					yuebaoBVO.setCsourcetypecode(htVO.getVdef21());	// ��������ƾ֤ csourcetypecode
					
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
					
					// ��� ֮ǰ����� ����Ӫҵ����𣬱����� Ӫҵ����� ���� Ӫҵ����� �滻֮ǰ�ġ�
					if ("����Ӫҵ�����".equals(yuebaoBVO.getVbdef03())) {
						yuebaoBVO.setVbdef03(htVO.getVdef03());	// ֧����Ŀ-����
						yuebaoBVO.setSrxm(htVO.getPk_srxm());	// ֧����Ŀpk
					}
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
			// ���ڸ�����Ӹ������������ͬ������רƱ����˰�ʵ�����£����ڸ��������˰�ʻ���ɲ��� ˰�Ľ�
			// Ҳ����59621.4/1.05=56782.29
			StringBuffer querySQL_FK = 
			new StringBuffer(" select ")
					.append(" a.pk_customer ")				// 0�ͻ�pk
					.append(",a.vdef10 ")					// 1����pk
					.append(",sum(a.fkje) fkje ")			// 2������
					.append(",max(a.fplx) ")				// 3��Ʊ����
					.append(",max(a.sl) ") 					// 4˰��
					.append(",max(a.gys_name) vdef01 ")		// 5�Է�-name
					.append(" from (")
					// ���ָ������帶���src�ֶζ��Ǻ�ͬ�����Կ���ͳһ����
						.append(" select ")
						.append(" ht.cvendorid pk_customer ")	// �Է�pk
						.append(",ht.vbillcode vdef10 ")		// ��ͬ��
						.append(",sum(fkb.money_de) fkje ")		// ������
						.append(",max(fplx.name) fplx ")		// ��Ʊ����name
						.append(",max(to_number(nvl(replace(replace(sl.name,'~',''),'%',''),'0.0'))) sl ")		// ˰��
						.append(",max(gys.name) gys_name ")	// ��Ӧ������
						.append(" from ap_paybill fk ")
						.append(" inner join ap_payitem fkb on fk.pk_paybill = fkb.pk_paybill ")
						.append(" inner join ct_pu ht on fkb.src_billid = ht.pk_ct_pu ")
						.append(" inner join ct_payplan fkjh on fkb.src_itemid = fkjh.pk_ct_payplan ")
						.append(" left join ct_pu_b htb on (ht.pk_ct_pu = htb.pk_ct_pu and htb.dr = 0 and fkjh.crowno = htb.crowno) ")
						.append(" left join bd_defdoc fplx on ht.vdef3 = fplx.pk_defdoc ")	// ��Ʊ����
						.append(" left join bd_defdoc sl on ht.vdef4 = sl.pk_defdoc ")		// ˰��
						.append(" left join bd_inoutbusiclass szxm on fkb.def13 = szxm.pk_inoutbusiclass ")// ������Ŀ
						.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ") // ��Ӧ��
						.append(" where fk.dr = 0 and fkb.dr = 0 ")
						.append(" and ht.dr = 0 and fkjh.dr = 0 ")
						.append(" and fk.billstatus = 8 ") // ��� ȡ ��ǩ�� ״̬
						.append(" and nvl(szxm.name, 'NULL') not like '%��֤��%' ")	// ��ȡ ��֤�� �ĸ���
						.append(" and ht.pk_org = '").append(pk_org).append("' ")
						.append(whereSql_zeren) // ����ƾ֤�Ĺ���
//						.append(" and fk.billyear || '-' || fk.billperiod = '").append(yearMonth).append("' ")
						.append(" and fk.signdate between '").append(str_yb_ksrq).append(" 00:00:00' and '").append(str_yb_jsrq).append(" 23:59:59' ")
						.append(" group by ht.cvendorid, ht.vbillcode ")
					.append(" ) a ")
					.append(" group by a.pk_customer, a.vdef10 ")
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
					
					String fplx = PuPubVO.getString_TrimZeroLenAsNull(obj[3]);	// ��Ʊ����
					UFDouble ht_sl = PuPubVO.getUFDouble_NullAsZero(obj[4]);	// ��ͬ˰��
					if ("ר�÷�Ʊ".equals(fplx)) {// רƱ Ҫȥ��˰
						skje = skje.div(UFDouble.ONE_DBL.add(ht_sl.div(100.00))).setScale(2, UFDouble.ROUND_HALF_UP);	// ȥ��˰
					}
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setBqskje(skje);
					}
					else
					{ /**
					   * HK
					   * 2020��3��19��13:40:26
					   * �� �޵����������ݵ� ��������뵽���塣
					   */
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setBqskje(skje);		// �����տ���
						yuebaoBVO.setQmyskye(skje);		// ��ĩԤ�տ���� = �����տ���
						
						yuebaoBVO.setPk_cutomer(PuPubVO.getString_TrimZeroLenAsNull(obj[0]));	// �ͻ�pk
						yuebaoBVO.setVbdef10(PuPubVO.getString_TrimZeroLenAsNull(obj[1]));		// ��ͬ��
						yuebaoBVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(obj[5])); 		// �Է�����
						
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
						.append(",max(yb.vbdef09) ")	// δ̯������
						
						.append(" from hk_zulin_yuebao y ")
						.append(" inner join hk_zulin_yuebao_b yb on y.pk_hk_zulin_yuebao = yb.pk_hk_zulin_yuebao ")
						.append(" where y.dr=0 and yb.dr=0 ")
						.append(" and y.vbilltypecode = 'HK43' ")
						.append(" and nvl(y.vdef02, 'N') in ('~', 'N', 'n') ")	// ֻȡ ��ӡ��˰��
						.append(whereSql_zeren_sy)	// �� �Ƿ�����ƾ֤��ȡ���µ����
						.append(" and y.yearmonth = '"+syqj+"' ")
						.append(" and y.pk_org = '"+pk_org+"' ")
						.append(" group by yb.pk_cutomer, yb.vbdef10 ")	// Group By  �ͻ�+����
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
					String vbdef10 = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					
					// �Է�##��ͬ��
					String key = pk_cutomer + "##" + vbdef10;	// key
					
					UFDouble qcye = PuPubVO.getUFDouble_NullAsZero(obj[2]);		// �ڳ����
					
					Integer ls_wtxts = PuPubVO.getInteger_NullAs(obj[13], 0);	// δ̯������
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{
						yuebaoBVO.setQcyskye(qcye);
						// ���������ʷ���ݣ���ȡ ��ʷ��δ̯������
						yuebaoBVO.setVbdef09(ls_wtxts.toString());
					}
					else
					{
						yuebaoBVO = new YuebaoBVO();
						yuebaoBVO.setPk_cutomer(pk_cutomer);		// �Է�
//						yuebaoBVO.setRoomno(roomno);				// ����
						yuebaoBVO.setQcyskye(qcye);					// �ڳ����
						
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
						yuebaoBVO.setVbdef09(ls_wtxts.toString());	// δ̯������
						yuebaoBVO.setVbdef10(vbdef10);	// ��ͬ��
						yuebaoBVO.setVbmemo("���������������޷�����");	// ��ע
						
						MAP_yuebaoBVO.put(key, yuebaoBVO);
					}
				}
			}
		}
		/***END***/
		
		/**
		 * ��ѯ ���µ�������
		 */
		{
			StringBuffer querySQL_TZ = 
				new StringBuffer(" select ")
					.append(" tzb.vbdef11 ")	// �Է�pk
					.append(",tzb.vbdef12 ")	// ��ͬ��
					.append(",tzb.dytzje ")		// ���·��õ���
					.append(",tzb.vbdef01 ")	// ����Ԥ������
					.append(",tzb.vbdef02 ")	// ����̯����������
					.append(" from hk_zulin_tiaozheng tz ")
					.append(" inner join hk_zulin_tiaozheng_b tzb on tz.pk_hk_zulin_tiaozheng = tzb.pk_hk_zulin_tiaozheng ")
					.append(" where tz.dr=0 and tzb.dr=0 ")
					.append(" and tz.vbilltypecode = 'HK44' ")	// �������͹���
					.append(" and tz.ibillstatus = 1 ")		// ֻȡ ���ͨ����
					.append(" and tz.yearmonth = '"+yearMonth+"' ")
					.append(" and tz.pk_org = '"+pk_org+"' ")
					.append(whereSql_zeren_tz)	// �� �Ƿ�����ƾ֤��ȡ������
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
					
					// �Է�##��ͬ��
					String key = pk_cutomer+"##"+roomno;
					
					UFDouble tzje = PuPubVO.getUFDouble_ZeroAsNull(obj[2]);		// �������
					UFDouble yfje = PuPubVO.getUFDouble_ZeroAsNull(obj[3]);		// Ԥ���������
					Integer txTs  = PuPubVO.getInteger_NullAs(obj[4], 0);	// ̯������
					
					YuebaoBVO yuebaoBVO = MAP_yuebaoBVO.get(key);
					if(yuebaoBVO!=null)
					{	// �����Զ���08 �洢 �������
						yuebaoBVO.setVbdef08(tzje==null?null:tzje.toString());
						// ���ε��ݺ� �洢 Ԥ���������
						yuebaoBVO.setVsourcebillcode(yfje==null?null:yfje.toString());
						// ̯������
						Integer txTs_VO = (PuPubVO.getInteger_NullAs(yuebaoBVO.getVbdef09(),0)
								+ txTs);
						yuebaoBVO.setVbdef09(
							txTs_VO.toString()
						);
					}
					else
					{// ����������� û��  ��������
						
					}
				}
			}
		}
		/***END***/
		
		YuebaoBVO[] bodyVOs = MAP_yuebaoBVO.values().toArray(new YuebaoBVO[0]);
		
		/**
		 * ��������
		 */
		for(YuebaoBVO bvo : bodyVOs)
		{
			// ��ĩ��� = �ڳ����+���ڸ���+����Ԥ������-���ڷ���-���ڵ���
			UFDouble qmye = 
					 PuPubVO.getUFDouble_NullAsZero(bvo.getQcyskye())
				.add(PuPubVO.getUFDouble_NullAsZero(bvo.getBqskje()))
				.add(PuPubVO.getUFDouble_NullAsZero(bvo.getVsourcebillcode()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()))
				.sub(PuPubVO.getUFDouble_NullAsZero(bvo.getVbdef08()))
			;
			bvo.setQmyskye(qmye);
			UFDouble mianji = PuPubVO.getUFDouble_ZeroAsNull(bvo.getMianji());
			if (mianji == null) {
				mianji = UFDouble.ONE_DBL;
			}
			// ���㵥�� = �ܶ�/��/���/
			if(bvo.getDysrqrje() != null 
//			&& bvo.getMianji() != null 
			&& bvo.getDysrqrts() != null
			&& !UFDouble.ZERO_DBL.equals(bvo.getDysrqrts())	
//			&& !UFDouble.ZERO_DBL.equals(bvo.getMianji())	
			)
			{
				UFDouble danjia = // ����ȷ�Ϸ���/����/���
						  bvo.getDysrqrje()
					.div( bvo.getDysrqrts() )
					.div( mianji )
					.setScale(8, UFDouble.ROUND_HALF_UP)
				;
				bvo.setDanjia(danjia);
			}
			
			// ���㱾�ε���ʷδ̯������
			Integer byWtsts =  PuPubVO.getInteger_NullAs(bvo.getVbdef09(), 0) - 
					PuPubVO.getInteger_NullAs(bvo.getDysrqrts(), 0)
					;
			bvo.setVbdef09(byWtsts.toString());
			
			// ���δ̯������δ0������ ��ĩԤ�������Ϊ ����1 ֮�ڣ��� ��ĩԤ�������ӵ� ���ڷ�����
			// ������ں�ͬ
			// 2020��5��29��16:27:08
			if (byWtsts == 0 
			 && qmye.abs().compareTo(UFDouble.ONE_DBL) < 0
			 && bvo.getVbdef10() != null
			) {
				bvo.setDysrqrje(PuPubVO.getUFDouble_NullAsZero(bvo.getDysrqrje()).add(qmye));
				bvo.setQmyskye(UFDouble.ZERO_DBL);
				String vbmemo = "���Զ�����" + qmye + "��";
				bvo.setVbmemo(
					bvo.getVbmemo() == null
					? vbmemo
					: bvo.getVbmemo() + vbmemo
				);
			}
			// END
		}
		/***END***/
		
		this.getEditor().getBillCardPanel().getBillModel().setBodyDataVO(bodyVOs);
		
	}
	
	/**
	 * ӡ��˰�Ĵ���
	 * @throws BusinessException 
	 */
	private void handleYhs(String pk_org, String str_yb_ksrq, String str_yb_jsrq) throws BusinessException {
		
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" max(yhs.pk_inoutbusiclass) pk_srxm ")		// ӡ��˰-֧����Ŀpk
				.append(",max(yhs.name) vdef03 ")			// ӡ��˰-֧����Ŀname
				.append(",sum(htb.norigtaxmny) vdef12 ")	// ��˰���
				.append(",max(ht.depid) vdef05 ")			// ����pk
				.append(",max(dept.name) vdef04 ")			// ����name
				.append(",max(ht.vbillcode) vdef10 ")		// ��ͬ��
				.append(",max(ht.cvendorid) pk_customer ")	// �Է�pk
				.append(",max(gys.name) vdef01 ")			// �Է�name
//				.append(",max(to_number(nvl(replace(ht.vdef20,'~',''),'0.0'))) vdef14 ")// ӡ��˰��
				.append(",max(to_number(nvl(replace(yhssl.name,'��',''),'0.0'))) vdef14 ")// ӡ��˰��
				.append(" from ct_pu ht ")		// ��ͬ��ͷ
				.append(" inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")		// ��ͬ����
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")	// ��֧��Ŀ
				.append(" left join org_dept dept on ht.depid = dept.pk_dept ")				// ����
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")	// ��Ӧ��
				.append(" left join bd_defdoc yhssl on ht.vdef20 = yhssl.pk_defdoc ") 	// ӡ��˰-˰��
				.append(" left join bd_inoutbusiclass yhs on yhs.name = 'ӡ��˰' and yhs.dr = 0 ")	// ӡ��˰
				.append(" left join (")
					.append(" select distinct ")
					.append(" yb.pk_cutomer df ")	// �Է�pk
					.append(",yb.vbdef10 hth ")		// ��ͬ��
					.append(" from hk_zulin_yuebao y ")
					.append(" inner join hk_zulin_yuebao_b yb on y.pk_hk_zulin_yuebao = yb.pk_hk_zulin_yuebao ")
					.append(" where y.dr=0 and yb.dr=0 ")
					.append(" and y.vbilltypecode = 'HK43' ")
					.append(" and nvl(y.vdef02, 'N') in ('Y', 'y') ")	// ֻȡ ӡ��˰��
					.append(" and y.pk_org = '").append(pk_org).append("' ")
				.append(" ) yb on (ht.cvendorid = yb.df and ht.vbillcode = yb.hth) ")
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.vtrantypecode = '").append(IPub_data.BKHT_type_code).append("' ")
				// ��ȡ֮ǰ�Ѿ�����������
				.append(" and yb.hth is null ")
				// ��ȡ��֤��
				.append(" and szxm.code not in ('2005', '2022') ")
				.append(" and ht.pk_org = '").append(pk_org).append("' ")
				// ֻȡ���°�
				.append(" and ht.blatest = 'Y' ")
				// ֻȡ��Ч��
				.append(" and ht.fstatusflag = 1 ")
				// ֻȡ��һ��(������ �б�������ǻ�ûȡ��������������Բ����޶���ֻȡ��һ��)
//				.append(" and ht.version = 1.0 ")
				// ��ͬ��ʼ���ڣ�ֻ�� ��ͬ��ʼ���� ���ڼ䷶Χ�ڣ��Ϳ���ȷ��Ψһȡֵ��
//				.append(" and ht.valdate between '")
				// �ú�ͬ��Ч���� ȥ�ж�
				.append(" and ht.actualvalidate between '")
				.append(str_yb_ksrq).append("' and '")
				.append(str_yb_jsrq).append("' ")
				// ����ͬ�Ż���
				.append(" group by ht.vbillcode ")
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
			
			UFDouble ht_mny = htVO.getVdef12();	// ��ͬ��������֤��
			UFDouble yhsl = htVO.getVdef14();	// ӡ��˰��(ǧ��֮)
			
			UFDouble yhs = ht_mny.multiply(yhsl).div(1000.00).setScale(2, UFDouble.ROUND_HALF_UP); // ӡ��˰
			
			if (yhs.compareTo(UFDouble.ZERO_DBL) == 0){
				continue;
			}
			
			String ht_code = htVO.getVdef10();	// ��ͬ��	
			String ht_pk_customer = htVO.getPk_customer();	// �Է�pk
//			String srxm_name = htVO.getVdef03();	// ֧����Ŀ-����
			
			// ͬʱ �� YuebaoBVO ���з�װ����
			// �� �Է�##��ͬ��  ����
			{
				String key = ht_pk_customer + "##" + ht_code;

				YuebaoBVO yuebaoBVO = new YuebaoBVO();
				yuebaoBVO.setPk_cutomer(ht_pk_customer);	// �Է�pk
//					yuebaoBVO.setJsksrq(js_ksrq);				// ���㿪ʼ����
//					yuebaoBVO.setJsjsrq(js_jsrq);				// �����������
//					yuebaoBVO.setDysrqrts(PuPubVO.getUFDouble_NullAsZero(yb_days));	// ���·���ȷ������
				yuebaoBVO.setDysrqrje(yhs);				// ���·���ȷ�Ͻ�ӡ��˰��
//					yuebaoBVO.setMianji(ht_mianji);				// ���
//					yuebaoBVO.setDanjia(ht_danjia);				// ����
				
				yuebaoBVO.setSrxm(htVO.getPk_srxm());		// ������Ŀ
				yuebaoBVO.setVbdef01(htVO.getVdef01());		// �Է�����
				yuebaoBVO.setVbdef02(htVO.getVdef02());		// ��Ʊ����-����
				yuebaoBVO.setVbdef03(htVO.getVdef03());		// ֧����Ŀ-����
				yuebaoBVO.setVbdef04(htVO.getVdef04());		// ��������
				yuebaoBVO.setVbdef05(htVO.getVdef05());		// ����pk
				yuebaoBVO.setVbdef07(htVO.getVdef07());		// ʵ�ʺ�ͬ���
				yuebaoBVO.setVbdef10(htVO.getVdef10());		// ��ͬ��
//					yuebaoBVO.setVbdef09(zthtTs.toString());	// �����ͬ����

				MAP_yuebaoBVO.put(key, yuebaoBVO);
			}
		}
		
		YuebaoBVO[] bodyVOs = MAP_yuebaoBVO.values().toArray(new YuebaoBVO[0]);
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
