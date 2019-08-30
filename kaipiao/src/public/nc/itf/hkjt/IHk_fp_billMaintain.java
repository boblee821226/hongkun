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
	 * �����˵���+��֯������ �˵�VO
	 * ���  2016��8��22��21:51:49
	 */
	public ZhangdanBillVO[] queryZhangdanBill(String[] billno,String[] pk_org,Object obj)throws BusinessException;
	
	/**
	 * ִ��sql
	 * ���  2016��8��26��22:12:59
	 */
	public Integer exceSQL(String exceSQL)throws BusinessException;
	
	/**
	 * ��Ʊ��д
	 * ��� 2016��9��8��21:02:25
	 */
	public Object writeFpInfo(ExcelBillVO[] excelVOs,Object other) throws BusinessException;
	
	/**
	 * ���Խӿ�
	 */
	public Object executeTest(Object obj) throws BusinessException;
}
