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
 * 备注：库存其它出库单的签字 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-6-10)
 * 
 * @author 平台脚本生成
 */
public class N_4I_SIGN extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

	private Hashtable m_keyHas = null;

	/**
	 * N_4I_SIGN 构造子注解。
	 */
	public N_4I_SIGN() {
		super();
	}

	/*
	 * 备注：平台编写规则类 接口执行类
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
			// 库存其他出库单推转固单
//			creatHJFrom4i(retunVos);	// 2019年9月10日10点12分 将 推 转固的功能 去掉

			return retunVos;
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
		}
		return null;
	}

	/*
	 * 备注：平台编写原始脚本
	 */
	@Override
	public String getCodeRemark() {
		return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4008003_0", "04008003-0108")/* @res "不支持修改脚本" */;
	}

	/*
	 * 备注：设置脚本变量的HAS
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
	 * 库存其他出库单签字完 直接生产 固定资产：转固单
	 * 
	 * @author wll
	 * @throws BusinessException
	 * */
	private void creatHJFrom4i(nc.vo.ic.m4i.entity.GeneralOutVO[] billvos)
			throws BusinessException {
		// VO转换服务类
		IPfExchangeService iPfExchangeService = (IPfExchangeService) NCLocator
				.getInstance().lookup(IPfExchangeService.class);
		BaseDAO dao = new BaseDAO();

		nc.vo.ic.m4i.entity.GeneralOutVO[] billvos2 = billvos.clone();
		// 转固单list集合
		// ArrayList<nc.vo.fa.transasset.TransassetVO> transassetvo_list = new
		// ArrayList<nc.vo.fa.transasset.TransassetVO>();

		for (nc.vo.ic.m4i.entity.GeneralOutVO billvo : billvos2) {
			/******* 表体物料过滤 *********/
			nc.vo.ic.m4i.entity.GeneralOutBodyVO[] bodyvos = billvo.getBodys();
			String pk_org = billvo.getParentVO().getPk_org();
			// 表体物料pk集合
			ArrayList list = new ArrayList<String>();
			for (nc.vo.ic.m4i.entity.GeneralOutBodyVO bvo : bodyvos) {
				list.add(bvo.getCmaterialvid());
			}
			// 查询物料、实物物料价值管理模式
			String sql = "select pk_material "
					+ ",(case materialvaluemgt when 1 then '存货核算' when 2 then '固定资产' when 3 then '费用' else '其他' end)  wltype "
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
			// 物料——实物物料价值管理模式， 对照关系
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			for (int i = 0; i < retObj.size(); i++) {
				Object[] object = retObj.get(i);
				map.put(object[0], object[1]);
			}

			// 物料过滤
			ArrayList<nc.vo.ic.m4i.entity.GeneralOutBodyVO> list_bvo = new ArrayList<nc.vo.ic.m4i.entity.GeneralOutBodyVO>();
			for (nc.vo.ic.m4i.entity.GeneralOutBodyVO bvo : bodyvos) {
				String pk_material = bvo.getCmaterialvid();
				if ("固定资产".equals(map.get(pk_material))) {
					list_bvo.add(bvo);
				}
			}
			// 表体物料全部 不属于 固定资产， 不推单
			if (list_bvo == null || list_bvo.size() < 1
					|| list_bvo.get(0) == null) {
				continue;
			}
			// 表体行号 重新排序
			int row = 10;
			for (nc.vo.ic.m4i.entity.GeneralOutBodyVO bvo : list_bvo) {
				bvo.setCrowno(String.valueOf(row));
				row += 10;
			}
			// 新的表体VO
			nc.vo.ic.m4i.entity.GeneralOutBodyVO[] bvo_new = list_bvo
					.toArray(new nc.vo.ic.m4i.entity.GeneralOutBodyVO[0]);
			billvo.setChildrenVO(bvo_new);
			// 执行转换规则
			TransassetVO retobj = (TransassetVO) iPfExchangeService
					.runChangeData("4I", "HJ-02", billvo, null);
			// transassetvo_list.add(retobj);
			// 保存转固单
			ITransasset iTransasset = (ITransasset) NCLocator.getInstance()
					.lookup(ITransasset.class.getName());
			TransportBillVO transportbillvo = iTransasset.insert(null, retobj);
		}

	}
}