package nc.itf.hkjt;

import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.BusinessException;


/**
 * @author zhangjc
 *�˵��������ݼ���ӿ�
 */
public interface IHg_zhangdanDataCompute {
	public ZhangdanBillVO[] computeBodyDate(ZhangdanBillVO[] billvos,String pk_org)throws BusinessException;
}
