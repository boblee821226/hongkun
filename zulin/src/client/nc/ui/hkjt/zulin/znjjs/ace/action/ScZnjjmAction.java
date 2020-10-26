package nc.ui.hkjt.zulin.znjjs.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_zulin_znjjmMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.RefreshSingleAction;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmHVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

/**
 * 生成滞纳金减免单
 *
 */
public class ScZnjjmAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public ScZnjjmAction() {
		setBtnName("生成滞纳金减免单");
		setCode("scZnjjmAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction addLineAction;
	private RefreshSingleAction refreshCardAction;

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
	
	public BodyAddLineAction getAddLineAction() {
		return addLineAction;
	}

	public void setAddLineAction(BodyAddLineAction addLineAction) {
		this.addLineAction = addLineAction;
	}

	public RefreshSingleAction getRefreshCardAction() {
		return refreshCardAction;
	}

	public void setRefreshCardAction(RefreshSingleAction refreshCardAction) {
		this.refreshCardAction = refreshCardAction;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		if(this.getListview().isShowing()){
			MessageDialog.showErrorDlg(this.getListview(), "", "请进入卡片界面 操作");
			return;
		}
		
		int[] selectRows = this.getEditor().getBillCardPanel().getBillTable().getSelectedRows();
		
		if(selectRows==null || selectRows.length<=0){
			MessageDialog.showErrorDlg(this.getEditor(), "", "请选择表体数据");
			return;
		}
		
		ZnjjmHVO znjjmHVO = new ZnjjmHVO();
//		ZnjjmBVO[] znjjmBVOs = new ZnjjmBVO[selectRows.length];
		ArrayList<ZnjjmBVO> znjjmBVOs = new ArrayList<ZnjjmBVO>(selectRows.length);
		
		for(int i=0;i<selectRows.length;i++){
			
			int row = selectRows[i];
			
			String pk_cust  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_cust") );
			String pk_area  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_area") );
			String pk_room  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_room") );
			String ht_code  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "ht_code") );
			String ht_rowno = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "ht_rowno") );
			UFDate jf_date  = PuPubVO.getUFDate( getEditor().getBillCardPanel().getBodyValueAt(row, "jf_date") );
			UFDouble jf_mny = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "jf_mny") );
			UFDouble yq_num = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_num") );
			UFDouble yq_bl  = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_bl") );
			UFDouble yq_mny = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_mny") );
			// 应收金额
			UFDouble ys_mny = PuPubVO.getUFDouble_NullAsZero( getEditor().getBillCardPanel().getBodyValueAt(row, "vbdef02") );
			// 实际逾期金额 = 逾期金额 - 应收金额
			yq_mny = yq_mny.sub(ys_mny).setScale(2, UFDouble.ROUND_HALF_UP);
			
			if (yq_mny.compareTo(UFDouble.ZERO_DBL) == 0) {
				continue;
			}
			
			ZnjjmBVO bvo = new ZnjjmBVO();
			bvo.setVrowno( ""+((znjjmBVOs.size()+1)*10) );
			bvo.setPk_cust(pk_cust);
			bvo.setPk_area(pk_area);
			bvo.setPk_room(pk_room);
			bvo.setHt_code(ht_code);
			bvo.setHt_rowno(ht_rowno);
			bvo.setJf_date(jf_date);
			bvo.setJf_mny(jf_mny);
			bvo.setYq_num(yq_num);
			bvo.setYq_bl(yq_bl);
			bvo.setYq_mny(yq_mny);
			
			bvo.setCsourcebillid( PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getHeadItem("pk_hk_zulin_znjjs").getValueObject() ) );
			bvo.setCsourcebillbid( PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_hk_zulin_znjjs_b") ) );
			bvo.setCsourcetypecode( PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getHeadItem("vbilltypecode").getValueObject() ) );
			bvo.setVsourcebillcode( PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getHeadItem("vbillcode").getValueObject() ) );
					
			znjjmBVOs.add(bvo);
		}
		
		if (znjjmBVOs.size() == 0) {
			MessageDialog.showErrorDlg(this.getEditor(), "", "没有要生成的数据。");
			return;
		}
		
		String pk_group = PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("pk_group").getValueObject() );
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject() );
		String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("pk_org_v").getValueObject() );
		UFDate dbilldate = PuPubVO.getUFDate(
				this.getEditor().getBillCardPanel().getHeadItem("dbilldate").getValueObject() );
		
		znjjmHVO.setPk_group(pk_group);
		znjjmHVO.setPk_org(pk_org);
		znjjmHVO.setPk_org_v(pk_org_v);
		znjjmHVO.setDbilldate(dbilldate);
		znjjmHVO.setIbillstatus(-1);
		znjjmHVO.setCreator(AppContext.getInstance().getPkUser());
		znjjmHVO.setCreationtime(new UFDateTime());
		
		ZnjjmBillVO znjjmBillVO = new ZnjjmBillVO();
		znjjmBillVO.setParentVO(znjjmHVO);
		znjjmBillVO.setChildrenVO(znjjmBVOs.toArray(new ZnjjmBVO[0]));
		
		// 进行保存
		IHk_zulin_znjjmMaintain itf = (IHk_zulin_znjjmMaintain)NCLocator.getInstance().lookup(IHk_zulin_znjjmMaintain.class.getName());
		itf.insert(new ZnjjmBillVO[]{znjjmBillVO}, null);
		
		MessageDialog.showWarningDlg(this.getEditor(), "", "成功");
		
	}
}
