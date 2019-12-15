package nc.vo.hkjt.srgk.huiguan.yyribao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import nc.vo.pub.SuperVO;

public class U8VoucherVO extends SuperVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 134623946667617998L;
	private Integer iperiod;
	private Integer iyear;
	private Integer iyperiod;
	private Integer ino_id;
	private Integer inid;
	private Timestamp dbill_date;
	private Integer iflag;
	private String ctext1;
	private String ctext2;
	private String cdigest;
	private String ccode;
	private BigDecimal md;
	private BigDecimal mc;
	private Timestamp dt_date;
	private String cdept_id;
	private String cperson_id;
	private String ccus_id;
	private String csup_id;
	private String citem_id;
	private String citem_class;

	private String ccode_equal;
	private String ccodeexch_equal;
	private Timestamp doutbilldate;
	private String coutno_id;
	private String ccodecontrol;
	private String rowguid;
	private Timestamp tvouchtime;
	private String csign;
	
	private Boolean bflagout;
	private Integer idoc;
	private BigDecimal md_f;
	private BigDecimal mc_f;
	private Double nfrat;
	private Double nd_s;
	private Double nc_s;
	private Integer ibook;
	private Integer isignseq;
	private Boolean bpcsedit;
	private Boolean bdeptedit;
	private Boolean bitemedit;
	private Boolean bcussupinput;  
	private Boolean bvouchedit ;
	private String cbill;
	
	public String getCbill() {
		return cbill;
	}

	public void setCbill(String cbill) {
		this.cbill = cbill;
	}

	public Boolean getBvouchedit() {
		return bvouchedit;
	}

	public void setBvouchedit(Boolean bvouchedit) {
		this.bvouchedit = bvouchedit;
	}

	public Boolean getBpcsedit() {
		return bpcsedit;
	}

	public void setBpcsedit(Boolean bpcsedit) {
		this.bpcsedit = bpcsedit;
	}

	public Boolean getBdeptedit() {
		return bdeptedit;
	}

	public void setBdeptedit(Boolean bdeptedit) {
		this.bdeptedit = bdeptedit;
	}

	public Boolean getBitemedit() {
		return bitemedit;
	}

	public void setBitemedit(Boolean bitemedit) {
		this.bitemedit = bitemedit;
	}

	public Boolean getBcussupinput() {
		return bcussupinput;
	}

	public void setBcussupinput(Boolean bcussupinput) {
		this.bcussupinput = bcussupinput;
	}

	public Integer getIsignseq() {
		return isignseq;
	}

	public void setIsignseq(Integer isignseq) {
		this.isignseq = isignseq;
	}

	public Integer getIperiod() {
		return iperiod;
	}

	public void setIperiod(Integer iperiod) {
		this.iperiod = iperiod;
	}

	public Integer getIyear() {
		return iyear;
	}

	public void setIyear(Integer iyear) {
		this.iyear = iyear;
	}

	public Integer getIyperiod() {
		return iyperiod;
	}

	public void setIyperiod(Integer iyperiod) {
		this.iyperiod = iyperiod;
	}

	public Integer getIno_id() {
		return ino_id;
	}

	public void setIno_id(Integer ino_id) {
		this.ino_id = ino_id;
	}

	public Integer getInid() {
		return inid;
	}

	public void setInid(Integer inid) {
		this.inid = inid;
	}

	public Timestamp getDbill_date() {
		return dbill_date;
	}

	public void setDbill_date(Timestamp dbill_date) {
		this.dbill_date = dbill_date;
	}

	public Integer getIflag() {
		return iflag;
	}

	public void setIflag(Integer iflag) {
		this.iflag = iflag;
	}

	public String getCtext1() {
		return ctext1;
	}

	public void setCtext1(String ctext1) {
		this.ctext1 = ctext1;
	}

	public String getCtext2() {
		return ctext2;
	}

	public void setCtext2(String ctext2) {
		this.ctext2 = ctext2;
	}

	public String getCdigest() {
		return cdigest;
	}

	public void setCdigest(String cdigest) {
		this.cdigest = cdigest;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public BigDecimal getMd() {
		return md;
	}

	public void setMd(BigDecimal md) {
		this.md = md;
	}

	public BigDecimal getMc() {
		return mc;
	}

	public void setMc(BigDecimal mc) {
		this.mc = mc;
	}

	public Timestamp getDt_date() {
		return dt_date;
	}

	public void setDt_date(Timestamp dt_date) {
		this.dt_date = dt_date;
	}

	public String getCdept_id() {
		return cdept_id;
	}

	public void setCdept_id(String cdept_id) {
		this.cdept_id = cdept_id;
	}

	public String getCperson_id() {
		return cperson_id;
	}

	public void setCperson_id(String cperson_id) {
		this.cperson_id = cperson_id;
	}

	public String getCcus_id() {
		return ccus_id;
	}

	public void setCcus_id(String ccus_id) {
		this.ccus_id = ccus_id;
	}

	public String getCsup_id() {
		return csup_id;
	}

	public void setCsup_id(String csup_id) {
		this.csup_id = csup_id;
	}

	public String getCitem_id() {
		return citem_id;
	}

	public void setCitem_id(String citem_id) {
		this.citem_id = citem_id;
	}

	public String getCitem_class() {
		return citem_class;
	}

	public void setCitem_class(String citem_class) {
		this.citem_class = citem_class;
	}

	public String getCcode_equal() {
		return ccode_equal;
	}

	public void setCcode_equal(String ccode_equal) {
		this.ccode_equal = ccode_equal;
	}

	public String getCcodeexch_equal() {
		return ccodeexch_equal;
	}

	public void setCcodeexch_equal(String ccodeexch_equal) {
		this.ccodeexch_equal = ccodeexch_equal;
	}

	public Timestamp getDoutbilldate() {
		return doutbilldate;
	}

	public void setDoutbilldate(Timestamp doutbilldate) {
		this.doutbilldate = doutbilldate;
	}

	public String getCoutno_id() {
		return coutno_id;
	}

	public void setCoutno_id(String coutno_id) {
		this.coutno_id = coutno_id;
	}

	public String getCcodecontrol() {
		return ccodecontrol;
	}

	public void setCcodecontrol(String ccodecontrol) {
		this.ccodecontrol = ccodecontrol;
	}

	public String getRowguid() {
		return rowguid;
	}

	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}

	public Timestamp getTvouchtime() {
		return tvouchtime;
	}

	public void setTvouchtime(Timestamp tvouchtime) {
		this.tvouchtime = tvouchtime;
	}

	public String getCsign() {
		return csign;
	}

	public void setCsign(String csign) {
		this.csign = csign;
	}

	public Boolean getBflagout() {
		return bflagout;
	}

	public void setBflagout(Boolean bflagout) {
		this.bflagout = bflagout;
	}

	public Integer getIdoc() {
		return idoc;
	}

	public void setIdoc(Integer idoc) {
		this.idoc = idoc;
	}

	public BigDecimal getMd_f() {
		return md_f;
	}

	public void setMd_f(BigDecimal md_f) {
		this.md_f = md_f;
	}

	public BigDecimal getMc_f() {
		return mc_f;
	}

	public void setMc_f(BigDecimal mc_f) {
		this.mc_f = mc_f;
	}

	public Double getNfrat() {
		return nfrat;
	}

	public void setNfrat(Double nfrat) {
		this.nfrat = nfrat;
	}

	public Double getNd_s() {
		return nd_s;
	}

	public void setNd_s(Double nd_s) {
		this.nd_s = nd_s;
	}

	public Double getNc_s() {
		return nc_s;
	}

	public void setNc_s(Double nc_s) {
		this.nc_s = nc_s;
	}

	public Integer getIbook() {
		return ibook;
	}

	public void setIbook(Integer ibook) {
		this.ibook = ibook;
	}

	@Override
	public String getTableName() {
		return "gl_accvouch";
	}
}
