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
	/** ��־ */
	private Log log = Log.getInstance(this.getClass()); 

	DBAccessUtil dbAccessUtil = new DBAccessUtil();
	VOPersistUtil persistUtil = new VOPersistUtil();

	@Override
	/**
	 * * �жϱ����Ƿ��ѽ���
	 */
	public boolean isCurrentPeriodCloseBook(String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		// �н��˼�¼�����Ѿ�����
		//���������֯Ϊ�գ����Ը���accyear��period��pk_accbook�ж��Ƿ��Ѿ�����
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
	 * * �ж��Ƿ�����ѽ�����
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
	 * * �Ƿ���Сδ������
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
			// ����н��˼�¼����ȡ����ѽ����µ���һ���ڼ�ʹ�����ڼ�Ƚ�
			String maxCloseYear = yearPeriod.substring(0, 4);
			String maxClosePeriod = yearPeriod.substring(4);
			AccperiodVO accperiodVO = periodManager.getNextPeriod(pk_accbook, maxCloseYear, maxClosePeriod);
			minUnCloseYear = accperiodVO.getAccyear();
			minUnClosePeriod = accperiodVO.getPeriod();
		} else {
			// ���û�н��˼�¼�����ù̶��ʲ������ڼ�ʹ�����ڼ�Ƚ�
			AccperiodVO accperiodVO = PeriodManager.getStartPeriod_FA(pk_accbook);
			if (accperiodVO == null) {
				// ��������˲�
				IGeneralAccessor accountbook_general = GeneralAccessorFactory.getAccessor(ClassIDConst.ASSET_ACCBOOK_ID);
				IBDData accountData = accountbook_general.getDocByPk(pk_accbook);
				throw new BusinessException(accountData.getName() + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0015")/*@res " �˲� ��û������ \n "*/);
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
	 * * �Ƿ���Сδ������
	 */
	public boolean isMinUnCloseBookPeriod(String pk_org, String date, String pk_accbook) throws BusinessException {
		AccperiodVO accperiodVO = null;
		try{
			accperiodVO = PeriodManager.getInstance().queryPerriodMonth(pk_accbook, date);
		}catch(Exception e){
			// ��������ģ��һ���ڼ�
			accperiodVO = AssetDateCalUtils.getTempPeriod(pk_accbook, UFDate.getDate(date));
		}
		String accyear = accperiodVO.getAccyear();
		String period = accperiodVO.getPeriod();
		return isMinUnCloseBookPeriod(pk_org, accyear, period, pk_accbook);
	}

	@Override
	/**
	 * * ��ѯ����ѽ�����
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
	 * * ��ѯ��Сδ������
	 */
	public AccperiodVO queryMinUnClosebookPeriod(String pk_org, String pk_accbook) throws BusinessException {
		// ��ѯ����ѽ�����
		String querySQL = " select max(accyear||period) from fa_closebook where dr=0 and pk_accbook = ? ";
		// String querySQL =
		// " select max(accyear||period) from fa_closebook where pk_accbook = ? and pk_org = ? and dr=0 ";
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(pk_accbook);
		// ��֯�Ĺ���������ȥ��
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
			// ����н��˼�¼������СΪ������Ϊ����һ���ڼ�
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
			// ���û�н��˼�¼����Сδ������Ϊ�̶��ʲ������ڼ�
			return PeriodManager.getStartPeriod_FA(pk_accbook);
		}
	}

	@Override
	/**
	 * * ����
	 */
	public void closeBook(String pk_group, String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		// ����
		try {
			String lockID = "CloseBook" + pk_org;
			LockManager.lock(new BaseLockData<String>(lockID));
		} catch (Exception e) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0016")/*@res " ����������Ա���ڶԱ���֯���˻򷴽��ˣ����Ժ����ԣ�"*/);
		}

		// �ж��Ƿ�ѡ���ڼ�
		if(StringUtils.isEmpty(accyear) || StringUtils.isEmpty(period)){
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0017")/*@res " ��ѡ���ڼ䣡"*/);
		}
		try {
			// ��ȡ��һ���ڼ�, �ж��Ƿ���������һ�ڼ���ڼ䷽��
			AccperiodVO periodVO = PeriodManager.getInstance().getNextPeriod(pk_accbook, accyear, period);
			if (periodVO == null || periodVO.getAccyear() == null) {
				ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0018")/*@res " û��������һ���ڼ���ڼ䷽�������ȼ���ڼ䷽�����ã�"*/);
			}
		} catch (Exception e) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0018")/*@res " û��������һ���ڼ���ڼ䷽�������ȼ���ڼ䷽�����ã�"*/);
		}

		// �ж��Ƿ���Сδ������
		if (!isMinUnCloseBookPeriod(pk_org, accyear, period, pk_accbook)) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0019")/*@res " ���ڼ䲻����Сδ�����£����ܽ��ˣ�"*/);
		}

		//����ǰ����
		CloseBookVO closeBookVO = createCloseBookVO(pk_group, pk_org, accyear, period, pk_accbook);
		String beanId = MetaDataTools.getBeanID(CloseBookVO.class);
		BusinessEvent beforeEvent = new BusinessEvent(beanId, IEventType.TYPE_ACCOUNT_BEFORE, closeBookVO);
		fireEvent(beforeEvent);


		// �����豸�������������ﴦ��
		if (ModuleInfoQuery.isAIMEnabled()) {
			AMProxy.lookup(IEquipToFaService.class).updateFinanceDatasWhenCloseBook(pk_org);
		}
		// �ж����е����Ƿ��Ѿ����ͨ��
		StringBuffer errMsg = new StringBuffer();
		String[] tempBillCodes;
		String stringEnd = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0020")/*@res " ��δ���ͨ����"*/;
		
		/**
		 * HK
		 * 2019��10��15�� 16��36��
		 * �ж� ת�̵� �Ƿ�ȫ�������� �̶��ʲ���Ƭ��
		 */
		if(true) {
			// ����ڼ� ��Ӧ�� ��ʼ���ڡ���������
			String[] date = PeriodManager.getInstance().queryPerriodDate(pk_accbook, accyear, period);
			String begDate = date[0].substring(0,10);
			String endDate = date[1].substring(0,10);
			
			StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" max(zg.bill_code) ")	//0ת�̵���
					.append(",max(inv.code) ")		//1���ϱ���
					.append(",max(inv.name) ")		//2��������
					.append(",max(zgb.amount) ")	//3ת������
					.append(",sum(nvl(cardhis.card_num,0)) ")	//4��Ƭ����
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
	        		  .append("��ת�̵��ţ�").append(billCode).append("��")
	        		  .append("�����ϣ�").append(invCode).append(invName).append("��")
	        		  .append("{ת�̵�����������").append(zgNum).append("}��")
	        		  .append("{�������ʲ���Ƭ������").append(kpNum).append("}")
	        		  .append("\r\n")
	        		  ;
	    		  }
	    		  
	    		  errMsg
	    		  .append("����ת�̵�δ�����ʲ���Ƭ��\r\n")
	    		  .append(errMSg_temp);
			}
		}
		/***END***/
		
		// �жϵĵ�����
		// �ʲ��䶯�������б䶯����
		// �̵�������
		tempBillCodes = AMProxy.lookup(IAlterService.class).queryNotPassAlterBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0021")/*@res "�䶯����"*/, tempBillCodes, stringEnd);
		// �ʲ�����
		tempBillCodes = AMProxy.lookup(IEvaluateService.class).notApproveBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0022")/*@res "��������"*/, tempBillCodes, stringEnd);
		// �ʲ���ֵ
		tempBillCodes = AMProxy.lookup(IPredevaluateService.class).notApproveBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0023")/*@res "��ֵ����"*/, tempBillCodes, stringEnd);
		// �ʲ����
		tempBillCodes = AMProxy.lookup(IAssetSplitService.class).notApproveBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0024")/*@res "��ֵ���"*/, tempBillCodes, stringEnd);
		// �ʲ��ϲ�
		tempBillCodes = AMProxy.lookup(IAssetCombinService.class).notApproveBills(pk_org, pk_accbook, accyear, period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0025")/*@res "�ϲ�����"*/, tempBillCodes, stringEnd);
		// �ʲ�����
		tempBillCodes = AMProxy.lookup(IDeployService.class).queryNotPassDeployOutBills(pk_org, pk_accbook, accyear,
				period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0026")/*@res "��������"*/, tempBillCodes, stringEnd);
		// �Ѿ�ȷ�ϱ��µ���ĵ��뵥����������Ϊ������ڼ䣩�����뵥�ĵ�������Ϊ��ʱ������Խ��ˣ�
		tempBillCodes = AMProxy.lookup(IDeployService.class).queryNotPassDeployInBills(pk_org, pk_accbook, accyear,
				period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0027")/*@res "���뵥��"*/, tempBillCodes, stringEnd);

		// ��ȷ�ϱ��µ����ֶΡ�ΪY�ĵ�������Ӧ��δ���ͨ���ĵ��뵥�����뵥�������ͨ�����ܽ���
		Map<String, String> billCodeMap = AMProxy.lookup(IDeployService.class).queryNotPassCorrespondingDeployInBills(
				pk_org, pk_accbook, accyear, period);
		// ������Ϣ
		processDeployBillCodeMap(errMsg, billCodeMap);

		// �ʲ�����
		tempBillCodes = AMProxy.lookup(IReduceService.class).queryNotPassReduceBills(pk_org, pk_accbook, accyear,
				period);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0028")/*@res "���ٵ���"*/, tempBillCodes, stringEnd);

		if (errMsg.length() > 0) {
			errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0029")/*@res " ���ܽ��ˣ�"*/);
			ExceptionUtils.asBusinessException(errMsg.toString());
		}

		if(ParameterManager.getParaBoolean(pk_accbook, ParameterConst.IS_NEED_DEPR).booleanValue()){
			// �ж��Ƿ��Ѿ������۾�
			//			boolean isDepComplete = AMProxy.lookup(ILogService.class).isDepComplete(pk_org, pk_accbook, accyear, period);
			//			if (!isDepComplete) {
			//				ExceptionUtils.asBusinessException("���ڼ仹δ�����۾ɣ����ȼ����۾ɣ�");
			//			}
			LogVO logVO = AMProxy.lookup(ILogService.class).queryDepLogVO(pk_org, pk_accbook, accyear, period);
			if(logVO == null){
				ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0030")/*@res "���ڼ仹δ�����۾ɣ����ȼ����۾ɣ�"*/);
			} else {
				if (logVO.getRedep_flag().booleanValue()) {
					ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0031")/*@res "�����Ѽ��ᣬ��������Ӱ���۾ɵĲ�������Ҫ���¼��ᣡ"*/);
				}
			}
		}
		// ���ˣ����¿�Ƭ��
		// ������ĩΪ�����³�
		// ����������ĩ����
		AMProxy.lookup(IAssetService.class).disposeAssetDataWhenCloseBook(pk_org, pk_accbook, accyear, period);
		// ������˼�¼
		insertCloseBookInfo(pk_group, pk_org, accyear, period, pk_accbook);
		// ��������ͻ�����Ŀ
		AMProxy.lookup(IOptionService.class).insertNextMounthOption(period, accyear, pk_accbook);

		// ��д���˽���״̬
		// ��ѯ��ǰ��������˲������������ڼ�
		String pk_accountBookPeriod = AMProxy.lookup(IAccountingBookPubService.class).queryAccountBookPeriodByAccountingBookID(pk_accbook);
		if (StringUtils.isNotEmpty(pk_accountBookPeriod)) {
			// ��ȡ���˶�Ӧ���ڼ�����
			AccperiodVO glStartPeriod = PeriodManager.queryPeriodByAccperiodMonth(pk_accountBookPeriod);
			// �ж������Ƿ�����
			if (glStartPeriod != null && StringUtils.isNotEmpty(glStartPeriod.getAccyear()) && StringUtils.isNotEmpty(glStartPeriod.getPeriod())) {
				// ���쵱ǰ�����ڼ��    �����ͻ��������
				String[] closeBookPeriod = new String[2];
				closeBookPeriod[0] = accyear;
				closeBookPeriod[1] = period;
				// �����������õ��ڼ��    �����ͻ��������
				String[] glPeriod = new String[2];
				glPeriod[0] = glStartPeriod.getAccyear();
				glPeriod[1] = glStartPeriod.getPeriod();
				// �ڼ�ıȽϽ��    ����   < 0  ��ʾ���˵������ڼ����ڹ̶��ʲ����˵��ڼ�
				int compareResult = PeriodManager.compareTwoPeriods(closeBookPeriod, glPeriod);
				// �ж��Ƿ���������   ����   �����ڼ��Ѿ�����������
				if(ModuleInfoQuery.isModuleEnabledBySysCode(ModuleConst.GL) && compareResult >= 0){
					// ȡ�õ�ǰ�˲����˲�����
					AccountingBookVO[] accountingBooks = AMProxy.lookup(IAccountingBookQryService.class).queryVOsByPks(new String[]{pk_accbook});
					if (ArrayUtils.isNotEmpty(accountingBooks)) {
						String pk_setofBook = accountingBooks[0].getPk_setofbook();
						// ȡ�õ�ǰ�˲����ڼ䷽��
						String pk_accPeriodScheme = AMProxy.lookup(ISetOfBookQryService.class).querySetOfBookVOByPK(pk_setofBook).getPk_accperiodscheme();
						String yearMth = accyear + "-" + period;
						// ȡ�ò�ѯ���ڼ������
						String pk_accperiodmonth = AMProxy.lookup(IAccPeriodQueryServicer.class).getAccByAccSchemePKAndYearMth(pk_accPeriodScheme, yearMth);
						// �������˽ӿڻ�д
						AMProxy.lookup(ICloseAccPubServicer.class).account(ModuleConst.FA_FUNCCODE, pk_org, pk_accbook, pk_accperiodmonth);
					}
				}
			}
		}

		// ���˺�������
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
	 * ��ʾ��䴦��
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
	 * ��������ȷ�ϵ��롱�ĵ������ݵ���ʾ��Ϣ
	 *
	 * @param msgBuffer
	 * @param deployBillCodeMap
	 * @author yanjq
	 * @date 2010-5-25
	 */
	private void processDeployBillCodeMap(StringBuffer msgBuffer, Map<String, String> deployBillCodeMap) {

		for (Map.Entry<String, String> entry : deployBillCodeMap.entrySet()) {
			msgBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0032")/*@res "������"*/ + entry.getKey() + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0033")/*@res " ��Ӧ�ĵ��뵥"*/ + entry.getValue() + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0034")/*@res "��δ���ͨ����\n"*/);
		}
	}

	/**
	 * ������˼�¼
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
	 * * ������
	 */
	public void deCloseBook(String pk_group, String pk_org, String accyear, String period, String pk_accbook)
			throws BusinessException {
		// ����
		try {
			String lockID = "CloseBook" + pk_org;
			LockManager.lock(new BaseLockData<String>(lockID));
		} catch (Exception e) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0016")/*@res " ����������Ա���ڶԱ���֯���˻򷴽��ˣ����Ժ����ԣ�"*/);
		}
		// �Ƿ�����ѽ�����
		if (!isMaxCloseBookPeriod(pk_org, accyear, period, pk_accbook)) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0035")/*@res " ���ڼ䲻������ѽ����£����ܷ����ˣ�"*/);
		}
		// ������У�������Ƿ���� 
		if(ModuleInfoQuery.isModuleEnabledBySysCode(ModuleConst.GL)){
			String pk_glbook = ParameterManager.getParaString(pk_org, ParameterConst.GL_CHECKACCBOOK_WHENCLOSE);
			if (StringUtils.isNotEmpty(pk_glbook) && pk_accbook.equals(pk_glbook)) {
				String sCurrYM = accyear + "-" + period;

				boolean isClose = AMProxy.lookup(ICloseAccQryPubServicer.class).isCloseByAccountBookId(pk_glbook, sCurrYM);
				if (isClose) {
					/*
					 * @res "���˸��·��ѹ��ˣ��̶��ʲ����ܷ����ˣ�"
					 */
					ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0036")/*@res "���˸��·��ѹ��ˣ��̶��ʲ����ܷ����ˣ�"*/);
				}
			}
		} 
		// ������ǰ�������
		CloseBookVO closeBookVO = createCloseBookVO(pk_group, pk_org, accyear, period, pk_accbook);
		String beanId = MetaDataTools.getBeanID(CloseBookVO.class);
		BusinessEvent beforeEvent = new BusinessEvent(beanId, IEventType.TYPE_REACCOUNT_BEFORE, closeBookVO);
		fireEvent(beforeEvent);

		// ��ȡ��һ���ڼ�
		AccperiodVO periodvo = PeriodManager.getInstance().getNextPeriod(pk_accbook, accyear, period);
		String nextaccyear = periodvo.getAccyear();
		String nextPeriod = periodvo.getPeriod();

		// �ж��۾��Ƿ��Ƶ�
		if (FipManager.isFipMakeVoucher(pk_org, nextaccyear, nextPeriod, pk_accbook).booleanValue()) {
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0039")/*@res "���ڼ��۾��Ѿ��Ƶ������ܷ����ˣ�"*/);
		}

		//ɾ����Сδ������ƾ֤
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

		// �ж��Ƿ����е��ݶ���������̬
		StringBuffer errMsg = new StringBuffer();
		String[] tempBillCodes;
		String stringEnd = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0037")/*@res " ��������̬��"*/;
		// �жϵĵ�����
		// �ʲ��䶯�������б䶯��
		// �̵�������
		tempBillCodes = AMProxy.lookup(IAlterService.class).queryNotFreeAlterBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0021")/*@res "�䶯����"*/, tempBillCodes, stringEnd);
		// �ʲ�����
		tempBillCodes = AMProxy.lookup(IEvaluateService.class).notFreeCheckBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0022")/*@res "��������"*/, tempBillCodes, stringEnd);
		// �ʲ���ֵ
		tempBillCodes = AMProxy.lookup(IPredevaluateService.class).notFreeCheckBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0023")/*@res "��ֵ����"*/, tempBillCodes, stringEnd);
		// �ʲ����
		tempBillCodes = AMProxy.lookup(IAssetSplitService.class).notFreeCheckBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0024")/*@res "��ֵ���"*/, tempBillCodes, stringEnd);
		// �ʲ��ϲ�
		tempBillCodes = AMProxy.lookup(IAssetCombinService.class).notFreeCheckBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0025")/*@res "�ϲ�����"*/, tempBillCodes, stringEnd);
		// // �ʲ��̵�
		// tempBillCodes =
		// AMProxy.lookup(IInventoryService.class).queryNotFreeAlterBills(pk_org,
		// accyear, nextPeriod);
		// processMessage(errMsg, "�̵㵥��", tempBillCodes, stringEnd);
		// �ʲ�����
		tempBillCodes = AMProxy.lookup(IDeployService.class).queryNotFreeDeployOutBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0026")/*@res "��������"*/, tempBillCodes, stringEnd);
		// �ʲ�����
		tempBillCodes = AMProxy.lookup(IReduceService.class).queryNotFreeReduceBills(pk_org, pk_accbook, nextaccyear,
				nextPeriod);
		processMessage(errMsg, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0028")/*@res "���ٵ���"*/, tempBillCodes, stringEnd);

		if (errMsg.length() > 0) {
			errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0038")/*@res " ���ܷ����ˣ�"*/);
			ExceptionUtils.asBusinessException(errMsg.toString());
		}

		// ɾ������̬����
		// ɾ���ĵ�����
		// �ʲ��䶯�������б䶯��
		// �̵�������
		AMProxy.lookup(IAlterService.class).deleteAllFreeAlterBills(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// �ʲ�����
		AMProxy.lookup(IEvaluateService.class).deleteAllFreeCheck(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// �ʲ���ֵ
		AMProxy.lookup(IPredevaluateService.class).deleteAllFreeCheck(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// �ʲ����
		AMProxy.lookup(IAssetSplitService.class).deleteAllFreeCheck(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// �ʲ��ϲ�
		AMProxy.lookup(IAssetCombinService.class).deleteAllFreeCheck(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// �ʲ��̵�
		// AMProxy.lookup(IInventoryService.class).deleteAllFreeAlterBills(pk_org,
		// accyear, nextPeriod);
		// �ʲ�����
		AMProxy.lookup(IDeployService.class).deleteAllFreeDeployBills(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// �ʲ�����
		AMProxy.lookup(IReduceService.class).deleteAllFreeReduceBills(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// ɾ���۾ɻ��ƾ֤

		// ɾ���۾ɻ�����Ϣ
		AMProxy.lookup(IDepService.class).deleteGatherInfo(pk_org, pk_accbook, nextaccyear, nextPeriod);
		// ɾ���۾���־
		LogVO logVO = new LogVO();
		logVO.setPk_accbook(pk_accbook);
		logVO.setAccyear(nextaccyear);
		logVO.setPeriod(nextPeriod);
		logVO.setLog_type(LogTypeConst.dep);
		logVO.setPk_org(pk_org);
		AMProxy.lookup(ILogService.class).deleteLogs(logVO);
		// ���¿�Ƭ,���������³�����Ϊ������ĩ���ݣ�ɾ��������ĩ���ݣ���������¼��Ŀ�Ƭ��
		AMProxy.lookup(IAssetService.class).disposeAssetDataWhenUnCloseBook(pk_org, pk_accbook, accyear, period);

		// ɾ���������û������Ŀ
		AccperiodVO accperiodVO = PeriodManager.getInstance().getNextPeriod(pk_accbook, accyear, period);
		if (accperiodVO == null)
			return;
		AMProxy.lookup(IOptionService.class).deleteMounthOption(accperiodVO.getPeriod(), accperiodVO.getAccyear(),
				pk_accbook);

		// ɾ�����˼�¼
		deleteCloseBookInfo(pk_group, pk_org, accyear, period, pk_accbook);

		// �����豸��������
		if (ModuleInfoQuery.isAIMEnabled()) {
			AMProxy.lookup(IEquipToFaService.class).updateFinanceDatasWhenUnCloseBook(pk_org);
		}
		// �жϵ�ǰ�Ĺ̶��ʲ��Ƿ��д�˽���״̬�ı�
		String[] isClose = AMProxy.lookup(ICloseAccQryPubServicer.class)
				.isEndaccByAccountBookIdAndModuleId(new String[]{pk_accbook}, ModuleConst.FA_FUNCCODE, accyear + "-" + period);
		// ������˲��Ѿ���д�����˱�־-------���֮ǰû��д���������������Ļ��������ﴦ��Ļ������б���
		if (isClose != null && isClose.length > 0) {
			// ��д���˽���״̬
			// ��ѯ��ǰ��������˲������������ڼ�
			String pk_accountBookPeriod = AMProxy.lookup(IAccountingBookPubService.class).queryAccountBookPeriodByAccountingBookID(pk_accbook);
			if (StringUtils.isNotEmpty(pk_accountBookPeriod)) {
				// ��ȡ���˶�Ӧ���ڼ�����
				AccperiodVO glStartPeriod = PeriodManager.queryPeriodByAccperiodMonth(pk_accountBookPeriod);
				if (glStartPeriod != null && StringUtils.isNotEmpty(glStartPeriod.getAccyear()) && StringUtils.isNotEmpty(glStartPeriod.getPeriod())) {
					// ���쵱ǰ�������ڼ��    �����ͻ��������
					String[] closeBookPeriod = new String[2];
					closeBookPeriod[0] = accyear;
					closeBookPeriod[1] = period;
					// �����������õ��ڼ��    �����ͻ��������
					String[] glPeriod = new String[2];
					glPeriod[0] = glStartPeriod.getAccyear();
					glPeriod[1] = glStartPeriod.getPeriod();
					// �ڼ�ıȽϽ��    ����   < 0  ��ʾ���˵������ڼ����ڹ̶��ʲ������˵��ڼ�
					int compareResult = PeriodManager.compareTwoPeriods(closeBookPeriod, glPeriod);
					// �ж��Ƿ���������   ����   �������ڼ��Ѿ�����������
					if(ModuleInfoQuery.isModuleEnabledBySysCode(ModuleConst.GL) && compareResult >= 0){
						// ȡ�õ�ǰ�˲����˲�����
						AccountingBookVO[] accountingBooks = AMProxy.lookup(IAccountingBookQryService.class).queryVOsByPks(new String[]{pk_accbook});
						if (ArrayUtils.isNotEmpty(accountingBooks)) {
							String pk_setofBook = accountingBooks[0].getPk_setofbook();
							// ȡ�õ�ǰ�˲����ڼ䷽��
							String pk_accPeriodScheme = AMProxy.lookup(ISetOfBookQryService.class).querySetOfBookVOByPK(pk_setofBook).getPk_accperiodscheme();
							String yearMth = accyear + "-" + period;
							// ȡ�ò�ѯ���ڼ������
							String pk_accperiodmonth = AMProxy.lookup(IAccPeriodQueryServicer.class).getAccByAccSchemePKAndYearMth(pk_accPeriodScheme, yearMth);
							// �������˽ӿڻ�д
							AMProxy.lookup(ICloseAccPubServicer.class).reAccount(ModuleConst.FA_FUNCCODE, pk_org, pk_accbook, pk_accperiodmonth);
						}
					}
				}
			}
		}
		//�����˺���
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
	 * ɾ��������Ϣ
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
	 * * ��ѯ�����ѽ�����
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
	 * * �ж��Ƿ���Сδ������
	 * ����ע�����񷽷���Ӧ����������׳��쳣���������׳��쳣�ķ����ǹ��õģ�Ŀǰû��һ���ʺϵĵط����ã���ʱ�������added by yanjq
	 */
	public void checkMinUnCloseBookPeriod(AggregatedValueObject billVO) throws BusinessException {
		SuperVO headVO = (SuperVO) billVO.getParentVO();
		UFDate bizDate = (UFDate) headVO.getAttributeValue(CommonKeyConst.business_date);
		String pk_org = (String) headVO.getAttributeValue(CommonKeyConst.pk_org);
		String pk_accbook = (String) headVO.getAttributeValue(CommonKeyConst.pk_accbook);
		String bill_type = (String) headVO.getAttributeValue(CommonKeyConst.bill_type);
		Integer bill_status = (Integer) headVO.getAttributeValue(CommonKeyConst.bill_status);
		// ���pk_accbook��ҵ���˲�������Ҫ������е��˲��������ж�

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
			//���ٵ� ���� ������ ����������⣬��Ϊ�Ѿ����٣�����Ҫ��ѯ�����˲�
			if ((BillTypeConst_FA.REDUCE.equals(bill_type))
					&& bill_status == BillStatusConst.check_pass) {
				map = AMProxy.lookup(IAssetService.class).queryCardNAssociateAccbooks(pkCardList.toArray(new String[0]));
				String billCode = (String) headVO.getAttributeValue(CommonKeyConst.bill_code);
				filterCurrentBillPkaccbook(map, billCode);
			} else if( BillTypeConst_FA.DEPLOY_OUT.equals(bill_type)
					&& bill_status == BillStatusConst.check_pass){
				map = AMProxy.lookup(IAssetService.class).queryCardAssociateAccbooks(pkCardList.toArray(new String[0]));
			} // ҵ��䶯����ҪУ����֯����������˲�����Сδ�����¡�
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
			// �ʲ�����
			// ����ӿ�Ƭ��ʷ��ò����˲���Ϣ��ͨ����֯��ѯ���п����˲�
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
			ExceptionUtils.asBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0040")/*@res "��ҵ������"*/ + bizDate + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0041")/*@res " �Ҳ����κ��˲�!"*/);
		}

		//��ѯ�����˲���Ӧ������
		Map<String, String> accbookNameByPK = AccbookManager.queryAccbookNamesByOrg(pk_org, bizDate.toString());

		if (accbookNameByPK == null || accbookNameByPK.size() == 0) {
			String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"closebook_0", "02012055-0042", null,
					new String[] { bizDate.toString()})/*
					 * @res
					 * ����֯��ҵ������:{0}û�������˲�! 
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
							 * �˲� [{0}] ������{1}������Сδ������!
							 */;

					ExceptionUtils.asBusinessException(msg);
				}
			}
		}
	}

	/**
	 * <p>
	 * ���˷ǵ�ǰ���ٵ�������˲����ݡ�
	 * 
	 * <p>
	 * ҵ����ٵ��Ὣָ����Ƭ������δ�����ٵ��˲���ͳһ�����٣�ȡ������ʱ�Ͳ���ҪУ��֮ǰ�Ѿ������ٵ��˲��ˡ�<br>
	 * �˴�ͨ���̶��ʲ���Ƭ����־��Ϣȡ�÷ǵ�ǰ���ٵ�������˲������ҹ��˵���
	 * 
	 * @param map
	 * @param billCode
	 */
	@SuppressWarnings("unchecked")
	private void filterCurrentBillPkaccbook(Map<String, List<String>> map, String billCode) {

		if (org.apache.commons.collections.MapUtils.isNotEmpty(map) 
				&& StringUtils.isNotEmpty(billCode)) {
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				// ȡ�÷ǵ�ǰ���ٵ�������˲���
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
				// �����˲������ҵ�������
				String bookName =key_multi.get(tempPk_accbook);

				String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"closebook_0", "02012055-0047", null,
						new String[] { bookName,date})/*
						 * @res
						 * �˲�{0}:������{1}������Сδ������!
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
			// ��ѯ�Ƿ���Сδ������
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
	 * �жϲ�ֽ��������Ƿ�����Сδ������
	 */
	@Override
	public void checkCreateCardMinUnCloseBookPeriodForAssetSplit(AggregatedValueObject billVO) throws BusinessException {
		AssetSplitHeadVO headVO = (AssetSplitHeadVO) billVO.getParentVO();
		AssetSplitBodyVO[] childVOs = (AssetSplitBodyVO[]) billVO.getChildrenVO();
		String pk_org = headVO.getPk_org();

		// ��һ�еĽ�������
		UFDate createCard = (UFDate) childVOs[0].getAttributeValue(CommonKeyConst.business_date);

		// ȡ��ÿ�еĽ�������set
		Set<UFDate> busiDates = VOManager.getAttributeValueSet(childVOs, CommonKeyConst.business_date);


		// ��ǰ�е��ʲ����
		String errorMsg = "";

		// �õ����ʲ��������������˲�
		List<String> pk_accbooks = AMProxy.lookup(IAssetService.class).queryNotReducedAccbook(headVO.getPk_card()).get(
				headVO.getPk_card());

		if (pk_accbooks == null || pk_accbooks.size() == 0) {
			return;
		}
		//��ѯ�����˲���Ӧ������
		Map<String, String> accbookNameByPK = AccbookManager.queryAccbookNamesByOrg(pk_org, createCard.toString());

		for (String pk_accbook : pk_accbooks) {
			// �õ���Сδ������
			AccperiodVO minPeriod = queryMinUnClosebookPeriod(pk_org, pk_accbook);
			if (minPeriod == null) {
				continue;
			}
			// ���ڼ俪ʼ����
			UFDate beginDate = minPeriod.getStartdate();
			// ���ڼ��������
			UFDate endDate = minPeriod.getEnddate();
			for (UFDate busiDate : busiDates) {
				// ��ֳ��Ŀ�Ƭ���������Ƿ��� ���ڼ�����֮��
				if ((busiDate.afterDate(endDate) || busiDate.beforeDate(beginDate))) {

					String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0", "02012055-0050",
							null, new String[] { busiDate.toString(), accbookNameByPK.get(pk_accbook) })/*
							 * @res
							 * ��ֳ����ʲ��Ľ�������
							 * {0}
							 * ���˲�[{1}]�в�����Сδ������
							 * ��
							 */;

					errorMsg += msg;
				}
			}
			// ��ֳ��Ŀ�Ƭ���������Ƿ��� ���ڼ�����֮��
			//			if ((createCard.afterDate(endDate) || createCard.beforeDate(beginDate))) {
			//				
			//				String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
			//					      "closebook_0", "02012055-0050", null,
			//					      new String[] { createCard.toString() , accbookNameByPK.get(pk_accbook)})/*
			//					              * @res
			//					              * ��ֳ����ʲ��Ľ�������{0}���˲�[{1}]�в�����Сδ�����¡�
			//					              */;
			//				
			//				errorMsg += msg;
			//			} else if (busiDates.size() > 1){ // ����Ľ�������������1
			//				busiDates.remove(createCard);
			//				for (UFDate busiDate : busiDates) {
			//					String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
			//						      "closebook_0", "02012055-0050", null,
			//						      new String[] { busiDate.toString() , accbookNameByPK.get(pk_accbook)})/*
			//						              * @res
			//						              * ��ֳ����ʲ��Ľ�������{0}���˲�[{1}]�в�����Сδ�����¡�
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

		// ÿ�еĽ�������
		UFDate createCard = (UFDate) headVO.getAttributeValue(AssetCombinHeadVO.CREATECARD_DATE);

		// ��ǰ�е��ʲ����
		String errorMsg = "";

		// �õ����ʲ��������������˲�
		List<String> pk_accbooks = null;
		if (ArrayUtils.isNotEmpty(childVOs)) {
			pk_accbooks = AMProxy.lookup(IAssetService.class).queryNotReducedAccbook(childVOs[0].getPk_card()).get(
					childVOs[0].getPk_card());
		}
		if (pk_accbooks == null || pk_accbooks.size() == 0) {
			return;
		}

		//��ѯ�����˲���Ӧ������
		Map<String, String> accbookNameByPK = AccbookManager.queryAccbookNamesByOrg(pk_org, createCard.toString());

		for (String pk_accbook : pk_accbooks) {
			// �õ���Сδ������
			AccperiodVO minPeriod = queryMinUnClosebookPeriod(pk_org, pk_accbook);
			if (minPeriod == null) {
				continue;
			}
			// ���ڼ俪ʼ����
			UFDate beginDate = minPeriod.getStartdate();
			// ���ڼ��������
			UFDate endDate = minPeriod.getEnddate();
			// �ϲ����Ŀ�Ƭ���������Ƿ��� ���ڼ�����֮��
			if (createCard.afterDate(endDate) || createCard.beforeDate(beginDate)) {

				String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"closebook_0", "02012055-0053", null,
						new String[] { createCard.toString() , accbookNameByPK.get(pk_accbook)})/*
						 * @res
						 * �ϲ������ʲ��Ľ�������{0}���˲�[{1}]�в�����Сδ�����¡�
						 */;

				errorMsg += msg;
			}
		}

		if (StringUtils.isNotEmpty(errorMsg)) {
			ExceptionUtils.asBusinessException(errorMsg.toString());
		}
	}

	/**
	 * �жϲ�ֱ�ͷ��ҵ�������Ƿ�����Сδ������
	 */
	@Override
	public void checkHeadMinUnCloseBookPeriod(AggregatedValueObject billVO) throws BusinessException {
		AssetSplitHeadVO headVO = (AssetSplitHeadVO) billVO.getParentVO();

		String pk_card = headVO.getPk_card();
		String pk_org = (String) headVO.getAttributeValue(CommonKeyConst.pk_org);
		UFDate businessDate = headVO.getBusiness_date();
		// �õ���Ƭ�����������˲�
		Map<String, List<String>> map = AMProxy.lookup(IAssetService.class).queryNotReducedAccbook(pk_card);
		if (map == null || map.size() == 0) {
			return;
		}
		List<String> accbookList = map.get(pk_card);

		//��ѯ�����˲���Ӧ������
		Map<String, String> accbookNameByPK = AccbookManager.queryAccbookNamesByOrg(pk_org, businessDate.toString());

		for (String pk_accbook : accbookList) {
			// �Ƿ���Сδ������
			boolean isMinPeriod = isMinUnCloseBookPeriod(pk_org, businessDate.toString(), pk_accbook);

			if (!isMinPeriod) {
				String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"closebook_0", "02012055-0044", null,
						new String[] {accbookNameByPK.get(pk_accbook),businessDate.toString()})/*
						 * @res
						 * �˲� [{0}] ������{1}������Сδ������!
						 */;

				ExceptionUtils.asBusinessException(msg);
			}
		}
	}


	/**
	 * ��ѯ����˲��Ƿ���Сδ������
	 *
	 */
	@Override
	public Map<String, UFBoolean> queryIsMinUnClosePeriod(String pk_org, String date, String[] pk_accbooks)
			throws BusinessException {
		Map<String, UFBoolean> isMinUnCloseBook = new HashMap<String, UFBoolean>();
		if (pk_accbooks != null && pk_accbooks.length > 0) {
			for (int i = 0; i < pk_accbooks.length; i++) {
				// ��ʱ����ѭ�����á�
				boolean isMinUnClose = isMinUnCloseBookPeriod(pk_org, date, pk_accbooks[i]);
				isMinUnCloseBook.put(pk_accbooks[i], UFBoolean.valueOf(isMinUnClose));
			}
		}
		return isMinUnCloseBook;
	}

	/**
	 * ����֯���ˣ��˷�����ʱ���ã�����֯ʱ��ǰ̨ѭ�����ý��˷���
	 */
	@Override
	public String mutiCloseBook(String pk_group, String accyear, String period, String[] pk_accbooks)
			throws BusinessException {
		Map<String, String> accbookMap = queryAccbookNamesByOrg(pk_group);
		StringBuffer resultMsg = new StringBuffer();
		for (int i = 0; i < pk_accbooks.length; i++) {
			try {
				// modified by zhaoxnc
				// ͨ�����Ų�ѯ�˲�ʱֱ�ӽ���֯����ȡ��������map�У�keyֵΪ--#�˲�����#
				String pk_org = new String();
				String accbookpk = "#" + pk_accbooks[i] + "#";
				if(!accbookMap.isEmpty() && accbookMap.containsKey(accbookpk)){
					pk_org = accbookMap.get(accbookpk);
				} else {
					pk_org = AMProxy.lookup(IAccountingBookPubService.class).queryFinanceOrgIDSByAccountingBookIDS(new String[]{pk_accbooks[i]})[0];
				}
				AMProxy.lookup(ICloseBook.class).closeBook_RequiresNew(pk_group, pk_org, accyear, period, pk_accbooks[i]);
				resultMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0054")/*@res "�˲���"*/ + accbookMap.get(pk_accbooks[i]) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0004")/*@res "���˳ɹ���"*/ + "\r\n");
			} catch (Exception e) {
				Logger.debug(e.getMessage());
				resultMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0054")/*@res "�˲���"*/ + accbookMap.get(pk_accbooks[i]) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0055")/*@res "����ʧ�ܣ�"*/
						+ ExceptionUtils.getMessage(e) + "\r\n");
			}
		}
		return resultMsg.toString();
	}


	/**
	 * ����֯�����ˣ��˷�����ʱ���ã�����֯ʱ��ǰ̨ѭ�����÷����˷���
	 */
	@Override
	public String mutiDeCloseBook(String pk_group, String accyear, String period, String[] pk_accbooks)
			throws BusinessException {
		Map<String, String> accbookMap = queryAccbookNamesByOrg(pk_group);
		StringBuffer resultMsg = new StringBuffer();

		for (int i = 0; i < pk_accbooks.length; i++) {
			try {
				// modified by zhaoxnc
				// ͨ�����Ų�ѯ�˲�ʱֱ�ӽ���֯����ȡ��������map�У�keyֵΪ--#�˲�����#
				String pk_org = new String();
				String accbookpk = "#" + pk_accbooks[i] + "#";
				if(!accbookMap.isEmpty() && accbookMap.containsKey(accbookpk)){
					pk_org = accbookMap.get(accbookpk);
				} else {
					pk_org = AMProxy.lookup(IAccountingBookPubService.class).queryFinanceOrgIDSByAccountingBookIDS(new String[]{pk_accbooks[i]})[0];
				}
				AMProxy.lookup(ICloseBook.class).deCloseBook_RequiresNew(pk_group, pk_org, accyear, period, pk_accbooks[i]);
				resultMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0054")/*@res "�˲���"*/ + accbookMap.get(pk_accbooks[i]) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0008")/*@res "�����˳ɹ���"*/ + "\r\n");
			} catch (Exception e) {
				Logger.debug(e.getMessage());
				resultMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0054")/*@res "�˲���"*/ + accbookMap.get(pk_accbooks[i]) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0056")/*@res "������ʧ�ܣ�"*/
						+ ExceptionUtils.getMessage(e) + "\r\n");
			}
		}
		return resultMsg.toString();
	}

	/**
	 * ���ݼ���ȡ�����˲�����(����ѡ���ҵ�񵥾ݻ�ȡ����<�̶��ʲ���Ƭʹ��>)
	 *
	 * @param pk_group
	 *
	 *
	 * @return Map<�����˲������������˲�����>
	 * @throws BusinessException
	 */
	private static Map<String, String> queryAccbookNamesByOrg(String pk_group) throws BusinessException {

		Map<String, String> data = new HashMap<String, String>();
		// ��ѯ��������˲�
		AccountingBookVO[] accounting = AMProxy.lookup(IAccountingBookQryService.class)
				.queryAllAccountingBookVOSByGroupIDAndClause(pk_group, null);
		if (accounting != null && accounting.length > 0) {
			for (int i = 0; i < accounting.length; i++) {
				String pk_accbook = accounting[i].getPk_accountingbook();
				//String name = accounting[i].getName();
				// ȡ������Ҫ�������
				String accbook_name = MultiLanguageUtil.getMultiLanguageValue(accounting[i], AccountingBookVO.NAME);
				data.put(pk_accbook, accbook_name);
				// �˲������Ӧ����֯�����ֵ䣬����SQL����   modified by zhaoxnc
				String accbookpk = "#" + pk_accbook + "#";
				data.put(accbookpk, accounting[i].getPk_relorg());
			}
		}
		return data;
	}

	/**
	 * ������ѯ��Сδ������
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
		// ��ѯ����ѽ�����
		String querySQL = " select pk_accbook ,max(accyear||period) from fa_closebook where pk_accbook in"
				+ InSqlManager.getInSQLValue(pk_accbooks) + " group by pk_accbook";
		// ��ѯ���˵����ݿ��
		Map<String, String> accbookClosePeriod = (Map<String, String>) dbAccessUtil.executeQuery(querySQL,
				new KeyValueMapProcessor<String, String>());
		Map<String, AccperiodVO> closePeriodData = new HashMap<String, AccperiodVO>();
		// û�н��˼�¼���˲�����  add by zhaoxnc
		List<String> pkAccbookString = new ArrayList<String>();
		
		for (int i = 0; i < pk_accbooks.length; i++) {
			String pk_accbook = pk_accbooks[i];
			AccperiodVO minUnClose = null;
			if (accbookClosePeriod.containsKey(pk_accbook)) {
				String yearPeriod = accbookClosePeriod.get(pk_accbook);
				// ����н��˼�¼������СΪ������Ϊ����һ���ڼ�
				String maxCloseYear = yearPeriod.substring(0, 4);
				String maxClosePeriod = yearPeriod.substring(4);
				// ȡ�������µ���һ���£���Сδ�����£�
				AccperiodVO nextaccperiodVO = PeriodManager.getInstance().getNextPeriod(pk_accbook, maxCloseYear, maxClosePeriod);
				minUnClose = nextaccperiodVO;
				closePeriodData.put(pk_accbook, minUnClose);
			} else {
				// ���û�н��˼�¼����Сδ������Ϊ�̶��ʲ������ڼ�
				// û�н��˼�¼���˲��ŵ�List��   add by zhaoxnc
				if(!pkAccbookString.contains(pk_accbook)){
					pkAccbookString.add(pk_accbook);
				}
			}
		}
		// ������ѯû�н��˼�¼���˲��������ڼ�    add by zhaoxnc
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
	 * �����¼�
	 *
	 * @param event
	 * @throws BusinessException
	 */
	protected void fireEvent(BusinessEvent event) throws BusinessException {
		if (event != null) {
			StringBuffer msg = new StringBuffer();
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0057")/*@res "�����¼���ʼ-->\n"*/);
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0058")/*@res "�¼�Դ��"*/ + event.getSourceID()).append(",");
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0059")/*@res "�¼����ͣ�"*/ + event.getEventType()).append(",");
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0060")/*@res "ҵ�����"*/ + event.getObject()).append(".\n");
			log.debug(msg.toString());

			EventDispatcher.fireEvent(event);

			msg.delete(0, msg.length() - 1);
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0057")/*@res "�����¼���ʼ-->\n"*/);
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0058")/*@res "�¼�Դ��"*/ + event.getSourceID()).append(",");
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0059")/*@res "�¼����ͣ�"*/ + event.getEventType()).append(",");
			msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0060")/*@res "ҵ�����"*/ + event.getObject()).append(".\n");
			log.debug(msg.toString());
		} else {
			log.debug(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("closebook_0","02012055-0061")/*@res "������¼�Ϊ�գ�"*/);
		}
	}


	/**
	 * Զ�̼���FORM��Ҫ�����ݣ�Ϊ�˼���������
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
		// ����洢���ݶ���
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
			// ����ѡ����Сδ������
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

		// ���GL034���õ��˲�
		String gl034Accbook = SysInit.getParaString(pk_org,ParameterConst.GL_CHECKACCBOOK_WHENCLOSE); 

		return gl034Accbook;

	}

	
	/**
	 * �Ƿ���Сδ�����ڼ䡣���ݴ������ڲ�ѯ�ڼ䷽�����ڻ���ڼ�Ľ������
	 * 
	 * @param pk_org
	 * @param accyear
	 * @param period
	 * @param pk_accbooks
	 * @throws BusinessException
	 * @return �����˲�
	 * @author zhaoxnc
	 */
	@Override
	public Map<String, Boolean> isCurrentPeriodCloseBooks(String syearmonth, String[] pk_accountingbooks)
			throws BusinessException {
		if(syearmonth.length() != 6){
			String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"closebook_0", "02012055-0069")/*
					 * @res
					 * ��������ڼ��ʽ����
					 */;

			ExceptionUtils.asBusinessException(msg);
		} else if( pk_accountingbooks == null || pk_accountingbooks.length < 1){
			String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"closebook_0", "02012055-0070")/*
					 * @res
					 * δ�����˲�!
					 */;

			ExceptionUtils.asBusinessException(msg);
		} else if(syearmonth.length() == 6 && pk_accountingbooks != null && pk_accountingbooks.length >= 1){
			Map<String, Boolean> isCurrentPeriodClose = new HashMap<String, Boolean>(); 
			
			//���ݸ���String���ô������� accyear �� period
			String accyear = syearmonth.substring(0, 4);
			String period = syearmonth.substring(4, 6);
			
			//���β�ѯÿ���˲��Ƿ����
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
		// ��ѯ����ѽ�����
		String querySQL = " select pk_accbook ,max(accyear||period) from fa_closebook where pk_accbook in"
				+ InSqlManager.getInSQLValue(pk_accbooks)
				+ " group by pk_accbook";
		// ��ѯ���˵����ݿ��
		Map<String, String> accbookClosePeriod = (Map<String, String>) dbAccessUtil.executeQuery(querySQL, new KeyValueMapProcessor<String, String>());
		Map<String, AccperiodVO> closePeriodData = new HashMap<String, AccperiodVO>();
		
		// û�н��˼�¼���˲����� 
		List<String> pkAccbookString = new ArrayList<String>();
		for (int i = 0; i < pk_accbooks.length; i++) {
			String pk_accbook = pk_accbooks[i];
			AccperiodVO minUnClose = null;
			if (accbookClosePeriod.containsKey(pk_accbook)) {
				String yearPeriod = accbookClosePeriod.get(pk_accbook);
				// ����н��˼�¼������СΪ������Ϊ����һ���ڼ�
				String maxCloseYear = yearPeriod.substring(0, 4);
				String maxClosePeriod = yearPeriod.substring(4);
				// ȡ�������µ���һ���£���Сδ�����£�
				AccperiodVO nextaccperiodVO = PeriodManager.getInstance().getNextPeriod(pk_accbook, maxCloseYear, maxClosePeriod);
				minUnClose = nextaccperiodVO;
				closePeriodData.put(pk_accbook, minUnClose);
			} else {
				// ���û�н��˼�¼����Сδ������Ϊ�̶��ʲ������ڼ�
				// û�н��˼�¼���˲��ŵ�List�� 
				if (!pkAccbookString.contains(pk_accbook)) {
					pkAccbookString.add(pk_accbook);
				}
			}
		}
		
		// ��δ���˵��˲��������δ������δ��������˲�
		if(!pkAccbookString.isEmpty()){
			List<String> pkAccbookToQry = new ArrayList<String>();
			if(accbookAndStartPeriod != null && accbookAndStartPeriod.size() > 0 ){
				for( String pkAccbook : pkAccbookString){
					// �����а���δ�����˲�����������
					if(accbookAndStartPeriod.containsKey(pkAccbook)){
						closePeriodData.put(pkAccbook, accbookAndStartPeriod.get(pkAccbook));
					} else {
						// ��δ��������ӵ�δ������δ������˲�����
						pkAccbookToQry.add(pkAccbook);
					}
				}
			} else {
				pkAccbookToQry.addAll(pkAccbookString);
			}
			
			// ������ѯû�н��˼�¼��û����������˲��������ڼ� 
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