package nc.ui.hkjt.srgk.huiguan.sgshuju.ace.handler;

import hd.vo.pub.tools.PuPubVO;
import nc.itf.hkjt.HKJT_PUB;
import nc.ui.pub.beans.UIRefPane;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuHVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBVO;
import nc.vo.pub.lang.UFBoolean;

public class BeforeHandler {
	/**
	 * 表体参照账单主表信息过滤
	 * */
	public void resetZDHRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		Object dbilldate = e.getBillCardPanel()
				.getHeadItem(SgshujuHVO.DBILLDATE).getValueObject();
		if ( dbilldate == null || dbilldate.toString().trim().length()<1 ) {
			return;
		} else {
			UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
					.getBodyItem(e.getKey()).getComponent();
			Object pk_org = e.getBillCardPanel().getHeadItem("pk_org")
					.getValueObject();
			zdref.getRefModel().addWherePart(
					" and pk_org = '" + pk_org
							+ "' and substr(dbilldate,0,10)='"
							+ dbilldate.toString().substring(0, 10) + "' and nvl(dr,0)=0");
		}
	}

	/**
	 * 表体参照账单子表信息过滤
	 * */
	public void resetZDBRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		Object pk_zd = e.getBillCardPanel().getBodyValueAt(e.getRow(),
				SgshujuBVO.ZD_PK);
		if ( pk_zd == null || pk_zd.toString().trim().length()<1 ) {
			return;
		} else {
			UIRefPane zdbref = (UIRefPane) e.getBillCardPanel()
					.getBodyItem(e.getKey()).getComponent();
			//设置多选
			zdbref.setMultiSelectedEnabled(true);
			zdbref.getRefModel().addWherePart(
					" and " + ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN + "='"
							+ pk_zd.toString() + "'");
		}
	}

	/**
	 * 表体参照 结账方式 过滤
	 */
	public void resetJZFSRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
				.getBodyItem(e.getKey()).getComponent();
		String pk_org = e.getBillCardPanel().getHeadItem("pk_org").getValue();
		
		// HK 2018年11月6日14:11:27  是否酒店
		UFBoolean isjd = PuPubVO.getUFBoolean_NullAs(
			 e.getBillCardPanel().getHeadItem("vdef10").getValueObject()
			,UFBoolean.FALSE);
		
		/**
		 * HK 2019年5月22日15:30:13
		 * 因为增加了  0310转0308的情况，所以要统一考虑逻辑。
		 * 如果 表头 是否酒店 打勾了，那就肯定是酒店。
		 * 如果 表头 是否酒店 没打勾，就判断 是否为，康福瑞酒店（目前只有康福瑞酒店 是单酒店的情况）
		 */
		if(isjd.booleanValue()){	// 酒店打勾 就一定是酒店
			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// 酒店基础
		}else{
			if(pk_org.equals(HKJT_PUB.PK_ORG_JIUDIAN_kfrxyld)){// 酒店没打勾，只有 康福瑞酒店 为酒店
				pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// 酒店基础;
			}else{	// 其它 均为 会馆
				pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// 会馆基础;
			}
		}
		
//		if(HKJT_PUB.PK_ORG_HUIGUAN_gjhg.equals(pk_org)){// 国际+康西
//			if(isjd.booleanValue())
//			{// 如果是 酒店 则 返回 0309的pk
//				pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// 康西
//			}
//		}
//		else if(HKJT_PUB.PK_ORG_JIUDIAN_llzjd.equals(pk_org)){// 朗丽兹+西山
//			if(!isjd.booleanValue())
//			{// 如果是 不是酒店 则 返回 0310的pk
//				pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// 西山
//			}
//		}
		
		zdref.getRefModel().setPk_org(pk_org);
	}
	
	/**
	 * 表体参照 收入项目 过滤
	 */
	public void resetSRXMRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
				.getBodyItem(e.getKey()).getComponent();
		String pk_org = e.getBillCardPanel().getHeadItem("pk_org").getValue();
		
		// HK 2018年11月6日14:11:27  是否酒店
		UFBoolean isjd = PuPubVO.getUFBoolean_NullAs(
			 e.getBillCardPanel().getHeadItem("vdef10").getValueObject()
			,UFBoolean.FALSE);
		
		/**
		 * HK 2019年5月22日15:30:13
		 * 因为增加了  0310转0308的情况，所以要统一考虑逻辑。
		 * 如果 表头 是否酒店 打勾了，那就肯定是酒店。
		 * 如果 表头 是否酒店 没打勾，就判断 是否为，康福瑞酒店（目前只有康福瑞酒店 是单酒店的情况）
		 */
		if(isjd.booleanValue()){	// 酒店打勾 就一定是酒店
			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// 酒店基础
		}else{
			if(pk_org.equals(HKJT_PUB.PK_ORG_JIUDIAN_kfrxyld)){// 酒店没打勾，只有 康福瑞酒店 为酒店
				pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// 酒店基础;
			}else{	// 其它 均为 会馆
				pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// 会馆基础;
			}
		}
		
//		if(HKJT_PUB.PK_ORG_HUIGUAN_gjhg.equals(pk_org)){// 国际+康西
//			if(isjd.booleanValue())
//			{// 如果是 酒店 则 返回 0309的pk
//				pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// 康西
//			}
//		}
//		else if(HKJT_PUB.PK_ORG_JIUDIAN_llzjd.equals(pk_org)){// 朗丽兹+西山
//			if(!isjd.booleanValue())
//			{// 如果是 不是酒店 则 返回 0310的pk
//				pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// 西山
//			}
//		}
		
		zdref.getRefModel().setPk_org(pk_org);
	}
}
