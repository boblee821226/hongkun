package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFileChooser;

import nc.itf.hkjt.PUB_kaipiao;
import nc.ui.am.common.XlsFileFilter;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.ImpFptzVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImpfptzAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4611851518901961791L;

	public ImpfptzAction() {
		setBtnName("�����������");
		setCode("impfptzAction");
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
	public void doAction(ActionEvent arg0) throws Exception {
		if (getUIFileChooser().showDialog(
				getModel().getContext().getEntranceUI(), "����") == JFileChooser.APPROVE_OPTION) {
			file = getUIFileChooser().getSelectedFile();
			if (file == null || file.getName().trim().length() == 0) {
				MessageDialog.showErrorDlg(
						getModel().getContext().getEntranceUI(),
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"ampub_0", "04501000-0382")/* @res "����" */,
						"��ѡ��Ҫ�����Excel�ļ�!");
				return;
			}
			doImport(file);
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(),
					"�������", "�������");
		}
	}
	
	File file = null;
	private JFileChooser fileChooser = null;

	private JFileChooser getUIFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			XlsFileFilter filter = new XlsFileFilter();
			filter.addExtension("xlsx");
			filter.setDescription(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("ampub_0", "04501000-0468")/*
															 * @res
															 * "Microsoft Excel �ļ�"
															 */);
			fileChooser.setFileFilter(filter);

		}
		return fileChooser;
	}

	public void doImport(File file) throws FileNotFoundException, IOException,
			Exception, BusinessException {
		ArrayList<ImpFptzVO> vos = new ArrayList<ImpFptzVO>();
		InputStream is = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(is);
		for (int numSheet = 0; numSheet < 1; numSheet++) {
			
			Sheet sheet = workbook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				// �ӵڶ��� ��ʼ��������
				
				ImpFptzVO vo = new ImpFptzVO();
				
				Row row = sheet.getRow(rowNum);
				
				Cell cell = row.getCell(0);// �Ͽ���
				if (
					cell == null 
				||  PuPubVO.getString_TrimZeroLenAsNull( cell.getStringCellValue() ) == null
				) {
					continue;
				}
				vo.setKa_code_old( getStringCellValue(cell) );
				
				
				cell = row.getCell(1);// �¿���
				if (
					cell == null 
				||  PuPubVO.getString_TrimZeroLenAsNull( cell.getStringCellValue() ) == null
				) {
					continue;
				}
				vo.setKa_code_new( getStringCellValue(cell) );
				
				
				cell = row.getCell(2);// ��Ʊ���
				if (
					cell == null 
				||  PuPubVO.getUFDouble_ZeroAsNull( cell.getNumericCellValue() ) == null
				) {
					throw new BusinessException("�����Excel��,��" + (rowNum + 1)
							+ "������ ��· Ϊ��,����д���ٽ��е��룡");
				}
				vo.setFpje( PuPubVO.getUFDouble_ZeroAsNull(getStringCellValue(cell)) );
				
				vos.add(vo);
			}
		}
		if (vos.size() == 0) {
			MessageDialog.showErrorDlg(
					getModel().getContext().getEntranceUI(),
					nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
							"ampub_0", "04501000-0382")/* @res "����" */,
					"���������Ϊ��!");
			return;
		}
		
		// ��ձ�������
		if( this.getEditor().getBillCardPanel().getBillModel().getRowCount() > 0 )
		{
			int rowCount = this.getEditor().getBillCardPanel().getBillModel().getRowCount();
			int[] deleteRow = new int[rowCount];
			for(int i=0;i<rowCount;i++)
			{
				deleteRow[i] = i;
			}
			this.getEditor().getBillCardPanel().getBillModel().delLine(deleteRow);
		}
		
		// ��ѯ �ɿ�Ʊ��Ϣ
		String ka_code_query = "''";
		for(int i=0;i<vos.size();i++)
		{
			ImpFptzVO vo = vos.get(i);
			ka_code_query += ",'"+vo.getKa_code_old().toUpperCase()+"','"+vo.getKa_code_new().toUpperCase()+"'" ;
		}
		
		KaipiaoqueryBillVO[] queryBillVO = PUB_kaipiao.bbcx_data(ka_code_query, null,false,"");
		HashMap<String,KaipiaoqueryHVO> queryVO_MAP = new HashMap<String,KaipiaoqueryHVO>();
		for(int i=0;queryBillVO!=null&&i<queryBillVO.length;i++)
		{
			KaipiaoqueryHVO hvo = queryBillVO[i].getParentVO();
			queryVO_MAP.put(hvo.getKa_code().toUpperCase(), hvo);
		}
		
		// ѭ����ֵ
		int count = -1;
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		for(int i=0;i<vos.size();i++)
		{
			ImpFptzVO vo = vos.get(i);
			// �Ͽ���
			billModel.addLine();
			count++;
			billModel.setValueAt( vo.getKa_code_old(), count, "ka_code" );
			billModel.setValueAt( (""+(count+1)*10), count, "vrowno" );
			this.setRowData(billModel, queryVO_MAP.get(vo.getKa_code_old().toUpperCase()), count);
			billModel.setValueAt( UFDouble.ZERO_DBL.sub( vo.getFpje() ), count, "fpje" );
			// �¿���
			billModel.addLine();
			count++;
			billModel.setValueAt( vo.getKa_code_new(), count, "ka_code" );
			billModel.setValueAt( (""+(count+1)*10), count, "vrowno" );
			this.setRowData(billModel, queryVO_MAP.get(vo.getKa_code_new().toUpperCase()), count);
			billModel.setValueAt( vo.getFpje(), count, "fpje" );
		}
		
		// ѭ���ж� �Ƿ�Ϊ ����״̬
		for(int i=0;i<billModel.getRowCount();i++)
		{
			UFDouble result = // ��Ʊ��� + ֮ǰ��Ʊ��� - �ɿ�Ʊ���   ����� ������  ˵����Ʊ�����ˣ�
					  PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i, "fpje"))
				.add( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i, "zqkpje")) )
				.sub( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i, "kkpze")) )
			;
			if( result.compareTo(UFDouble.ZERO_DBL)>0 )
			{
				this.getEditor().getBillCardPanel().getHeadItem("vdef02").setValue("����");
				break;
			}
		}
		
		this.getEditor().getBillCardPanel().getHeadItem("vdef01").setValue("ת��");
	}
	
	private void setRowData(BillModel billModel,KaipiaoqueryHVO vo,int i)
	{
		billModel.setValueAt(vo.getKa_code(), i, "ka_code");	// ����
		billModel.setValueAt(vo.getSykpje(), i, "fpje");		// ��Ʊ���
		billModel.setValueAt(vo.getYkpze(), i, "zqkpje");		// ֮ǰ�ɿ�Ʊ�ܶ�
		billModel.setValueAt(vo.getKkpze(), i, "kkpze");		// �ɿ�Ʊ�ܶ�
		billModel.setValueAt(vo.getVdef03(), i, "vbdef03");		// ת�����
		billModel.setValueAt(vo.getKa_pk(), i, "ka_pk");		// ��pk
		billModel.setValueAt(vo.getKaxing_code(), i, "kaxing_code");	// ����code
		billModel.setValueAt(vo.getKaxing_name(), i, "kaxing_name");	// ����name
		billModel.setValueAt(vo.getKaxing_pk(), i, "kaxing_pk");		// ����pk
		billModel.setValueAt(vo.getVdef02(), i, "vbdef02");			// ������
	}
	
	public String getStringCellValue(Cell cell) { 
		String value = ""; 
		if(null==cell){ 
		  return value; 
		} 
		switch (cell.getCellType()) { 
		case Cell.CELL_TYPE_NUMERIC: 
		  if (DateUtil.isCellDateFormatted(cell)) { 
		    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()); 
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		    value = format.format(date);; 
		  }else { 
		    BigDecimal big=new BigDecimal(cell.getNumericCellValue()); 
		    value = big.toString(); 
		    if(null!=value&&!"".equals(value.trim())){ 
		       String[] item = value.split("[.]"); 
		       if(1<item.length&&"0".equals(item[1])){ 
		         value=item[0]; 
		       } 
		    } 
		  } 
		  break; 
		case Cell.CELL_TYPE_STRING: 
		  value = cell.getStringCellValue().toString(); 
		  break; 
		case Cell.CELL_TYPE_FORMULA: 
		  value = String.valueOf(cell.getNumericCellValue()); 
		  if (value.equals("NaN")) {
		    value = cell.getStringCellValue().toString(); 
		  } 
		  break; 
		case Cell.CELL_TYPE_BOOLEAN: 
		  value = " "+ cell.getBooleanCellValue(); 
		  break; 
		case Cell.CELL_TYPE_BLANK:  
		  value = ""; 
		  break; 
		case Cell.CELL_TYPE_ERROR:  
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
