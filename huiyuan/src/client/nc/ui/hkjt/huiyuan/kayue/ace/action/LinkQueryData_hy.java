package nc.ui.hkjt.huiyuan.kayue.ace.action;

import nc.ui.pub.linkoperate.ILinkQueryData;

public class LinkQueryData_hy implements ILinkQueryData {
	
	String billid;
	Object obj;

	@Override
	public String getBillID() {
		return billid;
	}

	@Override
	public String getBillType() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public String getPkOrg() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public Object getUserObject() {
		// TODO �Զ����ɵķ������
		return obj;
	}
		

}
