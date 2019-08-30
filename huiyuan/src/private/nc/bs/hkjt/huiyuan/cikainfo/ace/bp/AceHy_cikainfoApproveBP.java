package nc.bs.hkjt.huiyuan.cikainfo.ace.bp;

import java.util.ArrayList;
import java.util.Collection;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKCZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKXFVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;

/**
 * 标准单据审核的BP
 */
public class AceHy_cikainfoApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public CikainfoBillVO[] approve(CikainfoBillVO[] clientBills,
			CikainfoBillVO[] originBills)throws BusinessException {
		for (CikainfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikainfoBillVO> update = new BillUpdate<CikainfoBillVO>();
		CikainfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// 将信息同步到 会员卡档案，逐行处理
		BaseDAO baseDAO = new BaseDAO();
		SequenceGenerator pkGenerator = new SequenceGenerator();	// pk 生成器
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			ArrayList<KadanganCKCZVO> insertVOs_ckcz = new ArrayList<KadanganCKCZVO>();		// 充值
			ArrayList<KadanganCKXFVO> insertVOs_ckxf = new ArrayList<KadanganCKXFVO>();		// 消费
			CikainfoBVO[] cikainfoBVOs = (CikainfoBVO[])returnVos[vos_i].getChildrenVO();	// 子表VO
			CikainfoHVO cikainfoHVO = returnVos[vos_i].getParentVO();			// 主表VO
			
			// 1、校验，是否数据完整（提交时 判断）
			
			// 2、进行处理
			for( int i=0;i<cikainfoBVOs.length;i++ )
			{
				String xmdl = cikainfoBVOs[i].getXmdl();	// 项目大类
				
				if( cikainfoBVOs[i].getKa_pk()!=null )
				{// 卡号 不等于空的  才进行处理
				
					if("充值".equals(xmdl))
					{
						KadanganCKCZVO ckczvo = new KadanganCKCZVO();
						ckczvo.setPk_hk_huiyuan_kadangan( cikainfoBVOs[i].getKa_pk() );	// 卡pk
						ckczvo.setCz_time( cikainfoBVOs[i].getYwsj() );		// 充值时间
						ckczvo.setZdh( cikainfoBVOs[i].getZdh() );			// 账单号
						ckczvo.setKabili( cikainfoBVOs[i].getKabili() );	// 卡比例
						ckczvo.setTotalnum( cikainfoBVOs[i].getShuliang());	// 总数量
						ckczvo.setItemid( cikainfoBVOs[i].getItemid() );
						ckczvo.setItemname( cikainfoBVOs[i].getItemname() );
						ckczvo.setUsednum( UFDouble.ZERO_DBL );
						ckczvo.setStartdata( cikainfoBVOs[i].getStartdata() );
						ckczvo.setExpdata( cikainfoBVOs[i].getExpdata() );
						ckczvo.setPrice( cikainfoBVOs[i].getDanjia() );		// 单价
						ckczvo.setTimescardwaternum( cikainfoBVOs[i].getTimescardwaternum() );		// 次卡水号
						
						ckczvo.setCsourcetypecode("HK29");			// 上游单据类型
						ckczvo.setCsourcebillid(cikainfoBVOs[i].getPk_hk_huiyuan_cikainfo());		// 上游单据id
						ckczvo.setCsourcebillbid(cikainfoBVOs[i].getPk_hk_huiyuan_cikainfo_b());	// 上游单据行id
						ckczvo.setPk_hk_huiyuan_kadangan_ckcz(pkGenerator.generate());			// 赋值pk
						ckczvo.setDr(0);
						ckczvo.setVbdef01( cikainfoHVO.getVdef01() );	// 门店
						ckczvo.setVbdef02( cikainfoBVOs[i].getXmlx() );	// 项目类型
						ckczvo.setVbdef03( cikainfoBVOs[i].getVbdef03() );	// 不减次
						
						/**
						 * HK 2019年1月16日17:04:41
						 * 新次卡 次卡水号 有相同的
						 */
						// 判断  如果 存在着次卡水号， 就不再插入
//						Collection query_result = baseDAO.retrieveByClause(KadanganCKCZVO.class, " nvl(dr,0)=0 and timescardwaternum ='"+ckczvo.getTimescardwaternum()+"' ");
//						if( query_result.size()==0)
//						{
//							insertVOs_ckcz.add(ckczvo);
//						}
						
						insertVOs_ckcz.add(ckczvo);
						
					}
					
					else if("消费".equals(xmdl)
						|| "充负数".equals(xmdl)
						)
					{
						KadanganCKXFVO ckxfvo = new KadanganCKXFVO();
						ckxfvo.setPk_hk_huiyuan_kadangan( cikainfoBVOs[i].getKa_pk() );	// 卡pk
						ckxfvo.setXf_time( cikainfoBVOs[i].getYwsj() );		// 消费时间
						ckxfvo.setZdh( cikainfoBVOs[i].getZdh() );			// 账单号
						ckxfvo.setKabili( cikainfoBVOs[i].getKabili() );	// 卡比例
						ckxfvo.setUsednum( cikainfoBVOs[i].getShuliang());	// 数量
						ckxfvo.setPrice( cikainfoBVOs[i].getDanjia() );		// 单价
						ckxfvo.setItemid( cikainfoBVOs[i].getItemid() );
						ckxfvo.setItemname( cikainfoBVOs[i].getItemname() );
						
						ckxfvo.setCsourcetypecode("HK29");			// 上游单据类型
						ckxfvo.setCsourcebillid(cikainfoBVOs[i].getPk_hk_huiyuan_cikainfo());		// 上游单据id
						ckxfvo.setCsourcebillbid(cikainfoBVOs[i].getPk_hk_huiyuan_cikainfo_b());	// 上游单据行id
						ckxfvo.setPk_hk_huiyuan_kadangan_ckxf(pkGenerator.generate());			// 赋值pk
						ckxfvo.setDr(0);
						ckxfvo.setVbdef01( cikainfoHVO.getVdef01() );	// 门店
						ckxfvo.setVbdef02( cikainfoBVOs[i].getXmlx() );	// 项目类型
						ckxfvo.setVbdef03( cikainfoBVOs[i].getVbdef03() );	// 不减次
						
						insertVOs_ckxf.add(ckxfvo);
						
					}
				}
			}
			baseDAO.insertVOList(insertVOs_ckcz);	// 充值
			baseDAO.insertVOList(insertVOs_ckxf);	// 消费
		}
		
		
		return returnVos;
	}

}
