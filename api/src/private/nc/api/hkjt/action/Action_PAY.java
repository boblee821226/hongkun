package nc.api.hkjt.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.LoginVO;
import nc.api.hkjt.vo.PayBVO;
import nc.api.hkjt.vo.PayVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
/**
 * 缴费通知
 * @author lb
 *
 */
public class Action_PAY {
	
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
		else if( action.equals(ApiPubInfo.ACTION_ADD) )
		{// 新增
			return ADD(data,other);
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
					.append(",skb.customer ")		// 1、客户id
					.append(",cust.code ")			// 2、客户code
					.append(",cust.name ")			// 3、客户name
					.append(",sk.def22 ")			// 4、WEB单号
					.append(",sk.billno ")			// 5、NC单号
					.append(",case sk.effectstatus when 0 then '未生效' when 10 then '已生效' end ")	// 6、状态
					.append(",skb.pk_gatheritem ")	// 7、收款id
					.append(",skb.money_cr ")		// 8、收款金额
					.append(",case when skb.src_billtype = 'F0' then skb.src_itemid else null end ")// 9、应收id
					.append(",skb.scomment ")		// 10、摘要
					.append(",sfxm.name ")			// 11、收费项目
					.append(",jsfs.name ")			// 12、缴费方式
					.append(",ct.vbillcode ctCode ")		// 13、合同号
					.append(" from ar_gatherbill sk ")
					.append(" inner join ar_gatheritem skb on sk.pk_gatherbill = skb.pk_gatherbill ")
					.append(" inner join (select distinct ct.pk_customer from ct_sale ct where ct.dr=0 and ct.blatest = 'Y' and ct.fstatusflag in (1,6)) ctcust on skb.customer = ctcust.pk_customer ")
					.append(" left join org_salesorg org on sk.pk_org = org.pk_salesorg ")
					.append(" left join bd_customer cust on skb.customer = cust.pk_customer ")
					.append(" left join bd_defdoc sfxm on skb.def1 = sfxm.pk_defdoc ")
					.append(" left join bd_balatype jsfs on skb.pk_balatype = jsfs.pk_balatype ")
					.append(" left join ar_recitem ysb on (skb.src_billtype = 'F0' and skb.src_itemid = ysb.pk_recitem ) ")	// 关联-应收
					.append(" left join ct_sale ct on ct.pk_ct_sale = ysb.def30 ")	// 关联-销售合同
					.append(" where sk.dr=0 and skb.dr=0 ")
//					.append(" and sk.billno = 'D22019060400005619' ")	// 测试
					.append(" order by org.code,cust.code,sk.billno ")
		;
		
		BaseDAO dao = new BaseDAO();
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		// 按客户+单号分组
		HashMap<String,ArrayList<Object>> MAP = new HashMap<String,ArrayList<Object>>();
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++){
				Object[] value = (Object[])list.get(i);
				
				String custId = PuPubVO.getString_TrimZeroLenAsNull(value[1]);
				String ncCode = PuPubVO.getString_TrimZeroLenAsNull(value[5]);
				String MAP_key = custId+"##"+ncCode;
				
				if(MAP.containsKey(MAP_key))
				{
					MAP.get(MAP_key).add(value);
				}
				else
				{
					ArrayList<Object> MAP_value = new ArrayList<Object>();
					MAP_value.add(value);
					MAP.put(MAP_key, MAP_value);
				}
			}
		}
		
		// 根据分组的信息  生成 返回的结果
		
		if(MAP!=null&&MAP.size()>0)
		{
			String[] MAP_key = new String[MAP.size()];
			MAP_key = MAP.keySet().toArray(MAP_key);
			PayVO[] result = new PayVO[MAP_key.length];
			
			for(int i=0;i<MAP_key.length;i++){
				
				ArrayList<Object> list_temp = MAP.get(MAP_key[i]);
				PayVO payVO = new PayVO();
				PayBVO[] payBVOs = null;
				for( int k=0;k<list_temp.size();k++ )
				{
					Object[] value = (Object[])list_temp.get(k);
					if(k==0){// 如果是第一个 则需要 给表头VO赋值
						payVO.setOrgCode(	PuPubVO.getString_TrimZeroLenAsNull(value[0]));
						payVO.setCustId(	PuPubVO.getString_TrimZeroLenAsNull(value[1]));
						payVO.setCustCode(	PuPubVO.getString_TrimZeroLenAsNull(value[2]));
						payVO.setCustName(	PuPubVO.getString_TrimZeroLenAsNull(value[3]));
						payVO.setWebCode(	PuPubVO.getString_TrimZeroLenAsNull(value[4]));
						payVO.setNcCode(	PuPubVO.getString_TrimZeroLenAsNull(value[5]));
						payVO.setStatus(	PuPubVO.getString_TrimZeroLenAsNull(value[6]));
						payBVOs = new PayBVO[list_temp.size()];
					}
					// 给表体VO赋值
					payBVOs[k] = new PayBVO();
					payBVOs[k].setPayId(	PuPubVO.getString_TrimZeroLenAsNull(value[7]));
					UFDouble value_8 = PuPubVO.getUFDouble_ZeroAsNull((value[8]));
					payBVOs[k].setPayMoney(value_8==null?null:value_8.toDouble());
					payBVOs[k].setPayableId(PuPubVO.getString_TrimZeroLenAsNull(value[9]));
					payBVOs[k].setScomment(	PuPubVO.getString_TrimZeroLenAsNull(value[10]));
					payBVOs[k].setPayItem(	PuPubVO.getString_TrimZeroLenAsNull(value[11]));
					payBVOs[k].setPayWay(	PuPubVO.getString_TrimZeroLenAsNull(value[12]));
					
					payBVOs[k].setCtCode(	PuPubVO.getString_TrimZeroLenAsNull(value[13]));	// 合同号
					
					/**
					 * HK 2019年11月11日 17点52分
					 * 将摘要里 收入的描述 给去掉
					 */
					if (payBVOs[k].getScomment() != null) {
						payBVOs[k].setScomment(payBVOs[k].getScomment().replaceAll("收入", ""));
					}
					/***END***/
				}
				
				payVO.setPayBVOs(payBVOs);
				result[i] = payVO;
			}
			return result;
		}
		
		return null;
	}
	/**
	 * 新增
	 */
	private static Object ADD (
			 Object data
			,Object other
	) throws BusinessException
	{
		return null;
	}
}
