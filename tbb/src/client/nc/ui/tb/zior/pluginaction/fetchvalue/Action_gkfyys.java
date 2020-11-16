package nc.ui.tb.zior.pluginaction.fetchvalue;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zior.tbb.ICalcuateperce;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ms.tb.task.data.TaskDataModel;
import nc.ms.tb.task.data.TaskSheetDataModel;
import nc.ui.dcm.chnlrplstrct.maintain.action.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.tb.model.TBDataCellRefModel;
import nc.ui.tb.zior.TBSheetViewer;
import nc.ui.tb.zior.TbVarAreaUtil;
import nc.ui.tb.zior.pluginaction.edit.model.VarCellValueModel;
import nc.ui.tb.zior.pluginaction.edit.pageaction.CellContentUtil;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.mdm.dim.DimLevel;
import nc.vo.mdm.dim.DimMember;
import nc.vo.mdm.dim.LevelValue;
import nc.vo.mdm.pub.NtbLogger;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.form.excel.ExVarAreaDef;
import nc.vo.tb.form.excel.ExVarDef;
import nc.vo.tb.form.iufo.CellExtInfo;
import nc.vo.tb.form.iufo.TbIufoConst;
import nc.vo.tb.obj.LevelValueOfDimLevelVO;
import nc.vo.tb.zior.pluginaction.fetchvalue.GkfyysVO;

import com.ufsoft.table.Cell;
import com.ufsoft.table.CellsModel;

/**
 * ��ڷ���Ԥ���
 */
public class Action_gkfyys implements Action_itf {

	IUAPQueryBS iquerybs = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
	IVOPersistence ip = (IVOPersistence) NCLocator.getInstance().lookup(IVOPersistence.class);
	ICalcuateperce ica = (ICalcuateperce) NCLocator.getInstance().lookup(ICalcuateperce.class);
	
