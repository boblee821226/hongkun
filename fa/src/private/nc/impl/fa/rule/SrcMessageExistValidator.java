package nc.impl.fa.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.am.common.InSqlManager;
import nc.impl.am.db.QueryUtil;
import nc.message.itf.IMessageQueryService;
import nc.message.vo.MessageVO;
import nc.message.vo.NCMessage;
import nc.pub.am.framework.validation.BaseValidator;
import nc.vo.am.common.util.ArrayUtils;
import nc.vo.am.common.util.CollectionUtils;
import nc.vo.am.common.util.StringUtils;
import nc.vo.am.constant.BillTypeOther;
import nc.vo.am.constant.CommonKeyConst;
import nc.vo.am.messagenote.MessageNoteVO;
import nc.vo.am.proxy.AMProxy;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;

/**
 * ����Ϣ���Ĵ򿪵ĵ����ڱ������ʱ��ҪУ�����Ϣ�Ƿ�ɾ����
 * @author wangweiak
 * @version NC V636
 * @date 2015��5��21��  ����7:15:39
 */
public class SrcMessageExistValidator extends  BaseValidator {

	/**
	 * ���кš�
	 */
	private static final long serialVersionUID = -5762759362289617816L;

	@Override
	public void validateBillVO(AggregatedValueObject billVO) {
		List<String> pk_bill_srcList = getPk_bill_srcs(billVO);
		srcMessageIsDeleted(pk_bill_srcList);
	}

	/**
	 * ������Դ���������жϸ���Ϣ�Ƿ��Ѿ���ɾ����
	 * @param updateBillPks
	 * @throws BusinessException
	 */
	private void srcMessageIsDeleted(List<String> pk_bill_srcList) {
		if (CollectionUtils.isNotEmpty(pk_bill_srcList)) {
			String bill_type_src = pk_bill_srcList.get(pk_bill_srcList.size() - 1); // ȡ�����һ������Ϊ"��Ŀ�ɱ�ת�̵�"�����⴦������
			
			if ("27".equals(bill_type_src)) {
				return; // HK 2019��9��11��19��02��  ����� �ɹ����㵥 �������ж�
			}
			
			if (BillTypeOther.proadapt.equals(bill_type_src)) {
				return; // �������ֵ��������Ϣ��������Ŀ���п��ƣ�����am_messagenote��sm_msg_content����
			}
			if (BillTypeOther.cbs_project.equals(bill_type_src)) {
				pk_bill_srcList.remove(pk_bill_srcList.size() - 1);
				NCMessage[] ncmsgs = null;
				try {
					ncmsgs = AMProxy.lookup(IMessageQueryService.class).queryNCMessages(MessageVO.PK_DETAIL + " in " + InSqlManager.getInSQLValue(pk_bill_srcList));
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				if(ArrayUtils.isEmpty(ncmsgs)){
					appendErrInfo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
							"fapub_0", "02012000-0141")
							/*
							 * @res
							 * ��Դ��Ϣ�Ѿ�ɾ�����޷����б�������� 
							 */);
				}
			} else {
				// ͨ��am_messagenote�����sm_msg_content�е�����
				String[] notePks = QueryUtil.queryPksByWhereSql(MessageNoteVO.class,MessageNoteVO.SRC_PK_BILL + " in " + InSqlManager.getInSQLValue(pk_bill_srcList));
				if (ArrayUtils.isEmpty(notePks)) {
					appendErrInfo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
							"fapub_0", "02012000-0141")
							/*
							 * @res
							 * ��Դ��Ϣ�Ѿ�ɾ�����޷����б�������� 
							 */);
				} 
			}
		}
	}

	/**
	 * ȡ����Դ����������
	 * @param aggVo
	 * @throws BusinessException
	 */
	private List<String> getPk_bill_srcs(AggregatedValueObject billVO) {
		List<String> pk_bill_srcList = new ArrayList<String>();
		ISuperVO headVO = (ISuperVO) billVO.getParentVO();
		// ��Դ��������
		String pk_bill_src = (String) headVO.getAttributeValue(CommonKeyConst.pk_bill_src);
		if (null == pk_bill_src) {
			return null; // �����Դ��������Ϊnull���򲻴��������ε���
		}
		// ��Դ��������
		String bill_type_src = (String) headVO.getAttributeValue(CommonKeyConst.bill_type_src);

		if (StringUtils.isNotEmpty(pk_bill_src)) {				
			pk_bill_srcList.add(pk_bill_src);
		}
		/** added by wangweiak 2015-01-09 �����Դ��������Ϊ"��Ŀ�ɱ�ת�̵�"�����⴦����Ϣ
		 * ���ʲ��ڲ�ʹ�õ��� am_messagenote���ͱ������ʹ�õ�����Ϣƽ̨��sm_msg_content��*/
		if (BillTypeOther.cbs_project.equals(bill_type_src) 
		 || BillTypeOther.proadapt.equals(bill_type_src)
		 || "27".equals(bill_type_src)	// HK 2019��9��11��18��43��  �������ε���Ϊ �ɹ����㵥
		) {
			pk_bill_srcList.add(bill_type_src);
		}
		return pk_bill_srcList;
	}
}
