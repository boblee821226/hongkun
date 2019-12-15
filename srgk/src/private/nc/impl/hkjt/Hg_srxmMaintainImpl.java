package nc.impl.hkjt;

import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.uap.lock.PKLock;
import nc.impl.pub.ace.AceHg_srxmPubServiceImpl;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;

public class Hg_srxmMaintainImpl extends AceHg_srxmPubServiceImpl implements
		nc.itf.hkjt.IHg_srxmMaintain {

	@Override
	public void delete(SrxmHVO vos) throws BusinessException {
		super.deletetreeinfo(vos);
	}

	@Override
	public SrxmHVO insert(SrxmHVO vos) throws BusinessException {
		return super.inserttreeinfo(vos);
	}

	@Override
	public SrxmHVO update(SrxmHVO vos) throws BusinessException {
		vos.setModifiedtime(new UFDateTime());
		return super.updatetreeinfo(vos);
	}

	@Override
	public SrxmHVO[] query(String whereSql) throws BusinessException {
		return super.querytreeinfo(whereSql);
	}

	@Override
	public void fenpeiSrxmKjkmInfo(SrxmHVO vo) throws Exception {
		PKLock pklock = PKLock.getInstance();
		// �����ȼ�������
		String pk_srxm = vo.getPk_hk_srgk_hg_srxm();
		boolean islock = pklock.addBatchDynamicLock(new String[] { pk_srxm });
		if (!islock) {
			throw new BusinessException("�Ѿ������������Ժ�����!");
		}
		BaseDAO bd = new BaseDAO();
		// ��ȡ��Ӧ��������TS��Ϣ����ֹ�����ٴ��޸�
		StringBuffer sb_ts = new StringBuffer();
		sb_ts.append("select ts from hk_srgk_hg_srxm where nvl(dr,0)=0  and Pk_hk_srgk_hg_srxm='"
				+ pk_srxm + "'");
		String tscolumn = (String) bd.executeQuery(sb_ts.toString(),
				new ColumnProcessor());
		UFDateTime ts = vo.getTs();
		UFDateTime ts_new = new UFDateTime(tscolumn);
		if (ts.compareTo(ts_new) != 0) {
			throw new BusinessException("��Ϣ�ѱ��޸�,��ˢ�º�����!");
		}
		// ����������ѯ�¼���Ϣ
		StringBuffer sb = new StringBuffer();
		sb.append("select * from hk_srgk_hg_srxm where nvl(dr,0)=0 and pk_parent='"
				+ pk_srxm + "'");
		@SuppressWarnings("unchecked")
		List<SrxmHVO> list = (List<SrxmHVO>) bd.executeQuery(sb.toString(),
				new BeanListProcessor(SrxmHVO.class));
		String kjkm = vo.getPk_kjkm();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setPk_kjkm(kjkm);
			}
			// �������Ķ�Ӧ��ƿ�Ŀ��Ϣ����Ϊ��
			vo.setPk_kjkm(null);
			list.add(vo);
			bd.updateVOArray(list.toArray(new SrxmHVO[] {}),
					new String[] { SrxmHVO.PK_KJKM });
		}
	}

}
