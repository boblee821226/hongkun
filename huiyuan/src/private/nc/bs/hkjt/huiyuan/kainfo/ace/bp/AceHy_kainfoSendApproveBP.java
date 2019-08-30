package nc.bs.hkjt.huiyuan.kainfo.ace.bp;

import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHy_kainfoSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public KainfoBillVO[] sendApprove(KainfoBillVO[] clientBills,
			KainfoBillVO[] originBills)throws BusinessException {
		for (KainfoBillVO clientFullVO : clientBills) {
			
			/**
			 *  提交前 校验
			 */
			KainfoBVO[] kainfoBVOs = (KainfoBVO[])clientFullVO.getChildrenVO();
			HashMap<String,String> xmka_MAP = new HashMap<String,String>();	// 用来记录本张单据的要激活的休眠卡号，不得重复
			for( int b_index=0;b_index<kainfoBVOs.length;b_index++ )
			{
				KainfoBVO bvo = kainfoBVOs[b_index];
				int rowno = bvo.getVrowno();
				// 卡
				String ka_code = bvo.getKa_code();		
				String ka_pk = bvo.getKa_pk();
				// 源卡
				String y_ka_code = bvo.getY_ka_code();	
				String y_ka_pk = bvo.getY_ka_pk();
				// 休眠卡
				String xmka_code = PuPubVO.getString_TrimZeroLenAsNull(bvo.getXmka_code());	
				String xmka_pk = bvo.getXmka_pk();
				
				if( PuPubVO.getString_TrimZeroLenAsNull(ka_pk)==null )
				{// 卡pk不能等于空
					throw new BusinessException("第"+rowno+"行，会员卡不存在与NC系统中，请检查！");
				}
				if( PuPubVO.getString_TrimZeroLenAsNull(y_ka_code)!=null 
				 && PuPubVO.getString_TrimZeroLenAsNull(y_ka_pk)==null 
				  )
				{// 如果 源卡号不为空  则  源卡pk也不能为空
					throw new BusinessException("第"+rowno+"行，源卡不存在与NC系统中，请检查！");
				}
				if( PuPubVO.getString_TrimZeroLenAsNull(xmka_code)!=null 
				 && PuPubVO.getString_TrimZeroLenAsNull(xmka_pk)==null )
				{// 如果 休眠卡号不为空  则  休眠卡pk也不能为空
					throw new BusinessException("第"+rowno+"行，休眠卡不存在与NC系统中，请检查！");
				}
				
				if( "0103001500".equals(y_ka_code)
				 && xmka_code==null
				)
				{// 如果是  休眠大卡转出的，休眠卡号 必填
					throw new BusinessException("第"+rowno+"行，休眠卡号必须填写，请检查！");
				}
				
				if( xmka_code != null )
				{// 如果休眠卡 不为空，则要判断 在本张单据的唯一性
					if( xmka_MAP.containsKey(xmka_code) )
					{
						throw new BusinessException("第"+rowno+"行，休眠卡号重复【"+xmka_code+"】，请检查！");
					}
					else
					{
						xmka_MAP.put(xmka_code, xmka_pk);
					}
				}
				
			}
			/**END*/
			
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		KainfoBillVO[] returnVos = new BillUpdate<KainfoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
