package nc.ui.hkjt.fapiao_fk.bill.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.BusinessExceptionAdapter;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.pub.BusinessException;

public class CommitScriptAction extends
		nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5277502932808318036L;

	@Override
	protected void beforeCheck(Object vo) {
		if(vo != null){
			BillSkFpBillVO billVO=(BillSkFpBillVO)vo;
			// ��������
			Integer count = billVO.getChildrenVO().length;
			// ��鸽������
			if (!checkFujian(billVO.getParentVO().getPk_hk_fapiao_sk_bill(), count)) {
			  MessageDialog.showErrorDlg(
			        this.getModel().getContext().getEntranceUI(),
			        "�������",
			    "���ϴ�������������������С�ڱ�������");
			  throw new BusinessExceptionAdapter(new BusinessException("���ϴ�������������������С�ڱ�������"));
	        }
		}
	}
	
	/**
	   * HK
	   * 2020��2��24��16:27:12
	   * �жϸ����������Ƿ���ڵ��ڱ�������
	 * @throws BusinessException 
	   */
	  private boolean checkFujian(String pk_bill, Integer fileNum) {
		try {
			StringBuilder querySQL = new StringBuilder();
			querySQL.append(" select count(0) ")
					.append(" from sm_pub_filesystem ")
					.append(" where ")
					.append(" filepath like '").append(pk_bill).append("/%' ")
					.append(" and nvl(isfolder,'n') in ('N','n') ")
					.append(" and dr = 0 ")
			;
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null && list.size() > 0)
			{
				int fileCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
				if(fileCount >= fileNum) {
					return true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	  }
}
