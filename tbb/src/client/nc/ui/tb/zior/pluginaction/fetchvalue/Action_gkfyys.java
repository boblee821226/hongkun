package nc.ui.tb.zior.pluginaction.fetchvalue;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zior.tbb.ICalcuateperce;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ms.tb.task.data.TaskDataModel;
import nc.ms.tb.task.data.TaskSheetDataModel;
import nc.ui.dcm.chnlrplstrct.maintain.action.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.tb.model.TBDataCellRefModel;
import nc.ui.tb.zior.TBSheetViewer;
import nc.ui.tb.zior.TbVarAreaUtil;
import nc.ui.tb.zior.pluginaction.edit.model.VarCellValueModel;
import nc.ui.tb.zior.pluginaction.edit.pageaction.CellContentUtil;
import nc.vo.mdm.dim.DimLevel;
import nc.vo.mdm.dim.DimMember;
import nc.vo.mdm.dim.LevelValue;
import nc.vo.mdm.pub.NtbLogger;
import nc.vo.pub.BusinessException;
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
		/**
		 * ֻȡ����ͷ-̯����ֹ���� С�ڵ��� ����-��ֹ���� �����ݡ�
		 */
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
				// ���帶������ �� ������
				.append(" and substr(htb.vbdef2,1,10) between '").append(year).append("-01-01' and '").append(year).append("-12-31' ")
				.append(" and ht.depid = '").append(pk_dept).append("' ")
				// ���忪ʼ���� <= ��ͷ��ֹ����
				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.invallidate, '~', ''), '2099-12-31'), 1, 10) ")
				.append(" order by htb.vbdef1,ht.cvendorid ")
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
				VarCellValueModel vm_szxm = new VarCellValueModel(0, csModel, currRow, cols[0], dms_szxm, 1);
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
				VarCellValueModel vm_gys = new VarCellValueModel(0, csModel, currRow, cols[1], dms_gys, 1);
				try {
					vm_gys.fireCellValueChaned();
				} catch (BusinessException be) {
					NtbLogger.error(be);
				}
			}
			currRow ++;
		}
		
	}
	
	private void setMm(GkfyysVO vo, String mm, UFDouble je) {
		switch (mm) {
			case "01":
				vo.setM_01(PuPubVO.getUFDouble_NullAsZero(vo.getM_01()).add(je));
				break;
			case "02":
				vo.setM_02(PuPubVO.getUFDouble_NullAsZero(vo.getM_02()).add(je));
				break;
			case "03":
				vo.setM_03(PuPubVO.getUFDouble_NullAsZero(vo.getM_03()).add(je));
				break;
			case "04":
				vo.setM_04(PuPubVO.getUFDouble_NullAsZero(vo.getM_04()).add(je));
				break;
			case "05":
				vo.setM_05(PuPubVO.getUFDouble_NullAsZero(vo.getM_05()).add(je));
				break;
			case "06":
				vo.setM_06(PuPubVO.getUFDouble_NullAsZero(vo.getM_06()).add(je));
				break;
			case "07":
				vo.setM_07(PuPubVO.getUFDouble_NullAsZero(vo.getM_07()).add(je));
				break;
			case "08":
				vo.setM_08(PuPubVO.getUFDouble_NullAsZero(vo.getM_08()).add(je));
				break;
			case "09":
				vo.setM_09(PuPubVO.getUFDouble_NullAsZero(vo.getM_09()).add(je));
				break;
			case "10":
				vo.setM_10(PuPubVO.getUFDouble_NullAsZero(vo.getM_10()).add(je));
				break;
			case "11":
				vo.setM_11(PuPubVO.getUFDouble_NullAsZero(vo.getM_11()).add(je));
				break;
			case "12":
				vo.setM_12(PuPubVO.getUFDouble_NullAsZero(vo.getM_12()).add(je));
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
