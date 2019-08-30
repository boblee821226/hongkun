package nc.ui.hkjt.fapiao.bill.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;

import nc.bs.framework.common.NCLocator;
import nc.bs.hkjt.srgk.huiguan.zhangdan.workplugin.ImpZhangDanBill;
import nc.itf.hkjt.IHk_fp_billMaintain;
import nc.ui.am.common.XlsFileFilter;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.excelimport.ImportAction;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.hkjt.fapiao.bill.ExcelBillVO;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImpFPdataAction extends ImportAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5676997736417993469L;
	
	public ImpFPdataAction(){
		this.setBtnName("���뷢Ʊ����");	
		this.setCode("impFPdata");
	}
	
	private AbstractUIAppModel model;
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

	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
	}

	/**
	 * ���밴ťҵ���߼�ʵ�ֹ���
	 * 1��������Ҫ�����Exel������ȡ����
	 * 2������1�����ݷ�װ��VO
	 * 3��ͨ��VO�����ݲ��뵽��Ӧ�����ݿ����
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		/**
		 * ��ȡExel��
		 */
//		impfromexel();
		
		/**
		 * ���� �������˵��� ��̨����
		 */
		{
			IHk_fp_billMaintain itf = (IHk_fp_billMaintain)NCLocator.getInstance().lookup(IHk_fp_billMaintain.class.getName());
			itf.executeTest(null);
		}
		/***END***/
		
	}
	File file =null;
	private void impfromexel() throws Exception {
		// TODO �Զ����ɵķ������
		/**
		 * 1����ȡ���ļ�ѡ�����Ի���ģ�͡������ġ���ڣ��ļ���ť==�ļ�ѡ������======������ļ�ѡ�����Ի���
		 * 
		 */
		if(getUIFileChooser().showDialog(getModel().getContext().getEntranceUI(),"����")==JFileChooser.APPROVE_OPTION){
			file =getUIFileChooser().getSelectedFile();  //�ļ�����ѡ����ѡ���ļ�
			if (file == null || file.getName().trim().length() == 0) { //���ļ�Ϊ�ջ����ֳ���Ϊ0������ѡ���ļ���ʾ
				//������Ϣ��ʾ����ʾ����
				MessageDialog.showErrorDlg(getModel().getContext().getEntranceUI(), nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ampub_0","04501000-0382")/*@res "����"*/, "��ѡ��Ҫ�����Excel�ļ�!");
				return;
			}
			InputStream is = new FileInputStream(file);
			Workbook workbook=null;
			if(file.getPath().endsWith(".xls")){
				 workbook= new HSSFWorkbook(is);
			}else if(file.getPath().endsWith(".xlsx")){
				 workbook= new XSSFWorkbook(is);
			}
            // ���ļ� һ��sheetһ��sheet�ض�ȡ
			/**
			 * VO����
			 */
			List<ExcelBillVO> vo_list =new ArrayList<ExcelBillVO>(); 
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();
                // ��ȡ������
                //���ݼ���---
                for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                	List<Object> list =new ArrayList<Object>();
                    Row currentRow = sheet.getRow(rowIndex);// ��ǰ��
                    int firstColumnIndex = currentRow.getFirstCellNum(); // ����
                    int lastColumnIndex = currentRow.getLastCellNum();// ���һ��
                    for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex-1; columnIndex++) {
                        Cell currentCell = currentRow.getCell(columnIndex);// ��ǰ��Ԫ��
                        String currentCellValue = this.getCellValue(currentCell);// ��ǰ��Ԫ���ֵ
                        list.add(currentCellValue);  
                    }
                    /**
                     * �������ݷ�װ��VO
                     */
                    excevo(vo_list, list);
                }  
            }
            /**
             * ��VO��װ��VO����
             */
            ExcelBillVO[] billvos = vo_list.toArray(new ExcelBillVO[0]);
            
            IHk_fp_billMaintain itf = (IHk_fp_billMaintain)NCLocator.getInstance().lookup(IHk_fp_billMaintain.class.getName());
            
            int totalRow  = billvos.length;	// excel������
            int updateRow = 0;	// ���µ�����
            
            Object result = itf.writeFpInfo(billvos, null);	// ���л�д����
            
            if( result instanceof Integer )
            {
            	updateRow = PuPubVO.getInteger_NullAs(result, 0);
            }
            
