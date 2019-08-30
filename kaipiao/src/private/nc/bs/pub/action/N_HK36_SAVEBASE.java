package nc.bs.pub.action;

import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.fapiao_sk.bill.plugin.bpplugin.Hk_fp_sk_billPluginPoint;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.vo.hkjt.fapiao.bill.BillFpHVO;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpHVO;
import nc.itf.hkjt.IHk_fp_sk_billMaintain;

public class N_HK36_SAVEBASE extends AbstractPfAction<BillSkFpBillVO> {

	@Override
	protected CompareAroundProcesser<BillSkFpBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillSkFpBillVO> processor = null;
		BillSkFpBillVO[] clientFullVOs = (BillSkFpBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<BillSkFpBillVO>(
					Hk_fp_sk_billPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<BillSkFpBillVO>(
					Hk_fp_sk_billPluginPoint.SCRIPT_INSERT);
		}
		// TODO �ڴ˴����ǰ�����
		IRule<BillSkFpBillVO> rule = null;

		return processor;
	}

	@Override
	protected BillSkFpBillVO[] processBP(Object userObj,
			BillSkFpBillVO[] clientFullVOs, BillSkFpBillVO[] originBills) {

		BillSkFpBillVO[] bills = null;
		try {
			
			/**
			 * 2018��8��10��10:17:54
			 * ����ǰУ�飬��Ʊ����ĳ���  ��  �����ĳ��� �Ƿ�һ��
			 * 2018��8��17��10:40:52
			 * ��Ʊ�ŵĲ��� ��� ������ȵġ��ö��ŷָ���
			 */
			for(BillSkFpBillVO billVO : clientFullVOs)
			{
				BillSkFpHVO hVO = billVO.getParentVO();
				String fphm = hVO.getFphm();
				
//				Integer fpLength = PuPubVO.getInteger_NullAs( hVO.getVdef10() , 18 );
//				
//				if(fphm.length()!=fpLength)
//				{
//					throw new BusinessException("��Ʊ����ĳ��ȱ���Ϊ��"+fpLength+"��λ��");
//				}
				
				String[] str_fpLength = hVO.getVdef10().split(",");
				HashMap<String,String> MAP_fpLength = new HashMap<String,String>();
				for(int i=0;i<str_fpLength.length;i++)
				{
					MAP_fpLength.put(str_fpLength[i], str_fpLength[i]);
				}
				
				String fpLength = ""+fphm.length();
				
				if( !MAP_fpLength.containsKey(fpLength) )
				{
					throw new BusinessException("��Ʊ����ĳ��ȱ���Ϊ��"+hVO.getVdef10()+"��λ��");
				}
				
			}
			/***END***/
			
			IHk_fp_sk_billMaintain operator = NCLocator.getInstance()
					.lookup(IHk_fp_sk_billMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
