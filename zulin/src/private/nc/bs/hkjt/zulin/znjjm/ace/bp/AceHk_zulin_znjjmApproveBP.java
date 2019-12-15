package nc.bs.hkjt.zulin.znjjm.ace.bp;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_zulin_znjjmApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public ZnjjmBillVO[] approve(ZnjjmBillVO[] clientBills,
			ZnjjmBillVO[] originBills) throws BusinessException {
		for (ZnjjmBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ZnjjmBillVO> update = new BillUpdate<ZnjjmBillVO>();
		ZnjjmBillVO[] returnVos = update.update(clientBills, originBills);
		
		for(int i=0;i<returnVos.length;i++)
		{// ����������������ֻ�е� ���״̬Ϊ1  �Ž����Ƶ� 
			ZnjjmBillVO billVO = returnVos[i];
			Integer ibillstatus = billVO.getParentVO().getIbillstatus();
			if(ibillstatus==1)
			{
				this.updateZnjjmdJmje(billVO,ibillstatus,null);	// ���� ���ɽ���㵥 �� ������
			}
		}
		
		return returnVos;
	}
	
	/**
	 * ��д ���ɽ���㵥 �� ������
	 */
	public void updateZnjjmdJmje(ZnjjmBillVO billVO,Integer ibillstatus,Object other) 
			throws BusinessException{
		
		BaseDAO dao = new BaseDAO();
		
		ZnjjmBVO[] jmBVOs = (ZnjjmBVO[])billVO.getChildrenVO();
		
		HashMap<String,ZnjjmBVO> jmBVO_MAP = new HashMap<String,ZnjjmBVO>();
		
		String where_bid = " ('null'";
		for(int i=0;i<jmBVOs.length;i++){
			ZnjjmBVO jmBVO = jmBVOs[i];
			String bid = jmBVO.getCsourcebillbid();
			where_bid += ",'"+bid+"'";
			jmBVO_MAP.put(bid, jmBVO);
		}
		where_bid += ") ";
		
		String querySQL = 
				" pk_hk_zulin_znjjs_b in " + where_bid
			;
		// ȡ�� ���� ���㵥
		ArrayList<ZnjjsBVO> jsBVO_list = (ArrayList<ZnjjsBVO>)dao.retrieveByClause( ZnjjsBVO.class,querySQL );
		
		if(jsBVO_list==null || jsBVO_list.size()<=0){
			if(ibillstatus==1)
				throw new BusinessException("û�ҵ���Ӧ�ļ��㵥���������");
			else
				throw new BusinessException("û�ҵ���Ӧ�ļ��㵥����������");
		}
		
		String pk_znjjs = jsBVO_list.get(0).getPk_hk_zulin_znjjs();
		
		// �޸� ���� ���㵥 �� ������
		for(int i=0;i<jsBVO_list.size();i++)
		{
			ZnjjsBVO jsBVO = jsBVO_list.get(i);
			ZnjjmBVO jmBVO = jmBVO_MAP.get(jsBVO.getPk_hk_zulin_znjjs_b());
			
			if(ibillstatus==1){	// ��������������д���
				jsBVO.setVbdef01( ""+jmBVO.getJm_mny() );
			}else{	// ��������������
				jsBVO.setVbdef01( null );
			}
		}
		
		// ���� ���� ���㵥����
		dao.updateVOList(jsBVO_list);
		// ���� ���� ���㵥��ͷ-ʱ���(������һ���ֶΣ�ϵͳ���Զ����ʱ����ĸ���)
		String updateSQL = 
			" update hk_zulin_znjjs js " +
			" set js.vdef20 = js.vdef20 " +
			" where js.pk_hk_zulin_znjjs = '"+pk_znjjs+"' "
		;
		dao.executeUpdate(updateSQL);
	}

}
