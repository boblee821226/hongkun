package nc.ui.hkjt.huiyuan.cikainfo.ace.handler;

import java.util.ArrayList;
import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKCZVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class CkinfoCardBodyAfterEditEvent implements IAppEventHandler<CardBodyAfterEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		if( "y_ka_code".equals( e.getKey() ) )	// 源卡号
		{
			try {
				
				BillModel billModel = this.getCardForm().getBillCardPanel().getBillModel();
				int row = e.getRow();	// 所在行
				String y_ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(row, "y_ka_code") );
				
				if( PuPubVO.getString_TrimZeroLenAsNull(y_ka_code)==null )
				{
					billModel.setValueAt(null, row, "xmlx");
					return;
				}
				
				billModel.setValueAt("转入", row, "xmlx");
				
				int rowCount = billModel.getRowCount();
				
				HashMap<String,KadanganCKCZVO> CKCZ_MAP_key = new HashMap<String,KadanganCKCZVO>();	// 卡缓存(key=卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期)
				
				for(int i=0;i<rowCount;i++)
				{// 将 充值信息 放置到 缓存（用于 消费的取数）
					
					if( "充值".equals( billModel.getValueAt(i, "xmdl") ) )
					{
					
						String ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "ka_code") );
						String itemname = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "itemname") );
						String startdata = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "startdata") );
						
						String key = ka_code+"@@@@"+itemname+"@@@@"+startdata;	// key = 卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期；
						
						KadanganCKCZVO ckczvo = new KadanganCKCZVO();
						ckczvo.setKabili( PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "kabili") ) );	// 卡比例
						ckczvo.setPrice( PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "danjia") ) );		// 单价
						
						CKCZ_MAP_key.put(key, ckczvo);
					}
				}
				
				{
					IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
					
					String itemname = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(row, "itemname") );
					String startdata = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(row, "startdata") ); 
					
					String key = y_ka_code+"@@@@"+itemname+"@@@@"+startdata;	// key = 源卡号+"@@@@"+项目名称+"@@@@"+次卡开始日期；
					
					if( CKCZ_MAP_key.containsKey(key) )
					{// 如果在缓存里
					
						KadanganCKCZVO ckczvo = CKCZ_MAP_key.get(key);
						if( ckczvo!=null )
						{
							billModel.setValueAt(ckczvo.getKabili(), row, "kabili");
							billModel.setValueAt(ckczvo.getPrice(), row, "danjia");
							billModel.setValueAt(
											   ckczvo.getKabili()
									.multiply( ckczvo.getPrice() )
									.multiply( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(row, "shuliang")) )
									.setScale( 2, UFDouble.ROUND_HALF_UP )
									, row,"jine");
						}
						else
						{
							billModel.setValueAt(null, row, "kabili");
							billModel.setValueAt(null, row, "danjia");
							billModel.setValueAt(null, row,"jine");
						}
					}
					else
					{// 不在缓存里，需要查询数据库 （查完之后 放到缓存里）
						StringBuffer querySQL = 
								new StringBuffer("select")
										.append(" ckcz.kabili ")	// 卡比例
										.append(",ckcz.price ")		// 单价
										.append(" from hk_huiyuan_kadangan_ckcz ckcz ")
										.append(" inner join hk_huiyuan_kadangan ka on ckcz.pk_hk_huiyuan_kadangan = ka.pk_hk_huiyuan_kadangan ")
										.append(" where ckcz.dr=0 and ka.dr=0 ")
										.append(" and ka.ka_code   	 = '").append(y_ka_code).append("' ")
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
							
							CKCZ_MAP_key.put(key, ckczvo);
							
							billModel.setValueAt(ckczvo.getKabili(), row, "kabili");
							billModel.setValueAt(ckczvo.getPrice(), row, "danjia");
							billModel.setValueAt(
											   ckczvo.getKabili()
									.multiply( ckczvo.getPrice() )
									.multiply( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(row, "shuliang")) )
									.setScale( 2, UFDouble.ROUND_HALF_UP )
									, row,"jine");
							
						}else
						{
							CKCZ_MAP_key.put(key, null);
							billModel.setValueAt(null, row, "kabili");
							billModel.setValueAt(null, row, "danjia");
							billModel.setValueAt(null, row,"jine");
						}
						
					}
					
				}
				
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
	}

	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
	}

	public ShowUpableBillForm getCardForm() {
		return cardForm;
	}

	public void setCardForm(ShowUpableBillForm cardForm) {
		this.cardForm = cardForm;
	}
	
}
