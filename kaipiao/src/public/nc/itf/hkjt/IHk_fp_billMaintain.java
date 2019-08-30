package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.vo.hkjt.fapiao.bill.ExcelBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_fp_billMaintain {

	public void delete(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException;

	public BillFpBillVO[] insert(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException;

	public BillFpBillVO[] update(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException;

	public BillFpBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public BillFpBillVO[] save(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException;

	public BillFpBillVO[] unsave(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException;

	public BillFpBillVO[] approve(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException;

	public BillFpBillVO[] unapprove(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException;
	
	/**
	 * 根据账单号+组织，返回 账单VO
	 * 李彬  2016年8月22日21:51:49
	 */
	public ZhangdanBillVO[] queryZhangdanBill(String[] billno,String[] pk_org,Object obj)throws BusinessException;
	
	/**
	 * 执行sql
	 * 李彬  2016年8月26日22:12:59
	 */
	public Integer exceSQL(String exceSQL)throws BusinessException;
	
	/**
	 * 发票回写
	 * 李彬 2016年9月8日21:02:25
	 */
	public Object writeFpInfo(ExcelBillVO[] excelVOs,Object other) throws BusinessException;
	
	/**
	 * 测试接口
	 */
	public Object executeTest(Object obj) throws BusinessException;
}
