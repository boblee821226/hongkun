package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.hkjt.huiyuan.kaipiaoinfo.plugin.bpplugin.Hy_kaipiaoinfoPluginPoint;
import nc.bs.hkjt.huiyuan.workplugin.HuiyuanPlugin;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * ��׼��������BP
 */
public class AceHy_kaipiaoinfoInsertBP {

	public KaipiaoinfoBillVO[] insert(KaipiaoinfoBillVO[] bills) throws BusinessException {
				
		/**
		 * У��  ��û��  ���� ��û����д  ����
		 */
//		System.out.println("addAfterRule"+bills);
		for( int i=0;i<bills.length;i++ )
		{
			KaipiaoinfoBillVO billVO = bills[i];
			UFDouble fpje = PuPubVO.getUFDouble_NullAsZero( billVO.getParentVO().getFpje() );
			String lx = PuPubVO.getString_TrimZeroLenAsNull( billVO.getParentVO().getVdef01() );
			if(	 fpje.compareTo(UFDouble.ZERO_DBL)<0
			  && lx==null
			)
			{
				throw new BusinessException("��Ʊ���Ϊ����������Ҫ��д���͡�");
			}
		}
		/**END*/
		
		InsertBPTemplate<KaipiaoinfoBillVO> bp = new InsertBPTemplate<KaipiaoinfoBillVO>(
				Hy_kaipiaoinfoPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<KaipiaoinfoBillVO> processor) {
		// TODO ���������
		IRule<KaipiaoinfoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("HK33");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<KaipiaoinfoBillVO> processer) {
		// TODO ����ǰ����
		IRule<KaipiaoinfoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("HK33");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
	}
}
