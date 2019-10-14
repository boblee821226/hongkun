package nc.bs.erm.matterapp.eventlistener;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.erm.event.ErmBusinessEvent;
import nc.bs.erm.event.ErmBusinessEvent.ErmCommonUserObj;
import nc.bs.erm.event.ErmEventType;
import nc.bs.erm.matterapp.control.IErmMatterAppYsControlService;
import nc.bs.framework.common.NCLocator;
import nc.vo.arap.bx.util.BXConstans;
import nc.vo.erm.matterapp.AggMatterAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * ��������������-Ԥ��ҵ��ʵ�ֲ��
 * 
 * @author lvhj
 *
 */
public class ErmMatterAppYsControlListener implements IBusinessListener {
	
	IErmMatterAppYsControlService service;

	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		ErmBusinessEvent erevent = (ErmBusinessEvent) event;
		String eventType = erevent.getEventType();
		
		Object userDefObj = erevent.getUserDefineObjs();
		if(userDefObj != null && (Boolean)userDefObj){//�Զ��رյ����뵥������Ԥ��
			return;
		}
		
		ErmCommonUserObj obj = (ErmCommonUserObj) erevent.getUserObject();
		AggMatterAppVO[] vos = (AggMatterAppVO[]) obj.getNewObjects();
		
		AggMatterAppVO[] cloneVos = new AggMatterAppVO[vos.length];
		
		for(int i = 0; i < vos.length ; i ++){
			cloneVos[i] = (AggMatterAppVO)vos[i].clone();
		}
		
		boolean isContray = false; // �Ƿ������
		String actionCode = BXConstans.ERM_NTB_SAVE_KEY;// Ĭ��Ϊ���涯��
		
		if (ErmEventType.TYPE_UPDATE_BEFORE.equalsIgnoreCase(eventType)
				|| ErmEventType.TYPE_UPDATE_AFTER.equalsIgnoreCase(eventType)) {
			// �޸Ĳ���
			AggMatterAppVO[] oldvos = (AggMatterAppVO[]) obj.getOldObjects();
			getYsService().ysControlUpdate(cloneVos, oldvos);
			return ;
		} else if (ErmEventType.TYPE_DELETE_BEFORE.equalsIgnoreCase(eventType) 
				|| ErmEventType.TYPE_INVALID_AFTER.equalsIgnoreCase(eventType)
				|| ErmEventType.TYPE_INVALID_BEFORE.equalsIgnoreCase(eventType)) {
			// ɾ������
			isContray = true;
		}else if(ErmEventType.TYPE_SIGN_BEFORE.equalsIgnoreCase(eventType)
				|| ErmEventType.TYPE_SIGN_AFTER.equalsIgnoreCase(eventType)){
			// ��Ч����
			actionCode = BXConstans.ERM_NTB_APPROVE_KEY;
		}else if(ErmEventType.TYPE_UNSIGN_BEFORE.equalsIgnoreCase(eventType)
				|| ErmEventType.TYPE_UNSIGN_AFTER.equalsIgnoreCase(eventType)){
			// ȡ����Ч����
			isContray = true;
			actionCode = BXConstans.ERM_NTB_APPROVE_KEY;
		}else if(ErmEventType.TYPE_CLOSE_AFTER.equalsIgnoreCase(eventType)){
			//�رպ����
			actionCode = BXConstans.ERM_NTB_CLOSE_KEY;
			//���ݼ������
			for (int i = 0; i < cloneVos.length; i++) {
				for (int j = 0; j < cloneVos[i].getChildrenVO().length; j++) {
					
					UFDouble rest_amount = cloneVos[i].getChildrenVO()[j].getRest_amount();
					UFDouble org_rest_amount = cloneVos[i].getChildrenVO()[j].getOrg_rest_amount();
					UFDouble group_rest_amount = cloneVos[i].getChildrenVO()[j].getGroup_rest_amount();
					UFDouble global_rest_amount = cloneVos[i].getChildrenVO()[j].getGlobal_rest_amount();
					
					if(rest_amount.compareTo(UFDouble.ZERO_DBL)<0){
						// ���С��0�������0����
						rest_amount = UFDouble.ZERO_DBL;
						org_rest_amount = UFDouble.ZERO_DBL;
						group_rest_amount = UFDouble.ZERO_DBL;
						global_rest_amount = UFDouble.ZERO_DBL;
					}
					
					cloneVos[i].getChildrenVO()[j].setOrig_amount(rest_amount);
					cloneVos[i].getChildrenVO()[j].setOrg_amount(org_rest_amount);
					cloneVos[i].getChildrenVO()[j].setGroup_amount(group_rest_amount);
					cloneVos[i].getChildrenVO()[j].setGlobal_amount(global_rest_amount);
				}
			}
		}else if(ErmEventType.TYPE_UNCLOSE_AFTER.equalsIgnoreCase(eventType)){
			//ȡ���رպ����
			isContray = true;
			actionCode = BXConstans.ERM_NTB_CLOSE_KEY;
			//���ݻ������
			for (int i = 0; i < cloneVos.length; i++) {
				/**
				 * HK
				 * 2019��10��12�� 16��26��
				 * �����ж� cloneVos[i].getChildrenVO() != null
				 */
				for (int j = 0; cloneVos[i].getChildrenVO() != null && j < cloneVos[i].getChildrenVO().length; j++) {
					UFDouble rest_amount = cloneVos[i].getChildrenVO()[j].getRest_amount();
					UFDouble org_rest_amount = cloneVos[i].getChildrenVO()[j].getOrg_rest_amount();
					UFDouble group_rest_amount = cloneVos[i].getChildrenVO()[j].getGroup_rest_amount();
					UFDouble global_rest_amount = cloneVos[i].getChildrenVO()[j].getGlobal_rest_amount();
					if(rest_amount.compareTo(UFDouble.ZERO_DBL)<0){
						// ���С��0�������0����
						rest_amount = UFDouble.ZERO_DBL;
						org_rest_amount = UFDouble.ZERO_DBL;
						group_rest_amount = UFDouble.ZERO_DBL;
						global_rest_amount = UFDouble.ZERO_DBL;
					}
					
					cloneVos[i].getChildrenVO()[j].setOrig_amount(rest_amount);
					cloneVos[i].getChildrenVO()[j].setOrg_amount(org_rest_amount);
					cloneVos[i].getChildrenVO()[j].setGroup_amount(group_rest_amount);
					cloneVos[i].getChildrenVO()[j].setGlobal_amount(global_rest_amount);
				}
			}
		}
		
		getYsService().ysControl(cloneVos, isContray, actionCode, true);
	}

	private IErmMatterAppYsControlService getYsService() {
		if(service == null){
			service = NCLocator.getInstance().lookup(IErmMatterAppYsControlService.class);
		}
		return service;
	}
}
