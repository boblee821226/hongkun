package nc.impl.erm.matterapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.er.util.BXBsUtil;
import nc.bs.erm.matterapp.common.ErmMatterAppConst;
import nc.bs.erm.matterapp.common.MatterAppQueryCondition;
import nc.bs.erm.util.CacheUtil;
import nc.bs.erm.util.ErUtil;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.arap.prv.IBXBillPrivate;
import nc.itf.erm.extendconfig.ErmExtendconfigInterfaceCenter;
import nc.itf.erm.mactrlschema.IErmMappCtrlBillQuery;
import nc.itf.erm.matterapp.IErmMatterAppBillQueryPrivate;
import nc.itf.erm.prv.IErmBsCommonService;
import nc.itf.fi.pub.Currency;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.erm.matterapp.IErmMatterAppBillQuery;
import nc.pubitf.rbac.IUserPubService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.arap.bx.util.BXConstans;
import nc.vo.arap.bx.util.BXStatusConst;
import nc.vo.er.djlx.DjLXVO;
import nc.vo.er.exception.ExceptionHandler;
import nc.vo.erm.matterapp.AggMatterAppVO;
import nc.vo.erm.matterapp.MaBillCombineUtil;
import nc.vo.erm.matterapp.MatterAppCloseStatusEnum;
import nc.vo.erm.matterapp.MatterAppVO;
import nc.vo.erm.matterapp.MatterViewVO;
import nc.vo.erm.matterapp.MtAppDetailVO;
import nc.vo.erm.matterappctrl.MtappbillpfVO;
import nc.vo.erm.util.VOUtils;
import nc.vo.fi.pub.SqlUtils;
import nc.vo.fipub.utils.SqlBuilder;
import nc.vo.fipub.utils.VOUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.trade.pub.IBillStatus;
import nc.vo.uap.rbac.constant.IRoleConst;
import nc.vo.util.AuditInfoUtil;

import org.apache.commons.lang.ArrayUtils;


public class ErmMatterAppBillQueryImpl implements IErmMatterAppBillQuery, IErmMatterAppBillQueryPrivate {

	/**
	 * ���뵥�����ѯ����
	 */
	private static final String Mtapptable_alis = MatterAppVO.getDefaultTableName() + ".";

