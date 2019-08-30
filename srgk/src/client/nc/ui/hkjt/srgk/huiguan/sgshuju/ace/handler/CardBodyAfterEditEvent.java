package nc.ui.hkjt.srgk.huiguan.sgshuju.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.vo.hkjt.srgk.huiguan.hzshuju.SgsjInfoVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;

public class CardBodyAfterEditEvent
		implements
		IAppEventHandler<nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(
			nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent e) {
		if("zd_pk".equals(e.getKey())){//账单-主表
//			UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
//					.getBodyItem(e.getKey()).getComponent();
//			String []pks=zdref.getRefPKs();
//			for (int i=1;i<pks.length;i++) {
//				e.getBillCardPanel().addLine();
//				e.getBillCardPanel().getBillModel().setValueAt(aValue, row, column)
//			}
			
		}else if("zd_item_pk".equals(e.getKey())){//账单-子表{
			UIRefPane zdbref = (UIRefPane) e.getBillCardPanel()
					.getBodyItem(e.getKey()).getComponent();
			String[] pks = zdbref.getRefModel().getPkValues();
			int row  = e.getRow();
			if(pks!=null&&pks.length>0){
				for (int i = 0; i < pks.length; i++) {
					String string = pks[i];
					if(i==0){
						e.getBillCardPanel().setBodyValueAt(string, row, "zd_item_pk");
					}else{
						e.getBillCardPanel().addLine();
						int row_max = e.getBillCardPanel().getRowCount();
						//获取新增行的行号
						SgshujuBVO bvo = (SgshujuBVO) e.getBillCardPanel().getBillModel().getBodyValueRowVO(e.getRow(), SgshujuBVO.class.getName());
						bvo.setZd_item_pk(string);
						bvo.setVrowno(String.valueOf((row_max)*10));
						e.getBillCardPanel().getBillModel().setBodyRowVO(bvo, row_max-1);
					}
				}
			}
		}
	}

}
