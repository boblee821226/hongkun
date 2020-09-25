package nc.cmp.settlement.validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.cmp.util.SqlUtils;
import nc.bs.cmp.validate.SettlementBillDataIntegralityValidate;
import nc.bs.crossrule.external.CrossRuleParamContext;
import nc.bs.crossrule.service.CrossRulePubServiceFacade;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.uap.bd.notetype.INotetypeConst;
import nc.bs.uif2.validation.DefaultValidationService;
import nc.bs.uif2.validation.ValidationFailure;
import nc.bs.uif2.validation.Validator;
import nc.cmp.pub.exception.ExceptionHandler;
import nc.cmp.utils.CmpInterfaceProxy;
import nc.cmp.utils.CmpQueryModulesUtil;
import nc.cmp.utils.CmpUtils;
import nc.cmp.utils.InterfaceLocator;
import nc.cmp.utils.Lists;
import nc.cmp.utils.SettleUtils;
import nc.itf.bd.bankacc.subinfo.IBankAccSubInfoQueryService;
import nc.itf.cm.prv.CmpConst;
import nc.itf.cmp.bill.ICmpPayBillQueryService;
import nc.itf.cmp.bill.ICmpRecBillQueryService;
import nc.itf.cmp.settlement.ISettlementService;
import nc.itf.tmpub.pub.SupplierPayFreezeUtil;
import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.pubitf.cmp.bankaccbook.IAccQueryService;
import nc.pubitf.uapbd.INotetypePubService;
import nc.uap.pf.metadata.PfMetadataTools;
import nc.vo.arap.utils.DataUtil;
import nc.vo.bd.balatype.BalaTypeVO;
import nc.vo.bd.bankaccount.BankAccSubVO;
import nc.vo.bd.cust.CustbankVO;
import nc.vo.bd.notetype.NotetypeVO;
import nc.vo.bd.psnbankacc.PsnBankaccVO;
import nc.vo.cmp.BusiStatus;
import nc.vo.cmp.SettleStatus;
import nc.vo.cmp.bill.BillAggVO;
import nc.vo.cmp.bill.BillDetailVO;
import nc.vo.cmp.bill.RecBillAggVO;
import nc.vo.cmp.bill.RecBillDetailVO;
import nc.vo.cmp.netpay.PaymentCrumbVO;
import nc.vo.cmp.pub.BillEnumCollection;
import nc.vo.cmp.settlement.CheckException;
import nc.vo.cmp.settlement.SettleEnumCollection;
import nc.vo.cmp.settlement.SettleEnumCollection.Direction;
import nc.vo.cmp.settlement.SettlementAggVO;
import nc.vo.cmp.settlement.SettlementBodyVO;
import nc.vo.cmp.settlement.SettlementHeadVO;
import nc.vo.cmp.util.StringUtils;
import nc.vo.cmp.validate.CMPValidate;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tmpub.util.StringUtil;

public class SettleValidate extends DefaultValidationService {

	public SettleValidate(boolean isFormUI) {
		if (isFormUI) {
			addValidator(new StateValidator());
		} else {
			addValidator(new SettleNoValidator());
			addValidator(new CommonValidator());
			addValidator(new CodeValidator());
			addValidator(new SettleSignValidate.AccountValidator());
		}
	}

	/**
	 * UI端的状态校验
	 *
	 * @author liuzz
	 *
	 */
	private static class StateValidator implements Validator {

