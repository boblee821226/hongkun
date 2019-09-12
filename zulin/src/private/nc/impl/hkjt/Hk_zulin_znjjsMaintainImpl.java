package nc.impl.hkjt;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceHk_zulin_znjjsPubServiceImpl;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmHVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public class Hk_zulin_znjjsMaintainImpl extends AceHk_zulin_znjjsPubServiceImpl
		implements IHk_zulin_znjjsMaintain {

	@Override
	public void delete(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] insert(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		
		/**
		 * �������棺�ж� ��֯+���� Ψһ����
		 */
		BaseDAO dao = new BaseDAO();
		for(ZnjjsBillVO billVO : clientFullVOs) {
			ZnjjsHVO hVO = billVO.getParentVO();
			String pkOrg = hVO.getPk_org();
			UFDate billDate = hVO.getDbilldate();
			String billDateStr = billDate.toString().substring(0, 10);
			
			StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" js.pk_hk_zulin_znjjs ")
					.append(" from hk_zulin_znjjs js ")
					.append(" where js.dr = 0 ")
					.append(" and js.pk_org = '"+pkOrg+"' ")
					.append(" and substr(js.dbilldate,1,10) >= '"+billDateStr+"' ")
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null&&list.size()>0) {
				throw new BusinessException("����֯�ڱ��ڻ�֮�����ڣ��Ѿ����������ɽ���㵥�������ظ����ڡ�");
			}
		}
		/***END***/
		
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] update(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		
		/**
		 * �޸ı��棺�ж� ��֯+���� Ψһ���� ���ٳ����ŵ��ݣ�
		 */
		BaseDAO dao = new BaseDAO();
		for(ZnjjsBillVO billVO : clientFullVOs) {
			ZnjjsHVO hVO = billVO.getParentVO();
			String pkOrg = hVO.getPk_org();
			UFDate billDate = hVO.getDbilldate();
			String billDateStr = billDate.toString().substring(0, 10);
			String pk = hVO.getPk_hk_zulin_znjjs();
			
			StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" js.pk_hk_zulin_znjjs ")
					.append(" from hk_zulin_znjjs js ")
					.append(" where js.dr = 0 ")
					.append(" and js.pk_org = '"+pkOrg+"' ")
					.append(" and substr(js.dbilldate,1,10) >= '"+billDateStr+"' ")
					.append(" and js.Pk_hk_zulin_znjjs <> '"+pk+"' ")	// Ҫ�ٳ� ������
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null&&list.size()>0) {
				throw new BusinessException("����֯�ڱ��ڻ�֮�����ڣ��Ѿ����������ɽ���㵥�������ظ����ڡ�");
			}
		}
		/***END***/
		
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public ZnjjsBillVO[] save(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] unsave(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] approve(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] unapprove(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
