package nc.ui.hkjt.huiyuan.kazhangwu.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
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
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBVO;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuHVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
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
			
			String[] PK_CORP = new String[]
				{
					"0001N510000000001SXX",	// 贵宾楼
					"0001N510000000001SXV",	// 国际会馆
					"0001N510000000001SY1",	// 康福瑞酒店
					"0001N510000000001SY5",	// 康福瑞西山店
					"0001N510000000001SY3",	// 朗丽兹酒店
					"0001N510000000001SY7",	// 西山温泉
					"0001N5100000000UVI5I",	// 太申
				};
			
			// 封装出 报表格式
			KazhangwuHVO   resultHVO  = new KazhangwuHVO();
			KazhangwuBVO[] resultBVOs = new KazhangwuBVO[37];
			
			resultBVOs[0] = new KazhangwuBVO("     充值","售卡");
			resultBVOs[1] = new KazhangwuBVO("","赠送");
			resultBVOs[2] = new KazhangwuBVO("","正德招待");
			resultBVOs[3] = new KazhangwuBVO("","各店专用");
			resultBVOs[4] = new KazhangwuBVO("","营销专用");
			resultBVOs[5] = new KazhangwuBVO("","赔偿专用");
			resultBVOs[6] = new KazhangwuBVO("","贵宾金卡");
			resultBVOs[7] = new KazhangwuBVO("","幸福感恩卡");
			resultBVOs[8] = new KazhangwuBVO("","作废卡");
			resultBVOs[9] = new KazhangwuBVO("","回充");
			resultBVOs[10] = new KazhangwuBVO("----合计----","");
			
			Integer first_index_xf = 11;
			resultBVOs[11] = new KazhangwuBVO("     消费","贵宾楼卡消费");
			resultBVOs[12] = new KazhangwuBVO("","国际店卡消费");
			resultBVOs[13] = new KazhangwuBVO("","康福瑞学院路卡消费");
			resultBVOs[14] = new KazhangwuBVO("","康福瑞西山卡消费");
			resultBVOs[15] = new KazhangwuBVO("","朗丽兹卡消费");
			resultBVOs[16] = new KazhangwuBVO("","西山温泉卡消费");
			resultBVOs[17] = new KazhangwuBVO("","太申卡消费");	// TODO
			resultBVOs[18] = new KazhangwuBVO("----合计----","");
			
			Integer first_index_tz = 19;
			resultBVOs[19] = new KazhangwuBVO("     调整","贵宾楼卡调整");
			resultBVOs[20] = new KazhangwuBVO("","国际店卡调整");
			resultBVOs[21] = new KazhangwuBVO("","康福瑞学院路卡调整");
			resultBVOs[22] = new KazhangwuBVO("","康福瑞西山卡调整");
			resultBVOs[23] = new KazhangwuBVO("","朗丽兹卡调整");
			resultBVOs[24] = new KazhangwuBVO("","西山温泉卡调整");
			resultBVOs[25] = new KazhangwuBVO("","太申卡调整");	// TODO
			resultBVOs[26] = new KazhangwuBVO("----合计----","");
			
			Integer first_index_yf = 27;
			resultBVOs[27] = new KazhangwuBVO("  应付货币","在贵宾楼刷卡");
			resultBVOs[28] = new KazhangwuBVO("","在国际店刷卡");
			resultBVOs[29] = new KazhangwuBVO("","在康福瑞学院路刷卡");
			resultBVOs[30] = new KazhangwuBVO("","在康福瑞西山刷卡");
			resultBVOs[31] = new KazhangwuBVO("","在朗丽兹刷卡");
			resultBVOs[32] = new KazhangwuBVO("","在西山温泉刷卡");
			resultBVOs[33] = new KazhangwuBVO("","在太申刷卡");	// TODO
			Integer total_index = 34;
			resultBVOs[34] = new KazhangwuBVO("----合计----","");
			resultBVOs[35] = new KazhangwuBVO("----余额----","");
			resultBVOs[36] = new KazhangwuBVO("---余转差异---","");
			
			String hzdate = getValueForColumn(dlg, "dbilldate", true, true);
			String pk_org = getValueForColumn(dlg, "pk_org", true, false);
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
			
			
			/**
			 * 1、查询充值信息
			 */
			{
				StringBuffer querySQL_1 = 
						new StringBuffer("select ")
								.append(" sum( case when kib.xmdl='充值' and ( nvl(kib.xmlx,'售卡')='售卡' or (nvl(kib.xmlx,'售卡')='作废卡' and nvl(kib.zdh,'null')='null') ) then kib.ka_ss else 0 end ) sk ")	// 包含 售卡 + 手工录入的作废卡
								.append(",sum( case when kib.xmdl='充值' and ( nvl(kib.xmlx,'售卡')='售卡' or (nvl(kib.xmlx,'售卡')='作废卡' and nvl(kib.zdh,'null')='null') ) then kib.ka_zs else 0 end ) zs ")
								.append(",sum( case when kib.xmdl='充值' and nvl(kib.xmlx,'售卡')='正德招待' then kib.ka_je else 0 end )  zdzd ")
								.append(",sum( case when kib.xmdl='充值' and nvl(kib.xmlx,'售卡')='各店专用' then kib.ka_je else 0 end )  gdzy ")
								.append(",sum( case when kib.xmdl='充值' and nvl(kib.xmlx,'售卡')='营销专用' then kib.ka_je else 0 end )  yxzy ")
								.append(",sum( case when kib.xmdl='充值' and nvl(kib.xmlx,'售卡')='赔偿专用' then kib.ka_je else 0 end )  pczy ")
								.append(",sum( case when kib.xmdl='充值' and nvl(kib.xmlx,'售卡')='贵宾金卡' then kib.ka_je else 0 end )  gbjk ")
								.append(",sum( case when kib.xmdl='充值' and nvl(kib.xmlx,'售卡')='幸福感恩卡' then kib.ka_je else 0 end )  xfgek ")
								.append(",sum( case when kib.xmdl='充值' and nvl(kib.xmlx,'售卡')='作废卡' and kib.ka_code not between '0302101237' and '0302101326' then kib.ka_je else 0 end )  zuofeika ")
								.append(",sum( case when kib.xmdl='充值' and nvl(kib.xmlx,'售卡')='回充' then kib.ka_je else 0 end )  huichong ")
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" where ")
								.append(" ki.dr=0 and kib.dr=0 ")
								.append(" and ki.pk_org = '").append(pk_org).append("' ")	// 组织
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// 时间段
				; 
				
				ArrayList list_1 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
				
				if( list_1.size()>0 )
				{
					Object[] obj = (Object[])list_1.get(0);
					UFDouble cz_kajie_total = UFDouble.ZERO_DBL;
					
					for(int obj_i=0;obj_i<obj.length;obj_i++)
					{
						resultBVOs[obj_i].setKajie( PuPubVO.getUFDouble_NullAsZero(obj[obj_i]) );
						cz_kajie_total = cz_kajie_total.add( resultBVOs[obj_i].getKajie() );
					}
					
					resultBVOs[0].setKajie_kbl( resultBVOs[0].getKajie() );	// 将售卡的卡比例卡结  赋值为 卡结
					resultBVOs[10].setKajie( cz_kajie_total );	// 充值 卡结 合计
//					resultBVOs[10].setKajie_kbl( resultBVOs[0].getKajie() );	// 充值 卡比例卡结 合计
				}
			}
			
			/**
			 * 2、查询 消费信息
			 * 卡结             vbdef03
			 * 卡比例卡结  vbdef04
			 */
			{
				StringBuffer querySQL_2 = 
						new StringBuffer("select ")
								.append(" ka.pk_org ")		// pk_org
								.append(",sum(kib.ka_je) ")	// 卡金额
								.append(",sum(kib.ka_ss) ")	// 卡实收
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")
								.append(" where ki.dr=0 and kib.dr=0 ")
								.append(" and kib.xmdl = '消费' ")	// 项目大类 = 消费
								.append(" and ki.pk_org = '").append(pk_org).append("' ")	// 组织
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// 时间段
								.append(" group by ka.pk_org ")
								;
				
				ArrayList list_2 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_2 = new HashMap<String,UFDouble[]>();
				
				if( list_2.size()>0 )
				{
					for( int i=0;i<list_2.size();i++ )
					{// 放在 HashMap
						Object[] obj = (Object[])list_2.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = PuPubVO.getUFDouble_NullAsZero( obj[1] );
						map_value[1] = PuPubVO.getUFDouble_NullAsZero( obj[2] );
						
						map_2.put(map_key, map_value);
					}
					
					UFDouble xf_kj_total = UFDouble.ZERO_DBL;
					UFDouble xf_kbl_total = UFDouble.ZERO_DBL;
					int first_index = first_index_xf;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{ 
						UFDouble[] map_value = map_2.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setVbdef03(UFDouble.ZERO_DBL.toString());
							resultBVOs[first_index+i].setVbdef04(UFDouble.ZERO_DBL.toString());
						}
						else
						{
							resultBVOs[first_index+i].setVbdef03(map_value[0].toString());
							resultBVOs[first_index+i].setVbdef04(map_value[1].toString());
							
							xf_kj_total  =  xf_kj_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef03() ) );
							xf_kbl_total = xf_kbl_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef04() ) );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setVbdef03( xf_kj_total.toString() );		// 消费 卡结 合计
					resultBVOs[first_index+PK_CORP.length].setVbdef04( xf_kbl_total.toString() );		// 消费 卡比例卡结 合计
				}
			}
			
			/**
			 * 2.1、分区消费
			 * 卡结             vbdef01
			 * 卡比例卡结  vbdef02
			 * 
			 * 2018年4月27日10:27:44
			 */
			{
				StringBuffer querySQL_2 = 
						new StringBuffer("select ")
								.append(" ka.pk_org ")		// pk_org
								.append(",sum( to_number(zb.vbdef01) ) ")	// 卡金额
								.append(",sum( round(to_number(zb.vbdef01)*to_number(zb.vbdef03),2) ) ")	// 卡实收
								.append(" from hk_srgk_hg_zhangdan z ")
								.append(" inner join hk_srgk_hg_zhangdan_b zb on ( z.pk_hk_dzpt_hg_zhangdan = zb.pk_hk_dzpt_hg_zhangdan and nvl(zb.vbdef01,'~') != '~' ) ")
								.append(" left join hk_huiyuan_kadangan ka on ( zb.vbdef02 = ka.ka_code and ka.dr=0 ) ")
								.append(" where z.dr=0 and zb.dr=0 ")
								.append(" and z.pk_org = '").append(pk_org).append("' ")	// 组织
								.append(" and substr(z.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// 时间段
								.append(" group by ka.pk_org ")
								;
				
				ArrayList list_2 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_2 = new HashMap<String,UFDouble[]>();
				
				if( list_2.size()>0 )
				{
					for( int i=0;i<list_2.size();i++ )
					{// 放在 HashMap
						Object[] obj = (Object[])list_2.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( obj[1] ) );	// 消费取负数，与之前逻辑一致
						map_value[1] = UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( obj[2] ) );
						
						map_2.put(map_key, map_value);
					}
					
					UFDouble fq_kj_total = UFDouble.ZERO_DBL;
					UFDouble fq_kbl_total = UFDouble.ZERO_DBL;
					int first_index = first_index_xf;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{// Vbdef01 分区卡结    Vbdef02 分区卡比例卡结
						UFDouble[] map_value = map_2.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setVbdef01(UFDouble.ZERO_DBL.toString());
							resultBVOs[first_index+i].setVbdef02(UFDouble.ZERO_DBL.toString());
						}
						else
						{
							resultBVOs[first_index+i].setVbdef01(map_value[0].toString());
							resultBVOs[first_index+i].setVbdef02(map_value[1].toString());
							
							fq_kj_total  =  fq_kj_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef01() ) );
							fq_kbl_total =  fq_kbl_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef02() ) );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setVbdef01( fq_kj_total.toString() ); 	// 分区 卡结 合计
					resultBVOs[first_index+PK_CORP.length].setVbdef02( fq_kbl_total.toString() );	// 分区 卡比例卡结 合计
				}
			}
			
			/**
			 * 2.2、合计卡结         = 会员卡结 3 + 分区卡结 1
			 *   合计卡比例卡结    = 会员卡比例卡结 4 + 分区卡比例卡结 2
			 */
			{
				int first_index = first_index_xf;	// 
				for( int i=0;i<PK_CORP.length + 1;i++ )	// 需要计算  合计行
				{
					resultBVOs[first_index+i].setKajie(
							  PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef03() )
						.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef01() ) )
					);
					resultBVOs[first_index+i].setKajie_kbl(
							  PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef04() )
						.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef02() ) )
					);
				}
				
			}
			
			/**
			 * 3、查询 回充信息
			 */
			{
				StringBuffer querySQL_3 = 
						new StringBuffer("select ")
								.append(" ka.pk_org ")		// pk_org
								.append(",sum(kib.ka_je) ")	// 卡金额
								.append(",sum(kib.ka_ss) ")	// 卡实收
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")
								.append(" where ki.dr=0 and kib.dr=0 ")
								.append(" and kib.xmlx = '回充' ")	// 项目类型 = 回充
								.append(" and ki.pk_org = '").append(pk_org).append("' ")	// 组织
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// 时间段
								.append(" group by ka.pk_org ")
								;
				
				ArrayList list_3 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_3.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_3 = new HashMap<String,UFDouble[]>();
				
				if( list_3.size()>0 )
				{
					for( int i=0;i<list_3.size();i++ )
					{// 放在 HashMap
						Object[] obj = (Object[])list_3.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = PuPubVO.getUFDouble_NullAsZero( obj[1] );
						map_value[1] = PuPubVO.getUFDouble_NullAsZero( obj[2] );
						
						map_3.put(map_key, map_value);
					}
					
					UFDouble tz_kj_total  = UFDouble.ZERO_DBL;
					UFDouble tz_kbl_total = UFDouble.ZERO_DBL;
					int first_index = first_index_tz;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{
						UFDouble[] map_value = map_3.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setKajie(UFDouble.ZERO_DBL);
							resultBVOs[first_index+i].setKajie_kbl(UFDouble.ZERO_DBL);
						}
						else
						{
							resultBVOs[first_index+i].setKajie( PuPubVO.getUFDouble_NullAsZero(map_value[0]) );
							resultBVOs[first_index+i].setKajie_kbl( PuPubVO.getUFDouble_NullAsZero(map_value[1]) );
							
							tz_kj_total  =  tz_kj_total.add( resultBVOs[first_index+i].getKajie() );
							tz_kbl_total = tz_kbl_total.add( resultBVOs[first_index+i].getKajie_kbl() );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setKajie( tz_kj_total );		// 调整 卡结 合计
					resultBVOs[first_index+PK_CORP.length].setKajie_kbl( tz_kbl_total );// 调整 卡比例卡结 合计
				}
			}
			
			/**
			 * 4、查询 应付货币
			 * 卡结             vbdef03
			 * 卡比例卡结  vbdef04
			 */
			{

				StringBuffer querySQL_4 = 
						new StringBuffer("select ")
								.append(" ki.pk_org ")		// pk_org
								.append(",sum(kib.ka_je) ")	// 卡金额
								.append(",sum(kib.ka_ss) ")	// 卡实收
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")
								.append(" where ki.dr=0 and kib.dr=0 ")
								.append(" and ( kib.xmdl = '消费' or kib.xmlx = '回充' ) ")	// 项目大类=消费 or 项目类型=回充
								.append(" and ka.pk_org = '").append(pk_org).append("' ")	// 组织
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// 时间段
								.append(" group by ki.pk_org ")
								;
				
				ArrayList list_4 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_4.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_4 = new HashMap<String,UFDouble[]>();
				
				if( list_4.size()>0 )
				{
					for( int i=0;i<list_4.size();i++ )
					{// 放在 HashMap
						Object[] obj = (Object[])list_4.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = PuPubVO.getUFDouble_NullAsZero( obj[1] );
						map_value[1] = PuPubVO.getUFDouble_NullAsZero( obj[2] );
						
						map_4.put(map_key, map_value);
					}
					
					UFDouble yf_kj_total  = UFDouble.ZERO_DBL;
					UFDouble yf_kbl_total = UFDouble.ZERO_DBL;
					int first_index = first_index_yf;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{
						UFDouble[] map_value = map_4.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setVbdef03(UFDouble.ZERO_DBL.toString());
							resultBVOs[first_index+i].setVbdef04(UFDouble.ZERO_DBL.toString());
						}
						else
						{
							resultBVOs[first_index+i].setVbdef03(map_value[0].toString());
							resultBVOs[first_index+i].setVbdef04(map_value[1].toString());
							
							yf_kj_total  =  yf_kj_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef03() ) );
							yf_kbl_total = yf_kbl_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef04() ) );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setVbdef03( yf_kj_total.toString() );	// 应付 卡结 合计
					resultBVOs[first_index+PK_CORP.length].setVbdef04( yf_kbl_total.toString() );	// 应付 卡比例卡结 合计
				}
			
			}
			
			/**
			 * 4.1、查询 应付货币（分区）
			 * 卡结             vbdef01
			 * 卡比例卡结  vbdef02
			 */
			{

				StringBuffer querySQL_4 = 
						new StringBuffer("select ")
								.append(" z.pk_org ")		// pk_org
								.append(",sum( to_number(zb.vbdef01) ) ")	// 卡金额
								.append(",sum( round(to_number(zb.vbdef01)*to_number(zb.vbdef03),2) ) ")	// 卡实收
								.append(" from hk_srgk_hg_zhangdan z ")
								.append(" inner join hk_srgk_hg_zhangdan_b zb on ( z.pk_hk_dzpt_hg_zhangdan = zb.pk_hk_dzpt_hg_zhangdan and nvl(zb.vbdef01,'~') != '~' ) ")
								.append(" left join hk_huiyuan_kadangan ka on ( zb.vbdef02 = ka.ka_code and ka.dr=0 ) ")
								.append(" where z.dr=0 and zb.dr=0 ")
								.append(" and ka.pk_org = '").append(pk_org).append("' ")	// 组织
								.append(" and substr(z.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")	// 时间段
								.append(" group by z.pk_org ")
								;
				
				ArrayList list_4 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_4.toString(), new ArrayListProcessor());
				HashMap<String,UFDouble[]> map_4 = new HashMap<String,UFDouble[]>();
				
				if( list_4.size()>0 )
				{
					for( int i=0;i<list_4.size();i++ )
					{// 放在 HashMap
						Object[] obj = (Object[])list_4.get(i);
						String map_key = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
						UFDouble[] map_value = new UFDouble[2];
						map_value[0] = UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( obj[1] ) );
						map_value[1] = UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( obj[2] ) );
						
						map_4.put(map_key, map_value);
					}
					
					UFDouble fq_yf_kj_total  = UFDouble.ZERO_DBL;
					UFDouble fq_yf_kbl_total = UFDouble.ZERO_DBL;
					int first_index = first_index_yf;
					
					for( int i=0;i<PK_CORP.length;i++ )
					{
						UFDouble[] map_value = map_4.get(PK_CORP[i]);
						if( map_value==null )
						{
							resultBVOs[first_index+i].setVbdef01(UFDouble.ZERO_DBL.toString());
							resultBVOs[first_index+i].setVbdef02(UFDouble.ZERO_DBL.toString());
						}
						else
						{
							resultBVOs[first_index+i].setVbdef01(map_value[0].toString());
							resultBVOs[first_index+i].setVbdef02(map_value[1].toString());
							
							fq_yf_kj_total  = fq_yf_kj_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef01() ) );
							fq_yf_kbl_total = fq_yf_kbl_total.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef02() ) );
						}
					}
					
					resultBVOs[first_index+PK_CORP.length].setVbdef01( fq_yf_kj_total.toString() );		// 分区 应付 卡结 合计
					resultBVOs[first_index+PK_CORP.length].setVbdef02( fq_yf_kbl_total.toString() );	// 分区 应付 卡比例卡结 合计
				}
			}
			
			/**
			 * 4.2、合计卡结         = 会员卡结 3 + 分区卡结 1
			 *   合计卡比例卡结    = 会员卡比例卡结 4 + 分区卡比例卡结 2
			 */
			{
				int first_index = first_index_yf;
				for( int i=0;i<PK_CORP.length;i++ )
				{
					resultBVOs[first_index+i].setKajie(
							  PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef03() )
						.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef01() ) )
					);
					resultBVOs[first_index+i].setKajie_kbl(
							  PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef04() )
						.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[first_index+i].getVbdef02() ) )
					);
				}
			}
			
			
			/**
			 * 5、贵宾楼 与 国际店  不区分业务
			 * "0001N510000000001SXX",	// 贵宾楼
			 * "0001N510000000001SXV",	// 国际会馆
			 * resultBVOs[11] = new KazhangwuBVO("     消费","贵宾楼卡消费");
			 * resultBVOs[12] = new KazhangwuBVO("","国际店卡消费");
			 * resultBVOs[25] = new KazhangwuBVO("  应付货币","在贵宾楼刷卡");
			 * resultBVOs[26] = new KazhangwuBVO("","在国际店刷卡");
			 * resultBVOs[31] = new KazhangwuBVO("----合计----","");
			 * 
			 */
			{
				if( "0001N510000000001SXX".equals(pk_org) )
				{// 贵宾楼
					// 将 国际店卡的消费 转到 贵宾楼 （不影响 消费合计）
					// 合计
					resultBVOs[11].setKajie( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getKajie())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie()) ) 
					);
					resultBVOs[11].setKajie_kbl( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getKajie_kbl())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie_kbl()) ) 
					);
					resultBVOs[12].setKajie( UFDouble.ZERO_DBL );
					resultBVOs[12].setKajie_kbl( UFDouble.ZERO_DBL );
					// 会员
					resultBVOs[11].setVbdef03( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef03())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef03()) ) 
						.toString()
					);
					resultBVOs[11].setVbdef04( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef04())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef04()) )
						.toString()
					);
					resultBVOs[12].setVbdef03( UFDouble.ZERO_DBL.toString() );
					resultBVOs[12].setVbdef04( UFDouble.ZERO_DBL.toString() );
					// 分区
					resultBVOs[11].setVbdef01( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef01())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef01()) ) 
						.toString()
					);
					resultBVOs[11].setVbdef02( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef02())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef02()) )
						.toString()
					);
					resultBVOs[12].setVbdef01( UFDouble.ZERO_DBL.toString() );
					resultBVOs[12].setVbdef02( UFDouble.ZERO_DBL.toString() );
					
					// 应付货币  将 在国际店刷卡的清空
					// 将 在贵宾楼刷卡 = 贵宾楼消费 + 贵宾楼回充
					// 会影响 应付货币 合计，需要重算 合计
					////// 合计 //////
					resultBVOs[26+2].setKajie( UFDouble.ZERO_DBL );	// 清空 国际店
					resultBVOs[26+2].setKajie_kbl( UFDouble.ZERO_DBL );
					
					resultBVOs[25+2].setKajie( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getKajie())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getKajie())) );	// 赋值 贵宾楼的新数据
					resultBVOs[25+2].setKajie_kbl( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getKajie_kbl())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getKajie_kbl()) ) );
					////// 会员 //////
					resultBVOs[26+2].setVbdef03( UFDouble.ZERO_DBL.toString() );
					resultBVOs[26+2].setVbdef04( UFDouble.ZERO_DBL.toString() );
					
					resultBVOs[25+2].setVbdef03( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef03())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getVbdef03())) 
										.toString() );	// 赋值 贵宾楼的新数据
					resultBVOs[25+2].setVbdef04( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef04())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getVbdef04()) ) 
											.toString() );
					////// 分区 //////
					resultBVOs[26+2].setVbdef01( UFDouble.ZERO_DBL.toString() );
					resultBVOs[26+2].setVbdef02( UFDouble.ZERO_DBL.toString() );
					
					resultBVOs[25+2].setVbdef01( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef01())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getVbdef01())) 
										.toString() );	// 赋值 贵宾楼的新数据
					resultBVOs[25+2].setVbdef02( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef02())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getVbdef02()) ) 
											.toString() );
				}
				
				else if( "0001N510000000001SXV".equals(pk_org) )
				{// 国际店
					// 将 贵宾楼的消费 转到 国际店
					// 合计
					resultBVOs[12].setKajie( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getKajie())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie()) ) 
					);
					resultBVOs[12].setKajie_kbl( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getKajie_kbl())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie_kbl()) ) 
					);
					resultBVOs[11].setKajie( UFDouble.ZERO_DBL );
					resultBVOs[11].setKajie_kbl( UFDouble.ZERO_DBL );
					// 会员
					resultBVOs[12].setVbdef03( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef03())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef03()) ) 
						.toString()
					);
					resultBVOs[12].setVbdef04( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef04())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef04()) )
						.toString()
					);
					resultBVOs[11].setVbdef03( UFDouble.ZERO_DBL.toString() );
					resultBVOs[11].setVbdef04( UFDouble.ZERO_DBL.toString() );
					// 分区
					resultBVOs[12].setVbdef01( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef01())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef01()) ) 
						.toString()
					);
					resultBVOs[12].setVbdef02( 
							  PuPubVO.getUFDouble_NullAsZero(resultBVOs[11].getVbdef02())
						.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getVbdef02()) )
						.toString()
					);
					resultBVOs[11].setVbdef01( UFDouble.ZERO_DBL.toString() );
					resultBVOs[11].setVbdef02( UFDouble.ZERO_DBL.toString() );
					
					// 应付货币  将 在贵宾楼刷卡的清空
					// 将 在国际店刷卡 = 国际店消费  + 国际店回充
					// 会影响 应付货币 合计，需要重算 合计
					////// 合计 //////
					resultBVOs[25+2].setKajie( UFDouble.ZERO_DBL );
					resultBVOs[25+2].setKajie_kbl( UFDouble.ZERO_DBL );
					
					resultBVOs[26+2].setKajie(  PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie())
										.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[19+1].getKajie()) ) );
					resultBVOs[26+2].setKajie_kbl( PuPubVO.getUFDouble_NullAsZero(resultBVOs[12].getKajie_kbl())
										.add( PuPubVO.getUFDouble_NullAsZero(resultBVOs[19+1].getKajie_kbl()) ) );
					////// 会员 //////
					resultBVOs[25+2].setVbdef03( UFDouble.ZERO_DBL.toString() );
					resultBVOs[25+2].setVbdef04( UFDouble.ZERO_DBL.toString() );
					
					resultBVOs[26+2].setVbdef03( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef03())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getVbdef03())) 
										.toString() );	// 赋值 贵宾楼的新数据
					resultBVOs[26+2].setVbdef04( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef04())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getVbdef04()) ) 
											.toString() );
					////// 分区 //////
					resultBVOs[25+2].setVbdef01( UFDouble.ZERO_DBL.toString() );
					resultBVOs[25+2].setVbdef02( UFDouble.ZERO_DBL.toString() );
					
					resultBVOs[26+2].setVbdef01( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef01())
										.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getVbdef01())) 
										.toString() );	// 赋值 贵宾楼的新数据
					resultBVOs[26+2].setVbdef02( PuPubVO.getUFDouble_NullAsZero( resultBVOs[11].getVbdef02())
											.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[18+1].getVbdef02()) ) 
															.toString() );
				}
				
				resultBVOs[31+3].setKajie( //重算合计
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25+2].getKajie() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26+2].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27+2].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28+2].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29+2].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30+2].getKajie() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[31+2].getKajie() ) ) 
				);
				resultBVOs[31+3].setKajie_kbl( 
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25+2].getKajie_kbl() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26+2].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27+2].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28+2].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29+2].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30+2].getKajie_kbl() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[31+2].getKajie_kbl() ) )
				);
				resultBVOs[31+3].setVbdef03( //重算合计--会员
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25+2].getVbdef03() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26+2].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27+2].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28+2].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29+2].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30+2].getVbdef03() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[31+2].getVbdef03() ) )
					.toString()
				);
				resultBVOs[31+3].setVbdef04( //重算合计--会员
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25+2].getVbdef04() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26+2].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27+2].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28+2].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29+2].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30+2].getVbdef04() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[31+2].getVbdef04() ) )
					.toString()
				);
				resultBVOs[31+3].setVbdef01( //重算合计--分区
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25+2].getVbdef01() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26+2].getVbdef01() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27+2].getVbdef01() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28+2].getVbdef01() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29+2].getVbdef01() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30+2].getVbdef01() ) )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[31+2].getVbdef01() ) )
					.toString()
				);
				resultBVOs[total_index].setVbdef02( //重算合计--分区
						  PuPubVO.getUFDouble_NullAsZero( resultBVOs[25+2].getVbdef02() )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[26+2].getVbdef02() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[27+2].getVbdef02() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[28+2].getVbdef02() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[29+2].getVbdef02() ) ) 
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[30+2].getVbdef02() ) )
					.add( PuPubVO.getUFDouble_NullAsZero( resultBVOs[31+2].getVbdef02() ) )
					.toString()
				);
				
			}
			
			
			/**
			 * 6、 计算 应付余额
			 */
			{
				resultBVOs[resultBVOs.length-2].setKajie(
						resultBVOs[10].getKajie()
					.sub( resultBVOs[8].getKajie() )
					.add( resultBVOs[total_index].getKajie() ) 
				);	// 所有充值（除作废）-应付货币之和 （消费、应付 是由负数体现， 所以用add）
				resultBVOs[resultBVOs.length-2].setKajie_kbl(
						resultBVOs[0].getKajie_kbl()
					.add( resultBVOs[total_index].getKajie_kbl() ) 
				);	// 售卡 - 应付货币之和  （消费、应付 是由负数体现， 所以用add）
			}
			
			/**
			 * 7、 计算 余转差异
			 */
			{
				// 会员的
				StringBuffer querySQL_6 = 
						new StringBuffer("select ")
								.append(" sum( nvl(kib.y_ka_ss,0) - nvl(kib.ka_ss,0) ) ")
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" where ki.dr=0 and kib.dr=0 ")
								.append(" and kib.xmdl = '余转' ")
								/**
								 * HK 2019年3月15日13:58:08
								 * 增加了 不计算余转的卡
								 */
