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
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		try
		{
			if( "sk_code".equals( e.getKey() ) )	// 收款单号
			{
				String sk_code = PuPubVO.getString_TrimZeroLenAsNull( e.getValue() );
				
				IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
				
				if( sk_code!=null )
				{
				
					sk_code = sk_code.toUpperCase();	// 转成大写 进行处理
					String pk_org = cardForm.getBillCardPanel().getHeadItem("pk_org").getValue();
	
					/**
					 * 1、判断  本张单子 有没有同样的单号， 如果有 提示错误
					 */
					for(int i=0;i<cardForm.getBillCardPanel().getBillModel().getRowCount();i++)
					{
						if( i==e.getRow() )
						{// 如果 循环到 本行， 则跳过。 不能自己跟自己判断。
							continue;
						}
						
						String sk_code_temp = PuPubVO.getString_TrimZeroLenAsNull(
							cardForm.getBillCardPanel().getBillModel().getValueAt(i,"sk_code")
							);
						
						if( sk_code.equalsIgnoreCase(sk_code_temp) )
						{// 不区分大小写
							MessageDialog.showErrorDlg(cardForm, "", "收款单号不能重复");
							cardForm.getBillCardPanel().getBillModel().setValueAt(null,e.getRow(),"sk_code");
							return;
						}
						
					}
					/**END*/
					
					StringBuffer querySQL_sk = 
						new StringBuffer(" select ")
								.append(" sk.pk_gatherbill ")	// 收款单pk
								.append(",max(sk.billno) ")		// 收款单号
								.append(",max(skb.customer) ")	// 客户
								.append(",sum(skb.money_cr) ")	// 收款金额
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
						 * 已开票金额
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
						
						UFDouble bckpje = skJe.sub(ykpje);	// 本次开票金额（剩余开票金额）  = 账单金额 - 已开票金额
						
						/**END*/
						
						// 本次开票金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								bckpje
							  , e.getRow()
							  , "sk_money"
						);
						// 剩余开票金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								bckpje
							  , e.getRow()
							  , "vbdef03"
						);
						// 收款单金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								skJe
							  , e.getRow()
							  , "vbdef01"
						);
						// 已开票金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								ykpje
							  , e.getRow()
							  , "vbdef02"
						);
						// 回写上 正确的收款单号
						cardForm.getBillCardPanel().getBillModel().setValueAt(	
								skCode
							  , e.getRow()
							  , "sk_code"
						);
						// 回写上 收款单pk
						cardForm.getBillCardPanel().getBillModel().setValueAt(	
								skPk
							  , e.getRow()
							  , "sk_pk"
						);
						// 回写上 表头客户
						cardForm.getBillCardPanel().getHeadItem("pk_customer").setValue(kh);
					}
					else
					{
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								null
							  , e.getRow()
							  , "sk_money"
						);
						MessageDialog.showErrorDlg(cardForm, "", "没有该帐单，请检查 收款单号是否正确。");
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
				
				// 将金额 叠加到 表头发票金额
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
				// 将金额 叠加到 表头发票金额
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
