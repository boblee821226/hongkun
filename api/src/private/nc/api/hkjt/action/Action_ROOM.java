package nc.api.hkjt.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.LoginVO;
import nc.api.hkjt.vo.RoomVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
/**
 * 房间
 * @author lb
 *
 */
public class Action_ROOM {
	
	public static Object doAction(
		 String action
		,LoginVO loginVO
		,Object data
		,Object other
	)throws BusinessException
	{
		if( action.equals(ApiPubInfo.ACTION_QUY) )
		{// 查询
			return QUY(data,other);
		}
		else if( action.equals(ApiPubInfo.ACTION_MOD) )
		{// 修改
			return MOD(data,other);
		}
		return null;
	}
	
	/**
	 * 查询
	 */
	private static Object QUY (
			 Object data
			,Object other
	) throws BusinessException
	{
		
		StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" org.code ")			// 0、物业公司
					.append(",room.pk_defdoc ")		// 1、档案id
					.append(",room.code ")			// 2、档案code
					.append(",room.name ")			// 3、档案name
					.append(",room.memo ")			// 4、面积
					.append(",room.def1 ")			// 5、朝向
					.append(",room.def2 ")			// 6、内景/外景
					.append(" from bd_defdoc room ")
					.append(" left join org_orgs org on room.pk_org = org.pk_org ")
					.append(" where room.dr=0 ")
					.append(" and room.pk_defdoclist = '1001N51000000062V9UU' ")
					.append(" order by org.code,room.code ")
		;
		
		BaseDAO dao = new BaseDAO();
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		RoomVO[] result = null;
		
		if(list!=null&&list.size()>0)
		{
			result = new RoomVO[list.size()];
			for(int i=0;i<list.size();i++){
				Object[] value = (Object[])list.get(i);
				RoomVO vo = new RoomVO();
				vo.setOrgCode(	PuPubVO.getString_TrimZeroLenAsNull(value[0]));
				vo.setRoomId(	PuPubVO.getString_TrimZeroLenAsNull(value[1]));
				vo.setRoomCode(	PuPubVO.getString_TrimZeroLenAsNull(value[2]));
				vo.setRoomName(	PuPubVO.getString_TrimZeroLenAsNull(value[3]));
				vo.setArea(		PuPubVO.getString_TrimZeroLenAsNull(value[4]));
				vo.setDirection(PuPubVO.getString_TrimZeroLenAsNull(value[5]));
				vo.setInOut(	PuPubVO.getString_TrimZeroLenAsNull(value[6]));
				
				result[i] = vo;
			}
		}
		
		return result;
	}
	/**
	 * 修改
	 */
	private static Object MOD (
			 Object data
			,Object other
	) throws BusinessException
	{
		return null;
	}
	
}
