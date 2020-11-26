package nc.ui.tb.zior.pluginaction.planning;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ms.mdm.dim.DimServiceGetter;
import nc.ms.tb.event.TaskChangeEvent;
import nc.ms.tb.ext.plan.TbCompliePlanConst;
import nc.ms.tb.task.TaskActionCtl;
import nc.ms.tb.task.TbTaskCtl;
import nc.ms.tb.task.data.TaskDataCtl;
import nc.ms.tb.task.data.TaskSheetDataModel;
import nc.ms.tb.zior.vo.ITbPlanActionCode;
import nc.ms.tb.zior.vo.TbActionName;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.tb.model.TBDataCellRefModel;
import nc.ui.tb.plan.action.ApprovePlanTools;
import nc.ui.tb.zior.CompileSaveLogs;
import nc.ui.tb.zior.TbPlanContext;
import nc.ui.tb.zior.TbPlanFrameUtil;
import nc.ui.tb.zior.TbVarAreaUtil;
import nc.ui.tb.zior.pluginaction.AbstractTbRepPluginAction;
import nc.ui.tb.zior.pluginaction.TbPluginActionDescriptor;
import nc.ui.tb.zior.pluginaction.edit.model.VarCellValueModel;
import nc.vo.mdm.dim.DimDef;
import nc.vo.mdm.dim.DimLevel;
import nc.vo.mdm.dim.DimMember;
import nc.vo.mdm.dim.LevelValue;
import nc.vo.mdm.pub.NtbLogger;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.tb.control.exception.AdjustControlException;
import nc.vo.tb.form.excel.ExVarDef;
import nc.vo.tb.form.iufo.CellExtInfo;
import nc.vo.tb.ntbenum.CtrlTypeEnum;
import nc.vo.tb.obj.LevelValueOfDimLevelVO;
import nc.vo.tb.obj.UserLoginVO;
import nc.vo.tb.task.ITaskAction;
import nc.vo.tb.task.ITaskStatus;
import nc.vo.tb.task.MdTask;
import nc.vo.tb.task.MdTaskDef;
import nc.vo.tb.util.IConst;

import com.ufida.zior.plugin.PluginKeys.XPOINT;
import com.ufida.zior.plugin.event.PluginActionEvent;
import com.ufsoft.table.CellsModel;
import com.ufsoft.table.Cell;
public class PluginAction_CompileSave extends AbstractTbRepPluginAction {
	private ImageIcon saveIcon = null;
	private ActionEvent actionevent = null;
	public PluginAction_CompileSave(String name, String code) {
		super(name, code);
	}

	@Override
	public TbPluginActionDescriptor getTbPluginActionDescriptor() {
		TbPluginActionDescriptor  desc =new TbPluginActionDescriptor();
		desc.setName(TbActionName.getName_CompileSave());
		desc.setGroupPaths(TbActionName.getName_file());
		desc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		desc.setExtensionPoints(new XPOINT[]{XPOINT.TOOLBAR,XPOINT.MENU});
		desc.setIcon(ITbPlanActionCode.SAVE_ICON);

		return desc;
	}

