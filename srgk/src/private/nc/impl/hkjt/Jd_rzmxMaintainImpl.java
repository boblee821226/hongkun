package nc.impl.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pub.ace.AceJd_rzmxPubServiceImpl;
import nc.itf.hkjt.IJd_rzmxMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;

public class Jd_rzmxMaintainImpl extends AceJd_rzmxPubServiceImpl
		implements IJd_rzmxMaintain {

	@Override
	public void delete(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] insert(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] update(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public RzmxBillVO[] save(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] unsave(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] approve(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] unapprove(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public Object chaifen(RzmxBillVO[] billVOs)
			throws BusinessException {
		
		BaseDAO dao = new BaseDAO();
		
		String _0309 = "88888";	// 0309的虚拟房号
		String _0309_org = "0001N510000000001SY5";	// 0309的pk_org
		String _0309_org_v = "0001N510000000001SY4";
		String _pk_group = "0001N510000000000EGY";
		HashMap<String, String> _0309_room = new HashMap<>();
		_0309_room.put(_0309, _0309);
		// 查询出 0309的房间号
		StringBuffer querySQL =
		new StringBuffer("select room_no ")
			.append(" from hk_srgk_jd_rzmx_room ")
			.append(" where pk_org = '").append(_0309_org).append("' ")
		;
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				Object[] row = (Object[])obj;
				String room = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
				_0309_room.put(room, room);
			}
		}
		/**
		 * select 
			 s.name
			,s.pk_hk_srgk_hg_spfl
			,s.pk_dept
			,dept.pk_fatherorg
			from hk_srgk_hg_spfl s
			left join org_dept dept on s.pk_dept = dept.pk_dept
			where s.pk_org = '0001N510000000001SY5'
		 */
		// 获取 0309的商品分类档案  key：商品分类名称  value：[nc商品分类、nc部门、nc上级部门]
		HashMap<String, String[]> SPFL_DOC = new HashMap<>();
		StringBuffer querySQL_spfl = 
		new StringBuffer("select ")
			.append(" s.name ")					// 0、商品分类名称
			.append(",s.pk_hk_srgk_hg_spfl ")	// 1、nc商品分类
			.append(",s.pk_dept ")				// 2、nc部门
			.append(",dept.pk_fatherorg ")		// 3、nc上级部门（如果没有上级，就赋值为本级）
			.append(" from hk_srgk_hg_spfl s ")
			.append(" left join org_dept dept on s.pk_dept = dept.pk_dept ")
			.append(" where s.dr = 0 ")
			.append(" and s.pk_org = '").append(_0309_org).append("' ")
		;
		ArrayList list_spfl = (ArrayList)dao.executeQuery(querySQL_spfl.toString(), new ArrayListProcessor());
		if (list_spfl != null && list_spfl.size() > 0) {
			for (Object obj : list_spfl) {
				Object[] row = (Object[])obj;
				String spflName = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
				String pk_spfl = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
				String pk_dept = PuPubVO.getString_TrimZeroLenAsNull(row[2]);
				String pk_dept_p = PuPubVO.getString_TrimZeroLenAsNull(row[3]);
				SPFL_DOC.put(spflName, new String[]{
						pk_spfl,
						pk_dept,
						pk_dept_p
				});
			}
		}
		
		/**
		 * 根据所传的单据，循环处理
		 */
		for (RzmxBillVO billVO : billVOs) {
			ArrayList<RzmxBVO> bVOList = new ArrayList<>();
			
			RzmxHVO hVO = billVO.getParentVO();
			RzmxBVO[] bVOs = (RzmxBVO[])billVO.getChildrenVO();
			// 循环表体，找出 需要转移的数据VO
			for (RzmxBVO bVO : bVOs) {
				String rmno = bVO.getRmno();
//				String bId = bVO.getPk_hk_srgk_jd_rzmx_b();
				if (rmno != null && _0309_room.containsKey(rmno)) {
					bVOList.add(bVO);
				}
			}
			
			if (bVOList.size() == 0) {continue;}
			
			String userId = InvocationInfoProxy.getInstance().getUserId();
			UFDateTime now = new UFDateTime();
			
			// yyyy-mm-dd
			String dBillDate = hVO.getDbilldate().toString().substring(0, 10);
			String _0308_billId = hVO.getPk_hk_srgk_jd_rzmx();
			String _0309_billId = null; 
			// 查找 0309 有没有单据
			StringBuffer querySQL_2 = 
			new StringBuffer("select pk_hk_srgk_jd_rzmx ")
			.append(" from hk_srgk_jd_rzmx ")
			.append(" where dr = 0 ")
			.append(" and vbilltypecode = 'HK11' ")
			.append(" and pk_org = '").append(_0309_org).append("' ")
			.append(" and substr(dbilldate,1,10) = '").append(dBillDate).append("' ")
			;
			ArrayList list_2 = (ArrayList)dao.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
			if (list_2 != null && list_2.size() > 0) {
				_0309_billId = PuPubVO.getString_TrimZeroLenAsNull(((Object[])list_2.get(0))[0]);
			}
			if (_0309_billId == null) {
				// 如果 0309 没有该单据，则 需要 新增
				RzmxHVO _0309_hVO = new RzmxHVO();
				_0309_hVO.setPk_group(_pk_group);
				_0309_hVO.setPk_org(_0309_org);
				_0309_hVO.setPk_org_v(_0309_org_v);
				_0309_hVO.setVbilltypecode("HK11");
				_0309_hVO.setIbillstatus(-1);
				_0309_hVO.setDbilldate(PuPubVO.getUFDate(dBillDate));
				_0309_hVO.setCreator(userId);
				_0309_hVO.setCreationtime(now);
				_0309_hVO.setDirty(false);
				
				RzmxBillVO _0309_billVO = new RzmxBillVO();
				_0309_billVO.setParentVO(_0309_hVO);
				RzmxBillVO[] _0309_billVO_res = this.insert(new RzmxBillVO[]{_0309_billVO}, null);
				if (_0309_billVO_res !=null && _0309_billVO_res.length == 1) {
					_0309_billId = _0309_billVO_res[0].getParentVO().getPk_hk_srgk_jd_rzmx();
				}
			}
			if (_0309_billId != null) {				
				// 1、将数据转移到0309，并且变更档案pk、商品分类、部门、父部门。
				for (RzmxBVO bVO : bVOList) {
					String spfl = bVO.getSpfl_name();
					String[] spfl_info = SPFL_DOC.get(spfl);
					String spflId = null;
					String bmId = null;
					String bmFid = null;
					if (spfl_info != null) {
						spflId = spfl_info[0];
						bmId = spfl_info[1];
						bmFid = spfl_info[2];
					}
					if (bmFid == null || "~".equals(bmFid)) {
						bmFid = bmId; 
					}
					bVO.setPk_hk_srgk_jd_rzmx(_0309_billId);
					bVO.setSpfl_id(spflId);
					bVO.setBm_id(bmId);
					bVO.setBm_fid(bmFid);
					bVO.setDirty(false);
					bVO.setAttributeValue("dr", 0);
				}
				int update_res = dao.updateVOArray(bVOList.toArray(new RzmxBVO[0]));
				
				if (true) {
					// 2、更新 0308 和 0309 表头的合计金额
					StringBuffer updateSQL_3 = 
					new StringBuffer(" update hk_srgk_jd_rzmx h ")
						.append(" set ")
						.append(" h.xfje = (select sum(b.charge) from hk_srgk_jd_rzmx_b b where b.pk_hk_srgk_jd_rzmx = h.pk_hk_srgk_jd_rzmx and b.dr = 0) ")
						.append(",h.jzje = (select sum(b.payment) from hk_srgk_jd_rzmx_b b where b.pk_hk_srgk_jd_rzmx = h.pk_hk_srgk_jd_rzmx and b.dr = 0) ")
						.append(" where h.pk_hk_srgk_jd_rzmx in ('")
						.append(_0308_billId)
						.append("','")
						.append(_0309_billId)
						.append("') ")
					;
					int update_res_3 = dao.executeUpdate(updateSQL_3.toString());
				}
			}
		}
		return "ok";
	}
}
