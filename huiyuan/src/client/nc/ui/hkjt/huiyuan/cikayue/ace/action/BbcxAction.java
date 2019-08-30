package nc.ui.hkjt.huiyuan.cikayue.ace.action;

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
import nc.vo.hkjt.huiyuan.cikayue.CikayueBVO;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.vo.hkjt.huiyuan.cikayue.CikayueHVO;
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
//			String itemname = getValueForColumn(dlg, "itemname", false, false);
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
							.append(" ckinfo.itemname")
							.append(",sum(ckinfo.qc_sl) qc_sl")	//�ڳ�
							.append(",sum(ckinfo.qc_je) qc_je")
							.append(",sum(ckinfo.cz_sl) cz_sl")	//��ֵ
							.append(",sum(ckinfo.cz_je) cz_je")
							.append(",sum(ckinfo.xf_sl) xf_sl")	//����
							.append(",sum(ckinfo.xf_je) xf_je")
							.append(",sum(ckinfo.tj_sl) tj_sl")	//����
							.append(",sum(ckinfo.tj_je) tj_je")
							.append(",sum(ckinfo.zr_sl) zr_sl")	//ת��
							.append(",sum(ckinfo.zr_je) zr_je")
							.append(",sum(ckinfo.zc_sl) zc_sl")	//ת��
							.append(",sum(ckinfo.zc_je) zc_je")
							.append(",sum(ckinfo.jg_sl) jg_sl ")	//���
							.append(" from ")
							.append(" ( ")
								// �ڳ�
								.append(" select ")
								.append(" ckcz.itemname ")
								.append(",sum( ckcz.times ) qc_sl ")
//								.append(",sum( nvl(ckcz.kabili,0) * nvl(ckcz.price,0) * nvl(ckcz.times,0) ) qc_je ")
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
								.append(" group by ckcz.itemname ")
								// ҵ���ڳ�
							.append("     union all ")
								.append(" select ")
								.append(" cib.itemname ")
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
								.append(" group by cib.itemname ")
								// ���ڷ���
							.append("     union all ")
								.append(" select ")
								.append(" cib.itemname ")
								.append(",0 qc_sl ")
								.append(",0 qc_je ")
								/** 
								 * ��ֵ  Ҫ ����   �为����  ����ֵ�����
								 * ���  2016��2��27��16:16:05
								 */
//								.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='null' then cib.shuliang else 0 end ) cz_sl ")
//								.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='null' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) else 0 end ) cz_je ")
								.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='null' then cib.shuliang else 0 end ) " +
									" +   sum( case when cib.xmdl='�为��' and nvl(cib.xmlx,'����')='����ֵ' then cib.shuliang else 0 end ) cz_sl ")
								.append(",sum( case when cib.xmdl='��ֵ' and nvl(cib.xmlx,'null')='null' then round(nvl(cib.kabili,0),4) * round(nvl(cib.danjia,0),2) * nvl(cib.shuliang,0) else 0 end ) " +
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
								.append(" group by cib.itemname ")
								// ������
							.append("     union all ")
								.append(" select ")
								.append(" ckcz.itemname ")
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
								.append(" group by ckcz.itemname ")
								
							.append(" ) ckinfo ")
							.append(" group by ckinfo.itemname ")
			;
			ArrayList<CikayueBVO> list = null;
			try
			{
				list = (ArrayList<CikayueBVO>)iUAPQueryBS.executeQuery(querySQL.toString(), new BeanListProcessor(CikayueBVO.class));
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			if(list==null || list.size()<1)
			{
				getEditor().getBillCardPanel().setBillData(
						getEditor().getBillCardPanel().getBillData());
				getEditor().getModel().initModel(null);
				return;
			}
			
			// ������Ĵ���
			CikayueBillVO[] result_vos = new CikayueBillVO[1];
			CikayueBVO[] result_bvos = new CikayueBVO[list.size()];
			result_bvos = list.toArray(result_bvos);
			result_vos[0] = new CikayueBillVO();
			result_vos[0].setChildrenVO(result_bvos);
			CikayueHVO result_hvo = new CikayueHVO();
			result_hvo.setIbillstatus(-1);
			result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//����
			result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org ����pk
			result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
			result_hvo.setVdef01( ks_date.equals(js_date)?ks_date:(ks_date +" �� "+js_date) );	// ҵ������
//			result_hvo.setItemname(itemname);	// ��Ŀ����
			result_vos[0].setParentVO(result_hvo);
			
			
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
				
				result_bvos[i].setVbdef01(ks_date);
				result_bvos[i].setVbdef02(js_date);
				
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
			tempinfo.setFunNode("HKJ20622");
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
