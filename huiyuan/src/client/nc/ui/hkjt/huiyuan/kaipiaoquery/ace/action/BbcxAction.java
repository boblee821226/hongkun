package nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.itf.hkjt.PUB_kaipiao;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.value.IFieldValue;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.ui.querytemplate.value.RefValueObject;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.querytemplate.TemplateInfo;

public class BbcxAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public BbcxAction() {
		setBtnName("查询");
		setCode("bbcxAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	
	public static String KA_CODE;
	public static String FPH;

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
	public void doAction(ActionEvent e) throws Exception {
		
		QueryConditionDLGDelegator dlg = getDlgDelegator();
		
//		String pk_org = getModel().getContext().getPk_org();	// 默认业务单元
//		WorkbenchEnvironment.getInstance().getLoginUser().
//		
//		if(true)
//		{
//			System.out.println("=="+pk_org);
//			return ;
//		}
		
		if (1 == dlg.showModal()) {
			
			this.KA_CODE = PuPubVO.getString_TrimZeroLenAsNull( getValueForColumn(dlg, "ka_code", false, false) );
			this.FPH = PuPubVO.getString_TrimZeroLenAsNull( getValueForColumn(dlg, "fph", false, false) );
			
			String ka_code = this.KA_CODE;
			String fph = this.FPH;
			
			if( ka_code==null && fph==null )
			{
				MessageDialog.showErrorDlg(this.getEditor(), "", "查询时，请填写 会员卡号 或者 发票号。");
				return ;
			}
			
			this.bbcx(ka_code, fph,false,true);
		}
	}
	
	public KaipiaoqueryBillVO[] bbcx(String ka_code,String fph,boolean returnData,boolean isRefresh) throws Exception 
	{
		
		getEditor().getModel().initModel(null);
		
		String currTime = new UFDateTime().toString();	// 当前服务器 时间
		
		ArrayList<KaipiaoqueryBillVO> result_list = new ArrayList<KaipiaoqueryBillVO>();
		
		/**
		 * 按照如下顺序 查数据
		 * 1、有开票记录的  会员卡档案
		 * 2、无开票记录的  会员卡档案
		 * 3、有开票记录的  无业务卡档案
		 * 4、无开票记录的  无业务卡档案  
		 */
		if(ka_code!=null && !"".equals(ka_code.trim()))
		{
			String[] ka_code_str = ka_code.split(",");
			
			for(int i=0;i<ka_code_str.length;i++)
			{
				KaipiaoqueryBillVO[] result_temp = PUB_kaipiao.queryData(currTime, ka_code_str[i], null, isRefresh,"");
				if(result_temp!=null && result_temp.length>0)
				{
					result_list.add(result_temp[0]);
				}
			}
			
		}
		else if(fph!=null && !"".equals(fph.trim()))
		{
			KaipiaoqueryBillVO[] result_temp = PUB_kaipiao.queryData(currTime, null, fph, isRefresh,"");
			if(result_temp!=null && result_temp.length>0)
			{
				for(int i=0;i<result_temp.length;i++)
				{
					result_list.add(result_temp[i]);
				}
			}
		}
		
		KaipiaoqueryBillVO[] RESULT = null;
		if(result_list.size()>0)
		{
			RESULT = new KaipiaoqueryBillVO[result_list.size()];
			RESULT = result_list.toArray(RESULT);
		}
		
		// 将结果集 放到界面
		getEditor().getBillCardPanel().setBillData(
				getEditor().getBillCardPanel().getBillData());
		getEditor().getModel().initModel(RESULT);
		
		return RESULT;
		
	}
	
	QueryConditionDLGDelegator dlgDelegator;
	private QueryConditionDLGDelegator getDlgDelegator() {
		if (dlgDelegator == null) {
			TemplateInfo tempinfo = new TemplateInfo();
			tempinfo.setPk_Org(this.getModel().getContext().getPk_group());
			tempinfo.setUserid(this.getModel().getContext().getPk_loginUser());
			tempinfo.setFunNode("HKJ20632");
			tempinfo.setNodekey("bbcx");
			dlgDelegator = new QueryConditionDLGDelegator(this.getModel()
					.getContext(), tempinfo);
			dlgDelegator.registerNeedPermissionOrgFieldCode("pk_org");
//			QueryTempMainOrgFilterBaseDoc_HZCX bankaccByOrgFileter = new QueryTempMainOrgFilterBaseDoc_HZCX(
//					dlgDelegator, HZShuJuVO.PK_ORG, HZShuJuVO.PK_DEPT);
//			bankaccByOrgFileter.addEditorListener();
		}
		return dlgDelegator;
	}
	
	/**
	 * 获取计算界面条件
	 * 
	 * @param dlg
	 *            查询模板元素
	 * @param column
	 *            界面字段信息
	 * @param isPK
	 *            参照返回是否PK
	 * @param isdate
	 *            是否日期格式
	 * */
	private String getValueForColumn(QueryConditionDLGDelegator dlg,
			String column, boolean isPK, boolean isdate) {
		List<IFilter> filtersByFieldCode = dlg.getQueryConditionDLG().getFiltersByFieldCode(column);
		if (filtersByFieldCode != null && filtersByFieldCode.size() > 0) {
			
			String new_value = "";
			
			for(int field_i=0;field_i<filtersByFieldCode.size();field_i++)
			{
				IFilter filter = filtersByFieldCode.get(field_i);
				if (filter != null) {
					List<String> qryfields = getQryFields(filter, isPK);
					if (qryfields != null && qryfields.size() > 0) {
						for (int i = 0; i < qryfields.size(); i++) {
							String value = qryfields.get(i);
							if (isdate) {
								new_value = new_value + value.substring(0, 10)
										+ ",";
							} else {
								new_value = new_value + "'"+value+"'" + ",";
							}
						}
					}
				}
			}
			
			if(new_value.length()>1 && new_value.endsWith(","))
			{
				new_value = new_value.substring(0, new_value.length() - 1);
				return new_value;
			}
		}
		return "";

	}
	
	/**
	 * 在查询模板中取得某列值
	 * 
	 * @param filter
	 * @return
	 */
	private List<String> getQryFields(IFilter filter, boolean isPk) {
		List<String> rtList = new ArrayList<String>();
		if (filter != null) {
			IFieldValue fieldValue = filter.getFieldValue();
			if (fieldValue != null) {

				List<IFieldValueElement> fieldValues = fieldValue
						.getFieldValues();
				if (fieldValues != null && fieldValues.size() > 0) {
					for (IFieldValueElement fieldValueElement : fieldValues) {
						Object valueObject = fieldValueElement.getValueObject();
						if (valueObject != null) {
							if (valueObject instanceof RefValueObject) {
								RefValueObject refValue = (RefValueObject) valueObject;
								String value = null;
								if (isPk) {
									value = refValue.getPk();
								} else {
									value = refValue.getCode();
								}
								rtList.add(value);
							} else if (valueObject instanceof DefaultConstEnum) {
								DefaultConstEnum constEnum = (DefaultConstEnum) valueObject;
								Object value = constEnum.getValue();
								if (value != null) {
									rtList.add(String.valueOf(value));
								}
							} else if (valueObject instanceof UFDate) {
								rtList.add(valueObject.toString());
							} 
							else
							{
								rtList.add(valueObject.toString());
							}
						}
					}
				}
			}
		}
		return rtList;
	}
	
	BaseDAO dao=null;
	public BaseDAO getBaseDAO(){
		if(dao==null)
			dao=new BaseDAO();
		return dao;
	}
	
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}
	
}
