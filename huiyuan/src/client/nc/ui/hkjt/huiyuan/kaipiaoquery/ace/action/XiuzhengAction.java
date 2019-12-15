package nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.dcm.chnlrplstrct.maintain.action.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class XiuzhengAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1049580924829904678L;

	public XiuzhengAction() {
		setBtnName("�����������Ŀ�Ʊ����");
		setCode("xiuzhengAction");
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
		
		/**
		 * 1����ѯ�� ��һ�µ�����
		 */
		StringBuffer querySQL = 
			new StringBuffer(" select * from ")
					.append(" ( ")
					.append(" 	select ")
					.append("   kp_ka.pk_hk_huiyuan_kadangan ")
					.append("  ,kp_ka.�ѿ�Ʊ�ܶ� ")
					.append("  ,kp_dj.����� ")
					.append("  ,kp_ka.���� ")
					.append("  from ")
					.append("  ( ")
					.append("  	 select")
					.append("    ka.pk_hk_huiyuan_kadangan ")
					.append("	,max(ka.ka_code) ���� ")
					.append("   ,sum( nvl(ka_kkp.ykp_je,0) ) �ѿ�Ʊ�ܶ� ")
					.append("  	from hk_huiyuan_kadangan ka ")
					.append("  	inner join hk_huiyuan_kadangan_kkp ka_kkp ")
					.append("   on ka.pk_hk_huiyuan_kadangan = ka_kkp.pk_hk_huiyuan_kadangan ")
					.append("   inner join hk_huiyuan_kaxing kx ")
					.append("   on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
					.append("   where ka.dr=0 and ka_kkp.dr=0 ")
					.append("   group by ka.pk_hk_huiyuan_kadangan ")
					.append("  ) kp_ka ")
					.append("  left join ( ")
					.append("  	 select ")
					.append("  	  kpb.ka_pk ")
					.append("  	 ,sum( case when kp.ibillstatus =1 then kpb.fpje else 0 END ) ����� ")
					.append("  	 from hk_huiyuan_kaipiaoinfo kp ")
					.append("  	 inner join hk_huiyuan_kaipiaoinfo_b kpb ")
					.append("  	 on kp.pk_hk_huiyuan_kaipiaoinfo = kpb.pk_hk_huiyuan_kaipiaoinfo ")
					.append("  	 where (1=1) ")
					.append("  	 and kp.dr=0 and kpb.dr=0 ")
					.append("  	 group by kpb.ka_pk ")
					.append("  ) kp_dj ")
					.append("  on kp_dj.ka_pk = kp_ka.pk_hk_huiyuan_kadangan ")
					.append("  where (1=1) ")
					.append(" ) bb ")
					.append(" where bb.�ѿ�Ʊ�ܶ� <> ����� ")
					/**
					 * ����
					 */
//					.append(" and bb.���� = 'GJYHZS02000517' ")
					/***END***/
		;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
		ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor() );
		
		String msg = "û����Ҫ����������";
		
		if( list!=null && list.size()>0 )
		{
			msg  = "�ܹ���"+list.size()+"����������Ҫ�������Ƿ����������"+"\r\n";
			msg += "�����š������������ѿ�Ʊ�ܶ�������ϵ��ѿ�Ʊ�ܶ"+"\r\n";
			for(int i=0;i<list.size();i++)
			{
				Object[] obj = (Object[])list.get(i);
				msg+= "��"+obj[3]+"����"+obj[1]+"����"+obj[2]+"��"+"\r\n";
			}
		}else{
			MessageDialog.showWarningDlg(this.getListview(), "", msg);
			return;
		}
		
		int flag = MessageDialog.showYesNoDlg(this.getListview(), "��Ϣ", msg);
		
		/**
		 * 2��������� ���д���
		 */
		if( flag!=MessageDialog.ID_YES ){return;}
		
		/**
		 * 2.1��
		 */
		{
			ArrayList<KadanganKKPVO> updateVOs_kkp = new ArrayList<KadanganKKPVO>();	// Ҫ���µ�VO  �ɿ�Ʊ
			
			for(int i=0;i<list.size();i++)		
			{
				Object[] obj = (Object[])list.get(i);
				
				String ka_pk = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
				
				ArrayList<KadanganKKPVO> kkp_list = 
						(ArrayList<KadanganKKPVO>)iUAPQueryBS.retrieveByClause(
						KadanganKKPVO.class,
						" (1=1) " +
						" and dr = 0 " +
						" and pk_hk_huiyuan_kadangan = '"+ka_pk+"' " +
						" and nvl(kkp_je,0) > 0.00 " +		// �ɿ�Ʊ�ܶ� ���� 0
						" order by kpjz_time "				// �� ��Ʊ��ֹ���� ����
//						new String[]{" kpjz_time "}	// order by ��Ʊ����ʱ��
				);
				
				UFDouble fpje = PuPubVO.getUFDouble_NullAsZero( obj[2] );
				
				// ���Ƚ� �ѿ�Ʊ��� ��ֵΪ0
				for( KadanganKKPVO kkpVO:kkp_list )
				{
					kkpVO.setYkp_je(UFDouble.ZERO_DBL);
				}
				
				if( fpje.compareTo(UFDouble.ZERO_DBL)>=0 )
				{//  ��� ��Ʊ��� Ϊ������ ������ �ֿ�
					
					for( KadanganKKPVO kkpVO:kkp_list )
					{
						UFDouble kkp_je = // �ɿ�Ʊ���  = �ɿ�Ʊ��� - �ѿ�Ʊ���
								 PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
							.sub(PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
						);
						if( fpje.compareTo(kkp_je)>=0 )
						{// ��� ��Ʊ��� ���ڵ��� �ɿ�Ʊ�� ��  ���� ʵ�ʿ�Ʊ��� Ϊ  �ɿ�Ʊ�� ��Ʊ��� ���еֿۡ�
							
							kkpVO.setYkp_je( // �ѿ�Ʊ��� = ֮ǰ�ѿ�Ʊ��� + ���ο�Ʊ���
									  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
								.add( kkp_je )
							);
							fpje = fpje.sub(kkp_je);
						}
						else
						{// ���� ��  ���� ʵ�ʿ�Ʊ��� Ϊ  ��Ʊ�� ��Ʊ��� �ֿ�Ϊ0��
							
							kkpVO.setYkp_je( // �ѿ�Ʊ��� = ֮ǰ�ѿ�Ʊ��� + ���ο�Ʊ���
									  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
								.add( fpje )
							);
							fpje = UFDouble.ZERO_DBL;
						}
						
						updateVOs_kkp.add(kkpVO);	// ���Ҫ���µ�vo
						
						if(fpje.compareTo(UFDouble.ZERO_DBL)==0)
						{// �����Ʊ���  �ֿ�Ϊ0�ˣ� ���ٵֿ� �˳�ѭ��
							break;
						}
					}
				}
				else
				{// ��� ��Ʊ��� Ϊ��������ֱ���ۼ�
					if( kkp_list!=null && kkp_list.size()>0 )
					{
						for(int kkp_i=0;kkp_i<kkp_list.size();kkp_i++)
						{
							KadanganKKPVO kkpVO = kkp_list.get(kkp_i);
							
							UFDouble kkp_je = // �ɿ�Ʊ���  = �ɿ�Ʊ��� - �ѿ�Ʊ���
									  PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
								.sub( PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
							);
								
							if( kkp_je.compareTo(UFDouble.ZERO_DBL)!=0 
							 || kkp_i == kkp_list.size()-1
							  )
							{
								kkpVO.setYkp_je( // �ѿ�Ʊ = �ѿ�Ʊ + ��ǰ��Ʊ
										  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
									.add( PuPubVO.getUFDouble_NullAsZero(fpje) )
								);
								
								updateVOs_kkp.add(kkpVO);	// ���Ҫ���µ�vo
								
								fpje = UFDouble.ZERO_DBL;
								
								break;
							}
						}
					}
				}
			}
			
			KadanganKKPVO[] vos = new KadanganKKPVO[updateVOs_kkp.size()];
			vos = updateVOs_kkp.toArray(vos);
			HYPubBO_Client.updateAry(vos);	// ���� �ɿ�Ʊ����
			
			nc.ui.pub.beans.MessageDialog.showWarningDlg(this.getListview(), "", "�������");
		}
	}
}
