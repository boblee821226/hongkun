package nc.bs.hkjt.listener;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.dao.BaseDAO;
import nc.bs.ic.general.util.ICEventType;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.hkjt.store.lvyun.in.DeletePosStoreMasterWithDetail;
import nc.vo.hkjt.store.lvyun.in.HttpUtil;
import nc.vo.hkjt.store.lvyun.in.PosStoreArticle;
import nc.vo.hkjt.store.lvyun.in.PosStoreDetail;
import nc.vo.hkjt.store.lvyun.in.Result;
import nc.vo.hkjt.store.lvyun.in.SavePosStoreArticle;
import nc.vo.hkjt.store.lvyun.in.SavePosStoreMasterWithDetail;
import nc.vo.ic.m4a.entity.GeneralInBodyVO;
import nc.vo.ic.m4a.entity.GeneralInHeadVO;
import nc.vo.ic.m4a.entity.GeneralInVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

import org.codehaus.jackson.map.ObjectMapper;

public class SendLvyunDataListener implements IBusinessListener {

	private static ObjectMapper JSON = new ObjectMapper();
	private static String URL = "http://183.129.215.114:41104/pos/router";
	private static BaseDAO DAO = new BaseDAO();
	private static Boolean isUse = Boolean.TRUE;	// 是否启用
	/**
	 * key：单据id
	 * value：审核时间的 时+分
	 */
	private static HashMap<String,String> MAP_audittime = new HashMap<>();
	/**
	 * 根据单据id查询审核时间：时+分
	 */
	private static String queryAuditTime(String id) throws BusinessException {
		String result = null;
		StringBuffer querySQL = 
		new StringBuffer("select ")
			.append(" taudittime ")
			.append(" from ic_generalin_h ")
			.append(" where cgeneralhid = '").append(id).append("' ")
		;
		// 2020-09-15 19:12:30 = 1912
		ArrayList list = (ArrayList)DAO.executeQuery(querySQL.toString(), new ArrayListProcessor());
		if (list != null && !list.isEmpty()) {
			String auditTime = PuPubVO.getString_TrimZeroLenAsNull(((Object[])list.get(0))[0]);
			result = getAuditTime(auditTime);
		}
		return result;
	}
	/**
	 * 获取审核时间中的 时+分
	 */
	private static String getAuditTime(String auditTime) throws BusinessException {
		String result = null;
		if (auditTime != null && auditTime.length() == 19) {
			result = auditTime.substring(11, 13) + auditTime.substring(14, 16);
		}
		return result;
	}
	/**
	 * 获取审核时间中的 时+分
	 */
	private static String getAuditTime(UFDate auditTime) throws BusinessException {
		String result = null;
		result = getAuditTime(auditTime.toString());
		return result;
	}
	
	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		nc.bs.ic.general.businessevent.ICGeneralCommonEvent e = (nc.bs.ic.general.businessevent.ICGeneralCommonEvent)event;
		String eventType = e.getEventType();
		
