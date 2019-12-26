package nc.ui.bd.ref.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import nc.bs.bank_cvp.compile.registry.BussinessMethods;
import nc.bs.framework.common.NCLocator;
import nc.bs.sec.esapi.NCESAPI;
import nc.itf.bd.pub.IBDResourceIDConst;
import nc.itf.org.IOrgConst;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zior.tbb.ICalcuateperce;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ms.tb.task.TbTaskCtl;
import nc.ms.tb.task.data.TaskDataModel;
import nc.uap.lfw.core.AppInteractionUtil;
import nc.ui.bd.ref.AbstractRefTreeModel;
import nc.ui.bd.ref.IRefDocEdit;
import nc.ui.bd.ref.IRefMaintenanceHandler;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.bd.inoutbusiclass.InoutBusiClassVO;
import nc.vo.bd.inoutbusiclass.InoutUseVO;
import nc.vo.pub.BusinessException;
import nc.vo.tb.task.MdTask;
import uap.web.bd.pub.AppUtil;

public class InoutBusiClassDefaultRefModel extends AbstractRefTreeModel {
	IUAPQueryBS iquerybs = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
	ICalcuateperce ica = (ICalcuateperce) NCLocator.getInstance().lookup(ICalcuateperce.class);
	public InoutBusiClassDefaultRefModel() {
    setRefNodeName("收支项目");
//    setAddEnvWherePart(false);
//    setAddEnableStateWherePart(false);
  }

