package nc.ui.hkjt.huiyuan.cikayue.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBVO;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.vo.hkjt.huiyuan.cikayue.CikayueHVO;
import nc.vo.pubapp.AppContext;
import nc.vo.sm.funcreg.FuncRegisterVO;

public class CemxAction extends NCAction {

	private static final long serialVersionUID = -5244330074432692395L;

	public CemxAction() {
		setBtnName("�����ϸ");
		setCode("cemxAction");
	}
	
	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;

	public ShowUpableBillListView getListview() {
		return listview;
	}

	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		int row = -1;
		BillModel billModel = null;
		
		if( this.getEditor().isShowing() )
		{// ��Ƭ����
			row = this.getEditor().getBillCardPanel().getBillTable().getSelectedRow();
			billModel = this.getEditor().getBillCardPanel().getBillModel();
		}
		else
		{// �б����
			row = this.getListview().getBillListPanel().getBodyTable().getSelectedRow();
			billModel = this.getListview().getBillListPanel().getBodyBillModel();
		}
		
		
		Object itemname = billModel.getValueAt(row, "itemname");
		String ks_date = (String)billModel.getValueAt(row, "vbdef01");
		String js_date = (String)billModel.getValueAt(row, "vbdef02");
		
//		MessageDialog.showErrorDlg(this.getEditor(), "hello", "=="+row+"===="+kaxing_name+"===="+kaxing_pk+"====");
		
		StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" ckinfo.ka_code ")
						.append(",ckinfo.itemname ")
						.append(",max(ckinfo.vbdef03) vbdef03 ")	// ������
						.append(",ckinfo.startdata ")
						.append(",sum(ckinfo.qc_sl) qc_sl ")	//�ڳ�
						.append(",sum(ckinfo.qc_je) qc_je ")
						.append(",sum(ckinfo.cz_sl) cz_sl ")	//��ֵ
						.append(",sum(ckinfo.cz_je) cz_je ")
						.append(",sum(ckinfo.xf_sl) xf_sl ")	//����
						.append(",sum(ckinfo.xf_je) xf_je ")
						.append(",sum(ckinfo.tj_sl) tj_sl ")	//����
						.append(",sum(ckinfo.tj_je) tj_je ")
						.append(",sum(ckinfo.zr_sl) zr_sl ")	//ת��
						.append(",sum(ckinfo.zr_je) zr_je ")
						.append(",sum(ckinfo.zc_sl) zc_sl ")	//ת��
						.append(",sum(ckinfo.zc_je) zc_je ")
						.append(",sum(ckinfo.jg_sl) jg_sl ")	//���
						.append(" from ")
						.append(" ( ")
							// �ڳ�
							.append(" select ")
							.append(" ka.ka_code ")
							.append(",ckcz.itemname ")
							.append(",max(ckcz.vbdef03) vbdef03 ")	// ������
							.append(",ckcz.startdata ")
							.append(",sum( ckcz.times ) qc_sl ")
							.append(",sum( round(nvl(ckcz.kabili,0),4) * round(nvl(ckcz.price,0),2) * nvl(ckcz.times,0) ) qc_je ")
							.append(",0 cz_sl ")	// ��ֵ
							.append(",0 cz_je ")
							.append(",0 xf_sl ")	// ����
							.append(",0 xf_je ")
							.append(",0 tj_sl ")	// ����
							.append(",0 tj_je ")
							.append(",0 zr_sl ")	// ת��
							.append(",0 zr_je ")
							.append(",0 zc_sl ")	// ת��
							.append(",0 zc_je ")
							.append(",0 jg_sl ")	// ���
							.append(" from hk_huiyuan_kadangan_ckcz ckcz ")
							.append(" inner join hk_huiyuan_kadangan ka on ckcz.pk_hk_huiyuan_kadangan = ka.pk_hk_huiyuan_kadangan ")
							.append(" where ckcz.dr=0 and ka.dr=0 ")
							.append(" and ckcz.ywdate is null ")
							.append(" and ckcz.itemname = '").append(itemname).append("' ")
							.append(" group by ka.ka_code,ckcz.itemname,ckcz.startdata ")
							// ҵ���ڳ�
						.append("     union all ")
							.append(" select ")
							.append(" cib.ka_code ")
							.append(",cib.itemname ")
							.append(",max(cib.vbdef03) vbdef03 ")	// ������
							.append(",cib.startdata ")
							.append(",sum( cib.shuliang ) qc_sl ")
							.append(",sum( round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) ) qc_je ")
							.append(",0 cz_sl ")	// ��ֵ
							.append(",0 cz_je ")
							.append(",0 xf_sl ")	// ����
							.append(",0 xf_je ")
							.append(",0 tj_sl ")	// ����
							.append(",0 tj_je ")
							.append(",0 zr_sl ")	// ת��
							.append(",0 zr_je ")
							.append(",0 zc_sl ")	// ת��
							.append(",0 zc_je ")
							.append(",0 jg_sl ")	// ���
							.append(" from hk_huiyuan_cikainfo ci ")
							.append(" inner join hk_huiyuan_cikainfo_b cib on ci.pk_hk_huiyuan_cikainfo = cib.pk_hk_huiyuan_cikainfo ")
							.append(" where ci.dr=0 and cib.dr=0 ")
							.append(" and substr(ci.dbilldate,0,10) < '").append(ks_date).append("' ")
							.append(" and cib.itemname = '").append(itemname).append("' ")
							.append(" group by cib.ka_code,cib.itemname,cib.startdata ")
							// ���ڷ���
						.append("     union all ")
							.append(" select ")
							.append(" cib.ka_code ")
							.append(",cib.itemname ")
							.append(",max(cib.vbdef03) vbdef03 ")	// ������
							.append(",cib.startdata ")
							.append(",0 qc_sl ")
							.append(",0 qc_je ")
							/** 
							 * ��ֵ  Ҫ ����   �为����  ����ֵ�����
							 * ���  2016��2��27��16:16:05
							 */
