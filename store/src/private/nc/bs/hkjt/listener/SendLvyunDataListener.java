package nc.bs.hkjt.listener;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.dao.BaseDAO;
import nc.bs.hrss.pub.Logger;
import nc.bs.ic.general.util.ICEventType;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
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
//	private static String URL = "http://183.129.215.114:41104/pos/router";
	private static BaseDAO DAO = new BaseDAO();
	private static Boolean isUse = Boolean.TRUE;// 是否启用
	private static String CSEG = "1";			// 大类
	private static String SSEG = "1001";		// 小类
	private static String USERCODE = "10SJZYY";	// 操作员-于洋
	private static HashMap<String,Object> BILLTYPECODE;	// 需要同步的绿云的其它入库 交易类型
	static {
		BILLTYPECODE = new HashMap<>();
		BILLTYPECODE.put("4A-Cxx-01", "4A-Cxx-01");
		BILLTYPECODE.put("4A-Cxx-02", "4A-Cxx-02");
	}
	/**
	 * key：单据id
	 * value：审核时间的 时+分
	 */
	private static HashMap<String,String> MAP_audittime = new HashMap<>();
	/**
	 * 根据单据id查询审核时间：时+分
	 */
	private static String queryAuditTime(String id, String type) throws BusinessException {
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
			if (type == null) return auditTime;
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
	/**
	 * 根据公司 返回配置信息
	 * key：pk_org
	 * value：hotel_code、url
	 */
	HashMap<String,String[]> MAP_info = new HashMap<>();
	private String[] getLvyunInfo(String pk_org) throws BusinessException {
		if (!MAP_info.containsKey(pk_org)) {
			// 如果不存在则查询
			String sql = "select hotel_code,url from HK_SRGK_LVYUN_INFO where pk_org = '" + pk_org + "' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)DAO.executeQuery(sql, new MapProcessor());
			if (INFO_MAP == null) throw new BusinessException("请检查配置表");
			String hotelCode = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("hotel_code"));
			String url = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("url"));
			MAP_info.put(pk_org, new String[]{
					hotelCode
					,url
			});
		}
		return MAP_info.get(pk_org);
	}
	/**
	 * 根据仓库 返回吧台信息
	 * key：pk_stor
	 * value：自定义5 吧台编码
	 */
	HashMap<String,String> MAP_batai = new HashMap<>();
	private String getBatai(String pk_stor) throws BusinessException {
		if (!MAP_batai.containsKey(pk_stor)) {
			// 如果不存在则查询
			String sql = "select def5 from bd_stordoc where pk_stordoc = '" + pk_stor + "' and nvl(def5,'~') <> '~' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)DAO.executeQuery(sql, new MapProcessor());
			if (INFO_MAP != null) {
				String def5 = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("def5"));
				MAP_batai.put(
						 pk_stor
						,def5
				);
			} else {
				MAP_batai.put(
						 pk_stor
						,null
				);
			}
		}
		return MAP_batai.get(pk_stor);
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
				String pk_org = hVO.getPk_org();				// 组织
				String billcode = hVO.getVbillcode();			// 单号
//				String billId = hVO.getCgeneralhid();			// ID
				// 需要改成 签字日期
//				String dbilldateStr = PuPubVO.getString_TrimZeroLenAsNull(hVO.getDbilldate());	// 单据日期
				String dbilldateStr = PuPubVO.getString_TrimZeroLenAsNull(hVO.getTaudittime());	// 签字日期
				if (dbilldateStr != null) { // 绿云 只认 年月日 加上 默认的 00:00:00 （HK 2020年10月13日16:02:12）
					dbilldateStr = dbilldateStr.substring(0, 10) + " 00:00:00";
				}
				String pk_stor = hVO.getCwarehouseid();		// 仓库
				if (!BILLTYPECODE.containsKey(billTypeCode)) continue;	// 只有 4A-Cxx-01 才需要发送给绿云
				String batai = getBatai(pk_stor);	// 吧台
				if (batai == null) continue;	// 吧台为空，不做处理
				String[] lyInfo = getLvyunInfo(pk_org);	// 配置信息
				String hotelCode = lyInfo[0];
				/**
				 * TODO 测试
				 */
//				if (true) throw new BusinessException("测试");
				/***END***/
				String url = lyInfo[1] + "/pos/router";
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
//							.append(",m.def8 lyCode ")		// 绿云code
							.append(",ms.def1 lyCode ")		// 绿云code
							.append(" from bd_material m ")
							.append(" left join bd_measdoc dw on m.pk_measdoc = dw.pk_measdoc ")
							.append(" left join bd_materialstock ms on ms.dr = 0 and m.pk_material = ms.pk_material and ms.pk_org = '").append(pk_org).append("' ")
							.append(" where (1=1) ")
