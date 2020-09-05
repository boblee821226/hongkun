package nc.ui.erm.link;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.itf.ApiPubTool;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.ep.bx.JKBXVO;
import nc.vo.pub.BusinessException;

/**
 * 月报 取数
 *
 */
public class OaLinkAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public OaLinkAction() {
		setBtnName("OA");
		setCode("oaLinkAction");
	}

	private AbstractUIAppModel model;
	private Long lastTime = null;	// 最后一次的点击时间
	private IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
	private HashMap<String,String> MAP_creator = new HashMap<>();
	
	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		JKBXVO selectedData = (JKBXVO) getModel().getSelectedData();
		if (selectedData == null) {
			return false;
		}
		// url为空，不进行联查
		String url = PuPubVO.getString_TrimZeroLenAsNull(selectedData.getParentVO().getZyx26());
		if (url == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public void doAction(ActionEvent e) throws BusinessException {
		
		JKBXVO selectedData = (JKBXVO) getModel().getSelectedData();
		String url = PuPubVO.getString_TrimZeroLenAsNull(selectedData.getParentVO().getZyx26());
		if (url == null) return;
		String creator = selectedData.getParentVO().getCreator();
		if (creator == null) return;
		String phone = null;
		{// 根据制单人，去找 手机号
			// 先从缓存里查找
			if (MAP_creator.containsKey(creator)) {
				phone = MAP_creator.get(creator);
			} else { // 查找数据库
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
					MAP_creator.put(creator, phone);	// 放入缓存
				}
			}
		}
		if (phone == null) {
			throw new BusinessException("制单人没有手机号");
		}
		/**
		 * HK 2020年9月4日18:41:18
		 * 需要加时间锁，防止多次点击。10s
		 */
		Long minTime = (long)(10 * 1000);	// 最小间隔时间 10s 
		Long currTime = System.currentTimeMillis();
		if (lastTime != null && currTime - lastTime < minTime) {
			return;
		} else {
			lastTime = currTime;
		}
		/***END***/
		try {
			if (url != null) {
				// 先申请token 18610372523、18701546487
				String token = PuPubVO.getString_TrimZeroLenAsNull(OaHttpUtil.getToken(phone));	// 手机号
				if (token == null) {
					throw new BusinessException("token为空");
				}
				if (token.length() != 128) {
					throw new BusinessException(token);
				}
				// 调用url
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
