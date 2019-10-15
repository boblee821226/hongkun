package nc.impl.fa.closebook;

import hd.vo.pub.tools.PuPubVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.logging.Log;
import nc.bs.logging.Logger;
import nc.impl.am.bill.BatchDocBaseDAO;
import nc.impl.am.common.InSqlManager;
import nc.impl.am.db.DBAccessUtil;
import nc.impl.am.db.VOPersistUtil;
import nc.impl.am.db.processor.KeyValueMapProcessor;
import nc.itf.aim.pub.IEquipToFaService;
import nc.itf.bd.commoninfo.accperiod.IAccPeriodQueryServicer;
import nc.itf.fa.prv.ICloseBook;
import nc.itf.fa.service.IAlterService;
import nc.itf.fa.service.IAssetCombinService;
import nc.itf.fa.service.IAssetService;
import nc.itf.fa.service.IAssetSplitService;
import nc.itf.fa.service.ICloseBookService;
import nc.itf.fa.service.IDepService;
import nc.itf.fa.service.IDeployService;
import nc.itf.fa.service.IEvaluateService;
import nc.itf.fa.service.IFADateService;
import nc.itf.fa.service.ILogService;
import nc.itf.fa.service.IOptionService;
import nc.itf.fa.service.IPredevaluateService;
import nc.itf.fa.service.IQueryDepGatherInfo;
import nc.itf.fa.service.IReduceService;
import nc.itf.fi.pub.SysInit;
import nc.itf.org.IAccountingBookQryService;
import nc.itf.org.IOrgMetaDataIDConst;
import nc.itf.org.ISetOfBookQryService;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.pub.fa.card.AssetDateCalUtils;
import nc.pub.fa.common.consts.BillTypeConst;
import nc.pub.fa.common.consts.ClassIDConst;
import nc.pub.fa.common.consts.LogTypeConst;
import nc.pub.fa.common.consts.ParameterConst;
import nc.pub.fa.common.manager.FipManager;
import nc.pub.fa.common.util.StringUtils;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.pubitf.bd.accessor.IGeneralAccessor;
import nc.pubitf.fip.service.IFipMessageService;
import nc.pubitf.org.IAccountingBookPubService;
import nc.pubitf.org.ICloseAccPubServicer;
import nc.pubitf.org.ICloseAccQryPubServicer;
import nc.vo.am.common.BaseLockData;
import nc.vo.am.common.util.ArrayUtils;
import nc.vo.am.common.util.CollectionUtils;
import nc.vo.am.common.util.ExceptionUtils;
import nc.vo.am.common.util.MetaDataTools;
import nc.vo.am.common.util.MultiLanguageUtil;
import nc.vo.am.constant.BillStatusConst;
import nc.vo.am.constant.BillTypeConst_FA;
import nc.vo.am.constant.CommonKeyConst;
import nc.vo.am.constant.ModuleConst;
import nc.vo.am.manager.AccbookManager;
import nc.vo.am.manager.AccperiodVO;
import nc.vo.am.manager.CurrencyManager;
import nc.vo.am.manager.LockManager;
import nc.vo.am.manager.ParameterManager;
import nc.vo.am.manager.PeriodManager;
import nc.vo.am.manager.VOManager;
import nc.vo.am.proxy.AMProxy;
import nc.vo.am.pub.uap.ModuleInfoQuery;
import nc.vo.bd.accessor.IBDData;
import nc.vo.fa.assetcombin.AssetCombinBodyVO;
import nc.vo.fa.assetcombin.AssetCombinHeadVO;
import nc.vo.fa.assetsplit.AssetSplitBodyVO;
import nc.vo.fa.assetsplit.AssetSplitHeadVO;
import nc.vo.fa.closebook.CloseBookVO;
import nc.vo.fa.dep.gather.AggGatherVO;
import nc.vo.fa.dep.gather.GatherHVO;
import nc.vo.fa.log.LogVO;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.org.AccountingBookVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * @author lilc
 * @date 2010-4-7
 */
public class CloseBookImpl extends BatchDocBaseDAO implements ICloseBookService, ICloseBook {
	/** 日志 */
	private Log log = Log.getInstance(this.getClass()); 

	DBAccessUtil dbAccessUtil = new DBAccessUtil();
	VOPersistUtil persistUtil = new VOPersistUtil();

