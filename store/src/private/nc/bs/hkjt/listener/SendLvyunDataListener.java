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
	private static Boolean isUse = Boolean.TRUE;// �Ƿ�����
	private static String CSEG = "1";			// ����
	private static String SSEG = "1001";		// С��
	private static String USERCODE = "10SJZYY";	// ����Ա-����
	private static HashMap<String,Object> BILLTYPECODE;	// ��Ҫͬ�������Ƶ�������� ��������
	static {
		BILLTYPECODE = new HashMap<>();
		BILLTYPECODE.put("4A-Cxx-01", "4A-Cxx-01");
		BILLTYPECODE.put("4A-Cxx-02", "4A-Cxx-02");
	}
	/**
	 * key������id
	 * value�����ʱ��� ʱ+��
	 */
	private static HashMap<String,String> MAP_audittime = new HashMap<>();
	/**
	 * ���ݵ���id��ѯ���ʱ�䣺ʱ+��
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
	 * ��ȡ���ʱ���е� ʱ+��
	 */
	private static String getAuditTime(String auditTime) throws BusinessException {
		String result = null;
		if (auditTime != null && auditTime.length() == 19) {
			result = auditTime.substring(11, 13) + auditTime.substring(14, 16);
		}
		return result;
	}
	/**
	 * ��ȡ���ʱ���е� ʱ+��
	 */
	private static String getAuditTime(UFDate auditTime) throws BusinessException {
		String result = null;
		result = getAuditTime(auditTime.toString());
		return result;
	}
	/**
	 * ���ݹ�˾ ����������Ϣ
	 * key��pk_org
	 * value��hotel_code��url
	 */
	HashMap<String,String[]> MAP_info = new HashMap<>();
	private String[] getLvyunInfo(String pk_org) throws BusinessException {
		if (!MAP_info.containsKey(pk_org)) {
			// ������������ѯ
			String sql = "select hotel_code,url from HK_SRGK_LVYUN_INFO where pk_org = '" + pk_org + "' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)DAO.executeQuery(sql, new MapProcessor());
			if (INFO_MAP == null) throw new BusinessException("�������ñ�");
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
	 * ���ݲֿ� ���ذ�̨��Ϣ
	 * key��pk_stor
	 * value���Զ���5 ��̨����
	 */
	HashMap<String,String> MAP_batai = new HashMap<>();
	private String getBatai(String pk_stor) throws BusinessException {
		if (!MAP_batai.containsKey(pk_stor)) {
			// ������������ѯ
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
			if(!isUse) return;	// �Ƿ�����
			// ǩ�ֺ�
			for (Object billVoItem : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)billVoItem;
				GeneralInHeadVO hVO = (GeneralInHeadVO)billVO.getParentVO();	//  ��ͷ
				String billTypeCode = hVO.getVtrantypecode();	// �������
				String pk_org = hVO.getPk_org();				// ��֯
				String billcode = hVO.getVbillcode();			// ����
//				String billId = hVO.getCgeneralhid();			// ID
				// ��Ҫ�ĳ� ǩ������
//				String dbilldateStr = PuPubVO.getString_TrimZeroLenAsNull(hVO.getDbilldate());	// ��������
				String dbilldateStr = PuPubVO.getString_TrimZeroLenAsNull(hVO.getTaudittime());	// ǩ������
				if (dbilldateStr != null) { // ���� ֻ�� ������ ���� Ĭ�ϵ� 00:00:00 ��HK 2020��10��13��16:02:12��
					dbilldateStr = dbilldateStr.substring(0, 10) + " 00:00:00";
				}
				String pk_stor = hVO.getCwarehouseid();		// �ֿ�
				if (!BILLTYPECODE.containsKey(billTypeCode)) continue;	// ֻ�� 4A-Cxx-01 ����Ҫ���͸�����
				String batai = getBatai(pk_stor);	// ��̨
				if (batai == null) continue;	// ��̨Ϊ�գ���������
				String[] lyInfo = getLvyunInfo(pk_org);	// ������Ϣ
				String hotelCode = lyInfo[0];
				/**
				 * TODO ����
				 */
				if (true) throw new BusinessException("����");
				/***END***/
				String url = lyInfo[1] + "/pos/router";
				// �жϲֿ��Ƿ��� ��Ʒ���ࣺӪҵ���� �������á�������������� ��˵���òֿ�Ϊ���ƶ����⣬����Ҫ���ͣ�
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
				GeneralInBodyVO[] bVOs = billVO.getBodys();	// ����
				// ѭ�����壬�ҳ�����pk
				ArrayList<String> pkInvList = new ArrayList<>();
				for (GeneralInBodyVO bVO : bVOs) {
					pkInvList.add(bVO.getCmaterialvid());
				}
				// ��ѯ����Ҫ ͬ������������
				HashMap<String,String> MAP_inv = new HashMap<>();	// ���ϻ��棺key-NCpk��value-����code
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
							.append(" m.code ")			// ����
							.append(",m.name ")			// ����
							.append(",m.materialspec || m.materialtype as standent ")	// ���
							.append(",dw.name unit")	// ��λ
							.append(",m.def6 price ")	// ����
//							.append(",m.code helpCode ")	// ������
//							.append(",'6002' sseg ")		// С��code
//							.append(",'6' cseg ")			// ����code
//							.append(",'ref' ref ")			// ��ע
//							.append(",'storage' storage ")	// ��Ŀ����
							.append(",m.pk_material pk ")	// ����pk
//							.append(",m.def8 lyCode ")		// ����code
							.append(",ms.def1 lyCode ")		// ����code
							.append(" from bd_material m ")
							.append(" left join bd_measdoc dw on m.pk_measdoc = dw.pk_measdoc ")
							.append(" left join bd_materialstock ms on ms.dr = 0 and m.pk_material = ms.pk_material and ms.pk_org = '").append(pk_org).append("' ")
							.append(" where (1=1) ")
//							.append(" and nvl(m.def8,'~') = '~' ")	// ֻ��ѯ ���Ʊ���Ϊ�յ�
							.append(" and m.pk_material in ").append(PuPubVO.getSqlInByList(pkInvList)).append(" ")
					;
					ArrayList<PosStoreArticle> list = (ArrayList<PosStoreArticle>) DAO.executeQuery(querySQL.toString(), new BeanListProcessor(PosStoreArticle.class));
					if (list != null && !list.isEmpty()) {
						ArrayList<PosStoreArticle> saveList = new ArrayList<>();	// ��Ҫ���͵�list
						ArrayList<String> saveInvPkList = new ArrayList<>();	// ��Ҫ�������Ʊ��������
						for (PosStoreArticle item : list) {
							// �������codeΪ�գ��Ž��з���
							String lyCode = PuPubVO.getString_TrimZeroLenAsNull(item.getLyCode());	// ����code
							String MAP_value = lyCode;
							if (lyCode == null || "~".equals(lyCode)) {
								// ��ΪҪ�����ܴ������������ֶβ���Ϊnull����ֵΪ""
								if (item.getStandent() == null) item.setStandent("");	// ���
								item.setHelpCode("");	// ������
								item.setRef("");		// ��ע
								item.setStorage("");	// ��Ŀ����
								item.setCseg(CSEG);		// ����
								item.setSseg(SSEG);		// С��
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
								String resStr = HttpUtil.doPost(url, JSON.writeValueAsString(sendData));
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
									// 0 Ϊִ�гɹ�
									String resultMsg = res.getResultMsg();
									if (resultMsg != null && resultMsg.indexOf("��Ӧ�������Ѿ�����") >= 0) {
										// ��Ӧ�������Ѿ����ڣ��൱�����ϴ��ڣ���������ϵ���������code
//										DAO.executeUpdate(updateSQL_1.toString());
//										DAO.executeUpdate(updateSQL_2.toString());
										DAO.executeUpdate(updateSQL_3.toString());
									} else {
										throw new BusinessException("���ƣ�" + res.getResultMsg());
									}
								} else {
									// ���ִ�гɹ�����������ϵ���������code
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
				// ��ⵥ�ķ���
				if (true) {
					try {
						String hhmi = getAuditTime(hVO.getTaudittime());
						PosStoreDetail[] details = new PosStoreDetail[bVOs.length];
						for (int i = 0; i < bVOs.length; i++) {
							details[i] = new PosStoreDetail();
							details[i].setArticleCode(MAP_inv.get(bVOs[i].getCmaterialvid()));	// ���ϱ���
							details[i].setNumber(bVOs[i].getNnum().toString());					// ����
							details[i].setPrice("0.00");	// ����
						}
						SavePosStoreMasterWithDetail sendData = 
							new SavePosStoreMasterWithDetail(
								 hotelCode			// �Ƶ����
								,billcode + hhmi	// ����
								,dbilldateStr		// ʱ��
								,batai			// ��̨���ֿ�-�Զ���5��
								,USERCODE		// ����Ա������
								,""				// ��Ʊ��
								,""				// ��ע
								,details		// ������ϸ
							);
						String resStr = HttpUtil.doPost(url, JSON.writeValueAsString(sendData));
						Result res = JSON.readValue(resStr, Result.class);
						if (res.getResultCode() != 0) {
							// 0 Ϊִ�гɹ�
							throw new BusinessException("���ƣ�" + res.getResultMsg());
						} else {
							// ִ�гɹ�
						}
//						System.out.println("====" + res);
					} catch (Exception e1) {
						throw new BusinessException(e1.getMessage());
					}
				}
			}
//			throw new BusinessException("==ǩ�ֺ�==����");
		} else if (ICEventType.CancelsignBefore.getCode().equals(eventType)) {
			if(!isUse) return;	// �Ƿ�����
			// ȡ��ǩ��ǰ
			for (Object item : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)item;
				GeneralInHeadVO hVO = (GeneralInHeadVO)billVO.getParentVO();	//  ��ͷ
				String billTypeCode = hVO.getVtrantypecode();	// �������
				String billId = hVO.getCgeneralhid();			// ID
				if (!BILLTYPECODE.containsKey(billTypeCode)) continue;	// ֻ�� 4A-Cxx-01 ����Ҫ���͸�����
				if (true) {
					// ��ѯ ���ʱ��
					String approveDate = queryAuditTime(billId, null);
					// ֻ�е���ĵ��� ��������
//					UFDate approveDate = hVO.getTaudittime();
					UFDate nowDate = new UFDate();
					if (!approveDate.toString().substring(0, 10).equals(nowDate.toString().substring(0, 10))) {
						throw new BusinessException("ֻ�ܽ��������ⵥȡ��ǩ�֡�");
					}
					// �ŵ�������
					MAP_audittime.put(billId, getAuditTime(approveDate));
				}
			}
			// throw new BusinessException("==ȡ��ǩ�ֺ�==");
		} else if (ICEventType.CancelsignAfter.getCode().equals(eventType)) {
			if(!isUse) return;	// �Ƿ�����
			// ȡ��ǩ�ֺ�
			for (Object item : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)item;
				GeneralInHeadVO hVO = (GeneralInHeadVO)billVO.getParentVO();	//  ��ͷ
				String billTypeCode = hVO.getVtrantypecode();	// �������
				String billcode = hVO.getVbillcode();			// ����
				String billId = hVO.getCgeneralhid();			// ID
				String pk_stor = hVO.getCwarehouseid();		// �ֿ�
				String pk_org = hVO.getPk_org();	// ��֯
				if (!BILLTYPECODE.containsKey(billTypeCode)) continue;	// ֻ�� 4A-Cxx-01 ����Ҫ���͸�����
				String batai = getBatai(pk_stor);	// ��̨
				if (batai == null) continue;	// ��̨Ϊ�գ���������
				String[] lyInfo = getLvyunInfo(pk_org);	// ������Ϣ
				String hotelCode = lyInfo[0];
				String url = lyInfo[1] + "/pos/router";
				// ɾ�� ��ⵥ����
				if (true) {
					try {
						String hhmi = MAP_audittime.remove(billId);
						DeletePosStoreMasterWithDetail sendData = 
							new DeletePosStoreMasterWithDetail(
								 hotelCode			// �Ƶ����
								,billcode + hhmi	// ���� + hhmi
						);
						String resStr = HttpUtil.doPost(url, JSON.writeValueAsString(sendData));
						Result res = JSON.readValue(resStr, Result.class);
						if (res.getResultCode() != 0) {
							// 0 Ϊִ�гɹ�
							throw new BusinessException("���ƣ�" + res.getErrorMsg());
						} else {
							// ִ�гɹ�
						}
//						System.out.println("====" + res);
					} catch (Exception e1) {
						throw new BusinessException(e1.getMessage());
					}
				}
			}
//			throw new BusinessException("ȡ��ǩ�ֺ�");
		}
	}
}
