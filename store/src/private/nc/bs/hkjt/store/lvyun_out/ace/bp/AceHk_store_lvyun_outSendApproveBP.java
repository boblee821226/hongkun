package nc.bs.hkjt.store.lvyun_out.ace.bp;

import java.util.ArrayList;
import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.hkjt.store.lvyun.out.LvyunBomVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreCVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreDVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHk_store_lvyun_outSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public LyOutStoreBillVO[] sendApprove(LyOutStoreBillVO[] clientBills,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		
		for (LyOutStoreBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
			/**
			 * 2020年7月30日14:52:57
			 * 根据页签二 生成页签三
			 */
			LyOutStoreCVO[] cVOs = (LyOutStoreCVO[])clientFullVO.getChildren(new LyOutStoreCVO().getMetaData());
			LyOutStoreDVO[] dVOs = this.gen_dVO(cVOs);
			clientFullVO.setChildren(new LyOutStoreDVO().getMetaData(), dVOs);
			/***END***/
		}
		// 数据持久化
		LyOutStoreBillVO[] returnVos = new BillUpdate<LyOutStoreBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
	
	/**
	 * 2020年7月30日14:52:57
	 * TODO 根据页签二 生成页签三
	 */
	private LyOutStoreDVO[] gen_dVO(LyOutStoreCVO[] cVOs) throws BusinessException {
		ArrayList<LyOutStoreDVO> res_dVO_list = new ArrayList<>(); // 返回的数据
		ArrayList<String> pk_bom_list = new ArrayList<>();	// 所有的bom
		// 第一次循环
		for (LyOutStoreCVO cVO : cVOs) {
			String pk_bom = PuPubVO.getString_TrimZeroLenAsNull(cVO.getPk_bom());
			if (pk_bom == null) continue;
			// 先获得出所有的pk_bom
			if (!pk_bom_list.contains(pk_bom)) {
				pk_bom_list.add(pk_bom);
			}
		}
		// 查询出bom明细，封装成MAP，备用
		HashMap<String, ArrayList<LvyunBomVO>> bom_map = new HashMap<>();
		{
			StringBuffer querySQL = 
				new StringBuffer("select cbomid, cmaterialvid, vdef3, vdef4 ")
					.append(" from bd_bom_b ")
					.append(" where ")
					.append(" cbomid in ").append(PuPubVO.getSqlInByList(pk_bom_list)).append(" ")
			;
			ArrayList list = (ArrayList)getDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
			if (list != null && !list.isEmpty()) {
				for (Object item :list) {
					Object[] obj = (Object[])item;
					String key = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					String pk_inv = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					UFDouble num = PuPubVO.getUFDouble_NullAsZero(obj[2]);
					UFDouble price = PuPubVO.getUFDouble_NullAsZero(obj[3]);
					if (!bom_map.containsKey(key)) {
						bom_map.put(key, new ArrayList<LvyunBomVO>());
					}
					bom_map.get(key).add(new LvyunBomVO(pk_inv,num,price));
				}
			}
		}
		// 第二次循环，按bom进行处理
		for (LyOutStoreCVO cVO : cVOs) {
			String pk_bom = PuPubVO.getString_TrimZeroLenAsNull(cVO.getPk_bom());
			if (pk_bom == null) continue;
			ArrayList<LvyunBomVO> bom_list = bom_map.get(pk_bom);
			if (bom_list != null && !bom_list.isEmpty()) {
				for (LvyunBomVO bomItem : bom_list) {
					LyOutStoreDVO dVO = new LyOutStoreDVO();
					dVO.setCsourcebillbid(cVO.getPk_hk_store_lvyun_out_c()); // cVO的pk
//					dVO.setCsourcebillid(cVO.getPk_hk_store_lvyun_out()); // hVO的pk（这个没用，但是uap会控制）
					dVO.setPk_hk_store_lvyun_out(cVO.getPk_hk_store_lvyun_out());	// hVO的pk
					dVO.setYyd_code(cVO.getYyd_code());
					dVO.setYyd_name(cVO.getYyd_name());
					dVO.setLy_cp_code(cVO.getLy_cp_code());
					dVO.setLy_cp_name(cVO.getLy_cp_name());
					dVO.setCp_out_quantity(cVO.getCp_out_quantity());
					dVO.setInv_out_quantity( // 材料数量
							PuPubVO.getUFDouble_NullAsZero(dVO.getCp_out_quantity())
						.multiply(PuPubVO.getUFDouble_NullAsZero(bomItem.getNum()))
						.setScale(2, UFDouble.ROUND_HALF_UP)
					);
					dVO.setInv_out_amount( // 材料金额
							PuPubVO.getUFDouble_NullAsZero(dVO.getInv_out_quantity())
						.multiply(PuPubVO.getUFDouble_NullAsZero(bomItem.getPrice()))
						.setScale(2, UFDouble.ROUND_HALF_UP)
					);
					dVO.setPk_inv( // 材料 NC
							bomItem.getPk_inv()
					);
					dVO.setPk_store(cVO.getPk_store());
					dVO.setPk_dept(cVO.getPk_dept());
					dVO.setPk_bom(cVO.getPk_bom());
					
					dVO.setVrowno(""+((res_dVO_list.size()+1)*10));	// 行号
					dVO.setStatus(VOStatus.NEW);	// 新增
					
					res_dVO_list.add(dVO);
				}
			}
		}
		
		return res_dVO_list.toArray(new LyOutStoreDVO[0]);
	}
	
	/**
	 * DAO
	 */
	private BaseDAO DAO = null;
	private BaseDAO getDAO() {
		if (DAO == null) {
			DAO = new BaseDAO();
		}
		return DAO;
	}
}
