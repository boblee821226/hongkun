package nc.itf.pms;

import java.util.HashMap;

import nc.vo.pub.BusinessException;

public interface PmsItf {

	/**
	 * 同步获取pms数据
	 */
	public Object sync_pms(HashMap param,Object other) throws BusinessException;
	
}
