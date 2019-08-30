package nc.api.hkjt.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.LoginVO;
import nc.api.hkjt.vo.PayableVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
/**
 * 缴费通知
 * @author lb
 *
 */
public class Action_PAYABLE {
	
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
					.append(",bt.billtypename ")	// 1、档案id
					.append(",ysb.pk_recitem ")		// 2、id
					.append(",ysb.customer ")		// 3、客户id
					.append(",cust.code ")			// 4、客户code
					.append(",cust.name ")			// 5、客户name
					.append(",ysb.money_de ")		// 6、金额
					.append(",ysb.scomment ")		// 7、摘要
					.append(",ysb.def8 ")			// 8、房间id
					.append(",room.name  ")			// 9、房间号
					.append(",weizhi.name ")		// 10、位置
					.append(",sdtype.name ")		// 11、水电费类型
					.append(",ct.vbillcode ctCode ")		// 12、合同号
					.append(" from ar_recbill ys ")
					.append(" inner join ar_recitem ysb on ys.pk_recbill = ysb.pk_recbill ")
					.append(" left join org_salesorg org on ys.pk_org = org.pk_salesorg ")
					.append(" left join bd_customer cust on ysb.customer = cust.pk_customer ")
					.append(" left join bd_billtype bt on ys.pk_tradetypeid = bt.pk_billtypeid ")
					.append(" left join bd_defdoc room on ysb.def8 = room.pk_defdoc ")
					.append(" left join bd_defdoc weizhi on ysb.def11 = weizhi.pk_defdoc ")
					.append(" left join bd_defdoc sdtype on ysb.def1 = sdtype.pk_defdoc ")
					.append(" left join ct_sale ct on ct.pk_ct_sale = ysb.def30 ")	// 销售合同
					.append(" where ys.dr = 0 and ysb.dr = 0 ")
					.append(" and ys.pk_tradetypeid in ('1001N5100000006402AK','1001N51000000063ZZH4') ")
					.append(" and ys.effectstatus = 10 ")
					.append(" order by org.code,cust.code,ys.billno ")
		;
		
		BaseDAO dao = new BaseDAO();
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		PayableVO[] result = null;
		
		if(list!=null&&list.size()>0)
		{
			result = new PayableVO[list.size()];
			for(int i=0;i<list.size();i++){
				Object[] value = (Object[])list.get(i);
				PayableVO vo = new PayableVO();
				vo.setOrgCode(		PuPubVO.getString_TrimZeroLenAsNull(value[0]));
				vo.setPayableType(	PuPubVO.getString_TrimZeroLenAsNull(value[1]));
				vo.setPayableId(	PuPubVO.getString_TrimZeroLenAsNull(value[2]));
				vo.setCustId(		PuPubVO.getString_TrimZeroLenAsNull(value[3]));
				vo.setCustCode(		PuPubVO.getString_TrimZeroLenAsNull(value[4]));
				vo.setCustName(		PuPubVO.getString_TrimZeroLenAsNull(value[5]));
				
				UFDouble value_6 = PuPubVO.getUFDouble_ZeroAsNull((value[6]));
				vo.setPayableMoney(value_6==null?null:value_6.toDouble());
				
				vo.setScomment(	PuPubVO.getString_TrimZeroLenAsNull(value[7]));
				vo.setRoomId(	PuPubVO.getString_TrimZeroLenAsNull(value[8]));
				vo.setRoomName(	PuPubVO.getString_TrimZeroLenAsNull(value[9]));
				
				vo.setEquipmentLocation(	PuPubVO.getString_TrimZeroLenAsNull(value[10]));
				vo.setEquipmentType(		PuPubVO.getString_TrimZeroLenAsNull(value[11]));
				
				vo.setCtCode(	PuPubVO.getString_TrimZeroLenAsNull(value[12]));	// 合同号
				
				result[i] = vo;
			}
		}
		
		return result;
	}
	
}
