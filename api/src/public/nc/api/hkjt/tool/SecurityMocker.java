package nc.api.hkjt.tool;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.server.ISecurityTokenCallback;
import nc.uap.lfw.core.servlet.ILfwSecurityMocker;
import nc.uap.lfw.core.servlet.LfwSecurityMocker;
import uap.lfw.core.locator.ServiceLocator;

public class SecurityMocker
implements ILfwSecurityMocker
{
	private static final String ANNOYMOUS = "annoymous";
	private static final String NCPORTAL = "1";
	private static byte[] origin = "1".getBytes();
	private byte[] annonyTokens;

	private byte[] getAnnoymousTokens()
	{
		if (annonyTokens == null) {
			synchronized (LfwSecurityMocker.class) {
				if (annonyTokens == null)
				{
					String userCode = InvocationInfoProxy.getInstance().getUserCode();

					ISecurityTokenCallback sc = (ISecurityTokenCallback)ServiceLocator.getService(ISecurityTokenCallback.class);
					annonyTokens = sc.token(origin, userCode.getBytes());
				}
			}
		}
		return annonyTokens;
	}

	protected byte[] genTokens(ISecurityTokenCallback sc)
	{
		String userCode = InvocationInfoProxy.getInstance().getUserCode();
		if ((userCode == null) || (userCode.equals(""))) {
			userCode = "annoymous";
			InvocationInfoProxy.getInstance().setUserCode(userCode);
		}

		byte[] tokens = sc.token(origin, userCode.getBytes());
		return tokens;
	}

	public void ensureSecurity()
	{
		ISecurityTokenCallback sc = (ISecurityTokenCallback)ServiceLocator.getService(ISecurityTokenCallback.class);
		sc.restore(getAnnoymousTokens());
	}

	@Override
	public void destorySecurityToken() {
		// TODO 自动生成的方法存根
		
	}
}