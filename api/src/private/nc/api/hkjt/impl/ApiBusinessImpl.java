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
			String account,		// ����
			String userid,		// �û�
			String billtype,	// ����
			String action,		// ����
			Object data,		// ����
			String token,		// ����
			LoginVO loginVO,	// ��¼�û�VO
			Object other		// ���������ã�
			)
	throws BusinessException 
	{
		
		if( billtype.equals(ApiPubInfo.BILLTYPE_LOGIN) ){			// ��¼
			return Action_LOGIN.doAction(action, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_CUSTOMER) ){	// �ͻ�
			return Action_CUSTOMER.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_MANAGER) ){	// Ա��
			return Action_MANAGER.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_ROOM) ){		// ����
			return Action_ROOM.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_CONTRACT) ){	// ��ͬ
			return Action_CONTRACT.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_PAYABLE) ){	// �ɷ�֪ͨ
			return Action_PAYABLE.doAction(action, loginVO, data, other);
		}
		else if( billtype.equals(ApiPubInfo.BILLTYPE_PAY) ){		// �ɷѼ�¼
			return Action_PAY.doAction(action, loginVO, data, other);
		}
		
		return null;
		
	}

}
