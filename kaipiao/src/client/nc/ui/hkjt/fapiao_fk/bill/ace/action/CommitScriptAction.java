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
			// 表体行数
//			Integer count = billVO.getChildrenVO().length;
			Integer count = 1;
			// 检查附件数量
			if (!checkFujian(billVO.getParentVO().getPk_hk_fapiao_sk_bill(), count)) {
			  MessageDialog.showErrorDlg(
			        this.getModel().getContext().getEntranceUI(),
			        "附件检查",
			    "请上传附件，附件数量不能小于1");
			  throw new BusinessExceptionAdapter(new BusinessException("请上传附件，附件数量不能小于1"));
	        }
		}
	}
	
	/**
	   * HK
	   * 2020年2月24日16:27:12
	   * 判断附件个数，是否大于等于表体行数
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