	private IMDPersistenceQueryService getService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}

	@SuppressWarnings("unchecked")
	public String[] queryBillPksByWhere(MatterAppQueryCondition condVo) throws BusinessException {
		if (condVo == null) {
			return new String[] {};
		}
		StringBuffer whereSql = new StringBuffer(" 1 = 1 ");

		// ����Ȩ��
		Map<String, String> map = NCLocator.getInstance().lookup(IErmBsCommonService.class)
				.getPermissonOrgMapCall(condVo.getPk_user(), condVo.getNodeCode(), condVo.getPk_group());
		String[] permissionOrgs = map.values().toArray(new String[0]);
		whereSql.append(" and " + SqlUtils.getInStr(Mtapptable_alis + MatterAppVO.PK_ORG, permissionOrgs, true));
		
		//����
		if (condVo.getPk_group() != null) {
			whereSql.append(" and ").append(Mtapptable_alis + MatterAppVO.PK_GROUP + "='" + condVo.getPk_group() + "'");
		}

		String billMaker = NCLocator.getInstance().lookup(IUserPubService.class)
				.queryPsndocByUserid(condVo.getPk_user());
		StringBuffer billMakerSql = new StringBuffer();
		billMakerSql.append(" and (");
		billMakerSql.append(Mtapptable_alis + MatterAppVO.CREATOR + "='" + condVo.getPk_user() + "'");
		billMakerSql.append(" or ");
		billMakerSql.append(Mtapptable_alis + MatterAppVO.BILLMAKER + "='" + billMaker + "')");

		if (ErmMatterAppConst.MAPP_NODECODE_MN.equals(condVo.getNodeCode())) {// ����ڵ���¼��ڵ�����
			if (condVo.getPk_user() != null) {
				whereSql.append(" and (" + Mtapptable_alis
						+ "pk_mtapp_bill in (select wf.billid from pub_workflownote wf ");
				whereSql.append(" where er_mtapp_bill.pk_mtapp_bill= wf.billid and wf.checkman= '"
						+ condVo.getPk_user() + "' ");
				whereSql.append(" and wf.actiontype <> 'MAKEBILL' ) or ");// ��������������Ϊ������ʱ
				whereSql.append(Mtapptable_alis + MatterAppVO.APPROVER + "='" + condVo.getPk_user() + "' ");//������Ϊ����
				//���������������˻��������Ǳ���
				whereSql.append("or ("
						+ Mtapptable_alis
						+ "pk_mtapp_bill not in (select wf.billid  from pub_workflownote wf where er_mtapp_bill.pk_mtapp_bill= wf.billid ");
				whereSql.append(" and wf.checkman= '" + condVo.getPk_user() + "'  and wf.actiontype <> 'MAKEBILL')");
				whereSql.append(billMakerSql.toString());
				whereSql.append(" and er_mtapp_bill.apprstatus = " + IBillStatus.COMMIT + " )");// ��������������״̬Ϊ�ύ̬�ĵ���
				whereSql.append(")");

				DjLXVO[] djlxbms = CacheUtil.getValueFromCacheByWherePart(DjLXVO.class, " djdl='ma' and pk_group='"
						+ condVo.getPk_group() + "' order by djlxbm ");
				String[] tradeTypes = null;
				if (djlxbms != null && djlxbms.length > 0) {
					tradeTypes = VOUtil.getAttributeValues(djlxbms, "djlxbm");
				}

				if (condVo.isUser_approving()) { // �Ҵ�����
					String[] billPks = NCLocator.getInstance().lookup(IErmBsCommonService.class)
							.queryApprovedWFBillPksByCondition(null, tradeTypes, false);
					if (billPks != null && billPks.length > 0) {
						whereSql.append(" and " + SqlUtils.getInStr(MatterAppVO.PK_MTAPP_BILL, billPks, true));
					} else {
						whereSql.append(" and 1=0 ");
					}
				}

				if (condVo.isUser_approved()) { // ��������
					String[] billPks = NCLocator.getInstance().lookup(IErmBsCommonService.class)
							.queryApprovedWFBillPksByCondition(null, tradeTypes, true);

					if (billPks != null && billPks.length > 0) {
						whereSql.append(" and (" + SqlUtils.getInStr(MatterAppVO.PK_MTAPP_BILL, billPks, true));
						whereSql.append(" or approver = '" + condVo.getPk_user() + "')");
					} else {
						whereSql.append(" and approver = '" + condVo.getPk_user() + "' ");
					}
				}
			}
		} else if(ErmMatterAppConst.MAPP_NODECODE_QY.equals(condVo.getNodeCode())){//���ݲ�ѯ
			
		} else {
			whereSql.append(" and " + Mtapptable_alis + MatterAppVO.PK_TRADETYPE + " = '" + condVo.getPk_tradetype()
					+ "'");
			if (condVo.getPk_user() != null) {
				whereSql.append(billMakerSql.toString());
			}
		}

		if (condVo.getWhereSql() != null) {
			if(ErmMatterAppConst.MAPP_NODECODE_QY.equals(condVo.getNodeCode())){
				whereSql.append(" and " + ErUtil.getQueryNomalSql(condVo.getWhereSql()));//��ѯȥ������Ȩ��
			}else{
				whereSql.append(" and " + condVo.getWhereSql());
			}
		}

		whereSql.append(" order by " + Mtapptable_alis + MatterAppVO.BILLDATE + " desc, ");
		whereSql.append(Mtapptable_alis + MatterAppVO.BILLNO + " desc ");

		BaseDAO dao = new BaseDAO();

		Collection<MatterAppVO> c = dao.retrieveByClause(MatterAppVO.class, whereSql.toString(),
				new String[] { MatterAppVO.PK_MTAPP_BILL });

		if (c != null && c.size() > 0) {
			String[] pks = new String[c.size()];
			int i = 0;
			for (Object vo : c) {
				pks[i] = ((MatterAppVO) vo).getPrimaryKey();
				i++;
			}
			return pks;
		}
		return null;
	}

	@Override
	public AggMatterAppVO queryBillByPK(String pk) throws BusinessException {
		AggMatterAppVO[] aggVos = queryBillByPKs(new String[] { pk }, false);

		if (aggVos != null && aggVos.length > 0) {
			return aggVos[0];
		}

		return null;
	}

	public AggMatterAppVO[] queryBillByPKs(String[] pks) throws BusinessException {
		return queryBillByPKs(pks, false);
	}

	@Override
	public MatterAppVO[] getMtappByMthPk(String pkOrg, String begindate, String enddate) throws BusinessException {
		String sql = Mtapptable_alis + "pk_org=? and " + Mtapptable_alis + "billdate>=? and " + Mtapptable_alis
				+ "billdate<=?";
		SQLParameter param = new SQLParameter();
		param.addParam(pkOrg);
		param.addParam(begindate);
		param.addParam(enddate);
		@SuppressWarnings("unchecked")
		Collection<MatterAppVO> c = new BaseDAO().retrieveByClause(MatterAppVO.class, sql, param);
		if (c == null || c.isEmpty()) {
			return null;
		}
		return c.toArray(new MatterAppVO[] {});
	}

	@Override
	public AggMatterAppVO[] queryBillFromMtapp(String condition, String djlxbm, String pk_org, String pk_psndoc)
			throws BusinessException {
		String fixedCon = "";
		if (djlxbm.startsWith(BXConstans.JK_PREFIX)) {
			// �������������ܶ� > �ܵ�ִ���� + �ܵ�Ԥռ�� ��Total > totalExe + totalPre��
			fixedCon += " orig_amount > isnull(( SELECT sum(p.exe_amount+p.pre_amount) FROM er_mtapp_billpf p WHERE p.pk_mtapp_detail=er_mtapp_detail.pk_mtapp_detail GROUP BY p.pk_mtapp_detail ),0 ) ";
		} else if (djlxbm.startsWith(BXConstans.BX_PREFIX)) {
			// �����������������ܶ�>������ִ����+������Ԥռ�� ��Total > bxExe + bxPre��
			fixedCon += " orig_amount > isnull(( SELECT sum(p.exe_amount+p.pre_amount) FROM er_mtapp_billpf p WHERE p.pk_mtapp_detail=er_mtapp_detail.pk_mtapp_detail and p.pk_djdl='bx' GROUP BY p.pk_mtapp_detail ),0 ) ";
		}
		fixedCon += " and pk_tradetype in (select pk_tradetype from er_mtapp_cbill where src_tradetype = '";
		fixedCon += djlxbm;
		fixedCon += "' and pk_org in( '";
		fixedCon += pk_org + "','" + InvocationInfoProxy.getInstance().getGroupId() + "'))";
		if (pk_psndoc != null) {
			fixedCon += " and apply_dept in (";
			fixedCon += " select pk_dept from bd_psnjob where pk_psndoc ='";
			fixedCon += pk_psndoc + "'";
			fixedCon += " )";
		}
		fixedCon += " and effectstatus=1 and close_status=2 ";
		fixedCon += " and dr = 0  order by rowno ";
//		String whereSql = StringUtil.isEmpty(condition) ? fixedCon : condition + fixedCon;

		return queryBillFromMtappByWhere(djlxbm, fixedCon,condition);
	}

	private AggMatterAppVO[] queryBillFromMtappByWhere(String djlxbm, String bodySql,String headSql) throws MetaDataException,
			BusinessException {
		// ��ѯ���������ķ������뵥�ӱ�,���õ��������뵥����Ϊkey��map��
		@SuppressWarnings("unchecked")
		Collection<MtAppDetailVO> detailList = getService().queryBillOfVOByCond(MtAppDetailVO.class, bodySql, true);
		if (detailList.isEmpty()) {
			return null;
		}
		// �޸����뵥��ϸ�е����(��������ʾ)
		boolean isJkDjlx = djlxbm.startsWith(BXConstans.JK_PREFIX);
		boolean isBxDjlx = djlxbm.startsWith(BXConstans.BX_PREFIX);

		for (MtAppDetailVO detailvo : detailList) {
			UFDouble pf = UFDouble.ZERO_DBL;
			if (isJkDjlx) {
				// �Խ����������=MA�ܽ��-��ִ�У�JK+BX��-��Ԥռ��JK+BX��
				pf = getPfByMtappdetail(detailvo.getPk_mtapp_detail(), new String[] { BXConstans.BX_DJDL,
						BXConstans.JK_DJDL });
			} else {
				if (isBxDjlx) {
					// �Ա��������������=MA�ܽ��-BX��ִ��-BX��Ԥռ
					pf = getPfByMtappdetail(detailvo.getPk_mtapp_detail(), new String[] { BXConstans.BX_DJDL });
				}
			}
			detailvo.setUsable_amout(detailvo.getOrig_amount().sub(pf));
		}
		Map<String, List<MtAppDetailVO>> map = VOUtils.changeCollection2MapList(
				Arrays.asList(detailList.toArray(new MtAppDetailVO[] {})), new String[] { MatterAppVO.PK_MTAPP_BILL });

		// �õ����������ķ������뵥����PKS
		Set<String> mtAppPKs = new HashSet<String>();
		for (MtAppDetailVO mtAppDetailVO : detailList) {
			mtAppPKs.add(mtAppDetailVO.getPk_mtapp_bill());
		}
		MatterAppVO[] mtAppVOS = queryMatterAppVoByPks(mtAppPKs.toArray(new String[] {}),headSql);
		
		if(mtAppVOS == null || mtAppVOS.length == 0){
			return null;
		}

		// ��װ�����
		AggMatterAppVO[] results = new AggMatterAppVO[mtAppVOS.length];
		for (int i = 0; i < mtAppVOS.length; i++) {
			results[i] = new AggMatterAppVO();
			results[i].setParentVO(mtAppVOS[i]);
			MtAppDetailVO[] details = map.get(mtAppVOS[i].getPk_mtapp_bill()).toArray(new MtAppDetailVO[] {});
			results[i].setChildrenVO(details);
		}
		return results;
	}

	/**
	 * 
	 * @param pk_mtapp_detail
	 * @return ִ�м�¼���д������뵥��ϸ�б�ĳЩ�������ͣ�eg,JK/BX����ռ�õ�ִ����
	 * @throws BusinessException
	 * @throws SQLException
	 */
	private UFDouble getPfByMtappdetail(String pk_mtapp_detail, String[] djdl) throws BusinessException {
		StringBuffer sqlBuf = new StringBuffer();
		String insql;
		try {
			insql = SqlUtils.getInStr(MtappbillpfVO.PK_DJDL, djdl, true);
			sqlBuf.append("select sum(exe_amount) from er_mtapp_billpf where pk_mtapp_detail='");
			sqlBuf.append(pk_mtapp_detail);
			sqlBuf.append("' and  ");
			sqlBuf.append(insql);
			// sqlBuf.append("group by pk_mtapp_detail");
			return (UFDouble) new BaseDAO().executeQuery(sqlBuf.toString(), getResultSetProcessor());
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
		return UFDouble.ZERO_DBL;
	}

	private static ResultSetProcessor getResultSetProcessor() {
		ResultSetProcessor processor = new ResultSetProcessor() {
			private static final long serialVersionUID = 1L;

			public Object handleResultSet(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return new UFDouble(rs.getDouble(1));
				}
				return new UFDouble(0);
			}
		};
		return processor;
	}

	@Override
	public AggMatterAppVO[] queryBillByWhere(String condition) throws BusinessException {
		String fixedCon = Mtapptable_alis + "dr = 0 ";
		String whereSql = StringUtil.isEmpty(condition) ? fixedCon : condition + " and " + fixedCon;
		whereSql += " order by " + Mtapptable_alis + "billdate desc";
		@SuppressWarnings("unchecked")
		Collection<AggMatterAppVO> resultList = getService().queryBillOfVOByCond(AggMatterAppVO.class, whereSql, false);
		return resultList.toArray(new AggMatterAppVO[] {});
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggMatterAppVO[] queryBillByPKs(String[] pks, boolean lazyLoad) throws BusinessException {
		Collection<AggMatterAppVO> resultList = null;
		try {
			String whereSql = SqlUtils.getInStr(Mtapptable_alis + MatterAppVO.PK_MTAPP_BILL, pks, false);

			whereSql = whereSql + " order by " + Mtapptable_alis + MatterAppVO.BILLDATE + " desc, " + Mtapptable_alis
					+ MatterAppVO.BILLNO + " desc ";

			resultList = getService().queryBillOfVOByCondWithOrder(AggMatterAppVO.class, whereSql, false, lazyLoad,
					new String[] { "mtapp_detail.rowno" });// ��������Ԫ���ݱ��루���壩 +
															// ������
		} catch (Exception e) {
			Logger.error("����pk������ѯ�������뵥ʧ��", e);
			ExceptionHandler.handleException(e);
		}
		AggMatterAppVO[] aggvos = resultList.toArray(new AggMatterAppVO[0]);
		if (aggvos.length > 0) {
			// �������չҳǩ��Ϣ
			String pk_group = aggvos[0].getParentVO().getPk_group();
			ErmExtendconfigInterfaceCenter.fillExtendTabVOs(pk_group, MatterAppVO.PK_TRADETYPE, aggvos);
		}
		return aggvos;
	}

	@Override
	public MatterAppVO[] queryMatterAppVoByPks(String[] pks) throws BusinessException {

		try {
			String sql = SqlUtils.getInStr(Mtapptable_alis + MatterAppVO.PK_MTAPP_BILL, pks, true);
			sql += " order by " + Mtapptable_alis + MatterAppVO.BILLDATE + " desc, " + Mtapptable_alis
					+ MatterAppVO.BILLNO + " desc ";
			@SuppressWarnings("unchecked")
			Collection<MatterAppVO> result = new BaseDAO().retrieveByClause(MatterAppVO.class, sql);
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.toArray(new MatterAppVO[] {});
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}

		return null;
	}
	

	public MatterAppVO[] queryMatterAppVoByPks(String[] pks,String headSql) throws BusinessException {

		try {
			String sql = SqlUtils.getInStr(Mtapptable_alis + MatterAppVO.PK_MTAPP_BILL, pks, true);
			sql += " order by " + Mtapptable_alis + MatterAppVO.BILLDATE + " desc, " + Mtapptable_alis
					+ MatterAppVO.BILLNO + " desc ";
			String whereSql = StringUtil.isEmpty(headSql) ? sql : headSql +" and "+ sql;
			@SuppressWarnings("unchecked")
			Collection<MatterAppVO> result = new BaseDAO().retrieveByClause(MatterAppVO.class, whereSql);
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.toArray(new MatterAppVO[] {});
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}

		return null;
	}
	

	@Override
	public MtAppDetailVO[] queryMtAppDetailVOVoByPks(String[] pks) throws BusinessException {
		try {
			String sql = SqlUtils.getInStr(MtAppDetailVO.PK_MTAPP_DETAIL, pks, true);
			@SuppressWarnings("unchecked")
			Collection<MtAppDetailVO> result = new BaseDAO().retrieveByClause(MtAppDetailVO.class, sql);
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.toArray(new MtAppDetailVO[] {});
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
		return null;
	}

	@Override
	public AggMatterAppVO[] queryBillFromMtappByPsn(String headSql, String djlxbm, String pk_org, String pk_psndoc,
			String rolerSql) throws BusinessException {
		String fixedCon = "";
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		if (djlxbm.startsWith(BXConstans.JK_PREFIX)) {
			// �������������ܶ� > �ܵ�ִ���� + �ܵ�Ԥռ�� ��Total > totalExe + totalPre��
			fixedCon += " orig_amount > isnull(( SELECT sum(p.exe_amount+p.pre_amount) FROM er_mtapp_billpf p WHERE p.pk_mtapp_detail=er_mtapp_detail.pk_mtapp_detail GROUP BY p.pk_mtapp_detail ),0 ) ";
		} else if (djlxbm.startsWith(BXConstans.BX_PREFIX)) {
			// �����������������ܶ�>������ִ����+������Ԥռ�� ��Total > bxExe + bxPre��
			fixedCon += " orig_amount > isnull(( SELECT sum(p.exe_amount+p.pre_amount) FROM er_mtapp_billpf p WHERE p.pk_mtapp_detail=er_mtapp_detail.pk_mtapp_detail and p.pk_djdl='bx' GROUP BY p.pk_mtapp_detail ),0 ) ";
		}

		if (!StringUtil.isEmpty(pk_psndoc)) {
			fixedCon += " and " + SqlUtils.getInStr(MatterAppVO.BILLMAKER, getAgentPsnDocs(pk_psndoc, rolerSql), true);
		}

		// ���������������͵����뵥�����������е��������ͣ�
		// fixedCon +=
		// " and pk_tradetype in (select djlxbm from er_djlx where djdl='ma'  and matype is null or matype in( 0, 1) and pk_group='"
		// + pk_group + "')";
		
		String djlxSql = " select djlxbm from er_djlx where djdl='ma' and matype is null or matype in( 0, 1) and pk_group='"
				+ pk_group + "' ";
		fixedCon += " and " + SqlUtils.getInStr("pk_tradetype", queryPksBySql(djlxSql), true);
		
		fixedCon += " and effectstatus=1 and close_status=2 ";
		fixedCon += " and dr = 0 ";
		AggMatterAppVO[] aggvos = queryBillFromMtappByWhere(djlxbm, fixedCon,headSql);

		return filtMatterBillByCtrlSchema(aggvos, djlxbm);
	}

	private List<String> getAgentPsnDocs(String pk_psndoc, String rolersql) throws BusinessException {
		String sql = "";

		if (rolersql == null || rolersql.length() == 0) {
			// ǰ̨��������rolersqlΪ��ʱ����̨�����²�һ��
			rolersql = BXBsUtil.getRoleInStr(InvocationInfoProxy.getInstance().getUserId(), IRoleConst.BUSINESS_TYPE);
		}
		String dept = null;
		if (pk_psndoc != null && pk_psndoc.length() != 0) {
			dept = BXBsUtil.getPsnPk_dept(pk_psndoc);
		}
		
		// ����֧�ָ�����Ȩ���� add 2015-3-12 chenshuaia
		String billtypeSql = " and billtype like '261%' ";
		String date = AuditInfoUtil.getCurrentTime().getDate().toString();
		
		sql += " (select distinct bd_psndoc.pk_psndoc from bd_psndoc,bd_psnjob where bd_psndoc.pk_psndoc=bd_psnjob.pk_psndoc and (bd_psndoc.pk_psndoc='"
				+ pk_psndoc
				+ "' "
				+ " or ( bd_psndoc.pk_psndoc in(select pk_user from er_indauthorize where type=0 and keyword = 'busiuser' and "
				+ rolersql
				+ ") ) "
				+ " or ( bd_psnjob.pk_dept in(select pk_user from er_indauthorize where type=0 and keyword = 'pk_deptdoc' and "
				+ rolersql
				+ ") )"
				+ " or ((select count(pk_user) from er_indauthorize where type=0 and keyword = 'isall' and pk_user like 'true%' and "
				+ rolersql
				+ ") > 0) "
				+ " or ((select count(pk_user) from er_indauthorize where type=0 and keyword = 'issamedept' and pk_user like 'true%' and "
				+ rolersql + ") > 0 and bd_psnjob.pk_dept ='" + dept + "' ) "
				+ " or (bd_psndoc.pk_psndoc in(select pk_user from er_indauthorize where pk_operator='"
				+ AuditInfoUtil.getCurrentUser()
				+ "'" + billtypeSql + " and '" + date + "'<=enddate and '" + date + "'>=startdate))"
				+ " ))";
		
		//ִ��sql������ȡ���
		List<String> billMakerList = queryPksBySql(sql);
		
		return billMakerList;
	}
	
	private List<String> queryPksBySql(String sql) throws DAOException {
		// ִ��sql������ȡ���
		BaseDAO dao = new BaseDAO();
		@SuppressWarnings("unchecked")
		List<String> billMakerList = (List<String>) dao.executeQuery(sql, new ListResultSetProcessor());
		return billMakerList;
	}
	
	private class ListResultSetProcessor implements ResultSetProcessor {
		private static final long serialVersionUID = 1L;
		@Override
		public Object handleResultSet(ResultSet rs) throws SQLException {
			List<String> topPks = new ArrayList<String>();
			while (rs.next()) {
				topPks.add(rs.getString(1));
			}
			return topPks;
		}
	}
	

	@SuppressWarnings("unchecked")
	private AggMatterAppVO[] filtMatterBillByCtrlSchema(AggMatterAppVO[] aggvos, String djlxbm)
			throws BusinessException {
		if (aggvos == null || aggvos.length < 0 || djlxbm == null || djlxbm.trim() == null) {
			return null;
		}
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		List<String[]> paramList = new ArrayList<String[]>();
		for (AggMatterAppVO aggvo : aggvos) {
			paramList.add(new String[] { aggvo.getParentVO().getPk_org(), aggvo.getParentVO().getPk_tradetype() });
		}
		@SuppressWarnings("rawtypes")
		Map[] ctrlmap = NCLocator.getInstance().lookup(IErmMappCtrlBillQuery.class).queryCtrlShema(paramList, pk_group);
		Map<String, List<String>> key2CtrlBillVosMap = ctrlmap[0];
		List<AggMatterAppVO> result = new ArrayList<AggMatterAppVO>();
		if (key2CtrlBillVosMap != null) {
			for (AggMatterAppVO aggvo : aggvos) {
				List<String> srcTradetype = key2CtrlBillVosMap.get(aggvo.getParentVO().getPk_org()
						+ aggvo.getParentVO().getPk_tradetype());
				if (srcTradetype != null && srcTradetype.contains(djlxbm)) {
					result.add(aggvo);
				}
			}
		}
		return result == null ? null : result.toArray(new AggMatterAppVO[result.size()]);
	}
	
	public AggMatterAppVO getAddInitAggMatterVo(DjLXVO djlx, String funnode, AggMatterAppVO vo)
			throws BusinessException {
		if (djlx == null) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("accruedbill_0", "02011001-0031")/*
																																 * @
																																 * res
																																 * "�������Ͳ���Ϊ��"
																																 */);
		}
		
		AggMatterAppVO defaultVo = vo;
		
		if(defaultVo == null){
			defaultVo = new AggMatterAppVO();
			MatterAppVO head = new MatterAppVO();
			defaultVo.setParentVO(head);
		}
		
		UFDate busiDate = new UFDate(InvocationInfoProxy.getInstance().getBizDateTime());
		String loginUser = AuditInfoUtil.getCurrentUser();
		
		//��ͷĬ��ֵ
		MatterAppVO parentVo = defaultVo.getParentVO();
		parentVo.setPk_billtype(ErmMatterAppConst.MatterApp_BILLTYPE);
		parentVo.setPk_tradetype(djlx.getDjlxbm());
		parentVo.setBilldate(busiDate);
		parentVo.setPk_group(InvocationInfoProxy.getInstance().getGroupId());
		
		//��ͷ���Ĭ��ֵ
		String[] headAmounts = AggMatterAppVO.getHeadAmounts();
		for (String field : headAmounts) {
			parentVo.setAttributeValue(field, UFDouble.ZERO_DBL);
		}
		
		//��β
		parentVo.setApprover(null);
		parentVo.setApprovetime(null);
		parentVo.setCloseman(null);
		parentVo.setClosedate(null);
		parentVo.setPrinter(null);
		parentVo.setPrintdate(null);
		parentVo.setCreator(loginUser);
		parentVo.setCreationtime(null);
		
		//״ֵ̬
		parentVo.setBillstatus(BXStatusConst.DJZT_Saved);
		parentVo.setApprstatus(IBillStatus.FREE);
		parentVo.setEffectstatus(ErmMatterAppConst.EFFECTSTATUS_NO);
		parentVo.setClose_status(ErmMatterAppConst.CLOSESTATUS_N);
		
		//������Ա��Ϣ
		setPsnInfoByUserId(parentVo, loginUser, parentVo.getPk_group());
		//���ñ���
		setCurrency(parentVo,djlx);
		//���û���
		setCurrencyRate(parentVo);
		
		return defaultVo;
	}

	private void setPsnInfoByUserId(MatterAppVO parentVo, String userId , String pk_group) throws BusinessException {
		String[] psnInfos = NCLocator.getInstance().lookup(IBXBillPrivate.class).queryPsnidAndDeptid(userId, pk_group);
		if(psnInfos != null && psnInfos.length > 0){
			parentVo.setBillmaker(psnInfos[0]);
			parentVo.setApply_dept(psnInfos[1]);
			parentVo.setAssume_dept(psnInfos[1]);
			parentVo.setPk_org(psnInfos[2]);
		}
	}

	private void setCurrency(MatterAppVO parentVo, DjLXVO djlx) throws BusinessException {
		String pk_currency = null;
		if (djlx == null || djlx.getDefcurrency() == null) {
			// ��֯���ұ���
			
			if(parentVo.getPk_org() != null){
				pk_currency = Currency.getOrgLocalCurrPK(parentVo.getPk_org());
			}
			// Ĭ����֯������Ϊԭ��
		} else {
			pk_currency = djlx.getDefcurrency();
		}
		
		parentVo.setPk_currtype(pk_currency);
	}

	private void setCurrencyRate(MatterAppVO parentVo) {
		String pk_org = parentVo.getPk_org();
		String pk_currtype = parentVo.getPk_currtype();
		UFDate date = parentVo.getBilldate();

		if (pk_org == null || pk_currtype == null || date == null) {
			return;
		}
		try {
			// ����(���ң����ű��ң�ȫ�ֱ��һ���)
			UFDouble orgRate = Currency.getRate(pk_org, pk_currtype, date);
			UFDouble groupRate = Currency.getGroupRate(pk_org, parentVo.getPk_group(), pk_currtype, date);
			UFDouble globalRate = Currency.getGlobalRate(pk_org, pk_currtype, date);
			parentVo.setOrg_currinfo(orgRate);
			parentVo.setGroup_currinfo(groupRate);
			parentVo.setGlobal_currinfo(globalRate);
		} catch (Exception e) {
			ExceptionHandler.consume(e);
		}
	}

	
	@Override
	public AggMatterAppVO[] queryMaFor35ByQueryScheme(IQueryScheme queryScheme) throws BusinessException {

		String sql = createSql(queryScheme);
		DataAccessUtils utils = new DataAccessUtils();
		String[] ids = utils.query(sql).toOneDimensionStringArray();
		MatterViewVO[] views = new ViewQuery<MatterViewVO>(MatterViewVO.class).query(ids);
		if (ArrayUtils.isEmpty(views)) {
			return null;
		}

		AggMatterAppVO[] queryVos = new MaBillCombineUtil<AggMatterAppVO>(AggMatterAppVO.class, MatterAppVO.class,
				MtAppDetailVO.class).combineViewToAgg(views, MatterAppVO.PK_MTAPP_BILL);

		return queryVos;

	}
	
	private String createSql(IQueryScheme queryScheme) throws BusinessException {

		QuerySchemeProcessor qrySchemeProcessor = new QuerySchemeProcessor(queryScheme);
		qrySchemeProcessor.appendRefTrantypeWhere(ErmMatterAppConst.MatterApp_BILLTYPE, "35", MatterAppVO.PK_TRADETYPE);
		SqlBuilder h_sql = new SqlBuilder();
		String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
		h_sql.append("select distinct(" + mainTableAlias + ".pk_mtapp_bill)");
		h_sql.append(qrySchemeProcessor.getFinalFromWhere());
		h_sql.append(" and ");
		h_sql.append(mainTableAlias + ".pk_group", BXBsUtil.getPK_group());
		h_sql.append(" and ");
		h_sql.append(mainTableAlias + ".effectstatus", BXStatusConst.SXBZ_VALID);
		h_sql.append(" and ");
		h_sql.append(mainTableAlias + ".close_status", MatterAppCloseStatusEnum.UNCLOSED.toIntValue());
		h_sql.append(" and ");
		h_sql.append(mainTableAlias + ".pk_tradetype");
		h_sql.append(" in (select djlxbm from er_djlx where matype=2 )");
		
		SqlBuilder b_sql = new SqlBuilder();
		b_sql.append("select er_mtapp_detail.pk_mtapp_detail from er_mtapp_detail where er_mtapp_detail.close_status=2 ");
		b_sql.append(" and er_mtapp_detail.pk_mtapp_bill in (");
	    b_sql.append(h_sql);
	    b_sql.append(") order by er_mtapp_detail.billdate");
		return b_sql.toString();
	}

}

