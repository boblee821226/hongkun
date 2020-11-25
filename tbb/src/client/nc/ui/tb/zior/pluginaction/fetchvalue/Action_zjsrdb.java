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
import nc.jdbc.framework.processor.ArrayListProcessor;
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
import nc.vo.tb.zior.pluginaction.fetchvalue.ZjsrdbVO;

import com.ufsoft.table.Cell;
import com.ufsoft.table.CellsModel;

/**
 * 租赁收入底表
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
		
//		String PK_SZXM = "1001N510000000024S47";
//		String SZXM_NAME = "租金收入";
		
		Integer beginRow = 8;	// 开始行
		Integer[] cols = new Integer[]{
			6,	// 0预算项目（600101、租金收入、1001N510000000024S47）（收支档案）
			9,	// 1物料（房间号）（物料档案）
			10,	// 2收费项目（自定义档案）
			11,	// 3客户（客户档案）
			12,	// 4面积
			13,	// 5单价
			14,	// 6开始日期
			15,	// 7截至日期
			28,	// 8数据来源=日租型
		};
		Integer monthBeginCol = 16 - 1;
		
		String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
		String pk_dept = tsmodel.getMdTask().getPk_dataent();	// 预算维度 到部门
		UIRefPane refPane = new UIRefPane("维度选择");
		TBDataCellRefModel refModel_szxm = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel refModel_cust = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel refModel_room = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel refModel_sfxm = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel refModel_zjtz = (TBDataCellRefModel) refPane.getRefModel();
				
		List<Cell> cells1 = tbSheetViewer.getSelectedCell();
		Cell cell = cells1.get(cells1.size() - 1);
		CellExtInfo cInfo1 = (CellExtInfo) cell.getExtFmt(TbIufoConst.tbKey);
		int varType = cInfo1.getExVarAreaDef().varAreaType;
		int celNum = varType == ExVarAreaDef.varAreatType_ROW ? cell.getCol() : cell.getRow();
		// 指标：收支项目
		LevelValueOfDimLevelVO dim_szxm = new LevelValueOfDimLevelVO(celNum, "MEASURE", null, null, taskDataModel.getMdTask());
		// 维度：客户
		LevelValueOfDimLevelVO dim_cust = new LevelValueOfDimLevelVO(celNum, "CUSTOM", null, null, taskDataModel.getMdTask());
		// 维度：物料-房号
		LevelValueOfDimLevelVO dim_room = new LevelValueOfDimLevelVO(celNum, "MARBAS", null, null, taskDataModel.getMdTask());
		// 维度：自定义-收费项目
		LevelValueOfDimLevelVO dim_sfxm = new LevelValueOfDimLevelVO(celNum, "SFXM", null, null, taskDataModel.getMdTask());
		// 维度：租金调整-渠道类型
		LevelValueOfDimLevelVO dim_zjtz = new LevelValueOfDimLevelVO(celNum, "QDLX", null, null, taskDataModel.getMdTask());
		
		ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo1);
		Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(cell, cInfo1, exVarDef, tbSheetViewer.getCellsPane());
		TbVarAreaUtil.initTBDataCellRefModel(refModel_szxm, dim_szxm, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_szxm.getData();
		TbVarAreaUtil.initTBDataCellRefModel(refModel_cust, dim_cust, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_cust.getData();
		TbVarAreaUtil.initTBDataCellRefModel(refModel_room, dim_room, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_room.getData();
		TbVarAreaUtil.initTBDataCellRefModel(refModel_sfxm, dim_sfxm, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_sfxm.getData();
		TbVarAreaUtil.initTBDataCellRefModel(refModel_zjtz, dim_zjtz, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		refModel_zjtz.getData();
		
		ArrayList<String[]> zjtzList = new ArrayList<String[]>();
		zjtzList.add(new String[]{"租金调整一", "1001N5100000000V2BBZ"});
		zjtzList.add(new String[]{"租金调整二", "1001N5100000000V2BC0"});
		zjtzList.add(new String[]{"租金调整三", "1001N5100000000V2BC1"});
		zjtzList.add(new String[]{"租金调整四", "1001N510000000AQ4J1E"});
		zjtzList.add(new String[]{"租金调整五", "1001N510000000AQ4J1F"});
		
		/**
		 * 先处理手工增行的数据
		 */
		{
			// 循环找到手工录入的结束行，小计为结束行。
			Integer currRow = beginRow;
			boolean isExec = true;	// 是否执行，当遇到小计行时停止
			while (isExec) {
				// 根据首个字段，来判断是否为小计行
				String firstCell = PuPubVO.getString_TrimZeroLenAsNull(csModel.getCellValue(currRow, cols[0]));
				// 房间号
				String roomCell = PuPubVO.getString_TrimZeroLenAsNull(csModel.getCellValue(currRow, cols[1]));
//				System.out.println(roomCell);
				if (firstCell != null && "小计：".equals(firstCell.replaceAll(" ", ""))) {
					isExec = false;
				} else {
					if (roomCell != null) {
						/**
						 * 根据房间号查询出物料档案的 规格（面积）、自定义5（收支项目）
						 */
						StringBuffer querySQL = 
						new StringBuffer("select ")
							.append(" m.materialspec ")			// 0、面积
							.append(",szxm.pk_inoutbusiclass ")	// 1、收支pk
							.append(",szxm.name ")				// 2、收支name
							.append(" from bd_material m ")
							.append(" left join bd_inoutbusiclass szxm on m.def5 = szxm.pk_inoutbusiclass ")
							.append(" where m.pk_org in ( ")
							.append(" 	select pk_org from org_dept ")
							.append(" 	where pk_dept = '").append(pk_dept).append("' ")
							.append(" ) ")
							.append(" and m.name = '").append(roomCell).append("' ")
						;
						ArrayList list = (ArrayList)iquerybs.executeQuery(querySQL.toString(), new ArrayListProcessor());
						if (list != null && !list.isEmpty()) {
							Object[] obj = (Object[])list.get(0);
							String mianji = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
							String pk_szxm = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
							String szxm_name = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
							csModel.setCellValue(currRow, cols[4], mianji);
							csModel.setCellValue(currRow, cols[0], szxm_name);
							DimMember dm_szxm = refModel_szxm.getDimMember(pk_szxm);
							if (null != dm_szxm) {
								DimMember[] dms_szxm = new DimMember[]{dm_szxm};
								VarCellValueModel vm_szxm = new VarCellValueModel(0, csModel, currRow, cols[0], dms_szxm, 1);
								try {
									vm_szxm.fireCellValueChaned();
								} catch (BusinessException be) {
									NtbLogger.error(be);
								}
							}
						}
						/***END***/
						ZjsrdbVO vo = new ZjsrdbVO();
						String ksrq = PuPubVO.getString_TrimZeroLenAsNull(csModel.getCellValue(currRow, cols[6]));
						String jzrq = PuPubVO.getString_TrimZeroLenAsNull(csModel.getCellValue(currRow, cols[7]));
						if (ksrq == null) ksrq = "" + year + "-01-01";
						if (jzrq == null) jzrq = "" + year + "-12-31";
						vo.setKsrq(ksrq);
						vo.setJzrq(jzrq);
						this.calcTs(vo, vo, year, 1);
						for (int mm = 1; mm <= 12; mm ++) {
							String key = (mm < 10 ? "0" : "") + mm;
							Integer ts = this.getMm(vo, key);
							if (ts != null) {
								csModel.setCellValue(currRow, monthBeginCol + mm, ts);// 月份天数
							}
						}
						csModel.setCellValue(currRow, cols[8], "日租型");
					}
					currRow++;
				}
				
			}
		}
		/***END***/

		// TODO 测试