//								.append(" and kib.ka_code not   in ('0203005888','0302101237','0103001500') ")
//								.append(" and kib.y_ka_code not in ('0203005888','0302101237','0103001500') ")
								.append(" and kib.ka_code not   in ('0203005888','0302101237','0103001500','0203005666','XSZSZZK000002','XSZSZZK000001') ")
								.append(" and kib.y_ka_code not in ('0203005888','0302101237','0103001500','0203005666','XSZSZZK000002','XSZSZZK000001') ")
								/***END***/
								.append(" and ki.pk_org = '").append(pk_org).append("' ")
								.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
				;
				
				ArrayList list_6 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_6.toString(), new ArrayListProcessor());
				
				if( list_6.size()>0 )
				{
					Object[] obj = (Object[])list_6.get(0);
					
					resultBVOs[resultBVOs.length-1].setVbdef04( PuPubVO.getUFDouble_NullAsZero(obj[0]).toString() );
				}
				
				// 分区的
				
				// 合计 = 会员 + 分区
				resultBVOs[resultBVOs.length-1].setKajie_kbl( 
						 PuPubVO.getUFDouble_NullAsZero( resultBVOs[resultBVOs.length-1].getVbdef04() )
					.add(PuPubVO.getUFDouble_NullAsZero( resultBVOs[resultBVOs.length-1].getVbdef02() ))
				);
				
			}
			
			/**
			 * 8、查询 验证数据
			 * 取  当天、当公司的  营业日报的数据
			 */
			{
				StringBuffer querySQL_8 = 
						new StringBuffer("select sum(yb.jine) ")
								.append(" from hk_srgk_hg_yyribao y ")
								.append(" inner join hk_srgk_hg_yyribao_b yb on y.pk_hk_srgk_hg_yyribao = yb.pk_hk_srgk_hg_yyribao ")
								.append(" where y.dr=0 and yb.dr=0 ")
								.append(" and y.pk_org='").append(pk_org).append("' and substr(y.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
								.append(" and y.ts in (select max(ts) from hk_srgk_hg_yyribao  where dr=0 and pk_org='").append(pk_org).append("' and substr(dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' group by substr(dbilldate,0,10) ) ")
								.append(" and yb.jzfs_code in ( ")
								.append("  '0301' ")	// 会员卡销售
								.append(" ,'0302' ")	// 次卡销售
								.append(" ,'050401' ")	// 国际会馆卡
								.append(" ,'050402' ")	// 贵宾楼卡
//								.append(" ,'050403' ")	// 上地会馆卡
								.append(" ,'050404' ")	// 西山温泉卡
								.append(" ,'050405' ")	// 朗丽兹卡
								.append(" ,'050406' ")	// 康福瑞学院路卡
								.append(" ,'050407' ")	// 康福瑞西山卡
								.append(" ) ")
				;
				
				ArrayList list_8 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_8.toString(), new ArrayListProcessor());
				
				if( list_8.size()>0 )
				{
					Object[] obj = (Object[])list_8.get(0);
					
					resultHVO.setVdef02( obj[0]==null?"":obj[0].toString() );
				}
				
			}
			
			resultHVO.setIbillstatus(-1);
			resultHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
			resultHVO.setPk_org( pk_org );	// pk_org 
			resultHVO.setVdef01( ks_date.equals(js_date)?ks_date:(ks_date +" 至 "+js_date) );	// 业务日期
			
			KazhangwuBillVO resultBillVO = new KazhangwuBillVO();
			resultBillVO.setParentVO(resultHVO);
			resultBillVO.setChildrenVO(resultBVOs);
			// 将结果集 放到界面
			getEditor().getBillCardPanel().setBillData(
					getEditor().getBillCardPanel().getBillData());
			getEditor().getModel().initModel(new KazhangwuBillVO[]{resultBillVO});
		}
	}
	
	
	QueryConditionDLGDelegator dlgDelegator;
	private QueryConditionDLGDelegator getDlgDelegator() {
		if (dlgDelegator == null) {
			TemplateInfo tempinfo = new TemplateInfo();
			tempinfo.setPk_Org(this.getModel().getContext().getPk_group());
			tempinfo.setUserid(this.getModel().getContext().getPk_loginUser());
			tempinfo.setFunNode("HKJ20606");
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
