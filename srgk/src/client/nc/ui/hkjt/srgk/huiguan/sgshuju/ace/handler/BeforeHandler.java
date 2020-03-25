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
	 * ��������˵�������Ϣ����
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
	 * ��������˵��ӱ���Ϣ����
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
			//���ö�ѡ
			zdbref.setMultiSelectedEnabled(true);
			zdbref.getRefModel().addWherePart(
					" and " + ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN + "='"
							+ pk_zd.toString() + "'");
		}
	}

	/**
	 * ������� ���˷�ʽ ����
	 */
	public void resetJZFSRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
				.getBodyItem(e.getKey()).getComponent();
		String pk_org = e.getBillCardPanel().getHeadItem("pk_org").getValue();
		
		// HK 2018��11��6��14:11:27  �Ƿ�Ƶ�
		UFBoolean isjd = PuPubVO.getUFBoolean_NullAs(
			 e.getBillCardPanel().getHeadItem("vdef10").getValueObject()
			,UFBoolean.FALSE);
		
		/**
		 * HK 2019��5��22��15:30:13
		 * ��Ϊ������  0310ת0308�����������Ҫͳһ�����߼���
		 * ��� ��ͷ �Ƿ�Ƶ� ���ˣ��ǾͿ϶��ǾƵꡣ
		 * ��� ��ͷ �Ƿ�Ƶ� û�򹴣����ж� �Ƿ�Ϊ��������Ƶ꣨Ŀǰֻ�п�����Ƶ� �ǵ��Ƶ�������
		 */
//		if(isjd.booleanValue()){	// �Ƶ�� ��һ���ǾƵ�
//			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// �Ƶ����
//		}else{
//			if(pk_org.equals(HKJT_PUB.PK_ORG_JIUDIAN_kfrxyld)){// �Ƶ�û�򹴣�ֻ�� ������Ƶ� Ϊ�Ƶ�
//				pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// �Ƶ����;
//			}else{	// ���� ��Ϊ ���
//				pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// ��ݻ���;
//			}
//		}
		/**
		 * 2020��3��24��16:20:26
		 * �Ƶ����Ϊ ������
		 * ��ݻ���Ϊ ��ɽ
		 */
		if (isjd.booleanValue()) {
			pk_org = "LY0-" + pk_org;	// �Ƶ����
		} else {
			pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// ��ݻ���;
		}
		
//		if(HKJT_PUB.PK_ORG_HUIGUAN_gjhg.equals(pk_org)){// ����+����
//			if(isjd.booleanValue())
//			{// ����� �Ƶ� �� ���� 0309��pk
//				pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// ����
//			}
//		}
//		else if(HKJT_PUB.PK_ORG_JIUDIAN_llzjd.equals(pk_org)){// ������+��ɽ
//			if(!isjd.booleanValue())
//			{// ����� ���ǾƵ� �� ���� 0310��pk
//				pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// ��ɽ
//			}
//		}
		
		zdref.getRefModel().setPk_org(pk_org);
	}
	
	/**
	 * ������� ������Ŀ ����
	 */
	public void resetSRXMRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
				.getBodyItem(e.getKey()).getComponent();
		String pk_org = e.getBillCardPanel().getHeadItem("pk_org").getValue();
		
		// HK 2018��11��6��14:11:27  �Ƿ�Ƶ�
		UFBoolean isjd = PuPubVO.getUFBoolean_NullAs(
			 e.getBillCardPanel().getHeadItem("vdef10").getValueObject()
			,UFBoolean.FALSE);
		
		/**
		 * HK 2019��5��22��15:30:13
		 * ��Ϊ������  0310ת0308�����������Ҫͳһ�����߼���
		 * ��� ��ͷ �Ƿ�Ƶ� ���ˣ��ǾͿ϶��ǾƵꡣ
		 * ��� ��ͷ �Ƿ�Ƶ� û�򹴣����ж� �Ƿ�Ϊ��������Ƶ꣨Ŀǰֻ�п�����Ƶ� �ǵ��Ƶ�������
		 */
//		if(isjd.booleanValue()){	// �Ƶ�� ��һ���ǾƵ�
//			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// �Ƶ����
//		}else{
//			if(pk_org.equals(HKJT_PUB.PK_ORG_JIUDIAN_kfrxyld)){// �Ƶ�û�򹴣�ֻ�� ������Ƶ� Ϊ�Ƶ�
//				pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// �Ƶ����;
//			}else{	// ���� ��Ϊ ���
//				pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// ��ݻ���;
//			}
//		}
		/**
		 * 2020��3��24��16:20:09
		 * ����Ƶ�򹴣� ���ұ���˾��������Ŀ
		 * ���û�򹴣����һ�ݵ�������Ŀ
		 */
		if(isjd.booleanValue()){
			pk_org = "LY0-" + pk_org;
		} else {
			pk_org = HKJT_PUB.PK_ORG_HUIGUAN; // ��ݻ���
		}
		
//		if(HKJT_PUB.PK_ORG_HUIGUAN_gjhg.equals(pk_org)){// ����+����
//			if(isjd.booleanValue())
//			{// ����� �Ƶ� �� ���� 0309��pk
//				pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// ����
//			}
//		}
//		else if(HKJT_PUB.PK_ORG_JIUDIAN_llzjd.equals(pk_org)){// ������+��ɽ
//			if(!isjd.booleanValue())
//			{// ����� ���ǾƵ� �� ���� 0310��pk
//				pk_org = HKJT_PUB.PK_ORG_HUIGUAN;	// ��ɽ
//			}
//		}
		
		zdref.getRefModel().setPk_org(pk_org);
	}
}