	private ImageIcon getIcon() {
		if (saveIcon == null) {
			saveIcon = new ImageIcon(getClass().getResource(ITbPlanActionCode.SAVE_ICON));
		}
		return saveIcon;
	}
	  IUAPQueryBS iquerybs = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
	@Override
	public void actionPerformed(ActionEvent actionevent) {
		try {
//			if(getContext() != null){
//				MdTask task = getMdTask();
//				TbPlanContext tbPlanContext = getContext();
//				if(GlobalParameter.getInstance().getPara("checkDirtyWhenCompile", Boolean.class) != null){
//					Boolean bl = GlobalParameter.getInstance().getPara("checkDirtyWhenCompile", Boolean.class);
//					if(bl != null && bl.booleanValue()){
//						if(tbPlanContext.getCurrentTs() != null){
//							String lastVersion = TbTaskServiceGetter.getTaskDataService().isLastVersion(task.getPk_obj(), tbPlanContext.getCurrentTs());
//							if(lastVersion != null){
//								MessageDialog.showErrorDlg(this.getMainboard(), NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000480")/*提示*/, "数据发生变化，请更新数据");
//								return ;
//							}
//						}
//					}
//				}
//			}
			CompileSaveLogs compileSaveLogs=new CompileSaveLogs();
			UFDateTime currentTs = getContext().getCurrentTs();
			getContext().setAction_Code(ITbPlanActionCode.code_CompileSave);
			boolean taskCacheTimeOut = getMdTask() == null? false:TaskDataCtl.isTaskCacheTimeOut(getMdTask().getPk_obj(),currentTs);
			if(taskCacheTimeOut){
				TbPlanFrameUtil.getTbPlanFrame(getMainboard()).stopAndreleaseProgress();
				MessageDialog.showErrorDlg(this.getMainboard(), NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000480")/*提示*/, NCLangRes.getInstance().getStrByID("tbb_plan_0","01050plan001-0408")/*@res "数据发生变化，请刷新数据"*/);
				return ;
			}
			//任务被停用后不能被保存
			MdTask task = getMdTask();
			MdTaskDef mdTaskDefBy= TbTaskCtl.getMdTaskDefByPk(task.getPk_taskdef(), false);
			String isactive = mdTaskDefBy == null ? null:mdTaskDefBy.getIsactive();
			if(isactive != null && IConst.FALSE.equals(isactive)){
				throw new BusinessException(NCLangRes.getInstance().getStrByID("tbb_plan_0","01050plan001-0119")/*@res "该任务模板已被停用，无法保存数据"*/);
			}
			boolean isIndexApp = getContext().isIndexApprove();  //是否指标审批
			if(!isIndexApp){
				UserLoginVO userLoginVO = ApprovePlanTools.getCompleteUser(getMdTask());
				Boolean flag = TaskActionCtl.checkPrevStatus(userLoginVO,
						new MdTask[] { task }, ITaskAction.COMPILE);
				if (flag) {
					showMessage(NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000250")/*任务状态改变中.....*/);
					processAction(userLoginVO, new MdTask[]{task}, ITaskAction.COMPILE, null);
					showMessage(NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000251")/*成功改变任务状态*/);
				} else {
					MessageDialog.showErrorDlg(this.getMainboard(), NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000480")/*提示*/, NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000252", null, new String[]{task.getObjname()})/*任务{0}状态不符*/);
					return;
				}
			}
		      TaskSheetDataModel tsmodel = getCurrentViewer().getTsDataModel();// 固定模型
		      List<List<Cell>> sheets=getCurrentViewer().getCellsModel().getCells();
		      CellsModel csModel = getCellsModel();
		      List<List<Cell>> cells = csModel.getCells();
		      String name= tsmodel.getName();
		      int rowno = csModel.getRowNum();// 即时行数
		      int colno = csModel.getColNum();// 即时列数
		      //保存前带出物料对应的项目
		      afterMarbas( rowno,  name,  colno,  tsmodel,  csModel,  cells);
		      //保存前校验项目只能为末级收支项目
			  Boolean istrue= checkXm( rowno, name, colno, tsmodel, csModel,cells);
		      //保存之前验证所有的行项目相同只有唯一一个税率
			  Boolean istrue1= checkSl(rowno,  name,colno, tsmodel,sheets);
			  if(!istrue||!istrue1){
					return;
			  }
			// 保存任务（在该方法内增加了更新前台缓存时间的方法）
			compileSaveLogs= getCurrentViewer().getViewManager().saveTasks();
			if(compileSaveLogs == null){
				return;
			}
			if(compileSaveLogs != null){
				if(compileSaveLogs.getAdjustControlRuleMessage() != null)
					throw new AdjustControlException(compileSaveLogs.getAdjustControlRuleMessage(), CtrlTypeEnum.WarningControl.toCodeString());
				if(compileSaveLogs.getRuleMessage() != null){
					NtbLogger.print(compileSaveLogs.getRuleMessage());
					getMainboard().getStatusBar().setHintMessage(NCLangRes.getInstance().getStrByID("tbb_plan_0","01050plan002-0359")/*@res ""部分单元格公式未执行成功，具体请查看日志！"*/,false);
				}
			}
			if(getTbReportDirView() != null){
				this.getTbReportDirView().setEditEnable(true);
			}
			/////////////////////////
			if(isIndexApp)   //由于liuyshb添加下面逻辑，需要改变任务状态的，导致指标审批总是弹出错误提示，暂时设定指标审批时可以直接返回
				return;
			MdTask[] tasks=TbTaskCtl.getMdTasksByWhere(task.getPKFieldName() +"='"+task.getPk_obj()+"'", true);
			if(tasks.length != 1){
				throw new BusinessException(NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000253")/*任务状态出错*/);
			}
			task = tasks[0];
//				getContext().setComplieStatus(TbCompliePlanConst.COM_MODE_TASKEDIT);
			getContext().setTaskStatus(tasks[0].getPlanstatus());
			getContext().setTasks(tasks);
			this.getTbReportDirView().getViewManager().stopAllViewEditing();
			TaskChangeEvent taskChangeEvent = new TaskChangeEvent(getCurrentView(), 1);
			PluginActionEvent pluginActionEvent = new PluginActionEvent(getCurrentView(),1);
			this.getMainboard().getEventManager().dispatch(pluginActionEvent);
			this.getMainboard().getEventManager().dispatch(taskChangeEvent);
			this.getTbReportDirView().refreshTask(task);
		} catch(final AdjustControlException e) { 
		
			if(e.getControlType().equals(CtrlTypeEnum.WarningControl.toCodeString())) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						MessageDialog.showWarningDlg(getMainboard(), NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000480")/*提示*/, e.getMessage());
					}
				});
			} else {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						MessageDialog.showErrorDlg(getMainboard(),  NCLangRes.getInstance().getStrByID("tbb_bean", "01420ben_000018")/*错误*/, e.getMessage());

					}
				});
			}
