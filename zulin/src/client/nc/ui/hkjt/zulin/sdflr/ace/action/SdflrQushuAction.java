package nc.ui.hkjt.zulin.sdflr.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 水电费取数
 *
 */
public class SdflrQushuAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public SdflrQushuAction() {
		setBtnName("取数");
		setCode("qushuAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction addLineAction;

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

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		String pk_org = 
			PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject()
			);
		
		if(pk_org==null)
		{
			MessageDialog.showErrorDlg(this.getEditor(), "", "请先输入组织");
			return;
		}
		
		// 表体有数据，不能取数
		{
			int rowCount = getEditor().getBillCardPanel().getBillModel().getRowCount();
			if(rowCount>0)
			{
				MessageDialog.showErrorDlg(this.getEditor(), "", "表体有数据，不能取数。请先删除表体数据。");
				return;
			}
		}
		
		StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" lrb.pk_cust ")
					.append(",lrb.times ")
					.append(",lrb.price ")
					.append(",lr.dbilldate ")
					.append(",lrb.bccb_num ")
					.append(",lrb.pk_room ")
					.append(",lrb.pk_area ")
					.append(",lrb.pk_sfxm ")
					.append(",lrb.pk_place ")
					.append(" from hk_zulin_sdflr lr ")
					.append(" inner join hk_zulin_sdflr_b lrb on lr.pk_hk_zulin_sdflr = lrb.pk_hk_zulin_sdflr ")
					.append(" inner join ")
					.append(" ( ")
					.append(" 	select")
					.append("	 lrb.pk_room ")
					.append("	,lrb.pk_area ")
					.append("	,lrb.pk_sfxm ")
					.append("	,lrb.pk_place ")
					.append("	,max(lr.dbilldate) dbilldate ")
					.append("	from hk_zulin_sdflr lr ")
					.append("	inner join hk_zulin_sdflr_b lrb on lr.pk_hk_zulin_sdflr = lrb.pk_hk_zulin_sdflr ")
					.append("	where lr.dr=0 and lrb.dr=0 ")
					.append("	and lr.ibillstatus = 1 ")
					.append(" 	and lr.pk_org = '"+pk_org+"'" )
					.append("	group by lrb.pk_room,lrb.pk_area,lrb.pk_sfxm,lrb.pk_place ")
					.append(" ) sc ")
					.append("	on ")
					.append("	( ")
					.append("		sc.pk_room = lrb.pk_room and sc.pk_area = lrb.pk_area ")
					.append("		and sc.pk_sfxm = lrb.pk_sfxm and sc.pk_place = lrb.pk_place ")
					.append("		and sc.dbilldate = lr.dbilldate ")
					.append("	) ")
					.append(" where lr.dr=0 and lrb.dr=0 ")
					.append(" and lr.ibillstatus = 1 ")
					.append(" and lr.pk_org = '"+pk_org+"'" )
		;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
	
		if(list!=null && list.size()>0)
		{
			for(int row=0;row<list.size();row++)
			{
				Object[] obj = (Object[])list.get(row);
				
				String	  pk_cust = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
				UFDouble	times = PuPubVO.getUFDouble_ValueAsValue(obj[1]);
				UFDouble	price = PuPubVO.getUFDouble_ValueAsValue(obj[2]);
				UFDate  sccb_date = PuPubVO.getUFDate(obj[3]);
				UFDouble sccb_num = PuPubVO.getUFDouble_ValueAsValue(obj[4]);
				String 	  pk_room = PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
				String 	  pk_area = PuPubVO.getString_TrimZeroLenAsNull(obj[6]);
				String 	  pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(obj[7]);
				String 	 pk_place = PuPubVO.getString_TrimZeroLenAsNull(obj[8]);
				
				this.getAddLineAction().doAction();
				
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_cust,row,"pk_cust");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(times,row,"times");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(price,row,"price");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(sccb_date,row,"sccb_date");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(sccb_num,row,"sccb_num");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_room,row,"pk_room");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_area,row,"pk_area");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_sfxm,row,"pk_sfxm");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_place,row,"pk_place");
				
			}
			
			// 将 参照翻译过来
			getEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		}
	}
	
	/**
	 * 根据 yyyymm 返回 addMonth的 yyyy-mm
	 * month 可正可负
	 */
	public static String getYYYYMM(String yyyymm,int addMonth)
	{
		String result = null;
		
		String[] ym = yyyymm.split("-");
		int year  = PuPubVO.getInteger_NullAs(ym[0], 0);
		int month = PuPubVO.getInteger_NullAs(ym[1], 0);
		
		month = month + addMonth;
		
		if(month<=0)
		{
			year--;
			month+=12;
		}
		else if(month>12)
		{
			year++;
			month-=12;
		}
		
		result = ""+year+"-"+(month<10?"0":"")+month;
		
		return result;
	}
	
	/**
	 * 根据 UFDate 返回 addMonth的 strDate
	 * month 可正可负
	 */
	public static UFDate getDateAddMM(UFDate date,int addMonth)
	{
		UFDate result = null;
		
		int year   = date.getYear();
		int month  = date.getMonth();
		String day = date.getStrDay();
		
		month = month + addMonth;
		
		if(month<=0)
		{
			year--;
			month+=12;
		}
		else if(month>12)
		{
			year++;
			month-=12;
		}
		
		result = new UFDate(""+year+"-"+(month<10?"0":"")+month+"-"+day);
		
		return result;
	}
}
