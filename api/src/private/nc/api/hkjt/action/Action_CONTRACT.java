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
 * ��ͬ
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
		
		QueryParamVO queryVO = (QueryParamVO)data;
		String ts = null;	// ʱ���
		if(queryVO!=null) {
			ts = PuPubVO.getString_TrimZeroLenAsNull(queryVO.getTs());
		}
		
		StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" org.code ")			// 0����ҵ��˾
					.append(",ht.pk_customer ")		// 1���ͻ�id
					.append(",cust.code ")			// 2���ͻ�code
					.append(",cust.name ")			// 3���ͻ�name
					.append(",ht.vdef16 ")			// 4������id
					.append(",room.name ")			// 5������name
					.append(",ht.vbillcode ")		// 6����ͬ��
					.append(",substr(ht.subscribedate,1,10) ")	// 7��ǩ������
					.append(",substr(ht.valdate,1,10) ")		// 8����������
					.append(",substr(ht.invallidate,1,10) ")	// 9����ֹ����
					.append(",substr(ht.vdef1,1,10) ")			// 10�����⿪ʼ����
					.append(",substr(ht.vdef2,1,10) ")			// 11�������ֹ����
					.append(",jffs.name ")			// 12���ɷѷ�ʽ
					.append(",ht.vdef4 ")			// 13������
					.append(",ht.vdef5 ")			// 14�����
					.append(",ht.personnelid ")		// 15��Ա��id
					.append(",psn.code ")			// 16��Ա��code
					.append(",psn.name ")			// 17��Ա��name
					.append(",case ht.fstatusflag " +
							" when 0 then '����' " +
							" when 1 then '��Ч' " +
							" when 2 then '������' " +
							" when 3 then '����ͨ��' " +
							" when 4 then '����δͨ��' " +
							" when 5 then '����' " +
							" when 6 then '��ֹ' " +
							" when 7 then '�ύ' " +
							" end fstatusflag ")	// 18����ͬ״̬��0=���ɣ�1=��Ч��2=�����У�3=����ͨ����4=����δͨ����5=���ᣬ6=��ֹ��7=�ύ��
					.append(",quyu.name ")			// 19������
					.append(",ht.version ")			// 20���汾��
					.append(",ht.vdef3 ")			// 21���Ʒ�ʱ��
					.append(",substr(ht.vdef19,1,10) ")				// 22�����ȷ�Ͻ�������
					.append(",substr(ht.actualinvalidate,1,10) ")	// 23��ʵ����ֹ����
					.append(",ht.ntotalorigmny ")	// 24����ͬ���
					.append(",ht.norigpshamount ")	// 25���տ��ܶ�
					.append(",zhongjie.name ")		// 26���Ƿ��н�����
					.append(",ht.vdef9 ")			// 27��Ӷ����
					.append(",ht.pk_ct_sale ")		// 28����ͬpk
					.append(",ht.dr ")			// 29��ɾ����־
					.append(",ht.ts ")			// 30��ʱ���
					.append(" from ct_sale ht ")
					.append(" left join org_orgs org on ht.pk_org = org.pk_org ")
					.append(" left join bd_customer cust on ht.pk_customer = cust.pk_customer ")
					.append(" left join bd_defdoc room on ht.vdef16 = room.pk_defdoc ")		// �����
					.append(" left join bd_defdoc jffs on ht.vdef7 = jffs.pk_defdoc ")		// ���㷽ʽ
					.append(" left join bd_psndoc psn on ht.personnelid = psn.pk_psndoc ")	// ��Ա
					.append(" left join bd_defdoc quyu on ht.vdef15 = quyu.pk_defdoc ")		// ����
					.append(" left join bd_defdoc zhongjie on ht.vdef11 = zhongjie.pk_defdoc ")		// �Ƿ��н�����
					.append(" where ")
					.append(" ht.blatest = 'Y' ")	// ��Զȡ ���°棬�����ǲ���ɾ���ġ�����Ҫ����һ�£�ɾ���� �᲻���ΪN��
					.append(" and ht.fstatusflag in (1,6) ")
//					.append(" and ht.vbillcode = '201806239001' ")	// ����
					.append( ts==null 
							? " and ht.dr = 0 " 			// �������ts���򷵻� ���д��ڵ����ݡ�
							: " and ht.ts >= '"+ts+"' " )	// �������ts���򷵻ظ�ts֮������ݣ�����ɾ���ġ�
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
				
				vo.setDr(PuPubVO.getInteger_NullAs(value[29], 0));			// ɾ����־
				vo.setTs(PuPubVO.getString_TrimZeroLenAsNull(value[30]));	// ʱ���
				
				if(vo.getDr()==0) {	// ֻ�� dr=0 �Ĳ���Ҫ ��ѯ��ͬ���塣 ɾ��״̬�� �����ѯ���塣
					querySQL_pk_ct += ",'"+pk_ct+"'";
				}
				
				result_MAP.put(pk_ct, vo);
				
				result[i] = vo;
			}
			
			querySQL_pk_ct += ") ";
			
			// ��ѯ ��ͬ����
			StringBuffer querySQL_2 = 
			new StringBuffer(" select ")
					.append(" csb.pk_ct_sale ")	// 0��pk
					.append(",csb.crowno ")		// 1���к�
					.append(",sfxm.name ")		// 2���շ���Ŀ
					.append(",substr(csb.vbdef3,1,10) ")		// 3����ʼ����
					.append(",substr(csb.vbdef4,1,10) ")		// 4����������
					.append(",csb.vbdef8 ")		// 5���ɷ�����
					.append(",csb.vbdef5 ")		// 6������
					.append(",csb.vbdef6 ")		// 7�����
					.append(",csb.vbdef7 ")		// 8�����
					.append(",csb.norigtaxmny ")		// 9��Ӧ�ɽ��
					.append(",csb.noritotalgpmny ")		// 10���տ���
					.append(",null ")			// 11��δ�տ���
					.append(",csb.vmemo ")		// 12����ע
					.append(" from ct_sale_b csb ")
					.append(" left join bd_defdoc sfxm on csb.vbdef1 = sfxm.pk_defdoc ")		// �շ���Ŀ
					.append(" where csb.dr = 0 ")
					.append(" and csb.pk_ct_sale in ").append(querySQL_pk_ct)
					.append(" order by csb.pk_ct_sale,to_number(csb.crowno) ")	// ����ͬ �� �к� ����
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
