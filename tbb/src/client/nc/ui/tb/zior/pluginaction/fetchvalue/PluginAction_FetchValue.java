package nc.ui.tb.zior.pluginaction.fetchvalue;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.mdm.dim.IDimManager;
import nc.itf.tb.cell.ICellInfoChangedListener;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.itf.zior.tbb.ICalcuateperce;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ms.mdm.cube.CubeServiceGetter;
import nc.ms.mdm.dim.DimServiceGetter;
import nc.ms.tb.ext.plan.TbCompliePlanConst;
import nc.ms.tb.task.TbTaskCtl;
import nc.ms.tb.task.data.TaskDataModel;
import nc.ms.tb.task.data.TaskSheetDataModel;
import nc.pub.fa.report.depgather.DepGatherVO;
import nc.ui.dcm.chnlrplstrct.maintain.action.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.tb.model.TBDataCellRefModel;
import nc.ui.tb.zior.TBSheetViewer;
import nc.ui.tb.zior.TbPlanContext;
import nc.ui.tb.zior.TbVarAreaUtil;
import nc.ui.tb.zior.pluginaction.AbstractTbPluginAction;
import nc.ui.tb.zior.pluginaction.TbPluginActionDescriptor;
import nc.ui.tb.zior.pluginaction.edit.model.VarCellValueModel;
import nc.ui.tb.zior.pluginaction.edit.pageaction.CellContentUtil;
import nc.vo.bd.bom.bom0202.entity.BomItemVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.fa.assetcard.CardhistoryVO;
import nc.vo.mdm.cube.CubeDef;
import nc.vo.mdm.dim.DimDef;
import nc.vo.mdm.dim.DimHierarchy;
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
import nc.vo.tb.task.MdTask;
import nc.vo.tbb.gxh.CellwbkVO;

import com.ufsoft.table.AreaPosition;
import com.ufsoft.table.Cell;
import com.ufsoft.table.CellPosition;
import com.ufsoft.table.CellsModel;

/**
 * 固定表取数
 * 
 * @author gaoxh
 * 
 */

public class PluginAction_FetchValue extends AbstractTbPluginAction {
	IUAPQueryBS iquerybs = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
	IVOPersistence ip = (IVOPersistence) NCLocator.getInstance().lookup(IVOPersistence.class);
	ICalcuateperce ica = (ICalcuateperce) NCLocator.getInstance().lookup(ICalcuateperce.class);
	// 预算表取数用模型
	private transient CellsModel csmodel1;
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");// 定义日期格式
	Object sale = null;
	PluginManWork_FetchValue pf = new PluginManWork_FetchValue();
	Object dustcode = null;
	Object dustcode1 = null;
	Object pk_org = null;
	Object uppkinou = null;
	Object pk_work = null;
	Object taskpk = null;
	Object code = null;
	String pklevel = null;
	String orgdept = "";
	String ysxm = "";
	@SuppressWarnings("unused")
	private ICellInfoChangedListener listener;

	public PluginAction_FetchValue(String name, String code) {
		super(name, code);
	}

	public PluginAction_FetchValue(String name, String code, String tooltip) {
		super(name, code, tooltip);
	}

	public PluginAction_FetchValue(String name, String code, String[] groupPaths) {
		super(name, code, groupPaths);
	}

	public PluginAction_FetchValue(String name, String code, String tooltip, String[] groupPaths) {
		super(name, code, tooltip, groupPaths);
	}

