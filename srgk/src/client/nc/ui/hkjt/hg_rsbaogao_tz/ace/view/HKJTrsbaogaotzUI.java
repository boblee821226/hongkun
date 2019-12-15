package nc.ui.hkjt.hg_rsbaogao_tz.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.hkjt.srgk.huiguan.rsbaogao.ace.view.ShowUpableBillForm;
import nc.ui.hkjt.srgk.huiguan.rsbaogao.ace.view.ShowUpableBillForm.LinkQueryData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.uif.pub.exception.UifException;
public class HKJTrsbaogaotzUI extends DefaultFuncNodeInitDataListener {
	private NCAction batchEditAction;
	private NCAction batchAddLineAction;
	private NCAction batchDelLineAction;

	 @Override
		public void initData(FuncletInitData data) {
		 super.initData(null);
		 if(data!=null&&data.getInitData()!=null){
		 LinkQueryData linkQueryData=(LinkQueryData)data.getInitData();
		 this.getModel().initModel(linkQueryData.getUserObject());
		 initButtonStatus();
		 }
}
	 
	 public void initButtonStatus(){
			String hid=ShowUpableBillForm.rsbgpks[0];
			try {
				Object obj=HYPubBO_Client.findColValue("hk_srgk_hg_rsbaogao", "to_char(ibillstatus)", "nvl(dr,0)=0 and pk_hk_srgk_hg_rsbaogao='"+hid+"'");
				if(obj!=null&&!obj.toString().equals("-1")){
					batchEditAction.setEnabled(false);
					batchAddLineAction.setEnabled(false);
					batchDelLineAction.setEnabled(false);
				}
			} catch (UifException e) {
				e.printStackTrace();
			}
			
		}
	public NCAction getBatchAddLineAction() {
		return batchAddLineAction;
	}
	public void setBatchAddLineAction(NCAction batchAddLineAction) {
		this.batchAddLineAction = batchAddLineAction;
	}
	public NCAction getBatchEditAction() {
		return batchEditAction;
	}
	public void setBatchEditAction(NCAction batchEditAction) {
		this.batchEditAction = batchEditAction;
	}
	public NCAction getBatchDelLineAction() {
		return batchDelLineAction;
	}
	public void setBatchDelLineAction(NCAction batchDelLineAction) {
		this.batchDelLineAction = batchDelLineAction;
	}
}
