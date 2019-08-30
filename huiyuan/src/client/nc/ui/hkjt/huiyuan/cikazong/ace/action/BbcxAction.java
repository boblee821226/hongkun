package nc.ui.hkjt.huiyuan.cikazong.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
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
import nc.vo.hkjt.huiyuan.cikazong.CikazongBVO;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.vo.hkjt.huiyuan.cikazong.CikazongHVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.querytemplate.TemplateInfo;

public class BbcxAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public BbcxAction() {
		setBtnName("�����ѯ");
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
			String pk_org = getValueForColumn(dlg, "pk_org", true, false);
			// ����  ��֯��ѡ�����
			String pk_org_where = "''";
			String[] pk_org_s = pk_org.split(",");
			for(int i=0;i<pk_org_s.length;i++)
			{// 
				pk_org_where += ",'" + pk_org_s[i] + "'";
			}
			
			String itemname = PuPubVO.getString_TrimZeroLenAsNull( getValueForColumn(dlg, "itemname", false, false) );
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			// ������ ��ʼ������ ����
			String[] chaxun_date = hzdate.split(",");
			
			String ks_date = chaxun_date[0];
			String js_date = chaxun_date[0];
			if(chaxun_date.length==2)
			{
				js_date = chaxun_date[1];
			}
			
			// ���SQL
			StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" substr(ck.dbilldate,0,10) rq ")
							/** 
							 * ��ֵ  Ҫ ����   �为����  ����ֵ�����
							 * ���  2016��2��27��16:16:05
							 */
//							.append(",sum(case when ckb.xmdl='��ֵ' and nvl(ckb.xmlx,'null')!='ת��' then ckb.shuliang else 0 end ) cz_num ")					//��ֵ����
//							.append(",sum(case when ckb.xmdl='��ֵ' and nvl(ckb.xmlx,'null')!='ת��' then ROUND(nvl(ckb.kabili,0),4)*ROUND(nvl(ckb.danjia,0),2)*nvl(ckb.shuliang,0) else 0 end ) cz_money ")		//��ֵ���
							.append(",sum(case when ckb.xmdl='��ֵ' and nvl(ckb.xmlx,'null')='null' then ckb.shuliang else 0 end )" +
								" +   sum(case when ckb.xmdl='�为��' and nvl(ckb.xmlx,'����')='����ֵ' then ckb.shuliang else 0 end ) cz_num ")					//��ֵ����
							.append(",sum(case when ckb.xmdl='��ֵ' and nvl(ckb.xmlx,'null')='null' then ROUND(nvl(ckb.kabili,0),4)*ROUND(nvl(ckb.danjia,0),2)*nvl(ckb.shuliang,0) else 0 end ) " +
								" +   sum(case when ckb.xmdl='�为��' and nvl(ckb.xmlx,'����')='����ֵ' then ROUND(nvl(ckb.kabili,0),4)*ROUND(nvl(ckb.danjia,0),2)*nvl(ckb.shuliang,0) else 0 end ) cz_money ")		//��ֵ���
							/**END*/
							.append(",sum(case when ckb.xmdl='����' then ckb.shuliang else 0 end ) xf_num ")										//��������
							.append(",sum(case when ckb.xmdl='����' then ROUND(nvl(ckb.kabili,0),4)*ROUND(nvl(ckb.danjia,0),2)*nvl(ckb.shuliang,0) else 0 end ) xf_money ")							//���ѽ��
