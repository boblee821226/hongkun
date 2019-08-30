package nc.ui.hkjt.huiyuan.cikainfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKCZVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class CheckAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public CheckAction() {
		setBtnName("取数");
		setCode("checkAction");
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
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		
		int rowCount = billModel.getRowCount();
		
		HashMap<String,KadanganCKCZVO> CKCZ_MAP_key = new HashMap<String,KadanganCKCZVO>();	// 卡缓存(key=卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期)
		HashMap<String,KadanganCKCZVO> CKCZ_MAP_sh  = new HashMap<String,KadanganCKCZVO>();	// 卡缓存(key=次卡水号)
		HashMap<String,KadanganCKCZVO> CKZC_MAP_key = new HashMap<String,KadanganCKCZVO>();	// 转出卡缓存(key=卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期)
		
		for(int i=0;i<rowCount;i++)
		{// 将 充值信息 放置到 缓存（用于 消费的取数）
			
			if(  "充值".equals( billModel.getValueAt(i, "xmdl") )
			 && !"转入".equals( billModel.getValueAt(i, "czlx") )	// 转入的数据 并非充值，稍后处理（HK 2019年1月10日17:10:23）
			  )
			{
			
				if(  billModel.getValueAt(i, "kabili")==null
				  || billModel.getValueAt(i, "danjia")==null	
				)
				{
					throw new BusinessException("第"+(1+1)+"行，充值的 卡比例 和 单价 不能为空。");
				}
				
				String ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "ka_code") );
				String itemname = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "itemname") );
				String startdata = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "startdata") );
				
				String cksh = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "timescardwaternum") );	// 次卡水号
				String key = ka_code+"@@@@"+itemname+"@@@@"+startdata;	// key = 卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期；
				
				KadanganCKCZVO ckczvo = new KadanganCKCZVO();
				ckczvo.setKabili( PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "kabili") ) );	// 卡比例
				ckczvo.setPrice( PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "danjia") ) );		// 单价
				
				CKCZ_MAP_key.put(key, ckczvo);
				CKCZ_MAP_sh.put(cksh, ckczvo);
			}
		}
		
		for(int i=0;i<rowCount;i++)
		{// 充负数  和  消费的   要根据档案  来赋值  卡比例  和  单价， 并且计算 金额。
			
			String ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "ka_code") );
			String itemname = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "itemname") );
			String startdata = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "startdata") );
			
			String cksh = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "timescardwaternum") );	// 次卡水号
			String key = ka_code+"@@@@"+itemname+"@@@@"+startdata;	// key = 卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期；
			
			if(  "充负数".equals( billModel.getValueAt(i, "xmdl") ) 
			  || "消费".equals( billModel.getValueAt(i, "xmdl") )
			)	// 充负数的 要按key  去匹配。
			{
				
				if( cksh!=null 
				 && "消费".equals( billModel.getValueAt(i, "xmdl") )
				)
				{// 如果次卡水号 不为空，则就按 次卡水号 去查询  （充负数的 要按key 去匹配）
					
					if( CKCZ_MAP_sh.containsKey(cksh) )
					{// 如果在缓存里
					
						KadanganCKCZVO ckczvo = CKCZ_MAP_sh.get(cksh);
						if( ckczvo!=null )
						{
							billModel.setValueAt(ckczvo.getKabili(), i, "kabili");
							billModel.setValueAt(ckczvo.getPrice(), i, "danjia");
							billModel.setValueAt(
											   ckczvo.getKabili()
									.multiply( ckczvo.getPrice() )
									.multiply( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i, "shuliang")) )
									.setScale( 2, UFDouble.ROUND_HALF_UP )
									, i,"jine");
						}
					}
					else
					{// 不在缓存里，需要查询数据库 （查完之后 放到缓存里）
						StringBuffer querySQL = 
								new StringBuffer("select")
										.append(" ckcz.kabili ")	// 卡比例
										.append(",ckcz.price ")		// 单价
//										.append(",ckcz.timescardwaternum ") // 次卡水号
										.append(",ka.ka_code ")
										.append(",ckcz.itemname ")
										.append(",ckcz.startdata ")
										.append(" from hk_huiyuan_kadangan_ckcz ckcz ")
										.append(" inner join hk_huiyuan_kadangan ka on ckcz.pk_hk_huiyuan_kadangan = ka.pk_hk_huiyuan_kadangan ")
										.append(" where ckcz.dr=0 and ka.dr=0 ")
										.append(" and ckcz.timescardwaternum = '").append(cksh).append("' ")
						;
						
						ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
						
						if( list.size()>0 )
						{
							Object[] obj = (Object[])list.get(0);
							
							KadanganCKCZVO ckczvo = new KadanganCKCZVO();
							ckczvo.setKabili( PuPubVO.getUFDouble_NullAsZero(obj[0]) );	// 卡比例
							ckczvo.setPrice( PuPubVO.getUFDouble_NullAsZero(obj[1]) );	// 单价
							String key_temp = obj[2]+"@@@@"+obj[3]+"@@@@"+obj[4];		// key = 卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期；
							
							CKCZ_MAP_key.put(key_temp, ckczvo);
							CKCZ_MAP_sh.put(cksh, ckczvo);
							
							billModel.setValueAt(ckczvo.getKabili(), i, "kabili");
							billModel.setValueAt(ckczvo.getPrice(), i, "danjia");
							billModel.setValueAt(
											   ckczvo.getKabili()
									.multiply( ckczvo.getPrice() )
									.multiply( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i, "shuliang")) )
									.setScale( 2, UFDouble.ROUND_HALF_UP )
									, i,"jine");
							
						}else
						{
							CKCZ_MAP_key.put(key, null);
							CKCZ_MAP_sh.put(cksh, null);
						}
						
					}
					
				}
				else
				{// 如果次卡水号 为空，则就按  key 去查询  （充负数的要按key去匹配）
					
					if( CKCZ_MAP_key.containsKey(key) )
					{// 如果在缓存里
					
						KadanganCKCZVO ckczvo = CKCZ_MAP_key.get(key);
						if( ckczvo!=null )
						{
							billModel.setValueAt(ckczvo.getKabili(), i, "kabili");
							billModel.setValueAt(ckczvo.getPrice(), i, "danjia");
							billModel.setValueAt(
											   ckczvo.getKabili()
									.multiply( ckczvo.getPrice() )
									.multiply( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i, "shuliang")) )
									.setScale( 2, UFDouble.ROUND_HALF_UP )
									, i,"jine");
						}
					}
					else
					{// 不在缓存里，需要查询数据库 （查完之后 放到缓存里）
						StringBuffer querySQL = 
								new StringBuffer("select")
										.append(" ckcz.kabili ")	// 卡比例
										.append(",ckcz.price ")		// 单价
										.append(",ckcz.timescardwaternum ") // 次卡水号
										.append(" from hk_huiyuan_kadangan_ckcz ckcz ")
										.append(" inner join hk_huiyuan_kadangan ka on ckcz.pk_hk_huiyuan_kadangan = ka.pk_hk_huiyuan_kadangan ")
										.append(" where ckcz.dr=0 and ka.dr=0 ")
										.append(" and ka.ka_code   	 = '").append(ka_code).append("' ")
										.append(" and ckcz.itemname  = '").append(itemname).append("' ")
										.append(" and ckcz.startdata = '").append(startdata).append("' ")
						;
						
						ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
						
						if( list.size()>0 )
						{
							Object[] obj = (Object[])list.get(0);
							
							KadanganCKCZVO ckczvo = new KadanganCKCZVO();
							ckczvo.setKabili( PuPubVO.getUFDouble_NullAsZero(obj[0]) );	// 卡比例
							ckczvo.setPrice( PuPubVO.getUFDouble_NullAsZero(obj[1]) );	// 单价
							String cksh_temp = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
							ckczvo.setTimescardwaternum( PuPubVO.getString_TrimZeroLenAsNull(obj[2]) );	// 次卡水号
							
							CKCZ_MAP_key.put(key, ckczvo);
							CKCZ_MAP_sh.put(cksh_temp, ckczvo);
							
							billModel.setValueAt(ckczvo.getKabili(), i, "kabili");
							billModel.setValueAt(ckczvo.getPrice(), i, "danjia");
							billModel.setValueAt(
											   ckczvo.getKabili()
									.multiply( ckczvo.getPrice() )
									.multiply( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i, "shuliang")) )
									.setScale( 2, UFDouble.ROUND_HALF_UP )
									, i,"jine");
							
						}else
						{
							CKCZ_MAP_key.put(key, null);
							CKCZ_MAP_sh.put(cksh, null);
						}
					}
				}
			}
			
			/**
			 * 如果项目类型 为 转出，则将数据放到缓存里， 以备后面 转入 所用。
			 * HK 2019年1月10日17:17:29
			 */
			if( "转出".equals( billModel.getValueAt(i, "xmlx") ) )
			{
				UFDouble kabili = PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "kabili") );
				UFDouble danjia = PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "danjia") );
				
				KadanganCKCZVO ckczvo = new KadanganCKCZVO();
				ckczvo.setKabili( kabili );	// 卡比例
				ckczvo.setPrice( danjia );	// 单价
				
				CKZC_MAP_key.put(key, ckczvo);
				
			}
			
		}
		
		/**
		 * 充值类型 为 转入
		 * 转入的处理，依据转出的来赋值
		 * HK 2019年1月10日17:28:38
		 */
		for(int i=0;i<rowCount;i++)
		{
			
			if( "转入".equals( billModel.getValueAt(i, "czlx") ) )
			{
				String y_ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "y_ka_code") );
				String itemname = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "itemname") );
				String startdata = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "startdata") );
				
				String key = y_ka_code+"@@@@"+itemname+"@@@@"+startdata;	// key = 源卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期；
				
				KadanganCKCZVO ckczvo = CKZC_MAP_key.get(key);
				
				if(ckczvo!=null)
				{
					billModel.setValueAt(ckczvo.getKabili(), i, "kabili");
					billModel.setValueAt(ckczvo.getPrice(), i, "danjia");
					billModel.setValueAt(
									   ckczvo.getKabili()
							.multiply( ckczvo.getPrice() )
							.multiply( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i, "shuliang")) )
							.setScale( 2, UFDouble.ROUND_HALF_UP )
							, i,"jine");
				}
				
			}
			
		}
		
	}
	
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}

}
