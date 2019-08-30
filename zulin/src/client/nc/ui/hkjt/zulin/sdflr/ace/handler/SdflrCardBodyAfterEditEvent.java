package nc.ui.hkjt.zulin.sdflr.ace.handler;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class SdflrCardBodyAfterEditEvent implements IAppEventHandler<CardBodyAfterEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		try
		{
			if( "pk_area".equals( e.getKey() ) )
			{// 区域
				this.fullOtherData(e);
			}
			else if( "pk_room".equals( e.getKey() ) )
			{// 房号
				this.fullOtherData(e);
			}
			else if( "pk_sfxm".equals( e.getKey() ) )
			{// 收费项目
				this.fullOtherData(e);
			}
			else if( "pk_place".equals( e.getKey() ) )
			{// 位置
				this.fullOtherData(e);
			}
			else if( "bccb_num".equals( e.getKey() ) )
			{// 本次抄表数
				// 因为存在 表数归零，所以 存在这  本次抄表数 小于 上次抄表数  的情况
//				if( !this.checkBccb_num(e) )
//				{
//					return ;
//				}
				this.calc(e);
			}
			else if( "sccb_num".equals( e.getKey() ) )
			{// 上次抄表数
				this.calc(e);
			}
			else if( "times".equals( e.getKey() ) )
			{// 倍数
				this.calc(e);
			}
			else if( "price".equals( e.getKey() ) )
			{// 单价
				this.calc(e);
			}
			else if( "use_num".equals( e.getKey() ) )
			{// 用量
				this.calc(e);
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 本次抄表数的校验
	 * 1、本次抄表数 不能小于 上次抄表数
	 */
	private boolean checkBccb_num(CardBodyAfterEditEvent e){
		
		int row = e.getRow();
		// 本次抄表数
		UFDouble bccb_num = PuPubVO.getUFDouble_ValueAsValue(
			this.getCardForm().getBillCardPanel().getBodyValueAt(row,"bccb_num")
		);
		// 上次抄表数
		UFDouble sccb_num = PuPubVO.getUFDouble_ValueAsValue(
			this.getCardForm().getBillCardPanel().getBodyValueAt(row,"sccb_num")
		);
		
		if(bccb_num.compareTo(sccb_num)<0)
		{
			MessageDialog.showErrorDlg(this.getCardForm(), "", "本次抄表数 不能小于 上次抄表数");
			this.getCardForm().getBillCardPanel().setBodyValueAt(null,row,"bccb_num");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 计算 用量、应缴金额
	 */
	private void calc(CardBodyAfterEditEvent e){
		try
		{
			int row = e.getRow();
			// 倍数
			UFDouble times = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"times")
			);
			// 单价
			UFDouble price = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"price")
			);
			// 本次抄表数
			UFDouble bccb_num = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"bccb_num")
			);
			// 上次抄表数
			UFDouble sccb_num = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"sccb_num")
			);
			// 用量
			UFDouble use_num = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"use_num")
			);
			// 金额
			UFDouble use_mny = null;
			
			// 如果 用量 为空 或者 （本次 大于 上次），则 先计算 用量。
			if( use_num==null 
			 || (bccb_num!=null && sccb_num!=null && bccb_num.compareTo(sccb_num) >= 0 )
			)
			{
				if(
					bccb_num!=null && sccb_num!=null
				 && bccb_num.compareTo(sccb_num) >= 0 
				)
				{
					use_num = bccb_num.sub(sccb_num);
				}
			}
			
			// 计算金额
			if( use_num!=null && times!=null && price!=null )
			{
				use_mny = use_num.multiply(times).multiply(price);
			}
			
			this.getCardForm().getBillCardPanel().getBillModel().setValueAt(use_num,row,"use_num");
			this.getCardForm().getBillCardPanel().getBillModel().setValueAt(use_mny,row,"use_mny");
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 带出其它数据
	 */
	private void fullOtherData(CardBodyAfterEditEvent e){
		try
		{
			int row = e.getRow();
			// 区域
			String pk_area = PuPubVO.getString_TrimZeroLenAsNull(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"pk_area")
			);
			// 房号
			String pk_room = PuPubVO.getString_TrimZeroLenAsNull(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"pk_room")
			);
			// 收费项目
			String pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(
//				this.getCardForm().getBillCardPanel().getBillModel().getValueAt(row,"pk_sfxm")	// 界面显示内容
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"pk_sfxm")	// 实际存的值
			);
			// 位置
			String pk_place = PuPubVO.getString_TrimZeroLenAsNull(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"pk_place")
			);
			// 组织
			String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
					this.getCardForm().getBillCardPanel().getHeadItem("pk_org").getValueObject()
				);
			
			if(pk_area==null || pk_room==null || pk_sfxm==null || pk_place==null || pk_org==null)
			{
				return;
			}
			
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" lrb.pk_cust ")	// 客户
						.append(",lrb.times ")		// 倍数
						.append(",lrb.price ")		// 单价
						.append(",lr.dbilldate ")	// 上次抄表日期
						.append(",lrb.bccb_num ")	// 上次抄表数
						.append(" from hk_zulin_sdflr lr ")
						.append(" inner join hk_zulin_sdflr_b lrb on lr.pk_hk_zulin_sdflr = lrb.pk_hk_zulin_sdflr ")
						.append(" where lr.dr=0 and lrb.dr=0 ")
						.append(" and lr.ibillstatus = 1 ")	// 审核态
						.append(" and lr.pk_org   = '"+pk_org+"' ")
						.append(" and lrb.pk_area = '"+pk_area+"' ")
						.append(" and lrb.pk_room = '"+pk_room+"' ")
						.append(" and lrb.pk_sfxm = '"+pk_sfxm+"' ")
						.append(" and lrb.pk_place= '"+pk_place+"' ")
						.append(" and lr.dbilldate = ")
						.append(" ( ")
						.append(" 	select max(lr.dbilldate) ")
						.append(" 	from hk_zulin_sdflr lr ")
						.append(" 	inner join hk_zulin_sdflr_b lrb on lr.pk_hk_zulin_sdflr = lrb.pk_hk_zulin_sdflr ")
						.append(" 	where lr.dr=0 and lrb.dr=0 ")
						.append(" 	and lr.ibillstatus = 1 ")	// 审核态
						.append(" 	and lr.pk_org   = '"+pk_org+"' ")
						.append(" 	and lrb.pk_area = '"+pk_area+"' ")
						.append(" 	and lrb.pk_room = '"+pk_room+"' ")
						.append(" 	and lrb.pk_sfxm = '"+pk_sfxm+"' ")
						.append(" 	and lrb.pk_place= '"+pk_place+"' ")
						.append(" ) ")
			;
			
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
			if(list!=null && list.size()>0)
			{
				Object[] obj = (Object[])list.get(0);
				
				String	  pk_cust = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
				UFDouble	times = PuPubVO.getUFDouble_ValueAsValue(obj[1]);
				UFDouble	price = PuPubVO.getUFDouble_ValueAsValue(obj[2]);
				UFDate  sccb_date = PuPubVO.getUFDate(obj[3]);
				UFDouble sccb_num = PuPubVO.getUFDouble_ValueAsValue(obj[4]);
				
				this.getCardForm().getBillCardPanel().getBillModel().setValueAt(pk_cust,row,"pk_cust");
				this.getCardForm().getBillCardPanel().getBillModel().setValueAt(times,row,"times");
				this.getCardForm().getBillCardPanel().getBillModel().setValueAt(price,row,"price");
				this.getCardForm().getBillCardPanel().getBillModel().setValueAt(sccb_date,row,"sccb_date");
				this.getCardForm().getBillCardPanel().getBillModel().setValueAt(sccb_num,row,"sccb_num");
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
