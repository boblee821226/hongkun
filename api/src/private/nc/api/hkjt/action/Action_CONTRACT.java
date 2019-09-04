package nc.api.hkjt.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.ContractBVO;
import nc.api.hkjt.vo.ContractVO;
import nc.api.hkjt.vo.LoginVO;
import nc.api.hkjt.vo.QueryParamVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
/**
 * 合同
 * @author lb
 *
 */
public class Action_CONTRACT {
	
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
		
		QueryParamVO queryVO = (QueryParamVO)data;
		String ts = null;	// 时间戳
		if(queryVO!=null) {
			ts = PuPubVO.getString_TrimZeroLenAsNull(queryVO.getTs());
		}
		
		StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" org.code ")			// 0、物业公司
					.append(",ht.pk_customer ")		// 1、客户id
					.append(",cust.code ")			// 2、客户code
					.append(",cust.name ")			// 3、客户name
					.append(",ht.vdef16 ")			// 4、房间id
					.append(",room.name ")			// 5、房间name
					.append(",ht.vbillcode ")		// 6、合同号
					.append(",substr(ht.subscribedate,1,10) ")	// 7、签订日期
					.append(",substr(ht.valdate,1,10) ")		// 8、起租日期
					.append(",substr(ht.invallidate,1,10) ")	// 9、终止日期
					.append(",substr(ht.vdef1,1,10) ")			// 10、免租开始日期
					.append(",substr(ht.vdef2,1,10) ")			// 11、免租截止日期
					.append(",jffs.name ")			// 12、缴费方式
					.append(",ht.vdef4 ")			// 13、单价
					.append(",ht.vdef5 ")			// 14、面积
					.append(",ht.personnelid ")		// 15、员工id
					.append(",psn.code ")			// 16、员工code
					.append(",psn.name ")			// 17、员工name
					.append(",case ht.fstatusflag " +
							" when 0 then '自由' " +
							" when 1 then '生效' " +
							" when 2 then '审批中' " +
							" when 3 then '审批通过' " +
							" when 4 then '审批未通过' " +
							" when 5 then '冻结' " +
							" when 6 then '终止' " +
							" when 7 then '提交' " +
							" end fstatusflag ")	// 18、合同状态（0=自由，1=生效，2=审批中，3=审批通过，4=审批未通过，5=冻结，6=终止，7=提交）
					.append(",quyu.name ")			// 19、区域
					.append(",ht.version ")			// 20、版本号
					.append(",ht.vdef3 ")			// 21、计费时长
					.append(",substr(ht.vdef19,1,10) ")				// 22、租金确认截至日期
					.append(",substr(ht.actualinvalidate,1,10) ")	// 23、实际终止日期
					.append(",ht.ntotalorigmny ")	// 24、合同金额
					.append(",ht.norigpshamount ")	// 25、收款总额
					.append(",zhongjie.name ")		// 26、是否中介渠道
					.append(",ht.vdef9 ")			// 27、佣金倍数
					.append(",ht.pk_ct_sale ")		// 28、合同pk
					.append(",ht.dr ")			// 29、删除标志
					.append(",ht.ts ")			// 30、时间戳
					.append(" from ct_sale ht ")
					.append(" left join org_orgs org on ht.pk_org = org.pk_org ")
					.append(" left join bd_customer cust on ht.pk_customer = cust.pk_customer ")
					.append(" left join bd_defdoc room on ht.vdef16 = room.pk_defdoc ")		// 房间号
					.append(" left join bd_defdoc jffs on ht.vdef7 = jffs.pk_defdoc ")		// 结算方式
					.append(" left join bd_psndoc psn on ht.personnelid = psn.pk_psndoc ")	// 人员
					.append(" left join bd_defdoc quyu on ht.vdef15 = quyu.pk_defdoc ")		// 区域
					.append(" left join bd_defdoc zhongjie on ht.vdef11 = zhongjie.pk_defdoc ")		// 是否中介渠道
					.append(" where ")
					.append(" ht.blatest = 'Y' ")	// 永远取 最新版，不管是不是删除的。（需要测试一下，删除后 会不会改为N）
					.append(" and ht.fstatusflag in (1,6) ")
