package nc.api.hkjt.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.LoginVO;
import nc.api.hkjt.vo.ManagerVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
/**
 * Ա��
 * @author lb
 *
 */
public class Action_MANAGER {
	
	public static Object doAction(
		 String action
		,LoginVO loginVO
		,Object data
		,Object other
	)throws BusinessException
	{
		if( action.equals(ApiPubInfo.ACTION_QUY) )
		{// ��ѯ
			return QUY(data,other);
		}
		else if( action.equals(ApiPubInfo.ACTION_MOD) )
		{// �޸�
			return MOD(data,other);
		}
		return null;
	}
	
	/**
	 * ��ѯ
	 */
	private static Object QUY (
			 Object data
			,Object other
	) throws BusinessException
	{
		
		StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" org.code ")			// 0����ҵ��˾
					.append(",psn.pk_psndoc ")		// 1��Ա��id
					.append(",psn.code ")			// 2��Ա��code
					.append(",psn.name ")			// 3��Ա��name
					.append(",case psn.sex when 1 then '��' when 2 then 'Ů' else null end ")	// 4���Ա�
					.append(",psn.birthdate ")		// 5������
					.append(",psn.id ")				// 6�����֤��
					.append(",psn.mobile ")			// 7���ֻ���
					.append(",psn.email ")			// 8������
					.append(" from bd_psndoc psn ")
					.append(" inner join bd_psnjob pb  ")	// ��ְ��Ϣ��ֻȡ ��ְ����Ϊ�յ����У�
					.append(" on (psn.pk_psndoc = pb.pk_psndoc and nvl(pb.enddutydate,'~') = '~') ")
					.append(" inner join org_salesorg org ")// ������֯��ֻȡ ������֯�µģ�
					.append(" on (psn.pk_org = org.pk_salesorg) ")
					.append(" left join org_dept dept ")	// ���ţ�03��04ȡ����  ����ȡȫ����
					.append(" on (pb.pk_dept = dept.pk_dept) ")
					.append(" where psn.dr=0  ")
					.append(" and psn.enablestate = 2 ")	// ֻȡ ����״̬��
					.append(" and (" +
							"    (org.code like '0303' and dept.code like '030312%') " +
							" or (org.code like '0304' and dept.code like '030412%') " +
							" or (org.code like '0305' and dept.code like '030512%') " +
							" or (org.code like '0308' and dept.code like '030812%') " +
							" or (org.code like '0317' and dept.code like '03170501%') " +
							" or (org.code like '0408' and dept.code like '040805%') " +
							" or (org.code like '0416' and dept.code like '041604%') " +
							" or (org.code like '05%' ) " +
							") ")	// ����֯������03��04ȡ����  05ȡȫ����
					.append(" order by org.code,psn.code ")
		;
		
		BaseDAO dao = new BaseDAO();
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		ManagerVO[] result = null;
		
		if(list!=null&&list.size()>0)
		{
			result = new ManagerVO[list.size()];
			for(int i=0;i<list.size();i++){
				Object[] value = (Object[])list.get(i);
				ManagerVO vo = new ManagerVO();
				vo.setOrgCode(	PuPubVO.getString_TrimZeroLenAsNull(value[0]));
				vo.setPsnId(	PuPubVO.getString_TrimZeroLenAsNull(value[1]));
				vo.setPsnCode(	PuPubVO.getString_TrimZeroLenAsNull(value[2]));
				vo.setPsnName(	PuPubVO.getString_TrimZeroLenAsNull(value[3]));
				vo.setSex(		PuPubVO.getString_TrimZeroLenAsNull(value[4]));
				vo.setBirthdate(PuPubVO.getString_TrimZeroLenAsNull(value[5]));
				vo.setIdNumber(	PuPubVO.getString_TrimZeroLenAsNull(value[6]));
				vo.setMobile(	PuPubVO.getString_TrimZeroLenAsNull(value[7]));
				vo.setEmail(	PuPubVO.getString_TrimZeroLenAsNull(value[8]));
				
				result[i] = vo;
			}
		}
		
		return result;
	}
	/**
	 * �޸�
	 */
	private static Object MOD (
			 Object data
			,Object other
	) throws BusinessException
	{
		return null;
	}
	
}
