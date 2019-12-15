package nc.ui.hkjt.fapiao_sk.bill.ace.handler;

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

public class SkFpCardBodyAfterEditEvent implements IAppEventHandler<CardBodyAfterEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// ��Ƭ����
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		try
		{
			if( "sk_code".equals( e.getKey() ) )	// �տ��
			{
				String sk_code = PuPubVO.getString_TrimZeroLenAsNull( e.getValue() );
				
				IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
				
				if( sk_code!=null )
				{
				
					sk_code = sk_code.toUpperCase();	// ת�ɴ�д ���д���
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
						
						String sk_code_temp = PuPubVO.getString_TrimZeroLenAsNull(
							cardForm.getBillCardPanel().getBillModel().getValueAt(i,"sk_code")
							);
						
						if( sk_code.equalsIgnoreCase(sk_code_temp) )
						{// �����ִ�Сд
							MessageDialog.showErrorDlg(cardForm, "", "�տ�Ų����ظ�");
							cardForm.getBillCardPanel().getBillModel().setValueAt(null,e.getRow(),"sk_code");
							return;
						}
						
					}
					/**END*/
					
					StringBuffer querySQL_sk = 
						new StringBuffer(" select ")
								.append(" sk.pk_gatherbill ")	// �տpk
								.append(",max(sk.billno) ")		// �տ��
								.append(",max(skb.customer) ")	// �ͻ�
								.append(",sum(skb.money_cr) ")	// �տ���
								.append(" from ar_gatherbill sk ")
								.append(" inner join ar_gatheritem skb on sk.pk_gatherbill = skb.pk_gatherbill ")
								.append(" where sk.dr=0 and skb.dr=0 ")
								.append(" and NLS_UPPER(sk.billno) = '"+sk_code+"' ")
								.append(" group by sk.pk_gatherbill ")
					;
					
					ArrayList list_sk = (ArrayList)iUAPQueryBS.executeQuery(querySQL_sk.toString(), new ArrayListProcessor());
					
					if(list_sk!=null && list_sk.size()>0)
					{
						Object[] obj = (Object[])list_sk.get(0);
						
						String   skPk = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
						String skCode = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
						String     kh = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
						UFDouble skJe = PuPubVO.getUFDouble_NullAsZero(obj[3]);
						
						/**
						 * �ѿ�Ʊ���
						 */
						UFDouble ykpje = UFDouble.ZERO_DBL;
						
						StringBuffer querySQL = 
								new StringBuffer(" select ")
										.append(" sum(fpb.sk_money) ")
										.append(" from hk_fapiao_sk_bill fp ")
										.append(" inner join hk_fapiao_sk_bill_b fpb on fp.pk_hk_fapiao_sk_bill = fpb.pk_hk_fapiao_sk_bill ")
										.append(" where fp.dr=0 and fpb.dr=0 ")
										.append(" and NLS_UPPER(fpb.sk_code) = '"+sk_code+"' ")
//										.append(" and fp.pk_org = '"+pk_org+"' ")
						;
						ArrayList list_ykp = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
						
						if(list_ykp!=null && list_ykp.size()>0)
						{
							ykpje = PuPubVO.getUFDouble_NullAsZero( ((Object[])list_ykp.get(0))[0] );
						}
						
						UFDouble bckpje = skJe.sub(ykpje);	// ���ο�Ʊ��ʣ�࿪Ʊ��  = �˵���� - �ѿ�Ʊ���
						
						/**END*/
						
						// ���ο�Ʊ���
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								bckpje
							  , e.getRow()
							  , "sk_money"
						);
						// ʣ�࿪Ʊ���
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								bckpje
							  , e.getRow()
							  , "vbdef03"
						);
						// �տ���
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								skJe
							  , e.getRow()
							  , "vbdef01"
						);
						// �ѿ�Ʊ���
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								ykpje
							  , e.getRow()
							  , "vbdef02"
						);
						// ��д�� ��ȷ���տ��
						cardForm.getBillCardPanel().getBillModel().setValueAt(	
								skCode
							  , e.getRow()
							  , "sk_code"
						);
						// ��д�� �տpk
						cardForm.getBillCardPanel().getBillModel().setValueAt(	
								skPk
							  , e.getRow()
							  , "sk_pk"
						);
						// ��д�� ��ͷ�ͻ�
						cardForm.getBillCardPanel().getHeadItem("pk_customer").setValue(kh);
					}
					else
					{
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								null
							  , e.getRow()
							  , "sk_money"
						);
						MessageDialog.showErrorDlg(cardForm, "", "û�и��ʵ������� �տ���Ƿ���ȷ��");
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
							  cardForm.getBillCardPanel().getBillModel().getValueAt(i,"sk_money") 
							) 
						  );
				}
				cardForm.getBillCardPanel().getHeadItem("fpje").setValue(fpje);
				
			}
			
			else if( "sk_money".equals( e.getKey() ) )
			{
				// ����� ���ӵ� ��ͷ��Ʊ���
				UFDouble fpje = UFDouble.ZERO_DBL;
				for(int i=0;i<cardForm.getBillCardPanel().getBillModel().getRowCount();i++)
				{
					fpje =  fpje
					  .add( PuPubVO.getUFDouble_NullAsZero( 
							  cardForm.getBillCardPanel().getBillModel().getValueAt(i,"sk_money") 
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
