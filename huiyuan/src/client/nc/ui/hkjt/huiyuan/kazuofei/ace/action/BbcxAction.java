package nc.ui.hkjt.huiyuan.kazuofei.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
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
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBVO;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiHVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.querytemplate.TemplateInfo;

public class BbcxAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public BbcxAction() {
		setBtnName("报表查询");
		setCode("bbcxAction");
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
	public void doAction(ActionEvent e) throws Exception {
		
		QueryConditionDLGDelegator dlg = getDlgDelegator();
		
		if (1 == dlg.showModal()) {
			
			String hzdate = getValueForColumn(dlg, "dbilldate", true, true);
			UFBoolean isshowall = PuPubVO.getUFBoolean_NullAs( getValueForColumn(dlg, "isshowall", false, false),UFBoolean.FALSE );
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			
			// 解析出 开始、结束 日期
			if(hzdate==null || "".equals(hzdate))
				hzdate = "1990-01-01,2990-12-01";
			
			String[] chaxun_date = hzdate.split(",");
			
			String ks_date = chaxun_date[0];
			String js_date = chaxun_date[0];
			if(chaxun_date.length==2)
			{
				js_date = chaxun_date[1];
			}
			
			// 组合SQL
			StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" ka.pk_hk_huiyuan_kaxing KAXING_PK ")
							.append(",ka.ka_code ")
							.append(",ka.pk_hk_huiyuan_kadangan KA_PK ")
							.append(",kaxing.kaxing_code ")
							.append(",kaxing.kaxing_name ")
							.append(",kaxing.ka_je KAXING_JE ")
							.append(",kazf.zc_je YZ_JE ")
							.append(",kacz.cz_time ZF_TIME ")	// 作废时间
							.append(",kazf.zf_time yz_time ")	// 余转时间
							.append(" from hk_huiyuan_kadangan ka ")
							.append(" left join hk_huiyuan_kadangan_cz kacz ")
							.append(" on (ka.pk_hk_huiyuan_kadangan = kacz.pk_hk_huiyuan_kadangan and kacz.vbdef02='作废卡' and nvl(kacz.dr,0)=0) ")
							.append(" left join " +
									"(" +
									"  select " +
									"		zf.pk_hk_huiyuan_kadangan " +
									"      ,max(zf.pk_hk_huiyuan_kadangan_zf) pk_hk_huiyuan_kadangan_zf " +
									"	   ,sum(zf.zc_je) zc_je " +
									"	   ,max(zf.zf_time) zf_time " +
									"  from hk_huiyuan_kadangan_zf zf " + 
									"  where zf.dr = 0 " +
									"  group by zf.pk_hk_huiyuan_kadangan " +
									"  having sum(zf.zc_je)<>0.00 " + 
									") " +
									"kazf ")	// 作废信息可能有多行（存在回冲） 所以要进行汇总，只取 不为0的数据  （可能还得考虑回充日期）
							.append(" on (ka.pk_hk_huiyuan_kadangan = kazf.pk_hk_huiyuan_kadangan) ")
							.append(" left join hk_huiyuan_kaxing kaxing ")
							.append(" on (ka.pk_hk_huiyuan_kaxing = kaxing.pk_hk_huiyuan_kaxing) ")
							.append(" where nvl(ka.dr,0)=0 ")
							.append(" and ( (kacz.pk_hk_huiyuan_kadangan_cz is null or kazf.pk_hk_huiyuan_kadangan_zf is null) ")
			.append( isshowall.booleanValue() ? "    or (kacz.pk_hk_huiyuan_kadangan_cz is not null or kazf.pk_hk_huiyuan_kadangan_zf is not null) " : "")
							.append("     ) ")
							.append(" and ( (substr(kacz.cz_time,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ) ")
							.append("    or (substr(kazf.zf_time,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ) ")
							.append("     )")
			;
			
			ArrayList<KazuofeiBVO> list = (ArrayList<KazuofeiBVO>)iUAPQueryBS.executeQuery(querySQL.toString(), new BeanListProcessor(KazuofeiBVO.class));
			
			// 结果集的处理
			KazuofeiBillVO[] result_vos = new KazuofeiBillVO[1];
			KazuofeiBVO[] result_bvos = new KazuofeiBVO[list.size()];
			result_bvos = list.toArray(result_bvos);
			result_vos[0] = new KazuofeiBillVO();
			result_vos[0].setChildrenVO(result_bvos);
			KazuofeiHVO result_hvo = new KazuofeiHVO();
			result_hvo.setIbillstatus(-1);
			result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
			result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org 正德pk
			result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
			result_hvo.setVdef01( ks_date.equals(js_date)?ks_date:(ks_date +" 至 "+js_date) );	// 业务日期
			result_vos[0].setParentVO(result_hvo);
			
			
//			for( int i=0;i<result_bvos.length;i++ )
//			{
//				
//			}
			
			// 将结果集 放到界面
			getEditor().getBillCardPanel().setBillData(
					getEditor().getBillCardPanel().getBillData());
			getEditor().getModel().initModel(result_vos);
		}
	}
	
	
	QueryConditionDLGDelegator dlgDelegator;
	private QueryConditionDLGDelegator getDlgDelegator() {
		if (dlgDelegator == null) {
			TemplateInfo tempinfo = new TemplateInfo();
			tempinfo.setPk_Org(this.getModel().getContext().getPk_group());
			tempinfo.setUserid(this.getModel().getContext().getPk_loginUser());
			tempinfo.setFunNode("HKJ20608");
			tempinfo.setNodekey("bbcx");
			dlgDelegator = new QueryConditionDLGDelegator(this.getModel()
					.getContext(), tempinfo);
			dlgDelegator.registerNeedPermissionOrgFieldCode(HZShuJuVO.PK_ORG);
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
		List<IFilter> filtersByFieldCode = dlg.getQueryConditionDLG()
				.getFiltersByFieldCode(column);
		if (filtersByFieldCode != null && filtersByFieldCode.size() > 0) {
			IFilter filter = filtersByFieldCode.get(0);
			if (filter != null) {
				List<String> qryfields = getQryFields(filter, isPK);
				if (qryfields != null && qryfields.size() > 0) {
					String new_value = "";
					for (int i = 0; i < qryfields.size(); i++) {
						String value = qryfields.get(i);
						if (isdate) {
							new_value = new_value + value.substring(0, 10)
									+ ",";
						} else {
							new_value = new_value + value + ",";
						}
					}
					new_value = new_value.substring(0, new_value.length() - 1);
					return new_value;
				}
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
