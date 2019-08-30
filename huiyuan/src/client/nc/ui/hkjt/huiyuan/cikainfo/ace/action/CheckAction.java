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
		setBtnName("ȡ��");
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
		
		HashMap<String,KadanganCKCZVO> CKCZ_MAP_key = new HashMap<String,KadanganCKCZVO>();	// ������(key=����+"@@@@"+��Ŀ����+"@@@@"+�ο���ʼ����)
		HashMap<String,KadanganCKCZVO> CKCZ_MAP_sh  = new HashMap<String,KadanganCKCZVO>();	// ������(key=�ο�ˮ��)
		HashMap<String,KadanganCKCZVO> CKZC_MAP_key = new HashMap<String,KadanganCKCZVO>();	// ת��������(key=����+"@@@@"+��Ŀ����+"@@@@"+�ο���ʼ����)
		
		for(int i=0;i<rowCount;i++)
		{// �� ��ֵ��Ϣ ���õ� ���棨���� ���ѵ�ȡ����
			
			if(  "��ֵ".equals( billModel.getValueAt(i, "xmdl") )
			 && !"ת��".equals( billModel.getValueAt(i, "czlx") )	// ת������� ���ǳ�ֵ���Ժ���HK 2019��1��10��17:10:23��
			  )
			{
			
				if(  billModel.getValueAt(i, "kabili")==null
				  || billModel.getValueAt(i, "danjia")==null	
				)
				{
					throw new BusinessException("��"+(1+1)+"�У���ֵ�� ������ �� ���� ����Ϊ�ա�");
				}
				
				String ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "ka_code") );
				String itemname = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "itemname") );
				String startdata = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "startdata") );
				
				String cksh = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "timescardwaternum") );	// �ο�ˮ��
				String key = ka_code+"@@@@"+itemname+"@@@@"+startdata;	// key = ����+"@@@@"+��Ŀ����+"@@@@"+�ο���ʼ���ڣ�
				
				KadanganCKCZVO ckczvo = new KadanganCKCZVO();
				ckczvo.setKabili( PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "kabili") ) );	// ������
				ckczvo.setPrice( PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "danjia") ) );		// ����
				
				CKCZ_MAP_key.put(key, ckczvo);
				CKCZ_MAP_sh.put(cksh, ckczvo);
			}
		}
		
		for(int i=0;i<rowCount;i++)
		{// �为��  ��  ���ѵ�   Ҫ���ݵ���  ����ֵ  ������  ��  ���ۣ� ���Ҽ��� ��
			
			String ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "ka_code") );
			String itemname = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "itemname") );
			String startdata = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "startdata") );
			
			String cksh = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "timescardwaternum") );	// �ο�ˮ��
			String key = ka_code+"@@@@"+itemname+"@@@@"+startdata;	// key = ����+"@@@@"+��Ŀ����+"@@@@"+�ο���ʼ���ڣ�
			
			if(  "�为��".equals( billModel.getValueAt(i, "xmdl") ) 
			  || "����".equals( billModel.getValueAt(i, "xmdl") )
			)	// �为���� Ҫ��key  ȥƥ�䡣
			{
				
				if( cksh!=null 
				 && "����".equals( billModel.getValueAt(i, "xmdl") )
				)
				{// ����ο�ˮ�� ��Ϊ�գ���Ͱ� �ο�ˮ�� ȥ��ѯ  ���为���� Ҫ��key ȥƥ�䣩
					
					if( CKCZ_MAP_sh.containsKey(cksh) )
					{// ����ڻ�����
					
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
					{// ���ڻ������Ҫ��ѯ���ݿ� ������֮�� �ŵ������
						StringBuffer querySQL = 
								new StringBuffer("select")
										.append(" ckcz.kabili ")	// ������
										.append(",ckcz.price ")		// ����
//										.append(",ckcz.timescardwaternum ") // �ο�ˮ��
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
							ckczvo.setKabili( PuPubVO.getUFDouble_NullAsZero(obj[0]) );	// ������
							ckczvo.setPrice( PuPubVO.getUFDouble_NullAsZero(obj[1]) );	// ����
							String key_temp = obj[2]+"@@@@"+obj[3]+"@@@@"+obj[4];		// key = ����+"@@@@"+��Ŀ����+"@@@@"+�ο���ʼ���ڣ�
							
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
				{// ����ο�ˮ�� Ϊ�գ���Ͱ�  key ȥ��ѯ  ���为����Ҫ��keyȥƥ�䣩
					
					if( CKCZ_MAP_key.containsKey(key) )
					{// ����ڻ�����
					
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
					{// ���ڻ������Ҫ��ѯ���ݿ� ������֮�� �ŵ������
						StringBuffer querySQL = 
								new StringBuffer("select")
										.append(" ckcz.kabili ")	// ������
										.append(",ckcz.price ")		// ����
										.append(",ckcz.timescardwaternum ") // �ο�ˮ��
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
							ckczvo.setKabili( PuPubVO.getUFDouble_NullAsZero(obj[0]) );	// ������
							ckczvo.setPrice( PuPubVO.getUFDouble_NullAsZero(obj[1]) );	// ����
							String cksh_temp = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
							ckczvo.setTimescardwaternum( PuPubVO.getString_TrimZeroLenAsNull(obj[2]) );	// �ο�ˮ��
							
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
			 * �����Ŀ���� Ϊ ת���������ݷŵ������ �Ա����� ת�� ���á�
			 * HK 2019��1��10��17:17:29
			 */
			if( "ת��".equals( billModel.getValueAt(i, "xmlx") ) )
			{
				UFDouble kabili = PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "kabili") );
				UFDouble danjia = PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(i, "danjia") );
				
				KadanganCKCZVO ckczvo = new KadanganCKCZVO();
				ckczvo.setKabili( kabili );	// ������
				ckczvo.setPrice( danjia );	// ����
				
				CKZC_MAP_key.put(key, ckczvo);
				
			}
			
		}
		
		/**
		 * ��ֵ���� Ϊ ת��
		 * ת��Ĵ�������ת��������ֵ
		 * HK 2019��1��10��17:28:38
		 */
		for(int i=0;i<rowCount;i++)
		{
			
			if( "ת��".equals( billModel.getValueAt(i, "czlx") ) )
			{
				String y_ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "y_ka_code") );
				String itemname = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "itemname") );
				String startdata = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "startdata") );
				
				String key = y_ka_code+"@@@@"+itemname+"@@@@"+startdata;	// key = Դ����+"@@@@"+��Ŀ����+"@@@@"+�ο���ʼ���ڣ�
				
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
