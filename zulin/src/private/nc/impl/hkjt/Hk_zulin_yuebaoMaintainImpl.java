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
			
			String qijian = ybhvo.getVdef01();	// 期间
			String pk_org = ybhvo.getPk_org();	// 组织
			String billType = ybhvo.getVbilltypecode();	// 单据类型
			String zhaiyao_tiaozheng = "调整收入";
			/**
			 * 租赁月报HK37  与  成本月报HK43，公用，信息来区分
			 */
			if ("HK43".equals(billType)) {
				zhaiyao_tiaozheng = "调整成本";
			}
			/***END***/
			
			// 组织+期间 要进行加锁处理
			PKLock pklock = PKLock.getInstance();
			if (!pklock.addBatchDynamicLock(new String[] { pk_org
					+ qijian + billType })) {
				throw new BusinessException("该组织+期间已经加锁处理，请稍后再试!");
			}

			// 构造凭证信息
			YuebaoBVO[] ybbvos = (YuebaoBVO[]) ybBillvo.getChildrenVO();
			// 去除 当月收入 为空的数据（当期 确认收入天数为0）。
			Vector<YuebaoBVO> ybbvos_v_final = new Vector<YuebaoBVO>();
			
			// 查询出部门pk
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
			{// 封装凭证所需要的数据 (2029房间25天租金收入)
				
				/**
				 * HK 2019年3月26日14:09:53
				 */
				UFDouble pzje = // 生成凭证的金额 = 当月确认收入金额 + 当月收入调整金额
					PuPubVO.getUFDouble_ZeroAsNull
					(
							 PuPubVO.getUFDouble_NullAsZero( bvo.getDysrqrje() )
						.add(PuPubVO.getUFDouble_NullAsZero( bvo.getVbdef08() ) )
					)
				;
				
				if(
					pzje == null
				)
				{// 如果 当月收入 为 0， 则跳过。
					continue;
				}
				
				String zhaiyao = "";
				if ("HK37".equals(billType)) {
					zhaiyao = "【"+bvo.getVbdef02()+"】"+
						(
							bvo.getDysrqrts()==null
							?
							zhaiyao_tiaozheng
							:
							((int)bvo.getDysrqrts().getDouble())+"天"+bvo.getVbdef03()
						)
					;
				} else if ("HK43".equals(billType)) {
					zhaiyao = "【"+bvo.getVbdef01()+"】"+
						(
							bvo.getDysrqrts()==null
							?
							zhaiyao_tiaozheng
							:
							bvo.getVbdef03() + ((int)bvo.getDysrqrts().getDouble()) + "天"
						)
					;
				}
				
				bvo.setVbdef06(zhaiyao);	// 用自定义项6 存放摘要
				
				/**
				 * HK 2019年3月14日14:44:33
				 * 将 当月调整金额  叠加到  当月收入金额
				 */
				bvo.setDysrqrje( pzje );
				/***END***/
				
				/**
				 * HK 2020年10月13日22:16:52
				 * 成本月报，需要根据 部门分摊
				 */
				if ("HK43".equals(billType)) {
					ybbvos_v_final = this.fentan_bm(ybbvos_v_final, bm_MAP, bvo);
				} else {
					// 否则，直接添加
					ybbvos_v_final.add(bvo);
				}
				
			}
			
			YuebaoBVO[] ybbvos_final = new YuebaoBVO[ybbvos_v_final.size()];
			ybbvos_final = (YuebaoBVO[])ybbvos_v_final.toArray(ybbvos_final);
			
			// 进行凭证传递
			this.sendVoucher( ybhvo , ybbvos_final , flag );
			

		}
		return null;
	}
	
	/**
	 * HK 2020年10月13日23:09:34
	 * 按部门比例分摊
	 */
	private Vector<YuebaoBVO> fentan_bm(Vector<YuebaoBVO> ybbvos_v_final, Map<String,String> bm_MAP, YuebaoBVO yuebaoBVO) throws BusinessException {
		
		String bmft = PuPubVO.getString_TrimZeroLenAsNull(yuebaoBVO.getCsourcebillid());	// 部门分摊 字符串
		if (bmft == null || "~".equals(bmft)) { // 如果部门分摊 为空，直接返回
			ybbvos_v_final.add(yuebaoBVO);
			return ybbvos_v_final;
		}
		/**
		 * 进行分摊处理
		 */
		// 1、将 逗号 转换成 半角，去掉空格
		bmft = bmft.replaceAll("，", ",").replaceAll(" ", "");
		// 2、拆分
		String[] chaifen_1 = bmft.split(",");
		UFDouble fentan = yuebaoBVO.getDysrqrje();	// 待分摊总额
		UFDouble total_fentan = UFDouble.ZERO_DBL;	// 累计分摊金额
		for (int i = 0; i < chaifen_1.length; i++) {
			String itemStr = chaifen_1[i];
			String[] chaifen_2 = itemStr.split("=");
			String bm = chaifen_2[0];	// 部门name
			UFDouble bl = PuPubVO.getUFDouble_NullAsZero(chaifen_2[1]);	// 比例
			
			String bmPk = bm_MAP.get(bm);
			if (bmPk == null) throw new BusinessException(yuebaoBVO.getVbdef10() + "："+bm+" 不存在。");
			UFDouble benci = fentan.multiply(bl).setScale(2, UFDouble.ROUND_HALF_UP);
			if (i == chaifen_1.length - 1) {
				// 若是最后一笔，则反算
				benci = fentan.sub(total_fentan);
			}
			total_fentan = total_fentan.add(benci);	// 叠加  累计分摊金额
			
			// 生成vo
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
			// 判断 有无生成过凭证
			FipExtendAggVO[] pz = NCLocator.getInstance().lookup(IFipBillQueryService.class).queryDesBillBySrc(	// 根据单据信息 查询 所生成的凭证
					 new FipRelationInfoVO[]{ constructFipRalactionInfo(ybBillVO) } 
					,null
			);
			boolean hasPZ = pz!=null && pz.length>0;	// 是否存在凭证
			if( hasPZ )
				throw new BusinessException("已生成过凭证，不能重复生成。");
			
			// 生成凭证
			this.sendMsgFip(
					 new YuebaoBillVO[]{ybBillVO}
					,FipMessageVO.MESSAGETYPE_ADD
			);
		}
		else if( flag==1 )
		{
			// 删除凭证
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
			
			//0表示新增,若不赋值，默认为0
			fipmessagevo.setMessagetype(type);		
	//		 //Dirty默认为false，AuotSum默认为false
	//		fipmessagevo.setDirty(false);
			fipmessagevo.setAuotSum(false);
	//		//PrimaryKey 可以不用赋值
	//		fipmessagevo.setPrimaryKey("");
			
			//填充BillVO
			fipmessagevo.setBillVO(billVos[i]);
			//填充FipRelationInfoVO
			fipmessagevo.setMessageinfo(reVO);
			
			messageVOs[i]=fipmessagevo;			
		}
		
		invokeFipMessage(messageVOs);
		
	}

	public static FipRelationInfoVO constructFipRalactionInfo(YuebaoBillVO billVo) {	
	
		//填充消息VO		
		FipRelationInfoVO relation = new FipRelationInfoVO();
		
		String sbilltype = billVo.getParentVO().getVbilltypecode();
		BilltypeVO billType = PfDataCache.getBillType(sbilltype);
		
		relation.setPk_group( billVo.getParentVO().getPk_group() );	// 集团
		relation.setPk_org( billVo.getParentVO().getPk_org() );		// 组织
		relation.setRelationID( billVo.getPrimaryKey() );			// 单据ID
		relation.setPk_system( billType.getSystemcode() );			// 来源系统
		relation.setBusidate( billVo.getParentVO().getDbilldate() );// 业务日期
		relation.setPk_billtype(sbilltype);							// 单据类型
		relation.setPk_operator(InvocationInfoProxy.getInstance().getUserId());	// 制单人

		relation.setFreedef1(billVo.getParentVO().getVbillcode());	// 单据号 
		relation.setFreedef2(billVo.getParentVO().getVmemo());		// 备注信息
		
		return relation;		
		
	}

	private static FipMsgResultVO[] invokeFipMessage(FipMessageVO[] messageVOs) throws BusinessException {
		if (Logger.isDebugEnabled()) {
			Logger.info("sendMessage is over!");
		}
		return NCLocator.getInstance().lookup(IFipMessageService.class).sendMessages(messageVOs);
	}

}