//							.append(" and nvl(m.def8,'~') = '~' ")	// 只查询 绿云编码为空的
							.append(" and m.pk_material in ").append(PuPubVO.getSqlInByList(pkInvList)).append(" ")
					;
					ArrayList<PosStoreArticle> list = (ArrayList<PosStoreArticle>) DAO.executeQuery(querySQL.toString(), new BeanListProcessor(PosStoreArticle.class));
					if (list != null && !list.isEmpty()) {
						ArrayList<PosStoreArticle> saveList = new ArrayList<>();	// 需要发送的list
						ArrayList<String> saveInvPkList = new ArrayList<>();	// 需要更新绿云编码的物料
						for (PosStoreArticle item : list) {
							// 如果绿云code为空，才进行发送
							String lyCode = PuPubVO.getString_TrimZeroLenAsNull(item.getLyCode());	// 绿云code
							String MAP_value = lyCode;
							if (lyCode == null || "~".equals(lyCode)) {
								// 因为要做加密处理，所以所有字段不能为null，赋值为""
								if (item.getStandent() == null) item.setStandent("");	// 规格
								item.setHelpCode("");	// 助记码
								item.setRef("");		// 备注
								item.setStorage("");	// 科目代码
								item.setCseg(CSEG);		// 大类
								item.setSseg(SSEG);		// 小类
								saveList.add(item);
								saveInvPkList.add(item.getPk());
								MAP_value = item.getCode();
							}
							MAP_inv.put(item.getPk(), MAP_value);
						}
						if (saveList != null && !saveList.isEmpty()) {
							try {
								SavePosStoreArticle sendData = 
									new SavePosStoreArticle(
										 hotelCode
										,saveList.toArray(new PosStoreArticle[0])
									);
								Logger.error("==========发送给绿云begin==========");
								String sendStr = JSON.writeValueAsString(sendData);
								Logger.error(url);
								Logger.error(sendStr);
								String resStr = HttpUtil.doPost(url, sendStr);
								Logger.error(resStr);
								Logger.error("==========发送给绿云end==========");
								Result res = JSON.readValue(resStr, Result.class);
//								StringBuffer updateSQL_1 = 
//								new StringBuffer("update bd_material_v ")
//									.append(" set def8 = code ")
//									.append(" where ")
//									.append(" pk_material in ").append(PuPubVO.getSqlInByList(saveInvPkList)).append(" ")
//									;
//								StringBuffer updateSQL_2 = 
//								new StringBuffer("update bd_material ")
//									.append(" set def8 = code ")
//									.append(" where ")
//									.append(" pk_material in ").append(PuPubVO.getSqlInByList(saveInvPkList)).append(" ")
//									;
								StringBuffer updateSQL_3 = 
								new StringBuffer("update bd_materialstock ms ")
									.append(" set def1 = (select max(m.code) from bd_material m where m.pk_material = ms.pk_material) ")
									.append(" where ")
									.append(" dr = 0 and pk_org = '").append(pk_org).append("' ")
									.append(" and pk_material in ").append(PuPubVO.getSqlInByList(saveInvPkList)).append(" ")
									;
								if (res.getResultCode() != 0) {
									// 0 为执行成功
									String resultMsg = res.getResultMsg();
									if (resultMsg != null && resultMsg.indexOf("对应的物料已经存在") >= 0) {
										// 对应的物料已经存在，相当于物料存在，则更新物料档案的绿云code
//										DAO.executeUpdate(updateSQL_1.toString());
//										DAO.executeUpdate(updateSQL_2.toString());
										DAO.executeUpdate(updateSQL_3.toString());
									} else {
										throw new BusinessException("绿云：" + res.getResultMsg());
									}
								} else {
									// 如果执行成功，则更新物料档案的绿云code
//									DAO.executeUpdate(updateSQL_1.toString());
//									DAO.executeUpdate(updateSQL_2.toString());
									DAO.executeUpdate(updateSQL_3.toString());
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
							details[i].setPrice("0.00");	// 单价
						}
						SavePosStoreMasterWithDetail sendData = 
							new SavePosStoreMasterWithDetail(
								 hotelCode			// 酒店编码
								,billcode + hhmi	// 单号
								,dbilldateStr		// 时间
								,batai			// 吧台（仓库-自定义5）
								,USERCODE		// 操作员（于洋）
								,""				// 发票号
								,""				// 备注
								,details		// 物料明细
							);
						Logger.error("==========发送给绿云begin==========");
						String sendStr = JSON.writeValueAsString(sendData);
						Logger.error(url);
						Logger.error(sendStr);
						String resStr = HttpUtil.doPost(url, sendStr);
						Logger.error(resStr);
						Logger.error("==========发送给绿云end==========");
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
				if (!BILLTYPECODE.containsKey(billTypeCode)) continue;	// 只有 4A-Cxx-01 才需要发送给绿云
				if (true) {
					// 查询 审核时间
					String approveDate = queryAuditTime(billId, null);
					// 只有当天的单据 可以弃审
//					UFDate approveDate = hVO.getTaudittime();
					UFDate nowDate = new UFDate();
					if (!approveDate.toString().substring(0, 10).equals(nowDate.toString().substring(0, 10))) {
						throw new BusinessException("只能将当天的入库单取消签字。");
					}
					// 放到缓存里
					MAP_audittime.put(billId, getAuditTime(approveDate));
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
				String pk_stor = hVO.getCwarehouseid();		// 仓库
				String pk_org = hVO.getPk_org();	// 组织
				if (!BILLTYPECODE.containsKey(billTypeCode)) continue;	// 只有 4A-Cxx-01 才需要发送给绿云
				String batai = getBatai(pk_stor);	// 吧台
				if (batai == null) continue;	// 吧台为空，不做处理
				String[] lyInfo = getLvyunInfo(pk_org);	// 配置信息
				String hotelCode = lyInfo[0];
				String url = lyInfo[1] + "/pos/router";
				// 删除 入库单发送
				if (true) {
					try {
						String hhmi = MAP_audittime.remove(billId);
						DeletePosStoreMasterWithDetail sendData = 
							new DeletePosStoreMasterWithDetail(
								 hotelCode			// 酒店编码
								,billcode + hhmi	// 单号 + hhmi
						);
						Logger.error("==========发送给绿云begin==========");
						String sendStr = JSON.writeValueAsString(sendData);
						Logger.error(url);
						Logger.error(sendStr);
						String resStr = HttpUtil.doPost(url, sendStr);
						Logger.error(resStr);
						Logger.error("==========发送给绿云end==========");
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
