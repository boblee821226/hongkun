package nc.ui.hkjt.hg_rsbaogao_tz.action;
import nc.ui.hkjt.srgk.huiguan.rsbaogao.ace.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO;
/**
  batch addLine or insLine action autogen
*/
public class Hg_rsbaogao_tzAddLineAction extends BatchAddLineAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected void setDefaultData(Object obj) {
		super.setDefaultData(obj);
		RsbaogaoCVO singleDocVO = (RsbaogaoCVO) obj;
		singleDocVO.setPk_hk_srgk_hg_rsbaogao(ShowUpableBillForm.rsbgpks[0]);
		singleDocVO.setPk_hk_srgk_hg_rsbaogao_b(ShowUpableBillForm.rsbgpks[1]);
		singleDocVO.setPk_group(ShowUpableBillForm.rsbgpks[2]);
		singleDocVO.setPk_org(ShowUpableBillForm.rsbgpks[3]);
	}

}