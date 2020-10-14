package nc.impl.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pf.pub.PfDataCache;
import nc.bs.uap.lock.PKLock;
import nc.impl.pub.ace.AceHk_zulin_yuebaoPubServiceImpl;
import nc.itf.hkjt.IHk_zulin_yuebaoMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pubitf.fip.service.IFipBillQueryService;
import nc.pubitf.fip.service.IFipMessageService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.fip.external.FipExtendAggVO;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipMsgResultVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoBVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFDouble;

public class Hk_zulin_yuebaoMaintainImpl extends AceHk_zulin_yuebaoPubServiceImpl
		implements IHk_zulin_yuebaoMaintain {

	@Override
	public void delete(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public YuebaoBillVO[] insert(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public YuebaoBillVO[] update(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public YuebaoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public YuebaoBillVO[] save(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public YuebaoBillVO[] unsave(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public YuebaoBillVO[] approve(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public YuebaoBillVO[] unapprove(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public String genNCVoucherInfo(YuebaoBillVO ybBillvo, int flag)
			throws Exception {
		
		if (ybBillvo != null) {
			YuebaoHVO ybhvo = ybBillvo.getParentVO();
			
			String qijian = ybhvo.getVdef01();	// �ڼ�
			String pk_org = ybhvo.getPk_org();	// ��֯
			String billType = ybhvo.getVbilltypecode();	// ��������
			String zhaiyao_tiaozheng = "��������";
			/**
			 * �����±�HK37  ��  �ɱ��±�HK43�����ã���Ϣ������
			 */
			if ("HK43".equals(billType)) {
				zhaiyao_tiaozheng = "�����ɱ�";
			}
			/***END***/
			
			// ��֯+�ڼ� Ҫ���м�������
			PKLock pklock = PKLock.getInstance();
			if (!pklock.addBatchDynamicLock(new String[] { pk_org
					+ qijian + billType })) {
				throw new BusinessException("����֯+�ڼ��Ѿ������������Ժ�����!");
			}

			// ����ƾ֤��Ϣ
			YuebaoBVO[] ybbvos = (YuebaoBVO[]) ybBillvo.getChildrenVO();
			// ȥ�� �������� Ϊ�յ����ݣ����� ȷ����������Ϊ0����
			Vector<YuebaoBVO> ybbvos_v_final = new Vector<YuebaoBVO>();
			
			// ��ѯ������pk
			Map<String,String> bm_MAP = new HashMap<>();
			if ("HK43".equals(billType)) {
				StringBuffer bmQuerySQL = 
					new StringBuffer("select name, pk_dept from org_dept ")
							.append(" where enablestate = 2 ")
							.append(" and dr = 0 ")
							.append(" and pk_org = '").append(pk_org).append("' ");
				BaseDAO dao = new BaseDAO();
				ArrayList list = (ArrayList)dao.executeQuery(bmQuerySQL.toString(), new ArrayListProcessor());
				if (list != null && !list.isEmpty()) {
					for (Object item : list) {
						Object[] obj = (Object[])item;
						String deptName = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
						String deptPk = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
						bm_MAP.put(deptName, deptPk);
					}
				}
			}
			
			for(YuebaoBVO bvo : ybbvos)
			{// ��װƾ֤����Ҫ������ (2029����25���������)
				
				/**
				 * HK 2019��3��26��14:09:53
				 */
				UFDouble pzje = // ����ƾ֤�Ľ�� = ����ȷ�������� + ��������������
					PuPubVO.getUFDouble_ZeroAsNull
					(
							 PuPubVO.getUFDouble_NullAsZero( bvo.getDysrqrje() )
						.add(PuPubVO.getUFDouble_NullAsZero( bvo.getVbdef08() ) )
					)
				;
				
				if(
					pzje == null
				)
				{// ��� �������� Ϊ 0�� ��������
					continue;
				}
				
				String zhaiyao = "";
				if ("HK37".equals(billType)) {
					zhaiyao = "��"+bvo.getVbdef02()+"��"+
						(
							bvo.getDysrqrts()==null
							?
							zhaiyao_tiaozheng
							:
							((int)bvo.getDysrqrts().getDouble())+"��"+bvo.getVbdef03()
						)
					;
				} else if ("HK43".equals(billType)) {
					zhaiyao = "��"+bvo.getVbdef01()+"��"+
						(
							bvo.getDysrqrts()==null
							?
							zhaiyao_tiaozheng
							:
							bvo.getVbdef03() + ((int)bvo.getDysrqrts().getDouble()) + "��"
						)
					;
				}
				
				bvo.setVbdef06(zhaiyao);	// ���Զ�����6 ���ժҪ
				
				/**
				 * HK 2019��3��14��14:44:33
				 * �� ���µ������  ���ӵ�  ����������
				 */
				bvo.setDysrqrje( pzje );
				/***END***/
				
				/**
				 * HK 2020��10��13��22:16:52
				 * �ɱ��±�����Ҫ���� ���ŷ�̯
				 */
				if ("HK43".equals(billType)) {
					ybbvos_v_final = this.fentan_bm(ybbvos_v_final, bm_MAP, bvo);
				} else {
					// ����ֱ�����
					ybbvos_v_final.add(bvo);
				}
				
			}
			
			YuebaoBVO[] ybbvos_final = new YuebaoBVO[ybbvos_v_final.size()];
			ybbvos_final = (YuebaoBVO[])ybbvos_v_final.toArray(ybbvos_final);
			
			// ����ƾ֤����
			this.sendVoucher( ybhvo , ybbvos_final , flag );
			

		}
		return null;
	}
	
	/**
	 * HK 2020��10��13��23:09:34
	 * �����ű�����̯
	 */
	private Vector<YuebaoBVO> fentan_bm(Vector<YuebaoBVO> ybbvos_v_final, Map<String,String> bm_MAP, YuebaoBVO yuebaoBVO) throws BusinessException {
		
		String bmft = PuPubVO.getString_TrimZeroLenAsNull(yuebaoBVO.getCsourcebillid());	// ���ŷ�̯ �ַ���
		if (bmft == null || "~".equals(bmft)) { // ������ŷ�̯ Ϊ�գ�ֱ�ӷ���
			ybbvos_v_final.add(yuebaoBVO);
			return ybbvos_v_final;
		}
		/**
		 * ���з�̯����
		 */
		// 1���� ���� ת���� ��ǣ�ȥ���ո�
		bmft = bmft.replaceAll("��", ",").replaceAll(" ", "");
		// 2�����
		String[] chaifen_1 = bmft.split(",");
		UFDouble fentan = yuebaoBVO.getDysrqrje();	// ����̯�ܶ�
		UFDouble total_fentan = UFDouble.ZERO_DBL;	// �ۼƷ�̯���
		for (int i = 0; i < chaifen_1.length; i++) {
			String itemStr = chaifen_1[i];
			String[] chaifen_2 = itemStr.split("=");
			String bm = chaifen_2[0];	// ����name
			UFDouble bl = PuPubVO.getUFDouble_NullAsZero(chaifen_2[1]);	// ����
			
			String bmPk = bm_MAP.get(bm);
			if (bmPk == null) throw new BusinessException(yuebaoBVO.getVbdef10() + "��"+bm+" �����ڡ�");
			UFDouble benci = fentan.multiply(bl).setScale(2, UFDouble.ROUND_HALF_UP);
			if (i == chaifen_1.length - 1) {
				// �������һ�ʣ�����
				benci = fentan.sub(total_fentan);
			}
			total_fentan = total_fentan.add(benci);	// ����  �ۼƷ�̯���
			
			// ����vo
			YuebaoBVO bvo = (YuebaoBVO)yuebaoBVO.clone();
			bvo.setDysrqrje(benci);
			bvo.setVbdef05(bmPk);
			ybbvos_v_final.add(bvo);
		}

		return ybbvos_v_final;
	}
	
	private void sendVoucher(YuebaoHVO ybHVO,YuebaoBVO[] ybBVOs,int flag) throws BusinessException{
		
		YuebaoBillVO ybBillVO = new YuebaoBillVO();
		ybBillVO.setParentVO(ybHVO);
		ybBillVO.setChildrenVO(ybBVOs);
		
		if( flag==0 )
		{
			// �ж� �������ɹ�ƾ֤
			FipExtendAggVO[] pz = NCLocator.getInstance().lookup(IFipBillQueryService.class).queryDesBillBySrc(	// ���ݵ�����Ϣ ��ѯ �����ɵ�ƾ֤
					 new FipRelationInfoVO[]{ constructFipRalactionInfo(ybBillVO) } 
					,null
			);
			boolean hasPZ = pz!=null && pz.length>0;	// �Ƿ����ƾ֤
			if( hasPZ )
				throw new BusinessException("�����ɹ�ƾ֤�������ظ����ɡ�");
			
			// ����ƾ֤
			this.sendMsgFip(
					 new YuebaoBillVO[]{ybBillVO}
					,FipMessageVO.MESSAGETYPE_ADD
			);
		}
		else if( flag==1 )
		{
			// ɾ��ƾ֤
			this.sendMsgFip(
					 new YuebaoBillVO[]{ybBillVO}
					,FipMessageVO.MESSAGETYPE_DEL
			);
		}
		
	}
	
	public static void sendMsgFip(YuebaoBillVO[] billVos,int type) throws BusinessException{
			
		FipMessageVO[] messageVOs=new FipMessageVO[billVos.length];
		for (int i=0;i<billVos.length;i++ ){
			FipMessageVO fipmessagevo = new FipMessageVO();	
			FipRelationInfoVO reVO =constructFipRalactionInfo(billVos[i]);
			
			//0��ʾ����,������ֵ��Ĭ��Ϊ0
			fipmessagevo.setMessagetype(type);		
	//		 //DirtyĬ��Ϊfalse��AuotSumĬ��Ϊfalse
	//		fipmessagevo.setDirty(false);
			fipmessagevo.setAuotSum(false);
	//		//PrimaryKey ���Բ��ø�ֵ
	//		fipmessagevo.setPrimaryKey("");
			
			//���BillVO
			fipmessagevo.setBillVO(billVos[i]);
			//���FipRelationInfoVO
			fipmessagevo.setMessageinfo(reVO);
			
			messageVOs[i]=fipmessagevo;			
		}
		
		invokeFipMessage(messageVOs);
		
	}

	public static FipRelationInfoVO constructFipRalactionInfo(YuebaoBillVO billVo) {	
	
		//�����ϢVO		
		FipRelationInfoVO relation = new FipRelationInfoVO();
		
		String sbilltype = billVo.getParentVO().getVbilltypecode();
		BilltypeVO billType = PfDataCache.getBillType(sbilltype);
		
		relation.setPk_group( billVo.getParentVO().getPk_group() );	// ����
		relation.setPk_org( billVo.getParentVO().getPk_org() );		// ��֯
		relation.setRelationID( billVo.getPrimaryKey() );			// ����ID
		relation.setPk_system( billType.getSystemcode() );			// ��Դϵͳ
		relation.setBusidate( billVo.getParentVO().getDbilldate() );// ҵ������
		relation.setPk_billtype(sbilltype);							// ��������
		relation.setPk_operator(InvocationInfoProxy.getInstance().getUserId());	// �Ƶ���

		relation.setFreedef1(billVo.getParentVO().getVbillcode());	// ���ݺ� 
		relation.setFreedef2(billVo.getParentVO().getVmemo());		// ��ע��Ϣ
		
		return relation;		
		
	}

	private static FipMsgResultVO[] invokeFipMessage(FipMessageVO[] messageVOs) throws BusinessException {
		if (Logger.isDebugEnabled()) {
			Logger.info("sendMessage is over!");
		}
		return NCLocator.getInstance().lookup(IFipMessageService.class).sendMessages(messageVOs);
	}

}
