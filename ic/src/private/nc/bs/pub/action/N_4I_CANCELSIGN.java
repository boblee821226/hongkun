package nc.bs.pub.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.fa.service.IFAForSCMService;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ��ע������������ⵥ��ȡ��ǩ�� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-3-31)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4I_CANCELSIGN extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

	private Hashtable m_keyHas = null;

	/**
	 * N_4I_CANCELSIGN ������ע�⡣
	 */
	public N_4I_CANCELSIGN() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			nc.vo.ic.m4i.entity.GeneralOutVO[] inVOs = (nc.vo.ic.m4i.entity.GeneralOutVO[]) getVos();

			checkIsHJ(inVOs);
			return nc.bs.framework.common.NCLocator.getInstance()
					.lookup(nc.itf.ic.m4i.IGeneralOutMaintain.class)
					.cancelSign(inVOs);
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
		}
		return null;
	}

	/*
	 * ��ע��ƽ̨��дԭʼ�ű�
	 */
	@Override
	public String getCodeRemark() {
		return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4008003_0", "04008003-0108")/* @res "��֧���޸Ľű�" */;
	}

	/*
	 * ��ע�����ýű�������HAS
	 */
	private void setParameter(String key, Object val) {
		if (m_keyHas == null) {
			m_keyHas = new Hashtable();
		}
		if (val != null) {
			m_keyHas.put(key, val);
		}
	}

	/**
	 * ȡ��ǩ��ǰ��飺 �Ƿ�������� ���ݣ� ת�̵�
	 * 
	 * @throws BusinessException
	 * */
	private void checkIsHJ(nc.vo.ic.m4i.entity.GeneralOutVO[] inVOs)
			throws BusinessException {

		for (nc.vo.ic.m4i.entity.GeneralOutVO inVO : inVOs) {
			String pk_org = inVO.getParentVO().getPk_org();
			String bill_code = inVO.getParentVO().getVbillcode();

			BaseDAO dao = new BaseDAO();
			String sql = " select * from fa_transasset_b b "
					+ " where b.bill_type_src = '4I' "
					+ " and b.bill_code_source = '" + bill_code + "' "
					+ " and b.pk_org = '" + pk_org + "' "
					+ " and nvl(b.dr,0)=0 ";

			List list = (ArrayList<nc.vo.ic.m4i.entity.GeneralOutVO>) dao
					.executeQuery(sql, new BeanListProcessor(
							nc.vo.ic.m4i.entity.GeneralOutVO.class));

			// ��������ת�̵�
			if (list != null && list.size() > 0 && list.get(0) != null) {
				List<String> errBids = new ArrayList<String>();
				try {
					errBids = NCLocator
							.getInstance()
							.lookup(IFAForSCMService.class)
							.deleteFromPurchaseInbound(
									new nc.vo.ic.m4i.entity.GeneralOutVO[] { inVO });
				} catch (BusinessException e) {
					throw new BusinessException(e);
				}

				if (errBids != null && errBids.size() > 0
						&& errBids.get(0) != null) {
					throw new BusinessException("����ת�̵�ֻ������̬������ſ���ȡ��ǩ�֣�");
				}
			}
		}
	}
}