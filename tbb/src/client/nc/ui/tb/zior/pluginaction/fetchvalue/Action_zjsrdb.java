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
import nc.jdbc.framework.processor.BeanListProcessor;
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
import nc.vo.pub.lang.UFDate;
import nc.vo.tb.form.excel.ExVarAreaDef;
import nc.vo.tb.form.excel.ExVarDef;
import nc.vo.tb.form.iufo.CellExtInfo;
import nc.vo.tb.form.iufo.TbIufoConst;
import nc.vo.tb.obj.LevelValueOfDimLevelVO;
import nc.vo.tb.zior.pluginaction.fetchvalue.HtcbtxVO;

import com.ufsoft.table.Cell;
import com.ufsoft.table.CellsModel;

/**
 * 租金收入底表
 */
public class Action_zjsrdb implements Action_itf {

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
			int rowno,
			int colno,
			CellsModel csModel)
		throws BusinessException {
		
		if (tbSheetViewer.getSelectedCell().get(0).getRow() <7) {
			MessageDialog.showErrorDlg(null, "提示","请选择第8行之后的预算项目下的数据取数");
			return;
		}
		
		Integer beginRow = 8;	// 开始行
		Integer[] cols = new Integer[]{
			7,	// 0预算项目名称
			8,	// 1供应商
			12,	// 2是否分摊=Y
			13,	// 3合同号
			14,	// 4单价
			15,	// 5面积
			16,	// 6税率
			17,	// 7数据来源=合同成本
			18,	// 8开始日期
			19,	// 9截至日期
		};
		Integer monthBeginCol = 20 - 1;
		
		String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
		String pk_dept = tsmodel.getMdTask().getPk_dataent();	// 预算维度 到部门
		UIRefPane refPane = new UIRefPane("维度选择");
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
				.append(" htb.vbdef1 pk_srxm,")		// 0、收支项目
				.append(" szxm.name srxm_name,")	// 1、收支项目name
				.append(" substr(ht.valdate,1,10) begin_date,") 	// 2、合同开始日期
				.append(" substr(ht.invallidate,1,10) end_date,")	// 3、合同截至日期
				.append(" substr(htb.vbdef3,1,10) ksrq,")	// 4、行 开始日期
				.append(" substr(htb.vbdef4,1,10) jzrq,")	// 5、行 截至日期
				.append(" htb.vbdef5 price,")	// 6、单价
				.append(" fplx.name fplx,")	// 7、发票类型
				.append(" ht.vbillcode,")	// 8、合同号
				.append(" gys.name gys_name, ")		// 9、供应商name
				.append(" to_number(replace(nvl(sl.name,'0'),'%','')) sl,")		// 10、税率
				// 11、面积
				.append(" to_number(replace(nvl(ht.vdef8,'~'),'~','0')) + to_number(replace(nvl(ht.vdef11,'~'),'~','0')) mianji ")
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
				.append(" order by htb.vbdef1,ht.cvendorid ")
		;
		ArrayList<HtcbtxVO> list = (ArrayList)iquerybs.executeQuery(querySQL.toString(), new BeanListProcessor(HtcbtxVO.class));
		// 封装HashMap
		HashMap<String,HtcbtxVO> dataMap = new HashMap<>();
		for (HtcbtxVO item : list) {
			String key = item.getVbillcode() + "@@@@" 
						+ item.getPk_srxm() + "@@@@" 
						+ item.getCvendorid() + "@@@@"
						+ item.getPrice()
						;
			if (dataMap.containsKey(key)) {
				this.calcTs(dataMap.get(key), item, year);
			} else {
				this.calcTs(item, item, year);
				dataMap.put(key, item);
			}
		}
		
		CellContentUtil util = new CellContentUtil(tbSheetViewer);
		util.addMultiLine(1, dataMap.size());
		Integer currRow = beginRow;
		
		for (Entry<String,HtcbtxVO> item : dataMap.entrySet()) {
			HtcbtxVO vo = item.getValue();
			csModel.setCellValue(currRow, cols[0], vo.getSrxm_name());//0预算项目名称
			csModel.setCellValue(currRow, cols[1], vo.getGys_name());//1供应商
			csModel.setCellValue(currRow, cols[2], "Y");//2是否分摊
			csModel.setCellValue(currRow, cols[3], vo.getVbillcode());//3合同号
			csModel.setCellValue(currRow, cols[4], PuPubVO.getUFDouble_NullAsZero(vo.getPrice()));//4单价
			csModel.setCellValue(currRow, cols[5], vo.getMianji());//5面积
			csModel.setCellValue(currRow, cols[6], vo.getSl());//6税率
			csModel.setCellValue(currRow, cols[7], "合同成本");//7数据来源
			csModel.setCellValue(currRow, cols[8], vo.getBegin_date());//8开始日期
			csModel.setCellValue(currRow, cols[9], vo.getEnd_date());//9截至日期
			
			for (int mm = 1; mm <= 12; mm ++) {
				String key = (mm < 10 ? "0" : "") + mm;
				Integer ts = this.getMm(vo, key);
				if (ts != null) {
					csModel.setCellValue(currRow, monthBeginCol + mm, ts);// 月份天数
				}
			}
			
			// 维度：项目
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
			// 维度：供应商
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
	
	private void calcTs(HtcbtxVO root, HtcbtxVO calc, Integer year) {
		String ksrqStr = calc.getKsrq();
		String jzrqStr = calc.getJzrq();
		Integer[] ts = fentanTs(ksrqStr, jzrqStr, year);
		for (int i=1;i<=12;i++) {
			String mm = (i<10?"0":"")+i;
			this.setMm(root, mm, ts[i-1]);
		}
	}
	
	private Integer[] fentanTs(String ksrqStr, String jzrqStr, Integer year) {
		Integer[] result = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0};
		UFDate ksrq = new UFDate(ksrqStr);	// A1
		UFDate jzrq = new UFDate(jzrqStr);	// A2
		for (int i=1; i<=12; i++) {
			String mm = (i<10?"0":"") + i;
			UFDate mm_ksrq = new UFDate(""+year+"-"+mm+"-01");	// B1
			int days = mm_ksrq.getDaysMonth(year, i);
			UFDate mm_jzrq = new UFDate(""+year+"-"+mm+"-"+(days<10?"0":"")+days);	// B2
			/**
			 * 分三种情况 来判断： A1 A2  B1 B2
			 * 1、A1 > B2 continue
			 * 2、A2 < B1 break
			 * 3、A1 <= B1  计算开始日期 = B1
			 * 4、A1 > B1     计算开始日期 = A1
			 * 5、A2 <= B2   计算截至日期 = A2
			 * 6、A2 > B2      计算截至日期 = B2
			 */
			if (ksrq.after(mm_jzrq)) continue;	// 1
			if (jzrq.before(mm_ksrq)) break;	// 2
			UFDate calc_ksrq = null;
			UFDate calc_jzrq = null;
			if (ksrq.after(mm_ksrq)) calc_ksrq = ksrq;	// 4
			else calc_ksrq = mm_ksrq;	// 3
			if (jzrq.after(mm_jzrq)) calc_jzrq = mm_jzrq;	// 6
			else calc_jzrq = jzrq;		// 5
			
			Integer ts = calc_jzrq.getDaysAfter(calc_ksrq) + 1;
			result[i-1] = ts;
		}
		return result;
	}
	
	private void setMm(HtcbtxVO vo, String mm, Integer ts) {
		switch (mm) {
			case "01":
				vo.setM_01(PuPubVO.getInteger_NullAs(vo.getM_01(),0) + ts);
				break;
			case "02":
				vo.setM_02(PuPubVO.getInteger_NullAs(vo.getM_02(),0) + ts);
				break;
			case "03":
				vo.setM_03(PuPubVO.getInteger_NullAs(vo.getM_03(),0) + ts);
				break;
			case "04":
				vo.setM_04(PuPubVO.getInteger_NullAs(vo.getM_04(),0) + ts);
				break;
			case "05":
				vo.setM_05(PuPubVO.getInteger_NullAs(vo.getM_05(),0) + ts);
				break;
			case "06":
				vo.setM_06(PuPubVO.getInteger_NullAs(vo.getM_06(),0) + ts);
				break;
			case "07":
				vo.setM_07(PuPubVO.getInteger_NullAs(vo.getM_07(),0) + ts);
				break;
			case "08":
				vo.setM_08(PuPubVO.getInteger_NullAs(vo.getM_08(),0) + ts);
				break;
			case "09":
				vo.setM_09(PuPubVO.getInteger_NullAs(vo.getM_09(),0) + ts);
				break;
			case "10":
				vo.setM_10(PuPubVO.getInteger_NullAs(vo.getM_10(),0) + ts);
				break;
			case "11":
				vo.setM_11(PuPubVO.getInteger_NullAs(vo.getM_11(),0) + ts);
				break;
			case "12":
				vo.setM_12(PuPubVO.getInteger_NullAs(vo.getM_12(),0) + ts);
				break;
		}
	}
	
	private Integer getMm(HtcbtxVO vo, String mm) {
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
