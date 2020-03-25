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
	setBtnName("����");
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
		MAP_HG_JD.put("0001N510000000001SY7", "0001N510000000001SY7");	// ��ɽ��Ȫ
		
		Object[] allData = (Object[])getModel().getAllDatas();
		if (allData == null || allData.length <= 0) {
			MessageDialog.showErrorDlg(this.getEditor(), "", "������ĿΪ�գ�������Ϊ����Դ��");
			return;
		}
		
		// ����ѡ�� Ŀ����֯
		// ˵��
		BillItem corpItem = new BillItem();
		corpItem.setName("Ŀ����֯��");
		corpItem.setKey("corp");
		corpItem.setDataType(IBillItem.UFREF);
		corpItem.setRefType("������֯");
		corpItem.setEdit(true);	// ���ܱ༭
		corpItem.setNull(true);	// �Ƿ�ǿ�  false ���Ƿǿ�
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				this.getEditor()
				,new BillItem[]{
					corpItem,
				});
		dlg.setTitle("����");
		
		if(UIDialog.ID_OK != dlg.showModal()) return;
		
		String des_org = PuPubVO.getString_TrimZeroLenAsNull(corpItem.getValueObject());
		
		if (des_org == null) return;
		
		/**
		 * 1��Ŀ�� �� Դ ����֯ ������ͬ
		 */
		String source_org = ((SrxmHVO)allData[0]).getPk_org();
		if (des_org.equals(source_org)) {
			MessageDialog.showErrorDlg(this.getEditor(), "", "Ŀ�� �� Դ ����֯ ������ͬ��");
			return;
		}
		/**
		 * 2������ Ŀ�� ��֯��ȥ���ң���û�д��ڵ� ������Ŀ��
		 * ����У��� ���ܸ��ơ�
		 * Ŀ����֯ ���� ��ݺ;Ƶ� ����ҵ��
		 */
		String root_pk = null; // ֻ�� ��� �� �Ƶ깲��� ������Ŀ�� ����Ҫ����
		if (!MAP_HG_JD.containsKey(des_org)) {
			SrxmHVO[] existVOs = (SrxmHVO[])HYPubBO_Client.queryByCondition(
				SrxmHVO.class, 
				" dr=0 and pk_org = '" + des_org + "' "
			);
			if (existVOs != null && existVOs.length > 0) {
				MessageDialog.showErrorDlg(this.getEditor(), "", "Ŀ����֯ �Ѿ�����������Ŀ�����ܸ��ơ�");
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
				MessageDialog.showErrorDlg(this.getEditor(), "", "Ŀ����֯ Ϊ �Ƶ��ݹ���ҵ����Ҫ���ֹ�����һ������LY�����þƵ��������Ŀ��");
				return;
			} else if (existVOs.length != 1) {
				MessageDialog.showErrorDlg(this.getEditor(), "", "Ŀ����֯ Ϊ �Ƶ��ݹ���ҵ��LY�µ�������Ŀ��Ϊ�գ����ܸ��ơ�");
				return;
			}
		}
		/**
		 * �� Դ��VO��ѭ������
		 * 1���� pk ��  �ϼ�pk ǰ4λ �滻�ɣ�Ŀ�ĵ���֯���롣
		 * 2����� root_pk ��Ϊ�գ����� pk_parent Ϊ�գ� �� pk_parent = root_pk
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
				MessageDialog.showWarningDlg(this.getEditor(), "", "������ɡ�");
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