		@Override
		public ValidationFailure validate(Object obj) {
			SettlementHeadVO head = (SettlementHeadVO) ((SettlementAggVO) obj).getParentVO();
			StringBuilder sb = new StringBuilder();
			if (head.getBusistatus() != BusiStatus.Sign.getBillStatusKind()) {
				sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0004")/*
																												* @
																												* res
																												* "没有签字的单据不能结算"
																												*/);
			}
			if (head.getSettlestatus() == SettleStatus.SUCCESSSETTLE.getStatus()) {
				sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0005")/*
																												* @
																												* res
																												* "结算过的不能再次结算"
																												*/);
			}
			if (head.getPk_ftsbill() != null) {
				sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0581")/*
																												* @
																												* res
																												* "推式生成的下游单据不能手工结算"
																												*/);
			}
			// if(head.getIsapplybill().equals(UFBoolean.TRUE)) {
			// sb.append("提交资金组织的单据不能手工结算");
			// }

			if (sb.length() != 0) {
				return new ValidationFailure(sb.toString());
			}
			return null;
		}

	}

	/**
	 * 结算号的校验
	 *
	 * @author liuzz
	 *
	 */
	private static class SettleNoValidator implements Validator {

		@Override
		public ValidationFailure validate(Object obj) {
			return null;
		}
	}

	/**
	 * 校验是否满足结算的条件
	 *
	 * @author liuzz
	 *
	 */
	private static class CommonValidator implements Validator {

		@Override
		public ValidationFailure validate(Object obj) {
			SettlementHeadVO head = (SettlementHeadVO) ((SettlementAggVO) obj).getParentVO();
			StringBuilder sb = new StringBuilder();
			if (head.getSettlestatus() == SettleStatus.HANDPAYAG.getStatus()) {
				return null;
			}
			List<PaymentCrumbVO> payList = null;
			try {
				payList = CmpInterfaceProxy.INSTANCE.getPaymentService().queryCrumb(head.getPrimaryKey());
			} catch (Exception e) {
				sb.append(e.getMessage());
				return new ValidationFailure(sb.toString());
			}
			Integer status = null;
			if (payList != null) {
				for (PaymentCrumbVO vo : payList) {
					if (vo.getPk_settlement().equals(head.getPk_settlement())) {
						status = vo.getPaystatus();
						break;
					}
				}
				if (status != null && status != 4) {
					if (head.getSettlestatus() != SettleStatus.PAYFAIL.getStatus() && status != 1) {
						sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0582")/*
																														* @
																														* res
																														* "不在未结算状态的单据不能手工结算"
																														*/);
					}
				}
			} else {
				if (head.getSettlestatus() != SettleStatus.NONESETTLE.getStatus()) {
					sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0582")/*
																													* @
																													* res
																													* "不在未结算状态的单据不能手工结算"
																													*/);
				}
			}
			if (sb.length() != 0) {
				return new ValidationFailure(sb.toString());
			}
			return null;
		}

	}

	/**
	 * 数字签名的校验
	 *
	 * @author liuzz
	 *
	 */
	public static class CodeValidator implements Validator {

		@Override
		public ValidationFailure validate(Object obj) {
			// SettlementAggVO agg = (SettlementAggVO)obj;
			StringBuilder sb = new StringBuilder();
			try {
				// if(!new AddandReleaseCode().checkCode(agg)) {
				//
				// }
			} catch (Exception e) {
				sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0583")/*
																												* @
																												* res
																												* "数字签名不正确"
																												*/);
				return new ValidationFailure(sb.toString());
			}
			return null;
		}
	}

	/**
	 * 网上支付的单据单据行校验
	 *
	 * @param aggVOs
	 * @throws BusinessException
	 */
	public static void validateNetPayNegativeRow(SettlementAggVO... aggVOs) throws BusinessException {
		if (CmpUtils.isListNull(aggVOs)) {
			return;
		}

		List<SettlementBodyVO> bodys = new ArrayList<SettlementBodyVO>();
		List<SettlementBodyVO> bodys_change = new ArrayList<SettlementBodyVO>();
		// List<SettlementAggVO> AggvoLst =
		// SettleUtils.filterSettleInfo4NetSettleFlagUnSettle(aggVOs);
		// AggvoLst.toArray(aggVOs);
		// if(CmpUtils.isListNull(aggVOs)){
		// return;
		// }
		for (SettlementAggVO aggvo : aggVOs) {
			if (aggvo.getChildrenVO() == null) {
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
						"03607set-0584")/* @res "结算信息数据异常，请检查该数据！" */);
			}
			if (((SettlementHeadVO) aggvo.getParentVO()).getDirection().equals(Direction.CHANGE.VALUE)) {
				bodys_change.addAll(CmpUtils.covertArraysToList((SettlementBodyVO[]) aggvo.getChildrenVO()));
			} else {

				bodys.addAll(CmpUtils.covertArraysToList((SettlementBodyVO[]) aggvo.getChildrenVO()));
			}
		}
		for (SettlementBodyVO body : bodys) {
			// 负数判断
			if (body.getDirection() == CmpConst.Direction_Pay && UFDouble.ZERO_DBL.compareTo(body.getPaylocal()) > 0) {
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
						"03607set-0585")/* @res "支付金额为负数的单据不能网上支付！" */);

			}



			if (Integer.valueOf(4).equals(body.getTradertype())
					&& (body.getOppaccname() == null || body.getOppaccount() == null)) {
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607settle4_0","03607settle4-0010")/*@res "结算方式为网银属性并且对方为散户时，对方银行账户和对方账户户名必填!"*/);
			} else if (!"DS".equals(body.getPk_billtype())) {
				if (body.getPk_oppaccount() == null && (body.getOppaccname() == null || body.getOppaccount() == null)) {
					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
							"03607set-0857")/* @res "结算方式为网银属性时，收款银行账户和收款账户户名必填" */);
				}

			}

		}
		for (SettlementBodyVO body : bodys_change) {
			// 负数判断
			if (body.getDirection() == CmpConst.Direction_Pay && UFDouble.ZERO_DBL.compareTo(body.getPaylocal()) > 0) {
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
						"03607set-0585")/* @res "支付金额为负数的单据不能网上支付！" */);

			}
		}
	}

	/**
	 * 校验单据状态
	 *
	 * @param aggVOs
	 * @throws BusinessException
	 */
	public static void validateNetPayState(SettlementAggVO... aggVOs) throws BusinessException {
		if (aggVOs == null || aggVOs.length == 0) {
			return;
		}
		String pk_user = DataUtil.getCurrentUser();
		// TODO 单据必须录入收付款银行账户，且账户应开通网银支付 否则支付时给出提示
		for (SettlementAggVO aggvo : aggVOs) {
			
			SettlementHeadVO head = (SettlementHeadVO) aggvo.getParentVO();
			
			// 已经结算成功的单据 不能进行网上转账
			if (head.getSettlestatus().equals(SettleStatus.SUCCESSSETTLE.getStatus())) {
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
						"03607set-0587")/* @res "已成功结算的单据不能进行网上转账" */);
			}
			if (head.getSettlestatus().equals(SettleStatus.SETTLERESET.getStatus())) {
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
						"03607set-0588")/* @res "结算红冲的单据不能进行网上转账" */);
			}
			
			AggregatedValueObject billAggVo = (AggregatedValueObject)CmpInterfaceProxy.INSTANCE.getQueryService().findBusiBillVO(aggvo);
			
			IFlowBizItf fbi = PfMetadataTools.getBizItfImpl(billAggVo,IFlowBizItf.class);
			
			
			/**
			 * HK 2020年9月25日16:52:14
			 * 去掉限制
			 */
