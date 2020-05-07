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

	/**
	 * HK 2020��3��17��11:01:11
	 * ���ѿͻ������תӦ�գ���Ҫ���ͻ����� �����¼���
	 * ԭϵͳΪ ���ѿͻ��������ϵͳΪ תӦ�ա�
	 */
	private String ZHUANYINGSHOU = "תӦ��";
	private String ZHUANYINGSHOU_POS = "POS-תӦ��";
	/***END***/
	
	@Override
	public HZShuJuVO[] handlerHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws Exception {
		// ��Ϊ��֯�����Ƕ�������鴦��
		String[] orgs = pk_org.split(",");
		// �˴�Ӧ��Ϊ��֯+ʱ����� ��ʱ�ȴ���Ե������ڼ���
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
			throw new BusinessException("�Ѿ���������!");
		}
		// ����ಿ��
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
	 * ����֮ǰ����Ҫ���ֶ��Ƿ�Ϊ��
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
			mess.append("û��Ҫ������˵����ݣ�");
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
				// 2020��3��16��11:00:21
				// ֻ�е� ���˽�Ϊ0 ���ű����в���
				if (xfjine != null && xfjine.compareTo(UFDouble.ZERO_DBL) != 0) {
					if (pk_fdept == null || pk_fdept.length() <= 0
					|| "~".equals(pk_fdept) || pk_dept == null
					|| pk_dept.length() <= 0 || "~".equals(pk_dept)) {
						set.add("���������ڣ�" + dbilldate + ",�к�:" + vrowno
								+ "!���ź��ϼ����Ų���Ϊ��!��\n");
					}
				}
				//2015-12-30�޸�  ����Ƶ��˵� ������˽�Ϊ0�ҽ��˷�ʽΪ�ջ���~ ���п���
				if (jzjine != null && jzjine.compareTo(UFDouble.ZERO_DBL) != 0
						&& (pk_jzfs == null || "".equals(pk_jzfs)|| "~".equals(pk_jzfs))) {
					set.add("���������ڣ�" + dbilldate + ",�к�:" + vrowno
							+ "!���˷�ʽ����Ϊ��!��\n");
				}
				//2015-12-30�޸�  ����Ƶ��˵� ������ѽ�Ϊ0����Ʒ����Ϊ�ջ���~ ���п���
				if (xfjine != null && xfjine.compareTo(UFDouble.ZERO_DBL) != 0
						&& (pk_srxm == null || "".equals(pk_srxm)|| "~".equals(pk_srxm))) {
					set.add("���������ڣ�" + dbilldate + ",�к�:" + vrowno
							+ "!������Ŀ����Ϊ��!��\n");
				}
				if (jzfsname != null && jzfsname.equals(ZHUANYINGSHOU)
						&& (khmz == null || "".equals(khmz))) {
					set.add("���������ڣ�" + dbilldate + ",�к�:" + vrowno
							+ "!���˷�ʽΪ "+ZHUANYINGSHOU+" ʱ�ͻ����Ʋ���Ϊ��!��\n");
				}
				if (jzfsname != null && jzfsname.equals(ZHUANYINGSHOU_POS)
						&& (khmz == null || "".equals(khmz))) {
					set.add("���������ڣ�" + dbilldate + ",�к�:" + vrowno
							+ "!���˷�ʽΪ "+ZHUANYINGSHOU_POS+" ʱ�ͻ����Ʋ���Ϊ��!��\n");
				}
			}
			if (set.size() > 0) {
				Iterator<String> it = set.iterator();
				mess.append("�����˵����к���Ϣ����!����!\n");
				while (it.hasNext()) {
					String me = it.next();
					mess.append(me);
				}
			}
		}
		return mess.toString();

	}

	/**
	 * ��ȡ��ɾ������
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
	 * ���ű�����Զ�����Ķ�Ӧ��ϵ
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
		// ����֯�Ͷಿ�Ŵ��������֯Ϊ��������Ų�����
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
		// ��ʱȡ��һ����֯
		String first_org = orgs[0];
		// ����Ĭ�ϰ���һ������
		String dept = null;
		if (pk_dept != null && !"".equals(pk_dept)) {
			String[] depts = pk_dept.split(",");
			dept = depts[0];
		}
		String pk_org_v = getOrg_v(first_org);
		// �ж��Ƿ���ʾ����ƾ֤��Ϣ
		boolean isdept = false;
		if ("Y".equals(isShowDept) && orgs.length == 1
				&& (dept == null || "".equals(dept))) {
			isdept = true;
		}
		// ���������ֹ�����
		SgsjInfoVO[] sgsjvos = getSgsjInfoVOs(first_org, orgsql.toString(),
				pk_dept, begindate, enddate, isShowDept,isjd);
		RsbaogaoCVO_YY[] rstz = null;	// ���󱨸�-����
		CctzVO_YY[] cctz = null;		// ������
		if (isRs) {
			rstz = getRsTzInfo(first_org, orgsql.toString(), pk_dept,
					begindate, enddate, isShowDept);
			cctz = getCctzInfo(first_org, orgsql.toString(), pk_dept,
					begindate, enddate, isShowDept);
		}

		// ��ѯ��ȡ�Ƶ���ˣ�������Ŀ����������Ϣ
		Map<String, List<HZShuJuVO>> map_hzshuju = getHzShuJuInfo(first_org,
				orgsql.toString(), pk_dept, begindate, enddate, isRs,
				isShowDept, sgsjvos, rstz, cctz);

		// ��ȡ������ƽ�����۵���Ϣ
		RzmxHVO rzmxvo = getFangJiaInfo(orgsql.toString(), begindate, enddate);

		// �����ݷ����Լ������������
		String[] hvoinfo = new String[] { first_org, pk_org_v, pk_dept,
				begindate };
		SrdibiaoBillVO[] srdbvos = handlerHzshujuLeftAndRight(map_hzshuju,
				rzmxvo, sgsjvos, rstz, cctz, isdept, hvoinfo, first_org);
		
		/**
		 * �� �к�  ���и�ֵ
		 * ���  2016��4��29��19:54:21
		 */
		for( int bill_i=0;srdbvos!=null&&bill_i<srdbvos.length;bill_i++ )
		{
			SrdibiaoBillVO billVO = srdbvos[bill_i];
			Object body_obj = billVO.getChildrenVO();
			if(body_obj!=null && body_obj instanceof SrdibiaoBVO[])
			{
				this.resetVrowNo((SrdibiaoBVO[])body_obj);
			}
			
//			billVO.getParentVO().setVdef10(isjd.toString());	// ��HK 2018��11��6��17:50:23��
			
		}
		/**END*/
		
		return srdbvos;
	}

	// ��ȡ���ܱ�����
	@SuppressWarnings("unchecked")
	private Map<String, List<HZShuJuVO>> getHzShuJuInfo(String first_org,
			String pk_org, String pk_dept, String begindate, String enddate,
			boolean isRs, String isShowDept, SgsjInfoVO[] sgsjvos,
			RsbaogaoCVO_YY[] rstz, CctzVO_YY[] cctz) throws DAOException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ( ");
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
	//		sb.append(" where payment<>0 and nvl(hzshuju.dr,0)=0 and jzfs.name<>'" + ZHUANYINGSHOU + "' ");
			sb.append(" where payment<>0 and nvl(hzshuju.dr,0)=0 " +
					" and jzfs.name not in ('" + ZHUANYINGSHOU + "','" + ZHUANYINGSHOU_POS + "') ");
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
			sb.append(" from hk_srgk_jd_hzshuju hzshuju left join ");
			sb.append(" hk_srgk_hg_jzfs jzfs ");
			sb.append(" on hzshuju.jzfs_id=jzfs.pk_hk_srgk_hg_jzfs ");
			sb.append(" where payment<>0 and nvl(hzshuju.dr,0)=0 and jzfs.name='" + ZHUANYINGSHOU + "' ");
			// POS-תӦ��
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
			sb.append("'xfwlk-pos' xflx ");
			sb.append(" from hk_srgk_jd_hzshuju hzshuju left join ");
			sb.append(" hk_srgk_hg_jzfs jzfs ");
			sb.append(" on hzshuju.jzfs_id=jzfs.pk_hk_srgk_hg_jzfs ");
			sb.append(" where payment<>0 and nvl(hzshuju.dr,0)=0 and jzfs.name='" + ZHUANYINGSHOU_POS + "' ");
			
			sb.append(" ) xfinfo ");
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
				// ����֧�ֶ��ֻ��ܷ�ʽ������sql�����ص����� ������
				// Ŀǰ�� xfwlk��תӦ�գ���xfwlk-pos��POS-תӦ�գ�
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
		// �����Ӫҵ�ձ����������Ͳ�����������
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
	 * �����ֹ�������Ϣ
	 * */
	private void handlerSgsjInfo(SuperVO sgsjInfoVO, String dept, String group,
			String org, String tzdate, String fdept,
			Map<String, List<HZShuJuVO>> map) {
		// �ֹ����ݵĽ��˷�ʽ1����
		String jzfs1 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_jzfs_1"));
		String jzfsjine1 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_1"));

		// �ֹ����ݵĽ��˷�ʽ2����
		String jzfs2 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_jzfs_2"));
		String jzfsjine2 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_2"));

		// ������1����
		String info1 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_info_1"));
		String info_jine1 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_1"));
		// ������2����
		String info2 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_info_2"));
		String info_jine2 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_2"));

		// �ֹ����ݵ�������Ŀ1����
		String srxm1 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_srxm_1"));
		// �ֹ����ݵ�������Ŀ2����
		String srxm_jine1 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_1"));
		String srxm2 = getNull_Str(sgsjInfoVO.getAttributeValue("Tz_km_srxm_2"));
		String srxm_jine2 = getNull_Str(sgsjInfoVO
				.getAttributeValue("Tz_km_data_2"));
		// ������ (��Ҫ�ж�  �� תӦ��  ����  POS-תӦ��)
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
			/**
			 * 1001ZZ10000000AOWSYR POS-תӦ��
			 * 1001ZZ10000000AOWSZI תӦ��
			 */
			if ("1001ZZ10000000AOWSZI".equals(jzfs1)) {
				if (!map.containsKey("xfwlk")) {
					List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
					list.add(hzsj);
					map.put("xfwlk", list);
				} else {
					List<HZShuJuVO> list = map.get("xfwlk");
					list.add(hzsj);
					map.put("xfwlk", list);
				}
			} else {
				// POS-תӦ��
				String key_xfwlk_pos = "xfwlk-pos";
				if (!map.containsKey(key_xfwlk_pos)) {
					List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
					list.add(hzsj);
					map.put(key_xfwlk_pos, list);
				} else {
					List<HZShuJuVO> list = map.get(key_xfwlk_pos);
					list.add(hzsj);
					map.put(key_xfwlk_pos, list);
				}
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
			if ("1001ZZ10000000AOWSZI".equals(jzfs1)) {
				if (!map.containsKey("xfwlk")) {
					List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
					list.add(hzsj);
					map.put("xfwlk", list);
				} else {
					List<HZShuJuVO> list = map.get("xfwlk");
					list.add(hzsj);
					map.put("xfwlk", list);
				}
			} else {
				// POS-תӦ��
				String key_xfwlk_pos = "xfwlk-pos";
				if (!map.containsKey(key_xfwlk_pos)) {
					List<HZShuJuVO> list = new ArrayList<HZShuJuVO>();
					list.add(hzsj);
					map.put(key_xfwlk_pos, list);
				} else {
					List<HZShuJuVO> list = map.get(key_xfwlk_pos);
					list.add(hzsj);
					map.put(key_xfwlk_pos, list);
				}
			}
		}
		// ���˷�ʽ
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
		// ������Ŀ
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
	 * �������ݣ����ҹ�������ױ���Ϣ
	 * 
	 * @throws DAOException
	 * */
	private SrdibiaoBillVO[] handlerHzshujuLeftAndRight(
			Map<String, List<HZShuJuVO>> map_hzshuju, RzmxHVO rzmxvo,
			SgsjInfoVO[] sgsjvos, RsbaogaoCVO_YY[] rstz, CctzVO_YY[] cctz,
			boolean isdept, String[] hvoinfo, 
			String pk_org) throws DAOException {
		if (map_hzshuju.size() > 0) {
			// ��ȡ���˷�ʽ
			List<JzfsHVO> list_jzfs = getJzfsInfo(pk_org);
			// ��ȡ������Ŀ
			List<SrxmHVO> list_srxm = getSrxmInfo(pk_org);

			// ������Ŀ��������ϸ��Ϣ��Ӧ��ϵ
			Map<String, SrxmHVO> map_pk_srxm = new HashMap<String, SrxmHVO>();
			for (int i = 0; i < list_srxm.size(); i++) {
				SrxmHVO srxm = list_srxm.get(i);
				String srxm_pk = srxm.getPk_hk_srgk_hg_srxm();
				map_pk_srxm.put(srxm_pk, srxm);
			}
			// ������˷�ʽ
			List<HZShuJuVO> list_jzfsvo = map_hzshuju.get("jzfs");
			// ����������Ŀ
			List<HZShuJuVO> list_srxmvo = map_hzshuju.get("srxm");
			// ����ͻ����ѿͻ�������\תӦ��
			List<HZShuJuVO> list_xfwlk = map_hzshuju.get("xfwlk");
			// ����ͻ����ѿͻ�������\POS-תӦ��
			List<HZShuJuVO> list_xfwlk_pos = map_hzshuju.get("xfwlk-pos");
			// ����ɢ��Ѻ��
			UFDouble skyj = computeSKYJJine(map_hzshuju);
			// ������벿����Ϣ
			List<SrdibiaoBVO> jzfsvos = handlerLeftJzfsInfo(
					list_jzfs,
					list_jzfsvo, 
					list_xfwlk, 
					list_xfwlk_pos, 
					skyj);
			Map<String, UFDouble> map_jine = new HashMap<String, UFDouble>();
			Map<String, String> map_dept_vdef = new HashMap<String, String>();
			Map<String, DeptVO> map_deptinfo = new HashMap<String, DeptVO>();
			Map<String, String[]> vdefdeptinfo = new HashMap<String, String[]>();
			if (isdept) {

				// �������в�����Ϣ���
				map_jine = getAllDeptInfo(list_srxmvo, sgsjvos, cctz, rstz);
				// ���ź��Զ�����Ķ��չ�ϵ
				Set<String> set = map_jine.keySet();
				Iterator<String> it = set.iterator();
				int temp = 0;
				// �˴�����ĩ�����Ŷ�Ӧ��ϵ
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
				// �����ص�ǰ̨�Ĳ��Ŷ�Ӧ�Զ��������Ϣ
				Set<String> set_dept_vdef = map_dept_vdef.keySet();
				Iterator<String> it_dept_vdef = set_dept_vdef.iterator();
				while (it_dept_vdef.hasNext()) {
					String key = it_dept_vdef.next();
					// 0�Զ�����1��������2���ű���
					String[] str = new String[] { map_dept_vdef.get(key),
							map_deptinfo.get(key).getName(),
							map_deptinfo.get(key).getCode() };
					vdefdeptinfo.put(key, str);
				}
				// �������Ӳ����ϼ�������Ŀ��Ϣ
				Set<String> set1 = map_jine.keySet();
				Iterator<String> it1 = set1.iterator();
				Map<String, UFDouble> map_jine_parent = new HashMap<String, UFDouble>();
				Map<String, UFDouble> map_jine_dept = new HashMap<String, UFDouble>();
				while (it1.hasNext()) {
					String key = it1.next();
					String dept = key.substring(0, 20);
					String srxm = key.substring(20, 40);
					UFDouble money = map_jine.get(key);
					// �����źϼ�
					setToalMoneyForDept(money, map_jine_dept, dept);
					handlerUpSrxmJineForDept(money, srxm, dept,
							map_jine_parent, map_pk_srxm);
				}
				// ���map_jine �� map_jine_parent�ϲ�
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
			// �����Ұ벿����Ϣ
			List<SrdibiaoBVO> srxmvos = handlerRightSrxmInfo(list_srxm,
					list_srxmvo, map_jine, map_dept_vdef, map_pk_srxm);

			// �������ݽ��
			SrdibiaoBVO[] srdibiao = handlerLeftAndRightInfo(jzfsvos, srxmvos,
					rzmxvo);
			// ����ۺ�VO
			SrdibiaoBillVO srdbvo = new SrdibiaoBillVO();
			SrdibiaoHVO srdbhvo = new SrdibiaoHVO();
			srdbhvo.setVdefdeptinfo(vdefdeptinfo);
			// srdbhvo.setPk_group(pk_group);
			// �����ͷ�Զ�����1 ��ű����Զ�����Ŀ�벿�����ƶ�Ӧ��ϵ
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
	 * �����ͷ�Զ�����1 ��ű����Զ�����Ŀ�벿�����ƶ�Ӧ��ϵ
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
				sb.append(vdefname + "=" + key + "��" + deptcode + "��"
						+ deptname + ",");
			}
			hvo.setVdef01(sb.toString());
		}
	}

	/**
	 * Ӫҵ�ձ����źϼ�
	 * */
	private void setToalMoneyForDept(UFDouble money,
			Map<String, UFDouble> map_jine, String dept) {

		money = getNull_Zero(money);
		// ����ϼ���Ϣ
		if (!map_jine.containsKey(dept + "heji")) {
			map_jine.put(dept + "heji", money);
		} else {
			map_jine.put(dept + "heji",
					getNull_Zero(map_jine.get(dept + "heji")).add(money));
		}

	}

	/**
	 * �����ӱ����ݺϲ�����
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
					// ���һ��Ϊ�ϼ���
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
				// �����ƽ�����۵���Ϣ
				if (rzmxvo != null) {
					SrdibiaoBVO srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("������");
					srdbvo.setJine(rzmxvo.getFfl());
					jzfsvos.add(srdbvo);
					srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("ƽ������");
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
				// �����ƽ�����۵���Ϣ
				if (rzmxvo != null) {
					SrdibiaoBVO srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("������");
					srdbvo.setJine(rzmxvo.getFfl());
					srxmvos.add(srdbvo);
					srdbvo = new SrdibiaoBVO();
					srdbvo.setJzfs_name("ƽ������");
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
	 * ���˷�ʽ����ֶ�
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
	 * ����֯��ѯӪҵ�ձ�������Ϣ�Ĵ���
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
					// �����ֹ����ݲ���������Ŀ��Ϣ
					if (!map.containsKey(key)) {
						map.put(key, jine);
					} else {
						map.put(key, getNull_Zero(map.get(key)).add(jine));
					}
				}
			}
		}
		/**
		 * ��� ��    2016��3��15��17:43:47
		 * ֮ǰ�� ����ѭ��� ���� �����쳣�Ķ�
		 */
		handlerAllDeptSrxm(sgsj, map);
		// ������������ݲ���������Ŀ��Ϣ
		handlerAllDeptSrxm(cctz, map);
		// �������󱨸����ݲ���������Ŀ��Ϣ
		handlerAllDeptSrxm(rsbg, map);
		
		return map;
	}

	/**
	 * ����Ӫҵ�ձ�����������Ŀ�ֹ�����
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
	 * ����ɢ��Ѻ����˷�ʽ
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
					// �����Դ�����˵��ľͲ�����
					if (!"N".equals(iszd)) {
						// ���˷�ʽ�Ϳͻ�����������
						if (key.equals("jzfs") 
						|| key.equals("xfwlk")
						|| key.equals("xfwlk-pos")
						) {
							jzfsjine = jzfsjine.add(jine);
						} else if (key.equals("srxm")) {
							// ������Ŀ���
							srxmjine = srxmjine.add(jine);
						}
					}
				}
			}
		}
		return srxmjine.sub(jzfsjine);
	}

	/**
	 * ������벿�ֽ��˷�ʽ���ݺ�����Ŀͻ�����������
	 * */
	private List<SrdibiaoBVO> handlerLeftJzfsInfo(List<JzfsHVO> list_jzfs,
			List<HZShuJuVO> list_jzfsvo, List<HZShuJuVO> list_xfwlk,
			List<HZShuJuVO> list_xfwlk_pos,
			UFDouble skyj) {
		List<SrdibiaoBVO> list_srbvo = new ArrayList<SrdibiaoBVO>();
		if (list_jzfs != null && list_jzfs.size() > 0) {

			Map<String, UFDouble> map_jzfs_jine = new HashMap<String, UFDouble>();
			Map<String, JzfsHVO> map_pk_jzfs = new HashMap<String, JzfsHVO>();
			// ���˷�ʽPK����ϸ��Ϣ�Ķ�Ӧ��ϵ
			for (int i = 0; i < list_jzfs.size(); i++) {
				JzfsHVO jzfs_vo = list_jzfs.get(i);
				String pk_jzfs = jzfs_vo.getPk_hk_srgk_hg_jzfs();
				map_pk_jzfs.put(pk_jzfs, jzfs_vo);
			}

			// ���������������תӦ��
			if (list_xfwlk != null && list_xfwlk.size() > 0) {
				for (int i = 0; i < list_xfwlk.size(); i++) {
					HZShuJuVO xfwlk = list_xfwlk.get(i);
					String jzfs_id = xfwlk.getLx_id();
					UFDouble money = getNull_Zero(xfwlk.getJine());
					// ����ϼ���Ϣ
					setTotalMoney(map_jzfs_jine, money);

					if (!map_jzfs_jine.containsKey(jzfs_id)) {
						map_jzfs_jine.put(jzfs_id, money);
					} else {
						map_jzfs_jine.put(jzfs_id, map_jzfs_jine.get(jzfs_id)
								.add(money));
					}
					// �����ϼ����˷�ʽ��Ϣ
					handlerUpJzfsJine(money, jzfs_id, map_jzfs_jine,
							map_pk_jzfs);
				}
			}
			
			// ���������������POS-תӦ��
			if (list_xfwlk_pos != null && list_xfwlk_pos.size() > 0) {
				for (int i = 0; i < list_xfwlk_pos.size(); i++) {
					HZShuJuVO xfwlk_pos = list_xfwlk_pos.get(i);
					String jzfs_id_pos = xfwlk_pos.getLx_id();
					UFDouble money_pos = getNull_Zero(xfwlk_pos.getJine());
					// ����ϼ���Ϣ
					setTotalMoney(map_jzfs_jine, money_pos);

					if (!map_jzfs_jine.containsKey(jzfs_id_pos)) {
						map_jzfs_jine.put(jzfs_id_pos, money_pos);
					} else {
						map_jzfs_jine.put(jzfs_id_pos, 
								map_jzfs_jine.get(jzfs_id_pos).add(money_pos)
								);
					}
					// �����ϼ����˷�ʽ��Ϣ
					handlerUpJzfsJine(money_pos, jzfs_id_pos, map_jzfs_jine,
							map_pk_jzfs);
				}
			}

			// ������˷�ʽPK �� ���Ķ�Ӧ��ϵ
			if (list_jzfsvo != null && list_jzfsvo.size() > 0) {
				for (int i = 0; i < list_jzfsvo.size(); i++) {
					HZShuJuVO hzshuju = list_jzfsvo.get(i);
					String jzfs_id = hzshuju.getLx_id();
					UFDouble money = getNull_Zero(hzshuju.getJine());
					// ����ϼ���Ϣ
					setTotalMoney(map_jzfs_jine, money);
					if (!map_jzfs_jine.containsKey(jzfs_id)) {
						map_jzfs_jine.put(jzfs_id, money);
					} else {
						map_jzfs_jine.put(jzfs_id, map_jzfs_jine.get(jzfs_id)
								.add(money));
					}
					// �����ϼ����˷�ʽ��Ϣ
					handlerUpJzfsJine(money, jzfs_id, map_jzfs_jine,
							map_pk_jzfs);
				}

			}
			// ����ɢ��Ѻ��
			for (int i = 0; i < list_jzfs.size(); i++) {
				JzfsHVO jzfs_vo = list_jzfs.get(i);
				// �������⴦�� ���ȴ���ɢ��Ѻ��
				if ("ɢ��Ѻ��".equals(jzfs_vo.getName())) {
					map_jzfs_jine.put(jzfs_vo.getPk_hk_srgk_hg_jzfs(), skyj);
					// ����ϼ���Ϣ
					setTotalMoney(map_jzfs_jine, skyj);
					handlerUpJzfsJine(skyj, jzfs_vo.getPk_hk_srgk_hg_jzfs(),
							map_jzfs_jine, map_pk_jzfs);
					break;
				}
			}
			// �����µ���������ױ����VO
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
				// ��������ѿͻ������תӦ�� ����������
				if (ZHUANYINGSHOU.equals(jzfs_vo.getName())){
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
							// �Զ����ӵ��⼸��Ϊ������ƾ֤����Ҫ��ֵ�����ֶ�
							srdibiaobvo.setPk_fjzfs(xfwlk.getLx_id());
							srdibiaobvo.setIswanglai("Y");
							list_srbvo.add(srdibiaobvo);
						}
					}
				}
				// ��������ѿͻ������POS-תӦ�� ������������
				if (ZHUANYINGSHOU_POS.equals(jzfs_vo.getName())){
					if (list_xfwlk_pos != null && list_xfwlk_pos.size() > 0) {
						for (int j = 0; j < list_xfwlk_pos.size(); j++) {
							HZShuJuVO xfwlk = list_xfwlk_pos.get(j);
							srdibiaobvo = new SrdibiaoBVO();
							String khmz = xfwlk.getKhmz();
							int levelno1 = levelno + 1;
							for (int k = 0; k < levelno1; k++) {
								khmz = "   " + khmz;
							}
							srdibiaobvo.setJzfs_name(khmz);
							srdibiaobvo.setJine(xfwlk.getJine());
							// �Զ����ӵ��⼸��Ϊ������ƾ֤����Ҫ��ֵ�����ֶ�
							srdibiaobvo.setPk_fjzfs(xfwlk.getLx_id());
							srdibiaobvo.setIswanglai("Y");
							list_srbvo.add(srdibiaobvo);
						}
					}
				}
			}
			// ��������кϼ�
			SrdibiaoBVO srdibiaobvo = new SrdibiaoBVO();
			list_srbvo.add(srdibiaobvo);
			srdibiaobvo = new SrdibiaoBVO();
			list_srbvo.add(srdibiaobvo);
			srdibiaobvo = new SrdibiaoBVO();
			srdibiaobvo.setJzfs_name("�ϼ�");
			srdibiaobvo.setJine(map_jzfs_jine.get("heji"));
			list_srbvo.add(srdibiaobvo);
		}
		return list_srbvo;
	}

	/**
	 * ����ϼ���Ϣ
	 * */
	private void setTotalMoney(Map<String, UFDouble> map_heji, UFDouble money) {
		money = getNull_Zero(money);
		// ����ϼ���Ϣ
		if (!map_heji.containsKey("heji")) {
			map_heji.put("heji", money);
		} else {
			map_heji.put("heji", getNull_Zero(map_heji.get("heji")).add(money));
		}
	}

	/**
	 * �����Ұ벿������ױ�������Ϣ
	 * */
	private List<SrdibiaoBVO> handlerRightSrxmInfo(List<SrxmHVO> list_srxm,
			List<HZShuJuVO> list_srxmvo, Map<String, UFDouble> map_jine,
			Map<String, String> map_dept_vdef, Map<String, SrxmHVO> map_pk_srxm) {
		List<SrdibiaoBVO> list_srbvo = new ArrayList<SrdibiaoBVO>();
		if (list_srxm != null && list_srxm.size() > 0) {
			// ������Ŀ��������ϸ��Ϣ��Ӧ��ϵ
			// Map<String, SrxmHVO> map_pk_srxm = new HashMap<String,
			// SrxmHVO>();
			// for (int i = 0; i < list_srxm.size(); i++) {
			// SrxmHVO srxm = list_srxm.get(i);
			// String srxm_pk = srxm.getPk_hk_srgk_hg_srxm();
			// map_pk_srxm.put(srxm_pk, srxm);
			// }
			// ������Ŀ���������Ӧ��Ϣ
			Map<String, UFDouble> map_srxm_jine = new HashMap<String, UFDouble>();
			if (list_srxmvo != null && list_srxmvo.size() > 0) {
				for (int i = 0; i < list_srxmvo.size(); i++) {
					HZShuJuVO srxmvo = list_srxmvo.get(i);
					String pk_srxm = srxmvo.getLx_id();
					UFDouble money = srxmvo.getJine();
					// ����ϼ�
					setTotalMoney(map_srxm_jine, money);
					if (!map_srxm_jine.containsKey(pk_srxm)) {
						map_srxm_jine.put(pk_srxm, money);
					} else {
						map_srxm_jine.put(pk_srxm, map_srxm_jine.get(pk_srxm)
								.add(money));
					}
					// �����ϼ����
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
					// �˴������Զ����ֶ���Ϣ
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
			// ��������кϼ�
			SrdibiaoBVO srdibiaobvo = new SrdibiaoBVO();
			list_srbvo.add(srdibiaobvo);
			srdibiaobvo = new SrdibiaoBVO();
			list_srbvo.add(srdibiaobvo);
			srdibiaobvo = new SrdibiaoBVO();
			srdibiaobvo.setSrmx_name("�ϼ�");
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
	 * �ݹ鴦��ϼ��ϼ����˷�ʽ���
	 * */
	private void handlerUpJzfsJine(UFDouble money, String jzfs_id,
			Map<String, UFDouble> map_jzfs_jine,
			Map<String, JzfsHVO> map_pk_jzfs) {
		// ��ȡ�ϼ����˷�ʽ��Ϣ
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
			// ��������ϼ���Ϣ��ô�ݹ��ٽ����ϼ���ֵ
			handlerUpJzfsJine(money, upjzfspk, map_jzfs_jine, map_pk_jzfs);
		}

	}

	/**
	 * �ݹ鴦���ϼ�������Ŀ�ϼƽ��
	 * */
	private void handlerUpSrxmJine(UFDouble money, String srxm_id,
			Map<String, UFDouble> map_srxm_jine,
			Map<String, SrxmHVO> map_pk_srxm) {
		// ��ȡ�ϼ�������Ŀ��Ϣ
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
			// ��������ϼ���Ϣ��ô�ݹ��ٽ����ϼ���ֵ
			handlerUpSrxmJine(money, upsrxmpk, map_srxm_jine, map_pk_srxm);
		}
	}

	/**
	 * �ݹ鴦�����ϼ�������Ŀ
	 * */
	private void handlerUpSrxmJineForDept(UFDouble money, String srxm_id,
			String pk_dept, Map<String, UFDouble> map_deptsrxm_jine,
			Map<String, SrxmHVO> map_pk_srxm) {

		// ��ȡ�ϼ�������Ŀ��Ϣ
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
			// ��������ϼ���Ϣ��ô�ݹ��ٽ����ϼ���ֵ
			handlerUpSrxmJineForDept(money, upsrxmpk, pk_dept,
					map_deptsrxm_jine, map_pk_srxm);
		}

	}

	/**
	 * ��ѯ���˷�ʽ���Լ�����
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private List<JzfsHVO> getJzfsInfo(String pk_org) throws DAOException {
		// ���˷�ʽ���ݿ�������ɽ����֯����
		String sql = "select jzfs.*,jzfs_f.name vdef5 from hk_srgk_hg_jzfs jzfs left join hk_srgk_hg_jzfs jzfs_f on jzfs.pk_parent=jzfs_f.pk_hk_srgk_hg_jzfs where nvl(jzfs.dr,0)=0 and jzfs.pk_org='"
				+ "0001N510000000001SY3" + "' order by jzfs.code";
		List<JzfsHVO> list = (List<JzfsHVO>) getBD().executeQuery(sql,
				new BeanListProcessor(JzfsHVO.class));
		return list;
	}

	/**
	 * ��ѯ������Ŀ���Լ�����
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private List<SrxmHVO> getSrxmInfo(String pk_org) throws DAOException {
		// ���˷�ʽ���ݿ�������ɽ����֯����
		String sql = "select * from hk_srgk_hg_srxm where nvl(dr,0)=0 and pk_org='"
				+ pk_org + "' order by code";
		List<SrxmHVO> list = (List<SrxmHVO>) getBD().executeQuery(sql,
				new BeanListProcessor(SrxmHVO.class));
		return list;
	}

	// ��ȡ�ֹ�����
	/**
	 * ������֯+����+���Ų�ѯ �ֹ�������Ϣ
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
			sb.append(" and nvl(replace(sgsj.vdef10,'~',''),'N') = '" +isjd.toString()+ "' ");	// ���� �Ƿ�Ƶ� ��HK 2018��11��6��17:26:25��
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
				sb.append(" and nvl(replace(sgsj.vdef10,'~',''),'N') = '" +isjd.toString()+ "' ");	// ���� �Ƿ�Ƶ� ��HK 2018��11��6��17:26:25��
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
				sb.append(" and nvl(replace(sgsj.vdef10,'~',''),'N') = '" +isjd.toString()+ "' ");	// ���� �Ƿ�Ƶ� ��HK 2018��11��6��17:26:25��
				sb.append("group by sgsj.pk_group,sgsjb.tz_km_jzfs_1,sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sgsjb.tz_km_jzfs_2,");
				sb.append("sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,srxm1.pk_parent,srxm2.pk_parent,jzfs1.pk_parent,jzfs2.pk_parent");
			}
		}
		List<SgsjInfoVO> list = (List<SgsjInfoVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(SgsjInfoVO.class));
		return list.toArray(new SgsjInfoVO[] {});
	}

	// ��ѯ��������
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

	// ���󱨸������
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

	// �����ϼ����Ż�ȡ�¼�������Ϣ
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
	 * ��ȡƽ�����ۣ������۵���Ϣ
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

	// ���ݲ���������ȡ������Ϣ
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

	// ��ȡ��֯�汾
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
		// �ж� �� �Ƶ� ���� ��ݣ�Ȼ�� ��ֵΪ  �Ƶ� �� ���  Ĭ�ϵ��Ǹ�pk_org
		if(isJd.booleanValue()){
			pk_org = "0001N510000000001SY3";
		}
//		else if(HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)){
//			pk_org = HKJT_PUB.PK_ORG_HUIGUAN;
//		}else if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)){
//			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;
//		}
		else {
			pk_org = HKJT_PUB.PK_ORG_HUIGUAN;
		}
		
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
		// �ж� �� �Ƶ� ���� ��ݣ�Ȼ�� ��ֵΪ  �Ƶ� �� ���  Ĭ�ϵ��Ǹ�pk_org
		if(isJd.booleanValue()){
//			pk_org = pk_org;
		}
//		else if(HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)){
//			pk_org = HKJT_PUB.PK_ORG_HUIGUAN;
//		}else if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)){
//			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;
//		}
		else {
			pk_org = HKJT_PUB.PK_ORG_HUIGUAN;
		}
		
		String sql = "select * from hk_srgk_hg_srxm " 
				+ " where nvl(pk_kjkm,'~')<>'~'"
				+ " and pk_org='"+ pk_org + "'"
				+ " and dr = 0 "
				// Լ�� �Ƶ��������Ŀ ǰ׺Ϊ LY
				+ (isJd.booleanValue()? " and code like 'LY%'": " and code not like 'LY%'")
		;
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
			// �����ͷ�Զ������
			Map<String, String[]> mapdvdefdept = srdbhvo.getVdefdeptinfo();
			String vdef1 = srdbhvo.getVdef01();
			if (mapdvdefdept.size() <= 0 && vdef1 != null && !"".equals(vdef1)
					&& !"~".equals(vdef1)) {
				String[] vdef1s = vdef1.split(",");
				for (int i = 0; i < vdef1s.length; i++) {
					String vdef = vdef1s[i];
					String[] vdefinfo = vdef.split("=");
					String vdefcolumn = vdefinfo[0];
					String[] deptinfos = vdefinfo[1].split("��");
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
			// ��������
			UFDate dbilldate = srdbhvo.getDbilldate();
			String pk_org = srdbhvo.getPk_org();
			// ���ڼ���֯Ҫ���м�������
			if (!pklock.addBatchDynamicLock(new String[] { pk_org
					+ dbilldate.toString() })) {
				throw new BusinessException("����֯+�����Ѿ������������Ժ�����!");
			}

			UFBoolean isJd = PuPubVO.getUFBoolean_NullAs(srdbhvo.getVdef10(),UFBoolean.FALSE);
			// ��ȡ���˷�ʽ���Ŀ��Ӧ��ϵ
			Map<String, JzfsHVO> jzfs_km_map = getJzfs_KM_MapInfo(pk_org,isJd);
			// ��ȡ������Ŀ���Ŀ�Ķ�Ӧ��ϵ
			Map<String, SrxmHVO> srxm_km_map = getSrxm_KM_MapInfo(pk_org,isJd);
			// ����U8ƾ֤��Ϣ
			YyribaoBVO[] yyrbbvos = (YyribaoBVO[]) srdbvo.getChildrenVO();
			// ��ȡU8���ݿ�����
			JDBCUtils jdbc = new JDBCUtils("hkjt_u8");
			Connection conn = null;
			try {
				// ����ʹ�� Ĭ���Ȱ�pk_org ����Ϊ398
//				pk_org = "398";
				String sql_orgcode = "select code from org_orgs where pk_org='"+pk_org+"'";
				String orgcode=(String) getBD().executeQuery(sql_orgcode, new ColumnProcessor());
				pk_org = orgcode.substring(1, orgcode.length());
				conn = jdbc.getU8Conn();
				conn.setAutoCommit(false);
				// ��ȡ��Ӧ��U8���ݿ���
				String dbname = getU8DBName(conn, pk_org);
				// ��ȡ�����
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
				// ��ȡ��Ŀ��Ϣ�Ƿ����ø�������
				Map<String, U8FzhsVO> map_fzhsvo = getU8FzhsInfo(conn, dbname);
				// ��ѯ����ƾ֤��
				String maxpznum = getMaxPzNum(conn, dbname, kjyear, kjmonth);
				// ����set���ϴ�ŶԷ���Ŀ
				Set<String> srxmkjkm = new HashSet<String>();
				Set<String> jzfskjkm = new HashSet<String>();
				// ����������Ŀƾ֤
				List<U8VoucherVO> list_srxmpz = handlerSrxmPingZheng(srdbhvo,
						yyrbbvos, srxm_km_map, map_fzhsvo, maxpznum, kjyear,
						kjmonth, srxmkjkm);
				// ������˷�ʽƾ֤
				List<U8VoucherVO> list_jzfspz = handlerJzfsPingZheng(srdbhvo,
						yyrbbvos, jzfs_km_map, map_fzhsvo, maxpznum, kjyear,
						kjmonth, jzfskjkm);
				// ����Է���Ŀ
				String srxmkjkm_info = "";
				// if (srxmkjkm.size() > 0) {
				// srxmkjkm_info = getEqualSrxmKjkm(srxmkjkm);
				// }
				String jzfskjkm_info = "";
				// if (jzfskjkm.size() > 0) {
				// jzfskjkm_info = getEqualSrxmKjkm(jzfskjkm);
				// }

				// ����U8VO��Ϣ���������ݿ�
				handlerU8VOInfo(list_srxmpz, list_jzfspz, conn, dbname,
						srxmkjkm_info, jzfskjkm_info);
				// ����Զ��ύ
				conn.commit();
				// ��ϸ���֮�����ɵ�ƾ֤�Ÿ��µ��Զ�����2��
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
	 * ���ݻ����ͻ���£�ƾ֤�Ų�ѯ�Ƿ��Ѿ����ɹ�ƾ֤
	 * 
	 * @throws SQLException
	 * */
	private String selectPzInfo(Connection conn, String pznum, String dbname,
			String pzyear, String pzmonth,String dbdate) throws SQLException {
		String sql = "select i_id from  " + dbname
				+ ".dbo.GL_accvouch where iyear='" + pzyear + "' and iperiod='"
				+ pzmonth + "' and csign='��' and SUBSTRING(CONVERT(CHAR(19),daudit_date, 20),0,11)= '"+dbdate.substring(0, 10)+"'";
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
	 * ����������Ŀ��ƿ�Ŀ���Է���
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
	 * ����������Ŀ��ƿ�Ŀ���Է���
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
	 * ����U8ƾ֤��������Ϣ
	 * */
	private void setU8PubInfo(U8VoucherVO u8vo, String kjyear, String kjmonth,
			String maxpznum, UFDate dbilldate) {
		// �����
		u8vo.setIperiod(Integer.valueOf(kjmonth));
		// �����
		u8vo.setIyear(Integer.valueOf(kjyear));
		// ����ڼ�(�˴���Ҫ�����ĸ�����ڼ�)
		if (kjmonth.length() < 2) {
			kjmonth = "0" + kjmonth;
		}
		u8vo.setIyperiod(Integer.valueOf(kjyear + kjmonth));
		// ƾ֤�������ţ�Ĭ����Ϊ2
		u8vo.setIsignseq(2);
		// ��¼��
		u8vo.setInid(0);
		u8vo.setIno_id(Integer.valueOf(maxpznum));
		u8vo.setCsign("��");
		// �Ƶ�����
		u8vo.setDbill_date(Timestamp.valueOf(dbilldate.toString().substring(0,
				10)
				+ " 00:00:00.000"));
		//u8vo.setIflag(0); // ���ϱ�־ 1
		u8vo.setCtext1("");// ƾ֤���� ���Ͻǵ�������һ��
		u8vo.setCtext2("");// ƾ֤���� ���Ͻǵ������ڶ���
		u8vo.setDoutbilldate(Timestamp.valueOf(dbilldate.toString().substring(
				0, 10)
				+ " 00:00:00.000")); // �ⲿƾ֤�Ƶ�����
		u8vo.setCoutno_id("GLsrgk" + dbilldate.toString().substring(0, 4)
				+ dbilldate.toString().substring(5, 7)
				+ dbilldate.toString().substring(8, 10)); // �ⲿƾ֤ҵ��ţ�����GLsrgkYYYYMMDD�ĸ�ʽ��
		u8vo.setRowguid("GLsrgk" + dbilldate.toString().substring(0, 4)
				+ dbilldate.toString().substring(5, 7)
				+ dbilldate.toString().substring(8, 10) + "00000000000000"); // �б�ʾ
		u8vo.setTvouchtime(Timestamp.valueOf(new UFDateTime().toString())); // ƾ֤����ʱ�䣨��ȷ��ʱ������룩
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
		//Ĭ�����ô���U8ƾ֤���Ƶ���
		u8vo.setCbill("����ƽ̨");
	}

	/**
	 * ���˷�ʽ���跽��ƾ֤����
	 * 
	 * @throws BusinessException
	 * */
	private List<U8VoucherVO> handlerJzfsPingZheng(YyribaoHVO srdbhvo,
			YyribaoBVO[] srdbbvos, Map<String, JzfsHVO> jzfs_km_map,
			Map<String, U8FzhsVO> map_fzhsvo, String maxpznum, String kjyear,
			String kjmonth, Set<String> jzfskjkm) throws BusinessException {
		List<U8VoucherVO> list_u8 = new ArrayList<U8VoucherVO>();
		// ƾ֤����
		UFDate dbilldate = srdbhvo.getDbilldate();
		// �˴�ѭ������ĩ��������Ϣ
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
			// ��ȡ���˷�ʽ�Ƿ��ж�Ӧ�Ļ�ƿ�Ŀ�����򲻽��д���
			String pk_kjkm = jzfs_km_map.get(pk_jzfs) == null ? null
					: jzfs_km_map.get(pk_jzfs).getPk_kjkm();
			if (pk_kjkm == null || pk_kjkm.equals("") || pk_kjkm.equals("~")) {
				continue;
			}
			// �������������Ϣ����������Ϊ���µĴ��������ϲ�������ϢȥУ�鴦��
			if ("Y".equals(iswanglai)) {
				continue;
			}
			if (jzfs_jine == null
					|| jzfs_jine.compareTo(UFDouble.ZERO_DBL) == 0) {
				continue;
			}
			// ���������˸�������Ľ������⴦��
			// �жϿ�Ŀ�����Ƿ����ø�������
			if (map_fzhsvo.containsKey(pk_kjkm)) {
				String iscus = map_fzhsvo.get(pk_kjkm).getBcus();
				// ����ж�Ӧ��������Ϣ�����¼���������Ϣ
				if (map_wanglai.containsKey(pk_jzfs)) {
					List<YyribaoBVO> list_b = map_wanglai.get(pk_jzfs);
					for (int j = 0; j < list_b.size(); j++) {
						YyribaoBVO srdibiaoBVO = list_b.get(j);
						jzfs_jine = srdibiaoBVO.getJine();
						jzfsname = srdibiaoBVO.getJzfs_name() == null ? null
								: srdibiaoBVO.getJzfs_name().trim();
						U8VoucherVO u8vo = new U8VoucherVO();
						if (iscus != null && "1".equals(iscus)) {
							// Ĭ�Ͽͻ���Ϣ,����ϴ�·�
							u8vo.setCcus_id("010001");
						}
						// ��ֵĬ����Ϣ
						setU8PubInfo(u8vo, kjyear, kjmonth, maxpznum, dbilldate);
						u8vo.setCcode(pk_kjkm);// ��Ŀ����
						u8vo.setMc(UFDouble.ZERO_DBL.toBigDecimal());
						u8vo.setMd(UFDouble.ZERO_DBL.toBigDecimal());
						if (jzfs_km_map.get(pk_jzfs) != null
								&& jzfs_km_map.get(pk_jzfs).getJdinfo() != null
								&& 2 == jzfs_km_map.get(pk_jzfs).getJdinfo()) {
							u8vo.setMc(getNull_Zero(jzfs_jine).toBigDecimal());// �������
						} else {
							u8vo.setMd(getNull_Zero(jzfs_jine).toBigDecimal());// �跽���
						}
						u8vo.setCdigest(String.valueOf(dbilldate.getMonth())
								+ "." + String.valueOf(dbilldate.getDay())
								+ jzfsname);// ժҪ��ÿһ�з�¼��ժҪ ���Բ�ͬ��
						// ��������
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
							+ String.valueOf(dbilldate.getDay()) + jzfsname);// ժҪ��ÿһ�з�¼��ժҪ
					u8vo.setCcode(pk_kjkm);// ��Ŀ����
					u8vo.setMc(UFDouble.ZERO_DBL.toBigDecimal());
					u8vo.setMd(UFDouble.ZERO_DBL.toBigDecimal());
					if (jzfs_km_map.get(pk_jzfs) != null
							&& jzfs_km_map.get(pk_jzfs).getJdinfo() != null
							&& 2 == jzfs_km_map.get(pk_jzfs).getJdinfo()) {
						u8vo.setMc(getNull_Zero(jzfs_jine).toBigDecimal());// �������
					} else {
						u8vo.setMd(getNull_Zero(jzfs_jine).toBigDecimal());// �跽���
					}
					// ��������
					u8vo.setCitem_id("");

					list_u8.add(u8vo);
					jzfskjkm.add(pk_kjkm);
				}

			} else {
				throw new BusinessException("U8��ƿ�Ŀ����" + pk_kjkm + "����!");
			}
		}
		return list_u8;
	}

	/**
	 * ������Ŀ��������ƾ֤����
	 * 
	 * @throws BusinessException
	 * */
	private List<U8VoucherVO> handlerSrxmPingZheng(YyribaoHVO srdbhvo,
			YyribaoBVO[] srdbbvos, Map<String, SrxmHVO> srxm_km_map,
			Map<String, U8FzhsVO> map_fzhsvo, String maxpznum, String kjyear,
			String kjmonth, Set<String> srxmkjkm) throws BusinessException {
		List<U8VoucherVO> list_u8 = new ArrayList<U8VoucherVO>();
		// ƾ֤����
		UFDate dbilldate = srdbhvo.getDbilldate();
		// ��˾
		String pk_org = srdbhvo.getPk_org();
		
		for (int i = 0; i < srdbbvos.length; i++) {
			YyribaoBVO srdbbvo = srdbbvos[i];
			String pk_srxm = srdbbvo.getSrmx_pk();
			String srxmname = srdbbvo.getSrmx_name() == null ? null : srdbbvo
					.getSrmx_name().trim();
			/**
			 * �� ����ƾ֤�Ľ���ֶ� �ĳ� ����
			 * ��� 2016��4��25��17:26:09
			 * �Ƶ� �� ���  Ҫ���ֶԴ��� ��Ϊ�Ƶ�� ����û��ֵ�� ����  Ҫȡ Ӧ�ա�   ��ݵ� ȡ ���롣
			 */
			UFDouble srxm_jine = getNull_Zero(srdbbvo.getShouru());
			
			if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org))	// MAP��key-��ı���  value-NC��˾pk
			{// ����� �Ƶ�  ����  Ӧ�ս��
				srxm_jine = getNull_Zero(srdbbvo.getYingshou());
			}
			
			/**END*/

			// ��ȡ������Ŀ�Ƿ��ж�Ӧ�Ļ�ƿ�Ŀ�����򲻽��д���
			String pk_kjkm = srxm_km_map.get(pk_srxm) == null ? null
					: srxm_km_map.get(pk_srxm).getPk_kjkm();
			if (pk_kjkm == null || pk_kjkm.equals("") || pk_kjkm.equals("~")) {
				continue;
			}
			if (srxm_jine == null
					|| srxm_jine.compareTo(UFDouble.ZERO_DBL) == 0) {
				continue;
			}

			// ���������˸�������Ľ������⴦��
			// �жϿ�Ŀ�����Ƿ����ø�������
			if (map_fzhsvo.containsKey(pk_kjkm)) {
				String isdept = map_fzhsvo.get(pk_kjkm).getBdept();
				String ispro = map_fzhsvo.get(pk_kjkm).getCass_item();
				if (isdept != null && "1".equals(isdept)) {
					// ����������Ŀ��Ϣ
					Map<String, String[]> map = srdbhvo.getVdefdeptinfo();
					Set<String> set = map.keySet();
					Iterator<String> it = set.iterator();
					while (it.hasNext()) {
						String key = it.next();
						String[] defpinfo = map.get(key);
						// �Զ�����
						String vdef = defpinfo[0];
						// ���ű���
						String deptcode = defpinfo[2];
						Object obj = srdbbvo.getAttributeValue(vdef);
						if (obj != null
								&& new UFDouble(obj.toString())
										.compareTo(UFDouble.ZERO_DBL) != 0) {
							U8VoucherVO u8vo = new U8VoucherVO();
							setU8PubInfo(u8vo, kjyear, kjmonth, maxpznum,
									dbilldate);
							u8vo.setCdept_id(deptcode);// ���Ÿ����������
							u8vo.setCitem_class(ispro);

							u8vo.setCdigest(String.valueOf(dbilldate.getMonth())
									+ "."
									+ String.valueOf(dbilldate.getDay())
									+ srxmname);// ժҪ��ÿһ�з�¼��ժҪ ���Բ�ͬ��
							u8vo.setCcode(pk_kjkm);// ��Ŀ����
							u8vo.setMc(new UFDouble(obj.toString())
									.toBigDecimal());// �������
							u8vo.setMd(UFDouble.ZERO_DBL.toBigDecimal());
							// ��������
							u8vo.setCitem_id("");
							list_u8.add(u8vo);
							srxmkjkm.add(pk_kjkm);
						}
					}

				} else {

					U8VoucherVO u8vo = new U8VoucherVO();
					setU8PubInfo(u8vo, kjyear, kjmonth, maxpznum, dbilldate);
					u8vo.setCdigest(String.valueOf(dbilldate.getMonth()) + "."
							+ String.valueOf(dbilldate.getDay()) + srxmname);// ժҪ��ÿһ�з�¼��ժҪ
																				// ���Բ�ͬ��
					u8vo.setCcode(pk_kjkm);// ��Ŀ����
					u8vo.setMc(srxm_jine.toBigDecimal());// �������
					u8vo.setMd(UFDouble.ZERO_DBL.toBigDecimal());
					// ��������
					u8vo.setCitem_id("");

					list_u8.add(u8vo);
					srxmkjkm.add(pk_kjkm);
				}

			} else {
				throw new BusinessException("U8��ƿ�Ŀ����" + pk_kjkm + "����!");
			}
		}
		return list_u8;
	}

	/**
	 * ����U8VO��������U8���ݿ�
	 * 
	 * @throws SQLException
	 * */
	private void handlerU8VOInfo(List<U8VoucherVO> srxmu8vo,
			List<U8VoucherVO> jzfsu8vo, Connection conn, String dbname,
			String srxmkjkm, String jzfskjkm) throws SQLException {
		// ����sql���
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
		// ���˷�ʽƾ֤�Ĵ���
		if (jzfsu8vo != null && jzfsu8vo.size() > 0) {
			for (int i = 0; i < jzfsu8vo.size(); i++) {
				U8VoucherVO u8vo = jzfsu8vo.get(i);
				u8vo.setCcode_equal(srxmkjkm);
				u8vo.setCcodeexch_equal(srxmkjkm);
				// �����б�ʾ�ͷ�¼��
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
		// ������Ŀƾ֤ �Ĵ���
		if (srxmu8vo != null && srxmu8vo.size() > 0) {
			for (int i = 0; i < srxmu8vo.size(); i++) {
				U8VoucherVO u8vo = srxmu8vo.get(i);
				u8vo.setCcode_equal(jzfskjkm);
				u8vo.setCcodeexch_equal(jzfskjkm);
				// �����б�ʾ�ͷ�¼��
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
	 * �������ױ�Ż�ȡ��Ӧ���û�����
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
	 * ��ѯU8ƾ֤��
	 * 
	 * @throws SQLException
	 * */
	private String getMaxPzNum(Connection conn, String dbname, String kjyear,
			String kjmonth) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("select isnull(max(ino_id),0)+1 as u8pzid from " + dbname
				+ ".dbo.GL_accvouch where iyear='" + kjyear + "' and iperiod='"
				+ kjmonth + "' and csign='��'");
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
	 * ��ȡ�����
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
	 * ��ѯU8���ݿ� ���������������Ϣ
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
	 * ���¸�ֵ�кŴ���
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
	 * ����  NCƾ֤
	 */
	@Override
	public String genNCVoucherInfo(YyribaoBillVO yyrbBillvo,int flag) throws Exception {
		PKLock pklock = PKLock.getInstance();
		if (yyrbBillvo != null) {
			YyribaoHVO yyrbhvo = yyrbBillvo.getParentVO();
			// �����ͷ�Զ������
			Map<String, String[]> mapdvdefdept = yyrbhvo.getVdefdeptinfo();	// key-����pk value-[�������������ơ����ű���]
			String vdef1 = yyrbhvo.getVdef01();		// ������Ϣ
			// shouru_bm01=1001NC1000000000036P��03020201���ͷ�һ��,shouru_bm02=1001NC1000000000036R��03020202���ͷ�����,shouru_bm03=1001NC1000000000036T��03020203���ͷ�����,shouru_bm04=1001NC1000000000036V��03020204���ͷ��Ĳ�,shouru_bm05=1001NC1000000000036X��03020205���ͷ����,shouru_bm06=1001NC1000000000036Z��03020206���ͷ�����,shouru_bm07=1001NC10000000000371��03020207���ͷ��߲�,shouru_bm08=1001NC10000000000377��03020301�������Ĳ�,shouru_bm09=1001NC1000000000037D��03020401����һ��,shouru_bm10=1001NC1000000000037F��03020402���ڶ���,shouru_bm11=1001NC1000000000037H��03020403��������,shouru_bm12=1001NC1000000000037L��03020405��������,shouru_bm13=1001NC1000000000037N��03020406��������,shouru_bm14=1001NC1000000000037P��03020407��������,shouru_bm15=1001NC1000000000037R��03020408���ڰ���,shouru_bm16=1001NC1000000000037X��03020411����ʮһ��,shouru_bm17=1001NC10000000000389��030205����ԡ,shouru_bm18=1001NC1000000000038B��030206��Ůԡ,shouru_bm19=1001NC10000000000397��030220��������,shouru_bm20=1001NC1000000000039F��030224���������,shouru_bm21=1001NC1000000000039J��030226��������,
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
					String[] deptinfos = vdefinfo[1].split("��");
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
			// ��֯+���� Ҫ���м�������
			if (!pklock.addBatchDynamicLock(new String[] { pk_org
					+ dbilldate.toString() })) {
				throw new BusinessException("����֯+�����Ѿ������������Ժ�����!");
			}

			UFBoolean isJd = PuPubVO.getUFBoolean_NullAs(yyrbhvo.getVdef10(),UFBoolean.FALSE);
			// ��ȡ���˷�ʽ���Ŀ�Ķ�Ӧ��ϵ ( ֻȡ �����˿�Ŀ�� )
			Map<String, JzfsHVO> jzfs_km_map = getJzfs_KM_MapInfo(pk_org,isJd);	// key-���˷�ʽpk �� value-���˷�ʽvo
			
			// ��ȡ������Ŀ���Ŀ�Ķ�Ӧ��ϵ ( ֻȡ �����˿�Ŀ�� )
			Map<String, SrxmHVO> srxm_km_map = getSrxm_KM_MapInfo(pk_org,isJd);	// key-������Ŀpk �� value-������Ŀvo
			
			/**
			 * ѭ��������Ŀ���� ��Ŀ���� ת��Ϊpk
			 * ���
			 * 2016��6��3��09:48:17
			 */
			if( srxm_km_map!=null && srxm_km_map.size()>0 )
			{
				StringBuffer querySQL = 
						new StringBuffer("select ")
								.append(" doc.code ")
								.append(",doc.pk_defdoc ")
								.append(" from bd_defdoc doc ")
								.append(" where doc.dr=0 ")
								.append(" and doc.pk_defdoclist = '1001N510000000000RUV' ")	// ��Ŀ����
								.append(" and doc.pk_group = '0001N510000000000EGY' ")
								.append(" and doc.pk_org in ('0001N510000000000EGY','"+pk_org+"') ")
								.append(" order by doc.code ")
				;
				ArrayList list = (ArrayList)this.getBD().executeQuery(querySQL.toString(), new ArrayListProcessor());
				HashMap<String,String> MAP_xmdn = new HashMap<String,String>();	// ��Ŀ����MAP  key-����code  value-����pk
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
							.append(" and doc.pk_defdoclist = '1001N510000000002OXB' ")	// ��Ŀ����
							.append(" and doc.pk_group = '0001N510000000000EGY' ")
				;
				ArrayList list_2 = (ArrayList)this.getBD().executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				HashMap<String,String> MAP_xmDL = new HashMap<String,String>();	// ��Ŀ����MAP  key-����code  value-����pk
				for(int i=0;list_2!=null&&i<list_2.size();i++)
				{
					Object[] obj = (Object[])list_2.get(i);
					MAP_xmDL.put(
							  PuPubVO.getString_TrimZeroLenAsNull(obj[0])
							, PuPubVO.getString_TrimZeroLenAsNull(obj[1])
							);
				}
				
				String org_code = HKJT_PUB.MAP_corp_dianCode.get(pk_org);	// ��˾����
				String[] srxm_key = new String[srxm_km_map.size()];
				srxm_key = srxm_km_map.keySet().toArray(srxm_key);
				for( int i=0;i<srxm_key.length;i++ )
				{	
					SrxmHVO srxmHVO = srxm_km_map.get(srxm_key[i]);
					String xmdn = PuPubVO.getString_TrimZeroLenAsNull(srxmHVO.getVdef1());	// ������Ŀ ��ͷ�Զ���1  ��� ��Ŀ����
					
					if( xmdn==null || "~".equals(xmdn) ) continue;
					
					// �����ַ���
					// ��ʽһ �� ��֯����=��Ŀ���롢��֯����=��Ŀ����
					// ��ʽ�� �� ��Ŀ����
					// ���� ����Ҫ ����Ŀ���ำֵ�� ȡ 
					if( xmdn.indexOf("=")>=0 )
					{// �������=����˵���� ��ʽһ
						String[] xmdn_temp = xmdn.split("��");	// �ٺ� �ָ� �����˾
						for( int ii=0;xmdn_temp!=null&&ii<xmdn_temp.length;ii++ )
						{
							String[] xmdn_temp_ii = xmdn_temp[ii].split("=");
							String v1 = PuPubVO.getString_TrimZeroLenAsNull(xmdn_temp_ii[0]);
							String v2 = PuPubVO.getString_TrimZeroLenAsNull(xmdn_temp_ii[1]);
							
							if( org_code.equals(v1) )
							{
								srxmHVO.setVdef1( MAP_xmdn.get(v2) );	// �� ��Ŀ�������� �����pk
								String xmdl = v2.substring(0, 3);		// ǰ��λ �� ��Ŀ�������
								srxmHVO.setVdef2( MAP_xmDL.get(xmdl) );	// �� ��Ŀ������� �����pk
								break;
							}
						}
					}
					else
					{
						srxmHVO.setVdef1( MAP_xmdn.get(xmdn) );	// �� ��Ŀ�������� �����pk
						String xmdl = xmdn.substring(0, 3);		// ǰ��λ �� ��Ŀ�������
						srxmHVO.setVdef2( MAP_xmDL.get(xmdl) );	// �� ��Ŀ������� �����pk
					}
				}
			}
			/**END*/
			
			// ����ƾ֤��Ϣ
			YyribaoBVO[] yyrbbvos = (YyribaoBVO[]) yyrbBillvo.getChildrenVO();
			
			// ����  ������Ŀ ����
			List<YyribaoBVO> list_srxmpz_NC = handlerSrxmPingZheng_NC(
					yyrbhvo, yyrbbvos, srxm_km_map );
			// ����  ���˷�ʽ ����
			List<YyribaoBVO> list_jzfspz_NC = handlerJzfsPingZheng_NC(
					yyrbhvo, yyrbbvos, jzfs_km_map );
			
//			System.out.println("=="+list_srxmpz_NC+"=="+list_srxmpz_NC);
			
//			if(true) throw new BusinessException("������");
			
			// ����ƾ֤����
			this.sendVoucher(yyrbhvo , list_srxmpz_NC , list_jzfspz_NC , flag);
			

		}
		return null;
	}
	
	/**
	 * ������Ŀ��Ĭ��Ϊ������ƾ֤����
	 * ���  2016��5��10��14:12:49 
	 */
	private List<YyribaoBVO> handlerSrxmPingZheng_NC(
			YyribaoHVO   srdbhvo,		// Ӫҵ�ձ�-��ͷ
			YyribaoBVO[] srdbbvos,		// Ӫҵ�ձ�-����
			Map<String,SrxmHVO> srxm_km_map	// ������ĿVO
	) throws BusinessException {
		
		List<YyribaoBVO> list_NC = new ArrayList<YyribaoBVO>();
		
		String pk_org = srdbhvo.getPk_org();	// ��˾
		UFBoolean isJd = PuPubVO.getUFBoolean_NullAs( srdbhvo.getVdef10() , UFBoolean.FALSE);
		
		for (int i = 0; i < srdbbvos.length; i++) {
			
//			if(i==35)
//			{
//				System.out.println("=="+i+"==");
//			}
			
			YyribaoBVO srdbbvo = srdbbvos[i];
			String pk_srxm  = PuPubVO.getString_TrimZeroLenAsNull( srdbbvo.getSrmx_pk() );
			String srxmname = PuPubVO.getString_TrimZeroLenAsNull( srdbbvo.getSrmx_name() ) ;
			
			if(pk_srxm==null || "~".equals(pk_srxm)) continue;	// ������Ŀpk Ϊ��    ��������
			
			/**
			 * �� ����ƾ֤�Ľ���ֶ� �ĳ� ����
			 * ��� 2016��4��25��17:26:09
			 * �Ƶ� �� ���  Ҫ���ֶԴ��� ��Ϊ�Ƶ�� ����û��ֵ�� ����  Ҫȡ Ӧ�ա�   ��ݵ� ȡ ���롣
			 */
			UFDouble srxm_jine = getNull_Zero(srdbbvo.getShouru());
//			if(isJd.booleanValue()&&HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org)){
//				// �Ƶ�
//				srxm_jine = getNull_Zero(srdbbvo.getYingshou());
//			}else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)) {
//				// ���
//			}else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)) {
//				// �Ƶ�
//				srxm_jine = getNull_Zero(srdbbvo.getYingshou());
//			}
			// 2020��3��27��15:52:41
			if(isJd.booleanValue()){
				// �Ƶ�
				srxm_jine = getNull_Zero(srdbbvo.getYingshou());
			}
			/**END*/

			// ��ȡ������Ŀ�Ƿ��ж�Ӧ�Ļ�ƿ�Ŀ�����򲻽��д���
			String pk_kjkm = srxm_km_map.get(pk_srxm) == null ? null
					: srxm_km_map.get(pk_srxm).getPk_kjkm();
			
			// ��Ŀ Ϊ��   ��������
			if( PuPubVO.getString_TrimZeroLenAsNull(pk_kjkm) == null || "~".equals(pk_kjkm) ) continue;
			// ��� Ϊ��   ��������
			if( PuPubVO.getUFDouble_ZeroAsNull(srxm_jine) == null )	continue;

			if (true) {
				
				String xmdn = srxm_km_map.get(pk_srxm).getVdef1();	// ��Ŀ����pk
				String xmDL = srxm_km_map.get(pk_srxm).getVdef2();	// ��Ŀ����pk
				
				// ����������Ŀ��Ϣ
				Map<String, String[]> map = srdbhvo.getVdefdeptinfo();
				Set<String> set = map.keySet();
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					String pk_dept = it.next();
					String[] defpinfo = map.get(pk_dept);
					// �Զ�����(���Ž���ֶ�)
					String vdef_key = defpinfo[0];
					String deptcode = defpinfo[2];// ���ű���
					String deptname = defpinfo[1];// ��������
					
					UFDouble bm_je = PuPubVO.getUFDouble_ZeroAsNull( srdbbvo.getAttributeValue(vdef_key) );	// ��� ���Ž��
					
					if ( bm_je!=null ) {	// �в���
						
						YyribaoBVO yyrbBVO_list = new YyribaoBVO();
						
						yyrbBVO_list.setPk_kjkm(pk_kjkm);	// ��Ŀ
						yyrbBVO_list.setPk_dept(pk_dept);	// ����
						yyrbBVO_list.setDaifang(bm_je);		// ��������Ҫ���� ������Ŀ�����÷���  ������ �������跽��
						yyrbBVO_list.setVbmemo( srxmname );	// ��ע-ժҪ���ڴ� ��װ�� ժҪ��
						yyrbBVO_list.setVbdef01( xmdn );	// �Զ���01  ��Ŀ����
						yyrbBVO_list.setVbdef02( xmDL );	// �Զ���02  ��Ŀ����
						
						list_NC.add(yyrbBVO_list);
						
					}

//					else {	// �޲���
//	
//						YyribaoBVO yyrbBVO_list = new YyribaoBVO();
//						
//						yyrbBVO_list.setPk_kjkm(pk_kjkm);	// ��Ŀ
//						yyrbBVO_list.setPk_dept(null);		// ����
//						yyrbBVO_list.setDaifang(srxm_jine);	// ����
//						yyrbBVO_list.setVbmemo( srxmname );	// ��ע-ժҪ ���ڴ� ��װ�� ժҪ��
//						yyrbBVO_list.setVbdef01( xmdn );	// �Զ���01  ��Ŀ����
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
	 * ���˷�ʽ��Ĭ��Ϊ�跽��ƾ֤����
	 * ��� 2016��5��10��17:13:23
	 */
	private List<YyribaoBVO> handlerJzfsPingZheng_NC(
			YyribaoHVO   srdbhvo,		// Ӫҵ�ձ� ��vo
			YyribaoBVO[] srdbbvos,		// Ӫҵ�ձ� ��vo
			Map<String, JzfsHVO> jzfs_km_map	// ���˷�ʽ
			) throws BusinessException {
		
		List<YyribaoBVO> list_NC = new ArrayList<YyribaoBVO>();
		
		String pk_org = srdbhvo.getPk_org();	// ��֯
		// �Ƿ�Ƶ�
		boolean isJD = PuPubVO.getUFBoolean_NullAs(srdbhvo.getVdef10(), UFBoolean.FALSE).booleanValue();
		/**
		 * 1���� Ӫҵ�ձ���vo  ��������
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
		 * 2������Ҫ����ƾ֤�� ���� �ó���
		 * ԭ���� ��ѭ�� ����ӡ�  ��Ϊ��������жϳ� ǰһ���� �����¼���  �Ȱ� ǰһ�� ���� temp�
		 */
		List<YyribaoBVO> list_2 = new ArrayList<YyribaoBVO>();
		YyribaoBVO yyrbBVO_temp = null;
		String jzfs_code_temp   = "Begin";
		String jzfs_pk_temp     = null;
		boolean isWanglai_temp	= false;	// �Ƿ�����
		for(int i = 0 ; i < srdbbvos.length ; i++)
		{
			String jzfs_pk   = PuPubVO.getString_TrimZeroLenAsNull( srdbbvos[i].getJzfs_pk() );
			String jzfs_name = PuPubVO.getString_TrimZeroLenAsNull( srdbbvos[i].getJzfs_name() );
			String jzfs_code = PuPubVO.getString_TrimZeroLenAsNull( srdbbvos[i].getJzfs_code() );
			UFDouble jine 	 = PuPubVO.getUFDouble_ZeroAsNull( srdbbvos[i].getJine() );
			
//			if ( jine != null 
//			&& UFDouble.ONE_DBL.compareTo(jine) == 0
//			) {
//				System.out.println("====");
//			}
//			
//			if ( jine != null 
//			&& new UFDouble(-2.0).compareTo(jine) == 0
//			) {
//				System.out.println("====");
//			}
			
			// ���߽�Ϊ��  ˵���Ѿ��������
			if( jzfs_pk==null && jzfs_name==null && jzfs_code==null )
			{
				break;
			}
			
			// ���߽Բ�Ϊ��
			if( jzfs_pk!=null && jzfs_name!=null && jzfs_code!=null )
			{
				// �ж� ���α��� �Ƿ� �������ϴε��¼�, ���� �ϴ� ����������
				if(  !jzfs_code.startsWith(jzfs_code_temp)
				  && !isWanglai_temp	
				  )
				{// ���������  ����ϴεķ��ڻ�����
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
			
			// ����Ϊ�գ�һ�߲�Ϊ�� ��ֻ�� ���˷�ʽ���Ʋ�Ϊ�գ�
			if( jzfs_pk==null && jzfs_name!=null && jzfs_code==null )
			{
				srdbbvos[i].setJzfs_pk(jzfs_pk_temp);
				srdbbvos[i].setJzfs_code(jzfs_code_temp);
				
				// ���ξ͵���(ֻ�� �н���)
				if( jine!=null )
					list_2.add(srdbbvos[i]);
				
				// �����»���
//				yyrbBVO_temp	= srdbbvos[i];
//				jzfs_code_temp	= jzfs_code;
//				jzfs_pk_temp	= jzfs_pk;
				isWanglai_temp	= true;
			}
		}
	
		/**
		 * 3������2�Ľ����  �� ��� ƾ֤��Ϣ
		 *   Ϊ  ���յķ��� ���
		 *   ���� �������  �� ���� �跽���������
		 */
		for( YyribaoBVO yyrbBVO_3 : list_2 )
		{
			
//			if ("POS-��Ԥ��".equals(yyrbBVO_3.getJzfs_name())) {
//				System.out.println("===");
//			}
			
			JzfsHVO jzfsVO = jzfs_km_map.get( yyrbBVO_3.getJzfs_pk() );
			
			if( jzfsVO!=null && jzfsVO.getPk_kjkm()!=null )
			{
				yyrbBVO_3.setPk_kjkm( jzfsVO.getPk_kjkm() );	// ��Ŀ
				
				int JD = PuPubVO.getInteger_NullAs(jzfsVO.getJdinfo(), 1);	// 1-�衢2-��  �����˷�ʽ Ĭ��Ϊ�跽��
				if(1==JD)
				{
					yyrbBVO_3.setJiefang( yyrbBVO_3.getJine() );	// �跽
				}
				else if(2==JD)
				{
					yyrbBVO_3.setDaifang( yyrbBVO_3.getJine() );	// ����
				}
				
				/**
				 * Ѻ��������  ��  �����Ž跽�� �����Ŵ���������ȡ����
				 * 2016��7��21��17:19:33
				 */
				if("Ѻ��������".equals(jzfsVO.getName()))
				{
					if( UFDouble.ZERO_DBL.compareTo(yyrbBVO_3.getJine()) > 0 )
					{// ��� С����
						yyrbBVO_3.setJiefang( null );
						yyrbBVO_3.setDaifang( UFDouble.ZERO_DBL.sub(yyrbBVO_3.getJine()) );	// ������� ȡ���� �ŵ� ����
					}
				}
				/**END*/
				
				yyrbBVO_3.setVbmemo( yyrbBVO_3.getJzfs_name().trim() );// ��ע-ժҪ ���ڴ� ��װ�� ժҪ��
				
				/**
				 * ���� pk_org + jzfs_name  ��ȡ�ÿͻ�
				 * ֻ��� ������  ���д���
				 */
				String jzfs_code = PuPubVO.getString_TrimZeroLenAsNull(yyrbBVO_3.getJzfs_code());
				String jzfs_name = null; // ����ƥ�� �ͻ�����				
				if(isJD)
				{// �Ƶ�
					if(   jzfs_code == null
					  || (jzfs_code.startsWith("05-") && jzfs_code.length() >= 7) // תӦ��
					  || (jzfs_code.startsWith("08-") && jzfs_code.length() >= 7) // ��Ա������
					)
					{
						jzfs_name = PuPubVO.getString_TrimZeroLenAsNull(
							yyrbBVO_3.getJzfs_name()==null?null:yyrbBVO_3.getJzfs_name().replaceAll("���˻���","")
						);
					}
				}
				else
				{// ���
					if(   jzfs_code==null
					  || (jzfs_code.startsWith("05") && jzfs_code.length()>=4)
					)
					{
						jzfs_name = PuPubVO.getString_TrimZeroLenAsNull(
							yyrbBVO_3.getJzfs_name()==null?null:yyrbBVO_3.getJzfs_name().replaceAll("���˻���","")
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
						yyrbBVO_3.setVbdef03(pk_customer);	// �Զ���3 ����pk
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
		 * �жϽ���� ��� �Ƿ�һ��
		 */
		UFDouble total_jiefang = UFDouble.ZERO_DBL;	// �跽 ֮��
		UFDouble total_daifang = UFDouble.ZERO_DBL;	// ���� ֮��
		String jie_info = "";	// �跽info
		String dai_info = "";	// ����info
		for(int i=0;yyrbBVOs!=null&&i<yyrbBVOs.length;i++)
		{
			total_jiefang = total_jiefang.add( // �跽
					PuPubVO.getUFDouble_NullAsZero( yyrbBVOs[i].getJiefang() )
			);
			total_daifang = total_daifang.add( // ����
					PuPubVO.getUFDouble_NullAsZero( yyrbBVOs[i].getDaifang() )
			);
			
			if (PuPubVO.getUFDouble_ZeroAsNull(yyrbBVOs[i].getJiefang()) != null) {
				// �跽��� ��Ϊ��
				jie_info += (
					"[" + yyrbBVOs[i].getVbmemo() + "]" + 
					yyrbBVOs[i].getJiefang() +
					"\r\n"
				);
			}
			if (PuPubVO.getUFDouble_ZeroAsNull(yyrbBVOs[i].getDaifang()) != null) {
				// ������� ��Ϊ��
				dai_info += (
					"[" + yyrbBVOs[i].getVbmemo() + "]" + 
					yyrbBVOs[i].getDaifang() +
					"\r\n"
				);
			}
		}
		
		if(total_jiefang.compareTo(total_daifang) !=0 ){	
			throw new BusinessException(
				"�������һ�£����顣\r\n" +
				"���跽��"+total_jiefang+"��\r\n" +
				"��������"+total_daifang+"��\r\n" +
				"�跽��Ϣ��\r\n" +
				jie_info + 
				"������Ϣ��\r\n" +
				dai_info
			);
		}		
		/***END***/
		
		/**
		 * ����
		 */
//		if(true)
//		{
//			throw new BusinessException("==����==");
//		}
		/***END***/
		
//		System.out.println("=="+yyrbBVOs);
		
		if( flag==0 )
		{
			// �ж� �������ɹ�ƾ֤
			FipExtendAggVO[] pz = NCLocator.getInstance().lookup(IFipBillQueryService.class).queryDesBillBySrc(	// ���ݵ�����Ϣ ��ѯ �����ɵ�ƾ֤
					 new FipRelationInfoVO[]{ constructFipRalactionInfo(yyrbBillVO) } 
					,null
			);
			boolean hasPZ = pz!=null && pz.length>0;	// �Ƿ����ƾ֤
			if( hasPZ )
				throw new BusinessException("�����ɹ�ƾ֤�������ظ����ɡ�");
			
			// ����ƾ֤
			this.sendMsgFip(
					 new YyribaoBillVO[]{yyrbBillVO}
					,FipMessageVO.MESSAGETYPE_ADD
			);
		}
		else if( flag==1 )
		{
			// ɾ��ƾ֤
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
			
			//0��ʾ����,������ֵ��Ĭ��Ϊ0
			fipmessagevo.setMessagetype(type);		
	//		 //DirtyĬ��Ϊfalse��AuotSumĬ��Ϊfalse
	//		fipmessagevo.setDirty(false);
			fipmessagevo.setAuotSum(false);
	//		//PrimaryKey ���Բ��ø�ֵ
	//		fipmessagevo.setPrimaryKey("");
			
			//���BillVO
			fipmessagevo.setBillVO(billVos[i]);
			//���FipRelationInfoVO
			fipmessagevo.setMessageinfo(reVO);
			messageVOs[i]=fipmessagevo;			
		}
		
		invokeFipMessage(messageVOs);
		
	}

	public static FipRelationInfoVO constructFipRalactionInfo(YyribaoBillVO billVo) {	
	
		//�����ϢVO		
		FipRelationInfoVO relation=new FipRelationInfoVO();
		
		String sbilltype="HK06";
		BilltypeVO billType = PfDataCache.getBillType(sbilltype);
		
		relation.setPk_group( billVo.getParentVO().getPk_group() );	// ����
		relation.setPk_org( billVo.getParentVO().getPk_org() );		// ��֯
		relation.setRelationID( billVo.getPrimaryKey() );			// ����ID
		relation.setPk_system( billType.getSystemcode() );			// ��Դϵͳ
		relation.setBusidate( billVo.getParentVO().getDbilldate() );// ҵ������
		relation.setPk_billtype(sbilltype);							// ��������
		relation.setPk_operator(InvocationInfoProxy.getInstance().getUserId());	// �Ƶ���

		relation.setFreedef1(billVo.getParentVO().getVbillcode());	// ���ݺ� 
		relation.setFreedef2(billVo.getParentVO().getVmemo());		// ��ע��Ϣ
		
		return relation;		
		
	}

	private static FipMsgResultVO[] invokeFipMessage(FipMessageVO[] messageVOs) throws BusinessException {
		if (Logger.isDebugEnabled()) {
			Logger.info("sendMessage is over!");
		}
		return NCLocator.getInstance().lookup(IFipMessageService.class).sendMessages(messageVOs);
	}
	
}
