package nc.itf.pms;

import java.util.HashMap;

import nc.vo.pub.BusinessException;

public interface PmsItf {

	/**
	 * ͬ����ȡpms����
	 */
	public Object sync_pms(HashMap param,Object other) throws BusinessException;
	
}
