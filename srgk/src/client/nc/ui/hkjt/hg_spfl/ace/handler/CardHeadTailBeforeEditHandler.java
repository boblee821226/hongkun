package nc.ui.hkjt.hg_spfl.ace.handler;

import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.hkjt.pub.ace.handler.DeptFilterUtils;
import nc.ui.hkjt.srgk.huiguan.sgshuju.ace.handler.BeforeHandler;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.pub.BusinessException;

public class CardHeadTailBeforeEditHandler implements
IAppEventHandler<CardHeadTailBeforeEditEvent>{

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		e.setReturnValue(true);
		if(SpflHVO.PK_DEPT.equals(e.getKey())){//部门
			new DeptFilterUtils().resetDeptRefSql(e);
		}
		else if( SpflHVO.PK_HK_SRGK_HG_SRXM.equals(e.getKey()) ){//收入项目 
			
			try {
			
				UIRefPane zdref = (UIRefPane) e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
				String pk_org = e.getBillCardPanel().getHeadItem("pk_org").getValue();
				
				IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
				String pk_parent = e.getBillCardPanel().getHeadItem("pk_parent").getValue();	// 父pk
				
				/**
				 * 如果 组织为 国际会馆 并且 一级的商品分类 是 0-消费类  9-房间类型，则用酒店的收入项目
				 */
				if( pk_org.equals(HKJT_PUB.PK_ORG_HUIGUAN_gjhg) ){
					// 如果 当前组织 为 国际店
					if( pk_parent!=null && !"".equals(pk_parent) && !"~".equals(pk_parent) )
					{
						StringBuffer querySQL = 
									// 二级菜单
							new StringBuffer("select sp1.code ")
									.append(" from hk_srgk_hg_spfl sp1 ")
									.append(" where (1=1) ")
									.append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"' ")
									.append(" and sp1.code in ('0','9') ")
								.append(" union all ")
									// 三级菜单
									.append(" select sp2.code ")
									.append(" from hk_srgk_hg_spfl sp1 ")
									.append(" left join hk_srgk_hg_spfl sp2 on sp1.pk_parent = sp2.pk_hk_srgk_hg_spfl ")
									.append(" where (1=1) ")
									.append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"' ")
									.append(" and sp2.code in ('0','9') ")
								.append(" union all ")
									// 四级菜单
									.append(" select sp3.code  ")
									.append(" from hk_srgk_hg_spfl sp1  ")
									.append(" left join hk_srgk_hg_spfl sp2 on sp1.pk_parent = sp2.pk_hk_srgk_hg_spfl  ")
								    .append(" left join hk_srgk_hg_spfl sp3 on sp2.pk_parent = sp3.pk_hk_srgk_hg_spfl  ")
								    .append(" where (1=1)  ")
								    .append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"'  ")
								    .append(" and sp3.code in ('0','9')  ")
							;
						
						ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
						
						if(list!=null && list.size()>0)
						{// 说明 顶级分类是   0-消费类  9-房间类型
							zdref.getRefModel().setPk_org(HKJT_PUB.PK_ORG_JIUDIAN);
							return;
						}
					}
				}
				/***END***/
				/**
				 * 如果 组织为 朗丽兹酒店 并且 一级的商品分类 是 01-消费类  09-房间类型，则用酒店的收入项目
				 */
				else if( pk_org.equals(HKJT_PUB.PK_ORG_JIUDIAN_llzjd) ){
					// 如果 当前组织 为 朗丽兹酒店
					if( pk_parent!=null && !"".equals(pk_parent) && !"~".equals(pk_parent) )
					{
						StringBuffer querySQL = 
									// 二级菜单
							new StringBuffer("select sp1.code ")
									.append(" from hk_srgk_hg_spfl sp1 ")
									.append(" where (1=1) ")
									.append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"' ")
									.append(" and sp1.code in ('01','09') ")
								.append(" union all ")
									// 三级菜单
									.append(" select sp2.code ")
									.append(" from hk_srgk_hg_spfl sp1 ")
									.append(" left join hk_srgk_hg_spfl sp2 on sp1.pk_parent = sp2.pk_hk_srgk_hg_spfl ")
									.append(" where (1=1) ")
									.append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"' ")
									.append(" and sp2.code in ('01','09') ")
								.append(" union all ")
									// 四级菜单
									.append(" select sp3.code  ")
									.append(" from hk_srgk_hg_spfl sp1  ")
									.append(" left join hk_srgk_hg_spfl sp2 on sp1.pk_parent = sp2.pk_hk_srgk_hg_spfl  ")
								    .append(" left join hk_srgk_hg_spfl sp3 on sp2.pk_parent = sp3.pk_hk_srgk_hg_spfl  ")
								    .append(" where (1=1)  ")
								    .append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"'  ")
								    .append(" and sp3.code in ('01','09')  ")
							;
						
						ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
						
						if(list!=null && list.size()>0)
						{// 说明 顶级分类是   0-消费类  09-房间类型  属于酒店
							zdref.getRefModel().setPk_org(HKJT_PUB.PK_ORG_JIUDIAN);
							return;
						}
						else{// 否则  属于会馆
							zdref.getRefModel().setPk_org(HKJT_PUB.PK_ORG_HUIGUAN);
							return;
						}
					}
				}
				/***END***/
				
				zdref.getRefModel().setPk_org(pk_org);
				
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}

}
