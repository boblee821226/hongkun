package nc.ui.hkjt.huiyuan.kazhangwu.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.value.IFieldValue;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.ui.querytemplate.value.RefValueObject;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBVO;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuHVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.querytemplate.TemplateInfo;

public class BbcxAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public BbcxAction() {
		setBtnName("�����ѯ");
		setCode("bbcxAction");
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
		
		QueryConditionDLGDelegator dlg = getDlgDelegator();
		
		if (1 == dlg.showModal()) {
			
			String[] PK_CORP = new String[]
				{
					"0001N510000000001SXX",	// ���¥
					"0001N510000000001SXV",	// ���ʻ��
					"0001N510000000001SY1",	// ������Ƶ�
					"0001N510000000001SY5",	// ��������ɽ��
					"0001N510000000001SY3",	// �����ȾƵ�
					"0001N510000000001SY7",	// ��ɽ��Ȫ
				};
			
			// ��װ�� �����ʽ
			KazhangwuHVO   resultHVO  = new KazhangwuHVO();
			KazhangwuBVO[] resultBVOs = new KazhangwuBVO[34];
			
			resultBVOs[0] = new KazhangwuBVO("     ��ֵ","�ۿ�");
			resultBVOs[1] = new KazhangwuBVO("","����");
			resultBVOs[2] = new KazhangwuBVO("","�����д�");
			resultBVOs[3] = new KazhangwuBVO("","����ר��");
			resultBVOs[4] = new KazhangwuBVO("","Ӫ��ר��");
			resultBVOs[5] = new KazhangwuBVO("","�⳥ר��");
			resultBVOs[6] = new KazhangwuBVO("","�����");
			resultBVOs[7] = new KazhangwuBVO("","�Ҹ��ж���");
			resultBVOs[8] = new KazhangwuBVO("","���Ͽ�");
			resultBVOs[9] = new KazhangwuBVO("","�س�");
			resultBVOs[10] = new KazhangwuBVO("----�ϼ�----","");
			
			resultBVOs[11] = new KazhangwuBVO("     ����","���¥������");
			resultBVOs[12] = new KazhangwuBVO("","���ʵ꿨����");
			resultBVOs[13] = new KazhangwuBVO("","������ѧԺ·������");
			resultBVOs[14] = new KazhangwuBVO("","��������ɽ������");
			resultBVOs[15] = new KazhangwuBVO("","�����ȿ�����");
			resultBVOs[16] = new KazhangwuBVO("","��ɽ��Ȫ������");
			resultBVOs[17] = new KazhangwuBVO("----�ϼ�----","");
			
			resultBVOs[18] = new KazhangwuBVO("     ����","���¥������");
			resultBVOs[19] = new KazhangwuBVO("","���ʵ꿨����");
			resultBVOs[20] = new KazhangwuBVO("","������ѧԺ·������");
			resultBVOs[21] = new KazhangwuBVO("","��������ɽ������");
			resultBVOs[22] = new KazhangwuBVO("","�����ȿ�����");
			resultBVOs[23] = new KazhangwuBVO("","��ɽ��Ȫ������");
			resultBVOs[24] = new KazhangwuBVO("----�ϼ�----","");
			
			resultBVOs[25] = new KazhangwuBVO("  Ӧ������","�ڹ��¥ˢ��");
			resultBVOs[26] = new KazhangwuBVO("","�ڹ��ʵ�ˢ��");
			resultBVOs[27] = new KazhangwuBVO("","�ڿ�����ѧԺ·ˢ��");
			resultBVOs[28] = new KazhangwuBVO("","�ڿ�������ɽˢ��");
			resultBVOs[29] = new KazhangwuBVO("","��������ˢ��");
			resultBVOs[30] = new KazhangwuBVO("","����ɽ��Ȫˢ��");
			resultBVOs[31] = new KazhangwuBVO("----�ϼ�----","");
			resultBVOs[32] = new KazhangwuBVO("----���----","");
			resultBVOs[33] = new KazhangwuBVO("---��ת����---","");
			
			String hzdate = getValueForColumn(dlg, "dbilldate", true, true);
			String pk_org = getValueForColumn(dlg, "pk_org", true, false);
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			
			
			// ������ ��ʼ������ ����
			if(hzdate==null || "".equals(hzdate))
				hzdate = "1990-01-01,2990-12-01";
			
			String[] chaxun_date = hzdate.split(",");
			
			String ks_date = chaxun_date[0];
			String js_date = chaxun_date[0];
			if(chaxun_date.length==2)
			{
				js_date = chaxun_date[1];
			}
			
			
			/**
			 * 1����ѯ��ֵ��Ϣ
			 */
			{
				StringBuffer querySQL_1 = 
						new StringBuffer("select ")
								.append(" sum( case when kib.xmdl='��ֵ' and ( nvl(kib.xmlx,'�ۿ�')='�ۿ�' or (nvl(kib.xmlx,'�ۿ�')='���Ͽ�' and nvl(kib.zdh,'null')='null') ) then kib.ka_ss else 0 end ) sk ")	// ���� �ۿ� + �ֹ�¼������Ͽ�
								.append(",sum( case when kib.xmdl='��ֵ' and ( nvl(kib.xmlx,'�ۿ�')='�ۿ�' or (nvl(kib.xmlx,'�ۿ�')='���Ͽ�' and nvl(kib.zdh,'null')='null') ) then kib.ka_zs else 0 end ) zs ")
								.append(",sum( case when kib.xmdl='��ֵ' and nvl(kib.xmlx,'�ۿ�')='�����д�' then kib.ka_je else 0 end )  zdzd ")
								.append(",sum( case when kib.xmdl='��ֵ' and nvl(kib.xmlx,'�ۿ�')='����ר��' then kib.ka_je else 0 end )  gdzy ")
								.append(",sum( case when kib.xmdl='��ֵ' and nvl(kib.xmlx,'�ۿ�')='Ӫ��ר��' then kib.ka_je else 0 end )  yxzy ")
								.append(",sum( case when kib.xmdl='��ֵ' and nvl(kib.xmlx,'�ۿ�')='�⳥ר��' then kib.ka_je else 0 end )  pczy ")
								.append(",sum( case when kib.xmdl='��ֵ' and nvl(kib.xmlx,'�ۿ�')='�����' then kib.ka_je else 0 end )  gbjk ")
								.append(",sum( case when kib.xmdl='��ֵ' and nvl(kib.xmlx,'�ۿ�')='�Ҹ��ж���' then kib.ka_je else 0 end )  xfgek ")
								.append(",sum( case when kib.xmdl='��ֵ' and nvl(kib.xmlx,'�ۿ�')='���Ͽ�' and kib.ka_code not between '0302101237' and '0302101326' then kib.ka_je else 0 end )  zuofeika ")
								.append(",sum( case when kib.xmdl='��ֵ' and nvl(kib.xmlx,'�ۿ�')='�س�' then kib.ka_je else 0 end )  huichong ")
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" where ")
								.append(" ki.dr=0 and kib.dr=0 ")
								.append(" and ki.pk_org = '").append(pk_org).append("' ")	// ��֯
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// ʱ���
				; 
				
				ArrayList list_1 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
				
				if( list_1.size()>0 )
				{
					Object[] obj = (Object[])list_1.get(0);
					UFDouble cz_kajie_total = UFDouble.ZERO_DBL;
					
					for(int obj_i=0;obj_i<obj.length;obj_i++)
					{
						resultBVOs[obj_i].setKajie( PuPubVO.getUFDouble_NullAsZero(obj[obj_i]) );
						cz_kajie_total = cz_kajie_total.add( resultBVOs[obj_i].getKajie() );
					}
					
					resultBVOs[0].setKajie_kbl( resultBVOs[0].getKajie() );	// ���ۿ��Ŀ���������  ��ֵΪ ����
					resultBVOs[10].setKajie( cz_kajie_total );	// ��ֵ ���� �ϼ�
//					resultBVOs[10].setKajie_kbl( resultBVOs[0].getKajie() );	// ��ֵ ���������� �ϼ�
				}
			}
			
