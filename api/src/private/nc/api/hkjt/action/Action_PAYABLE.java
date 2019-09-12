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
					.append(",bt.billtypename ")	// 1、单据类型
					.append(",ysb.pk_recitem ")		// 2、id
					.append(",ysb.customer ")		// 3、客户id
					.append(",cust.code ")			// 4、客户code
					.append(",cust.name ")			// 5、客户name
					.append(",ysb.money_de ")		// 6、金额
					.append(",ysb.scomment ")		// 7、摘要
					.append(",ysb.def8 ")			// 8、房间id
					.append(",room.name ")			// 9、房间号
					.append(",weizhi.name ")		// 10、位置
					.append(",sdtype.name ")		// 11、水电费类型
					.append(",ct.vbillcode ctCode ")// 12、合同号
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
					.append(" and ys.pk_tradetypeid in ('1001N5100000006402AK','1001N51000000063ZZH4') ")// 水电费应收单、缴费通知单
					.append(" and ys.effectstatus = 10 ")
				.append(" union all ")
					// 取 滞纳金计算单
					.append(" select ")
					.append(" org.code ")
					.append(",'滞纳金计算单' ")
					.append(",js.pk_hk_zulin_znjjs ")
					.append(",jsb.pk_cust ")
					.append(",cust.code ")
					.append(",cust.name ")
					.append(",jsb.yq_mny - to_number(nvl( replace(jsb.vbdef01,'~',''),'0' )) ")
					.append(",'房租滞纳金' ")
					.append(",jsb.pk_room ")
					.append(",room.code ")
					.append(",null ")
					.append(",null ")
					.append(",ct.vbillcode ctCode ")// 12、合同号
					.append(" from hk_zulin_znjjs js ")
					.append(" inner join ( select js.pk_org,max(js.dbilldate) dbilldate from hk_zulin_znjjs js where dr=0 group by js.pk_org ")
					.append("  ) js_new on (js.pk_org=js_new.pk_org and js.dbilldate=js_new.dbilldate) ")
					.append(" inner join hk_zulin_znjjs_b jsb on (js.pk_hk_zulin_znjjs = jsb.pk_hk_zulin_znjjs) ")
//					.append(" left join ar_recitem ysb on (jsb.pk_hk_zulin_znjjs_b = ysb.def29 and ysb.dr=0) ")
					.append(" left join bd_defdoc room on (jsb.pk_room = room.pk_defdoc) ")
					.append(" left join org_salesorg org on (js.pk_org = org.pk_salesorg) ")
					.append(" left join bd_customer cust on (jsb.pk_cust = cust.pk_customer) ")
					.append(" left join ct_sale ct on (jsb.ht_id = ct.pk_ct_sale) ")
					.append(" where js.dr=0 and jsb.dr=0 ")
//					.append(" and ysb.pk_recitem is null ")
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