//							.append(",sum(case when ckb.xmdl='�为��' and nvl(ckb.xmlx,'����')!='ת��' then ckb.shuliang else 0 end ) tj_num ")				//��������
//							.append(",sum(case when ckb.xmdl='�为��' and nvl(ckb.xmlx,'����')!='ת��' then ROUND(nvl(ckb.kabili,0),4)*ROUND(nvl(ckb.danjia,0),2)*nvl(ckb.shuliang,0) else 0 end ) tj_money ")	//�������
							.append(",sum(case when ckb.xmdl='�为��' and nvl(ckb.xmlx,'����')='����' then ckb.shuliang else 0 end ) tj_num ")				//��������
							.append(",sum(case when ckb.xmdl='�为��' and nvl(ckb.xmlx,'����')='����' then ROUND(nvl(ckb.kabili,0),4)*ROUND(nvl(ckb.danjia,0),2)*nvl(ckb.shuliang,0) else 0 end ) tj_money ")	//�������
							.append(",sum(case when ckb.xmdl='��ֵ' and nvl(ckb.xmlx,'null')='ת��' then ckb.shuliang else 0 end ) zr_num ")					//ת������
							.append(",sum(case when ckb.xmdl='��ֵ' and nvl(ckb.xmlx,'null')='ת��' then ROUND(nvl(ckb.kabili,0),4)*ROUND(nvl(ckb.danjia,0),2)*nvl(ckb.shuliang,0) else 0 end ) zr_money ")		//ת����
							.append(",sum(case when ckb.xmdl='�为��' and nvl(ckb.xmlx,'����')='ת��' then ckb.shuliang else 0 end ) zc_num ")					//ת������
							.append(",sum(case when ckb.xmdl='�为��' and nvl(ckb.xmlx,'����')='ת��' then ROUND(nvl(ckb.kabili,0),4)*ROUND(nvl(ckb.danjia,0),2)*nvl(ckb.shuliang,0) else 0 end ) zc_money ")		//ת�����
							.append(" from hk_huiyuan_cikainfo_b ckb ")
							.append(" inner join hk_huiyuan_cikainfo ck on ckb.pk_hk_huiyuan_cikainfo = ck.pk_hk_huiyuan_cikainfo ")
