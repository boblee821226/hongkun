package nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoHVO;

/**
 * ��׼������˵�BP
 */
public class AceHg_rsbaogaoApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public RsbaogaoBillVO[] approve(RsbaogaoBillVO[] clientBills,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		for (RsbaogaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			
			/**
			 * 2016��9��10��19:12:30
			 * ����Ҫ�жϣ� ��Ʊ��û�� ȫ�� ���ͨ��
			 * ��Ʊ ���������֣� 1���˵�    2����Ա
			 */
			RsbaogaoHVO hVO = clientBill.getParentVO();
			String ywDate = hVO.getDbilldate().toString().substring(0, 10);	// ҵ������
			String pk_org = hVO.getPk_org();	// pk_org
			BaseDAO dao = new BaseDAO();
			/**
			 * 1����ѯ  �˵���Ʊ
			 */
			{
				StringBuffer querySQL_1 = 
					new StringBuffer("select ")
							.append(" fp.pk_hk_fapiao_bill ")
							.append(" from hk_fapiao_bill fp ")
							.append(" where fp.dr=0 ")
							.append(" and fp.ibillstatus != 1 ")	// ��Ϊ ���״̬��
							.append(" and substr(fp.dbilldate,1,10) = '"+ywDate+"' ")	// ҵ������
							.append(" and fp.pk_org = '"+pk_org+"' ")	// pk_org
				;
				ArrayList list_1 = (ArrayList)dao.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
				if(list_1!=null && list_1.size()>0)
				{
					throw new BusinessException("�˵���Ʊ����δȫ����ˣ����Բ���������󱨸档");
				}
			}
			/**END*/
			
			/**
			 * 2����ѯ  ��Ա��Ʊ
			 */
			{
				StringBuffer querySQL_2 = 
					new StringBuffer("select ")
							.append(" kp.pk_hk_huiyuan_kaipiaoinfo ")
							.append(" from hk_huiyuan_kaipiaoinfo kp ")
							.append(" where kp.dr=0 ")
							.append(" and kp.ibillstatus != 1 ")	// ��Ϊ ���״̬��
							.append(" and substr(kp.dbilldate,1,10) = '"+ywDate+"' ")	// ҵ������
							.append(" and kp.pk_org = '"+pk_org+"' ")	// pk_org
				;
				ArrayList list_2 = (ArrayList)dao.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				if(list_2!=null && list_2.size()>0)
				{
					throw new BusinessException("��Ա��Ʊ����δȫ����ˣ����Բ���������󱨸档");
				}
			}
			/**END*/
			
		}
		BillUpdate<RsbaogaoBillVO> update = new BillUpdate<RsbaogaoBillVO>();
		RsbaogaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