		if (ICEventType.SignAfter.getCode().equals(eventType)) {
			if(!isUse) return;	// 是否启用
			// 签字后
			for (Object billVoItem : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)billVoItem;
				GeneralInHeadVO hVO = (GeneralInHeadVO)billVO.getParentVO();	//  表头
				String billTypeCode = hVO.getVtrantypecode();	// 入库类型
				String cwarehouseid = hVO.getCwarehouseid();	// 仓库
				String pk_org = hVO.getPk_org();				// 组织
				String billcode = hVO.getVbillcode();			// 单号
				String billId = hVO.getCgeneralhid();			// ID
				String dbilldateStr = PuPubVO.getString_TrimZeroLenAsNull(hVO.getDbilldate());			// 单据日期
				if (!"4A-Cxx-01".equals(billTypeCode)) continue;	// 只有 4A-Cxx-01 才需要发送给绿云
				// 判断仓库是否在 商品分类：营业点里 做过配置。（如果做过配置 就说明该仓库为绿云二级库，就需要发送）
//				{
//					String whereSQL = " dr = 0 " +
//							" and code like 'LY05-%' " +
//							" and pk_org = '" + pk_org + "' " +
//							" and vdef1 = '" + cwarehouseid + "' ";
//					ArrayList<SpflHVO> list = (ArrayList<SpflHVO>)dao.retrieveByClause(SpflHVO.class, whereSQL);
//					if (list == null || list.isEmpty()) {
//						continue;
//					}
//				}
				GeneralInBodyVO[] bVOs = billVO.getBodys();	// 表体
				// 循环表体，找出物料pk
				ArrayList<String> pkInvList = new ArrayList<>();
				for (GeneralInBodyVO bVO : bVOs) {
					pkInvList.add(bVO.getCmaterialvid());
				}
				// 查询出需要 同步的物料数据
				HashMap<String,String> MAP_inv = new HashMap<>();	// 物料缓存：key-NCpk，value-绿云code
				if (true) {
					/**
					 * select 
					 m.code
					,m.name
					,m.materialspec || m.materialtype as standent
					,dw.name unit
					,m.def6 price
					from bd_material m
					left join bd_measdoc dw on m.pk_measdoc = dw.pk_measdoc
					 */
					StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" m.code ")			// 编码
							.append(",m.name ")			// 名称
							.append(",m.materialspec || m.materialtype as standent ")	// 规格
							.append(",dw.name unit")	// 单位
							.append(",m.def6 price ")	// 单价
//							.append(",m.code helpCode ")	// 助记码
//							.append(",'6002' sseg ")		// 小类code
//							.append(",'6' cseg ")			// 大类code
//							.append(",'ref' ref ")			// 备注
//							.append(",'storage' storage ")	// 科目代码
							.append(",m.pk_material pk ")	// 物料pk
							.append(",m.def8 lyCode ")		// 绿云code
							.append(" from bd_material m ")
							.append(" left join bd_measdoc dw on m.pk_measdoc = dw.pk_measdoc ")
							.append(" where (1=1) ")
//							.append(" and nvl(m.def8,'~') = '~' ")	// 只查询 绿云编码为空的
							.append(" and m.pk_material in ").append(PuPubVO.getSqlInByList(pkInvList)).append(" ")
					;
					ArrayList<PosStoreArticle> list = (ArrayList<PosStoreArticle>) DAO.executeQuery(querySQL.toString(), new BeanListProcessor(PosStoreArticle.class));
					if (list != null && !list.isEmpty()) {
						ArrayList<PosStoreArticle> saveList = new ArrayList<>();	// 需要发送的list
						for (PosStoreArticle item : list) {
							// 如果绿云code为空，才进行发送
							String lyCode = PuPubVO.getString_TrimZeroLenAsNull(item.getLyCode());	// 绿云code
							String MAP_value = lyCode;
							if (lyCode == null || "~".equals(lyCode)) {
								// 因为要做加密处理，所以所有字段不能为null，赋值为""
								item.setHelpCode("");	// 助记码
								item.setRef("");		// 备注
								item.setStorage("");	// 科目代码
								item.setCseg("6");		// 大类
								item.setSseg("6002");	// 小类
								saveList.add(item);
								MAP_value = item.getCode();
							}
							MAP_inv.put(item.getPk(), MAP_value);
						}
						if (saveList != null && !saveList.isEmpty()) {
							try {
								SavePosStoreArticle sendData = 
									new SavePosStoreArticle(
										"GCBZ"
										,list.toArray(new PosStoreArticle[0])
									);
								String resStr = HttpUtil.doPost(URL, JSON.writeValueAsString(sendData));
								Result res = JSON.readValue(resStr, Result.class);
								StringBuffer updateSQL_1 = 
								new StringBuffer("update bd_material_v ")
									.append(" set def8 = code ")
									.append(" where ")
									.append(" pk_material in ").append(PuPubVO.getSqlInByList(pkInvList)).append(" ")
									;
								StringBuffer updateSQL_2 = 
								new StringBuffer("update bd_material ")
									.append(" set def8 = code ")
									.append(" where ")
									.append(" pk_material in ").append(PuPubVO.getSqlInByList(pkInvList)).append(" ")
									;
								if (res.getResultCode() != 0) {
									// 0 为执行成功
									String resultMsg = res.getResultMsg();
									if (resultMsg != null && resultMsg.indexOf("对应的物料已经存在") >= 0) {
										// TODO 对应的物料已经存在，相当于物料存在，则更新物料档案的绿云code
										DAO.executeUpdate(updateSQL_1.toString());
										DAO.executeUpdate(updateSQL_2.toString());
									} else {
										throw new BusinessException("绿云：" + res.getResultMsg());
									}
								} else {
									// TODO 如果执行成功，则更新物料档案的绿云code
									DAO.executeUpdate(updateSQL_1.toString());
									DAO.executeUpdate(updateSQL_2.toString());
								}
//								System.out.println("====" + res);
							} catch (Exception e1) {
								throw new BusinessException(e1.getMessage());
							}
						}
					}
//					System.out.println(list);
				}
				// 入库单的发送
				if (true) {
					try {
						String hhmi = getAuditTime(hVO.getTaudittime());
						PosStoreDetail[] details = new PosStoreDetail[bVOs.length];
						for (int i = 0; i < bVOs.length; i++) {
							details[i] = new PosStoreDetail();
							details[i].setArticleCode(MAP_inv.get(bVOs[i].getCmaterialvid()));	// 物料编码
							details[i].setNumber(bVOs[i].getNnum().toString());					// 数量
						}
						SavePosStoreMasterWithDetail sendData = 
							new SavePosStoreMasterWithDetail(
								 "GCBZ"				// 酒店编码
								,billcode + hhmi	// 单号
								,dbilldateStr		// 时间
								,"003"		// 吧台（跟仓库匹配上？）
								,"zwf"		// 操作员（如何找？）
								,""			// 发票号
								,""			// 备注
								,details	// 物料明细
							);
						String resStr = HttpUtil.doPost(URL, JSON.writeValueAsString(sendData));
						Result res = JSON.readValue(resStr, Result.class);
						if (res.getResultCode() != 0) {
							// 0 为执行成功
							throw new BusinessException("绿云：" + res.getResultMsg());
						} else {
							// 执行成功
						}
//						System.out.println("====" + res);
					} catch (Exception e1) {
						throw new BusinessException(e1.getMessage());
					}
				}
			}