//		if (true) return;
		
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" htb.vbdef1 pk_sfxm,")	// 0、收费项目
				.append(" sfxm.name sfxm_name,")// 1、收费项目name
				.append(" substr(ht.valdate,1,10) begin_date,") 	// 2、合同开始日期
//				.append(" substr(ht.invallidate,1,10) end_date,")	// 3、合同截至日期
				.append(" case when nvl(ht.vdef19,'~') = '~' " +
						" then substr(ht.invallidate,1,10) " +
						" else substr(ht.vdef19,1,10) " +
						" end end_date,")// 3、租金确认截至日 or 合同截至日期
				.append(" substr(htb.vbdef3,1,10) ksrq,")	// 4、行 开始日期
				.append(" substr(htb.vbdef4,1,10) jzrq,")	// 5、行 截至日期
				.append(" htb.vbdef5 price,")				// 6、单价
				.append(" to_number(htb.vbdef6) mianji,") 	// 7、面积
				.append(" ht.pk_customer pk_cust,")		// 8、客户pk
				.append(" cust.name cust_name,")		// 9、客户name
				.append(" room.name room_name,")		// 10、房号
				.append(" inv.pk_material pk_room,")	// 11、物料-房号pk
				.append(" szxm.pk_inoutbusiclass pk_szxm,")	// 12、收费项目
				.append(" szxm.name szxm_name, ")			// 13、收费项目name
				.append(" htb.vbdef7 money ")			// 14、金额
				.append(" from ct_sale ht ")
				.append(" inner join ct_sale_b htb on ht.pk_ct_sale = htb.pk_ct_sale ")
				.append(" left join bd_customer cust on ht.pk_customer = cust.pk_customer ")
				.append(" left join bd_defdoc sfxm on htb.vbdef1  = sfxm.pk_defdoc ")
				.append(" left join bd_defdoc room on ht.vdef16 = room.pk_defdoc ")
				.append(" left join bd_material inv on inv.dr = 0 and inv.pk_org = ht.pk_org and inv.code = room.code ")
				.append(" left join bd_inoutbusiclass szxm on inv.def5 = szxm.pk_inoutbusiclass ")
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.blatest = 'Y' ")
				.append(" and sfxm.name not like '%押金%' ")
				.append(" and ht.fstatusflag in (1, 6) ")
				.append(" and htb.norigtaxmny <> 0.00 ")
				// 表体开始日期 or 表体结束日期  在本年内
				.append(" and (substr(htb.vbdef3,1,10) between '").append(year).append("-01-01' and '").append(year).append("-12-31' ")
				.append(" 	or substr(htb.vbdef4,1,10) between '").append(year).append("-01-01' and '").append(year).append("-12-31' ")
				.append(" ) ")
				// 表体开始日期 <= 表头截止日期
				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.invallidate, '~', ''), '2099-12-31'), 1, 10) ")
				// 表体开始日期 <= 表头租金确认截至日
				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.vdef19, '~', ''), '2099-12-31'), 1, 10) ")
				// 部门过滤
				.append(" and ht.depid = '").append(pk_dept).append("' ")
				// 测试
