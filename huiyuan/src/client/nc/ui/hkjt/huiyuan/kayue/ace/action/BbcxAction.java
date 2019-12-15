package nc.ui.hkjt.huiyuan.kayue.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
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
import nc.vo.hkjt.huiyuan.kayue.KayueBVO;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.vo.hkjt.huiyuan.kayue.KayueHVO;
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
			HashMap<String,KayueBVO> result_map = new HashMap<String, KayueBVO>(); 
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			// ������ ��ʼ������ ����
			String[] chaxun_date = hzdate.split(",");
			
			String ks_date = chaxun_date[0];
			String js_date = chaxun_date[0];
			if(chaxun_date.length==2)
			{
				js_date = chaxun_date[1];
			}
			
			
			// 1����ѯ��  �ڳ��������
			{
				StringBuffer querySQL_1 = 
						new StringBuffer("select ")
								.append(" ka.pk_hk_huiyuan_kaxing kaxing_pk")
								.append(",max(kx.kaxing_name)     kaxing_name")
								.append(",sum(ka.qc_ye) qichu")
								.append(",count(0) ka_num ")
								.append(" from hk_huiyuan_kadangan ka ")
								.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
								.append(" where ka.dr=0 ")
								.append(" and ka.kastatus not in ( '����','����') ")
								.append(" group by ka.pk_hk_huiyuan_kaxing ")
				;
				ArrayList<KayueBVO> list_1 = (ArrayList<KayueBVO>)iUAPQueryBS.executeQuery(querySQL_1.toString(), new BeanListProcessor(KayueBVO.class));
				
				for( int i=0;i<list_1.size();i++ )
				{
					KayueBVO tempVO = list_1.get(i);
					String kaxing_pk = tempVO.getKaxing_pk();
					
					result_map.put(kaxing_pk, tempVO);
				}
			}
			
			// 2����ѯ��  ҵ����ڳ�����
			{
				
				StringBuffer querySQL_2 = 
						new StringBuffer("select ")
								.append(" kainfo.kaxing_pk ")
								.append(",max(kainfo.kaxing_name) kaxing_name ")
								.append(",sum( nvl(kainfo.chongzhi,0) + nvl(kainfo.xiaofei,0) + nvl(kainfo.zhuanru,0) - nvl(kainfo.zhuanchu,0) ) qichu ")
								.append("from")
								.append("( ")
									.append(" select ")
									.append(" kx.pk_hk_huiyuan_kaxing kaxing_pk ")
									.append(",max(kx.kaxing_name) kaxing_name")
									.append(",sum( case when kib.xmdl='��ֵ' then kib.ka_je else 0 end ) chongzhi")
									.append(",sum( case when kib.xmdl='����' then kib.ka_je else 0 end ) xiaofei")
									.append(",sum( case when kib.xmdl='��ת' then kib.ka_je else 0 end ) zhuanru")
									.append(",0 zhuanchu ")
									.append(" from hk_huiyuan_kainfo ki ")
									.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
									.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")			// ������Ա������
									.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// �ɻ�Ա���Ŀ��ͣ�ȥ�����͵���
									.append(" where ki.dr=0 and kib.dr=0 ")
									.append(" and nvl(kib.zdh,'null')!='null' ")	// ��ȡ�ֹ����ϵ�����
									.append(" and substr(ki.dbilldate,0,10) < '"+ks_date+"' ")
									.append(" group by kx.pk_hk_huiyuan_kaxing ")
								.append(" union all ")
									.append(" select ")
									.append(" kx.pk_hk_huiyuan_kaxing kaxing_pk ")
									.append(",max(kx.kaxing_name) ")
									.append(",0 ")
									.append(",0 ")
									.append(",0 ")
									.append(",sum( case when kib.xmdl='��ת' then kib.ka_je else 0 end ) zhuanchu ")
									.append(" from hk_huiyuan_kainfo ki ")
									.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
									.append(" left join hk_huiyuan_kadangan ka on kib.y_ka_pk = ka.pk_hk_huiyuan_kadangan ")		// ������Ա������
									.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// �ɻ�Ա���Ŀ��ͣ�ȥ�����͵���
									.append(" where ki.dr=0 and kib.dr=0 ")
									.append(" and substr(ki.dbilldate,0,10) < '"+ks_date+"' ")
									.append(" group by kx.pk_hk_huiyuan_kaxing ")
								.append(") kainfo ")
								.append(" group by kainfo.kaxing_pk ")
				;
				ArrayList<KayueBVO> list_2 = (ArrayList<KayueBVO>)iUAPQueryBS.executeQuery(querySQL_2.toString(), new BeanListProcessor(KayueBVO.class));
				
				for( int i=0;i<list_2.size();i++ )
				{
					KayueBVO tempVO = list_2.get(i);
					String kaxing_pk = tempVO.getKaxing_pk();
					
					KayueBVO vo = result_map.get(kaxing_pk);
					if( vo==null )
					{
						result_map.put(kaxing_pk, tempVO);
					}
					else
					{
						vo.setQichu( 
								  PuPubVO.getUFDouble_NullAsZero(vo.getQichu())
							.add( PuPubVO.getUFDouble_NullAsZero(tempVO.getQichu()) )
						);
					}
				}
				
			}
			
			
			// 3����ѯ�� �� �ڼ��ڵ� ҵ������  ��ֵ�����ѡ���ת �����ڷ�����
			{
				StringBuffer querySQL_3 = 
						new StringBuffer("select ")
								.append(" kainfo.kaxing_pk ")
								.append(",max(kainfo.kaxing_name) kaxing_name ")
								.append(",sum(kainfo.chongzhi) chongzhi ")
								.append(",sum(kainfo.xiaofei) xiaofei ")
								.append(",sum(kainfo.zhuanru) zhuanru ")
								.append(",sum(kainfo.zhuanchu) zhuanchu ")
								.append("from")
								.append("( ")
									.append(" select ")
									.append(" kx.pk_hk_huiyuan_kaxing kaxing_pk ")
									.append(",max(kx.kaxing_name) kaxing_name")
									.append(",sum( case when kib.xmdl='��ֵ' then kib.ka_je else 0 end ) chongzhi")
									.append(",sum( case when kib.xmdl='����' then kib.ka_je else 0 end ) xiaofei")
									.append(",sum( case when kib.xmdl='��ת' then kib.ka_je else 0 end ) zhuanru")
									.append(",0 zhuanchu ")
									.append(" from hk_huiyuan_kainfo ki ")
									.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
									.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")			// ������Ա������
									.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// �ɻ�Ա���Ŀ��ͣ�ȥ�����͵���
									.append(" where ki.dr=0 and kib.dr=0 ")
									.append(" and nvl(kib.zdh,'null')!='null' ")	// ��ȡ�ֹ����ϵ�����
									.append(" and substr(ki.dbilldate,0,10) between '"+ks_date+"' and '"+js_date+"' ")
									.append(" group by kx.pk_hk_huiyuan_kaxing ")
								.append(" union all ")
									.append(" select ")
									.append(" kx.pk_hk_huiyuan_kaxing kaxing_pk ")
									.append(",max(kx.kaxing_name) ")
									.append(",0 ")
									.append(",0 ")
									.append(",0 ")
									.append(",sum( case when kib.xmdl='��ת' then kib.ka_je else 0 end ) zhuanchu ")
									.append(" from hk_huiyuan_kainfo ki ")
									.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
									.append(" left join hk_huiyuan_kadangan ka on kib.y_ka_pk = ka.pk_hk_huiyuan_kadangan ")		// ������Ա������
									.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// �ɻ�Ա���Ŀ��ͣ�ȥ�����͵���
									.append(" where ki.dr=0 and kib.dr=0 ")
									.append(" and substr(ki.dbilldate,0,10) between '"+ks_date+"' and '"+js_date+"' ")
									.append(" group by kx.pk_hk_huiyuan_kaxing ")
								.append(") kainfo ")
								.append(" group by kainfo.kaxing_pk ")
				;
				
				ArrayList<KayueBVO> list_3 = (ArrayList<KayueBVO>)iUAPQueryBS.executeQuery(querySQL_3.toString(), new BeanListProcessor(KayueBVO.class));
				
				for( int i=0;i<list_3.size();i++ )
				{
					KayueBVO tempVO = list_3.get(i);
					String kaxing_pk = tempVO.getKaxing_pk();
					
					KayueBVO vo = result_map.get(kaxing_pk);
					if( vo==null )
					{
						result_map.put(kaxing_pk, tempVO);
					}
					else
					{
						vo.setChongzhi(tempVO.getChongzhi());	// ��ֵ
						vo.setXiaofei(tempVO.getXiaofei());		// ����
						vo.setZhuanru(tempVO.getZhuanru());		// ת��
						vo.setZhuanchu( UFDouble.ZERO_DBL.sub(nullAsZero(tempVO.getZhuanchu())) );	// ת�� ��ȡ������
					}
				}
			}
			
			// 4����ѯ��  ������
			{
				StringBuffer querySQL_4 = 
						new StringBuffer("select ")
								.append(" ka.pk_hk_huiyuan_kaxing kaxing_pk ")
								.append(",max(kx.kaxing_name) kaxing_name ")
								.append(",sum(jg.jg_yue) yue_jg ")
								.append(" from hk_huiyuan_kadangan ka ")
								.append(" inner join hk_huiyuan_kadangan_jg jg on ka.pk_hk_huiyuan_kadangan = jg.pk_hk_huiyuan_kadangan ")
								.append(" inner join  ")
								.append(" ( ")
								.append(" select ")
								.append("  pk_hk_huiyuan_kadangan ")
								.append(" ,nvl( max( vbdef01 ),'null' )  vbdef01 ")
								.append(" from hk_huiyuan_kadangan_jg jg ")
								.append(" where dr=0 ")
								.append(" and nvl(vbdef01,'1990-01-01 00:00:00')<='"+js_date+" 23:59:59' ")
								.append(" group by jg.pk_hk_huiyuan_kadangan ")
								.append(" ) jgsj on ka.pk_hk_huiyuan_kadangan = jgsj.pk_hk_huiyuan_kadangan ")
								.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
								.append(" where ka.dr=0 and jg.dr=0 ")
								.append(" and ka.kastatus not in ( '����','����') ")
								.append(" and nvl(jg.vbdef01,'null') = nvl(jgsj.vbdef01,'null') ")
								.append(" group by ka.pk_hk_huiyuan_kaxing ")
				;
				
				ArrayList<KayueBVO> list_4 = (ArrayList<KayueBVO>)iUAPQueryBS.executeQuery(querySQL_4.toString(), new BeanListProcessor(KayueBVO.class));
				
				for( int i=0;i<list_4.size();i++ )
				{
					KayueBVO tempVO = list_4.get(i);
					String kaxing_pk = tempVO.getKaxing_pk();
					
					KayueBVO vo = result_map.get(kaxing_pk);
					if( vo==null )
					{
						result_map.put(kaxing_pk, tempVO);
					}
					else
					{
						vo.setYue_jg(tempVO.getYue_jg());	// ������
					}
				}
			}
			
			
			// ������Ĵ���
			KayueBillVO[] result_vos = new KayueBillVO[1];
			KayueBVO[] result_bvos = new KayueBVO[result_map.size()];
			result_bvos = result_map.values().toArray(result_bvos);
			result_vos[0] = new KayueBillVO();
			result_vos[0].setChildrenVO(result_bvos);
			KayueHVO result_hvo = new KayueHVO();
			result_hvo.setIbillstatus(-1);
			result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//����
			result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org ����pk
			result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
			result_hvo.setVdef01( ks_date.equals(js_date)?ks_date:(ks_date +" �� "+js_date) );	// ҵ������
			result_vos[0].setParentVO(result_hvo);
			
			
			for( int i=0;i<result_bvos.length;i++ )
			{
				result_bvos[i].setYue( 
						 nullAsZero(result_bvos[i].getQichu())		//  �ڳ�
					.add(nullAsZero(result_bvos[i].getChongzhi()))	// +��ֵ
					.add(nullAsZero(result_bvos[i].getZhuanru()))	// +ת��
					.add(nullAsZero(result_bvos[i].getXiaofei()))	// -���� ��Ŀǰ �����Ǹ�����������+��
					.add(nullAsZero(result_bvos[i].getZhuanchu()))	// -ת�� ��Ŀǰ ת���Ǹ�����������+��
				);
				
				result_bvos[i].setChae(	// ��� = NC��� - ������
						 nullAsZero(result_bvos[i].getYue())
					.sub(nullAsZero(result_bvos[i].getYue_jg()))
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
			tempinfo.setFunNode("HKJ20605");
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
