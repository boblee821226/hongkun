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
 * ��������ױ�
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
			MessageDialog.showErrorDlg(null, "��ʾ","��ѡ���8��֮���Ԥ����Ŀ�µ�����ȡ��");
			return;
		}
		
//		String PK_SZXM = "1001N510000000024S47";
//		String SZXM_NAME = "�������";
		
		Integer beginRow = 8;	// ��ʼ��
		Integer[] cols = new Integer[]{
			6,	// 0Ԥ����Ŀ��600101��������롢1001N510000000024S47������֧������
			9,	// 1���ϣ�����ţ������ϵ�����
			10,	// 2�շ���Ŀ���Զ��嵵����
			11,	// 3�ͻ����ͻ�������
			12,	// 4���
			13,	// 5����
			14,	// 6��ʼ����
			15,	// 7��������
			28 + 1,	// 8������Դ=������    HK˰���ᵽ�µ�ǰ�棬����˳��+1
		};
		Integer monthBeginCol = 16 - 1 + 1;	// HK˰���ᵽ�µ�ǰ�棬����˳��+1
		
		String yearFirstDate = "" + year + "-01-01";
		String yearLastDate = "" + year + "-12-31";
		
		String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
		String pk_dept = tsmodel.getMdTask().getPk_dataent();	// Ԥ��ά�� ������
		UIRefPane refPane = new UIRefPane("ά��ѡ��");
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
		// ָ�꣺��֧��Ŀ
		LevelValueOfDimLevelVO dim_szxm = new LevelValueOfDimLevelVO(celNum, "MEASURE", null, null, taskDataModel.getMdTask());
		// ά�ȣ��ͻ�
		LevelValueOfDimLevelVO dim_cust = new LevelValueOfDimLevelVO(celNum, "CUSTOM", null, null, taskDataModel.getMdTask());
		// ά�ȣ�����-����
		LevelValueOfDimLevelVO dim_room = new LevelValueOfDimLevelVO(celNum, "MARBAS", null, null, taskDataModel.getMdTask());
		// ά�ȣ��Զ���-�շ���Ŀ
		LevelValueOfDimLevelVO dim_sfxm = new LevelValueOfDimLevelVO(celNum, "SFXM", null, null, taskDataModel.getMdTask());
		// ά�ȣ�������-��������
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
		zjtzList.add(new String[]{"������һ", "1001N5100000000V2BBZ"});
		zjtzList.add(new String[]{"��������", "1001N5100000000V2BC0"});
		zjtzList.add(new String[]{"��������", "1001N5100000000V2BC1"});
		zjtzList.add(new String[]{"��������", "1001N510000000AQ4J1E"});
		zjtzList.add(new String[]{"��������", "1001N510000000AQ4J1F"});
		
		/**
		 * �ȴ����ֹ����е�����
		 */
		{
			// ѭ���ҵ��ֹ�¼��Ľ����У�С��Ϊ�����С�
			Integer currRow = beginRow;
			boolean isExec = true;	// �Ƿ�ִ�У�������С����ʱֹͣ
			while (isExec) {
				// �����׸��ֶΣ����ж��Ƿ�ΪС����
				String firstCell = PuPubVO.getString_TrimZeroLenAsNull(csModel.getCellValue(currRow, cols[0]));
				// �����
				String roomCell = PuPubVO.getString_TrimZeroLenAsNull(csModel.getCellValue(currRow, cols[1]));
				if (firstCell != null && "С�ƣ�".equals(firstCell.replaceAll(" ", ""))) {
					isExec = false;
				} else {
					if (roomCell != null) {
						/**
						 * ���ݷ���Ų�ѯ�����ϵ����� �����������Զ���5����֧��Ŀ��
						 */
						StringBuffer querySQL = 
						new StringBuffer("select ")
							.append(" m.materialspec ")			// 0�����
							.append(",szxm.pk_inoutbusiclass ")	// 1����֧pk
							.append(",szxm.name ")				// 2����֧name
							.append(" from bd_material m ")
							.append(" left join bd_inoutbusiclass szxm on m.def5 = szxm.pk_inoutbusiclass ")
							.append(" where m.pk_org in ( ")
							.append(" 	select pk_org from org_dept ")
							.append(" 	where pk_dept = '").append(pk_dept).append("' ")
							.append(" ) ")
							.append(" and m.enablestate = 2 ")	// ֻȡ����״̬��
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
						if (ksrq == null) ksrq = yearFirstDate;
						if (jzrq == null) jzrq = yearLastDate;
						vo.setKsrq(ksrq);
						vo.setJzrq(jzrq);
						this.calcTs(vo, vo, year, 1);
						for (int mm = 1; mm <= 12; mm ++) {
							String key = (mm < 10 ? "0" : "") + mm;
							Integer ts = this.getMm(vo, key);
							if (ts != null) {
								csModel.setCellValue(currRow, monthBeginCol + mm, ts);// �·�����
							}
						}
						csModel.setCellValue(currRow, cols[8], "������");
					}
					currRow++;
				}
				
			}
		}
		/***END***/

		// TODO ����
//		if (true) return;
		
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" htb.vbdef1 pk_sfxm,")	// 0���շ���Ŀ
				.append(" sfxm.name sfxm_name,")// 1���շ���Ŀname
				.append(" substr(ht.valdate,1,10) begin_date,") 	// 2����ͬ��ʼ����
//				.append(" substr(ht.invallidate,1,10) end_date,")	// 3����ͬ��������
				.append(" case when nvl(ht.vdef19,'~') = '~' " +
						" then substr(ht.invallidate,1,10) " +
						" else substr(ht.vdef19,1,10) " +
						" end end_date,")// 3�����ȷ�Ͻ����� or ��ͬ��������
				.append(" substr(htb.vbdef3,1,10) ksrq,")	// 4���� ��ʼ����
				.append(" substr(htb.vbdef4,1,10) jzrq,")	// 5���� ��������
				.append(" htb.vbdef5 price,")				// 6������
				.append(" to_number(htb.vbdef6) mianji,") 	// 7�����
				.append(" ht.pk_customer pk_cust,")		// 8���ͻ�pk
				.append(" cust.name cust_name,")		// 9���ͻ�name
				.append(" room.name room_name,")		// 10������
				.append(" inv.pk_material pk_room,")	// 11������-����pk
				.append(" szxm.pk_inoutbusiclass pk_szxm,")	// 12���շ���Ŀ
				.append(" szxm.name szxm_name, ")			// 13���շ���Ŀname
				.append(" htb.vbdef7 money ")			// 14�����
				.append(" from ct_sale ht ")
				.append(" inner join ct_sale_b htb on ht.pk_ct_sale = htb.pk_ct_sale ")
				.append(" left join bd_customer cust on ht.pk_customer = cust.pk_customer ")
				.append(" left join bd_defdoc sfxm on htb.vbdef1  = sfxm.pk_defdoc ")
				.append(" left join bd_defdoc room on ht.vdef16 = room.pk_defdoc ")
				.append(" left join bd_material inv on inv.dr = 0 and inv.enablestate = 2 and inv.pk_org = ht.pk_org and inv.code = room.code ")
				.append(" left join bd_inoutbusiclass szxm on inv.def5 = szxm.pk_inoutbusiclass ")
				.append(" where ht.dr = 0 and htb.dr = 0 ")
				.append(" and ht.blatest = 'Y' ")
				.append(" and sfxm.name not like '%Ѻ��%' ")
				.append(" and ht.fstatusflag in (1, 6) ")
				.append(" and htb.norigtaxmny <> 0.00 ")
				// ���忪ʼ���� or �����������  �ڱ�����
				.append(" and (substr(htb.vbdef3,1,10) between '").append(yearFirstDate).append("' and '").append(yearLastDate).append("' ")
				.append(" 	or substr(htb.vbdef4,1,10) between '").append(yearFirstDate).append("' and '").append(yearLastDate).append("' ")
				.append(" ) ")
				// ���忪ʼ���� <= ��ͷ��ֹ����
				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.invallidate, '~', ''), '2099-12-31'), 1, 10) ")
				// ���忪ʼ���� <= ��ͷ���ȷ�Ͻ�����
				.append(" and substr(htb.vbdef3,1,10) <= substr(nvl(replace(ht.vdef19, '~', ''), '2099-12-31'), 1, 10) ")
				// ��ͷ���ȷ�Ͻ����գ���ͷ��ֹ���ڣ� >= �����һ��
				.append(" and (case when nvl(ht.vdef19,'~') = '~'" +
						"		then substr(ht.invallidate,1,10) " +
						"		else substr(ht.vdef19,1,10) end >= '" + yearFirstDate + "' " +
						"	) ")
				// ���Ź���
				.append(" and ht.depid = '").append(pk_dept).append("' ")
				// ����
//				.append(" and ht.vbillcode = '20190801901' ")
//				.append(" and ht.pk_customer = '1001N5100000006SB94B' ")
				// ����
				.append(" order by htb.vbdef1,ht.pk_customer,htb.norigtaxmny desc,to_number(htb.vbdef5) desc ")
		;
		ArrayList<ZjsrdbVO> list = (ArrayList)iquerybs.executeQuery(querySQL.toString(), new BeanListProcessor(ZjsrdbVO.class));
		// ��װHashMap
		HashMap<String,ZjsrdbVO> dataMap = new HashMap<>();
		// ���pkΪ�յķ����
		HashMap<String,String> nullRoomMap = new HashMap<>();
		for (ZjsrdbVO item : list) {
			// ��������Ŀ����ͬ���嵥��Ϊ��������Ҫȥ�����ţ���ԭʼ�н��е��ӣ�ֻ���������ۼ���
			// ���һЩ�����ݵ���Ϊ�����������ж�����������������Ϊ��������Ϊ��
			String sfxm_name = item.getSfxm_name();
			String price = item.getPrice();
			String money = item.getMoney();
			int flag = 1;	// �������� Ϊ -1
			if (sfxm_name.startsWith("����")) {
				// ֧�� ����ģʽ���ж� ��������  ���� �� ���
				sfxm_name = sfxm_name.replaceFirst("����", "");
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
						+ price
						;
			if (dataMap.containsKey(key)) {
				this.calcTs(dataMap.get(key), item, year, flag);
			} else {
				this.calcTs(item, item, year, flag);
				dataMap.put(key, item);
			}
			// �շ���Ĵ���
			if (PuPubVO.isNullOrEmpty(item.getPk_room())) {
				nullRoomMap.put(item.getRoom_name(), item.getRoom_name());
			}
		}
		
		// ������ڿշ��䣬����ʾ����
		if (!nullRoomMap.isEmpty()) {
			String msg = "";
			for (Entry<String, String> item : nullRoomMap.entrySet()) {
				msg += item.getKey() + "��";
			}
			throw new BusinessException("���·��������ϵ�����û�н�����\r\n" + msg);
		}
		
		CellContentUtil util = new CellContentUtil(tbSheetViewer);
		util.addMultiLine(1, dataMap.size());
		Integer currRow = beginRow;
		
		/**
		 * �Ȱ�map��key����
		 */
		String[] mapKeys = dataMap.keySet().toArray(new String[0]);
		Arrays.sort(mapKeys);
		/***END***/
		
		HashMap<String, Integer> rowKeyMap = new HashMap<>();	// ÿ��ά�ȵĳ��ִ���
		for (String mapKey : mapKeys) {
			ZjsrdbVO vo = dataMap.get(mapKey);
			String beginDate = vo.getKsrq_calc();
			String endDate = vo.getJzrq_calc();
			// ��ͬ����  �� ������ĩ�������Ƚ�
			beginDate = yearFirstDate.compareTo(beginDate)>0 ? yearFirstDate: beginDate;
			endDate = yearLastDate.compareTo(endDate)<0 ? yearLastDate: endDate;
			
			csModel.setCellValue(currRow, cols[0], vo.getSzxm_name());//0Ԥ����Ŀ����
			csModel.setCellValue(currRow, cols[1], vo.getRoom_name());//1���ϣ�����ţ�
			csModel.setCellValue(currRow, cols[2], vo.getSfxm_name());//2�շ���Ŀ
			csModel.setCellValue(currRow, cols[3], vo.getCust_name());//3�ͻ�
			csModel.setCellValue(currRow, cols[4], vo.getMianji());//4���
			csModel.setCellValue(currRow, cols[5], PuPubVO.getUFDouble_NullAsZero(vo.getPrice()));//5����
			csModel.setCellValue(currRow, cols[6], beginDate);//6��ʼ����
			csModel.setCellValue(currRow, cols[7], endDate);//7��������
			csModel.setCellValue(currRow, cols[8], "������");//8������Դ
			
			for (int mm = 1; mm <= 12; mm ++) {
				String key = (mm < 10 ? "0" : "") + mm;
				Integer ts = this.getMm(vo, key);
				if (ts != null) {
					csModel.setCellValue(currRow, monthBeginCol + mm, ts);// �·�����
				}
			}
			
			// ָ�꣺��֧��Ŀ
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
//				throw new BusinessException("��֧��Ŀ����Ϊ��");
//				MessageDialog.showErrorDlg(tbSheetViewer,"����","��֧��Ŀ����Ϊ��");
			}
			// ά�ȣ�����
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
				throw new BusinessException("���ϵ���-���Ų���Ϊ��");
//				MessageDialog.showErrorDlg(tbSheetViewer,"����","���ϵ���-���Ų���Ϊ��");
			}
			// ά�ȣ��շ���Ŀ
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
//				throw new BusinessException("�շ���Ŀ����Ϊ��");
//				MessageDialog.showErrorDlg(tbSheetViewer,"����","�շ���Ŀ����Ϊ��");
			}
			// ά�ȣ��ͻ�
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
//				throw new BusinessException("�ͻ�����Ϊ��");
//				MessageDialog.showErrorDlg(tbSheetViewer,"����","�ͻ�����Ϊ��");
			}
			// key = �շ���Ŀ+�ͻ�+�����
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
		 * HK 2020��11��24��19:33:43
		 * �����ͬ�Ŀ�ʼ���� �󣬾�ȡ ��ͬ�Ŀ�ʼ����
		 * �����ͬ�Ľ������� С����ȡ ��ͬ�Ľ�������
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
		/**
		 * HK 2020��11��27��15:13:48
		 * ���������ڼ�������� ��ֵ�� ��ͬ�����ϡ�
		 */
		String ksrq_calc = root.getKsrq_calc();
		String jzrq_calc = root.getJzrq_calc();
		if (ksrq_calc == null || ksrq_calc.compareTo(ksrqStr) > 0) {
			root.setKsrq_calc(ksrqStr);
		}
		if (jzrq_calc == null || jzrq_calc.compareTo(jzrqStr) < 0) {
			root.setJzrq_calc(jzrqStr);
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
			 * ��������� ���жϣ� A1 A2  B1 B2
			 * 1��A1 > B2 continue
			 * 2��A2 < B1 break
			 * 3��A1 <= B1  ���㿪ʼ���� = B1
			 * 4��A1 > B1     ���㿪ʼ���� = A1
			 * 5��A2 <= B2   ����������� = A2
			 * 6��A2 > B2      ����������� = B2
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
