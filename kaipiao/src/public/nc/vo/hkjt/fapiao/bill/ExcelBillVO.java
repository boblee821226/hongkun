package nc.vo.hkjt.fapiao.bill;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class ExcelBillVO extends SuperVO {
	public String INFO;           //INFO
	public String billtype;       //��Ʊ����
	public String billstatus;     //��Ʊ״̬
	public String billcode;       //��Ʊ����
	public String billID;         //��Ʊ����
	public String customername;   //�ͻ�����
	public String tradename;      //��Ʒ����
	public UFDouble tax;            //˰��
	public UFDouble amount;         //�ϼƽ��
	public UFDouble pricetax_total;//��˰�ϼ�
	public String raw_billcode;   //ԭ��Ʊ����
	public String raw_billID;     //ԭ��Ʊ����
	public String req_code;       //֪ͨ�����
	public String drawer;         //��Ʊ��
	public String bill_date;      //��Ʊ����
	public String obsoleter;      //������
	public String obsolete_date;  //��������
	public String customer_idnumber;//�ͻ�ʶ����
	//�Զ��� ��
	public String vdef01;//�Զ���01
	public String vdef02;//�Զ���02
	public String vdef03;//�Զ���03
	public String vdef04;//�Զ���04
	public String vdef05;//�Զ���05
	
	public String getINFO() {
		return INFO;
	}
	public void setINFO(String iNFO) {
		INFO = iNFO;
	}
	public String getBilltype() {
		return billtype;
	}
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	public String getBillstatus() {
		return billstatus;
	}
	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}
	public String getBillcode() {
		return billcode;
	}
	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}
	public String getBillID() {
		return billID;
	}
	public void setBillID(String billID) {
		this.billID = billID;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	public UFDouble getTax() {
		return tax;
	}
	public void setTax(UFDouble tax) {
		this.tax = tax;
	}
	public UFDouble getAmount() {
		return amount;
	}
	public void setAmount(UFDouble amount) {
		this.amount = amount;
	}
	public UFDouble getPricetax_total() {
		return pricetax_total;
	}
	public void setPricetax_total(UFDouble pricetax_total) {
		this.pricetax_total = pricetax_total;
	}
	public String getRaw_billcode() {
		return raw_billcode;
	}
	public void setRaw_billcode(String raw_billcode) {
		this.raw_billcode = raw_billcode;
	}
	public String getRaw_billID() {
		return raw_billID;
	}
	public void setRaw_billID(String raw_billID) {
		this.raw_billID = raw_billID;
	}
	public String getReq_code() {
		return req_code;
	}
	public void setReq_code(String req_code) {
		this.req_code = req_code;
	}
	public String getDrawer() {
		return drawer;
	}
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public String getObsoleter() {
		return obsoleter;
	}
	public void setObsoleter(String obsoleter) {
		this.obsoleter = obsoleter;
	}
	public String getObsolete_date() {
		return obsolete_date;
	}
	public void setObsolete_date(String obsolete_date) {
		this.obsolete_date = obsolete_date;
	}
	public String getCustomer_idnumber() {
		return customer_idnumber;
	}
	public void setCustomer_idnumber(String customer_idnumber) {
		this.customer_idnumber = customer_idnumber;
	}
	public String getVdef01() {
		return vdef01;
	}
	public void setVdef01(String vdef01) {
		this.vdef01 = vdef01;
	}
	public String getVdef02() {
		return vdef02;
	}
	public void setVdef02(String vdef02) {
		this.vdef02 = vdef02;
	}
	public String getVdef03() {
		return vdef03;
	}
	public void setVdef03(String vdef03) {
		this.vdef03 = vdef03;
	}
	public String getVdef04() {
		return vdef04;
	}
	public void setVdef04(String vdef04) {
		this.vdef04 = vdef04;
	}
	public String getVdef05() {
		return vdef05;
	}
	public void setVdef05(String vdef05) {
		this.vdef05 = vdef05;
	}
	
	public String getTableName()
    {
		return "hk_fapiao_bill_temp";
    }
	

}