  public void setRefNodeName(String refNodeName) {
    m_strRefNodeName = refNodeName;
    setFieldCode(new String[] {"code", "name", "mnecode"});
    setFieldName(new String[] {
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("10140ioib", "010140ioib0008"),
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("10140ioib", "010140ioib0009"),
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("10140ioib", "010140ioib0010")});
    setHiddenFieldCode(new String[] {"pk_inoutbusiclass", "pk_parent"});
    setPkFieldCode("pk_inoutbusiclass");
    setRefCodeField("code");
    setRefNameField("name");
    setTableName("bd_inoutbusiclass");
    setFatherField("pk_parent");
    setChildField("pk_inoutbusiclass");
    setMnecode(new String[] {"mnecode"});
    setRefTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("10140ioib", "010140ioib0003"));
    setResourceID(IBDResourceIDConst.INOUTBUSICLASS);
    setFilterRefNodeName(new String[] {"业务单元"});
    setAddEnableStateWherePart(false);
//    setAddEnvWherePart(false);
    resetFieldName();
    setRefMaintenanceHandler(new IRefMaintenanceHandler() {

      @Override
      public String[] getFucCodes() {
        return new String[] {"10140IOIB", "10140IOIG", "10140IOIO"};
      }

      @Override
      public IRefDocEdit getRefDocEdit() {
        return null;
      }
    });
  }

  @Override
  public void filterValueChanged(ValueChangedEvent changedValue) {
    super.filterValueChanged(changedValue);
    String[] pk_orgs = (String[]) changedValue.getNewValue();
    if (pk_orgs != null && pk_orgs.length > 0) {
      setPk_org(pk_orgs[0]);
    } else {
      setPk_org(this.getPk_group());
    }
  }

  @Override
  protected String getEnvWherePart() {
	String nodecode=AppUtil.getAppAttr("nodecode")==null?null:AppUtil.getAppAttr("nodecode").toString();
	String fydept=AppUtil.getAppAttr("fydept")==null?null:AppUtil.getAppAttr("fydept").toString();
	if(fydept==null){
		fydept=getPara2();
	}
	if(nodecode==null){
		nodecode=getPara1();
	}
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String ts =sdf1.format(new Date());
	String nodesql="select mnecode,name,shortname,exsysdes from bd_defdoc left join xx_bdcontra_b on xx_bdcontra_b.pk_bdclass  =bd_defdoc.pk_defdoc where name='"+nodecode+"'";
	ArrayList<Map<String, String>> node=null;
	try {
		node = (ArrayList<Map<String, String>>)iquerybs.executeQuery(nodesql,new MapListProcessor());
	} catch (BusinessException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
	String mnecode="";
	String name="";
	String shortname="";
	String   exsysdes="";
	//  [{mnecode='预算数', name=E1100310, shortname=nc_y='4', exsysval=维修改造预算}]		  
    String year= sdf.format(new Date());
	String condition = null;
    String identify = (String) AppUtil.getAppAttr("01");
    if (IOrgConst.GLOBEORG.equals(this.getPk_org())) {
      condition = InoutBusiClassVO.PK_ORG + " ='" + IOrgConst.GLOBEORG + "'";
    } else if (this.getPk_group().equalsIgnoreCase(this.getPk_org())) {
      condition = InoutBusiClassVO.PK_ORG + " ='" + IOrgConst.GLOBEORG + "' or " + InoutBusiClassVO.PK_ORG
              + " ='" + NCESAPI.clientSqlEncode(this.getPk_group()) + "'";
    } else if("监事会".equals(identify)){
      String sqlcond = " ( exists (select 1 from " + InoutUseVO.getDefaultTableName() + " a where a."
              + InoutUseVO.PK_INOUTBUSICLASS + " = " + new InoutBusiClassVO().getTableName() + "."
              + InoutBusiClassVO.PK_INOUTBUSICLASS + " and a." + InoutUseVO.PK_ORG + "='0001N510000000000EGY') or " + InoutBusiClassVO.PK_ORG
              + "='0001N510000000000EGY' AND code LIKE '6405%')";
      condition = sqlcond;
   } else if("总办".equals(identify)){
      String sqlcond = " ( exists (select 1 from " + InoutUseVO.getDefaultTableName() + " a where a."
                    + InoutUseVO.PK_INOUTBUSICLASS + " = " + new InoutBusiClassVO().getTableName() + "."
                    + InoutBusiClassVO.PK_INOUTBUSICLASS + " and a." + InoutUseVO.PK_ORG + "='0001N510000000000EGY') or " + InoutBusiClassVO.PK_ORG
                    + "='0001N510000000000EGY' AND code LIKE '660201%' OR code LIKE '660202%')";
            condition = sqlcond;
   }else if(node!=null&&node.size()>0){//工程  '维修改造预算'
	    mnecode= node.get(0).get("mnecode");
	    name=node.get(0).get("name");
	    shortname=node.get(0).get("shortname");
	    exsysdes=node.get(0).get("exsysdes");	
	    if(name!=null&&nodecode!=null&&name.equals(nodecode)){
		  try {
			  if(fydept==null){
//				  throw new BusinessException("费用承担部门不可为空！！");	
				  AppInteractionUtil.showMessageDialogWithCallBack("费用承担部门不可为空！！");
			  }
		      String tasksql=" SELECT	pk_obj	FROM tb_md_task	WHERE pk_paradims LIKE '%"+year+"%' AND pk_workbook IN (SELECT	pk_workbook	FROM tb_md_sheet"
					+"	WHERE objname IN ("+exsysdes+")) AND (pk_dataent = '"+fydept+"') AND  pk_mvtype IN (SELECT pk_obj FROM tb_dataattr WHERE objname = '"+mnecode+"') ";
			  String pk_task = (String) iquerybs.executeQuery(tasksql, new ColumnProcessor());
			  if(pk_task==null){
				  condition = " pk_inoutbusiclass = '1001N5100000000HB08U'";
			  }else{
				  String pk_sheet="";
				  String tablekeysql="select tablekey from tb_md_workbook  where pk_obj=(select distinct (pk_workbook) from tb_md_sheet where objname in ("+ exsysdes+")) and nvl(dr,0)=0 ";
				  String tablekey = (String) iquerybs.executeQuery(tablekeysql, new ColumnProcessor());
				  StringBuffer sb=new StringBuffer();
				  String pk_sheets="";
				  if(exsysdes.contains(",")){
					  String[] ysname= exsysdes.split(",");
					  String []s2=new String[ysname.length];
					  for(int i=0;i<ysname.length;i++){
						  sb.append("'");
						  String sheetsql="select pk_obj from tb_md_sheet where objname="+ysname[i]+"";	
						  pk_sheet = (String) iquerybs.executeQuery(sheetsql, new ColumnProcessor());
						  s2[i]=pk_sheet.toString();
						  sb.append(pk_sheet).append("',");
					  }
					  pk_sheets= sb.toString().substring(0, sb.toString().length()-1);
					  String sql="select count(*) from HK_isload where pk_task='"+pk_task+"' and pk_sheet in("+pk_sheets+")";
					  Integer exist =  (Integer) iquerybs.executeQuery(sql, new ColumnProcessor());
					  if(exist==0){
						  MdTask task = TbTaskCtl.getMdTaskByPk(pk_task.toString(), true);
						  TaskDataModel dModel=new TaskDataModel(task,s2,true,"");
						  dModel.loadData();
						  for(int i=0;i<ysname.length;i++){
							  String sheetsql="select pk_obj from tb_md_sheet where objname="+ysname[i]+"";	
							  pk_sheet = (String) iquerybs.executeQuery(sheetsql, new ColumnProcessor());
							  ica.updatesql("INSERT INTO HK_isload(PK_SHEET, PK_TASK,ts) VALUES ('"+pk_sheet+"','"+pk_task+"','"+ts+"') ");
						  }
					  }
				  }else{
					  String sheetsql="select pk_obj from tb_md_sheet where objname="+exsysdes+"";	
					  pk_sheet = (String) iquerybs.executeQuery(sheetsql, new ColumnProcessor());
					  String []s2=new String[1];
				 	  s2[0]=pk_sheet.toString();
				 	  pk_sheets= sb.append("'").append(pk_sheet).append("'").toString();
				 	  String sql="select count(*) from HK_isload where pk_task='"+pk_task+"' and pk_sheet in("+pk_sheets+")";
					  Integer exist =  (Integer) iquerybs.executeQuery(sql, new ColumnProcessor());
					  if(exist==0){
						  MdTask task = TbTaskCtl.getMdTaskByPk(pk_task.toString(), true);
						  TaskDataModel dModel=new TaskDataModel(task,s2,true,"");
						  dModel.loadData();
						  ica.updatesql("INSERT INTO HK_isload(PK_SHEET, PK_TASK,ts) VALUES ('"+pk_sheet+"','"+pk_task+"','"+ts+"') ");
					  }
				  }
			 	  String tablename="tb_cell_"+ tablekey.toString()+"";
				  String sqlcond =" pk_inoutbusiclass IN ( select distinct(pk_inoutbusiclass) from "+tablename+" left join bd_inoutbusiclass  on  bd_inoutbusiclass.name =value"
								   +" where pk_sheet in( "+ pk_sheets+") and pk_task='"+pk_task+"' and value is not null  and ("+shortname+" ))  or ( pk_inoutbusiclass = '1001N5100000000HB08U')";
				  condition = sqlcond;
			 }
		  } catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		  }
	   }else{
		   String sqlcond = " ( exists (select 1 from " + InoutUseVO.getDefaultTableName() + " a where a."
                   + InoutUseVO.PK_INOUTBUSICLASS + " = " + new InoutBusiClassVO().getTableName() + "."
                   + InoutBusiClassVO.PK_INOUTBUSICLASS + " and a." + InoutUseVO.PK_ORG + "='0001N510000000001SXL') or " + InoutBusiClassVO.PK_ORG
                   + "='0001N510000000001SXL')";
           condition = sqlcond;
	   }
	}else{
      String sqlcond = " ( exists (select 1 from " + InoutUseVO.getDefaultTableName() + " a where a."
                    + InoutUseVO.PK_INOUTBUSICLASS + " = " + new InoutBusiClassVO().getTableName() + "."
                    + InoutBusiClassVO.PK_INOUTBUSICLASS + " and a." + InoutUseVO.PK_ORG + "='0001N510000000001SXL') or " + InoutBusiClassVO.PK_ORG
                    + "='0001N510000000001SXL')";
            condition = sqlcond;
    }
    return condition;
  }
}
