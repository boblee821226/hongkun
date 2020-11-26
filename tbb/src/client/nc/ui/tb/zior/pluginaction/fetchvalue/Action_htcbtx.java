package nc.ui.tb.zior.pluginaction.fetchvalue;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.Arrays;
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
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.form.excel.ExVarAreaDef;
import nc.vo.tb.form.excel.ExVarDef;
import nc.vo.tb.form.iufo.CellExtInfo;
import nc.vo.tb.form.iufo.TbIufoConst;
import nc.vo.tb.obj.LevelValueOfDimLevelVO;
import nc.vo.tb.zior.pluginaction.fetchvalue.HtcbtxVO;
import nc.vo.tb.zior.pluginaction.fetchvalue.ZjsrdbVO;

import com.ufsoft.table.Cell;
import com.ufsoft.table.CellsModel;

/**
 * 合同成本摊销
 */
public class Action_htcbtx implements Action_itf {

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
			CellsModel csModel
		) throws BusinessException {
		
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
			18,	// 7数据来源=合同成本
			19,	// 8开始日期
			20,	// 9截至日期
			17,	// 10分摊比例
		};
		Integer monthBeginCol = 21 - 1;
		
		String yearFirstDate = "" + year + "-01-01";
		String yearLastDate = "" + year + "-12-31";
		
		String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
		String pk_dept = tsmodel.getMdTask().getPk_dataent();	// 预算维度 到部门
		UIRefPane refPane = new UIRefPane("维度选择");
		TBDataCellRefModel refModel_szxm = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel refModel_gys = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel refModel_eps = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel refModel_xmmx = (TBDataCellRefModel) refPane.getRefModel();
		
		List<Cell> cells1 = tbSheetViewer.getSelectedCell();
		Cell cell = cells1.get(cells1.size() - 1);
		CellExtInfo cInfo1 = (CellExtInfo) cell.getExtFmt(TbIufoConst.tbKey);
		int varType = cInfo1.getExVarAreaDef().varAreaType;
		int celNum = varType == ExVarAreaDef.varAreatType_ROW ? cell.getCol() : cell.getRow();
		
		LevelValueOfDimLevelVO dim_szxm = new LevelValueOfDimLevelVO(celNum, "MEASURE", null, null, taskDataModel.getMdTask());
		LevelValueOfDimLevelVO dim_gys = new LevelValueOfDimLevelVO(celNum, "SUPPLIER", null, null, taskDataModel.getMdTask());
		LevelValueOfDimLevelVO dim_eps = new LevelValueOfDimLevelVO(celNum, "PROJECT", null, null, taskDataModel.getMdTask());
		LevelValueOfDimLevelVO dim_xmmx = new LevelValueOfDimLevelVO(celNum, "XMMX", null, null, taskDataModel.getMdTask());
		ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo1);
		Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(cell, cInfo1, exVarDef, tbSheetViewer.getCellsPane());
		TbVarAreaUtil.initTBDataCellRefModel(refModel_szxm, dim_szxm, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_szxm.getData();
		TbVarAreaUtil.initTBDataCellRefModel(refModel_gys, dim_gys, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_gys.getData();
		TbVarAreaUtil.initTBDataCellRefModel(refModel_eps, dim_eps, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_eps.getData();
		TbVarAreaUtil.initTBDataCellRefModel(refModel_xmmx, dim_xmmx, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_xmmx.getData();
		
		ArrayList<String[]> xmmxList = new ArrayList<String[]>();
		xmmxList.add(new String[]{"调整一", "1001S910000000BX27M3"});
		xmmxList.add(new String[]{"调整二", "1001S910000000BX27M4"});
		xmmxList.add(new String[]{"调整三", "1001S910000000BX27M5"});
		xmmxList.add(new String[]{"调整四", "1001S910000000BX27M6"});
		xmmxList.add(new String[]{"调整五", "1001S910000000BX27M7"});
		
		/**
		 * 分为两部分sql
		 * 1、部门分摊页签的数据为空，就按照表头的部门where 并且 分摊比例=1
		 * 2、部门分摊页签的数据不为空，则按照 分摊页签的部门where，并且取 相应的分摊比例
		 */
		String ct_pu_SQL_1 = " select * from ct_pu " +
						 " where pk_ct_pu not in " +
						 " ( " +
						 " select pk_ct_pu from ct_pu_term " +
						 " where dr = 0 " +
						 " group by pk_ct_pu " +
						 " ) ";
		String ct_pu_SQL_2 = " select * from ct_pu " +
						 " where pk_ct_pu in " +
						 " ( " +
						 " select pk_ct_pu from ct_pu_term " +
						 " where dr = 0 " +
						 " and vhkbdef3 = '" + pk_dept + "'" +
						 " group by pk_ct_pu " +
						 " ) ";
		StringBuffer querySQL = 
		new StringBuffer("select * from ")
			.append(" ( ")
				.append(" select ")
				.append(" htb.vbdef1 pk_srxm,")		// 0、收支项目
				.append(" szxm.name srxm_name,")	// 1、收支项目name
				.append(" substr(ht.valdate,1,10) begin_date,") 	// 2、合同开始日期
				.append(" substr(ht.invallidate,1,10) end_date,")	// 3、合同截至日期
				.append(" substr(htb.vbdef3,1,10) ksrq,")	// 4、行 开始日期
				.append(" substr(htb.vbdef4,1,10) jzrq,")	// 5、行 截至日期
				.append(" htb.vbdef5 price,")	// 6、单价
				.append(" fplx.name fplx,")	// 7、发票类型
				.append(" ht.vbillcode,")	// 8、合同号
				.append(" ht.cvendorid, ")		// 9、供应商pk
				.append(" gys.name gys_name, ")		// 10、供应商name
				.append(" to_number(replace(nvl(sl.name,'0'),'%','')) sl,")		// 11、税率
				// 12、面积
				.append(" to_number(replace(nvl(ht.vdef8,'~'),'~','0')) + to_number(replace(nvl(ht.vdef11,'~'),'~','0')) mianji, ")
				.append(" '1.0' ftbl, ")// 13、比例
				.append(" htb.norigtaxmny ")// 14、金额
				.append(" from (").append(ct_pu_SQL_1).append(") ht ")	// 合同：无部门分摊的合同
				.append(" inner join ct_pu_b htb on ht.pk_ct_pu = htb.pk_ct_pu ")	// 合同：行信息
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
				// 表体开始日期 or 表体结束日期  在本年内
				.append(" and (substr(htb.vbdef3,1,10) between '").append(yearFirstDate).append("' and '").append(yearLastDate).append("' ")
				.append(" 	or substr(htb.vbdef4,1,10) between '").append(yearFirstDate).append("' and '").append(yearLastDate).append("' ")
				.append(" ) ")
				// 表体开始日期 <= 表头截止日期
				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.invallidate, '~', ''), '2099-12-31'), 1, 10) ")
				// 表头租金确认截至日 >= 本年第一天
				.append(" and substr(ht.invallidate,1,10) >= '").append(yearFirstDate).append("' ")
				// 所属部门
				.append(" and ht.depid = '").append(pk_dept).append("' ")
//				.append(" and ht.vbillcode = '050820150806#2' ")// 测试代码
		.append(" union all ")
				.append(" select ")
				.append(" htb.vbdef1 pk_srxm,")		// 0、收支项目
				.append(" szxm.name srxm_name,")	// 1、收支项目name
				.append(" substr(ht.valdate,1,10) begin_date,") 	// 2、合同开始日期
				.append(" substr(ht.invallidate,1,10) end_date,")	// 3、合同截至日期
				.append(" substr(htb.vbdef3,1,10) ksrq,")	// 4、行 开始日期
				.append(" substr(htb.vbdef4,1,10) jzrq,")	// 5、行 截至日期
				.append(" htb.vbdef5 price,")	// 6、单价
				.append(" fplx.name fplx,")	// 7、发票类型
				.append(" ht.vbillcode,")	// 8、合同号
				.append(" ht.cvendorid, ")		// 9、供应商pk
				.append(" gys.name gys_name, ")		// 10、供应商name
				.append(" to_number(replace(nvl(sl.name,'0'),'%','')) sl,")		// 11、税率
				// 12、面积
				.append(" to_number(replace(nvl(ht.vdef8,'~'),'~','0')) + to_number(replace(nvl(ht.vdef11,'~'),'~','0')) mianji, ")
				.append(" htft.vhkbdef2 ftbl, ")// 13、比例
				.append(" htb.norigtaxmny ")// 14、金额
				.append(" from (").append(ct_pu_SQL_2).append(") ht ")	// 合同：有部门分摊的合同
				.append(" inner join ct_pu_term htft on ht.pk_ct_pu = htft.pk_ct_pu  ")	// 合同：部门分摊
				.append(" inner join ct_pu_b htb on htft.pk_ct_pu = htb.pk_ct_pu ")		// 合同：行信息
				.append(" left join bd_defdoc fplx on ht.vdef3 = fplx.pk_defdoc ")
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")
				.append(" left join bd_defdoc sl on ht.vdef4 = sl.pk_defdoc ")
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")
				.append(" where ht.dr = 0 and htb.dr = 0 and htft.dr = 0 ")
				.append(" and ht.vtrantypecode = 'Z2-Cxx-01' ")
				.append(" and szxm.code not in ('2005', '2022') ")
				.append(" and ht.blatest = 'Y' ")
				.append(" and ht.fstatusflag in (1, 6) ")
				.append(" and htb.norigtaxmny <> 0.00 ")
				// 表体开始日期 or 表体结束日期  在本年内
				.append(" and (substr(htb.vbdef3,1,10) between '").append(yearFirstDate).append("' and '").append(yearLastDate).append("' ")
				.append(" 	or substr(htb.vbdef4,1,10) between '").append(yearFirstDate).append("' and '").append(yearLastDate).append("' ")
				.append(" ) ")
				// 表体开始日期 <= 表头截止日期
				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.invallidate, '~', ''), '2099-12-31'), 1, 10) ")
				// 分摊部门过滤
				.append(" and htft.vhkbdef3 = '").append(pk_dept).append("' ")