//				.append(" and ht.vbillcode = '20200901西配楼' ")
				// 排序
				.append(" order by htb.vbdef1,ht.pk_customer,htb.norigtaxmny desc ")
		;
		ArrayList<ZjsrdbVO> list = (ArrayList)iquerybs.executeQuery(querySQL.toString(), new BeanListProcessor(ZjsrdbVO.class));
		// 封装HashMap
		HashMap<String,ZjsrdbVO> dataMap = new HashMap<>();
		// 存放pk为空的房间号
		HashMap<String,String> nullRoomMap = new HashMap<>();
		for (ZjsrdbVO item : list) {
			// 调整的项目，合同表体单价为负数，需要去掉负号，与原始行进行叠加，只是天数来扣减。
			// 灵活一些，根据单价为正负数，来判断天数的增减。正数为增、负数为减
			String sfxm_name = item.getSfxm_name();
			String price = item.getPrice();
			String money = item.getMoney();
			int flag = 1;	// 负数调整 为 -1
			if (sfxm_name.startsWith("调整")) {
				// 支持 两种模式来判断 调增调减  单价 和 金额
				sfxm_name = sfxm_name.replaceFirst("调整", "");
				if (price.startsWith("-")) {
					price = price.replaceFirst("-", "");
					flag = -1;
				}
				if (money.startsWith("-")) {
					flag = -1;
				}
			}
			String key = item.getPk_room() + "@@@@" 
						+ sfxm_name + "@@@@" 
						+ item.getPk_cust() + "@@@@"
						+ item.getPrice()
						;
			if (dataMap.containsKey(key)) {
				this.calcTs(dataMap.get(key), item, year, flag);
			} else {
				this.calcTs(item, item, year, flag);
				dataMap.put(key, item);
			}
			// 空房间的处理
			if (PuPubVO.isNullOrEmpty(item.getPk_room())) {
				nullRoomMap.put(item.getRoom_name(), item.getRoom_name());
			}
		}
		
		// 如果存在空房间，则提示错误
		if (!nullRoomMap.isEmpty()) {
			String msg = "";
			for (Entry<String, String> item : nullRoomMap.entrySet()) {
				msg += item.getKey() + "、";
			}
			throw new BusinessException("以下房间在物料档案中没有建立：\r\n" + msg);
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
//		for (Entry<String, ZjsrdbVO> item : dataMap.entrySet()) {
		for (String mapKey : mapKeys) {
			ZjsrdbVO vo = dataMap.get(mapKey);
			csModel.setCellValue(currRow, cols[0], vo.getSzxm_name());//0预算项目名称
			csModel.setCellValue(currRow, cols[1], vo.getRoom_name());//1物料（房间号）
			csModel.setCellValue(currRow, cols[2], vo.getSfxm_name());//2收费项目
			csModel.setCellValue(currRow, cols[3], vo.getCust_name());//3客户
			csModel.setCellValue(currRow, cols[4], vo.getMianji());//4面积
			csModel.setCellValue(currRow, cols[5], PuPubVO.getUFDouble_NullAsZero(vo.getPrice()));//5单价
			csModel.setCellValue(currRow, cols[6], vo.getBegin_date());//6截至日期
			csModel.setCellValue(currRow, cols[7], vo.getEnd_date());//7截至日期
			csModel.setCellValue(currRow, cols[8], "日租型");//8数据来源
			
			for (int mm = 1; mm <= 12; mm ++) {
				String key = (mm < 10 ? "0" : "") + mm;
				Integer ts = this.getMm(vo, key);
				if (ts != null) {
					csModel.setCellValue(currRow, monthBeginCol + mm, ts);// 月份天数
				}
			}
			
			// 指标：收支项目
			if (!PuPubVO.isNullOrEmpty(vo.getPk_szxm())) {
				DimMember dm_szxm = refModel_szxm.getDimMember(vo.getPk_szxm());
				if (null != dm_szxm) {
					DimMember[] dms_szxm = new DimMember[]{dm_szxm};
					VarCellValueModel vm_szxm = new VarCellValueModel(0, csModel, currRow, cols[0], dms_szxm, 1);
					try {
						vm_szxm.fireCellValueChaned();
					} catch (BusinessException be) {
						NtbLogger.error(be);
					}
				}
			} else {
//				throw new BusinessException("收支项目不能为空");
//				MessageDialog.showErrorDlg(tbSheetViewer,"错误","收支项目不能为空");
			}
			// 维度：房号
			if (!PuPubVO.isNullOrEmpty(vo.getPk_room())) {
				DimMember dm_room = refModel_room.getDimMember("MARBAS"+vo.getPk_room());
				if (null != dm_room) {
					DimMember[] dms_room = new DimMember[]{dm_room};
					VarCellValueModel vm_room = new VarCellValueModel(0, csModel, currRow, cols[1], dms_room, 1);
					try {
						vm_room.fireCellValueChaned();
					} catch (BusinessException be) {
						NtbLogger.error(be);
					}
				}
			} else {
				throw new BusinessException("物料档案-房号不能为空");
//				MessageDialog.showErrorDlg(tbSheetViewer,"错误","物料档案-房号不能为空");
			}
			// 维度：收费项目
			if (!PuPubVO.isNullOrEmpty(vo.getPk_sfxm())) {
				DimMember dm_sfxm = refModel_sfxm.getDimMember(vo.getPk_sfxm());
				if (null != dm_sfxm) {
					DimMember[] dms_sfxm = new DimMember[]{dm_sfxm};
					VarCellValueModel vm_sfxm = new VarCellValueModel(0, csModel, currRow, cols[2], dms_sfxm, 1);
					try {
						vm_sfxm.fireCellValueChaned();
					} catch (BusinessException be) {
						NtbLogger.error(be);
					}
				}
			} else {
//				throw new BusinessException("收费项目不能为空");
//				MessageDialog.showErrorDlg(tbSheetViewer,"错误","收费项目不能为空");
			}
			// 维度：客户
			if (!PuPubVO.isNullOrEmpty(vo.getPk_cust())) {
				DimMember dm_cust = refModel_cust.getDimMember("CUSTOM"+vo.getPk_cust());
				if (null != dm_cust) {
					DimMember[] dms_cust = new DimMember[]{dm_cust};
					VarCellValueModel vm_cust = new VarCellValueModel(0, csModel, currRow, cols[3], dms_cust, 1);
					try {
						vm_cust.fireCellValueChaned();
					} catch (BusinessException be) {
						NtbLogger.error(be);
					}
				}
			} else {
//				throw new BusinessException("客户不能为空");
//				MessageDialog.showErrorDlg(tbSheetViewer,"错误","客户不能为空");
			}
			// key = 收费项目+客户+房间号
			String rowKey = vo.getPk_sfxm() + "@@@@" + vo.getPk_cust() + "@@@@" + vo.getPk_room();
			if (rowKeyMap.containsKey(rowKey)) {
				Integer index = rowKeyMap.get(rowKey);
				if (index < zjtzList.size()-1) {
					String[] zjtz = zjtzList.get(index);
					String pk_zjtz = zjtz[1];
					String zjtz_name = zjtz[0];
					csModel.setCellValue(currRow, cols[0]+1, zjtz_name);
					DimMember dm_zjtz = refModel_zjtz.getDimMember(pk_zjtz);
					if (null != dm_zjtz) {
						DimMember[] dms_zjtz = new DimMember[]{dm_zjtz};
						VarCellValueModel vm_zjtz = new VarCellValueModel(0, csModel, currRow, cols[0]+1, dms_zjtz, 1);
						try {
							vm_zjtz.fireCellValueChaned();
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
	
	private void calcTs(ZjsrdbVO root, ZjsrdbVO calc, Integer year, Integer flag) {
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
	
	private void setMm(ZjsrdbVO vo, String mm, Integer ts) {
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
	
	private Integer getMm(ZjsrdbVO vo, String mm) {
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
