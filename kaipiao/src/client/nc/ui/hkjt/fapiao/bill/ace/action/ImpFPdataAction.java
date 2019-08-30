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
		this.setBtnName("导入发票数据");	
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
	 * 导入按钮业务逻辑实现功能
	 * 1、查找需要导入的Exel表，并读取数据
	 * 2、将【1】数据封装到VO
	 * 3、通过VO将数据插入到对应的数据库表中
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		/**
		 * 获取Exel表
		 */
//		impfromexel();
		
		/**
		 * 测试 导入会馆账单的 后台任务
		 */
		{
			IHk_fp_billMaintain itf = (IHk_fp_billMaintain)NCLocator.getInstance().lookup(IHk_fp_billMaintain.class.getName());
			itf.executeTest(null);
		}
		/***END***/
		
	}
	File file =null;
	private void impfromexel() throws Exception {
		// TODO 自动生成的方法存根
		/**
		 * 1、获取的文件选择器对话框（模型、上下文、入口，文件按钮==文件选择器）======导入的文件选择器对话框
		 * 
		 */
		if(getUIFileChooser().showDialog(getModel().getContext().getEntranceUI(),"导入")==JFileChooser.APPROVE_OPTION){
			file =getUIFileChooser().getSelectedFile();  //文件查找选择器选择文件
			if (file == null || file.getName().trim().length() == 0) { //若文件为空或名字长度为0，做出选择文件提示
				//错误消息提示框，提示口令
				MessageDialog.showErrorDlg(getModel().getContext().getEntranceUI(), nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ampub_0","04501000-0382")/*@res "错误"*/, "请选择要导入的Excel文件!");
				return;
			}
			InputStream is = new FileInputStream(file);
			Workbook workbook=null;
			if(file.getPath().endsWith(".xls")){
				 workbook= new HSSFWorkbook(is);
			}else if(file.getPath().endsWith(".xlsx")){
				 workbook= new XSSFWorkbook(is);
			}
            // 读文件 一个sheet一个sheet地读取
			/**
			 * VO集合
			 */
			List<ExcelBillVO> vo_list =new ArrayList<ExcelBillVO>(); 
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();
                // 读取数据行
                //数据集合---
                for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                	List<Object> list =new ArrayList<Object>();
                    Row currentRow = sheet.getRow(rowIndex);// 当前行
                    int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                    int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                    for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex-1; columnIndex++) {
                        Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                        String currentCellValue = this.getCellValue(currentCell);// 当前单元格的值
                        list.add(currentCellValue);  
                    }
                    /**
                     * 将行数据封装到VO
                     */
                    excevo(vo_list, list);
                }  
            }
            /**
             * 将VO封装成VO数组
             */
            ExcelBillVO[] billvos = vo_list.toArray(new ExcelBillVO[0]);
            
            IHk_fp_billMaintain itf = (IHk_fp_billMaintain)NCLocator.getInstance().lookup(IHk_fp_billMaintain.class.getName());
            
            int totalRow  = billvos.length;	// excel总行数
            int updateRow = 0;	// 更新的行数
            
            Object result = itf.writeFpInfo(billvos, null);	// 进行回写处理
            
            if( result instanceof Integer )
            {
            	updateRow = PuPubVO.getInteger_NullAs(result, 0);
            }
            
