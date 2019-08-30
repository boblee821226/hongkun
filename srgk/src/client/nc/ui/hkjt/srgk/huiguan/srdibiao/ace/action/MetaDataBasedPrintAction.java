package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.action;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.bs.uif2.IActionCode;
import nc.funcnode.ui.action.INCAction;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pub.print.IPrintListener;
import nc.ui.pub.query.tools.ImageIconAccessor;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.HierachicalDataAppModel;
import nc.ui.uif2.CheckDataPermissionUtil;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.uif2.annoations.MethodType;
import nc.uif2.annoations.ModelMethod;
import nc.uif2.annoations.ModelType;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.trade.checkrule.VOChecker;
import nc.vo.uif2.LoginContext;

/**
 * @author kongxd
 * 
 */
public class MetaDataBasedPrintAction extends NCAction {

  public interface IBeforePrintDataProcess {
    Object[] processData(Object[] datas);
  }

  public interface IDataSplit {
    Object[] splitData(Object[] datas);
  }

  public class MetaDataSource implements IMetaDataDataSource {

    /**
         * 
         */
    private static final long serialVersionUID = -4436257555817100372L;

    private Object[] printData;

    public MetaDataSource() {
      this(null);
    }

    public MetaDataSource(Object[] printData) {
      this.printData = printData;

    }

    @Override
    public String[] getAllDataItemExpress() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public String[] getAllDataItemNames() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public String[] getDependentItemExpressByExpress(String itemExpress) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public String[] getItemValuesByExpress(String itemExpress) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public Object[] getMDObjects() {
      // Object[] datas = null;
      // if (MetaDataBasedPrintAction.this.getModel() instanceof
      // BillManageModel) {
      // datas =
      // ((BillManageModel) MetaDataBasedPrintAction.this.getModel())
      // .getSelectedOperaDatas();
      // }
      // else {
      // datas =
      // ((BatchBillTableModel) MetaDataBasedPrintAction.this.getModel())
      // .getSelectedOperaDatas();
      // }
      if (MetaDataBasedPrintAction.this.getBeforePrintDataProcess() != null) {
        this.printData =
            MetaDataBasedPrintAction.this.getBeforePrintDataProcess()
                .processData(this.printData);
      }
      return this.printData;
    }

    @Override
    public String getModuleName() {
      // TODO Auto-generated method stub
      return null;
    }

    public Object[] getPrintData() {
      return this.printData;
    }

    @Override
    public boolean isNumber(String itemExpress) {
      // TODO Auto-generated method stub
      return false;
    }

    public void setPrintData(Object[] printData) {
      this.printData = printData;
    }

  }

  private static final long serialVersionUID = 1L;

  protected AbstractUIAppModel model;

  private IBeforePrintDataProcess beforePrintDataProcess;

  private IMetaDataDataSource dataSource;

  private IDataSplit dataSplit;

  private boolean isPrintAll = false;

  private String nodeKey;

  private Container parent;

  private boolean preview = true;

  private String actioncode;
  
  	public String getActioncode() {
		return actioncode;
	}
	
	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}
	
	private String actionname;
	
	public String getActionname() {
		return actionname;
	}
	
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	
	private nc.ui.pub.print.PrintEntry printEntry;

	private IPrintListener printListener;

  public IPrintListener getPrintListener() {
		return printListener;
	}

	public void setPrintListener(IPrintListener printListener) {
		this.printListener = printListener;
	}

