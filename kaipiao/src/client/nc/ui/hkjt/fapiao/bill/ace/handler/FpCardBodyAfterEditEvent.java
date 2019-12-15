package nc.ui.hkjt.fapiao.bill.ace.handler;

import java.util.ArrayList;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.hkjt.IHk_fp_billMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.hkjt.pub.Pub_Param;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class FpCardBodyAfterEditEvent implements IAppEventHandler<CardBodyAfterEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// ��Ƭ����
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		try
		{
			if( "billno".equals( e.getKey() ) )	// �˵���
			{
				String billno = PuPubVO.getString_TrimZeroLenAsNull( e.getValue() );
				
				if( billno!=null )
				{
				
					billno = billno.toUpperCase();	// ת�ɴ�д ���д���
					String pk_org = cardForm.getBillCardPanel().getHeadItem("pk_org").getValue();
	
					/**
					 * 1���ж�  ���ŵ��� ��û��ͬ���ĵ��ţ� ����� ��ʾ����
					 */
					for(int i=0;i<cardForm.getBillCardPanel().getBillModel().getRowCount();i++)
					{
						if( i==e.getRow() )
						{// ��� ѭ���� ���У� �������� �����Լ����Լ��жϡ�
							continue;
						}
						
						String billno_temp = PuPubVO.getString_TrimZeroLenAsNull(
							cardForm.getBillCardPanel().getBillModel().getValueAt(i,"billno")
							);
						
						if( billno.equalsIgnoreCase(billno_temp) )
						{// �����ִ�Сд
							MessageDialog.showErrorDlg(cardForm, "", "�˵��Ų����ظ�");
							cardForm.getBillCardPanel().getBillModel().setValueAt(null,e.getRow(),"billno");
							return;
						}
						
					}
					/**END*/
					
					/** 
					 * 2��ȡ�ѿ�Ʊ�Ľ��
					 */
//					StringBuffer querySQL = 
//							new StringBuffer(" select ")
//									.append(" sum(fpb.billje) ")
//									.append(" from hk_fapiao_bill fp ")
//									.append(" inner join hk_fapiao_bill_b fpb on fp.pk_hk_fapiao_bill = fpb.pk_hk_fapiao_bill ")
//									.append(" where fp.dr=0 and fpb.dr=0 ")
//									.append(" and NLS_UPPER(fpb.billno) = '"+billno+"' ")
//									.append(" and fp.pk_org = '"+pk_org+"' ")
//					;
//					IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
//					ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
//					if( list!=null && list.size()>0 )
//					{
//						MessageDialog.showErrorDlg(cardForm, "", "���˵� �Ѿ�����Ʊ�ˣ������ظ���Ʊ��");
//						cardForm.getBillCardPanel().getBillModel().setValueAt(null,e.getRow(),"billno");
//						return;
//					}
					/**END*/
					
					IHk_fp_billMaintain itf = NCLocator.getInstance().lookup(IHk_fp_billMaintain.class);
					ZhangdanBillVO[] result = itf.queryZhangdanBill(
							new String[]{billno}	// �˵���
						  , new String[]{pk_org}	// ��˾
						  , null
					);
					
					if(result!=null && result.length>0)
					{
						ZhangdanHVO zdHVO = result[0].getParentVO();
						
						/**
						 *  ��� �˵����� �Ƿ���Ϲ淶
						 *  1���˵����� ������ڵ���  ��������
						 *  2���˵����� ������6����֮��
						 */
						Integer KPQX = Pub_Param.getInstance().KPQX;
						UFDate  SXSJ = PuPubVO.getUFDate( Pub_Param.getInstance().SXSJ_MAP.get(pk_org) );
						
						if( SXSJ.compareTo(zdHVO.getDbilldate())>0 )
						{// ��� ��Чʱ��  ����  �˵�ҵ�����ڣ� ���ܿ�Ʊ
							MessageDialog.showErrorDlg(cardForm, "", "ֻ�ܿ� "+(Pub_Param.getInstance().SXSJ_MAP.get(pk_org))+" ֮����˵���");
							return;
						}
						else if( WorkbenchEnvironment.getServerTime().getDate().compareTo( zdHVO.getDbilldate().getDateAfter( 30*KPQX ) ) > 0 )
						{// ��� ��ǰʱ��  ����  �˵����� 6���£� ���ܿ�Ʊ
							MessageDialog.showErrorDlg(cardForm, "","�Ѿ�������Ʊ����"+KPQX+"���£��˵�����Ϊ"+(zdHVO.getDbilldate().toString().substring(0, 10))+"��");
							return;
						}
						
						/**END*/
						
						UFDouble billje = new UFDouble(0.0);
						
						// �˵���� = �ֽ��࣬�ֽ�֧Ʊ��pos��  �� ΢�š�֧������Ӧ���� �� ����
						billje =  PuPubVO.getUFDouble_NullAsZero( zdHVO.getXianjin() )
							.add( PuPubVO.getUFDouble_NullAsZero( zdHVO.getPos() ) )
							.add( PuPubVO.getUFDouble_NullAsZero( zdHVO.getZhipiao() ) )
							.add( PuPubVO.getUFDouble_NullAsZero( zdHVO.getWanglai() ) )
						;
						
						/**
						 * �ѿ�Ʊ���
						 */
						UFDouble ykpje = UFDouble.ZERO_DBL;
						
						StringBuffer querySQL = 
								new StringBuffer(" select ")
										.append(" sum(fpb.billje) ")
										.append(" from hk_fapiao_bill fp ")
										.append(" inner join hk_fapiao_bill_b fpb on fp.pk_hk_fapiao_bill = fpb.pk_hk_fapiao_bill ")
										.append(" where fp.dr=0 and fpb.dr=0 ")
										.append(" and NLS_UPPER(fpb.billno) = '"+billno+"' ")
										.append(" and fp.pk_org = '"+pk_org+"' ")
						;
						IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
						ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
						
						if(list!=null && list.size()>0)
						{
							ykpje = PuPubVO.getUFDouble_NullAsZero( ((Object[])list.get(0))[0] );
						}
						
						UFDouble bckpje = billje.sub(ykpje);	// ���ο�Ʊ��ʣ�࿪Ʊ��  = �˵���� - �ѿ�Ʊ���
						
						/**END*/
						
						// ���ο�Ʊ���
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								bckpje
							  , e.getRow()
							  , "billje"
						);
						// ʣ�࿪Ʊ���
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								bckpje
							  , e.getRow()
							  , "vbdef03"
						);
						// �˵����
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								billje
							  , e.getRow()
							  , "vbdef01"
						);
						// �ѿ�Ʊ���
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								ykpje
							  , e.getRow()
							  , "vbdef02"
						);
						
						cardForm.getBillCardPanel().getBillModel().setValueAt(	// ��д�� ��ȷ���˵���
								zdHVO.getVbillcode()
							  , e.getRow()
							  , "billno"
						);
						
					}
					else
					{
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								null
							  , e.getRow()
							  , "billje"
						);
						MessageDialog.showErrorDlg(cardForm, "", "û�и��ʵ������� �˵����Ƿ���ȷ��");
						return;
					}
					
				}
				else
				{
					cardForm.getBillCardPanel().getBillModel().setValueAt(
							null
						  , e.getRow()
						  , "billje"
					);
				}
				
				// ����� ���ӵ� ��ͷ��Ʊ���
				UFDouble fpje = UFDouble.ZERO_DBL;
				for(int i=0;i<cardForm.getBillCardPanel().getBillModel().getRowCount();i++)
				{
					fpje =  fpje
					  .add( PuPubVO.getUFDouble_NullAsZero( 
							  cardForm.getBillCardPanel().getBillModel().getValueAt(i,"billje") 
							) 
						  );
				}
				cardForm.getBillCardPanel().getHeadItem("fpje").setValue(fpje);
				
			}
			
			else if( "billje".equals( e.getKey() ) )
			{
				// ����� ���ӵ� ��ͷ��Ʊ���
				UFDouble fpje = UFDouble.ZERO_DBL;
				for(int i=0;i<cardForm.getBillCardPanel().getBillModel().getRowCount();i++)
				{
					fpje =  fpje
					  .add( PuPubVO.getUFDouble_NullAsZero( 
							  cardForm.getBillCardPanel().getBillModel().getValueAt(i,"billje") 
							) 
						  );
				}
				cardForm.getBillCardPanel().getHeadItem("fpje").setValue(fpje);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
	}

	public ShowUpableBillForm getCardForm() {
		return cardForm;
	}

	public void setCardForm(ShowUpableBillForm cardForm) {
		this.cardForm = cardForm;
	}
	
}