			/**
			 * 2����ѯ ������Ϣ
			 * ����             vbdef03
			 * ����������  vbdef04
			 */
			{
				StringBuffer querySQL_2 = 
						new StringBuffer("select ")
								.append(" ka.pk_org ")		// pk_org
								.append(",sum(kib.ka_je) ")	// �����
								.append(",sum(kib.ka_ss) ")	// ��ʵ��
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")
								.append(" where ki.dr=0 and kib.dr=0 ")
								.append(" and kib.xmdl = '����' ")	// ��Ŀ���� = ����
								.append(" and ki.pk_org = '").append(pk_org).append("' ")	// ��֯
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// ʱ���
								.append(" group by ka.pk_org ")
								;
				
				ArrayList list_2 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_2 = new HashMap<String,UFDouble[]>();
				
				if( list_2.size()>0 )
				{
					for( int i=0;i<list_2.size();i++ )
					{// ���� HashMap
						Object[] obj = (Object[])list_2.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = PuPubVO.getUFDouble_NullAsZero( obj[1] );
						map_value[1] = PuPubVO.getUFDouble_NullAsZero( obj[2] );
						
						map_2.put(map_key, map_value);
					}
					
					UFDouble xf_kj_total = UFDouble.ZERO_DBL;
					UFDouble xf_kbl_total = UFDouble.ZERO_DBL;
					int first_index = 11;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{ 
						UFDouble[] map_value = map_2.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setVbdef03(UFDouble.ZERO_DBL.toString());
							resultBVOs[first_index+i].setVbdef04(UFDouble.ZERO_DBL.toString());
						}
						else
						{
							resultBVOs[first_index+i].setVbdef03(map_value[0].toString());
							resultBVOs[first_index+i].setVbdef04(map_value[1].toString());
							
							xf_kj_total  =  xf_kj_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef03() ) );
							xf_kbl_total = xf_kbl_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef04() ) );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setVbdef03( xf_kj_total.toString() );		// ���� ���� �ϼ�
					resultBVOs[first_index+PK_CORP.length].setVbdef04( xf_kbl_total.toString() );		// ���� ���������� �ϼ�
				}
			}
			
			/**
			 * 2.1����������
			 * ����             vbdef01
			 * ����������  vbdef02
			 * 
			 * 2018��4��27��10:27:44
			 */
			{
				StringBuffer querySQL_2 = 
						new StringBuffer("select ")
								.append(" ka.pk_org ")		// pk_org
								.append(",sum( to_number(zb.vbdef01) ) ")	// �����
								.append(",sum( round(to_number(zb.vbdef01)*to_number(zb.vbdef03),2) ) ")	// ��ʵ��
								.append(" from hk_srgk_hg_zhangdan z ")
								.append(" inner join hk_srgk_hg_zhangdan_b zb on ( z.pk_hk_dzpt_hg_zhangdan = zb.pk_hk_dzpt_hg_zhangdan and nvl(zb.vbdef01,'~') != '~' ) ")
								.append(" left join hk_huiyuan_kadangan ka on ( zb.vbdef02 = ka.ka_code and ka.dr=0 ) ")
								.append(" where z.dr=0 and zb.dr=0 ")
								.append(" and z.pk_org = '").append(pk_org).append("' ")	// ��֯
								.append(" and substr(z.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// ʱ���
								.append(" group by ka.pk_org ")
								;
				
				ArrayList list_2 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_2 = new HashMap<String,UFDouble[]>();
				
				if( list_2.size()>0 )
				{
					for( int i=0;i<list_2.size();i++ )
					{// ���� HashMap
						Object[] obj = (Object[])list_2.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( obj[1] ) );	// ����ȡ��������֮ǰ�߼�һ��
						map_value[1] = UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( obj[2] ) );
						
						map_2.put(map_key, map_value);
					}
					
					UFDouble fq_kj_total = UFDouble.ZERO_DBL;
					UFDouble fq_kbl_total = UFDouble.ZERO_DBL;
					int first_index = 11;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{// Vbdef01 ��������    Vbdef02 ��������������
						UFDouble[] map_value = map_2.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setVbdef01(UFDouble.ZERO_DBL.toString());
							resultBVOs[first_index+i].setVbdef02(UFDouble.ZERO_DBL.toString());
						}
						else
						{
							resultBVOs[first_index+i].setVbdef01(map_value[0].toString());
							resultBVOs[first_index+i].setVbdef02(map_value[1].toString());
							
							fq_kj_total  =  fq_kj_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef01() ) );
							fq_kbl_total =  fq_kbl_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef02() ) );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setVbdef01( fq_kj_total.toString() ); 	// ���� ���� �ϼ�
					resultBVOs[first_index+PK_CORP.length].setVbdef02( fq_kbl_total.toString() );	// ���� ���������� �ϼ�
				}
			}
			
			/**
			 * 2.2���ϼƿ���         = ��Ա���� 3 + �������� 1
			 *   �ϼƿ���������    = ��Ա���������� 4 + �������������� 2
			 */
			{
				int first_index = 11;
				for( int i=0;i<PK_CORP.length + 1;i++ )	// ��Ҫ����  �ϼ���
				{
					resultBVOs[first_index+i].setKajie(
							  PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef03() )
						.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef01() ) )
					);
					resultBVOs[first_index+i].setKajie_kbl(
							  PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef04() )
						.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef02() ) )
					);
				}
				
			}
			
			/**
			 * 3����ѯ �س���Ϣ
			 */
			{
				StringBuffer querySQL_3 = 
						new StringBuffer("select ")
								.append(" ka.pk_org ")		// pk_org
								.append(",sum(kib.ka_je) ")	// �����
								.append(",sum(kib.ka_ss) ")	// ��ʵ��
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")
								.append(" where ki.dr=0 and kib.dr=0 ")
								.append(" and kib.xmlx = '�س�' ")	// ��Ŀ���� = �س�
								.append(" and ki.pk_org = '").append(pk_org).append("' ")	// ��֯
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// ʱ���
								.append(" group by ka.pk_org ")
								;
				
				ArrayList list_3 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_3.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_3 = new HashMap<String,UFDouble[]>();
				
				if( list_3.size()>0 )
				{
					for( int i=0;i<list_3.size();i++ )
					{// ���� HashMap
						Object[] obj = (Object[])list_3.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = PuPubVO.getUFDouble_NullAsZero( obj[1] );
						map_value[1] = PuPubVO.getUFDouble_NullAsZero( obj[2] );
						
						map_3.put(map_key, map_value);
					}
					
					UFDouble tz_kj_total  = UFDouble.ZERO_DBL;
					UFDouble tz_kbl_total = UFDouble.ZERO_DBL;
					int first_index = 18;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{
						UFDouble[] map_value = map_3.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setKajie(UFDouble.ZERO_DBL);
							resultBVOs[first_index+i].setKajie_kbl(UFDouble.ZERO_DBL);
						}
						else
						{
							resultBVOs[first_index+i].setKajie( PuPubVO.getUFDouble_NullAsZero(map_value[0]) );
							resultBVOs[first_index+i].setKajie_kbl( PuPubVO.getUFDouble_NullAsZero(map_value[1]) );
							
							tz_kj_total  =  tz_kj_total.add( resultBVOs[first_index+i].getKajie() );
							tz_kbl_total = tz_kbl_total.add( resultBVOs[first_index+i].getKajie_kbl() );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setKajie( tz_kj_total );		// ���� ���� �ϼ�
					resultBVOs[first_index+PK_CORP.length].setKajie_kbl( tz_kbl_total );// ���� ���������� �ϼ�
				}
			}
			
			/**
			 * 4����ѯ Ӧ������
			 * ����             vbdef03
			 * ����������  vbdef04
			 */
			{

				StringBuffer querySQL_4 = 
						new StringBuffer("select ")
								.append(" ki.pk_org ")		// pk_org
								.append(",sum(kib.ka_je) ")	// �����
								.append(",sum(kib.ka_ss) ")	// ��ʵ��
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")
								.append(" where ki.dr=0 and kib.dr=0 ")
								.append(" and ( kib.xmdl = '����' or kib.xmlx = '�س�' ) ")	// ��Ŀ����=���� or ��Ŀ����=�س�
								.append(" and ka.pk_org = '").append(pk_org).append("' ")	// ��֯
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// ʱ���
								.append(" group by ki.pk_org ")
								;
				
				ArrayList list_4 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_4.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_4 = new HashMap<String,UFDouble[]>();
				
				if( list_4.size()>0 )
				{
					for( int i=0;i<list_4.size();i++ )
					{// ���� HashMap
						Object[] obj = (Object[])list_4.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = PuPubVO.getUFDouble_NullAsZero( obj[1] );
						map_value[1] = PuPubVO.getUFDouble_NullAsZero( obj[2] );
						
						map_4.put(map_key, map_value);
					}
					
					UFDouble yf_kj_total  = UFDouble.ZERO_DBL;
					UFDouble yf_kbl_total = UFDouble.ZERO_DBL;
					int first_index = 25;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{
						UFDouble[] map_value = map_4.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setVbdef03(UFDouble.ZERO_DBL.toString());
							resultBVOs[first_index+i].setVbdef04(UFDouble.ZERO_DBL.toString());
						}
						else
						{
							resultBVOs[first_index+i].setVbdef03(map_value[0].toString());
							resultBVOs[first_index+i].setVbdef04(map_value[1].toString());
							
							yf_kj_total  =  yf_kj_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef03() ) );
							yf_kbl_total = yf_kbl_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef04() ) );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setVbdef03( yf_kj_total.toString() );	// Ӧ�� ���� �ϼ�
					resultBVOs[first_index+PK_CORP.length].setVbdef04( yf_kbl_total.toString() );	// Ӧ�� ���������� �ϼ�
				}
			
			}
			
			/**
			 * 4.1����ѯ Ӧ�����ң�������
			 * ����             vbdef01
			 * ����������  vbdef02
			 */
			{

				StringBuffer querySQL_4 = 
						new StringBuffer("select ")
								.append(" z.pk_org ")		// pk_org
								.append(",sum( to_number(zb.vbdef01) ) ")	// �����
								.append(",sum( round(to_number(zb.vbdef01)*to_number(zb.vbdef03),2) ) ")	// ��ʵ��
								.append(" from hk_srgk_hg_zhangdan z ")
								.append(" inner join hk_srgk_hg_zhangdan_b zb on ( z.pk_hk_dzpt_hg_zhangdan = zb.pk_hk_dzpt_hg_zhangdan and nvl(zb.vbdef01,'~') != '~' ) ")
								.append(" left join hk_huiyuan_kadangan ka on ( zb.vbdef02 = ka.ka_code and ka.dr=0 ) ")
								.append(" where z.dr=0 and zb.dr=0 ")
								.append(" and ka.pk_org = '").append(pk_org).append("' ")	// ��֯
								.append(" and substr(z.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// ʱ���
								.append(" group by z.pk_org ")
								;
				
				ArrayList list_4 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_4.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_4 = new HashMap<String,UFDouble[]>();
				
				if( list_4.size()>0 )
				{
					for( int i=0;i<list_4.size();i++ )
					{// ���� HashMap
						Object[] obj = (Object[])list_4.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( obj[1] ) );
						map_value[1] = UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( obj[2] ) );
						
						map_4.put(map_key, map_value);
					}
					
					UFDouble fq_yf_kj_total  = UFDouble.ZERO_DBL;
					UFDouble fq_yf_kbl_total = UFDouble.ZERO_DBL;
					int first_index = 25;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{
						UFDouble[] map_value = map_4.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setVbdef01(UFDouble.ZERO_DBL.toString());
							resultBVOs[first_index+i].setVbdef02(UFDouble.ZERO_DBL.toString());
						}
						else
						{
							resultBVOs[first_index+i].setVbdef01(map_value[0].toString());
							resultBVOs[first_index+i].setVbdef02(map_value[1].toString());
							
							fq_yf_kj_total  = fq_yf_kj_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef01() ) );
							fq_yf_kbl_total = fq_yf_kbl_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef02() ) );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setVbdef01( fq_yf_kj_total.toString() );		// ���� Ӧ�� ���� �ϼ�
					resultBVOs[first_index+PK_CORP.length].setVbdef02( fq_yf_kbl_total.toString() );	// ���� Ӧ�� ���������� �ϼ�
				}
			}
			
			/**
			 * 4.2���ϼƿ���         = ��Ա���� 3 + �������� 1
			 *   �ϼƿ���������    = ��Ա���������� 4 + �������������� 2
			 */
			{
				int first_index = 25;
				for( int i=0;i<PK_CORP.length;i++ )
				{
					resultBVOs[first_index+i].setKajie(
							  PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef03() )
						.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef01() ) )
					);
					resultBVOs[first_index+i].setKajie_kbl(
							  PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef04() )
						.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef02() ) )
					);
				}
			}
			
			
			/**
			 * 5�����¥ �� ���ʵ�  ������ҵ��
			 * "0001N510000000001SXX",	// ���¥
			 * "0001N510000000001SXV",	// ���ʻ��
			 * resultBVOs[11] = new KazhangwuBVO("     ����","���¥������");
			 * resultBVOs[12] = new KazhangwuBVO("","���ʵ꿨����");
			 * resultBVOs[25] = new KazhangwuBVO("  Ӧ������","�ڹ��¥ˢ��");
			 * resultBVOs[26] = new KazhangwuBVO("","�ڹ��ʵ�ˢ��");
			 * resultBVOs[31] = new KazhangwuBVO("----�ϼ�----","");
			 * 
			 */
			{
				if( "0001N510000000001SXX".equals(pk_org) )
				{// ���¥
					// �� ���ʵ꿨������ ת�� ���¥ ����Ӱ�� ���Ѻϼƣ�
					// �ϼ�
					resultBVOs[11].setKajie( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getKajie())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie()) ) 
					);
					resultBVOs[11].setKajie_kbl( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getKajie_kbl())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie_kbl()) ) 
					);
					resultBVOs[12].setKajie( UFDouble.ZERO_DBL );
					resultBVOs[12].setKajie_kbl( UFDouble.ZERO_DBL );
					// ��Ա
					resultBVOs[11].setVbdef03( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef03())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef03()) ) 
						.toString()
					);
					resultBVOs[11].setVbdef04( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef04())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef04()) )
						.toString()
					);
					resultBVOs[12].setVbdef03( UFDouble.ZERO_DBL.toString() );
					resultBVOs[12].setVbdef04( UFDouble.ZERO_DBL.toString() );
					// ����
					resultBVOs[11].setVbdef01( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef01())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef01()) ) 
						.toString()
					);
					resultBVOs[11].setVbdef02( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef02())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef02()) )
						.toString()
					);
					resultBVOs[12].setVbdef01( UFDouble.ZERO_DBL.toString() );
					resultBVOs[12].setVbdef02( UFDouble.ZERO_DBL.toString() );
					
					// Ӧ������  �� �ڹ��ʵ�ˢ�������
					// �� �ڹ��¥ˢ�� = ���¥���� + ���¥�س�
					// ��Ӱ�� Ӧ������ �ϼƣ���Ҫ���� �ϼ�
					////// �ϼ� //////
					resultBVOs[26].setKajie( UFDouble.ZERO_DBL );	// ��� ���ʵ�
					resultBVOs[26].setKajie_kbl( UFDouble.ZERO_DBL );
					
					resultBVOs[25].setKajie( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getKajie())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getKajie())) );	// ��ֵ ���¥��������
					resultBVOs[25].setKajie_kbl( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getKajie_kbl())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getKajie_kbl()) ) );
					////// ��Ա //////
					resultBVOs[26].setVbdef03( UFDouble.ZERO_DBL.toString() );
					resultBVOs[26].setVbdef04( UFDouble.ZERO_DBL.toString() );
					
					resultBVOs[25].setVbdef03( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef03())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getVbdef03())) 
										.toString() );	// ��ֵ ���¥��������
					resultBVOs[25].setVbdef04( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef04())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getVbdef04()) ) 
											.toString() );
					////// ���� //////
					resultBVOs[26].setVbdef01( UFDouble.ZERO_DBL.toString() );
					resultBVOs[26].setVbdef02( UFDouble.ZERO_DBL.toString() );
					
					resultBVOs[25].setVbdef01( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef01())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getVbdef01())) 
										.toString() );	// ��ֵ ���¥��������
					resultBVOs[25].setVbdef02( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef02())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getVbdef02()) ) 
											.toString() );
				}
				
				else if( "0001N510000000001SXV".equals(pk_org) )
				{// ���ʵ�
					// �� ���¥������ ת�� ���ʵ�
					// �ϼ�
					resultBVOs[12].setKajie( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getKajie())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie()) ) 
					);
					resultBVOs[12].setKajie_kbl( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getKajie_kbl())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie_kbl()) ) 
					);
					resultBVOs[11].setKajie( UFDouble.ZERO_DBL );
					resultBVOs[11].setKajie_kbl( UFDouble.ZERO_DBL );
					// ��Ա
					resultBVOs[12].setVbdef03( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef03())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef03()) ) 
						.toString()
					);
					resultBVOs[12].setVbdef04( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef04())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef04()) )
						.toString()
					);
					resultBVOs[11].setVbdef03( UFDouble.ZERO_DBL.toString() );
					resultBVOs[11].setVbdef04( UFDouble.ZERO_DBL.toString() );
					// ����
					resultBVOs[12].setVbdef01( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef01())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef01()) ) 
						.toString()
					);
					resultBVOs[12].setVbdef02( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef02())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef02()) )
						.toString()
					);
					resultBVOs[11].setVbdef01( UFDouble.ZERO_DBL.toString() );
					resultBVOs[11].setVbdef02( UFDouble.ZERO_DBL.toString() );
					
					// Ӧ������  �� �ڹ��¥ˢ�������
					// �� �ڹ��ʵ�ˢ�� = ���ʵ�����  + ���ʵ�س�
					// ��Ӱ�� Ӧ������ �ϼƣ���Ҫ���� �ϼ�
					////// �ϼ� //////
					resultBVOs[25].setKajie( UFDouble.ZERO_DBL );
					resultBVOs[25].setKajie_kbl( UFDouble.ZERO_DBL );
					
					resultBVOs[26].setKajie(  PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie())
										.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[19].getKajie()) ) );
					resultBVOs[26].setKajie_kbl( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie_kbl())
										.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[19].getKajie_kbl()) ) );
					////// ��Ա //////
					resultBVOs[25].setVbdef03( UFDouble.ZERO_DBL.toString() );
					resultBVOs[25].setVbdef04( UFDouble.ZERO_DBL.toString() );
					
					resultBVOs[26].setVbdef03( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef03())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getVbdef03())) 
										.toString() );	// ��ֵ ���¥��������
					resultBVOs[26].setVbdef04( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef04())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getVbdef04()) ) 
											.toString() );
					////// ���� //////
					resultBVOs[25].setVbdef01( UFDouble.ZERO_DBL.toString() );
					resultBVOs[25].setVbdef02( UFDouble.ZERO_DBL.toString() );
					
					resultBVOs[26].setVbdef01( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef01())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getVbdef01())) 
										.toString() );	// ��ֵ ���¥��������
					resultBVOs[26].setVbdef02( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef02())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18].getVbdef02()) ) 
															.toString() );
				}
				
				resultBVOs[31].setKajie( //����ϼ�
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25].getKajie() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30].getKajie() ) ) 
				);
				resultBVOs[31].setKajie_kbl( 
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25].getKajie_kbl() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30].getKajie_kbl() ) ) 
				);
				resultBVOs[31].setVbdef03( //����ϼ�--��Ա
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25].getVbdef03() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30].getVbdef03() ) ) 
					.toString()
				);
				resultBVOs[31].setVbdef04( //����ϼ�--��Ա
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25].getVbdef04() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30].getVbdef04() ) ) 
					.toString()
				);
				resultBVOs[31].setVbdef01( //����ϼ�--����
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25].getVbdef01() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26].getVbdef01() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27].getVbdef01() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28].getVbdef01() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29].getVbdef01() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30].getVbdef01() ) ) 
					.toString()
				);
				resultBVOs[31].setVbdef02( //����ϼ�--����
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25].getVbdef02() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26].getVbdef02() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27].getVbdef02() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28].getVbdef02() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29].getVbdef02() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30].getVbdef02() ) ) 
					.toString()
				);
				
			}
			
			
			/**
			 * 6�� ���� Ӧ�����
			 */
			{
				resultBVOs[resultBVOs.length-2].setKajie(
						resultBVOs[10].getKajie().sub( resultBVOs[8].getKajie() ).add( resultBVOs[31].getKajie() ) 
				);	// ���г�ֵ�������ϣ�-Ӧ������֮�� �����ѡ�Ӧ�� ���ɸ������֣� ������add��
				resultBVOs[resultBVOs.length-2].setKajie_kbl(
						resultBVOs[0].getKajie_kbl().add( resultBVOs[31].getKajie_kbl() ) 
				);	// �ۿ� - Ӧ������֮��  �����ѡ�Ӧ�� ���ɸ������֣� ������add��
			}
			
			/**
			 * 7�� ���� ��ת����
			 */
			{
				// ��Ա��
				StringBuffer querySQL_6 = 
						new StringBuffer("select ")
								.append(" sum( nvl(kib.y_ka_ss,0) - nvl(kib.ka_ss,0) ) ")
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" where ki.dr=0 and kib.dr=0 ")
								.append(" and kib.xmdl = '��ת' ")
								/**
								 * HK 2019��3��15��13:58:08
								 * ������ ��������ת�Ŀ�
								 */