//            String[] pks = HYPubBO_Client.insertAry(billvos);
//            System.out.println("=="+pks);
//            if(true) return;	// ����
            
            /**
             * ��� excel �����ݽ��л�д��
             */
//            for(int i=0;i<billvos.length;i++)
//            {
//            	ExcelBillVO excelVO = billvos[i];
//            	String fpdm = excelVO.getBillcode();
//            	String fphm = excelVO.getBillID();
//            	
//            	StringBuffer exceSQL = 
//            			new StringBuffer("update hk_fapiao_bill fp ")
//            					.append(" set ")
//            					.append(" fp.dr=0 ")	// DR
////            					.append(" fp.TS=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ")		// TS
//            					.append(excelVO.getCustomername()==null?""	:",fp.i_kpkh = '"+excelVO.getCustomername()+"' ")		//��Ʊ�ͻ�
//            					.append(excelVO.getTradename()==null?""		:",fp.i_spmc = '"+excelVO.getTradename()+"' ")			//��Ʒ����
//            					.append(excelVO.getTax()==null?""			:",fp.i_se   = "+excelVO.getTax()+" ")					//˰��
//            					.append(excelVO.getAmount()==null?""		:",fp.i_je   = "+excelVO.getAmount()+" ")				//���
//            					.append(excelVO.getPricetax_total()==null?"":",fp.i_jshj = "+excelVO.getPricetax_total()+" ")		//��˰�ϼ�
//            					.append(excelVO.getBill_date()==null?""		:",fp.i_kprq = '"+excelVO.getBill_date()+"' ")			//��Ʊ����
//            					.append(excelVO.getRaw_billcode()==null?""	:",fp.i_yfpdm= '"+excelVO.getRaw_billcode()+"' ")		//ԭ��Ʊ����
//            					.append(excelVO.getRaw_billID()==null?""	:",fp.i_yfphm= '"+excelVO.getRaw_billID()+"' ")			//ԭ��Ʊ����
//            					.append(excelVO.getObsolete_date()==null?""	:",fp.i_zfrq = '"+excelVO.getObsolete_date()+"' ")		//��������
//            					.append(excelVO.getCustomer_idnumber()==null?"":",fp.i_khsbh= '"+excelVO.getCustomer_idnumber()+"' ")	// �ͻ�ʶ���
//            					.append(" where fp.dr=0 ")
//            					.append(" and fp.fpdm = '"+fpdm+"' ")	// ��Ʊ����
//            					.append(" and fp.fphm = '"+fphm+"' ")	// ��Ʊ����
//            	;
//            	
//            	Integer result = itf.exceSQL(exceSQL.toString());
//            	if(result >0 ){
//            		updateRow+=result;
//            	}
//            }
            
            MessageDialog.showHintDlg(listview, "������"
            		,"Excel�ܹ���"+totalRow+"���У��ɹ������ˡ�"+updateRow+"���С�"
            );
            
		}
	}
	/**
	 * ��ֵ��װΪVO
	 * @param vo_list
	 * @param list
	 */
	private void excevo(List<ExcelBillVO> vo_list, List<Object> list) {
		ExcelBillVO billvo =new ExcelBillVO();
		billvo.setINFO(PuPubVO.getString_TrimZeroLenAsNull(list.get(0)));        //IFNO
		billvo.setBilltype(PuPubVO.getString_TrimZeroLenAsNull(list.get(1)));    //��Ʊ����
		billvo.setBillstatus(PuPubVO.getString_TrimZeroLenAsNull(list.get(2)));  //��Ʊ״̬
		billvo.setBillcode(PuPubVO.getString_TrimZeroLenAsNull(list.get(3)));    //��Ʊ����
		billvo.setBillID(PuPubVO.getString_TrimZeroLenAsNull(list.get(4)));      //��Ʊ����
		billvo.setCustomername(PuPubVO.getString_TrimZeroLenAsNull(list.get(5)));//�ͻ�����
		billvo.setTradename(PuPubVO.getString_TrimZeroLenAsNull(list.get(6)));   //��Ҫ��Ʒ����
		billvo.setTax(PuPubVO.getUFDouble_ZeroAsNull(list.get(7)));              //˰��
		billvo.setAmount(PuPubVO.getUFDouble_ZeroAsNull(list.get(8)));           //�ϼƽ��
		billvo.setPricetax_total(PuPubVO.getUFDouble_ZeroAsNull(list.get(9)));   //��˰�ϼ�
		billvo.setRaw_billcode(PuPubVO.getString_TrimZeroLenAsNull(list.get(10)));//ԭ��Ʊ����
		billvo.setRaw_billID(PuPubVO.getString_TrimZeroLenAsNull(list.get(11)));  //ԭ��Ʊ����
		billvo.setReq_code(PuPubVO.getString_TrimZeroLenAsNull(list.get(12)));    //֪ͨ�����
		billvo.setDrawer(PuPubVO.getString_TrimZeroLenAsNull(list.get(13)));      //��Ʊ��
		billvo.setBill_date(PuPubVO.getString_TrimZeroLenAsNull(list.get(14)));   //��Ʊ����
		billvo.setObsoleter(PuPubVO.getString_TrimZeroLenAsNull(list.get(15)));   //������
		billvo.setObsolete_date(PuPubVO.getString_TrimZeroLenAsNull(list.get(16)));//��������
		billvo.setCustomer_idnumber(PuPubVO.getString_TrimZeroLenAsNull(list.get(17))); //�ͻ�ʶ���
		vo_list.add(billvo);
	}

	//������
	/**
	 * �ļ�ѡ����
	 */
	private JFileChooser fileChooser = null;
	private JFileChooser getUIFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			XlsFileFilter filter = new XlsFileFilter();
			filter.addExtension("xlsx");
			filter.addExtension("xls");
			
			filter.setDescription(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ampub_0","04501000-0468")/*@res "Microsoft Excel �ļ�"*/);
			fileChooser.setFileFilter(filter);

		}
		return fileChooser;
	}
	
	@Override
	protected boolean isActionEnable() {
		return super.isActionEnable();
	}
	
	public String getCellValue(Cell cell) { //��Ԫ
	    String value = ""; 
	    if(null==cell){ 
	      return value; 
	    } 
	    switch (cell.getCellType()) { //��Ԫ����
	    case Cell.CELL_TYPE_NUMERIC: //��Ϊ��������
	      if (DateUtil.isCellDateFormatted(cell)) { //�������ڸ�ʽ
	        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()); //��ȡֵ��ת��Ϊ���ڸ�ʽ
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        value = format.format(date);; 
	      }else { 
	    	  
	    	value = new DecimalFormat("#").format( cell.getNumericCellValue() ); //��ȡֵ��Ϊdouble
	        
	        if(null!=value&&!"".equals(value.trim())){ 
	           String[] item = value.split("[.]"); 
	           if(1<item.length&&"0".equals(item[1])){ 
	             value=item[0]; 
	           } 
	    	//  value =cell.getStringCellValue();
        } 
	      } 
	      break; 
	    case Cell.CELL_TYPE_STRING: //�ַ�������
	      value = cell.getStringCellValue().toString(); 
	      break; 
	    case Cell.CELL_TYPE_FORMULA: //��ʽ
	      value = String.valueOf(cell.getNumericCellValue()); //��ȡ��ֵ
	      if (value.equals("NaN")) {
	        value = cell.getStringCellValue().toString(); 
	      } 
	      break; 
	    case Cell.CELL_TYPE_BOOLEAN: //������
	      value = " "+ cell.getBooleanCellValue(); 
	      break; 
	    case Cell.CELL_TYPE_BLANK: // �հ�
	      value = ""; 
	      break; 
	    case Cell.CELL_TYPE_ERROR:  //����
	      value = ""; 
	      break; 
	    default: 
	      value = cell.getStringCellValue().toString(); 
	  } 
	  if("null".endsWith(value.trim())){ 
	    value=""; 
	  } 
	 return value; 
	  }
	
}
