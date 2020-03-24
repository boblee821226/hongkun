package nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action;

import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.trade.business.HYPubBO;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuHVO;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class AceHg_sgshujuBeforeSaveAction extends ReWriteBaseAction{
	@Override
	public void execute(SgshujuBillVO aggvo) throws BusinessException {
		String dbilldate=aggvo.getParentVO().getDbilldate().toString().substring(0,10);
		SgshujuHVO[]hvo=(SgshujuHVO[])getSuperDMO().queryByWhereClause(
				SgshujuHVO .class, 
				" nvl(dr,0)=0 and substr(dbilldate,0,10)='"+dbilldate+"' " +
				" and pk_org='"+aggvo.getParentVO().getPk_org()+"' " +
				" and pk_hk_srgk_hg_sgshuju <>'"+aggvo.getParentVO().getPk_hk_srgk_hg_sgshuju()+"' " +
				" and nvl(replace(vdef10,'~',''),'N') = '"+aggvo.getParentVO().getVdef10()+"' "		// HK 2018年11月6日14:42:09 按是否酒店 去查找
		);
		if(hvo!=null&&hvo.length>0){
			throw new BusinessException("业务日期["+dbilldate+"]的单据已存在，不允许重复保存！");
		}
		
		SgshujuBVO[] bvos=(SgshujuBVO[])aggvo.getChildrenVO();
		String err="";
		for (SgshujuBVO sgshujuBVO : bvos) {
			boolean group1=(sgshujuBVO.getTz_km_jzfs_1()!=null||sgshujuBVO.getTz_km_srxm_1()!=null)&&sgshujuBVO.getTz_km_data_1()==null;
			boolean group2=(sgshujuBVO.getTz_km_jzfs_2()!=null||sgshujuBVO.getTz_km_srxm_2()!=null)&&sgshujuBVO.getTz_km_data_2()==null;
			if(group1||group2){
				err+="行号["+sgshujuBVO.getVrowno()+"]表体 调整-"+(group1?" A ":"")+(group2?" B  ":"")+"组中【数据】字段不能为空！\r\n";
			}
		}
		
		SgshujuHVO hVO = aggvo.getParentVO();
		
//		/**
//		 * HK 2019年6月6日16:12:31
//		 * 1、如果 收入项目 有值，则 部门 和 类型 是必填。
//		 * 2、如果 是酒店  类型 必是 收入， 会馆的话 无所谓。
//		 */
//		{
//			String pk_org = hVO.getPk_org();	// 组织
//			String vdef10 = hVO.getVdef10();	// 自定义项-是否酒店
//			boolean isJD = false;				// 是否酒店
//			if("Y".equals(vdef10)&&HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org)){
//				isJD = true;
//			}else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)) {
//				
//			}else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)) {
//				isJD = true;
//			}
//			
//			for (SgshujuBVO bVO : bvos) {
//				
//				String 		 vrowno = bVO.getVrowno();	// 行号
//				String 		  bm_pk = PuPubVO.getString_TrimZeroLenAsNull( bVO.getBm_pk() );		// 部门
//				String tz_km_srxm_1 = PuPubVO.getString_TrimZeroLenAsNull( bVO.getTz_km_srxm_1() );	// 收入项目1
//				String tz_km_srxm_2 = PuPubVO.getString_TrimZeroLenAsNull( bVO.getTz_km_srxm_2() );	// 收入项目2
//				String tz_km_srxm_type1 = PuPubVO.getString_TrimZeroLenAsNull( bVO.getTz_km_srxm_type1() );	// 类型1
//				String tz_km_srxm_type2 = PuPubVO.getString_TrimZeroLenAsNull( bVO.getTz_km_srxm_type1() );	// 类型2
//				
//				if( 
//				   (tz_km_srxm_1!=null || tz_km_srxm_2!=null) 
//				&& (bm_pk==null)
//				){
//					err+="行号["+vrowno+"]填了收入项目，则部门必填。\r\n";
//				}
//				
//				if( 
//				   (tz_km_srxm_1!=null && tz_km_srxm_type1==null)
//				|| (tz_km_srxm_2!=null && tz_km_srxm_type2==null)
//				){
//					err+="行号["+vrowno+"]填了收入项目，则类型必填。\r\n";
//				}
//				
//				if(
//				   ( 
//					 (tz_km_srxm_type1!=null && !tz_km_srxm_type1.equals("sr"))
//				     ||
//				     (tz_km_srxm_type2!=null && !tz_km_srxm_type2.equals("sr"))
//				   )
//				&& (isJD)
//				){
//					err+="行号["+vrowno+"]酒店的类型，只能填(收入)。\r\n";
//				}
//			}
//		}
		
		/**
		 * 2019年8月2日18:02:52
		 * 保存校验
		 * 1、酒店和会馆，无论日审在手工数据A列还是B列调整结账方式，且数据不为“零”时，类型都必须选择新增的类型{结账方式}，方可保存手工数据，否则无法保存。
		 */
		{
			
			String pk_org = hVO.getPk_org();	// 组织
			String vdef10 = hVO.getVdef10();	// 自定义项-是否酒店
			boolean isJD = false;				// 是否酒店
//			if("Y".equals(vdef10)&&HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org)){
//				isJD = true;
//			}else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)) {
//				
//			}else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)) {
//				isJD = true;
//			}
			// 2020年3月24日16:28:29
			if ("Y".equals(vdef10)) {
				isJD = true;
			}
			// key=收入项目pk value=收入项目编码
			HashMap<String, String> MAP_SRXM = new HashMap<String, String>();
			HYPubBO dao = new HYPubBO();
			for( int j=0;j<bvos.length;j++ )
			{
				SgshujuBVO bvo = bvos[j];
				
				String rowno = bvo.getVrowno();
				
				String jzfsA 	= bvo.getTz_km_jzfs_1();		// 结账方式A
				String srxmA 	= bvo.getTz_km_srxm_1();		// 收入项目A
				UFDouble dataA 	= PuPubVO.getUFDouble_ValueAsValue(bvo.getTz_km_data_1());		// 数据A
				String typeA 	= bvo.getTz_km_srxm_type1();	// 类型A
//				String infoA 	= PuPubVO.getString_TrimZeroLenAsNull( bvo.getTz_km_info_1() );	// 明细A
				
				String jzfsB 	= bvo.getTz_km_jzfs_2();		// 结账方式B
				String srxmB 	= bvo.getTz_km_srxm_2();		// 收入项目B
				UFDouble dataB 	= PuPubVO.getUFDouble_ValueAsValue(bvo.getTz_km_data_2());		// 数据B
				String typeB 	= bvo.getTz_km_srxm_type2();	// 类型B
//				String infoB 	= PuPubVO.getString_TrimZeroLenAsNull( bvo.getTz_km_info_2() );	// 明细B
				
				String bm_pk	= bvo.getBm_pk();	// 部门
				
				/**
				 * 如果数据不为空
				 * 结账方式 和 收入项目 不能同时存在，也不能同时为空
				 */
				if(dataA!=null)
				{
					if(jzfsA!=null && srxmA!=null)
					{
						throw new BusinessException("【"+rowno+"】行，{结账方式A}和{收入项目A}不能同时录入。");
					}
					if(jzfsA==null && srxmA==null)
					{
						throw new BusinessException("【"+rowno+"】行，{结账方式A}和{收入项目A}必须填写一种。");
					}
				}
				if(dataB!=null)
				{
					if(jzfsB!=null && srxmB!=null)
					{
						throw new BusinessException("【"+rowno+"】行，{结账方式B}和{收入项目B}不能同时录入。");
					}
					if(jzfsB==null && srxmB==null)
					{
						throw new BusinessException("【"+rowno+"】行，{结账方式B}和{收入项目B}必须填写一种。");
					}
				}
				
				/**
				 *  结账方式 录入了的 必要判断
				 *  数据必须有，类型=结账方式
				 */
				if(jzfsA!=null)
				{
					if(dataA==null){
						throw new BusinessException("【"+rowno+"】行，{结账方式A}已经录入了，所以必须录入{数据A}。");
					}
					else if(!"jzfs".equals(typeA)){
						throw new BusinessException("【"+rowno+"】行，{结账方式A}已经录入了，所以{类型A}必须为[结账方式]。");
					}
				}
				if(jzfsB!=null)
				{
					if(dataB==null){
						throw new BusinessException("【"+rowno+"】行，{结账方式B}已经录入了，所以必须录入{数据B}。");
					}
					else if(!"jzfs".equals(typeB)){
						throw new BusinessException("【"+rowno+"】行，{结账方式B}已经录入了，所以{类型B}必须为[结账方式]。");
					}
				}
				
				/**
				 * 如果是酒店，并且 收入项目非空，
				 * 则 类型=收入
				 */
				if(isJD)
				{
					if(srxmA!=null){
						if(!"sr".equals(typeA)){
							throw new BusinessException("【"+rowno+"】行，【酒店】的{收入项目A}已经录入了，所以{类型A}必须为[收入]。");
						}
					}
					if(srxmB!=null){
						if(!"sr".equals(typeB)){
							throw new BusinessException("【"+rowno+"】行，【酒店】的{收入项目B}已经录入了，所以{类型B}必须为[收入]。");
						}
					}
					
					if(srxmA!=null && "sr".equals(typeA)){
						if(bm_pk==null){
							throw new BusinessException("【"+rowno+"】行，【酒店】的A列调整{收入}，必须录入{部门}。");
						}
					}
				}
				
				/**
				 * 如果是会馆，并且 在A调整的是收入，
				 * 则 B 只能是自动优惠，并且部门必输。
				 */
				if(!isJD)
				{
					if(srxmA!=null && "sr".equals(typeA)){
						if(bm_pk==null){
							throw new BusinessException("【"+rowno+"】行，【会馆】的A列调整{收入}，必须录入{部门}。");
						}
						if(srxmB==null){
							throw new BusinessException("【"+rowno+"】行，【会馆】的A列调整{收入}，B列必须填{收入项目}。");
						}
						if(!"zdyh".equals(typeB)){
							throw new BusinessException("【"+rowno+"】行，【会馆】的A列调整{收入}，B列必须是调整{自动优惠} 。");
						}
					}
					
					if(srxmA!=null) {
						if(
							!"sr".equals(typeA)
						 && !"zdyh".equals(typeA)
						) {
							throw new BusinessException("【"+rowno+"】行，【会馆】的{收入项目A}已经录入了，A列必须是调整{收入、自动优惠} 。");
						}
					}
					
					if(srxmB!=null) {
						if(
							!"sr".equals(typeB)
						 && !"zdyh".equals(typeB)
						) {
							throw new BusinessException("【"+rowno+"】行，【会馆】的{收入项目B}已经录入了，B列必须是调整{收入、自动优惠} 。");
						}
					}
				}
				
				/**
				 * 2020年3月24日16:30:32
				 * 如果是酒店，则要判断，如果收入项目的编码是 LY01 开头， 那么 3个自定义档案 是必输的
				 */
				if (isJD) {
					String vbdef01 = PuPubVO.getString_TrimZeroLenAsNull(bvo.getVbdef01());
					String vbdef02 = PuPubVO.getString_TrimZeroLenAsNull(bvo.getVbdef02());
					String vbdef03 = PuPubVO.getString_TrimZeroLenAsNull(bvo.getVbdef03());
					if (srxmA != null) {
						String srxm_code = null;
						if (MAP_SRXM.containsKey(srxmA)) {
							srxm_code = MAP_SRXM.get(srxmA);
						} else {
							// 查数据库
							SrxmHVO vo = (SrxmHVO)dao.queryByPrimaryKey(SrxmHVO.class, srxmA);
							if (vo != null) {
								srxm_code = vo.getCode();
							}
							MAP_SRXM.put(srxmA, srxm_code);
						}
						if (srxm_code != null
						 && srxm_code.startsWith("LY01")
						) {
							if( vbdef01 == null
							 || vbdef02 == null
							 || vbdef03 == null
							) {
								throw new BusinessException("【"+rowno+"】行，【酒店】的{收入项目A}为客房收入，所以{市场、渠道、来源}必填 。");
							}
						}
					}
					if (srxmB != null) {
						String srxm_code = null;
						if (MAP_SRXM.containsKey(srxmB)) {
							srxm_code = MAP_SRXM.get(srxmB);
						} else {
							// 查数据库
							SrxmHVO vo = (SrxmHVO)dao.queryByPrimaryKey(SrxmHVO.class, srxmB);
							if (vo != null) {
								srxm_code = vo.getCode();
							}
							MAP_SRXM.put(srxmB, srxm_code);
						}
						if (srxm_code != null
						 && srxm_code.startsWith("LY01")
						) {
							if( vbdef01 == null
							 || vbdef02 == null
							 || vbdef03 == null
							) {
								throw new BusinessException("【"+rowno+"】行，【酒店】的{收入项目B}为客房收入，所以{市场、渠道、来源}必填 。");
							}
						}
					}
				}
			}
		}
		/***END***/
		
		if(err.length()>0){
			throw new BusinessException(err);
		}
	}

}