//					.append(" and ht.vbillcode = '201806239001' ")	// 测试
					.append( ts==null 
							? " and ht.dr = 0 " 			// 如果不传ts，则返回 所有存在的数据。
							: " and ht.ts >= '"+ts+"' " )	// 如果传了ts，则返回该ts之后的数据，包括删除的。
					.append(" order by org.code,cust.code,ht.vbillcode ")
		;
		
		BaseDAO dao = new BaseDAO();
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		ContractVO[] result = null;
		
		HashMap<String,ContractVO> result_MAP = new HashMap<String,ContractVO>();
		
		if(list!=null&&list.size()>0)
		{
			String querySQL_pk_ct = " ('null'";
			
			result = new ContractVO[list.size()];
			for(int i=0;i<list.size();i++){
				Object[] value = (Object[])list.get(i);
				ContractVO vo = new ContractVO();
				vo.setOrgCode(	PuPubVO.getString_TrimZeroLenAsNull(value[0]));
				vo.setCustId(	PuPubVO.getString_TrimZeroLenAsNull(value[1]));
				vo.setCustCode(	PuPubVO.getString_TrimZeroLenAsNull(value[2]));
				vo.setCustName(	PuPubVO.getString_TrimZeroLenAsNull(value[3]));
				
				vo.setRoomId(	PuPubVO.getString_TrimZeroLenAsNull(value[4]));
				vo.setRoomName(	PuPubVO.getString_TrimZeroLenAsNull(value[5]));
				
				vo.setCtCode(	PuPubVO.getString_TrimZeroLenAsNull(value[6]));
				
				vo.setSignDate(	PuPubVO.getString_TrimZeroLenAsNull(value[7]));
				vo.setStartDate(PuPubVO.getString_TrimZeroLenAsNull(value[8]));
				vo.setStopDate(	PuPubVO.getString_TrimZeroLenAsNull(value[9]));
				vo.setFreeBeginDate(PuPubVO.getString_TrimZeroLenAsNull(value[10]));
				vo.setFreeEndDate(	PuPubVO.getString_TrimZeroLenAsNull(value[11]));
				
				vo.setPayment(	PuPubVO.getString_TrimZeroLenAsNull(value[12]));
				
				UFDouble value_13 = PuPubVO.getUFDouble_ZeroAsNull((value[13]));
				vo.setPrice(value_13==null?null:value_13.toDouble());
				UFDouble value_14 = PuPubVO.getUFDouble_ZeroAsNull((value[14]));
				vo.setArea(value_14==null?null:value_14.toDouble());
				
				vo.setPsnId(	PuPubVO.getString_TrimZeroLenAsNull(value[15]));
				vo.setPsnCode(	PuPubVO.getString_TrimZeroLenAsNull(value[16]));
				vo.setPsnName(	PuPubVO.getString_TrimZeroLenAsNull(value[17]));
				
				vo.setCtStatus(	PuPubVO.getString_TrimZeroLenAsNull(value[18]));
				
				vo.setRegion(	PuPubVO.getString_TrimZeroLenAsNull(value[19]));
				vo.setVersion(	PuPubVO.getString_TrimZeroLenAsNull(value[20]));
				UFDouble value_21 = PuPubVO.getUFDouble_ValueAsValue((value[21]));
				vo.setDays(value_21==null?null:value_21.toDouble());
				vo.setRentalEndDate(	PuPubVO.getString_TrimZeroLenAsNull(value[22]));
				vo.setReallyStopDate(	PuPubVO.getString_TrimZeroLenAsNull(value[23]));
				UFDouble value_24 = PuPubVO.getUFDouble_ValueAsValue((value[24]));
				vo.setCtMny(value_24==null?null:value_24.toDouble());
				UFDouble value_25 = PuPubVO.getUFDouble_ValueAsValue((value[25]));
				vo.setSkMny(value_25==null?null:value_25.toDouble());
				vo.setIsAgency(	PuPubVO.getString_TrimZeroLenAsNull(value[26]));
				UFDouble value_27 = PuPubVO.getUFDouble_ValueAsValue((value[27]));
				vo.setCommission(value_27==null?null:value_27.toDouble());
				
				String pk_ct = PuPubVO.getString_TrimZeroLenAsNull(value[28]);
				
				vo.setDr(PuPubVO.getInteger_NullAs(value[29], 0));			// 删除标志
				vo.setTs(PuPubVO.getString_TrimZeroLenAsNull(value[30]));	// 时间戳
				
				if(vo.getDr()==0) {	// 只有 dr=0 的才需要 查询合同表体。 删除状态的 无需查询表体。
					querySQL_pk_ct += ",'"+pk_ct+"'";
				}
				
				result_MAP.put(pk_ct, vo);
				
				result[i] = vo;
			}
			
			querySQL_pk_ct += ") ";
			
			// 查询 合同表体
			StringBuffer querySQL_2 = 
			new StringBuffer(" select ")
					.append(" csb.pk_ct_sale ")	// 0、pk
					.append(",csb.crowno ")		// 1、行号
					.append(",sfxm.name ")		// 2、收费项目
					.append(",substr(csb.vbdef3,1,10) ")		// 3、开始日期
					.append(",substr(csb.vbdef4,1,10) ")		// 4、截至日期
					.append(",csb.vbdef8 ")		// 5、缴费周期
					.append(",csb.vbdef5 ")		// 6、单价
					.append(",csb.vbdef6 ")		// 7、面积
					.append(",csb.vbdef7 ")		// 8、租金
					.append(",csb.norigtaxmny ")		// 9、应缴金额
					.append(",csb.noritotalgpmny ")		// 10、收款金额
					.append(",null ")			// 11、未收款金额
					.append(",csb.vmemo ")		// 12、备注
					.append(" from ct_sale_b csb ")
					.append(" left join bd_defdoc sfxm on csb.vbdef1 = sfxm.pk_defdoc ")		// 收费项目
					.append(" where csb.dr = 0 ")
					.append(" and csb.pk_ct_sale in ").append(querySQL_pk_ct)
					.append(" order by csb.pk_ct_sale,to_number(csb.crowno) ")	// 按合同 和 行号 排序
			;
			ArrayList list_2 = (ArrayList)dao.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
			for(int i=0;i<list_2.size();i++) {
				Object[] value = (Object[])list_2.get(i);
				String pk_ct = PuPubVO.getString_TrimZeroLenAsNull(value[0]);
				ContractBVO bvo = new ContractBVO();
				bvo.setRowNo(		PuPubVO.getString_TrimZeroLenAsNull(value[1]));
				bvo.setPayItem(		PuPubVO.getString_TrimZeroLenAsNull(value[2]));
				bvo.setBeginDate(	PuPubVO.getString_TrimZeroLenAsNull(value[3]));
				bvo.setEndDate(		PuPubVO.getString_TrimZeroLenAsNull(value[4]));
				UFDouble value_5 = PuPubVO.getUFDouble_ValueAsValue((value[5]));
				bvo.setPaymentCycle(value_5==null?null:value_5.toDouble());
				UFDouble value_6 = PuPubVO.getUFDouble_ValueAsValue((value[6]));
				bvo.setPrice(value_6==null?null:value_6.toDouble());
				UFDouble value_7 = PuPubVO.getUFDouble_ValueAsValue((value[7]));
				bvo.setArea(value_7==null?null:value_7.toDouble());
				UFDouble value_8 = PuPubVO.getUFDouble_ValueAsValue((value[8]));
				bvo.setRental(value_8==null?null:value_8.toDouble());
				UFDouble value_9 = PuPubVO.getUFDouble_NullAsZero((value[9]));
				bvo.setPayableMoney(value_9==null?null:value_9.toDouble());
				UFDouble value_10 = PuPubVO.getUFDouble_NullAsZero((value[10]));
				bvo.setPayMoney(value_10==null?null:value_10.toDouble());
				bvo.setNotPayMoney(	value_9.sub(value_10).toDouble() );
				bvo.setVnote(	PuPubVO.getString_TrimZeroLenAsNull(value[12]));
				
				result_MAP.get(pk_ct).getbVOs().add(bvo);
				
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
