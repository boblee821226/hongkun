package nc.impl.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHG_hzshujuMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.VectorProcessor;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.pub.billcode.itf.IBillcodeManage;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzVO_YY;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZGZShuJuVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HykACkInfoVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.SgsjInfoVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.WangLaiInfoVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.YyrbDeptInfoVO;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.othersystem.BanCiVO;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO_YY;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoHVO;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class Hg_hzshujuMaintainImpl implements IHG_hzshujuMaintain {

	@Override
	public HZShuJuVO[] handlerHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws BusinessException {
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
				getBD().updateVOArray(
						upvos.toArray(new HZShuJuVO[] {}),
						new String[] { "yingshou", "zdyh", "sgyh", "kblyh",
								"shishou", "qrsr", "xianjin", "pos", "zhipiao",
								"huiyuanka", "fenqu", "miandan", "daijinquan",
								"cika", "qtyh", "huiyuankashiji",
								"youhui_sg_01", "youhui_sg_02", "youhui_sg_03",
								"youhui_sg_04", "youhui_sg_05", "youhui_sg_06",
								"youhui_sg_07", "youhui_sg_08", "youhui_sg_09",
								"youhui_sg_10" });
			}
			if (delvos.size() > 0) {
				getBD().updateVOArray(delvos.toArray(new HZShuJuVO[] {}),
						new String[] { "dr" });
			}
		}
		// ������ܹ�����Ϣ
		List<HZGZShuJuVO> gzvos = getGuaZhangInfo(orgsql.toString(),
				deptsql.toString(), begindate, enddate);
		if (gzvos != null && gzvos.size() > 0) {
			List<HZGZShuJuVO> up_gzvos = new ArrayList<HZGZShuJuVO>();
			List<HZGZShuJuVO> add_gzvos = new ArrayList<HZGZShuJuVO>();
			List<HZGZShuJuVO> del_gzvos = new ArrayList<HZGZShuJuVO>();
			for (int i = 0; i < gzvos.size(); i++) {
				HZGZShuJuVO gzvo = gzvos.get(i);
				Integer uptype = gzvo.getUptype();
				if (uptype == 0) {
					add_gzvos.add(gzvo);
				} else if (uptype == 1) {
					up_gzvos.add(gzvo);
				} else if (uptype == 2) {
					gzvo.setDr(1);
					del_gzvos.add(gzvo);
				}
			}
			if (add_gzvos.size() > 0) {
				getBD().insertVOArray(add_gzvos.toArray(new HZGZShuJuVO[] {}));
			}
			if (up_gzvos.size() > 0) {
				getBD().updateVOArray(up_gzvos.toArray(new HZGZShuJuVO[] {}),
						new String[] { "gz_jine" });
			}
			if (del_gzvos.size() > 0) {
				getBD().updateVOArray(del_gzvos.toArray(new HZGZShuJuVO[] {}),
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
		sb.append("select zhangdan.vbillcode,zhangdanb.vrowno,zhangdan.pk_group,zhangdan.pk_org,");
		sb.append("zhangdan.pk_org_v,zhangdanb.bm_fid   pk_fdept,zhangdanb.srxm_id  pk_srxm,zhangdanb.bm_id  pk_dept ");
		sb.append("from hk_srgk_hg_zhangdan zhangdan left join hk_srgk_hg_zhangdan_b zhangdanb ");
		sb.append("on zhangdan.pk_hk_dzpt_hg_zhangdan = zhangdanb.pk_hk_dzpt_hg_zhangdan where zhangdan.pk_org in ("
				+ orgsql + ") ");
		sb.append("and to_date(substr(zhangdan.dbilldate, 0, 10), 'yyyy-mm-dd hh24:mi:ss') >=  to_date(substr('"
				+ begindate + "', 0, 10), 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(substr(zhangdan.dbilldate, 0, 10), 'yyyy-mm-dd hh24:mi:ss') <=to_date(substr('"
				+ enddate + "', 0, 10), 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" and nvl(zhangdan.dr, 0) = 0 and nvl(zhangdanb.dr, 0) = 0 and zhangdanb.sqfl_name not in ('��Ա��','�ײͷ���') ");
		if (deptsql != null && deptsql.length() > 0) {
			sb.append("and zhangdanb.bm_id in (" + deptsql + ")");
		}
		List<HZShuJuVO> list = (List<HZShuJuVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(HZShuJuVO.class));
		StringBuffer mess = new StringBuffer();
		if (list == null || list.size() <= 0) {
			mess.append("û��Ҫ������˵����ݣ�");
		} else {
			Set<String> set = new HashSet<String>();
			for (int i = 0; i < list.size(); i++) {
				HZShuJuVO vo = list.get(i);
				String pk_fdept = vo.getPk_fdept();
				String pk_dept = vo.getPk_dept();
				String pk_srxm = vo.getPk_srxm();
				String vbillcode = vo.getVbillcode();
				String vrowno = vo.getVrowno();
				if (pk_fdept == null || pk_fdept.length() <= 0
						|| "~".equals(pk_fdept) || pk_dept == null
						|| pk_dept.length() <= 0 || "~".equals(pk_dept)
						|| pk_srxm == null || pk_srxm.length() <= 0
						|| "~".equals(pk_srxm)) {
					set.add("���˵��ţ�" + vbillcode + ",�к�:" + vrowno + "��\n");
				}
			}
			if (set.size() > 0) {
				Iterator<String> it = set.iterator();
				mess.append("�����˵��Ŷ�Ӧ���к�û���ϼ����ţ����ţ�������Ŀ��Ϣ!����!\n");
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
		sb.append("select decode(hzshuju.pk_hk_srgk_hg_hzshuju,'',0,decode(zhangdanshuju.pk_group||zhangdanshuju.pk_org,'',2,1)) uptype,");
		sb.append("hzshuju.pk_hk_srgk_hg_hzshuju,zhangdanshuju.hzdate,zhangdanshuju.pk_group,zhangdanshuju.pk_org,zhangdanshuju.pk_org_v,zhangdanshuju.pk_fdept,");
		sb.append("zhangdanshuju.pk_srxm,zhangdanshuju.pk_dept,zhangdanshuju.yingshou,zhangdanshuju.zdyh,zhangdanshuju.sgyh,zhangdanshuju.kblyh,zhangdanshuju.shishou, zhangdanshuju.qrsr, ");
		sb.append(" zhangdanshuju.xianjin,zhangdanshuju.pos,zhangdanshuju.zhipiao,zhangdanshuju.huiyuanka,zhangdanshuju.fengqu fenqu,zhangdanshuju.miandan,zhangdanshuju.daijinquan,zhangdanshuju.cika,zhangdanshuju.qtyh,zhangdanshuju.huiyuankashiji huiyuankashiji, ");
		sb.append(" zhangdanshuju.zhipiao, ");	// ��� 2016��5��31��23:10:54
		sb.append(" zhangdanshuju.youhui_sg_01,zhangdanshuju.youhui_sg_02,");
		sb.append(" zhangdanshuju.youhui_sg_03,zhangdanshuju.youhui_sg_04,zhangdanshuju.youhui_sg_05, zhangdanshuju.youhui_sg_06,");
		sb.append(" zhangdanshuju.youhui_sg_07,zhangdanshuju.youhui_sg_08,zhangdanshuju.youhui_sg_09,");
		sb.append(" zhangdanshuju.youhui_sg_10 ");
		sb.append(" from ( select * from hk_srgk_hg_hzshuju where pk_org in ("
				+ orgsql + ") ");
		sb.append(" and to_date(hzdate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
				+ begindate + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" and to_date(hzdate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
				+ enddate
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and nvl(dr,0)=0 ");
		if (deptsql.length() > 0) {
			sb.append(" and pk_dept in (" + deptsql + ") ");
		}
		sb.append(") hzshuju full outer join (");
		sb.append(" select substr(zhangdan.dbilldate, 0, 10) hzdate,zhangdan.pk_group,zhangdan.pk_org,zhangdan.pk_org_v,zhangdanb.bm_fid pk_fdept,zhangdanb.srxm_id pk_srxm,");
		sb.append("zhangdanb.bm_id pk_dept, sum(zhangdanb.yingshou) yingshou,sum(zhangdanb.youhui_zidong) zdyh,sum(zhangdanb.youhui_shougong) sgyh,sum(zhangdanb.youhui_kabili) kblyh,");
		sb.append("sum(zhangdanb.shishou) shishou,sum(zhangdanb.shouru) qrsr, sum(zhangdanb.xianjin) xianjin,");
		sb.append(" sum(zhangdanb.pos) pos,sum(zhangdanb.huiyuanka) huiyuanka,sum(zhangdanb.fenqu) fengqu,sum(zhangdanb.miandan) miandan,sum(zhangdanb.zhipiao) zhipiao,");
		sb.append("sum(zhangdanb.daijinquan) daijinquan,sum(zhangdanb.cika) cika,sum(zhangdanb.youhui_qt) qtyh"
				+
				// ",sum(zhangdanb.huiyuanka)-sum(zhangdanb.youhui_kabili) huiyuankashiji "
				",sum( nvl(zhangdanb.huiyuanka,0) - nvl(zhangdanb.youhui_kabili,0) ) huiyuankashiji, " // �ȷǿմ���
																										// �����㡣�����
																										// 2015��7��22��16:26:49��
		);
		sb.append(" sum(zhangdanb.youhui_sg_01) youhui_sg_01,sum(zhangdanb.youhui_sg_02) youhui_sg_02,");
		sb.append(" sum(zhangdanb.youhui_sg_03) youhui_sg_03,sum(zhangdanb.youhui_sg_04) youhui_sg_04,");
		sb.append(" sum(zhangdanb.youhui_sg_05) youhui_sg_05,sum(zhangdanb.youhui_sg_06) youhui_sg_06,");
		sb.append(" sum(zhangdanb.youhui_sg_07) youhui_sg_07,sum(zhangdanb.youhui_sg_08) youhui_sg_08,");
		sb.append(" sum(zhangdanb.youhui_sg_09) youhui_sg_09,sum(zhangdanb.youhui_sg_10) youhui_sg_10 ");
		sb.append(" from hk_srgk_hg_zhangdan zhangdan ");
		sb.append(" left join hk_srgk_hg_zhangdan_b zhangdanb ");
		sb.append(" on zhangdan.pk_hk_dzpt_hg_zhangdan = zhangdanb.pk_hk_dzpt_hg_zhangdan ");
		sb.append(" where zhangdanb.sqfl_name not in ('��Ա��','�ײͷ���','�ο��ײʹ���') and zhangdan.pk_org in ("
				+ orgsql
				+ ") and  to_date(substr(zhangdan.dbilldate, 0, 10),'yyyy-mm-dd hh24:mi:ss')>=to_date(substr('"
				+ begindate + "', 0, 10),'yyyy-mm-dd hh24:mi:ss')");
		sb.append(" and  to_date(substr(zhangdan.dbilldate, 0, 10),'yyyy-mm-dd hh24:mi:ss')<=to_date(substr('"
				+ enddate
				+ "', 0, 10),'yyyy-mm-dd hh24:mi:ss') and nvl(zhangdan.dr,0)=0 and nvl(zhangdanb.dr,0)=0 ");
		if (deptsql.length() > 0) {
			sb.append(" and zhangdanb.bm_id in (" + deptsql + ")");
		}
		sb.append("  group by zhangdan.pk_group,zhangdan.pk_org,zhangdanb.bm_fid,zhangdanb.srxm_id,zhangdanb.bm_id,substr(zhangdan.dbilldate, 0, 10),zhangdan.pk_org_v");
		sb.append(") zhangdanshuju on ");
		sb.append(" substr(hzshuju.hzdate,0,10)=zhangdanshuju.hzdate and hzshuju.pk_group=zhangdanshuju.pk_group ");
		sb.append(" and hzshuju.pk_org=zhangdanshuju.pk_org and hzshuju.pk_org_v=zhangdanshuju.pk_org_v ");
		sb.append(" and hzshuju.pk_dept=zhangdanshuju.pk_dept and hzshuju.pk_fdept=zhangdanshuju.pk_fdept and hzshuju.pk_srxm=zhangdanshuju.pk_srxm");
		List<HZShuJuVO> havedates = (List<HZShuJuVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(HZShuJuVO.class));

		return havedates;

	}

	/**
	 * ���������Ϣ
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private List<HZGZShuJuVO> getGuaZhangInfo(String orgsql, String deptsql,
			String begindate, String enddate) throws DAOException {
		StringBuffer sb = new StringBuffer();
		sb.append("select decode(gzshuju.pk_hk_srgk_hg_gz,'',0,decode(zhangdan.pk_group||zhangdan.pk_org,'',2,1)) uptype,zhangdan.bm_id pk_dept,zhangdan.bm_fid pk_fdept,");
		sb.append("zhangdan.pk_group,zhangdan.pk_org,zhangdan.dbilldate hzdate,zhangdan.guazhang_info gz_info,zhangdan.gz_jine,gzshuju.pk_hk_srgk_hg_gz ");
		sb.append(" from (select h.pk_group, h.pk_org,h.dbilldate,h.guazhang_info,b.bm_id,b.bm_fid,sum(b.guazhang) gz_jine from hk_srgk_hg_zhangdan h ");
		sb.append(" left join hk_srgk_hg_zhangdan_b b  on h.pk_hk_dzpt_hg_zhangdan = b.pk_hk_dzpt_hg_zhangdan  where nvl(h.dr, 0) = 0  and nvl(b.dr, 0) = 0 ");
		sb.append("  and h.guazhang_info is not null  and h.guazhang_info <> '~' ");
		sb.append(" and to_date(h.dbilldate, 'yyyy-mm-dd hh24:mi:ss')>=to_date('"
				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" and to_date(h.dbilldate, 'yyyy-mm-dd hh24:mi:ss')<=to_date('"
				+ enddate + "','yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" and h.pk_org in (" + orgsql + ") ");
		if (deptsql != null && !"".equals(deptsql)) {
			sb.append(" and b.bm_id in (" + deptsql + ") ");
		}
		sb.append(" group by h.pk_group,h.pk_org, h.dbilldate, h.guazhang_info, b.bm_id, b.bm_fid ");
		sb.append(") zhangdan full outer join ");
		sb.append("(select * from ");
		sb.append("hk_srgk_hg_hzshuju_gz where nvl(dr,0)=0 and pk_org in ("
				+ orgsql + ")  ");
		sb.append(" and to_date(hzdate, 'yyyy-mm-dd hh24:mi:ss')>=to_date('"
				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" and to_date(hzdate, 'yyyy-mm-dd hh24:mi:ss')<=to_date('"
				+ enddate + "','yyyy-mm-dd hh24:mi:ss') ");
		if (deptsql != null && !"".equals(deptsql)) {
			sb.append(" and pk_dept in (" + deptsql + ") ");
		}
		sb.append(") gzshuju");
		sb.append(" on zhangdan.pk_org=gzshuju.pk_org and zhangdan.pk_group=gzshuju.pk_group and substr(zhangdan.dbilldate,0,10)=substr(gzshuju.hzdate,0,10) and zhangdan.bm_id=gzshuju.pk_dept ");
		sb.append("and zhangdan.bm_fid = gzshuju.pk_fdept ");
		List<HZGZShuJuVO> havedates = (List<HZGZShuJuVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(HZGZShuJuVO.class));

		return havedates;
	}

	PKLock pklock = null;
	BaseDAO bd = null;

	public PKLock getPklock() {
		if (pklock == null) {
			pklock = PKLock.getInstance();
		}
		return pklock;
	}

	private BaseDAO getBD() {
		if (bd == null) {
			bd = new BaseDAO();
		}
		return bd;
	}

	/**
	 * HK-����2
	 * 2019��1��8��20:23:50
	 */
	private HashMap<String,String> VDEF_Info = null;
	private String[] VDEF_Info_code = null;
	private void get_VDEF_Info() throws DAOException
	{
		try
		{
			VDEF_Info = new HashMap<String,String>();
			
			StringBuffer querySQL = 
					new StringBuffer()
						.append(" select code,name_nc ")
						.append(" from hk_srgk_hg_zhangdan_vdef v ")
						.append(" where v.isused in ('Y','y') ")
			;
			
			ArrayList list = (ArrayList) this.getBD().executeQuery(querySQL.toString(), new ArrayListProcessor());
			
			if(list==null || list.size()<=0) return;
			
			for(Object obj_temp:list)
			{
				Object[] obj = (Object[])obj_temp;
				String code = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
				String name = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
				
				VDEF_Info.put(code, name);
			}
			
			VDEF_Info_code = new String[VDEF_Info.size()];
			VDEF_Info_code = VDEF_Info.keySet().toArray(VDEF_Info_code);
			
		}catch(Exception ex)
		{
			throw new DAOException(ex);
		}
	}
	
	private String getSQL_1()
	{
		String result = "";
		for(int i=0;i<VDEF_Info_code.length;i++)
		{
			result += " and nvl(h."+VDEF_Info_code[i]+",'~') = '~' ";
		}
		
		return result;
	}
	
	private String getSQL_2(String first_org,String pk_org,String begindate,String enddate)
	{
		StringBuffer sql = new StringBuffer();
		
		for( int i=0;i<VDEF_Info_code.length;i++ )
		{
			String code = VDEF_Info_code[i];
			String name = VDEF_Info.get(code);
			
			sql.append(" union all ");
			sql.append("select "
					+ " '"+ first_org +"' as pk_org "
					+ ",'"+ begindate.substring(0, 10) + " 00:00:00' hzdate "
					+ ",sum(to_number(h."+code+")) wanglai_jine" 	// code
					+ ",'"+name+"' wanglai_name " 					// NC_name
					+ " from hk_srgk_hg_zhangdan h ");
			sql.append(" where nvl(h.dr,0)=0 ");
			sql.append(" and h.pk_org in (" + pk_org + ")");
			sql.append(" and ( nvl(h."+code+",'~') != '~' ) ");		// code
			sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
					+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
			sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
					+ enddate
					+ "','yyyy-mm-dd hh24:mi:ss') group by h.wanglai_info ");
		}
		
		return sql.toString();
	}
	
	@Override
	public SrdibiaoBillVO[] selectHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate, Map<String, String[]> map,
			boolean isRs) throws DAOException {
		
		this.get_VDEF_Info();	// ����˵�Ӧ����Ŀ��HK-����2 2019��1��8��20:27:13��
		
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
		// ��ȡ��֯�汾PK
		String pk_org_v = getOrg_v(first_org);
		// ����ò���Ϊ�ϼ����ţ��¼�����Ĭ�϶�Ϊĩ�����Ŵ���
		// ���ݲ���������ѯ�����¼����ŵ�����
		// ��������󱨸棬����Ҫ�������󱨸�����Ͳ������Ĳ�ѯ
		RsbaogaoCVO_YY[] rstz = null;
		CctzVO_YY[] cctz = null;
		if (isRs) {
			rstz = getRsTzInfo(first_org, orgsql.toString(), pk_dept,
					begindate, enddate);
			cctz = getCctzInfo(first_org, orgsql.toString(), pk_dept,
					begindate, enddate);
		}
		// ��ȡ�ֹ�������Ϣ
		SgsjInfoVO[] sgsjvos = getSgsjInfoVOs(first_org, orgsql.toString(),
				pk_dept, begindate, enddate);
		// ��ȡ�������ݣ����룩
		HZShuJuVO[] hzsjvos = getHZShuJuInfo(first_org, orgsql.toString(),
				dept, begindate, enddate, sgsjvos, rstz, cctz);
		// ��ȡ���˷�ʽ��Ϣ
		List<JzfsHVO> jzfsvos = getJzfsInfo();
		// ��ȡ������Ϣ
		Map<String, UFDouble> map_wanglai_zd = new HashMap<String, UFDouble>();
		// ��ȡ���񲿵ĳ俨��Ϣ������֯��ѯʱ��ʹ��
		Map<String, HykACkInfoVO> map_caiwu = new HashMap<String, HykACkInfoVO>();
		// ������Ϣ�Ͳ����������֯��ѯ
		if (pk_dept == null || "".equals(pk_dept)) {
			map_wanglai_zd = getWangLaiInfo(first_org, orgsql.toString(),
					begindate, enddate);
			map_caiwu = getCWHuiyuankaAndCikaJine(first_org, orgsql.toString(),
					begindate, enddate);
		}
		// ��ȡ������Ϣ
		Map<String, UFDouble> gz_info = getGZ_Info(first_org,
				orgsql.toString(), pk_dept, begindate, enddate);

		// ��벿�ֵļ��˷�ʽ��Ϣ����
		Map<String, List<SrdibiaoBVO>> map_leftsfdb = setLeftJzfsInfo(jzfsvos,
				hzsjvos, sgsjvos, dept, map_caiwu, gz_info, rstz, cctz,
				map_wanglai_zd);
		// �����ϲ��Լ�ȫ��������Ŀ�����Ϣ
		Map<String, HZShuJuVO> map_fsrxm_money = getUpSrxmMoney(
				orgsql.toString(), pk_dept, begindate, enddate, hzsjvos);
		// �ұ����ݵĻ��ܲ���
		SrdibiaoBillVO[] billvos = handlerAllDeptHZSJInfo(hzsjvos, dept,
				orgs[0], begindate, enddate, map, map_fsrxm_money, sgsjvos,
				rstz, cctz);
		// �����µĻ�������
		List<SrdibiaoBillVO> list_new = new ArrayList<SrdibiaoBillVO>();
		// ��ȡ��������
		Map<String, Integer> map_jdrs = getInPerson(first_org,
				orgsql.toString(), begindate, enddate);
		// ��벿�ֺ��ְ벿�����ݺϲ�
		if (billvos != null && billvos.length > 0) {
			for (int i = 0; i < billvos.length; i++) {
				SrdibiaoBillVO srdibiaoBillVO = billvos[i];
				SrdibiaoHVO hvo = srdibiaoBillVO.getParentVO();
				// �����ģ����ñ�ͷ��ѯ����
				hvo.setPk_org_v(pk_org_v);
				hvo.setPk_dept(pk_dept);
				SrdibiaoBVO[] srdibiaovo = (SrdibiaoBVO[]) srdibiaoBillVO
						.getChildrenVO();
				String pk_org1 = hvo.getPk_org();
				String dbilldate = hvo.getDbilldate().toString();
				List<SrdibiaoBVO> lfetbvos = map_leftsfdb.get(pk_org1);
				if (srdibiaovo.length >= lfetbvos.size()) {
					List<SrdibiaoBVO> list_bnew = new ArrayList<SrdibiaoBVO>();
					for (int j = 0; j < lfetbvos.size(); j++) {
						SrdibiaoBVO bvo = srdibiaovo[j];
						if (j != lfetbvos.size() - 1) {
							bvo.setJzfs_code(lfetbvos.get(j).getJzfs_code());
							bvo.setJzfs_name(lfetbvos.get(j).getJzfs_name());
							bvo.setJzfs_pk(lfetbvos.get(j).getJzfs_pk());
							bvo.setJine(lfetbvos.get(j).getJine());
						}
					}
					// �������Ľ��
					SrdibiaoBVO bvolast = lfetbvos.get(lfetbvos.size() - 1);
					UFDouble lastjine = bvolast.getJine();
					srdibiaovo[srdibiaovo.length - 1].setJzfs_name("�ϼ�");
					srdibiaovo[srdibiaovo.length - 1].setJine(lastjine);

					for (int j = 0; j < srdibiaovo.length; j++) {
						SrdibiaoBVO srdibiaoBVO = srdibiaovo[j];
						list_bnew.add(srdibiaoBVO);
					}
					// �������һ�н�������
					SrdibiaoBVO bvo_jdrs = new SrdibiaoBVO();
					if (pk_dept == null || "".equals(pk_dept)
							|| "~".equals(pk_dept)) {
						Integer jdrs = getInteger_NullToZero(map_jdrs
								.get(pk_org1 + dbilldate));
						// ��ȡ�ϸ�vo
						SrdibiaoBVO upvo = list_bnew.get(list_bnew.size() - 1);
						bvo_jdrs.setJzfs_name("��������");
						bvo_jdrs.setJine(PuPubVO.getUFDouble_NullAsZero(jdrs));
						computInPersonJine(upvo, bvo_jdrs);
					} else {
						Integer jdrs = getInteger_NullToZero(map_jdrs
								.get(pk_org1 + dbilldate));
						bvo_jdrs.setJzfs_name("��������");
						bvo_jdrs.setJine(PuPubVO.getUFDouble_NullAsZero(jdrs));
					}
					list_bnew.add(bvo_jdrs);
					// ���������к�
					resetVrowNo(list_bnew);
					srdibiaoBillVO.setChildrenVO(list_bnew
							.toArray(new SrdibiaoBVO[] {}));
				} else {
					SrdibiaoBVO bvo_column = new SrdibiaoBVO();
					String[] columns = bvo_column.getAttributeNames();
					for (int j = 0; j < srdibiaovo.length; j++) {
						if (j != srdibiaovo.length - 1) {
							SrdibiaoBVO srdibiaoBVO = srdibiaovo[j];
							SrdibiaoBVO bvo = lfetbvos.get(j);
							for (int k = 0; k < columns.length; k++) {
								String string = columns[k];
								if (!"jzfs_code".equals(string)
										&& !"jzfs_name".equals(string)
										&& !"jzfs_pk".equals(string)
										&& !"jine".equals(string)) {
									bvo.setAttributeValue(string, srdibiaoBVO
											.getAttributeValue(string));
								}

							}
						} else {
							SrdibiaoBVO lastbvo = srdibiaovo[j];
							SrdibiaoBVO lastleftvo = lfetbvos.get(lfetbvos
									.size() - 1);
							lastleftvo.setJzfs_name("�ϼ�");
							for (int k = 0; k < columns.length; k++) {
								String string = columns[k];
								if (!"jzfs_code".equals(string)
										&& !"jzfs_name".equals(string)
										&& !"jzfs_pk".equals(string)
										&& !"jine".equals(string)) {
									lastleftvo.setAttributeValue(string,
											lastbvo.getAttributeValue(string));
								}

							}
						}
					}

					// �������һ�н�������
					SrdibiaoBVO bvo_jdrs = new SrdibiaoBVO();
					if (pk_dept == null || "".equals(pk_dept)
							|| "~".equals(pk_dept)) {
						Integer jdrs = getInteger_NullToZero(map_jdrs
								.get(pk_org1 + dbilldate));
						bvo_jdrs.setJzfs_name("��������");
						bvo_jdrs.setJine(PuPubVO.getUFDouble_NullAsZero(jdrs));
						computInPersonJine(lfetbvos.get(lfetbvos.size() - 1),
								bvo_jdrs);
					} else {
						Integer jdrs = getInteger_NullToZero(map_jdrs
								.get(pk_org1 + dbilldate));
						bvo_jdrs.setJzfs_name("��������");
						bvo_jdrs.setJine(PuPubVO.getUFDouble_NullAsZero(jdrs));
					}
					lfetbvos.add(bvo_jdrs);
					// ���������к�
					resetVrowNo(lfetbvos);
					srdibiaoBillVO.setChildrenVO(lfetbvos
							.toArray(new SrdibiaoBVO[] {}));
				}
				list_new.add(srdibiaoBillVO);
			}
		}
		billvos = list_new.toArray(new SrdibiaoBillVO[] {});

		return billvos;
	}

	/**
	 * ���¸�ֵ�кŴ���
	 * */
	private void resetVrowNo(List<SrdibiaoBVO> srdbbvos) {
		if (srdbbvos != null && srdbbvos.size() > 0) {
			int vrowno = 0;
			for (int i = 0; i < srdbbvos.size(); i++) {
				vrowno = vrowno + 10;
				SrdibiaoBVO bvo = srdbbvos.get(i);
				bvo.setVrowno(String.valueOf(vrowno));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private SrdibiaoBillVO[] handlerAllDeptHZSJInfo(HZShuJuVO[] hzsjvos,
			String pk_dept, String pk_org, String begindate, String enddate,
			Map<String, String[]> map, Map<String, HZShuJuVO> map_fsrxm_money,
			SgsjInfoVO[] sgsjvos, RsbaogaoCVO_YY[] rstz, CctzVO_YY[] cctz)
			throws DAOException {
		boolean isFDept = false;
		if (hzsjvos != null && hzsjvos.length > 0) {
			// ��ȡ������Ŀ������Ϣ
			List<SrxmHVO> list = getSrxmInfo();
			for (int i = 0; i < hzsjvos.length; i++) {
				HZShuJuVO hzShuJuVO = hzsjvos[i];
				String fdept = hzShuJuVO.getPk_fdept();
				// �˴��жϲ�ѯ�����еĲ����Ƿ�Ϊ�ϼ������������isFDept����Ϊtrue֮��Ͳ���Ҫ���ж���
				if (!isFDept) {
					if (pk_dept != null && !"".equals(pk_dept) && fdept != null
							&& fdept.equals(pk_dept)) {
						isFDept = true;
						break;
					}
				}

			}
			// ����������ϼ��������ٽ��в�ѯ�ϼ����ŵĻ�����
			if (isFDept) {
				StringBuffer sb = new StringBuffer();
				sb.append("select hzsj.*,srxm.code srxm_code,srxm.name srxm_name,srxm.pk_parent srxm_fpk,srxm.levelno srxm_levelno,fsrxm.name srxm_fname,fsrxm.code srxm_fcode,fsrxm.levelno srxm_flevelno  from (");
				String sql = "select pk_group,pk_org,pk_org_v,pk_fdept,pk_srxm,'"
						+ begindate.substring(0, 10)
						+ " 00:00:00' hzdate,"
						+ " sum(yingshou) yingshou, sum(zdyh) zdyh,sum(sgyh) sgyh, sum(shishou) shishou, sum(kblyh) kblyh,sum(qrsr) qrsr "
						+ " from hk_srgk_hg_hzshuju where 1=1 and nvl(dr,0)=0 and pk_fdept in ('"
						+ pk_dept
						+ "') and pk_org in ('"
						+ pk_org
						+ "') and  to_date(hzdate,'yyyy-mm-dd hh24:mi:ss')>= to_date('"
						+ begindate
						+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')"
						+ " and  to_date(hzdate,'yyyy-mm-dd hh24:mi:ss')<= to_date('"
						+ enddate
						+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') group by pk_group,pk_org,pk_org_v,pk_fdept,pk_srxm ";
				sb.append(sql);
				sb.append(" ) hzsj left join hk_srgk_hg_srxm srxm on srxm.pk_hk_srgk_hg_srxm=hzsj.pk_srxm ");
				sb.append(" left join hk_srgk_hg_srxm fsrxm on fsrxm.pk_hk_srgk_hg_srxm=srxm.pk_parent where nvl(srxm.dr,0)=0 and nvl(fsrxm.dr,0)=0  order by srxm.code");
				List<HZShuJuVO> vos = (List<HZShuJuVO>) getBD().executeQuery(
						sb.toString(), new BeanListProcessor(HZShuJuVO.class));
				// �˴������ֹ���������������Ŀ
				handlerAddSrxm(vos, sgsjvos, cctz, rstz);
				if (vos != null && vos.size() > 0) {
					// ѭ���ж��ֹ��������Ƿ��ж�Ӧ��������Ŀ������Ϣ,���д��Ż���
					for (int i = 0; i < vos.size(); i++) {
						HZShuJuVO hzsjvo = vos.get(i);
						String hzsj_group = hzsjvo.getPk_group();
						String hzsj_org = hzsjvo.getPk_org();
						String hzsj_dept = hzsjvo.getPk_fdept() == null ? ""
								: hzsjvo.getPk_fdept();
						String hzsj_hzdate = hzsjvo.getHzdate().toString();
						String hzsj_srxm = hzsjvo.getPk_srxm();
						for (int j = 0; j < sgsjvos.length; j++) {
							SgsjInfoVO sgsjvo = sgsjvos[j];
							String sgsj_group = sgsjvo.getPk_group();
							String sgsj_org = sgsjvo.getPk_org();
							String sgsj_dept = sgsjvo.getDept_fpk() == null ? ""
									: sgsjvo.getDept_fpk();
							String sgsj_hzdate = sgsjvo.getHzdate().substring(
									0, 10)
									+ " 00:00:00";
							String sgsj_tzsrxm1 = sgsjvo.getTz_km_srxm_1();
							String sgsj_tzdate1 = sgsjvo.getTz_km_data_1();
							String sgsj_tzsrxm2 = sgsjvo.getTz_km_srxm_2();
							String sgsj_tzdate2 = sgsjvo.getTz_km_data_2();
							String sgsj_type1 = sgsjvo.getTz_km_srxm_type1();
							String sgsj_type2 = sgsjvo.getTz_km_srxm_type2();
							if (sgsj_tzsrxm1 != null
									&& sgsj_tzsrxm1.equals(hzsj_srxm)) {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)
										&& sgsj_dept.equals(hzsj_dept)) {
									if ("xse".equals(sgsj_type1)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("zdyh".equals(sgsj_type1)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sgyh".equals(sgsj_type1)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("kblyh".equals(sgsj_type1)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sr".equals(sgsj_type1)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									}
								}
							}
							if (sgsj_tzsrxm2 != null
									&& sgsj_tzsrxm2.equals(hzsj_srxm)) {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)
										&& sgsj_dept.equals(hzsj_dept)) {
									if ("xse".equals(sgsj_type2)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("zdyh".equals(sgsj_type2)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sgyh".equals(sgsj_type2)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("kblyh".equals(sgsj_type2)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sr".equals(sgsj_type2)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									}
								}
							}
						}
						// �˴��������������Ϣ�����Ϊ��Ĭ��Ϊ����ױ��ѯ
						if (rstz != null && rstz.length > 0) {
							for (int j = 0; j < rstz.length; j++) {
								RsbaogaoCVO_YY rstzvo = rstz[j];

								String sgsj_group = rstzvo.getPk_group();
								String sgsj_org = rstzvo.getPk_org();
								String sgsj_dept = rstzvo.getPk_fdept() == null ? ""
										: rstzvo.getPk_fdept();
								String sgsj_hzdate = rstzvo.getDbilldate()
										.substring(0, 10) + " 00:00:00";
								String sgsj_tzsrxm1 = rstzvo.getTz_km_srxm_1();
								UFDouble sgsj_tzdate1 = rstzvo
										.getTz_km_data_1();
								String sgsj_tzsrxm2 = rstzvo.getTz_km_srxm_2();
								UFDouble sgsj_tzdate2 = rstzvo
										.getTz_km_data_2();

								String sgsj_type1 = rstzvo
										.getTz_km_srxm_type1();
								String sgsj_type2 = rstzvo
										.getTz_km_srxm_type2();
								if (sgsj_tzsrxm1 != null
										&& sgsj_tzsrxm1.equals(hzsj_srxm)) {
									if (sgsj_group.equals(hzsj_group)
											&& sgsj_org.equals(hzsj_org)
											&& sgsj_hzdate.equals(hzsj_hzdate)
											&& sgsj_dept.equals(hzsj_dept)) {

										if ("xse".equals(sgsj_type1)) {
											hzsjvo.setYingshou(hzsjvo
													.getYingshou()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										} else if ("zdyh".equals(sgsj_type1)) {
											hzsjvo.setZdyh(hzsjvo
													.getZdyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										} else if ("sgyh".equals(sgsj_type1)) {
											hzsjvo.setSgyh(hzsjvo
													.getSgyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										} else if ("kblyh".equals(sgsj_type1)) {
											hzsjvo.setKblyh(hzsjvo
													.getKblyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										} else if ("sr".equals(sgsj_type1)) {
											hzsjvo.setQrsr(hzsjvo
													.getQrsr()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										}
									}
								}
								if (sgsj_tzsrxm2 != null
										&& sgsj_tzsrxm2.equals(hzsj_srxm)) {
									if (sgsj_group.equals(hzsj_group)
											&& sgsj_org.equals(hzsj_org)
											&& sgsj_hzdate.equals(hzsj_hzdate)
											&& sgsj_dept.equals(hzsj_dept)) {
										if ("xse".equals(sgsj_type2)) {
											hzsjvo.setYingshou(hzsjvo
													.getYingshou()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										} else if ("zdyh".equals(sgsj_type2)) {
											hzsjvo.setZdyh(hzsjvo
													.getZdyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										} else if ("sgyh".equals(sgsj_type2)) {
											hzsjvo.setSgyh(hzsjvo
													.getSgyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										} else if ("kblyh".equals(sgsj_type2)) {
											hzsjvo.setKblyh(hzsjvo
													.getKblyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										} else if ("sr".equals(sgsj_type2)) {
											hzsjvo.setQrsr(hzsjvo
													.getQrsr()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										}
									}
								}

							}
						}

						// �˴������������Ϣ�����Ϊ��Ĭ��Ϊ����ױ��ѯ
						if (cctz != null && cctz.length > 0) {
							for (int j = 0; j < cctz.length; j++) {
								CctzVO_YY rstzvo = cctz[j];

								String sgsj_group = rstzvo.getPk_group();
								String sgsj_org = rstzvo.getPk_org();
								String sgsj_dept = rstzvo.getPk_fdept() == null ? ""
										: rstzvo.getPk_fdept();
								String sgsj_hzdate = rstzvo.getTz_date()
										.toString().substring(0, 10)
										+ " 00:00:00";
								String sgsj_tzsrxm1 = rstzvo.getTz_km_srxm_1();
								UFDouble sgsj_tzdate1 = rstzvo
										.getTz_km_data_1();
								String sgsj_tzsrxm2 = rstzvo.getTz_km_srxm_2();
								UFDouble sgsj_tzdate2 = rstzvo
										.getTz_km_data_2();
								String sgsj_type1 = rstzvo
										.getTz_km_srxm_type1();
								String sgsj_type2 = rstzvo
										.getTz_km_srxm_type2();
								if (sgsj_tzsrxm1 != null
										&& sgsj_tzsrxm1.equals(hzsj_srxm)) {
									if (sgsj_group.equals(hzsj_group)
											&& sgsj_org.equals(hzsj_org)
											&& sgsj_hzdate.equals(hzsj_hzdate)
											&& sgsj_dept.equals(hzsj_dept)) {
										if ("xse".equals(sgsj_type1)) {
											hzsjvo.setYingshou(hzsjvo
													.getYingshou()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										} else if ("zdyh".equals(sgsj_type1)) {
											hzsjvo.setZdyh(hzsjvo
													.getZdyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										} else if ("sgyh".equals(sgsj_type1)) {
											hzsjvo.setSgyh(hzsjvo
													.getSgyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										} else if ("kblyh".equals(sgsj_type1)) {
											hzsjvo.setKblyh(hzsjvo
													.getKblyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										} else if ("sr".equals(sgsj_type1)) {
											hzsjvo.setQrsr(hzsjvo
													.getQrsr()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate1))));
										}
									}
								}
								if (sgsj_tzsrxm2 != null
										&& sgsj_tzsrxm2.equals(hzsj_srxm)) {
									if (sgsj_group.equals(hzsj_group)
											&& sgsj_org.equals(hzsj_org)
											&& sgsj_hzdate.equals(hzsj_hzdate)
											&& sgsj_dept.equals(hzsj_dept)) {
										if ("xse".equals(sgsj_type2)) {
											hzsjvo.setYingshou(hzsjvo
													.getYingshou()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										} else if ("zdyh".equals(sgsj_type2)) {
											hzsjvo.setZdyh(hzsjvo
													.getZdyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										} else if ("sgyh".equals(sgsj_type2)) {
											hzsjvo.setSgyh(hzsjvo
													.getSgyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										} else if ("kblyh".equals(sgsj_type2)) {
											hzsjvo.setKblyh(hzsjvo
													.getKblyh()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										} else if ("sr".equals(sgsj_type2)) {
											hzsjvo.setQrsr(hzsjvo
													.getQrsr()
													.add(PuPubVO.getUFDouble_NullAsZero(
															getUFDouble_NullToZero(sgsj_tzdate2))));
										}
									}
								}

							}
						}
					}
				}
				SrdibiaoBillVO[] billvos = getSrdibiaoInfo(
						vos.toArray(new HZShuJuVO[] {}), hzsjvos, map,
						map_fsrxm_money, list);
				return billvos;
			} else {
				// �����ĩ�����Ŵ�������Ĭ��Ϊһ������֯Ĭ��Ϊһ����������Ҫ�ٲ�ѯ�ϼ����ŵĻ�������
				return getSrdibiaoInfoForLastDept(hzsjvos, map_fsrxm_money,
						list);
			}

		}
		return null;
	}

	/**
	 * ĩ�����ŷ�װ����ױ�ۺ�VO
	 * */
	private SrdibiaoBillVO[] getSrdibiaoInfoForLastDept(
			HZShuJuVO[] hzsjvos_dept, Map<String, HZShuJuVO> map_fsrxm_money,
			List<SrxmHVO> list_srxm) {
		if (hzsjvos_dept != null && hzsjvos_dept.length > 0) {
			Map<String, UFDouble[]> map_srxm_jine = new HashMap<String, UFDouble[]>();
			Map<String, SrxmHVO> map_pk_srxm = new HashMap<String, SrxmHVO>();
			for (int i = 0; i < list_srxm.size(); i++) {
				SrxmHVO srxm = list_srxm.get(i);
				String srxm_pk = srxm.getPk_hk_srgk_hg_srxm();
				map_pk_srxm.put(srxm_pk, srxm);
			}
			// list_srxm ѭ��������Ϣ
			for (int i = 0; i < list_srxm.size(); i++) {
				SrxmHVO srxmvo = list_srxm.get(i);
				String srxm_pk = srxmvo.getPk_hk_srgk_hg_srxm();
				for (int j = 0; j < hzsjvos_dept.length; j++) {
					HZShuJuVO hzShuJuVO = hzsjvos_dept[j];
					String srxm = hzShuJuVO.getPk_srxm();
					UFDouble yingshou = getNull_Zero(hzShuJuVO.getYingshou());
					UFDouble zdyh = getNull_Zero(hzShuJuVO.getZdyh());
					UFDouble sgyh = getNull_Zero(hzShuJuVO.getSgyh());
					UFDouble shishou = getNull_Zero(hzShuJuVO.getQrsr());
					UFDouble kblyh = getNull_Zero(hzShuJuVO.getKblyh());
					UFDouble[] ufdouble = new UFDouble[] { yingshou, zdyh,
							sgyh, kblyh, shishou };
					if (srxm_pk.equals(srxm)) {
						if (!map_srxm_jine.containsKey(srxm_pk)) {
							map_srxm_jine.put(srxm_pk, ufdouble);
						} else {
							UFDouble[] ud = map_srxm_jine.get(srxm_pk);
							UFDouble uf0 = ud[0];
							UFDouble uf1 = ud[1];
							UFDouble uf2 = ud[2];
							UFDouble uf3 = ud[3];
							UFDouble uf4 = ud[4];
							ufdouble = new UFDouble[] { ufdouble[0].add(uf0),
									ufdouble[1].add(uf1), ufdouble[2].add(uf2),
									ufdouble[3].add(uf3), ufdouble[4].add(uf4) };
							map_srxm_jine.put(srxm_pk, ufdouble);
						}

						handlerUpSrxmJine(ufdouble, srxm_pk, map_srxm_jine,
								map_pk_srxm);
					}
				}

			}
			List<SrdibiaoBillVO> billvolist = new ArrayList<SrdibiaoBillVO>();
			SrdibiaoBillVO billvo = new SrdibiaoBillVO();
			HZShuJuVO hzShuJuVO = hzsjvos_dept[0];
			String pk_group = hzShuJuVO.getPk_group();
			String org = hzShuJuVO.getPk_org();
			String hzdate = hzShuJuVO.getHzdate().toString();
			SrdibiaoHVO hvo = new SrdibiaoHVO();
			hvo.setPk_group(pk_group);
			hvo.setPk_org(org);
			hvo.setIbillstatus(-1);
			hvo.setDbilldate(hzShuJuVO.getHzdate());
			List<SrdibiaoBVO> list_b = new ArrayList<SrdibiaoBVO>();

			for (int i = 0; i < list_srxm.size(); i++) {
				SrxmHVO srxmvo = list_srxm.get(i);
				String srxm_pk = srxmvo.getPk_hk_srgk_hg_srxm();
				String srxmcode = srxmvo.getCode();
				String srxmname = srxmvo.getName();
				int levelno = srxmvo.getLevelno();
				if (map_srxm_jine.containsKey(srxm_pk)) {
					SrdibiaoBVO bvo = new SrdibiaoBVO();
					bvo.setSrmx_pk(srxm_pk);
					bvo.setSrmx_code(srxmcode);
					for (int j = 0; j < levelno; j++) {
						if (levelno != 1) {
							srxmname = "    " + srxmname;
						}
					}
					bvo.setSrmx_name(srxmname);
					bvo.setYingshou(map_srxm_jine.get(srxm_pk)[0]);
					bvo.setYouhui_zidong(map_srxm_jine.get(srxm_pk)[1]);
					bvo.setYouhui_shougong(map_srxm_jine.get(srxm_pk)[2]);
					bvo.setYouhui_kabili(map_srxm_jine.get(srxm_pk)[3]);
					bvo.setShouru(map_srxm_jine.get(srxm_pk)[4]);
					list_b.add(bvo);
				}
			}
			list_b.add(new SrdibiaoBVO());
			list_b.add(new SrdibiaoBVO());
			SrdibiaoBVO bvo = new SrdibiaoBVO();
			HZShuJuVO hzvo = map_fsrxm_money.get(pk_group + org + hzdate);
			bvo.setYingshou(hzvo.getYingshou());
			bvo.setShouru(hzvo.getQrsr());
			bvo.setYouhui_kabili(hzvo.getKblyh());
			bvo.setYouhui_shougong(hzvo.getSgyh());
			bvo.setYouhui_zidong(hzvo.getZdyh());
			list_b.add(bvo);
			billvo.setParentVO(hvo);
			billvo.setChildrenVO(list_b.toArray(new SrdibiaoBVO[] {}));
			billvolist.add(billvo);
			return billvolist.toArray(new SrdibiaoBillVO[] {});
		}
		return null;
	}

	/**
	 * �ݹ鴦���ϼ�������Ŀ�ϼƽ��
	 * */
	private void handlerUpSrxmJine(UFDouble[] ufdouble, String srxm_id,
			Map<String, UFDouble[]> map_srxm_jine,
			Map<String, SrxmHVO> map_pk_srxm) {
		// ��ȡ�ϼ�������Ŀ��Ϣ
		SrxmHVO srxmh = map_pk_srxm.get(srxm_id);
		String upsrxmpk = null;
		if (srxmh != null) {
			upsrxmpk = srxmh.getPk_parent();
		}
		if (upsrxmpk != null && !"".equals(upsrxmpk) && !"~".equals(upsrxmpk)) {
			if (!map_srxm_jine.containsKey(upsrxmpk)) {
				map_srxm_jine.put(upsrxmpk, ufdouble);
			} else {
				UFDouble[] ud = map_srxm_jine.get(upsrxmpk);
				UFDouble uf0 = ud[0];
				UFDouble uf1 = ud[1];
				UFDouble uf2 = ud[2];
				UFDouble uf3 = ud[3];
				UFDouble uf4 = ud[4];
				ufdouble = new UFDouble[] { ufdouble[0].add(uf0),
						ufdouble[1].add(uf1), ufdouble[2].add(uf2),
						ufdouble[3].add(uf3), ufdouble[4].add(uf4) };
				map_srxm_jine.put(upsrxmpk, ufdouble);
			}
			// ��������ϼ���Ϣ��ô�ݹ��ٽ����ϼ���ֵ
			handlerUpSrxmJine(ufdouble, upsrxmpk, map_srxm_jine, map_pk_srxm);
		}
	}

	/**
	 * �ݹ鴦�����ϼ�������Ŀ
	 * */
	private void handlerUpSrxmJineForDept(UFDouble[] money, String srxm_id,
			String pk_dept, Map<String, UFDouble[]> map_deptsrxm_jine,
			Map<String, SrxmHVO> map_pk_srxm) {

		// ��ȡ�ϼ�������Ŀ��Ϣ
		SrxmHVO srxmh = map_pk_srxm.get(srxm_id);
		String upsrxmpk = null;
		if (srxmh != null) {
			upsrxmpk = srxmh.getPk_parent();
		}
		String dept_upsrxmpk = pk_dept + upsrxmpk;
		if (upsrxmpk != null && !"".equals(upsrxmpk) && !"~".equals(upsrxmpk)) {
			if (!map_deptsrxm_jine.containsKey(dept_upsrxmpk)) {
				map_deptsrxm_jine.put(dept_upsrxmpk, money);
			} else {
				UFDouble[] ud = map_deptsrxm_jine.get(dept_upsrxmpk);
				UFDouble uf0 = ud[0];
				UFDouble uf1 = ud[1];
				UFDouble uf2 = ud[2];
				UFDouble uf3 = ud[3];
				UFDouble uf4 = ud[4];
				money = new UFDouble[] { money[0].add(uf0), money[1].add(uf1),
						money[2].add(uf2), money[3].add(uf3), money[4].add(uf4) };
				map_deptsrxm_jine.put(dept_upsrxmpk, money);
			}
			// ��������ϼ���Ϣ��ô�ݹ��ٽ����ϼ���ֵ
			handlerUpSrxmJineForDept(money, upsrxmpk, pk_dept,
					map_deptsrxm_jine, map_pk_srxm);
		}

	}

	private UFDouble getNull_Zero(UFDouble jine) {
		if (jine == null) {
			return UFDouble.ZERO_DBL;
		} else {
			return jine;
		}
	}

	/**
	 * �����¼����ŷ�װ����ױ�ۺ�VO
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SrdibiaoBillVO[] getSrdibiaoInfo(HZShuJuVO[] hzsjvos_fdept,
			HZShuJuVO[] hzsjvos_dept, Map<String, String[]> code_def_map,
			Map<String, HZShuJuVO> map_fsrxm_money, List<SrxmHVO> list_srxm) {
		// �������VO������VO����Ϊ�� ��˵�����ϼ����ŵĻ���
		Map<String, List<SrdibiaoBVO>> map = new HashMap<String, List<SrdibiaoBVO>>();
		if (hzsjvos_fdept != null && hzsjvos_fdept.length > 0
				&& hzsjvos_dept != null && hzsjvos_dept.length > 0) {
			Map<String, SrxmHVO> map_pk_srxm = new HashMap<String, SrxmHVO>();
			for (int i = 0; i < list_srxm.size(); i++) {
				SrxmHVO srxm = list_srxm.get(i);
				String srxm_pk = srxm.getPk_hk_srgk_hg_srxm();
				map_pk_srxm.put(srxm_pk, srxm);
			}
			Set<String> dept_def_set = code_def_map.keySet();
			List<SrdibiaoBillVO> billvolist = new ArrayList<SrdibiaoBillVO>();
			if (hzsjvos_fdept != null && hzsjvos_fdept.length > 0) {
				Map<String, UFDouble[]> map_srxm_jine = new HashMap<String, UFDouble[]>();

				// list_srxm ѭ��������Ϣ
				for (int i = 0; i < list_srxm.size(); i++) {
					SrxmHVO srxmvo = list_srxm.get(i);
					String srxm_pk = srxmvo.getPk_hk_srgk_hg_srxm();
					for (int j = 0; j < hzsjvos_fdept.length; j++) {
						HZShuJuVO hzShuJuVO = hzsjvos_fdept[j];
						String srxm = hzShuJuVO.getPk_srxm();
						UFDouble yingshou = getNull_Zero(hzShuJuVO
								.getYingshou());
						UFDouble zdyh = getNull_Zero(hzShuJuVO.getZdyh());
						UFDouble sgyh = getNull_Zero(hzShuJuVO.getSgyh());
						UFDouble shishou = getNull_Zero(hzShuJuVO.getQrsr());
						UFDouble kblyh = getNull_Zero(hzShuJuVO.getKblyh());
						UFDouble[] ufdouble = new UFDouble[] { yingshou, zdyh,
								sgyh, kblyh, shishou };
						if (srxm_pk.equals(srxm)) {
							if (!map_srxm_jine.containsKey(srxm_pk)) {
								map_srxm_jine.put(srxm_pk, ufdouble);
							} else {
								UFDouble[] ud = map_srxm_jine.get(srxm_pk);
								UFDouble uf0 = ud[0];
								UFDouble uf1 = ud[1];
								UFDouble uf2 = ud[2];
								UFDouble uf3 = ud[3];
								UFDouble uf4 = ud[4];
								ufdouble = new UFDouble[] {
										ufdouble[0].add(uf0),
										ufdouble[1].add(uf1),
										ufdouble[2].add(uf2),
										ufdouble[3].add(uf3),
										ufdouble[4].add(uf4) };
								map_srxm_jine.put(srxm_pk, ufdouble);
							}

							handlerUpSrxmJine(ufdouble, srxm_pk, map_srxm_jine,
									map_pk_srxm);
						}
					}

				}
				SrdibiaoBillVO billvo = new SrdibiaoBillVO();
				HZShuJuVO hzShuJuVO = hzsjvos_dept[0];
				String pk_group = hzShuJuVO.getPk_group();
				String org = hzShuJuVO.getPk_org();
				String hzdate = hzShuJuVO.getHzdate().toString();
				SrdibiaoHVO hvo = new SrdibiaoHVO();
				hvo.setPk_group(pk_group);
				hvo.setPk_org(org);
				hvo.setIbillstatus(-1);
				hvo.setDbilldate(hzShuJuVO.getHzdate());
				List<SrdibiaoBVO> list_b = new ArrayList<SrdibiaoBVO>();

				for (int i = 0; i < list_srxm.size(); i++) {
					SrxmHVO srxmvo = list_srxm.get(i);
					String srxm_pk = srxmvo.getPk_hk_srgk_hg_srxm();
					String srxmcode = srxmvo.getCode();
					String srxmname = srxmvo.getName();
					int levelno = srxmvo.getLevelno();
					if (map_srxm_jine.containsKey(srxm_pk)) {
						SrdibiaoBVO bvo = new SrdibiaoBVO();
						bvo.setSrmx_pk(srxm_pk);
						bvo.setSrmx_code(srxmcode);
						for (int j = 0; j < levelno; j++) {
							if (levelno != 1) {
								srxmname = "    " + srxmname;
							}
						}
						bvo.setSrmx_name(srxmname);
						bvo.setYingshou(map_srxm_jine.get(srxm_pk)[0]);
						bvo.setYouhui_zidong(map_srxm_jine.get(srxm_pk)[1]);
						bvo.setYouhui_shougong(map_srxm_jine.get(srxm_pk)[2]);
						bvo.setYouhui_kabili(map_srxm_jine.get(srxm_pk)[3]);
						bvo.setShouru(map_srxm_jine.get(srxm_pk)[4]);
						list_b.add(bvo);
					}
				}
				billvo.setParentVO(hvo);
				billvo.setChildrenVO(list_b.toArray(new SrdibiaoBVO[] {}));
				map.put(hzdate, list_b);
				billvolist.add(billvo);
			}

			// �ϼ����Ż��������Ѿ���װ��ɣ�֮����ж��¼��������ݵĴ���
			Map<String, UFDouble[]> map_deptsrxm_jine = new HashMap<String, UFDouble[]>();
			for (int i = 0; i < hzsjvos_dept.length; i++) {
				HZShuJuVO hzShuJuVO = hzsjvos_dept[i];
				String pk_dept = hzShuJuVO.getPk_dept();
				String pk_srxm = hzShuJuVO.getPk_srxm();
				UFDouble yingshou = getNull_Zero(hzShuJuVO.getYingshou());
				UFDouble zdyh = getNull_Zero(hzShuJuVO.getZdyh());
				UFDouble sgyh = getNull_Zero(hzShuJuVO.getSgyh());
				UFDouble kblyh = getNull_Zero(hzShuJuVO.getKblyh());
				UFDouble shishou = getNull_Zero(hzShuJuVO.getQrsr());
				UFDouble[] money = new UFDouble[] { yingshou, zdyh, sgyh,
						kblyh, shishou };
				if (!map_deptsrxm_jine.containsKey(pk_dept + pk_srxm)) {
					map_deptsrxm_jine.put(pk_dept + pk_srxm, money);
				} else {
					UFDouble[] ud = map_deptsrxm_jine.get(pk_dept + pk_srxm);
					UFDouble uf0 = ud[0];
					UFDouble uf1 = ud[1];
					UFDouble uf2 = ud[2];
					UFDouble uf3 = ud[3];
					UFDouble uf4 = ud[4];
					money = new UFDouble[] { money[0].add(uf0),
							money[1].add(uf1), money[2].add(uf2),
							money[3].add(uf3), money[4].add(uf4) };
					map_deptsrxm_jine.put(pk_dept + pk_srxm, money);
				}
				handlerUpSrxmJineForDept(money, pk_srxm, pk_dept,
						map_deptsrxm_jine, map_pk_srxm);
			}
			// ���Ĵ��� �ۺ�VO
			Set set = map.keySet();
			Iterator<String> it = set.iterator();
			List<SrdibiaoBillVO> list_aggvo = new ArrayList<SrdibiaoBillVO>();
			while (it.hasNext()) {
				String dateinfo = it.next();
				SrdibiaoBillVO aggvo = new SrdibiaoBillVO();
				SrdibiaoHVO hvo = new SrdibiaoHVO();
				hvo.setPk_org(hzsjvos_dept[0].getPk_org());
				hvo.setDbilldate(new UFDate(dateinfo));
				hvo.setIbillstatus(-1);
				hvo.setPk_group(hzsjvos_dept[0].getPk_group());
				// �˴����ö�̬�в�����Ϣ VBDEF=DEPTNAME
				setSrdbHVdef_DeptInfo(code_def_map, hvo);
				aggvo.setParent(hvo);
				List<SrdibiaoBVO> list = map.get(dateinfo);
				for (int i = 0; i < list.size(); i++) {
					SrdibiaoBVO srdibiaobvo = list.get(i);
					String srxmpk = srdibiaobvo.getSrmx_pk();
					Iterator<String> it1 = dept_def_set.iterator();
					while (it1.hasNext()) {
						String pk_dept = it1.next();
						UFDouble[] deptud = map_deptsrxm_jine.get(pk_dept
								+ srxmpk);
						if (deptud != null) {
							UFDouble qrsr = deptud[4];
							srdibiaobvo.setAttributeValue(
									code_def_map.get(pk_dept)[0], qrsr);
						} else {
							srdibiaobvo.setAttributeValue(
									code_def_map.get(pk_dept)[0],
									UFDouble.ZERO_DBL);
						}

					}

				}
				list.add(new SrdibiaoBVO());
				list.add(new SrdibiaoBVO());
				SrdibiaoBVO bvo = new SrdibiaoBVO();
				HZShuJuVO hzvo = map_fsrxm_money.get(hzsjvos_dept[0]
						.getPk_group()
						+ hzsjvos_dept[0].getPk_org()
						+ hzsjvos_dept[0].getHzdate());
				bvo.setYingshou(hzvo.getYingshou());
				bvo.setShouru(hzvo.getQrsr());
				bvo.setYouhui_kabili(hzvo.getKblyh());
				bvo.setYouhui_shougong(hzvo.getSgyh());
				bvo.setYouhui_zidong(hzvo.getZdyh());
				Iterator<String> it1 = dept_def_set.iterator();
				while (it1.hasNext()) {
					String dept_key = it1.next();
					String vdef = code_def_map.get(dept_key)[0];
					HZShuJuVO hzvo1 = map_fsrxm_money.get(hzsjvos_dept[0]
							.getPk_group()
							+ hzsjvos_dept[0].getPk_org()
							+ dept_key + hzsjvos_dept[0].getHzdate());
					if (hzvo1 != null) {
						bvo.setAttributeValue(vdef, hzvo1.getQrsr());
					} else {
						bvo.setAttributeValue(vdef, UFDouble.ZERO_DBL);
					}
				}
				list.add(bvo);
				aggvo.setChildrenVO(list.toArray(new SrdibiaoBVO[] {}));
				list_aggvo.add(aggvo);
			}
			return list_aggvo.toArray(new SrdibiaoBillVO[] {});
		}
		return null;
	}

	/**
	 * ���ð����ϼ����Ų�ѯʱ���ͷ�Զ���1��Ŷ�Ӧ��ϵ
	 * */
	private void setSrdbHVdef_DeptInfo(Map<String, String[]> map,
			SrdibiaoHVO hvo) {
		if (map != null && map.size() > 0) {
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				String key = it.next();
				String[] vdefdeptinfo = map.get(key);
				String vdef = vdefdeptinfo[0];
				String deptname = vdefdeptinfo[1];
				String deptcode = vdefdeptinfo[3];
				sb.append(vdef + "=" + key + "��" + deptcode + "��" + deptname
						+ "" + ",");
			}
			hvo.setVdef01(sb.toString());
		}
	}

	@SuppressWarnings("unchecked")
	private HZShuJuVO[] getHZShuJuInfo(String first_org, String pk_org,
			String pk_dept, String begindate, String enddate,
			SgsjInfoVO[] sgsgvos, RsbaogaoCVO_YY[] rstz, CctzVO_YY[] cctz)
			throws DAOException {
		StringBuffer sb = new StringBuffer();
		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append("select hzsj.*,srxm.code srxm_code,srxm.name srxm_name,srxm.pk_parent srxm_fpk,srxm.levelno srxm_levelno,fsrxm.name srxm_fname,fsrxm.code srxm_fcode,fsrxm.levelno srxm_flevelno,");
			sb.append(" dept.code dept_code,dept.name dept_name  from (");
			sb.append("select pk_group,'"
					+ first_org// �����޸ģ���������͸���PK��ͬ����Ѹ�������Ϊ��
					+ "' as pk_org,pk_dept,decode(pk_fdept,pk_dept,'',pk_fdept) pk_fdept,pk_srxm,'"
					+ begindate.substring(0, 10)
					+ " 00:00:00' hzdate,sum(yingshou) yingshou, sum(zdyh) zdyh,sum(sgyh) sgyh, sum(shishou) shishou, sum(kblyh) kblyh,sum(qrsr) qrsr, ");
			sb.append(" sum(cika) cika,sum(daijinquan) daijinquan,sum(miandan) miandan,sum(fenqu) fenqu,sum(huiyuanka) huiyuanka,sum(pos) pos,sum(xianjin) xianjin,sum(qtyh) qtyh,sum(huiyuankashiji) huiyuankashiji, ");
			sb.append(" sum(zhipiao) zhipiao, ");	// ��� 2016��5��31��23:09:17
			sb.append("sum(youhui_sg_01) youhui_sg_01,sum(youhui_sg_02) youhui_sg_02,");
			sb.append("sum(youhui_sg_03) youhui_sg_03,sum(youhui_sg_04) youhui_sg_04,");
			sb.append("sum(youhui_sg_05) youhui_sg_05,sum(youhui_sg_06) youhui_sg_06,");
			sb.append("sum(youhui_sg_07) youhui_sg_07,sum(youhui_sg_08) youhui_sg_08,");
			sb.append("sum(youhui_sg_09) youhui_sg_09,sum(youhui_sg_10) youhui_sg_10 ");
			sb.append(" from hk_srgk_hg_hzshuju where 1=1 and nvl(dr,0)=0 "
					+ " and pk_org in("
					+ pk_org
					+ ") and to_date(hzdate,'yyyy-mm-dd hh24:mi:ss')>= to_date('"
					+ begindate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ");
			sb.append(" and to_date(hzdate,'yyyy-mm-dd hh24:mi:ss')<= to_date('"
					+ enddate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ");
			sb.append(" and ( pk_dept in (select dept.pk_dept from org_dept dept where  dept.def1 is not null and dept.def1<>'~' connect by dept.pk_fatherorg = prior dept.pk_dept start with dept.pk_dept in ('"
					+ pk_dept + "')) or pk_dept in ('" + pk_dept + "'))");
			sb.append(" group by pk_group,pk_dept,pk_fdept,pk_srxm");
			sb.append(") hzsj  left join hk_srgk_hg_srxm srxm on srxm.pk_hk_srgk_hg_srxm=hzsj.pk_srxm ");
			sb.append(" left join hk_srgk_hg_srxm fsrxm on fsrxm.pk_hk_srgk_hg_srxm=srxm.pk_parent ");
			sb.append(" left join org_dept dept on dept.pk_dept=hzsj.pk_dept order by srxm.code");
		} else {
			sb.append("select hzsj.*,srxm.code srxm_code,srxm.name srxm_name,srxm.pk_parent srxm_fpk,srxm.levelno srxm_levelno,fsrxm.name srxm_fname,fsrxm.code srxm_fcode,fsrxm.levelno srxm_flevelno from (");
			sb.append(" select pk_group,'"
					+ first_org
					+ "' as pk_org,pk_srxm,'"
					+ begindate.substring(0, 10)
					+ " 00:00:00' hzdate,sum(yingshou) yingshou, sum(zdyh) zdyh,sum(sgyh) sgyh, sum(shishou) shishou, sum(kblyh) kblyh,sum(qrsr) qrsr, ");
			sb.append(" sum(cika) cika,sum(daijinquan) daijinquan,sum(miandan) miandan,sum(fenqu) fenqu,sum(huiyuanka) huiyuanka,sum(pos) pos,sum(xianjin) xianjin,sum(qtyh) qtyh,sum(huiyuankashiji) huiyuankashiji,   ");
			sb.append(" sum(zhipiao) zhipiao, ");	// ��� 2016��5��31��23:09:17
			sb.append("sum(youhui_sg_01) youhui_sg_01,sum(youhui_sg_02) youhui_sg_02,");
			sb.append("sum(youhui_sg_03) youhui_sg_03,sum(youhui_sg_04) youhui_sg_04,");
			sb.append("sum(youhui_sg_05) youhui_sg_05,sum(youhui_sg_06) youhui_sg_06,");
			sb.append("sum(youhui_sg_07) youhui_sg_07,sum(youhui_sg_08) youhui_sg_08,");
			sb.append("sum(youhui_sg_09) youhui_sg_09,sum(youhui_sg_10) youhui_sg_10 ");
			sb.append(" from hk_srgk_hg_hzshuju where 1=1 and nvl(dr,0)=0 "
					+ " and pk_org in("
					+ pk_org
					+ ") and to_date(hzdate,'yyyy-mm-dd hh24:mi:ss')>= to_date('"
					+ begindate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ");
			sb.append(" and to_date(hzdate,'yyyy-mm-dd hh24:mi:ss')<= to_date('"
					+ enddate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ");
			sb.append(" group by pk_group,pk_srxm");
			sb.append(") hzsj left join hk_srgk_hg_srxm srxm on srxm.pk_hk_srgk_hg_srxm=hzsj.pk_srxm");
			sb.append(" left join hk_srgk_hg_srxm fsrxm on fsrxm.pk_hk_srgk_hg_srxm=srxm.pk_parent order by srxm.code");
		}
		List<HZShuJuVO> list = (List<HZShuJuVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(HZShuJuVO.class));
		// �˴������ֹ�����������������Ŀ
		handlerAddSrxm(list, sgsgvos, cctz, rstz);
		if (list != null && list.size() > 0) {
			// ѭ���ж��ֹ��������Ƿ��ж�Ӧ��������Ŀ������Ϣ,���д��Ż���
			for (int i = 0; i < list.size(); i++) {
				HZShuJuVO hzsjvo = list.get(i);
				String hzsj_group = hzsjvo.getPk_group();
				String hzsj_org = hzsjvo.getPk_org();
				String hzsj_dept = hzsjvo.getPk_dept() == null ? "" : hzsjvo
						.getPk_dept();
				String hzsj_hzdate = hzsjvo.getHzdate().toString();
				String hzsj_srxm = hzsjvo.getPk_srxm();
				for (int j = 0; j < sgsgvos.length; j++) {
					SgsjInfoVO sgsjvo = sgsgvos[j];
					String sgsj_group = sgsjvo.getPk_group();
					String sgsj_org = sgsjvo.getPk_org();
					String sgsj_dept = sgsjvo.getPk_dept() == null ? ""
							: sgsjvo.getPk_dept();
					String sgsj_hzdate = sgsjvo.getHzdate().substring(0, 10)
							+ " 00:00:00";
					String sgsj_tzsrxm1 = sgsjvo.getTz_km_srxm_1();
					String sgsj_tzdate1 = sgsjvo.getTz_km_data_1();
					String sgsj_tzsrxm2 = sgsjvo.getTz_km_srxm_2();
					String sgsj_tzdate2 = sgsjvo.getTz_km_data_2();
					String sgsj_type1 = sgsjvo.getTz_km_srxm_type1();
					String sgsj_type2 = sgsjvo.getTz_km_srxm_type2();
					// UFDouble hzsj_yingshou = hzsjvo.getYingshou();
					if (sgsj_tzsrxm1 != null && sgsj_tzsrxm1.equals(hzsj_srxm)) {
						// δ���ݲ��Ų�ѯ
						if (pk_dept == null || "".equals(pk_dept)) {
							if (sgsj_group.equals(hzsj_group)
									&& sgsj_org.equals(hzsj_org)
									&& sgsj_hzdate.equals(hzsj_hzdate)) {
								if ("xse".equals(sgsj_type1)) {
									hzsjvo.setYingshou(hzsjvo.getYingshou()
											.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								} else if ("zdyh".equals(sgsj_type1)) {
									hzsjvo.setZdyh(hzsjvo.getZdyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								} else if ("sgyh".equals(sgsj_type1)) {
									hzsjvo.setSgyh(hzsjvo.getSgyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								} else if ("kblyh".equals(sgsj_type1)) {
									hzsjvo.setKblyh(hzsjvo.getKblyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								} else if ("sr".equals(sgsj_type1)) {
									hzsjvo.setQrsr(
											PuPubVO.getUFDouble_NullAsZero(hzsjvo.getQrsr()).add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								}

							}
						} else {
							if (sgsj_group.equals(hzsj_group)
									&& sgsj_org.equals(hzsj_org)
									&& sgsj_hzdate.equals(hzsj_hzdate)
									&& sgsj_dept.equals(hzsj_dept)) {
								if ("xse".equals(sgsj_type1)) {
									hzsjvo.setYingshou(hzsjvo.getYingshou()
											.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								} else if ("zdyh".equals(sgsj_type1)) {
									hzsjvo.setZdyh(hzsjvo.getZdyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								} else if ("sgyh".equals(sgsj_type1)) {
									hzsjvo.setSgyh(hzsjvo.getSgyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								} else if ("kblyh".equals(sgsj_type1)) {
									hzsjvo.setKblyh(hzsjvo.getKblyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								} else if ("sr".equals(sgsj_type1)) {
									hzsjvo.setQrsr(hzsjvo.getQrsr().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
								}
							}
						}
					}
					if (sgsj_tzsrxm2 != null && sgsj_tzsrxm2.equals(hzsj_srxm)) {
						// δ���ݲ��Ų�ѯ
						if (pk_dept == null || "".equals(pk_dept)) {
							if (sgsj_group.equals(hzsj_group)
									&& sgsj_org.equals(hzsj_org)
									&& sgsj_hzdate.equals(hzsj_hzdate)) {
								if ("xse".equals(sgsj_type2)) {
									hzsjvo.setYingshou(hzsjvo.getYingshou()
											.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								} else if ("zdyh".equals(sgsj_type2)) {
									hzsjvo.setZdyh(hzsjvo.getZdyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								} else if ("sgyh".equals(sgsj_type2)) {
									hzsjvo.setSgyh(hzsjvo.getSgyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								} else if ("kblyh".equals(sgsj_type2)) {
									hzsjvo.setKblyh(hzsjvo.getKblyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								} else if ("sr".equals(sgsj_type2)) {
									hzsjvo.setQrsr(hzsjvo.getQrsr().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								}
							}
						} else {
							if (sgsj_group.equals(hzsj_group)
									&& sgsj_org.equals(hzsj_org)
									&& sgsj_hzdate.equals(hzsj_hzdate)
									&& sgsj_dept.equals(hzsj_dept)) {
								if ("xse".equals(sgsj_type2)) {
									hzsjvo.setYingshou(hzsjvo.getYingshou()
											.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								} else if ("zdyh".equals(sgsj_type2)) {
									hzsjvo.setZdyh(hzsjvo.getZdyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								} else if ("sgyh".equals(sgsj_type2)) {
									hzsjvo.setSgyh(hzsjvo.getSgyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								} else if ("kblyh".equals(sgsj_type2)) {
									hzsjvo.setKblyh(hzsjvo.getKblyh().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								} else if ("sr".equals(sgsj_type2)) {
									hzsjvo.setQrsr(hzsjvo.getQrsr().add(
											PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
								}
							}
						}
					}
				}
				// �˴��������󱨸������Ϣ�����Ϊ�ղ����������Ϊ������ױ�Ĳ�ѯ
				if (rstz != null && rstz.length > 0) {
					for (int j = 0; j < rstz.length; j++) {

						RsbaogaoCVO_YY sgsjvo = rstz[j];
						String sgsj_group = sgsjvo.getPk_group();
						String sgsj_org = sgsjvo.getPk_org();
						String sgsj_dept = sgsjvo.getBm_pk() == null ? ""
								: sgsjvo.getBm_pk();
						String sgsj_hzdate = sgsjvo.getDbilldate().substring(0,
								10)
								+ " 00:00:00";
						String sgsj_tzsrxm1 = sgsjvo.getTz_km_srxm_1();
						UFDouble sgsj_tzdate1 = sgsjvo.getTz_km_data_1();
						String sgsj_tzsrxm2 = sgsjvo.getTz_km_srxm_2();
						UFDouble sgsj_tzdate2 = sgsjvo.getTz_km_data_2();
						String sgsj_type1 = sgsjvo.getTz_km_srxm_type1();
						String sgsj_type2 = sgsjvo.getTz_km_srxm_type2();

						if (sgsj_tzsrxm1 != null
								&& sgsj_tzsrxm1.equals(hzsj_srxm)) {
							// δ���ݲ��Ų�ѯ
							if (pk_dept == null || "".equals(pk_dept)) {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)) {
									if ("xse".equals(sgsj_type1)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("zdyh".equals(sgsj_type1)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sgyh".equals(sgsj_type1)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("kblyh".equals(sgsj_type1)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sr".equals(sgsj_type1)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									}
								}
							} else {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)
										&& sgsj_dept.equals(hzsj_dept)) {
									if ("xse".equals(sgsj_type1)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("zdyh".equals(sgsj_type1)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sgyh".equals(sgsj_type1)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("kblyh".equals(sgsj_type1)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sr".equals(sgsj_type1)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									}
								}
							}
						}
						if (sgsj_tzsrxm2 != null
								&& sgsj_tzsrxm2.equals(hzsj_srxm)) {
							// δ���ݲ��Ų�ѯ
							if (pk_dept == null || "".equals(pk_dept)) {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)) {
									if ("xse".equals(sgsj_type2)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("zdyh".equals(sgsj_type2)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sgyh".equals(sgsj_type2)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("kblyh".equals(sgsj_type2)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sr".equals(sgsj_type2)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									}
								}
							} else {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)
										&& sgsj_dept.equals(hzsj_dept)) {
									if ("xse".equals(sgsj_type2)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("zdyh".equals(sgsj_type2)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sgyh".equals(sgsj_type2)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("kblyh".equals(sgsj_type2)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sr".equals(sgsj_type2)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									}
								}
							}
						}
					}
				}

				// �˴������������Ϣ�����Ϊ�ղ����������Ϊ������ױ�Ĳ�ѯ
				if (cctz != null && cctz.length > 0) {
					for (int j = 0; j < cctz.length; j++) {

						CctzVO_YY sgsjvo = cctz[j];
						String sgsj_group = sgsjvo.getPk_group();
						String sgsj_org = sgsjvo.getPk_org();
						String sgsj_dept = sgsjvo.getBm_pk() == null ? ""
								: sgsjvo.getBm_pk();
						String sgsj_hzdate = sgsjvo.getTz_date().toString()
								.substring(0, 10)
								+ " 00:00:00";
						String sgsj_tzsrxm1 = sgsjvo.getTz_km_srxm_1();
						UFDouble sgsj_tzdate1 = sgsjvo.getTz_km_data_1();
						String sgsj_tzsrxm2 = sgsjvo.getTz_km_srxm_2();
						UFDouble sgsj_tzdate2 = sgsjvo.getTz_km_data_2();
						String sgsj_type1 = sgsjvo.getTz_km_srxm_type1();
						String sgsj_type2 = sgsjvo.getTz_km_srxm_type2();

						if (sgsj_tzsrxm1 != null
								&& sgsj_tzsrxm1.equals(hzsj_srxm)) {
							// δ���ݲ��Ų�ѯ
							if (pk_dept == null || "".equals(pk_dept)) {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)) {
									if ("xse".equals(sgsj_type1)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("zdyh".equals(sgsj_type1)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sgyh".equals(sgsj_type1)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("kblyh".equals(sgsj_type1)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sr".equals(sgsj_type1)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									}
								}
							} else {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)
										&& sgsj_dept.equals(hzsj_dept)) {
									if ("xse".equals(sgsj_type1)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("zdyh".equals(sgsj_type1)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sgyh".equals(sgsj_type1)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("kblyh".equals(sgsj_type1)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									} else if ("sr".equals(sgsj_type1)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
									}
								}
							}
						}
						if (sgsj_tzsrxm2 != null
								&& sgsj_tzsrxm2.equals(hzsj_srxm)) {
							// δ���ݲ��Ų�ѯ
							if (pk_dept == null || "".equals(pk_dept)) {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)) {
									if ("xse".equals(sgsj_type2)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("zdyh".equals(sgsj_type2)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sgyh".equals(sgsj_type2)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("kblyh".equals(sgsj_type2)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sr".equals(sgsj_type2)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									}
								}
							} else {
								if (sgsj_group.equals(hzsj_group)
										&& sgsj_org.equals(hzsj_org)
										&& sgsj_hzdate.equals(hzsj_hzdate)
										&& sgsj_dept.equals(hzsj_dept)) {
									if ("xse".equals(sgsj_type2)) {
										hzsjvo.setYingshou(hzsjvo
												.getYingshou()
												.add(PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("zdyh".equals(sgsj_type2)) {
										hzsjvo.setZdyh(hzsjvo.getZdyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sgyh".equals(sgsj_type2)) {
										hzsjvo.setSgyh(hzsjvo.getSgyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("kblyh".equals(sgsj_type2)) {
										hzsjvo.setKblyh(hzsjvo.getKblyh().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									} else if ("sr".equals(sgsj_type2)) {
										hzsjvo.setQrsr(hzsjvo.getQrsr().add(
												PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
									}
								}
							}
						}
					}
				}
			}

			return list.toArray(new HZShuJuVO[] {});
		}
		return null;
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
	 * ��ȡ�ϲ�������Ŀ���ܽ��
	 * 
	 * @throws DAOException
	 * */
	private Map<String, HZShuJuVO> getUpSrxmMoney(String pk_org,
			String pk_dept, String begindate, String enddate,
			HZShuJuVO[] hzsjvos) throws DAOException {
		Map<String, HZShuJuVO> map = new HashMap<String, HZShuJuVO>();

		if (hzsjvos != null && hzsjvos.length > 0)
			for (int i = 0; i < hzsjvos.length; i++) {
				HZShuJuVO vo = hzsjvos[i];
				vo.setYingshou(vo.getYingshou() == null ? UFDouble.ZERO_DBL
						: vo.getYingshou());
				vo.setSgyh(vo.getSgyh() == null ? UFDouble.ZERO_DBL : vo
						.getSgyh());
				vo.setKblyh(vo.getKblyh() == null ? UFDouble.ZERO_DBL : vo
						.getKblyh());
				vo.setZdyh(vo.getZdyh() == null ? UFDouble.ZERO_DBL : vo
						.getZdyh());
				vo.setQrsr(vo.getQrsr() == null ? UFDouble.ZERO_DBL : vo
						.getQrsr());
				// �ϲ���Ŀ�Ļ��ܽ��
				HZShuJuVO vo2 = new HZShuJuVO();
				vo2.setYingshou(vo.getYingshou());
				vo2.setSgyh(vo.getSgyh());
				vo2.setKblyh(vo.getKblyh());
				vo2.setZdyh(vo.getZdyh());
				vo2.setQrsr(vo.getQrsr());
				// ������Ŀ�Ļ��ܽ��
				HZShuJuVO vo3 = new HZShuJuVO();
				vo3.setYingshou(vo.getYingshou());
				vo3.setSgyh(vo.getSgyh());
				vo3.setKblyh(vo.getKblyh());
				vo3.setZdyh(vo.getZdyh());
				vo3.setQrsr(vo.getQrsr());

				// �Ӳ��ŵĻ��ܽ��
				HZShuJuVO vo4 = new HZShuJuVO();
				vo4.setYingshou(vo.getYingshou());
				vo4.setSgyh(vo.getSgyh());
				vo4.setKblyh(vo.getKblyh());
				vo4.setZdyh(vo.getZdyh());
				vo4.setQrsr(vo.getQrsr());

				// �Ӳ��ŵĻ��ܽ��
				HZShuJuVO vo5 = new HZShuJuVO();
				vo5.setYingshou(vo.getYingshou());
				vo5.setSgyh(vo.getSgyh());
				vo5.setKblyh(vo.getKblyh());
				vo5.setZdyh(vo.getZdyh());
				vo5.setQrsr(vo.getQrsr());

				// �Ӳ��ŵĻ��ܽ��
				HZShuJuVO vo6 = new HZShuJuVO();
				vo6.setYingshou(vo.getYingshou());
				vo6.setSgyh(vo.getSgyh());
				vo6.setKblyh(vo.getKblyh());
				vo6.setZdyh(vo.getZdyh());
				vo6.setQrsr(vo.getQrsr());

				String group = vo.getPk_group();
				String org = vo.getPk_org();
				String dept = vo.getPk_dept();
				String fdept = vo.getPk_fdept();
				String srxm_fpk = vo.getSrxm_fpk();
				String hzdate = vo.getHzdate().toString();

				String key_fdept = group + org + fdept + srxm_fpk + hzdate;
				String key_hj = group + org + hzdate;
				String key_dept_hj = group + org + dept + hzdate;
				String key_fdept_dept = group + org + fdept + dept + srxm_fpk
						+ hzdate;

				String key_org_fsrxm = group + org + srxm_fpk + hzdate;

				if (pk_dept != null && !"".equals(pk_dept)) {
					if (!map.containsKey(key_fdept)) {
						map.put(key_fdept, vo2);
					} else {
						HZShuJuVO hvo = map.get(key_fdept);
						hvo.setYingshou(hvo.getYingshou()
								.add(vo2.getYingshou()));
						hvo.setSgyh(hvo.getSgyh().add(vo2.getSgyh()));
						hvo.setKblyh(hvo.getKblyh().add(vo2.getKblyh()));
						hvo.setZdyh(hvo.getZdyh().add(vo2.getZdyh()));
						hvo.setQrsr(hvo.getQrsr().add(vo2.getQrsr()));
						map.put(key_fdept, hvo);
					}
					if (!map.containsKey(key_fdept_dept)) {
						map.put(key_fdept_dept, vo6);
					} else {
						HZShuJuVO hvo = map.get(key_fdept_dept);
						hvo.setYingshou(hvo.getYingshou()
								.add(vo6.getYingshou()));
						hvo.setSgyh(hvo.getSgyh().add(vo6.getSgyh()));
						hvo.setKblyh(hvo.getKblyh().add(vo6.getKblyh()));
						hvo.setZdyh(hvo.getZdyh().add(vo6.getZdyh()));
						hvo.setQrsr(hvo.getQrsr().add(vo6.getQrsr()));
						map.put(key_fdept_dept, hvo);
					}
					if (!map.containsKey(key_dept_hj)) {
						map.put(key_dept_hj, vo4);
					} else {
						HZShuJuVO hvo = map.get(key_dept_hj);
						hvo.setYingshou(hvo.getYingshou()
								.add(vo4.getYingshou()));
						hvo.setSgyh(hvo.getSgyh().add(vo4.getSgyh()));
						hvo.setKblyh(hvo.getKblyh().add(vo4.getKblyh()));
						hvo.setZdyh(hvo.getZdyh().add(vo4.getZdyh()));
						hvo.setQrsr(hvo.getQrsr().add(vo4.getQrsr()));
						map.put(key_dept_hj, hvo);
					}
					if (!map.containsKey(key_hj)) {
						map.put(key_hj, vo3);
					} else {
						HZShuJuVO hvo = map.get(key_hj);
						hvo.setYingshou(hvo.getYingshou()
								.add(vo3.getYingshou()));
						hvo.setSgyh(hvo.getSgyh().add(vo3.getSgyh()));
						hvo.setKblyh(hvo.getKblyh().add(vo3.getKblyh()));
						hvo.setZdyh(hvo.getZdyh().add(vo3.getZdyh()));
						hvo.setQrsr(hvo.getQrsr().add(vo3.getQrsr()));
						map.put(key_hj, hvo);
					}
					// ��֪��Ϊʲôע�͵�������û��ʹ�ø�keyֵ
					// map.put(group + org + dept + srxm_fpk + hzdate, vo);
				} else {
					if (!map.containsKey(key_org_fsrxm)) {
						map.put(key_org_fsrxm, vo5);
					} else {
						HZShuJuVO hvo = map.get(key_org_fsrxm);
						hvo.setYingshou(hvo.getYingshou()
								.add(vo5.getYingshou()));
						hvo.setSgyh(hvo.getSgyh().add(vo5.getSgyh()));
						hvo.setKblyh(hvo.getKblyh().add(vo5.getKblyh()));
						hvo.setZdyh(hvo.getZdyh().add(vo5.getZdyh()));
						hvo.setQrsr(hvo.getQrsr().add(vo5.getQrsr()));
						map.put(key_org_fsrxm, hvo);
					}
					if (!map.containsKey(key_hj)) {
						map.put(key_hj, vo3);
					} else {
						HZShuJuVO hvo = map.get(key_hj);
						hvo.setYingshou(hvo.getYingshou()
								.add(vo3.getYingshou()));
						hvo.setSgyh(hvo.getSgyh().add(vo3.getSgyh()));
						hvo.setKblyh(hvo.getKblyh().add(vo3.getKblyh()));
						hvo.setZdyh(hvo.getZdyh().add(vo3.getZdyh()));
						hvo.setQrsr(hvo.getQrsr().add(vo3.getQrsr()));
						map.put(key_hj, hvo);
					}
				}

			}

		return map;
	}

	/**
	 * ������֯+����+���Ų�ѯ �ֹ�������Ϣ
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private SgsjInfoVO[] getSgsjInfoVOs(String first_org, String pk_org,
			String pk_dept, String begindate, String enddate)
			throws DAOException {
		StringBuffer sb = new StringBuffer();
		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append("select sgsj.pk_group,'"
					+ first_org
					+ "' as pk_org,'"
					+ begindate.substring(0, 10)
					+ " 00:00:00' hzdate,sgsjb.bm_pk pk_dept,dept.pk_fatherorg dept_fpk,sgsjb.tz_km_jzfs_1,sgsjb.tz_km_srxm_type1,sgsjb.tz_km_srxm_type2,");
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
			sb.append(" and nvl(replace(sgsj.vdef10,'~',''),'N')='N' ");	// ��ݲ�ȡ �Ƿ�Ƶ�=��  ������ ��HK 2018��11��6��21:05:30��
			sb.append("group by sgsj.pk_group,sgsjb.bm_pk,sgsjb.tz_km_jzfs_1,sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sgsjb.tz_km_jzfs_2,sgsjb.tz_km_srxm_type1,sgsjb.tz_km_srxm_type2,");
			sb.append("sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,srxm1.pk_parent,srxm2.pk_parent,jzfs1.pk_parent,jzfs2.pk_parent,dept.pk_fatherorg");
		} else {
			sb.append("select sgsj.pk_group,'" + first_org + "' as pk_org,'"
					+ begindate.substring(0, 10)
					+ " 00:00:00' hzdate,sgsjb.tz_km_jzfs_1,");
			sb.append("sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sum(sgsjb.tz_km_data_1) tz_km_data_1,sgsjb.tz_km_jzfs_2,sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,sgsjb.tz_km_srxm_type1,sgsjb.tz_km_srxm_type2,");
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
			sb.append(" and nvl(replace(sgsj.vdef10,'~',''),'N')='N' ");	// ��ݲ�ȡ �Ƿ�Ƶ�=��  ������ ��HK 2018��11��6��21:05:30��
			sb.append("group by sgsj.pk_group,sgsjb.tz_km_jzfs_1,sgsjb.tz_km_srxm_1,sgsjb.tz_km_info_1,sgsjb.tz_km_jzfs_2,sgsjb.tz_km_srxm_type1,sgsjb.tz_km_srxm_type2,");
			sb.append("sgsjb.tz_km_srxm_2,sgsjb.tz_km_info_2,srxm1.pk_parent,srxm2.pk_parent,jzfs1.pk_parent,jzfs2.pk_parent");
		}
		List<SgsjInfoVO> list = (List<SgsjInfoVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(SgsjInfoVO.class));
		return list.toArray(new SgsjInfoVO[] {});
	}

	/**
	 * ��ѯ���˷�ʽ���Լ�����
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private List<JzfsHVO> getJzfsInfo() throws DAOException {
		// ���˷�ʽ������ɽ��Ȫ��֯����
		String sql = "select jzfs.*,jzfs_f.name vdef5 from hk_srgk_hg_jzfs jzfs left join hk_srgk_hg_jzfs jzfs_f on jzfs.pk_parent=jzfs_f.pk_hk_srgk_hg_jzfs where nvl(jzfs.dr,0)=0 and jzfs.pk_org='"
				+ HKJT_PUB.PK_ORG_HUIGUAN + "' order by jzfs.code";
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
	private List<SrxmHVO> getSrxmInfo() throws DAOException {
		// ���˷�ʽ���ݿ�������ɽ����֯����
		String sql = "select * from hk_srgk_hg_srxm where nvl(dr,0)=0 and pk_org='"
				+ HKJT_PUB.PK_ORG_HUIGUAN + "' order by code";
		List<SrxmHVO> list = (List<SrxmHVO>) getBD().executeQuery(sql,
				new BeanListProcessor(SrxmHVO.class));
		return list;
	}

	/**
	 * ������벿�ֽ��˷�ʽ��Ϣ
	 * */
	private Map<String, List<SrdibiaoBVO>> setLeftJzfsInfo(
			List<JzfsHVO> jzfsvos, HZShuJuVO[] hzsjvos, SgsjInfoVO[] sgsjvos,
			String pk_dept, Map<String, HykACkInfoVO> caiwu,
			Map<String, UFDouble> gz_info, RsbaogaoCVO_YY[] rstz,
			CctzVO_YY[] cctz, Map<String, UFDouble> map_wanglai_zd) {
		// ֻ��������Ϊһ�������
		Map<String, UFDouble> map_hzjzfs = new HashMap<String, UFDouble>();
		Map<String, List<SrdibiaoBVO>> map = new HashMap<String, List<SrdibiaoBVO>>();
		if (jzfsvos != null && jzfsvos.size() > 0 && hzsjvos != null
				&& hzsjvos.length > 0) {
			// �����ȼ����ѯ����֯��Ӧ�ĸ������˷�ʽ���
			for (int i = 0; i < hzsjvos.length; i++) {
				HZShuJuVO hzShuJuVO = hzsjvos[i];
				String pk_org = hzShuJuVO.getPk_org();
				UFDouble xianjin = getUFDouble_NullToZero(hzShuJuVO
						.getXianjin());
				UFDouble pos = getUFDouble_NullToZero(hzShuJuVO.getPos());
				UFDouble zhipiao = getUFDouble_NullToZero(hzShuJuVO
						.getZhipiao());
				UFDouble cika = getUFDouble_NullToZero(hzShuJuVO.getCika());
				UFDouble huiyuanka = getUFDouble_NullToZero(hzShuJuVO
						.getHuiyuankashiji());
				UFDouble daijinjuan = getUFDouble_NullToZero(hzShuJuVO
						.getDaijinquan());
				UFDouble fenqu = getUFDouble_NullToZero(hzShuJuVO.getFenqu());
				UFDouble miandan = getUFDouble_NullToZero(hzShuJuVO
						.getMiandan());
				UFDouble qtyh = getUFDouble_NullToZero(hzShuJuVO.getQtyh());
				UFDouble zdyh = getUFDouble_NullToZero(hzShuJuVO.getZdyh());
				UFDouble kblyh = getUFDouble_NullToZero(hzShuJuVO.getKblyh());
				String key_xianjin = pk_org + "xianjin";
				String key_pos = pk_org + "pos";
				String key_cika = pk_org + "cika";
				String key_huiyuanka = pk_org + "huiyuanka";
				String key_daijinjuan = pk_org + "daijinjuan";
				String key_fenqu = pk_org + "fenqu";
				String key_miandan = pk_org + "miandan";
				String key_zdyh = pk_org + "zdyh";
				String key_kblyh = pk_org + "kblyh";
				String key_qtyh = pk_org + "qtyh";
				String key_zhipiao = pk_org + "zhipiao";
				// �˴�����̬��ӵ��ֹ��Ż���Ϣ,Ĭ�Ͼ��ȴ���10��
				String sgyh_column = "youhui_sg_";
				for (int j = 1; j <= 10; j++) {
					if (j != 10) {
						sgyh_column = sgyh_column + "0" + j;
					} else {
						sgyh_column = sgyh_column + j;
					}
					String key = pk_org + sgyh_column;
					Object obj_youhui = hzShuJuVO
							.getAttributeValue(sgyh_column);
					if (!map_hzjzfs.keySet().contains(key)) {
						map_hzjzfs.put(key,
								obj_youhui == null ? UFDouble.ZERO_DBL
										: PuPubVO.getUFDouble_NullAsZero(obj_youhui.toString()));
					} else {
						map_hzjzfs.put(
								key,
								map_hzjzfs.get(key).add(
										obj_youhui == null ? UFDouble.ZERO_DBL
												: PuPubVO.getUFDouble_NullAsZero(obj_youhui
														.toString())));
					}
					sgyh_column = "youhui_sg_";
				}
				// �ֽ���˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_xianjin)) {
					map_hzjzfs.put(key_xianjin, xianjin);
				} else {
					map_hzjzfs.put(key_xianjin, map_hzjzfs.get(key_xianjin)
							.add(xianjin));
				}
				// pos���˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_pos)) {
					map_hzjzfs.put(key_pos, pos);
				} else {
					map_hzjzfs.put(key_pos, map_hzjzfs.get(key_pos).add(pos));
				}
				// ֧Ʊ���˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_zhipiao)) {
					map_hzjzfs.put(key_zhipiao, zhipiao);
				} else {
					map_hzjzfs.put(key_zhipiao, map_hzjzfs.get(key_zhipiao)
							.add(zhipiao));
				}
				// �ο����˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_cika)) {
					map_hzjzfs.put(key_cika, cika);
					// �˴��Զ��Żݼ�ȥ�ο�
					// map_hzjzfs.put(key_zdyh,
					// getUFDouble_NullToZero(map_hzjzfs.get(key_zdyh))
					// .sub(cika));
				} else {
					map_hzjzfs
							.put(key_cika, map_hzjzfs.get(key_cika).add(cika));
					// �˴��Զ��Żݼ�ȥ�ο�
					// map_hzjzfs.put(key_zdyh,
					// getUFDouble_NullToZero(map_hzjzfs.get(key_zdyh))
					// .sub(cika));
				}
				// ��Ա�����˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_huiyuanka)) {
					map_hzjzfs.put(key_huiyuanka, huiyuanka);
				} else {
					map_hzjzfs.put(key_huiyuanka, map_hzjzfs.get(key_huiyuanka)
							.add(huiyuanka));
				}
				// ���������˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_daijinjuan)) {
					map_hzjzfs.put(key_daijinjuan, daijinjuan);
				} else {
					map_hzjzfs.put(key_daijinjuan,
							map_hzjzfs.get(key_daijinjuan).add(daijinjuan));
				}
				// �������˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_fenqu)) {
					map_hzjzfs.put(key_fenqu, fenqu);
				} else {
					map_hzjzfs.put(key_fenqu,
							map_hzjzfs.get(key_fenqu).add(fenqu));
				}
				// �ⵥ���˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_miandan)) {
					map_hzjzfs.put(key_miandan, miandan);
				} else {
					map_hzjzfs.put(key_miandan, map_hzjzfs.get(key_miandan)
							.add(miandan));
				}
				// �Զ��Żݽ��˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_zdyh)) {
					map_hzjzfs.put(key_zdyh, zdyh);
				} else {
					map_hzjzfs
							.put(key_zdyh, map_hzjzfs.get(key_zdyh).add(zdyh));
				}
				// �������Żݽ��˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_kblyh)) {
					map_hzjzfs.put(key_kblyh, kblyh);
				} else {
					map_hzjzfs.put(key_kblyh,
							map_hzjzfs.get(key_kblyh).add(kblyh));
				}
				// �����Żݽ��˷�ʽ���
				if (!map_hzjzfs.keySet().contains(key_qtyh)) {
					map_hzjzfs.put(key_qtyh, qtyh);
				} else {
					map_hzjzfs
							.put(key_qtyh, map_hzjzfs.get(key_qtyh).add(qtyh));
				}
			}
			// �����ֹ�����
			Map<String, UFDouble> map_sgsj = new HashMap<String, UFDouble>();
			// ������Ϣ
			Map<String, List<String[]>> map_wanglai = new HashMap<String, List<String[]>>();
			if (sgsjvos != null && sgsjvos.length > 0) {

				for (int i = 0; i < sgsjvos.length; i++) {
					SgsjInfoVO sgsjInfoVO = sgsjvos[i];
					String sgsj_org = sgsjInfoVO.getPk_org();
					String sgsj_tzjzfs1 = sgsjInfoVO.getTz_km_jzfs_1();
					String sgsj_tzdate1 = sgsjInfoVO.getTz_km_data_1();
					String sgsj_tzjzfs2 = sgsjInfoVO.getTz_km_jzfs_2();
					String sgsj_tzdate2 = sgsjInfoVO.getTz_km_data_2();
					String key1 = sgsj_org + sgsj_tzjzfs1;
					String key2 = sgsj_org + sgsj_tzjzfs2;
					// ��ȡ������ϸ
					String sgsj_tzinfo1 = sgsjInfoVO.getTz_km_info_1();
					String sgsj_tzinfo2 = sgsjInfoVO.getTz_km_info_2();
					if (sgsj_tzinfo1 != null && !"".equals(sgsj_tzinfo1)) {
						if (!map_wanglai.keySet().contains(key1)) {
							List<String[]> list = new ArrayList<String[]>();
							list.add(new String[] { sgsj_tzinfo1, sgsj_tzdate1 });
							map_wanglai.put(key1, list);
						} else {
							map_wanglai.get(key1)
									.add(new String[] { sgsj_tzinfo1,
											sgsj_tzdate1 });
						}
					}
					if (sgsj_tzinfo2 != null && !"".equals(sgsj_tzinfo2)) {
						if (!map_wanglai.keySet().contains(key2)) {
							List<String[]> list = new ArrayList<String[]>();
							list.add(new String[] { sgsj_tzinfo2, sgsj_tzdate2 });
							map_wanglai.put(key2, list);
						} else {
							map_wanglai.get(key2)
									.add(new String[] { sgsj_tzinfo2,
											sgsj_tzdate2 });
						}
					}

					if (!map_sgsj.keySet().contains(key1)) {
						map_sgsj.put(key1, PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1));
					} else {
						map_sgsj.put(
								key1,
								map_sgsj.get(key1).add(
										PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate1)));
					}
					if (!map_sgsj.keySet().contains(key2)) {
						map_sgsj.put(key2, PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2));
					} else {
						map_sgsj.put(
								key2,
								map_sgsj.get(key2).add(
										PuPubVO.getUFDouble_NullAsZero(sgsj_tzdate2)));
					}

				}
			}
			// �˴��������󱨸������Ϣ�����Ϊ��Ĭ�ϲ��������Ĭ��Ϊ������ױ��ѯ
			Map<String, UFDouble> map_rstz = new HashMap<String, UFDouble>();
			if (rstz != null && rstz.length > 0) {
				for (int i = 0; i < rstz.length; i++) {
					RsbaogaoCVO_YY rstzvo = rstz[i];
					String sgsj_org = rstzvo.getPk_org();
					String sgsj_tzjzfs1 = rstzvo.getTz_km_jzfs_1();
					UFDouble sgsj_tzdate1 = rstzvo.getTz_km_data_1();
					String sgsj_tzjzfs2 = rstzvo.getTz_km_jzfs_2();
					UFDouble sgsj_tzdate2 = rstzvo.getTz_km_data_2();
					String key1 = sgsj_org + sgsj_tzjzfs1;
					String key2 = sgsj_org + sgsj_tzjzfs2;
					if (!map_rstz.keySet().contains(key1)) {
						map_rstz.put(key1, getUFDouble_NullToZero(sgsj_tzdate1));
					} else {
						map_rstz.put(
								key1,
								map_rstz.get(key1).add(
										getUFDouble_NullToZero(sgsj_tzdate1)));
					}
					if (!map_rstz.keySet().contains(key2)) {
						map_rstz.put(key2, getUFDouble_NullToZero(sgsj_tzdate2));
					} else {
						map_rstz.put(
								key2,
								map_rstz.get(key2).add(
										getUFDouble_NullToZero(sgsj_tzdate2)));
					}

				}
			}

			// �˴������������Ϣ�����Ϊ��Ĭ�ϲ��������Ĭ��Ϊ������ױ��ѯ
			Map<String, UFDouble> map_cctz = new HashMap<String, UFDouble>();
			if (cctz != null && cctz.length > 0) {
				for (int i = 0; i < cctz.length; i++) {
					CctzVO_YY rstzvo = cctz[i];
					String sgsj_org = rstzvo.getPk_org();
					String sgsj_tzjzfs1 = rstzvo.getTz_km_jzfs_1();
					UFDouble sgsj_tzdate1 = rstzvo.getTz_km_data_1();
					String sgsj_tzjzfs2 = rstzvo.getTz_km_jzfs_2();
					UFDouble sgsj_tzdate2 = rstzvo.getTz_km_data_2();
					String key1 = sgsj_org + sgsj_tzjzfs1;
					String key2 = sgsj_org + sgsj_tzjzfs2;
					if (!map_rstz.keySet().contains(key1)) {
						map_rstz.put(key1, getUFDouble_NullToZero(sgsj_tzdate1));
					} else {
						map_rstz.put(
								key1,
								map_rstz.get(key1).add(
										getUFDouble_NullToZero(sgsj_tzdate1)));
					}
					if (!map_rstz.keySet().contains(key2)) {
						map_rstz.put(key2, getUFDouble_NullToZero(sgsj_tzdate2));
					} else {
						map_rstz.put(
								key2,
								map_rstz.get(key2).add(
										getUFDouble_NullToZero(sgsj_tzdate2)));
					}

				}
			}
			Set<String> ishaveJzfs = new HashSet<String>();
			// �ϲ���˷�ʽ�Ľ�����
			Map<String, UFDouble> map_fjzfs = new HashMap<String, UFDouble>();
			// ���˷�ʽPK ���ϼ��Ķ�Ӧ���� �ݹ鴦���ϲ���Ļ���
			Map<String, String> map_jzfs_fjzfs = new HashMap<String, String>();
			for (int i = 0; i < jzfsvos.size(); i++) {
				JzfsHVO jzfsvo = jzfsvos.get(i);
				String jzfs_pk = jzfsvo.getPk_hk_srgk_hg_jzfs();
				String jzfs_fpk = jzfsvo.getPk_parent();
				if (jzfs_pk != null && !"".equals(jzfs_pk) && jzfs_fpk != null
						&& !"".equals(jzfs_fpk)) {
					map_jzfs_fjzfs.put(jzfs_pk, jzfs_fpk);
				}
			}
			for (int i = 0; i < jzfsvos.size(); i++) {
				JzfsHVO jzfsvo = jzfsvos.get(i);
				String jzfs_pk = jzfsvo.getPk_hk_srgk_hg_jzfs();
				String jzfszdzd = jzfsvo.getVdef1();
				String jzfsfname = jzfsvo.getVdef5();
				String jzfs_fpk = jzfsvo.getPk_parent();
				String jzfsname = jzfsvo.getName();
				String jzfs_wanglai_name = jzfsvo.getVdef3();
				String jzfsname_temp = jzfsvo.getName();
				Integer levelno = jzfsvo.getLevelno();
				SrdibiaoBVO srdb = new SrdibiaoBVO();
				srdb.setJzfs_code(jzfsvo.getCode());
				if (levelno != null && levelno != 1) {
					for (int j = 0; j < levelno; j++) {
						jzfsname_temp = "    " + jzfsname_temp;
					}
					srdb.setJzfs_name(jzfsname_temp);
				} else {
					srdb.setJzfs_name(jzfsvo.getName());
				}
				srdb.setJzfs_pk(jzfs_pk);
				// ��ȡ�м�����֯������
				Set<String> set = map_hzjzfs.keySet();
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					String str = it.next();
					String orgjine = str.substring(0, 20);
					// String org_jzfs = str.substring(0, 20) +
					// jzfsvo.getName();
					String org_jzfs_code = str.substring(0, 20)
							+ jzfsvo.getPk_hk_srgk_hg_jzfs();// ԭ���Ǹ�����֯+����У�飬�ָ�Ϊ��֯+���˷�ʽ����У��
					if (!ishaveJzfs.contains(org_jzfs_code)) {
						// ��ȡ��Ա����ֵ�ʹο���ֵ��Ϣ
						HykACkInfoVO cwzvo_hyk = caiwu.get(orgjine + "��Ա��");
						HykACkInfoVO cwzvo_ck = caiwu.get(orgjine + "�ײͷ���");
						HykACkInfoVO cwzvo_cktcdz = caiwu.get(orgjine + "�ο��ײʹ���");
						
						if (jzfsname.equals("�ֽ�")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "xianjin")));
							// �жϻ�Ա�����ۺʹο����ۼ�¼
							// �˴������Ա�����ۣ��ο����� �е� pos���ֽ��� ֻ����ĩ��
							if (pk_dept == null || "".equals(pk_dept)
									|| "~".equals(pk_dept)) {

								UFDouble xianjin_1 = cwzvo_hyk == null ? UFDouble.ZERO_DBL
										: cwzvo_hyk.getXianjin();
								UFDouble xianjin_2 = cwzvo_ck == null ? UFDouble.ZERO_DBL
										: cwzvo_ck.getXianjin();
								/**���   �ο��ײʹ���*/
								UFDouble xianjin_3 = cwzvo_cktcdz == null ? UFDouble.ZERO_DBL
										: cwzvo_cktcdz.getXianjin();
								UFDouble xianjin = getUFDouble_NullToZero(xianjin_1)
											  .add(getUFDouble_NullToZero(xianjin_2))
											  .add(getUFDouble_NullToZero(xianjin_3));	// ���
								srdb.setJine(srdb.getJine().add(xianjin));
							}
						} else if (jzfsname.equals("POS")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "pos")));
							if (pk_dept == null || "".equals(pk_dept)
									|| "~".equals(pk_dept)) {
								// �����Ա��
								UFDouble pos_1 = cwzvo_hyk == null ? UFDouble.ZERO_DBL
										: cwzvo_hyk.getPos();
								UFDouble pos_2 = cwzvo_ck == null ? UFDouble.ZERO_DBL
										: cwzvo_ck.getPos();
								/**���   �ο��ײʹ���*/
								UFDouble pos_3 = cwzvo_cktcdz == null ? UFDouble.ZERO_DBL
										: cwzvo_cktcdz.getPos();
								UFDouble pos_uf = getUFDouble_NullToZero(pos_1)
										.add(getUFDouble_NullToZero(pos_2))
										.add(getUFDouble_NullToZero(pos_3));	// ���
								srdb.setJine(srdb.getJine().add(pos_uf));
							}
						} else if (jzfsname.equals("֧Ʊ")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "zhipiao")));
							if (pk_dept == null || "".equals(pk_dept)
									|| "~".equals(pk_dept)) {
								// �����Ա��֧Ʊ��Ϣ
								UFDouble zhipiao_1 = cwzvo_hyk == null ? UFDouble.ZERO_DBL
										: cwzvo_hyk.getZhipiao();
								UFDouble zhipiao_2 = cwzvo_ck == null ? UFDouble.ZERO_DBL
										: cwzvo_ck.getZhipiao();
								UFDouble zhipiao_3 = cwzvo_cktcdz == null ? UFDouble.ZERO_DBL
										: cwzvo_cktcdz.getZhipiao();
								UFDouble zhipiao_uf = getUFDouble_NullToZero(
										zhipiao_1).add(
										getUFDouble_NullToZero(zhipiao_2)).add(
										getUFDouble_NullToZero(zhipiao_3));
								srdb.setJine(srdb.getJine().add(zhipiao_uf));
							}
						} else if (jzfsname.equals("��Ա������")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "huiyuanka")));
							// �˴�����俨��Ա��ˢ�Ľ��
							if (pk_dept == null || "".equals(pk_dept)
									|| "~".equals(pk_dept)) {
								// �����Ա��
								srdb.setJine(srdb
										.getJine()
										.add(getUFDouble_NullToZero(cwzvo_hyk == null ? UFDouble.ZERO_DBL
												: cwzvo_hyk.getHyaskje())));
								// �����Ա��ʵ��
								srdb.setJine(srdb
										.getJine()
										.add(getUFDouble_NullToZero(cwzvo_ck == null ? UFDouble.ZERO_DBL
												: cwzvo_ck.getHyaskje())));
								// ����ο��ײʹ���
								srdb.setJine(srdb
										.getJine()
										.add(getUFDouble_NullToZero(cwzvo_cktcdz == null ? UFDouble.ZERO_DBL
												: cwzvo_cktcdz.getHyaskje())));

							}
						} else if (jzfsname.equals("�ο�����")) {
							srdb.setJine(UFDouble.ZERO_DBL);
							// srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
							// .get(orgjine + "cika")));
						} else if (jzfsname.equals("�Զ��Ż�")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "zdyh")));
						} else if (jzfsname.equals("����ȯ")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "daijinjuan")));
						} else if (jzfsname.equals("�ⵥ")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "miandan")));
						} else if (jzfsname.equals("�������")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "fenqu")));
						} else if (jzfsname.equals("�������Ż�")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "kblyh")));
						} else if (jzfsname.equals("�����Ż�")) {
							srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
									.get(orgjine + "qtyh")));
						} else if (jzfsname.equals("��Ա������")) {
							srdb.setJine(UFDouble.ZERO_DBL);
							if (pk_dept == null || "".equals(pk_dept)
									|| "~".equals(pk_dept)) {
								srdb.setJine(getUFDouble_NullToZero(cwzvo_hyk == null ? UFDouble.ZERO_DBL
										: cwzvo_hyk.getHyksr()));
							}

						} else if (jzfsname.equals("�ο�����")) {
							srdb.setJine(UFDouble.ZERO_DBL);
							if (pk_dept == null || "".equals(pk_dept)
									|| "~".equals(pk_dept)) {
								// �����ײͷ���
								srdb.setJine(getUFDouble_NullToZero(cwzvo_ck == null ? UFDouble.ZERO_DBL
										: cwzvo_ck.getCksr()));
								// ����ο��ײʹ���
								srdb.setJine(srdb
										.getJine()
										.add(cwzvo_cktcdz == null ? UFDouble.ZERO_DBL
												: cwzvo_cktcdz.getCksr()));
							}
						}
						// else if (jzfsname.equals("�����ͻ�����")) {
						// srdb.setJine(UFDouble.ZERO_DBL);
						// if (pk_dept == null || "".equals(pk_dept)
						// || "~".equals(pk_dept)) {
						// srdb.setJine(map_wanglai_zd.get(orgjine));
						// }
						//
						// }
						// ���������Ľ��˷�ʽ���繺���ͻ�������΢��֧����
						else if (jzfs_wanglai_name != null
								&& !"".equals(jzfs_wanglai_name)
								&& !"~".equals(jzfs_wanglai_name)) {
							srdb.setJine(UFDouble.ZERO_DBL);
							if (pk_dept == null || "".equals(pk_dept)
									|| "~".equals(pk_dept)) {
								String[] jzfswlnames = jzfs_wanglai_name
										.split("��");
								for (int j = 0; j < jzfswlnames.length; j++) {
									String jzfsname1 = jzfswlnames[j];
									if (map_wanglai_zd.containsKey(orgjine
											+ jzfsname1)) {
										srdb.setJine(srdb.getJine().add(
												map_wanglai_zd.get(orgjine
														+ jzfsname1)));
									}
								}
							}
						} else if (map_hzjzfs.keySet().contains(
								orgjine + jzfszdzd)) {
							// ����̬�ֶ��Ż���Ϣ
							if (jzfszdzd != null && !"".equals(jzfszdzd)) {
								srdb.setJine(getUFDouble_NullToZero(map_hzjzfs
										.get(orgjine + jzfszdzd)));
							}

						} else {
							srdb.setJine(UFDouble.ZERO_DBL);
						}
						ishaveJzfs.add(org_jzfs_code);
						// �˴������ֹ�������Ϣ
						if (map_sgsj.size() > 0) {
							if (map_sgsj.keySet().contains(orgjine + jzfs_pk)) {
								srdb.setJine(getUFDouble_NullToZero(
										srdb.getJine()).add(
										map_sgsj.get(orgjine + jzfs_pk)));
							}
						}
						// �˴��������󱨸���Ϣ�����Ϊ�տ���Ĭ��Ϊ����ױ��ѯ
						if (map_rstz.size() > 0) {
							if (map_rstz.keySet().contains(orgjine + jzfs_pk)) {
								srdb.setJine(getUFDouble_NullToZero(
										srdb.getJine()).add(
										map_rstz.get(orgjine + jzfs_pk)));
							}
						}
						// �˴������������Ϣ�����Ϊ�տ���Ĭ��Ϊ����ױ��ѯ
						if (map_cctz.size() > 0) {
							if (map_cctz.keySet().contains(orgjine + jzfs_pk)) {
								srdb.setJine(getUFDouble_NullToZero(
										srdb.getJine()).add(
										map_cctz.get(orgjine + jzfs_pk)));
							}
						}

						// �˴���Ҫ�����ڲ�������Ҳ���ǹ�����Ϣ
						// if (gz_info.size() > 0) {
						// if (gz_info.keySet().contains(orgjine + jzfs_pk)) {
						// srdb.setJine(getUFDouble_NullToZero(
						// srdb.getJine()).add(
						// gz_info.get(orgjine + jzfs_pk)));
						// }
						// }

						// ��¼�ϲ���ܺͣ��ݹ�
						// ��� ���˷�ʽ ���õ�������Ŀ �ظ��� ��ᵼ�� ���ܵ����� Ҳ�ظ������  2016��7��7��17:21:03��
						if (jzfs_fpk != null && !"".equals(jzfs_fpk)
								&& !"~".equals(jzfs_fpk)) {
							setForFJzfsJine(map_jzfs_fjzfs, jzfs_fpk,
									map_fjzfs, orgjine,
									getUFDouble_NullToZero(srdb.getJine()));
						}
						// ����ǻ�Ա�����ۺʹο����������,Ҫ��ȥ�ۿ��Ľ��

						if (!map_fjzfs.keySet().contains(orgjine)) {
							if ("��Ա������".equals(srdb.getJzfs_name().trim())
									|| "�ο�����"
											.equals(srdb.getJzfs_name().trim())) {
								map_fjzfs.put(orgjine,
										UFDouble.ZERO_DBL.sub(srdb.getJine()));
							} else {
								map_fjzfs.put(orgjine, srdb.getJine());
							}

						} else {
							if ("��Ա������".equals(srdb.getJzfs_name().trim())
									|| "�ο�����"
											.equals(srdb.getJzfs_name().trim())) {
								map_fjzfs.put(
										orgjine,
										getUFDouble_NullToZero(
												map_fjzfs.get(orgjine)).sub(
												getUFDouble_NullToZero(srdb
														.getJine())));
							} else {
								map_fjzfs.put(
										orgjine,
										getUFDouble_NullToZero(
												map_fjzfs.get(orgjine)).add(
												getUFDouble_NullToZero(srdb
														.getJine())));
							}
						}

						if (!map.keySet().contains(orgjine)) {
							List<SrdibiaoBVO> list = new ArrayList<SrdibiaoBVO>();
							list.add(srdb);
							map.put(orgjine, list);
						} else {
							map.get(orgjine).add(srdb);
						}
						if (jzfsfname != null && jzfsfname.equals("����")) {

							List<String[]> list2 = map_wanglai.get(orgjine
									+ jzfs_pk);
							if (list2 != null && list2.size() > 0) {
								for (int j = 0; j < list2.size(); j++) {
									String[] str1 = list2.get(j);
									String name = str1[0];
									String jine = str1[1];
									SrdibiaoBVO srdibiao = new SrdibiaoBVO();
									for (int k = 0; k < levelno + 1; k++) {
										name = "    " + name;
									}
									srdibiao.setJzfs_name(name);
									srdibiao.setPk_fjzfs(jzfs_pk);
									srdibiao.setIswanglai("Y");
									srdibiao.setJine(PuPubVO.getUFDouble_NullAsZero(jine));

									if (map.keySet().contains(orgjine)) {
										map.get(orgjine).add(srdibiao);
									}
								}
							}
						}
					}
				}
			}
			Set<String> left_org = map.keySet();
			Iterator<String> it = left_org.iterator();
			while (it.hasNext()) {
				String org = it.next();
				List<SrdibiaoBVO> org_srdbvo = map.get(org);
				for (int i = 0; i < org_srdbvo.size(); i++) {
					SrdibiaoBVO srdbvo = org_srdbvo.get(i);
					String jfzfpk = srdbvo.getJzfs_pk();
					if (map_fjzfs.keySet().contains(org + jfzfpk)) {
						srdbvo.setJine(map_fjzfs.get(org + jfzfpk));
					}

				}
				// �����ܽ��
				SrdibiaoBVO bvo = new SrdibiaoBVO();
				SrdibiaoBVO bvo1 = new SrdibiaoBVO();
				SrdibiaoBVO bvo2 = new SrdibiaoBVO();
				bvo.setJine(map_fjzfs.get(org));
				org_srdbvo.add(bvo1);
				org_srdbvo.add(bvo2);
				org_srdbvo.add(bvo);
			}
		}
		return map;
	}

	// ��ȡ���񲿻�Ա���ʹο���ֵ��Ϣ��ֻ����ڰ���֯��ѯʱ��Ч
	@SuppressWarnings("unchecked")
	private Map<String, HykACkInfoVO> getCWHuiyuankaAndCikaJine(
			String first_org, String pk_org, String begindate, String enddate)
			throws DAOException {
		StringBuffer sql = new StringBuffer();
		sql.append("select '"
				+ first_org
				+ "' as pk_org,'"
				+ begindate.substring(0, 10)
				+ " 00:00:00' dbilldate,b.sqfl_name, sum(nvl(b.xianjin,0)) xianjin,sum(nvl(b.pos,0)) pos,sum(nvl(b.zhipiao,0)) zhipiao,sum(nvl(b.huiyuanka,0)-nvl(b.youhui_kabili,0)) hyaskje,sum(nvl(b.chongka_hyk_sr,0)) hyksr,sum(nvl(b.chongka_ck_sr,0)) cksr from hk_srgk_hg_zhangdan h left join hk_srgk_hg_zhangdan_b b ");
		sql.append(" on h.pk_hk_dzpt_hg_zhangdan=b.pk_hk_dzpt_hg_zhangdan ");
		sql.append("where nvl(h.dr,0)=0 and nvl(b.dr,0)=0 and  b.sqfl_name in ('��Ա��','�ײͷ���','�ο��ײʹ���') ");
		sql.append(" and h.pk_org in (" + pk_org + ")");
		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
				+ enddate + "','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" group by sqfl_name");

		List<HykACkInfoVO> list = (List<HykACkInfoVO>) getBD().executeQuery(
				sql.toString(), new BeanListProcessor(HykACkInfoVO.class));
		Map<String, HykACkInfoVO> map = new HashMap<String, HykACkInfoVO>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HykACkInfoVO vo = list.get(i);
				String goodscatalogname = vo.getSqfl_name();
				String org = vo.getPk_org();
				map.put(org + goodscatalogname, vo);
			}
		}
		return map;
	}

	// ��ȡ�˵���Ա��������Ϣ�������˷�ʽ----�����ͻ�����
	@SuppressWarnings("unchecked")
	private Map<String, UFDouble> getWangLaiInfo(String first_org,
			String pk_org, String begindate, String enddate)
			throws DAOException {

		StringBuffer sql = new StringBuffer();
		sql.append("select  '"
				+ first_org
				+ "' as pk_org,'"
				+ begindate.substring(0, 10)
				+ " 00:00:00' hzdate,sum(nvl(b.wanglai,0)) wanglai_jine,h.wanglai_info wanglai_name " 
				+ " from hk_srgk_hg_zhangdan h ");
		sql.append(" left join hk_srgk_hg_zhangdan_b b on h.pk_hk_dzpt_hg_zhangdan=b.pk_hk_dzpt_hg_zhangdan ");
		sql.append(" where nvl(h.dr,0)=0   ");
		sql.append(" and nvl(b.dr,0)=0 ");
		// sql.append("and  b.sqfl_name in ('��Ա��','�ײͷ���','�ο��ײʹ���') ");//
		// Ŀǰֻ�л�Ա����ֵ�Ż����ǹ�Ʊ�������
		sql.append(" and h.pk_org in (" + pk_org + ") ");
		// ��ȡ ΢��֧��  ��  ֧����֧��   ����ȡ Ӧ�����š�Ӧ��Я�̡��Ź���Ʊ 2017��10��16��11:14:56 ��
		/**
		 * HK-����2
		 * 2019��1��8��18:27:00
		 */
		sql.append( this.getSQL_1() );
		
		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
				+ enddate
				+ "','yyyy-mm-dd hh24:mi:ss') group by h.wanglai_info ");
		
		/**
		 * 2017��7��24��19:29:46
		 * ���ֿ�  ΢��֧��  ��  ֧����֧��
		 * 
		 * 2017��10��16��11:18:26
		 * �����ֿ�   Ӧ�����š�Ӧ��Я�̡��Ź���Ʊ
		 */
//		sql.append(" union all ");
//		sql.append("select "
//				+ " '"+ first_org +"' as pk_org "
//				+ ",'"+ begindate.substring(0, 10) + " 00:00:00' hzdate "
//				+ ",sum(to_number(h.vdef01)) wanglai_jine" 
//				+ ",'΢��֧��' wanglai_name " 
//				+ " from hk_srgk_hg_zhangdan h ");
//		sql.append(" where nvl(h.dr,0)=0 ");
//		sql.append(" and h.pk_org in (" + pk_org + ")");
//		sql.append(" and ( nvl(h.vdef01,'~') != '~' ) ");	// ȡ ΢��֧��
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
//				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
//				+ enddate
//				+ "','yyyy-mm-dd hh24:mi:ss') group by h.wanglai_info ");
//		
//		sql.append(" union all ");
//		sql.append("select "
//				+ " '"+ first_org +"' as pk_org "
//				+ ",'"+ begindate.substring(0, 10) + " 00:00:00' hzdate "
//				+ ",sum(to_number(h.vdef02)) wanglai_jine" 
//				+ ",'֧����֧��' wanglai_name " 
//				+ " from hk_srgk_hg_zhangdan h ");
//		sql.append(" where nvl(h.dr,0)=0 ");
//		sql.append(" and h.pk_org in (" + pk_org + ")");
//		sql.append(" and ( nvl(h.vdef02,'~') != '~' ) ");	// ȡ ֧����֧��
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
//				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
//				+ enddate
//				+ "','yyyy-mm-dd hh24:mi:ss') group by h.wanglai_info ");
//		
//		sql.append(" union all ");
//		sql.append("select "
//				+ " '"+ first_org +"' as pk_org "
//				+ ",'"+ begindate.substring(0, 10) + " 00:00:00' hzdate "
//				+ ",sum(to_number(h.vdef03)) wanglai_jine" 
//				+ ",'Ӧ������' wanglai_name " 
//				+ " from hk_srgk_hg_zhangdan h ");
//		sql.append(" where nvl(h.dr,0)=0 ");
//		sql.append(" and h.pk_org in (" + pk_org + ")");
//		sql.append(" and ( nvl(h.vdef03,'~') != '~' ) ");	// ȡ Ӧ������
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
//				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
//				+ enddate
//				+ "','yyyy-mm-dd hh24:mi:ss') group by h.wanglai_info ");
//		
//		sql.append(" union all ");
//		sql.append("select "
//				+ " '"+ first_org +"' as pk_org "
//				+ ",'"+ begindate.substring(0, 10) + " 00:00:00' hzdate "
//				+ ",sum(to_number(h.vdef04)) wanglai_jine" 
//				+ ",'Ӧ��Я��' wanglai_name " 
//				+ " from hk_srgk_hg_zhangdan h ");
//		sql.append(" where nvl(h.dr,0)=0 ");
//		sql.append(" and h.pk_org in (" + pk_org + ")");
//		sql.append(" and ( nvl(h.vdef04,'~') != '~' ) ");	// ȡ Ӧ��Я��
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
//				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
//				+ enddate
//				+ "','yyyy-mm-dd hh24:mi:ss') group by h.wanglai_info ");
//		
//		sql.append(" union all ");
//		sql.append("select "
//				+ " '"+ first_org +"' as pk_org "
//				+ ",'"+ begindate.substring(0, 10) + " 00:00:00' hzdate "
//				+ ",sum(to_number(h.vdef05)) wanglai_jine" 
//				+ ",'�Ź���Ʊ' wanglai_name " 
//				+ " from hk_srgk_hg_zhangdan h ");
//		sql.append(" where nvl(h.dr,0)=0 ");
//		sql.append(" and h.pk_org in (" + pk_org + ")");
//		sql.append(" and ( nvl(h.vdef05,'~') != '~' ) ");	// ȡ �Ź���Ʊ
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
//				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
//		sql.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
//				+ enddate
//				+ "','yyyy-mm-dd hh24:mi:ss') group by h.wanglai_info ");
		
		/**
		 * HK-����2
		 * 2019��1��8��20:45:01
		 */
		sql.append(this.getSQL_2(first_org, pk_org, begindate, enddate));
		
		/**END*/

		List<WangLaiInfoVO> list = (List<WangLaiInfoVO>) getBD().executeQuery(
				sql.toString(), new BeanListProcessor(WangLaiInfoVO.class));
		Map<String, UFDouble> map = new HashMap<String, UFDouble>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				WangLaiInfoVO vo = list.get(i);
				String wanglai_name = vo.getWanglai_name();
				if (wanglai_name == null || "".equals(wanglai_name)
						|| "~".equals(wanglai_name)) {
					continue;
				}
				String org = vo.getPk_org();
				String key = org + wanglai_name;
				UFDouble wlje = vo.getWanglai_jine();
				if (!map.keySet().contains(key)) {
					map.put(key, wlje);
				} else {
					map.put(key, map.get(key).add(wlje));
				}
			}
		}
		return map;

	}

	// �ݹ鸳ֵ�ϲ���˷�ʽ���
	private void setForFJzfsJine(Map<String, String> map_jzfs_fjzfs,
			String key_jzfshz, Map<String, UFDouble> map_fjzfs, String org,
			UFDouble jine) {
		if (key_jzfshz != null && !"".equals(key_jzfshz)) {
			if (!map_fjzfs.keySet().contains(org + key_jzfshz)) {
				map_fjzfs.put(org + key_jzfshz, jine);
			} else {
				map_fjzfs.put(org + key_jzfshz, map_fjzfs.get(org + key_jzfshz)
						.add(jine));
			}
			String f_jzfs = map_jzfs_fjzfs.get(key_jzfshz);
			if (f_jzfs != null && !"".equals(f_jzfs)) {
				setForFJzfsJine(map_jzfs_fjzfs, f_jzfs, map_fjzfs, org, jine);
			}
		}
	}

	// ��ѯ��������
	@SuppressWarnings("unchecked")
	private Map<String, Integer> getInPerson(String first_org, String pk_org,
			String begindate, String enddate) throws DAOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append("select '"
				+ first_org
				+ "' as pk_org, '"
				+ begindate.substring(0, 10)
				+ " 00:00:00' dbilldate," +
				" count(0) checkoutperson " +
				" from hk_srgk_hg_zhangdan h " +
				" left join ");
		sb.append("hk_srgk_hg_zhangdan_b b on h.pk_hk_dzpt_hg_zhangdan=b.pk_hk_dzpt_hg_zhangdan" +
				" where h.dr=0 and b.dr=0  ");
		sb.append(" and b.sq_name in ('����Ʊ','Ů��Ʊ','ԡ��','���޹�����','Ů�޹�����','��������')" +
				  " and h.pk_org in (" + pk_org
				+ ") ");
		sb.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')>=to_date('"
				+ begindate + "','yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" and to_date(h.dbilldate,'yyyy-mm-dd hh24:mi:ss')<=to_date('"
				+ enddate + "','yyyy-mm-dd hh24:mi:ss')");
		// sb.append("   group by h.pk_org ");
		List<BanCiVO> list = (List<BanCiVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(BanCiVO.class));
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BanCiVO banci = list.get(i);
				String org = banci.getPk_org();
				String dbilldate = banci.getDbilldate().toString();
				Integer checkoutperson = banci.getCheckoutperson();
				map.put(org + dbilldate, checkoutperson);
			}
		}

		/**
		 * �ֹ����ݣ����Խ��� ��������������
		 * HK 2021��2��18��22:43:11
		 * select sg.pk_org,
			substr(sg.dbilldate,1,10) || ' 00:00:00' as dbilldate,
			to_number(sg.vdef01) checkoutperson 
			from hk_srgk_hg_sgshuju sg
			where sg.dr = 0
			and sg.ibillstatus = 1
			and to_date(sg.dbilldate,'yyyy-mm-dd hh24:mi:ss') >= to_date('2021-02-17 00:00:00','yyyy-mm-dd hh24:mi:ss')
			and to_date(sg.dbilldate,'yyyy-mm-dd hh24:mi:ss') <= to_date('2021-02-17 23:59:59','yyyy-mm-dd hh24:mi:ss') 
			and nvl(sg.vdef10, '~') in ('N','~')
			and sg.vdef01 <> '~'
		 */
		{
			StringBuffer sg_SQL = new StringBuffer("select ")
				.append(" sg.pk_org, ")
				.append(" substr(sg.dbilldate,1,10) || ' 00:00:00' as dbilldate, ")
				.append(" to_number(sg.vdef01) checkoutperson ")
				.append(" from hk_srgk_hg_sgshuju sg ")
				.append(" where sg.dr = 0 ")
				.append(" and sg.ibillstatus = 1 ")
				.append(" and sg.pk_org = '").append(first_org).append("' ")
				.append(" and substr(sg.dbilldate,1,10) between '")
							.append(begindate)
							.append("' and '")
							.append(enddate)
							.append("' ")
				.append(" and nvl(sg.vdef10, '~') in ('N','~') ")
				.append(" and nvl(sg.vdef01, '~') <> '~' ")
			;
			List<BanCiVO> sg_list = (List<BanCiVO>) getBD().executeQuery(
					sg_SQL.toString(), new BeanListProcessor(BanCiVO.class));
			if (sg_list != null && !sg_list.isEmpty()) {
				for (int i = 0; i < sg_list.size(); i++) {
					BanCiVO vo = sg_list.get(i);
					String org = vo.getPk_org();
					String dbilldate = vo.getDbilldate().toString();
					Integer checkoutperson = vo.getCheckoutperson();
					String key = org + dbilldate;
					if (map.containsKey(key)) {
						map.put(key, map.get(key) + checkoutperson);
					} else {
						map.put(key, checkoutperson);
					}
				}
			}
		}
		/***END***/
		
		return map;
	}

	private Integer getInteger_NullToZero(Integer jdrs) {
		if (jdrs == null) {
			return 0;
		} else {
			return jdrs;
		}
	}

	/** ��ѯƽ��ֵ */
	private void computInPersonJine(SrdibiaoBVO jine, SrdibiaoBVO inperson_srdb) {
		UFDouble inper = inperson_srdb.getJine();
		UFDouble yingshou = getUFDouble_NullToZero(jine.getYingshou());
		UFDouble shouru = getUFDouble_NullToZero(jine.getShouru());
		UFDouble kabili = getUFDouble_NullToZero(jine.getYouhui_kabili());
		UFDouble shougong = getUFDouble_NullToZero(jine.getYouhui_shougong());
		UFDouble zidong = getUFDouble_NullToZero(jine.getYouhui_zidong());
		inperson_srdb.setSrmx_name("�˾�");
		if (inper.compareTo(UFDouble.ZERO_DBL) != 0) {
			inperson_srdb.setYingshou(yingshou.div(inper));
			inperson_srdb.setShouru(shouru.div(inper));
			inperson_srdb.setYouhui_kabili(kabili.div(inper));
			inperson_srdb.setYouhui_shougong(shougong.div(inper));
			inperson_srdb.setYouhui_zidong(zidong.div(inper));
		}

	}

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @throws DAOException
	 * */
	@SuppressWarnings("unchecked")
	private Map<String, UFDouble> getGZ_Info(String first_org, String pk_org,
			String pk_dept, String begindate, String enddate)
			throws DAOException {
		StringBuffer sb = new StringBuffer();
		sb.append("select gzshuju.*,jzfs.pk_hk_srgk_hg_jzfs jzfs_pk from ( ");
		sb.append("select pk_group,'"
				+ first_org
				+ "' as pk_org,'"
				+ begindate.substring(0, 10)
				+ " 00:00:00' hzdate,gz_info,sum(gz_jine) gz_jine from hk_srgk_hg_hzshuju_gz ");
		sb.append(" where nvl(dr,0)=0 and pk_org  in (" + pk_org + ") ");
		sb.append(" and to_date(hzdate, 'yyyy-mm-dd hh24:mi:ss')>=to_date(substr('"
				+ begindate + "',0,10),'yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" and to_date(hzdate, 'yyyy-mm-dd hh24:mi:ss')<=to_date(substr('"
				+ enddate + "',0,10),'yyyy-mm-dd hh24:mi:ss') ");
		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append(" and pk_dept in (select dept.pk_dept from org_dept dept where  dept.def1 is not null and dept.def1<>'~' connect by dept.pk_fatherorg = prior dept.pk_dept start with dept.pk_dept in ('"
					+ pk_dept + "'))");
		}
		sb.append("group by pk_group,gz_info) gzshuju");
		sb.append(" left join hk_srgk_hg_jzfs jzfs ");
		sb.append(" on jzfs.name=gzshuju.gz_info where nvl(jzfs.dr,0)=0 ");
		List<HZGZShuJuVO> list = (List<HZGZShuJuVO>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(HZGZShuJuVO.class));
		Map<String, UFDouble> map = new HashMap<String, UFDouble>();
		if (list != null && list.size() > 0) {
			// ��֯+���˷�ʽpk ʱ����ʱһ�촦��
			for (int i = 0; i < list.size(); i++) {
				HZGZShuJuVO gzshuju = list.get(i);
				String org = gzshuju.getPk_org();
				String jzfspk = gzshuju.getJzfs_pk();
				UFDouble jine = gzshuju.getGz_jine();
				String key = org + jzfspk;
				if (!map.keySet().contains(key)) {
					map.put(key, jine);
				} else {
					map.put(key, map.get(key).add(jine));
				}
			}
		}
		return map;
	}

	private UFDouble getUFDouble_NullToZero(UFDouble uf) {
		if (uf == null) {
			return UFDouble.ZERO_DBL;
		} else {
			return uf;
		}
	}

	// ���󱨸������
	@SuppressWarnings("unchecked")
	private RsbaogaoCVO_YY[] getRsTzInfo(String first_org, String pk_org,
			String pk_dept, String begindate, String enddate)
			throws DAOException {
		StringBuffer sb = new StringBuffer();

		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append("select '" + begindate.substring(0, 10)
					+ " 00:00:00' dbilldate, '" + first_org
					+ "' pk_org, rs.pk_org_v, rs.pk_group,");
			sb.append("rstz.tz_km_jzfs_1,rstz.tz_km_srxm_1,rstz.tz_km_data_1,rstz.tz_km_jzfs_2,rstz.tz_km_srxm_type1,rstz.tz_km_srxm_type2,");
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

			sb.append("select '" + begindate.substring(0, 10)
					+ " 00:00:00' dbilldate, '" + first_org
					+ "' pk_org, rs.pk_org_v, rs.pk_group,");
			sb.append("rstz.tz_km_jzfs_1,rstz.tz_km_srxm_1,rstz.tz_km_data_1,rstz.tz_km_jzfs_2,rstz.tz_km_srxm_type1,rstz.tz_km_srxm_type2,");
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
		List<RsbaogaoCVO_YY> list = (List<RsbaogaoCVO_YY>) getBD()
				.executeQuery(sb.toString(),
						new BeanListProcessor(RsbaogaoCVO_YY.class));
		return list.toArray(new RsbaogaoCVO_YY[] {});
	}

	// ��ѯ��������
	@SuppressWarnings("unchecked")
	private CctzVO_YY[] getCctzInfo(String first_org, String pk_org,
			String pk_dept, String begindate, String enddate)
			throws DAOException {

		StringBuffer sb = new StringBuffer();
		if (pk_dept != null && !"".equals(pk_dept)) {
			sb.append("select cctzh.pk_group,'" + begindate.substring(0, 10)
					+ " 00:00:00' tz_date,'" + first_org
					+ "' pk_org,cctzb.tz_km_jzfs_1,");
			sb.append("cctzb.tz_km_srxm_1,cctzb.tz_km_data_1,cctzb.tz_km_jzfs_2,cctzb.tz_km_srxm_2,cctzb.tz_km_srxm_type1,cctzb.tz_km_srxm_type2,");
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
			sb.append("select cctzh.pk_group,'" + begindate.substring(0, 10)
					+ " 00:00:00' tz_date,'" + first_org
					+ "' pk_org,cctzb.tz_km_jzfs_1,");
			sb.append("cctzb.tz_km_srxm_1,cctzb.tz_km_data_1,cctzb.tz_km_jzfs_2,cctzb.tz_km_srxm_2,cctzb.tz_km_srxm_type1,cctzb.tz_km_srxm_type2,");
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
		List<CctzVO_YY> list = (List<CctzVO_YY>) getBD().executeQuery(
				sb.toString(), new BeanListProcessor(CctzVO_YY.class));
		return list.toArray(new CctzVO_YY[] {});
	}

	// ��ȡ��֯�汾
	private String getOrg_v(String org) throws DAOException {
		String sql = "select distinct pk_vid from org_orgs where pk_org='"
				+ org + "' and nvl(dr,0)=0";
		String pk_org_v = (String) getBD().executeQuery(sql,
				new ColumnProcessor());
		return pk_org_v;
	}

	// ���Ӫҵ�ձ������У�ȷ�����룩�Ĳ�ѯ,ֻ���һ����֯�Ĳ�ѯ������
	@SuppressWarnings("unchecked")
	@Override
	public YyrbDeptInfoVO[] getYyrbDeptYingShou(String pk_org,
			String begindate, String enddate) throws DAOException {
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
		StringBuffer sb = new StringBuffer();

		// ��sql���� �����������󱨸棬�ֹ�������Ϣ
		sb.append("select * from (");
		sb.append("select decode(hzsjinfo2.pk_org,null,sgsjshuju.pk_org,hzsjinfo2.pk_org) pk_org,");
		sb.append("decode(hzsjinfo2.pk_srxm,null,sgsjshuju.srxm,hzsjinfo2.pk_srxm) pk_srxm,");
		sb.append("decode(hzsjinfo2.pk_group,null,sgsjshuju.pk_group,hzsjinfo2.pk_group) pk_group,");
		sb.append("decode(hzsjinfo2.pk_fsrxm,null,sgsjshuju.pk_parent,hzsjinfo2.pk_fsrxm) pk_fsrxm,");
		sb.append("decode(hzsjinfo2.name,null,sgsjshuju.name,hzsjinfo2.name) name,");
		sb.append("decode(hzsjinfo2.code,null,sgsjshuju.code,hzsjinfo2.code) code,");
		sb.append("decode(hzsjinfo2.hzdate,null,sgsjshuju.hzdate,hzsjinfo2.hzdate) hzdate,");
		sb.append("decode(hzsjinfo2.pk_dept,null,sgsjshuju.bm_pk,hzsjinfo2.pk_dept) pk_dept,");
		sb.append("nvl(hzsjinfo2.yinghsou,0)+nvl(sgsjshuju.srje,0) yinghsou ");
		sb.append(" from (select decode(hzdeptinfo.pk_org,null,decode(rstzinfo.pk_org,null,cctzshuju.pk_org,rstzinfo.pk_org),hzdeptinfo.pk_org) pk_org, ");
		sb.append("decode( hzdeptinfo.pk_srxm,null,decode(rstzinfo.srxm,null,cctzshuju.srxm,rstzinfo.srxm), hzdeptinfo.pk_srxm) pk_srxm, ");
		sb.append("decode(hzdeptinfo.pk_group,null,decode(rstzinfo.pk_group,null,cctzshuju.pk_group,rstzinfo.pk_group), hzdeptinfo.pk_group) pk_group, ");
		sb.append("decode(hzdeptinfo.pk_fxrxm,null,decode(rstzinfo.pk_parent,null,cctzshuju.pk_parent,rstzinfo.pk_parent), hzdeptinfo.pk_fxrxm) pk_fsrxm, ");
		sb.append("decode(hzdeptinfo.name,null,decode(rstzinfo.name,null,cctzshuju.name,rstzinfo.name), hzdeptinfo.name) name, ");
		sb.append("decode(hzdeptinfo.code,null,decode(rstzinfo.code,null,cctzshuju.code,rstzinfo.code), hzdeptinfo.code) code, ");
		sb.append("decode(hzdeptinfo.hzdate,null,decode(rstzinfo.dbilldate,null,cctzshuju.tz_date,rstzinfo.dbilldate), hzdeptinfo.hzdate) hzdate, ");
		sb.append("decode(hzdeptinfo.pk_dept,null,decode(rstzinfo.bm_pk,null,cctzshuju.bm_pk,rstzinfo.bm_pk), hzdeptinfo.pk_dept) pk_dept, ");
		sb.append("nvl(hzdeptinfo.yinghsou, 0) + nvl(rstzinfo.srje, 0) + ");
		sb.append("nvl(cctzshuju.srje, 0)  yinghsou ");
		sb.append("from (select hzshuju.pk_group, ");
		sb.append("'" + first_org + "' pk_org, ");
		sb.append("hzshuju.pk_srxm, ");
		sb.append("'" + begindate.substring(0, 10) + " 00:00:00' hzdate, ");
		sb.append("srxm.pk_parent pk_fxrxm, ");
		sb.append("dept.pk_dept, ");
		sb.append("dept.name, ");
		sb.append("dept.code, ");
		sb.append("sum(hzshuju.qrsr) yinghsou ");
		sb.append("from hk_srgk_hg_hzshuju hzshuju ");
		sb.append("inner join org_dept dept ");
		sb.append("on hzshuju.pk_dept = dept.pk_dept ");
		sb.append("inner join hk_srgk_hg_srxm srxm ");
		sb.append("on srxm.pk_hk_srgk_hg_srxm = hzshuju.pk_srxm ");
		sb.append("where hzshuju.pk_org in (" + orgsql.toString() + ") ");
		sb.append("and to_date(hzshuju.hzdate, 'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date(substr('" + begindate
				+ "', 0, 10), 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(hzshuju.hzdate, 'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date(substr('" + enddate
				+ "', 0, 10), 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and nvl(hzshuju.dr, 0) = 0 ");
		sb.append("group by hzshuju.pk_group, ");
		sb.append("dept.pk_dept, ");
		sb.append("hzshuju.pk_srxm, ");
		sb.append("srxm.pk_parent, ");
		sb.append("dept.name, ");
		sb.append("dept.code) hzdeptinfo ");

		sb.append("full outer  join ");

		sb.append("(select cctzinfo.tz_date, ");
		sb.append("cctzinfo.pk_org, ");
		sb.append("cctzinfo.bm_pk, ");
		sb.append("cctzinfo.srxm, cctzinfo.pk_group,srxm.pk_parent,dept.name,dept.code,");
		sb.append("sum(cctzinfo.srje) srje ");
		sb.append("from (select '" + begindate.substring(0, 10)
				+ " 00:00:00' tz_date, ");
		sb.append("'" + first_org + "' pk_org, cctzh.pk_group,");
		sb.append("cctzb.tz_km_srxm_1 srxm, ");
		sb.append("cctzb.tz_km_data_1 srje, ");
		sb.append("cctzb.bm_pk ");
		sb.append("from hk_srgk_hg_cctz cctzh ");
		sb.append("left join hk_srgk_hg_cctz_b cctzb ");
		sb.append("on cctzh.pk_hk_srgk_hg_cctz = cctzb.pk_hk_srgk_hg_cctz ");
		sb.append("where nvl(cctzh.dr, 0) = 0 ");
		sb.append("and nvl(cctzb.dr, 0) = 0 ");
		sb.append("and cctzh.ibillstatus = 1 and cctzb.tz_km_srxm_type1='sr' ");
		sb.append("and to_date(substr(cctzb.tz_date, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date('" + begindate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(substr(cctzb.tz_date, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date('" + enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and cctzh.pk_org in (" + orgsql.toString() + ") ");
		sb.append("union all ");
		sb.append("select '" + begindate.substring(0, 10)
				+ " 00:00:00' tz_date, ");
		sb.append("'" + first_org + "' pk_org, cctzh.pk_group,");
		sb.append("cctzb.tz_km_srxm_2 srxm, ");
		sb.append("cctzb.tz_km_data_2 srje, ");
		sb.append("cctzb.bm_pk ");
		sb.append("from hk_srgk_hg_cctz cctzh ");
		sb.append("left join hk_srgk_hg_cctz_b cctzb ");
		sb.append("on cctzh.pk_hk_srgk_hg_cctz = cctzb.pk_hk_srgk_hg_cctz ");
		sb.append("where nvl(cctzh.dr, 0) = 0 ");
		sb.append("and nvl(cctzb.dr, 0) = 0 ");
		sb.append("and cctzh.ibillstatus = 1  and cctzb.tz_km_srxm_type2='sr'  ");
		sb.append("and to_date(substr(cctzb.tz_date, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date('" + begindate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(substr(cctzb.tz_date, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date('" + enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and cctzh.pk_org in ("
				+ orgsql.toString()
				+ ")) cctzinfo  left join org_dept dept on cctzinfo.bm_pk = dept.pk_dept left join hk_srgk_hg_srxm srxm on srxm.pk_hk_srgk_hg_srxm = cctzinfo.srxm ");
		sb.append("group by cctzinfo.tz_date, cctzinfo.pk_org, cctzinfo.bm_pk, cctzinfo.pk_group,srxm.pk_parent,dept.name,dept.code, cctzinfo.srxm) cctzshuju ");
		sb.append("on hzdeptinfo.pk_org = cctzshuju.pk_org ");
		sb.append("and cctzshuju.srxm = hzdeptinfo.pk_srxm ");
		sb.append("and cctzshuju.tz_date = hzdeptinfo.hzdate ");
		sb.append("and hzdeptinfo.pk_dept = cctzshuju.bm_pk ");
		sb.append("full outer join (select rsbg.dbilldate, ");
		sb.append("rsbg.pk_org,  rsbg.pk_group,");
		sb.append("rsbg.srxm, ");
		sb.append("rsbg.bm_pk, srxm.pk_parent,dept.name,dept.code, ");
		sb.append("sum(rsbg.srje) srje ");
		sb.append("from (select '" + begindate.substring(0, 10)
				+ " 00:00:00' dbilldate, ");
		sb.append("'" + first_org + "' pk_org, rs.pk_group,");
		sb.append("rstz.bm_pk, ");
		sb.append("rstz.tz_km_srxm_1 srxm, ");
		sb.append("rstz.tz_km_data_1 srje ");
		sb.append("from hk_srgk_hg_rsbaogao_c rstz ");
		sb.append("left join hk_srgk_hg_rsbaogao rs ");
		sb.append("on rstz.pk_hk_srgk_hg_rsbaogao = ");
		sb.append("rs.pk_hk_srgk_hg_rsbaogao ");
		sb.append("where rs.ibillstatus = 1  and rstz.tz_km_srxm_type1='sr'  ");
		sb.append("and to_date(substr(rs.dbilldate, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date('" + begindate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(substr(rs.dbilldate, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date('" + enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and nvl(rs.dr, 0) = 0 ");
		sb.append("and nvl(rstz.dr, 0) = 0 ");
		sb.append("and rs.pk_org in (" + orgsql.toString() + ") ");
		sb.append("union all ");
		sb.append("select '" + begindate.substring(0, 10)
				+ " 00:00:00' dbilldate, ");
		sb.append("'" + first_org + "' pk_org, rs.pk_group,");
		sb.append("rstz.bm_pk, ");
		sb.append("rstz.tz_km_srxm_2 srxm, ");
		sb.append("rstz.tz_km_data_2 srje ");
		sb.append("from hk_srgk_hg_rsbaogao_c rstz ");
		sb.append("left join hk_srgk_hg_rsbaogao rs ");
		sb.append("on rstz.pk_hk_srgk_hg_rsbaogao = ");
		sb.append("rs.pk_hk_srgk_hg_rsbaogao ");
		sb.append("where rs.ibillstatus = 1  and rstz.tz_km_srxm_type2='sr' ");
		sb.append("and to_date(substr(rs.dbilldate, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date('" + begindate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(substr(rs.dbilldate, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date('" + enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and nvl(rs.dr, 0) = 0 ");
		sb.append("and nvl(rstz.dr, 0) = 0 ");
		sb.append("and rs.pk_org in ("
				+ orgsql.toString()
				+ ")) rsbg left join org_dept dept on rsbg.bm_pk = dept.pk_dept left join hk_srgk_hg_srxm srxm on srxm.pk_hk_srgk_hg_srxm = rsbg.srxm ");
		sb.append("group by rsbg.dbilldate, rsbg.pk_org, rsbg.srxm, rsbg.bm_pk,srxm.pk_parent,rsbg.pk_group,dept.name,dept.code) rstzinfo ");

		sb.append("on hzdeptinfo.pk_org = rstzinfo.pk_org ");
		sb.append("and rstzinfo.srxm = hzdeptinfo.pk_srxm ");
		sb.append("and rstzinfo.dbilldate = hzdeptinfo.hzdate ");
		sb.append("and hzdeptinfo.pk_dept = rstzinfo.bm_pk ) hzsjinfo2 ");

		sb.append("full outer join (select sgsjinfo.pk_org, ");
		sb.append("sgsjinfo.hzdate,  sgsjinfo.pk_group,");
		sb.append("sgsjinfo.bm_pk, srxm.pk_parent,dept.name,dept.code,");
		sb.append("sgsjinfo.srxm, ");
		sb.append("sum(sgsjinfo.srje) srje ");
		sb.append("from (select '" + first_org + "' pk_org, ");
		sb.append("'" + begindate.substring(0, 10) + " 00:00:00' hzdate, ");
		sb.append("sgsjb.bm_pk, sgsj.pk_group,");
		sb.append("sgsjb.tz_km_srxm_1 srxm, ");
		sb.append("sgsjb.tz_km_data_1 srje ");
		sb.append("from hk_srgk_hg_sgshuju sgsj ");
		sb.append("left join hk_srgk_hg_sgshuju_b sgsjb ");
		sb.append("on sgsj.pk_hk_srgk_hg_sgshuju = ");
		sb.append("sgsjb.pk_hk_srgk_hg_sgshuju ");
		sb.append("where nvl(sgsj.dr, 0) = 0 ");
		sb.append("and nvl(sgsjb.dr, 0) = 0 ");
		sb.append("and nvl(replace(sgsj.vdef10,'~',''),'N')='N' ");	// ��ݲ�ȡ �Ƿ�Ƶ�=��  ������ ��HK 2019��5��25��16:57:54��
		sb.append("and sgsj.ibillstatus = 1  and sgsjb.tz_km_srxm_type1='sr'  ");
		sb.append("and to_date(substr(sgsj.dbilldate, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date('" + begindate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(substr(sgsj.dbilldate, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date('" + enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and sgsj.pk_org in (" + orgsql.toString() + ") ");

		sb.append("union all ");
		sb.append("select '" + first_org + "' pk_org, ");
		sb.append("'" + begindate.substring(0, 10) + " 00:00:00' hzdate, ");
		sb.append("sgsjb.bm_pk,  sgsj.pk_group,");
		sb.append("sgsjb.tz_km_srxm_2 srxm, ");
		sb.append("sgsjb.tz_km_data_2 srje ");
		sb.append("from hk_srgk_hg_sgshuju sgsj ");
		sb.append("left join hk_srgk_hg_sgshuju_b sgsjb ");
		sb.append("on sgsj.pk_hk_srgk_hg_sgshuju = ");
		sb.append("sgsjb.pk_hk_srgk_hg_sgshuju ");
		sb.append("where nvl(sgsj.dr, 0) = 0 ");
		sb.append("and nvl(sgsjb.dr, 0) = 0 ");
		sb.append("and nvl(replace(sgsj.vdef10,'~',''),'N')='N' ");	// ��ݲ�ȡ �Ƿ�Ƶ�=��  ������ ��HK 2019��5��25��16:57:54��
		sb.append("and sgsj.ibillstatus = 1  and sgsjb.tz_km_srxm_type2='sr'  ");
		sb.append("and to_date(substr(sgsj.dbilldate, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') >= ");
		sb.append("to_date('" + begindate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and to_date(substr(sgsj.dbilldate, 0, 10), ");
		sb.append("'yyyy-mm-dd hh24:mi:ss') <= ");
		sb.append("to_date('" + enddate + "', 'yyyy-mm-dd hh24:mi:ss') ");
		sb.append("and sgsj.pk_org in ("
				+ orgsql.toString()
				+ ")) sgsjinfo left join org_dept dept on sgsjinfo.bm_pk = dept.pk_dept ");
		sb.append(" left join hk_srgk_hg_srxm srxm on srxm.pk_hk_srgk_hg_srxm = sgsjinfo.srxm group by sgsjinfo.pk_org, ");
		sb.append("sgsjinfo.hzdate, sgsjinfo.pk_group,srxm.pk_parent,dept.name,dept.code,");
		sb.append("sgsjinfo.bm_pk, ");
		sb.append("sgsjinfo.srxm) sgsjshuju ");

		sb.append("on hzsjinfo2.pk_org = sgsjshuju.pk_org ");
		sb.append("and sgsjshuju.srxm = hzsjinfo2.pk_srxm ");
		sb.append("and sgsjshuju.hzdate = hzsjinfo2.hzdate ");
		sb.append("and hzsjinfo2.pk_dept = sgsjshuju.bm_pk) hzsjinfo3 ");

		sb.append("order by hzsjinfo3.code ");

		List<YyrbDeptInfoVO> list = (List<YyrbDeptInfoVO>) getBD()
				.executeQuery(sb.toString(),
						new BeanListProcessor(YyrbDeptInfoVO.class));
		return list.toArray(new YyrbDeptInfoVO[] {});
	}

	/**
	 * �����˵�û�е��ֹ����ݣ������������󱨸�ά����������Ŀ
	 * */
	private void handlerAddSrxm(List<HZShuJuVO> hzsjvo, SgsjInfoVO[] sgsjvos,
			CctzVO_YY[] cctzs, RsbaogaoCVO_YY[] rsbgs) {
		Set<String> set_hzsj = new HashSet<String>();
		String pk_org_v = "";
		if (hzsjvo != null && hzsjvo.size() > 0) {
			pk_org_v = hzsjvo.get(0).getPk_org_v();
			for (int i = 0; i < hzsjvo.size(); i++) {
				HZShuJuVO hzsj = hzsjvo.get(i);
				String pk_dept = hzsj.getPk_dept();
				String pk_srxm = hzsj.getPk_srxm();
				set_hzsj.add(pk_dept + pk_srxm);
			}
		}
		Map<String, HZShuJuVO> map_sgsj = new HashMap<String, HZShuJuVO>();
		if (sgsjvos != null && sgsjvos.length > 0) {
			// �����ֹ�����
			for (int i = 0; i < sgsjvos.length; i++) {
				SgsjInfoVO sgsjInfoVO = sgsjvos[i];
				String pk_dept = sgsjInfoVO.getPk_dept();
				String srxm_fpk1 = sgsjInfoVO.getSrxm_fpk1();
				String srxm_fpk2 = sgsjInfoVO.getSrxm_fpk2();
				String dept_fpk = sgsjInfoVO.getDept_fpk();
				String hzdate = sgsjInfoVO.getHzdate();
				String pk_group = sgsjInfoVO.getPk_group();
				String pk_org = sgsjInfoVO.getPk_org();
				String srxm1 = sgsjInfoVO.getTz_km_srxm_1();
				String srxm2 = sgsjInfoVO.getTz_km_srxm_2();
				String key1 = pk_dept + srxm1;
				String key2 = pk_dept + srxm2;
				if (map_sgsj.containsKey(key1)) {
					continue;
				}
				if (map_sgsj.containsKey(key2)) {
					continue;
				}
				if (srxm1 != null && !"".equals(srxm1)) {
					if (!map_sgsj.containsKey(key1)) {
						HZShuJuVO hzsj = new HZShuJuVO();
						hzsj.setPk_dept(pk_dept);
						hzsj.setPk_fdept(dept_fpk);
						hzsj.setPk_group(pk_group);
						hzsj.setPk_org(pk_org);
						hzsj.setPk_org_v(pk_org_v);
						hzsj.setPk_srxm(srxm1);
						hzsj.setSrxm_fpk(srxm_fpk1);
						hzsj.setHzdate(new UFDate(hzdate));
						setHZShujuInfo(hzsj);
						map_sgsj.put(key1, hzsj);
					}
				}

				if (srxm2 != null && !"".equals(srxm2)) {
					if (!map_sgsj.containsKey(key2)) {
						HZShuJuVO hzsj = new HZShuJuVO();
						hzsj.setPk_srxm(srxm2);
						hzsj.setSrxm_fpk(srxm_fpk2);
						hzsj.setPk_dept(pk_dept);
						hzsj.setPk_fdept(dept_fpk);
						hzsj.setPk_group(pk_group);
						hzsj.setPk_org(pk_org);
						hzsj.setPk_org_v(pk_org_v);
						hzsj.setHzdate(new UFDate(hzdate));
						setHZShujuInfo(hzsj);
						map_sgsj.put(key2, hzsj);
					}
				}
			}
			// �����������
			if (cctzs != null && cctzs.length > 0) {
				for (int i = 0; i < cctzs.length; i++) {
					CctzVO_YY cctz = cctzs[i];
					String pk_dept = cctz.getBm_pk();
					String dept_fpk = cctz.getPk_fdept();
					String hzdate = cctz.getTz_date().toString();
					String pk_group = cctz.getPk_group();
					String pk_org = cctz.getPk_org();
					String srxm1 = cctz.getTz_km_srxm_1();
					String srxm2 = cctz.getTz_km_srxm_2();
					String key1 = pk_dept + srxm1;
					String key2 = pk_dept + srxm2;
					if (map_sgsj.containsKey(key1)) {
						continue;
					}
					if (map_sgsj.containsKey(key2)) {
						continue;
					}
					if (srxm1 != null && !"".equals(srxm1)) {
						if (!map_sgsj.containsKey(key1)) {
							HZShuJuVO hzsj = new HZShuJuVO();
							hzsj.setPk_dept(pk_dept);
							hzsj.setPk_fdept(dept_fpk);
							hzsj.setPk_group(pk_group);
							hzsj.setPk_org(pk_org);
							hzsj.setPk_org_v(pk_org_v);
							hzsj.setPk_srxm(srxm1);
							// hzsj.setSrxm_fpk(srxm_fpk1);
							hzsj.setHzdate(new UFDate(hzdate));
							setHZShujuInfo(hzsj);
							map_sgsj.put(key1, hzsj);
						}
					}

					if (srxm2 != null && !"".equals(srxm2)) {
						if (!map_sgsj.containsKey(key2)) {
							HZShuJuVO hzsj = new HZShuJuVO();
							hzsj.setPk_srxm(srxm2);
							// hzsj.setSrxm_fpk(srxm_fpk2);
							hzsj.setPk_dept(pk_dept);
							hzsj.setPk_fdept(dept_fpk);
							hzsj.setPk_group(pk_group);
							hzsj.setPk_org(pk_org);
							hzsj.setPk_org_v(pk_org_v);
							hzsj.setHzdate(new UFDate(hzdate));
							setHZShujuInfo(hzsj);
							map_sgsj.put(key2, hzsj);
						}
					}
				}
			}
			// �������󱨸�
			if (rsbgs != null && rsbgs.length > 0) {
				for (int i = 0; i < rsbgs.length; i++) {

					RsbaogaoCVO_YY rsbg = rsbgs[i];
					String pk_dept = rsbg.getBm_pk();
					String dept_fpk = rsbg.getPk_fdept();
					String hzdate = rsbg.getDbilldate();
					String pk_group = rsbg.getPk_group();
					String pk_org = rsbg.getPk_org();
					String srxm1 = rsbg.getTz_km_srxm_1();
					String srxm2 = rsbg.getTz_km_srxm_2();
					String key1 = pk_dept + srxm1;
					String key2 = pk_dept + srxm2;
					if (map_sgsj.containsKey(key1)) {
						continue;
					}
					if (map_sgsj.containsKey(key2)) {
						continue;
					}
					if (srxm1 != null && !"".equals(srxm1)) {
						if (!map_sgsj.containsKey(key1)) {
							HZShuJuVO hzsj = new HZShuJuVO();
							hzsj.setPk_dept(pk_dept);
							hzsj.setPk_fdept(dept_fpk);
							hzsj.setPk_group(pk_group);
							hzsj.setPk_org(pk_org);
							hzsj.setPk_org_v(pk_org_v);
							hzsj.setPk_srxm(srxm1);
							// hzsj.setSrxm_fpk(srxm_fpk1);
							hzsj.setHzdate(new UFDate(hzdate));
							setHZShujuInfo(hzsj);
							map_sgsj.put(key1, hzsj);
						}
					}

					if (srxm2 != null && !"".equals(srxm2)) {
						if (!map_sgsj.containsKey(key2)) {
							HZShuJuVO hzsj = new HZShuJuVO();
							hzsj.setPk_srxm(srxm2);
							// hzsj.setSrxm_fpk(srxm_fpk2);
							hzsj.setPk_dept(pk_dept);
							hzsj.setPk_fdept(dept_fpk);
							hzsj.setPk_group(pk_group);
							hzsj.setPk_org(pk_org);
							hzsj.setPk_org_v(pk_org_v);
							hzsj.setHzdate(new UFDate(hzdate));
							setHZShujuInfo(hzsj);
							map_sgsj.put(key2, hzsj);
						}
					}
				}
			}
		}
		map_sgsj.keySet().removeAll(set_hzsj);
		if (map_sgsj.size() > 0) {
			Set<String> set = map_sgsj.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String key = it.next();
				HZShuJuVO hzsj = map_sgsj.get(key);
				hzsjvo.add(hzsj);
			}
		}
	}

	private void setHZShujuInfo(HZShuJuVO hzsj) {
		hzsj.setYingshou(UFDouble.ZERO_DBL);
		hzsj.setZdyh(UFDouble.ZERO_DBL);
		hzsj.setSgyh(UFDouble.ZERO_DBL);
		hzsj.setShishou(UFDouble.ZERO_DBL);
		hzsj.setKblyh(UFDouble.ZERO_DBL);
		hzsj.setQrsr(UFDouble.ZERO_DBL);
		hzsj.setXianjin(UFDouble.ZERO_DBL);
		hzsj.setPos(UFDouble.ZERO_DBL);
		hzsj.setHuiyuanka(UFDouble.ZERO_DBL);
		hzsj.setFenqu(UFDouble.ZERO_DBL);
		hzsj.setMiandan(UFDouble.ZERO_DBL);
		hzsj.setDaijinquan(UFDouble.ZERO_DBL);
		hzsj.setCika(UFDouble.ZERO_DBL);
		hzsj.setQtyh(UFDouble.ZERO_DBL);
		hzsj.setHuiyuankashiji(UFDouble.ZERO_DBL);
	}

	@Override
	public SrdibiaoBillVO insert(SrdibiaoBillVO srdb) throws BusinessException {
		SrdibiaoHVO hvo = srdb.getParentVO();
		String billno = NCLocator
				.getInstance()
				.lookup(IBillcodeManage.class)
				.getBillCode_RequiresNew("HK03", hvo.getPk_group(),
						hvo.getPk_org(), hvo);
		hvo.setVbillcode(billno);
		hvo.setAttributeValue("dr", 0);
		String pk_h = getBD().insertVO(hvo);
		SrdibiaoBVO[] srdbbvos = (SrdibiaoBVO[]) srdb.getChildrenVO();
		for (int i = 0; i < srdbbvos.length; i++) {
			SrdibiaoBVO srdibiaoBVO = srdbbvos[i];
			srdibiaoBVO.setPk_hk_srgk_hg_srdibiao(pk_h);
			srdibiaoBVO.setAttributeValue("dr", 0);
		}
		getBD().insertVOArray(srdbbvos);
		SrdibiaoBillVO[] srdbvos =queryAggSrdbVOFullData(new String[]{pk_h});
		return srdbvos[0];
	}

	@Override
	public YyribaoBillVO insert(YyribaoBillVO srdb) throws Exception {
		YyribaoHVO hvo = srdb.getParentVO();
		String billno = NCLocator
				.getInstance()
				.lookup(IBillcodeManage.class)
				.getBillCode_RequiresNew("HK06", hvo.getPk_group(),
						hvo.getPk_org(), hvo);
		hvo.setVbillcode(billno);
		hvo.setAttributeValue("dr", 0);
		String pk_h = getBD().insertVO(hvo);
		YyribaoBVO[] srdbbvos = (YyribaoBVO[]) srdb.getChildrenVO();
		for (int i = 0; i < srdbbvos.length; i++) {
			YyribaoBVO srdibiaoBVO = srdbbvos[i];
			srdibiaoBVO.setPk_hk_srgk_hg_yyribao(pk_h);
			srdibiaoBVO.setAttributeValue("dr", 0);
		}
		getBD().insertVOArray(srdbbvos);
		YyribaoBillVO[] billvos = queryAggYyrbVOFullData(new String[]{pk_h});
		return billvos[0];
	}

	protected SrdibiaoBillVO[] queryAggSrdbVOFullData(String[] pk_cards)
			throws BusinessException {

		Collection<SrdibiaoBillVO> datas = getMDQueryService()
				.queryBillOfVOByPKs(SrdibiaoBillVO.class, pk_cards, false);
		if (datas == null || datas.size() == 0) {
			return null;
		}

		return datas.toArray(new SrdibiaoBillVO[] {});
	}

	protected YyribaoBillVO[] queryAggYyrbVOFullData(String[] pk_cards)
			throws BusinessException {

		Collection<YyribaoBillVO> datas = getMDQueryService()
				.queryBillOfVOByPKs(YyribaoBillVO.class, pk_cards, false);
		if (datas == null || datas.size() == 0) {
			return null;
		}

		return datas.toArray(new YyribaoBillVO[] {});
	}
	private IMDPersistenceQueryService getMDQueryService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}
}
