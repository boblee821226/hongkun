package nc.ui.hkjt.srgk.huiguan.rsbaogao.ace.handler;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		// 设置主组织默认值
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		// 设置单据状态、单据业务日期默认值
		panel.setHeadItem("ibillstatus", BillStatusEnum.FREE.value());
		panel.setHeadItem("dbilldate", AppContext.getInstance().getBusiDate());
		
		HashMap<String,String>  hgmap=HKJT_PUB.PK_ORG_HUIGUAN_MAP;
		HashMap<String,String>  jdmap=HKJT_PUB.PK_ORG_JIUDIAN_MAP;
			if(hgmap.values().contains(pk_org)){
			setBodyDate(HKJT_PUB.PK_ORG_HUIGUAN,panel);
			}else if(jdmap.values().contains(pk_org)){
			setBodyDate(HKJT_PUB.PK_ORG_JIUDIAN,panel);
			}
	}
	
	/**
	 * 设置表体数据
	 * @param pk_org
	 */
	public void setBodyDate(String pk_org,BillCardPanel panel){
		try{
			ArrayList<RsbaogaoBVO> bodys=getTempletOfrsbaogaoByPk_org(pk_org);
			panel.getBillModel().setBodyDataVO(bodys.toArray(new RsbaogaoBVO[]{}));
		}catch(Exception e){
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		
	}
	/**
	 * 根据组织得到日期最大的日审报告模板
	 * @param pk_org
	 * @return
	 * @throws BusinessException
	 */
	public ArrayList<RsbaogaoBVO> getTempletOfrsbaogaoByPk_org(String pk_org) throws BusinessException{
		IUAPQueryBS ibs=NCLocator.getInstance().lookup(IUAPQueryBS.class);
String sql="select b.vrowno,b.dalei,b.jihedian,b.jhyq,b.jhfw,b.zhangtai,b.ycyy,b.fjsm from hk_srgk_hg_rsbaogao h  left join hk_srgk_hg_rsbaogao_b b on h.pk_hk_srgk_hg_rsbaogao=b.pk_hk_srgk_hg_rsbaogao"+
				" where nvl(h.dr,0)=0 and nvl(b.dr,0)=0 and h.ismoban='Y' and h.pk_org='"+pk_org+"' and h.dbilldate in("+
				" select max(h.dbilldate) from hk_srgk_hg_rsbaogao h where nvl(h.dr,0)=0 and h.ismoban='Y' and h.pk_org='"+pk_org+"')" +
				" order by to_number(b.vrowno)";
ArrayList<RsbaogaoBVO> bodyList=(ArrayList<RsbaogaoBVO>)ibs.executeQuery(sql, new BeanListProcessor(RsbaogaoBVO.class));
		return bodyList;
	}
}
