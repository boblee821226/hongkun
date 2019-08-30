package nc.ui.hkjt.zulin.znjjm.ace.handler;

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

public class ZnjjmCardBodyAfterEditEvent implements IAppEventHandler<CardBodyAfterEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		try
		{
			if( "jm_mny".equals( e.getKey() ) )
			{// 减免金额
				/**
				 * 1、如果减免金额 大于 滞纳金，则 自动等于 滞纳金。
				 */
				int row = e.getRow();
				UFDouble jm_mny = PuPubVO.getUFDouble_NullAsZero(e.getValue());
				UFDouble yq_mny = PuPubVO.getUFDouble_NullAsZero(
						getCardForm().getBillCardPanel().getBodyValueAt(e.getRow(), "yq_mny") );
				
				if(jm_mny.compareTo(yq_mny)>0){
					getCardForm().getBillCardPanel().setBodyValueAt(yq_mny, row, "jm_mny");
				}
				
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
