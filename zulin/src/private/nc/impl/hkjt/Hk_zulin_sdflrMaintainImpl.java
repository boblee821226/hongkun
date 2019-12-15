package nc.impl.hkjt;

import java.util.ArrayList;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceHk_zulin_sdflrPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.sdflr.SdflrBVO;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.vo.hkjt.zulin.sdflr.SdflrHVO;
import nc.itf.hkjt.IHk_zulin_sdflrMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class Hk_zulin_sdflrMaintainImpl extends AceHk_zulin_sdflrPubServiceImpl
		implements IHk_zulin_sdflrMaintain {

	@Override
	public void delete(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public SdflrBillVO[] insert(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		this.checkBeforeSave(clientFullVOs, originBills);	// 保存前校验
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public SdflrBillVO[] update(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		this.checkBeforeSave(clientFullVOs, originBills);	// 保存前校验
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public SdflrBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public SdflrBillVO[] save(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SdflrBillVO[] unsave(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SdflrBillVO[] approve(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SdflrBillVO[] unapprove(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		this.checkBeforeUnapprove(clientFullVOs, originBills);	// 弃审前的校验
		return super.pubunapprovebills(clientFullVOs, originBills);
	}
	
	/**
	 * 保存前的校验
	 */
	private boolean checkBeforeSave(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException{
		
		for(int i=0;i<clientFullVOs.length;i++){
			SdflrBillVO billVO = clientFullVOs[i];
			SdflrHVO    hVO = billVO.getParentVO();
			SdflrBVO[] bVOs = (SdflrBVO[])billVO.getChildrenVO();
			
			UFBoolean isInit = hVO.getIs_init();	// 是否期初
			UFDate ksrq = PuPubVO.getUFDate(hVO.getVdef01());	// 开始日期
			UFDate jzrq = PuPubVO.getUFDate(hVO.getVdef02());	// 截止日期
			
			for(int row=0;row<bVOs.length;row++){
				
				SdflrBVO bVO = bVOs[row];
				
				UFDouble sccb_num = bVO.getSccb_num();	// 上次抄表数
				UFDate  sccb_date = bVO.getSccb_date();	// 上次抄表日期
				UFDouble use_num = bVO.getUse_num();	// 用量
				UFDouble use_mny = bVO.getUse_mny();	// 应缴金额
				
				String rowNo = bVO.getVrowno();		// 行号
				
				if(isInit.booleanValue())
				{// 期初
					if(sccb_num!=null || sccb_date!=null || use_num!=null || use_mny!=null){
						throw new BusinessException("【"+rowNo+"】已经存在期初数据，不能再次录入期初。");
					}
				}
				else{// 非期初
					if( sccb_num==null || sccb_date==null ){
						throw new BusinessException("【"+rowNo+"】请先录入该设备的期初数据。");
					}
					if( use_num==null || use_mny==null ){
						throw new BusinessException("【"+rowNo+"】请先录入用量。");
					}
				}
			}
			
			if(isInit.booleanValue()){
				// 期初
				if(ksrq!=null || jzrq!=null){
					throw new BusinessException("期初数据，不需要录入开始日期、截止日期。");
				}
			}else{
				// 非期初
				if(ksrq==null || jzrq==null){
					throw new BusinessException("非期初数据，必须录入开始日期、截止日期。");
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 弃审前的校验
	 */
	private boolean checkBeforeUnapprove(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException{
		
		for(int i=0;i<clientFullVOs.length;i++){
			SdflrBillVO billVO = clientFullVOs[i];
			SdflrHVO    hVO = billVO.getParentVO();
			
			String pk_sdflr = hVO.getPk_hk_zulin_sdflr();
			
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" distinct ar.billno ")
						.append(" from ar_recitem ari ")
						.append(" inner join ar_recbill ar ")
						.append(" on (ari.pk_recbill = ar.pk_recbill) ")
						.append(" where ari.dr=0 and ar.dr=0 ")
						.append(" and ari.def30 ='"+pk_sdflr+"' ")
			;
			ArrayList list = (ArrayList)new BaseDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null && list.size()>0)
			{
				String billno = "";
				for(int j=0;j<list.size();j++)
				{
					billno += ((Object[])list.get(j))[0]+",";
				}
				throw new BusinessException("存在下游的水电费应收单，请先删除 才能弃审。【"+billno+"】");
			}
		}
		return true;
	}
}