//								.append(" and kib.ka_code not   in ('0203005888','0302101237','0103001500') ")
//								.append(" and kib.y_ka_code not in ('0203005888','0302101237','0103001500') ")
								.append(" and kib.ka_code not   in ('0203005888','0302101237','0103001500','0203005666','XSZSZZK000002','XSZSZZK000001') ")
								.append(" and kib.y_ka_code not in ('0203005888','0302101237','0103001500','0203005666','XSZSZZK000002','XSZSZZK000001') ")
								/***END***/
								.append(" and ki.pk_org = '").append(pk_org).append("' ")
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
				;
				
				ArrayList list_6 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_6.toString(), new ArrayListProcessor());
				
				if( list_6.size()>0 )
				{
					Object[] obj = (Object[])list_6.get(0);
					
					resultBVOs[resultBVOs.length-1].setVbdef04( PuPubVO.getUFDouble_NullAsZero(obj[0]).toString() );
				}
				
				// ������
				
				// �ϼ� = ��Ա + ����
				resultBVOs[resultBVOs.length-1].setKajie_kbl( 
						 PuPubVO.getUFDouble_NullAsZero( resultBVOs[resultBVOs.length-1].getVbdef04() )
					.add(PuPubVO.getUFDouble_NullAsZero( resultBVOs[resultBVOs.length-1].getVbdef02() ))
				);
				
			}
			
			/**
			 * 8����ѯ ��֤����
			 * ȡ  ���졢����˾��  Ӫҵ�ձ�������
			 */
			{
				StringBuffer querySQL_8 = 
						new StringBuffer("select sum(yb.jine) ")
								.append(" from hk_srgk_hg_yyribao y ")
								.append(" inner join hk_srgk_hg_yyribao_b yb on y.pk_hk_srgk_hg_yyribao = yb.pk_hk_srgk_hg_yyribao ")
								.append(" where y.dr=0 and yb.dr=0 ")
								.append(" and y.pk_org='").append(pk_org).append("' and substr(y.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
								.append(" and y.ts in (select max(ts) from hk_srgk_hg_yyribao  where dr=0 and pk_org='").append(pk_org).append("' and substr(dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' group by substr(dbilldate,0,10) ) ")
								.append(" and yb.jzfs_code in ( ")
								.append("  '0301' ")	// ��Ա������
								.append(" ,'0302' ")	// �ο�����
								.append(" ,'050401' ")	// ���ʻ�ݿ�
								.append(" ,'050402' ")	// ���¥��
//								.append(" ,'050403' ")	// �ϵػ�ݿ�
								.append(" ,'050404' ")	// ��ɽ��Ȫ��
								.append(" ,'050405' ")	// �����ȿ�
								.append(" ,'050406' ")	// ������ѧԺ·��
								.append(" ,'050407' ")	// ��������ɽ��
								.append(" ) ")
				;
				
				ArrayList list_8 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_8.toString(), new ArrayListProcessor());
				
				if( list_8.size()>0 )
				{
					Object[] obj = (Object[])list_8.get(0);
					
					resultHVO.setVdef02( obj[0]==null?"":obj[0].toString() );
				}
				
			}
			
			resultHVO.setIbillstatus(-1);
			resultHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//����
			resultHVO.setPk_org( pk_org );	// pk_org 
			resultHVO.setVdef01( ks_date.equals(js_date)?ks_date:(ks_date +" �� "+js_date) );	// ҵ������
			
			KazhangwuBillVO resultBillVO = new KazhangwuBillVO();
			resultBillVO.setParentVO(resultHVO);
			resultBillVO.setChildrenVO(resultBVOs);
			// ������� �ŵ�����
			getEditor().getBillCardPanel().setBillData(
					getEditor().getBillCardPanel().getBillData());
			getEditor().getModel().initModel(new KazhangwuBillVO[]{resultBillVO});
		}
	}
	
	
	QueryConditionDLGDelegator dlgDelegator;
	private QueryConditionDLGDelegator getDlgDelegator() {
		if (dlgDelegator == null) {
			TemplateInfo tempinfo = new TemplateInfo();
			tempinfo.setPk_Org(this.getModel().getContext().getPk_group());
			tempinfo.setUserid(this.getModel().getContext().getPk_loginUser());
			tempinfo.setFunNode("HKJ20606");
			tempinfo.setNodekey("bbcx");
			dlgDelegator = new QueryConditionDLGDelegator(this.getModel()
					.getContext(), tempinfo);
			dlgDelegator.registerNeedPermissionOrgFieldCode(HZShuJuVO.PK_ORG);
//			QueryTempMainOrgFilterBaseDoc_HZCX bankaccByOrgFileter = new QueryTempMainOrgFilterBaseDoc_HZCX(
//					dlgDelegator, HZShuJuVO.PK_ORG, HZShuJuVO.PK_DEPT);
//			bankaccByOrgFileter.addEditorListener();
		}
		return dlgDelegator;
	}
	
	/**
	 * ��ȡ�����������
	 * 
	 * @param dlg
	 *            ��ѯģ��Ԫ��
	 * @param column
	 *            �����ֶ���Ϣ
	 * @param isPK
	 *            ���շ����Ƿ�PK
	 * @param isdate
	 *            �Ƿ����ڸ�ʽ
	 * */
	private String getValueForColumn(QueryConditionDLGDelegator dlg,
			String column, boolean isPK, boolean isdate) {
		List<IFilter> filtersByFieldCode = dlg.getQueryConditionDLG()
				.getFiltersByFieldCode(column);
		if (filtersByFieldCode != null && filtersByFieldCode.size() > 0) {
			IFilter filter = filtersByFieldCode.get(0);
			if (filter != null) {
				List<String> qryfields = getQryFields(filter, isPK);
				if (qryfields != null && qryfields.size() > 0) {
					String new_value = "";
					for (int i = 0; i < qryfields.size(); i++) {
						String value = qryfields.get(i);
						if (isdate) {
							new_value = new_value + value.substring(0, 10)
									+ ",";
						} else {
							new_value = new_value + value + ",";
						}
					}
					new_value = new_value.substring(0, new_value.length() - 1);
					return new_value;
				}
			}
		}
		return "";

	}
	
	/**
	 * �ڲ�ѯģ����ȡ��ĳ��ֵ
	 * 
	 * @param filter
	 * @return
	 */
	private List<String> getQryFields(IFilter filter, boolean isPk) {
		List<String> rtList = new ArrayList<String>();
		if (filter != null) {
			IFieldValue fieldValue = filter.getFieldValue();
			if (fieldValue != null) {

				List<IFieldValueElement> fieldValues = fieldValue
						.getFieldValues();
				if (fieldValues != null && fieldValues.size() > 0) {
					for (IFieldValueElement fieldValueElement : fieldValues) {
						Object valueObject = fieldValueElement.getValueObject();
						if (valueObject != null) {
							if (valueObject instanceof RefValueObject) {
								RefValueObject refValue = (RefValueObject) valueObject;
								String value = null;
								if (isPk) {
									value = refValue.getPk();
								} else {
									value = refValue.getCode();
								}
								rtList.add(value);
							} else if (valueObject instanceof DefaultConstEnum) {
								DefaultConstEnum constEnum = (DefaultConstEnum) valueObject;
								Object value = constEnum.getValue();
								if (value != null) {
									rtList.add(String.valueOf(value));
								}
							} else if (valueObject instanceof UFDate) {
								rtList.add(valueObject.toString());
							} 
							else
							{
								rtList.add(valueObject.toString());
							}
						}
					}
				}
			}
		}
		return rtList;
	}
	
	BaseDAO dao=null;
	public BaseDAO getBaseDAO(){
		if(dao==null)
			dao=new BaseDAO();
		return dao;
	}
	
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}

}
