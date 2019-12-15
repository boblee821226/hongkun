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
	private ShowUpableBillForm cardForm;	// ��Ƭ����
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		if( "ka_code".equals( e.getKey() ) )	// ����
		{
			try {
				
				int row = e.getRow();	// ������
				String ka_code = PuPubVO.getString_TrimZeroLenAsNull(e.getValue());	// ����
				if(ka_code==null)
				{
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "ka_code");	// ����
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "fpje");		// ��Ʊ���
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "zqkpje");		// ֮ǰ�ɿ�Ʊ�ܶ�
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "kkpze");		// �ɿ�Ʊ�ܶ�
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "vbdef03");	// ת�����
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "ka_pk");		// ��pk
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "kaxing_code");	// ����code
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "kaxing_name");	// ����name
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "kaxing_pk");		// ����pk
					cardForm.getBillCardPanel().getBillModel().setValueAt(null, row, "vbdef02");		// ������
				}
				else
				{
					
					String ka_code_str = "'" + ka_code.toUpperCase() + "'";
					
					KaipiaoqueryBillVO[] queryBillVO = PUB_kaipiao.bbcx_data(ka_code_str, null,false,"");
					
					if( queryBillVO.length>0 )
					{
						KaipiaoqueryHVO vo = queryBillVO[0].getParentVO();
						
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKa_code(), row, "ka_code");	// ����
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getSykpje(), row, "fpje");		// ��Ʊ���
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getYkpze(), row, "zqkpje");	// ֮ǰ�ɿ�Ʊ�ܶ�
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKkpze(), row, "kkpze");		// �ɿ�Ʊ�ܶ�
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getVdef03(), row, "vbdef03");	// ת�����
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKa_pk(), row, "ka_pk");		// ��pk
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKaxing_code(), row, "kaxing_code");	// ����code
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKaxing_name(), row, "kaxing_name");	// ����name
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getKaxing_pk(), row, "kaxing_pk");		// ����pk
						cardForm.getBillCardPanel().getBillModel().setValueAt(vo.getVdef02(), row, "vbdef02");			// ������
					}
				}
				
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			
		}
		else if( "fpje".equals( e.getKey() ) )	// ��Ʊ���
		{
			int rowCount = cardForm.getBillCardPanel().getRowCount();
			UFDouble total = UFDouble.ZERO_DBL;
			String fpzt = "����";	// ��Ʊ״̬�� ����������
			for( int i=0;i<rowCount;i++ )
			{
				UFDouble kp_je = PuPubVO.getUFDouble_NullAsZero( cardForm.getBillCardPanel().getBillModel().getValueAt(i, "fpje") );
				total = total.add( kp_je );
				
				// ��� ��Ʊ��� + ֮ǰ��Ʊ��� + ת�� > �ɿ�Ʊ�ܶ ��Ϊ ����
				// zeroifnull(fpje)+zeroifnull(zqkpje)+zeroifnull(vbdef03)>zeroifnull(kkpze)
				if(
						kp_je
				   .add(PuPubVO.getUFDouble_NullAsZero( cardForm.getBillCardPanel().getBillModel().getValueAt(i, "zqkpje") ))
//				   .add(PuPubVO.getUFDouble_NullAsZero( cardForm.getBillCardPanel().getBillModel().getValueAt(i, "vbdef03") ))
				   .compareTo( PuPubVO.getUFDouble_NullAsZero( cardForm.getBillCardPanel().getBillModel().getValueAt(i, "kkpze") ) )
				   >0
				)
				{
					fpzt = "����";
				}
			}
			
			cardForm.getBillCardPanel().setHeadItem("fpje", total);
			cardForm.getBillCardPanel().setHeadItem("vdef02", fpzt);	// ��Ʊ״̬
			
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