//			ITaskDataInteractService service = TbTaskInteractServiceGetter.getTaskDataInteractService();
//			service.exportTaskData(task.getPk_obj());
		} catch(final Exception e) {
			getCurrentViewer().getViewManager().refresh(getCurrentViewer());
			NtbLogger.error(e);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					
					MessageDialog.showErrorDlg(getMainboard(), NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000480")/*提示*/, e.getMessage());
				}
			});
		}catch(final Throwable e){    //捕获保存时Error错误
			NtbLogger.error(e);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					
					MessageDialog.showErrorDlg(getMainboard(), NCLangRes.getInstance().getStrByID("tbb_plan", "01812pln_000480")/*提示*/, e.getMessage());
				}
			});
		}
	}
	public void afterMarbas(int rowno, String name, int colno, TaskSheetDataModel tsmodel, CellsModel csModel, List<List<Cell>> cells) {
		  try{
		    UIRefPane refPane = new UIRefPane("维度选择");/* -=notranslate=- */
			refPane.setMultiSelectedEnabled(true);
			TBDataCellRefModel tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
			String pk_user = WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
			String pk_group = WorkbenchEnvironment.getInstance().getGroupVO().getPrimaryKey();
	      //保存前如果物料不为空，则带出项目
			if(name!=null&&name.length()>0){
				if("产品价格底表".equals(name)||"产品销售预算".equals(name)||"房费收入预算".equals(name)||"租金收入预算".equals(name)||"物品需求预算".equals(name)){
					int colwl = 0;
					int colxm = 0;
					//当新增行系统把值set完之后，当编辑物料时，带出对应得成本预算
					for (int k = 0; k< rowno; k++) {
					    for (int h = 0; h < colno; h++){
					        Object name2 = csModel.getCell(k, h).getValue();
					        if (name2 != null&&("产品名称".equals(name2)||"商品名称".equals(name2)||"房型名称".equals(name2)||"物料名称".equals(name2)||"摊位名称/房间名称".equals(name2))) {
					       	 colwl=h;
					        }
					        if(name != null&&"预算项目名称".equals(name2)){
					       	 colxm=h;
					        }
					    }
					    if(colwl!=0&&colxm!=0){
					   	 break;
					    }
					}
			        for (int i = 7; i < rowno; i++) {
		              Boolean flag=false;
			          List<Cell> row = (List)cells.get(i);
			          if (row != null) {
			            for (int j = 0; j < colno; j++) {
						  if(flag){
								break;
						   }
			              Cell c = (Cell)row.get(j);
			              if (c == null) {
			                c = csModel.getCell(i, j);
			              }
			              CellExtInfo cInfo = c == null ? null : (CellExtInfo)c.getExtFmt("tbinfo");
			              if ((cInfo != null) && (cInfo.getVarId() != null) && (cInfo.getVarId().length() > 0) && (cInfo.getCubeCode() != null) && (cInfo.getDimVector() != null)) {
			            	  LevelValueOfDimLevelVO measure = new LevelValueOfDimLevelVO(colxm, "MEASURE", null, null, getMdTask());
	  						  ExVarDef exVarDef = TbVarAreaUtil.getVarDefByCellExtInfo(cInfo);
	  						  Map<DimLevel, LevelValue> dvMap = TbVarAreaUtil.getDVMap(c, cInfo, exVarDef, getContext().getCurrReportViewer().getCellsPane());
			            	  DimDef dd=null;	
			            	  dd = DimServiceGetter.getDimManager().getDimDefByBusiCode("MARBAS");
			            	  if("物品需求预算".equals(name)){
			            		  dd = DimServiceGetter.getDimManager().getDimDefByBusiCode("MPPMARVERSION");
			            	  }
		                  	  DimMember wlDim=cInfo.getDimVector().getDimMember(dd);
		                  	  if(wlDim!=null){
		                  		String  dimCode = dd.getObjCode();
			                  	String wlcode=wlDim.getUniqKey();
			                  	if(wlcode != null){
			                  		flag=true;
			    					String pk_mater = wlcode.substring(dimCode.length(),wlcode.length());	
			    					Object name_cbxm="";
			    					Object pk_cbxm = iquerybs.executeQuery("select def5 from bd_material  where pk_material='" + pk_mater + "' and nvl(dr,0)=0", new ColumnProcessor());
			    					if(pk_cbxm!=null&&!"".equals(pk_cbxm)){
			    					    name_cbxm = iquerybs.executeQuery("select name from bd_inoutbusiclass  where pk_inoutbusiclass='" + pk_cbxm + "' and nvl(dr,0)=0", new ColumnProcessor());
			    					}
		    						TbVarAreaUtil.initTBDataCellRefModel(tBDataCellRefModel, measure, pk_user, pk_group, cInfo.getCubeCode(), exVarDef, null, dvMap);
		    						tBDataCellRefModel.getData();
		    						tBDataCellRefModel = (TBDataCellRefModel) refPane.getRefModel();
		    						Object xmname = csModel.getCell(i, colxm).getValue();
		    						if(xmname==null||"".equals(xmname)){
			    						if("产品价格底表".equals(name)){
		    								csModel.setCellValue(i, colxm, name_cbxm);
			    						}else if("产品销售预算".equals(name)){
			    							csModel.setCellValue(i, colxm, name_cbxm);
			    						}else if("房费收入预算".equals(name)){
			    							csModel.setCellValue(i, colxm, name_cbxm);
			    						}else if("租金收入预算".equals(name)){
			    							csModel.setCellValue(i, colxm, name_cbxm);
			    						}else if("物品需求预算".equals(name)){
			    							csModel.setCellValue(i, colxm, name_cbxm);
			    						}
			    						DimMember dimMember = tBDataCellRefModel.getDimMember(pk_cbxm==null?null:pk_cbxm.toString());
			    						if (null != dimMember) {
			    							DimMember[] dms1 = new DimMember[1];
			    							dms1[0] = dimMember;
			    							VarCellValueModel varCellModel = new VarCellValueModel(0, getCellsModel(), i, colxm, dms1, 1);
			    							try {
			    								// 设置单元格的多维信息
			    								varCellModel.fireCellValueChaned();
			    							} catch (BusinessException be) {
			    								NtbLogger.error(be);
			    							}
			    						}
		    						}

		    					}
		    				}
	                  	}
			          }
			        }
		         }
			   }
			 }
	    } catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	 
	  }
	  public Boolean checkXm(int rowno, String name, int colno, TaskSheetDataModel tsmodel, CellsModel csModel, List<List<Cell>> cells) {
		 try {
			 if(name!=null&&name.length()>0){
				if(!"预算收支明细表".equals(name)){
			      for (int i = 0; i < rowno; i++) {
			          List<Cell> row = null;
			          row = (List)cells.get(i);
			          if (row != null) {
			            for (int j = 0; j < colno; j++) {
			              Cell c = (Cell)row.get(j);
			              if (c == null) {
			                c = csModel.getCell(i, j);
			              }
			              CellExtInfo cInfo = c == null ? null : (CellExtInfo)c.getExtFmt("tbinfo");
			              if ((cInfo != null) && (cInfo.getVarId() != null) && (cInfo.getVarId().length() > 0) && (cInfo.getCubeCode() != null) && (cInfo.getDimVector() != null)) {
			                  if (cInfo.getDimVector().getMeasure()!= null) {
			                  	if(cInfo.getDimVector().getMeasure().getUniqKey() != null){
			                  		String pk_inoutbusiclass=cInfo.getDimVector().getMeasure().getUniqKey();
			                  		String sql="select count(*) from bd_inoutbusiclass where pk_parent in( '"+pk_inoutbusiclass+"')";
			                      	Object count = iquerybs.executeQuery(sql, new ColumnProcessor());
			                      	if(count!=null&&!count.toString().equals("0")){
			                      		MessageDialog.showErrorDlg(null, "提示","第"+(i+1)+"行预算项目名称["+cInfo.getDimVector().getMeasure()+"]不是末级预算项目！！");
			          					return false;
			                      	}
			                      	String sql1="select name from bd_inoutbusiclass where pk_inoutbusiclass in( '"+pk_inoutbusiclass+"')";
			                      	Object names = iquerybs.executeQuery(sql1, new ColumnProcessor());
			                      	if(names!=null&&names.toString().equals("预算项目未定义")){
			                      		MessageDialog.showErrorDlg(null, "提示","第"+(i+1)+"行预算项目名称为[预算项目未定义],不可保存！！");
			                      		return false;
			                      	}
			                  	}
			                  }
			              }
			            }
			          }
			        }
				}
			}
	      } catch (BusinessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
		  }
		return true;
	  }
	  public Boolean checkSl(int rowno, String name, int colno, TaskSheetDataModel tsmodel, List<List<Cell>>  sheets) {
		  int colsl=0;
		  Boolean flag=false;
		  for (int i3 = 4; i3 < rowno; i3++) {
			  for (int t4 = 3; t4 < colno; t4++) {
				if (null != tsmodel.getCellElement(i3, t4)){
					if (null != tsmodel.getCellElement(i3, t4).getValue()) {
						if (("税率").equals(tsmodel.getCellElement(i3, t4).getValue().toString().trim())) {
							flag=true;
							colsl=t4;
							break;
						}        
					}
				  }
		        }
				if(flag){
					break;
				}
		  }
//	      if(name!=null&&"其他收入预算".equals(name)){
//	          for (int i = 6; i < sheets.size(); i++) {
//	        	 List<Cell> sheet =sheets.get(i);
//	         	 Map <String,String> map=new HashMap<String,String>();
//	         	 int size=0;
//	         	 if(sheet!=null){
//		         	 if(sheet.get(4).getValue() != null &&!sheet.get(4).getValue().equals("")){
//		         		 String valuexm=sheet.get(4).getValue().toString().trim();
//			     		 String valuesl= sheet.get(colsl).getValue()==null?null:sheet.get(colsl).getValue().toString().trim();
//			     		 if(valuexm!=null){
//			     			 if(valuexm.equals("合计")){
//			     				 break;
//			     			 }
//			     		     if(valuesl!=null){
//			     				 map.put(valuexm, valuesl);
//			     			 }
//			     			 for(int j=6;j<sheets.size();j++){
//			     				 if(i!=j){
//			     					List<Cell> sheet1 =sheets.get(j);
//			     					if(sheet1!=null){
//			     					 size=map.size();
//			     					 String nextvaluexm=sheet1.get(4).getValue()==null?null:sheet1.get(4).getValue().toString().trim();
//			     					 String nextvaluesl= sheet1.get(colsl).getValue()==null?null:sheet1.get(colsl).getValue().toString().trim();
//			     					 if(nextvaluexm!=null&&valuexm.equals(nextvaluexm)){
//			     						if(nextvaluesl!=null){
//			     							if(size!=0){
//			     								MessageDialog.showErrorDlg(null, "提示","预算项目名称["+valuexm+"]的税率只能填一行！！");
//			     								return false;
//			     							}else{
//			     								map.put(nextvaluexm, valuesl);
//			     							}
//			     						  }
//			     					   }
//			     				    }
//			     				 }
//			     			 }
//				     		 size=map.size();
//			     			 if(size==0){
//			     				 MessageDialog.showErrorDlg(null, "提示","预算项目名称["+valuexm+"]的税率必须填一行！！");
//			     				 return false;
//			     			 }
//			     		 }
//		         	 }
//	         	 }
//	     	  }
//	      }else
	    	  if(name!=null&&"产品价格底表".equals(name)){
	    	  int colzkl=0;
	    	  Boolean flag1=false;
	    	  for (int i3 = 4; i3 < rowno; i3++) {
	    		  for (int t4 = 3; t4 < colno; t4++) {
	    			if (null != tsmodel.getCellElement(i3, t4)){
	    				if (null != tsmodel.getCellElement(i3, t4).getValue()) {
	    					if (("折扣率%").equals(tsmodel.getCellElement(i3, t4).getValue().toString().trim())) {
	    						flag1=true;
	    						colzkl=t4;
	    						break;
	    					}        
	    				}
	    			  }
	    	        }
	    			if(flag1){
	    				break;
	    			}
	    	  }
	          for (int i = 8; i < sheets.size(); i++) {
	        	 List<Cell> sheet =sheets.get(i);
	         	 if(sheet!=null){
		         	 if(sheet.get(7).getValue() != null &&!sheet.get(7).getValue().equals("")&&sheet.get(5).getValue()!=null&&!sheet.get(5).getValue().equals("")){
		         		 String sp= sheet.get(7).getValue().toString();//商品名称
			     		 String valuesl= sheet.get(colsl).getValue()==null?null:sheet.get(colsl).getValue().toString().trim();
			     		 String valuezkl= sheet.get(colzkl).getValue()==null?null:sheet.get(colzkl).getValue().toString().trim();
		     			 if(valuesl==null||"".equals(valuesl)||valuesl.length()==0){
		     				MessageDialog.showErrorDlg(null, "提示","商品名称["+sp+"]的税率不能为空！！");
		     				return false;
		     			 } 
		     			 if(valuezkl==null||"".equals(valuezkl)||valuezkl.length()==0){
		     				MessageDialog.showErrorDlg(null, "提示","商品名称["+sp+"]的折扣率不能为空！！");
		     				return false;
		     			 } 
		         	 }
	         	 }
	     	  }
	      }else if(name!=null&&"租金收入底表".equals(name)){
	          for (int i = 6; i < sheets.size(); i++) {
	        	 List<Cell> sheet =sheets.get(i);
	        	 int size=0;
	         	 Map <String,String> map=new HashMap<String,String>();
	         	 if(sheet!=null){
		         	 if(sheet.get(7).getValue() != null &&!sheet.get(7).getValue().equals("")&&sheet.get(9).getValue() != null &&!sheet.get(9).getValue().equals("")){
		         		 String valuexm=sheet.get(7).getValue().toString().trim();//客户
		         		 String valuetw= sheet.get(9).getValue().toString().trim();//摊位
			     		 String valuesl= sheet.get(colsl).getValue()==null?null:sheet.get(colsl).getValue().toString().trim();
			     		 if(valuexm!=null&&valuetw!=null){
			     		     if(valuesl!=null){
			     				 map.put(valuexm, valuetw);
			     			 }
			     			 for(int j=6;j<sheets.size();j++){
			     				 if(i!=j){
			     					List<Cell> sheet1 =sheets.get(j);
			     					if(sheet1!=null){
			     					 size=map.size();
			     					 String nextvaluexm=sheet1.get(7).getValue()==null?null:sheet1.get(7).getValue().toString().trim();
			     					 String nextvaluetw=sheet1.get(9).getValue()==null?null:sheet1.get(9).getValue().toString().trim();
			     					 String nextvaluesl= sheet1.get(colsl).getValue()==null?null:sheet1.get(colsl).getValue().toString().trim();
			     					 if(nextvaluexm!=null&&nextvaluetw!=null&&valuexm.equals(nextvaluexm)&&nextvaluetw.equals(valuetw)){
			     						if(nextvaluesl!=null){
			     							if(size!=0){
			     								MessageDialog.showErrorDlg(null, "提示","客户名称["+valuexm+"]和摊位名称房间名称["+valuetw+"]的税率只能填一行！！");
			     								return false;
			     							}else{
			     								map.put(nextvaluexm, nextvaluetw);
			     							}
			     						  }
			     					   }
			     				    }
			     				 }
			     			 }
				     		 size=map.size();
			     			 if(size==0){
			     				 MessageDialog.showErrorDlg(null, "提示","客户名称["+valuexm+"]和摊位名称房间名称["+valuetw+"]的税率必须填一行！！");
			     				return false;
			     			 }
			     		 }
		         	 }
	         	 }
	     	  }
	      }
    	  /**
    	   * HK 2020年11月26日09:19:21
    	   * 租赁收入底表，针对税率做判断
    	   */
		else if (name != null && "租赁收入底表".equals(name)) {
			// sheets 是所有行的集合
			int beginRow = 7;
			int custCol = 11; // 客户
			int roomCol = 9; // 房号
			int sfxmCol = 10; // 收费
			Map<String, Integer> MAP = new HashMap<>(); // 维度信息
			for (int i = beginRow; i < sheets.size(); i++) {
				List<Cell> row = sheets.get(i);
				if (row != null) {
					Cell custCell = row.get(custCol);
					Cell roomCell = row.get(roomCol);
					Cell sfxmCell = row.get(sfxmCol);
					Cell slCell = row.get(colsl);
					String custValue = PuPubVO
							.getString_TrimZeroLenAsNull(custCell.getValue());
					String roomValue = PuPubVO
							.getString_TrimZeroLenAsNull(roomCell.getValue());
					String sfxmValue = PuPubVO
							.getString_TrimZeroLenAsNull(sfxmCell.getValue());
					String slValue = PuPubVO.getString_TrimZeroLenAsNull(slCell
							.getValue());
					if (custValue != null && roomValue != null
							&& sfxmValue != null) {
						// 客户 + 房间 + 收费项目
						String key = custValue + "@@@@" + roomValue + "@@@@"
								+ sfxmValue;

						if (!MAP.containsKey(key)) {
							MAP.put(key, 0);
						}

						if (slValue != null) {
							if (MAP.get(key) == 1) {
								// 如果 税率有值，并且之前也有值，则报错。
								MessageDialog
								.showErrorDlg(null, "提示", "客户名称【"
										+ custValue + "】、房间号【"
										+ roomValue + "】、收费项目【"
										+ sfxmValue + "】的税率只能填一行！！");
								return false;
							} else {
								MAP.put(key, 1);
							}
						}
					}
				}
			}
			if (!MAP.isEmpty()) {
				for (Entry<String, Integer> item : MAP.entrySet()) {
					Integer value = item.getValue();
					String key = item.getKey();
					String[] keys = key.split("@@@@");
					if (value == 0) {
						MessageDialog
						.showErrorDlg(null, "提示", "客户名称【"
								+ keys[0] + "】、房间号【"
								+ keys[1] + "】、收费项目【"
								+ keys[2] + "】的税率必须填一行！！");
						return false;
					}
				}
			}
		}
		return true;
	  }
	public boolean isShowProgress(){
		return true;
	}
	public void processAction(UserLoginVO userVo, MdTask[] tasks, String action, HashMap<String, Object> params) throws BusinessException {
		TaskActionCtl.processAction(userVo, tasks, action, params);
	}
	@Override
	public boolean isActionEnabled() {
		TbPlanContext tbPlanContext = getContext();
		if(tbPlanContext != null){
			String planstatus = tbPlanContext.getTaskStatus();
			if(planstatus != null){
				int status = tbPlanContext.getComplieStatus();
				List<String> statuss=new ArrayList<String>();
				//指标审批保存 指标审批状态为FALSE 浏览状态 已上报
				boolean isIndexApprove = tbPlanContext.isIndexApprove();
				if (TbCompliePlanConst.COM_MODE_TASKEDIT == status){
					if(isIndexApprove){
						statuss.add(ITaskStatus.PROMOTED);
						statuss.add(ITaskStatus.LOCAL_PROMOTED);
						statuss.add(ITaskStatus.APPROVING);
						statuss.add(ITaskStatus.LOCAL_APPROVING);
					}else{
						statuss.add(ITaskStatus.STARTED);
						statuss.add(ITaskStatus.ADJUSTING);
						statuss.add(ITaskStatus.COMPILING);
						statuss.add(ITaskStatus.LOCAL_APPROVE_NOTPASS);
						statuss.add(ITaskStatus.APPROVE_NOTPASS);
					}
					if(statuss.contains(planstatus)){
						return true;
					}
				}
			}
		}
		return false;
	}

}
