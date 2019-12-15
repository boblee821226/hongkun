package nc.impl.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.hkjt.huiyuan.workplugin.HuiyuanPlugin;
import nc.impl.pub.ace.AceHy_kaipiaoinfoPubServiceImpl;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao.bill.BillFpHVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoHVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryHVO;
import nc.itf.hkjt.IHy_kaipiaoinfoMaintain;
import nc.itf.hkjt.PUB_kaipiao;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.bill.BillVOStatus;

public class Hy_kaipiaoinfoMaintainImpl extends AceHy_kaipiaoinfoPubServiceImpl
		implements IHy_kaipiaoinfoMaintain {

	@Override
	public void delete(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] insert(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2016年9月12日20:10:03
		 * 要确保 发票代码+发票号码的 唯一性
		 * clientFullVOs  新增数据  只有 界面数据
		 * 检查 是否为本店的卡
		 */
		HashMap<String,String> MAP_without = new HashMap<String,String>();	// 不受约束的人
		MAP_without.put("1001N510000000002VFH", "1001N510000000002VFH");	// 姚美玲
		MAP_without.put("1001N51000000002G8PK", "1001N51000000002G8PK");	// shengji
		String userId = InvocationInfoProxy.getInstance().getUserId();		// 登录用户id
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			KaipiaoinfoHVO clientHVO = clientFullVOs[i].getParentVO();
			
			// 1、只能开  本店的卡
			if(!MAP_without.containsKey(userId))
			{// 管理员  不用做此 限制。
				
				String dian = HuiyuanPlugin.MAP_corp_dian.get(clientHVO.getPk_org());	// 根据pk_org 得到店名（简称）
				KaipiaoinfoBVO[] bVOs = (KaipiaoinfoBVO[])clientFullVOs[i].getChildrenVO();
				for(int ii=0;ii<bVOs.length;ii++)
				{
					if( dian!=null && !dian.equals( bVOs[ii].getVbdef02() ) )
					{
						throw new BusinessException("不是本店发的卡，不能开票。");
					}
				}
			}
			
			// 2、发票号不能重复
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" fp.pk_hk_huiyuan_kaipiaoinfo ")
						.append(" from hk_huiyuan_kaipiaoinfo fp ")
						.append(" where fp.dr = 0 ")
						.append(" and fp.fph  = '"+clientHVO.getAttributeValue("fph") +"' ")
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
	public KaipiaoinfoBillVO[] update(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2016年9月12日20:11:50
		 * 要确保 发票代码+发票号码的 唯一性
		 * clientFullVOs 是界面的数据
		 * originBills 缓存的数据
		 */
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			KaipiaoinfoHVO clientHVO = clientFullVOs[i].getParentVO();
			KaipiaoinfoHVO cacheHVO  = originBills[i].getParentVO();
			
			if( 
			   clientHVO.getAttributeValue("fph").equals( cacheHVO.getAttributeValue("fph") )
			)
			{// 如果发票代码、号码  ， 界面与 缓存的一致， 则 跳过、不需要处理。
				continue;
			}
			else
			{// 如果 界面 与 缓存 不一致  则需要 查询数据库 来 判断。
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" fp.pk_hk_huiyuan_kaipiaoinfo ")
							.append(" from hk_huiyuan_kaipiaoinfo fp ")
							.append(" where fp.dr = 0 ")
							.append(" and fp.fph  = '"+clientHVO.getAttributeValue("fph") +"' ")
							.append(" and fp.pk_hk_huiyuan_kaipiaoinfo != '"+clientHVO.getPk_hk_huiyuan_kaipiaoinfo()+"' ")
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
	public KaipiaoinfoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KaipiaoinfoBillVO[] save(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] unsave(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] approve(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] unapprove(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public Object updateSQL(String sql, Object other) throws BusinessException {
		
		Integer result = new BaseDAO().executeUpdate(sql);
		
		return result;
	}

	@Override
	public Object checkErrorFP(ArrayList<String> error_key, Object other)
			throws BusinessException {
		
		BillQuery<KaipiaoinfoBillVO> billQuery = new BillQuery<KaipiaoinfoBillVO>(KaipiaoinfoBillVO.class);
		
		String[] keys = new String[error_key.size()];
		keys = error_key.toArray(keys);
		
		// 查询出 需要处理的 开票VO
		KaipiaoinfoBillVO[] kpBillVOs = billQuery.query(keys);
		
//		String ka_code_str = "''";
//		for( int i=0;i<kpBillVOs.length;i++ )
//		{// 针对 错误开票VO 进行处理， 组合kaCode
//			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])kpBillVOs[i].getChildrenVO();
//			for(int j=0;j<kpBVOs.length;j++)
//			{
//				KaipiaoinfoBVO kpBVO = kpBVOs[j];
//				
//				ka_code_str = ka_code_str + ",'" + kpBVO.getKa_code() + "'";
//				
//			}
//		}
//		KaipiaoqueryBillVO[] billVO_source = PUB_kaipiao.bbcx_data(ka_code_str, null, true,"checkError");
//		HashMap<String,KaipiaoqueryHVO> MAP_srouce = new HashMap<String,KaipiaoqueryHVO>();	// key-卡号
//		for(int i=0;billVO_source!=null&&i<billVO_source.length;i++)
//		{
//			KaipiaoqueryHVO hvo = billVO_source[i].getParentVO();
//			MAP_srouce.put(hvo.getKa_code(),hvo);
//		}
		
		HashMap<String,KaipiaoqueryHVO> MAP_srouce = new HashMap<String,KaipiaoqueryHVO>();	// key-卡号
		for( int i=0;i<kpBillVOs.length;i++ )
		{// 针对 错误开票VO 进行处理， 组合kaCode
			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])kpBillVOs[i].getChildrenVO();
			for(int j=0;j<kpBVOs.length;j++)
			{
				KaipiaoinfoBVO kpBVO = kpBVOs[j];
				String ka_code = kpBVO.getKa_code() ;
				
				if( !MAP_srouce.containsKey(ka_code) )
				{
					String ka_code_str = "'" + ka_code + "'";
					KaipiaoqueryBillVO[] billVO_source = PUB_kaipiao.bbcx_data(ka_code_str, null, true,"checkError");
					
					if(billVO_source!=null && billVO_source.length>0)
					{
						MAP_srouce.put(ka_code, billVO_source[0].getParentVO());
					}
				}
				
			}
		}
		
		String errorMsg = "";
		ArrayList<KaipiaoinfoBillVO> update_fp_list = new ArrayList<KaipiaoinfoBillVO>();	// 修改的vo
		ArrayList<KaipiaoinfoBillVO>  cache_fp_list = new ArrayList<KaipiaoinfoBillVO>();	// 原始的vo
		
		//对 错误发票 进行 循环检查
		for( int i=0;i<kpBillVOs.length;i++ )
		{// 针对 错误开票VO 进行检查
			
			KaipiaoinfoBillVO kp_clone = (KaipiaoinfoBillVO)kpBillVOs[i].clone();
			boolean isError = false;
			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])kp_clone.getChildrenVO();
			for(int j=0;j<kpBVOs.length;j++)
			{
				
				KaipiaoinfoBVO kpBVO = kpBVOs[j];
				
				String kaCode = kpBVO.getKa_code();
				
				if(kaCode==null && kaCode.trim().length()<=0)
				{
					throw new BusinessException(""+kp_clone.getParentVO().getFph()+" 有问题，请检查。");
				}
				
				KaipiaoqueryHVO kkp_temp = MAP_srouce.get(kaCode);
				
				if(kkp_temp==null)
				{
					throw new BusinessException(""+kp_clone.getParentVO().getFph()+" 有问题，请检查。");
				}
				
//				if(kkp_temp==null)
//				{
//					System.out.println("======"+kkp_temp);
//				}
				
				if( 	PuPubVO.getUFDouble_NullAsZero(kpBVO.getFpje())
					.compareTo( PuPubVO.getUFDouble_NullAsZero(kkp_temp.getKkpze())
						  .sub( PuPubVO.getUFDouble_NullAsZero(kkp_temp.getYkpze()) ) )<=0 )
				{// 检查是否允许开票
				 // 如果 开票金额 <= 发票总额-已开票总额  则允许开票
					
					kpBVO.setKkpze(kkp_temp.getKkpze());
					kpBVO.setZqkpje(kkp_temp.getYkpze());
					kpBVO.setStatus(BillVOStatus.UPDATED);
					
					// 增加(本次开票金额) 到缓存里的  已开票总额
					kkp_temp.setYkpze( PuPubVO.getUFDouble_NullAsZero(kpBVO.getFpje())
							     .add( PuPubVO.getUFDouble_NullAsZero(kkp_temp.getYkpze()) ) );
					
				}
				else
				{// 如果不满足， 则 跳出，  整单都不能通过。
					errorMsg = errorMsg + kp_clone.getParentVO().getFpdm()+""+kp_clone.getParentVO().getFph()+"-"+ kaCode + "\r\n";
					isError = true;
					break;
				}
			}
			
			if(!isError)
			{// 如果整单 都没有错误，则 可以进行更新
				kp_clone.getParentVO().setVdef02("正常");
				kp_clone.getParentVO().setStatus(BillVOStatus.UPDATED);
				update_fp_list.add(kp_clone);		// 修改
				cache_fp_list.add(kpBillVOs[i]);	// 缓存
			}
		}
		
		//更新数据
		Integer update_num = 0;	// 更新的单据数
		if( update_fp_list!=null && update_fp_list.size()>0 )
		{
			KaipiaoinfoBillVO[] updateVOs = new KaipiaoinfoBillVO[update_fp_list.size()];
			updateVOs = update_fp_list.toArray(updateVOs);
			KaipiaoinfoBillVO[] cacheVOs = new KaipiaoinfoBillVO[cache_fp_list.size()];
			cacheVOs = cache_fp_list.toArray(cacheVOs);
			this.update(updateVOs, cacheVOs);
			update_num = updateVOs.length;
		}
		
		return new Object[]{update_num,errorMsg};
	}

}
