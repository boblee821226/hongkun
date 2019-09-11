package nc.itf.hkjt.report;

import java.util.HashMap;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * 报表接口、以及其它乱七八糟的接口 都在此实现。
 */
public interface HkjtReportITF {

	/**
	 * 所有报表查询的统一入口方法 
	 */
	public DataSet queryReport(
			SmartContext context,
			HashMap<String, Object> param,
			String type,
			String flag,
			Object other
	) throws Exception;

	/**
	 * 现金管理-结算  发送邮件
	 */
	public Object sendMail(Object data,Object other)
	throws BusinessException;
	
	/**
	 * 根据发票VO，判断是否应该生成 出库调整单
	 * 发票金额 与 入库金额 不一致，并且，期间有出库的情况下，需要生成调整单。
	 */
	public Object genCktzdByPoInvoice(InvoiceVO[] poInvoiceVOs, Object ohter) 
	throws BusinessException;
	/**
	 * 根据发票VO，判断是否应该生成 原币原值调整单
	 * 结算单 金额不一致，并且物料为 固定资产属性，才需要处理。
	 */
	public Object genYbyztzByPoInvoice(InvoiceVO[] poInvoiceVOs, Object ohter) 
			throws BusinessException;
}
