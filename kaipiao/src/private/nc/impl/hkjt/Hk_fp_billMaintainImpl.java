package nc.impl.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.hkjt.arap.workplugin.ImpLyArapData;
import nc.bs.hkjt.huiyuan.workplugin.HuiyuanPlugin;
import nc.bs.hkjt.plugin.UpdateCachePlugin;
import nc.bs.hkjt.srgk.huiguan.zhangdan.workplugin.ImpZhangDanBill;
import nc.bs.hkjt.srgk.jiudian.workplugin.ImpJiudianData;
import nc.bs.hkjt.srgk.jiudian.workplugin.ImpJiudianData_xr;
import nc.bs.hkjt.srgk.lvyun.workplugin.ImpLvyunData;
import nc.bs.hkjt.store.lvyun_out.workplugin.ImpLvyunOutData;
import nc.bs.hkjt.zulin.workplugin.ZnjjsPlugin;
import nc.bs.uap.lock.PKLock;
import nc.impl.pub.ace.AceHk_fp_billPubServiceImpl;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.hkjt.IHk_fp_billMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.vo.hkjt.fapiao.bill.BillFpHVO;
import nc.vo.hkjt.fapiao.bill.ExcelBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.BusinessException;

public class Hk_fp_billMaintainImpl extends AceHk_fp_billPubServiceImpl
		implements IHk_fp_billMaintain {

	@Override
	public void delete(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public BillFpBillVO[] insert(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2016年9月10日12:05:14
		 * 要确保 发票代码+发票号码的 唯一性
		 * clientFullVOs  新增数据  只有 界面数据
		 */
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			BillFpHVO clientHVO = clientFullVOs[i].getParentVO();
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" fp.pk_hk_fapiao_bill ")
						.append(" from hk_fapiao_bill fp ")
						.append(" where fp.dr = 0 ")
						.append(" and fp.fphm = '"+clientHVO.getFphm()+"' ")
//						.append(" and fp.pk_hk_fapiao_bill != '"+clientHVO.getPk_hk_fapiao_bill()+"' ")
			;
			ArrayList list = (ArrayList)new BaseDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null && list.size()>0)
			{
				throw new BusinessException("发票号 不能重复！");
			}
			
		}
		/**END*/
		
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public BillFpBillVO[] update(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2016年9月10日12:05:14
		 * 要确保 发票代码+发票号码的 唯一性
		 * clientFullVOs 是界面的数据
		 * originBills 缓存的数据
		 */
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			BillFpHVO clientHVO = clientFullVOs[i].getParentVO();
			BillFpHVO cacheHVO  = originBills[i].getParentVO();
			
			if( 
				clientHVO.getFphm().equals( cacheHVO.getFphm() )
			)
			{// 如果发票代码、号码  ， 界面与 缓存的一致， 则 跳过、不需要处理。
				continue;
			}
			else
			{// 如果 界面 与 缓存 不一致  则需要 查询数据库 来 判断。
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" fp.pk_hk_fapiao_bill ")
							.append(" from hk_fapiao_bill fp ")
							.append(" where fp.dr = 0 ")
							.append(" and fp.fphm = '"+clientHVO.getFphm()+"' ")
							.append(" and fp.pk_hk_fapiao_bill != '"+clientHVO.getPk_hk_fapiao_bill()+"' ")
				;
				ArrayList list = (ArrayList)new BaseDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
				if(list!=null && list.size()>0)
				{
					throw new BusinessException("发票号 不能重复！");
				}
			}
		}
		/**END*/
		
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public BillFpBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public BillFpBillVO[] save(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillFpBillVO[] unsave(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillFpBillVO[] approve(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillFpBillVO[] unapprove(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZhangdanBillVO[] queryZhangdanBill(String[] billnos, String[] pk_orgs,
			Object obj) throws BusinessException {
		
		BaseDAO dao = new BaseDAO();
		
		String billno = billnos[0];		// 账单号
		String pk_org = pk_orgs[0];		// 公司
		/**
		 * 1、根据账单号 和 pk_org  在NC查询出 账单VO
		 */
		StringBuffer querySQL_1 = 
				new StringBuffer(" select ")
						.append(" zd.pk_hk_dzpt_hg_zhangdan ")
						.append(" from hk_srgk_hg_zhangdan zd ")
						.append(" where zd.dr=0 ")
						.append(" and zd.pk_org = '"+pk_org+"' ")
						.append(" and zd.vbillcode = '"+billno+"' ")
		;
		
		ArrayList list_1 = (ArrayList)dao.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
		
		if( list_1!=null && list_1.size()>0 )
		{
			String pk_bill = PuPubVO.getString_TrimZeroLenAsNull( ((Object[])list_1.get(0))[0] );
			
			BillQuery<ZhangdanBillVO> query = new BillQuery<ZhangdanBillVO>(
					ZhangdanBillVO.class);
			ZhangdanBillVO[] result = query.query(new String[]{pk_bill});
			
			return result;
		}
		
		/**
		 * 2、如果没有查到账单VO， 则去 业务系统 将账单导过来
		 */
		ImpZhangDanBill zdDao = new ImpZhangDanBill();
		ZhangdanBillVO[] result = zdDao.getBillByZDH(billno, pk_org);
		
		return result;
	}

	@Override
	public Integer exceSQL(String exceSQL) throws BusinessException {
		
		BaseDAO dao = new BaseDAO();
		
		Integer result = dao.executeUpdate(exceSQL);
		
		return result;
	}

	@Override
	public Object writeFpInfo(ExcelBillVO[] excelVOs, Object other)
			throws BusinessException {
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{"nc.impl.hkjt.Hk_fp_billMaintainImpl.writeFpInfo"});
		if(!lock){
			throw new BusinessException("其他人正在导入数据,请稍后再操作！");
		}
		
		BaseDAO dao = new BaseDAO();
		
		String[] pks = dao.insertVOArray(excelVOs);
		
		Integer result_1=0,result_2=0;
		
		/**
		 * 1、更新账单开票
		 */
		{
			StringBuffer updateSQL_1 = 
				new StringBuffer("merge into hk_fapiao_bill fp ")
						.append(" using hk_fapiao_bill_temp fpt ")
//						.append(" on (fp.dr=0 and fp.fpdm = fpt.billcode and fp.fphm = fpt.billid ) ")	// 需要按组织过滤，后期处理
						.append(" on (fp.dr=0 and fp.fphm = fpt.billcode || fpt.billid ) ")	// 需要按组织过滤，后期处理（NC只保留 发票号码 2016年10月31日10:32:57）
						.append(" when matched then ")
						.append(" update set ")
						.append(" fp.i_kpkh  = fpt.customername ")
						.append(",fp.i_je    = fpt.amount ")
						.append(",fp.i_jshj  = fpt.pricetax_total ")
						.append(",fp.i_khsbh = fpt.customer_idnumber ")
						.append(",fp.i_kprq  = fpt.bill_date ")
						.append(",fp.i_se    = fpt.tax ")
						.append(",fp.i_spmc  = fpt.tradename ")
						.append(",fp.i_yfpdm = fpt.raw_billcode ")
						.append(",fp.i_yfphm = fpt.raw_billID ")
						.append(",fp.i_zfrq  = fpt.obsolete_date ")
						
			;
			result_1 = dao.executeUpdate(updateSQL_1.toString());
		}
		
		/**
		 * 2、更新会员开票
		 */
		{
			StringBuffer updateSQL_2 = 
					new StringBuffer("merge into hk_huiyuan_kaipiaoinfo kp ")
							.append(" using hk_fapiao_bill_temp fpt ")
//							.append(" on (kp.dr=0 and kp.fpdm = fpt.billcode and kp.fph = fpt.billid ) ")	// 需要按组织过滤，后期处理
							.append(" on (kp.dr=0 and kp.fph = fpt.billcode || fpt.billid ) ") // 需要按组织过滤，后期处理（NC只保留 发票号码 2016年10月31日10:32:57）
							.append(" when matched then ")
							.append(" update set ")
							.append(" kp.i_kpkh  = fpt.customername ")
							.append(",kp.i_je    = fpt.amount ")
							.append(",kp.i_jshj  = fpt.pricetax_total ")
							.append(",kp.i_khsbh = fpt.customer_idnumber ")
							.append(",kp.i_kprq  = fpt.bill_date ")
							.append(",kp.i_se    = fpt.tax ")
							.append(",kp.i_spmc  = fpt.tradename ")
							.append(",kp.i_yfpdm = fpt.raw_billcode ")
							.append(",kp.i_yfphm = fpt.raw_billID ")
							.append(",kp.i_zfrq  = fpt.obsolete_date ")
							
				;
				result_2 = dao.executeUpdate(updateSQL_2.toString());
			}
		
		return result_1+result_2;
	}

	@Override
	public Object executeTest(Object obj) throws BusinessException {
		
		System.out.println("==obj=="+obj);
		
		/**
		 * 账单测试
		 */
		ImpZhangDanBill bb = new ImpZhangDanBill();
		Object result = bb.executeTest(obj);
		
		/**
		 * 会员测试
		 */
//		HuiyuanPlugin hy = new HuiyuanPlugin();
//		Object result = hy.executeTest(obj);
		
		/**
		 * 西软测试
		 */
//		ImpJiudianData_xr xr = new ImpJiudianData_xr();
//		Object result = xr.executeTest(obj);
		
		/**
		 * 千里马测试
		 */
//		ImpJiudianData qlm = new ImpJiudianData();
//		Object result = qlm.executeTest(obj);
		
		/**
		 * 滞纳金
		 */
//		ZnjjsPlugin znj = new ZnjjsPlugin();
//		Object result = znj.executeTest(obj);
		
		/**
		 * 绿云测试
		 */
//		ImpLvyunData lvyun = new ImpLvyunData();
//		Object result = lvyun.executeTest(obj);
		
		/**
		 * 绿云出库测试
		 */
//		ImpLvyunOutData lvyunOut = new ImpLvyunOutData();
//		Object result = lvyunOut.executeTest(obj);
		
		/**
		 * 绿云应收账务
		 */
//		ImpLyArapData lvyun = new ImpLyArapData();
//		Object result = lvyun.executeTest(obj);
		
		/**
		 * 档案缓存
		 */
//		UpdateCachePlugin cache = new UpdateCachePlugin();
//		Object result = cache.test(obj);
		
		return result;
	}

	
}