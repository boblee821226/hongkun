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
	private ShowUpableBillForm cardForm;	// ��Ƭ����
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		try
		{
			if( "pk_area".equals( e.getKey() ) )
			{// ����
				this.fullOtherData(e);
			}
			else if( "pk_room".equals( e.getKey() ) )
			{// ����
				this.fullOtherData(e);
			}
			else if( "pk_sfxm".equals( e.getKey() ) )
			{// �շ���Ŀ
				this.fullOtherData(e);
			}
			else if( "pk_place".equals( e.getKey() ) )
			{// λ��
				this.fullOtherData(e);
			}
			else if( "bccb_num".equals( e.getKey() ) )
			{// ���γ�����
				// ��Ϊ���� �������㣬���� ������  ���γ����� С�� �ϴγ�����  �����
//				if( !this.checkBccb_num(e) )
//				{
//					return ;
//				}
				this.calc(e);
			}
			else if( "sccb_num".equals( e.getKey() ) )
			{// �ϴγ�����
				this.calc(e);
			}
			else if( "times".equals( e.getKey() ) )
			{// ����
				this.calc(e);
			}
			else if( "price".equals( e.getKey() ) )
			{// ����
				this.calc(e);
			}
			else if( "use_num".equals( e.getKey() ) )
			{// ����
				this.calc(e);
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * ���γ�������У��
	 * 1�����γ����� ����С�� �ϴγ�����
	 */
	private boolean checkBccb_num(CardBodyAfterEditEvent e){
		
		int row = e.getRow();
		// ���γ�����
		UFDouble bccb_num = PuPubVO.getUFDouble_ValueAsValue(
			this.getCardForm().getBillCardPanel().getBodyValueAt(row,"bccb_num")
		);
		// �ϴγ�����
		UFDouble sccb_num = PuPubVO.getUFDouble_ValueAsValue(
			this.getCardForm().getBillCardPanel().getBodyValueAt(row,"sccb_num")
		);
		
		if(bccb_num.compareTo(sccb_num)<0)
		{
			MessageDialog.showErrorDlg(this.getCardForm(), "", "���γ����� ����С�� �ϴγ�����");
			this.getCardForm().getBillCardPanel().setBodyValueAt(null,row,"bccb_num");
			return false;
		}
		
		return true;
	}
	
	/**
	 * ���� ������Ӧ�ɽ��
	 */
	private void calc(CardBodyAfterEditEvent e){
		try
		{
			int row = e.getRow();
			// ����
			UFDouble times = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"times")
			);
			// ����
			UFDouble price = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"price")
			);
			// ���γ�����
			UFDouble bccb_num = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"bccb_num")
			);
			// �ϴγ�����
			UFDouble sccb_num = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"sccb_num")
			);
			// ����
			UFDouble use_num = PuPubVO.getUFDouble_ValueAsValue(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"use_num")
			);
			// ���
			UFDouble use_mny = null;
			
			// ��� ���� Ϊ�� ���� ������ ���� �ϴΣ����� �ȼ��� ������
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
			
			// ������
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
	 * ������������
	 */
	private void fullOtherData(CardBodyAfterEditEvent e){
		try
		{
			int row = e.getRow();
			// ����
			String pk_area = PuPubVO.getString_TrimZeroLenAsNull(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"pk_area")
			);
			// ����
			String pk_room = PuPubVO.getString_TrimZeroLenAsNull(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"pk_room")
			);
			// �շ���Ŀ
			String pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(
//				this.getCardForm().getBillCardPanel().getBillModel().getValueAt(row,"pk_sfxm")	// ������ʾ����
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"pk_sfxm")	// ʵ�ʴ��ֵ
			);
			// λ��
			String pk_place = PuPubVO.getString_TrimZeroLenAsNull(
				this.getCardForm().getBillCardPanel().getBodyValueAt(row,"pk_place")
			);
			// ��֯
			String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
					this.getCardForm().getBillCardPanel().getHeadItem("pk_org").getValueObject()
				);
			
			if(pk_area==null || pk_room==null || pk_sfxm==null || pk_place==null || pk_org==null)
			{
				return;
			}
			
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" lrb.pk_cust ")	// �ͻ�
						.append(",lrb.times ")		// ����
						.append(",lrb.price ")		// ����
						.append(",lr.dbilldate ")	// �ϴγ�������
						.append(",lrb.bccb_num ")	// �ϴγ�����
						.append(" from hk_zulin_sdflr lr ")
						.append(" inner join hk_zulin_sdflr_b lrb on lr.pk_hk_zulin_sdflr = lrb.pk_hk_zulin_sdflr ")
						.append(" where lr.dr=0 and lrb.dr=0 ")
						.append(" and lr.ibillstatus = 1 ")	// ���̬
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
						.append(" 	and lr.ibillstatus = 1 ")	// ���̬
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