//							.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='null' then cib.shuliang else 0 end ) cz_sl ")
//							.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='null' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) else 0 end ) cz_je ")
							.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='null' then cib.shuliang else 0 end )" +
								" +   sum( case when cib.xmdl='�为��' and nvl(cib.xmlx,'����')='����ֵ' then cib.shuliang else 0 end ) cz_sl ")
							.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='null' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) else 0 end )" +
								" +   sum( case when cib.xmdl='�为��' and nvl(cib.xmlx,'����')='����ֵ' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) else 0 end ) cz_je ")
							/**END*/
							.append(",sum( case when cib.xmdl='����' then cib.shuliang else 0 end ) xf_sl ")
							.append(",sum( case when cib.xmdl='����' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) end ) xf_sl ")
							.append(",sum( case when cib.xmdl='�为��' and nvl(cib.xmlx,'����')='����' then cib.shuliang else 0 end ) tj_sl ")
							.append(",sum( case when cib.xmdl='�为��' and nvl(cib.xmlx,'����')='����' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) else 0 end ) tj_je ")
							.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='ת��' then cib.shuliang else 0 end ) zr_sl ")
							.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='ת��' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) else 0 end ) zr_je ")
							.append(",sum( case when cib.xmdl='�为��' and nvl(cib.xmlx,'����')='ת��' then cib.shuliang else 0 end ) zc_sl ")
							.append(",sum( case when cib.xmdl='�为��' and nvl(cib.xmlx,'����')='ת��' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) else 0 end ) zc_je ")
							.append(",0 jg_sl ")	// ���
							.append(" from hk_huiyuan_cikainfo ci ")
							.append(" inner join hk_huiyuan_cikainfo_b cib on ci.pk_hk_huiyuan_cikainfo = cib.pk_hk_huiyuan_cikainfo ")
							.append(" where ci.dr=0 and cib.dr=0 ")
							.append(" and substr(ci.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
							.append(" and cib.itemname = '").append(itemname).append("' ")
							.append(" group by cib.ka_code,cib.itemname,cib.startdata ")
							// ������
						.append("     union all ")
							.append(" select ")
							.append(" ka.ka_code ")
							.append(",ckcz.itemname ")
							.append(",max(ckcz.vbdef03) vbdef03 ")	// ������
							.append(",ckcz.startdata ")
							.append(",0 qc_sl ")	// �ڳ�
							.append(",0 qc_je ")
							.append(",0 cz_sl ")	// ��ֵ
							.append(",0 cz_je ")
							.append(",0 xf_sl ")	// ����
							.append(",0 xf_je ")
							.append(",0 tj_sl ")	// ����
							.append(",0 tj_je ")
							.append(",0 zr_sl ")	// ת��
							.append(",0 zr_je ")
							.append(",0 zc_sl ")	// ת��
							.append(",0 zc_je ")
							.append(",sum(ckjg.yunum) jg_sl ")	// ���
							.append(" from hk_huiyuan_kadangan ka ")
							.append(" inner join hk_huiyuan_kadangan_ckcz ckcz on ka.pk_hk_huiyuan_kadangan = ckcz.pk_hk_huiyuan_kadangan ")
							.append(" left join hk_huiyuan_kadangan_ckjg ckjg on ckcz.timescardwaternum = ckjg.timescardwaternum ")
							.append(" left join (")
							.append("     select jg.timescardwaternum ")
							.append("           ,nvl(max(jg.yu_time), 'null') yu_time ")
							.append("     from hk_huiyuan_kadangan_ckjg jg ")
							.append("     where jg.dr = 0 ")
							.append("     and nvl(jg.yu_time, '1990-01-01 00:00:00') <='"+js_date+" 23:59:59' ")
							.append("     group by jg.timescardwaternum ")
							.append("     ) jgsj on ckcz.timescardwaternum = jgsj.timescardwaternum ")
							.append(" where ka.dr = 0 ")
							.append(" and ckcz.dr = 0 ")
							.append(" and ka.kastatus not in ('����', '����') ")
							.append(" and nvl(ckjg.yu_time, 'null') = nvl(jgsj.yu_time, 'null') ")
							.append(" and ckcz.itemname = '").append(itemname).append("' ")
							.append(" group by ka.ka_code,ckcz.itemname,ckcz.startdata ")
							
						.append(" ) ckinfo ")
						.append(" group by ckinfo.ka_code,ckinfo.itemname,ckinfo.startdata ")
						.append(" having sum(ckinfo.jg_sl) != sum(ckinfo.qc_sl)+sum(ckinfo.cz_sl)+sum(ckinfo.xf_sl)+sum(ckinfo.tj_sl)+sum(ckinfo.zr_sl)+sum(ckinfo.zc_sl) ")
		;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		ArrayList<CikayueBVO> list_vo = (ArrayList<CikayueBVO>)iUAPQueryBS.executeQuery(querySQL.toString(), new BeanListProcessor(CikayueBVO.class));
		
		
		CikayueBillVO billVO = new CikayueBillVO();
		CikayueBVO[] result_bvos = new CikayueBVO[list_vo.size()];
		result_bvos = list_vo.toArray(result_bvos);
		billVO.setChildrenVO(result_bvos);
		CikayueHVO result_hvo = new CikayueHVO();
		result_hvo.setIbillstatus(-1);
		result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//����
		result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org ����pk
		result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
		result_hvo.setVdef01( ks_date.equals(js_date)?ks_date:(ks_date +" �� "+js_date) );	// ҵ������
		billVO.setParentVO(result_hvo);
		
		for( int i=0;i<result_bvos.length;i++ )
		{// ѭ������  �������ݴ���
			
			// ����/���  = �ڳ� + ��ֵ + ��-ˢ�Σ� + ��-������ + ת�� + ��-ת����
			result_bvos[i].setYue_sl( 
					  PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getQc_sl())
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getCz_sl()) )
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getXf_sl()) )
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getTj_sl()) )
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getZr_sl()) )
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getZc_sl()) )
			);
			result_bvos[i].setYue_je( 
					  PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getQc_je())
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getCz_je()) )
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getXf_je()) )
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getTj_je()) )
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getZr_je()) )
				.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getZc_je()) )
			);
			
			// ���
			result_bvos[i].setCha_sl( // NC��� - ������
					  PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getYue_sl()) 
				.sub( PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getJg_sl()) )
			);
			
		}
		
		
		FuncRegisterVO funvo =
		          WorkbenchEnvironment.getInstance().getFuncRegisterVO("HKJ20622");
		if (null == funvo) {
			nc.vo.pubapp.pattern.exception.ExceptionUtils
	            .wrappBusinessException("��ǰ�û�û�д򿪽ڵ��Ȩ�ޣ�����");
			return;
	      }
		
		Dimension size = new Dimension(800, 500);	// ��ʾ�Ĵ�С
		FuncletInitData initData = new FuncletInitData();
		LinkQueryData_hy data= new LinkQueryData_hy();
		data.obj = billVO;
		initData.setInitType(ILinkType.LINK_TYPE_QUERY);
		initData.setInitData(data);
		
		FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getEditor(),funvo,initData,null,false,size);	// ��������
		
	}
	
	public class LinkQueryData_hy implements ILinkQueryData
	{
		String billid;
		Object obj;

		@Override
		public String getBillID() {
			return billid;
		}

		@Override
		public String getBillType() {
			// TODO �Զ����ɵķ������
			return null;
		}

		@Override
		public String getPkOrg() {
			// TODO �Զ����ɵķ������
			return null;
		}

		@Override
		public Object getUserObject() {
			// TODO �Զ����ɵķ������
			return obj;
		}
		
	}

}
