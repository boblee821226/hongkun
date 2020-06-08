package nc.bs.pub.action;

import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.bkfyft.plugin.bpplugin.Hk_zulin_yuebaoPluginPoint;
import nc.vo.hkjt.zulin.yuebao.YuebaoBVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoHVO;
import nc.itf.hkjt.IHk_zulin_yuebaoMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;

public class N_HK43_SAVEBASE extends AbstractPfAction<YuebaoBillVO> {

	@Override
	protected CompareAroundProcesser<YuebaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<YuebaoBillVO> processor = null;
		YuebaoBillVO[] clientFullVOs = (YuebaoBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<YuebaoBillVO>(
					Hk_zulin_yuebaoPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<YuebaoBillVO>(
					Hk_zulin_yuebaoPluginPoint.SCRIPT_INSERT);
		}
		// TODO �ڴ˴����ǰ�����
		IRule<YuebaoBillVO> rule = null;

		return processor;
	}

	@Override
	protected YuebaoBillVO[] processBP(Object userObj,
			YuebaoBillVO[] clientFullVOs, YuebaoBillVO[] originBills) {

		YuebaoBillVO[] bills = null;
		try {
			
			/**
			 * HK 2019��11��12��18��36��
			 * ����У��
			 * 1�����岻��Ϊ��
			 * 2��ͬ��֯ͬ�ڼ�  ֻ�ܴ���һ�ŵ���
			 * 3������ �Ƿ�ӡ��˰�� ���� ��2020��2��5��12:08:58��
			 */
			for(int i=0;i<clientFullVOs.length;i++)
			{
				YuebaoHVO    hvo = clientFullVOs[i].getParentVO();
				YuebaoBVO[] bvos = (YuebaoBVO[])clientFullVOs[i].getChildrenVO();
				
				hvo.setVbilltypecode("HK43");
				
				if (bvos == null || bvos.length <= 0){
					throw new BusinessException("�������ݲ���Ϊ��");
				}
				
				String pk = hvo.getPk_hk_zulin_yuebao();	// pk
				String pk_org = hvo.getPk_org();	// ��֯
				String qijian = hvo.getVdef01();	// �ڼ�
				String isYhs = hvo.getVdef02();	// �Ƿ�ӡ��˰
				if (null == isYhs
				 || "~".equals(isYhs)
				) {
					isYhs = "N";
				}
				String isZrpz = hvo.getVdef03();	// �Ƿ�����ƾ֤
				if (null == isZrpz
				 || "~".equals(isZrpz)
				) {
					isZrpz = "N";
				}
				
				if (pk == null) pk="null";
				
				BaseDAO dao = new BaseDAO();
				StringBuffer querySQL = 
					new StringBuffer(" select y.pk_hk_zulin_yuebao ")
							.append(" from hk_zulin_yuebao y ")
							.append(" where y.dr=0 ")
							.append(" and y.vbilltypecode = 'HK43' ")
							.append(" and y.pk_org = '"+pk_org+"' ")
							.append(" and y.vdef01 = '"+qijian+"' ")
							.append(" and y.pk_hk_zulin_yuebao != '"+pk+"' ")
							// ӡ��˰
							.append(" and replace(nvl(y.vdef02, 'N'), '~', 'N') = '").append(isYhs).append("' ")
							// ����ƾ֤
							.append(" and replace(nvl(y.vdef03, 'N'), '~', 'N') = '").append(isZrpz).append("' ")
				;
				List list = (List)dao.executeQuery(querySQL.toString(),new ArrayListProcessor());
				if(list!=null && list.size()>0)
				{
					throw new BusinessException("ͬ��֯ͬ�ڼ䣬���ܱ����ݷ��÷�̯��ӡ��˰��������ƾ֤����");
				}
				
			}
			/***END***/
			
			IHk_zulin_yuebaoMaintain operator = NCLocator.getInstance()
					.lookup(IHk_zulin_yuebaoMaintain.class);
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