	@Override
	public TbPluginActionDescriptor getTbPluginActionDescriptor() {
		return null;
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public void actionPerformed(ActionEvent actionevent) throws BusinessException {
		StringBuffer ormessage = new StringBuffer();
		TaskDataModel taskDataModel = getCurrentViewer().getTsDataModel().getParentModel();
//		taskDataModel.setIsSheetLockEnable(true);
		getContext();
		TaskSheetDataModel tsmodel = getCurrentViewer().getTsDataModel();// 固定模型
		CellsModel csModel = getCellsModel();// 即时模型
		int rowno = csModel.getRowNum();// 即时行数
		TBSheetViewer tbSheetViewer = (TBSheetViewer) this.getCurrentView();// 表单
		int colno = csModel.getColNum();// 即时列数
		int cpleng = 14;
		String key = tsmodel.getMdTask().getPk_dataent();// 责任主体
		String mainorg = iquerybs.executeQuery("select pk_corp from org_orgs where pk_org='" + key + "'", new ColumnProcessor()).toString();// 业务单元
		int yea = 0;
		if (null != tsmodel.getMdTask().getPk_paradims()) {
			String s[] = tsmodel.getMdTask().getPk_paradims().toString().split("=");// 取任务里的信息截取年度
			yea = Integer.parseInt(s[1].replace("]", ""));// 当前年份
		}
		String yeabytask = "[ACCP=" + yea + "]";// 用于查询任务
		String pk_sheet = tsmodel.getMdSheet().getAttributeValue("pk_obj").toString();
		String pk_task = tsmodel.getMdTask().getAttributeValue("pk_obj").toString();
		String orgsql = "select pk_corp from org_orgs where pk_org='" + key + "'";
		Object pk_orgs = iquerybs.executeQuery(orgsql, new ColumnProcessor());// 业务单元
		String workbsql = "select tt.pk_workbook\n" + "  from tb_md_taskdef tt\n" + " inner join tb_md_task tm\n"
				+ "    on tm.pk_workbook = tt.pk_workbook and nvl(tm.dr,0)=0\n" + " inner join org_orgs oo\n"
				+ "    on oo.pk_org = tm.pk_dataent and nvl(oo.dr,0)=0\n" + " inner join tb_md_taskdef tta "
				+ " on tt.pk_uplevel=tta.pk_obj and nvl(tta.dr,0)=0" + " where\n" + " tm.pk_dataent='" + key + "'\n" + " and oo.pk_corp='" + pk_orgs
				+ "' and tta.pk_obj = '1001N5100000000QMZE6'  and tm.pk_paradims='" + yeabytask + "'";
		Object pk_works = iquerybs.executeQuery(workbsql, new ColumnProcessor());
		String salethingsql = "select tablekey from tb_md_workbook  where pk_obj='" + pk_works + "' and nvl(dr,0)=0 ";
		Object sales = iquerybs.executeQuery(salethingsql, new ColumnProcessor());
		String existsql = "SELECT COUNT(*) FROM ALL_TABLES WHERE TABLE_NAME='tb_cell_" + sales + "'";
		Object exist = iquerybs.executeQuery(existsql, new ColumnProcessor());
		if (Integer.parseInt(exist.toString()) > 0) {
			String desql = "delete from tb_cell_" + sales + " where pk_sheet='" + pk_sheet + "' and pk_task='" + pk_task + "'";
			ica.updatesql(desql);
		}
		// name[0]=SUBSTITUtaskDataModelTE(name[0],RIGHT(name[0],2),"收入");
		if (tsmodel.getName().equals("薪酬底表")) {
			XcDb(csModel, tsmodel, mainorg, tbSheetViewer, taskDataModel, rowno, colno, key);
		}
		if (tsmodel.getName().equals("福利底表-手工填写") || tsmodel.getName().equals("福利底表-汇总")) {
			FlDb(csModel, tsmodel, mainorg, tbSheetViewer, taskDataModel, rowno, colno, key);
		}
		if (tsmodel.getName().equals("福利底表-取数")) {
			FlDbGetNum(csModel, tsmodel, mainorg, tbSheetViewer, taskDataModel, rowno, colno, key);
		}
		if (tsmodel.getName().equals("租金收入底表")) {
			ZjGetnum(ormessage, yea, tbSheetViewer,taskDataModel,tsmodel, rowno,colno, csModel);
		}
		if (tsmodel.getName().equals("人力成本预算")) {
			Rlbudget(tsmodel, rowno, csModel, tbSheetViewer, yea, cpleng, key);
		}
		if (tsmodel.getName().equals("物品需求预算")) {
			Thingnbudget(tsmodel, rowno, csModel, tbSheetViewer, yea, cpleng, key);
		}
		if (tsmodel.getName().equals("销售成本预算")) {
			Salencbudget(tsmodel, rowno, csModel, tbSheetViewer, yea, cpleng, key);
		}
		if (tsmodel.getName().equals("折旧摊销预算")) {
			if (tbSheetViewer.getSelectedCell().get(0).getRow() != 7) {
				throw new BusinessException("请选择第8行预算项目下的数据取数");
			}
			for (int e = 6; e < rowno - 2; e++) {
				if (null != csModel.getCellValue(e, 11) && csModel.getCellValue(e, 11).toString().equals("取数数据行")) {
					for (int w = 14; w < colno - 1; w = w + 2) {
						csModel.setCellValue(e, w, null);
					}
				}
			}
			String zktxsql = "";
			String toyearsql = "select max(period) as period from fa_depgather where pk_org='" + mainorg + "' and accyear= '" + (yea) + "'";
			String gathersql = "select max(period) as period from fa_depgather where pk_org='" + mainorg + "' and accyear= '" + (yea - 1) + "'";
			DepGatherVO gathermonth = (DepGatherVO) iquerybs.executeQuery(gathersql, new BeanProcessor(DepGatherVO.class));
			DepGatherVO toyearmonth = (DepGatherVO) iquerybs.executeQuery(toyearsql, new BeanProcessor(DepGatherVO.class));
			if (null != toyearmonth) {
				if (null != gathermonth) {
					if (Integer.parseInt(gathermonth.getPeriod()) == 11) {
						zktxsql = "  select  b.pk_org,b.pk_mandept, b.pk_category,b.depamount ,b.servicemonth-b.usedmonth as usedmonth "
								+ " from fa_cardhistory b  left join fa_usingstatus c on b.pk_usingstatus=c.pk_usingstatus "
								+ "  left join fa_depmethod d on b.pk_depmethod=d.pk_depmethod   "
								+ "   left join fa_card h on b.pk_card=h.pk_card "
								+ "      left join fa_category k on b.pk_category=k.pk_category "
								+ "        left join org_dept n on b.pk_mandept=n.pk_dept and b.pk_org=n.pk_org   left join fa_deptscale fd on fd.link_key=b.pk_usedept"
								+ " where b.dr =0 and c.status_name='在用' and d.depmethod_name='平均年限法(一)'   and b.asset_state ='exist' "
								+ "     and b.accyear=to_char(sysdate,'yyyy')  "
								+ "     and substr(k.cate_code,1,4) in ('1001','1201') and b.servicemonth-b.usedmonth-1>0          "
								+ "    and n.name not in ('技师三部','综合三部','技师二部')           and b.period='12' and b.pk_org='" + mainorg + "'  and fd.pk_dept ='"
								+ key + "'  and b.accyear='" + (yea - 1) + "' order by b.pk_category desc";// yea要减1
					} else if (Integer.parseInt(gathermonth.getPeriod()) == 12) {
						zktxsql = "  select  b.pk_org,b.pk_mandept, b.pk_category,b.depamount ,b.servicemonth-b.usedmonth-1 as usedmonth "
								+ " from fa_cardhistory b  left join fa_usingstatus c on b.pk_usingstatus=c.pk_usingstatus "
								+ "  left join fa_depmethod d on b.pk_depmethod=d.pk_depmethod   "
								+ "   left join fa_card h on b.pk_card=h.pk_card "
								+ "      left join fa_category k on b.pk_category=k.pk_category "
								+ "        left join org_dept n on b.pk_mandept=n.pk_dept and b.pk_org=n.pk_org   left join fa_deptscale fd on fd.link_key=b.pk_usedept"
								+ " where b.dr =0 and c.status_name='在用' and d.depmethod_name='平均年限法(一)'   and b.asset_state ='exist' "
								+ "     and b.accyear=to_char(sysdate,'yyyy')  "
								+ "     and substr(k.cate_code,1,4) in ('1001','1201') and b.servicemonth-b.usedmonth-1>0          "
								+ "    and n.name not in ('技师三部','综合三部','技师二部')           and b.period='11' and b.pk_org='" + mainorg + "'  and fd.pk_dept ='"
								+ key + "'  and b.accyear='" + (yea - 1) + "' order by b.pk_category desc";// yea要减1
					} else {
						MessageDialog.showErrorDlg(getCurrentView(), "提示", "当前组织上年11月未折旧!");
						return;
					}
				} else {
					return;
				}
			} else {
				MessageDialog.showErrorDlg(getCurrentView(), "提示", "本年已经折旧计提，无法取数!");
				return;
			}
			String salething1sql = "select cubecode from tb_md_workbook  where pk_obj='" + tsmodel.getMdSheet().getPk_workbook() + "' and nvl(dr,0)=0";
			code = iquerybs.executeQuery(salething1sql, new ColumnProcessor());
			int dsnum = 0;

			ArrayList<CardhistoryVO> dep = (ArrayList<CardhistoryVO>) iquerybs.executeQuery(zktxsql, new BeanListProcessor(CardhistoryVO.class));// 根据物料编码查询pk
			if (null == dep || dep.size() == 0) {
				MessageDialog.showErrorDlg(getCurrentView(), "提示", "没有符合条件的资产卡片!");
			}
			for (int it = 0; it < dep.size(); it++) {
				if (dep.get(it).getUsedmonth() > 0 && dep.get(it).getUsedmonth() < 13) {
					dsnum = dep.get(it).getUsedmonth();
				} else if (dep.get(it).getUsedmonth() > 12) {
					dsnum = 12;
				} else if (dep.get(it).getUsedmonth() < 0 && dep.get(it).getUsedmonth() == 0) {
					dsnum = 0;
				}
				int qq = 0;
				String ysxmsql = "select d.name from fip_docview_b b\n" + "left join bd_accasoa c on b.desdocvalue=c.pk_accasoa\n"
						+ "inner join bd_inoutbusiclass d on d.name=c.name\n" + "where b.pk_classview='1001N51000000001VGAK'  and factorvalue2='"
						+ dep.get(it).getPk_category() + "'";// 查预算项目名称
				Object ysname = iquerybs.executeQuery(ysxmsql, new ColumnProcessor());
				int montnum = 14;
				for (int t4 = 7; t4 < csModel.getColNum() - 2; t4++) {
					montnum = 14;
					if (null != csModel.getCellValue(t4, 6) && "取数数据行".equals(csModel.getCellValue(t4, 11)) && null != ysname
							&& csModel.getCellValue(t4, 6).equals(ysname)) {
						for (int t5 = 0; t5 < dsnum - 1; t5++) {
							if (null != csModel.getCellValue(t4, montnum) && !"".equals(csModel.getCellValue(t4, montnum).toString())
									&& null != dep.get(it).getDepamount().toDouble()) {
								csModel.setCellValue(t4, montnum, Double.valueOf(csModel.getCellValue(t4, montnum).toString())
										+ dep.get(it).getDepamount().toDouble());
								csModel.setCellValue(t4, 10, "Y");
								qq++;
								montnum = montnum + 2;
							} else {
								csModel.setCellValue(t4, montnum, Double.valueOf(dep.get(it).getDepamount().toDouble()));
								csModel.setCellValue(t4, 10, "Y");
								qq++;
								montnum = montnum + 2;
							}
						}
					}
					if (dsnum == 12) {
						csModel.setCellValue(t4, 36, csModel.getCellValue(t4, 34));
					}
				}
				if (qq == 0) {
					String tsql = "select pk_inoutbusiclass from bd_inoutbusiclass where name ='" + ysname.toString() + "'";
					// materValue.get
					Object fathreCodeValue = iquerybs.executeQuery(tsql, new ColumnProcessor());// 根据物料编码查询pk
					this.getCurrentViewer().getContext();
					CubeDef cd = CubeServiceGetter.getCubeDefQueryService().queryCubeDefByBusiCode(code.toString());// 根据cubecode获取前台信息
					List<DimDef> dd1 = cd.getDimDefs();
					DimDef dd = null;// 指标
					DimHierarchy dh = null;// 指标
					DimLevel dsl = null;
					for (int w = 0; w < dd1.size(); w++) {
						if (dd1.get(w).getObjName().equals("指标")) {
							dd = cd.getDimDefs().get(w);
							dh = cd.getDimHierarchy(dd1.get(w));
						}
					}
					for (int w = 0; w < cd.getDimLevels().size(); w++) {
						if (cd.getDimLevels().get(w).getObjName().equals("指标")) {
							dsl = cd.getDimLevels().get(w);
						}
					}
					LevelValue lv = dd.getLevelValue(dsl, fathreCodeValue.toString());
					DimMember g = dh.getDimMemberByLevelValues(lv);
					DimMember[] dimMembers = { g };
					Object[] aaa = new Object[dimMembers.length];
					aaa[0] = dimMembers[0];
					List<Cell> cells = tbSheetViewer.getSelectedCell();
					Cell c = cells.get(cells.size() - 1);
					CellExtInfo cInfo = (CellExtInfo) c.getExtFmt(TbIufoConst.tbKey);
					cInfo.setCellType(0);
					if (cInfo == null || cInfo.getVarId() == null)
						return;
					CellContentUtil util = new CellContentUtil(tbSheetViewer);
					util.addRow(aaa, 1);
					montnum = 14;
					for (int t6 = 0; t6 < dsnum - 1; t6++) {
						csModel.setCellValue(c.getRow() + 1, 6, ysname.toString());
						csModel.setCellValue(c.getRow() + 1, montnum, dep.get(it).getDepamount());
						csModel.setCellValue(c.getRow() + 1, 10, "Y");
						csModel.setCellValue(c.getRow() + 1, 11, "取数数据行");
						montnum = montnum + 2;
					}
					if (dsnum == 12) {
						csModel.setCellValue(c.getRow() + 1, 36, csModel.getCellValue(c.getRow() + 1, 34));
					}
				}
			}
		}
		/**
		 * HK 2020年11月2日16:45:55
		 */
		if (tsmodel.getName().equals("归口费用预算表")) {
			Action_itf itf = new Action_gkfyys();
			itf.doAction(ormessage, yea, tbSheetViewer, taskDataModel, tsmodel, rowno, colno, csModel);
		}
		/**
		 * HK 2020年11月2日16:46:05
		 */
		if (tsmodel.getName().equals("合同成本摊销预算")) {
			Action_itf itf = new Action_htcbtx();
			itf.doAction(ormessage, yea, tbSheetViewer, taskDataModel, tsmodel, rowno, colno, csModel);
		}
		/**
		 * HK 2020年11月19日23:41:31
		 */
		if (tsmodel.getName().equals("租赁收入底表")) {
			Action_itf itf = new Action_zjsrdb();
			itf.doAction(ormessage, yea, tbSheetViewer, taskDataModel, tsmodel, rowno, colno, csModel);
		}
		/***END***/
	}

	private void Rlbudget(TaskSheetDataModel tsmodel, int rowno, CellsModel csModel, TBSheetViewer tbSheetViewer, int yea, int cpleng, String key)
			throws BusinessException, DAOException {
		// TODO 自动生成的方法存根

		int leng = 14;
		int peng = 14;
		int gg = 0;
		int gg1 = 0;
		for (int q1 = 6; q1 < rowno - 3; q1++) {
			for (int ccc = 15; ccc < 29; ccc++) {
				if (null != csModel.getCellValue(q1, 11) && csModel.getCellValue(q1, 11).toString().equals("Y")) {
					csModel.setCellValue(q1, ccc, null);
				}
			}
		}
		// this.getCurrentView().get
		// if (tbSheetViewer.getSelectedCell().get(0).getRow() != 6) {
		// throw new BusinessException("请选择第7行预算项目下的数据取数");
		// }
		String yeabytask = "[ACCP=" + yea + "]";// 用于查询任务
		String orgsql = "select pk_corp from org_orgs where pk_org='" + key + "'";
		pk_org = iquerybs.executeQuery(orgsql, new ColumnProcessor());// 业务单元
		String workbsql = "select tt.pk_workbook\n" + "  from tb_md_taskdef tt\n" + " inner join tb_md_task tm\n"
				+ "    on tm.pk_workbook = tt.pk_workbook and nvl(tm.dr,0)=0\n" + " inner join org_orgs oo\n"
				+ "    on oo.pk_org = tm.pk_dataent and nvl(oo.dr,0)=0\n" + " inner join tb_md_taskdef tta "
				+ " on tt.pk_uplevel=tta.pk_obj and nvl(tta.dr,0)=0" + " where\n" + " tm.pk_dataent='" + key + "'\n" + " and oo.pk_corp='" + pk_org
				+ "' and tta.pk_obj = '1001N5100000000QMZE6'  and tm.pk_paradims='" + yeabytask + "'";
		String taskpksql = "select tm.pk_obj\n" + "  from tb_md_taskdef tt\n" + " inner join tb_md_task tm\n"
				+ "    on tm.pk_workbook = tt.pk_workbook and nvl(tm.dr,0)=0\n" + " inner join org_orgs oo\n"
				+ "    on oo.pk_org = tm.pk_dataent and nvl(oo.dr,0)=0\n" + " inner join tb_md_taskdef tta "
				+ " on tt.pk_uplevel=tta.pk_obj and nvl(tta.dr,0)=0" + " where\n" + " tm.pk_dataent='" + key + "'\n" + " and oo.pk_corp='" + pk_org
				+ "' and tta.pk_obj = '1001N5100000000QMZE6'  and tm.pk_paradims='" + yeabytask + "'";

		try {
			pk_work = iquerybs.executeQuery(workbsql, new ColumnProcessor());
			taskpk = iquerybs.executeQuery(taskpksql, new ColumnProcessor());
		} catch (BusinessException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		String salethingsql = "select tablekey from tb_md_workbook  where pk_obj='" + pk_work + "' and nvl(dr,0)=0 ";
		sale = iquerybs.executeQuery(salethingsql, new ColumnProcessor());

		String thingssql = "select pk_obj from tb_md_sheet where objname='产品销售预算' and pk_workbook='" + pk_work + "' and nvl(dr,0)=0";
		dustcode = iquerybs.executeQuery(thingssql, new ColumnProcessor());
		if (sale == null && dustcode == null) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "收入编制不存在对应会计期间的组织！");
			return;

		}
		MdTask task=TbTaskCtl.getMdTaskByPk(taskpk.toString(), true);
		String []s2=new String[1];
		s2[0]=dustcode.toString();
		TaskDataModel dModel=new TaskDataModel(task,s2,true,"");
		dModel.loadData();
		String zjbsql = "select * from tb_cell_" + sale.toString() + " where pk_sheet = '" + dustcode.toString() + "'  and pk_task='" + taskpk
				+ "' and nvl(dr,0)=0 order by nc_x,nc_y  ";
		ArrayList<CellwbkVO> md = null;
		try {
			md = (ArrayList<CellwbkVO>) iquerybs.executeQuery(zjbsql, new BeanListProcessor(CellwbkVO.class));
		} catch (BusinessException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		int m = 7;
		orgdept = "";
		ArrayList<BomItemVO> bomnum = null;
		// 循环获取产品信息
		for (int i1 = 0; i1 < md.size(); i1++) {
			orgdept = "";
			if (md.get(i1).getNc_x() == m && md.get(i1).getNc_y() == 7 && md.get(i1).getValue() != null) {// 判断位置
				orgdept = md.get(i1).getValue();
				String bomsql = "select bmb.*,bi.name as vdef12,bi.code as vdef13\n" + "  from bd_bom bm\n" + "  join bd_bom_b bmb\n"
						+ "    on bm.cbomid = bmb.cbomid\n" + "  join bd_material bml\n" + "    on bml.pk_material = bm.hcmaterialid and nvl(bml.dr,0)=0\n"
						+ "  join bd_accperiod ba\n" + "    on ba.periodyear='" + yea + "' and nvl(ba.dr,0)=0\n"
						+ "  join bd_inoutbusiclass bi .   on bi.pk_inoutbusiclass=bmb.vdef2 and nvl(bi.dr,0)=0 where bml.name = '"
						+ md.get(i1).getValue().toString() + "'\n" + "   and bm.pk_org = '" + pk_org + "'\n" + "   and bm.hfversiontype=1\n"
						+ " and nvl(bm.dr,0)=0   and bmb.cbeginperiod between ba.begindate and ba.enddate  and bi.code like'%640114%' "; //
				bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(bomsql, new BeanListProcessor(BomItemVO.class));
				// bi.code
				// like'%640114%'
				if (null == bomnum || bomnum.size() == 0) {// 如果bom中没有此产品，则去物料中查
					String wlsql = "select bi.name as vdef12,bi.code as vdef13,bv.name as vdef14,1 as Vdef3,def6 as vdef4,bv.pk_material  as Cmaterialvid ,bv.code as vdef15 from bd_material bv join bd_inoutbusiclass bi on bi.pk_inoutbusiclass=bv.def5  and nvl(bi.dr,0)=0 where bv.name='"+ orgdept + "' and bv.dr=0 and  bi.code not like'%640114%'";
					bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(wlsql, new BeanListProcessor(BomItemVO.class));
				}
			}
			
			if (bomnum != null) {
				// bomnum.get(j).getVdef2();// 默认预算项目
				// bomnum.get(j).getVdef4();
				for (int j = 0; j < bomnum.size(); j++) {
					leng = 14;
					peng = 14;
					cpleng = 15;
					int qq = 0;
					for (int q = 0; q < csModel.getColNum() - 3; q++) {
						if (null != csModel.getCellValue(q, 6)) {
							if (csModel.getCellValue(q, 6).toString().equals(bomnum.get(j).getVdef13().toString())) {
								// 如果人力中存在同样的预算项目则进行和每月原本金额相加运算
								for (int i2 = 200; i2 < md.size(); i2++) {
									if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == leng && md.get(i2).getNumvalue() != null) {
										if (csModel.getCellValue(q, 11).toString().equals("Y") && null != md.get(i2).getNumvalue()
												&& null != csModel.getCellValue(q, cpleng) && !"".equals(csModel.getCellValue(q, cpleng))) {
											// csModel.setCellValue(m - 1,
											// 7,
											// bomnum.get(j).getVdef12());
											// csModel.setCellValue(m - 1,
											// 6,
											// bomnum.get(j).getVdef13());
											csModel.setCellValue(
													q,
													cpleng,
													(md.get(i2).getNumvalue().toDouble() * (new UFDouble(bomnum.get(j).getVdef4())).toDouble())
															+ (Double.parseDouble(csModel.getCellValue(q, cpleng).toString())));// 设置每月金额
											cpleng++;
											leng = leng + 2;
										} else if (csModel.getCellValue(q, 11).toString().equals("Y") && null != md.get(i2).getNumvalue()) {
											csModel.setCellValue(q, 7, bomnum.get(j).getVdef12());
											csModel.setCellValue(q, 6, bomnum.get(j).getVdef13());
											csModel.setCellValue(q, cpleng,
													(md.get(i2).getNumvalue().toDouble() * (Double.parseDouble(bomnum.get(j).getVdef4()))));// 设置每月金额
											cpleng++;
											leng = leng + 2;
										}
									}
									if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == leng && md.get(i2).getNumvalue() != null) {
										cpleng++;
										leng = leng + 2;
									}
								}
								qq++;
							}
						}
					}
					if (qq == 0) {
						String tsql = "select pk_inoutbusiclass from bd_inoutbusiclass where name ='" + bomnum.get(j).getVdef12() + "' and nvl(dr,0)=0";
						Object fathreCodeValue = iquerybs.executeQuery(tsql, new ColumnProcessor());
						IDimManager dm = DimServiceGetter.getDimManager();
						DimDef dd = dm.getDimDefByPK("TB_DIMDEF_MEASURE_00");
						DimHierarchy dh = dm.getDimHierarchyByPK("TB_DIMHIER_MEASURE_0");
						DimLevel dsl = dm.getDimLevelByPK("TB_DIMLEV_MEASURE_00");
						LevelValue lv = dd.getLevelValue(dsl, fathreCodeValue.toString());
						DimMember g = dh.getDimMemberByLevelValues(lv);
						List<Object> busiObjs = new ArrayList<Object>();
						busiObjs.add(g);
						DimMember[] dimMembers = { g };
						Object[] aaa = new Object[dimMembers.length];
						aaa[0] = dimMembers[0];

						List<Cell> cells = tbSheetViewer.getSelectedCell();
						Cell c = cells.get(cells.size() - 1);
						CellExtInfo cInfo = (CellExtInfo) c.getExtFmt(TbIufoConst.tbKey);
						cInfo.setCellType(0);

						// String pk_user =
						// WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
						// String pk_group
						// =WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
						if (cInfo == null || cInfo.getVarId() == null)
							return;
						CellContentUtil util = new CellContentUtil(tbSheetViewer);
						// util.addLine(CellContentUtil.ADDLINEDOWN);
						// util.addRow(busiObjs.toArray(), 2);

						// util.addRow(1);
						// util.addTbBlock("D95", 7, aaa);

						// csModel.getgettaskSheetDataModel.addRow(baseBeginRow,blocksize,
						// blockCount,false,true);
						// String

						// ExVarDef exVarDef =
						// TbVarAreaUtil.getVarDefByCellExtInfo(cInfo);
						// ExTreeNode root = new ExTreeNode("指标", true);
						// DefaultTreeModel tm = new DefaultTreeModel(root,
						// false);
						// ExTreeNode nodeparFather = (ExTreeNode)
						// hm.get("1001N51000000002ARPX");
						// String ccc="BI.01.64010103 布草成本";
						// Object[] aaa = new String[1];
						// String[] bbb= ccc.toString().split("");
						// aaa[0]=bbb.toString();
						util.addRow(aaa, 1);
						csModel.setCellValue(c.getRow() + 1, 7, bomnum.get(j).getVdef12());
						csModel.setCellValue(c.getRow() + 1, 6, bomnum.get(j).getVdef13());
						for (int i2 = 240; i2 < md.size(); i2++) {
							if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == peng && md.get(i2).getNumvalue() != null) {
								if (null != md.get(i2).getNumvalue()) {
									csModel.setCellValue(c.getRow() + 1, 11, "Y");
									csModel.setCellValue(c.getRow() + 1, cpleng,
											(md.get(i2).getNumvalue().toDouble() * (new UFDouble(bomnum.get(j).getVdef4())).toDouble()));// 设置每月金额
									cpleng++;
									peng = peng + 2;
								}
							}
							if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == peng && md.get(i2).getNumvalue() != null) {
								cpleng++;
								peng = peng + 2;
							}

						}
					}
					gg++;
					// this.getCurrentView().refresh();
					// this.getCurrentView().insets();
				}
				gg1++;
			}

			if (md.get(i1).getNc_x() == m && md.get(i1).getNc_y() > 30) {
				m++;
			}
			bomnum = null;

		}
		if (gg == 0) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "产品销售表中无数据符合取数规则!");
			return;
		}
		if (gg1 == 0) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "项目成本卡中无数据符合取数规则!");
		}
		// this.getCurrentView().refresh();

	}

	private void Thingnbudget(TaskSheetDataModel tsmodel, int rowno, CellsModel csModel, TBSheetViewer tbSheetViewer, int yea, int cpleng, String key)
			throws BusinessException, DAOException {
		// TODO 自动生成的方法存根

		int leng = 14;
		int peng = 14;
		int gg = 0;
		int gg1 = 0;
		for (int q1 = 6; q1 < rowno - 3; q1++) {
			for (int ccc = 15; ccc < 29; ccc++) {
				if (null != csModel.getCellValue(q1, 11) && csModel.getCellValue(q1, 11).toString().equals("Y")) {
					csModel.setCellValue(q1, ccc, null);
				}
			}
		}
		StringBuffer matername = new StringBuffer();
		cpleng = 14;
		int qq = 0;
		if (null != csModel.getCellValue(7, 5)) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "重新取数，正在修改的数据将会被覆盖！");
		}
		// String[] name=taskDataModel.getMdTask().getObjname().split("_");
		// String newStr = name[0].replaceAll("支出","收入");
		String yeabytask = "[ACCP=" + yea + "]";// 用于查询任务
		String orgsql = "select pk_corp from org_orgs where pk_org='" + key + "'";
		pk_org = iquerybs.executeQuery(orgsql, new ColumnProcessor());// 业务单元
		String workbsql = "select tt.pk_workbook\n" + "  from tb_md_taskdef tt\n" + " inner join tb_md_task tm\n"
				+ "    on tm.pk_workbook = tt.pk_workbook and nvl(tm.dr,0)=0\n" + " inner join org_orgs oo\n"
				+ "    on oo.pk_org = tm.pk_dataent and nvl(oo.dr,0)=0\n" + " inner join tb_md_taskdef tta "
				+ " on tt.pk_uplevel=tta.pk_obj and nvl(tta.dr,0)=0" + " where\n" + " tm.pk_dataent='" + key + "'\n" + " and oo.pk_corp='" + pk_org
				+ "' and tta.pk_obj = '1001N5100000000QMZE6'  and tm.pk_paradims='" + yeabytask + "'";
		String taskpksql = "select tm.pk_obj\n" + "  from tb_md_taskdef tt\n" + " inner join tb_md_task tm\n"
				+ "    on tm.pk_workbook = tt.pk_workbook and nvl(tm.dr,0)=0\n" + " inner join org_orgs oo\n"
				+ "    on oo.pk_org = tm.pk_dataent and nvl(oo.dr,0)=0\n" + " inner join tb_md_taskdef tta "
				+ " on tt.pk_uplevel=tta.pk_obj and nvl(tta.dr,0)=0" + " where\n" + " tm.pk_dataent='" + key + "'\n" + " and oo.pk_corp='" + pk_org
				+ "' and tta.pk_obj = '1001N5100000000QMZE6'  and tm.pk_paradims='" + yeabytask + "'";

		try {
			pk_work = iquerybs.executeQuery(workbsql, new ColumnProcessor());
			taskpk = iquerybs.executeQuery(taskpksql, new ColumnProcessor());
		} catch (BusinessException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		String salethingsql = "select tablekey from tb_md_workbook  where pk_obj='" + pk_work + "' and nvl(dr,0)=0 ";
		sale = iquerybs.executeQuery(salethingsql, new ColumnProcessor());
		String salething1sql = "select cubecode from tb_md_workbook  where pk_obj='" + pk_work + "'  and nvl(dr,0)=0";
		code = iquerybs.executeQuery(salething1sql, new ColumnProcessor());
		if (sale == null || code == null) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "收入编制不存在对应会计期间的组织！");
			return;

		}
		String thingssql = "select pk_obj from tb_md_sheet where objname='产品销售预算' and pk_workbook='" + pk_work + "' and nvl(dr,0)=0";
		String thingstwosql = "select pk_obj from tb_md_sheet where objname='房费收入预算' and pk_workbook='" + pk_work + "' and nvl(dr,0)=0";
		dustcode = iquerybs.executeQuery(thingssql, new ColumnProcessor());
		dustcode1 = iquerybs.executeQuery(thingstwosql, new ColumnProcessor());
		if (null == dustcode && "".equals(dustcode) && null == dustcode1 && "".equals(dustcode1)) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "收入编制不存在对应会计期间的组织！");
			return;
		}
		MdTask task=TbTaskCtl.getMdTaskByPk(taskpk.toString(), true);
		String []s2=new String[2];
		s2[0]=dustcode.toString();
		s2[1]=dustcode1.toString();
		TaskDataModel dModel=new TaskDataModel(task,s2,true,"");
		dModel.loadData();
		String zjbsql = "select * from tb_cell_" + sale.toString() + " where pk_sheet = '" + dustcode.toString() + "'  and pk_task='" + taskpk
				+ "' and nvl(dr,0)=0 order by nc_x,nc_y ";
		String zjbsql1 = "select * from tb_cell_" + sale.toString() + " where pk_sheet = '" + dustcode1.toString() + "'  and pk_task='" + taskpk
				+ "' and nvl(dr,0)=0 order by nc_x,nc_y  ";
		ArrayList<CellwbkVO> md = null;
		ArrayList<CellwbkVO> ff = null;
		try {
			md = (ArrayList<CellwbkVO>) iquerybs.executeQuery(zjbsql, new BeanListProcessor(CellwbkVO.class));
			ff = (ArrayList<CellwbkVO>) iquerybs.executeQuery(zjbsql1, new BeanListProcessor(CellwbkVO.class));
		} catch (BusinessException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		int m = 7;
		orgdept = "";
		int n = 7;
		ArrayList<BomItemVO> bomnum = null;
		ysxm = "";
		StringBuffer bomname = new StringBuffer();
		// 循环获取产品信息
		for (int i1 = 0; i1 < md.size(); i1++) {
			if (md.get(i1).getNc_x() == m && md.get(i1).getNc_y() == 7 && md.get(i1).getValue() != null) {// 判断位置
				orgdept = md.get(i1).getValue();
				String bomsql = "select bmb.*,bi.name as vdef12,bi.code as vdef13\n"
						+ ", bv.name as vdef14  ,bv.code as vdef15,bm.hfversiontype as vdef16,bml.name as vdef17 	" + " from bd_bom bm\n"
						+ "  join bd_bom_b bmb\n" + "    on bm.cbomid = bmb.cbomid\n" + "   and nvl(bmb.dr,0 ) = 0\n" + "  join bd_material bml\n"
						+ "    on bml.pk_material = bm.hcmaterialid\n" + "   and nvl( bml.dr,0) = 0\n" + "  join bd_accperiod ba\n"
						+ "    on ba.periodyear = '" + yea + "'\n" + "   and nvl(ba.dr,0) = 0\n" + "  join bd_inoutbusiclass bi\n"
						+ "    on bi.pk_inoutbusiclass = bmb.vdef2    and nvl(bi.dr,0)=0\n" + "  join bd_material_v bv\n"
						+ "    on bmb.cmaterialvid = bv.pk_material     and nvl(bv.dr,0)=0 where bml.name = '" + md.get(i1).getValue().toString() + "'\n"
						+ "   and bm.pk_org = '" + pk_org + "'\n" + "  "
						+ "   and bmb.cbeginperiod between ba.begindate and ba.enddate and   bi.code not like'%640114%'  and bm.dr=0  ";// and
				bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(bomsql, new BeanListProcessor(BomItemVO.class));
				// bi.code
				// like'%640114%'
				if (null == bomnum || bomnum.size() == 0) {// 如果bom中没有此产品，则去物料中查
					String wlsql = "select bi.name as vdef12,bi.code as vdef13,bv.name as vdef14,1 as Vdef3,def6 as vdef4,bv.pk_material  as Cmaterialvid ,bv.code as vdef15 ,version as vdef16 from bd_material bv join bd_inoutbusiclass bi on bi.pk_inoutbusiclass=bv.def5  and nvl(bi.dr,0)=0 where bv.name='"
							+ orgdept+ "' and bv.dr=0 and  bi.code not like'%640114%'";
					bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(wlsql, new BeanListProcessor(BomItemVO.class));
				}
				for (int j = 0; j < bomnum.size(); j++) {
					if (bomnum.get(j).getVdef16() != null && Integer.parseInt(bomnum.get(j).getVdef16()) != 1) {// 去掉无效版本
						String bomrection = "项目成本卡中的产品'" + bomnum.get(j).getVdef17() + "'不是有效版本";
						if (matername.indexOf("''" + bomrection + "''") == -1) {
							matername.append("''").append(bomrection).append("''\n");
						}
						bomnum = null;
						break;
					}
				}

			}
			if (bomnum != null) {
				// bomnum.get(j).getVdef2();// 默认预算项目
				// bomnum.get(j).getVdef4();
				for (int j = 0; j < bomnum.size(); j++) {
					leng = 14;
					peng = 14;
					cpleng = 15;
					qq = 0;
					for (int q3 = 0; q3 < csModel.getRowNum() - 3; q3++) {
						// 匹配编码，如果相同则数量相加，若数量为空则赋值
						if (null != csModel.getCellValue(q3, 7) && null != csModel.getCellValue(q3, 4)
								&& !"".equals(csModel.getCellValue(q3, 4).toString())) {
							if (csModel.getCellValue(q3, 4).toString().equals(bomnum.get(j).getVdef13().toString())
									&& csModel.getCellValue(q3, 7).toString().equals(bomnum.get(j).getVdef15())) {
								// 如果需求表中中存在同样的预算项目则进行和每月原本金额相加运算
								for (int i2 = 200; i2 < md.size(); i2++) {
									if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == leng && md.get(i2).getNumvalue() != null) {
										if (null != md.get(i2).getNumvalue() && null != csModel.getCellValue(q3, cpleng - 1)
												&& null != csModel.getCellValue(q3, cpleng - 1)) {
											// csModel.setCellValue(m - 1,
											// 7,
											// bomnum.get(j).getVdef12());
											// csModel.setCellValue(m - 1,
											// 6,
											// bomnum.get(j).getVdef13());
											csModel.setCellValue(q3, cpleng - 1, ((md.get(i2).getNumvalue().toDouble() * (new UFDouble(bomnum.get(j)
													.getVdef3())).toDouble())) + (Double.parseDouble(csModel.getCellValue(q3, cpleng - 1).toString())));// 设置每月数量
											// csModel.setCellValue(q3,
											// cpleng,
											// ((md.get(i2).getNumvalue().toDouble()
											// * (new UFDouble(bomnum
											// .get(j).getVdef4())).toDouble()))
											// +
											// (Double.parseDouble(csModel.getCellValue(q3,
											// cpleng).toString())));//
											// 设置每月金额
											cpleng = cpleng + 2;
											leng = leng + 2;
										} else if (null != md.get(i2).getNumvalue()) {
											if (null != bomnum.get(j).getVdef3()) {

												// csModel.setCellValue(q3,
												// 5,
												// bomnum.get(j).getVdef12());
												// csModel.setCellValue(q3,
												// 4,
												// bomnum.get(j).getVdef13());
												csModel.setCellValue(q3, cpleng - 1,
														md.get(i2).getNumvalue().toDouble() * (new UFDouble(bomnum.get(j).getVdef3())).toDouble()
																* (new UFDouble(bomnum.get(j).getVdef3())).toDouble());// 数量=均次用量*数量
												// csModel.setCellValue(q3,
												// cpleng,
												// (md.get(i2).getNumvalue().toDouble()
												// *
												// (Double.parseDouble(bomnum.get(j).getVdef4()))));//
												// 设置每月金额
												cpleng = cpleng + 2;
												leng = leng + 2;
											}
										} else {
											csModel.setCellValue(q3, cpleng - 1, md.get(i2).getNumvalue().toDouble());
											cpleng = cpleng + 2;
											leng = leng + 2;
										}
									}
									if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == leng && md.get(i2).getNumvalue() == null) {
										cpleng = cpleng + 2;
										leng = leng + 2;
									}

								}
								qq++;
							}
						}
					}
					if (qq == 0) {
						// if(csModel.getSelectModel().getSelectedRow()[0]!=6&&csModel.getSelectModel().getSelectedCol()[0]!=7){
						// throw new BusinessException("请选择第7行的数据");
						// }
						String tsql = "select pk_inoutbusiclass from bd_inoutbusiclass where name ='" + bomnum.get(j).getVdef12() + "' and nvl(dr,0)=0";
						String wlql = " select * from bd_material where    pk_material='" + bomnum.get(j).getCmaterialvid() + "' and nvl(dr,0)=0";
						MaterialVO materValue = (MaterialVO) iquerybs.executeQuery(wlql, new BeanProcessor(MaterialVO.class));// 根据名字查询收支项目档案pk
						// materValue.get
						Object fathreCodeValue = iquerybs.executeQuery(tsql, new ColumnProcessor());// 根据物料编码查询pk
						CubeDef cd = CubeServiceGetter.getCubeDefQueryService().queryCubeDefByBusiCode(code.toString());// 根据cubecode获取前台信息
						List<DimDef> dd1 = cd.getDimDefs();
						DimDef dd = null;// 指标
						DimHierarchy dh = null;// 指标
						DimLevel dsl = null;
						DimDef wl = null;// 指标
						DimHierarchy wlchy = null;// 指标
						DimLevel wldsl = null;
						for (int w = 0; w < dd1.size(); w++) {
							if (dd1.get(w).getObjCode().equals("MEASURE")) {
								dd = cd.getDimDefs().get(w);
								dh = cd.getDimHierarchy(dd1.get(w));
							} else if (dd1.get(w).getObjCode().equals("MPPMARVERSION")) {
								wl = cd.getDimDefs().get(w);
								wlchy = cd.getDimHierarchy(dd1.get(w));
							}
						}
						for (int w = 0; w < cd.getDimLevels().size(); w++) {
							if (cd.getDimLevels().get(w).getObjCode().equals("MEASURE")) {
								dsl = cd.getDimLevels().get(w);
							} else if (cd.getDimLevels().get(w).getObjCode().equals("MPPMARVERSION")) {
								wldsl = cd.getDimLevels().get(w);
							}
						}
						LevelValue mater = wl.getLevelValue(wldsl, materValue.getPk_material().toString());
						LevelValue lv = dd.getLevelValue(dsl, fathreCodeValue.toString());
						if (lv == null) {
							if (matername.indexOf(bomnum.get(j).getVdef12()) == -1) {
								matername.append("当前指标档案没有分配'").append(bomnum.get(j).getVdef12()).append("'\n");
							}
							continue;
						}
						if (mater == null) {
							if (matername.indexOf(bomnum.get(j).getVdef14()) == -1) {
								matername.append("当前物料多版本档案没有分配'").append(bomnum.get(j).getVdef14()).append("'\n");
							}
							continue;
						}
						// 根据获取对应指标档案和物料多版本档案
						DimMember g = dh.getDimMemberByLevelValues(lv);
						DimMember maternu = wlchy.getDimMemberByLevelValues(mater);
						List<Object> busiObjs = new ArrayList<Object>();
						busiObjs.add(maternu);
						DimMember[] dimMembers = { g };
						Object[] aaa = new Object[dimMembers.length];
						aaa[0] = dimMembers[0];
						List<Cell> cells = tbSheetViewer.getSelectedCell();
						Cell c = cells.get(cells.size() - 1);
						CellExtInfo cInfo = (CellExtInfo) c.getExtFmt(TbIufoConst.tbKey);
						cInfo.setCellType(0);
						// String pk_user =
						// WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
						// String pk_group =
						// WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
						if (cInfo == null || cInfo.getVarId() == null)
							return;
						CellContentUtil util = new CellContentUtil(tbSheetViewer);
						util.addRow(aaa, 1);
						AreaPosition ap = csModel.getCombinedCellArea(CellPosition.getInstance(c.getRow() + 1, c.getCol() + 3));
						// 物料多版本关联指标
						VarCellValueModel varCellModel = new VarCellValueModel(0, csModel, c.getRow() + 1, c.getCol() + 3,
								new DimMember[] { (DimMember) maternu }, ap.split().length);
						varCellModel.fireCellValueChaned();
						csModel.setCellValue(c.getRow() + 1, 7, materValue.getCode());
						csModel.setCellValue(c.getRow() + 1, 8, materValue.getName());
						csModel.setCellValue(c.getRow() + 1, 5, bomnum.get(j).getVdef12());
						csModel.setCellValue(c.getRow() + 1, 4, bomnum.get(j).getVdef13());
						// 循环赋值
						for (int i2 = 240; i2 < md.size(); i2++) {
							if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == peng && md.get(i2).getNumvalue() != null) {
								if (null != md.get(i2).getNumvalue()) {
									csModel.setCellValue(c.getRow() + 1, 11, "N");
									csModel.setCellValue(c.getRow() + 1, cpleng - 1, md.get(i2).getNumvalue().toDouble()
											* (new UFDouble(bomnum.get(j).getVdef3())).toDouble());// 数量=均次用量*数量
									// csModel.setCellValue(c.getRow() + 1,
									// cpleng,
									// (md.get(i2).getNumvalue().toDouble()
									// * (new UFDouble(bomnum
									// .get(j).getVdef4())).toDouble()));//
									// 设置每月金额
									cpleng = cpleng + 2;
									peng = peng + 2;
								}
							}
							if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == peng && md.get(i2).getNumvalue() == null) {
								cpleng = cpleng + 2;
								peng = peng + 2;
							}
						}
					}
					gg++;
					// this.getCurrentView().refresh();
					// this.getCurrentView().insets();
				}
				m++;
			}
			bomnum = null;
			if (md.get(i1).getNc_x() == m && md.get(i1).getNc_y() > 30) {
				m++;
			}

		}

		// 循环获取房费信息
		for (int i1 = 0; i1 < ff.size(); i1++) {
			if (ff.get(i1).getNc_x() == n && ff.get(i1).getNc_y() == 6 && ff.get(i1).getValue() != null) {// 判断位置
				orgdept = ff.get(i1).getValue();
				String bomsql = "select bmb.*,bi.name as vdef12,bi.code as vdef13\n"
						+ ", bv.name as vdef14,bv.code as vdef15,bm.hfversiontype as vdef16,bml.name as vdef17	" + " from bd_bom bm\n"
						+ "  join bd_bom_b bmb\n" + "    on bm.cbomid = bmb.cbomid\n" + "   and nvl(bmb.dr,0) = 0\n" + "  join bd_material bml\n"
						+ "    on bml.pk_material = bm.hcmaterialid\n" + "   and nvl(bml.dr,0 ) = 0\n" + "  join bd_accperiod ba\n"
						+ "    on ba.periodyear = '" + yea + "'\n" + "   and nvl(ba.dr,0 ) = 0\n" + "  join bd_inoutbusiclass bi\n"
						+ "    on bi.pk_inoutbusiclass = bmb.vdef2    and nvl(bi.dr,0)=0\n" + "  join bd_material_v bv\n"
						+ "    on bmb.cmaterialvid = bv.pk_material     and nvl(bv.dr,0)=0 where bml.code = '" + ff.get(i1).getValue().toString() + "'\n"
						+ "   and bm.pk_org = '" + pk_org + "'\n" + "   "
						+ "   and bmb.cbeginperiod between ba.begindate and ba.enddate  and bi.code not like'%640114%'  ";// and
				// bi.code
				// like'%640114%'
				bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(bomsql, new BeanListProcessor(BomItemVO.class));
				if (null == bomnum || bomnum.size() == 0) {
					String wlsql = "select bi.name as vdef12,bi.code as vdef13,bv.name as vdef14,1 as Vdef3,def6 as vdef4,bv.pk_material as Cmaterialvid,bv.code as vdef15 from bd_material bv join bd_inoutbusiclass bi on bi.pk_inoutbusiclass=bv.def5  and nvl(bi.dr,0)=0where bv.code='"
							+ ff.get(i1).getValue().toString() + "'  and  bi.code not like'%640114%' and bv.dr=0 ";
					bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(wlsql, new BeanListProcessor(BomItemVO.class));
				}
				for (int j = 0; j < bomnum.size(); j++) {
					if (bomnum.get(j).getVdef16() != null && Integer.parseInt(bomnum.get(j).getVdef16()) != 1) {// 去掉无效版本
						String bomrection = "项目成本卡中的产品'" + bomnum.get(j).getVdef17() + "'不是有效版本";
						if (matername.indexOf("''" + bomrection + "''") == -1) {
							matername.append("''").append(bomrection).append("''\n");
						}
						bomnum = null;
						break;
					}
				}
			}
			if (bomnum != null && bomnum.size() > 0) {
				// bomnum.get(j).getVdef2();// 默认预算项目
				// bomnum.get(j).getVdef4();
				for (int j = 0; j < bomnum.size(); j++) {
					leng = 31;// 房费列值
					peng = 31;// 房费列值
					cpleng = 15;// 物品需求表的列值
					qq = 0;
					for (int q = 0; q < csModel.getRowNum() - 3; q++) {
						if (null != csModel.getCellValue(q, 4) && !"".equals(csModel.getCellValue(q, 4).toString()) && null != bomnum.get(j).getVdef13()) {
							if (null != csModel.getCellValue(q, 7) && csModel.getCellValue(q, 4).toString().equals(bomnum.get(j).getVdef13().toString())
									&& csModel.getCellValue(q, 7).toString().equals(bomnum.get(j).getVdef15())) {// 匹配编码，如果相同则数量相加，若数量为空则赋值
								// 如果需求表中中存在同样的预算项目则进行和每月原本金额相加运算
								for (int i2 = 0; i2 < ff.size(); i2++) {
									if (ff.get(i2).getNc_x() == n && ff.get(i2).getNc_y() == leng && ff.get(i2).getNumvalue() != null) {
										if (null != ff.get(i2).getNumvalue() && null != csModel.getCellValue(q, cpleng - 1)
												&& null != csModel.getCellValue(q, cpleng - 1)) {
											// csModel.setCellValue(n - 1,
											// 7,
											// bomnum.get(j).getVdef12());
											// csModel.setCellValue(n - 1,
											// 6,
											// bomnum.get(j).getVdef13());
											csModel.setCellValue(q, cpleng - 1, ((ff.get(i2).getNumvalue().toDouble() * (new UFDouble(bomnum.get(j)
													.getVdef3())).toDouble())) + (Double.parseDouble(csModel.getCellValue(q, cpleng - 1).toString())));// 设置每月金额
											// csModel.setCellValue(q,
											// cpleng, (
											// ff.get(i2).getNumvalue().toDouble()
											// * new UFDouble(bomnum.get(j)
											// .getVdef4()).toDouble()) +
											// (Double.parseDouble(csModel.getCellValue(q,
											// cpleng).toString())));//
											// 设置每月金额
											cpleng = cpleng + 2;
											leng = leng + 5;
										} else if (null != ff.get(i2).getNumvalue()) {
											if (null != bomnum.get(j).getVdef3()) {
												// csModel.setCellValue(q,
												// 5,
												// bomnum.get(j).getVdef12());
												// csModel.setCellValue(q,
												// 4,
												// bomnum.get(j).getVdef13());
												csModel.setCellValue(q, cpleng - 1, ff.get(i2).getNumvalue().toDouble()
														* (new UFDouble(bomnum.get(j).getVdef3())).toDouble());// 数量=均次用量*数量
												// csModel.setCellValue(q,
												// cpleng,
												// ff.get(i2).getNumvalue().toDouble()
												// *
												// (Double.parseDouble(bomnum.get(j).getVdef4())));//
												// 设置每月金额
												cpleng = cpleng + 2;
												leng = leng + 5;
											} else {
												csModel.setCellValue(q, cpleng - 1, ff.get(i2).getNumvalue().toDouble());
												cpleng = cpleng + 2;
												leng = leng + 5;
												if (q == 6) {
													q++;
												}
											}
										}
									}
									if (ff.get(i2).getNc_x() == n && ff.get(i2).getNc_y() == leng && ff.get(i2).getNumvalue() == null) {
										cpleng = cpleng + 2;
										leng = leng + 5;
									}
									qq++;
								}
							}
						}
					}
					if (qq == 0) {
						// if(csModel.getSelectModel().getSelectedRow()[0]!=6&&csModel.getSelectModel().getSelectedCol()[0]!=7){
						// throw new BusinessException("请选择第7行的数据");
						// }
						String tsql = "select pk_inoutbusiclass from bd_inoutbusiclass where name ='" + bomnum.get(j).getVdef12() + "' and nvl(dr,0)=0";
						String wlql = " select * from bd_material where    pk_material='" + bomnum.get(j).getCmaterialvid() + "' and nvl(dr,0)=0";
						MaterialVO materValue = (MaterialVO) iquerybs.executeQuery(wlql, new BeanProcessor(MaterialVO.class));// 根据名字查询收支项目档案pk
						// materValue.get
						Object fathreCodeValue = iquerybs.executeQuery(tsql, new ColumnProcessor());// 根据物料编码查询pk
						CubeDef cd = CubeServiceGetter.getCubeDefQueryService().queryCubeDefByBusiCode(code.toString());// 根据cubecode获取前台信息
						List<DimDef> dd1 = cd.getDimDefs();
						DimDef dd = null;// 指标
						DimHierarchy dh = null;// 指标
						DimLevel dsl = null;
						DimDef wl = null;// 指标
						DimHierarchy wlchy = null;// 指标
						DimLevel wldsl = null;

						for (int w = 0; w < dd1.size(); w++) {
							if (dd1.get(w).getObjName().equals("指标")) {
								dd = cd.getDimDefs().get(w);
								dh = cd.getDimHierarchy(dd1.get(w));
							} else if (dd1.get(w).getObjCode().equals("MPPMARVERSION")) {
								wl = cd.getDimDefs().get(w);
								wlchy = cd.getDimHierarchy(dd1.get(w));
							}
						}
						for (int w = 0; w < cd.getDimLevels().size(); w++) {
							if (cd.getDimLevels().get(w).getObjName().equals("指标")) {
								dsl = cd.getDimLevels().get(w);
							} else if (cd.getDimLevels().get(w).getObjCode().equals("MPPMARVERSION")) {
								wldsl = cd.getDimLevels().get(w);
							}
						}
						LevelValue mater = wl.getLevelValue(wldsl, materValue.getPk_material().toString());
						LevelValue lv = dd.getLevelValue(dsl, fathreCodeValue.toString());
						if (lv == null) {
							if (matername.indexOf(bomnum.get(j).getVdef12()) == -1) {
								matername.append("当前指标档案没有分配'").append(bomnum.get(j).getVdef12()).append("'\n");
							}
							continue;
						}
						if (mater == null) {
							if (matername.indexOf(bomnum.get(j).getVdef14()) == -1) {
								matername.append("当前物料多版本档案没有分配'").append(bomnum.get(j).getVdef14()).append("'\n");
							}
							continue;
						}
						DimMember g = dh.getDimMemberByLevelValues(lv);
						DimMember maternu = wlchy.getDimMemberByLevelValues(mater);
						if (mater == null || lv == null) {
							continue;
						}
						List<Object> busiObjs = new ArrayList<Object>();
						busiObjs.add(maternu);
						DimMember[] dimMembers = { g };
						Object[] aaa = new Object[dimMembers.length];
						aaa[0] = dimMembers[0];
						List<Cell> cells = tbSheetViewer.getSelectedCell();
						Cell c = cells.get(cells.size() - 1);
						CellExtInfo cInfo = (CellExtInfo) c.getExtFmt(TbIufoConst.tbKey);
						cInfo.setCellType(0);
						// String pk_user =
						// WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
						// String pk_group =
						// WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
						if (cInfo == null || cInfo.getVarId() == null)
							return;
						CellContentUtil util = new CellContentUtil(tbSheetViewer);
						// util.addLine(CellContentUtil.ADDLINEDOWN);
						// util.addRow(busiObjs.toArray(), 2);

						// util.addRow(1);
						// util.addTbBlock("D95", 7, aaa);
						util.addRow(aaa, 1);
						// util.addTbBlock("D139", 7, aaa);
						// listener.cellDataChanged(aaa);
						AreaPosition ap = csModel.getCombinedCellArea(CellPosition.getInstance(c.getRow() + 1, c.getCol() + 3));

						VarCellValueModel varCellModel = new VarCellValueModel(0, csModel, c.getRow() + 1, c.getCol() + 3,
								new DimMember[] { (DimMember) maternu }, ap.split().length);
						varCellModel.fireCellValueChaned();
						csModel.setCellValue(c.getRow() + 1, 7, materValue.getCode());
						csModel.setCellValue(c.getRow() + 1, 8, materValue.getName());
						csModel.setCellValue(c.getRow() + 1, 5, bomnum.get(j).getVdef12());
						csModel.setCellValue(c.getRow() + 1, 4, bomnum.get(j).getVdef13());
						for (int i2 = 240; i2 < ff.size(); i2++) {
							if (ff.get(i2).getNc_x() == n && ff.get(i2).getNc_y() == peng && ff.get(i2).getNumvalue() != null) {
								if (null != ff.get(i2).getNumvalue()) {
									csModel.setCellValue(c.getRow() + 1, 11, "N");
									csModel.setCellValue(c.getRow() + 1, cpleng - 1, ff.get(i2).getNumvalue().toDouble()
											* (new UFDouble(bomnum.get(j).getVdef3())).toDouble());// 数量=均次用量*数量
									// csModel.setCellValue(c.getRow() +
									// 1,
									// cpleng,
									// (ff.get(i2).getNumvalue().toDouble()
									// * (new UFDouble(bomnum
									// .get(j).getVdef4())).toDouble()));//
									// 设置每月金额
									cpleng = cpleng + 2;
									peng = peng + 5;
								}
							}
							if (ff.get(i2).getNc_x() == n && ff.get(i2).getNc_y() == peng && ff.get(i2).getNumvalue() == null) {
								cpleng = cpleng + 2;
								peng = peng + 5;
							}
						}
					}
				}
				gg1++;

				// this.getCurrentView().insets();
			}
			bomnum = null;
			if (ff.get(i1).getNc_x() == n && ff.get(i1).getNc_y() > 30) {
				n++;
			}

		}
		if (gg == 0) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "产品销售表中无数据符合取数规则!");
		}
		if (gg1 == 0) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "房费收入表中无数据符合取数规则!");
		}
		if (new String(matername) != null && !"".equals(new String(matername))) {
			matername = matername.deleteCharAt(matername.length() - 1);
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "" + matername + "!");
		}
	}

	private void Salencbudget(TaskSheetDataModel tsmodel, int rowno, CellsModel csModel, TBSheetViewer tbSheetViewer, int yea, int cpleng, String key)
			throws BusinessException, DAOException {
		if (tbSheetViewer.getSelectedCell().get(0).getRow() != 7) {
			throw new BusinessException("请选择第8行预算项目下的数据取数");
		}
		int colno = csModel.getColNum();// 即时列数
		StringBuffer matername = new StringBuffer();
		int leng = 14;
		int peng = 14;
		cpleng = 13;
		int qq = 0;
		int gg = 0;
		int m = 7;
		int n = 7;
		for (int q1 = 7; q1 < rowno - 1; q1++) {// 清空数据
			for (int ccc = 9; ccc < colno; ccc++) {
				csModel.setCellValue(q1, ccc, null);
			}
		}
	
		// String[] name=taskDataModel.getMdTask().getObjname().split("_");
		// String newStr = name[0].replaceAll("支出","收入");
		String yeabytask = "[ACCP=" + yea + "]";// 用于查询任务
		String orgsql = "select pk_corp from org_orgs where pk_org='" + key + "'";
		pk_org = iquerybs.executeQuery(orgsql, new ColumnProcessor());// 业务单元
		String taskpksql = "select tm.pk_obj \n" + "  from tb_md_taskdef tt\n" + "  join (select * from tb_md_taskdef where tasktype = 9) tb\n"
				+ "    on tb.pk_obj = tt.pk_uplevel\n" + "  join tb_md_task tm\n" + "    on tm.pk_taskdef = tt.pk_obj\n" + "  join org_orgs oo\n"
				+ "    on oo .pk_org=tm.pk_dataent\n" + " where\n" + " tm.pk_dataent='" + key + "'\n" + " and oo.pk_corp='" + pk_org + "' and tm.objname='"
				+ tsmodel.getMdTask().getObjname() + "'  and tm.pk_paradims='" + yeabytask + "'";
		try {
			taskpk = iquerybs.executeQuery(taskpksql, new ColumnProcessor());
		} catch (BusinessException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		String salethingsql = "select tablekey from tb_md_workbook  where "
				+ "pk_obj='" + tsmodel.getMdTask().getPk_workbook() + "' and nvl(dr,0)=0 ";
		sale = iquerybs.executeQuery(salethingsql, new ColumnProcessor());
		String salething1sql = "select cubecode from tb_md_workbook  where pk_obj='" + tsmodel.getMdTask().getPk_workbook() + "' and nvl(dr,0)=0";
		code = iquerybs.executeQuery(salething1sql, new ColumnProcessor());
		String thingssql = "select pk_obj from tb_md_sheet where objname='产品销售预算' and pk_workbook='" + tsmodel.getMdTask().getPk_workbook()
				+ "' and nvl(dr,0)=0";
		String thingstwosql = "select pk_obj from tb_md_sheet where objname='房费收入预算' and pk_workbook='" + tsmodel.getMdTask().getPk_workbook()
				+ "' and nvl(dr,0)=0";
	
		dustcode = iquerybs.executeQuery(thingssql, new ColumnProcessor());
		dustcode1 = iquerybs.executeQuery(thingstwosql, new ColumnProcessor());
		if (null == dustcode && null == dustcode1) {
			return;
		}	
		if (sale!=null) {
			String desql = "delete from tb_cell_" + sale.toString() + " where pk_sheet in('" + dustcode.toString() + "','" + dustcode1.toString() + "') and pk_task='" + taskpk + "'";
			ica.updatesql(desql);
		}
		MdTask task=TbTaskCtl.getMdTaskByPk(taskpk.toString(), true);
		String []s2=new String[2];
		s2[0]=dustcode.toString();
		s2[1]=dustcode1.toString();
		TaskDataModel dModel=new TaskDataModel(task,s2,true,"");
//		dModel.setIsSheetLockEnable(true);
		dModel.loadData();
		String zjbsql = "select * from tb_cell_" + sale.toString() + " where pk_sheet = '" + dustcode.toString() + "' and pk_task='" + taskpk
				+ "' and nvl(dr,0)=0  order by nc_x,nc_y ";
		String zjbsql1 = "select * from tb_cell_" + sale.toString() + " where pk_sheet = '" + dustcode1.toString() + "'  and pk_task='" + taskpk
				+ "' and nvl(dr,0)=0  order by nc_x,nc_y  ";
		ArrayList<CellwbkVO> md = null;
		ArrayList<CellwbkVO> ff = null;
		try {
			md = (ArrayList<CellwbkVO>) iquerybs.executeQuery(zjbsql, new BeanListProcessor(CellwbkVO.class));
			ff = (ArrayList<CellwbkVO>) iquerybs.executeQuery(zjbsql1, new BeanListProcessor(CellwbkVO.class));
		} catch (BusinessException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		String orgdept = "";
		ArrayList<BomItemVO> bomnum = null;
		String ysxm = "";
		ysxm = "";
		// 循环获取产品信息
		for (int i1 = 0; i1 < md.size(); i1++) {
			if (md.get(i1).getNc_x() == m && md.get(i1).getNc_y() ==6 && md.get(i1).getValue() != null) {// 判断位置
				orgdept = md.get(i1).getValue();
				String bomsql = "select sum(bmb.vdef4) as vdef4,\n"
						+ "       bi.name as vdef12,\n"
						+ "       bi.code as vdef13,\n"
						+ "       bml.name as vdef14,\n"
						+ "       bm.hfversiontype as vdef15,\n"
						+ "    bi.pk_inoutbusiclass as vdef17,bml.code as vdef16  from bd_bom bm\n"
						+ "  join bd_bom_b bmb\n"
						+ "    on bm.cbomid = bmb.cbomid\n"
						+ "   and nvl(bmb.dr,0) = 0\n"
						+ "  join bd_material bml\n"
						+ "    on bml.pk_material = bm.hcmaterialid\n"
						+ "   and nvl(bml.dr,0) = 0\n"
						+ "  join bd_accperiod ba on ba.periodyear = '"
						+ yea
						+ "'\n"
						+ "   and nvl(ba.dr,0) = 0\n"
						+ "  join bd_inoutbusiclass bi\n"
						+ "    on bi.pk_inoutbusiclass = bmb.vdef2    and nvl(bi.dr,0)=0\n"
						+ "  join bd_material_v bv\n"
						+ "    on bmb.cmaterialvid = bv.pk_material     and nvl(bv.dr,0)=0 where bml.code = '"
						+ orgdept
						+ "'\n"
						+ "  and nvl(bm.dr,0)=0  and bm.pk_org = '"
						+ pk_org
						+ "'\n"
						+ "   \n"
						+ "   and bmb.cbeginperiod between ba.begindate and ba.enddate  group by bi.name,bi.code ,bml.name,bm.hfversiontype, bi.pk_inoutbusiclass,bml.code ";// and
				bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(bomsql, new BeanListProcessor(BomItemVO.class));
				// bi.code
				// like'%640114%'
				if (null == bomnum || bomnum.size() == 0) {// 如果bom中没有此产品，则去物料中查
					String wlsql = "select bi.name as vdef12,bi.code as vdef13,bv.name as vdef14,1 as Vdef3,def6 as vdef4,bv.pk_material as Cmaterialvid,bv.code as vdef16 ,bi.pk_inoutbusiclass as vdef17,version as vdef15 from bd_material bv join bd_inoutbusiclass bi on bi.pk_inoutbusiclass=bv.def5     and nvl(bi.dr,0)=0 where bv.code='"
							+ orgdept + "' and nvl(bv.dr,0)=0";
					bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(wlsql, new BeanListProcessor(BomItemVO.class));
				}
				for (int j = 0; j < bomnum.size(); j++) {
					if (bomnum.get(j).getVdef15() != null && Integer.parseInt(bomnum.get(j).getVdef15()) != 1) {// 去掉无效版本
						String bomrection = "项目成本卡中的产品'" + bomnum.get(j).getVdef14() + "'不是有效版本";
						if (matername.indexOf("''" + bomrection + "''") == -1) {
							matername.append("''").append(bomrection).append("''\n");
						}
						bomnum = null;
						break;
					}
				}

			}
			if (bomnum != null) {
				// bomnum.get(j).getVdef2();// 默认预算项目
				// bomnum.get(j).getVdef4();
				for (int j = 0; j < bomnum.size(); j++) {
					leng = 14;
					peng = 14;
					cpleng = 9;
					qq = 0;
					for (int q3 = 0; q3 < csModel.getRowNum() - 2; q3++) {
						// 匹配编码，如果相同则数量相加，若数量为空则赋值
						if (null != csModel.getCellValue(q3, 5) && null != csModel.getCellValue(q3, 3) && !"".equals(csModel.getCellValue(q3, 3).toString())) {
							if (csModel.getCellValue(q3, 3).toString().equals(bomnum.get(j).getVdef13().toString())
									&& csModel.getCellValue(q3, 5).toString().equals(bomnum.get(j).getVdef16())) {
								// 如果需求表中中存在同样的预算项目则进行和每月原本金额相加运算
								for (int i2 = 200; i2 < md.size(); i2++) {
									if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == leng && md.get(i2).getNumvalue() != null) {
										if (null != md.get(i2).getNumvalue() && null != csModel.getCellValue(q3, cpleng)) {
											if (null != bomnum.get(j).getVdef4()) {
												// 设置成本金额
												csModel.setCellValue(q3, 8, Double.parseDouble(bomnum.get(j).getVdef4().toString()));// }
											}
											csModel.setCellValue(q3, cpleng,
													(md.get(i2).getNumvalue().toDouble()) + (Double.parseDouble(csModel.getCellValue(q3, cpleng).toString())));// 设置每月数量
											cpleng = cpleng + 2;
											leng = leng + 2;
										} else if (null != md.get(i2).getNumvalue()) {
											if (null != bomnum.get(j).getVdef4()) {
												csModel.setCellValue(q3, 8, Double.parseDouble(bomnum.get(j).getVdef4().toString()));// 金额
											}
											csModel.setCellValue(q3, cpleng, (md.get(i2).getNumvalue().toDouble()));// 设置每月
											cpleng = cpleng + 2;
											leng = leng + 2;
										}
									}
									if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == leng && md.get(i2).getNumvalue() == null) {
										cpleng = cpleng + 2;
										leng = leng + 2;
									}

								}
								qq++;
							}
						}
					}
					if (qq == 0) {
						// if(csModel.getSelectModel().getSelectedRow()[0]!=6&&csModel.getSelectModel().getSelectedCol()[0]!=7){
						// throw new BusinessException("请选择第7行的数据");
						// }
						String wlql = " select * from bd_material where    code='" + orgdept + "' and nvl(dr,0)=0";
						MaterialVO materValue = (MaterialVO) iquerybs.executeQuery(wlql, new BeanProcessor(MaterialVO.class));// 物料档案
						// materValue.get
						Object fathreCodeValue = bomnum.get(j).getVdef17();// 收支项目pk
						CubeDef cd = CubeServiceGetter.getCubeDefQueryService().queryCubeDefByBusiCode(code.toString());// 根据cubecode获取前台信息
						List<DimDef> dd1 = cd.getDimDefs();
						DimDef dd = null;// 指标
						DimHierarchy dh = null;// 指标
						DimLevel dsl = null;
						DimDef wl = null;// 指标
						DimHierarchy wlchy = null;// 指标
						DimLevel wldsl = null;

						for (int w = 0; w < dd1.size(); w++) {
							if (dd1.get(w).getObjName().equals("指标")) {
								dd = cd.getDimDefs().get(w);
								dh = cd.getDimHierarchy(dd1.get(w));
							} else if (dd1.get(w).getObjCode().equals("MARBAS")) {
								wl = cd.getDimDefs().get(w);
								wlchy = cd.getDimHierarchy(dd1.get(w));
							}
						}
						for (int w = 0; w < cd.getDimLevels().size(); w++) {
							if (cd.getDimLevels().get(w).getObjName().equals("指标")) {
								dsl = cd.getDimLevels().get(w);
							} else if (cd.getDimLevels().get(w).getObjCode().equals("MARBAS")) {
								wldsl = cd.getDimLevels().get(w);
							}
						}
						LevelValue mater = wl.getLevelValue(wldsl, materValue.getPk_material().toString());
						LevelValue lv = dd.getLevelValue(dsl, fathreCodeValue.toString());
						if (lv == null) {
							if (matername.indexOf(bomnum.get(j).getVdef12()) == -1) {
								matername.append("当前指标档案没有分配'").append(bomnum.get(j).getVdef12()).append("'\n");
							}
							continue;
						}
						if (mater == null) {
							if (matername.indexOf(bomnum.get(j).getVdef14()) == -1) {
								matername.append("当前物料多版本档案没有分配'").append(bomnum.get(j).getVdef14()).append("'\n");
							}
							continue;
						}
						DimMember g = dh.getDimMemberByLevelValues(lv);
						DimMember maternu = wlchy.getDimMemberByLevelValues(mater);
						List<Object> busiObjs = new ArrayList<Object>();
						busiObjs.add(maternu);
						DimMember[] dimMembers = { g };
						Object[] aaa = new Object[dimMembers.length];
						aaa[0] = dimMembers[0];
						List<Cell> cells = tbSheetViewer.getSelectedCell();
						Cell c = cells.get(cells.size() - 1);
						CellExtInfo cInfo = (CellExtInfo) c.getExtFmt(TbIufoConst.tbKey);
						cInfo.setCellType(0);
						// String pk_user =
						// WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
						// String pk_group =
						// WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
						if (cInfo == null || cInfo.getVarId() == null)
							return;
						CellContentUtil util = new CellContentUtil(tbSheetViewer);
						util.addRow(aaa, 1);
						AreaPosition ap = csModel.getCombinedCellArea(CellPosition.getInstance(c.getRow() + 1, c.getCol() + 2));

						VarCellValueModel varCellModel = new VarCellValueModel(0, csModel, c.getRow() + 1, c.getCol() + 2,
								new DimMember[] { (DimMember) maternu }, ap.split().length);
						csModel.setCellValue(c.getRow() + 1, 5, orgdept);// 物料编码
						csModel.setCellValue(c.getRow() + 1, 6, materValue.getName());// 物料名称
						csModel.setCellValue(c.getRow() + 1, 4, bomnum.get(j).getVdef12());// 预算项目数量
						csModel.setCellValue(c.getRow() + 1, 3, bomnum.get(j).getVdef13());// 预算项目编码
						varCellModel.fireCellValueChaned();
						// 物料多版本关联指标
						for (int i2 = 240; i2 < md.size(); i2++) {
//							if(md.get(i2).getNc_y()==4&&md.get(i2).getValue()== bomnum.get(j).getVdef13()){
//								csModel.setCellValue(c.getRow() + 1, 5, orgdept);// 物料编码
//								csModel.setCellValue(c.getRow() + 1, 6, materValue.getName());// 物料名称
//								csModel.setCellValue(c.getRow() + 1, 4, bomnum.get(j).getVdef12());// 预算项目数量
//								csModel.setCellValue(c.getRow() + 1, 3, bomnum.get(j).getVdef13());// 预算项目编码
//								varCellModel.fireCellValueChaned();
//							}
							if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == peng && md.get(i2).getNumvalue() != null) {
								if (null != md.get(i2).getNumvalue()) {
									if (null != bomnum.get(j).getVdef4()) {
										csModel.setCellValue(c.getRow() + 1, 8, Double.parseDouble(bomnum.get(j).getVdef4().toString()));// 成本金额总计
									}
									csModel.setCellValue(c.getRow() + 1, cpleng, md.get(i2).getNumvalue().toDouble());// 设置每月数量
									cpleng = cpleng + 2;
									peng = peng + 2;
								}
							}
							if (md.get(i2).getNc_x() == m && md.get(i2).getNc_y() == peng && md.get(i2).getNumvalue() == null) {
								cpleng = cpleng + 2;
								peng = peng + 2;
							}

						}

					}
					gg++;
					// this.getCurrentView().refresh();
					// this.getCurrentView().insets();
				}
				m++;
			}
			bomnum = null;
			if (md.get(i1).getNc_x() == m && md.get(i1).getNc_y() > 30) {
				m++;
			}

		}
		if (gg == 0) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "产品销售表中无数据符合取数规则!");
		}
		gg = 0;
		// 循环获取房费信息
		for (int i1 = 0; i1 < ff.size(); i1++) {
			if (ff.get(i1).getNc_x() == n && ff.get(i1).getNc_y() == 6 && ff.get(i1).getValue() != null) {// 判断位置
				orgdept = ff.get(i1).getValue();
				String bomsql = "select sum(bmb.vdef4) as vdef4,\n"
						+ "       bi.name as vdef12,\n"
						+ "       bi.code as vdef13,\n"
						+ "       bml.name as vdef14,\n"
						+ "       bm.hfversiontype as vdef15,\n"
						+ " bml.code as vdef16,   bi.pk_inoutbusiclass as vdef17  from bd_bom bm\n"
						+ "  join bd_bom_b bmb\n"
						+ "    on bm.cbomid = bmb.cbomid\n"
						+ "   and nvl(bmb.dr,0) = 0\n"
						+ "  join bd_material bml\n"
						+ "    on bml.pk_material = bm.hcmaterialid\n"
						+ "   and nvl(bml.dr,0) = 0\n"
						+ "  join bd_accperiod ba\n"
						+ "    on ba.periodyear = '"
						+ yea
						+ "'\n"
						+ "   and nvl(ba.dr,0) = 0\n"
						+ "  join bd_inoutbusiclass bi\n"
						+ "    on bi.pk_inoutbusiclass = bmb.vdef2    and nvl(bi.dr,0)=0\n"
						+ "  join bd_material_v bv\n"
						+ "    on bmb.cmaterialvid = bv.pk_material     and nvl(bv.dr,0)=0 where bml.code = '"
						+ orgdept
						+ "'\n"
						+ "  and nvl(bm.dr,0)=0  and bm.pk_org = '"
						+ pk_org
						+ "'\n"
						+ "   \n"
						+ "   and bmb.cbeginperiod between ba.begindate and ba.enddate  group by bi.name,bi.code ,bml.name,bm.hfversiontype, bi.pk_inoutbusiclass ,bml.code";// and
				bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(bomsql, new BeanListProcessor(BomItemVO.class));
				// bi.code
				// like'%640114%'
				if (null == bomnum || bomnum.size() == 0) {// 如果bom中没有此产品，则去物料中查
					String wlsql = "select bi.name as vdef12,bi.code as vdef13,bv.name as vdef14,1 as Vdef3,def6 as vdef4,bv.pk_material as Cmaterialvid ,bi.pk_inoutbusiclass as vdef17,bv.code as vdef16 from bd_material bv join bd_inoutbusiclass bi on bi.pk_inoutbusiclass=bv.def5     and nvl(bi.dr,0)=0 where bv.code='"
							+ orgdept + "' and nvl(bv.dr,0)=0";
					bomnum = (ArrayList<BomItemVO>) iquerybs.executeQuery(wlsql, new BeanListProcessor(BomItemVO.class));
				}
				for (int j = 0; j < bomnum.size(); j++) {
					if (bomnum.get(j).getVdef15() != null && Integer.parseInt(bomnum.get(j).getVdef15()) != 1) {// 去掉无效版本
						String bomrection = "项目成本卡中的产品'" + bomnum.get(j).getVdef14() + "'不是有效版本";
						if (matername.indexOf("''" + bomrection + "''") == -1) {
							matername.append("''").append(bomrection).append("''\n");
						}
						bomnum = null;
						break;
					}
				}
			}
			if (bomnum != null && bomnum.size() > 0) {
				// bomnum.get(j).getVdef2();// 默认预算项目
				// bomnum.get(j).getVdef4();
				for (int j = 0; j < bomnum.size(); j++) {
					leng = 31;// 房费列值
					peng = 31;// 房费列值
					cpleng = 9;// 物品需求表的列值
					qq = 0;
					for (int q = 0; q < csModel.getRowNum() - 2; q++) {
						if (null != csModel.getCellValue(q, 5) && null != csModel.getCellValue(q, 3) && !"".equals(csModel.getCellValue(q, 3).toString())) {
							if (csModel.getCellValue(q, 3).toString().equals(bomnum.get(j).getVdef13().toString())
									&& csModel.getCellValue(q, 5).toString().equals(bomnum.get(j).getVdef16())) {
								// 如果需求表中中存在同样的预算项目则进行和每月原本金额相加运算
								for (int i2 = 0; i2 < ff.size(); i2++) {
									if (ff.get(i2).getNc_x() == n && ff.get(i2).getNc_y() == leng && ff.get(i2).getNumvalue() != null) {
										if (null != ff.get(i2).getNumvalue() && null != csModel.getCellValue((q), cpleng)) {
											if (null != bomnum.get(j).getVdef4()) {
												csModel.setCellValue(q, 8, (Double.parseDouble(bomnum.get(j).getVdef4().toString())));
											}
											csModel.setCellValue(q, cpleng,
													(ff.get(i2).getNumvalue().toDouble()) + (Double.parseDouble(csModel.getCellValue(q, cpleng).toString())));// 设置每月数量
											cpleng += 2;
											leng += 5;
										} else if (((CellwbkVO) ff.get(i2)).getNumvalue() != null) {
											if (null != bomnum.get(j).getVdef4()) {
												csModel.setCellValue(q, 8, (Double.parseDouble(bomnum.get(j).getVdef4().toString())));
											}
											csModel.setCellValue(q, cpleng, ((CellwbkVO) ff.get(i2)).getNumvalue().toDouble());
											cpleng += 2;
											leng += 5;
										}
									}
									if (ff.get(i2).getNc_x() == n && ff.get(i2).getNc_y() == leng && ff.get(i2).getNumvalue() == null) {
										cpleng += 2;
										leng += 5;
									}

								}
								qq++;
							}

						}
					}
					if (qq == 0) {
						// if(csModel.getSelectModel().getSelectedRow()[0]!=6&&csModel.getSelectModel().getSelectedCol()[0]!=7){
						// throw new BusinessException("请选择第7行的数据");
						// }
						String wlql = " select * from bd_material where    code='" + orgdept + "' and nvl(dr,0)=0";
						MaterialVO materValue = (MaterialVO) iquerybs.executeQuery(wlql, new BeanProcessor(MaterialVO.class));// 物料档案
						// materValue.get
						Object fathreCodeValue = bomnum.get(j).getVdef17();// 收支项目pk
						CubeDef cd = CubeServiceGetter.getCubeDefQueryService().queryCubeDefByBusiCode(code.toString());// 根据cubecode获取前台信息
						List<DimDef> dd1 = cd.getDimDefs();
						DimDef dd = null;// 指标
						DimHierarchy dh = null;// 指标
						DimLevel dsl = null;
						DimDef wl = null;// 指标
						DimHierarchy wlchy = null;// 指标
						DimLevel wldsl = null;

						for (int w = 0; w < dd1.size(); w++) {
							if (dd1.get(w).getObjName().equals("指标")) {
								dd = cd.getDimDefs().get(w);
								dh = cd.getDimHierarchy(dd1.get(w));
							} else if (dd1.get(w).getObjCode().equals("MARBAS")) {
								wl = cd.getDimDefs().get(w);
								wlchy = cd.getDimHierarchy(dd1.get(w));
							}
						}
						for (int w = 0; w < cd.getDimLevels().size(); w++) {
							if (cd.getDimLevels().get(w).getObjName().equals("指标")) {
								dsl = cd.getDimLevels().get(w);
							} else if (cd.getDimLevels().get(w).getObjCode().equals("MARBAS")) {
								wldsl = cd.getDimLevels().get(w);
							}
						}
						LevelValue mater = wl.getLevelValue(wldsl, materValue.getPk_material().toString());
						LevelValue lv = dd.getLevelValue(dsl, fathreCodeValue.toString());
						if (lv == null) {
							if (matername.indexOf(bomnum.get(j).getVdef12()) == -1) {
								matername.append("当前指标档案没有分配'").append(bomnum.get(j).getVdef12()).append("'\n");
							}
							continue;
						}
						if (mater == null) {
							if (matername.indexOf(bomnum.get(j).getVdef14()) == -1) {
								matername.append("当前物料多版本档案没有分配'").append(bomnum.get(j).getVdef14()).append("'\n");
							}
							continue;
						}
						DimMember g = dh.getDimMemberByLevelValues(lv);
						DimMember maternu = wlchy.getDimMemberByLevelValues(mater);
						if (mater == null || lv == null) {
							continue;
						}
						List<Object> busiObjs = new ArrayList<Object>();
						busiObjs.add(maternu);
						DimMember[] dimMembers = { g };
						Object[] aaa = new Object[dimMembers.length];
						aaa[0] = dimMembers[0];
						List<Cell> cells = tbSheetViewer.getSelectedCell();
						Cell c = cells.get(cells.size() - 1);
						CellExtInfo cInfo = (CellExtInfo) c.getExtFmt(TbIufoConst.tbKey);
						cInfo.setCellType(0);
						if (cInfo == null || cInfo.getVarId() == null)
							return;
						CellContentUtil util = new CellContentUtil(tbSheetViewer);
						util.addRow(aaa, 1);
						AreaPosition ap = csModel.getCombinedCellArea(CellPosition.getInstance(c.getRow() + 1, c.getCol() + 2));

						VarCellValueModel varCellModel = new VarCellValueModel(0, csModel, c.getRow() + 1, c.getCol() + 2,
								new DimMember[] { (DimMember) maternu }, ap.split().length);
						csModel.setCellValue(c.getRow() + 1, 5, materValue.getCode());// 物料编码
						csModel.setCellValue(c.getRow() + 1, 6, materValue.getName());// 物料名称
						csModel.setCellValue(c.getRow() + 1, 4, bomnum.get(j).getVdef12());// 预算项目数量
						csModel.setCellValue(c.getRow() + 1, 3, bomnum.get(j).getVdef13());// 预算项目编码
						varCellModel.fireCellValueChaned();
						for (int i2 = 100; i2 < ff.size(); i2++) {
							if (ff.get(i2).getNc_x() == n && ff.get(i2).getNc_y() == peng && ff.get(i2).getNumvalue() != null) {
								if (null != ff.get(i2).getNumvalue()) {
									csModel.setCellValue(c.getRow() + 1, 10, csModel.getCellValue(c.getRow(), 10));
									if (null != bomnum.get(j).getVdef4()) {
										csModel.setCellValue(c.getRow() + 1, 8, Double.parseDouble(bomnum.get(j).getVdef4().toString()));// 每月金额
									}
									csModel.setCellValue(c.getRow() + 1, cpleng, ff.get(i2).getNumvalue().toDouble());// 数量
									cpleng = cpleng + 2;
									peng = peng + 5;
								}
							}
							if (ff.get(i2).getNc_x() == n && ff.get(i2).getNc_y() == peng && ff.get(i2).getNumvalue() == null) {
								cpleng = cpleng + 2;
								peng = peng + 5;
							}
						}
					}
					gg++;
				}

				// this.getCurrentView().insets();
			}
			bomnum = null;
			if (ff.get(i1).getNc_x() == n && ff.get(i1).getNc_y() > 30) {
				n++;
			}

		}
		if (gg == 0) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "房费收入表中无数据符合取数规则!");
		}
		if (new String(matername) != null && !"".equals(new String(matername))) {
			matername = matername.deleteCharAt(matername.length() - 1);
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "" + matername + "!");
		}
	}

	private void FlDbGetNum(CellsModel csModel, TaskSheetDataModel tsmodel, String mainorg, TBSheetViewer tbSheetViewer, TaskDataModel taskDataModel,
			int rowno, int colno, String key) throws BusinessException, DAOException {
		// TODO 自动生成的方法存根
		// 福利底表
		int y = 0;
		UIRefPane refPane = new UIRefPane("维度选择");/* -=notranslate=- */
		refPane.setMultiSelectedEnabled(true);
		TBDataCellRefModel tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel tBDataCellRefModel1 = (TBDataCellRefModel) refPane.getRefModel();
		DimMember[] dms1 = new DimMember[1];
		List<Cell> cells1 = tbSheetViewer.getSelectedCell();
		Cell cell = cells1.get(cells1.size() - 1);
		CellExtInfo cInfo1 = (CellExtInfo) cell.getExtFmt(TbIufoConst.tbKey);
		int varType = cInfo1.getExVarAreaDef().varAreaType;
		int celNum = varType == ExVarAreaDef.varAreatType_ROW ? cell.getCol() : cell.getRow();
		LevelValueOfDimLevelVO vo = new LevelValueOfDimLevelVO(celNum, "EMPLOYEE", null, null, taskDataModel.getMdTask());
		LevelValueOfDimLevelVO deptvo = new LevelValueOfDimLevelVO(celNum, "ENTITY", null, null, taskDataModel.getMdTask());

		ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo1);
		refPane.setMultiSelectedEnabled(true);
		String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
		Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(cell, cInfo1, exVarDef, tbSheetViewer.getCellsPane());
		try {
			TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel, vo, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		} catch (BusinessException e) {
			NtbLogger.print(e);
		}
		tBDataCellRefModel.getData();

		// 首先清空数据
		// for (int i2 = 6; i2 < rowno - 5; i2++) {// 先清空数据
		// for (int nul = 3; nul < colno; nul++) {
		// csModel.setCellValue(i2, nul, null);
		// }
		// }
		ArrayList<nc.vo.cmp.apply.ApplyVO> XClist = pf.getFlcsQSModels(csModel, tsmodel, mainorg);
		int colbm = 0;
		int rowbm = tbSheetViewer.getSelectedCell().get(0).getRow() - 2;

		int bircolbm = 0;
		// 查询部门所在行列
		for (int i3 = 6; i3 < rowno; i3++) {
			if (colbm > 0) {
				break;
			}
			for (int t4 = 3; t4 < colno; t4++) {
				if (null != tsmodel.getCellElement(i3, t4))
					if (null != tsmodel.getCellElement(i3, t4))
						if (null != tsmodel.getCellElement(i3, t4).getValue()) {
							if (("部门").equals(tsmodel.getCellElement(i3, t4).getValue().toString())) {
								colbm = t4;
								// rowbm = i3;
								break;
							}
						}
			}
		}

		rowbm = rowbm + 1;
		int rcolnum = 0;
		rcolnum = colbm;
		// 增行工具类
		CellContentUtil util = new CellContentUtil(tbSheetViewer);
		util.addMultiLine(1, XClist.size());
		int b = 0;
		Object jt = null;
		int xccol = 0;
		// 按固定行列赋值
		for (int i = 0; i < XClist.size(); i++) {
			tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
			rowbm++;
			xccol = 0;
			b = 0;
			for (int a = 0; a < 31; a++) {
				b = 1 + a;
				String project = "vdef" + b;
				jt = XClist.get(i).getAttributeValue(project);
				if (null != XClist.get(i).getAttributeValue(project) && b < 16) {
					int col = colbm + a;
					if (b > 9) {
						col++;
					}

					csModel.setCellValue(rowbm, col, XClist.get(i).getAttributeValue(project));

				}
				if (b == 16) {
					if (null == XClist.get(i).getVdef5()) {
						continue;
					}
					String birthmonth = Integer.parseInt(XClist.get(i).getVdef5()) + "月";
					for (int i3 = 3; i3 < rowno; i3++) {
						for (int t4 = 50; t4 < colno; t4++) {
							if (null != tsmodel.getCellElement(i3, t4).getValue())
								if ((tsmodel.getCellElement(i3, t4).getValue().toString()).contains("生日津贴")) {
									if ((birthmonth).equals(tsmodel.getCellElement(i3 + 1, t4).getValue().toString())) {
										bircolbm = t4;
										break;
									}
								}
						}
					}
					csModel.setCellValue(rowbm, bircolbm, jt);
				}
				if (b == 17 || b == 18 || b == 19 || b == 20 || b == 21 || b == 22 || b == 23 || b == 24) {
					if (b == 17) {
						for (int i3 = 3; i3 < rowno; i3++) {
							for (int t4 = 50; t4 < colno; t4++) {
								if (null != tsmodel.getCellElement(i3, t4).getValue())
									if ((tsmodel.getCellElement(i3, t4).getValue().toString()).contains("孝心养老")) {
										xccol = t4;
										break;
									}
							}
						}
					}
					for (int c = 0; c < 12; c++) {
						if (null != jt) {
							csModel.setCellValue(rowbm, xccol, XClist.get(i).getAttributeValue(project));
						}
						xccol = xccol + 1;
					}

					xccol++;

				}
				if (b == 25) {
					xccol = 0;
					for (int i3 = 3; i3 < rowno; i3++) {
						for (int t4 = 50; t4 < colno; t4++) {
							if (null != tsmodel.getCellElement(i3, t4).getValue())
								if ((tsmodel.getCellElement(i3, t4).getValue().toString()).contains("三年陈")) {
									xccol = t4;
									break;
								}
						}
						if (xccol > 0) {
							break;
						}
					}
					if (null != XClist.get(i).getAttributeValue(project)) {
						csModel.setCellValue(rowbm, xccol, jt);
					}
				}
				if (b == 26) {
					xccol = 0;
					for (int i3 = 3; i3 < rowno; i3++) {
						for (int t4 = 50; t4 < colno; t4++) {
							if (null != tsmodel.getCellElement(i3, t4).getValue())
								if ((tsmodel.getCellElement(i3, t4).getValue().toString()).contains("过节费")) {
									xccol = t4;
									break;
								}
						}
						if (xccol > 0) {
							break;
						}
					}
					for (int d = 0; d < 4; d++) {
						int f = b + d;
						project = "vdef" + f;
						jt = XClist.get(i).getAttributeValue(project);
						csModel.setCellValue(rowbm, xccol, jt);
						xccol++;
					}
				}
				if (b == 30) {
					xccol = 0;
					for (int i3 = 3; i3 < rowno; i3++) {
						for (int t4 = 50; t4 < colno; t4++) {
							if (null != tsmodel.getCellElement(i3, t4).getValue())
								if ((tsmodel.getCellElement(i3, t4).getValue().toString()).contains("女人节")) {
									xccol = t4;
									break;
								}
						}
						if (xccol > 0) {
							break;
						}
					}
					csModel.setCellValue(rowbm, xccol, jt);
				}
				if (b == 31) {
					xccol = 0;
					for (int i3 = 3; i3 < rowno; i3++) {
						for (int t4 = 50; t4 < colno; t4++) {
							if (null != tsmodel.getCellElement(i3, t4).getValue())
								if ((tsmodel.getCellElement(i3, t4).getValue().toString()).contains("劳保")) {
									xccol = t4;
									break;
								}
						}
						if (xccol > 0) {
							break;
						}
					}
					for (int d = 0; d < 4; d++) {
						int f = b + d;
						project = "summary";
						jt = XClist.get(i).getAttributeValue(project);
						csModel.setCellValue(rowbm, xccol, jt);
						xccol++;
					}
				}

			}
			// 设置人员维度信息

			DimMember dimMember = tBDataCellRefModel.getDimMember(XClist.get(i).getAttributeValue("pk_resuser").toString());
			if (null != dimMember) {
				DimMember[] dms = new DimMember[1];
				dms[0] = dimMember;
				VarCellValueModel varCellModel = new VarCellValueModel(0, getCellsModel(), rowbm, colbm + 1, dms, 1);

				try {
					// 设置单元格的多维信息
					varCellModel.fireCellValueChaned();
				} catch (BusinessException be) {
					NtbLogger.error(be);
				}
			}
			if (y == 0) {
				TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel1, deptvo, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
				y = 1;
				tBDataCellRefModel1.getData();
			}	DimMember dimMember1 = tBDataCellRefModel1.getDimMember(XClist.get(i).getAttributeValue("pk_busitype").toString());
		

			if (null != dimMember1) {
				dms1[0] = dimMember1;
			}
			VarCellValueModel varCellModel1 = new VarCellValueModel(0, getCellsModel(), rowbm, colbm, dms1, 1);
			try {
				varCellModel1.fireCellValueChaned();
			} catch (BusinessException be) {
				NtbLogger.error(be);
			}
		}

	}
	private void FlDb(CellsModel csModel, TaskSheetDataModel tsmodel, String mainorg, TBSheetViewer tbSheetViewer, TaskDataModel taskDataModel, int rowno,
			int colno, String key) throws BusinessException, DAOException {
	  try{
		// 福利底表
		int y = 0;
//			String month=sim.format(new Date()).substring(5, 7).indexOf(0)==0?Integer.valueOf(sim.format(new Date()).substring(6, 7))-1+"月":Integer.valueOf(sim.format(new Date()).substring(5, 7))-1+"月";
		UIRefPane refPane = new UIRefPane("维度选择");/* -=notranslate=- */
		refPane.setMultiSelectedEnabled(true);
		TBDataCellRefModel tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel tBDataCellRefModel1 = (TBDataCellRefModel) refPane.getRefModel();
		DimMember[] dms1 = new DimMember[1];
		List<Cell> cells1 = tbSheetViewer.getSelectedCell();
		Cell cell = cells1.get(cells1.size() - 1);
		CellExtInfo cInfo1 = (CellExtInfo) cell.getExtFmt(TbIufoConst.tbKey);
		int varType = cInfo1.getExVarAreaDef().varAreaType;
		int celNum = varType == ExVarAreaDef.varAreatType_ROW ? cell.getCol() : cell.getRow();
		LevelValueOfDimLevelVO vo = new LevelValueOfDimLevelVO(celNum, "EMPLOYEE", null, null, taskDataModel.getMdTask());
		LevelValueOfDimLevelVO deptvo = new LevelValueOfDimLevelVO(celNum, "ENTITY", null, null, taskDataModel.getMdTask());
		ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo1);
		refPane.setMultiSelectedEnabled(true);
		String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
		Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(cell, cInfo1, exVarDef, tbSheetViewer.getCellsPane());
	    TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel, vo, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
	    tBDataCellRefModel.getData();
		int rowbm = tbSheetViewer.getSelectedCell().get(0).getRow() - 2;
		int colbm=0;
		Boolean flag=false;
		// 查询部门第一次出现的行
		for (int i3 = 6; i3 < rowno; i3++) {
			for (int t4 = 3; t4 < colno; t4++) {
				if (null != tsmodel.getCellElement(i3, t4)){
					if (null != tsmodel.getCellElement(i3, t4).getValue()) {
						if (("部门").equals(tsmodel.getCellElement(i3, t4).getValue().toString().trim())) {
							flag=true;
							rowbm = i3;
							colbm=t4;
							break;
						}
					}
				}
			}
			if(flag){
				break;
			}
		}
		int b = rowbm;
		rowbm = rowbm + 2;	
		String sql="select emp.empid,emp.empname,emp.deptid,org_dept.name,bd_psndoc.glbdef26,org_dept.pk_dept,bd_psndoc.pk_psndoc,bd_psndoc.glbdef31,bd_psndoc.glbdef30 from emp left join org_dept on org_dept.def18 =emp.deptid "
				+" left join bd_psndoc on  bd_psndoc.glbdef26=emp.empid left join hi_psnjob on hi_psnjob.pk_psndoc=bd_psndoc.pk_psndoc left join bd_psncl on bd_psncl.pk_psncl=hi_psnjob.pk_psncl"
				+" where deptid in (select def18 from org_dept where code like (select code||'%' from org_dept  where pk_dept='"+key+"') and def18 !='~') and emp.deptid!='49447' and bd_psncl.pk_psncl!='1001N51000000000009C' and (length(bd_psndoc.id)=18 or length(bd_psndoc.id)=15) and hi_psnjob.lastflag='Y' and hi_psnjob.ismainjob='Y'  AND hi_psnjob.endflag !='Y' AND hi_psnjob.pk_psncl!='1001N51000000000009C'"
				+" group by emp.empid,emp.empname,emp.deptid,org_dept.name,bd_psndoc.glbdef26,org_dept.pk_dept,bd_psndoc.pk_psndoc,bd_psndoc.glbdef31,bd_psndoc.glbdef30 order by emp.deptid,bd_psndoc.glbdef26 ";
		ArrayList<Map<String, String>> emps = (ArrayList<Map<String, String>>)iquerybs.executeQuery(sql,new MapListProcessor());
		if(emps!=null&&emps.size()>0){
			// 增行工具类
			CellContentUtil util = new CellContentUtil(tbSheetViewer);
			util.addMultiLine(1, emps.size());
			for(int i=0;i<emps.size();i++){//循环此部门下的所有人员
				tBDataCellRefModel = (TBDataCellRefModel)refPane.getRefModel();
				rowbm++;
				String empname="";
				String deptname="";
				String pk_dept ="";
				String pk_psndoc="";
				String birthdate="";
				String month="";
				String sex="";
				String dl="";
				String fldj="";
				String ischild="";
				String rzrq="";
				String rznx="";
				empname=emps.get(i).get("empname")==null?"":emps.get(i).get("empname").toString();
				pk_dept = emps.get(i).get("pk_dept")==null?"":emps.get(i).get("pk_dept").toString();
				deptname = emps.get(i).get("name")==null?"":emps.get(i).get("name").toString();
				pk_psndoc= emps.get(i).get("pk_psndoc")==null?"":emps.get(i).get("pk_psndoc").toString();//部门pk
				//去定时器执行的中间表中去查询他的所有项目的薪资
				String sql1="select salaryname,salary from emp where EMPID='"+emps.get(i).get("glbdef26")+"'";
				ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>)iquerybs.executeQuery(sql1,new MapListProcessor());
				//入职时间（glbdef30） ，性别（ sex ），是否有子女（glbdef32），生日月份（birthdate ），入职日期（glbdef29），入职年限(glbdef25)FLOOR(MONTHS_BETWEEN (SYSDATE,	TO_DATE (hi_psnorg.begindate, 'yyyy-mm-dd')) / 12) rznx，福利等级（glbdef6），店龄（glbdef31），乐才id（glbdef26）
				String sqlAll="select substr(birthdate,6,2) birthdate,glbdef39,bd_defdoc.name,SUBSTR(bd_psndoc.glbdef29,0,11) glbdef29,"
							+ " case when bd_psndoc.sex = 1 then '男' when bd_psndoc.sex = 2 then '女' else null end sex, FLOOR(MONTHS_BETWEEN (SYSDATE,	TO_DATE (SUBSTR(bd_psndoc.glbdef29,0,11), 'yyyy-mm-dd')) / 12) rznx"
							+ " from bd_psndoc left join bd_psnjob on bd_psnjob.pk_psndoc= bd_psndoc.pk_psndoc "
							+ " INNER JOIN hi_psnorg ON bd_psndoc.pk_psndoc = hi_psnorg.pk_psndoc INNER JOIN hi_psnjob hi_psnjob ON hi_psnorg.pk_psnorg = hi_psnjob.pk_psnorg left join bd_defdoc  on bd_psndoc.glbdef6=bd_defdoc.pk_defdoc"
							+ " where bd_psndoc.pk_psndoc='"+pk_psndoc+"'  and hi_psnjob.lastflag='Y' and hi_psnjob.ismainjob='Y'  AND hi_psnjob.endflag !='Y' AND hi_psnjob.pk_psncl!='1001N51000000000009C'  and (length(bd_psndoc.id)=18 or length(bd_psndoc.id)=15) group by birthdate,glbdef39,bd_defdoc. NAME,bd_psndoc. glbdef29,bd_psndoc. sex";
				ArrayList<Map<String, Object>> XClist = (ArrayList<Map<String, Object>>)iquerybs.executeQuery(sqlAll,new MapListProcessor());
				if(XClist!=null&&XClist.size()>0){
					
					sex=XClist.get(0).get("sex")==null?"":XClist.get(0).get("sex").toString();
					birthdate=XClist.get(0).get("birthdate")==null?"":XClist.get(0).get("birthdate").toString();
					month=birthdate.length()==0?"":birthdate.substring(0, 1).equals("0")?birthdate.substring(1, 2):birthdate;
					dl=XClist.get(0).get("glbdef39")==null?"":XClist.get(0).get("glbdef39").toString();
					fldj=XClist.get(0).get("name")==null?"":XClist.get(0).get("name").toString();
					rzrq=XClist.get(0).get("glbdef29")==null?"":XClist.get(0).get("glbdef29").toString();//入职日期
					rznx=XClist.get(0).get("rznx")==null?"":XClist.get(0).get("rznx").toString();//入职年限
				}
				for (int t4 = 3; t4 < colno; t4++) {
					if (null != tsmodel.getCellElement(b, t4)){
						if (null != tsmodel.getCellElement(b, t4).getValue()) {
							if (("部门").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, deptname);//部门
							}else if (("姓名").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, empname);//姓名
							}else if (("性别").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, sex);//性别						
							}else if (("是否有子女").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, ischild);//是否有子女
							}else if (("生日月份").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, month);//生日月份
							}else if (("入职日期").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, rzrq);//入职日期
							}else if (("入职年限").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, rznx);//入职年限
							}else if (("福利等级").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, fldj);//福利等级
							}else if (("店龄").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, dl);//店龄
							}
						}
					}
				}
				if (list!=null&&list.size()>0){
					for (int t4 = 13; t4 < colno; t4++) {
						if (null != tsmodel.getCellElement(b, t4)){
							if (null != tsmodel.getCellElement(b, t4).getValue()) {
								for(int namecount=0;namecount<list.size();namecount++){//循环乐才在nc对照的所有的nc的项目名称
									String ncname=list.get(namecount).get("salaryname")==null?"":String.valueOf(list.get(namecount).get("salaryname")).trim();
									UFDouble sums=new UFDouble(list.get(namecount).get("salary")==null?"0":String.valueOf(list.get(namecount).get("salary")));
									String sheetname=tsmodel.getCellElement(b, t4).getValue().toString().trim();
									if ((ncname).equals(sheetname)) {
										if (!("小计").equals(tsmodel.getCellElement(b+1, t4).getValue().toString().trim())){
											csModel.setCellValue(rowbm, t4, sums);
										}
									}
								}
							}
						}
					}	
				}
			  DimMember dimMember = tBDataCellRefModel.getDimMember(pk_psndoc);
		      if (dimMember != null){
		        DimMember[] dms = new DimMember[1];
		        dms[0] = dimMember;
		        VarCellValueModel varCellModel = new VarCellValueModel(0, getCellsModel(), rowbm, colbm + 1, dms, 1);
		        varCellModel.fireCellValueChaned();
		      }
		      if (y == 0){
		        TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel1, deptvo, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		        y = 1;
		        tBDataCellRefModel1.getData();
		      }
		      DimMember dimMember1 = tBDataCellRefModel1.getDimMember(pk_dept);
		      if (dimMember1 != null) {
		         dms1[0] = dimMember1;
		      }
		      VarCellValueModel varCellModel1 = new VarCellValueModel(0, getCellsModel(), rowbm, colbm, dms1, 1);
			  varCellModel1.fireCellValueChaned();
			}
		 }
	   }catch (BusinessException e){
	     NtbLogger.print(e);
	   }
	}
	private void XcDb(CellsModel csModel, TaskSheetDataModel tsmodel, String mainorg, TBSheetViewer tbSheetViewer, TaskDataModel taskDataModel, int rowno,
			int colno, String key) throws BusinessException, DAOException {
	try{
		// 薪酬底表
		int y = 0;
		UIRefPane refPane = new UIRefPane("维度选择");/* -=notranslate=- */
		refPane.setMultiSelectedEnabled(true);
		TBDataCellRefModel tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
		TBDataCellRefModel tBDataCellRefModel1 = (TBDataCellRefModel) refPane.getRefModel();
		
		List<Cell> cells1 = tbSheetViewer.getSelectedCell();
		Cell cell = cells1.get(cells1.size() - 1);
		CellExtInfo cInfo1 = (CellExtInfo) cell.getExtFmt(TbIufoConst.tbKey);
		int varType = cInfo1.getExVarAreaDef().varAreaType;
		int celNum = varType == ExVarAreaDef.varAreatType_ROW ? cell.getCol() : cell.getRow();
		LevelValueOfDimLevelVO vo = new LevelValueOfDimLevelVO(celNum, "EMPLOYEE", null, null, taskDataModel.getMdTask());
		LevelValueOfDimLevelVO deptvo = new LevelValueOfDimLevelVO(celNum, "ENTITY", null, null, taskDataModel.getMdTask());
		ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo1);
		refPane.setMultiSelectedEnabled(true);
		String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
		Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(cell, cInfo1, exVarDef, tbSheetViewer.getCellsPane());
	    TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel, vo, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
	    tBDataCellRefModel.getData();
		int rowbm = tbSheetViewer.getSelectedCell().get(0).getRow() - 2;
		int colbm=0;
		Boolean flag=false;
		// 查询部门第一次出现的行
		for (int i3 = 5; i3 < rowno; i3++) {
			for (int t4 = 3; t4 < colno; t4++) {
				if (null != tsmodel.getCellElement(i3, t4)){
					if (null != tsmodel.getCellElement(i3, t4).getValue()) {
						if (("部门").equals(tsmodel.getCellElement(i3, t4).getValue().toString().trim())) {
							flag=true;
							rowbm = i3;
							colbm=t4;
							break;
						}
					}
				}
			}
			if(flag){
				break;
			}
		}
		int b = rowbm;
		rowbm = rowbm + 2;
		String sql="select emp.empid,emp.empname,emp.deptid,org_dept.name,bd_psndoc.glbdef26,org_dept.pk_dept,bd_psndoc.pk_psndoc,bd_psndoc.glbdef31,bd_psndoc.glbdef30 from emp left join org_dept on org_dept.def18 =emp.deptid "
				+" left join bd_psndoc on  bd_psndoc.glbdef26=emp.empid left join hi_psnjob on hi_psnjob.pk_psndoc=bd_psndoc.pk_psndoc left join bd_psncl on bd_psncl.pk_psncl=hi_psnjob.pk_psncl"
				+" where deptid in (select def18 from org_dept where code like (select code||'%' from org_dept  where pk_dept='"+key+"') and def18 !='~') and emp.deptid!='49447' and bd_psncl.pk_psncl!='1001N51000000000009C' and (length(bd_psndoc.id)=18 or length(bd_psndoc.id)=15) and hi_psnjob.lastflag='Y' and hi_psnjob.ismainjob='Y'  AND hi_psnjob.endflag !='Y' AND hi_psnjob.pk_psncl!='1001N51000000000009C'"
				+" group by emp.empid,emp.empname,emp.deptid,org_dept.name,bd_psndoc.glbdef26,org_dept.pk_dept,bd_psndoc.pk_psndoc,bd_psndoc.glbdef31,bd_psndoc.glbdef30 order by emp.deptid,bd_psndoc.glbdef26 ";
		ArrayList<Map<String, String>> emps = (ArrayList<Map<String, String>>)iquerybs.executeQuery(sql,new MapListProcessor());
		if(emps!=null&&emps.size()>0){
			// 增行工具类
			CellContentUtil util = new CellContentUtil(tbSheetViewer);
			util.addMultiLine(1, emps.size());
			for(int i=0;i<emps.size();i++){//循环此部门下的所有人员
				tBDataCellRefModel = (TBDataCellRefModel)refPane.getRefModel();
				rowbm++;
				String empname="";
				String deptname="";
				String pk_dept ="";
				String pk_psndoc="";
				String dl="";
				String rzrq="";
				empname=emps.get(i).get("empname")==null?"":emps.get(i).get("empname").toString();
				pk_dept = emps.get(i).get("pk_dept")==null?"":emps.get(i).get("pk_dept").toString();
				deptname = emps.get(i).get("name")==null?"":emps.get(i).get("name").toString();
				pk_psndoc= emps.get(i).get("pk_psndoc")==null?"":emps.get(i).get("pk_psndoc").toString();//部门pk
				//去定时器执行的中间表中去查询他的所有项目的薪资
				String sql1="select salaryname,salary from emp where EMPID='"+emps.get(i).get("glbdef26")+"'";
				ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>)iquerybs.executeQuery(sql1,new MapListProcessor());
				String sqlAll="select substr(bd_psndoc.glbdef29,0,11) glbdef29,glbdef39 dl from bd_psndoc where bd_psndoc.pk_psndoc='"+pk_psndoc+"'";
				ArrayList<Map<String, Object>> XClist = (ArrayList<Map<String, Object>>)iquerybs.executeQuery(sqlAll,new MapListProcessor());
//				入职时间（begindate）hi_psnjob   店龄(glbdef31) bd_psndoc
				if(XClist!=null&&XClist.size()>0){
					dl=XClist.get(0).get("glbdef39")==null?"":XClist.get(0).get("glbdef39").toString();
					rzrq=XClist.get(0).get("glbdef29")==null?"":XClist.get(0).get("glbdef29").toString();//入职日期
				}
				for (int t4 = 3; t4 < colno; t4++) {
					if (null != tsmodel.getCellElement(b, t4)){
						if (null != tsmodel.getCellElement(b, t4).getValue()) {
							if (("部门").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, deptname);//部门
							}else if (("姓名").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, empname);//姓名
							}else if (("入职时间").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, rzrq);//入职时间
							}else if (("店龄").equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
								csModel.setCellValue(rowbm, t4, dl);//店龄
							}
						}
					}
				}
				if(list!=null&&list.size()>0){
					for (int t4 = 12; t4 < colno; t4++) {
						if (null != tsmodel.getCellElement(b, t4)){
							if (null != tsmodel.getCellElement(b, t4).getValue()) {
								for(int namecount=0;namecount<list.size();namecount++){//循环乐才在nc对照的所有的nc的项目名称
									String ncname=list.get(namecount).get("salaryname")==null?"":String.valueOf(list.get(namecount).get("salaryname")).trim();
									UFDouble sums=new UFDouble(list.get(namecount).get("salary")==null?"0":String.valueOf(list.get(namecount).get("salary")));
									if ((ncname).equals(tsmodel.getCellElement(b, t4).getValue().toString().trim())) {
										if (!("小计").equals(tsmodel.getCellElement(b+1, t4).getValue().toString().trim())){
											csModel.setCellValue(rowbm, t4, sums);
										}
									}
								}
							}
						}
					}
				}
			      DimMember dimMember = tBDataCellRefModel.getDimMember(pk_psndoc);
			      if (dimMember != null){
			        DimMember[] dms = new DimMember[1];
			        dms[0] = dimMember;
			        VarCellValueModel varCellModel = new VarCellValueModel(0, getCellsModel(), rowbm, colbm + 1, dms, 1);
			        varCellModel.fireCellValueChaned();
			      }
			      if (y == 0){
			        TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel1, deptvo, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
			        y = 1;
			        tBDataCellRefModel1.getData();
			      }
			      DimMember dimMember1 = tBDataCellRefModel1.getDimMember(pk_dept);
			      DimMember[] dms1 = new DimMember[1];
			      if (dimMember1 != null) {
			        dms1[0] = dimMember1;
			      }
			      VarCellValueModel varCellModel1 = new VarCellValueModel(0, getCellsModel(), rowbm, colbm, dms1, 1);
			      varCellModel1.fireCellValueChaned();
			   }
		   }
	   }catch (BusinessException e){
	     NtbLogger.print(e);
	   }
	}
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private void ZjGetnum(StringBuffer ormessage, int yea,TBSheetViewer tbSheetViewer,TaskDataModel taskDataModel,TaskSheetDataModel tsmodel, int rowno,int colno,CellsModel csModel) {
		// 租金表计算天数
		try{
			if (tbSheetViewer.getSelectedCell().get(0).getRow() <7) {
				MessageDialog.showErrorDlg(null, "提示","请选择第8行之后的预算项目下的数据取数");
				return;
			}
			int y = 0;
			UIRefPane refPane = new UIRefPane("维度选择");/* -=notranslate=- */
			refPane.setMultiSelectedEnabled(true);
			TBDataCellRefModel tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
			TBDataCellRefModel tBDataCellRefModel1 = (TBDataCellRefModel) refPane.getRefModel();
//			TBDataCellRefModel tBDataCellRefModelwdjh = (TBDataCellRefModel) refPane.getRefModel();
			TBDataCellRefModel tBDataCellRefModelqdlx = (TBDataCellRefModel) refPane.getRefModel();
			List<Cell> cells1 = tbSheetViewer.getSelectedCell();
			Cell cell = cells1.get(cells1.size() - 1);
			CellExtInfo cInfo1 = (CellExtInfo) cell.getExtFmt(TbIufoConst.tbKey);
			int varType = cInfo1.getExVarAreaDef().varAreaType;
			int celNum = varType == ExVarAreaDef.varAreatType_ROW ? cell.getCol() : cell.getRow();
			LevelValueOfDimLevelVO vo = new LevelValueOfDimLevelVO(celNum, "CUSTOM", null, null, taskDataModel.getMdTask());
			LevelValueOfDimLevelVO fjhDim = new LevelValueOfDimLevelVO(celNum, "MARBAS", null, null, taskDataModel.getMdTask());
//			LevelValueOfDimLevelVO wdjhDim = new LevelValueOfDimLevelVO(celNum, "WDJH", null, null, taskDataModel.getMdTask());
			LevelValueOfDimLevelVO qdlxDim = new LevelValueOfDimLevelVO(celNum, "QDLX", null, null, taskDataModel.getMdTask());
			ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo1);
			refPane.setMultiSelectedEnabled(true);
			String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
			String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
			String key = tsmodel.getMdTask().getPk_dataent();// 责任主体
			String mainorg = iquerybs.executeQuery("select pk_corp from org_orgs where pk_org='" + key + "'", new ColumnProcessor()).toString();// 公司
			Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(cell, cInfo1, exVarDef, tbSheetViewer.getCellsPane());
			//渠道类型
			TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModelqdlx, qdlxDim, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
			tBDataCellRefModelqdlx.getData();
			//客户
		    TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel, vo, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
		    tBDataCellRefModel.getData();
		    //房间号
			TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel1, fjhDim, pk_user, pk_group, cInfo1.getCubeCode(), exVarDef, null, dvMap);
			tBDataCellRefModel1.getData();
			Boolean flag=false;
			String starttime=(yea-1)+"-12-26 00:00:00";
			String endtime=yea+"-12-25 23:59:59";
			String salething1sql = "select cubecode from tb_md_workbook  where pk_obj='" + tsmodel.getMdSheet().getPk_workbook() + "' and nvl(dr,0)=0";
			code = iquerybs.executeQuery(salething1sql, new ColumnProcessor());
			int colbm=0;
			int rowbm = 0;
			int selectrow=	tbSheetViewer.getSelectedCell().get(0).getRow() - 2;
			// 查询客户名称第一次出现的行
			for (int i3 = 5; i3 < rowno; i3++) {
				for (int t4 = 3; t4 < colno; t4++) {
					if (null != tsmodel.getCellElement(i3, t4)){
						if (null != tsmodel.getCellElement(i3, t4).getValue()) {
							if (("客户名称").equals(tsmodel.getCellElement(i3, t4).getValue().toString().trim())) {
								flag=true;
								rowbm = i3;
								colbm=t4;
								break;
							}
						}
					}
				}
				if(flag){
					break;
				}
			}
			int h = rowbm;//记录标题行
			rowbm = selectrow+1;
			//1生效  6终止2020-07-23  3审批通过 审批中
			String countsql="SELECT pk_ct_sale FROM ct_sale_b WHERE	vbdef4>='"+starttime+"' AND vbdef3<='"+endtime+"'  AND vbdef1 = '1001N51000000001UQ09' AND NVL (dr, 0) = 0"
						+" AND pk_ct_sale IN (select dd.pk_ct_sale from ( SELECT pk_ct_sale,DECODE(vdef19,'~',invallidate,vdef19) invallidate1"
						+" FROM	ct_sale ct_sale WHERE ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y' and fstatusflag in(1,3) AND ct_sale.pk_org = '"+mainorg+"' AND ct_sale.pk_group = '0001N510000000000EGY'"
						+" AND dr = 0	AND valdate <= '"+endtime+"')dd  where dd.invallidate1 >= '"+starttime+"' )GROUP BY pk_ct_sale,vbdef5";