//            String[] pks = HYPubBO_Client.insertAry(billvos);
//            System.out.println("=="+pks);
//            if(true) return;	// 测试
            
            /**
             * 针对 excel 的数据进行回写。
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
//            					.append(excelVO.getCustomername()==null?""	:",fp.i_kpkh = '"+excelVO.getCustomername()+"' ")		//开票客户
//            					.append(excelVO.getTradename()==null?""		:",fp.i_spmc = '"+excelVO.getTradename()+"' ")			//商品名称
//            					.append(excelVO.getTax()==null?""			:",fp.i_se   = "+excelVO.getTax()+" ")					//税额
//            					.append(excelVO.getAmount()==null?""		:",fp.i_je   = "+excelVO.getAmount()+" ")				//金额
//            					.append(excelVO.getPricetax_total()==null?"":",fp.i_jshj = "+excelVO.getPricetax_total()+" ")		//价税合计
//            					.append(excelVO.getBill_date()==null?""		:",fp.i_kprq = '"+excelVO.getBill_date()+"' ")			//开票日期
//            					.append(excelVO.getRaw_billcode()==null?""	:",fp.i_yfpdm= '"+excelVO.getRaw_billcode()+"' ")		//原发票代码
//            					.append(excelVO.getRaw_billID()==null?""	:",fp.i_yfphm= '"+excelVO.getRaw_billID()+"' ")			//原发票号码
//            					.append(excelVO.getObsolete_date()==null?""	:",fp.i_zfrq = '"+excelVO.getObsolete_date()+"' ")		//作废日期
//            					.append(excelVO.getCustomer_idnumber()==null?"":",fp.i_khsbh= '"+excelVO.getCustomer_idnumber()+"' ")	// 客户识别号
//            					.append(" where fp.dr=0 ")
//            					.append(" and fp.fpdm = '"+fpdm+"' ")	// 发票代码
//            					.append(" and fp.fphm = '"+fphm+"' ")	// 发票号码
//            	;
//            	
//            	Integer result = itf.exceSQL(exceSQL.toString());
//            	if(result >0 ){
//            		updateRow+=result;
//            	}
//            }
            
            MessageDialog.showHintDlg(listview, "导入结果"
            		,"Excel总共【"+totalRow+"】行，成功导入了【"+updateRow+"】行。"
            );
            
		}
	}
	/**
	 * 将值封装为VO
	 * @param vo_list
	 * @param list
	 */
	private void excevo(List<ExcelBillVO> vo_list, List<Object> list) {
		ExcelBillVO billvo =new ExcelBillVO();
		billvo.setINFO(PuPubVO.getString_TrimZeroLenAsNull(list.get(0)));        //IFNO
		billvo.setBilltype(PuPubVO.getString_TrimZeroLenAsNull(list.get(1)));    //发票类型
		billvo.setBillstatus(PuPubVO.getString_TrimZeroLenAsNull(list.get(2)));  //发票状态
		billvo.setBillcode(PuPubVO.getString_TrimZeroLenAsNull(list.get(3)));    //发票代码
		billvo.setBillID(PuPubVO.getString_TrimZeroLenAsNull(list.get(4)));      //发票号码
		billvo.setCustomername(PuPubVO.getString_TrimZeroLenAsNull(list.get(5)));//客户名称
		billvo.setTradename(PuPubVO.getString_TrimZeroLenAsNull(list.get(6)));   //主要商品名称
		billvo.setTax(PuPubVO.getUFDouble_ZeroAsNull(list.get(7)));              //税额
		billvo.setAmount(PuPubVO.getUFDouble_ZeroAsNull(list.get(8)));           //合计金额
		billvo.setPricetax_total(PuPubVO.getUFDouble_ZeroAsNull(list.get(9)));   //价税合计
		billvo.setRaw_billcode(PuPubVO.getString_TrimZeroLenAsNull(list.get(10)));//原发票代码
		billvo.setRaw_billID(PuPubVO.getString_TrimZeroLenAsNull(list.get(11)));  //原发票号码
		billvo.setReq_code(PuPubVO.getString_TrimZeroLenAsNull(list.get(12)));    //通知单编号
		billvo.setDrawer(PuPubVO.getString_TrimZeroLenAsNull(list.get(13)));      //开票人
		billvo.setBill_date(PuPubVO.getString_TrimZeroLenAsNull(list.get(14)));   //开票日期
		billvo.setObsoleter(PuPubVO.getString_TrimZeroLenAsNull(list.get(15)));   //作废人
		billvo.setObsolete_date(PuPubVO.getString_TrimZeroLenAsNull(list.get(16)));//作废日期
		billvo.setCustomer_idnumber(PuPubVO.getString_TrimZeroLenAsNull(list.get(17))); //客户识别号
		vo_list.add(billvo);
	}

	//公共类
	/**
	 * 文件选择器
	 */
	private JFileChooser fileChooser = null;
	private JFileChooser getUIFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			XlsFileFilter filter = new XlsFileFilter();
			filter.addExtension("xlsx");
			filter.addExtension("xls");
			
			filter.setDescription(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ampub_0","04501000-0468")/*@res "Microsoft Excel 文件"*/);
			fileChooser.setFileFilter(filter);

		}
		return fileChooser;
	}
	
	@Override
	protected boolean isActionEnable() {
		return super.isActionEnable();
	}
	
	public String getCellValue(Cell cell) { //单元
	    String value = ""; 
	    if(null==cell){ 
	      return value; 
	    } 
	    switch (cell.getCellType()) { //单元类型
	    case Cell.CELL_TYPE_NUMERIC: //若为数字类型
	      if (DateUtil.isCellDateFormatted(cell)) { //若是日期格式
	        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()); //获取值，转换为日期格式
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        value = format.format(date);; 
	      }else { 
	    	  
	    	value = new DecimalFormat("#").format( cell.getNumericCellValue() ); //获取值存为double
	        
	        if(null!=value&&!"".equals(value.trim())){ 
	           String[] item = value.split("[.]"); 
	           if(1<item.length&&"0".equals(item[1])){ 
	             value=item[0]; 
	           } 
	    	//  value =cell.getStringCellValue();
        } 
	      } 
	      break; 
	    case Cell.CELL_TYPE_STRING: //字符串类型
	      value = cell.getStringCellValue().toString(); 
	      break; 
	    case Cell.CELL_TYPE_FORMULA: //公式
	      value = String.valueOf(cell.getNumericCellValue()); //获取数值
	      if (value.equals("NaN")) {
	        value = cell.getStringCellValue().toString(); 
	      } 
	      break; 
	    case Cell.CELL_TYPE_BOOLEAN: //布尔型
	      value = " "+ cell.getBooleanCellValue(); 
	      break; 
	    case Cell.CELL_TYPE_BLANK: // 空白
	      value = ""; 
	      break; 
	    case Cell.CELL_TYPE_ERROR:  //错误
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
