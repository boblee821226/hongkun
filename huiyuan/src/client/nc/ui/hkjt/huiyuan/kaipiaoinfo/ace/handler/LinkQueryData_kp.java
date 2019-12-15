package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.handler;

import nc.ui.pub.linkoperate.ILinkQueryData;

public class LinkQueryData_kp implements ILinkQueryData
{
	public String billid;
	public Object obj;

	@Override
	public String getBillID() {
		return billid;
	}

	@Override
	public String getBillType() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getPkOrg() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object getUserObject() {
		// TODO 自动生成的方法存根
		return obj;
	}
	
}