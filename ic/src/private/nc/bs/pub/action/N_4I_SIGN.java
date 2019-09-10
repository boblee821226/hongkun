package nc.bs.pub.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.fa.prv.ITransasset;
import nc.itf.uap.pf.IPfExchangeService;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.am.common.TransportBillVO;
import nc.vo.fa.transasset.TransassetVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ��ע������������ⵥ��ǩ�� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-6-10)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4I_SIGN extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

	private Hashtable m_keyHas = null;

	/**
	 * N_4I_SIGN ������ע�⡣
	 */
	public N_4I_SIGN() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			nc.vo.ic.m4i.entity.GeneralOutVO[] outVOs = (nc.vo.ic.m4i.entity.GeneralOutVO[]) getVos();
			nc.vo.ic.m4i.entity.GeneralOutVO[] retunVos = new nc.vo.ic.m4i.entity.GeneralOutVO[outVOs.length];
			nc.itf.ic.m4i.IGeneralOutMaintain service = nc.bs.framework.common.NCLocator
					.getInstance().lookup(
							nc.itf.ic.m4i.IGeneralOutMaintain.class);
			for (int i = 0; i < outVOs.length; i++) {
				retunVos[i] = service
						.sign(new nc.vo.ic.m4i.entity.GeneralOutVO[] { outVOs[i] })[0];
			}
			// ����������ⵥ��ת�̵�
			creatHJFrom4i(retunVos);

			return retunVos;
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
	 * ����������ⵥǩ���� ֱ������ �̶��ʲ���ת�̵�
	 * 
	 * @author wll
	 * @throws BusinessException
	 * */
	private void creatHJFrom4i(nc.vo.ic.m4i.entity.GeneralOutVO[] billvos)
			throws BusinessException {
		// VOת��������
		IPfExchangeService iPfExchangeService = (IPfExchangeService) NCLocator
				.getInstance().lookup(IPfExchangeService.class);
		BaseDAO dao = new BaseDAO();

		nc.vo.ic.m4i.entity.GeneralOutVO[] billvos2 = billvos.clone();
		// ת�̵�list����
		// ArrayList<nc.vo.fa.transasset.TransassetVO> transassetvo_list = new
		// ArrayList<nc.vo.fa.transasset.TransassetVO>();

		for (nc.vo.ic.m4i.entity.GeneralOutVO billvo : billvos2) {
			/******* �������Ϲ��� *********/
			nc.vo.ic.m4i.entity.GeneralOutBodyVO[] bodyvos = billvo.getBodys();
			String pk_org = billvo.getParentVO().getPk_org();
			// ��������pk����
			ArrayList list = new ArrayList<String>();
			for (nc.vo.ic.m4i.entity.GeneralOutBodyVO bvo : bodyvos) {
				list.add(bvo.getCmaterialvid());
			}
			// ��ѯ���ϡ�ʵ�����ϼ�ֵ����ģʽ
			String sql = "select pk_material "
					+ ",(case materialvaluemgt when 1 then '�������' when 2 then '�̶��ʲ�' when 3 then '����' else '����' end)  wltype "
					+ " from bd_materialfi " + " where pk_material in ( '' ";
			for (int i = 0; i < list.size(); i++) {
				sql = sql + ",'" + list.get(i) + "'";
			}
			sql += " ) and pk_org = (select pk_financeorg "
					+ " from org_stockorg " + " where pk_stockorg = '" + pk_org
					+ "') ";

			ArrayList<Object[]> retObj = (ArrayList<Object[]>) dao
					.executeQuery(sql, new ArrayListProcessor());
			if (retObj == null || retObj.size() < 1 || retObj.get(0) == null) {
				continue;
			}
			// ���ϡ���ʵ�����ϼ�ֵ����ģʽ�� ���չ�ϵ
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			for (int i = 0; i < retObj.size(); i++) {
				Object[] object = retObj.get(i);
				map.put(object[0], object[1]);
			}

			// ���Ϲ���
			ArrayList<nc.vo.ic.m4i.entity.GeneralOutBodyVO> list_bvo = new ArrayList<nc.vo.ic.m4i.entity.GeneralOutBodyVO>();
			for (nc.vo.ic.m4i.entity.GeneralOutBodyVO bvo : bodyvos) {
				String pk_material = bvo.getCmaterialvid();
				if ("�̶��ʲ�".equals(map.get(pk_material))) {
					list_bvo.add(bvo);
				}
			}
			// ��������ȫ�� ������ �̶��ʲ��� ���Ƶ�
			if (list_bvo == null || list_bvo.size() < 1
					|| list_bvo.get(0) == null) {
				continue;
			}
			// �����к� ��������
			int row = 10;
			for (nc.vo.ic.m4i.entity.GeneralOutBodyVO bvo : list_bvo) {
				bvo.setCrowno(String.valueOf(row));
				row += 10;
			}
			// �µı���VO
			nc.vo.ic.m4i.entity.GeneralOutBodyVO[] bvo_new = list_bvo
					.toArray(new nc.vo.ic.m4i.entity.GeneralOutBodyVO[0]);
			billvo.setChildrenVO(bvo_new);
			// ִ��ת������
			TransassetVO retobj = (TransassetVO) iPfExchangeService
					.runChangeData("4I", "HJ-02", billvo, null);
			// transassetvo_list.add(retobj);
			// ����ת�̵�
			ITransasset iTransasset = (ITransasset) NCLocator.getInstance()
					.lookup(ITransasset.class.getName());
			TransportBillVO transportbillvo = iTransasset.insert(null, retobj);
		}

	}
}