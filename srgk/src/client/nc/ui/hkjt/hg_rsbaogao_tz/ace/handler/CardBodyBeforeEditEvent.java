package nc.ui.hkjt.hg_rsbaogao_tz.ace.handler;

import nc.ui.hkjt.srgk.huiguan.sgshuju.ace.handler.BeforeHandler;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO;
public class CardBodyBeforeEditEvent implements
IAppEventHandler<nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent>{
	

	@Override
	public void handleAppEvent(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		e.setReturnValue(true);
		if("bm_pk".equals(e.getKey())){

			Object pk_org = e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "pk_org");
			Object pk_group = e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "pk_group");
			UIRefPane deptpane = (UIRefPane) e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
//			deptpane.setMultiSelectedEnabled(true);
			deptpane.getRefModel().setAddEnvWherePart(false);
			deptpane.getRefModel().setWherePart(
					" pk_group='" + pk_group + "' and  pk_org = '" + pk_org
							+ "' and nvl(dr,0)=0");
		
		}else if(RsbaogaoCVO.TZ_KM_JZFS_1.equals(e.getKey())){//结账方式1
			Object srxm = e.getBillCardPanel().getBodyValueAt(e.getRow(), RsbaogaoCVO.TZ_KM_SRXM_1);
			if(srxm!=null){
				e.setReturnValue(false);
			}
		}else if(RsbaogaoCVO.TZ_KM_SRXM_1.equals(e.getKey())){//收入项目1
			Object jzfs = e.getBillCardPanel().getBodyValueAt(e.getRow(), RsbaogaoCVO.TZ_KM_JZFS_1);
			if(jzfs!=null){//如果结账方式1不为空，则收入项目1不能编辑
				e.setReturnValue(false);
			}
		}else if(RsbaogaoCVO.TZ_KM_JZFS_2.equals(e.getKey())){//结账方式2
			Object srxm = e.getBillCardPanel().getBodyValueAt(e.getRow(), RsbaogaoCVO.TZ_KM_SRXM_2);
			if(srxm!=null){
				e.setReturnValue(false);
			}
		}else if(RsbaogaoCVO.TZ_KM_SRXM_2.equals(e.getKey())){//收入项目2
			Object jzfs = e.getBillCardPanel().getBodyValueAt(e.getRow(), RsbaogaoCVO.TZ_KM_JZFS_2);
			if(jzfs!=null){//如果结账方式1不为空，则收入项目1不能编辑
				e.setReturnValue(false);
			}
		}
		
		if( RsbaogaoCVO.TZ_KM_JZFS_1.equals(e.getKey())
		|| 	RsbaogaoCVO.TZ_KM_JZFS_2.equals(e.getKey())	
		||  RsbaogaoCVO.TZ_KM_SRXM_1.equals(e.getKey())
		||  RsbaogaoCVO.TZ_KM_SRXM_2.equals(e.getKey())
		){//结账方式 && 收入项目
			UIRefPane zdref = (UIRefPane) e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			Object pk_org = e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "pk_org");
			zdref.getRefModel().setPk_org(pk_org.toString());
		}
		
	}

}
