package nc.impl.hkjt.report;

import java.util.Map;
import nc.bs.framework.common.NCLocator;
import nc.itf.fa.prv.ITransasset;
import nc.itf.uap.pf.IPfExchangeService;
import nc.vo.am.common.TransportBillVO;
import nc.vo.fa.transasset.TransassetVO;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.m4d.entity.MaterialOutVO;
import nc.vo.ic.m4i.entity.GeneralOutVO;
import nc.vo.pub.BusinessException;

public class ZhuanGuDAO {

	public Object doZhuanGu(MaterialOutVO billVO, Map<String, Object> param) 
			throws BusinessException {
		// VOת��������
		IPfExchangeService iPfExchangeService = (IPfExchangeService) NCLocator
				.getInstance().lookup(IPfExchangeService.class);
		// ת�̵�������
		ITransasset iTransasset = (ITransasset) NCLocator.getInstance()
				.lookup(ITransasset.class.getName());

		ICBillBodyVO[] bVOs = billVO.getChildrenVO();
		// �����к� ��������
		int row = 10;
		for (ICBillBodyVO bvo : bVOs) {
			bvo.setCrowno(String.valueOf(row));
			row += 10;
		}
		// ִ��ת������
		TransassetVO retobj = (TransassetVO) iPfExchangeService
				.runChangeData("4D", "HJ-02", billVO, null);
		// ����ת�̵�
		TransportBillVO transportbillvo = iTransasset.insert(null, retobj);

		return transportbillvo;
	}

}
