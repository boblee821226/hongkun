package nc.bs.pub.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.bs.hkjt.srgk.huiguan.sgshuju.plugin.bpplugin.Hg_sgshujuPluginPoint;
import nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action.AceHg_sgshujuBeforeSaveAction;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.hkjt.IHg_sgshujuMaintain;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuHVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_HK02_SAVEBASE extends AbstractPfAction<SgshujuBillVO> {

	@Override
	protected CompareAroundProcesser<SgshujuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SgshujuBillVO> processor = null;
		SgshujuBillVO[] clientFullVOs = (SgshujuBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<SgshujuBillVO>(
					Hg_sgshujuPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<SgshujuBillVO>(
					Hg_sgshujuPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new AceHg_sgshujuBeforeSaveAction());
		return processor;
	}

	@Override
	protected SgshujuBillVO[] processBP(Object userObj,
			SgshujuBillVO[] clientFullVOs, SgshujuBillVO[] originBills) {

		SgshujuBillVO[] bills = null;
		try {
			
			/**
			 * 2018年8月7日19:22:06
			 * 校验 消费客户往来款， 明细不能为空
			 * pk_jzfs、pk_org
			 */
			HashMap<String,String> JZFS_MAP = new HashMap<String, String>();
			JZFS_MAP.put("1001ZZ1000000000YQS7", "0001N510000000001SY5");
			JZFS_MAP.put("1001A810000000000TCF", "0001N510000000001SY7");
			
			for(int i=0;i<clientFullVOs.length;i++)
			{
				SgshujuBillVO billVO = clientFullVOs[i];
				SgshujuHVO hvo = billVO.getParentVO();
				SgshujuBVO[] bvos = (SgshujuBVO[])billVO.getChildrenVO();
				for( int j=0;j<bvos.length;j++ )
				{
					SgshujuBVO bvo = bvos[j];
					String rowno = bvo.getVrowno();
					String jzfsA = bvo.getTz_km_jzfs_1();
					String jzfsB = bvo.getTz_km_jzfs_2();
					String infoA = PuPubVO.getString_TrimZeroLenAsNull( bvo.getTz_km_info_1() );
					String infoB = PuPubVO.getString_TrimZeroLenAsNull( bvo.getTz_km_info_2() );
					if( 
						( JZFS_MAP.containsKey(jzfsA) && infoA==null ) 
					||  ( JZFS_MAP.containsKey(jzfsB) && infoB==null )
					  )
					{
						throw new BusinessException("【"+rowno+"】行，消费客户往来款，必须要填写明细。");
					}
				}
				
			}
			/***END***/
			
			IHg_sgshujuMaintain operator = NCLocator.getInstance()
					.lookup(IHg_sgshujuMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
