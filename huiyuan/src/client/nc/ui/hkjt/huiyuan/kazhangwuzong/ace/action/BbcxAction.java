package nc.ui.hkjt.huiyuan.kazhangwuzong.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBVO;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongHVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.pub.BusinessException;
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
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			
			
			// 解析出 开始、结束 日期
			if(hzdate==null || "".equals(hzdate))
				hzdate = "1990-01-01,2990-12-01";
			
			String[] chaxun_date = hzdate.split(",");
			
			String ks_date_str = chaxun_date[0];
			String js_date_str = chaxun_date[0];
			if(chaxun_date.length==2)
			{
				js_date_str = chaxun_date[1];
			}
			
			/**
			 * 首先　根据查询日期　　构造出　所显示的行，一天一行。
			 */
			UFDate ks_date = PuPubVO.getUFDate(ks_date_str);
			UFDate js_date = PuPubVO.getUFDate(js_date_str);
			int days = js_date.getDaysAfter(ks_date)+1;
			
			if( days>31 )
			{// 效率控制
				throw new BusinessException("由于效率的原因，最多只能查一个月的数据。");
			}
			
			KazhangwuzongBVO[] resultBVOs = new KazhangwuzongBVO[days];
			
			for(int i=0;i<days;i++)
			{
				String date = ks_date.getDateAfter(i).toString().substring(0, 10);
				
				resultBVOs[i] = new KazhangwuzongBVO();
				resultBVOs[i].setRq(date);
				
				/**
				 * 处理方式  改变
				 * 通过   查出可用余额 - 作废卡 - 三级大卡 - 一级大卡   来推算出  会员卡可用余额
				 */
				
				/**
				 * 1、 取 会员卡  总余额， 与   余额对比表 的算法一致
				 */
				{
					StringBuffer querySQL_1 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
										// 期初
										.append(" select ")
										.append(" sum(ka.qc_ye) je")
										.append(" from hk_huiyuan_kadangan ka ")
										.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
										.append(" where nvl(ka.dr,0)=0 ")
										.append(" and ka.kastatus not in ( '休眠','激活') ")
										
									.append(" union all ")
										
										.append(" select ")
										.append(" sum( nvl(kainfo.chongzhi,0) + nvl(kainfo.xiaofei,0) + nvl(kainfo.zhuanru,0) - nvl(kainfo.zhuanchu,0) ) je ")
										.append(" from ")
										.append("( ")
											.append(" select ")
											.append(" sum( case when kib.xmdl='充值' then kib.ka_je else 0 end ) chongzhi")
											.append(",sum( case when kib.xmdl='消费' then kib.ka_je else 0 end ) xiaofei")
											.append(",sum( case when kib.xmdl='余转' then kib.ka_je else 0 end ) zhuanru")
											.append(",0 zhuanchu ")
											.append(" from hk_huiyuan_kainfo ki ")
											.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
											.append(" left join hk_huiyuan_kaxing kx on kib.kaxing_pk = kx.pk_hk_huiyuan_kaxing ")
											.append(" where nvl(ki.dr,0)=0 and nvl(kib.dr,0)=0 ")
											.append(" and nvl(kib.zdh,'null')!='null' ")	// 不取手工作废的数据
											.append(" and substr(ki.dbilldate,0,10) <= '"+date+"' ")
										.append(" union all ")
											.append(" select ")
											.append(" 0 ")
											.append(",0 ")
											.append(",0 ")
											.append(",sum( case when kib.xmdl='余转' then kib.ka_je else 0 end ) zhuanchu ")
											.append(" from hk_huiyuan_kainfo ki ")
											.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
											.append(" left join hk_huiyuan_kaxing kx on kib.y_kaxing_pk = kx.pk_hk_huiyuan_kaxing ")
											.append(" where nvl(ki.dr,0)=0 and nvl(kib.dr,0)=0 ")
											.append(" and substr(ki.dbilldate,0,10) <= '"+date+"' ")
										.append(" ) kainfo ")
									.append(" ) ") 
										
					;
					
					ArrayList list_1 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
					
					if(list_1.size()>0)
					{
						Object[] obj = (Object[])list_1.get(0);
						resultBVOs[i].setYue( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
									
				}
				
				
//				/** 
//				 * 1、正常卡　可用余额
//				 */
//				{
//					StringBuffer whereSQL_1 = // 正常卡的固定sql
//							new StringBuffer("")
//									.append(" and ka.kastatus not in ('休眠','激活') ")		// 不取 休眠、激活
//									.append(" and ka.ka_code not in ('0203005888','0302101237') ") //不取 三级大卡、一级大卡
////									.append(" and (( ka.kastatus='作废' and substr(ka.vdef03,0,10)>'"+date+"' ) or ka.kastatus != '作废') ")//不取 作废卡，要考虑作废日期
//					;
//					
//					StringBuffer querySQL_1 = 
//							new StringBuffer("select sum(je) ")
//									.append(" from ( ")
//									// 期初
////									.append(" select sum(ka.qc_ye) je ")
////									.append(" from hk_huiyuan_kadangan ka ")
////									.append(" where ka.dr=0 ")
////									.append(whereSQL_1)
//									
//									.append(" select sum(qc.je) je ")
//									.append(" from ( ")
//										.append(" select max(ka.qc_ye) je,ka.pk_hk_huiyuan_kadangan ")
//										.append(" from hk_huiyuan_kadangan ka ")
//										.append(" left join ( ")
//											.append(" select ccz.pk_hk_huiyuan_kadangan,max(ccz.ts) ts ")
//											.append(" from hk_huiyuan_kadangan_cz ccz ")
//											.append(" where ccz.dr=0 ")
//											.append(" and substr(ccz.vbdef03,0,10)<='"+date+"' ")
//											.append(" group by ccz.pk_hk_huiyuan_kadangan ")
//										.append(" ) czts on ka.pk_hk_huiyuan_kadangan = czts.pk_hk_huiyuan_kadangan ")
//										.append(" left join hk_huiyuan_kadangan_cz cz on (cz.pk_hk_huiyuan_kadangan = czts.pk_hk_huiyuan_kadangan and cz.ts=czts.ts) ")
//										.append(" where ka.dr=0 ")
//										.append(" and ka.kastatus not in ('休眠','激活') ")
//										.append(" and ka.ka_code not in ('0203005888','0302101237') ")
//										.append(" and nvl(cz.vbdef02,'~') != '作废卡' ")
//										.append(" group by ka.pk_hk_huiyuan_kadangan ")
//									.append(" ) qc ") 
//									// 充值
//									.append(" union all ")
//									.append(" select sum(cz.cz_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and nvl(cz.vbdef02,'~') != '作废卡' ) ")
//									.append(" where ka.dr=0 and cz.dr=0 ")
//									.append(whereSQL_1)
//									.append(" and substr(cz.vbdef03,0,10)<='"+date+"' ")
//									// 余转
//									.append(" union all ")
//									.append(" select sum(nvl(yz.zr_je,0) - nvl(yz.zc_je,0)) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_yz yz on ka.pk_hk_huiyuan_kadangan = yz.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and yz.dr=0 ")
//									.append(whereSQL_1)
//									.append(" and substr(yz.vbdef03,0,10)<='"+date+"' ")
//									// 消费
//									.append(" union all ")
//									.append(" select sum(xf.xf_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_xf xf on ka.pk_hk_huiyuan_kadangan = xf.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and xf.dr=0 ")
//									.append(whereSQL_1)
//									.append(" and substr(xf.vbdef03,0,10)<='"+date+"' ")
//									
//									// 余额表 没有取 手工调整（作废卡）的数据， 所以要减掉 手工调整的数据
//									// 手工数据 的标志是   账单号  为空
//									.append(" union all ")
//									.append(" select sum(0-nvl(cz.cz_je,0)) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and nvl(cz.zdh,'~') = '~' ) ")
//									.append(" where ka.dr=0 and cz.dr=0 ")
//									.append(whereSQL_1)
//									.append(" and substr(cz.vbdef03,0,10)<='"+date+"' ")
//									.append(" and substr(cz.vbdef03,0,10)>='2015-10-28' ")
//									
//									.append(" ) ")
//									
//									
//					;
//					
//					ArrayList list_1 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
//					
//					if(list_1.size()>0)
//					{
//						Object[] obj = (Object[])list_1.get(0);
//						resultBVOs[i].setKayue( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
//					}
//					
//				}
				
				/** 
				 * 2、作废卡余额
				 *    作废卡  取 表体充值页签的  作废卡标识， 不能取表头的。
				 *    有可能  两笔充值，其中一条是作废，另一条是充值。 最终这个卡  是 正常状态。 只是作废那一笔  做报表统计。
				 *    作废卡 不能再使用， 所以不用考虑  数据追溯。
				 */
				{
					StringBuffer whereSQL_2 = // 作废卡的固定sql
							new StringBuffer("")
									.append(" and ka.kastatus not in ('休眠','激活') ")		// 不取 休眠、激活
									.append(" and ka.ka_code not in ('0203005888') ") 		// 不取 三级大卡
									.append(" and ka.ka_code not between '0302101237' and '0302101326'  ")	// 不取 一级大卡
//									.append(" and ( ka.kastatus='作废' and substr(ka.vdef03,0,10)<='"+date+"' ) ")//作废卡，要考虑作废日期
					;
					
					StringBuffer querySQL_2 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
									// 期初  （期初无作废）
//									.append(" select sum(ka.qc_ye) je ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" where ka.dr=0 ")
//									.append(whereSQL_2)
//									// 充值
//									.append(" union all ")
									.append(" select sum(nvl(cz.cz_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and cz.vbdef02 = '作废卡' and substr(cz.vbdef03,0,10)<='"+date+"' ) ")
									.append(" where ka.dr=0 and cz.dr=0 ")
									.append(whereSQL_2)
									// 作废余转（只关联 作废余转信息  不用关联 充值信息了）
									.append(" union all ")
									.append(" select sum(0-nvl(zf.zc_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and cz.vbdef02 = '作废卡' and substr(cz.vbdef03,0,10)<='"+date+"' ) ")
									.append(" inner join hk_huiyuan_kadangan_zf zf on ka.pk_hk_huiyuan_kadangan = zf.pk_hk_huiyuan_kadangan ")
									.append(" where ka.dr=0 and zf.dr=0 ")
									.append(whereSQL_2)
									.append(" and substr(zf.vbdef03,0,10)<='"+date+"' ")
									// 消费
//									.append(" union all ")
//									.append(" select sum(xf.xf_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_xf xf on ka.pk_hk_huiyuan_kadangan = xf.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and xf.dr=0 ")
//									.append(whereSQL_2)
//									.append(" and substr(xf.vbdef03,0,10)<='"+date+"' ")
									.append(" ) ")
					;
					
					ArrayList list_2 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
					
					if(list_2.size()>0)
					{
						Object[] obj = (Object[])list_2.get(0);
						resultBVOs[i].setZuofei( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				/** 
				 * 3、三级大卡卡余额 0203005888
				 *    三级大卡  应该只有余转， 没有充值 和 消费
				 */
				{
					StringBuffer whereSQL_3 = // 三级大卡的固定sql
							new StringBuffer("")
									.append(" and ka.ka_code = '0203005888' ") //只取 三级大卡
					;
					
					StringBuffer querySQL_3 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
									// 期初
									.append(" select sum(ka.qc_ye) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" where ka.dr=0 ")
									.append(whereSQL_3)
									// 充值
//									.append(" union all ")
//									.append(" select sum(cz.cz_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and cz.dr=0 ")
//									.append(whereSQL_3)
//									.append(" and substr(cz.vbdef03,0,10)<='"+date+"' ")
									// 余转
									.append(" union all ")
									.append(" select sum(nvl(yz.zr_je,0) - nvl(yz.zc_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" inner join hk_huiyuan_kadangan_yz yz on ka.pk_hk_huiyuan_kadangan = yz.pk_hk_huiyuan_kadangan ")
									.append(" where ka.dr=0 and yz.dr=0 ")
									.append(whereSQL_3)
									.append(" and substr(yz.vbdef03,0,10)<='"+date+"' ")
									// 消费
//									.append(" union all ")
//									.append(" select sum(xf.xf_je) ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_xf xf on ka.pk_hk_huiyuan_kadangan = xf.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and xf.dr=0 ")
//									.append(whereSQL_3)
//									.append(" and substr(xf.vbdef03,0,10)<='"+date+"' ")
									.append(" ) ")
					;
					
					ArrayList list_3 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_3.toString(), new ArrayListProcessor());
					
					if(list_3.size()>0)
					{
						Object[] obj = (Object[])list_3.get(0);
						resultBVOs[i].setDaka3( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				/** 
				 * 4、一级大卡卡余额 0302101237
				 *   一级大卡 的 删卡  按照 作废的 处理方式。
				 *   一般是  一级大卡 过500万  就做删卡 操作。
				 */
				{
					StringBuffer whereSQL_4 = // 一级大卡的固定sql
							new StringBuffer("")
									.append(" and ka.ka_code between '0302101237' and '0302101326'  ") //只取 一级大卡 （1237 到 1326）
					;
					
					StringBuffer querySQL_4 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
									// 期初
									.append(" select sum(ka.qc_ye) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" where ka.dr=0 ")
									.append(whereSQL_4)
									// 余转
									.append(" union all ")
									.append(" select sum(nvl(yz.zr_je,0) - nvl(yz.zc_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
									.append(" inner join hk_huiyuan_kadangan_yz yz on ka.pk_hk_huiyuan_kadangan = yz.pk_hk_huiyuan_kadangan ")
									.append(" where ka.dr=0 and yz.dr=0 ")
									.append(whereSQL_4)
									.append(" and substr(yz.vbdef03,0,10)<='"+date+"' ")
									// 作废
									.append(" union all ")
									.append(" select sum(0-nvl(zf.zc_je,0)) je ")
									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_cz cz on ( ka.pk_hk_huiyuan_kadangan = cz.pk_hk_huiyuan_kadangan and cz.vbdef02 = '作废卡' and substr(cz.vbdef03,0,10)<='"+date+"' ) ")
									.append(" inner join hk_huiyuan_kadangan_zf zf on ka.pk_hk_huiyuan_kadangan = zf.pk_hk_huiyuan_kadangan ")
									.append(" where ka.dr=0 and zf.dr=0 ")
									.append(whereSQL_4)
									.append(" and substr(zf.vbdef03,0,10)<='"+date+"' ")
									
									.append(" ) ")
									
					;
					
					ArrayList list_4 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_4.toString(), new ArrayListProcessor());
					
					if(list_4.size()>0)
					{
						Object[] obj = (Object[])list_4.get(0);
						resultBVOs[i].setDaka1( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				/**
				 * 5、休眠大卡 余额  （取  系统 休眠状态的卡的余额）
				 */
				{
					
//					resultBVOs[i].setYue_xm( UFDouble.ZERO_DBL );
					
//					StringBuffer whereSQL_5 = // 休眠大卡的固定sql
//							new StringBuffer("")
//									.append(" and ka.ka_code = '0103001500' ") //只取 休眠大卡
//					;
//					
//					StringBuffer querySQL_5 = 
//							new StringBuffer("select sum(je) ")
//									.append(" from ( ")
//									// 期初
//									.append(" select sum(ka.qc_ye) je ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" where ka.dr=0 ")
//									.append(whereSQL_5)
//									// 余转
//									.append(" union all ")
//									.append(" select sum(nvl(yz.zr_je,0) - nvl(yz.zc_je,0)) je ")
//									.append(" from hk_huiyuan_kadangan ka ")
//									.append(" inner join hk_huiyuan_kadangan_yz yz on ka.pk_hk_huiyuan_kadangan = yz.pk_hk_huiyuan_kadangan ")
//									.append(" where ka.dr=0 and yz.dr=0 ")
//									.append(whereSQL_5)
//									.append(" and substr(yz.vbdef03,0,10)<='"+date+"' ")
//									.append(" ) ")
//					;
//					
//					ArrayList list_5 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_5.toString(), new ArrayListProcessor());
//					
//					if(list_5.size()>0)
//					{
//						Object[] obj = (Object[])list_5.get(0);
//						resultBVOs[i].setYue_xm( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
//					}
					
					
//					StringBuffer whereSQL_5 = // 休眠 固定sql
//							new StringBuffer("")
//									.append(" and ka.kastatus in ('休眠','激活') ")		// 不取 休眠、激活
////									.append(" and ka.ka_code not in ('0203005888','0302101237') ") //不取 三级大卡、一级大卡
//									.append(" and ( ka.kastatus='激活' and substr(ka.vdef03,0,10)<='"+date+"' ) ")//激活卡，要考虑激活日期
//					;
					
					StringBuffer querySQL_5 = 
							new StringBuffer("select sum(je) ")
									.append(" from ( ")
										.append(" select sum(ka.qc_ye) je ")
										.append(" from hk_huiyuan_kadangan ka ")
										.append(" where ka.dr=0 ")
										.append(" and ka.kastatus in ('休眠','激活') ")
										.append(" and ( ka.kastatus = '休眠' or ( ka.kastatus = '激活' and substr(ka.vdef03,0,10)>'"+date+"'  ) ) ")
									.append(" ) ")
					;
					
					ArrayList list_5 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_5.toString(), new ArrayListProcessor());
					
					if(list_5.size()>0)
					{
						Object[] obj = (Object[])list_5.get(0);
						resultBVOs[i].setYue_xm( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				/**
				 * 6、金贵余额
				 */
				{
					StringBuffer querySQL_6 = 
						new StringBuffer("select ")
								.append(" sum(jg.jg_yue) yue_jg ")
								.append(" from hk_huiyuan_kadangan ka ")
								.append(" inner join hk_huiyuan_kadangan_jg jg on ka.pk_hk_huiyuan_kadangan = jg.pk_hk_huiyuan_kadangan ")
								.append(" inner join  ")
								.append(" ( ")
								.append(" select ")
								.append("  pk_hk_huiyuan_kadangan ")
								.append(" ,nvl( max( vbdef01 ),'null' )  vbdef01 ")
								.append(" from hk_huiyuan_kadangan_jg jg ")
								.append(" where nvl(dr,0)=0 ")
								.append(" and nvl(vbdef01,'1990-01-01 00:00:00')<='"+date+" 23:59:59' ")
								.append(" group by jg.pk_hk_huiyuan_kadangan ")
								.append(" ) jgsj on ka.pk_hk_huiyuan_kadangan = jgsj.pk_hk_huiyuan_kadangan ")
								.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
								.append(" where nvl(ka.dr,0)=0 and nvl(jg.dr,0)=0 ")
								.append(" and nvl(jg.vbdef01,'null') = nvl(jgsj.vbdef01,'null') ")
					;
					
					ArrayList list_6 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_6.toString(), new ArrayListProcessor());
					
					if(list_6.size()>0)
					{
						Object[] obj = (Object[])list_6.get(0);
						resultBVOs[i].setYue_jg( PuPubVO.getUFDouble_NullAsZero(obj[0]) );
					}
					
				}
				
				/**
				 * 7、相关 计算
				 */
//				// 总余额 = 会员可用余额 + 作废卡 + 三级 + 一级
//				resultBVOs[i].setYue( 
//						  resultBVOs[i].getKayue()
//					.add( resultBVOs[i].getZuofei() )
//					.add( resultBVOs[i].getDaka3() )
//					.add( resultBVOs[i].getDaka1() )
//				);
				
				// 会员可用余额  = 总余额  - 作废卡 - 三级  - 一级 
				resultBVOs[i].setKayue( 
						  resultBVOs[i].getYue()
					.sub( resultBVOs[i].getZuofei() )
					.sub( resultBVOs[i].getDaka3() )
					.sub( resultBVOs[i].getDaka1() )
						
				);
				
				// 应付总余额 = 总余额 + 休眠大卡
				resultBVOs[i].setYue_yf( 
						  resultBVOs[i].getYue()
					.add( resultBVOs[i].getYue_xm() )
				);
				
				// 验证 = 总余额 - 金贵余额
				resultBVOs[i].setYanzheng( 
						  resultBVOs[i].getYue()
					.sub( resultBVOs[i].getYue_jg() )
				);
				
			}
						
			
			KazhangwuzongHVO   resultHVO  = new KazhangwuzongHVO();
			resultHVO.setIbillstatus(-1);
			resultHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
			resultHVO.setPk_org("0001NC10000000004AXZ");	// pk_org 正德pk
			resultHVO.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
			resultHVO.setVdef01( ks_date_str.equals(js_date_str)?ks_date_str:(ks_date_str +" 至 "+js_date_str) );	// 业务日期
			
			KazhangwuzongBillVO resultBillVO = new KazhangwuzongBillVO();
			resultBillVO.setParentVO(resultHVO);
			resultBillVO.setChildrenVO(resultBVOs);
			// 将结果集 放到界面
			getEditor().getBillCardPanel().setBillData(
					getEditor().getBillCardPanel().getBillData());
			getEditor().getModel().initModel(new KazhangwuzongBillVO[]{resultBillVO});
		}
	}
	
	
	QueryConditionDLGDelegator dlgDelegator;
	private QueryConditionDLGDelegator getDlgDelegator() {
		if (dlgDelegator == null) {
			TemplateInfo tempinfo = new TemplateInfo();
			tempinfo.setPk_Org(this.getModel().getContext().getPk_group());
			tempinfo.setUserid(this.getModel().getContext().getPk_loginUser());
			tempinfo.setFunNode("HKJ20607");
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
