package nc.api.hkjt.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.CustomerVO;
import nc.api.hkjt.vo.LoginVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
/**
 * 客户
 * @author lb
 *
 */
public class Action_CUSTOMER {
	
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
					.append(",ct.pk_customer ")		// 1、客户id
					.append(",cust.code ")			// 2、客户code
					.append(",max(cust.name) ")		// 3、客户name
					.append(",max(cust.trade) ")	// 4、所属行业id
					.append(",max(sshy.name) ")		// 5、所属行业name
					.append(",max(curr.name) ")		// 6、币种
					.append(",max(cust.registerfund) ")		// 7、注册资金
					.append(",replace(substr(max(cust.def3),1,10),'~','') ")	// 8、迁入日期
					.append(",max(bbank.accnum) ")	// 9、账号
					.append(",max(bankdoc.name) ")	// 10、开户行
					.append(",max(address.detailinfo) ")	// 11、地址
					.append(",max(cust.tel1) ")				// 12、电话
					.append(",max(cust.taxpayerid) ")		// 13、税号
					.append(" from ct_sale ct ")
					.append(" inner join bd_customer cust on ct.pk_customer = cust.pk_customer ")
					.append(" left join org_orgs org on ct.pk_org = org.pk_org ")
					.append(" left join bd_defdoc sshy on cust.trade = sshy.pk_defdoc ")
					.append(" left join bd_currtype curr on cust.pk_currtype = curr.pk_currtype ")
					.append(" left join bd_custbank cbank on (cbank.dr=0 and cbank.isdefault='Y' and cbank.pk_cust=cust.pk_customer) ")
					.append(" left join bd_bankaccbas bbank on cbank.pk_bankaccbas = bbank.pk_bankaccbas ")
					.append(" left join bd_bankdoc bankdoc on bbank.pk_bankdoc = bankdoc.pk_bankdoc ")
					.append(" left join bd_address address on cust.corpaddress = address.pk_address ")
					.append(" where ct.dr=0 ")
					.append(" group by org.code,ct.pk_customer,cust.code ")
					.append(" order by org.code,cust.code ")
		;
		
		BaseDAO dao = new BaseDAO();
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		CustomerVO[] result = null;
		
		if(list!=null&&list.size()>0)
		{
			result = new CustomerVO[list.size()];
			for(int i=0;i<list.size();i++){
				Object[] value = (Object[])list.get(i);
				CustomerVO vo = new CustomerVO();
				vo.setOrgCode(	PuPubVO.getString_TrimZeroLenAsNull(value[0]));
				vo.setCustId(	PuPubVO.getString_TrimZeroLenAsNull(value[1]));
				vo.setCustCode(	PuPubVO.getString_TrimZeroLenAsNull(value[2]));
				vo.setCustName(	PuPubVO.getString_TrimZeroLenAsNull(value[3]));
				vo.setTradeId(	PuPubVO.getString_TrimZeroLenAsNull(value[4]));
				vo.setTradeName(PuPubVO.getString_TrimZeroLenAsNull(value[5]));
				vo.setCurrtype(	PuPubVO.getString_TrimZeroLenAsNull(value[6]));
				UFDouble value_7 = PuPubVO.getUFDouble_ZeroAsNull((value[7]));
				vo.setRegisterfund(value_7==null?null:value_7.toDouble());
				vo.setInTime(	PuPubVO.getString_TrimZeroLenAsNull(value[8]));
				vo.setAccnum(	PuPubVO.getString_TrimZeroLenAsNull(value[9]));
				vo.setBankName(	PuPubVO.getString_TrimZeroLenAsNull(value[10]));
				
				vo.setAddress(	PuPubVO.getString_TrimZeroLenAsNull(value[11]));	// 地址
				vo.setPhone(	PuPubVO.getString_TrimZeroLenAsNull(value[12]));	// 电话
				vo.setTin(		PuPubVO.getString_TrimZeroLenAsNull(value[13]));	// 税号
				
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