@Override
  public void doAction(ActionEvent e) throws Exception {

    if (this.getPrintEntry().selectTemplate() != 1) {
      return;
    }
    if (this.getDataSource() != null) {
    	Object obj = this.getDataSource().getMDObjects();
		checkDataPermission(obj);// Ȩ��У��
      this.printEntry.setDataSource(this.getDataSource());
    }
    else {
    	Object obj = getDatas();
		checkDataPermission(obj);// Ȩ��У��
      IMetaDataDataSource[] defaultDataSource = this.getDefaultMetaDataSource();
      if (!VOChecker.isEmpty(defaultDataSource)) {
        for (IMetaDataDataSource dataSourceItem : defaultDataSource) {
          this.printEntry.setDataSource(dataSourceItem);
        }
      }
      else {
        return;
      }

    }
    if (this.isPreview()) {
      this.printEntry.preview();
    }
    else {
      this.printEntry.print();
    }

  }

  public IBeforePrintDataProcess getBeforePrintDataProcess() {
    return this.beforePrintDataProcess;
  }

  public IMetaDataDataSource getDataSource() {
    return this.dataSource;
  }

  public IDataSplit getDataSplit() {
    return this.dataSplit;
  }

  @ModelMethod(modelType=ModelType.AbstractUIAppModel,methodType=MethodType.GETTER)
  public AbstractUIAppModel getModel() {
    return this.model;
  }

  public String getNodeKey() {
    return this.nodeKey;
  }

  public Container getParent() {
    return this.parent;
  }

  /**
   * 
   * �Ƿ�Ԥ��
   * 
   * @return �����Ԥ��������true������Ǵ�ӡ������false
   */
  public boolean isPreview() {
    return this.preview;
  }

  public boolean isPrintAll() {
    return this.isPrintAll;
  }

  public void setBeforePrintDataProcess(
      IBeforePrintDataProcess beforePrintDataProcess) {
    this.beforePrintDataProcess = beforePrintDataProcess;
  }

  public void setDataSource(IMetaDataDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void setDataSplit(IDataSplit dataSplit) {
    this.dataSplit = dataSplit;
  }

  @ModelMethod(modelType=ModelType.AbstractUIAppModel,methodType=MethodType.SETTER)
  public void setModel(AbstractUIAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public void setNodeKey(String nodeKey) {
    this.nodeKey = nodeKey;
  }

  public void setParent(Container parent) {
    this.parent = parent;
  }

  public void setPreview(boolean preview) {
	//�����ļ���Ϊ�˰�ťע������ʱ��������ע��actioncode��actionname���ԣ���ע��preview����
    if (preview) {
      ActionInitializer.initializeAction(this, IActionCode.PREVIEW);
      this.putValue(Action.SMALL_ICON,
          ImageIconAccessor.getIcon("toolbar/icon/preview.gif"));
     
    }
    else {
      ActionInitializer.initializeAction(this, IActionCode.PRINT);
      this.putValue(Action.SMALL_ICON,
          ImageIconAccessor.getIcon("toolbar/icon/print.gif"));
    }
    if (!StringUtil.isEmpty(getActioncode())) {
  	  this.putValue(INCAction.CODE, getActioncode());
    }
    
    if (!StringUtil.isEmpty(getActionname())) {
	  this.putValue(INCAction.NAME, getActionname());
      }
    this.preview = preview;
  }

  public void setPrintAll(boolean isPrintAll) {
    this.isPrintAll = isPrintAll;
  }

  public Object[] getDatas() {
  	Object[] datas = null;
    if (this.getModel() instanceof BillManageModel) {
      if (this.isPrintAll()) {
        datas = ((BillManageModel) this.getModel()).getData().toArray(new Object[0]);
      } else {
        datas = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
      }
      if (VOChecker.isEmpty(datas)) {
        datas = new Object[] {this.getModel().getSelectedData()
        };
      }
    }
    else if (this.getModel() instanceof HierachicalDataAppModel) {
      if (this.isPrintAll()) {
        datas = ((HierachicalDataAppModel) this.getModel()).getAllDatas();
      } else {
        datas = ((HierachicalDataAppModel) this.getModel()).getSelectedDatas();
      }
    }
    else {
      if (this.isPrintAll()) {
        datas = ((BatchBillTableModel) this.getModel()).getRows().toArray(new Object[0]);
      } else {
        datas = ((BatchBillTableModel) this.getModel()).getSelectedOperaDatas();
      }
    }
    if (this.getDataSplit() != null) {
      datas = this.getDataSplit().splitData(datas);
    }
    
    return datas;
  }
  
  @SuppressWarnings("unchecked")
  protected IMetaDataDataSource[] getDefaultMetaDataSource() {
    IMetaDataDataSource[] defaultDataSource = null;
    Object[] datas = getDatas();
    
    boolean isMultiTask = (this.getModel() instanceof HierachicalDataAppModel) ? false : true;
    isMultiTask = (this.getModel() instanceof BatchBillTableModel) ? false : true;
    
    
    if (!VOChecker.isEmpty(datas)) {
      if (isMultiTask) {
        defaultDataSource = new MetaDataSource[datas.length];
        for (int i = 0; i < defaultDataSource.length; i++) {
          defaultDataSource[i] = new MetaDataSource(new Object[] {
            datas[i]
          });
        }
      } else {
        defaultDataSource = new MetaDataSource[] {new MetaDataSource(datas)};
      }
    }
    return defaultDataSource;
  }

  protected nc.ui.pub.print.PrintEntry getPrintEntry() {
    // if (null == this.printEntry) {
    if (this.getParent() == null) {
      // ����ΰ��ʾ����applet��Ϊ�����壬��������򿪺�Ԥ��ʱ��������
      // this.setParent(WorkbenchEnvironment.getClientApplet());
      this.setParent(this.getModel().getContext().getEntranceUI());
    }
    this.printEntry = new nc.ui.pub.print.PrintEntry(this.getParent(), null);
    LoginContext ctx = this.getModel().getContext();
    this.printEntry.setTemplateID(ctx.getPk_group(), ctx.getNodeCode(),
        ctx.getPk_loginUser(), null, this.getNodeKey());
    // }
    
    if(printListener != null) {
    	this.printEntry.setPrintListener(printListener);
    }
    return this.printEntry;

  }

  @Override
  protected boolean isActionEnable() {
	  
	  return true;
	  
//    return this.model.getUiState() == UIState.NOT_EDIT
//        && this.model.getSelectedData() != null;
  }

	/** ����Ȩ��У�� **/
	protected void checkDataPermission(Object obj) throws Exception {
		Object[] objs = getUnDataPermissionData(obj);
		if (objs != null && objs.length > 0) {
			throw new BusinessException(
					IShowMsgConstant.getDataPermissionInfo());
		}
	}

	protected Object[] getUnDataPermissionData(Object obj) {
		return CheckDataPermissionUtil.getUnDataPermissionData(operateCode,
				mdOperateCode, resourceCode, model.getContext(), obj);
	}

	// ����ʵ������Ȩ����Ҫ
	private String mdOperateCode = null; // Ԫ���ݲ�������
	private String operateCode = null; // ��Դ����������룬��������ע����һ������ע�룬�򲻽�������Ȩ�޿��ơ�
	private String resourceCode = null; // ҵ��ʵ����Դ����

	public String getMdOperateCode() {
		return mdOperateCode;
	}

	public void setMdOperateCode(String mdOperateCode) {
		this.mdOperateCode = mdOperateCode;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
}
