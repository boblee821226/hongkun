package nc.bs.hkjt.listener;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.dao.BaseDAO;
import nc.bs.ic.general.util.ICEventType;
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

import org.codehaus.jackson.map.ObjectMapper;

public class SendLvyunDataListener implements IBusinessListener {

	private static ObjectMapper JSON = new ObjectMapper();
	private static String URL = "http://183.129.215.114:41104/pos/router";
	private static BaseDAO DAO = new BaseDAO();
	
	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		nc.bs.ic.general.businessevent.ICGeneralCommonEvent e = (nc.bs.ic.general.businessevent.ICGeneralCommonEvent)event;
		String eventType = e.getEventType();
		
		if (ICEventType.SignAfter.getCode().equals(eventType)) {
			// ǩ�ֺ�
			for (Object billVoItem : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)billVoItem;
				GeneralInHeadVO hVO = (GeneralInHeadVO)billVO.getParentVO();	//  ��ͷ
				String billTypeCode = hVO.getVtrantypecode();	// �������
				String cwarehouseid = hVO.getCwarehouseid();	// �ֿ�
				String pk_org = hVO.getPk_org();				// ��֯
				String billcode = hVO.getVbillcode();			// ����
				String dbilldateStr = PuPubVO.getString_TrimZeroLenAsNull(hVO.getDbilldate());			// ��������
				if (!"4A-Cxx-01".equals(billTypeCode)) continue;	// ֻ�� 4A-Cxx-01 ����Ҫ���͸�����
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
							.append(" m.code ")		// ����
							.append(",m.name ")		// ����
							.append(",m.materialspec || m.materialtype as standent ")	// ���
							.append(",dw.name unit")	// ��λ
							.append(",m.def6 price ")	// ����
//							.append(",m.code helpCode ")	// ������
//							.append(",'6002' sseg ")	// С��code
//							.append(",'6' cseg ")		// ����code
//							.append(",'ref' ref ")		// ��ע
//							.append(",'storage' storage ")	// ��Ŀ����
							.append(",m.pk_material pk ")	// ����pk
							.append(",m.def8 lyCode ")		// ����code
							.append(" from bd_material m ")
							.append(" left join bd_measdoc dw on m.pk_measdoc = dw.pk_measdoc ")
							.append(" where (1=1) ")
//							.append(" and nvl(m.def8,'~') = '~' ")	// ֻ��ѯ ���Ʊ���Ϊ�յ�
							.append(" and m.pk_material in ").append(PuPubVO.getSqlInByList(pkInvList)).append(" ")
					;
					ArrayList<PosStoreArticle> list = (ArrayList<PosStoreArticle>) DAO.executeQuery(querySQL.toString(), new BeanListProcessor(PosStoreArticle.class));
					if (list != null && !list.isEmpty()) {
						ArrayList<PosStoreArticle> saveList = new ArrayList<>();	// ��Ҫ���͵�list
						for (PosStoreArticle item : list) {
							// �������codeΪ�գ��Ž��з���
							String lyCode = PuPubVO.getString_TrimZeroLenAsNull(item.getLyCode());	// ����code
							String MAP_value = lyCode;
							if (lyCode == null || "~".equals(lyCode)) {
								// ��ΪҪ�����ܴ������������ֶβ���Ϊnull����ֵΪ""
								item.setHelpCode("");	// ������
								item.setRef("");		// ��ע
								item.setStorage("");	// ��Ŀ����
								item.setCseg("6");		// ����
								item.setSseg("6002");	// С��
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
									// 0 Ϊִ�гɹ�
									String resultMsg = res.getResultMsg();
									if (resultMsg != null && resultMsg.indexOf("��Ӧ�������Ѿ�����") >= 0) {
										// TODO ��Ӧ�������Ѿ����ڣ��൱�����ϴ��ڣ���������ϵ���������code
										DAO.executeUpdate(updateSQL_1.toString());
										DAO.executeUpdate(updateSQL_2.toString());
									} else {
										throw new BusinessException("���ƣ�" + res.getResultMsg());
									}
								} else {
									// TODO ���ִ�гɹ�����������ϵ���������code
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
				// ��ⵥ�ķ���
				if (true) {
					try {
						PosStoreDetail[] details = new PosStoreDetail[bVOs.length];
						for (int i = 0; i < bVOs.length; i++) {
							details[i] = new PosStoreDetail();
							details[i].setArticleCode(MAP_inv.get(bVOs[i].getCmaterialvid()));	// ���ϱ���
							details[i].setNumber(bVOs[i].getNnum().toString());					// ����
						}
						SavePosStoreMasterWithDetail sendData = 
							new SavePosStoreMasterWithDetail(
								 "GCBZ"		// �Ƶ����
								,billcode		// ����
								,dbilldateStr	// ʱ��
								,"003"	// ��̨�����ֿ�ƥ���ϣ���
								,"zwf"	// ����Ա������ң���
								,""		// ��Ʊ��
								,""		// ��ע
								,details	// ������ϸ
							);
						String resStr = HttpUtil.doPost(URL, JSON.writeValueAsString(sendData));
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
		} else if (ICEventType.CancelsignAfter.getCode().equals(eventType)) {
			// ȡ��ǩ�ֺ�
			for (Object item : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)item;
				GeneralInHeadVO hVO = (GeneralInHeadVO)billVO.getParentVO();	//  ��ͷ
				String billTypeCode = hVO.getVtrantypecode();	// �������
				String billcode = hVO.getVbillcode();			// ����
				if (!"4A-Cxx-01".equals(billTypeCode)) continue;	// ֻ�� 4A-Cxx-01 ����Ҫ���͸�����
				// ɾ�� ��ⵥ����
				if (true) {
					try {
						DeletePosStoreMasterWithDetail sendData = 
							new DeletePosStoreMasterWithDetail(
								"GCBZ"		// �Ƶ����
								,billcode		// ����
						);
						String resStr = HttpUtil.doPost(URL, JSON.writeValueAsString(sendData));
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
