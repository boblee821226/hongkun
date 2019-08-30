package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.handler;

import java.util.ArrayList;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.PUB_kaipiao;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class KpCardBodyAfterEditEvent implements IAppEventHandler<CardBodyAfterEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		if( "ka_code".equals( e.getKey() ) )	// 卡号
		{
			try {
				
				int row = e.getRow();	// 所在行
				String ka_code = PuPubVO.getString_TrimZeroLenAsNull(e.getValue());	// 卡号
				if(ka_code==null)
				{
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "ka_code");	// 卡号
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "fpje");		// 发票金额
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "zqkpje");		// 之前可开票总额
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "kkpze");		// 可开票总额
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "vbdef03");	// 转卡金额
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "ka_pk");		// 卡pk
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "kaxing_code");	// 卡型code
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "kaxing_name");	// 卡型name
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "kaxing_pk");		// 卡型pk
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "vbdef02");		// 发卡店
				}
				else
				{
					
					String ka_code_str = "'" + ka_code.toUpperCase() + "'";
					
					KaipiaoqueryBillVO[] queryBillVO = PUB_kaipiao.bbcx_data(ka_code_str, null,false,"");
					
					if( queryBillVO.length>0 )
					{
						KaipiaoqueryHVO vo = queryBillVO[0].getParentVO();
						
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKa_code(), row, "ka_code");	// 卡号
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getSykpje(), row, "fpje");		// 发票金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getYkpze(), row, "zqkpje");	// 之前可开票总额
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKkpze(), row, "kkpze");		// 可开票总额
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getVdef03(), row, "vbdef03");	// 转卡金额
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKa_pk(), row, "ka_pk");		// 卡pk
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKaxing_code(), row, "kaxing_code");	// 卡型code
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKaxing_name(), row, "kaxing_name");	// 卡型name
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKaxing_pk(), row, "kaxing_pk");		// 卡型pk
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getVdef02(), row, "vbdef02");			// 发卡店
					}
				}
				
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			
		}
		else if( "fpje".equals( e.getKey() ) )	// 开票金额
		{
			int rowCount = cardForm.getBillCardPanel().getRowCount();
			UFDouble total = UFDouble.ZERO_DBL;
			String fpzt = "正常";	// 发票状态： 正常、错误
			for( int i=0;i<rowCount;i++ )
			{
				UFDouble kp_je = PuPubVO.getUFDouble_NullAsZero( cardForm.getBillCardPanel().getBillModel().getValueAt(i, "fpje") );
				total = total.add( kp_je );
				
				// 如果 发票金额 + 之前开票金额 + 转卡 > 可开票总额， 则为 错误
				// zeroifnull(fpje)+zeroifnull(zqkpje)+zeroifnull(vbdef03)>zeroifnull(kkpze)
				if(
						kp_je
				   .add(PuPubVO.getUFDouble_NullAsZero( cardForm.getBillCardPanel().getBillModel().getValueAt(i, "zqkpje") ))
//				   .add(PuPubVO.getUFDouble_NullAsZero( cardForm.getBillCardPanel().getBillModel().getValueAt(i, "vbdef03") ))
				   .compareTo( PuPubVO.getUFDouble_NullAsZero( cardForm.getBillCardPanel().getBillModel().getValueAt(i, "kkpze") ) )
				   >0
				)
				{
					fpzt = "错误";
				}
			}
			
			cardForm.getBillCardPanel().setHeadItem("fpje", total);
			cardForm.getBillCardPanel().setHeadItem("vdef02", fpzt);	// 发票状态
			
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
