package nc.api.hkjt.impl;

import nc.api.hkjt.action.Action_CONTRACT;
import nc.api.hkjt.action.Action_CUSTOMER;
import nc.api.hkjt.action.Action_LOGIN;
import nc.api.hkjt.action.Action_MANAGER;
import nc.api.hkjt.action.Action_PAY;
import nc.api.hkjt.action.Action_PAYABLE;
import nc.api.hkjt.action.Action_ROOM;
import nc.api.hkjt.itf.ApiBusinessItf;
import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.LoginVO;
import nc.vo.pub.BusinessException;

public class ApiBusinessImpl implements ApiBusinessItf {

	@Override
	public Object doBusiness(
			String account,		// 账套
			String userid,		// 用户
			String billtype,	// 类型
			String action,		// 动作
			Object data,		// 数据
			String token,		// 令牌
			LoginVO loginVO,	// 登录用户VO
			Object other		// 其它（备用）
			)
	throws BusinessException 
	{
		
		if( billtype.equals(ApiPubInfo.BILLTYPE_LOGIN) ){			// 登录
			return Action_LOGIN.doAction(action, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_CUSTOMER) ){	// 客户
			return Action_CUSTOMER.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_MANAGER) ){	// 员工
			return Action_MANAGER.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_ROOM) ){		// 房间
			return Action_ROOM.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_CONTRACT) ){	// 合同
			return Action_CONTRACT.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_PAYABLE) ){	// 缴费通知
			return Action_PAYABLE.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_PAY) ){		// 缴费记录
			return Action_PAY.doAction(action, loginVO, data, other);
		}
		
		return null;
		
	}

}
