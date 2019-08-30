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
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		try
		{
			if( "billno".equals( e.getKey() ) )	// 账单号
			{
				String billno = PuPubVO.getString_TrimZeroLenAsNull( e.getValue() );
				
				if( billno!=null )
				{
				
					billno = billno.toUpperCase();	// 转成大写 进行处理
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
						
						String billno_temp = PuPubVO.getString_TrimZeroLenAsNull(
							cardForm.getBillCardPanel().getBillModel().getValueAt(i,"billno")
							);
						
						if( billno.equalsIgnoreCase(billno_temp) )
						{// 不区分大小写
							MessageDialog.showErrorDlg(cardForm, "", "账单号不能重复");
							cardForm.getBillCardPanel().getBillModel().setValueAt(null,e.getRow(),"billno");
							return;
						}
						
					}
					/**END*/
					
					/** 
					 * 2、取已开票的金额
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
//						MessageDialog.showErrorDlg(cardForm, "", "该账单 已经开过票了，不能重复开票。");
//						cardForm.getBillCardPanel().getBillModel().setValueAt(null,e.getRow(),"billno");
//						return;
//					}
					/**END*/
					
					IHk_fp_billMaintain itf = NCLocator.getInstance().lookup(IHk_fp_billMaintain.class);
					ZhangdanBillVO[] result = itf.queryZhangdanBill(
							new String[]{billno}	// 账单号
						  , new String[]{pk_org}	// 公司
						  , null
					);
					
					if(result!=null && result.length>0)
					{
						ZhangdanHVO zdHVO = result[0].getParentVO();
						
						/**
						 *  检查 账单日期 是否符合规范
						 *  1、账单日期 必须大于等于  上线日期
						 *  2、账单日期 必须在6个月之内
						 */
						Integer KPQX = Pub_Param.getInstance().KPQX;
						UFDate  SXSJ = PuPubVO.getUFDate( Pub_Param.getInstance().SXSJ_MAP.get(pk_org) );
						
						if( SXSJ.compareTo(zdHVO.getDbilldate())>0 )
						{// 如果 生效时间  大于  账单业务日期， 则不能开票
							MessageDialog.showErrorDlg(cardForm, "", "只能开 "+(Pub_Param.getInstance().SXSJ_MAP.get(pk_org))+" 之后的账单。");
							return;
						}
						else if( WorkbenchEnvironment.getServerTime().getDate().compareTo( zdHVO.getDbilldate().getDateAfter( 30*KPQX ) ) > 0 )
						{// 如果 当前时间  大于  账单日期 6个月， 则不能开票
							MessageDialog.showErrorDlg(cardForm, "","已经超过开票期限"+KPQX+"个月，账单日期为"+(zdHVO.getDbilldate().toString().substring(0, 10))+"。");
							return;
						}
						
						/**END*/
						
						UFDouble billje = new UFDouble(0.0);
						
						// 账单金额 = 现金类，现金、支票、pos、  （ 微信、支付宝、应收账 ） 往来
						billje =  PuPubVO.getUFDouble_NullAsZero( zdHVO.getXianjin() )
							.add( PuPubVO.getUFDouble_NullAsZero( zdHVO.getPos() ) )
							.add( PuPubVO.getUFDouble_NullAsZero( zdHVO.getZhipiao() ) )
							.add( PuPubVO.getUFDouble_NullAsZero( zdHVO.getWanglai() ) )
						;
						
						/**
						 * 已开票金额
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
						
						UFDouble bckpje = billje.sub(ykpje);	// 本次开票金额（剩余开票金额）  = 账单金额 - 已开票金额
						
						/**END*/
						
						// 本次开票金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								bckpje
							  , e.getRow()
							  , "billje"
						);
						// 剩余开票金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								bckpje
							  , e.getRow()
							  , "vbdef03"
						);
						// 账单金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								billje
							  , e.getRow()
							  , "vbdef01"
						);
						// 已开票金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(
								ykpje
							  , e.getRow()
							  , "vbdef02"
						);
						
						cardForm.getBillCardPanel().getBillModel().setValueAt(	// 回写上 正确的账单号
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
						MessageDialog.showErrorDlg(cardForm, "", "没有该帐单，请检查 账单号是否正确。");
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
							  cardForm.getBillCardPanel().getBillModel().getValueAt(i,"billje") 
							) 
						  );
				}
				cardForm.getBillCardPanel().getHeadItem("fpje").setValue(fpje);
				
			}
			
			else if( "billje".equals( e.getKey() ) )
			{
				// 将金额 叠加到 表头发票金额
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
