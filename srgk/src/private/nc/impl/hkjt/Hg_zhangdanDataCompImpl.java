package nc.impl.hkjt;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import nc.bs.hkjt.srgk.huiguan.zhangdan.workplugin.ImpZhangDanBill;
import nc.itf.hkjt.IHg_zhangdanDataCompute;
import nc.jdbc.framework.JdbcSession;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO;
import nc.vo.pub.BusinessException;

public class Hg_zhangdanDataCompImpl extends ImpZhangDanBill implements IHg_zhangdanDataCompute{
	@Override
	public ZhangdanBillVO[] computeBodyDate(ZhangdanBillVO[] billvos,String pk_org) throws BusinessException {
		if(billvos==null||billvos.length==0)return null;
		ArrayList<ZhangdanHVO> hvos=new ArrayList<ZhangdanHVO>();
		for (ZhangdanHVO zhangdanHVO : hvos) {
			hvos.add(zhangdanHVO);
		}
		
		HashMap<String,String> infoMap=getDefaultInfo(pk_org);//得到配置表信息
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		try{
	hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
	hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
	HashMap<String,HashSet<String>> fenqumap=null;
//	fenqumap=getFenQuNameByBillIDs(hkjt_hg_pub_session, hvos, infoMap.get("vpnname"));
		for (ZhangdanBillVO zhangdanBillVO : billvos) {
			execBodyValues(zhangdanBillVO,hkjt_hg_pub_session,infoMap.get("vpnname"),fenqumap);
		}
		return billvos;
	}catch(Exception e){
		throw new BusinessException(e.getMessage());
	}finally{
		if(hkjt_hg_pub_session!=null)
		hkjt_hg_pub_session.closeAll();
		JDBCUtils.closeConn(hkjt_hg_pub_conn);
	}
}}
