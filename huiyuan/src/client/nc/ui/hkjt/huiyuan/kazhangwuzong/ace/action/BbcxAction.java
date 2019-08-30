package nc.ui.hkjt.huiyuan.kazhangwuzong.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBVO;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongHVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.pub.BusinessException;
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
			
			String hzdate = getValueForColumn(dlg, "dbilldate", true, true);
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			
			
			// ������ ��ʼ������ ����
			if(hzdate==null || "".equals(hzdate))
				hzdate = "1990-01-01,2990-12-01";
			
			String[] chaxun_date = hzdate.split(",");
			
			String ks_date_str = chaxun_date[0];
			String js_date_str = chaxun_date[0];
			if(chaxun_date.length==2)
			{
				js_date_str = chaxun_date[1];
			}
			
			/**
			 * ���ȡ����ݲ�ѯ���ڡ��������������ʾ���У�һ��һ�С�
			 */
			UFDate ks_date = PuPubVO.getUFDate(ks_date_str);
			UFDate js_date = PuPubVO.getUFDate(js_date_str);
			int days = js_date.getDaysAfter(ks_date)+1;
			
			if( days>31 )
			{// Ч�ʿ���
				throw new BusinessException("����Ч�ʵ�ԭ�����ֻ�ܲ�һ���µ����ݡ�");
			}
			
			KazhangwuzongBVO[] resultBVOs = new KazhangwuzongBVO[days];
			
			for(int i=0;i<days;i++)
			{
				String date = ks_date.getDateAfter(i).toString().substring(0, 10);
				
				resultBVOs[i] = new KazhangwuzongBVO();
				resultBVOs[i].setRq(date);
				
				/**
				 * ����ʽ  �ı�
				 * ͨ��   ���������� - ���Ͽ� - ������ - һ����   �������  ��Ա���������
				 */
				
				/**
				 * 1�� ȡ ��Ա��  ���� ��   ���Աȱ� ���㷨һ��
				 */
				{
					StringBuffer querySQL_1 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
										// �ڳ�
										.append(" select ")
										.append(" sum(ka.qc_ye) je")
										.append(" from hk_huiyuan_kadangan ka ")
										.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
										.append(" where nvl(ka.dr,0)=0 ")
										.append(" and ka.kastatus not in ( '����','����') ")
										
									.append(" union all ")
										
										.append(" select ")
										.append(" sum( nvl(kainfo.chongzhi,0) + nvl(kainfo.xiaofei,0) + nvl(kainfo.zhuanru,0) - nvl(kainfo.zhuanchu,0) ) je ")
										.append(" from ")
										.append("( ")
											.append(" select ")
											.append(" sum( case when kib.xmdl='��ֵ' then kib.ka_je else 0 end ) chongzhi")
											.append(",sum( case when kib.xmdl='����' then kib.ka_je else 0 end ) xiaofei")
											.append(",sum( case when kib.xmdl='��ת' then kib.ka_je else 0 end ) zhuanru")
											.append(",0 zhuanchu ")
											.append(" from hk_huiyuan_kainfo ki ")
											.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
											.append(" left join hk_huiyuan_kaxing kx on kib.kaxing_pk = kx.pk_hk_huiyuan_kaxing ")
											.append(" where nvl(ki.dr,0)=0 and nvl(kib.dr,0)=0 ")
											.append(" and nvl(kib.zdh,'null')!='null' ")	// ��ȡ�ֹ����ϵ�����
											.append(" and substr(ki.dbilldate,0,10) <= '"+date+"' ")
										.append(" union all ")
											.append(" select ")
											.append(" 0 ")
											.append(",0 ")
											.append(",0 ")
											.append(",sum( case when kib.xmdl='��ת' then kib.ka_je else 0 end ) zhuanchu ")
											.append(" from hk_huiyuan_kainfo ki ")
											.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
											.append(" left join hk_huiyuan_kaxing kx on kib.y_kaxing_pk = kx.pk_hk_huiyuan_kaxing ")
											.append(" where nvl(ki.dr,0)=0 and nvl(kib.dr,0)=0 ")
											.append(" and substr(ki.dbilldate,0,10) <= '"+date+"' ")
										.append(" ) kainfo ")
									.append(" ) ") 
										
					;
					
					ArrayList list_1 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
					
					if(list_1.size()>0)
					{
						Object[] obj = (Object[])list_1.get(0);
						resultBVOs[i].setYue( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
									
				}
				
				
//				/** 
//				 * 1�����������������
//				 */
//				{
//					StringBuffer whereSQL_1 = // �������Ĺ̶�sql
//							new StringBuffer("")
//									.append(" and ka.kastatus not in ('����','����') ")		// ��ȡ ���ߡ�����
//									.append(" and ka.ka_code not in ('0203005888','0302101237') ") //��ȡ �����󿨡�һ����
////									.append(" and (( ka.kastatus='����' and substr(ka.vdef03,0,10)>'"+date+"' ) or ka.kastatus != '����') ")//��ȡ ���Ͽ���Ҫ������������
//					;
//					
//					StringBuffer querySQL_1 = 
//							new StringBuffer("select sum(je) ")
//									.append(" from ( ")
//									// �ڳ�
////									.append(" select sum(ka.qc_ye) je ")
////									.append(" from hk_huiyuan_kadangan ka ")
////									.append(" where ka.dr=0 ")
////									.append(whereSQL_1)
//									
//									.append(" select sum(qc.je) je ")
//									.append(" from ( ")
//										.append(" select max(ka.qc_ye) je,ka.pk_hk_huiyuan_kadangan ")
//										.append(" from hk_huiyuan_kadangan ka ")
//										.append(" left join ( ")
//											.append(" select ccz.pk_hk_huiyuan_kadangan,max(ccz.ts) ts ")
//											.append(" from hk_huiyuan_kadangan_cz ccz ")
//											.append(" where ccz.dr=0 ")
//											.append(" and substr(ccz.vbdef03,0,10)<='"+date+"' ")
//											.append(" group by ccz.pk_hk_huiyuan_kadangan ")
//										.append(" ) czts on ka.pk_hk_huiyuan_kadangan = czts.pk_hk_huiyuan_kadangan ")
//										.append(" left join hk_huiyuan_kadangan_cz cz on (cz.pk_hk_huiyuan_kadangan = czts.pk_hk_huiyuan_kadangan and cz.ts=czts.ts) ")
//										.append(" where ka.dr=0 ")
//										.append(" and ka.kastatus not in ('����','����') ")
//										.append(" and ka.ka_code not in ('0203005888','0302101237') ")
//										.append(" and nvl(cz.vbdef02,'~') != '���Ͽ�' ")
//										.append(" group by ka.pk_hk_huiyuan_kadangan ")
//									.append(" ) qc ") 
//									// ��ֵ
//									.append(" union all ")
//									.append(" select sum(cz.cz_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and nvl(cz.vbdef02,'~') != '���Ͽ�' ) ")
//									.append(" where ka.dr=0 and cz.dr=0 ")
//									.append(whereSQL_1)
//									.append(" and substr(cz.vbdef03,0,10)<='"+date+"' ")
//									// ��ת
//									.append(" union all ")
//									.append(" select sum(nvl(yz.zr_je,0) - nvl(yz.zc_je,0)) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_yz yz on ka.pk_hk_huiyuan_kadangan = yz.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and yz.dr=0 ")
//									.append(whereSQL_1)
//									.append(" and substr(yz.vbdef03,0,10)<='"+date+"' ")
//									// ����
//									.append(" union all ")
//									.append(" select sum(xf.xf_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_xf xf on ka.pk_hk_huiyuan_kadangan = xf.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and xf.dr=0 ")
//									.append(whereSQL_1)
//									.append(" and substr(xf.vbdef03,0,10)<='"+date+"' ")
//									
//									// ���� û��ȡ �ֹ����������Ͽ��������ݣ� ����Ҫ���� �ֹ�����������
//									// �ֹ����� �ı�־��   �˵���  Ϊ��
//									.append(" union all ")
//									.append(" select sum(0-nvl(cz.cz_je,0)) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and nvl(cz.zdh,'~') = '~' ) ")
//									.append(" where ka.dr=0 and cz.dr=0 ")
//									.append(whereSQL_1)
//									.append(" and substr(cz.vbdef03,0,10)<='"+date+"' ")
//									.append(" and substr(cz.vbdef03,0,10)>='2015-10-28' ")
//									
//									.append(" ) ")
//									
//									
//					;
//					
//					ArrayList list_1 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
//					
//					if(list_1.size()>0)
//					{
//						Object[] obj = (Object[])list_1.get(0);
//						resultBVOs[i].setKayue( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
//					}
//					
//				}
				
				/** 
				 * 2�����Ͽ����
				 *    ���Ͽ�  ȡ �����ֵҳǩ��  ���Ͽ���ʶ�� ����ȡ��ͷ�ġ�
				 *    �п���  ���ʳ�ֵ������һ�������ϣ���һ���ǳ�ֵ�� ���������  �� ����״̬�� ֻ��������һ��  ������ͳ�ơ�
				 *    ���Ͽ� ������ʹ�ã� ���Բ��ÿ���  ����׷�ݡ�
				 */
				{
					StringBuffer whereSQL_2 = // ���Ͽ��Ĺ̶�sql
							new StringBuffer("")
									.append(" and ka.kastatus not in ('����','����') ")		// ��ȡ ���ߡ�����
									.append(" and ka.ka_code not in ('0203005888') ") 		// ��ȡ ������
									.append(" and ka.ka_code not between '0302101237' and '0302101326'  ")	// ��ȡ һ����
//									.append(" and ( ka.kastatus='����' and substr(ka.vdef03,0,10)<='"+date+"' ) ")//���Ͽ���Ҫ������������
					;
					
					StringBuffer querySQL_2 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
									// �ڳ�  ���ڳ������ϣ�
//									.append(" select sum(ka.qc_ye) je ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" where ka.dr=0 ")
//									.append(whereSQL_2)
//									// ��ֵ
//									.append(" union all ")
									.append(" select sum(nvl(cz.cz_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and cz.vbdef02 = '���Ͽ�' and substr(cz.vbdef03,0,10)<='"+date+"' ) ")
									.append(" where ka.dr=0 and cz.dr=0 ")
									.append(whereSQL_2)
									// ������ת��ֻ���� ������ת��Ϣ  ���ù��� ��ֵ��Ϣ�ˣ�
									.append(" union all ")
									.append(" select sum(0-nvl(zf.zc_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and cz.vbdef02 = '���Ͽ�' and substr(cz.vbdef03,0,10)<='"+date+"' ) ")
									.append(" inner join hk_huiyuan_kadangan_zf zf on ka.pk_hk_huiyuan_kadangan = zf.pk_hk_huiyuan_kadangan ")
									.append(" where ka.dr=0 and zf.dr=0 ")
									.append(whereSQL_2)
									.append(" and substr(zf.vbdef03,0,10)<='"+date+"' ")
									// ����
//									.append(" union all ")
//									.append(" select sum(xf.xf_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_xf xf on ka.pk_hk_huiyuan_kadangan = xf.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and xf.dr=0 ")
//									.append(whereSQL_2)
//									.append(" and substr(xf.vbdef03,0,10)<='"+date+"' ")
									.append(" ) ")
					;
					
					ArrayList list_2 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
					
					if(list_2.size()>0)
					{
						Object[] obj = (Object[])list_2.get(0);
						resultBVOs[i].setZuofei( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				/** 
				 * 3�������󿨿���� 0203005888
				 *    ������  Ӧ��ֻ����ת�� û�г�ֵ �� ����
				 */
				{
					StringBuffer whereSQL_3 = // �����󿨵Ĺ̶�sql
							new StringBuffer("")
									.append(" and ka.ka_code = '0203005888' ") //ֻȡ ������
					;
					
					StringBuffer querySQL_3 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
									// �ڳ�
									.append(" select sum(ka.qc_ye) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" where ka.dr=0 ")
									.append(whereSQL_3)
									// ��ֵ
//									.append(" union all ")
//									.append(" select sum(cz.cz_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and cz.dr=0 ")
//									.append(whereSQL_3)
//									.append(" and substr(cz.vbdef03,0,10)<='"+date+"' ")
									// ��ת
									.append(" union all ")
									.append(" select sum(nvl(yz.zr_je,0) - nvl(yz.zc_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" inner join hk_huiyuan_kadangan_yz yz on ka.pk_hk_huiyuan_kadangan = yz.pk_hk_huiyuan_kadangan ")
									.append(" where ka.dr=0 and yz.dr=0 ")
									.append(whereSQL_3)
									.append(" and substr(yz.vbdef03,0,10)<='"+date+"' ")
									// ����
//									.append(" union all ")
//									.append(" select sum(xf.xf_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_xf xf on ka.pk_hk_huiyuan_kadangan = xf.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and xf.dr=0 ")
//									.append(whereSQL_3)
//									.append(" and substr(xf.vbdef03,0,10)<='"+date+"' ")
									.append(" ) ")
					;
					
					ArrayList list_3 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_3.toString(), new ArrayListProcessor());
					
					if(list_3.size()>0)
					{
						Object[] obj = (Object[])list_3.get(0);
						resultBVOs[i].setDaka3( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				/** 
				 * 4��һ���󿨿���� 0302101237
				 *   һ���� �� ɾ��  ���� ���ϵ� ����ʽ��
				 *   һ����  һ���� ��500��  ����ɾ�� ������
				 */
				{
					StringBuffer whereSQL_4 = // һ���󿨵Ĺ̶�sql
							new StringBuffer("")
									.append(" and ka.ka_code between '0302101237' and '0302101326'  ") //ֻȡ һ���� ��1237 �� 1326��
					;
					
					StringBuffer querySQL_4 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
									// �ڳ�
									.append(" select sum(ka.qc_ye) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" where ka.dr=0 ")
									.append(whereSQL_4)
									// ��ת
									.append(" union all ")
									.append(" select sum(nvl(yz.zr_je,0) - nvl(yz.zc_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" inner join hk_huiyuan_kadangan_yz yz on ka.pk_hk_huiyuan_kadangan = yz.pk_hk_huiyuan_kadangan ")
									.append(" where ka.dr=0 and yz.dr=0 ")
									.append(whereSQL_4)
									.append(" and substr(yz.vbdef03,0,10)<='"+date+"' ")
									// ����
									.append(" union all ")
									.append(" select sum(0-nvl(zf.zc_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and cz.vbdef02 = '���Ͽ�' and substr(cz.vbdef03,0,10)<='"+date+"' ) ")
									.append(" inner join hk_huiyuan_kadangan_zf zf on ka.pk_hk_huiyuan_kadangan = zf.pk_hk_huiyuan_kadangan ")
									.append(" where ka.dr=0 and zf.dr=0 ")
									.append(whereSQL_4)
									.append(" and substr(zf.vbdef03,0,10)<='"+date+"' ")
									
									.append(" ) ")
									
					;
					
					ArrayList list_4 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_4.toString(), new ArrayListProcessor());
					
					if(list_4.size()>0)
					{
						Object[] obj = (Object[])list_4.get(0);
						resultBVOs[i].setDaka1( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				/**
				 * 5�����ߴ� ���  ��ȡ  ϵͳ ����״̬�Ŀ�����
				 */
				{
					
//					resultBVOs[i].setYue_xm( UFDouble.ZERO_DBL );
					
//					StringBuffer whereSQL_5 = // ���ߴ󿨵Ĺ̶�sql
//							new StringBuffer("")
//									.append(" and ka.ka_code = '0103001500' ") //ֻȡ ���ߴ�
//					;
//					
//					StringBuffer querySQL_5 = 
//							new StringBuffer("select sum(je) ")
//									.append(" from ( ")
//									// �ڳ�
//									.append(" select sum(ka.qc_ye) je ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" where ka.dr=0 ")
//									.append(whereSQL_5)
//									// ��ת
//									.append(" union all ")
//									.append(" select sum(nvl(yz.zr_je,0) - nvl(yz.zc_je,0)) je ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_yz yz on ka.pk_hk_huiyuan_kadangan = yz.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and yz.dr=0 ")
//									.append(whereSQL_5)
//									.append(" and substr(yz.vbdef03,0,10)<='"+date+"' ")
//									.append(" ) ")
//					;
//					
//					ArrayList list_5 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_5.toString(), new ArrayListProcessor());
//					
//					if(list_5.size()>0)
//					{
//						Object[] obj = (Object[])list_5.get(0);
//						resultBVOs[i].setYue_xm( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
//					}
					
					
//					StringBuffer whereSQL_5 = // ���� �̶�sql
//							new StringBuffer("")
//									.append(" and ka.kastatus in ('����','����') ")		// ��ȡ ���ߡ�����
////									.append(" and ka.ka_code not in ('0203005888','0302101237') ") //��ȡ �����󿨡�һ����
//									.append(" and ( ka.kastatus='����' and substr(ka.vdef03,0,10)<='"+date+"' ) ")//�����Ҫ���Ǽ�������
//					;
					
					StringBuffer querySQL_5 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
										.append(" select sum(ka.qc_ye) je ")
										.append(" from hk_huiyuan_kadangan ka ")
										.append(" where ka.dr=0 ")
										.append(" and ka.kastatus in ('����','����') ")
										.append(" and ( ka.kastatus = '����' or ( ka.kastatus = '����' and substr(ka.vdef03,0,10)>'"+date+"'  ) ) ")
									.append(" ) ")
					;
					
					ArrayList list_5 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_5.toString(), new ArrayListProcessor());
					
					if(list_5.size()>0)
					{
						Object[] obj = (Object[])list_5.get(0);
						resultBVOs[i].setYue_xm( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				/**
				 * 6��������
				 */
				{
					StringBuffer querySQL_6 = 
						new StringBuffer("select ")
								.append(" sum(jg.jg_yue) yue_jg ")
								.append(" from hk_huiyuan_kadangan ka ")
								.append(" inner join hk_huiyuan_kadangan_jg jg on ka.pk_hk_huiyuan_kadangan = jg.pk_hk_huiyuan_kadangan ")
								.append(" inner join  ")
								.append(" ( ")
								.append(" select ")
								.append("  pk_hk_huiyuan_kadangan ")
								.append(" ,nvl( max( vbdef01 ),'null' )  vbdef01 ")
								.append(" from hk_huiyuan_kadangan_jg jg ")
								.append(" where nvl(dr,0)=0 ")
								.append(" and nvl(vbdef01,'1990-01-01 00:00:00')<='"+date+" 23:59:59' ")
								.append(" group by jg.pk_hk_huiyuan_kadangan ")
								.append(" ) jgsj on ka.pk_hk_huiyuan_kadangan = jgsj.pk_hk_huiyuan_kadangan ")
								.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
								.append(" where nvl(ka.dr,0)=0 and nvl(jg.dr,0)=0 ")
								.append(" and nvl(jg.vbdef01,'null') = nvl(jgsj.vbdef01,'null') ")
					;
					
					ArrayList list_6 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_6.toString(), new ArrayListProcessor());
					
					if(list_6.size()>0)
					{
						Object[] obj = (Object[])list_6.get(0);
						resultBVOs[i].setYue_jg( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				
				/**
				 * 7����� ����
				 */
//				// ����� = ��Ա������� + ���Ͽ� + ���� + һ��
//				resultBVOs[i].setYue( 
//						  resultBVOs[i].getKayue()
//					.add( resultBVOs[i].getZuofei() )
//					.add( resultBVOs[i].getDaka3() )
//					.add( resultBVOs[i].getDaka1() )
//				);
				
				// ��Ա�������  = �����  - ���Ͽ� - ����  - һ�� 
				resultBVOs[i].setKayue( 
						  resultBVOs[i].getYue()
					.sub( resultBVOs[i].getZuofei() )
					.sub( resultBVOs[i].getDaka3() )
					.sub( resultBVOs[i].getDaka1() )
						
				);
				
				// Ӧ������� = ����� + ���ߴ�
				resultBVOs[i].setYue_yf( 
						  resultBVOs[i].getYue()
					.add( resultBVOs[i].getYue_xm() )
				);
				
				// ��֤ = ����� - ������
				resultBVOs[i].setYanzheng( 
						  resultBVOs[i].getYue()
					.sub( resultBVOs[i].getYue_jg() )
				);
				
			}
						
			
			KazhangwuzongHVO   resultHVO  = new KazhangwuzongHVO();
			resultHVO.setIbillstatus(-1);
			resultHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//����
			resultHVO.setPk_org("0001NC10000000004AXZ");	// pk_org ����pk
			resultHVO.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
			resultHVO.setVdef01( ks_date_str.equals(js_date_str)?ks_date_str:(ks_date_str +" �� "+js_date_str) );	// ҵ������
			
			KazhangwuzongBillVO resultBillVO = new KazhangwuzongBillVO();
			resultBillVO.setParentVO(resultHVO);
			resultBillVO.setChildrenVO(resultBVOs);
			// ������� �ŵ�����
			getEditor().getBillCardPanel().setBillData(
					getEditor().getBillCardPanel().getBillData());
			getEditor().getModel().initModel(new KazhangwuzongBillVO[]{resultBillVO});
		}
	}
	
	
	QueryConditionDLGDelegator dlgDelegator;
	private QueryConditionDLGDelegator getDlgDelegator() {
		if (dlgDelegator == null) {
			TemplateInfo tempinfo = new TemplateInfo();
			tempinfo.setPk_Org(this.getModel().getContext().getPk_group());
			tempinfo.setUserid(this.getModel().getContext().getPk_loginUser());
			tempinfo.setFunNode("HKJ20607");
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
