package nc.impl.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pf.pub.PfDataCache;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IJd_hzshujuMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.VectorProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.pubitf.fip.service.IFipBillQueryService;
import nc.pubitf.fip.service.IFipMessageService;
import nc.vo.fip.external.FipExtendAggVO;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipMsgResultVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzVO_YY;
import nc.vo.hkjt.srgk.huiguan.hzshuju.SgsjInfoVO;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO_YY;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoHVO;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.U8FzhsVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.U8VoucherVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoHVO;
import nc.vo.hkjt.srgk.jiudian.hzshuju.HZShuJuVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;
import nc.vo.org.DeptVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class Jd_hzshujuMaintainImpl implements IJd_hzshujuMaintain {

	@Override
	public HZShuJuVO[] handlerHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws Exception {
		// 因为组织可能是多个，分组处理
		String[] orgs = pk_org.split(",");
		// 此处应该为组织+时间加锁 暂时先处理对单个日期加锁
		String[] pks_info = new String[orgs.length];
		StringBuffer orgsql = new StringBuffer();
		for (int i = 0; i < orgs.length; i++) {
			String org = orgs[i];
			if (i == orgs.length - 1) {
				orgsql.append("'" + org + "'");
			} else {
				orgsql.append("'" + org + "',");
			}
			String lock_info = org + "######" + begindate + "######" + enddate;
			pks_info[i] = lock_info;
		}
		boolean isLock = getPklock().addBatchDynamicLock(pks_info);
		if (!isLock) {
			throw new BusinessException("已经加锁处理!");
		}
		// 处理多部门
		StringBuffer deptsql = new StringBuffer();
		if (pk_dept != null && !"".equals(pk_dept)) {
			String[] depts = pk_dept.split(",");
			for (int i = 0; i < depts.length; i++) {
				String dept = depts[i];
				if (i == orgs.length - 1) {
					deptsql.append("'" + dept + "'");
				} else {
					deptsql.append("'" + dept + "',");
				}
			}
		}
		String err_mess = checkColumNull(orgsql.toString(), deptsql.toString(),
				begindate, enddate);
		if (err_mess != null && err_mess.length() > 0) {
			throw new BusinessException(err_mess);
		}
		List<HZShuJuVO> hzdatas = getUpdata(orgsql.toString(),
				deptsql.toString(), begindate, enddate);
		if (hzdatas != null && hzdatas.size() > 0) {
			List<HZShuJuVO> upvos = new ArrayList<HZShuJuVO>();
			List<HZShuJuVO> addvos = new ArrayList<HZShuJuVO>();
			List<HZShuJuVO> delvos = new ArrayList<HZShuJuVO>();
			for (int i = 0; i < hzdatas.size(); i++) {
				HZShuJuVO hzshujuvo = hzdatas.get(i);
				String jzfs_id = hzshujuvo.getJzfs_id();
				String srxm_id = hzshujuvo.getSrxm_id();
				if (jzfs_id == null || "".equals(jzfs_id)) {
					hzshujuvo.setJzfs_id("~");
				}
				if (srxm_id == null || "".equals(srxm_id)) {
					hzshujuvo.setSrxm_id("~");
				}
				int uptype = hzshujuvo.getUptype();
				if (uptype == 0) {

					addvos.add(hzshujuvo);
				} else if (uptype == 1) {
					upvos.add(hzshujuvo);
				} else if (uptype == 2) {
					hzshujuvo.setDr(1);
					delvos.add(hzshujuvo);
				}
			}
			if (addvos.size() > 0) {
				getBD().insertVOArray(addvos.toArray(new HZShuJuVO[] {}));
			}
			if (upvos.size() > 0) {
				getBD().updateVOArray(upvos.toArray(new HZShuJuVO[] {}),
						new String[] { "charge", "payment" });
			}
			if (delvos.size() > 0) {
				getBD().updateVOArray(delvos.toArray(new HZShuJuVO[] {}),
						new String[] { "dr" });
			}
		}
		return null;
	}

	/**
	 * 计算之前检查必要的字段是否为空
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private String checkColumNull(String orgsql, String deptsql,
			String begindate, String enddate) throws DAOException {
		StringBuffer sb = new StringBuffer();
		sb.append("select rzmx.pk_group, ");
		sb.append("rzmx.pk_org, ");
		sb.append("rzmx.pk_org_v, ");
		sb.append("rzmx.vbillcode,");
		sb.append("rzmxb.vrowno,");
		sb.append("rzmx.dbilldate hzdate, ");
		sb.append("rzmxb.bm_id pk_dept, ");
		sb.append("rzmxb.bm_fid pk_fdept, ");
		sb.append("rzmxb.srxm_id, ");
		sb.append("rzmxb.jzfs_id, ");
		sb.append("rzmxb.khmz, ");
		sb.append("rzmxb.charge charge, ");
		sb.append("rzmxb.jzfs_name,");
		sb.append("rzmxb.payment payment ");
		sb.append("from hk_srgk_jd_rzmx rzmx ");
		sb.append("left join hk_srgk_jd_rzmx_b rzmxb ");
		sb.append("on rzmx.pk_hk_srgk_jd_rzmx = rzmxb.pk_hk_srgk_jd_rzmx ");
		sb.append("where nvl(rzmx.dr, 0) = 0 ");
		sb.append("and nvl(rzmxb.dr, 0) = 0 ");
		sb.append("and rzmx.pk_org in (" + orgsql + ") ");
		if (deptsql.length() > 0) {
			sb.append("and rzmxb.bm_id in (" + deptsql + ") ");
		}

		sb.append("and to_date(rzmx.dbilldate, 'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date(substr('" + begindate
				+ "', 0, 10), 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(rzmx.dbilldate, 'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date(substr('" + enddate
				+ "', 0, 10), 'yyyy-mm-dd hh24:mi:ss') ");
		List<HZShuJuVO> list = (List<HZShuJuVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(HZShuJuVO.class));
		StringBuffer mess = new StringBuffer();
		if (list == null || list.size() <= 0) {
			mess.append("没有要计算的账单数据！");
		} else {
			List<String> set = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				HZShuJuVO vo = list.get(i);
				String dbilldate = vo.getHzdate().toString().substring(0, 10);
				String pk_fdept = vo.getPk_fdept();
				String pk_dept = vo.getPk_dept();
				String pk_srxm = vo.getSrxm_id();
				String pk_jzfs = vo.getJzfs_id();
				String jzfsname = vo.getJzfs_name();
				String khmz = vo.getKhmz();
				String vrowno = vo.getVrowno();
				UFDouble jzjine = vo.getPayment();
				UFDouble xfjine = vo.getCharge();
				if (pk_fdept == null || pk_fdept.length() <= 0
						|| "~".equals(pk_fdept) || pk_dept == null
						|| pk_dept.length() <= 0 || "~".equals(pk_dept)) {
					set.add("【单据日期：" + dbilldate + ",行号:" + vrowno
							+ "!部门和上级部门不能为空!】\n");
				}
				//2015-12-30修改  计算酒店账单 如果结账金额不为0且结账方式为空或者~ 进行控制
				if (jzjine != null && jzjine.compareTo(UFDouble.ZERO_DBL) != 0
						&& (pk_jzfs == null || "".equals(pk_jzfs)|| "~".equals(pk_jzfs))) {
					set.add("【单据日期：" + dbilldate + ",行号:" + vrowno
							+ "!结账方式不能为空!】\n");
				}
				//2015-12-30修改  计算酒店账单 如果消费金额不为0且商品分类为空或者~ 进行控制
				if (xfjine != null && xfjine.compareTo(UFDouble.ZERO_DBL) != 0
						&& (pk_srxm == null || "".equals(pk_srxm)|| "~".equals(pk_srxm))) {
					set.add("【单据日期：" + dbilldate + ",行号:" + vrowno
							+ "!收入项目不能为空!】\n");
				}
				if (jzfsname != null && jzfsname.equals("消费客户往来款")
						&& (khmz == null || "".equals(khmz))) {
					set.add("【单据日期：" + dbilldate + ",行号:" + vrowno
							+ "!结账方式为消费客户往来款时客户名称不能为空!】\n");
				}
			}
			if (set.size() > 0) {
				Iterator<String> it = set.iterator();
				mess.append("以下账单的行号信息有误!请检查!\n");
				while (it.hasNext()) {
					String me = it.next();
					mess.append(me);
				}
			}
		}
		return mess.toString();

	}

	/**
	 * 获取增删改数据
	 * */
	@SuppressWarnings("unchecked")
	private List<HZShuJuVO> getUpdata(String orgsql, String deptsql,
			String begindate, String enddate) throws DAOException {
		StringBuffer sb = new StringBuffer();
		sb.append("select decode(hzshuju.pk_hk_srgk_jd_hzshuju, ");
		sb.append("'', ");
		sb.append("0, ");
		sb.append("decode(rzmxinfo.pk_group || rzmxinfo.pk_org, '', 2, 1)) uptype, ");
		sb.append("hzshuju.pk_hk_srgk_jd_hzshuju, ");
		sb.append("rzmxinfo.pk_group, ");
		sb.append("rzmxinfo.pk_org, ");
		sb.append("rzmxinfo.pk_org_v, ");
		sb.append("rzmxinfo.dbilldate hzdate, ");
		sb.append("rzmxinfo.bm_id pk_dept, ");
		sb.append("rzmxinfo.bm_fid pk_fdept, ");
		sb.append("rzmxinfo.srxm_id, ");
		sb.append("rzmxinfo.jzfs_id, ");
		sb.append("rzmxinfo.khmz, ");
		sb.append("rzmxinfo.charge, ");
		sb.append("rzmxinfo.payment ");
		sb.append("from (select rzmx.pk_group, ");
		sb.append("rzmx.pk_org, ");
		sb.append("rzmx.pk_org_v, ");
		sb.append("rzmx.dbilldate, ");
		sb.append("rzmxb.bm_id, ");
		sb.append("rzmxb.bm_fid, ");
		sb.append("rzmxb.srxm_id, ");
		sb.append("rzmxb.jzfs_id, ");
		sb.append("rzmxb.khmz, ");
		sb.append("sum(rzmxb.charge) charge, ");
		sb.append("sum(rzmxb.payment) payment ");
		sb.append("from hk_srgk_jd_rzmx rzmx ");
		sb.append("left join hk_srgk_jd_rzmx_b rzmxb ");
		sb.append("on rzmx.pk_hk_srgk_jd_rzmx = rzmxb.pk_hk_srgk_jd_rzmx ");
		sb.append("where nvl(rzmx.dr, 0) = 0 ");
		sb.append("and nvl(rzmxb.dr, 0) = 0 ");
		sb.append("and rzmx.pk_org in (" + orgsql + ") ");
		if (deptsql.length() > 0) {
			sb.append("and rzmxb.bm_id in (" + deptsql + ") ");
		}

		sb.append("and to_date(rzmx.dbilldate, 'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date(substr('" + begindate
				+ "', 0, 10), 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(rzmx.dbilldate, 'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date(substr('" + enddate
				+ "', 0, 10), 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("group by rzmx.pk_group, ");
		sb.append("rzmx.pk_org, ");
		sb.append("rzmx.pk_org_v, ");
		sb.append("rzmx.dbilldate, ");
		sb.append("rzmxb.bm_id, ");
		sb.append("rzmxb.bm_fid, ");
		sb.append("rzmxb.srxm_id, ");
		sb.append("rzmxb.jzfs_id, ");
		sb.append("rzmxb.khmz) rzmxinfo ");
		sb.append("full outer join (select * ");
		sb.append("from hk_srgk_jd_hzshuju ");
		sb.append("where nvl(dr, 0) = 0 ");
		sb.append("and pk_org in (" + orgsql + ") ");
		if (deptsql.length() > 0) {
			sb.append("and pk_dept in (" + deptsql + ")  ");
		}
		sb.append("and to_date(hzdate, 'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date(substr('" + begindate + "', 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(hzdate, 'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date(substr('" + enddate + "', 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss')) hzshuju ");

		sb.append("on hzshuju.pk_group = rzmxinfo.pk_group ");
		sb.append("and hzshuju.pk_org = rzmxinfo.pk_org ");
		sb.append("and hzshuju.pk_org_v = rzmxinfo.pk_org_v ");
		sb.append("and hzshuju.hzdate = rzmxinfo.dbilldate ");
		sb.append("and hzshuju.pk_dept = rzmxinfo.bm_id ");
		sb.append("and hzshuju.pk_fdept = rzmxinfo.bm_fid ");
		sb.append("and hzshuju.srxm_id = rzmxinfo.srxm_id ");
		sb.append("and hzshuju.jzfs_id = rzmxinfo.jzfs_id ");
		sb.append("and hzshuju.khmz = rzmxinfo.khmz ");

		List<HZShuJuVO> havedates = (List<HZShuJuVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(HZShuJuVO.class));

		return havedates;

	}

	/**
	 * 部门编码和自定义项的对应关系
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, String[]> getDept_Vdef(String pk_dept)
			throws DAOException {
		Map<String, String[]> map = new HashMap<String, String[]>();
		String sql = "select pk_dept,name,pk_fatherorg from org_dept where pk_fatherorg='"
				+ pk_dept
				+ "' and nvl(dr,0)=0 and def1<>'~' and def1 is not null order by code";
		Vector v = (Vector) getBD().executeQuery(sql, new VectorProcessor());
		if (v != null && v.size() > 0) {
			for (int i = 0; i < v.size(); i++) {
				Vector v_1 = (Vector) v.get(i);
				String dept = (String) v_1.elementAt(0);
				String name = (String) v_1.elementAt(1);
				String fdept = (String) v_1.elementAt(2);
				String[] str = new String[3];
				if (i + 1 < 10) {
					str[0] = "shouru_bm0" + (i + 1);
				} else {
					str[0] = "shouru_bm" + (i + 1);
				}
				str[1] = name;
				str[2] = fdept;
				map.put(dept, str);
			}
		}
		return map;

	}

	BaseDAO bd = null;
	PKLock pklock = null;

	private BaseDAO getBD() {
		if (bd == null) {
			bd = new BaseDAO();
		}
		return bd;
	}

	public PKLock getPklock() {
		if (pklock == null) {
			pklock = PKLock.getInstance();
		}
		return pklock;
	}

	@Override
	public SrdibiaoBillVO[] selectHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate, Map<String, String[]> dept_def,
			boolean isRs, String isShowDept,UFBoolean isjd) throws Exception {
		// 多组织和多部门处理，如果组织为多个，则部门不处理。
		String[] orgs = pk_org.split(",");
		StringBuffer orgsql = new StringBuffer();
		for (int i = 0; i < orgs.length; i++) {
			String org = orgs[i];
			if (i == orgs.length - 1) {
				orgsql.append("'" + org + "'");
			} else {
				orgsql.append("'" + org + "',");
			}
		}
		// 暂时取第一个组织
		String first_org = orgs[0];
		// 部门默认按照一个处理
		String dept = null;
		if (pk_dept != null && !"".equals(pk_dept)) {
			String[] depts = pk_dept.split(",");
			dept = depts[0];
		}
		String pk_org_v = getOrg_v(first_org);
		// 判断是否显示部门凭证信息
		boolean isdept = false;
		if (isRs && "Y".equals(isShowDept) && orgs.length == 1
				&& (dept == null || "".equals(dept))) {
			isdept = true;
		}
		// 处理收入手工数据
		SgsjInfoVO[] sgsjvos = getSgsjInfoVOs(first_org, orgsql.toString(),
				pk_dept, begindate, enddate, isShowDept,isjd);
		RsbaogaoCVO_YY[] rstz = null;	// 日审报告-调整
		CctzVO_YY[] cctz = null;		// 差错调整
		if (isRs) {
			rstz = getRsTzInfo(first_org, orgsql.toString(), pk_dept,
					begindate, enddate, isShowDept);
			cctz = getCctzInfo(first_org, orgsql.toString(), pk_dept,
					begindate, enddate, isShowDept);
		}

		// 查询获取酒店结账，收入项目，和往来信息
		Map<String, List<HZShuJuVO>> map_hzshuju = getHzShuJuInfo(first_org,
				orgsql.toString(), pk_dept, begindate, enddate, isRs,
				isShowDept, sgsjvos, rstz, cctz);

		// 获取翻房，平均房价等信息
		RzmxHVO rzmxvo = getFangJiaInfo(orgsql.toString(), begindate, enddate);

		// 对数据分析以及构造表体数据
		String[] hvoinfo = new String[] { first_org, pk_org_v, pk_dept,
				begindate };
		SrdibiaoBillVO[] srdbvos = handlerHzshujuLeftAndRight(map_hzshuju,
				rzmxvo, sgsjvos, rstz, cctz, isdept, hvoinfo);
		
		/**
		 * 对 行号  进行赋值
		 * 李彬  2016年4月29日19:54:21
		 */
		for( int bill_i=0;srdbvos!=null&&bill_i<srdbvos.length;bill_i++ )
		{
			SrdibiaoBillVO billVO = srdbvos[bill_i];
			Object body_obj = billVO.getChildrenVO();
			if(body_obj!=null && body_obj instanceof SrdibiaoBVO[])
			{
				this.resetVrowNo((SrdibiaoBVO[])body_obj);
			}
			
//			billVO.getParentVO().setVdef10(isjd.toString());	// （HK 2018年11月6日17:50:23）
			
		}
		/**END*/
		
		return srdbvos;
	}

	// 获取汇总表数据
	@SuppressWarnings("unchecked")
	private Map<String, List<HZShuJuVO>> getHzShuJuInfo(String first_org,
			String pk_org, String pk_dept, String begindate, String enddate,
			boolean isRs, String isShowDept, SgsjInfoVO[] sgsjvos,
			RsbaogaoCVO_YY[] rstz, CctzVO_YY[] cctz) throws DAOException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from( ");
		sb.append("select  ");
		sb.append("hzshuju.pk_group, ");
		sb.append("hzshuju.pk_org, ");
		sb.append("hzshuju.pk_org_v, ");
		sb.append("hzshuju.pk_dept, ");
		sb.append("hzshuju.pk_fdept, ");
		sb.append("hzshuju.hzdate, ");
		sb.append("hzshuju.jzfs_id lx_id, ");
		sb.append("hzshuju.khmz, ");
		sb.append("jzfs.pk_parent, ");
		sb.append("jzfs.code, ");
		sb.append("jzfs.name, ");
		sb.append("hzshuju.payment jine, ");
		sb.append("'jzfs' xflx ");
		sb.append(" from hk_srgk_jd_hzshuju  hzshuju left join  ");
		sb.append(" hk_srgk_hg_jzfs jzfs ");
		sb.append(" on hzshuju.jzfs_id=jzfs.pk_hk_srgk_hg_jzfs ");
		sb.append(" where payment<>0 and nvl(hzshuju.dr,0)=0 and jzfs.name<>'消费客户往来款' ");
		sb.append(" union all ");
		sb.append("select  ");
		sb.append("hzshuju.pk_group, ");
		sb.append("hzshuju.pk_org, ");
		sb.append("hzshuju.pk_org_v, ");
		sb.append("hzshuju.pk_dept, ");
		sb.append("hzshuju.pk_fdept, ");
		sb.append("hzshuju.hzdate, ");
		sb.append("hzshuju.srxm_id lx_id, ");
		sb.append("hzshuju.khmz, ");
		sb.append("srxm.pk_parent, ");
		sb.append("srxm.code, ");
		sb.append("srxm.name, ");
		sb.append("hzshuju.charge jine, ");
		sb.append("'srxm' xflx ");
		sb.append(" from hk_srgk_jd_hzshuju  hzshuju  left join hk_srgk_hg_srxm srxm ");
		sb.append("on hzshuju.srxm_id=srxm.pk_hk_srgk_hg_srxm ");
		sb.append(" where charge<>0 and nvl(hzshuju.dr,0)=0  ");
		sb.append(" union all ");
		sb.append(" select  ");
		sb.append("hzshuju.pk_group, ");
		sb.append("hzshuju.pk_org, ");
		sb.append("hzshuju.pk_org_v, ");
		sb.append("hzshuju.pk_dept, ");
		sb.append("hzshuju.pk_fdept, ");
		sb.append("hzshuju.hzdate, ");
		sb.append("hzshuju.jzfs_id lx_id, ");
		sb.append("hzshuju.khmz, ");
		sb.append("jzfs.pk_parent, ");
		sb.append("jzfs.code, ");
		sb.append("jzfs.name, ");
		sb.append("hzshuju.payment jine, ");
		sb.append("'xfwlk' xflx ");
		sb.append(" from hk_srgk_jd_hzshuju  hzshuju left join  ");
		sb.append(" hk_srgk_hg_jzfs jzfs ");
		sb.append(" on hzshuju.jzfs_id=jzfs.pk_hk_srgk_hg_jzfs ");
		sb.append(" where payment<>0 and nvl(hzshuju.dr,0)=0 and jzfs.name='消费客户往来款') xfinfo  ");
		sb.append(" where xfinfo.pk_org in (" + pk_org + ") ");
		sb.append(" and to_date(xfinfo.hzdate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
				+ begindate.substring(0, 10) + "','yyyy-mm-dd hh24:mi:ss') ");
		sb.append("  and to_date(xfinfo.hzdate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
				+ enddate.substring(0, 10) + "','yyyy-mm-dd hh24:mi:ss') ");
		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append("and (xfinfo.pk_dept in ");
			sb.append("(select dept.pk_dept ");
			sb.append(" from org_dept dept  where dept.def1 is not null and dept.def1 <> '~' ");
			sb.append(" connect by dept.pk_fatherorg = prior dept.pk_dept ");
			sb.append(" start with dept.pk_dept in ('" + pk_dept
					+ "')) or xfinfo.pk_dept in ('" + pk_dept + "')) ");
		}
		sb.append(" order by xfinfo.xflx,xfinfo.code ");

		List<HZShuJuVO> list = (List<HZShuJuVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(HZShuJuVO.class));
		Map<String, List<HZShuJuVO>> map = new HashMap<String, List<HZShuJuVO>>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HZShuJuVO hzshuju = list.get(i);
				String xflx = hzshuju.getXflx();
				if (!map.containsKey(xflx)) {
					List<HZShuJuVO> list_hz = new ArrayList<HZShuJuVO>();
					list_hz.add(hzshuju);
					map.put(xflx, list_hz);
				} else {
					List<HZShuJuVO> list_hz = map.get(xflx);
					list_hz.add(hzshuju);
					map.put(xflx, list_hz);
				}
			}
		}

		if (sgsjvos != null && sgsjvos.length > 0) {
			for (int i = 0; i < sgsjvos.length; i++) {
				SgsjInfoVO sgsjInfoVO = sgsjvos[i];
				String dept = sgsjInfoVO.getPk_dept();
				String group = sgsjInfoVO.getPk_group();
				String org = sgsjInfoVO.getPk_org();
				String tzdate = sgsjInfoVO.getHzdate();
				String fdept = sgsjInfoVO.getDept_fpk();
				handlerSgsjInfo(sgsjInfoVO, dept, group, org, tzdate, fdept,
						map);
			}
		}
		// 如果是营业日报则进行日审和差错调整单处理
		if (isRs) {
			if (rstz != null && rstz.length > 0) {
				for (int i = 0; i < rstz.length; i++) {
					RsbaogaoCVO_YY sgsjInfoVO = rstz[i];
					String dept = sgsjInfoVO.getBm_pk();
					String group = sgsjInfoVO.getPk_group();
					String org = sgsjInfoVO.getPk_org();
					String tzdate = sgsjInfoVO.getDbilldate();
					String fdept = sgsjInfoVO.getPk_fdept();
					handlerSgsjInfo(sgsjInfoVO, dept, group, org, tzdate,
							fdept, map);
				}
			}
			if (cctz != null && cctz.length > 0) {
				for (int i = 0; i < cctz.length; i++) {
					CctzVO_YY sgsjInfoVO = cctz[i];
					HZShuJuVO hzsj = new HZShuJuVO();
					String dept = sgsjInfoVO.getBm_pk();
					String group = sgsjInfoVO.getPk_group();
					String org = sgsjInfoVO.getPk_org();
					String tzdate = sgsjInfoVO.getTz_date().toString()
							.substring(0, 10)
							+ " 00:00:00";
					String fdept = sgsjInfoVO.getPk_fdept();
					hzsj.setPk_group(group);
					hzsj.setIszd("N");
					hzsj.setPk_org(org);
					hzsj.setPk_fdept(fdept);
					hzsj.setHzdate(new UFDate(tzdate));
					hzsj.setPk_dept(dept);
					handlerSgsjInfo(sgsjInfoVO, dept, group, org, tzdate,
							fdept, map);
				}
			}
		}
		return map;
	}

	/**
	 * 处理手工数据信息
	 * */
	private void handlerSgsjInfo(SuperVO sgsjInfoVO, String dept, String group,
			String org, String tzdate, String fdept,
			Map<String, List<HZShuJuVO>> map) {
		// 手工数据的结账方式1处理
		String jzfs1 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_jzfs_1"));
		String jzfsjine1 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_1"));

		// 手工数据的结账方式2处理
		String jzfs2 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_jzfs_2"));
		String jzfsjine2 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_2"));

		// 往来款1处理
		String info1 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_info_1"));
		String info_jine1 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_1"));
		// 往来款2处理
		String info2 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_info_2"));
		String info_jine2 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_2"));

		// 手工数据的收入项目1处理
		String srxm1 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_srxm_1"));
		// 手工数据的收入项目2处理
		String srxm_jine1 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_1"));
		String srxm2 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_srxm_2"));
		String srxm_jine2 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_2"));
		// 往来款
		if (jzfs1 != null && !"".equals(jzfs1) && info1 != null
				&& !"".equals(info1)) {
			HZShuJuVO hzsj = new HZShuJuVO();
			hzsj.setPk_group(group);
			hzsj.setIszd("N");
			hzsj.setPk_org(org);
			hzsj.setPk_fdept(fdept);
			hzsj.setHzdate(new UFDate(tzdate));
			hzsj.setPk_dept(dept);
			hzsj.setLx_id(jzfs1);
			hzsj.setKhmz(info1);
			hzsj.setJine(new UFDouble(info_jine1));
			if (!map.containsKey("xfwlk")) {
				List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
				list.add(hzsj);
				map.put("xfwlk", list);
			} else {
				List<HZShuJuVO> list = map.get("xfwlk");
				list.add(hzsj);
				map.put("xfwlk", list);
			}
		}
		if (jzfs2 != null && !"".equals(jzfs2) && info2 != null
				&& !"".equals(info2)) {
			HZShuJuVO hzsj = new HZShuJuVO();
			hzsj.setPk_group(group);
			hzsj.setIszd("N");
			hzsj.setPk_org(org);
			hzsj.setPk_fdept(fdept);
			hzsj.setHzdate(new UFDate(tzdate));
			hzsj.setPk_dept(dept);
			hzsj.setLx_id(jzfs1);
			hzsj.setLx_id(jzfs2);
			hzsj.setKhmz(info2);
			hzsj.setJine(new UFDouble(info_jine2));
			if (!map.containsKey("xfwlk")) {
				List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
				list.add(hzsj);
				map.put("xfwlk", list);
			} else {
				List<HZShuJuVO> list = map.get("xfwlk");
				list.add(hzsj);
				map.put("xfwlk", list);
			}
		}
		// 结账方式
		if (jzfs1 != null && !"".equals(jzfs1)
				&& (info1 == null || "".equals(info1))) {
			HZShuJuVO hzsj = new HZShuJuVO();
			hzsj.setPk_group(group);
			hzsj.setIszd("N");
			hzsj.setPk_org(org);
			hzsj.setPk_fdept(fdept);
			hzsj.setHzdate(new UFDate(tzdate));
			hzsj.setPk_dept(dept);
			hzsj.setLx_id(jzfs1);
			hzsj.setLx_id(jzfs1);
			hzsj.setJine(new UFDouble(jzfsjine1));
			if (!map.containsKey("jzfs")) {
				List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
				list.add(hzsj);
				map.put("jzfs", list);
			} else {
				List<HZShuJuVO> list = map.get("jzfs");
				list.add(hzsj);
				map.put("jzfs", list);
			}
		}
		if (jzfs2 != null && !"".equals(jzfs2)
				&& (info2 == null && "".equals(info2))) {
			HZShuJuVO hzsj = new HZShuJuVO();
			hzsj.setPk_group(group);
			hzsj.setIszd("N");
			hzsj.setPk_org(org);
			hzsj.setPk_fdept(fdept);
			hzsj.setHzdate(new UFDate(tzdate));
			hzsj.setPk_dept(dept);
			hzsj.setLx_id(jzfs1);
			hzsj.setLx_id(jzfs2);
			hzsj.setJine(new UFDouble(jzfsjine2));
			if (!map.containsKey("jzfs")) {
				List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
				list.add(hzsj);
				map.put("jzfs", list);
			} else {
				List<HZShuJuVO> list = map.get("jzfs");
				list.add(hzsj);
				map.put("jzfs", list);
			}
		}
		// 收入项目
		if (srxm1 != null && !"".equals(srxm1)) {
			HZShuJuVO hzsj = new HZShuJuVO();
			hzsj.setPk_group(group);
			hzsj.setIszd("N");
			hzsj.setPk_org(org);
			hzsj.setPk_fdept(fdept);
			hzsj.setHzdate(new UFDate(tzdate));
			hzsj.setPk_dept(dept);
			hzsj.setLx_id(jzfs1);
			hzsj.setLx_id(srxm1);
			hzsj.setJine(new UFDouble(srxm_jine1));
			if (!map.containsKey("srxm")) {
				List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
				list.add(hzsj);
				map.put("srxm", list);
			} else {
				List<HZShuJuVO> list = map.get("srxm");
				list.add(hzsj);
				map.put("srxm", list);
			}
		}
		if (srxm2 != null && !"".equals(srxm2)) {
			HZShuJuVO hzsj = new HZShuJuVO();
			hzsj.setPk_group(group);
			hzsj.setIszd("N");
			hzsj.setPk_org(org);
			hzsj.setPk_fdept(fdept);
			hzsj.setHzdate(new UFDate(tzdate));
			hzsj.setPk_dept(dept);
			hzsj.setLx_id(jzfs1);
			hzsj.setLx_id(srxm2);
			hzsj.setJine(new UFDouble(srxm_jine2));
			if (!map.containsKey("srxm")) {
				List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
				list.add(hzsj);
				map.put("srxm", list);
			} else {
				List<HZShuJuVO> list = map.get("srxm");
				list.add(hzsj);
				map.put("srxm", list);
			}
		}
	}

	/**
	 * 分析数据，并且构造收入底表信息
	 * 
	 * @throws DAOException
	 * */
	private SrdibiaoBillVO[] handlerHzshujuLeftAndRight(
			Map<String, List<HZShuJuVO>> map_hzshuju, RzmxHVO rzmxvo,
			SgsjInfoVO[] sgsjvos, RsbaogaoCVO_YY[] rstz, CctzVO_YY[] cctz,
			boolean isdept, String[] hvoinfo) throws DAOException {
		if (map_hzshuju.size() > 0) {
			// 获取结账方式
			List<JzfsHVO> list_jzfs = getJzfsInfo();
			// 获取收入项目
			List<SrxmHVO> list_srxm = getSrxmInfo();

			// 收入项目主键与详细信息对应关系
			Map<String, SrxmHVO> map_pk_srxm = new HashMap<String, SrxmHVO>();
			for (int i = 0; i < list_srxm.size(); i++) {
				SrxmHVO srxm = list_srxm.get(i);
				String srxm_pk = srxm.getPk_hk_srgk_hg_srxm();
				map_pk_srxm.put(srxm_pk, srxm);
			}
			// 处理结账方式
			List<HZShuJuVO> list_jzfsvo = map_hzshuju.get("jzfs");
			// 处理收入项目
			List<HZShuJuVO> list_srxmvo = map_hzshuju.get("srxm");
			// 处理客户消费客户往来款
			List<HZShuJuVO> list_xfwlk = map_hzshuju.get("xfwlk");
			// 计算散客押金
			UFDouble skyj = computeSKYJJine(map_hzshuju);
			// 构造左半部分信息
			List<SrdibiaoBVO> jzfsvos = handlerLeftJzfsInfo(list_jzfs,
					list_jzfsvo, list_xfwlk, skyj);
			Map<String, UFDouble> map_jine = new HashMap<String, UFDouble>();
			Map<String, String> map_dept_vdef = new HashMap<String, String>();
			Map<String, DeptVO> map_deptinfo = new HashMap<String, DeptVO>();
			Map<String, String[]> vdefdeptinfo = new HashMap<String, String[]>();
			if (isdept) {

				// 处理所有部门信息金额
				map_jine = getAllDeptInfo(list_srxmvo, sgsjvos, cctz, rstz);
				// 部门和自定义项的对照关系
				Set<String> set = map_jine.keySet();
				Iterator<String> it = set.iterator();
				int temp = 0;
				// 此处处理末级部门对应关系
				StringBuffer sqlwhere = new StringBuffer();
				while (it.hasNext()) {
					String key = it.next();
					String dept = key.substring(0, 20);

					if (!map_dept_vdef.containsKey(dept)) {
						temp = temp + 1;
						String temp_str = String.valueOf(temp);
						if (temp_str.length() == 1) {
							temp_str = "0" + temp_str;
						}
						map_dept_vdef.put(dept, "shouru_bm" + temp_str);
						sqlwhere.append("'" + dept + "',");
					}
				}
				sqlwhere.deleteCharAt(sqlwhere.length() - 1);
				map_deptinfo = getDeptInfo(sqlwhere.toString());
				// 处理返回到前台的部门对应自定义项的信息
				Set<String> set_dept_vdef = map_dept_vdef.keySet();
				Iterator<String> it_dept_vdef = set_dept_vdef.iterator();
				while (it_dept_vdef.hasNext()) {
					String key = it_dept_vdef.next();
					// 0自定义项1部门名称2部门编码
					String[] str = new String[] { map_dept_vdef.get(key),
							map_deptinfo.get(key).getName(),
							map_deptinfo.get(key).getCode() };
					vdefdeptinfo.put(key, str);
				}
				// 处理增加部门上级收入项目信息
				Set<String> set1 = map_jine.keySet();
				Iterator<String> it1 = set1.iterator();
				Map<String, UFDouble> map_jine_parent = new HashMap<String, UFDouble>();
				Map<String, UFDouble> map_jine_dept = new HashMap<String, UFDouble>();
				while (it1.hasNext()) {
					String key = it1.next();
					String dept = key.substring(0, 20);
					String srxm = key.substring(20, 40);
					UFDouble money = map_jine.get(key);
					// 处理部门合计
					setToalMoneyForDept(money, map_jine_dept, dept);
					handlerUpSrxmJineForDept(money, srxm, dept,
							map_jine_parent, map_pk_srxm);
				}
				// 最后将map_jine 和 map_jine_parent合并
				Set<String> set_parent = map_jine_parent.keySet();
				Iterator<String> it_parnet = set_parent.iterator();
				while (it_parnet.hasNext()) {
					String key = it_parnet.next();
					map_jine.put(key, map_jine_parent.get(key));
				}
				Set<String> set_dept = map_jine_dept.keySet();
				Iterator<String> it_dept = set_dept.iterator();
				while (it_dept.hasNext()) {
					String key = it_dept.next();
					map_jine.put(key, map_jine_dept.get(key));
				}
			}
			// 构造右半部分信息
			List<SrdibiaoBVO> srxmvos = handlerRightSrxmInfo(list_srxm,
					list_srxmvo, map_jine, map_dept_vdef, map_pk_srxm);

			// 左右数据结合
			SrdibiaoBVO[] srdibiao = handlerLeftAndRightInfo(jzfsvos, srxmvos,
					rzmxvo);
			// 构造聚合VO
			SrdibiaoBillVO srdbvo = new SrdibiaoBillVO();
			SrdibiaoHVO srdbhvo = new SrdibiaoHVO();
			srdbhvo.setVdefdeptinfo(vdefdeptinfo);
			// srdbhvo.setPk_group(pk_group);
			// 处理表头自定义项1 存放表体自定义项目与部门名称对应关系
			setSrdbHVdef_DeptInfo(vdefdeptinfo, srdbhvo);
			srdbhvo.setPk_dept(hvoinfo[2]);
			srdbhvo.setPk_org(hvoinfo[0]);
			srdbhvo.setPk_org_v(hvoinfo[1]);
			srdbhvo.setDbilldate(new UFDate(hvoinfo[3]));
			srdbhvo.setIbillstatus(-1);
			srdbvo.setParentVO(srdbhvo);
			srdbvo.setChildrenVO(srdibiao);
			return new SrdibiaoBillVO[] { srdbvo };
		}
		return null;
	}

	/**
	 * 处理表头自定义项1 存放表体自定义项目与部门名称对应关系
	 * */
	private void setSrdbHVdef_DeptInfo(Map<String, String[]> vdefdeptinfo,
			SrdibiaoHVO hvo) {
		if (vdefdeptinfo != null && vdefdeptinfo.size() > 0) {
			StringBuffer sb = new StringBuffer();
			Set<String> set = vdefdeptinfo.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String[] defdeptinfo = vdefdeptinfo.get(key);
				String vdefname = defdeptinfo[0];
				String deptname = defdeptinfo[1];
				String deptcode = defdeptinfo[2];
				sb.append(vdefname + "=" + key + "、" + deptcode + "、"
						+ deptname + ",");
			}
			hvo.setVdef01(sb.toString());
		}
	}

	/**
	 * 营业日报部门合计
	 * */
	private void setToalMoneyForDept(UFDouble money,
			Map<String, UFDouble> map_jine, String dept) {

		money = getNull_Zero(money);
		// 处理合计信息
		if (!map_jine.containsKey(dept + "heji")) {
			map_jine.put(dept + "heji", money);
		} else {
			map_jine.put(dept + "heji",
					getNull_Zero(map_jine.get(dept + "heji")).add(money));
		}

	}

	/**
	 * 左右子表数据合并处理
	 * */
	private SrdibiaoBVO[] handlerLeftAndRightInfo(List<SrdibiaoBVO> jzfsvos,
			List<SrdibiaoBVO> srxmvos, RzmxHVO rzmxvo) {
		if (jzfsvos != null && srxmvos != null) {
			SrdibiaoBVO srxmbvo_column = new SrdibiaoBVO();
			String[] colums = srxmbvo_column.getAttributeNames();
			Set<String> jzsfcolumn = getJzfsColum();
			if (jzfsvos.size() > srxmvos.size()) {
				for (int i = 0; i < srxmvos.size(); i++) {
					SrdibiaoBVO srxmbvo = srxmvos.get(i);
					// 最后一行为合计行
					if (i != srxmvos.size() - 1) {
						for (int j = 0; j < colums.length; j++) {
							String column = colums[j];
							if (!jzsfcolumn.contains(column)) {
								jzfsvos.get(i).setAttributeValue(column,
										srxmbvo.getAttributeValue(column));
							}
						}
					} else {
						for (int j = 0; j < colums.length; j++) {
							String column = colums[j];
							if (!jzsfcolumn.contains(column)) {
								jzfsvos.get(jzfsvos.size() - 1)
										.setAttributeValue(
												column,
												srxmbvo.getAttributeValue(column));
							}
						}
					}

				}
				// 最后处理平均房价等信息
				if (rzmxvo != null) {
					SrdibiaoBVO srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("翻房率");
					srdbvo.setJine(rzmxvo.getFfl());
					jzfsvos.add(srdbvo);
					srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("平均房价");
					srdbvo.setJine(rzmxvo.getPjfj());
					jzfsvos.add(srdbvo);
					srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("REVPAR");
					srdbvo.setJine(rzmxvo.getRevpar());
					jzfsvos.add(srdbvo);
				}
				return jzfsvos.toArray(new SrdibiaoBVO[] {});
			} else {
				for (int i = 0; i < jzfsvos.size(); i++) {
					SrdibiaoBVO jzfsbvo = jzfsvos.get(i);
					if (i != jzfsvos.size() - 1) {
						for (int j = 0; j < colums.length; j++) {
							String column = colums[j];
							if (jzsfcolumn.contains(column)) {
								srxmvos.get(i).setAttributeValue(column,
										jzfsbvo.getAttributeValue(column));
							}
						}
					} else {
						for (int j = 0; j < colums.length; j++) {
							String column = colums[j];
							if (jzsfcolumn.contains(column)) {
								srxmvos.get(srxmvos.size() - 1)
										.setAttributeValue(
												column,
												jzfsbvo.getAttributeValue(column));
							}
						}
					}
				}
				// 最后处理平均房价等信息
				if (rzmxvo != null) {
					SrdibiaoBVO srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("翻房率");
					srdbvo.setJine(rzmxvo.getFfl());
					srxmvos.add(srdbvo);
					srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("平均房价");
					srdbvo.setJine(rzmxvo.getPjfj());
					srxmvos.add(srdbvo);
					srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("REVPAR");
					srdbvo.setJine(rzmxvo.getRevpar());
					srxmvos.add(srdbvo);
				}

				return srxmvos.toArray(new SrdibiaoBVO[] {});
			}
		}
		return null;
	}

	/**
	 * 结账方式相关字段
	 * */
	private Set<String> getJzfsColum() {
		Set<String> set = new HashSet<String>();
		set.add(SrdibiaoBVO.JINE);
		set.add(SrdibiaoBVO.JZFS_CODE);
		set.add(SrdibiaoBVO.JZFS_NAME);
		set.add(SrdibiaoBVO.JZFS_PK);
		return set;
	}

	/**
	 * 按组织查询营业日报部门信息的处理
	 * */
	private Map<String, UFDouble> getAllDeptInfo(List<HZShuJuVO> list_srxmvo,
			SgsjInfoVO[] sgsj, CctzVO_YY[] cctz, RsbaogaoCVO_YY[] rsbg) {
		Map<String, UFDouble> map = new HashMap<String, UFDouble>();
		if (list_srxmvo != null && list_srxmvo.size() > 0) {
			for (int i = 0; i < list_srxmvo.size(); i++) {
				HZShuJuVO hzsj = list_srxmvo.get(i);
				String iszd = hzsj.getIszd();
				String dept = hzsj.getPk_dept();
				UFDouble jine = getNull_Zero(hzsj.getJine());
				String pk_srxm = hzsj.getLx_id();
				String key = dept + pk_srxm;
				if (!"N".equals(iszd)) {
					// 处理手工数据部门收入项目信息
					if (!map.containsKey(key)) {
						map.put(key, jine);
					} else {
						map.put(key, getNull_Zero(map.get(key)).add(jine));
					}
				}
			}
		}
		/**
		 * 李彬 改    2016年3月15日17:43:47
		 * 之前是 放在循环里， 导致 数据异常的多
		 */
		handlerAllDeptSrxm(sgsj, map);
		// 处理差错调整数据部门收入项目信息
		handlerAllDeptSrxm(cctz, map);
		// 处理日审报告数据部门收入项目信息
		handlerAllDeptSrxm(rsbg, map);
		
		return map;
	}

	/**
	 * 处理营业日报部门收入项目手工数据
	 * */
	private void handlerAllDeptSrxm(SuperVO[] sgsj, Map<String, UFDouble> map) {
		if (sgsj != null && sgsj.length > 0) {
			for (int j = 0; j < sgsj.length; j++) {
				SuperVO sgsjvo = sgsj[j];
				String sgsjdept = null;
				if (sgsjvo instanceof SgsjInfoVO) {
					sgsjdept = PuPubVO.getString_TrimZeroLenAsNull(sgsjvo.getAttributeValue("Pk_dept") );
				} else {
					sgsjdept = PuPubVO.getString_TrimZeroLenAsNull(sgsjvo.getAttributeValue("bm_pk"));
				}

				String sgsj_srxm1 = PuPubVO.getString_TrimZeroLenAsNull(sgsjvo
						.getAttributeValue("Tz_km_srxm_1"));
				String sgsj_srxm2 = PuPubVO.getString_TrimZeroLenAsNull(sgsjvo
						.getAttributeValue("Tz_km_srxm_2"));
				String sgsj_data1 = PuPubVO.getString_TrimZeroLenAsNull(sgsjvo
						.getAttributeValue("Tz_km_data_1"));
				String sgsj_data2 = PuPubVO.getString_TrimZeroLenAsNull(sgsjvo
						.getAttributeValue("Tz_km_data_2"));
				if (sgsjdept != null) {
					if (sgsj_srxm1 != null && !"".equals(sgsj_srxm1)) {
						String key = sgsjdept + sgsj_srxm1;
						if (!map.containsKey(key)) {
							map.put(key, new UFDouble(sgsj_data1));
						} else {
							map.put(key,
									getNull_Zero(map.get(key)).add(
											new UFDouble(sgsj_data1)));
						}
					}
					if (sgsj_srxm2 != null && !"".equals(sgsj_srxm2)) {
						String key = sgsjdept + sgsj_srxm2;
						if (!map.containsKey(key)) {
							map.put(key, new UFDouble(sgsj_data2));
						} else {
							map.put(key,
									getNull_Zero(map.get(key)).add(
											new UFDouble(sgsj_data2)));
						}
					}
				}

			}
		}

	}

	/**
	 * 计算散客押金结账方式
	 * */
	private UFDouble computeSKYJJine(Map<String, List<HZShuJuVO>> map_hzshuju) {
		UFDouble jzfsjine = UFDouble.ZERO_DBL;
		UFDouble srxmjine = UFDouble.ZERO_DBL;
		Set<String> set = map_hzshuju.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			List<HZShuJuVO> hzshujuvos = map_hzshuju.get(key);
			if (hzshujuvos != null && hzshujuvos.size() > 0) {
				for (int i = 0; i < hzshujuvos.size(); i++) {
					HZShuJuVO hzsj = hzshujuvos.get(i);
					UFDouble jine = hzsj.getJine();
					String iszd = hzsj.getIszd();
					// 如果来源不是账单的就不计算
					if (!"N".equals(iszd)) {
						// 结账方式和客户消费往来款
						if (key.equals("jzfs") || key.equals("xfwlk")) {
							jzfsjine = jzfsjine.add(jine);
						} else if (key.equals("srxm")) {
							// 收入项目金额
							srxmjine = srxmjine.add(jine);
						}
					}
				}
			}
		}
		return srxmjine.sub(jzfsjine);
	}

	/**
	 * 处理左半部分结账方式数据和特殊的客户消费往来款
	 * */
	private List<SrdibiaoBVO> handlerLeftJzfsInfo(List<JzfsHVO> list_jzfs,
			List<HZShuJuVO> list_jzfsvo, List<HZShuJuVO> list_xfwlk,
			UFDouble skyj) {
		List<SrdibiaoBVO> list_srbvo = new ArrayList<SrdibiaoBVO>();
		if (list_jzfs != null && list_jzfs.size() > 0) {

			Map<String, UFDouble> map_jzfs_jine = new HashMap<String, UFDouble>();
			Map<String, JzfsHVO> map_pk_jzfs = new HashMap<String, JzfsHVO>();
			// 结账方式PK与详细信息的对应关系
			for (int i = 0; i < list_jzfs.size(); i++) {
				JzfsHVO jzfs_vo = list_jzfs.get(i);
				String pk_jzfs = jzfs_vo.getPk_hk_srgk_hg_jzfs();
				map_pk_jzfs.put(pk_jzfs, jzfs_vo);
			}

			// 处理消费往来款金额
			if (list_xfwlk != null && list_xfwlk.size() > 0) {
				for (int i = 0; i < list_xfwlk.size(); i++) {
					HZShuJuVO xfwlk = list_xfwlk.get(i);
					String jzfs_id = xfwlk.getLx_id();
					UFDouble money = getNull_Zero(xfwlk.getJine());
					// 处理合计信息
					setTotalMoney(map_jzfs_jine, money);

					if (!map_jzfs_jine.containsKey(jzfs_id)) {
						map_jzfs_jine.put(jzfs_id, money);
					} else {
						map_jzfs_jine.put(jzfs_id, map_jzfs_jine.get(jzfs_id)
								.add(money));
					}
					// 处理上级结账方式信息
					handlerUpJzfsJine(money, jzfs_id, map_jzfs_jine,
							map_pk_jzfs);
				}
			}

			// 处理结账方式PK 与 金额的对应关系
			if (list_jzfsvo != null && list_jzfsvo.size() > 0) {
				for (int i = 0; i < list_jzfsvo.size(); i++) {
					HZShuJuVO hzshuju = list_jzfsvo.get(i);
					String jzfs_id = hzshuju.getLx_id();
					UFDouble money = getNull_Zero(hzshuju.getJine());
					// 处理合计信息
					setTotalMoney(map_jzfs_jine, money);
					if (!map_jzfs_jine.containsKey(jzfs_id)) {
						map_jzfs_jine.put(jzfs_id, money);
					} else {
						map_jzfs_jine.put(jzfs_id, map_jzfs_jine.get(jzfs_id)
								.add(money));
					}
					// 处理上级结账方式信息
					handlerUpJzfsJine(money, jzfs_id, map_jzfs_jine,
							map_pk_jzfs);
				}

			}
			// 处理散客押金
			for (int i = 0; i < list_jzfs.size(); i++) {
				JzfsHVO jzfs_vo = list_jzfs.get(i);
				// 必须在这处理 首先处理散客押金
				if ("散客押金".equals(jzfs_vo.getName())) {
					map_jzfs_jine.put(jzfs_vo.getPk_hk_srgk_hg_jzfs(), skyj);
					// 处理合计信息
					setTotalMoney(map_jzfs_jine, skyj);
					handlerUpJzfsJine(skyj, jzfs_vo.getPk_hk_srgk_hg_jzfs(),
							map_jzfs_jine, map_pk_jzfs);
					break;
				}
			}
			// 构造新的数据收入底表结账VO
			for (int i = 0; i < list_jzfs.size(); i++) {
				JzfsHVO jzfs_vo = list_jzfs.get(i);
				SrdibiaoBVO srdibiaobvo = new SrdibiaoBVO();
				int levelno = jzfs_vo.getLevelno();
				String jzsfname = jzfs_vo.getName();
				if (levelno != 1) {
					for (int j = 0; j < levelno; j++) {
						jzsfname = "  " + jzsfname;
					}
				}
				srdibiaobvo.setJzfs_name(jzsfname);
				srdibiaobvo.setJzfs_code(jzfs_vo.getCode());
				srdibiaobvo.setJzfs_pk(jzfs_vo.getPk_hk_srgk_hg_jzfs());
				srdibiaobvo.setJine(getNull_Zero(map_jzfs_jine.get(jzfs_vo
						.getPk_hk_srgk_hg_jzfs())));
				list_srbvo.add(srdibiaobvo);
				// 如果是消费客户往来款进行新增行
				if ("消费客户往来款".equals(jzfs_vo.getName())) {
					if (list_xfwlk != null && list_xfwlk.size() > 0) {
						for (int j = 0; j < list_xfwlk.size(); j++) {
							HZShuJuVO xfwlk = list_xfwlk.get(j);
							srdibiaobvo = new SrdibiaoBVO();
							String khmz = xfwlk.getKhmz();
							int levelno1 = levelno + 1;
							for (int k = 0; k < levelno1; k++) {
								khmz = "   " + khmz;

							}
							srdibiaobvo.setJzfs_name(khmz);
							srdibiaobvo.setJine(xfwlk.getJine());
							// 自动增加的这几行为了生成凭证还需要赋值几个字段
							srdibiaobvo.setPk_fjzfs(xfwlk.getLx_id());
							srdibiaobvo.setIswanglai("Y");
							list_srbvo.add(srdibiaobvo);
						}
					}

				}
			}
			// 最后增加行合计
			SrdibiaoBVO srdibiaobvo = new SrdibiaoBVO();
			list_srbvo.add(srdibiaobvo);
			srdibiaobvo = new SrdibiaoBVO();
			list_srbvo.add(srdibiaobvo);
			srdibiaobvo = new SrdibiaoBVO();
			srdibiaobvo.setJzfs_name("合计");
			srdibiaobvo.setJine(map_jzfs_jine.get("heji"));
			list_srbvo.add(srdibiaobvo);
		}
		return list_srbvo;
	}

	/**
	 * 处理合计信息
	 * */
	private void setTotalMoney(Map<String, UFDouble> map_heji, UFDouble money) {
		money = getNull_Zero(money);
		// 处理合计信息
		if (!map_heji.containsKey("heji")) {
			map_heji.put("heji", money);
		} else {
			map_heji.put("heji", getNull_Zero(map_heji.get("heji")).add(money));
		}
	}

	/**
	 * 构造右半部分收入底表数据信息
	 * */
	private List<SrdibiaoBVO> handlerRightSrxmInfo(List<SrxmHVO> list_srxm,
			List<HZShuJuVO> list_srxmvo, Map<String, UFDouble> map_jine,
			Map<String, String> map_dept_vdef, Map<String, SrxmHVO> map_pk_srxm) {
		List<SrdibiaoBVO> list_srbvo = new ArrayList<SrdibiaoBVO>();
		if (list_srxm != null && list_srxm.size() > 0) {
			// 收入项目主键与详细信息对应关系
			// Map<String, SrxmHVO> map_pk_srxm = new HashMap<String,
			// SrxmHVO>();
			// for (int i = 0; i < list_srxm.size(); i++) {
			// SrxmHVO srxm = list_srxm.get(i);
			// String srxm_pk = srxm.getPk_hk_srgk_hg_srxm();
			// map_pk_srxm.put(srxm_pk, srxm);
			// }
			// 收入项目主键与金额对应信息
			Map<String, UFDouble> map_srxm_jine = new HashMap<String, UFDouble>();
			if (list_srxmvo != null && list_srxmvo.size() > 0) {
				for (int i = 0; i < list_srxmvo.size(); i++) {
					HZShuJuVO srxmvo = list_srxmvo.get(i);
					String pk_srxm = srxmvo.getLx_id();
					UFDouble money = srxmvo.getJine();
					// 处理合计
					setTotalMoney(map_srxm_jine, money);
					if (!map_srxm_jine.containsKey(pk_srxm)) {
						map_srxm_jine.put(pk_srxm, money);
					} else {
						map_srxm_jine.put(pk_srxm, map_srxm_jine.get(pk_srxm)
								.add(money));
					}
					// 处理上级金额
					handlerUpSrxmJine(money, pk_srxm, map_srxm_jine,
							map_pk_srxm);
				}
			}

			for (int i = 0; i < list_srxm.size(); i++) {
				SrxmHVO srxm = list_srxm.get(i);
				String srxm_pk = srxm.getPk_hk_srgk_hg_srxm();
				UFDouble money = map_srxm_jine.get(srxm_pk);
				int levelno = srxm.getLevelno();
				String srxmname = srxm.getName();
				if (levelno != 1) {
					for (int j = 0; j < levelno; j++) {
						srxmname = "  " + srxmname;
					}
				}
				if (map_srxm_jine.containsKey(srxm_pk)) {
					SrdibiaoBVO srdbvo = new SrdibiaoBVO();
					srdbvo.setSrmx_code(srxm.getCode());
					srdbvo.setSrmx_name(srxmname);
					srdbvo.setSrmx_pk(srxm.getPk_hk_srgk_hg_srxm());
					srdbvo.setYingshou(money);
					// 此处处理自定义字段信息
					if (map_dept_vdef.size() > 0) {
						Set<String> set = map_dept_vdef.keySet();
						Iterator<String> it = set.iterator();
						while (it.hasNext()) {
							String key = it.next();
							String vdefcolumn = map_dept_vdef.get(key);
							String deptmoney = key
									+ srxm.getPk_hk_srgk_hg_srxm();
							srdbvo.setAttributeValue(vdefcolumn,
									map_jine.get(deptmoney));
						}
					}
					list_srbvo.add(srdbvo);
				}

			}
			// 最后增加行合计
			SrdibiaoBVO srdibiaobvo = new SrdibiaoBVO();
			list_srbvo.add(srdibiaobvo);
			srdibiaobvo = new SrdibiaoBVO();
			list_srbvo.add(srdibiaobvo);
			srdibiaobvo = new SrdibiaoBVO();
			srdibiaobvo.setSrmx_name("合计");
			srdibiaobvo.setYingshou(map_srxm_jine.get("heji"));
			if (map_dept_vdef.size() > 0) {
				Set<String> set = map_dept_vdef.keySet();
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					String key = it.next();
					String vdefcolumn = map_dept_vdef.get(key);
					String deptmoney = key + "heji";
					srdibiaobvo.setAttributeValue(vdefcolumn,
							map_jine.get(deptmoney));
				}
			}
			list_srbvo.add(srdibiaobvo);
		}
		return list_srbvo;
	}

	/**
	 * 递归处理合计上级结账方式金额
	 * */
	private void handlerUpJzfsJine(UFDouble money, String jzfs_id,
			Map<String, UFDouble> map_jzfs_jine,
			Map<String, JzfsHVO> map_pk_jzfs) {
		// 获取上级结账方式信息
		JzfsHVO jzfsh = map_pk_jzfs.get(jzfs_id);
		String upjzfspk = null;
		if (jzfsh != null) {
			upjzfspk = jzfsh.getPk_parent();
		}
		money = getNull_Zero(money);
		if (upjzfspk != null && !"".equals(upjzfspk) && !"~".equals(upjzfspk)) {
			if (!map_jzfs_jine.containsKey(upjzfspk)) {
				map_jzfs_jine.put(upjzfspk, money);
			} else {
				map_jzfs_jine.put(upjzfspk,
						getNull_Zero(map_jzfs_jine.get(upjzfspk)).add(money));
			}
			// 如果还有上级信息那么递归再进行上级赋值
			handlerUpJzfsJine(money, upjzfspk, map_jzfs_jine, map_pk_jzfs);
		}

	}

	/**
	 * 递归处理上级收入项目合计金额
	 * */
	private void handlerUpSrxmJine(UFDouble money, String srxm_id,
			Map<String, UFDouble> map_srxm_jine,
			Map<String, SrxmHVO> map_pk_srxm) {
		// 获取上级收入项目信息
		SrxmHVO srxmh = map_pk_srxm.get(srxm_id);
		String upsrxmpk = null;
		if (srxmh != null) {
			upsrxmpk = srxmh.getPk_parent();
		}
		money = getNull_Zero(money);
		if (upsrxmpk != null && !"".equals(upsrxmpk) && !"~".equals(upsrxmpk)) {
			if (!map_srxm_jine.containsKey(upsrxmpk)) {
				map_srxm_jine.put(upsrxmpk, money);
			} else {
				map_srxm_jine.put(upsrxmpk,
						getNull_Zero(map_srxm_jine.get(upsrxmpk)).add(money));
			}
			// 如果还有上级信息那么递归再进行上级赋值
			handlerUpSrxmJine(money, upsrxmpk, map_srxm_jine, map_pk_srxm);
		}
	}

	/**
	 * 递归处理部门上级收入项目
	 * */
	private void handlerUpSrxmJineForDept(UFDouble money, String srxm_id,
			String pk_dept, Map<String, UFDouble> map_deptsrxm_jine,
			Map<String, SrxmHVO> map_pk_srxm) {

		// 获取上级收入项目信息
		SrxmHVO srxmh = map_pk_srxm.get(srxm_id);
		String upsrxmpk = null;
		if (srxmh != null) {
			upsrxmpk = srxmh.getPk_parent();
		}
		String dept_upsrxmpk = pk_dept + upsrxmpk;
		money = getNull_Zero(money);
		if (upsrxmpk != null && !"".equals(upsrxmpk) && !"~".equals(upsrxmpk)) {
			if (!map_deptsrxm_jine.containsKey(dept_upsrxmpk)) {
				map_deptsrxm_jine.put(dept_upsrxmpk, money);
			} else {
				map_deptsrxm_jine.put(
						dept_upsrxmpk,
						getNull_Zero(map_deptsrxm_jine.get(dept_upsrxmpk)).add(
								money));
			}
			// 如果还有上级信息那么递归再进行上级赋值
			handlerUpSrxmJineForDept(money, upsrxmpk, pk_dept,
					map_deptsrxm_jine, map_pk_srxm);
		}

	}

	/**
	 * 查询结账方式，以及排序
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private List<JzfsHVO> getJzfsInfo() throws DAOException {
		// 结账方式根据康复瑞西山店组织过滤
		String sql = "select jzfs.*,jzfs_f.name vdef5 from hk_srgk_hg_jzfs jzfs left join hk_srgk_hg_jzfs jzfs_f on jzfs.pk_parent=jzfs_f.pk_hk_srgk_hg_jzfs where nvl(jzfs.dr,0)=0 and jzfs.pk_org='"
				+ HKJT_PUB.PK_ORG_JIUDIAN + "' order by jzfs.code";
		List<JzfsHVO> list = (List<JzfsHVO>) getBD().executeQuery(sql,
				new BeanListProcessor(JzfsHVO.class));
		return list;
	}

	/**
	 * 查询收入项目，以及排序
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private List<SrxmHVO> getSrxmInfo() throws DAOException {
		// 结账方式根据康复瑞西山店组织过滤
		String sql = "select * from hk_srgk_hg_srxm where nvl(dr,0)=0 and pk_org='"
				+ HKJT_PUB.PK_ORG_JIUDIAN + "' order by code";
		List<SrxmHVO> list = (List<SrxmHVO>) getBD().executeQuery(sql,
				new BeanListProcessor(SrxmHVO.class));
		return list;
	}

	// 获取手工数据
	/**
	 * 根据组织+日期+部门查询 手工数据信息
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private SgsjInfoVO[] getSgsjInfoVOs(String first_org, String pk_org,
			String pk_dept, String begindate, String enddate, String isShowDept,UFBoolean isjd)
			throws DAOException {
		StringBuffer sb = new StringBuffer();
		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append("select sgsj.pk_group,'"
					+ first_org
					+ "' as pk_org,'"
					+ begindate.substring(0, 10)
					+ " 00:00:00' hzdate,sgsjb.bm_pk pk_dept,dept.pk_fatherorg dept_fpk,sgsjb.tz_km_jzfs_1,");
			sb.append("sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sum(sgsjb.tz_km_data_1) tz_km_data_1,sgsjb.tz_km_jzfs_2,sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,");
			sb.append("sum(sgsjb.tz_km_data_2) tz_km_data_2,srxm1.pk_parent srxm_fpk1,srxm2.pk_parent srxm_fpk2,jzfs1.pk_parent jzfs_fpk1,jzfs2.pk_parent jzfs_fpk2");
			sb.append(" from hk_srgk_hg_sgshuju sgsj ");
			sb.append(" left join hk_srgk_hg_sgshuju_b sgsjb on sgsj.pk_hk_srgk_hg_sgshuju = sgsjb.pk_hk_srgk_hg_sgshuju ");
			sb.append(" left join org_dept dept on dept.pk_dept=sgsjb.bm_pk left join hk_srgk_hg_srxm srxm1 on srxm1.pk_hk_srgk_hg_srxm=sgsjb.tz_km_srxm_1 ");
			sb.append("left join hk_srgk_hg_srxm srxm2 on srxm2.pk_hk_srgk_hg_srxm=sgsjb.tz_km_srxm_2 left join hk_srgk_hg_jzfs jzfs1 on jzfs1.pk_hk_srgk_hg_jzfs=sgsjb.tz_km_jzfs_1 ");
			sb.append("left join hk_srgk_hg_jzfs jzfs2 on jzfs2.pk_hk_srgk_hg_jzfs=sgsjb.tz_km_jzfs_2 ");
			sb.append("where (sgsjb.zd_pk is null or sgsjb.zd_pk='~') and nvl(sgsj.dr,0)=0 and nvl(sgsjb.dr,0)=0 and sgsj.ibillstatus=1 ");
			sb.append(" and to_date(substr(sgsj.dbilldate,0,10),'yyyy-mm-dd hh24:mi:ss')>=to_date('"
					+ begindate + "','yyyy-mm-dd hh24:mi:ss')");
			sb.append(" and to_date(substr(sgsj.dbilldate,0,10),'yyyy-mm-dd hh24:mi:ss')<=to_date('"
					+ enddate + "','yyyy-mm-dd hh24:mi:ss')");
			sb.append(" and sgsj.pk_org in ("
					+ pk_org
					+ ") and  sgsjb.bm_pk in (select dept.pk_dept  from org_dept dept  where dept.def1 is not null and dept.def1 <> '~' connect by dept.pk_fatherorg = prior dept.pk_dept  start with dept.pk_dept in ('"
					+ pk_dept + "')) ");
			sb.append(" and nvl(replace(sgsj.vdef10,'~',''),'N') = '" +isjd.toString()+ "' ");	// 过滤 是否酒店 （HK 2018年11月6日17:26:25）
			sb.append("group by sgsj.pk_group,sgsjb.bm_pk,sgsjb.tz_km_jzfs_1,sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sgsjb.tz_km_jzfs_2,");
			sb.append("sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,srxm1.pk_parent,srxm2.pk_parent,jzfs1.pk_parent,jzfs2.pk_parent,dept.pk_fatherorg");
		} else {
			if ("Y".equals(isShowDept)) {
				sb.append("select sgsj.pk_group,'"
						+ first_org
						+ "' as pk_org,'"
						+ begindate.substring(0, 10)
						+ " 00:00:00' hzdate,sgsjb.bm_pk pk_dept,dept.pk_fatherorg dept_fpk,sgsjb.tz_km_jzfs_1,");
				sb.append("sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sum(sgsjb.tz_km_data_1) tz_km_data_1,sgsjb.tz_km_jzfs_2,sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,");
				sb.append("sum(sgsjb.tz_km_data_2) tz_km_data_2,srxm1.pk_parent srxm_fpk1,srxm2.pk_parent srxm_fpk2,jzfs1.pk_parent jzfs_fpk1,jzfs2.pk_parent jzfs_fpk2");
				sb.append(" from hk_srgk_hg_sgshuju sgsj ");
				sb.append(" left join hk_srgk_hg_sgshuju_b sgsjb on sgsj.pk_hk_srgk_hg_sgshuju = sgsjb.pk_hk_srgk_hg_sgshuju ");
				sb.append(" left join org_dept dept on dept.pk_dept=sgsjb.bm_pk left join hk_srgk_hg_srxm srxm1 on srxm1.pk_hk_srgk_hg_srxm=sgsjb.tz_km_srxm_1 ");
				sb.append("left join hk_srgk_hg_srxm srxm2 on srxm2.pk_hk_srgk_hg_srxm=sgsjb.tz_km_srxm_2 left join hk_srgk_hg_jzfs jzfs1 on jzfs1.pk_hk_srgk_hg_jzfs=sgsjb.tz_km_jzfs_1 ");
				sb.append("left join hk_srgk_hg_jzfs jzfs2 on jzfs2.pk_hk_srgk_hg_jzfs=sgsjb.tz_km_jzfs_2 ");
				sb.append("where (sgsjb.zd_pk is null or sgsjb.zd_pk='~') and nvl(sgsj.dr,0)=0 and nvl(sgsjb.dr,0)=0 and sgsj.ibillstatus=1 ");
				sb.append(" and to_date(substr(sgsj.dbilldate,0,10),'yyyy-mm-dd hh24:mi:ss')>=to_date('"
						+ begindate + "','yyyy-mm-dd hh24:mi:ss')");
				sb.append(" and to_date(substr(sgsj.dbilldate,0,10),'yyyy-mm-dd hh24:mi:ss')<=to_date('"
						+ enddate + "','yyyy-mm-dd hh24:mi:ss')");
				sb.append(" and sgsj.pk_org in (" + pk_org + ") ");
				sb.append(" and nvl(replace(sgsj.vdef10,'~',''),'N') = '" +isjd.toString()+ "' ");	// 过滤 是否酒店 （HK 2018年11月6日17:26:25）
				sb.append("group by sgsj.pk_group,sgsjb.bm_pk,sgsjb.tz_km_jzfs_1,sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sgsjb.tz_km_jzfs_2,");
				sb.append("sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,srxm1.pk_parent,srxm2.pk_parent,jzfs1.pk_parent,jzfs2.pk_parent,dept.pk_fatherorg");
			} else {
				sb.append("select sgsj.pk_group,'" + first_org
						+ "' as pk_org,'" + begindate.substring(0, 10)
						+ " 00:00:00' hzdate,sgsjb.tz_km_jzfs_1,");
				sb.append("sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sum(sgsjb.tz_km_data_1) tz_km_data_1,sgsjb.tz_km_jzfs_2,sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,");
				sb.append("sum(sgsjb.tz_km_data_2) tz_km_data_2,srxm1.pk_parent srxm_fpk1,srxm2.pk_parent srxm_fpk2,jzfs1.pk_parent jzfs_fpk1,jzfs2.pk_parent jzfs_fpk2");
				sb.append(" from hk_srgk_hg_sgshuju sgsj ");
				sb.append(" left join hk_srgk_hg_sgshuju_b sgsjb on sgsj.pk_hk_srgk_hg_sgshuju = sgsjb.pk_hk_srgk_hg_sgshuju ");
				sb.append(" left join hk_srgk_hg_srxm srxm1 on srxm1.pk_hk_srgk_hg_srxm=sgsjb.tz_km_srxm_1 ");
				sb.append("left join hk_srgk_hg_srxm srxm2 on srxm2.pk_hk_srgk_hg_srxm=sgsjb.tz_km_srxm_2 left join hk_srgk_hg_jzfs jzfs1 on jzfs1.pk_hk_srgk_hg_jzfs=sgsjb.tz_km_jzfs_1 ");
				sb.append("left join hk_srgk_hg_jzfs jzfs2 on jzfs2.pk_hk_srgk_hg_jzfs=sgsjb.tz_km_jzfs_2 ");
				sb.append("where (sgsjb.zd_pk is null or sgsjb.zd_pk='~') and nvl(sgsj.dr,0)=0 and nvl(sgsjb.dr,0)=0 and sgsj.ibillstatus=1 ");
				sb.append(" and to_date(substr(sgsj.dbilldate,0,10),'yyyy-mm-dd hh24:mi:ss')>=to_date('"
						+ begindate + "','yyyy-mm-dd hh24:mi:ss')");
				sb.append(" and to_date(substr(sgsj.dbilldate,0,10),'yyyy-mm-dd hh24:mi:ss')<=to_date('"
						+ enddate + "','yyyy-mm-dd hh24:mi:ss')");
				sb.append(" and sgsj.pk_org in (" + pk_org + ") ");
				sb.append(" and nvl(replace(sgsj.vdef10,'~',''),'N') = '" +isjd.toString()+ "' ");	// 过滤 是否酒店 （HK 2018年11月6日17:26:25）
				sb.append("group by sgsj.pk_group,sgsjb.tz_km_jzfs_1,sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sgsjb.tz_km_jzfs_2,");
				sb.append("sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,srxm1.pk_parent,srxm2.pk_parent,jzfs1.pk_parent,jzfs2.pk_parent");
			}
		}
		List<SgsjInfoVO> list = (List<SgsjInfoVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(SgsjInfoVO.class));
		return list.toArray(new SgsjInfoVO[] {});
	}

	// 查询差错调整单
	@SuppressWarnings("unchecked")
	private CctzVO_YY[] getCctzInfo(String first_org, String pk_org,
			String pk_dept, String begindate, String enddate, String isShowDept)
			throws DAOException {

		StringBuffer sb = new StringBuffer();
		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append("select cctzh.pk_group,'" + begindate.substring(0, 10)
					+ " 00:00:00' tz_date,'" + first_org
					+ "' pk_org,cctzb.tz_km_jzfs_1,");
			sb.append("cctzb.tz_km_srxm_1,cctzb.tz_km_data_1,cctzb.tz_km_jzfs_2,cctzb.tz_km_srxm_2,");
			sb.append("cctzb.tz_km_data_2,cctzb.bm_pk,dept.pk_fatherorg pk_fdept ");
			sb.append(" from hk_srgk_hg_cctz cctzh left join hk_srgk_hg_cctz_b cctzb ");
			sb.append("on cctzh.pk_hk_srgk_hg_cctz=cctzb.pk_hk_srgk_hg_cctz left join org_dept dept ");
			sb.append("on dept.pk_dept=cctzb.bm_pk where nvl(cctzh.dr,0)=0 and nvl(cctzb.dr,0)=0 and cctzh.ibillstatus=1 ");
			sb.append("  and to_date(substr(cctzb.tz_date,0,10),'yyyy-mm-dd hh24:mi:ss')>=to_date('"
					+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
			sb.append("  and to_date(substr(cctzb.tz_date,0,10),'yyyy-mm-dd hh24:mi:ss')<=to_date('"
					+ enddate + "','yyyy-mm-dd hh24:mi:ss') ");
			sb.append(" and cctzh.pk_org in ("
					+ pk_org
					+ ") and cctzb.bm_pk in (select dept.pk_dept  from org_dept dept  where dept.def1 is not null and dept.def1 <> '~' connect by dept.pk_fatherorg = prior dept.pk_dept  start with dept.pk_dept in ('"
					+ pk_dept + "'))");
		} else {
			if ("Y".equals(isShowDept)) {
				sb.append("select cctzh.pk_group,'"
						+ begindate.substring(0, 10) + " 00:00:00' tz_date,'"
						+ first_org + "' pk_org,cctzb.tz_km_jzfs_1,");
				sb.append("cctzb.tz_km_srxm_1,cctzb.tz_km_data_1,cctzb.tz_km_jzfs_2,cctzb.tz_km_srxm_2,");
				sb.append("cctzb.tz_km_data_2,cctzb.bm_pk,dept.pk_fatherorg pk_fdept ");
				sb.append(" from hk_srgk_hg_cctz cctzh left join hk_srgk_hg_cctz_b cctzb ");
				sb.append("on cctzh.pk_hk_srgk_hg_cctz=cctzb.pk_hk_srgk_hg_cctz left join org_dept dept ");
				sb.append("on dept.pk_dept=cctzb.bm_pk where nvl(cctzh.dr,0)=0 and nvl(cctzb.dr,0)=0 and cctzh.ibillstatus=1 ");
				sb.append("  and to_date(substr(cctzb.tz_date,0,10),'yyyy-mm-dd hh24:mi:ss')>=to_date('"
						+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
				sb.append("  and to_date(substr(cctzb.tz_date,0,10),'yyyy-mm-dd hh24:mi:ss')<=to_date('"
						+ enddate + "','yyyy-mm-dd hh24:mi:ss') ");
				sb.append(" and cctzh.pk_org in (" + pk_org + ") ");
			} else {
				sb.append("select cctzh.pk_group,'"
						+ begindate.substring(0, 10) + " 00:00:00' tz_date,'"
						+ first_org + "' pk_org,cctzb.tz_km_jzfs_1,");
				sb.append("cctzb.tz_km_srxm_1,cctzb.tz_km_data_1,cctzb.tz_km_jzfs_2,cctzb.tz_km_srxm_2,");
				sb.append("cctzb.tz_km_data_2,cctzb.bm_pk ");
				sb.append(" from hk_srgk_hg_cctz cctzh left join hk_srgk_hg_cctz_b cctzb ");
				sb.append("on cctzh.pk_hk_srgk_hg_cctz=cctzb.pk_hk_srgk_hg_cctz left join org_dept dept ");
				sb.append("on dept.pk_dept=cctzb.bm_pk where nvl(cctzh.dr,0)=0 and nvl(cctzb.dr,0)=0 and cctzh.ibillstatus=1 ");
				sb.append("  and to_date(substr(cctzb.tz_date,0,10),'yyyy-mm-dd hh24:mi:ss')>=to_date('"
						+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
				sb.append("  and to_date(substr(cctzb.tz_date,0,10),'yyyy-mm-dd hh24:mi:ss')<=to_date('"
						+ enddate + "','yyyy-mm-dd hh24:mi:ss') ");
				sb.append(" and cctzh.pk_org in (" + pk_org + ")");
			}
		}
		List<CctzVO_YY> list = (List<CctzVO_YY>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(CctzVO_YY.class));
		return list.toArray(new CctzVO_YY[] {});
	}

	// 日审报告调整单
	@SuppressWarnings("unchecked")
	private RsbaogaoCVO_YY[] getRsTzInfo(String first_org, String pk_org,
			String pk_dept, String begindate, String enddate, String isShowDept)
			throws DAOException {
		StringBuffer sb = new StringBuffer();

		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append("select '" + begindate.substring(0, 10)
					+ " 00:00:00' dbilldate, '" + first_org
					+ "' pk_org, rs.pk_org_v, rs.pk_group,");
			sb.append("rstz.tz_km_jzfs_1,rstz.tz_km_srxm_1,rstz.tz_km_data_1,rstz.tz_km_jzfs_2,");
			sb.append("rstz.tz_km_srxm_2,rstz.tz_km_data_2,rstz.bm_pk,dept.pk_fatherorg pk_fdept ");
			sb.append(" from hk_srgk_hg_rsbaogao_c rstz left join hk_srgk_hg_rsbaogao rs ");
			sb.append(" on rstz.pk_hk_srgk_hg_rsbaogao = rs.pk_hk_srgk_hg_rsbaogao  left join org_dept dept on ");
			sb.append(" dept.pk_dept=rstz.bm_pk ");
			sb.append("  where rs.ibillstatus=1 and to_date(substr(rs.dbilldate, 0, 10), 'yyyy-mm-dd hh24:mi:ss') >= to_date('"
					+ begindate + "', 'yyyy-mm-dd hh24:mi:ss')");
			sb.append(" and  to_date(substr(rs.dbilldate, 0, 10), 'yyyy-mm-dd hh24:mi:ss') <= to_date('"
					+ enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
			sb.append(" and nvl(rs.dr, 0) = 0  and nvl(rstz.dr, 0) = 0");
			sb.append(" and rs.pk_org in ("
					+ pk_org
					+ ") and  rstz.bm_pk in (select dept.pk_dept  from org_dept dept  where dept.def1 is not null and dept.def1 <> '~' connect by dept.pk_fatherorg = prior dept.pk_dept  start with dept.pk_dept in ('"
					+ pk_dept + "')) ");
		} else {
			if ("Y".equals(isShowDept)) {
				sb.append("select '" + begindate.substring(0, 10)
						+ " 00:00:00' dbilldate, '" + first_org
						+ "' pk_org, rs.pk_org_v, rs.pk_group,");
				sb.append("rstz.tz_km_jzfs_1,rstz.tz_km_srxm_1,rstz.tz_km_data_1,rstz.tz_km_jzfs_2,");
				sb.append("rstz.tz_km_srxm_2,rstz.tz_km_data_2,rstz.bm_pk,dept.pk_fatherorg pk_fdept ");
				sb.append(" from hk_srgk_hg_rsbaogao_c rstz left join hk_srgk_hg_rsbaogao rs ");
				sb.append(" on rstz.pk_hk_srgk_hg_rsbaogao = rs.pk_hk_srgk_hg_rsbaogao  left join org_dept dept on ");
				sb.append(" dept.pk_dept=rstz.bm_pk ");
				sb.append("  where rs.ibillstatus=1 and to_date(substr(rs.dbilldate, 0, 10), 'yyyy-mm-dd hh24:mi:ss') >= to_date('"
						+ begindate + "', 'yyyy-mm-dd hh24:mi:ss')");
				sb.append(" and  to_date(substr(rs.dbilldate, 0, 10), 'yyyy-mm-dd hh24:mi:ss') <= to_date('"
						+ enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
				sb.append(" and nvl(rs.dr, 0) = 0  and nvl(rstz.dr, 0) = 0");
				sb.append(" and rs.pk_org in (" + pk_org + ") ");
			} else {
				sb.append("select '" + begindate.substring(0, 10)
						+ " 00:00:00' dbilldate, '" + first_org
						+ "' pk_org, rs.pk_org_v, rs.pk_group,");
				sb.append("rstz.tz_km_jzfs_1,rstz.tz_km_srxm_1,rstz.tz_km_data_1,rstz.tz_km_jzfs_2,");
				sb.append("rstz.tz_km_srxm_2,rstz.tz_km_data_2 ");
				sb.append(" from hk_srgk_hg_rsbaogao_c rstz left join hk_srgk_hg_rsbaogao rs ");
				sb.append(" on rstz.pk_hk_srgk_hg_rsbaogao = rs.pk_hk_srgk_hg_rsbaogao ");
				sb.append("  where rs.ibillstatus=1 and to_date(substr(rs.dbilldate, 0, 10), 'yyyy-mm-dd hh24:mi:ss') >= to_date('"
						+ begindate + "', 'yyyy-mm-dd hh24:mi:ss')");
				sb.append(" and  to_date(substr(rs.dbilldate, 0, 10), 'yyyy-mm-dd hh24:mi:ss') <= to_date('"
						+ enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
				sb.append(" and nvl(rs.dr, 0) = 0  and nvl(rstz.dr, 0) = 0");
				sb.append(" and rs.pk_org in (" + pk_org + ")");
			}

		}
		List<RsbaogaoCVO_YY> list = (List<RsbaogaoCVO_YY>) getBD()
				.executeQuery(sb.toString(),
						new BeanListProcessor(RsbaogaoCVO_YY.class));
		return list.toArray(new RsbaogaoCVO_YY[] {});
	}

	// 根据上级部门获取下级部门信息
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map<String, String[]> getNextDeptInfo(String pk_dept)
			throws DAOException {

		Map<String, String[]> map = new HashMap<String, String[]>();
		String sql = "select pk_dept,name,pk_fatherorg,code from org_dept where pk_fatherorg='"
				+ pk_dept
				+ "' and nvl(dr,0)=0 and def1<>'~' and def1 is not null order by code";
		Vector v = (Vector) getBD().executeQuery(sql, new VectorProcessor());
		if (v != null && v.size() > 0) {
			for (int i = 0; i < v.size(); i++) {
				Vector v_1 = (Vector) v.get(i);
				String dept = (String) v_1.elementAt(0);
				String name = (String) v_1.elementAt(1);
				String fdept = (String) v_1.elementAt(2);
				String code = (String) v_1.elementAt(3);
				String[] str = new String[4];
				if (i + 1 < 10) {
					str[0] = "shouru_bm0" + (i + 1);
				} else {
					str[0] = "shouru_bm" + (i + 1);
				}
				str[1] = name;
				str[2] = fdept;
				str[3] = code;
				map.put(dept, str);
			}
		}
		return map;

	}

	/**
	 * 获取平均房价，翻房价等信息
	 * 
	 * @throws DAOException
	 * */
	private RzmxHVO getFangJiaInfo(String pk_org, String begindate,
			String enddate) throws DAOException {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(hk_srgk_jd_rzmx.ffl) ffl, sum(hk_srgk_jd_rzmx.pjfj) pjfj, sum(hk_srgk_jd_rzmx.revpar) revpar ");
		sb.append("from hk_srgk_jd_rzmx where nvl(dr, 0) = 0  and pk_org in ("
				+ pk_org + ") ");
		sb.append("and to_date(dbilldate, 'yyyy-mm-dd hh24:mi:ss') >=to_date('"
				+ begindate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(dbilldate, 'yyyy-mm-dd hh24:mi:ss') <=to_date('"
				+ enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		RzmxHVO hvo = (RzmxHVO) getBD().executeQuery(sb.toString(),
				new BeanProcessor(RzmxHVO.class));
		return hvo;
	}

	// 根据部门主键获取部门信息
	@SuppressWarnings("unchecked")
	private Map<String, DeptVO> getDeptInfo(String wheresql)
			throws DAOException {
		Map<String, DeptVO> map = new HashMap<String, DeptVO>();
		String sql = "select * from org_dept where nvl(dr,0)=0 and pk_dept in ("
				+ wheresql + ")";
		List<DeptVO> list = (List<DeptVO>) getBD().executeQuery(sql,
				new BeanListProcessor(DeptVO.class));
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				DeptVO vo = list.get(i);
				String pk_dept = vo.getPk_dept();
				map.put(pk_dept, vo);
			}
		}
		return map;
	}

	// 获取组织版本
	private String getOrg_v(String org) throws DAOException {
		String sql = "select distinct pk_vid from org_orgs where pk_org='"
				+ org + "' and nvl(dr,0)=0";
		String pk_org_v = (String) getBD().executeQuery(sql,
				new ColumnProcessor());
		return pk_org_v;
	}

	private UFDouble getNull_Zero(UFDouble jine) {
		if (jine == null) {
			return UFDouble.ZERO_DBL;
		} else {
			return jine;
		}
	}

	private String getNull_Str(Object o) {
		if (o == null) {
			return "";
		} else {
			return o.toString();
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, JzfsHVO> getJzfs_KM_MapInfo(String pk_org,UFBoolean isJd)
			throws DAOException, InstantiationException, IllegalAccessException {
		// 判断 是 酒店 还是 会馆，然后 赋值为  酒店 或 会馆  默认的那个pk_org
		if(isJd.booleanValue()){
			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;
		}else if(HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)){
			pk_org = HKJT_PUB.PK_ORG_HUIGUAN;
		}else if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)){
			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;
		}
		// TODO test
		pk_org = HKJT_PUB.PK_ORG_HUIGUAN;
		
		String sql = "select * from hk_srgk_hg_jzfs " 
				+ " where nvl(pk_kjkm,'~')<>'~' " 
				+ " and pk_org='"+ pk_org + "' "
				+ " and dr=0";
		Map<String, JzfsHVO> map = new HashMap<String, JzfsHVO>();
		List<JzfsHVO> list = (List<JzfsHVO>) getBD().executeQuery(sql,
				new BeanListProcessor(JzfsHVO.class));
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				JzfsHVO jzfshvo = list.get(i);
				String key = jzfshvo.getPk_hk_srgk_hg_jzfs();
				map.put(key, jzfshvo);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private Map<String, SrxmHVO> getSrxm_KM_MapInfo(String pk_org,UFBoolean isJd)
			throws Exception {
		// 判断 是 酒店 还是 会馆，然后 赋值为  酒店 或 会馆  默认的那个pk_org
		if(isJd.booleanValue()){
			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;
		}else if(HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)){
			pk_org = HKJT_PUB.PK_ORG_HUIGUAN;
		}else if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)){
			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;
		}
		// TODO test
		pk_org = HKJT_PUB.PK_ORG_HUIGUAN;
		
		String sql = "select * from hk_srgk_hg_srxm " 
				+ " where nvl(pk_kjkm,'~')<>'~'"
				+ " and pk_org='"+ pk_org + "'"
				+ " and dr=0";
		Map<String, SrxmHVO> map = new HashMap<String, SrxmHVO>();
		List<SrxmHVO> list = (List<SrxmHVO>) getBD().executeQuery(sql,
				new BeanListProcessor(SrxmHVO.class));
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SrxmHVO srxmhvo = list.get(i);
				String key = srxmhvo.getPk_hk_srgk_hg_srxm();
				map.put(key, srxmhvo);
			}
		}
		return map;

	}

	@Override
	public String genU8VoucherInfo(YyribaoBillVO srdbvo) throws Exception {
		PKLock pklock = PKLock.getInstance();
		if (srdbvo != null) {
			YyribaoHVO srdbhvo = srdbvo.getParentVO();
			// 处理表头自定义项构造
			Map<String, String[]> mapdvdefdept = srdbhvo.getVdefdeptinfo();
			String vdef1 = srdbhvo.getVdef01();
			if (mapdvdefdept.size() <= 0 && vdef1 != null && !"".equals(vdef1)
					&& !"~".equals(vdef1)) {
				String[] vdef1s = vdef1.split(",");
				for (int i = 0; i < vdef1s.length; i++) {
					String vdef = vdef1s[i];
					String[] vdefinfo = vdef.split("=");
					String vdefcolumn = vdefinfo[0];
					String[] deptinfos = vdefinfo[1].split("、");
					String pk_dept = deptinfos[0];
					String code = deptinfos[1];
					String name = deptinfos[2];
					String[] map_str = new String[3];
					map_str[0] = vdefcolumn;
					map_str[1] = name;
					map_str[2] = code;
					mapdvdefdept.put(pk_dept, map_str);
				}

			}
			// 生成日期
			UFDate dbilldate = srdbhvo.getDbilldate();
			String pk_org = srdbhvo.getPk_org();
			// 日期加组织要进行加锁处理
			if (!pklock.addBatchDynamicLock(new String[] { pk_org
					+ dbilldate.toString() })) {
				throw new BusinessException("该组织+日期已经加锁处理，请稍后再试!");
			}

			UFBoolean isJd = PuPubVO.getUFBoolean_NullAs(srdbhvo.getVdef10(),UFBoolean.FALSE);
			// 获取结账方式与科目对应关系
			Map<String, JzfsHVO> jzfs_km_map = getJzfs_KM_MapInfo(pk_org,isJd);
			// 获取收入项目与科目的对应关系
			Map<String, SrxmHVO> srxm_km_map = getSrxm_KM_MapInfo(pk_org,isJd);
			// 构造U8凭证信息
			YyribaoBVO[] yyrbbvos = (YyribaoBVO[]) srdbvo.getChildrenVO();
			// 获取U8数据库连接
			JDBCUtils jdbc = new JDBCUtils("hkjt_u8");
			Connection conn = null;
			try {
				// 测试使用 默认先把pk_org 设置为398
//				pk_org = "398";
				String sql_orgcode = "select code from org_orgs where pk_org='"+pk_org+"'";
				String orgcode=(String) getBD().executeQuery(sql_orgcode, new ColumnProcessor());
				pk_org = orgcode.substring(1, orgcode.length());
				conn = jdbc.getU8Conn();
				conn.setAutoCommit(false);
				// 获取对应的U8数据库名
				String dbname = getU8DBName(conn, pk_org);
				// 获取会计月
				String kjmonth = getU8KjqjInfo(conn, pk_org, dbilldate
						.toString().substring(0, 10));
				String kjyear = dbilldate.toString().substring(0, 4);
				String pznum = srdbhvo.getVdef02();
				if (pznum != null && !"".equals(pznum) && !"~".equals(pznum)) {
					String dbdate = srdbhvo.getDbilldate().toString();
					String isgenpz = selectPzInfo(conn, pznum, dbname, kjyear,
							kjmonth,dbdate);
					if (isgenpz != null && !"".equals(isgenpz)) {
						return null;
					}
				}
				// 获取科目信息是否启用辅助核算
				Map<String, U8FzhsVO> map_fzhsvo = getU8FzhsInfo(conn, dbname);
				// 查询最大的凭证号
				String maxpznum = getMaxPzNum(conn, dbname, kjyear, kjmonth);
				// 设置set集合存放对方科目
				Set<String> srxmkjkm = new HashSet<String>();
				Set<String> jzfskjkm = new HashSet<String>();
				// 处理收入项目凭证
				List<U8VoucherVO> list_srxmpz = handlerSrxmPingZheng(srdbhvo,
						yyrbbvos, srxm_km_map, map_fzhsvo, maxpznum, kjyear,
						kjmonth, srxmkjkm);
				// 处理结账方式凭证
				List<U8VoucherVO> list_jzfspz = handlerJzfsPingZheng(srdbhvo,
						yyrbbvos, jzfs_km_map, map_fzhsvo, maxpznum, kjyear,
						kjmonth, jzfskjkm);
				// 处理对方科目
				String srxmkjkm_info = "";
				// if (srxmkjkm.size() > 0) {
				// srxmkjkm_info = getEqualSrxmKjkm(srxmkjkm);
				// }
				String jzfskjkm_info = "";
				// if (jzfskjkm.size() > 0) {
				// jzfskjkm_info = getEqualSrxmKjkm(jzfskjkm);
				// }

				// 解析U8VO信息并插入数据库
				handlerU8VOInfo(list_srxmpz, list_jzfspz, conn, dbname,
						srxmkjkm_info, jzfskjkm_info);
				// 最后自动提交
				conn.commit();
				// 更细完成之后将生成的凭证号更新到自定义项2上
				srdbhvo.setVdef02(maxpznum);
				getBD().updateVO(srdbhvo, new String[] { "vdef02" });
				return maxpznum;
			} catch (Exception e) {
				jdbc.rollBack(conn);
				throw new BusinessException(e.toString());
			} finally {
				JDBCUtils.closeConn(conn);
			}

		}
		return null;
	}

	/**
	 * 根据会计年和会计月，凭证号查询是否已经生成过凭证
	 * 
	 * @throws SQLException
	 * */
	private String selectPzInfo(Connection conn, String pznum, String dbname,
			String pzyear, String pzmonth,String dbdate) throws SQLException {
		String sql = "select i_id from  " + dbname
				+ ".dbo.GL_accvouch where iyear='" + pzyear + "' and iperiod='"
				+ pzmonth + "' and csign='收' and SUBSTRING(CONVERT(CHAR(19),daudit_date, 20),0,11)= '"+dbdate.substring(0, 10)+"'";
		PreparedStatement prep = conn.prepareStatement(sql);
		ResultSet rs = prep.executeQuery();
		String idinfo = "";
		if (rs.next()) {
			idinfo = rs.getString("i_id");
		}
		if (rs != null) {
			rs.close();
		}
		if (prep != null) {
			prep.close();
		}
		return idinfo;
	}

	/**
	 * 处理收入项目会计科目（对方）
	 * */
	@SuppressWarnings("unused")
	private String getEqualSrxmKjkm(Set<String> srxmkjkm) {
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = srxmkjkm.iterator();
		while (it.hasNext()) {
			String value = it.next();
			sb.append(value + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 处理收入项目会计科目（对方）
	 * */
	@SuppressWarnings("unused")
	private String getEqualJzfsKjkm(Set<String> jzfskjkm) {

		StringBuffer sb = new StringBuffer();
		Iterator<String> it = jzfskjkm.iterator();
		while (it.hasNext()) {
			String value = it.next();
			sb.append(value + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();

	}

	/**
	 * 设置U8凭证公共的信息
	 * */
	private void setU8PubInfo(U8VoucherVO u8vo, String kjyear, String kjmonth,
			String maxpznum, UFDate dbilldate) {
		// 会计月
		u8vo.setIperiod(Integer.valueOf(kjmonth));
		// 会计年
		u8vo.setIyear(Integer.valueOf(kjyear));
		// 会计期间(此处需要属于哪个会计期间)
		if (kjmonth.length() < 2) {
			kjmonth = "0" + kjmonth;
		}
		u8vo.setIyperiod(Integer.valueOf(kjyear + kjmonth));
		// 凭证类别排序号，默认先为2
		u8vo.setIsignseq(2);
		// 分录号
		u8vo.setInid(0);
		u8vo.setIno_id(Integer.valueOf(maxpznum));
		u8vo.setCsign("收");
		// 制单日期
		u8vo.setDbill_date(Timestamp.valueOf(dbilldate.toString().substring(0,
				10)
				+ " 00:00:00.000"));
		//u8vo.setIflag(0); // 作废标志 1
		u8vo.setCtext1("");// 凭证界面 右上角的输入框第一行
		u8vo.setCtext2("");// 凭证界面 右上角的输入框第二行
		u8vo.setDoutbilldate(Timestamp.valueOf(dbilldate.toString().substring(
				0, 10)
				+ " 00:00:00.000")); // 外部凭证制单日期
		u8vo.setCoutno_id("GLsrgk" + dbilldate.toString().substring(0, 4)
				+ dbilldate.toString().substring(5, 7)
				+ dbilldate.toString().substring(8, 10)); // 外部凭证业务号（可用GLsrgkYYYYMMDD的格式）
		u8vo.setRowguid("GLsrgk" + dbilldate.toString().substring(0, 4)
				+ dbilldate.toString().substring(5, 7)
				+ dbilldate.toString().substring(8, 10) + "00000000000000"); // 行标示
		u8vo.setTvouchtime(Timestamp.valueOf(new UFDateTime().toString())); // 凭证保存时间（精确到时分秒毫秒）
		u8vo.setBflagout(false);
		u8vo.setIdoc(-1);
		u8vo.setMd_f(UFDouble.ZERO_DBL.toBigDecimal());
		u8vo.setMc_f(UFDouble.ZERO_DBL.toBigDecimal());
		u8vo.setNfrat(UFDouble.ZERO_DBL.toDouble());
		u8vo.setNd_s(UFDouble.ZERO_DBL.toDouble());
		u8vo.setNc_s(UFDouble.ZERO_DBL.toDouble());
		u8vo.setIbook(0);
		u8vo.setBpcsedit(true);
		u8vo.setBdeptedit(true);
		u8vo.setBitemedit(true);
		u8vo.setBcussupinput(false);
		u8vo.setBvouchedit(true);
		//默认设置传入U8凭证的制单人
		u8vo.setCbill("收入平台");
	}

	/**
	 * 结账方式（借方）凭证处理
	 * 
	 * @throws BusinessException
	 * */
	private List<U8VoucherVO> handlerJzfsPingZheng(YyribaoHVO srdbhvo,
			YyribaoBVO[] srdbbvos, Map<String, JzfsHVO> jzfs_km_map,
			Map<String, U8FzhsVO> map_fzhsvo, String maxpznum, String kjyear,
			String kjmonth, Set<String> jzfskjkm) throws BusinessException {
		List<U8VoucherVO> list_u8 = new ArrayList<U8VoucherVO>();
		// 凭证日期
		UFDate dbilldate = srdbhvo.getDbilldate();
		// 此处循环处理末级往来信息
		Map<String, List<YyribaoBVO>> map_wanglai = new HashMap<String, List<YyribaoBVO>>();
		for (int i = 0; i < srdbbvos.length; i++) {
			YyribaoBVO srdibiaoBVO = srdbbvos[i];
			String iswanglai = srdibiaoBVO.getIswanglai();
			String pk_fjzfs = srdibiaoBVO.getPk_fjzfs();
			if ("Y".equals(iswanglai) && pk_fjzfs != null
					&& !"".equals(pk_fjzfs)&& !"~".equals(pk_fjzfs)) {
				if (!map_wanglai.containsKey(pk_fjzfs)) {
					List<YyribaoBVO> list = new ArrayList<YyribaoBVO>();
					list.add(srdibiaoBVO);
					map_wanglai.put(pk_fjzfs, list);
				} else {
					List<YyribaoBVO> list = map_wanglai.get(pk_fjzfs);
					list.add(srdibiaoBVO);
					map_wanglai.put(pk_fjzfs, list);
				}
			}
		}
		for (int i = 0; i < srdbbvos.length; i++) {
			YyribaoBVO srdbbvo = srdbbvos[i];
			String pk_jzfs = srdbbvo.getJzfs_pk();
			UFDouble jzfs_jine = getNull_Zero(srdbbvo.getJine());
			String jzfsname = srdbbvo.getJzfs_name() == null ? null : srdbbvo
					.getJzfs_name().trim();
			String iswanglai = srdbbvo.getIswanglai();
			// 获取结账方式是否有对应的会计科目，否则不进行处理
			String pk_kjkm = jzfs_km_map.get(pk_jzfs) == null ? null
					: jzfs_km_map.get(pk_jzfs).getPk_kjkm();
			if (pk_kjkm == null || pk_kjkm.equals("") || pk_kjkm.equals("~")) {
				continue;
			}
			// 如果是外来的信息，跳过，因为以下的代码会根据上层往来信息去校验处理
			if ("Y".equals(iswanglai)) {
				continue;
			}
			if (jzfs_jine == null
					|| jzfs_jine.compareTo(UFDouble.ZERO_DBL) == 0) {
				continue;
			}
			// 对于启用了辅助核算的进行特殊处理
			// 判断科目编码是否启用辅助核算
			if (map_fzhsvo.containsKey(pk_kjkm)) {
				String iscus = map_fzhsvo.get(pk_kjkm).getBcus();
				// 如果有对应的往来信息则处理下级的往来信息
				if (map_wanglai.containsKey(pk_jzfs)) {
					List<YyribaoBVO> list_b = map_wanglai.get(pk_jzfs);
					for (int j = 0; j < list_b.size(); j++) {
						YyribaoBVO srdibiaoBVO = list_b.get(j);
						jzfs_jine = srdibiaoBVO.getJine();
						jzfsname = srdibiaoBVO.getJzfs_name() == null ? null
								: srdibiaoBVO.getJzfs_name().trim();
						U8VoucherVO u8vo = new U8VoucherVO();
						if (iscus != null && "1".equals(iscus)) {
							// 默认客户信息,测试洗衣房
							u8vo.setCcus_id("010001");
						}
						// 赋值默认信息
						setU8PubInfo(u8vo, kjyear, kjmonth, maxpznum, dbilldate);
						u8vo.setCcode(pk_kjkm);// 科目编码
						u8vo.setMc(UFDouble.ZERO_DBL.toBigDecimal());
						u8vo.setMd(UFDouble.ZERO_DBL.toBigDecimal());
						if (jzfs_km_map.get(pk_jzfs) != null
								&& jzfs_km_map.get(pk_jzfs).getJdinfo() != null
								&& 2 == jzfs_km_map.get(pk_jzfs).getJdinfo()) {
							u8vo.setMc(getNull_Zero(jzfs_jine).toBigDecimal());// 贷方金额
						} else {
							u8vo.setMd(getNull_Zero(jzfs_jine).toBigDecimal());// 借方金额
						}
						u8vo.setCdigest(String.valueOf(dbilldate.getMonth())
								+ "." + String.valueOf(dbilldate.getDay())
								+ jzfsname);// 摘要（每一行分录的摘要 可以不同）
						// 辅助核算
						u8vo.setCitem_id("");

						list_u8.add(u8vo);
						jzfskjkm.add(pk_kjkm);
					}
				} else {
					U8VoucherVO u8vo = new U8VoucherVO();
					if (iscus != null && "1".equals(iscus)) {
						u8vo.setCcus_id("010001");
					}
					setU8PubInfo(u8vo, kjyear, kjmonth, maxpznum, dbilldate);
					u8vo.setCdigest(String.valueOf(dbilldate.getMonth()) + "."
							+ String.valueOf(dbilldate.getDay()) + jzfsname);// 摘要（每一行分录的摘要
					u8vo.setCcode(pk_kjkm);// 科目编码
					u8vo.setMc(UFDouble.ZERO_DBL.toBigDecimal());
					u8vo.setMd(UFDouble.ZERO_DBL.toBigDecimal());
					if (jzfs_km_map.get(pk_jzfs) != null
							&& jzfs_km_map.get(pk_jzfs).getJdinfo() != null
							&& 2 == jzfs_km_map.get(pk_jzfs).getJdinfo()) {
						u8vo.setMc(getNull_Zero(jzfs_jine).toBigDecimal());// 贷方金额
					} else {
						u8vo.setMd(getNull_Zero(jzfs_jine).toBigDecimal());// 借方金额
					}
					// 辅助核算
					u8vo.setCitem_id("");

					list_u8.add(u8vo);
					jzfskjkm.add(pk_kjkm);
				}

			} else {
				throw new BusinessException("U8会计科目编码" + pk_kjkm + "有误!");
			}
		}
		return list_u8;
	}

	/**
	 * 收入项目（贷方）凭证处理
	 * 
	 * @throws BusinessException
	 * */
	private List<U8VoucherVO> handlerSrxmPingZheng(YyribaoHVO srdbhvo,
			YyribaoBVO[] srdbbvos, Map<String, SrxmHVO> srxm_km_map,
			Map<String, U8FzhsVO> map_fzhsvo, String maxpznum, String kjyear,
			String kjmonth, Set<String> srxmkjkm) throws BusinessException {
		List<U8VoucherVO> list_u8 = new ArrayList<U8VoucherVO>();
		// 凭证日期
		UFDate dbilldate = srdbhvo.getDbilldate();
		// 公司
		String pk_org = srdbhvo.getPk_org();
		
		for (int i = 0; i < srdbbvos.length; i++) {
			YyribaoBVO srdbbvo = srdbbvos[i];
			String pk_srxm = srdbbvo.getSrmx_pk();
			String srxmname = srdbbvo.getSrmx_name() == null ? null : srdbbvo
					.getSrmx_name().trim();
			/**
			 * 将 生成凭证的金额字段 改成 收入
			 * 李彬 2016年4月25日17:26:09
			 * 酒店 与 会馆  要区分对待。 因为酒店的 收入没有值， 所以  要取 应收。   会馆的 取 收入。
			 */
			UFDouble srxm_jine = getNull_Zero(srdbbvo.getShouru());
			
			if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org))	// MAP：key-店的编码  value-NC公司pk
			{// 如果是 酒店  则用  应收金额
				srxm_jine = getNull_Zero(srdbbvo.getYingshou());
			}
			
			/**END*/

			// 获取收入项目是否有对应的会计科目，否则不进行处理
			String pk_kjkm = srxm_km_map.get(pk_srxm) == null ? null
					: srxm_km_map.get(pk_srxm).getPk_kjkm();
			if (pk_kjkm == null || pk_kjkm.equals("") || pk_kjkm.equals("~")) {
				continue;
			}
			if (srxm_jine == null
					|| srxm_jine.compareTo(UFDouble.ZERO_DBL) == 0) {
				continue;
			}

			// 对于启用了辅助核算的进行特殊处理
			// 判断科目编码是否启用辅助核算
			if (map_fzhsvo.containsKey(pk_kjkm)) {
				String isdept = map_fzhsvo.get(pk_kjkm).getBdept();
				String ispro = map_fzhsvo.get(pk_kjkm).getCass_item();
				if (isdept != null && "1".equals(isdept)) {
					// 处理收入项目信息
					Map<String, String[]> map = srdbhvo.getVdefdeptinfo();
					Set<String> set = map.keySet();
					Iterator<String> it = set.iterator();
					while (it.hasNext()) {
						String key = it.next();
						String[] defpinfo = map.get(key);
						// 自定义项
						String vdef = defpinfo[0];
						// 部门编码
						String deptcode = defpinfo[2];
						Object obj = srdbbvo.getAttributeValue(vdef);
						if (obj != null
								&& new UFDouble(obj.toString())
										.compareTo(UFDouble.ZERO_DBL) != 0) {
							U8VoucherVO u8vo = new U8VoucherVO();
							setU8PubInfo(u8vo, kjyear, kjmonth, maxpznum,
									dbilldate);
							u8vo.setCdept_id(deptcode);// 部门辅助核算编码
							u8vo.setCitem_class(ispro);

							u8vo.setCdigest(String.valueOf(dbilldate.getMonth())
									+ "."
									+ String.valueOf(dbilldate.getDay())
									+ srxmname);// 摘要（每一行分录的摘要 可以不同）
							u8vo.setCcode(pk_kjkm);// 科目编码
							u8vo.setMc(new UFDouble(obj.toString())
									.toBigDecimal());// 贷方金额
							u8vo.setMd(UFDouble.ZERO_DBL.toBigDecimal());
							// 辅助核算
							u8vo.setCitem_id("");
							list_u8.add(u8vo);
							srxmkjkm.add(pk_kjkm);
						}
					}

				} else {

					U8VoucherVO u8vo = new U8VoucherVO();
					setU8PubInfo(u8vo, kjyear, kjmonth, maxpznum, dbilldate);
					u8vo.setCdigest(String.valueOf(dbilldate.getMonth()) + "."
							+ String.valueOf(dbilldate.getDay()) + srxmname);// 摘要（每一行分录的摘要
																				// 可以不同）
					u8vo.setCcode(pk_kjkm);// 科目编码
					u8vo.setMc(srxm_jine.toBigDecimal());// 贷方金额
					u8vo.setMd(UFDouble.ZERO_DBL.toBigDecimal());
					// 辅助核算
					u8vo.setCitem_id("");

					list_u8.add(u8vo);
					srxmkjkm.add(pk_kjkm);
				}

			} else {
				throw new BusinessException("U8会计科目编码" + pk_kjkm + "有误!");
			}
		}
		return list_u8;
	}

	/**
	 * 处理U8VO，并插入U8数据库
	 * 
	 * @throws SQLException
	 * */
	private void handlerU8VOInfo(List<U8VoucherVO> srxmu8vo,
			List<U8VoucherVO> jzfsu8vo, Connection conn, String dbname,
			String srxmkjkm, String jzfskjkm) throws SQLException {
		// 构造sql语句
		String[] colunms = new String[] { "iperiod", "iyear", "iyperiod",
				"ino_id", "inid", "dbill_date", "ctext1", "ctext2",
				"cdigest", "ccode", "md", "mc", "dt_date", "cdept_id",
				"cperson_id", "ccus_id", "csup_id", "citem_id", "citem_class",
				"ccode_equal", "ccodeexch_equal", "doutbilldate", "coutno_id",
				"ccodecontrol", "rowguid", "tvouchtime", "csign", "bflagout",
				"idoc", "md_f", "mc_f", "nfrat", "nd_s", "nc_s", "ibook",
				"isignseq", "bpcsedit", "bdeptedit", "bitemedit",
				"bcussupinput", "bvouchedit", "cbill" };
		StringBuffer sql = new StringBuffer();
		sql.append("insert into " + dbname + ".dbo.GL_accvouch ( ");
		for (int i = 0; i < colunms.length; i++) {
			String column = colunms[i];
			if (i != colunms.length - 1) {
				sql.append(column + ",");
			} else {
				sql.append(column);
			}

		}
		sql.append(")");
		sql.append(" values ");
		sql.append("(");
		for (int i = 0; i < colunms.length; i++) {
			if (i != colunms.length - 1) {
				sql.append("?,");
			} else {
				sql.append("?");
			}

		}
		sql.append(")");
		PreparedStatement prep = conn.prepareStatement(sql.toString());
		int pzfl = 0;
		// 结账方式凭证的处理
		if (jzfsu8vo != null && jzfsu8vo.size() > 0) {
			for (int i = 0; i < jzfsu8vo.size(); i++) {
				U8VoucherVO u8vo = jzfsu8vo.get(i);
				u8vo.setCcode_equal(srxmkjkm);
				u8vo.setCcodeexch_equal(srxmkjkm);
				// 处理行标示和分录号
				pzfl = pzfl + 1;
				u8vo.setInid(pzfl);
				u8vo.setRowguid(u8vo.getRowguid() + String.valueOf(pzfl));
				for (int j = 0; j < colunms.length; j++) {
					String column = colunms[j];
					prep.setObject(j + 1, u8vo.getAttributeValue(column));
				}
				prep.addBatch();
			}
		}
		// 收入项目凭证 的处理
		if (srxmu8vo != null && srxmu8vo.size() > 0) {
			for (int i = 0; i < srxmu8vo.size(); i++) {
				U8VoucherVO u8vo = srxmu8vo.get(i);
				u8vo.setCcode_equal(jzfskjkm);
				u8vo.setCcodeexch_equal(jzfskjkm);
				// 处理行标示和分录号
				pzfl = pzfl + 1;
				u8vo.setInid(pzfl);
				u8vo.setRowguid(u8vo.getRowguid() + String.valueOf(pzfl));
				for (int j = 0; j < colunms.length; j++) {
					String column = colunms[j];
					prep.setObject(j + 1, u8vo.getAttributeValue(column));
				}
				prep.addBatch();
			}
		}
		prep.executeBatch();
		if (prep != null) {
			prep.close();
		}
	}

	/**
	 * 根据帐套编号获取对应的用户库名
	 * 
	 * @throws SQLException
	 * */
	private String getU8DBName(Connection conn, String org_code)
			throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("select reportdb.dbo.myu8db(1,'" + org_code
				+ "',GETDATE()) as DBName");
		PreparedStatement prep = conn.prepareStatement(sb.toString());
		ResultSet rs = prep.executeQuery();
		String dbname = null;
		if (rs.next()) {
			dbname = rs.getString("DBName");
		}
		if (rs != null) {
			rs.close();
		}
		if (prep != null) {
			prep.close();
		}
		return dbname;
	}

	/**
	 * 查询U8凭证号
	 * 
	 * @throws SQLException
	 * */
	private String getMaxPzNum(Connection conn, String dbname, String kjyear,
			String kjmonth) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("select isnull(max(ino_id),0)+1 as u8pzid from " + dbname
				+ ".dbo.GL_accvouch where iyear='" + kjyear + "' and iperiod='"
				+ kjmonth + "' and csign='收'");
		PreparedStatement prep = conn.prepareStatement(sb.toString());
		ResultSet rs = prep.executeQuery();
		String pznum = null;
		if (rs.next()) {
			pznum = rs.getString("u8pzid");
		}
		if (rs != null) {
			rs.close();
		}
		if (prep != null) {
			prep.close();
		}
		return pznum;
	}

	/**
	 * 获取会计月
	 * 
	 * @throws SQLException
	 * */
	private String getU8KjqjInfo(Connection conn, String orgcode, String pzdate)
			throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("select reportdb.dbo.MYU8DB(3,'" + orgcode + "','" + pzdate
				+ "')");
		PreparedStatement prep = conn.prepareStatement(sb.toString());
		ResultSet rs = prep.executeQuery();
		String kjqj = null;
		if (rs.next()) {
			kjqj = rs.getString(1);
		}
		if (rs != null) {
			rs.close();
		}
		if (prep != null) {
			prep.close();
		}
		return kjqj;
	}

	/**
	 * 查询U8数据库 辅助核算的启用信息
	 * 
	 * @throws SQLException
	 * */
	private Map<String, U8FzhsVO> getU8FzhsInfo(Connection conn, String dbname)
			throws SQLException {
		Map<String, U8FzhsVO> map = new HashMap<String, U8FzhsVO>();
		StringBuffer sb = new StringBuffer();
		sb.append("select ccode,ccode_name,bcus,bdept,cass_item ");
		sb.append("from " + dbname + ".dbo.code where (1=1) and bclose = 0 ");
		PreparedStatement prep = conn.prepareStatement(sb.toString());
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			U8FzhsVO usfzhsvo = new U8FzhsVO();
			String ccode = rs.getString("ccode");
			String ccode_name = rs.getString("ccode_name");
			String bcus = rs.getString("bcus");
			String bdept = rs.getString("bdept");
			String cass_item = rs.getString("cass_item");
			usfzhsvo.setCcode(ccode);
			usfzhsvo.setCcode_name(ccode_name);
			usfzhsvo.setBcus(bcus);
			usfzhsvo.setBdept(bdept);
			usfzhsvo.setCass_item(cass_item);
			map.put(ccode, usfzhsvo);
		}
		if (rs != null) {
			rs.close();
		}
		if (prep != null) {
			rs.close();
		}
		return map;
	}
	
	/**
	 * 重新赋值行号处理
	 * */
	private void resetVrowNo(SrdibiaoBVO[] srdbbvos) {
		if (srdbbvos != null && srdbbvos.length > 0) {
			int vrowno = 0;
			for (int i = 0; i < srdbbvos.length; i++) {
				vrowno = vrowno + 10;
				SrdibiaoBVO bvo = srdbbvos[i];
				bvo.setVrowno(String.valueOf(vrowno));
			}
		}
	}

	/**
	 * 生成  NC凭证
	 */
	@Override
	public String genNCVoucherInfo(YyribaoBillVO yyrbBillvo,int flag) throws Exception {
		PKLock pklock = PKLock.getInstance();
		if (yyrbBillvo != null) {
			YyribaoHVO yyrbhvo = yyrbBillvo.getParentVO();
			// 处理表头自定义项构造
			Map<String, String[]> mapdvdefdept = yyrbhvo.getVdefdeptinfo();	// key-部门pk value-[列名、部门名称、部门编码]
			String vdef1 = yyrbhvo.getVdef01();		// 部门信息
			// shouru_bm01=1001NC1000000000036P、03020201、客房一层,shouru_bm02=1001NC1000000000036R、03020202、客房二层,shouru_bm03=1001NC1000000000036T、03020203、客房三层,shouru_bm04=1001NC1000000000036V、03020204、客房四层,shouru_bm05=1001NC1000000000036X、03020205、客房五层,shouru_bm06=1001NC1000000000036Z、03020206、客房六层,shouru_bm07=1001NC10000000000371、03020207、客房七层,shouru_bm08=1001NC10000000000377、03020301、餐厅四层,shouru_bm09=1001NC1000000000037D、03020401、第一组,shouru_bm10=1001NC1000000000037F、03020402、第二组,shouru_bm11=1001NC1000000000037H、03020403、第三组,shouru_bm12=1001NC1000000000037L、03020405、第五组,shouru_bm13=1001NC1000000000037N、03020406、第六组,shouru_bm14=1001NC1000000000037P、03020407、第七组,shouru_bm15=1001NC1000000000037R、03020408、第八组,shouru_bm16=1001NC1000000000037X、03020411、第十一组,shouru_bm17=1001NC10000000000389、030205、男浴,shouru_bm18=1001NC1000000000038B、030206、女浴,shouru_bm19=1001NC10000000000397、030220、养生部,shouru_bm20=1001NC1000000000039F、030224、好宴餐厅,shouru_bm21=1001NC1000000000039J、030226、收银部,
			if (   mapdvdefdept.size() <= 0 
				&& vdef1 != null 
				&& !"".equals(vdef1)
				&& !"~".equals(vdef1)
			   ) 
			{
				String[] vdef1s = vdef1.split(",");
				for (int i = 0; i < vdef1s.length; i++) {
					String vdef = vdef1s[i];
					String[] vdefinfo = vdef.split("=");
					String vdefcolumn = vdefinfo[0];
					String[] deptinfos = vdefinfo[1].split("、");
					String pk_dept = deptinfos[0];
					String code = deptinfos[1];
					String name = deptinfos[2];
					String[] map_str = new String[3];
					map_str[0] = vdefcolumn;
					map_str[1] = name;
					map_str[2] = code;
					mapdvdefdept.put(pk_dept, map_str);
				}

			}
			
			UFDate dbilldate = yyrbhvo.getDbilldate();
			String pk_org = yyrbhvo.getPk_org();
			// 组织+日期 要进行加锁处理
			if (!pklock.addBatchDynamicLock(new String[] { pk_org
					+ dbilldate.toString() })) {
				throw new BusinessException("该组织+日期已经加锁处理，请稍后再试!");
			}

			UFBoolean isJd = PuPubVO.getUFBoolean_NullAs(yyrbhvo.getVdef10(),UFBoolean.FALSE);
			// 获取结账方式与科目的对应关系 ( 只取 配置了科目的 )
			Map<String, JzfsHVO> jzfs_km_map = getJzfs_KM_MapInfo(pk_org,isJd);	// key-结账方式pk ， value-结账方式vo
			
			// 获取收入项目与科目的对应关系 ( 只取 配置了科目的 )
			Map<String, SrxmHVO> srxm_km_map = getSrxm_KM_MapInfo(pk_org,isJd);	// key-收入项目pk ， value-收入项目vo
			
			/**
			 * 循环收入项目，将 项目档案 转换为pk
			 * 李彬
			 * 2016年6月3日09:48:17
			 */
			if( srxm_km_map!=null && srxm_km_map.size()>0 )
			{
				StringBuffer querySQL = 
						new StringBuffer("select ")
								.append(" doc.code ")
								.append(",doc.pk_defdoc ")
								.append(" from bd_defdoc doc ")
								.append(" where doc.dr=0 ")
								.append(" and doc.pk_defdoclist = '1001N510000000000RUV' ")	// 项目档案
								.append(" and doc.pk_group = '0001N510000000000EGY' ")
								.append(" and doc.pk_org in ('0001N510000000000EGY','"+pk_org+"') ")
								.append(" order by doc.code ")
				;
				ArrayList list = (ArrayList)this.getBD().executeQuery(querySQL.toString(), new ArrayListProcessor());
				HashMap<String,String> MAP_xmdn = new HashMap<String,String>();	// 项目档案MAP  key-档案code  value-档案pk
				for(int i=0;list!=null&&i<list.size();i++)
				{
					Object[] obj = (Object[])list.get(i);
					MAP_xmdn.put(
							  PuPubVO.getString_TrimZeroLenAsNull(obj[0])
							, PuPubVO.getString_TrimZeroLenAsNull(obj[1])
							);
				}
				
				StringBuffer querySQL_2 = 
						new StringBuffer("select ")
							.append(" doc.code ")
							.append(",doc.pk_defdoc ")
							.append(" from bd_defdoc doc ")
							.append(" where doc.dr=0 ")
							.append(" and doc.pk_defdoclist = '1001N510000000002OXB' ")	// 项目大类
							.append(" and doc.pk_group = '0001N510000000000EGY' ")
				;
				ArrayList list_2 = (ArrayList)this.getBD().executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				HashMap<String,String> MAP_xmDL = new HashMap<String,String>();	// 项目大类MAP  key-大类code  value-大类pk
				for(int i=0;list_2!=null&&i<list_2.size();i++)
				{
					Object[] obj = (Object[])list_2.get(i);
					MAP_xmDL.put(
							  PuPubVO.getString_TrimZeroLenAsNull(obj[0])
							, PuPubVO.getString_TrimZeroLenAsNull(obj[1])
							);
				}
				
				String org_code = HKJT_PUB.MAP_corp_dianCode.get(pk_org);	// 公司编码
				String[] srxm_key = new String[srxm_km_map.size()];
				srxm_key = srxm_km_map.keySet().toArray(srxm_key);
				for( int i=0;i<srxm_key.length;i++ )
				{	
					SrxmHVO srxmHVO = srxm_km_map.get(srxm_key[i]);
					String xmdn = PuPubVO.getString_TrimZeroLenAsNull(srxmHVO.getVdef1());	// 收入项目 表头自定义1  存放 项目档案
					
					if( xmdn==null || "~".equals(xmdn) ) continue;
					
					// 解析字符串
					// 方式一 ： 组织编码=项目编码、组织编码=项目编码
					// 方式二 ： 项目编码
					// 另外 还需要 给项目大类赋值， 取 
					if( xmdn.indexOf("=")>=0 )
					{// 如果包含=，则说明是 方式一
						String[] xmdn_temp = xmdn.split("、");	// 顿号 分割 多个公司
						for( int ii=0;xmdn_temp!=null&&ii<xmdn_temp.length;ii++ )
						{
							String[] xmdn_temp_ii = xmdn_temp[ii].split("=");
							String v1 = PuPubVO.getString_TrimZeroLenAsNull(xmdn_temp_ii[0]);
							String v2 = PuPubVO.getString_TrimZeroLenAsNull(xmdn_temp_ii[1]);
							
							if( org_code.equals(v1) )
							{
								srxmHVO.setVdef1( MAP_xmdn.get(v2) );	// 将 项目档案编码 翻译成pk
								String xmdl = v2.substring(0, 3);		// 前三位 是 项目大类编码
								srxmHVO.setVdef2( MAP_xmDL.get(xmdl) );	// 将 项目大类编码 翻译成pk
								break;
							}
						}
					}
					else
					{
						srxmHVO.setVdef1( MAP_xmdn.get(xmdn) );	// 将 项目档案编码 翻译成pk
						String xmdl = xmdn.substring(0, 3);		// 前三位 是 项目大类编码
						srxmHVO.setVdef2( MAP_xmDL.get(xmdl) );	// 将 项目大类编码 翻译成pk
					}
				}
			}
			/**END*/
			
			// 构造凭证信息
			YyribaoBVO[] yyrbbvos = (YyribaoBVO[]) yyrbBillvo.getChildrenVO();
			
			// 处理  收入项目 数据
			List<YyribaoBVO> list_srxmpz_NC = handlerSrxmPingZheng_NC(
					yyrbhvo, yyrbbvos, srxm_km_map );
			// 处理  结账方式 数据
			List<YyribaoBVO> list_jzfspz_NC = handlerJzfsPingZheng_NC(
					yyrbhvo, yyrbbvos, jzfs_km_map );
			
//			System.out.println("=="+list_srxmpz_NC+"=="+list_srxmpz_NC);
			
//			if(true) throw new BusinessException("李彬测试");
			
			// 进行凭证传递
			this.sendVoucher(yyrbhvo , list_srxmpz_NC , list_jzfspz_NC , flag);
			

		}
		return null;
	}
	
	/**
	 * 收入项目（默认为贷方）凭证处理
	 * 李彬  2016年5月10日14:12:49 
	 */
	private List<YyribaoBVO> handlerSrxmPingZheng_NC(
			YyribaoHVO   srdbhvo,		// 营业日报-表头
			YyribaoBVO[] srdbbvos,		// 营业日报-表体
			Map<String,SrxmHVO> srxm_km_map	// 收入项目VO
	) throws BusinessException {
		
		List<YyribaoBVO> list_NC = new ArrayList<YyribaoBVO>();
		
		String pk_org = srdbhvo.getPk_org();	// 公司
		UFBoolean isJd = PuPubVO.getUFBoolean_NullAs( srdbhvo.getVdef10() , UFBoolean.FALSE);
		
		for (int i = 0; i < srdbbvos.length; i++) {
			
//			if(i==35)
//			{
//				System.out.println("=="+i+"==");
//			}
			
			YyribaoBVO srdbbvo = srdbbvos[i];
			String pk_srxm  = PuPubVO.getString_TrimZeroLenAsNull( srdbbvo.getSrmx_pk() );
			String srxmname = PuPubVO.getString_TrimZeroLenAsNull( srdbbvo.getSrmx_name() ) ;
			
			if(pk_srxm==null || "~".equals(pk_srxm)) continue;	// 收入项目pk 为空    则跳过。
			
			/**
			 * 将 生成凭证的金额字段 改成 收入
			 * 李彬 2016年4月25日17:26:09
			 * 酒店 与 会馆  要区分对待。 因为酒店的 收入没有值， 所以  要取 应收。   会馆的 取 收入。
			 */
			UFDouble srxm_jine = getNull_Zero(srdbbvo.getShouru());
			if(isJd.booleanValue()&&HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org)){
				// 酒店
				srxm_jine = getNull_Zero(srdbbvo.getYingshou());
			}else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)) {
				// 会馆
			}else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)) {
				// 酒店
				srxm_jine = getNull_Zero(srdbbvo.getYingshou());
			}
			
			/**END*/

			// 获取收入项目是否有对应的会计科目，否则不进行处理
			String pk_kjkm = srxm_km_map.get(pk_srxm) == null ? null
					: srxm_km_map.get(pk_srxm).getPk_kjkm();
			
			// 科目 为空   则跳过。
			if( PuPubVO.getString_TrimZeroLenAsNull(pk_kjkm) == null || "~".equals(pk_kjkm) ) continue;
			// 金额 为空   则跳过。
			if( PuPubVO.getUFDouble_ZeroAsNull(srxm_jine) == null )	continue;

			if (true) {
				
				String xmdn = srxm_km_map.get(pk_srxm).getVdef1();	// 项目档案pk
				String xmDL = srxm_km_map.get(pk_srxm).getVdef2();	// 项目大类pk
				
				// 处理收入项目信息
				Map<String, String[]> map = srdbhvo.getVdefdeptinfo();
				Set<String> set = map.keySet();
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					String pk_dept = it.next();
					String[] defpinfo = map.get(pk_dept);
					// 自定义项(部门金额字段)
					String vdef_key = defpinfo[0];
					String deptcode = defpinfo[2];// 部门编码
					String deptname = defpinfo[1];// 部门名称
					
					UFDouble bm_je = PuPubVO.getUFDouble_ZeroAsNull( srdbbvo.getAttributeValue(vdef_key) );	// 获得 部门金额
					
					if ( bm_je!=null ) {	// 有部门
						
						YyribaoBVO yyrbBVO_list = new YyribaoBVO();
						
						yyrbBVO_list.setPk_kjkm(pk_kjkm);	// 科目
						yyrbBVO_list.setPk_dept(pk_dept);	// 部门
						yyrbBVO_list.setDaifang(bm_je);		// 贷方（需要根据 收入项目的配置方向  来决定 贷方、借方）
						yyrbBVO_list.setVbmemo( srxmname );	// 备注-摘要（在此 封装上 摘要）
						yyrbBVO_list.setVbdef01( xmdn );	// 自定义01  项目档案
						yyrbBVO_list.setVbdef02( xmDL );	// 自定义02  项目大类
						
						list_NC.add(yyrbBVO_list);
						
					}

//					else {	// 无部门
//	
//						YyribaoBVO yyrbBVO_list = new YyribaoBVO();
//						
//						yyrbBVO_list.setPk_kjkm(pk_kjkm);	// 科目
//						yyrbBVO_list.setPk_dept(null);		// 部门
//						yyrbBVO_list.setDaifang(srxm_jine);	// 贷方
//						yyrbBVO_list.setVbmemo( srxmname );	// 备注-摘要 （在此 封装上 摘要）
//						yyrbBVO_list.setVbdef01( xmdn );	// 自定义01  项目档案
//						
//						list_NC.add(yyrbBVO_list);
//						
//					}
				}
			}
		}

		return list_NC;
	}
	
	/**
	 * 结账方式（默认为借方）凭证处理
	 * 李彬 2016年5月10日17:13:23
	 */
	private List<YyribaoBVO> handlerJzfsPingZheng_NC(
			YyribaoHVO   srdbhvo,		// 营业日报 主vo
			YyribaoBVO[] srdbbvos,		// 营业日报 子vo
			Map<String, JzfsHVO> jzfs_km_map	// 结账方式
			) throws BusinessException {
		
		List<YyribaoBVO> list_NC = new ArrayList<YyribaoBVO>();
		
		String pk_org = srdbhvo.getPk_org();	// 组织
		
		/**
		 * 1、对 营业日报子vo  进行排序
		 */
		for(int i = 0 ; i < srdbbvos.length-1 ; i++)
		{
			int row_i = srdbbvos[i].getVrowno();
			for(int j = i+1 ; j < srdbbvos.length ; j++)
			{
				int row_j = srdbbvos[j].getVrowno();
				if( row_i>row_j )
				{
					YyribaoBVO temp = srdbbvos[j];
					srdbbvos[j] = srdbbvos[i];
					srdbbvos[i] = temp;
				}
			}
		}
		/**
		 * 2、将需要生成凭证的 数据 拿出来
		 * 原则是 先循环 后添加。  因为后面才能判断出 前一个的 有无下级。  先把 前一个 放在 temp里。
		 */
		List<YyribaoBVO> list_2 = new ArrayList<YyribaoBVO>();
		YyribaoBVO yyrbBVO_temp = null;
		String jzfs_code_temp   = "Begin";
		String jzfs_pk_temp     = null;
		boolean isWanglai_temp	= false;	// 是否往来
		for(int i = 0 ; i < srdbbvos.length ; i++)
		{
			String jzfs_pk   = PuPubVO.getString_TrimZeroLenAsNull( srdbbvos[i].getJzfs_pk() );
			String jzfs_name = PuPubVO.getString_TrimZeroLenAsNull( srdbbvos[i].getJzfs_name() );
			String jzfs_code = PuPubVO.getString_TrimZeroLenAsNull( srdbbvos[i].getJzfs_code() );
			UFDouble jine 	 = PuPubVO.getUFDouble_ZeroAsNull( srdbbvos[i].getJine() );
			
			// 三者皆为空  说明已经处理完毕
			if( jzfs_pk==null && jzfs_name==null && jzfs_code==null )
			{
				break;
			}
			
			// 三者皆不为空
			if( jzfs_pk!=null && jzfs_name!=null && jzfs_code!=null )
			{
				// 判断 本次编码 是否 不属于上次的下级, 并且 上次 不是往来的
				if(  !jzfs_code.startsWith(jzfs_code_temp)
				  && !isWanglai_temp	
				  )
				{// 如果不属于  则把上次的放在缓存里
					if(
					   yyrbBVO_temp!=null 
					&& PuPubVO.getUFDouble_ZeroAsNull( yyrbBVO_temp.getJine() )!=null 
					  )
					{
						list_2.add(yyrbBVO_temp);
					}
				}
				
				yyrbBVO_temp	= srdbbvos[i];
				jzfs_code_temp	= jzfs_code;
				jzfs_pk_temp	= jzfs_pk;
				isWanglai_temp	= false;
				continue;
				
			}
			
			// 两者为空，一者不为空 （只有 结账方式名称不为空）
			if( jzfs_pk==null && jzfs_name!=null && jzfs_code==null )
			{
				srdbbvos[i].setJzfs_pk(jzfs_pk_temp);
				srdbbvos[i].setJzfs_code(jzfs_code_temp);
				
				// 本次就叠加(只加 有金额的)
				if( jine!=null )
					list_2.add(srdbbvos[i]);
				
				// 不更新缓存
//				yyrbBVO_temp	= srdbbvos[i];
//				jzfs_code_temp	= jzfs_code;
//				jzfs_pk_temp	= jzfs_pk;
				isWanglai_temp	= true;
			}
		}
	
		/**
		 * 3、依据2的结果集  来 组合 凭证信息
		 *   为  最终的返回 结果
		 *   根据 借贷方向  来 放置 借方、贷方金额
		 */
		for( YyribaoBVO yyrbBVO_3 : list_2 )
		{
			JzfsHVO jzfsVO = jzfs_km_map.get( yyrbBVO_3.getJzfs_pk() );
			
			if( jzfsVO!=null && jzfsVO.getPk_kjkm()!=null )
			{
				yyrbBVO_3.setPk_kjkm( jzfsVO.getPk_kjkm() );	// 科目
				
				int JD = PuPubVO.getInteger_NullAs(jzfsVO.getJdinfo(), 1);	// 1-借、2-贷  （结账方式 默认为借方）
				if(1==JD)
				{
					yyrbBVO_3.setJiefang( yyrbBVO_3.getJine() );	// 借方
				}
				else if(2==JD)
				{
					yyrbBVO_3.setDaifang( yyrbBVO_3.getJine() );	// 贷方
				}
				
				/**
				 * 押金往来款  的  正数放借方， 负数放贷方（并且取正）
				 * 2016年7月21日17:19:33
				 */
				if("押金往来款".equals(jzfsVO.getName()))
				{
					if( UFDouble.ZERO_DBL.compareTo(yyrbBVO_3.getJine()) > 0 )
					{// 金额 小于零
						yyrbBVO_3.setJiefang( null );
						yyrbBVO_3.setDaifang( UFDouble.ZERO_DBL.sub(yyrbBVO_3.getJine()) );	// 负数金额 取正后 放到 贷方
					}
				}
				/**END*/
				
				yyrbBVO_3.setVbmemo( yyrbBVO_3.getJzfs_name().trim() );// 备注-摘要 （在此 封装上 摘要）
				
				/**
				 * 根据 pk_org + jzfs_name  来取得客户
				 * 只针对 往来的  进行处理
				 */
				String jzfs_code = PuPubVO.getString_TrimZeroLenAsNull(yyrbBVO_3.getJzfs_code());
				String jzfs_name = null;
				if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org))
				{// 酒店
					if(   jzfs_code==null
					  || (jzfs_code.startsWith("03") && jzfs_code.length()>=4)
					)
					{
						jzfs_name = PuPubVO.getString_TrimZeroLenAsNull(
							yyrbBVO_3.getJzfs_name()==null?null:yyrbBVO_3.getJzfs_name().replaceAll("记账回收","")
						);
					}
				}
				else if(HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org))
				{// 会馆
					if(   jzfs_code==null
					  || (jzfs_code.startsWith("05") && jzfs_code.length()>=4)
					)
					{
						jzfs_name = PuPubVO.getString_TrimZeroLenAsNull(
							yyrbBVO_3.getJzfs_name()==null?null:yyrbBVO_3.getJzfs_name().replaceAll("记账回收","")
						);
					}
				}
				
				if(jzfs_name!=null)
				{
					StringBuffer queryCustomer = 
						new StringBuffer("select cus.pk_customer ")
								.append(" from bd_customer cus ")
								.append(" inner join bd_custfinance cusfi on cus.pk_customer = cusfi.pk_customer ")
								.append(" where cus.dr=0 and cusfi.dr=0 ")
								.append(" and cusfi.pk_org = '"+pk_org+"' ")
								.append(" and cus.shortname = '"+jzfs_name+"' ")
					;
					ArrayList list = (ArrayList)this.getBD().executeQuery(queryCustomer.toString(), new ArrayListProcessor());
					if(list!=null&&list.size()>0)
					{
						String pk_customer = PuPubVO.getString_TrimZeroLenAsNull( ((Object[])list.get(0))[0] );
						yyrbBVO_3.setVbdef03(pk_customer);	// 自定义3 客商pk
					}
				}
				/**END*/
				
				list_NC.add(yyrbBVO_3);
			}
		}
		
		return list_NC;
	}
	
	
	private void sendVoucher(YyribaoHVO yyrbHVO,List<YyribaoBVO> srxmList,List<YyribaoBVO> jzfsList,int flag) throws BusinessException{
		
		List<YyribaoBVO> yyrbBVO_list = new ArrayList<YyribaoBVO>();
		yyrbBVO_list.addAll(srxmList);
		yyrbBVO_list.addAll(jzfsList);
		
		YyribaoBVO[] yyrbBVOs = new YyribaoBVO[yyrbBVO_list.size()];
		yyrbBVOs = yyrbBVO_list.toArray(yyrbBVOs);
		
		YyribaoBillVO yyrbBillVO = new YyribaoBillVO();
		yyrbBillVO.setParentVO(yyrbHVO);
		yyrbBillVO.setChildrenVO(yyrbBVOs);
		
		/**
		 * 判断借贷方 金额 是否一致
		 */
		UFDouble total_jiefang = UFDouble.ZERO_DBL;	// 借方 之和
		UFDouble total_daifang = UFDouble.ZERO_DBL;	// 贷方 之和
		for(int i=0;yyrbBVOs!=null&&i<yyrbBVOs.length;i++)
		{
			total_jiefang = total_jiefang.add( // 借方
					PuPubVO.getUFDouble_NullAsZero( yyrbBVOs[i].getJiefang() )
			);
			total_daifang = total_daifang.add( // 贷方
					PuPubVO.getUFDouble_NullAsZero( yyrbBVOs[i].getDaifang() )
			);
		}
		
		if(total_jiefang.compareTo(total_daifang)!=0){
			throw new BusinessException(
					"借贷方金额不一致，请检查。\r\n" +
					"【借方："+total_jiefang+"】\r\n" +
					"【贷方："+total_daifang+"】\r\n" 
					);
		}		
		/***END***/
		
		/**
		 * 测试
		 */
//		if(true)
//		{
//			throw new BusinessException("==测试==");
//		}
		/***END***/
		
//		System.out.println("=="+yyrbBVOs);
		
		if( flag==0 )
		{
			// 判断 有无生成过凭证
			FipExtendAggVO[] pz = NCLocator.getInstance().lookup(IFipBillQueryService.class).queryDesBillBySrc(	// 根据单据信息 查询 所生成的凭证
					 new FipRelationInfoVO[]{ constructFipRalactionInfo(yyrbBillVO) } 
					,null
			);
			boolean hasPZ = pz!=null && pz.length>0;	// 是否存在凭证
			if( hasPZ )
				throw new BusinessException("已生成过凭证，不能重复生成。");
			
			// 生成凭证
			this.sendMsgFip(
					 new YyribaoBillVO[]{yyrbBillVO}
					,FipMessageVO.MESSAGETYPE_ADD
			);
		}
		else if( flag==1 )
		{
			// 删除凭证
			this.sendMsgFip(
					 new YyribaoBillVO[]{yyrbBillVO}
					,FipMessageVO.MESSAGETYPE_DEL
			);
		}
		
	}
	
	
	public static void sendMsgFip(YyribaoBillVO[] billVos,int type) throws BusinessException{
			
		FipMessageVO[] messageVOs=new FipMessageVO[billVos.length];
		for (int i=0;i<billVos.length;i++ ){
			FipMessageVO fipmessagevo=new FipMessageVO();	
			FipRelationInfoVO reVO =constructFipRalactionInfo(billVos[i]);
			
			//0表示新增,若不赋值，默认为0
			fipmessagevo.setMessagetype(type);		
	//		 //Dirty默认为false，AuotSum默认为false
	//		fipmessagevo.setDirty(false);
			fipmessagevo.setAuotSum(false);
	//		//PrimaryKey 可以不用赋值
	//		fipmessagevo.setPrimaryKey("");
			
			//填充BillVO
			fipmessagevo.setBillVO(billVos[i]);
			//填充FipRelationInfoVO
			fipmessagevo.setMessageinfo(reVO);
			messageVOs[i]=fipmessagevo;			
		}
		
		invokeFipMessage(messageVOs);
		
	}

	public static FipRelationInfoVO constructFipRalactionInfo(YyribaoBillVO billVo) {	
	
		//填充消息VO		
		FipRelationInfoVO relation=new FipRelationInfoVO();
		
		String sbilltype="HK06";
		BilltypeVO billType = PfDataCache.getBillType(sbilltype);
		
		relation.setPk_group( billVo.getParentVO().getPk_group() );	// 集团
		relation.setPk_org( billVo.getParentVO().getPk_org() );		// 组织
		relation.setRelationID( billVo.getPrimaryKey() );			// 单据ID
		relation.setPk_system( billType.getSystemcode() );			// 来源系统
		relation.setBusidate( billVo.getParentVO().getDbilldate() );// 业务日期
		relation.setPk_billtype(sbilltype);							// 单据类型
		relation.setPk_operator(InvocationInfoProxy.getInstance().getUserId());	// 制单人

		relation.setFreedef1(billVo.getParentVO().getVbillcode());	// 单据号 
		relation.setFreedef2(billVo.getParentVO().getVmemo());		// 备注信息
		
		return relation;		
		
	}

	private static FipMsgResultVO[] invokeFipMessage(FipMessageVO[] messageVOs) throws BusinessException {
		if (Logger.isDebugEnabled()) {
			Logger.info("sendMessage is over!");
		}
		return NCLocator.getInstance().lookup(IFipMessageService.class).sendMessages(messageVOs);
	}
	
}
