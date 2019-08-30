package nc.bs.hkjt.huiyuan.cikainfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHy_cikainfoSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public CikainfoBillVO[] sendApprove(CikainfoBillVO[] clientBills,
			CikainfoBillVO[] originBills)throws BusinessException {
		for (CikainfoBillVO clientFullVO : clientBills) {
			
			/**
			 *  提交前 校验
			 */
			CikainfoBVO[] ckinfoBVOs = (CikainfoBVO[])clientFullVO.getChildrenVO();
			for( int b_index=0;b_index<ckinfoBVOs.length;b_index++ )
			{
				CikainfoBVO bvo = ckinfoBVOs[b_index];
//				int rowno = bvo.getVrowno();
				String ka_pk = bvo.getKa_pk();
				UFDouble kabili = bvo.getKabili();
				UFDouble dj = bvo.getDanjia();
				UFDouble je = bvo.getJine();
				
				if( PuPubVO.getString_TrimZeroLenAsNull(ka_pk)==null )
				{// 卡pk不能等于空
					throw new BusinessException("第"+(b_index+1)+"行，会员卡不存在与NC系统中，请检查！");
				}
				
				if( kabili==null )
				{// 卡比例 不能为空
					throw new BusinessException("第"+(b_index+1)+"行，卡比例不能为空，请检查！");
				}
				
				if( dj==null )
				{// 单价 不能为空
					throw new BusinessException("第"+(b_index+1)+"行，单价不能为空，请检查！");
				}
				
//				if( je==null )
//				{// 金额 不能为空
//					throw new BusinessException("第"+rowno+"行，金额不能为空，请检查！");
//				}
				
			}
			
			
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		CikainfoBillVO[] returnVos = new BillUpdate<CikainfoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