	@Override
	public void doAction(
			StringBuffer ormessage,
			int year,
			TBSheetViewer tbSheetViewer,
			TaskDataModel taskDataModel,
			TaskSheetDataModel tsmodel,
			int rowno, int colno,
			CellsModel csModel
		) throws BusinessException {

		if (tbSheetViewer.getSelectedCell().get(0).getRow() <7) {
			MessageDialog.showErrorDlg(null, "��ʾ","��ѡ���8��֮���Ԥ����Ŀ�µ�����ȡ��");
			return;
		}
		
		Integer beginRow = 8;	// ��ʼ��
		Integer[] cols = new Integer[]{
			6,	// 0Ԥ����Ŀ����
			8,	// 1��Ӧ��
			9,	// 2�Ƿ��̯
			10,	// 3��ͬ��
			12,	// 4˰��
			11,	// 5������Դ
		};
		Integer monthBeginCol = 13 - 1;
		
//		refPane.setMultiSelectedEnabled(true);
		String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
		String pk_dept = tsmodel.getMdTask().getPk_dataent();	// Ԥ��ά�� ������
		UIRefPane refPane = new UIRefPane("ά��ѡ��");
		TBDataCellRefModel refModel_szxm = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel refModel_gys = (TBDataCellRefModel) refPane.getRefModel();
		
		List<Cell> cells1 = tbSheetViewer.getSelectedCell();
		Cell cell = cells1.get(cells1.size() - 1);
		CellExtInfo cInfo1 = (CellExtInfo) cell.getExtFmt(TbIufoConst.tbKey);
		int varType = cInfo1.getExVarAreaDef().varAreaType;
		int celNum = varType == ExVarAreaDef.varAreatType_ROW ? cell.getCol() : cell.getRow();
		
		LevelValueOfDimLevelVO dim_szxm = new LevelValueOfDimLevelVO(celNum, "MEASURE", null, null, taskDataModel.getMdTask());
		LevelValueOfDimLevelVO dim_gys = new LevelValueOfDimLevelVO(celNum, "SUPPLIER", null, null, taskDataModel.getMdTask());
		ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo1);
		Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(cell, cInfo1, exVarDef, tbSheetViewer.getCellsPane());
		TbVarAreaUtil.initTBDataCellRefModel(refModel_szxm, dim_szxm, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_szxm.getData();
		TbVarAreaUtil.initTBDataCellRefModel(refModel_gys, dim_gys, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_szxm.getData();
		
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" htb.vbdef1 pk_srxm, ")	// 0����֧��Ŀpk
				.append(" szxm.name srxm_name, ")	// 1����֧��Ŀname
				.append(" substr(htb.vbdef2,1,10) fkrq, ")	// 2����������yyyy-mm-dd
				.append(" htb.norigmny, ")		// 3����˰���
				.append(" htb.norigtaxmny, ")	// 4����˰���
				.append(" fplx.name fplx, ")	// 5����Ʊ����
				.append(" ht.vbillcode, ")		// 6����ͬ��
				.append(" ht.cvendorid, ")		// 7����Ӧ��pk
				.append(" gys.name, ")			// 8����Ӧ��name
				.append(" sl.name sl ")			// 9��˰��%
				.append(" from ct_pu ht ")
				.append(" inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")
				.append(" left join bd_defdoc fplx on ht.vdef3 = fplx.pk_defdoc ")
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")
				.append(" left join bd_defdoc sl on ht.vdef4 = sl.pk_defdoc ")
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.vtrantypecode = 'Z2-Cxx-01' ")
				.append(" and szxm.code not in ('2005', '2022') ")
				.append(" and ht.blatest = 'Y' ")
				.append(" and ht.fstatusflag in (1, 6) ")
				.append(" and htb.norigtaxmny <> 0.00 ")
				.append(" and substr(htb.vbdef2,1,10) between '").append(year).append("-01-01' and '").append(year).append("-12-31' ")
				.append(" and ht.depid = '").append(pk_dept).append("' ")
				.append(" order by htb.vbdef1,ht.cvendorid,htb.vbdef1 ")
		;
		ArrayList list = (ArrayList)iquerybs.executeQuery(querySQL.toString(), new ArrayListProcessor());
		// ��װHashMap
		HashMap<String,GkfyysVO> dataMap = new HashMap<>();
		for (Object obj : list) {
			Object[] item = (Object[])obj;
			String hth = PuPubVO.getString_TrimZeroLenAsNull(item[6]);// ��ͬ��
			String pk_szxm = PuPubVO.getString_TrimZeroLenAsNull(item[0]);// ��֧��Ŀpk
			String pk_gys = PuPubVO.getString_TrimZeroLenAsNull(item[7]);// ��Ӧ��pk
			String szxm_name = PuPubVO.getString_TrimZeroLenAsNull(item[1]);// ��֧��Ŀname
			String gys_name = PuPubVO.getString_TrimZeroLenAsNull(item[8]);//��Ӧ��name
			String sl = PuPubVO.getString_TrimZeroLenAsNull(item[9]);// ˰��
			String fkrq = PuPubVO.getString_TrimZeroLenAsNull(item[2]);// ��������
			String fplx = PuPubVO.getString_TrimZeroLenAsNull(item[5]);// ��Ʊ����
			UFDouble je = PuPubVO.getUFDouble_NullAsZero(item[4]);	// ��˰���
			
			UFDouble sl_d = UFDouble.ZERO_DBL;
			if ("ר�÷�Ʊ".equals(fplx) && sl != null) {
				sl_d = new UFDouble(sl.replace("%", ""));
			}
			
			String[] yyyymmdd = fkrq.split("-");
			String mm = yyyymmdd[1];
//			Integer month = PuPubVO.getInteger_NullAs(mm, 1);
			
			String key = hth + "@@@@" + pk_szxm + "@@@@" + pk_gys;
			if (dataMap.containsKey(key)) {
				this.setMm(dataMap.get(key), mm, je);
			} else {
				GkfyysVO vo = new GkfyysVO();
				vo.setPk_srxm(pk_szxm);
				vo.setVbillcode(hth);
				vo.setCvendorid(pk_gys);
				vo.setFplx(fplx);
				vo.setSl(sl_d);
				vo.setSrxm_name(szxm_name);
				vo.setGys_name(gys_name);
				this.setMm(vo, mm, je);
				dataMap.put(key, vo);
			}
			
		}
		
		CellContentUtil util = new CellContentUtil(tbSheetViewer);
		util.addMultiLine(1, dataMap.size());
		Integer currRow = beginRow;
		
		for (Entry<String,GkfyysVO> item : dataMap.entrySet()) {
			GkfyysVO vo = item.getValue();
			csModel.setCellValue(currRow, cols[0], vo.getSrxm_name());//0Ԥ����Ŀ����
			csModel.setCellValue(currRow, cols[1], vo.getGys_name());//1��Ӧ��
			csModel.setCellValue(currRow, cols[2], "N");//2�Ƿ��̯
			csModel.setCellValue(currRow, cols[3], vo.getVbillcode());//3��ͬ��
			csModel.setCellValue(currRow, cols[4], vo.getSl());//4˰��
			csModel.setCellValue(currRow, cols[5], "ȡ��������");//5ȡ��������
			
			for (int mm = 1; mm <= 12; mm ++) {
				String key = (mm < 10 ? "0" : "") + mm;
				UFDouble je = PuPubVO.getUFDouble_ZeroAsNull(this.getMm(vo, key));
				if (je != null) {
					csModel.setCellValue(currRow, monthBeginCol + mm, je);// �·ݽ��
				}
			}
			
			// ά�ȣ���Ŀ
			DimMember dm_szxm = refModel_szxm.getDimMember(vo.getPk_srxm());
			if (null != dm_szxm) {
				DimMember[] dms_szxm = new DimMember[]{dm_szxm};
				VarCellValueModel vm_szxm = new VarCellValueModel(0, csModel, currRow, 6, dms_szxm, 1);
				try {
					vm_szxm.fireCellValueChaned();
				} catch (BusinessException be) {
					NtbLogger.error(be);
				}
			}
			// ά�ȣ���Ӧ��
			DimMember dm_gys = refModel_gys.getDimMember("SUPPLIER"+vo.getCvendorid());
			if (null != dm_gys) {
				DimMember[] dms_gys = new DimMember[]{dm_gys};
				VarCellValueModel vm_gys = new VarCellValueModel(0, csModel, currRow, 8, dms_gys, 1);
				try {
					vm_gys.fireCellValueChaned();
				} catch (BusinessException be) {
					NtbLogger.error(be);
				}
			}
			currRow ++;
		}
		
//		for (Object obj : list) {
//			Object[] item = (Object[])obj;
//			String szxm_name = PuPubVO.getString_TrimZeroLenAsNull(item[1]);// ��֧��Ŀname
//			String hth = PuPubVO.getString_TrimZeroLenAsNull(item[6]);// ��ͬ��
//			String gys_name = PuPubVO.getString_TrimZeroLenAsNull(item[8]);//��Ӧ��name
//			String sl = PuPubVO.getString_TrimZeroLenAsNull(item[9]);// ˰��
//			String fkrq = PuPubVO.getString_TrimZeroLenAsNull(item[2]);// ��������
//			String fplx = PuPubVO.getString_TrimZeroLenAsNull(item[5]);// ��Ʊ����
//			UFDouble je = PuPubVO.getUFDouble_NullAsZero(item[4]);	// ��˰���
//			String isFt = "N";	// �Ƿ��̯
//			
//			UFDouble sl_d = UFDouble.ZERO_DBL;
//			if ("ר�÷�Ʊ".equals(fplx) && sl != null) {
//				sl_d = new UFDouble(sl.replace("%", ""));
//			}
//			
//			String[] yyyymmdd = fkrq.split("-");
//			Integer month = PuPubVO.getInteger_NullAs(yyyymmdd[1], 1);
//			csModel.setCellValue(currRow, monthBeginCol + month, je);// �·ݽ��
//			
//			csModel.setCellValue(currRow, cols[0], szxm_name);//0Ԥ����Ŀ����
//			csModel.setCellValue(currRow, cols[1], gys_name);//1��Ӧ��
//			csModel.setCellValue(currRow, cols[2], isFt);//2�Ƿ��̯
//			csModel.setCellValue(currRow, cols[3], hth);//3��ͬ��
//			csModel.setCellValue(currRow, cols[4], sl_d);//4˰��
//			
//			currRow++;
//		}
		
//		csModel.setCellValue(8, 6, "�豸����");
//		csModel.setCellValue(9, 6, "�豸����");
//		csModel.setCellValue(10, 6, "�豸����");
		
		// �����������
//		try{
//			if (tbSheetViewer.getSelectedCell().get(0).getRow() <7) {
//				MessageDialog.showErrorDlg(null, "��ʾ","��ѡ���8��֮���Ԥ����Ŀ�µ�����ȡ��");
//				return;
//			}
//			int y = 0;
//			UIRefPane refPane = new UIRefPane("ά��ѡ��");/* -=notranslate=- */
//			refPane.setMultiSelectedEnabled(true);
//			TBDataCellRefModel tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
//			TBDataCellRefModel tBDataCellRefModel1 = (TBDataCellRefModel) refPane.getRefModel();
////			TBDataCellRefModel tBDataCellRefModelwdjh = (TBDataCellRefModel) refPane.getRefModel();
//			TBDataCellRefModel tBDataCellRefModelqdlx = (TBDataCellRefModel) refPane.getRefModel();
//			List<Cell> cells1 = tbSheetViewer.getSelectedCell();
//			Cell cell = cells1.get(cells1.size() - 1);
//			CellExtInfo cInfo1 = (CellExtInfo) cell.getExtFmt(TbIufoConst.tbKey);
//			int varType = cInfo1.getExVarAreaDef().varAreaType;
//			int celNum = varType == ExVarAreaDef.varAreatType_ROW ? cell.getCol() : cell.getRow();
//			LevelValueOfDimLevelVO vo = new LevelValueOfDimLevelVO(celNum, "CUSTOM", null, null, taskDataModel.getMdTask());
//			LevelValueOfDimLevelVO fjhDim = new LevelValueOfDimLevelVO(celNum, "MARBAS", null, null, taskDataModel.getMdTask());
////			LevelValueOfDimLevelVO wdjhDim = new LevelValueOfDimLevelVO(celNum, "WDJH", null, null, taskDataModel.getMdTask());
//			LevelValueOfDimLevelVO qdlxDim = new LevelValueOfDimLevelVO(celNum, "QDLX", null, null, taskDataModel.getMdTask());
//			ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo1);
//			refPane.setMultiSelectedEnabled(true);
//			String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
//			String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
//			String key = tsmodel.getMdTask().getPk_dataent();// ��������
//			String mainorg = iquerybs.executeQuery("select pk_corp from org_orgs where pk_org='" + key + "'", new ColumnProcessor()).toString();// ��˾
//			Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(cell, cInfo1, exVarDef, tbSheetViewer.getCellsPane());
//			//��������
//			TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModelqdlx, qdlxDim, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
//			tBDataCellRefModelqdlx.getData();
//			//�ͻ�
//		    TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel, vo, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
//		    tBDataCellRefModel.getData();
//		    //�����
//			TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel1, fjhDim, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
//			tBDataCellRefModel1.getData();
//			Boolean flag=false;
//			String starttime=(year-1)+"-12-26 00:00:00";
//			String endtime=year+"-12-25 23:59:59";
//			String salething1sql = "select cubecode from tb_md_workbook  where pk_obj='" + tsmodel.getMdSheet().getPk_workbook() + "' and nvl(dr,0)=0";
//			code = iquerybs.executeQuery(salething1sql, new ColumnProcessor());
//			int colbm=0;
//			int rowbm = 0;
//			int selectrow=	tbSheetViewer.getSelectedCell().get(0).getRow() - 2;
//			// ��ѯ�ͻ����Ƶ�һ�γ��ֵ���
//			for (int i3 = 5; i3 < rowno; i3++) {
//				for (int t4 = 3; t4 < colno; t4++) {
//					if (null != tsmodel.getCellElement(i3, t4)){
//						if (null != tsmodel.getCellElement(i3, t4).getValue()) {
//							if (("�ͻ�����").equals(tsmodel.getCellElement(i3, t4).getValue().toString().trim())) {
//								flag=true;
//								rowbm = i3;
//								colbm=t4;
//								break;
//							}
//						}
//					}
//				}
//				if(flag){
//					break;
//				}
//			}
//			int h = rowbm;//��¼������
//			rowbm = selectrow+1;
//			//1��Ч  6��ֹ2020-07-23  3����ͨ�� ������
//			String countsql="SELECT pk_ct_sale FROM ct_sale_b WHERE	vbdef4>='"+starttime+"' AND vbdef3<='"+endtime+"'  AND vbdef1 = '1001N51000000001UQ09' AND NVL (dr, 0) = 0"
//						+" AND pk_ct_sale IN (select dd.pk_ct_sale from ( SELECT pk_ct_sale,DECODE(vdef19,'~',invallidate,vdef19) invallidate1"
//						+" FROM	ct_sale ct_sale WHERE ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y' and fstatusflag in(1,3) AND ct_sale.pk_org = '"+mainorg+"' AND ct_sale.pk_group = '0001N510000000000EGY'"
//						+" AND dr = 0	AND valdate <= '"+endtime+"')dd  where dd.invallidate1 >= '"+starttime+"' )GROUP BY pk_ct_sale,vbdef5";
////			String countsql="select pk_ct_sale from ct_sale_b where vbdef4>='"+starttime+"' and vbdef3<='"+endtime+"' and vbdef1='1001N51000000001UQ09'  and nvl(dr,0)=0 and pk_ct_sale in (SELECT ct_sale.pk_ct_sale FROM ct_sale ct_sale" 
////			+" WHERE ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y' AND ct_sale.pk_org='"+mainorg+"' AND ct_sale.pk_group = '0001N510000000000EGY' and dr=0 and  invallidate>='"+starttime+"' and valdate<='"+endtime+"') group by  pk_ct_sale,vbdef5";
//			ArrayList<Map<String, String>> count = (ArrayList<Map<String, String>>)iquerybs.executeQuery(countsql,new MapListProcessor());
////			String countqtsql="	SELECT dd.pk_ct_sale FROM (SELECT	pk_ct_sale,DECODE (vdef19,'~',invallidate,	vdef19) invallidate1 FROM ct_sale ct_sale WHERE	ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y' AND fstatusflag IN (1, 3)"
////					+"	AND ct_sale.pk_org = '"+mainorg+"'	AND ct_sale.pk_group = '0001N510000000000EGY'	AND dr = 0 AND valdate <= '"+endtime+"') dd left join ct_sale_b on ct_sale_b.pk_ct_sale=dd.pk_ct_sale "
////					+" WHERE	dd.invallidate1 >= '"+starttime+"' and 	ct_sale_b.vbdef4 >= '"+starttime+"' AND ct_sale_b.vbdef3 <= '"+endtime+"' AND ct_sale_b.vbdef1 IN (	'1001N5100000000HNWBR',	'1001N5100000006XQ06S','1001N5100000006XQ04W') group by dd.pk_ct_sale";
////			ArrayList<Map<String, String>> countqt = (ArrayList<Map<String, String>>)iquerybs.executeQuery(countqtsql,new MapListProcessor());
//			if(count!=null&&count.size()>0){
//				CellContentUtil util = new CellContentUtil(tbSheetViewer);
//				util.addMultiLine(1,count.size());
//				//��ѯ���з���������2019-12-26---2020-12-25���ĵ���
//				String sql="select dd.cusname cusname,dd. fjhname fjhname,dd.pk_ct_sale,dd.pk_customer,dd.vdef16,dd.valdate,dd.invallidate,dd.vdef5 from ( SELECT bd_customer. NAME cusname,"
//						+"	bd_defdoc. NAME fjhname,ct_sale.pk_ct_sale,ct_sale.pk_customer,ct_sale.vdef16,ct_sale.valdate,ct_sale.vdef5,DECODE(vdef19,'~',invallidate,vdef19) invallidate FROM	ct_sale ct_sale "
//						+"	LEFT JOIN bd_customer bd_customer ON ct_sale.pk_customer = bd_customer.pk_customer LEFT JOIN bd_defdoc bd_defdoc ON ct_sale.vdef16 = bd_defdoc.pk_defdoc WHERE	bd_defdoc.pk_defdoclist IN (SELECT pk_defdoclist"
//						+"	FROM bd_defdoclist WHERE NAME = '�����' AND NVL (dr, 0) = 0 ) and  ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y'  and ct_sale.fstatusflag in(1,3) AND ct_sale.pk_org='"+mainorg+"' AND ct_sale.pk_group = '0001N510000000000EGY'"
//						+"  AND ct_sale.dr = 0 AND ct_sale.valdate <= '"+endtime+"')dd  where dd.invallidate >= '"+starttime+"'  ORDER BY dd.pk_customer";
////				String sql="SELECT bd_customer.name cusname,bd_defdoc.name fjhname,ct_sale.pk_ct_sale,ct_sale.pk_customer,ct_sale.vdef16,ct_sale.valdate,ct_sale.invallidate,ct_sale.vdef5 FROM ct_sale ct_sale left join bd_customer bd_customer"
////						+" on ct_sale.pk_customer=bd_customer.pk_customer left join  bd_defdoc bd_defdoc on  ct_sale.vdef16 =  bd_defdoc.pk_defdoc    WHERE bd_defdoc.pk_defdoclist in(select pk_defdoclist from bd_defdoclist where name='�����' and nvl(dr,0)=0) and ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y'"
////						+" AND ct_sale.pk_group = '0001N510000000000EGY' AND ct_sale.pk_org='"+mainorg+"' and invallidate>='"+starttime+"' and valdate<='"+endtime+"' ORDER BY ct_sale.pk_customer";
//				ArrayList<Map<String, String>> emps = (ArrayList<Map<String, String>>)iquerybs.executeQuery(sql,new MapListProcessor());
//				if(emps!=null&&emps.size()>0){
//					for(int i=0;i<emps.size();i++){//ѭ��ÿ�����ݣ��������ں͵��ۣ���Ӧ�����е�������һ��
//						String zjbz=null;
//						String pk_zjbz=null;
//						String pk_customer="";
//						String fjh="";
//						String area=null;
//						String valdate =null;
//						String invallidate=null;
//						String pk_ct_sale="";
//						String rzj=null;
//						String vbdef3=null;
//						String vbdef4=null;
//						String cusname=emps.get(i).get("cusname")==null?"":emps.get(i).get("cusname").toString();
//						String fjhname=emps.get(i).get("fjhname")==null?"":emps.get(i).get("fjhname").toString();
//						pk_ct_sale=emps.get(i).get("pk_ct_sale")==null?"":emps.get(i).get("pk_ct_sale").toString();
//						pk_customer=emps.get(i).get("pk_customer")==null?"":emps.get(i).get("pk_customer").toString();
//						fjh = emps.get(i).get("vdef16")==null?"":emps.get(i).get("vdef16").toString();//�����
//						area= emps.get(i).get("vdef5")==null?"":emps.get(i).get("vdef5").toString();//���
//						valdate=emps.get(i).get("valdate")==null?"":emps.get(i).get("valdate").toString();//��ʼǩ������
//						invallidate=emps.get(i).get("invallidate")==null?"":emps.get(i).get("invallidate").toString();//��ֹ����
//						String sqlrzj="select vbdef5 from(select  row_nUMBER() OVER(partition by vbdef5 order by vbdef3) as id, vbdef5,vbdef3  from ct_sale_b where pk_ct_sale='"+pk_ct_sale+"' and  vbdef3<='"+endtime+"' and vbdef1='1001N51000000001UQ09'  "
//								+" and vbdef4>='"+starttime+"' and nvl(dr,0)=0  order by vbdef3)  where id = 1";
//						ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>)iquerybs.executeQuery(sqlrzj,new MapListProcessor());
//						int size=list.size();
//						if(list!=null&&size>0){
//							for(int k=0;k<size;k++){
//								tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
//								rzj =list.get(k).get("vbdef5").toString();
//								String sqlone="select vbdef3,vbdef4 from ct_sale_b where pk_ct_sale='"+pk_ct_sale+"' and vbdef3<='"+endtime+"' and vbdef4>='"+starttime+"'  and vbdef1='1001N51000000001UQ09' and vbdef5= '"+rzj+"' and nvl(dr,0)=0 order by vbdef3";
//								ArrayList<Map<String, Object>> listone = (ArrayList<Map<String, Object>>)iquerybs.executeQuery(sqlone,new MapListProcessor());
//								int listsize=listone.size();
//								if(listone!=null&&listsize>0){
//									//ֻ��һ�� ����𣬲��۷�Ϊ�����׶��£�������������ȡ��һ�еĿ�ʼ���ں�2019-12-26�Ƚϣ�ȡ���һ�еĽ�ֹ���ں�2020-12-25�Ƚ�
//									//���������ʱ��Ԥ��������У���һ�У���ʼ����ȡ����һ������𣬰����¼����򣬵�һ�еĿ�ʼ���ں�2019-12-26�Ƚ�ȡ2019-12-26��ģ���ֹ����ȡ����һ����������һ�еĽ�ֹ����
//									//�ڶ��У���ʼ����ȡ���ڶ�������𣬵�һ�еĿ�ʼ���ڣ���ֹ���ڣ�ȡ���һ�еĽ�ֹ���ں�2020-12-25�Ƚ�
//									//�������������ʱ����һ�����������һ�����������ڣ�ͬ���������һ��,
//									if(size==1){
//										valdate=listone.get(0).get("vbdef3").toString();
//										invallidate=listone.get(listsize-1).get("vbdef4").toString();
//										if(valdate.compareTo(starttime)<0||valdate.compareTo(starttime)==0){
//											vbdef3=starttime.substring(0, 10);
//										}else{
//											vbdef3=valdate.substring(0, 10);
//										}
//										if(invallidate.compareTo(endtime)<0||invallidate.compareTo(endtime)==0){
//											vbdef4=invallidate.substring(0, 10);
//										}else{
//											vbdef4=endtime.substring(0, 10);
//										}
//									}else if(size==2){
//										valdate=listone.get(0).get("vbdef3").toString();
//										invallidate=listone.get(listsize-1).get("vbdef4").toString();
//										if(k==0){
//											if(valdate.compareTo(starttime)<0||valdate.compareTo(starttime)==0){
//												vbdef3=starttime.substring(0, 10);
//											}else{
//												vbdef3=valdate.substring(0, 10);
//											}
//											vbdef4=invallidate.substring(0, 10);
//										}else{
//											vbdef3=valdate.substring(0, 10);
//											if(invallidate.compareTo(endtime)<0||invallidate.compareTo(endtime)==0){
//												vbdef4=invallidate.substring(0, 10);
//											}else{
//												vbdef4=endtime.substring(0, 10);
//											}
//											zjbz="������һ";
//											pk_zjbz="1001N5100000000V2BBZ";
//										}
//									}else{
//										valdate=listone.get(0).get("vbdef3").toString();
//										invallidate=listone.get(listsize-1).get("vbdef4").toString();
//										if(k==0){
//											if(valdate.compareTo(starttime)<0||valdate.compareTo(starttime)==0){
//												vbdef3=starttime.substring(0, 10);
//											}else{
//												vbdef3=valdate.substring(0, 10);
//											}
//											vbdef4=invallidate.substring(0, 10);
//										}else if(k==(listsize-1)){
//											vbdef3=valdate.substring(0, 10);
//											if(invallidate.compareTo(endtime)<0||invallidate.compareTo(endtime)==0){
//												vbdef4=invallidate.substring(0, 10);
//											}else{
//												vbdef4=endtime.substring(0, 10);
//											}
//											if(k==1){
//												zjbz="������һ";
//												pk_zjbz="1001N5100000000V2BBZ";
//											}else if(k==2){
//												zjbz="��������";
//												pk_zjbz="1001N5100000000V2BC0";
//											}else if(k==3){
//												zjbz="��������";
//												pk_zjbz="1001N5100000000V2BC1";
//											}else if(k==4){
//												zjbz="��������";
//												pk_zjbz="1001N5100000000V2BC2";
//											}else if(k==5){
//												zjbz="��������";
//												pk_zjbz="1001N5100000000V2BC3";
//											}
//										
//										}else{
//											vbdef3=valdate.substring(0, 10);
//											vbdef4=invallidate.substring(0, 10);
//											if(k==1){
//												zjbz="������һ";
//												pk_zjbz="1001N5100000000V2BBZ";
//											}else if(k==2){
//												zjbz="��������";
//												pk_zjbz="1001N5100000000V2BC0";
//											}else if(k==3){
//												zjbz="��������";
//												pk_zjbz="1001N5100000000V2BC1";
//											}else if(k==4){
//												zjbz="��������";
//												pk_zjbz="1001N5100000000V2BC2";
//											}else if(k==5){
//												zjbz="��������";
//												pk_zjbz="1001N5100000000V2BC3";
//											}
//										}
//									}
//									rowbm++;
//									//ѭ��setֵ
//									for (int t4 = 3; t4 < colno; t4++) {
//										if (null != tsmodel.getCellElement(h, t4)){
//											if (null != tsmodel.getCellElement(h, t4).getValue()) {
//												if (tsmodel.getCellElement(h, t4).getValue().toString().trim().contains("������")) {
//													csModel.setCellValue(rowbm, t4, zjbz);
//												}else if (("�ͻ�����").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
//													csModel.setCellValue(rowbm, t4, cusname);
//											    }else if (("�������").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
//													csModel.setCellValue(rowbm, t4, area);
//											    }else if (tsmodel.getCellElement(h, t4).getValue().toString().trim().contains("̯λ����")) {
//													csModel.setCellValue(rowbm, t4, fjhname);
//												}else if (("��ʼ����").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
//													csModel.setCellValue(rowbm, t4,java.sql.Date.valueOf(vbdef3));
////													tsmodel.getCellAt(rowbm, t4).setValue(java.sql.Date.valueOf(vbdef3));
//												}else if (("��ֹ����").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
//													csModel.setCellValue(rowbm, t4,java.sql.Date.valueOf(vbdef4));
////													tsmodel.getCellAt(rowbm, t4).setValue(java.sql.Date.valueOf(vbdef4));
//												}else if (("�����").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
//													csModel.setCellValue(rowbm, t4,rzj);
////													tsmodel.getCellAt(rowbm, t4).setValue(rzj);
//											    }
//											}
//										}
//									}
////									 ������������ά����Ϣ
//									if(pk_zjbz!=null&&pk_zjbz.length()>0&&!"".equals(pk_zjbz)){
//										DimMember dimMemberqdlx = tBDataCellRefModelqdlx.getDimMember(pk_zjbz);
//										if (null != dimMemberqdlx) {
//											DimMember[] dmsqdlx = new DimMember[1];
//											dmsqdlx[0] = dimMemberqdlx;
//											VarCellValueModel varCellModelqdlx = new VarCellValueModel(0, getCellsModel(), rowbm, colbm-1, dmsqdlx, 1);
//											try {
//												varCellModelqdlx.fireCellValueChaned();
//											} catch (BusinessException be) {
//												NtbLogger.error(be);
//											}
//										}	
//									}
////									 ���ÿͻ�ά����Ϣ
//									DimMember dimMember = tBDataCellRefModel.getDimMember("CUSTOM"+pk_customer);
//									if (null != dimMember) {
//										DimMember[] dms = new DimMember[1];
//										dms[0] = dimMember;
//										VarCellValueModel varCellModel = new VarCellValueModel(0, getCellsModel(), rowbm, colbm, dms, 1);
//										try {
//											// ���õ�Ԫ��Ķ�ά��Ϣ
//											varCellModel.fireCellValueChaned();
//										} catch (BusinessException be) {
//											NtbLogger.error(be);
//										}
//									}
//									// ���÷����ά����Ϣ
//									DimMember dimMember1 = tBDataCellRefModel1.getDimMember("MARBAS"+fjh);
//									if (null != dimMember1) {
//										DimMember[] dms1 = new DimMember[1];
//										dms1[0] = dimMember1;
//										VarCellValueModel varCellModel1 = new VarCellValueModel(0, getCellsModel(), rowbm, colbm+2, dms1, 1);
//										try {
//											varCellModel1.fireCellValueChaned();
//										} catch (BusinessException be) {
//											NtbLogger.error(be);
//										}
//									}
//									tbSheetViewer.getTable().repaint();
//								}
//					        }
//				        }								
//					 }
//				  }
//			   }
//			}catch (Exception e){
//			     NtbLogger.print(e);
//	    } 
//    	int col = tsmodel.getColNum();
//    	rowno = tsmodel.getRowNum();
//		UFDate beginTime = null;
//		UFDate endTime = null;
//		int coldate = 0;
//		for (int t4 = 10; t4 < col; t4++) {
//			for (int i3 = 2; i3 < 8; i3++) {
//				if (tsmodel.getCellElement(i3, t4).toString().equals("��ʼ����")) {
//					coldate = t4;
//				}
//			}
//		}
//		for (int i3 = 7; i3 < rowno - 6; i3++) {
//			try {
//				if (null != csModel.getCellValue(i3, coldate) && !"".equals(csModel.getCellValue(i3, coldate))
//						&& null != (csModel.getCellValue(i3, coldate + 1)) && !"".equals(csModel.getCellValue(i3, coldate + 1))) {
//					beginTime = new UFDate(sim.parse(csModel.getCellValue(i3, coldate).toString()));// ��ʼ����
//					endTime = new UFDate(csModel.getCellValue(i3, coldate + 1).toString());// ��������
//				}else {
//					int nul = 1;
//					for (int i2 = 0; i2 < rowno; i2++) {// ���������
//						if (nul < 13) {
//							csModel.setCellValue(i3, coldate + i2 - 12, null);
//							nul++;
//						}
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if (beginTime != null && endTime != null) {
//
//				Calendar cal1 = Calendar.getInstance();
//				cal1.setTime(beginTime.toDate());
//				long time1 = cal1.getTimeInMillis();
//				Calendar cal2 = Calendar.getInstance();
//				cal2.setTime(endTime.toDate());
//				long time2 = cal2.getTimeInMillis();
//				if (time1 > time2) {// �ж����ڸ�ʽ
//					i3 = i3 + 1;
//					ormessage.append("��ǰ���").append(i3).append("��������д����\n");
//					continue;
//				}
//				String begif = Integer.toString(yea);// �����ʼ����
//				String sql = "select bah.* from bd_accperiodmonth bah join bd_accperiod ba on ba.pk_accperiod=bah.pk_accperiod where ba.periodyear='" + begif
//						+ "'	";
//				ArrayList<AccperiodmonthVO> amonth = null;
//				try {
//					amonth = (ArrayList<AccperiodmonthVO>) iquerybs.executeQuery(sql, new BeanListProcessor(AccperiodmonthVO.class));
//				} catch (BusinessException e1) {
//					e1.printStackTrace();
//				}
//				Calendar cal3 = Calendar.getInstance();
//				int tt = 0;
//				cal3.setTime(amonth.get(0).getBegindate().toDate());
//				long time3 = cal3.getTimeInMillis();
//				Calendar cal4 = Calendar.getInstance();
//				cal4.setTime(amonth.get(11).getEnddate().toDate());
//				long time4 = cal4.getTimeInMillis();
//				long dayss = 1;
//				if (time1 < time3) {
//					beginTime = amonth.get(0).getBegindate();
//				}
//				if (time2 > time4) {
//					endTime = amonth.get(11).getEnddate();
//				}
//				UFDate b = null;
//				UFDate e = null;
//				int mon = beginTime.getMonth();
//				if (beginTime.getYear() != yea) {
//					tt = 0;
//				} else {
//					tt = mon - 1;
//				}
//				b = beginTime;
//				e = endTime;
//				if (time1 > time4) {// �ж��Ƿ񳬳�����ڼ�
//					continue;
//				}
//				for (int i = tt; i < amonth.size(); i++) {
//					int num1 = amonth.get(i).getBegindate().compareTo(b);
//					if (num1 == 1 || num1 == 0) {
//						b = amonth.get(i).getBegindate();
//					} else if (num1 == -1) {
//						b = beginTime;
//					}
//					int num2 = amonth.get(i).getEnddate().compareTo(endTime);
//					if (b.getDay() == 26 && e.getDay() == 26 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) < 26
//							&& b.getYear() == yea - 1) {
//						e = endTime;
//					} else if (b.getDay() == 26 && e.getDay() == 26
//							&& ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) < 26) {
//						e = endTime;
//						i++;
//					} else if (b.getDay() > 26 && tt != 0 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) > 26) {
//						e = amonth.get(tt + 1).getEnddate();
//						i++;
//					} else if (b.getDay() > 26 && tt == 0 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) > 26) {
//						e = amonth.get(tt).getEnddate();
//					} else if (b.getDay() > 26 && tt == 0 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) < 26) {
//						e = endTime;
//						i++;
//					} else if (b.getDay() > 26 && tt != 0 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) < 26) {
//						e = endTime;
//						i++;
//					} else if (num2 == -1 || num2 == 0) {
//						e = amonth.get(i).getEnddate();
//					} else if (num2 == 1) {
//						e = endTime;
//					}
//					double qw = e.toDate().getTime();
//					double qe = b.toDate().getTime();
//					if ((qw - qe) / (24 * 60 * 60 * 1000) < 0) {
//						continue;
//					}
//					dayss = (e.toDate().getTime() - b.toDate().getTime()) / (24 * 60 * 60 * 1000);
//					if (dayss < 0) {
//						break;
//					}
//					for (int i2 = 0; i2 < rowno-6; i2++) {// ��ֵ
//						//lily ����У�� 2019-12-23
//						int d=coldate+i-12;
//						if(d<coldate&&d>coldate-13){
//							csModel.setCellValue(i3, coldate + i - 12, Long.toString(dayss + 1));
//						}
//						
//					}
//
//				}
//
//				beginTime = null;
//				endTime = null;
//
//			}
//
//		}
//		JOptionPane.showMessageDialog(null, "���������ɹ�", "���������ɹ�", JOptionPane.WARNING_MESSAGE);
//		if (new String(ormessage) != null && !"".equals(new String(ormessage))) {
//			MessageDialog.showErrorDlg(getCurrentView(), "��ʾ", "" + ormessage + "");
//		}
		
	}
	
	private void setMm(GkfyysVO vo, String mm, UFDouble je) {
		switch (mm) {
			case "01":
				vo.setM_01(je);
				break;
			case "02":
				vo.setM_02(je);
				break;
			case "03":
				vo.setM_03(je);
				break;
			case "04":
				vo.setM_04(je);
				break;
			case "05":
				vo.setM_05(je);
				break;
			case "06":
				vo.setM_06(je);
				break;
			case "07":
				vo.setM_07(je);
				break;
			case "08":
				vo.setM_08(je);
				break;
			case "09":
				vo.setM_09(je);
				break;
			case "10":
				vo.setM_10(je);
				break;
			case "11":
				vo.setM_11(je);
				break;
			case "12":
				vo.setM_12(je);
				break;
		}
			
	}
	
	private UFDouble getMm(GkfyysVO vo, String mm) {
		switch (mm) {
			case "01":
				return vo.getM_01();
			case "02":
				return vo.getM_02();
			case "03":
				return vo.getM_03();
			case "04":
				return vo.getM_04();
			case "05":
				return vo.getM_05();
			case "06":
				return vo.getM_06();
			case "07":
				return vo.getM_07();
			case "08":
				return vo.getM_08();
			case "09":
				return vo.getM_09();
			case "10":
				return vo.getM_10();
			case "11":
				return vo.getM_11();
			case "12":
				return vo.getM_12();
		}
		return null;
	}

}
