package nc.ui.hkjt.hg_rsbaogao_tz.action;

import nc.bs.uif2.validation.ValidationException;
import nc.ui.pub.bill.BillData;
import nc.ui.uif2.model.DefaultBatchValidationService;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class whenSaveCheckAction extends DefaultBatchValidationService {

	@Override
	public void validate(Object obj) throws ValidationException {
		super.validate(obj);
		BillData data = getEditor().getBillCardPanel().getBillData();
			if(data != null){
				RsbaogaoCVO [] rsbgvos=(RsbaogaoCVO[])data.getBodyValueVOs("hg_RsbaogaoCVO", RsbaogaoCVO.class.getName());
				String err="";
				int i=1;
				for (RsbaogaoCVO rsbgtzVO : rsbgvos) {
					boolean group1=(rsbgtzVO.getTz_km_jzfs_1()!=null||rsbgtzVO.getTz_km_srxm_1()!=null)&&rsbgtzVO.getTz_km_data_1()==null;
					boolean group2=(rsbgtzVO.getTz_km_jzfs_2()!=null||rsbgtzVO.getTz_km_srxm_2()!=null)&&rsbgtzVO.getTz_km_data_2()==null;
					if(group1||group2){
						err+="第["+i+"]行数据 调整-"+(group1?" A ":"")+(group2?" B  ":"")+"组中【数据】字段不能为空！\r\n";
					}
					i++;
				}
				if(err.length()>0){
					ExceptionUtils.wrappBusinessException(err);
				}
				
			}
	}
}
