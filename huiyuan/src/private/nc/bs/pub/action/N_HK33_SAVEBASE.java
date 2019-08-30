package nc.bs.pub.action;

import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.framework.common.NCLocator;
import nc.bs.hkjt.huiyuan.kaipiaoinfo.plugin.bpplugin.Hy_kaipiaoinfoPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.hkjt.IHy_kaipiaoinfoMaintain;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoHVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_HK33_SAVEBASE extends AbstractPfAction<KaipiaoinfoBillVO> {

	@Override
	protected CompareAroundProcesser<KaipiaoinfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaipiaoinfoBillVO> processor = null;
		KaipiaoinfoBillVO[] clientFullVOs = (KaipiaoinfoBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<KaipiaoinfoBillVO>(
					Hy_kaipiaoinfoPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<KaipiaoinfoBillVO>(
					Hy_kaipiaoinfoPluginPoint.SCRIPT_INSERT);
		}
		// TODO �ڴ˴����ǰ�����
		IRule<KaipiaoinfoBillVO> rule = null;

		return processor;
	}

	@Override
	protected KaipiaoinfoBillVO[] processBP(Object userObj,
			KaipiaoinfoBillVO[] clientFullVOs, KaipiaoinfoBillVO[] originBills) {

		KaipiaoinfoBillVO[] bills = null;
		try {
			
			/**
			 * 2018��8��10��15:29:28
			 * ����ǰУ�飬��Ʊ����ĳ���  ��  �����ĳ��� �Ƿ�һ��
			 * 2018��8��17��10:40:52
			 * ��Ʊ�ŵĲ��� ��� ������ȵġ��ö��ŷָ���
			 */
			for(KaipiaoinfoBillVO billVO : clientFullVOs)
			{
				KaipiaoinfoHVO hVO = billVO.getParentVO();
				String fphm = hVO.getFph();
				
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
			
			IHy_kaipiaoinfoMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kaipiaoinfoMaintain.class);
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