//			if(pk_user.equals(head.getCreator())&&pk_user.equals(head.getPk_signer())&&pk_user.equals(fbi.getApprover())){
//				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
//						"03607set-0858")/* @res "不能一人同时进行支付、签字、审批、创建操作" */);
//			}
			/***END***/

		}
	}

	/**
	 * 1 结算号必须全部相同或则全部相等 2 结算号相等的情况下，必须同一批次的一起支付
	 *
	 * @param aggs
	 * @throws BusinessException
	 */

	public static void validateCombin(SettlementAggVO[] aggs, boolean isPay) throws BusinessException {

		Map<String, List<SettlementHeadVO>> map = new HashMap<String, List<SettlementHeadVO>>();
		for (SettlementAggVO aggVO : aggs) {
			SettlementHeadVO head = (SettlementHeadVO) aggVO.getParentVO();
			if (head.getSettlenum() != null) {
				List<SettlementHeadVO> list = map.get(head.getSettlenum());
				if (list == null) {
					list = Lists.newArrayList();
					map.put(head.getSettlenum(), list);
				}
				list.add(head);
			}
		}
		// 普通支付的情况
		if (isPay) {
			if (map.size() != 0) {
				for (String settleno : map.keySet()) {
					SettlementHeadVO[] headVOs = InterfaceLocator
							.getInterfaceLocator()
							.getSettlementQueryService()
							.findHeadsBySettleNo(((SettlementHeadVO) aggs[0].getParentVO()).getPk_group(),
									((SettlementHeadVO) aggs[0].getParentVO()).getPk_org(), settleno);
					if (!CheckException.checkArraysIsNull(headVOs)) {
						CheckException.checkArgument(headVOs.length != 1, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
								.getStrByID("3607set_0", "03607set-0591")/*
																			* @res
																			* "同一批次结算号的单据必须一起合并支付"
																			*/);
					}
				}
			}
			// 合并支付
		} else {
			if (map.size() != 0) {
				if (map.size() == 1) {
					Iterator<String> iter = map.keySet().iterator();
					String settleNo = iter.next();
					List<SettlementHeadVO> list = map.get(settleNo);
					SettlementHeadVO[] headVOs = InterfaceLocator.getInterfaceLocator().getSettlementQueryService()
							.findHeadsBySettleNo(list.get(0).getPk_group(), list.get(0).getPk_org(), settleNo);
					// if(headVOs.length == list.size()) {
					//
					// }
					CheckException.checkArgument(headVOs.length != list.size(), nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("3607set_0", "03607set-0591")/*
																					 * @
																					 * res
																					 * "同一批次结算号的单据必须一起合并支付"
																					 */);
					CheckException.checkArgument(headVOs.length != aggs.length, nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("3607set_0", "03607set-0591")/*
																					 * @
																					 * res
																					 * "同一批次结算号的单据必须一起合并支付"
																					 */);
				}
			}
			CheckException.checkArgument(map.size() > 1,
					nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0592")/*
																										 * @
																										 * res
																										 * "结算号必须全部为空或则全部相等才能支付"
																										 */);
		}

	}

	/**
	 * 检查数据
	 *
	 * @param aggVOs
	 * @throws BusinessException
	 */
	public static void validatePayData(SettlementAggVO... aggVOs) throws BusinessException {
		for (SettlementAggVO aggVO : aggVOs) {
			for (SettlementBodyVO body : (SettlementBodyVO[]) aggVO.getChildrenVO()) {
				CheckException.checkArgument(body.getPk_balatype() == null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
						.getStrByID("3607set_0", "03607set-0593")/*
																	* @res
																	* "网上支付的单据结算方式不能为空"
																	*/);

				if (StringUtils.isEmpty(body.getPk_account())) {

					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607bacd_0",
							"03607bacd-0064")/*@res "本方账户不能为空!"*/);
				}
			}

			validateSanhu(aggVO);
		}
	}

	/**
	 * 校验支付是否为人民币业务
	 *
	 * @param aggVOs
	 * @throws BusinessException
	 */
	public static void validateCurrency(SettlementAggVO... aggVOs) throws BusinessException {
		for (SettlementAggVO settlementAggVO : aggVOs) {
			// 判断是否非人民币
			for (SettlementBodyVO bodyVo : (SettlementBodyVO[]) settlementAggVO.getChildrenVO()) {
				if (!CmpConst.CONST_PK_CURRTYPE.equals(bodyVo.getPk_currtype())) {
					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
							"03607set-0768")/* @res "非人民币币种的记录不能进行支付！" */);
				}
			}
		}

	}

	/**
	 * 业务单元的银企直联模块启用作用在于当单据发起的网银支付时，需要检查该业务单元（即“财务组织”）的银企直联模块是否启用进行检查，需要检查的时点包括：
	 * 网银补录界面的弹出和单据上的支付动作
	 * 。如该业务单元（即“财务组织”）的银企直联模块没有启用，则抛出提示：“**没有启用银企直联模块，不允许进行网银支付
	 * 。”如已经启用，则不校验比较启用时间与业务时间的早晚，一律允许进行支付。
	 *
	 * @param aggVO
	 * @throws BusinessException
	 */
	public static void validateOBM(SettlementAggVO... aggVOs) throws BusinessException {
		Set<String> pk_orgs = new HashSet<>();
		for(SettlementAggVO aggVO : aggVOs){
			SettlementHeadVO head = (SettlementHeadVO) aggVO.getParentVO();
			pk_orgs.add( head.getPk_org());
		}
		for (String pk_org : pk_orgs) {
			CheckException.checkArgument(CmpQueryModulesUtil.getOBMPeriod(pk_org) == null,
					nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0594")/*
																										 * @
																										 * res
																										 * "现金管理没有启用银企直联模块，不允许进行网银支付。"
																										 */);
		}
	}

	/**
	 * 校验供应商是否冻结
	 *
	 * @param aggvos
	 * @throws BusinessException
	 */
	public static void validateSupplier(SettlementAggVO... aggvos) throws BusinessException {
		
		Map<String,Set<String>> orgSuppliers = new HashMap<>();
		
		for (SettlementAggVO aggvo : aggvos) {
			// 校验供应商是否已经付款冻结
			SettlementBodyVO[] bodys = (SettlementBodyVO[]) aggvo.getChildrenVO();
			if (bodys != null && bodys.length > 0) {
				Set<String> list_pk_trader = null;
				String pk_org = aggvo.getParentVO().getAttributeValue(SettlementHeadVO.PK_ORG).toString();
				if(orgSuppliers.containsKey(pk_org)){
					list_pk_trader = orgSuppliers.get(pk_org);
				}else{
					list_pk_trader = new HashSet<String>();
					orgSuppliers.put(pk_org, list_pk_trader);
				}
				for (SettlementBodyVO body : bodys) {
					if (body.getDirection().equals(SettleEnumCollection.Direction.PAY.VALUE)) {
						if (StringUtil.isNotNull(body.getPk_trader())) {
							list_pk_trader.add(body.getPk_trader());
						}
					}
				}
				
			}
		}
		
		for (Map.Entry<String, Set<String>> orgSupplier : orgSuppliers.entrySet()) {
			if (orgSupplier.getValue().size() > 0) {
				SupplierPayFreezeUtil.checkSupplierPayFreeze(orgSupplier.getKey(), orgSupplier.getValue().toArray(new String[] {}));

			}

		}
		

		
	}

	/**
	 * 校验散户信息是否完整
	 *
	 * @param aggvos
	 * @throws BusinessException
	 */
	public static void validateSanhu(SettlementAggVO... aggvos) throws BusinessException {
		for (SettlementAggVO aggvo : aggvos) {
			SettlementBodyVO[] bodys = (SettlementBodyVO[]) aggvo.getChildrenVO();
			if (bodys != null && bodys.length > 0) {

				for (SettlementBodyVO body : bodys) {

					if (Integer.valueOf(4).equals(body.getTradertype())) {
						StringBuilder sbBuilder = new StringBuilder();

						if (StringUtils.isNullWithTrim(body.getOppaccount())) {
							sbBuilder.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set1_0",
									"03607set1-0121")/*@res "对方银行账号不能为空，"*/);
						}
						if (StringUtils.isNullWithTrim(body.getOppaccname())) {
							sbBuilder.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set1_0",
									"03607set1-0122")/*@res "对方账户户名不能为空，"*/);
						}
						if (StringUtils.isNullWithTrim(body.getOppbank())) {
							sbBuilder.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set1_0",
									"03607set1-0123")/*@res "对方银行名称不能为空，"*/);
						}

						if (body.getAccounttype() == null) {
							sbBuilder.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set1_0",
									"03607set1-0124")/*@res "对方账户性质不能为空，"*/);
						}

						if (sbBuilder.length() > 9) {
							String err = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set1_0",
									"03607set1-0120")/*@res "对方类型为散户时："*/
									+ sbBuilder.toString().substring(0, sbBuilder.length() - 1);

							ExceptionHandler.createandthrowException(err);
						}
					}

				}

			}
		}
	}

	/**
	 * 校验保存时必须的网上支付信息
	 *
	 * @param aggvos
	 * @throws BusinessException
	 */
	public static void validateNetPayFields(SettlementAggVO... aggvos) throws BusinessException {
		if (aggvos == null || aggvos[0] == null) {
			return;
		}
		for (SettlementAggVO aggvo : aggvos) {
			SettlementBodyVO[] bodys = (SettlementBodyVO[]) aggvo.getChildrenVO();

			SettlementHeadVO settlementHeadVO = (SettlementHeadVO) aggvo.getParentVO();

			if (Integer.valueOf(1).equals(settlementHeadVO.getDirection())
					&& !"DS".equals(settlementHeadVO.getPk_tradetype()) && bodys != null && bodys.length > 0) {

				for (SettlementBodyVO body : bodys) {

					if (isBalaTypeOfNetBank(body.getPk_balatype())) {

						StringBuilder sbBuilder = new StringBuilder();

						if (StringUtils.isNullWithTrim(body.getOppaccount())) {
							sbBuilder.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set1_0",
									"03607set1-0121")/*@res "对方银行账号不能为空，"*/);
						}
						if (StringUtils.isNullWithTrim(body.getOppaccname())) {
							sbBuilder.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set1_0",
									"03607set1-0122")/*@res "对方账户户名不能为空，"*/);
						}

						if (sbBuilder.length() > 0) {

							ExceptionHandler.createandthrowException(sbBuilder.toString());
						}
					}

				}

			}
		}
	}

	/**
	 * 是否网银标志
	 */
	private static boolean isBalaTypeOfNetBank(String pk_balatype) {
		try {
			if (null != pk_balatype && !"".equals(pk_balatype)) {
				BalaTypeVO btvo = InterfaceLocator.getInterfaceLocator().getBalaQry().findBalaTypeVOByPK(pk_balatype);
				if (btvo.getNetbankflag() != null && btvo.getNetbankflag().equals(UFBoolean.TRUE)) {
					return true;
				}
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set1_0",
					"03607set1-0097")/*@res "获取结算方式失败"*/);
		}
		return false;
	}

	/**
	 *
	 * 1、现金账户 与 本方银行账户, 不可以同时录入 2、现金账户 与 空白票据，不可以同时录入 3、现金账户 与 商业票据，不可以同时luru
	 * 4、本方银行账户 与 空白票据，可以同时录入，资金形态取“银行” 5、本方银行账户 与 商业票据，不可以同时录入。
	 *
	 * @param aggvos
	 * @throws BusinessException
	 */
	public static void validateTradeType(SettlementAggVO... aggvos) throws BusinessException {
		for (SettlementAggVO aggvo : aggvos) {
			for (SettlementBodyVO body : (SettlementBodyVO[]) aggvo.getChildrenVO()) {
				NotetypeVO typeVO = null;
				if(StringUtil.isNotNull(body.getPk_notetype())){
					 typeVO = NCLocator.getInstance().lookup(INotetypePubService.class).
							queryNotetypeVOByPk(body.getPk_notetype());
				}
				// 现金账户 与 银行账户, 不可以同时录入
				if (!StringUtils.isNullWithTrim(body.getPk_cashaccount())
						&& !StringUtils.isNullWithTrim(body.getPk_account())) {
					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
							"03607set-0822")/* @res "现金账户 与 银行账户, 不可以同时录入" */);
				}
				// 现金账户 与 空白票据，不可以同时录入
				if (!StringUtils.isNullWithTrim(body.getPk_cashaccount())
						&& !StringUtils.isNullWithTrim(body.getNotenumber())) {
					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
							"03607set-0823")/* @res "现金账户 与 票据信息, 不可以同时录入" */);
				}
				if(StringUtil.isNotNull(body.getPk_account()) && typeVO != null 
						&& INotetypeConst.NOTETYPE_CLASS_BUSIPO == typeVO.getNoteclass()){
					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
								"03607set-0911")/* @res "银行账户与商业汇票 不可以同时录入" */);
					
				}
				
				
