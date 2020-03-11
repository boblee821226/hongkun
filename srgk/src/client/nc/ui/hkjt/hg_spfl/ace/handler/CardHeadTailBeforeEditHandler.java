package nc.ui.hkjt.hg_spfl.ace.handler;

import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.hkjt.pub.ace.handler.DeptFilterUtils;
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
		if(SpflHVO.PK_DEPT.equals(e.getKey())){//����
			new DeptFilterUtils().resetDeptRefSql(e);
		}
		else if( SpflHVO.PK_HK_SRGK_HG_SRXM.equals(e.getKey()) ){//������Ŀ 
			
			try {
			
				UIRefPane zdref = (UIRefPane) e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
				String pk_org = e.getBillCardPanel().getHeadItem("pk_org").getValue();
				
				IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
				String pk_parent = e.getBillCardPanel().getHeadItem("pk_parent").getValue();	// ��pk
				
				/**
				 * HK 2020��3��11��14:26:36
				 * ���ݱ����жϣ������LY��ͷ������ñ���˾��������Ŀ������
				 * ���� �� ֮ǰ���߼���
				 */
				String code = e.getBillCardPanel().getHeadItem("code").getValue();	// ����
				
				if (code.startsWith("LY0")) {
					zdref.getRefModel().setPk_org("LY0-" + pk_org);
					return;
				}
				/**
				 * ��� ��֯Ϊ ���ʻ�� ���� һ������Ʒ���� �� 0-������  9-�������ͣ����þƵ��������Ŀ
				 */
				if( pk_org.equals(HKJT_PUB.PK_ORG_HUIGUAN_gjhg) ){
					// ��� ��ǰ��֯ Ϊ ���ʵ�
					if( pk_parent!=null && !"".equals(pk_parent) && !"~".equals(pk_parent) )
					{
						StringBuffer querySQL = 
									// �����˵�
							new StringBuffer("select sp1.code ")
									.append(" from hk_srgk_hg_spfl sp1 ")
									.append(" where (1=1) ")
									.append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"' ")
									.append(" and sp1.code in ('0','9') ")
								.append(" union all ")
									// �����˵�
									.append(" select sp2.code ")
									.append(" from hk_srgk_hg_spfl sp1 ")
									.append(" left join hk_srgk_hg_spfl sp2 on sp1.pk_parent = sp2.pk_hk_srgk_hg_spfl ")
									.append(" where (1=1) ")
									.append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"' ")
									.append(" and sp2.code in ('0','9') ")
								.append(" union all ")
									// �ļ��˵�
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
						{// ˵�� ����������   0-������  9-��������
							zdref.getRefModel().setPk_org(HKJT_PUB.PK_ORG_JIUDIAN);
							return;
						}
					}
				}
				/***END***/
				/**
				 * ��� ��֯Ϊ �����ȾƵ� ���� һ������Ʒ���� �� 01-������  09-�������ͣ����þƵ��������Ŀ
				 */
				else if( pk_org.equals(HKJT_PUB.PK_ORG_JIUDIAN_llzjd) ){
					// ��� ��ǰ��֯ Ϊ �����ȾƵ�
					if( pk_parent!=null && !"".equals(pk_parent) && !"~".equals(pk_parent) )
					{
						StringBuffer querySQL = 
									// �����˵�
							new StringBuffer("select sp1.code ")
									.append(" from hk_srgk_hg_spfl sp1 ")
									.append(" where (1=1) ")
									.append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"' ")
									.append(" and sp1.code in ('01','09') ")
								.append(" union all ")
									// �����˵�
									.append(" select sp2.code ")
									.append(" from hk_srgk_hg_spfl sp1 ")
									.append(" left join hk_srgk_hg_spfl sp2 on sp1.pk_parent = sp2.pk_hk_srgk_hg_spfl ")
									.append(" where (1=1) ")
									.append(" and sp1.pk_hk_srgk_hg_spfl = '"+pk_parent+"' ")
									.append(" and sp2.code in ('01','09') ")
								.append(" union all ")
									// �ļ��˵�
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
						{// ˵�� ����������   0-������  09-��������  ���ھƵ�
							zdref.getRefModel().setPk_org(HKJT_PUB.PK_ORG_JIUDIAN);
							return;
						}
						else{// ����  ���ڻ��
							zdref.getRefModel().setPk_org(HKJT_PUB.PK_ORG_HUIGUAN);
							return;
						}
					}
				}
				/***END***/
				
				zdref.getRefModel().setPk_org(pk_org);
				
			} catch (BusinessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
	}

}