	@Override
	/**
	 * * 判断本月是否已结账
	 */
	public boolean isCurrentPeriodCloseBook(String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		// 有结账记录代表已经结账
		//如果传入组织为空，可以根据accyear、period和pk_accbook判断是否已经结账
		//String querySQL = " select closebook_flag from fa_closebook where dr=0 and pk_accbook = ? and accyear = ? and period = ? and pk_org = ? ";
		StringBuffer querySQL = new StringBuffer();
		querySQL.append(" select closebook_flag ");
		querySQL.append("from fa_closebook ");
		querySQL.append("where ");
		querySQL.append("dr=0 ");
		querySQL.append("and pk_accbook = ? ");
		querySQL.append("and accyear = ? ");
		querySQL.append("and period = ? ");
		if(StringUtils.isNotEmpty(pk_org)){
			querySQL.append("and pk_org = ? ");
		}
		
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(pk_accbook);
		parameter.addParam(accyear);
		parameter.addParam(period);
		if(StringUtils.isNotEmpty(pk_org)){
			parameter.addParam(pk_org);
		}
		
		return (Boolean) dbAccessUtil.executeQuery(querySQL.toString(), parameter, new ResultSetProcessor() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1190465517309149991L;

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				if (rs.next()) {
					int closebook_flag = rs.getInt(1);
					if (closebook_flag == 1) {
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	/**
	 * * 判断是否最大已结账月
	 */
	public boolean isMaxCloseBookPeriod(String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		String querySQL = " select max(accyear||period) from fa_closebook where pk_accbook = ? and pk_org = ? ";
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(pk_accbook);
		parameter.addParam(pk_org);

		String yearPeriod = (String) dbAccessUtil.executeQuery(querySQL, parameter, new ResultSetProcessor() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8285364931723672442L;

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getString(1);
				}
				return null;
			}
		});
		if (yearPeriod != null) {
			String maxCloseYear = yearPeriod.substring(0, 4);
			String maxClosePeriod = yearPeriod.substring(4, 6);
			if (maxCloseYear.equals(accyear) && maxClosePeriod.equals(period)) {
				return true;
			}
		}
		return false;
	}

	@Override
	/**
	 * * 是否最小未结账月
	 */
	public boolean isMinUnCloseBookPeriod(String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		StringBuffer querySql = new StringBuffer();
		querySql.append(" select max(accyear||period)");
		querySql.append(" from fa_closebook");
		querySql.append(" where");
		querySql.append(" dr = 0");
		querySql.append(" and pk_accbook = ?");
		if (pk_org != null) {
			querySql.append(" and pk_org = ?");
		}
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(pk_accbook);
		if (pk_org != null) {
			parameter.addParam(pk_org);
		}
		String yearPeriod = (String) dbAccessUtil.executeQuery(querySql.toString(), parameter, new ResultSetProcessor() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5607552427623302135L;

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getString(1);
				}
				return null;
			}
		});

		String minUnCloseYear;
		String minUnClosePeriod;

		PeriodManager periodManager = PeriodManager.getInstance();

		if (yearPeriod != null) {
			// 如果有结账记录，则取最大已结账月的下一个期间和传入的期间比较
			String maxCloseYear = yearPeriod.substring(0, 4);
			String maxClosePeriod = yearPeriod.substring(4);
			AccperiodVO accperiodVO = periodManager.getNextPeriod(pk_accbook, maxCloseYear, maxClosePeriod);
			minUnCloseYear = accperiodVO.getAccyear();
			minUnClosePeriod = accperiodVO.getPeriod();
		} else {
			// 如果没有结账记录，则用固定资产启用期间和传入的期间比较
			AccperiodVO accperiodVO = PeriodManager.getStartPeriod_FA(pk_accbook);
			if (accperiodVO == null) {
				// 财务核算账簿
				IGeneralAccessor accountbook_general = GeneralAccessorFactory.getAccessor(ClassIDConst.ASSET_ACCBOOK_ID);
				IBDData accountData = accountbook_general.getDocByPk(pk_accbook);
				throw new BusinessException(accountData.getName() + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0015")/*@res " 账簿 还没有启动 \n "*/);
			}
			minUnCloseYear = accperiodVO.getAccyear();
			minUnClosePeriod = accperiodVO.getPeriod();
		}
		if (minUnCloseYear.equals(accyear) && minUnClosePeriod.equals(period)) {
			return true;
		}
		return false;
	}

	@Override
	/**
	 * * 是否最小未结账月
	 */
	public boolean isMinUnCloseBookPeriod(String pk_org, String date, String pk_accbook) throws BusinessException {
		AccperiodVO accperiodVO = null;
		try{
			accperiodVO = PeriodManager.getInstance().queryPerriodMonth(pk_accbook, date);
		}catch(Exception e){
			// 不存在则模拟一个期间
			accperiodVO = AssetDateCalUtils.getTempPeriod(pk_accbook, UFDate.getDate(date));
		}
		String accyear = accperiodVO.getAccyear();
		String period = accperiodVO.getPeriod();
		return isMinUnCloseBookPeriod(pk_org, accyear, period, pk_accbook);
	}

	@Override
	/**
	 * * 查询最大已结账月
	 */
	public AccperiodVO queryMaxClosebookPeriod(String pk_org, String pk_accbook) throws BusinessException {
		String querySQL = " select max(accyear||period) from fa_closebook where pk_accbook = ? and pk_org = ? ";
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(pk_accbook);
		parameter.addParam(pk_org);

		String yearPeriod = (String) dbAccessUtil.executeQuery(querySQL, parameter, new ResultSetProcessor() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6214096199636550943L;

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getString(1);
				}
				return null;
			}
		});

		if (yearPeriod != null) {
			String maxCloseYear = yearPeriod.substring(0, 4);
			String maxClosePeriod = yearPeriod.substring(4,yearPeriod.length());
			AccperiodVO accperiodVO = new AccperiodVO();
			accperiodVO.setAccyear(maxCloseYear);
			accperiodVO.setPeriod(maxClosePeriod);
			return accperiodVO;
		}
		return null;
	}

	@Override
	/**
	 * * 查询最小未结账月
	 */
	public AccperiodVO queryMinUnClosebookPeriod(String pk_org, String pk_accbook) throws BusinessException {
		// 查询最大已结账月
		String querySQL = " select max(accyear||period) from fa_closebook where dr=0 and pk_accbook = ? ";
		// String querySQL =
		// " select max(accyear||period) from fa_closebook where pk_accbook = ? and pk_org = ? and dr=0 ";
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(pk_accbook);
		// 组织的过滤条件先去掉
		//		parameter.addParam(pk_org);

		String yearPeriod = (String) dbAccessUtil.executeQuery(querySQL, parameter, new ResultSetProcessor() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5372100284612109036L;

			@Override
			public Object handleResultSet(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getString(1);
				}
				return null;
			}
		});
		PeriodManager periodManager = PeriodManager.getInstance();
		if (yearPeriod != null) {
			// 如果有结账记录，则最小为结账月为其下一个期间
			String maxCloseYear = yearPeriod.substring(0, 4);
			String maxClosePeriod = yearPeriod.substring(4);
			AccperiodVO nextaccperiodVO = periodManager.getNextPeriod(pk_accbook, maxCloseYear, maxClosePeriod);
			String minUnCloseYear = nextaccperiodVO.getAccyear();
			String minUnClosePeriod = nextaccperiodVO.getPeriod();
			AccperiodVO accperiodVO = new AccperiodVO();
			accperiodVO.setAccyear(minUnCloseYear);
			accperiodVO.setPeriod(minUnClosePeriod);
			accperiodVO.setStartdate(nextaccperiodVO.getStartdate());
			accperiodVO.setEnddate(nextaccperiodVO.getEnddate());
			return accperiodVO;
		} else {
			// 如果没有结账记录则最小未结账月为固定资产启用期间
			return PeriodManager.getStartPeriod_FA(pk_accbook);
		}
	}

	@Override
	/**
	 * * 结账
	 */
	public void closeBook(String pk_group, String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		// 加锁
		try {
			String lockID = "CloseBook" + pk_org;
			LockManager.lock(new BaseLockData<String>(lockID));
		} catch (Exception e) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0016")/*@res " 有其他操作员正在对本组织结账或反结账，请稍后再试！"*/);
		}

		// 判断是否选择期间
		if(StringUtils.isEmpty(accyear) || StringUtils.isEmpty(period)){
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0017")/*@res " 请选择期间！"*/);
		}
		try {
			// 获取下一个期间, 判断是否设置了下一期间的期间方案
			AccperiodVO periodVO = PeriodManager.getInstance().getNextPeriod(pk_accbook, accyear, period);
			if (periodVO == null || periodVO.getAccyear() == null) {
				ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0018")/*@res " 没有设置下一个期间的期间方案，请先检查期间方案设置！"*/);
			}
		} catch (Exception e) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0018")/*@res " 没有设置下一个期间的期间方案，请先检查期间方案设置！"*/);
		}

		// 判断是否最小未结账月
		if (!isMinUnCloseBookPeriod(pk_org, accyear, period, pk_accbook)) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0019")/*@res " 本期间不是最小未结账月，不能结账！"*/);
		}

		//结账前处理
		CloseBookVO closeBookVO = createCloseBookVO(pk_group, pk_org, accyear, period, pk_accbook);
		String beanId = MetaDataTools.getBeanID(CloseBookVO.class);
		BusinessEvent beforeEvent = new BusinessEvent(beanId, IEventType.TYPE_ACCOUNT_BEFORE, closeBookVO);
		fireEvent(beforeEvent);


		// 更新设备财务数据在这里处理
		if (ModuleInfoQuery.isAIMEnabled()) {
			AMProxy.lookup(IEquipToFaService.class).updateFinanceDatasWhenCloseBook(pk_org);
		}
		// 判断所有单据是否已经审核通过
		StringBuffer errMsg = new StringBuffer();
		String[] tempBillCodes;
		String stringEnd = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0020")/*@res " 还未审核通过！"*/;
		
		/**
		 * HK
		 * 2019年10月15日 16点36分
		 * 判断 转固单 是否全部生成了 固定资产卡片。
		 */
		if(true) {
			// 会计期间 对应的 开始日期、结束日期
			String[] date = PeriodManager.getInstance().queryPerriodDate(pk_accbook, accyear, period);
			String begDate = date[0].substring(0,10);
			String endDate = date[1].substring(0,10);
			
			StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" max(zg.bill_code) ")	//0转固单号
					.append(",max(inv.code) ")		//1物料编码
					.append(",max(inv.name) ")		//2物料名称
					.append(",max(zgb.amount) ")	//3转固数量
					.append(",sum(nvl(cardhis.card_num,0)) ")	//4卡片数量
					.append(" from fa_transasset zg ")
					.append(" inner join fa_transasset_b zgb on zg.pk_transasset = zgb.pk_transasset ")
					.append(" inner join bd_material inv on zgb.pk_material = inv.pk_material ")
					.append(" left join fa_card card on (zgb.pk_transasset_b = card.pk_bill_b_src and card.dr = 0) ")
					.append(" left join fa_cardhistory cardhis on (card.pk_card = cardhis.pk_card and cardhis.laststate_flag='Y' and cardhis.dr = 0) ")
					.append(" where zg.dr = 0 and zgb.dr = 0  ")
					.append(" and zg.bill_status <> 6 ")
					.append(" and zg.pk_org = '").append(pk_org).append("' ")
					.append(" and substr(zg.business_date,1,10) between '").append(begDate).append("' and '").append(endDate).append("' ")
					.append(" group by zgb.pk_transasset_b ")
					.append(" having max(zgb.amount) <> sum(nvl(cardhis.card_num,0)) ")
					;
			BaseDAO dao = new BaseDAO();
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null && list.size()>0) {
				StringBuffer errMSg_temp = new StringBuffer();
	    		
	    		  for(int i=0;i<list.size();i++) {
	    			  Object[] obj = (Object[])list.get(i);
	    			  String billCode = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
	        		  String invCode = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
	        		  String invName = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
	        		  UFDouble zgNum = PuPubVO.getUFDouble_NullAsZero(obj[3]);
	        		  UFDouble kpNum = PuPubVO.getUFDouble_NullAsZero(obj[4]);
	        		  errMSg_temp
	        		  .append("【").append(billCode).append("】")
	        		  .append("【").append(invCode).append(invName).append("】")
	        		  .append("{转固单数量：").append(zgNum).append("}、")
	        		  .append("{卡片数量：").append(kpNum).append("}")
	        		  .append("\r\n")
	        		  ;
	    		  }
	    		  
	    		  errMsg
	    		  .append("以下转固单未生成资产卡片：\r\n")
	    		  .append(errMSg_temp);
			}
		}
		/***END***/
		
		// 判断的单据有
		// 资产变动单（所有变动单）
		// 盘点差异调整
		tempBillCodes = AMProxy.lookup(IAlterService.class).queryNotPassAlterBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0021")/*@res "变动单："*/, tempBillCodes, stringEnd);
		// 资产评估
		tempBillCodes = AMProxy.lookup(IEvaluateService.class).notApproveBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0022")/*@res "评估单："*/, tempBillCodes, stringEnd);
		// 资产减值
		tempBillCodes = AMProxy.lookup(IPredevaluateService.class).notApproveBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0023")/*@res "减值单："*/, tempBillCodes, stringEnd);
		// 资产拆分
		tempBillCodes = AMProxy.lookup(IAssetSplitService.class).notApproveBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0024")/*@res "拆分单："*/, tempBillCodes, stringEnd);
		// 资产合并
		tempBillCodes = AMProxy.lookup(IAssetCombinService.class).notApproveBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0025")/*@res "合并单："*/, tempBillCodes, stringEnd);
		// 资产调出
		tempBillCodes = AMProxy.lookup(IDeployService.class).queryNotPassDeployOutBills(pk_org, pk_accbook, accyear,
				period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0026")/*@res "调出单："*/, tempBillCodes, stringEnd);
		// 已经确认本月调入的调入单（调入日期为本会计期间）；调入单的调入日期为空时，则可以结账；
		tempBillCodes = AMProxy.lookup(IDeployService.class).queryNotPassDeployInBills(pk_org, pk_accbook, accyear,
				period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0027")/*@res "调入单："*/, tempBillCodes, stringEnd);

		// “确认本月调入字段”为Y的调出单对应的未审核通过的调入单。调入单必须审核通过才能结账
		Map<String, String> billCodeMap = AMProxy.lookup(IDeployService.class).queryNotPassCorrespondingDeployInBills(
				pk_org, pk_accbook, accyear, period);
		// 处理消息
		processDeployBillCodeMap(errMsg, billCodeMap);

		// 资产减少
		tempBillCodes = AMProxy.lookup(IReduceService.class).queryNotPassReduceBills(pk_org, pk_accbook, accyear,
				period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0028")/*@res "减少单："*/, tempBillCodes, stringEnd);

		if (errMsg.length() > 0) {
			errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0029")/*@res " 不能结账！"*/);
			ExceptionUtils.asBusinessException(errMsg.toString());
		}

		if(ParameterManager.getParaBoolean(pk_accbook, ParameterConst.IS_NEED_DEPR).booleanValue()){
			// 判断是否已经计提折旧
			//			boolean isDepComplete = AMProxy.lookup(ILogService.class).isDepComplete(pk_org, pk_accbook, accyear, period);
			//			if (!isDepComplete) {
			//				ExceptionUtils.asBusinessException("本期间还未计提折旧，请先计提折旧！");
			//			}
			LogVO logVO = AMProxy.lookup(ILogService.class).queryDepLogVO(pk_org, pk_accbook, accyear, period);
			if(logVO == null){
				ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0030")/*@res "本期间还未计提折旧，请先计提折旧！"*/);
			} else {
				if (logVO.getRedep_flag().booleanValue()) {
					ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0031")/*@res "当月已计提，但发生了影响折旧的操作，需要重新计提！"*/);
				}
			}
		}
		// 结账，更新卡片表
		// 更新月末为下月月初
		// 插入下月月末数据
		AMProxy.lookup(IAssetService.class).disposeAssetDataWhenCloseBook(pk_org, pk_accbook, accyear, period);
		// 插入结账记录
		insertCloseBookInfo(pk_group, pk_org, accyear, period, pk_accbook);
		// 处理参数和汇总项目
		AMProxy.lookup(IOptionService.class).insertNextMounthOption(period, accyear, pk_accbook);

		// 回写总账结账状态
		// 查询当前财务核算账簿的总账启用期间
		String pk_accountBookPeriod = AMProxy.lookup(IAccountingBookPubService.class).queryAccountBookPeriodByAccountingBookID(pk_accbook);
		if (StringUtils.isNotEmpty(pk_accountBookPeriod)) {
			// 获取总账对应的期间日期
			AccperiodVO glStartPeriod = PeriodManager.queryPeriodByAccperiodMonth(pk_accountBookPeriod);
			// 判断数据是否完整
			if (glStartPeriod != null && StringUtils.isNotEmpty(glStartPeriod.getAccyear()) && StringUtils.isNotEmpty(glStartPeriod.getPeriod())) {
				// 构造当前结账期间的    会计年和会计月数组
				String[] closeBookPeriod = new String[2];
				closeBookPeriod[0] = accyear;
				closeBookPeriod[1] = period;
				// 构造总账启用的期间的    会计年和会计月数组
				String[] glPeriod = new String[2];
				glPeriod[0] = glStartPeriod.getAccyear();
				glPeriod[1] = glStartPeriod.getPeriod();
				// 期间的比较结果    返回   < 0  表示总账的启用期间晚于固定资产结账的期间
				int compareResult = PeriodManager.compareTwoPeriods(closeBookPeriod, glPeriod);
				// 判断是否启用总账   并且   结账期间已经启用了总账
				if(ModuleInfoQuery.isModuleEnabledBySysCode(ModuleConst.GL) && compareResult >= 0){
					// 取得当前账簿的账簿类型
					AccountingBookVO[] accountingBooks = AMProxy.lookup(IAccountingBookQryService.class).queryVOsByPks(new String[]{pk_accbook});
					if (ArrayUtils.isNotEmpty(accountingBooks)) {
						String pk_setofBook = accountingBooks[0].getPk_setofbook();
						// 取得当前账簿的期间方案
						String pk_accPeriodScheme = AMProxy.lookup(ISetOfBookQryService.class).querySetOfBookVOByPK(pk_setofBook).getPk_accperiodscheme();
						String yearMth = accyear + "-" + period;
						// 取得查询的期间的主键
						String pk_accperiodmonth = AMProxy.lookup(IAccPeriodQueryServicer.class).getAccByAccSchemePKAndYearMth(pk_accPeriodScheme, yearMth);
						// 调用总账接口回写
						AMProxy.lookup(ICloseAccPubServicer.class).account(ModuleConst.FA_FUNCCODE, pk_org, pk_accbook, pk_accperiodmonth);
					}
				}
			}
		}

		// 结账后插件处理
		BusinessEvent afterEvent = new BusinessEvent(beanId, IEventType.TYPE_ACCOUNT_AFTER, closeBookVO);
		fireEvent(afterEvent);
	}

	private CloseBookVO createCloseBookVO(String pk_group, String pk_org, String accyear, String period, String pk_accbook){
		CloseBookVO closebookVO = new CloseBookVO();
		closebookVO.setPk_org(pk_org);
		closebookVO.setPk_accbook(pk_accbook);
		closebookVO.setPk_group(pk_group);
		closebookVO.setAccyear(accyear);
		closebookVO.setPeriod(period);
		return closebookVO;
	}
	/**
	 * 提示语句处理
	 *
	 * @param msg
	 * @param stringHead
	 * @param billCodes
	 */
	private void processMessage(StringBuffer msg, String stringHead, String[] billCodes, String stringEnd) {
		if (billCodes != null && billCodes.length > 0) {
			msg.append(stringHead);
			for (int i = 0; i < billCodes.length; i++) {
				msg.append(billCodes[i]);
				if (i < billCodes.length - 1) {
					msg.append(";");
				}
			}
			msg.append(stringEnd);
		}
	}

	/**
	 * 处理“本月确认调入”的调拨单据的提示信息
	 *
	 * @param msgBuffer
	 * @param deployBillCodeMap
	 * @author yanjq
	 * @date 2010-5-25
	 */
	private void processDeployBillCodeMap(StringBuffer msgBuffer, Map<String, String> deployBillCodeMap) {

		for (Map.Entry<String, String> entry : deployBillCodeMap.entrySet()) {
			msgBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0032")/*@res "调出单"*/ + entry.getKey() + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0033")/*@res " 对应的调入单"*/ + entry.getValue() + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0034")/*@res "还未审核通过！\n"*/);
		}
	}

	/**
	 * 插入结账记录
	 *
	 * @param pk_group
	 * @param pk_org
	 * @param accyear
	 * @param period
	 * @param pk_accbook
	 * @throws BusinessException
	 */
	private void insertCloseBookInfo(String pk_group, String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		CloseBookVO closeBookVO = new CloseBookVO();
		closeBookVO.setPk_group(pk_group);
		closeBookVO.setAccyear(accyear);
		closeBookVO.setPeriod(period);
		closeBookVO.setPk_accbook(pk_accbook);
		closeBookVO.setPk_org(pk_org);
		closeBookVO.setClosebook_flag(1);
		VOPersistUtil.insert(closeBookVO); 

		// getBusiLogUtil(MetaDataTools.getBeanID(closeBookVO.getClass())).writeBusiLog(FABusiOperatorConst.CLOSEBOOK, null, closeBookVO);
	}

	@Override
	/**
	 * * 反结账
	 */
	public void deCloseBook(String pk_group, String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		// 加锁
		try {
			String lockID = "CloseBook" + pk_org;
			LockManager.lock(new BaseLockData<String>(lockID));
		} catch (Exception e) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0016")/*@res " 有其他操作员正在对本组织结账或反结账，请稍后再试！"*/);
		}
		// 是否最大已结账月
		if (!isMaxCloseBookPeriod(pk_org, accyear, period, pk_accbook)) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0035")/*@res " 本期间不是最大已结账月，不能反结账！"*/);
		}
		// 反结账校验总账是否关账 
		if(ModuleInfoQuery.isModuleEnabledBySysCode(ModuleConst.GL)){
			String pk_glbook = ParameterManager.getParaString(pk_org, ParameterConst.GL_CHECKACCBOOK_WHENCLOSE);
			if (StringUtils.isNotEmpty(pk_glbook) && pk_accbook.equals(pk_glbook)) {
				String sCurrYM = accyear + "-" + period;

				boolean isClose = AMProxy.lookup(ICloseAccQryPubServicer.class).isCloseByAccountBookId(pk_glbook, sCurrYM);
				if (isClose) {
					/*
					 * @res "总账该月份已关账，固定资产不能反结账！"
					 */
					ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0036")/*@res "总账该月份已关账，固定资产不能反结账！"*/);
				}
			}
		} 
		// 反结账前插件处理
		CloseBookVO closeBookVO = createCloseBookVO(pk_group, pk_org, accyear, period, pk_accbook);
		String beanId = MetaDataTools.getBeanID(CloseBookVO.class);
		BusinessEvent beforeEvent = new BusinessEvent(beanId, IEventType.TYPE_REACCOUNT_BEFORE, closeBookVO);
		fireEvent(beforeEvent);

		// 获取下一个期间
		AccperiodVO periodvo = PeriodManager.getInstance().getNextPeriod(pk_accbook, accyear, period);
		String nextaccyear = periodvo.getAccyear();
		String nextPeriod = periodvo.getPeriod();

		// 判断折旧是否制单
		if (FipManager.isFipMakeVoucher(pk_org, nextaccyear, nextPeriod, pk_accbook).booleanValue()) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0039")/*@res "本期间折旧已经制单，不能反结账！"*/);
		}

		//删除最小未结帐月凭证
		GatherHVO[] hvos = AMProxy.lookup(IQueryDepGatherInfo.class).queryDepGatherInfos(pk_org, pk_accbook, nextaccyear, nextPeriod);
		if (hvos != null && hvos.length > 0) {
			ArrayList<FipMessageVO> messageList = new ArrayList<FipMessageVO>();
			for (int i = 0; i < hvos.length; i++) {
				GatherHVO hvo = hvos[i];
				AggGatherVO gatherVO = new AggGatherVO();
				gatherVO.setParent(hvo);
				FipMessageVO messageVO = createFipMessage(gatherVO, null, FipMessageVO.MESSAGETYPE_DEL);
				messageList.add(messageVO);
			}
			AMProxy.lookup(IFipMessageService.class).sendMessages(messageList.toArray(new FipMessageVO[0]));
		}

		// 判断是否所有单据都处于自由态
		StringBuffer errMsg = new StringBuffer();
		String[] tempBillCodes;
		String stringEnd = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0037")/*@res " 不是自由态！"*/;
		// 判断的单据有
		// 资产变动单（所有变动）
		// 盘点差异调整
		tempBillCodes = AMProxy.lookup(IAlterService.class).queryNotFreeAlterBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0021")/*@res "变动单："*/, tempBillCodes, stringEnd);
		// 资产评估
		tempBillCodes = AMProxy.lookup(IEvaluateService.class).notFreeCheckBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0022")/*@res "评估单："*/, tempBillCodes, stringEnd);
		// 资产减值
		tempBillCodes = AMProxy.lookup(IPredevaluateService.class).notFreeCheckBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0023")/*@res "减值单："*/, tempBillCodes, stringEnd);
		// 资产拆分
		tempBillCodes = AMProxy.lookup(IAssetSplitService.class).notFreeCheckBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0024")/*@res "拆分单："*/, tempBillCodes, stringEnd);
		// 资产合并
		tempBillCodes = AMProxy.lookup(IAssetCombinService.class).notFreeCheckBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0025")/*@res "合并单："*/, tempBillCodes, stringEnd);
		// // 资产盘点
		// tempBillCodes =
		// AMProxy.lookup(IInventoryService.class).queryNotFreeAlterBills(pk_org,
		// accyear, nextPeriod);
		// processMessage(errMsg, "盘点单：", tempBillCodes, stringEnd);
		// 资产调出
		tempBillCodes = AMProxy.lookup(IDeployService.class).queryNotFreeDeployOutBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0026")/*@res "调出单："*/, tempBillCodes, stringEnd);
		// 资产减少
		tempBillCodes = AMProxy.lookup(IReduceService.class).queryNotFreeReduceBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0028")/*@res "减少单："*/, tempBillCodes, stringEnd);

		if (errMsg.length() > 0) {
			errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0038")/*@res " 不能反结账！"*/);
			ExceptionUtils.asBusinessException(errMsg.toString());
		}

		// 删除自由态单据
		// 删除的单据有
		// 资产变动单（所有变动）
		// 盘点差异调整
		AMProxy.lookup(IAlterService.class).deleteAllFreeAlterBills(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// 资产评估
		AMProxy.lookup(IEvaluateService.class).deleteAllFreeCheck(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// 资产减值
		AMProxy.lookup(IPredevaluateService.class).deleteAllFreeCheck(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// 资产拆分
		AMProxy.lookup(IAssetSplitService.class).deleteAllFreeCheck(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// 资产合并
		AMProxy.lookup(IAssetCombinService.class).deleteAllFreeCheck(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// 资产盘点
		// AMProxy.lookup(IInventoryService.class).deleteAllFreeAlterBills(pk_org,
		// accyear, nextPeriod);
		// 资产调出
		AMProxy.lookup(IDeployService.class).deleteAllFreeDeployBills(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// 资产减少
		AMProxy.lookup(IReduceService.class).deleteAllFreeReduceBills(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// 删除折旧会计凭证

		// 删除折旧汇总信息
		AMProxy.lookup(IDepService.class).deleteGatherInfo(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// 删除折旧日志
		LogVO logVO = new LogVO();
		logVO.setPk_accbook(pk_accbook);
		logVO.setAccyear(nextaccyear);
		logVO.setPeriod(nextPeriod);
		logVO.setLog_type(LogTypeConst.dep);
		logVO.setPk_org(pk_org);
		AMProxy.lookup(ILogService.class).deleteLogs(logVO);
		// 更新卡片,更新下月月初数据为本月月末数据，删除下月月末数据，保留下月录入的卡片。
		AMProxy.lookup(IAssetService.class).disposeAssetDataWhenUnCloseBook(pk_org, pk_accbook, accyear, period);

		// 删除参数设置会汇总项目
		AccperiodVO accperiodVO = PeriodManager.getInstance().getNextPeriod(pk_accbook, accyear, period);
		if (accperiodVO == null)
			return;
		AMProxy.lookup(IOptionService.class).deleteMounthOption(accperiodVO.getPeriod(), accperiodVO.getAccyear(),
				pk_accbook);

		// 删除结账记录
		deleteCloseBookInfo(pk_group, pk_org, accyear, period, pk_accbook);

		// 更新设备财务数据
		if (ModuleInfoQuery.isAIMEnabled()) {
			AMProxy.lookup(IEquipToFaService.class).updateFinanceDatasWhenUnCloseBook(pk_org);
		}
		// 判断当前的固定资产是否回写了结账状态的表
		String[] isClose = AMProxy.lookup(ICloseAccQryPubServicer.class)
				.isEndaccByAccountBookIdAndModuleId(new String[]{pk_accbook}, ModuleConst.FA_FUNCCODE, accyear + "-" + period);
		// 如果该账簿已经会写过总账标志-------如果之前没会写过，例如是升级的环境，这里处理的话可能有报错
		if (isClose != null && isClose.length > 0) {
			// 回写总账结账状态
			// 查询当前财务核算账簿的总账启用期间
			String pk_accountBookPeriod = AMProxy.lookup(IAccountingBookPubService.class).queryAccountBookPeriodByAccountingBookID(pk_accbook);
			if (StringUtils.isNotEmpty(pk_accountBookPeriod)) {
				// 获取总账对应的期间日期
				AccperiodVO glStartPeriod = PeriodManager.queryPeriodByAccperiodMonth(pk_accountBookPeriod);
				if (glStartPeriod != null && StringUtils.isNotEmpty(glStartPeriod.getAccyear()) && StringUtils.isNotEmpty(glStartPeriod.getPeriod())) {
					// 构造当前反结账期间的    会计年和会计月数组
					String[] closeBookPeriod = new String[2];
					closeBookPeriod[0] = accyear;
					closeBookPeriod[1] = period;
					// 构造总账启用的期间的    会计年和会计月数组
					String[] glPeriod = new String[2];
					glPeriod[0] = glStartPeriod.getAccyear();
					glPeriod[1] = glStartPeriod.getPeriod();
					// 期间的比较结果    返回   < 0  表示总账的启用期间晚于固定资产反结账的期间
					int compareResult = PeriodManager.compareTwoPeriods(closeBookPeriod, glPeriod);
					// 判断是否启用总账   并且   反结账期间已经启用了总账
					if(ModuleInfoQuery.isModuleEnabledBySysCode(ModuleConst.GL) && compareResult >= 0){
						// 取得当前账簿的账簿类型
						AccountingBookVO[] accountingBooks = AMProxy.lookup(IAccountingBookQryService.class).queryVOsByPks(new String[]{pk_accbook});
						if (ArrayUtils.isNotEmpty(accountingBooks)) {
							String pk_setofBook = accountingBooks[0].getPk_setofbook();
							// 取得当前账簿的期间方案
							String pk_accPeriodScheme = AMProxy.lookup(ISetOfBookQryService.class).querySetOfBookVOByPK(pk_setofBook).getPk_accperiodscheme();
							String yearMth = accyear + "-" + period;
							// 取得查询的期间的主键
							String pk_accperiodmonth = AMProxy.lookup(IAccPeriodQueryServicer.class).getAccByAccSchemePKAndYearMth(pk_accPeriodScheme, yearMth);
							// 调用总账接口回写
							AMProxy.lookup(ICloseAccPubServicer.class).reAccount(ModuleConst.FA_FUNCCODE, pk_org, pk_accbook, pk_accperiodmonth);
						}
					}
				}
			}
		}
		//反结账后处理
		BusinessEvent afterEvent = new BusinessEvent(beanId, IEventType.TYPE_REACCOUNT_AFTER, closeBookVO);
		fireEvent(afterEvent);
	}

	protected FipMessageVO createFipMessage(AggGatherVO gatherVO, UFDate voucherDate, int messagetype) throws BusinessException {
		GatherHVO headVO = (GatherHVO) gatherVO.getParentVO();

		headVO.setPk_currency(CurrencyManager.getCurrencyPKByAccbook(headVO.getPk_accbook()));
		FipMessageVO fipMessageVO = new FipMessageVO();
		fipMessageVO.setMessagetype(messagetype);

		String bill_type = BillTypeConst.DEPANDGATHER;
		FipRelationInfoVO infoVO = new FipRelationInfoVO();
		infoVO.setPk_group(headVO.getPk_group());
		infoVO.setPk_org(headVO.getPk_org());
		infoVO.setPk_system(ModuleConst.FA);
		infoVO.setPk_billtype(bill_type);
		infoVO.setRelationID(headVO.getPrimaryKey());
		infoVO.setPk_operator(InvocationInfoProxy.getInstance().getUserId());
		if (messagetype == FipMessageVO.MESSAGETYPE_ADD) {
			infoVO.setBusidate(voucherDate);
			fipMessageVO.setBillVO(gatherVO);
		}
		fipMessageVO.setMessageinfo(infoVO);
		return fipMessageVO;
	}

	/**
	 * 删除结账信息
	 *
	 * @param pk_group
	 * @param pk_org
	 * @param accyear
	 * @param period
	 * @param pk_accbook
	 * @throws BusinessException
	 */
	private void deleteCloseBookInfo(String pk_group, String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		String deleteSQL = " delete from fa_closebook where dr = 0 and pk_accbook = ? and accyear = ? and period = ? and pk_org = ?";
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(pk_accbook);
		parameter.addParam(accyear);
		parameter.addParam(period);
		parameter.addParam(pk_org);
		dbAccessUtil.executeUpdate(deleteSQL, parameter, true);
		//		CloseBookVO closeBookVO = createCloseBookVO(pk_group, pk_org, accyear, period, pk_accbook);
		// getBusiLogUtil(MetaDataTools.getBeanID(closeBookVO.getClass())).writeBusiLog(FABusiOperatorConst.DECLOSEBOOK, null, closeBookVO);
	}


	@SuppressWarnings("unchecked")
	@Override
	/**
	 * * 查询所有已结账月
	 */
	public CloseBookVO[] queryAllCloseBookVO(String pk_group, String pk_org, String pk_accbook)
			throws BusinessException {
		String condition = " dr = 0 and pk_accbook = ? and pk_org = ?";
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(pk_accbook);
		parameter.addParam(pk_org);
		Collection<CloseBookVO> result = dbAccessUtil.retrieveByClause(CloseBookVO.class, condition, parameter);
		if (result != null) {
			return result.toArray(new CloseBookVO[0]);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * * 判断是否最小未结账月
	 * 。备注：服务方法不应该提调用者抛出异常，但由于抛出异常的方法是公用的，目前没有一个适合的地方放置，暂时放在这里。added by yanjq
	 */
	public void checkMinUnCloseBookPeriod(AggregatedValueObject billVO) throws BusinessException {
		SuperVO headVO = (SuperVO) billVO.getParentVO();
		UFDate bizDate = (UFDate) headVO.getAttributeValue(CommonKeyConst.business_date);
		String pk_org = (String) headVO.getAttributeValue(CommonKeyConst.pk_org);
		String pk_accbook = (String) headVO.getAttributeValue(CommonKeyConst.pk_accbook);
		String bill_type = (String) headVO.getAttributeValue(CommonKeyConst.bill_type);
		Integer bill_status = (Integer) headVO.getAttributeValue(CommonKeyConst.bill_status);
		// 如果pk_accbook是业务账簿，则需要查出所有的账簿来进行判断

		List<String> pkCardList = new ArrayList<String>();
		for (SuperVO bodyVO : (SuperVO[]) billVO.getChildrenVO()) {
			String pk_card = (String) bodyVO.getAttributeValue(CommonKeyConst.pk_card);
			if(StringUtils.isNotEmpty(pk_card)){
				pkCardList.add(pk_card);
			}
			// pkCardList.add((String) bodyVO.getAttributeValue(CommonKeyConst.pk_card));
		}
		Set<String> pk_accbookSet = null;
		if (AccbookManager.isBizAccbook(pk_accbook)) {
			Map<String, List<String>> map = null;
			//减少单 或者 调出单 弃审操作特殊，因为已经减少，所以要查询所有账簿
			if ((BillTypeConst_FA.REDUCE.equals(bill_type))
					&& bill_status == BillStatusConst.check_pass) {
				map = AMProxy.lookup(IAssetService.class).queryCardNAssociateAccbooks(pkCardList.toArray(new String[0]));
				String billCode = (String) headVO.getAttributeValue(CommonKeyConst.bill_code);
				filterCurrentBillPkaccbook(map, billCode);
			} else if( BillTypeConst_FA.DEPLOY_OUT.equals(bill_type)
					&& bill_status == BillStatusConst.check_pass){
				map = AMProxy.lookup(IAssetService.class).queryCardAssociateAccbooks(pkCardList.toArray(new String[0]));
			} // 业务变动单需要校验组织下所有相关账簿的最小未结账月。
			else if (BillTypeConst_FA.ALTER.equals(bill_type)) {
				map = AMProxy.lookup(IAssetService.class).queryCardAssociateAccbooks(pkCardList.toArray(new String[0]));
			} else {
				map = AMProxy.lookup(IAssetService.class).queryNotReducedAccbook(
						CollectionUtils.toArray(pkCardList));
			} 

			pk_accbookSet = new HashSet<String>();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				CollectionUtils.addAll(pk_accbookSet, entry.getValue());
			}

			// add by kongfy 2011-08-17
			// 资产调出
			// 如果从卡片历史表得不到账簿信息，通过组织查询所有可用账簿
			if( BillTypeConst_FA.DEPLOY_OUT.equals(bill_type)
					&& bill_status != BillStatusConst.check_pass){
				if (CollectionUtils.isEmpty(pk_accbookSet)) {
					pk_accbookSet = AccbookManager.queryBookIDsByOrg(pk_org,bizDate.toString());
				}
			}

		} else {
			pk_accbookSet = new HashSet<String>();
			pk_accbookSet.add(pk_accbook);
		}

		if (CollectionUtils.isEmpty(pk_accbookSet)) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0040")/*@res "在业务日期"*/ + bizDate + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0041")/*@res " 找不到任何账簿!"*/);
		}

		//查询财务账簿对应的名称
		Map<String, String> accbookNameByPK = AccbookManager.queryAccbookNamesByOrg(pk_org, bizDate.toString());

		if (accbookNameByPK == null || accbookNameByPK.size() == 0) {
			String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"closebook_0", "02012055-0042", null,
					new String[] { bizDate.toString()})/*
					 * @res
					 * 该组织在业务日期:{0}没有启用账簿! 
					 */;

			ExceptionUtils.asBusinessException(msg);
		} else {
			for (String pk_book : pk_accbookSet) {
				boolean isMinPeriod = isMinUnCloseBookPeriod(pk_org, bizDate.toString(), pk_book);

				if (!isMinPeriod) {
					String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
							"closebook_0", "02012055-0044", null,
							new String[] { accbookNameByPK.get(pk_book),bizDate.toString()})/*
							 * @res
							 * 账簿 [{0}] 在日期{1}不是最小未结账月!
							 */;

					ExceptionUtils.asBusinessException(msg);
				}
			}
		}
	}

	/**
	 * <p>
	 * 过滤非当前减少单处理的账簿数据。
	 * 
	 * <p>
	 * 业务减少单会将指定卡片在所有未被减少的账簿下统一做减少，取消审批时就不需要校验之前已经被减少的账簿了。<br>
	 * 此处通过固定资产卡片的日志信息取得非当前减少单处理的账簿，并且过滤掉。
	 * 
	 * @param map
	 * @param billCode
	 */
	@SuppressWarnings("unchecked")
	private void filterCurrentBillPkaccbook(Map<String, List<String>> map, String billCode) {

		if (org.apache.commons.collections.MapUtils.isNotEmpty(map) 
				&& StringUtils.isNotEmpty(billCode)) {
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				// 取得非当前减少单处理的账簿。
				String pk_card = entry.getKey();
				StringBuffer sqlBuff = new StringBuffer();
				sqlBuff.append(" select " + LogVO.PK_ACCBOOK + " ")
				.append("   from " + LogVO.getDefaultTableName() + " ")
				.append("  where " + LogVO.PK_BUSINESS + " = " + pk_card + " ")
				.append("    and " + LogVO.BILL_CODE + " <> '" + billCode + "' ")
				.append("    and " + LogVO.BILL_STATUS + " = " + BillStatusConst.check_pass + " ")
				.append("    and " + LogVO.BILL_TYPE + " = '" + BillTypeConst.REDUCE + "' ");
				List<String> notCurrentBillPkaccbooks = null;
				try {
					notCurrentBillPkaccbooks = new DBAccessUtil().executeQuerySQL(sqlBuff.toString());
				} catch (BusinessException e) {
					Logger.error(e.getMessage(), e);
				}

				if (notCurrentBillPkaccbooks != null && !notCurrentBillPkaccbooks.isEmpty()) {
					List<String> pk_accbook = entry.getValue();
					pk_accbook.removeAll(notCurrentBillPkaccbooks);
				}
			}
		}
	}

	@Override
	public void isMinUnCloseBooksPeriod(String pk_org, String date, String[] pk_accbooks) throws BusinessException {
		Map<String, String> key_multi = MultiLanguageUtil.getMultiLangByClassId(pk_accbooks, IOrgMetaDataIDConst.ACCOUNTINGBOOK);
		for (String tempPk_accbook : pk_accbooks) {
			boolean isMin = isMinUnCloseBookPeriod(pk_org, date, tempPk_accbook);
			if (!isMin) {
				// 根据账簿主键找到其名称
				String bookName =key_multi.get(tempPk_accbook);

				String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"closebook_0", "02012055-0047", null,
						new String[] { bookName,date})/*
						 * @res
						 * 账簿{0}:在日期{1}不是最小未结账月!
						 */;

				ExceptionUtils.asBusinessException(msg);
			}
		}
	}

	@Override
	public Map<String, Boolean> isMinUnCloseBooksQuery(String pk_org, String accyear, String period,
			String[] pk_accbooks) throws BusinessException {
		Map<String, Boolean> minUnClose = new HashMap<String, Boolean>();
		for (String tempPk_accbook : pk_accbooks) {
			// 查询是否最小未结账月
			boolean isMin = isMinUnCloseBookPeriod(pk_org, accyear, period, tempPk_accbook);
			if (!isMin) {
				minUnClose.put(tempPk_accbook, false);
			} else {
				minUnClose.put(tempPk_accbook, true);
			}
		}
		return minUnClose;
	}

	/**
	 * 判断拆分建卡日期是否处于最小未结账月
	 */
	@Override
	public void checkCreateCardMinUnCloseBookPeriodForAssetSplit(AggregatedValueObject billVO) throws BusinessException {
		AssetSplitHeadVO headVO = (AssetSplitHeadVO) billVO.getParentVO();
		AssetSplitBodyVO[] childVOs = (AssetSplitBodyVO[]) billVO.getChildrenVO();
		String pk_org = headVO.getPk_org();

		// 第一行的建卡日期
		UFDate createCard = (UFDate) childVOs[0].getAttributeValue(CommonKeyConst.business_date);

		// 取得每行的建卡日期set
		Set<UFDate> busiDates = VOManager.getAttributeValueSet(childVOs, CommonKeyConst.business_date);


		// 当前行的资产类别
		String errorMsg = "";

		// 得到本资产类别关联的所有账簿
		List<String> pk_accbooks = AMProxy.lookup(IAssetService.class).queryNotReducedAccbook(headVO.getPk_card()).get(
				headVO.getPk_card());

		if (pk_accbooks == null || pk_accbooks.size() == 0) {
			return;
		}
		//查询财务账簿对应的名称
		Map<String, String> accbookNameByPK = AccbookManager.queryAccbookNamesByOrg(pk_org, createCard.toString());

		for (String pk_accbook : pk_accbooks) {
			// 得到最小未结账月
			AccperiodVO minPeriod = queryMinUnClosebookPeriod(pk_org, pk_accbook);
			if (minPeriod == null) {
				continue;
			}
			// 本期间开始日期
			UFDate beginDate = minPeriod.getStartdate();
			// 本期间结束日期
			UFDate endDate = minPeriod.getEnddate();
			for (UFDate busiDate : busiDates) {
				// 拆分出的卡片建卡日期是否在 本期间日期之间
				if ((busiDate.afterDate(endDate) || busiDate.beforeDate(beginDate))) {

					String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0", "02012055-0050",
							null, new String[] { busiDate.toString(), accbookNameByPK.get(pk_accbook) })/*
							 * @res
							 * 拆分出的资产的建卡日期
							 * {0}
							 * 在账簿[{1}]中不是最小未结账月
							 * 。
							 */;

					errorMsg += msg;
				}
			}
			// 拆分出的卡片建卡日期是否在 本期间日期之间
			//			if ((createCard.afterDate(endDate) || createCard.beforeDate(beginDate))) {
			//				
			//				String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
			//					      "closebook_0", "02012055-0050", null,
			//					      new String[] { createCard.toString() , accbookNameByPK.get(pk_accbook)})/*
			//					              * @res
			//					              * 拆分出的资产的建卡日期{0}在账簿[{1}]中不是最小未结账月。
			//					              */;
			//				
			//				errorMsg += msg;
			//			} else if (busiDates.size() > 1){ // 表体的建卡日期数大于1
			//				busiDates.remove(createCard);
			//				for (UFDate busiDate : busiDates) {
			//					String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
			//						      "closebook_0", "02012055-0050", null,
			//						      new String[] { busiDate.toString() , accbookNameByPK.get(pk_accbook)})/*
			//						              * @res
			//						              * 拆分出的资产的建卡日期{0}在账簿[{1}]中不是最小未结账月。
			//						              */;
			//					errorMsg += msg;
			//				}
			//				
			//			}
		}

		if (StringUtils.isNotEmpty(errorMsg)) {
			ExceptionUtils.asBusinessException(errorMsg.toString());
		}
	}

	@Override
	public void checkCreateCardMinUnCloseBookPeriodForAssetCombin(AggregatedValueObject billVO) throws BusinessException {
		AssetCombinHeadVO headVO = (AssetCombinHeadVO) billVO.getParentVO();
		AssetCombinBodyVO[] childVOs = (AssetCombinBodyVO[]) billVO.getChildrenVO();
		String pk_org = headVO.getPk_org();

		// 每行的建卡日期
		UFDate createCard = (UFDate) headVO.getAttributeValue(AssetCombinHeadVO.CREATECARD_DATE);

		// 当前行的资产类别
		String errorMsg = "";

		// 得到本资产类别关联的所有账簿
		List<String> pk_accbooks = null;
		if (ArrayUtils.isNotEmpty(childVOs)) {
			pk_accbooks = AMProxy.lookup(IAssetService.class).queryNotReducedAccbook(childVOs[0].getPk_card()).get(
					childVOs[0].getPk_card());
		}
		if (pk_accbooks == null || pk_accbooks.size() == 0) {
			return;
		}

		//查询财务账簿对应的名称
		Map<String, String> accbookNameByPK = AccbookManager.queryAccbookNamesByOrg(pk_org, createCard.toString());

		for (String pk_accbook : pk_accbooks) {
			// 得到最小未结账月
			AccperiodVO minPeriod = queryMinUnClosebookPeriod(pk_org, pk_accbook);
			if (minPeriod == null) {
				continue;
			}
			// 本期间开始日期
			UFDate beginDate = minPeriod.getStartdate();
			// 本期间结束日期
			UFDate endDate = minPeriod.getEnddate();
			// 合并出的卡片建卡日期是否在 本期间日期之间
			if (createCard.afterDate(endDate) || createCard.beforeDate(beginDate)) {

				String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"closebook_0", "02012055-0053", null,
						new String[] { createCard.toString() , accbookNameByPK.get(pk_accbook)})/*
						 * @res
						 * 合并出的资产的建卡日期{0}在账簿[{1}]中不是最小未结账月。
						 */;

				errorMsg += msg;
			}
		}

		if (StringUtils.isNotEmpty(errorMsg)) {
			ExceptionUtils.asBusinessException(errorMsg.toString());
		}
	}

	/**
	 * 判断拆分表头的业务日期是否在最小未结账月
	 */
	@Override
	public void checkHeadMinUnCloseBookPeriod(AggregatedValueObject billVO) throws BusinessException {
		AssetSplitHeadVO headVO = (AssetSplitHeadVO) billVO.getParentVO();

		String pk_card = headVO.getPk_card();
		String pk_org = (String) headVO.getAttributeValue(CommonKeyConst.pk_org);
		UFDate businessDate = headVO.getBusiness_date();
		// 得到卡片的所关联的账簿
		Map<String, List<String>> map = AMProxy.lookup(IAssetService.class).queryNotReducedAccbook(pk_card);
		if (map == null || map.size() == 0) {
			return;
		}
		List<String> accbookList = map.get(pk_card);

		//查询财务账簿对应的名称
		Map<String, String> accbookNameByPK = AccbookManager.queryAccbookNamesByOrg(pk_org, businessDate.toString());

		for (String pk_accbook : accbookList) {
			// 是否最小未结账月
			boolean isMinPeriod = isMinUnCloseBookPeriod(pk_org, businessDate.toString(), pk_accbook);

			if (!isMinPeriod) {
				String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"closebook_0", "02012055-0044", null,
						new String[] {accbookNameByPK.get(pk_accbook),businessDate.toString()})/*
						 * @res
						 * 账簿 [{0}] 在日期{1}不是最小未结账月!
						 */;

				ExceptionUtils.asBusinessException(msg);
			}
		}
	}


	/**
	 * 查询多个账簿是否最小未结账月
	 *
	 */
	@Override
	public Map<String, UFBoolean> queryIsMinUnClosePeriod(String pk_org, String date, String[] pk_accbooks)
			throws BusinessException {
		Map<String, UFBoolean> isMinUnCloseBook = new HashMap<String, UFBoolean>();
		if (pk_accbooks != null && pk_accbooks.length > 0) {
			for (int i = 0; i < pk_accbooks.length; i++) {
				// 暂时采用循环调用。
				boolean isMinUnClose = isMinUnCloseBookPeriod(pk_org, date, pk_accbooks[i]);
				isMinUnCloseBook.put(pk_accbooks[i], UFBoolean.valueOf(isMinUnClose));
			}
		}
		return isMinUnCloseBook;
	}

	/**
	 * 多组织结账，此方法暂时不用，多组织时在前台循环调用结账方法
	 */
	@Override
	public String mutiCloseBook(String pk_group, String accyear, String period, String[] pk_accbooks)
			throws BusinessException {
		Map<String, String> accbookMap = queryAccbookNamesByOrg(pk_group);
		StringBuffer resultMsg = new StringBuffer();
		for (int i = 0; i < pk_accbooks.length; i++) {
			try {
				// modified by zhaoxnc
				// 通过集团查询账簿时直接将组织主键取出放置在map中，key值为--#账簿主键#
				String pk_org = new String();
				String accbookpk = "#" + pk_accbooks[i] + "#";
				if(!accbookMap.isEmpty() && accbookMap.containsKey(accbookpk)){
					pk_org = accbookMap.get(accbookpk);
				} else {
					pk_org = AMProxy.lookup(IAccountingBookPubService.class).queryFinanceOrgIDSByAccountingBookIDS(new String[]{pk_accbooks[i]})[0];
				}
				AMProxy.lookup(ICloseBook.class).closeBook_RequiresNew(pk_group, pk_org, accyear, period, pk_accbooks[i]);
				resultMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0054")/*@res "账簿："*/ + accbookMap.get(pk_accbooks[i]) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0004")/*@res "结账成功！"*/ + "\r\n");
			} catch (Exception e) {
				Logger.debug(e.getMessage());
				resultMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0054")/*@res "账簿："*/ + accbookMap.get(pk_accbooks[i]) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0055")/*@res "结账失败！"*/
						+ ExceptionUtils.getMessage(e) + "\r\n");
			}
		}
		return resultMsg.toString();
	}


	/**
	 * 多组织反结账，此方法暂时不用，多组织时在前台循环调用反结账方法
	 */
	@Override
	public String mutiDeCloseBook(String pk_group, String accyear, String period, String[] pk_accbooks)
			throws BusinessException {
		Map<String, String> accbookMap = queryAccbookNamesByOrg(pk_group);
		StringBuffer resultMsg = new StringBuffer();

		for (int i = 0; i < pk_accbooks.length; i++) {
			try {
				// modified by zhaoxnc
				// 通过集团查询账簿时直接将组织主键取出放置在map中，key值为--#账簿主键#
				String pk_org = new String();
				String accbookpk = "#" + pk_accbooks[i] + "#";
				if(!accbookMap.isEmpty() && accbookMap.containsKey(accbookpk)){
					pk_org = accbookMap.get(accbookpk);
				} else {
					pk_org = AMProxy.lookup(IAccountingBookPubService.class).queryFinanceOrgIDSByAccountingBookIDS(new String[]{pk_accbooks[i]})[0];
				}
				AMProxy.lookup(ICloseBook.class).deCloseBook_RequiresNew(pk_group, pk_org, accyear, period, pk_accbooks[i]);
				resultMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0054")/*@res "账簿："*/ + accbookMap.get(pk_accbooks[i]) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0008")/*@res "反结账成功！"*/ + "\r\n");
			} catch (Exception e) {
				Logger.debug(e.getMessage());
				resultMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0054")/*@res "账簿："*/ + accbookMap.get(pk_accbooks[i]) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0056")/*@res "反结账失败！"*/
						+ ExceptionUtils.getMessage(e) + "\r\n");
			}
		}
		return resultMsg.toString();
	}

	/**
	 * 根据集团取核算账簿数据(根据选择的业务单据获取数据<固定资产卡片使用>)
	 *
	 * @param pk_group
	 *
	 *
	 * @return Map<核算账簿主键，核算账簿名称>
	 * @throws BusinessException
	 */
	private static Map<String, String> queryAccbookNamesByOrg(String pk_group) throws BusinessException {

		Map<String, String> data = new HashMap<String, String>();
		// 查询财务核算账簿
		AccountingBookVO[] accounting = AMProxy.lookup(IAccountingBookQryService.class)
				.queryAllAccountingBookVOSByGroupIDAndClause(pk_group, null);
		if (accounting != null && accounting.length > 0) {
			for (int i = 0; i < accounting.length; i++) {
				String pk_accbook = accounting[i].getPk_accountingbook();
				//String name = accounting[i].getName();
				// 取得名字要处理多语
				String accbook_name = MultiLanguageUtil.getMultiLanguageValue(accounting[i], AccountingBookVO.NAME);
				data.put(pk_accbook, accbook_name);
				// 账簿及其对应的组织存入字典，减少SQL数量   modified by zhaoxnc
				String accbookpk = "#" + pk_accbook + "#";
				data.put(accbookpk, accounting[i].getPk_relorg());
			}
		}
		return data;
	}

	/**
	 * 批量查询最小未结账月
	 * @param orgAccbookMap
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public HashMap<String, AccperiodVO> batchQueryMinUnCloseBookPeriod(HashMap<String, String> orgAccbookMap)
			throws BusinessException {
		HashMap<String, AccperiodVO> resuleMap = new HashMap<String, AccperiodVO>();
		for (String pk_org : orgAccbookMap.keySet()) {
			String pk_accbook = orgAccbookMap.get(pk_org);
			AccperiodVO periodVO = queryMinUnClosebookPeriod(pk_org, pk_accbook);
			resuleMap.put(pk_accbook, periodVO);
		}
		return resuleMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, AccperiodVO> batchQueryMinUnCloseBookPeriod(String[] pk_accbooks) throws BusinessException {
		// 查询最大已结账月
		String querySQL = " select pk_accbook ,max(accyear||period) from fa_closebook where pk_accbook in"
				+ InSqlManager.getInSQLValue(pk_accbooks) + " group by pk_accbook";
		// 查询结账的数据库表
		Map<String, String> accbookClosePeriod = (Map<String, String>) dbAccessUtil.executeQuery(querySQL,
				new KeyValueMapProcessor<String, String>());
		Map<String, AccperiodVO> closePeriodData = new HashMap<String, AccperiodVO>();
		// 没有结账记录的账簿集合  add by zhaoxnc
		List<String> pkAccbookString = new ArrayList<String>();
		
		for (int i = 0; i < pk_accbooks.length; i++) {
			String pk_accbook = pk_accbooks[i];
			AccperiodVO minUnClose = null;
			if (accbookClosePeriod.containsKey(pk_accbook)) {
				String yearPeriod = accbookClosePeriod.get(pk_accbook);
				// 如果有结账记录，则最小为结账月为其下一个期间
				String maxCloseYear = yearPeriod.substring(0, 4);
				String maxClosePeriod = yearPeriod.substring(4);
				// 取最大结账月的下一个月（最小未结账月）
				AccperiodVO nextaccperiodVO = PeriodManager.getInstance().getNextPeriod(pk_accbook, maxCloseYear, maxClosePeriod);
				minUnClose = nextaccperiodVO;
				closePeriodData.put(pk_accbook, minUnClose);
			} else {
				// 如果没有结账记录则最小未结账月为固定资产启用期间
				// 没有结账记录的账簿放到List中   add by zhaoxnc
				if(!pkAccbookString.contains(pk_accbook)){
					pkAccbookString.add(pk_accbook);
				}
			}
		}
		// 批量查询没有结账记录的账簿的启用期间    add by zhaoxnc
		Map<String, AccperiodVO> minUnCloseMap = PeriodManager.getBatchStartPeriod_FA(pkAccbookString.toArray(new String[0]));
		for(String pk_accbook : pkAccbookString){
			closePeriodData.put(pk_accbook, minUnCloseMap.get(pk_accbook));
		}
		
		return closePeriodData;
	}

	@Override
	public void closeBook_RequiresNew(String pk_group, String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		closeBook(pk_group, pk_org, accyear, period, pk_accbook);
	}

	@Override
	public void deCloseBook_RequiresNew(String pk_group, String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		deCloseBook(pk_group, pk_org, accyear, period, pk_accbook);
	}
	/**
	 * 触发事件
	 *
	 * @param event
	 * @throws BusinessException
	 */
	protected void fireEvent(BusinessEvent event) throws BusinessException {
		if (event != null) {
			StringBuffer msg = new StringBuffer();
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0057")/*@res "触发事件开始-->\n"*/);
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0058")/*@res "事件源："*/ + event.getSourceID()).append(",");
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0059")/*@res "事件类型："*/ + event.getEventType()).append(",");
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0060")/*@res "业务对象："*/ + event.getObject()).append(".\n");
			log.debug(msg.toString());

			EventDispatcher.fireEvent(event);

			msg.delete(0, msg.length() - 1);
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0057")/*@res "触发事件开始-->\n"*/);
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0058")/*@res "事件源："*/ + event.getSourceID()).append(",");
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0059")/*@res "事件类型："*/ + event.getEventType()).append(",");
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0060")/*@res "业务对象："*/ + event.getObject()).append(".\n");
			log.debug(msg.toString());
		} else {
			log.debug(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0061")/*@res "传入的事件为空！"*/);
		}
	}


	/**
	 * 远程加载FORM需要的数据，为了减少连接数
	 * @param pk_group
	 * @param pk_org_obj
	 * @param pk_accbook_obj
	 * @param editFlag
	 * @throws BusinessException
	 */
	@Override
	public Map<String, Object> fetchFormData(String pk_group,
			Object pk_org_obj, Object pk_accbook_obj, String editFlag,Boolean isChange)
					throws BusinessException {
		// 定义存储数据对象
		Map<String,Object> dataMap = new HashMap<String, Object>();

		String pk_org = "";
		String pk_accbook = "";
		String accbookStr = "";
		if(null != pk_org_obj){
			pk_org = pk_org_obj.toString();
		}
		if(null != pk_accbook_obj){
			pk_accbook = pk_accbook_obj.toString();
		}

		if(StringUtils.isNotEmpty(pk_org) && isChange){
			//accbookStr = AccbookManager.queryMainAccbookIDByOrg(pk_org);
			Map<String,Object> accbookinfo = AMProxy.lookup(IFADateService.class).checkAccbookEnable(pk_org, null, false);
			if(!accbookinfo.isEmpty() && accbookinfo.containsKey("enablebook")){
				accbookStr = (String)accbookinfo.get("enablebook");

			}		
		}else{
			accbookStr = pk_accbook;
		} 


		if(StringUtils.isNotEmpty(accbookStr)){
			PeriodManager periodManager = PeriodManager.getInstance();
			String[] years = periodManager.getYearVOsOfCurrentScheme(accbookStr);
			AccperiodVO faStartPeriod = PeriodManager.getStartPeriod_FA(accbookStr);
			dataMap.put("years", years); 
			dataMap.put("faStartPeriod", faStartPeriod);
		}
		if(StringUtils.isNotEmpty(pk_org) && StringUtils.isNotEmpty(accbookStr)){
			// 设置选择最小未结账月
			AccperiodVO accperiodVO = AMProxy.lookup(ICloseBookService.class).queryMinUnClosebookPeriod(
					pk_org, accbookStr);

			CloseBookVO[] closeBookVOs = this.queryAllCloseBookVO(pk_group, pk_org, accbookStr);

			dataMap.put("accperiodVO", accperiodVO);
			dataMap.put("closeBookVOs", closeBookVOs);
		}

		dataMap.put("accbookStr", accbookStr);

		return dataMap;
	}

	public String getGLParam(String pk_org) throws BusinessException{

		// 获得GL034设置的账簿
		String gl034Accbook = SysInit.getParaString(pk_org,ParameterConst.GL_CHECKACCBOOK_WHENCLOSE); 

		return gl034Accbook;

	}

	
	/**
	 * 是否最小未结账期间。根据传入日期查询期间方案所在会计期间的结账情况
	 * 
	 * @param pk_org
	 * @param accyear
	 * @param period
	 * @param pk_accbooks
	 * @throws BusinessException
	 * @return 返回账簿
	 * @author zhaoxnc
	 */
	@Override
	public Map<String, Boolean> isCurrentPeriodCloseBooks(String syearmonth, String[] pk_accountingbooks)
			throws BusinessException {
		if(syearmonth.length() != 6){
			String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"closebook_0", "02012055-0069")/*
					 * @res
					 * 传入结算期间格式错误！
					 */;

			ExceptionUtils.asBusinessException(msg);
		} else if( pk_accountingbooks == null || pk_accountingbooks.length < 1){
			String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"closebook_0", "02012055-0070")/*
					 * @res
					 * 未传入账簿!
					 */;

			ExceptionUtils.asBusinessException(msg);
		} else if(syearmonth.length() == 6 && pk_accountingbooks != null && pk_accountingbooks.length >= 1){
			Map<String, Boolean> isCurrentPeriodClose = new HashMap<String, Boolean>(); 
			
			//根据给定String设置待传参数 accyear 和 period
			String accyear = syearmonth.substring(0, 4);
			String period = syearmonth.substring(4, 6);
			
			//依次查询每个账簿是否结账
			for(String pk_accountingbook : pk_accountingbooks){
				boolean isClose_flag = isCurrentPeriodCloseBook(null, accyear, period, pk_accountingbook);
				isCurrentPeriodClose.put(pk_accountingbook, isClose_flag);
			}
			
			return isCurrentPeriodClose;
		}
		return null;   
	}

	@Override
	public Map<String, AccperiodVO> batchQueryMinUnCloseBookPeriodWithBatch(String[] pk_accbooks, Map<String, AccperiodVO> accbookAndStartPeriod) throws BusinessException {
		// 查询最大已结账月
		String querySQL = " select pk_accbook ,max(accyear||period) from fa_closebook where pk_accbook in"
				+ InSqlManager.getInSQLValue(pk_accbooks)
				+ " group by pk_accbook";
		// 查询结账的数据库表
		Map<String, String> accbookClosePeriod = (Map<String, String>) dbAccessUtil.executeQuery(querySQL, new KeyValueMapProcessor<String, String>());
		Map<String, AccperiodVO> closePeriodData = new HashMap<String, AccperiodVO>();
		
		// 没有结账记录的账簿集合 
		List<String> pkAccbookString = new ArrayList<String>();
		for (int i = 0; i < pk_accbooks.length; i++) {
			String pk_accbook = pk_accbooks[i];
			AccperiodVO minUnClose = null;
			if (accbookClosePeriod.containsKey(pk_accbook)) {
				String yearPeriod = accbookClosePeriod.get(pk_accbook);
				// 如果有结账记录，则最小为结账月为其下一个期间
				String maxCloseYear = yearPeriod.substring(0, 4);
				String maxClosePeriod = yearPeriod.substring(4);
				// 取最大结账月的下一个月（最小未结账月）
				AccperiodVO nextaccperiodVO = PeriodManager.getInstance().getNextPeriod(pk_accbook, maxCloseYear, maxClosePeriod);
				minUnClose = nextaccperiodVO;
				closePeriodData.put(pk_accbook, minUnClose);
			} else {
				// 如果没有结账记录则最小未结账月为固定资产启用期间
				// 没有结账记录的账簿放到List中 
				if (!pkAccbookString.contains(pk_accbook)) {
					pkAccbookString.add(pk_accbook);
				}
			}
		}
		
		// 从未结账的账簿缓存过滤未结账且未作缓存的账簿
		if(!pkAccbookString.isEmpty()){
			List<String> pkAccbookToQry = new ArrayList<String>();
			if(accbookAndStartPeriod != null && accbookAndStartPeriod.size() > 0 ){
				for( String pkAccbook : pkAccbookString){
					// 缓存中包含未结账账簿的启用日期
					if(accbookAndStartPeriod.containsKey(pkAccbook)){
						closePeriodData.put(pkAccbook, accbookAndStartPeriod.get(pkAccbook));
					} else {
						// 若未包含，添加到未结账且未缓存的账簿表中
						pkAccbookToQry.add(pkAccbook);
					}
				}
			} else {
				pkAccbookToQry.addAll(pkAccbookString);
			}
			
			// 批量查询没有结账记录且没有做缓存的账簿的启用期间 
			if(!pkAccbookToQry.isEmpty()){
				Map<String, AccperiodVO> minUnCloseMap = PeriodManager.getBatchStartPeriod_FA(pkAccbookToQry.toArray(new String[0]));
				for (String pk_accbook : pkAccbookToQry) {
					closePeriodData.put(pk_accbook, minUnCloseMap.get(pk_accbook));
				}
			}
		}

		return closePeriodData;
	} 
	
}