//							.append(" left join hk_huiyuan_kadangan_ckcz ckcz on ckb.timescardwaternum = ckcz.timescardwaternum and nvl(ckcz.dr,0)=0 ")
							.append(" where nvl(ckb.dr,0)=0 and nvl(ck.dr,0)=0 ")
							.append(" and substr(ck.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
							.append( (itemname==null) ? ("") : (" and ckb.itemname = '"+itemname+"' ") )
							.append(" and ck.pk_org in (").append(pk_org_where).append(") ")
//							.append(" group by ck.dbilldate,ckb.itemname ")
							.append(" group by ck.dbilldate ")
							.append(" order by ck.dbilldate ")
			;
			
			ArrayList<CikazongBVO> list = (ArrayList<CikazongBVO>)iUAPQueryBS.executeQuery(querySQL.toString(), new BeanListProcessor(CikazongBVO.class));
			
			// ������Ĵ���
			CikazongBillVO[] result_vos = new CikazongBillVO[1];
			CikazongBVO[] result_bvos = new CikazongBVO[list.size()];
			result_bvos = list.toArray(result_bvos);
			result_vos[0] = new CikazongBillVO();
			result_vos[0].setChildrenVO(result_bvos);
			CikazongHVO result_hvo = new CikazongHVO();
			result_hvo.setIbillstatus(-1);
			result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//����
			result_hvo.setPk_org( pk_org_s[0] );	// pk_org ����pk
//			result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
			result_hvo.setVdef01( ks_date.equals(js_date)?ks_date:(ks_date +" �� "+js_date) );	// ҵ������
			result_hvo.setItemname(itemname);	// ��Ŀ����
			result_vos[0].setParentVO(result_hvo);
			
			/**
			 * ��ѯ  �ο���ֵ�����ѣ� ��װ��֤����
			 * Ӫҵ�ձ�
			 */
			HashMap<String,UFDouble[]> yyrb_MAP = new HashMap<String,UFDouble[]>();
			{
				StringBuffer querySQL_ck = 
						new StringBuffer("select ")
								.append(" substr(y.dbilldate,0,10) yyrq ")	// Ӫҵ�ձ�-����
								.append(",sum( case when yb.jzfs_code='0302' then yb.jine else 0 end) ckxs ")	// �ο�����
								.append(",sum( case when yb.jzfs_code='0202' then yb.jine else 0 end) ckkj ")	// �ο�����
								.append(" from hk_srgk_hg_yyribao y ")
								.append(" inner join hk_srgk_hg_yyribao_b yb on y.pk_hk_srgk_hg_yyribao = yb.pk_hk_srgk_hg_yyribao ")
								.append(" where y.dr=0 and yb.dr=0 ")
								.append(" and y.pk_org in (").append(pk_org_where).append(") and substr(y.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
								/**
								 * HK 2019��3��15��15:17:27
								 * ���ʵ�Ӫҵ�ձ� ������ ��Ϊ�Ƿ�Ƶ꣬ ֻȡ �Ƿ�Ƶ� ΪN�ġ�
								 * and nvl(vdef10,'N') in ('~','N','n')
								 */
								.append(" and y.ts in (select max(ts) from hk_srgk_hg_yyribao where dr=0 and nvl(vdef10,'N') in ('~','N','n') and pk_org in (").append(pk_org_where).append(") and substr(dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' group by substr(dbilldate,0,10) ) ")
								/***END***/
								.append(" and yb.jzfs_code in ( ")
								.append("  '0302' ")	// �ο�����
								.append(" ,'0202' ")	// �ο�����
								.append(" ) ")
								.append(" group by substr(y.dbilldate,0,10) ")
				;
				
				ArrayList list_ck = (ArrayList)iUAPQueryBS.executeQuery(querySQL_ck.toString(), new ArrayListProcessor());
				
				if( list_ck.size()>0 )
				{
					for( int i=0;i<list_ck.size();i++ )
					{
						Object[] obj = (Object[])list_ck.get(i);
						
						String key = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
						UFDouble ckxs = PuPubVO.getUFDouble_ValueAsValue(obj[1]);
						UFDouble ckkj = PuPubVO.getUFDouble_ValueAsValue(obj[2]);
						
						yyrb_MAP.put(key, new UFDouble[]{ckxs,ckkj});
						
					}
				}
			}
			
			for( int i=0;i<result_bvos.length;i++ )
			{
				result_bvos[i].setYuliang( // ���� = ��ֵ +��-���ѣ�+��-������+ ת�� +��-ת����
						  PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getCz_num() )
					.add( PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getXf_num() ) )
					.add( PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getTj_num() ) )
					.add( PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getZr_num() ) )
					.add( PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getZc_num() ) )
						
				);
				
				result_bvos[i].setYue( // ��� = ��ֵ +��-���ѣ�+��-������+ ת�� +��-ת����
						  PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getCz_money() )
					.add( PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getXf_money() ) )
					.add( PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getTj_money() ) )
					.add( PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getZr_money() ) )
					.add( PuPubVO.getUFDouble_NullAsZero( result_bvos[i].getZc_money() ) )
						
				);
				
				// ��֤����  ��ֵ
				String key = result_bvos[i].getRq().toString().substring(0,10);
				UFDouble[] value = yyrb_MAP.get(key);
				if( value!=null )
				{
					result_bvos[i].setVbdef01( value[0]==null?"":value[0].toString() );
					result_bvos[i].setVbdef02( value[1]==null?"":value[1].toString() );
				}
			}
			
			// ������� �ŵ�����
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
			tempinfo.setFunNode("HKJ20624");
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
	 * ��ȡ�����������
	 * 
	 * @param dlg
	 *            ��ѯģ��Ԫ��
	 * @param column
	 *            �����ֶ���Ϣ
	 * @param isPK
	 *            ���շ����Ƿ�PK
	 * @param isdate
	 *            �Ƿ����ڸ�ʽ
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
	 * �ڲ�ѯģ����ȡ��ĳ��ֵ
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
