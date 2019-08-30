package nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoHVO;

/**
 * 标准单据审核的BP
 */
public class AceHg_rsbaogaoApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public RsbaogaoBillVO[] approve(RsbaogaoBillVO[] clientBills,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		for (RsbaogaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			
			/**
			 * 2016年9月10日19:12:30
			 * 首先要判断： 发票有没有 全部 审核通过
			 * 发票 包含两部分： 1、账单    2、会员
			 */
			RsbaogaoHVO hVO = clientBill.getParentVO();
			String ywDate = hVO.getDbilldate().toString().substring(0, 10);	// 业务日期
			String pk_org = hVO.getPk_org();	// pk_org
			BaseDAO dao = new BaseDAO();
			/**
			 * 1、查询  账单开票
			 */
			{
				StringBuffer querySQL_1 = 
					new StringBuffer("select ")
							.append(" fp.pk_hk_fapiao_bill ")
							.append(" from hk_fapiao_bill fp ")
							.append(" where fp.dr=0 ")
							.append(" and fp.ibillstatus != 1 ")	// 不为 审核状态的
							.append(" and substr(fp.dbilldate,1,10) = '"+ywDate+"' ")	// 业务日期
							.append(" and fp.pk_org = '"+pk_org+"' ")	// pk_org
				;
				ArrayList list_1 = (ArrayList)dao.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
				if(list_1!=null && list_1.size()>0)
				{
					throw new BusinessException("账单开票，还未全部审核，所以不能审核日审报告。");
				}
			}
			/**END*/
			
			/**
			 * 2、查询  会员开票
			 */
			{
				StringBuffer querySQL_2 = 
					new StringBuffer("select ")
							.append(" kp.pk_hk_huiyuan_kaipiaoinfo ")
							.append(" from hk_huiyuan_kaipiaoinfo kp ")
							.append(" where kp.dr=0 ")
							.append(" and kp.ibillstatus != 1 ")	// 不为 审核状态的
							.append(" and substr(kp.dbilldate,1,10) = '"+ywDate+"' ")	// 业务日期
							.append(" and kp.pk_org = '"+pk_org+"' ")	// pk_org
				;
				ArrayList list_2 = (ArrayList)dao.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				if(list_2!=null && list_2.size()>0)
				{
					throw new BusinessException("会员开票，还未全部审核，所以不能审核日审报告。");
				}
			}
			/**END*/
			
		}
		BillUpdate<RsbaogaoBillVO> update = new BillUpdate<RsbaogaoBillVO>();
		RsbaogaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
