package nc.ui.hkjt.hg_srxm.ace.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHg_srxmMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.IQueryAndRefreshManager;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;

public class CopyAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1598197819707945750L;
	public CopyAction() {
	setBtnName("复制");
	setCode("copyAction");
	}
	private nc.ui.pubapp.uif2app.model.HierachicalDataAppModel model;
	private IQueryAndRefreshManager dataManager;
	private nc.ui.pubapp.uif2app.view.BillForm editor;
	
	public IQueryAndRefreshManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IQueryAndRefreshManager dataManager) {
		this.dataManager = dataManager;
	}

	public nc.ui.pubapp.uif2app.model.HierachicalDataAppModel getModel() {
		return model;
	}

	public void setModel(
			nc.ui.pubapp.uif2app.model.HierachicalDataAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	public nc.ui.pubapp.uif2app.view.BillForm getEditor() {
		return editor;
	}

	public void setEditor(nc.ui.pubapp.uif2app.view.BillForm editor) {
		this.editor = editor;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		HashMap<String, String> MAP_HG_JD = new HashMap<String, String>();
		MAP_HG_JD.put("0001N510000000001SY7", "0001N510000000001SY7");	// 西山温泉
		
		Object[] allData = (Object[])getModel().getAllDatas();
		if (allData == null || allData.length <= 0) {
			MessageDialog.showErrorDlg(this.getEditor(), "", "收入项目为空，不能作为复制源。");
			return;
		}
		
		// 弹框，选择 目的组织
		// 说明
		BillItem corpItem = new BillItem();
		corpItem.setName("目的组织：");
		corpItem.setKey("corp");
		corpItem.setDataType(IBillItem.UFREF);
		corpItem.setRefType("财务组织");
		corpItem.setEdit(true);	// 不能编辑
		corpItem.setNull(true);	// 是否非空  false 不是非空
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				this.getEditor()
				,new BillItem[]{
					corpItem,
				});
		dlg.setTitle("复制");
		
		if(UIDialog.ID_OK != dlg.showModal()) return;
		
		String des_org = PuPubVO.getString_TrimZeroLenAsNull(corpItem.getValueObject());
		
		if (des_org == null) return;
		
		/**
		 * 1、目的 与 源 的组织 不能相同
		 */
		String source_org = ((SrxmHVO)allData[0]).getPk_org();
		if (des_org.equals(source_org)) {
			MessageDialog.showErrorDlg(this.getEditor(), "", "目的 与 源 的组织 不能相同。");
			return;
		}
		/**
		 * 2、根据 目的 组织，去查找，有没有存在的 收入项目。
		 * 如果有，则 不能复制。
		 * 目的组织 不是 会馆和酒店 共有业务
		 */
		String root_pk = null; // 只有 会馆 和 酒店共享的 收入项目， 才需要区分
		if (!MAP_HG_JD.containsKey(des_org)) {
			SrxmHVO[] existVOs = (SrxmHVO[])HYPubBO_Client.queryByCondition(
				SrxmHVO.class, 
				" dr=0 and pk_org = '" + des_org + "' "
			);
			if (existVOs != null && existVOs.length > 0) {
				MessageDialog.showErrorDlg(this.getEditor(), "", "目的组织 已经存在收入项目，不能复制。");
				return;
			}
		} else {
			SrxmHVO[] existVOs = (SrxmHVO[])HYPubBO_Client.queryByCondition(
				SrxmHVO.class, 
				" dr=0 and pk_org = '" + des_org + "' " +
				" and code like 'LY%' "
			);
			if (existVOs != null) {
				for (SrxmHVO item : existVOs) {
					if ("LY".equals(item.getCode())) {
						root_pk = item.getPk_hk_srgk_hg_srxm();
						break;
					}
				}
			}
			if (root_pk == null) {
				MessageDialog.showErrorDlg(this.getEditor(), "", "目的组织 为 酒店会馆共有业务，需要先手工创建一级分类LY来放置酒店的收入项目。");
				return;
			} else if (existVOs.length != 1) {
				MessageDialog.showErrorDlg(this.getEditor(), "", "目的组织 为 酒店会馆共有业务，LY下的收入项目不为空，不能复制。");
				return;
			}
		}
		/**
		 * 将 源的VO，循环处理。
		 * 1、将 pk 和  上级pk 前4位 替换成，目的的组织编码。
		 * 2、如果 root_pk 不为空，并且 pk_parent 为空， 则 pk_parent = root_pk
		 */
		OrgVO des_orgVO = (OrgVO)HYPubBO_Client.queryByPrimaryKey(OrgVO.class,des_org);
		String des_org_code = des_orgVO.getCode();
		String des_org_v = des_orgVO.getPk_vid();
		ArrayList<SrxmHVO> copy_list = new ArrayList<SrxmHVO>();
		for (Object obj : allData) {
			SrxmHVO vo = (SrxmHVO)((SrxmHVO)obj).clone();
			vo.setPk_org(des_org);
			vo.setPk_org_v(des_org_v);
			vo.setPk_dept(null);
			vo.setPk_dept_v(null);
			vo.setAttributeValue("dr", 0);
			
			vo.setPk_hk_srgk_hg_srxm(
				des_org_code + vo.getPk_hk_srgk_hg_srxm().substring(des_org_code.length())
			);
			
			if (vo.getPk_parent() != null) {
				vo.setPk_parent(
					des_org_code + vo.getPk_parent().substring(des_org_code.length())
				);
			}
			
			if (root_pk != null 
			 && vo.getPk_parent() == null
			) {
				vo.setPk_parent(root_pk);
			}
			
			copy_list.add(vo);
		}
		if (copy_list.size() > 0) {
			String[] res = HYPubBO_Client.insertAry(copy_list.toArray(new SrxmHVO[0]));
			if (res!=null && res.length > 0) {
				MessageDialog.showWarningDlg(this.getEditor(), "", "复制完成。");
			}
		}
	}

	@Override
	protected boolean isActionEnable() {
		return super.isActionEnable();
	}

	IHg_srxmMaintain servece = null;

	private IHg_srxmMaintain getService() {
		if (servece == null) {
			servece = NCLocator.getInstance().lookup(IHg_srxmMaintain.class);
		}
		return servece;
	}
}
