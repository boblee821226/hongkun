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
 * 从消息中心打开的单据在保存操作时需要校验该消息是否被删除。
 * @author wangweiak
 * @version NC V636
 * @date 2015年5月21日  下午7:15:39
 */
public class SrcMessageExistValidator extends  BaseValidator {

	/**
	 * 序列号。
	 */
	private static final long serialVersionUID = -5762759362289617816L;

	@Override
	public void validateBillVO(AggregatedValueObject billVO) {
		List<String> pk_bill_srcList = getPk_bill_srcs(billVO);
		srcMessageIsDeleted(pk_bill_srcList);
	}

	/**
	 * 根据来源单据主键判断该消息是否已经被删除。
	 * @param updateBillPks
	 * @throws BusinessException
	 */
	private void srcMessageIsDeleted(List<String> pk_bill_srcList) {
		if (CollectionUtils.isNotEmpty(pk_bill_srcList)) {
			String bill_type_src = pk_bill_srcList.get(pk_bill_srcList.size() - 1); // 取出最后一个，作为"项目成本转固单"的特殊处理条件
			
			if ("27".equals(bill_type_src)) {
				return; // HK 2019年9月11日19点02分  如果是 采购结算单 则跳过判断
			}
			
			if (BillTypeOther.proadapt.equals(bill_type_src)) {
				return; // 产出物价值调整单消息由上游项目进行控制，不在am_messagenote和sm_msg_content表中
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
							 * 来源消息已经删除，无法进行保存操作！ 
							 */);
				}
			} else {
				// 通过am_messagenote查出在sm_msg_content中的主键
				String[] notePks = QueryUtil.queryPksByWhereSql(MessageNoteVO.class,MessageNoteVO.SRC_PK_BILL + " in " + InSqlManager.getInSQLValue(pk_bill_srcList));
				if (ArrayUtils.isEmpty(notePks)) {
					appendErrInfo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
							"fapub_0", "02012000-0141")
							/*
							 * @res
							 * 来源消息已经删除，无法进行保存操作！ 
							 */);
				} 
			}
		}
	}

	/**
	 * 取得来源单据主键。
	 * @param aggVo
	 * @throws BusinessException
	 */
	private List<String> getPk_bill_srcs(AggregatedValueObject billVO) {
		List<String> pk_bill_srcList = new ArrayList<String>();
		ISuperVO headVO = (ISuperVO) billVO.getParentVO();
		// 来源单据主键
		String pk_bill_src = (String) headVO.getAttributeValue(CommonKeyConst.pk_bill_src);
		if (null == pk_bill_src) {
			return null; // 如果来源单据主键为null，则不存在上下游单据
		}
		// 来源单据类型
		String bill_type_src = (String) headVO.getAttributeValue(CommonKeyConst.bill_type_src);

		if (StringUtils.isNotEmpty(pk_bill_src)) {				
			pk_bill_srcList.add(pk_bill_src);
		}
		/** added by wangweiak 2015-01-09 如果来源单据类型为"项目成本转固单"，特殊处理消息
		 * （资产内部使用的是 am_messagenote表，和别的领域使用的是消息平台的sm_msg_content表*/
		if (BillTypeOther.cbs_project.equals(bill_type_src) 
		 || BillTypeOther.proadapt.equals(bill_type_src)
		 || "27".equals(bill_type_src)	// HK 2019年9月11日18点43分  配置上游单据为 采购结算单
		) {
			pk_bill_srcList.add(bill_type_src);
		}
		return pk_bill_srcList;
	}
}