//			String countsql="select pk_ct_sale from ct_sale_b where vbdef4>='"+starttime+"' and vbdef3<='"+endtime+"' and vbdef1='1001N51000000001UQ09'  and nvl(dr,0)=0 and pk_ct_sale in (SELECT ct_sale.pk_ct_sale FROM ct_sale ct_sale" 
//			+" WHERE ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y' AND ct_sale.pk_org='"+mainorg+"' AND ct_sale.pk_group = '0001N510000000000EGY' and dr=0 and  invallidate>='"+starttime+"' and valdate<='"+endtime+"') group by  pk_ct_sale,vbdef5";
			ArrayList<Map<String, String>> count = (ArrayList<Map<String, String>>)iquerybs.executeQuery(countsql,new MapListProcessor());
//			String countqtsql="	SELECT dd.pk_ct_sale FROM (SELECT	pk_ct_sale,DECODE (vdef19,'~',invallidate,	vdef19) invallidate1 FROM ct_sale ct_sale WHERE	ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y' AND fstatusflag IN (1, 3)"
//					+"	AND ct_sale.pk_org = '"+mainorg+"'	AND ct_sale.pk_group = '0001N510000000000EGY'	AND dr = 0 AND valdate <= '"+endtime+"') dd left join ct_sale_b on ct_sale_b.pk_ct_sale=dd.pk_ct_sale "
//					+" WHERE	dd.invallidate1 >= '"+starttime+"' and 	ct_sale_b.vbdef4 >= '"+starttime+"' AND ct_sale_b.vbdef3 <= '"+endtime+"' AND ct_sale_b.vbdef1 IN (	'1001N5100000000HNWBR',	'1001N5100000006XQ06S','1001N5100000006XQ04W') group by dd.pk_ct_sale";
//			ArrayList<Map<String, String>> countqt = (ArrayList<Map<String, String>>)iquerybs.executeQuery(countqtsql,new MapListProcessor());
			if(count!=null&&count.size()>0){
				CellContentUtil util = new CellContentUtil(tbSheetViewer);
				util.addMultiLine(1,count.size());
				//查询所有符合条件（2019-12-26---2020-12-25）的单据
				String sql="select dd.cusname cusname,dd. fjhname fjhname,dd.pk_ct_sale,dd.pk_customer,dd.vdef16,dd.valdate,dd.invallidate,dd.vdef5 from ( SELECT bd_customer. NAME cusname,"
						+"	bd_defdoc. NAME fjhname,ct_sale.pk_ct_sale,ct_sale.pk_customer,ct_sale.vdef16,ct_sale.valdate,ct_sale.vdef5,DECODE(vdef19,'~',invallidate,vdef19) invallidate FROM	ct_sale ct_sale "
						+"	LEFT JOIN bd_customer bd_customer ON ct_sale.pk_customer = bd_customer.pk_customer LEFT JOIN bd_defdoc bd_defdoc ON ct_sale.vdef16 = bd_defdoc.pk_defdoc WHERE	bd_defdoc.pk_defdoclist IN (SELECT pk_defdoclist"
						+"	FROM bd_defdoclist WHERE NAME = '房间号' AND NVL (dr, 0) = 0 ) and  ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y'  and ct_sale.fstatusflag in(1,3) AND ct_sale.pk_org='"+mainorg+"' AND ct_sale.pk_group = '0001N510000000000EGY'"
						+"  AND ct_sale.dr = 0 AND ct_sale.valdate <= '"+endtime+"')dd  where dd.invallidate >= '"+starttime+"'  ORDER BY dd.pk_customer";
//				String sql="SELECT bd_customer.name cusname,bd_defdoc.name fjhname,ct_sale.pk_ct_sale,ct_sale.pk_customer,ct_sale.vdef16,ct_sale.valdate,ct_sale.invallidate,ct_sale.vdef5 FROM ct_sale ct_sale left join bd_customer bd_customer"
//						+" on ct_sale.pk_customer=bd_customer.pk_customer left join  bd_defdoc bd_defdoc on  ct_sale.vdef16 =  bd_defdoc.pk_defdoc    WHERE bd_defdoc.pk_defdoclist in(select pk_defdoclist from bd_defdoclist where name='房间号' and nvl(dr,0)=0) and ct_sale.dr = 0 AND ct_sale.bshowlatest = 'Y'"
//						+" AND ct_sale.pk_group = '0001N510000000000EGY' AND ct_sale.pk_org='"+mainorg+"' and invallidate>='"+starttime+"' and valdate<='"+endtime+"' ORDER BY ct_sale.pk_customer";
				ArrayList<Map<String, String>> emps = (ArrayList<Map<String, String>>)iquerybs.executeQuery(sql,new MapListProcessor());
				if(emps!=null&&emps.size()>0){
					for(int i=0;i<emps.size();i++){//循环每个单据，根据日期和单价，对应新增行的数量不一样
						String zjbz=null;
						String pk_zjbz=null;
						String pk_customer="";
						String fjh="";
						String area=null;
						String valdate =null;
						String invallidate=null;
						String pk_ct_sale="";
						String rzj=null;
						String vbdef3=null;
						String vbdef4=null;
						String cusname=emps.get(i).get("cusname")==null?"":emps.get(i).get("cusname").toString();
						String fjhname=emps.get(i).get("fjhname")==null?"":emps.get(i).get("fjhname").toString();
						pk_ct_sale=emps.get(i).get("pk_ct_sale")==null?"":emps.get(i).get("pk_ct_sale").toString();
						pk_customer=emps.get(i).get("pk_customer")==null?"":emps.get(i).get("pk_customer").toString();
						fjh = emps.get(i).get("vdef16")==null?"":emps.get(i).get("vdef16").toString();//房间号
						area= emps.get(i).get("vdef5")==null?"":emps.get(i).get("vdef5").toString();//面积
						valdate=emps.get(i).get("valdate")==null?"":emps.get(i).get("valdate").toString();//开始签订日期
						invallidate=emps.get(i).get("invallidate")==null?"":emps.get(i).get("invallidate").toString();//截止日期
						String sqlrzj="select vbdef5 from(select  row_nUMBER() OVER(partition by vbdef5 order by vbdef3) as id, vbdef5,vbdef3  from ct_sale_b where pk_ct_sale='"+pk_ct_sale+"' and  vbdef3<='"+endtime+"' and vbdef1='1001N51000000001UQ09'  "
								+" and vbdef4>='"+starttime+"' and nvl(dr,0)=0  order by vbdef3)  where id = 1";
						ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>)iquerybs.executeQuery(sqlrzj,new MapListProcessor());
						int size=list.size();
						if(list!=null&&size>0){
							for(int k=0;k<size;k++){
								tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
								rzj =list.get(k).get("vbdef5").toString();
								String sqlone="select vbdef3,vbdef4 from ct_sale_b where pk_ct_sale='"+pk_ct_sale+"' and vbdef3<='"+endtime+"' and vbdef4>='"+starttime+"'  and vbdef1='1001N51000000001UQ09' and vbdef5= '"+rzj+"' and nvl(dr,0)=0 order by vbdef3";
								ArrayList<Map<String, Object>> listone = (ArrayList<Map<String, Object>>)iquerybs.executeQuery(sqlone,new MapListProcessor());
								int listsize=listone.size();
								if(listone!=null&&listsize>0){
									//只有一个 日租金，不论分为几个阶段月，按照日期排序，取第一行的开始日期和2019-12-26比较，取最后一行的截止日期和2020-12-25比较
									//两个日租金时，预算表增两行，第一行，开始日期取：第一个日租金，按照事件排序，第一行的开始日期和2019-12-26比较取2019-12-26大的，截止日期取：第一个日租金，最后一行的截止日期
									//第二行，开始日期取：第二个日租金，第一行的开始日期，截止日期，取最后一行的截止日期和2020-12-25比较
									//两个以上日租金时：第一个日租金和最后一个日租金的日期，同两个日租金一样,
									if(size==1){
										valdate=listone.get(0).get("vbdef3").toString();
										invallidate=listone.get(listsize-1).get("vbdef4").toString();
										if(valdate.compareTo(starttime)<0||valdate.compareTo(starttime)==0){
											vbdef3=starttime.substring(0, 10);
										}else{
											vbdef3=valdate.substring(0, 10);
										}
										if(invallidate.compareTo(endtime)<0||invallidate.compareTo(endtime)==0){
											vbdef4=invallidate.substring(0, 10);
										}else{
											vbdef4=endtime.substring(0, 10);
										}
									}else if(size==2){
										valdate=listone.get(0).get("vbdef3").toString();
										invallidate=listone.get(listsize-1).get("vbdef4").toString();
										if(k==0){
											if(valdate.compareTo(starttime)<0||valdate.compareTo(starttime)==0){
												vbdef3=starttime.substring(0, 10);
											}else{
												vbdef3=valdate.substring(0, 10);
											}
											vbdef4=invallidate.substring(0, 10);
										}else{
											vbdef3=valdate.substring(0, 10);
											if(invallidate.compareTo(endtime)<0||invallidate.compareTo(endtime)==0){
												vbdef4=invallidate.substring(0, 10);
											}else{
												vbdef4=endtime.substring(0, 10);
											}
											zjbz="租金调整一";
											pk_zjbz="1001N5100000000V2BBZ";
										}
									}else{
										valdate=listone.get(0).get("vbdef3").toString();
										invallidate=listone.get(listsize-1).get("vbdef4").toString();
										if(k==0){
											if(valdate.compareTo(starttime)<0||valdate.compareTo(starttime)==0){
												vbdef3=starttime.substring(0, 10);
											}else{
												vbdef3=valdate.substring(0, 10);
											}
											vbdef4=invallidate.substring(0, 10);
										}else if(k==(listsize-1)){
											vbdef3=valdate.substring(0, 10);
											if(invallidate.compareTo(endtime)<0||invallidate.compareTo(endtime)==0){
												vbdef4=invallidate.substring(0, 10);
											}else{
												vbdef4=endtime.substring(0, 10);
											}
											if(k==1){
												zjbz="租金调整一";
												pk_zjbz="1001N5100000000V2BBZ";
											}else if(k==2){
												zjbz="租金调整二";
												pk_zjbz="1001N5100000000V2BC0";
											}else if(k==3){
												zjbz="租金调整三";
												pk_zjbz="1001N5100000000V2BC1";
											}else if(k==4){
												zjbz="租金调整四";
												pk_zjbz="1001N5100000000V2BC2";
											}else if(k==5){
												zjbz="租金调整五";
												pk_zjbz="1001N5100000000V2BC3";
											}
										
										}else{
											vbdef3=valdate.substring(0, 10);
											vbdef4=invallidate.substring(0, 10);
											if(k==1){
												zjbz="租金调整一";
												pk_zjbz="1001N5100000000V2BBZ";
											}else if(k==2){
												zjbz="租金调整二";
												pk_zjbz="1001N5100000000V2BC0";
											}else if(k==3){
												zjbz="租金调整三";
												pk_zjbz="1001N5100000000V2BC1";
											}else if(k==4){
												zjbz="租金调整四";
												pk_zjbz="1001N5100000000V2BC2";
											}else if(k==5){
												zjbz="租金调整五";
												pk_zjbz="1001N5100000000V2BC3";
											}
										}
									}
									rowbm++;
									//循环set值
									for (int t4 = 3; t4 < colno; t4++) {
										if (null != tsmodel.getCellElement(h, t4)){
											if (null != tsmodel.getCellElement(h, t4).getValue()) {
												if (tsmodel.getCellElement(h, t4).getValue().toString().trim().contains("租金调整")) {
													csModel.setCellValue(rowbm, t4, zjbz);
												}else if (("客户名称").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
													csModel.setCellValue(rowbm, t4, cusname);
											    }else if (("出租面积").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
													csModel.setCellValue(rowbm, t4, area);
											    }else if (tsmodel.getCellElement(h, t4).getValue().toString().trim().contains("摊位名称")) {
													csModel.setCellValue(rowbm, t4, fjhname);
												}else if (("开始日期").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
													csModel.setCellValue(rowbm, t4,java.sql.Date.valueOf(vbdef3));
//													tsmodel.getCellAt(rowbm, t4).setValue(java.sql.Date.valueOf(vbdef3));
												}else if (("截止日期").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
													csModel.setCellValue(rowbm, t4,java.sql.Date.valueOf(vbdef4));
//													tsmodel.getCellAt(rowbm, t4).setValue(java.sql.Date.valueOf(vbdef4));
												}else if (("日租金").equals(tsmodel.getCellElement(h, t4).getValue().toString().trim())) {
													csModel.setCellValue(rowbm, t4,rzj);
//													tsmodel.getCellAt(rowbm, t4).setValue(rzj);
											    }
											}
										}
									}
//									 设置渠道类型维度信息
									if(pk_zjbz!=null&&pk_zjbz.length()>0&&!"".equals(pk_zjbz)){
										DimMember dimMemberqdlx = tBDataCellRefModelqdlx.getDimMember(pk_zjbz);
										if (null != dimMemberqdlx) {
											DimMember[] dmsqdlx = new DimMember[1];
											dmsqdlx[0] = dimMemberqdlx;
											VarCellValueModel varCellModelqdlx = new VarCellValueModel(0, getCellsModel(), rowbm, colbm-1, dmsqdlx, 1);
											try {
												varCellModelqdlx.fireCellValueChaned();
											} catch (BusinessException be) {
												NtbLogger.error(be);
											}
										}	
									}
//									 设置客户维度信息
									DimMember dimMember = tBDataCellRefModel.getDimMember("CUSTOM"+pk_customer);
									if (null != dimMember) {
										DimMember[] dms = new DimMember[1];
										dms[0] = dimMember;
										VarCellValueModel varCellModel = new VarCellValueModel(0, getCellsModel(), rowbm, colbm, dms, 1);
										try {
											// 设置单元格的多维信息
											varCellModel.fireCellValueChaned();
										} catch (BusinessException be) {
											NtbLogger.error(be);
										}
									}
									// 设置房间号维度信息
									DimMember dimMember1 = tBDataCellRefModel1.getDimMember("MARBAS"+fjh);
									if (null != dimMember1) {
										DimMember[] dms1 = new DimMember[1];
										dms1[0] = dimMember1;
										VarCellValueModel varCellModel1 = new VarCellValueModel(0, getCellsModel(), rowbm, colbm+2, dms1, 1);
										try {
											varCellModel1.fireCellValueChaned();
										} catch (BusinessException be) {
											NtbLogger.error(be);
										}
									}
									tbSheetViewer.getTable().repaint();
								}
					        }
				        }								
					 }
				  }
			   }
			}catch (Exception e){
			     NtbLogger.print(e);
	    } 
    	int col = tsmodel.getColNum();
    	rowno = tsmodel.getRowNum();
		UFDate beginTime = null;
		UFDate endTime = null;
		int coldate = 0;
		for (int t4 = 10; t4 < col; t4++) {
			for (int i3 = 2; i3 < 8; i3++) {
				if (tsmodel.getCellElement(i3, t4).toString().equals("开始日期")) {
					coldate = t4;
				}
			}
		}
		for (int i3 = 7; i3 < rowno - 6; i3++) {
			try {
				if (null != csModel.getCellValue(i3, coldate) && !"".equals(csModel.getCellValue(i3, coldate))
						&& null != (csModel.getCellValue(i3, coldate + 1)) && !"".equals(csModel.getCellValue(i3, coldate + 1))) {
					beginTime = new UFDate(sim.parse(csModel.getCellValue(i3, coldate).toString()));// 开始日期
					endTime = new UFDate(csModel.getCellValue(i3, coldate + 1).toString());// 结束日期
				}else {
					int nul = 1;
					for (int i2 = 0; i2 < rowno; i2++) {// 先清空数据
						if (nul < 13) {
							csModel.setCellValue(i3, coldate + i2 - 12, null);
							nul++;
						}
					}
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if (beginTime != null && endTime != null) {

				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(beginTime.toDate());
				long time1 = cal1.getTimeInMillis();
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(endTime.toDate());
				long time2 = cal2.getTimeInMillis();
				if (time1 > time2) {// 判断日期格式
					i3 = i3 + 1;
					ormessage.append("当前表第").append(i3).append("行日期填写错误！\n");
					continue;
				}
				String begif = Integer.toString(yea);// 定义初始日期
				String sql = "select bah.* from bd_accperiodmonth bah join bd_accperiod ba on ba.pk_accperiod=bah.pk_accperiod where ba.periodyear='" + begif
						+ "'	";
				ArrayList<AccperiodmonthVO> amonth = null;
				try {
					amonth = (ArrayList<AccperiodmonthVO>) iquerybs.executeQuery(sql, new BeanListProcessor(AccperiodmonthVO.class));
				} catch (BusinessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
								
				Calendar cal3 = Calendar.getInstance();
				int tt = 0;
				cal3.setTime(amonth.get(0).getBegindate().toDate());
				long time3 = cal3.getTimeInMillis();
				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(amonth.get(11).getEnddate().toDate());
				long time4 = cal4.getTimeInMillis();
				long dayss = 1;
				if (time1 < time3) {
					beginTime = amonth.get(0).getBegindate();
				}
				if (time2 > time4) {
					endTime = amonth.get(11).getEnddate();
				}
				UFDate b = null;
				UFDate e = null;
				int mon = beginTime.getMonth();
				if (beginTime.getYear() != yea) {
					tt = 0;
				} else {
					tt = mon - 1;
				}
				b = beginTime;
				e = endTime;
				if (time1 > time4) {// 判断是否超出会计期间
					continue;
				}
				for (int i = tt; i < amonth.size(); i++) {
					int num1 = amonth.get(i).getBegindate().compareTo(b);
					if (num1 == 1 || num1 == 0) {
						b = amonth.get(i).getBegindate();
					} else if (num1 == -1) {
						b = beginTime;
					}
					int num2 = amonth.get(i).getEnddate().compareTo(endTime);
					if (b.getDay() == 26 && e.getDay() == 26 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) < 26
							&& b.getYear() == yea - 1) {
						e = endTime;
					} else if (b.getDay() == 26 && e.getDay() == 26
							&& ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) < 26) {
						e = endTime;
						i++;
					} else if (b.getDay() > 26 && tt != 0 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) > 26) {
						e = amonth.get(tt + 1).getEnddate();
						i++;
					} else if (b.getDay() > 26 && tt == 0 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) > 26) {
						e = amonth.get(tt).getEnddate();
					} else if (b.getDay() > 26 && tt == 0 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) < 26) {
						e = endTime;
						i++;
					} else if (b.getDay() > 26 && tt != 0 && ((endTime.toDate().getTime() - beginTime.toDate().getTime()) / (24 * 60 * 60 * 1000)) < 26) {
						e = endTime;
						i++;
					} else if (num2 == -1 || num2 == 0) {
						e = amonth.get(i).getEnddate();
					} else if (num2 == 1) {
						e = endTime;
					}
					double qw = e.toDate().getTime();
					double qe = b.toDate().getTime();
					if ((qw - qe) / (24 * 60 * 60 * 1000) < 0) {
						continue;
					}
					dayss = (e.toDate().getTime() - b.toDate().getTime()) / (24 * 60 * 60 * 1000);
					if (dayss < 0) {
						break;
					}
					for (int i2 = 0; i2 < rowno-6; i2++) {// 赋值
						//lily 新增校验 2019-12-23
						int d=coldate+i-12;
						if(d<coldate&&d>coldate-13){
							csModel.setCellValue(i3, coldate + i - 12, Long.toString(dayss + 1));
						}
						
					}

				}

				beginTime = null;
				endTime = null;

			}

		}
		JOptionPane.showMessageDialog(null, "生成天数成功", "生成天数成功", JOptionPane.WARNING_MESSAGE);
		if (new String(ormessage) != null && !"".equals(new String(ormessage))) {
			MessageDialog.showErrorDlg(getCurrentView(), "提示", "" + ormessage + "");
		}

	}

	public boolean isActionEnabled() {
		TbPlanContext tbPlanContext = getContext();
		if (tbPlanContext != null) {
			String planstatus = tbPlanContext.getTaskStatus();
			if (planstatus != null) {
				int status = tbPlanContext.getComplieStatus();
				int valueCale = tbPlanContext.getAttributeScale();
				// 分析查询节点 卡片态时一直亮着
				if (tbPlanContext.getNodeType().equals("fetchvalue") && tbPlanContext.isBrowse()) {
					return true;
				}
				if (valueCale == -1 && TbCompliePlanConst.COM_MODE_TASKEDIT == status
				/*
				 * && (ITaskStatus.STARTED.equals(planstatus) ||
				 * ITaskStatus.ADJUSTING.equals(planstatus) ||
				 * ITaskStatus.COMPILING.equals(planstatus))
				 */) {
					return true;
				}
			}
		}
		return false;
	}
}
