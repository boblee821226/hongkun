package nc.ui.pms;

import hd.vo.pub.tools.PuPubVO;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.pms.PmsItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.*;
import nc.ui.trade.excelimport.util.StatusBarMsgCleaner;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

@SuppressWarnings("deprecation")
public class PmsAdminUI extends ToftPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1311844826825768526L;

	private ButtonObject qushuAction = new ButtonObject("ȡ��",null,"qushu");
	
	private UIRefPane ref_ksrq;	// ��ʼ����
	private UIRefPane ref_jsrq;	// ��������
	
	private UITextField txt_token;
	private UITextField txt_hotelCode;
	private UITextField txt_userName;
	private UITextField txt_passWord;
	private UITextField txt_channel;
	
	public PmsAdminUI() {
		super();
		
		setButtons(new ButtonObject[]{
			qushuAction
		});

		setName("PmsAdminUI");
		setLayout(new java.awt.BorderLayout());
		add(getPanMain(), "Center");
//		add(getPanTop(), "North");
		
		
		// ��ʼ����������
		this.initData();
		
	}
	
	/**
	 * ��ʼ����������
	 */
	private void initData() {
		
		String token = "Bearer 7a9c8fa7-36e7-3a73-940c-fafd88bbf797";
		String hotelCode = "110001038";
		String userName = "CWAPI";
		String passWord = "58316383D08E49A11E96C72203E61D50";
		String channel = "TS";
		
		this.txt_token.setText(token);
		this.txt_hotelCode.setText(hotelCode);
		this.txt_userName.setText(userName);
		this.txt_passWord.setText(passWord);
		this.txt_channel.setText(channel);
		
	}
	
	
	/**
	 * �������(��)
	 */
	private UIPanel pan_main;
	private UIPanel getPanMain() {
		if (pan_main == null) {
			try {
				pan_main = new nc.ui.pub.beans.UIPanel();
//				pan_main.setBackground(Color.LIGHT_GRAY);
				pan_main.setName("panMain");
				pan_main.setLayout(null);
				// ��
				pan_main.add(getLabel("��ʼ���ڣ�", 	100, 50));	// ��ʼ����
				pan_main.add(getRefKsrq(			165, 50));
				pan_main.add(getLabel("�������ڣ�", 	100, 85));	// ��������
				pan_main.add(getRefJsrq(			165, 85));
				// ��
				pan_main.add(getLabel("Token��", 	350, 50));	// Token
				pan_main.add(getTxtToken(			415, 50));
				pan_main.add(getLabel("�Ƶ���룺", 	350, 85));	// �Ƶ����
				pan_main.add(getTxtHotelCode(		415, 85));
				pan_main.add(getLabel("�û���", 		350, 115));	// �û�
				pan_main.add(getTxtUserName(		415, 115));
				pan_main.add(getLabel("���룺", 		350, 145));	// ����
				pan_main.add(getTxtPassWord(		415, 145));
				pan_main.add(getLabel("channel��", 	350, 175));	// channel
				pan_main.add(getTxtChannel(			415, 175));
				
			} catch (java.lang.Throwable ivjExc) {
				
				handleException(ivjExc);
			}
		}
		return pan_main;
	}
	
	/**
	 * �������(��)
	 */
	private UIPanel pan_top;
	private UIPanel getPanTop() {
		if (pan_top == null) {
			try {
				pan_top = new nc.ui.pub.beans.UIPanel();
				pan_top.setBackground(Color.BLUE);
				pan_top.setName("panTop");
				pan_top.setLayout(null);
			} catch (java.lang.Throwable ivjExc) {
				handleException(ivjExc);
			}
		}
		return pan_top;
	}
	
	/**
	 * ��ǩ��ͳһ����
	 */
	private UILabel getLabel(String text, int x, int y) {
		UILabel label = null;
		try {
			label = new nc.ui.pub.beans.UILabel();
			label.setText(text);
			label.setLocation(x, y);
			label.setSize(100, 25);
		} catch (java.lang.Throwable ivjExc) {
			handleException(ivjExc);
		}

		return label;
	}
	
	/**
	 * ��ʼ����
	 */
	UIRefPane getRefKsrq(int x, int y) {
		if (ref_ksrq == null) {
			ref_ksrq = new UIRefPane("����");
			ref_ksrq.setLocation(x, y);
			ref_ksrq.setSize(100, 25);
		}
		return ref_ksrq;
	}
	
	/**
	 * ��������
	 */
	UIRefPane getRefJsrq(int x, int y) {
		if (ref_jsrq == null) {
			ref_jsrq = new UIRefPane("����");
			ref_jsrq.setLocation(x, y);
			ref_jsrq.setSize(100, 25);
		}
		return ref_jsrq;
	}
	
	/**
	 * token
	 */
	private UITextField getTxtToken(int x, int y) {
		if (txt_token == null) {
			txt_token = new UITextField();
			txt_token.setLocation(x, y);
			txt_token.setSize(300, 30);
		}
		return txt_token;
	}
	/**
	 * hotelCode
	 */
	private UITextField getTxtHotelCode(int x, int y) {
		if (txt_hotelCode == null) {
			txt_hotelCode = new UITextField();
			txt_hotelCode.setLocation(x, y);
			txt_hotelCode.setSize(150, 30);
		}
		return txt_hotelCode;
	}
	/**
	 * userName
	 */
	private UITextField getTxtUserName(int x, int y) {
		if (txt_userName == null) {
			txt_userName = new UITextField();
			txt_userName.setLocation(x, y);
			txt_userName.setSize(150, 30);
		}
		return txt_userName;
	}
	/**
	 * passWord
	 */
	private UITextField getTxtPassWord(int x, int y) {
		if (txt_passWord == null) {
			txt_passWord = new UITextField();
			txt_passWord.setLocation(x, y);
			txt_passWord.setSize(300, 30);
		}
		return txt_passWord;
	}
	/**
	 * channel
	 */
	private UITextField getTxtChannel(int x, int y) {
		if (txt_channel == null) {
			txt_channel = new UITextField();
			txt_channel.setLocation(x, y);
			txt_channel.setSize(150, 30);
		}
		return txt_channel;
	}
	
	
	@Override
	public String getTitle() {
		return "PMS";
	}

	@Override
	public void onButtonClicked(ButtonObject bo) {
		// ȡ��
		if ("qushu".equals(bo.getCode())) {
			
			// ��ȡ����������ָ�� ��ʼ���ڣ������������ Ϊ�գ���ȡ ��ǰ���ڵ�ǰһ��
			String ksrq = this.ref_ksrq.getText();
			String jsrq = this.ref_jsrq.getText();
			
			if (null == PuPubVO.getString_TrimZeroLenAsNull(ksrq)) {
				this.showWarningMessage("������д��ʼ����");
				return;
			}
			if (null == PuPubVO.getString_TrimZeroLenAsNull(jsrq)) {
				jsrq = new UFDate().getDateBefore(1).toString().substring(0, 10);
			}
			
//			System.out.println("==" + ksrq + "==" + jsrq + "==");
			
			ArrayList<String> dates = PuPubVO.getEveryDateList(ksrq, jsrq);
			
			String token = this.txt_token.getText();
			String hotelCode = this.txt_hotelCode.getText();
			String userName = this.txt_userName.getText();
			String passWord = this.txt_passWord.getText();
			String channel = this.txt_channel.getText();
			
			// ��װ����
			HashMap params = new HashMap();
			params.put("token", token);
			params.put("hotelCode", hotelCode);
			params.put("userName", userName);
			params.put("passWord", passWord);
			params.put("channel", channel);
			params.put("dates", dates);
			
			PmsItf itf = (PmsItf)NCLocator.getInstance().lookup(PmsItf.class.getName());
			try {
				Object obj = itf.sync_pms(params, null);
				System.out.println(obj);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void handleException(java.lang.Throwable exception) {
		Logger.error("--------- δ��׽�����쳣 ---------");
		// MessageDialog.showErrorDlg(this, null, exception.getMessage());
		showErrorMessage(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3607con_0","03607con-0045")/*@res "����"*/, exception.getMessage());
		StatusBarMsgCleaner.getInstance().messageAdded(this);
	}

}