//				if (!StringUtils.isNullWithTrim(body.getPk_notetype())
//						&& StringUtils.isNullWithTrim(body.getPK_notenumber())
//						&& StringUtils.isNullWithTrim(body.getNotenumber())) {
//					throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607pub_0",
//							"03607pub-0203")/* @res "表体行票据号不能为空" */);
//
//				}
				

			}
		}
	}

	/**
	 * 付款排程的单据是否被冻结
	 *
	 * @param aggVOs
	 * @throws BusinessException
	 */
	public static void validatePS(SettlementAggVO... aggVOs) throws BusinessException {
		for (SettlementAggVO settlementAggVO : aggVOs) {
			if (SettleUtils.isPsBillFrozen(settlementAggVO)) {
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
						"03607set-0595")/* @res "上游付款排程单据已被冻结，不能操作" */);
			}
		}
	}

	/**
	 * 付款类单据对子户全额冻结的控制
	 *
	 * @param aggVOs
	 * @throws BusinessException
	 */
	public static void validatePayBankAccount(SettlementAggVO... aggVOs) throws BusinessException {
		for (SettlementAggVO settlementAggVO : aggVOs) {
			SettlePaybillBankAccountValidate.checkPayBankAccount(settlementAggVO);
		}
	}

	/**
	 * 校验币种与本方账户币种一致
	 *
	 * @param aggvos
	 * @throws BusinessException
	 */
	public static void validateAccountAndCurrencyIdentical(SettlementAggVO... aggvos) throws BusinessException {
		IBankAccSubInfoQueryService bankAccSubInfoQueryService = NCLocator.getInstance().lookup(
				IBankAccSubInfoQueryService.class);

		Map<String,BankAccSubVO[]> map = new HashMap<String,BankAccSubVO[]>();
		
		for (SettlementAggVO aggvo : aggvos) {
			SettlementBodyVO[] bodys = (SettlementBodyVO[]) aggvo.getChildrenVO();
			if (bodys != null && bodys.length > 0) {
				for (SettlementBodyVO body : bodys) {
					if (body.getPk_account() != null && body.getPk_cashaccount() != null) {
						throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0",
								"03607set-0812"/* "同一表体行不允许同时存在本方账户和现金账户" */));
					}
					if (body.getPk_currtype_last() == null || body.getPk_currtype().equals(body.getPk_currtype_last())) {
						// 非购汇业务
						List<String> listAcc = CmpUtils.makeList();
						if (body.getPk_account() != null) {
							listAcc.add(body.getPk_account());
						}

						String pk_currtype = body.getPk_currtype();
						if (StringUtils.isNullWithTrim(body.getPk_account())) {
							continue;
						}

						BankAccSubVO[] bankAccSubVOs = null;
						if(map.get(listAcc.get(0))!=null)
						{
							bankAccSubVOs = map.get(listAcc.get(0));
						}
						else
						{
							bankAccSubVOs = bankAccSubInfoQueryService.querySubInfosByPKs(CmpUtils.covertListToArrays(
									listAcc, String.class));
							map.put(listAcc.get(0), bankAccSubVOs);
						}
						if (bankAccSubVOs != null && bankAccSubVOs.length > 0) {
							if (!(pk_currtype != null && pk_currtype.equals(bankAccSubVOs[0].getPk_currtype()))) {
								throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
										"3607set_0", "03607set-0596")/*
																		* @res
																		* "银行账户的币种与币种字段不一致!"
																		*/);
							}
						}
					} else {
						// 购汇业务
						if (StringUtils.isNullWithTrim(body.getPk_account())) {
							continue;
						}

						List<String> listAcc_last = CmpUtils.makeList();
						listAcc_last.add(body.getPk_account());
						String pk_currtype_last = body.getPk_currtype_last();// 取最终付款信息

						BankAccSubVO[] bankAccSubVOs = null;
						
						if (listAcc_last.size() > 0) {
							if(map.get(listAcc_last.get(0))!=null)
							{
								bankAccSubVOs = map.get(listAcc_last.get(0));
							}
							else
							{
								bankAccSubVOs = bankAccSubInfoQueryService.querySubInfosByPKs(CmpUtils.covertListToArrays(
										listAcc_last, String.class));
								map.put(listAcc_last.get(0),bankAccSubVOs);
							}

							if (bankAccSubVOs != null && bankAccSubVOs.length > 0) {
								if (!(pk_currtype_last != null && pk_currtype_last.equals(bankAccSubVOs[0]
										.getPk_currtype()))) {
									throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
											"3607set_0", "03607set-0596")/*
																			* @res
																			* "银行账户的币种与币种字段不一致!"
																			*/);
								}
							}
						}
					}
				}

			}
		}
	}

	/**
	 * 校验余额
	 *
	 * @param isOpp
	 *            是否反向
	 * @param isCurrent
	 *            是否实时
	 * @param aggVOs
	 * @throws BusinessException
	 */
	public static void validateBalance(boolean isOpp, boolean isCurrent, SettlementAggVO... aggVOs)
			throws BusinessException {
		ISettlementService iSettlementService = nc.cmp.utils.CmpInterfaceProxy.INSTANCE.getSettlementService();
		List<SettlementBodyVO> payBodyList = CmpUtils.makeList();
		List<SettlementBodyVO> recBodyList = CmpUtils.makeList();

		for (SettlementAggVO aggVO : aggVOs) {
			SettlementHeadVO head = (SettlementHeadVO) aggVO.getParentVO();
			SettlementBodyVO[] bodys = (SettlementBodyVO[]) aggVO.getChildrenVO();

			for (SettlementBodyVO settlementBodyVO : bodys) {
				// getBodyList().add(settlementBodyVO);
				if (settlementBodyVO.getDirection().equals(SettleEnumCollection.Direction.PAY.VALUE)) {
					// 付 方向
					payBodyList.add(settlementBodyVO);
				} else {
					// 收 方向
					recBodyList.add(settlementBodyVO);
				}

			}
		}
		if (CmpUtils.isListNotNull(payBodyList)) {
			iSettlementService.checkBalance(CmpUtils.covertListToArrays(payBodyList, SettlementBodyVO.class), isOpp,
					isCurrent, null);
		}

		if (CmpUtils.isListNotNull(recBodyList)) {
			InterfaceLocator.getBalanceCheck().checkOriginalBalance(recBodyList);
		}
	}

	/**
	 * 校验对方账户是否在同一组织
	 *
	 * @param bodys
	 * @throws BusinessException
	 */
	public static void validateOppAcount(SettlementBodyVO[] bodys) throws BusinessException {
		Set<String> custsuppBankAccMap = new HashSet<>();
		Set<String>  psnBankAccMap = new HashSet<>();
		StringBuilder sbmsg = new StringBuilder();
		for (SettlementBodyVO body : bodys) {
			if (body.getStatus() == nc.vo.pub.VOStatus.DELETED) {// 删除的行
				continue;
			}

			Integer objtype = body.getTradertype();
			String pk_trader = body.getPk_trader();
			String bankAccount = body.getPk_oppaccount();
			if (CmpUtils.isObjectNull(objtype) || StringUtils.isNullWithTrim(pk_trader)
					|| StringUtils.isNullWithTrim(bankAccount)) {
				// 没有对方信息或收款账户信息
				continue;
			}
			if (objtype.equals(CmpConst.TradeObjType_CUSTOMER)) {
				// 客户信息
				custsuppBankAccMap.add(pk_trader);
			} else if (objtype.equals(CmpConst.TradeObjType_SUPPLIER)) {
				// 供应商信息
				custsuppBankAccMap.add(pk_trader);
			} else if (objtype.equals(CmpConst.TradeObjType_Person)) {
				// 个人信息
				psnBankAccMap.add(pk_trader);
			}

		}
		Map<String, String> mapcustsup = queryBankaccPksByCustPk(custsuppBankAccMap.toArray(new String[] {}));
		Map<String, String> mappsn = queryBankaccPksByPsnPk(psnBankAccMap.toArray(new String[] {}));

		for (int i = 0; i < bodys.length; i++) {
			if (bodys[i].getStatus() == nc.vo.pub.VOStatus.DELETED) {// 删除的行
				continue;
			}
			Integer objtype = bodys[i].getTradertype();
			String pk_trader = bodys[i].getPk_trader();
			String bankAccount = bodys[i].getPk_oppaccount();
			if (CmpUtils.isObjectNull(objtype) || StringUtils.isNullWithTrim(pk_trader)
					|| StringUtils.isNullWithTrim(bankAccount)) {
				// 没有对方信息或收款账户信息
				continue;
			}
			if (objtype.equals(CmpConst.TradeObjType_CUSTOMER)) {
				// 客户信息
				if (mapcustsup.get(bodys[i].getPk_trader() + " " + bodys[i].getPk_oppaccount()) == null) {
					sbmsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0816")/*
																													 * @
																													 * res
																													 * "第"
																													 */)
							.append(i + 1)
							.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0817")/*
																														 * @
																														 * res
																														 * "行对方单位与对方银行账户组织不一致\n"
																														 */);
				}
			} else if (objtype.equals(CmpConst.TradeObjType_SUPPLIER)) {
				// 供应商信息
				if (mapcustsup.get(bodys[i].getPk_trader() + " " + bodys[i].getPk_oppaccount()) == null) {
					sbmsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0816")/*
																													 * @
																													 * res
																													 * "第"
																													 */)
							.append(i + 1)
							.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0817")/*
																														 * @
																														 * res
																														 * "行对方单位与对方银行账户组织不一致\n"
																														 */);
				}
			} else if (objtype.equals(CmpConst.TradeObjType_Person)) {
				// 个人信息
				if (mappsn.get(bodys[i].getPk_trader() + " " + bodys[i].getPk_oppaccount()) == null) {
					sbmsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0816")/*
																													 * @
																													 * res
																													 * "第"
																													 */)
							.append(i + 1)
							.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607set_0", "03607set-0817")/*
																														 * @
																														 * res
																														 * "行对方单位与对方银行账户组织不一致\n"
																														 */);
				}
			}

		}
		if (!sbmsg.toString().equals("")) {
			throw new BusinessException(sbmsg.toString());
		}
	}

	private static void setMapInfo(Map<String, List<String>> map, String pk_trader, String pk_oppaccount) {
		if (map.get(pk_trader) == null) {

			map.put(pk_trader, new ArrayList<String>());
		}
		map.get(pk_trader).add(pk_oppaccount);
	}

	private static Map<String, String> queryBankaccPksByCustPk(String... pk_custs) throws BusinessException {

		// Map<String,String> map = CmpUtils.makeMap();
		// String where =SqlUtils.getInStr(CustbankVO.PK_CUST, pk_custs, false)
		// ;
		// Collection<CustbankVO> col = new
		// BaseDAO().retrieveByClause(CustbankVO.class, where,
		// new String[] { CustbankVO.PK_BANKACCSUB,CustbankVO.PK_CUST });
		// CustbankVO[] vos = col == null || col.size() == 0 ? null :
		// col.toArray(new CustbankVO[0]);
		// if (!CmpUtils.isListNull(vos)) {
		// for (CustbankVO vo : vos) {
		// map.put(vo.getPk_cust()+" "+vo.getPk_bankaccsub(),
		// vo.getPk_cust()+" "+vo.getPk_bankaccsub());
		// }
		// }
		// ICustsupPubService service =
		// CmpInterfaceProxy.INSTANCE.getCustsupPubService();
		// Map<String, String> orgMap = service.queryOrgPkByCustsupPk(pk_custs);
		// if(orgMap==null){
		// return map;
		// }
		// String[] orgs = new String[orgMap.size()];
		// orgMap.values().toArray(orgs);
		// Map<String,String> custMap = CmpUtils.makeMap();
		//
		// // 反转key和value值
		// for (String key : orgMap.keySet()) {
		// custMap.put(orgMap.get(key), key);
		// }
		//
		// where =SqlUtils.getInStr(AccidVO.PK_OWNERORG, orgs, false) ;
		// where =where+ " or " +SqlUtils.getInStr(AccidVO.PK_ORG, pk_custs,
		// false) ;
		// col = new BaseDAO().retrieveByClause(AccidVO.class, where,
		// new String[] { AccidVO.PK_ACCID,AccidVO.PK_OWNERORG });
		// AccidVO[] accidvos = col == null || col.size() == 0 ? null :
		// col.toArray(new AccidVO[0]);
		//
		// if (!CmpUtils.isListNull(accidvos)) {
		// for (AccidVO vo : accidvos) {
		// String value =
		// custMap.get(vo.getPk_org())==null?custMap.get(vo.getPk_ownerorg()):custMap.get(vo.getPk_org());
		// value +=" "+vo.getPk_accid();
		// map.put(value,value);
		// }
		// }
		if (CmpUtils.isListNull(pk_custs)) {
			return null;
		}
		Map<String, String> map = CmpUtils.makeMap();
		String where = SqlUtils.getInStr(CustbankVO.PK_CUST, pk_custs, false);
		Collection<CustbankVO> col = new BaseDAO().retrieveByClause(CustbankVO.class, where, new String[] {
				CustbankVO.PK_BANKACCSUB, CustbankVO.PK_CUST });
		CustbankVO[] vos = col == null || col.size() == 0 ? null : col.toArray(new CustbankVO[0]);
		Set<String> accPkSet = null;
		if (!CmpUtils.isListNull(vos)) {
			for (CustbankVO vo : vos) {
				map.put(vo.getPk_cust() + " " + vo.getPk_bankaccsub(), vo.getPk_cust() + " " + vo.getPk_bankaccsub());
			}
		}
		return map;
	}

	private static Map<String, String> queryBankaccPksByPsnPk(String... pk_psns) throws BusinessException {
		if (CmpUtils.isListNull(pk_psns)) {
			return null;
		}
		Map<String, String> map = CmpUtils.makeMap();
		String where = SqlUtils.getInStr(PsnBankaccVO.PK_PSNDOC, pk_psns, false);
		// String where = PsnBankaccVO.PK_PSNDOC + " ='" + pk_psn + "'";
		Collection<PsnBankaccVO> col = new BaseDAO().retrieveByClause(PsnBankaccVO.class, where, new String[] {
				PsnBankaccVO.PK_BANKACCSUB, PsnBankaccVO.PK_PSNDOC });
		PsnBankaccVO[] vos = col == null || col.size() == 0 ? null : col.toArray(new PsnBankaccVO[0]);
		String[] accPks = null;
		Set<String> accPkSet = null;
		if (!CmpUtils.isListNull(vos)) {
			for (PsnBankaccVO vo : vos) {
				map.put(vo.getPk_psndoc() + " " + vo.getPk_bankaccsub(),
						vo.getPk_psndoc() + " " + vo.getPk_bankaccsub());
			}

		}

		return map;
	}
	
	public static void dataIntegralityValidate(SettlementAggVO... settlementAggVOs) throws BusinessException {
		if (settlementAggVOs == null) {
			return;
		}
		for (SettlementAggVO settlementAggVO : settlementAggVOs) {
			SettlementBillDataIntegralityValidate.checkDataIntegrality(settlementAggVO);

			//2015.10.14  无法全面校验素有单据，使用签名验签保证数据正确
//			SettlementHeadVO settlementHeadVO = (SettlementHeadVO) settlementAggVO.getParentVO();
//			String pk_tradertype = settlementHeadVO.getTradertypecode();
//			String pk_busibill = settlementHeadVO.getPk_busibill();
//			String billType = SettleUtils.getbilltype(pk_tradertype, settlementHeadVO.getPk_group());
//			if ("F4".equals(billType) || "F5".equals(billType)) {
//				if ("F5".equals(billType)) {
//					ICmpPayBillQueryService cmpPayBillQueryService = NCLocator.getInstance().lookup(
//							ICmpPayBillQueryService.class);
//					BillAggVO billAggVO = (BillAggVO) cmpPayBillQueryService.queryBillById(pk_busibill);
//
//					validateOPPInfo(settlementAggVO, billAggVO);
//				} else if ("F4".equals(billType)) {
//					ICmpRecBillQueryService cmpRecBillQueryService = NCLocator.getInstance().lookup(
//							ICmpRecBillQueryService.class);
//					RecBillAggVO recBillAggVO = (RecBillAggVO) cmpRecBillQueryService.queryBillById(pk_busibill);
//					validateOPPInfo(settlementAggVO, recBillAggVO);
//				}
//
//			}

		}
	}

	private static void validateOPPInfo(SettlementAggVO settlementAggVO, BillAggVO billAggVO) throws BusinessException {
		SettlementBodyVO[] settlementBodyVOs = (SettlementBodyVO[]) settlementAggVO.getChildrenVO();
		BillDetailVO[] billdaDetailVOs = (BillDetailVO[]) billAggVO.getChildrenVO();
		for (SettlementBodyVO settlementBodyVO : settlementBodyVOs) {
			String pk_billdetail = settlementBodyVO.getPk_billdetail();
			for (BillDetailVO billDetailVO : billdaDetailVOs) {
				
				if(Integer.valueOf(4).equals(billDetailVO.getObjecttype())){
					continue ;
				}
				
				if (pk_billdetail != null && pk_billdetail.equals(billDetailVO.getPk_paybill_detail())) {

					String pk_oppAccount = settlementBodyVO.getPk_oppaccount();
					String pk_oppAccountBill = billDetailVO.getPk_account();
					if (!(nc.cmp.utils.StringUtil.isEmptyWithTrim(pk_oppAccount)
							&& nc.cmp.utils.StringUtil.isEmptyWithTrim(pk_oppAccountBill) || !nc.cmp.utils.StringUtil
							.isEmptyWithTrim(pk_oppAccount) && pk_oppAccount.equals(pk_oppAccountBill))) {
						throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"3607pubcmp_1", "3607pubcmp2-0013"));
					}
					
					String pk_trader = settlementBodyVO.getPk_trader();
					String pk_Billtrader = getPk_opp(billDetailVO);
					if(!(pk_trader == null && pk_Billtrader == null || pk_trader != null && pk_trader.equals(pk_Billtrader))){
						throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"3607pubcmp_1", "3607pubcmp2-0014"));
					}

					break;
				}

			}

		}

	}

	private static void validateOPPInfo(SettlementAggVO settlementAggVO, RecBillAggVO recBillAggVO)
			throws BusinessException {
		SettlementBodyVO[] settlementBodyVOs = (SettlementBodyVO[]) settlementAggVO.getChildrenVO();
		RecBillDetailVO[] billdaDetailVOs = (RecBillDetailVO[]) recBillAggVO.getChildrenVO();
		for (SettlementBodyVO settlementBodyVO : settlementBodyVOs) {
			String pk_billdetail = settlementBodyVO.getPk_billdetail();
			for (RecBillDetailVO billDetailVO : billdaDetailVOs) {
				
				if (Integer.valueOf(4).equals(billDetailVO.getObjecttype())) {
					continue;
				}
				
				if (pk_billdetail != null && pk_billdetail.equals(billDetailVO.getPk_recbill_detail())) {

					String pk_oppAccount = settlementBodyVO.getPk_oppaccount();
					String pk_oppAccountBill = billDetailVO.getPk_oppaccount();
					if (!(nc.cmp.utils.StringUtil.isEmptyWithTrim(pk_oppAccount)
							&& nc.cmp.utils.StringUtil.isEmptyWithTrim(pk_oppAccountBill) || !nc.cmp.utils.StringUtil
							.isEmptyWithTrim(pk_oppAccount) && pk_oppAccount.equals(pk_oppAccountBill))) {
						throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"3607pubcmp_1", "3607pubcmp2-0013"));
					}

					String pk_trader = settlementBodyVO.getPk_trader();
					String pk_Billtrader = getPk_opp(billDetailVO);
					if (!(pk_trader == null && pk_Billtrader == null || pk_trader != null
							&& pk_trader.equals(pk_Billtrader))) {
						throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"3607pubcmp_1", "3607pubcmp2-0014"));
					}

					break;
				}

			}

		}

	}
	
	private static String getPk_opp(SuperVO superVO){
		String pk_oppFiledName = null;
		Integer objecttype = (Integer)superVO.getAttributeValue("objecttype");
		if(BillEnumCollection.ObjType.CUSTOMER.VALUE.equals(objecttype)){
			pk_oppFiledName = "pk_customer";
		}else if(BillEnumCollection.ObjType.SUPPLIER.VALUE.equals(objecttype)){
			pk_oppFiledName = "pk_supplier";
		}else if(BillEnumCollection.ObjType.DEP.VALUE.equals(objecttype)){
			pk_oppFiledName = "pk_dept";
		}else if(BillEnumCollection.ObjType.PERSON.VALUE.equals(objecttype)){
			pk_oppFiledName = "pk_busiman";
		}
		
		if(pk_oppFiledName == null){
			return null;
		}else{
			return (String)superVO.getAttributeValue(pk_oppFiledName);
		}
		
		
	}
	
	/**
	 * 检查关账结账
	 * @param aggs
	 * @throws BusinessException
	 */
	public static void checkCloseSettleAccount(SettlementAggVO... aggs )throws BusinessException{
		// 关账结账判断
		Map<String,SettlementBodyVO> settleaccountMap = CmpUtils.makeMap();
		Map<String,SettlementHeadVO> closeaccountMap = CmpUtils.makeMap();
		for (SettlementAggVO settlementAggVO : aggs) {
			// 新增判断关账，结算判断结账。
			SettlementHeadVO head = (SettlementHeadVO)settlementAggVO.getParentVO();
			// 暂存的已结算结算信息相当于是新增的结算信息，要判断关账结账，根据结算日期判断
			if(head.getBusistatus()==null || BillEnumCollection.BillSatus.Tempeorary.VALUE.equals(head.getBusistatus())){
				String key = head.getPk_org()+head.getSettledate();
				closeaccountMap.put(key, head);
			}
			for (SettlementBodyVO body : (SettlementBodyVO[])settlementAggVO.getChildrenVO()) {
				String key = body.getPk_org()+body.getTallydate();
				// 过滤掉重复的组织和结算日期信息
				if(settleaccountMap.containsKey(key)){
					continue;
				}else{
					settleaccountMap.put(key, body);
				}
			}
		}
		// 关账校验
		for (SettlementHeadVO head : closeaccountMap.values()) {
			String period =  head.getSettledate().toStdString().substring(0,7);
			getAccQueryService() .checkCloseAccount(head.getPk_org(), period);
		}
		// 结账校验
		for (SettlementBodyVO body : settleaccountMap.values()) {
			CMPValidate.validate(body.getPk_org(), body.getTallydate());
		}
	}
	/**
	 * 校验供应商是否冻结
	 *
	 * @param aggvos
	 * @throws BusinessException
	 */
	public static void validateCrossRule(SettlementAggVO... aggvos) throws BusinessException {
		for (SettlementAggVO aggvo : aggvos) {
			ValidationFailure validationFailure = null;
			String pk_groupid = (String)aggvo.getParentVO().getAttributeValue("pk_group");
			String pk_org = (String)aggvo.getParentVO().getAttributeValue("pk_org");
			try {
				validationFailure = CrossRulePubServiceFacade.getInstance().validate(new CrossRuleParamContext(CmpConst.PK_BILLTYPEID_SETTLEMENT,pk_groupid,pk_org), aggvo);
			} catch (BusinessException e) {
				validationFailure = new ValidationFailure(e.getMessage());
			}
			if(validationFailure!=null)
			{
				throw new BusinessException(validationFailure.getMessage());
			}
		}
	}
	private static IAccQueryService accQueryService = null;
	private static IAccQueryService getAccQueryService() {
		if(accQueryService==null){
			accQueryService = NCLocator.getInstance().lookup(IAccQueryService.class);
		}
		return accQueryService;
	}
}