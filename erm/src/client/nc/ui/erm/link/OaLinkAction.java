package nc.ui.erm.link;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.itf.ApiPubTool;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.ep.bx.JKBXVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

/**
 * ����oa
 *
 */
public class OaLinkAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public OaLinkAction() {
		setBtnName("����OA");
		setCode("oaLinkAction");
	}

	private BillListView listView = null;
	private BillForm editor = null;
	private AbstractUIAppModel model = null;
	private String billType = null;
	private Long lastTime = null;	// ���һ�εĵ��ʱ��
	private IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
	private HashMap<String,String> MAP_creator = new HashMap<>();
	
	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public BillListView getListView() {
		return listView;
	}

	public void setListView(BillListView listView) {
		this.listView = listView;
	}

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	@Override
	protected boolean isActionEnable() {
		if (this.getBillType() != null && this.getBillType().startsWith("OA")) {
			if (getEditor() == null || getEditor().getBillCardPanel() == null) return false;
			BillItem item = getEditor().getBillCardPanel().getHeadItem("oa_status");
			if (item != null) {
				String itemValue = PuPubVO.getString_TrimZeroLenAsNull(item.getValueObject());
				if ("�ѹ鵵".equals(itemValue)) return true;
			}
		} else {
			JKBXVO selectedData = (JKBXVO) getModel().getSelectedData();
			if (selectedData == null) {
				return false;
			}
			// urlΪ�գ�����������
			String url = PuPubVO.getString_TrimZeroLenAsNull(selectedData.getParentVO().getZyx26());
			if (url == null) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void doAction(ActionEvent e) throws BusinessException {
		String url = null;
		if (this.getBillType() != null && this.getBillType().startsWith("OA")) {
			url = PuPubVO.getString_TrimZeroLenAsNull(
					this.getEditor().getBillCardPanel().getHeadItem("oa_url"));
		} else {
			JKBXVO selectedData = (JKBXVO) getModel().getSelectedData();
			url = PuPubVO.getString_TrimZeroLenAsNull(selectedData.getParentVO().getZyx26());
		}
		if (url == null) return;
//		String creator = selectedData.getParentVO().getCreator();
		String creator = AppContext.getInstance().getPkUser();	// HK 2020��10��30��14:44:51 ��Ϊ��ǰ��½�ˡ�
		if (creator == null) return;
		String phone = null;
		{// �����Ƶ��ˣ�ȥ�� �ֻ���
			// �ȴӻ��������
			if (MAP_creator.containsKey(creator)) {
				phone = MAP_creator.get(creator);
			} else { // �������ݿ�
				StringBuffer queryPhone = 
				new StringBuffer("select bd_psndoc.mobile ")
						.append(" from sm_user ")
						.append(" left join bd_psndoc on bd_psndoc.pk_psndoc = sm_user.pk_psndoc ")
						.append(" where ")
						.append(" sm_user.cuserid = '").append(creator).append("' ")
				;
				ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(queryPhone.toString(), new ArrayListProcessor());
				if (list != null && list.size() > 0) {
					phone = PuPubVO.getString_TrimZeroLenAsNull(((Object[])list.get(0))[0]);
					MAP_creator.put(creator, phone);	// ���뻺��
				}
			}
		}
		if (phone == null) {
			throw new BusinessException("�Ƶ���û���ֻ���");
		}
		/**
		 * HK 2020��9��4��18:41:18
		 * ��Ҫ��ʱ��������ֹ��ε����10s
		 */
		Long minTime = (long)(10 * 1000);	// ��С���ʱ�� 10s 
		Long currTime = System.currentTimeMillis();
		if (lastTime != null && currTime - lastTime < minTime) {
			return;
		} else {
			lastTime = currTime;
		}
		/***END***/
		try {
			if (url != null) {
				// ������token 18610372523��18701546487
				String token = PuPubVO.getString_TrimZeroLenAsNull(OaHttpUtil.getToken(phone));	// �ֻ���
				if (token == null) {
					throw new BusinessException("tokenΪ��");
				}
				if (token.length() != 128) {
					throw new BusinessException(token);
				}
				// ����url
//				url = "http://111.204.181.93:93/workflow/request/ViewRequest.jsp?requestid=34034" +
				url = url +
					"&ssoToken=" + token;
				ApiPubTool.openURL(url);
			}
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage());
		} finally {
			// TODO
		}
	}
}