//				.append(" and ht.vbillcode = '050820150806#2' ")// 测试代码
			.append(" ) a ")
			.append(" order by a.pk_srxm,a.cvendorid,a.norigtaxmny desc ")
		;
		ArrayList<HtcbtxVO> list = (ArrayList)iquerybs.executeQuery(querySQL.toString(), new BeanListProcessor(HtcbtxVO.class));
		// 封装HashMap
		HashMap<String,HtcbtxVO> dataMap = new HashMap<>();
		for (HtcbtxVO item : list) {
			// 调整的项目，合同表体单价为负数，需要去掉负号，与原始行进行叠加，只是天数来扣减。
			// 灵活一些，根据单价为正负数，来判断天数的增减。正数为增、负数为减
			String srxm_name = item.getSrxm_name();
			String price = item.getPrice();
			int flag = 1;	// 负数调整 为 -1
			if (srxm_name.startsWith("调整")) {
				srxm_name = srxm_name.replaceFirst("调整", "");
				if (price.startsWith("-")) {
					price = price.replaceFirst("-", "");
					flag = -1;
				}
			}
			String key = item.getVbillcode() + "@@@@" 
						+ srxm_name + "@@@@" 
						+ item.getCvendorid() + "@@@@"
						+ price
						;
			if (dataMap.containsKey(key)) {
				this.calcTs(dataMap.get(key), item, year, flag);
			} else {
				if (!"专用发票".equals(item.getFplx())) {
					item.setSl(UFDouble.ZERO_DBL);
				}
				this.calcTs(item, item, year, flag);
				dataMap.put(key, item);
			}
		}
		
		CellContentUtil util = new CellContentUtil(tbSheetViewer);
		util.addMultiLine(1, dataMap.size());
		Integer currRow = beginRow;
		
		/**
		 * 先按map的key排序
		 */
		String[] mapKeys = dataMap.keySet().toArray(new String[0]);
		Arrays.sort(mapKeys);
		/***END***/
		
		HashMap<String, Integer> rowKeyMap = new HashMap<>();	// 每个维度的出现次数
		for (String mapKey : mapKeys) {
			HtcbtxVO vo = dataMap.get(mapKey);
			String beginDate = yearFirstDate.compareTo(vo.getBegin_date())>0 ? yearFirstDate: vo.getBegin_date();
			String endDate = yearLastDate.compareTo(vo.getEnd_date())<0 ? yearLastDate: vo.getEnd_date();
			csModel.setCellValue(currRow, cols[0], vo.getSrxm_name());//0预算项目名称
			csModel.setCellValue(currRow, cols[1], vo.getGys_name());//1供应商
			csModel.setCellValue(currRow, cols[2], "Y");//2是否分摊
			csModel.setCellValue(currRow, cols[3], vo.getVbillcode());//3合同号
			csModel.setCellValue(currRow, cols[4], PuPubVO.getUFDouble_NullAsZero(vo.getPrice()));//4单价
			csModel.setCellValue(currRow, cols[5], vo.getMianji());//5面积
			csModel.setCellValue(currRow, cols[6], vo.getSl());//6税率
			csModel.setCellValue(currRow, cols[7], "合同成本");//7数据来源
			csModel.setCellValue(currRow, cols[8], beginDate);//8开始日期
			csModel.setCellValue(currRow, cols[9], endDate);//9截至日期
			csModel.setCellValue(currRow, cols[10], vo.getFtbl());//10比例
			
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
			// 维度：EPS（合同成本摊销）
			DimMember dm_eps = refModel_gys.getDimMember("1001N5100000000QDS82");
			if (null != dm_eps) {
				DimMember[] dms_eps = new DimMember[]{dm_eps};
				VarCellValueModel vm_eps = new VarCellValueModel(0, csModel, currRow, cols[2], dms_eps, 1);
				try {
					vm_eps.fireCellValueChaned();
				} catch (BusinessException be) {
					NtbLogger.error(be);
				}
			}
			// key = 收入项目+供应商+合同号
			String rowKey = vo.getPk_srxm() + "@@@@" + vo.getCvendorid() + "@@@@" + vo.getVbillcode();
			if (rowKeyMap.containsKey(rowKey)) {
				Integer index = rowKeyMap.get(rowKey);
				if (index < xmmxList.size()-1) {
					String[] xmmx = xmmxList.get(index);
					String pk_xmmx = xmmx[1];
					String xmmx_name = xmmx[0];
					csModel.setCellValue(currRow, cols[2]-1, xmmx_name);//项目明细
					DimMember dm_xmmx = refModel_xmmx.getDimMember(pk_xmmx);
					if (null != dm_xmmx) {
						DimMember[] dms_xmmx = new DimMember[]{dm_xmmx};
						VarCellValueModel vm_xmmx = new VarCellValueModel(0, csModel, currRow, cols[2]-1, dms_xmmx, 1);
						try {
							vm_xmmx.fireCellValueChaned();
						} catch (BusinessException be) {
							NtbLogger.error(be);
						}
					}
				}
				rowKeyMap.put(rowKey, index+1);
			} else {
				rowKeyMap.put(rowKey, 0);
			}
			currRow ++;
		}
	}
	
	private void calcTs(HtcbtxVO root, HtcbtxVO calc, Integer year, Integer flag) {
		String ksrqStr = calc.getKsrq();
		String jzrqStr = calc.getJzrq();
		/**
		 * HK 2020年11月24日19:33:43
		 * 如果合同的开始日期 大，就取 合同的开始日期
		 * 如果合同的截至日期 小，就取 合同的截至日期
		 */
		String begin_date = calc.getBegin_date();
		String end_date = calc.getEnd_date();
		if (begin_date != null && begin_date.compareTo(ksrqStr) > 0) {
			ksrqStr = begin_date;
		}
		if (end_date != null && end_date.compareTo(jzrqStr) < 0) {
			jzrqStr = end_date;
		}
		/***END***/
		
		Integer[] ts = fentanTs(ksrqStr, jzrqStr, year);
		for (int i=1;i<=12;i++) {
			String mm = (i<10?"0":"")+i;
			this.setMm(root, mm, ts[i-1] * flag);
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