//			throw new BusinessException("==签字后==测试");
		} else if (ICEventType.CancelsignBefore.getCode().equals(eventType)) {
			if(!isUse) return;	// 是否启用
			// 取消签字前
			for (Object item : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)item;
				GeneralInHeadVO hVO = (GeneralInHeadVO)billVO.getParentVO();	//  表头
				String billTypeCode = hVO.getVtrantypecode();	// 入库类型
				String billId = hVO.getCgeneralhid();			// ID
				if (!"4A-Cxx-01".equals(billTypeCode)) continue;	// 只有 4A-Cxx-01 才需要发送给绿云
				if (true) {
					// 查询 审核时间
					String hhmi = queryAuditTime(billId);
					// 放到缓存里
					MAP_audittime.put(billId, hhmi);
				}
			}
			// throw new BusinessException("==取消签字后==");
		} else if (ICEventType.CancelsignAfter.getCode().equals(eventType)) {
			if(!isUse) return;	// 是否启用
			// 取消签字后
			for (Object item : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)item;
				GeneralInHeadVO hVO = (GeneralInHeadVO)billVO.getParentVO();	//  表头
				String billTypeCode = hVO.getVtrantypecode();	// 入库类型
				String billcode = hVO.getVbillcode();			// 单号
				String billId = hVO.getCgeneralhid();			// ID
				if (!"4A-Cxx-01".equals(billTypeCode)) continue;	// 只有 4A-Cxx-01 才需要发送给绿云
				// 删除 入库单发送
				if (true) {
					try {
						String hhmi = MAP_audittime.remove(billId);
						DeletePosStoreMasterWithDetail sendData = 
							new DeletePosStoreMasterWithDetail(
								"GCBZ"				// 酒店编码
								,billcode + hhmi	// 单号 + hhmi
						);
						String resStr = HttpUtil.doPost(URL, JSON.writeValueAsString(sendData));
						Result res = JSON.readValue(resStr, Result.class);
						if (res.getResultCode() != 0) {
							// 0 为执行成功
							throw new BusinessException("绿云：" + res.getErrorMsg());
						} else {
							// 执行成功
						}
//						System.out.println("====" + res);
					} catch (Exception e1) {
						throw new BusinessException(e1.getMessage());
					}
				}
			}
//			throw new BusinessException("取消签字后");
		}
	}
}
