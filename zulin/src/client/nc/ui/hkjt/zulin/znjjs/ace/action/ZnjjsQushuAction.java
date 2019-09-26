package nc.ui.hkjt.zulin.znjjs.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ���ɽ����ȡ��
 *
 */
public class ZnjjsQushuAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public ZnjjsQushuAction() {
		setBtnName("ȡ��");
		setCode("qushuAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction addLineAction;

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
	
	public BodyAddLineAction getAddLineAction() {
		return addLineAction;
	}

	public void setAddLineAction(BodyAddLineAction addLineAction) {
		this.addLineAction = addLineAction;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		String pk_org = 
			PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject()
			);
		
		if(pk_org==null)
		{
			MessageDialog.showErrorDlg(this.getEditor(), "", "����������֯");
			return;
		}
		
		UFDate dbilldate = 
			PuPubVO.getUFDate(
				this.getEditor().getBillCardPanel().getHeadItem("dbilldate").getValueObject()
			);
				
		if(dbilldate==null)
		{
			MessageDialog.showErrorDlg(this.getEditor(), "", "���������������");
			return;
		}
		
		String dbilldateStr = dbilldate.toString().substring(0, 10);
		
		// ���������ݣ�����ȡ��
		{
			int rowCount = getEditor().getBillCardPanel().getBillModel().getRowCount();
			if(rowCount>0)
			{
				MessageDialog.showErrorDlg(this.getEditor(), "", "���������ݣ�����ȡ��������ɾ���������ݡ�");
				return;
			}
		}
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		UFDouble yq_mny_total = UFDouble.ZERO_DBL;	// ��ͷ-���ɽ�ϼ�
		int addRowIndex = -1;	// ��ǰ����������
		// key=htBid , value=null  ���ڿ���ͳ�� ��ͬbid �Ƿ���ڡ�
		HashMap<String,Object> MAP_HT_INFO = new HashMap<String,Object>();
		/**
		 * 1���� ��ͬ��ȡ����ȡ δ�ɷѵ�
		 */
		{
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" ct.pk_customer ")	// 0�ͻ�
						.append(",ct.vdef15 ")		// 1����
						.append(",ct.vdef16 ")		// 2����
						.append(",ct.vbillcode ")	// 3��ͬ��
						.append(",ctb.crowno ")		// 4��ͬ��
						.append(",ctb.vbdef1 ")		// 5�շ���Ŀ
						.append(",substr(ctb.vbdef3,1,10) ")	// 6��ʼ����
						.append(",ctb.norigtaxmny ")	// 7Ӧ�ɽ��
						.append(",ctb.noritotalgpmny ")	// 8ʵ�ɽ��
						.append(",ctb.pk_ct_sale_b ")	// 9��ͬ�ӱ�pk
						.append(",ctb.pk_ct_sale ")		//10��ͬ����pk
						.append(",ct.vdef19 ")			//11���ȷ�Ͻ�����
						.append(" from ct_sale ct ")
						.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
						.append(" left join bd_defdoc srxm on ctb.vbdef1  = srxm.pk_defdoc ")
						.append(" where ct.dr = 0 and ctb.dr = 0 ")
						.append(" and ct.blatest = 'Y' ")
						.append(" and ct.pk_org = '"+pk_org+"' ")
						.append(" and substr(ctb.vbdef3,1,10) <= '"+dbilldateStr+"' ")
						.append(" and (nvl(ctb.norigtaxmny,0)-nvl(ctb.noritotalgpmny,0))<>0 ")
						.append(" and srxm.name not like '%Ѻ��%' ")
						.append(" and srxm.name not like '%����%' ")
//						.append(" and ct.vbillcode = '20181212A008' ")	// ����
			;
			
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
			
			if(list!=null && list.size()>0)
			{
				for(int row=0;row<list.size();row++)
				{
					Object[] obj = (Object[])list.get(row);
					String	  pk_cust = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					String 	  pk_area = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					String 	  pk_room = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
					String 	  ht_code = PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
					String 	 ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
					String 	  pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
					UFDate    jf_date = PuPubVO.getUFDate(obj[6]);
					UFDouble   yj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
					UFDouble   sj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[8]);
					String 		ht_id = PuPubVO.getString_TrimZeroLenAsNull(obj[9]);
					String     ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[10]);
					UFDate	  zjqrjzr = PuPubVO.getUFDate(obj[11]);	// ���ȷ�Ͻ�������
					UFDouble   jf_mny = yj_mny.sub(sj_mny);	// Ӧ�ɷѽ�� = Ӧ�ɷ��� - ʵ�ɷ���
					
					UFDate jisuanDate = dbilldate;	// �������ڣ�������ȷ�Ͻ������ڣ�С�� ��ǰ���ڣ� �Ǽ�������Ӧ�õ������ȷ�Ͻ������ڣ�
					String vbmemo = null;			// �б�ע����������ȷ�Ͻ�������������ģ����ֵ��б�ע�ϣ�
					if(zjqrjzr!=null && zjqrjzr.compareTo(dbilldate)<0) {
						jisuanDate = zjqrjzr;
						vbmemo = "���ȷ�Ͻ�������"+zjqrjzr.toString().substring(0, 10);
					}
					
					Integer yq_num = jisuanDate.getDaysAfter(jf_date) + 1;	// ��������
					
					if(yq_num<=0) {	// �������� <=0 , ��������
						continue;
					}
						
					UFDouble  yq_bl = new UFDouble(5);				// ����(ǧ��֮��)
					UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
							.setScale(2, UFDouble.ROUND_HALF_UP);	// ���ɽ�=Ӧ�ɷѽ�� * 5�� * ��������
					
					yq_mny_total = yq_mny_total.add(yq_mny);
					
					this.getAddLineAction().doAction();
					addRowIndex++;
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_cust, addRowIndex, "pk_cust");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_area, addRowIndex, "pk_area");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_room, addRowIndex, "pk_room");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_code, addRowIndex, "ht_code");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_rowno, addRowIndex, "ht_rowno");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_sfxm, addRowIndex, "pk_sfxm");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_date, addRowIndex, "jf_date");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_mny, addRowIndex, "jf_mny");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_bl, addRowIndex, "yq_bl");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_num, addRowIndex, "yq_num");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_mny, addRowIndex, "yq_mny");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_id, addRowIndex, "ht_id");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_bid, addRowIndex, "ht_bid");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(vbmemo, addRowIndex, "vbmemo");	// �б�ע
					
					MAP_HT_INFO.put(ht_bid, null);	// ��ӵ������� �� ������ ʹ��
				}
			}
		}
		
		/**
		 * 2��ȡ ���տ���� �����տ�� ���ݣ� ������ ֮ǰû��ȡ���ġ���ȷ��ֻȡһ�Σ���Ҫ�ظ�ȡ��
		 */
		{
			StringBuffer querySQL_2 = 
			new StringBuffer("select ")
					.append(" ct.pk_customer ")
					.append(",ct.vdef15 ")
					.append(",ct.vdef16 ")
					.append(",ct.vbillcode ")
					.append(",ctb.crowno ")
					.append(",skb.def1 ")
					.append(",substr(skb.def3,1,10) ")
					.append(",skb.money_cr ")
					.append(",0 ")
					.append(",ctb.pk_ct_sale_b ")
					.append(",ctb.pk_ct_sale ")
					.append(",substr(skb.busidate,1,10) ")
					.append(",sk.billno ")
					.append(",skb.pk_gatherbill ")
					.append(" from ar_gatherbill sk ")
					.append(" inner join ar_gatheritem skb on sk.pk_gatherbill = skb.pk_gatherbill ")
					.append(" inner join ct_sale_b ctb on skb.src_itemid = ctb.pk_ct_sale_b ")
					.append(" inner join ct_sale ct on ctb.pk_ct_sale = ct.pk_ct_sale ")
					.append(" left join bd_defdoc srxm on skb.def1 = srxm.pk_defdoc ")
					.append(" left join hk_zulin_znjjs_b jsb on skb.pk_gatherbill = jsb.vbdef04 ")
					.append(" where sk.dr = 0 and skb.dr = 0 ")
					.append(" and skb.src_tradetype = 'Z3-01' ")
					.append(" and srxm.name not like '%Ѻ��%' ")
					.append(" and srxm.name not like '%����%' ")
					.append(" and ct.pk_org = '"+pk_org+"' ")
					.append(" and jsb.pk_hk_zulin_znjjs_b is null ")
//					.append(" and sk.billno = 'D22019092400008794' ")	// ����
			;
			ArrayList list_2 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
			
			if(list_2!=null && list_2.size()>0)
			{
				for(int row=0;row<list_2.size();row++)
				{
					Object[] obj = (Object[])list_2.get(row);
					String	  pk_cust = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					String 	  pk_area = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
					String 	  pk_room = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
					String 	  ht_code = PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
					String 	 ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
					String 	  pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
					UFDate    jf_date = PuPubVO.getUFDate(obj[6]);
					UFDouble   yj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
					UFDouble   sj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[8]);
					String 		ht_id = PuPubVO.getString_TrimZeroLenAsNull(obj[9]);
					String     ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[10]);
					UFDate	  zjqrjzr = PuPubVO.getUFDate(obj[11]);	// ���ȷ�Ͻ�������
					UFDouble   jf_mny = yj_mny.sub(sj_mny);	// Ӧ�ɷѽ�� = Ӧ�ɷ��� - ʵ�ɷ���
					String     skCode = PuPubVO.getString_TrimZeroLenAsNull(obj[12]);	// �տ��
					String     skBid = PuPubVO.getString_TrimZeroLenAsNull(obj[13]);	// �տbid
					
					UFDate jisuanDate = dbilldate;	// �������ڣ�������ȷ�Ͻ������ڣ�С�� ��ǰ���ڣ� �Ǽ�������Ӧ�õ������ȷ�Ͻ������ڣ�
					String vbmemo = null;			// �б�ע����������ȷ�Ͻ�������������ģ����ֵ��б�ע�ϣ�
					if(zjqrjzr!=null && zjqrjzr.compareTo(dbilldate)<0) {
						jisuanDate = zjqrjzr;
						vbmemo = "�տ�����"+zjqrjzr.toString().substring(0, 10);
					}
					
					Integer yq_num = jisuanDate.getDaysAfter(jf_date);	// ��������
					
					if(yq_num<=0) {	// �������� <=0 , ��������
						continue;
					}
						
					UFDouble  yq_bl = new UFDouble(5);				// ����(ǧ��֮��)
					UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
							.setScale(2, UFDouble.ROUND_HALF_UP);	// ���ɽ�=Ӧ�ɷѽ�� * 5�� * ��������
					
					yq_mny_total = yq_mny_total.add(yq_mny);
					
					this.getAddLineAction().doAction();
					addRowIndex++;
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_cust, addRowIndex, "pk_cust");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_area, addRowIndex, "pk_area");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_room, addRowIndex, "pk_room");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_code, addRowIndex, "ht_code");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_rowno, addRowIndex, "ht_rowno");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_sfxm, addRowIndex, "pk_sfxm");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_date, addRowIndex, "jf_date");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_mny, addRowIndex, "jf_mny");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_bl, addRowIndex, "yq_bl");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_num, addRowIndex, "yq_num");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_mny, addRowIndex, "yq_mny");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_id, addRowIndex, "ht_id");
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_bid, addRowIndex, "ht_bid");
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(vbmemo, addRowIndex, "vbmemo");	// �б�ע
					
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(skCode, addRowIndex, "vbdef03");	// �տ��
					this.getEditor().getBillCardPanel().getBillModel().setValueAt(skBid, addRowIndex, "vbdef04");	// �տbid
					
				}
			}
		}
		
		/**
		 * 3�������ڵ� ���㵥�� ȡ��
		 */
		{
			StringBuffer querySQL_3 = 
			new StringBuffer("select ")
					.append(" jsb.pk_cust ")	// 0
					.append(",jsb.pk_area ")	// 1
					.append(",jsb.pk_room ")	// 2
					.append(",jsb.ht_code ")	// 3
					.append(",jsb.ht_rowno ")	// 4
					.append(",jsb.pk_sfxm ")	// 5
					.append(",jsb.jf_date ")	// 6
					.append(",jsb.jf_mny - to_number(nvl( replace(jsb.vbdef01,'~',''),'0' )) ")		// 7  ���ɽ�-������
					.append(",jsb.yq_bl ")		// 8
					.append(",jsb.yq_num ")		// 9
					.append(",jsb.yq_mny ")		//10
					.append(",jsb.ht_id ")		//11
					.append(",jsb.ht_bid ")		//12
					.append(",jsb.vbmemo ")		//13
					.append(",jsb.vbdef03 ")	//14 �տ��
					.append(",jsb.vbdef04 ")	//15 �տbid
					.append(" from hk_zulin_znjjs js ")
					.append(" inner join ( ")
					.append("	select js.pk_org,max(js.dbilldate) dbilldate ")
					.append("	from hk_zulin_znjjs js ")
					.append("	where dr=0 ")
					.append("	and js.pk_org = '"+pk_org+"' ")
					.append("	and substr(js.dbilldate,1,10) < '"+dbilldateStr+"' ")
					.append("	group by js.pk_org  ")
					.append(" ) js_new on (js.pk_org=js_new.pk_org and js.dbilldate=js_new.dbilldate) ")
					.append(" inner join hk_zulin_znjjs_b jsb on (js.pk_hk_zulin_znjjs = jsb.pk_hk_zulin_znjjs) ")	// ����Ӧ�յ�
					.append(" left join ar_recitem ysb on (jsb.pk_hk_zulin_znjjs_b = ysb.def29 and ysb.dr=0) ")
					.append(" where js.dr=0 and jsb.dr=0 ")
					.append(" and ysb.pk_recitem is null ")	// ֻȡ δ����Ӧ�յ��ģ�����Ӧ�յ�������ζ�� ���ɽ�ģ�� �������
			;
			
			ArrayList list_3 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_3.toString(), new ArrayListProcessor());
			
			if(list_3!=null && list_3.size()>0)
			{
				for(int i=0;i<list_3.size();i++)
				{
					Object[] obj = (Object[])list_3.get(i);
					String ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[12]);
					String  skBid = PuPubVO.getString_TrimZeroLenAsNull(obj[15]);	// �տbid
					// ����� �տbid��Ϊ�գ����� ���ٱ��ڵĵ�һ��������
					if(skBid!=null || !MAP_HT_INFO.containsKey(ht_bid)) {
						String pk_cust 	= PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
						String pk_area 	= PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
						String pk_room 	= PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
						String ht_code 	= PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
						String ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
						String pk_sfxm 	= PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
						UFDate jf_date 	= PuPubVO.getUFDate((obj[6]));
						UFDouble jf_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
						UFDouble yq_bl 	= PuPubVO.getUFDouble_ValueAsValue(obj[8]);
						UFDouble yq_num = PuPubVO.getUFDouble_ValueAsValue(obj[9]);
						UFDouble yq_mny = PuPubVO.getUFDouble_ValueAsValue(obj[10]);
						String ht_id 	= PuPubVO.getString_TrimZeroLenAsNull(obj[11]);
						String vbmemo 	= PuPubVO.getString_TrimZeroLenAsNull(obj[13]);
						String   skCode = PuPubVO.getString_TrimZeroLenAsNull(obj[14]);	// �տ��
						
						if(skBid==null) {	// ֻ�в��� �����տ�ģ��Ž��� ��ע�Ĵ���
							if(vbmemo==null){
								vbmemo = "";
							}
							vbmemo += "��������"+dbilldateStr+"�������ѽ��壬ֻǷ���ɽ�";
						}
						
						yq_mny_total = yq_mny_total.add(yq_mny);
						
						this.getAddLineAction().doAction();
						addRowIndex++;
						
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_cust, addRowIndex, "pk_cust");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_area, addRowIndex, "pk_area");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_room, addRowIndex, "pk_room");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_code, addRowIndex, "ht_code");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_rowno, addRowIndex, "ht_rowno");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_sfxm, addRowIndex, "pk_sfxm");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_date, addRowIndex, "jf_date");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(jf_mny, addRowIndex, "jf_mny");
						
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_bl, addRowIndex, "yq_bl");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_num, addRowIndex, "yq_num");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(yq_mny, addRowIndex, "yq_mny");
						
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_id, addRowIndex, "ht_id");
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_bid, addRowIndex, "ht_bid");
						
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(vbmemo, addRowIndex, "vbmemo");
					
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(skCode, addRowIndex, "vbdef03");	// �տ��
						this.getEditor().getBillCardPanel().getBillModel().setValueAt(skBid, addRowIndex, "vbdef04");	// �տbid
						
					}
				}
			}
		}
		
		// �� ���շ������
		getEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		// ��ͷ��ֵ
		getEditor().getBillCardPanel().getHeadItem("yq_mny_total").setValue(yq_mny_total);
	}
}
