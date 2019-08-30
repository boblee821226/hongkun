package nc.api.hkjt.itf;

import nc.api.hkjt.vo.LoginVO;
import nc.vo.pub.BusinessException;

/**
 * API 业务处理接口
 */
public interface ApiBusinessItf {
	
	public Object doBusiness(
		String account,		// 账套
		String userid,		// 用户
		String billtype,	// 类型
		String action,		// 动作
		Object data,		// 数据
		String token,		// 令牌
		LoginVO loginVO,	// 登录用户VO
		Object other		// 其它（备用）
	)throws BusinessException; 
	
}
