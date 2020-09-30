package nc.pubimpl.ic.m4455.action;

import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ic.pub.db.SqlIn;
import nc.bs.ic.pub.util.RefQueryTemplate;
import nc.pubitf.ic.transtype.IInOutTransTypeQueryService;
import nc.uap.ws.gen.util.StringUtil;
import nc.vo.ic.general.define.ICBillFlag;
import nc.vo.ic.m4455.entity.SapplyBillBodyVO;
import nc.vo.ic.m4455.entity.SapplyBillHeadVO;
import nc.vo.ic.m4455.entity.SapplyBillVO;
import nc.vo.ic.m4455.entity.SapplyBillViewVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ic.pub.sql.SqlUtil;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.ic.transtype.TransTypeExtendVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 出库单参照出库申请制单时查询方法
 *
 * @since 6.0
 * @version 2011-1-19 上午09:22:41
 * @author jinjya
 */
public class SapplyBillQueryAction extends
        RefQueryTemplate<SapplyBillVO, SapplyBillViewVO>{

  public SapplyBillQueryAction(
      String destbilltype) {
    super(destbilltype, new SapplyBillViewVO());
  }

  /**
   * 方法功能描述：根据单据编码查询出库申请的相对应的交易类型
   * <p>
   * <b>参数说明</b>
   *
   * @param billtype
   *          单据类型
   * @return 查询交易类型条件
   *         <p>
   * @since 6.0
   * @author jinjya
   * @throws BusinessException
   *           业务异常处理类
   * @time 2010-12-27 下午02:20:39
   */
  private String getTranstypeSql(String billtype) throws BusinessException {
    TransTypeExtendVO[] extVOs =
        NCLocator.getInstance().lookup(IInOutTransTypeQueryService.class)
            .getTransTypeid(ICBillType.SapplyBill.getCode(),billtype);
    if (ValueCheckUtil.isNullORZeroLength(extVOs)) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008003_0","04008003-0122")/*@res "请在出库申请单交易类型配置对应申请出库类型！"*/);
    }
    StringBuffer sf= new StringBuffer();
    Set<String> trantypeidSet = VOEntityUtil.getVOsValueSet(extVOs, ICPubMetaNameConst.CTRANTYPEID);
    String[] trantypeids = CollectionUtils.setToArray(trantypeidSet);
    sf.append(SqlIn.formInSQLWithoutAnd(ICPubMetaNameConst.CTRANTYPEID,trantypeids));
    return sf.toString();
  }

  @Override
  protected void appendWhereSQL(QuerySchemeProcessor processor) {
	//材料出库单支持红字退库申请sql处理
	String numsql = getNumSql(processor);
	
    SqlBuilder bf = new SqlBuilder();
    String headtableAlias = processor.getMainTableAlias();
    // 获取配置交易类型
    String transtypecode = null;
    try {
    	if ("4K".equals(this.getDestBilltype())) {
    		// HK 2020年9月28日22:46:01
    		// 适配 转库 拉 出库申请
    		transtypecode = "ic_sapply_h.vtrantypecode = '4455-Cxx-10'";
    	} else {
    		transtypecode = this.getTranstypeSql(this.getDestBilltype());
    		// HK 2020年9月28日23:04:20
    		// 标准单据的拉单，排除 转二级库这个类型
    		transtypecode += "ic_sapply_h.vtrantypecode <> '4455-Cxx-10'";
    	}
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (!StringUtil.isEmptyOrNull(transtypecode)) {
      bf.append(" and "+transtypecode);
    }

    bf.append(" and " + this.getTableAlia(this.getTableInfo().getBodyTable()) + "." + SapplyBillBodyVO.ISCLOSED
        + " <>'Y' and ");
    bf.append(headtableAlias +"."+ ICPubMetaNameConst.FBILLFLAG +" in ("+ICBillFlag.AUDIT.value()+") ");
    if(!StringUtil.isEmptyOrNull(numsql)){
    	bf.append(" and ");
    	bf.startParentheses();
    	bf.append(numsql);
    	bf.endParentheses();
    }
    /**
     * HK 2020年9月28日22:46:13
     * 根据下游单据是否存在 来确定是否参照出来
     * not exists (
	     select zkb.cspecialbid from ic_whstrans_b zkb
	     where zkb.dr = 0 and zkb.csourcetype = '4455' 
	     and zkb.csourcebillbid = ic_sapply_b.cgeneralbid 
	   )
     */
    if ("4K".equals(this.getDestBilltype())) {
	    StringBuffer whereSQL_4K = 
	    new StringBuffer(" not exists (")
	    		.append(" select zkb.cspecialbid from ic_whstrans_b zkb ")
	    		.append(" where zkb.dr = 0 and zkb.csourcetype = '4455' ")
	    		.append(" and zkb.csourcebillbid = ic_sapply_b.cgeneralbid ")
	    		.append(" ) ")
	    ;
	    bf.append(" and ");
	    bf.append(whereSQL_4K);
    }
    /***END***/
    processor.appendWhere(bf.toString());
  }

	private String getNumSql(QuerySchemeProcessor processor) {
		// 出库申请类别 全部2/出库申请0/退库申请1
		QueryCondition condition = processor
				.getQueryCondition(SapplyBillHeadVO.FREPLENISHFLAGS);
		int iType = 2;
		if (condition != null) {
			iType = Integer.valueOf(condition.getValues()[0]);
		}
		SqlBuilder numsql = new SqlBuilder();
		if(ICBillType.MaterialOut.getCode().equals(this.getDestBilltype())){
			if(iType == 0){
				numsql.append(this.getTableAlia(this.getTableInfo().getBodyTable())+"."+ICPubMetaNameConst.NNUM + " >= 0 " );
				numsql.append(" and (" + SqlUtil.getFun_ISNULL_For_Number(this.getTableAlia(this.getTableInfo().getBodyExtTable()), SapplyBillBodyVO.NOUTBOUNDNUM));
				numsql.append(" or ");
				numsql.append(this.getTableAlia(this.getTableInfo().getBodyExtTable())+"."+SapplyBillBodyVO.NOUTBOUNDNUM+" <= ");
				numsql.append(this.getTableAlia(this.getTableInfo().getBodyTable())+"."+ICPubMetaNameConst.NNUM);
				numsql.append(")");
			}
			if(iType == 1){
				numsql.append(this.getTableAlia(this.getTableInfo().getBodyTable())+"."+ICPubMetaNameConst.NNUM + " < 0 " );
				numsql.append(" and (" + SqlUtil.getFun_ISNULL_For_Number(this.getTableAlia(this.getTableInfo().getBodyExtTable()), SapplyBillBodyVO.NOUTBOUNDNUM));
				numsql.append(" or ");
				numsql.append(this.getTableAlia(this.getTableInfo().getBodyExtTable())+"."+SapplyBillBodyVO.NOUTBOUNDNUM+" > ");
				numsql.append(this.getTableAlia(this.getTableInfo().getBodyTable())+"."+ICPubMetaNameConst.NNUM);		
				numsql.append(")");
			}
			if(iType == 2){
			}
		}else{
			numsql.append(SqlUtil.getFun_ISNULL_For_Number(this.getTableAlia(this.getTableInfo().getBodyExtTable()), SapplyBillBodyVO.NOUTBOUNDNUM));
			numsql.append(" or ");
			numsql.append(this.getTableAlia(this.getTableInfo().getBodyExtTable())+"."+SapplyBillBodyVO.NOUTBOUNDNUM+" <= ");
			numsql.append(this.getTableAlia(this.getTableInfo().getBodyTable())+"."+ICPubMetaNameConst.NNUM);
		}
		return numsql.toString();
	}

  @Override
  protected ICBillType getBilltype() {
    return ICBillType.SapplyBill;
  }
  @Override
  protected String getBodyFixWhere(String bodytable, String exetable) {
    return null;
  }
  @Override
  protected String getHeadFixWhere(String headtable) {
    return null;
  }

  @Override
  protected void appendAdditionalSQL(QuerySchemeProcessor processor) {
    processor.appendRefTrantypeWhere(ICBillType.SapplyBill.getCode(),
      this.getDestBilltype(), ICPubMetaNameConst.VTRANTYPECODE);
  }
}