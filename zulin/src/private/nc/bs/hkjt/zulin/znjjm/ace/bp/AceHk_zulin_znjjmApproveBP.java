package nc.bs.hkjt.zulin.znjjm.ace.bp;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;

/**
 * 标准单据审核的BP
 */
public class AceHk_zulin_znjjmApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public ZnjjmBillVO[] approve(ZnjjmBillVO[] clientBills,
			ZnjjmBillVO[] originBills) throws BusinessException {
		for (ZnjjmBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ZnjjmBillVO> update = new BillUpdate<ZnjjmBillVO>();
		ZnjjmBillVO[] returnVos = update.update(clientBills, originBills);
		
		for(int i=0;i<returnVos.length;i++)
		{// 存在审批流，所以只有当 审核状态为1  才进行推单 
			ZnjjmBillVO billVO = returnVos[i];
			Integer ibillstatus = billVO.getParentVO().getIbillstatus();
			if(ibillstatus==1)
			{
				this.updateZnjjmdJmje(billVO,ibillstatus,null);	// 更新 滞纳金计算单 的 减免金额
			}
		}
		
		return returnVos;
	}
	
	/**
	 * 回写 滞纳金计算单 的 减免金额
	 */
	public void updateZnjjmdJmje(ZnjjmBillVO billVO,Integer ibillstatus,Object other) 
			throws BusinessException{
		
		BaseDAO dao = new BaseDAO();
		
		ZnjjmBVO[] jmBVOs = (ZnjjmBVO[])billVO.getChildrenVO();
		
		HashMap<String,ZnjjmBVO> jmBVO_MAP = new HashMap<String,ZnjjmBVO>();
		
		String where_bid = " ('null'";
		for(int i=0;i<jmBVOs.length;i++){
			ZnjjmBVO jmBVO = jmBVOs[i];
			String bid = jmBVO.getCsourcebillbid();
			where_bid += ",'"+bid+"'";
			jmBVO_MAP.put(bid, jmBVO);
		}
		where_bid += ") ";
		
		String querySQL = 
				" pk_hk_zulin_znjjs_b in " + where_bid
			;
		// 取得 上游 计算单
		ArrayList<ZnjjsBVO> jsBVO_list = (ArrayList<ZnjjsBVO>)dao.retrieveByClause( ZnjjsBVO.class,querySQL );
		
		if(jsBVO_list==null || jsBVO_list.size()<=0){
			if(ibillstatus==1)
				throw new BusinessException("没找到对应的计算单，不能审核");
			else
				throw new BusinessException("没找到对应的计算单，不能弃审");
		}
		
		String pk_znjjs = jsBVO_list.get(0).getPk_hk_zulin_znjjs();
		
		// 修改 上游 计算单 的 减免金额
		for(int i=0;i<jsBVO_list.size();i++)
		{
			ZnjjsBVO jsBVO = jsBVO_list.get(i);
			ZnjjmBVO jmBVO = jmBVO_MAP.get(jsBVO.getPk_hk_zulin_znjjs_b());
			
			if(ibillstatus==1){	// 如果是审批，则回写金额
				jsBVO.setVbdef01( ""+jmBVO.getJm_mny() );
			}else{	// 如果是弃审，则清空
				jsBVO.setVbdef01( null );
			}
		}
		
		// 更新 上游 计算单表体
		dao.updateVOList(jsBVO_list);
		// 更新 上游 计算单表头-时间戳(随便更新一个字段，系统会自动添加时间戳的更新)
		String updateSQL = 
			" update hk_zulin_znjjs js " +
			" set js.vdef20 = js.vdef20 " +
			" where js.pk_hk_zulin_znjjs = '"+pk_znjjs+"' "
		;
		dao.executeUpdate(updateSQL);
	}

}
