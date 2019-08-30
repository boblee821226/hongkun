package nc.ui.hkjt.huiyuan.kainfo.ace.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class ZfkAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public ZfkAction() {
		setBtnName("作废卡");
		setCode("zfkAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction bodyAddLineAction;

	public BodyAddLineAction getBodyAddLineAction() {
		return bodyAddLineAction;
	}

	public void setBodyAddLineAction(BodyAddLineAction bodyAddLineAction) {
		this.bodyAddLineAction = bodyAddLineAction;
	}

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
		
		Object dbilldate = this.getEditor().getBillCardPanel().getHeadItem("dbilldate").getValue();
		
		String dqsj = new UFDateTime().toString();
		
		String zfsj_init = dbilldate.toString().substring(0, 10) + " " + dqsj.substring(11);
		
		BillItem zfka_item = new BillItem();
		zfka_item.setName("作废卡号");
		zfka_item.setKey("zfka_code");
		zfka_item.setDataType(IBillItem.STRING);
//		zfka_item.setRefType("合同(自定义档案)");
		zfka_item.setEdit(true);
//		zfka_item.setNull(false);
//		zfka_item.setValue(ht);
		
		BillItem zfsj_item = new BillItem();
		zfsj_item.setName("作废时间");
		zfsj_item.setKey("zfsj");
		zfsj_item.setDataType(IBillItem.DATETIME);
		zfsj_item.setEdit(true);
		zfsj_item.setValue(zfsj_init);
		
		BillItem kje_item = new BillItem();	// 卡金额
		kje_item.setName("卡金额");
		kje_item.setKey("kje");
		kje_item.setDataType(IBillItem.DECIMAL);
		kje_item.setEdit(true);
		
		BillItem ssje_item = new BillItem();	// 实收金额
		ssje_item.setName("实收金额");
		ssje_item.setKey("ssje");
		ssje_item.setDataType(IBillItem.DECIMAL);
		ssje_item.setEdit(true);
		
		BillItem zsje_item = new BillItem();	// 赠送金额
		zsje_item.setName("赠送金额");
		zsje_item.setKey("zsje");
		zsje_item.setDataType(IBillItem.DECIMAL);
		zsje_item.setEdit(true);
		
		BillItem isdk_item = new BillItem();	// 是否一级大卡
		isdk_item.setName("是否一级大卡");
		isdk_item.setKey("dk");
		isdk_item.setDataType(IBillItem.BOOLEAN);
		isdk_item.setEdit(true);
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				 this.getEditor()
				,new BillItem[]{
					 zfka_item,
					 zfsj_item,
					 kje_item,
					 ssje_item,
					 zsje_item,
					 isdk_item,
				});
		dlg.setTitle("作废卡录入");
		
		if(UIDialog.ID_OK == dlg.showModal()){
			
			String zfka_code = PuPubVO.getString_TrimZeroLenAsNull(zfka_item.getValueObject());	// 作废卡号
			String zfsj = PuPubVO.getString_TrimZeroLenAsNull(zfsj_item.getValueObject());		// 作废时间
			
			UFDouble kje = PuPubVO.getUFDouble_NullAsZero( kje_item.getValueObject() );		// 卡金额
			UFDouble ssje = PuPubVO.getUFDouble_NullAsZero( ssje_item.getValueObject() );	// 实收金额
			UFDouble zsje = PuPubVO.getUFDouble_NullAsZero( zsje_item.getValueObject() );	// 赠送金额
			
			UFBoolean isDk = PuPubVO.getUFBoolean_NullAs(isdk_item.getValueObject(), UFBoolean.FALSE);	// 是否一级大卡
			
			if( zfka_code==null || zfsj==null )
			{// 作废卡号 和 时间  是必填
				MessageDialog.showErrorDlg(this.getEditor(), "", "必须填写 作废卡号 和 时间。");
				return;
			}
			
			// 查找 会员卡 相关信息。
			StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" ka.pk_hk_huiyuan_kadangan ")	// 卡 pk
							.append(",kx.kaxing_code ")				// 卡型code
							.append(",kx.kaxing_name ")				// 卡型名称
							.append(",kx.pk_hk_huiyuan_kaxing ")	// 卡型pk
							.append(",ka.ka_code ")		// 卡号
							.append(" from hk_huiyuan_kadangan ka ")
							.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
							.append(" where ka.dr=0  ")
							.append(" and NLS_UPPER(ka.ka_code) = NLS_UPPER('").append(zfka_code).append("') ")	// 都转换成大写来比较， 目的是 不区分大小写
			;
			
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor());
			
			if(list.size()==0)
			{// 判断有无  会员卡
				MessageDialog.showErrorDlg(this.getEditor(), "", "系统里 没有该会员卡，请检查。");
				return;
			}
			
			Object[] ka_value = (Object[])list.get(0);
			
			this.getBodyAddLineAction().doAction(e);	// 增行
			
			BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
			
			int endRow = billModel.getRowCount() -1;
			
			billModel.setValueAt("充值", endRow , "xmdl");	// 项目大类
			billModel.setValueAt("作废卡", endRow , "xmlx");	// 项目类型
			billModel.setValueAt(zfsj, endRow , "ywsj");	// 业务时间
			billModel.setValueAt(ka_value[0], endRow , "ka_pk");	// 卡pk
			billModel.setValueAt(ka_value[1], endRow , "kaxing_code");	// 卡型code
			billModel.setValueAt(ka_value[2], endRow , "kaxing_name");	// 卡型name
			billModel.setValueAt(ka_value[3], endRow , "kaxing_pk");	// 卡型pk
			billModel.setValueAt(ka_value[4], endRow , "ka_code");		// 卡code
			
			billModel.setValueAt( kje, endRow , "ka_je");	// 卡金额
			billModel.setValueAt(ssje, endRow , "ka_ss");	// 实收金额
			billModel.setValueAt(zsje, endRow , "ka_zs");	// 赠送金额
			
			if(isDk.booleanValue())
			{// 如果是 删除一级大卡，   则 再新增一个 余转行
				
				this.getBodyAddLineAction().doAction(e);	// 增行
				endRow = billModel.getRowCount() -1;
				
				billModel.setValueAt("余转", endRow , "xmdl");	// 项目大类
				billModel.setValueAt(zfsj, endRow , "ywsj");	// 业务时间
				
				billModel.setValueAt("0000000000000000XUNI", endRow , "ka_pk");		// 卡pk
				billModel.setValueAt("77", endRow , "kaxing_code");					// 卡型code
				billModel.setValueAt("财务大卡", endRow , "kaxing_name");				// 卡型name
				billModel.setValueAt("0001ZZ1000000001CU7L", endRow , "kaxing_pk");	// 卡型pk
				billModel.setValueAt("虚拟卡", endRow , "ka_code");					// 卡code
				
				billModel.setValueAt(ka_value[0], endRow , "y_ka_pk");		// 源卡pk
				billModel.setValueAt(ka_value[1], endRow , "y_kaxing_code");// 源卡型code
				billModel.setValueAt(ka_value[2], endRow , "y_kaxing_name");// 源卡型name
				billModel.setValueAt(ka_value[3], endRow , "y_kaxing_pk");	// 源卡型pk
				billModel.setValueAt(ka_value[4], endRow , "y_ka_code");	// 源卡code
				
				billModel.setValueAt( kje, endRow , "ka_je");	// 卡金额
				billModel.setValueAt(ssje, endRow , "ka_ss");	// 实收金额
				billModel.setValueAt(zsje, endRow , "ka_zs");	// 赠送金额
				
				billModel.setValueAt( kje, endRow , "y_ka_je");	// 卡金额
				billModel.setValueAt(ssje, endRow , "y_ka_ss");	// 实收金额
				billModel.setValueAt(zsje, endRow , "y_ka_zs");	// 赠送金额
				
			}
			
		}
		
	}

}
