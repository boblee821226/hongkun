package nc.ui.hkjt.arap.operate.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.progress.IProgressMonitor;
import nc.ui.pub.beans.progress.NCProgresses;
import nc.ui.pubapp.uif2app.query2.action.AfterQuery;
import nc.ui.querytemplate.queryarea.IQueryExecutor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.vo.hkjt.arap.operate.OperateBVO;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.vo.hkjt.arap.operate.OperateHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.query.ConditionVO;

import org.apache.commons.lang.StringUtils;

public class QueryExecutor implements IQueryExecutor {
	private DefaultQueryAction queryAction = null;
	
	private AfterQuery afterQuery = null;

	private IProgressMonitor progressMonitor = null;
	
	public QueryExecutor(DefaultQueryAction action) {
		this.queryAction = action;
	}

	@Override
	public void doQuery(final IQueryScheme queryScheme) {
		if (queryScheme == null) {
			return;
		}

		if (!queryAction.isShowProgress()) {
			try {
				this.queryData(queryScheme);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			// this.showQueryInfo();
			if (queryAction.getShowUpComponent() != null) {
				queryAction.getShowUpComponent().showMeUp();
			}
		}
		else {
			// 操作太频繁直接返回
			if (progressMonitor != null && !progressMonitor.isDone()) {
				return;
			}
			
			// 获得进度任务监控器
			progressMonitor = queryAction.isTPAMonitor() ? queryAction.getTpaProgressUtil().getTPAProgressMonitor() : 
				NCProgresses.createDialogProgressMonitor(null);
			String name = null;
			
			if(StringUtils.isBlank(queryAction.getProgressName())){
				name = NCLangRes.getInstance().getStrByID("_template", "UPP_NewQryTemplate-0141")/*查询中，请稍候...*/;
			}
			else{
				name = queryAction.getProgressName();
			}
			
			
			// 开始任务,总任务数未知
			progressMonitor.beginTask(name, IProgressMonitor.UNKNOWN_TOTAL_TASK);
			progressMonitor.setProcessInfo(name);
	
			//单起线程异步查询时，可能会先执行interceptor，因此在另起线程前，将interceptor备份后，清空queryAction的interceptor
			final ActionInterceptor backupinterceptor = queryAction.getInterceptor() != null ? queryAction.getInterceptor() : null;
			if (backupinterceptor != null)
				queryAction.setInterceptor(null);
			
			SwingWorker<Object[], Object> sw = new SwingWorker<Object[], Object>() {
	      	private Exception failed = null;
	      	
	          protected Object[] doInBackground() throws Exception {
	          		try{
	          			//开始查询时，将备份的拦截器再注入回去
	          			if (backupinterceptor != null)
	          				queryAction.setInterceptor(backupinterceptor);
	          			
	          			queryData(queryScheme);
					}catch(RuntimeException e){ 
						failed = e;
					}
					return null;
	          }
	
	          @Override
	          protected void done() {
	          	if (progressMonitor != null) {
	          		progressMonitor.done();
	          		progressMonitor = null;
	    		}
	          	if (failed != null) {
	          		ShowStatusBarMsgUtil.showErrorMsgWithClear(NCLangRes.getInstance().getStrByID("uif2", "QueryAction-0000")/*查询失败*/,
	          				failed.getMessage(), queryAction.getModel().getContext());
	          		Logger.error(failed.getMessage(), failed);
	          		return;
	          	}
	          	
	          	if (queryAction.getShowUpComponent() != null) {
	          		queryAction.getShowUpComponent().showMeUp();
				}
	          }
	      };
	      
	      sw.execute();
		}
		
	}

	public void queryData(IQueryScheme queryScheme) throws BusinessException {
		// nc.vo.pub.query.ConditionVO[]
		Object conditionObj = queryScheme.get("logicalcondition");
		/**
		 * (
		 * pk_trantype = '1001M910000000BQRK38' 
		 * AND (dbilldate >= '2021-01-03 00:00:00' and dbilldate <= '2021-01-03 23:59:59') 
		 * AND pk_org = '0001N510000000001SY3'
		 * )
		 */
		String where = PuPubVO.getString_TrimZeroLenAsNull(queryScheme.get("where"));
		String pkTrantypeKey = "pk_trantype";
		int pkTrantypeIndex = where.indexOf(pkTrantypeKey);
		String pkTrantype = where.substring(pkTrantypeIndex + pkTrantypeKey.length() + 4, pkTrantypeIndex + pkTrantypeKey.length() + 4 + 20);
		String newWhere = where.replaceAll(pkTrantypeKey + " = '" + pkTrantype + "'", "(1=1)");
		ConditionVO[] conditionList = null;
		if (conditionObj != null && conditionObj instanceof ConditionVO[]) {
			conditionList = (ConditionVO[])conditionObj;
		}
		String accid = null;
		for (ConditionVO item : conditionList) {
			if ("accid".equals(item.getFieldCode())) {
				accid = item.getValue();
			}
		}
		/**
		 * 根据交易类型，来封装不同的过滤sql
		 */
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		List<OperateBillVO> billVOList = new ArrayList<>();
		/**
		 * 0、查询ar账户信息
		 * select a.acc_code, a.acc_name, u.unit_code, u.unit_name
			from hk_arap_accout a
			left join hk_arap_unit_b ub on a.pk_hk_arap_account = ub.pk_account and ub.dr = 0
			left join hk_arap_unit u on ub.pk_hk_arap_unit = u.pk_hk_arap_unit
			where a.acc_code = 349
		 */
		StringBuffer querySQL_head = new StringBuffer()
			.append("select ")
			.append(" a.pk_org ")
			.append(",a.pk_org_v ")
			.append(",a.acc_code vdef01 ")
			.append(",a.acc_name vdef02 ")
			.append(",u.unit_code vdef03 ")
			.append(",u.unit_name vdef04 ")
			.append(" from hk_arap_accout a ")
			.append(" left join hk_arap_unit_b ub on a.pk_hk_arap_account = ub.pk_account and ub.dr = 0 ")
			.append(" left join hk_arap_unit u on ub.pk_hk_arap_unit = u.pk_hk_arap_unit ")
			.append(" where a.acc_code = ").append(accid)	// ar账户
		;
		List<OperateHeadVO> list_head = (List)iUAPQueryBS.executeQuery(
				 querySQL_head.toString()
				,new BeanListProcessor(OperateHeadVO.class)
		);
		for (OperateHeadVO headVO : list_head) {
			/**
			 * 1、查询需要 挂账审计的数据
			 * select 
				b.pk_hk_arap_bill_b, b.pk_hk_arap_bill, 
				b.id,b.gen_date, 
				b.ta_code, b.ta_descript, b.ta_remark,
				b.charge,b.charge9,b.pay,b.pay9,b.balance9
				from hk_arap_bill h
				inner join hk_arap_bill_b b on h.pk_hk_arap_bill = b.pk_hk_arap_bill
				where h.dr = 0 and b.dr = 0
				and accnt = 349
				and nvl(b.nc_gzsj_01,'~') in ('~','N')
			 */
			StringBuffer querySQL_b = new StringBuffer()
				.append("select ")
				.append(" b.pk_hk_arap_bill_b csourcebillbid ")
				.append(",b.pk_hk_arap_bill csourcebillid ")
				.append(",b.id vbdef01 ")
				.append(",b.gen_date vbdef02 ")
				.append(",b.ta_code vbdef03 ")
				.append(",b.ta_descript vbdef04 ")
				.append(",b.ta_remark vbmemo ")
				.append(",b.charge vbdef05 ")
				.append(",b.charge9 vbdef06 ")
				.append(",b.pay vbdef07 ")
				.append(",b.pay9 vbdef08 ")
				.append(",b.balance9 vbdef09 ")
				.append(" from hk_arap_bill h ")
				.append(" inner join hk_arap_bill_b b on h.pk_hk_arap_bill = b.pk_hk_arap_bill ")
				.append(" where h.dr = 0 and b.dr = 0 ")
				.append(" and nvl(b.nc_gzsj_01,'~') in ('~','N') ")
				.append(" and accnt = ").append(accid)	// ar账户
				.append(" and ").append(newWhere)	// 日期、组织 的条件
				.append(" order by b.gen_date desc")
			;
			List<OperateBVO> list_b = (List)iUAPQueryBS.executeQuery(
					 querySQL_b.toString()
					,new BeanListProcessor(OperateBVO.class)
			);
			
			OperateBillVO billVO = new OperateBillVO();
			billVO.setParentVO(headVO);
			billVO.setChildren(new OperateBVO().getMetaData(), list_b.toArray(new OperateBVO[0]));
			billVOList.add(billVO);
		}
		queryAction.getModel().initModel(billVOList.toArray(new OperateBillVO[0]));
		queryAction.showQueryInfo();
	}

	public AfterQuery getAfterQuery() {
		return afterQuery;
	}

	public void setAfterQuery(AfterQuery afterQuery) {
		this.afterQuery = afterQuery;
	}
}
