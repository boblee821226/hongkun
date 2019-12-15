package nc.itf.hkjt;

import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.BusinessException;


/**
 * @author zhangjc
 *账单表体数据计算接口
 */
public interface IHg_zhangdanDataCompute {
	public ZhangdanBillVO[] computeBodyDate(ZhangdanBillVO[] billvos,String pk_org)throws BusinessException;
}
