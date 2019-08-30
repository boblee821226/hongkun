package nc.bs.hkjt.srgk.jiudian.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.TimeService;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IJd_rzmxMaintain;
import nc.itf.org.IOrgVersionQryService;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.jdbc.framework.processor.VectorProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * �����Ƶ����� ��̨����
 * <p>
 * @author zhangjc
 * @date 2015-08-04
 *
 */
public class ImpJiudianData implements IBackgroundWorkPlugin {
	public final static int  DECIMALBIT =2;//С������λ��
	
	/**
	 * ���ڴ������
	 * ��̨�����޷�ִ��
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		
		String[] pk_orgs = new String[]{
				"0001N510000000001SXV"	// ����
//				"0001N510000000001SY5"	// ����
			};
		
		String[] dateP = new String[]{
				"2019-01-13",
				"2019-01-13"
			};

		long startTime=System.currentTimeMillis();
		
		if(pk_orgs==null||pk_orgs.length==0)return null;
		
		HashMap<String,String> infoMap=getDefaultInfo(pk_orgs[0]);//�õ����ñ���Ϣ
		
		if(infoMap!=null){
			
			boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{"HK-ǧ�������ݵ���"+pk_orgs[0]});
			
			if(!lock){
				throw new BusinessException("�������ڴ�����,�����ظ�������");
			}
			
//			infoMap.put("pk_org", "0001N510000000001SXV");	// ���� ��HK 2018��11��5��19:14:48��
//			infoMap.put("org_code", "0302");				// ���� ��HK 2018��11��5��19:21:24��
//			infoMap.put("org_name", "���ʻ��");				// ���� ��HK 2018��11��5��19:24:13��
			infoMap.put("db_name", "hkjt_jd_kfrxsd");		// ���� ��HK 2018��11��7��14:42:09��
			
			String[] timeDates=getTimeDates(dateP[0],dateP[1]);
			
			for (String date : timeDates) {
				
				String timeWhere=getTimeWhere(date,date,getTimeWhereField());//�õ���̨�����ж�������ڣ������where����
				executeDateTongBu(infoMap,timeWhere);
			}
		}
		
		System.out.println("�������,����ʱ��"+(System.currentTimeMillis()-startTime)+"����");
	
		return null;
	
	}
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		long startTime=System.currentTimeMillis();
		String []pk_orgs=bgwc.getPk_orgs();//��֯Ϊ���䣬���Կ϶���ֵ
		if(pk_orgs==null||pk_orgs.length==0)return null;
			HashMap<String,String> infoMap=getDefaultInfo(pk_orgs[0]);//�õ����ñ���Ϣ
			if(infoMap!=null){
				boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{bgwc.getAlertTypeName()+bgwc.getPk_orgs()[0]});
				if(!lock){
					throw new BusinessException("�������ڴ�����,�����ظ�������");
				}
				
				if("���ʻ��".equals(infoMap.get("org_name")))	// ����� ���ʻ�ݣ���Ҫ�ĳ� ���������ݿ�
				{
					infoMap.put("db_name", "hkjt_jd_kfrxsd");		// ���� ��HK 2018��11��7��14:42:09��
				}
				
				String[] timeDates=getTimeDates(bgwc.getKeyMap().get("begindate"),bgwc.getKeyMap().get("enddate"));
//				String timeWhere=getTimeWhere(bgwc.getKeyMap().get("begindate"),bgwc.getKeyMap().get("enddate"),getTimeWhereField());//�õ���̨�����ж�������ڣ������where����
				for (String date : timeDates) {
					String timeWhere=getTimeWhere(date,date,getTimeWhereField());//�õ���̨�����ж�������ڣ������where����
					executeDateTongBu(infoMap,timeWhere);
				}
				
			}
	System.out.println("�������,����ʱ��"+(System.currentTimeMillis()-startTime)+"����");
	
		return null;
	}

	/**
	 * zhangjc
	 * 2015-8-6����10:58:58
	 * void
	 * ������ϸͬ��
	 *
	 */
	public void executeDateTongBu(HashMap<String,String> infoMap,String timeWhere) throws BusinessException{
		Connection hkjt_jd_conn=null;
		JdbcSession hkjt_jd_conn_session =null;
		hkjt_jd_conn=new JDBCUtils(infoMap.get("db_name")).getConn(JDBCUtils.HKJT_HG);
		hkjt_jd_conn_session = new JdbcSession(hkjt_jd_conn);
		try{
		ArrayList<RzmxBVO> list=(ArrayList<RzmxBVO>)hkjt_jd_conn_session.executeQuery(getQuerySql(timeWhere), new BeanListProcessor(RzmxBVO.class));
		RzmxBillVO[] aggvos=getRzmxAggVO(list,infoMap,hkjt_jd_conn_session);//ת��ΪNC������ϸ�ۺ�VO
		
		saveRzmxAggVOs(aggvos);//����
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}finally{
			hkjt_jd_conn_session.closeAll();
			JDBCUtils.closeConn(hkjt_jd_conn);
		}
		
	}
	public void saveRzmxAggVOs(RzmxBillVO[] aggvos) throws BusinessException {
		if(aggvos!=null&&aggvos.length>0){
			IJd_rzmxMaintain itf=NCLocator.getInstance().lookup(IJd_rzmxMaintain.class);
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//�����Ƶ���
			
//			/**
//			 * HK 2018��11��5��20:04:50
//			 */
//			for(RzmxBillVO billVO : aggvos)
//			{
//				billVO.getParentVO().setPk_org("0001N510000000001SXV");		// ����
//				billVO.getParentVO().setPk_org_v("0001N510000000001SXU");	// ����
//			}
//			/***END***/
			
			itf.insert(aggvos, null);
		}
	}
	/**
	 * zhangjc
	 * 2015-8-5����10:23:07
	 * RzmxBillVO[]
	 *���������ϸ�ۺ�VO����
	 */
	public RzmxBillVO[] getRzmxAggVO(ArrayList<RzmxBVO> list,HashMap<String,String> infoMap,JdbcSession session) throws BusinessException{
		IOrgVersionQryService orgVersion=NCLocator.getInstance().lookup(IOrgVersionQryService.class);
		//����ǧ�����ĳɹ̶��� ���ʵ��pk��
		String pk_org = infoMap.get("pk_org");
//		String pk_org = "0001N510000000001SXV";		// ���� ��HK 2018��11��5��19:56:26��
		String pk_org_v=orgVersion.getOrgUnitLastVersionByOrgID(pk_org).getPk_vid();
		String pk_group=AppContext.getInstance().getPkGroup();
		
		HashMap<String,ArrayList<RzmxBVO>> mapvos=new HashMap<String, ArrayList<RzmxBVO>>();
		for (RzmxBVO bvo : list) {//�������ڷ���
			String dbilldate=bvo.getPk_hk_srgk_jd_rzmx();
			bvo.setPk_hk_srgk_jd_rzmx(null);
			if(mapvos.containsKey(dbilldate)){
				ArrayList<RzmxBVO> blist=mapvos.get(dbilldate);
				blist.add(bvo);
				mapvos.put(dbilldate, blist);
			}else{
				ArrayList<RzmxBVO> blist=new ArrayList<RzmxBVO>();
				blist.add(bvo);
				mapvos.put(dbilldate, blist);
			}
		}
		ArrayList<RzmxBillVO> listvo=new ArrayList<RzmxBillVO>();
		HashSet<String> exists=getNCBillsByPk_org(pk_org);//��NC���Ѿ����ڵ�������ϸ���ݣ����������ж���
		HashMap<String,UFDouble[]> otherdate=getOtherData(session, mapvos.keySet());//�����ʣ�ƽ�����ۣ��ͷ����룬�ɳ��ⷿ����, REVPAR 
		for (String key : mapvos.keySet()) {//��װ������ϸ�ۺ�VO
			if(exists.contains(key)){continue;}
			RzmxBillVO aggvo=new RzmxBillVO();
			RzmxHVO hvo = getHeadVOs(infoMap ,pk_org ,pk_org_v, pk_group,key);
			UFDouble[] datas=otherdate.get(key);
			if(datas!=null&&datas.length>0){
				hvo.setFfl(datas[0]);
				hvo.setPjfj(datas[1]);
				hvo.setKfsr(datas[2]);
				hvo.setKczfs(datas[3]);
				hvo.setRevpar(datas[4]);
			}
			aggvo.setChildrenVO(getBodyVos(mapvos.get(key),hvo));
			aggvo.setParentVO(hvo);
			listvo.add(aggvo);
		}
		return listvo.toArray(new RzmxBillVO[]{});
	}
	/**
	 * zhangjc
	 * 2015-8-5����10:23:25
	 * RzmxHVO
	 *�����ͷVO����
	 */
	public RzmxHVO getHeadVOs(HashMap<String, String> infoMap,String pk_org,
			String pk_org_v, String pk_group,String dbilldate) {
		RzmxHVO hvo=new RzmxHVO();
		hvo.setPk_org(pk_org);
		hvo.setPk_org_v(pk_org_v);
		hvo.setPk_group(pk_group);
		hvo.setIbillstatus(-1);
		hvo.setVbilltypecode("HK11");//��������
		hvo.setDbilldate(dbilldate==null?AppContext.getInstance().getBusiDate():new UFDate(dbilldate));
		return hvo;
	}
/**
 * zhangjc
 * 2015-8-5����10:15:29
 * RzmxBVO[]
 *�Ա���ֵ������
 * @throws DAOException 
 */
public RzmxBVO[] getBodyVos(ArrayList<RzmxBVO> bodyvos,RzmxHVO hvo) throws DAOException{
	HashMap<String,String[]> bmxx=getAllBmxxByPk_org(hvo.getPk_org());//�õ�������Ϣ key=�� �������� value={����������������������}
	HashMap<String,SpflHVO> spfl=getAllSpflByPk_org(hvo.getPk_org()) ;//�õ���Ʒ������Ϣ 
	HashMap<String,JzfsHVO> jzfs=getAllJzfsByPk_org(HKJT_PUB.PK_ORG_JIUDIAN) ;//�õ����˷�ʽ��Ϣ
	int vrowno=10;
	UFDouble xfje=UFDouble.ZERO_DBL;
	UFDouble jzje=UFDouble.ZERO_DBL;
	for (RzmxBVO rzmxBVO : bodyvos) {
		rzmxBVO.setVrowno(String.valueOf(vrowno));
		vrowno+=10;
		UFDouble xf=nullAsZero(rzmxBVO.getCharge());
		String itemName = rzmxBVO.getItem_name();	// ������Ŀ����
		String itemCode = rzmxBVO.getItem_code();	// ������Ŀ����
		if(!isZero(xf)){//���charge ��Ϊ0��Ҫ������Ʒ���ࡣ����item_name ����Ӧ������orȫ����,������-���ݷ��Ͷ�Ӧ������������ item_name ��Ӧ��
			String itemname=null;
			if( itemName!=null &&
				(
				   strEqual(new String[]{"ȫ����","������","�Ӵ���"},itemName)||itemName.trim().indexOf("����")!=-1||itemName.trim().indexOf("����")!=-1
				)
			){
				itemname=rzmxBVO.getRmtype_name();//����
			}else{
				itemname=itemName==null?itemName:itemName.toLowerCase();
			}
			SpflHVO spflvo=spfl.get(itemname);
			if(spflvo!=null){
				rzmxBVO.setSpfl_name(spflvo.getName());
				rzmxBVO.setSpfl_id(spflvo.getPk_hk_srgk_hg_spfl());
				rzmxBVO.setSrxm_id(spflvo.getPk_hk_srgk_hg_srxm());//������Ŀ ȡ NC��Ʒ���� ����������Ŀ
				/**
				 * ���
				 * 2016��3��20��14:49:59
				 */
				rzmxBVO.setBm_id(  spflvo.getPk_dept() );	// ��ֵ ��Ʒ���������õĲ���
				rzmxBVO.setBm_fid( spflvo.getPk_dept() );	// ��ֵ ��Ʒ���������õĲ���
				/**END*/
				
			}
		}
		UFDouble jz=nullAsZero(rzmxBVO.getPayment());
		if(!isZero(jz)){//payment ���˽�� ��Ϊ0�� ��Ҫ������˷�ʽ������item_name������ҡ���Ѻ��- �ֽ�������-pos��תӦ��-���ѿͻ������
			String jzfsname=null;
			if(strEqual(new String[]{"�ֽ��˿�","�����","��Ѻ��","��Ѻ��","Ԥ������"
									,"��Ҳ��","�ֽ�","�ֽ�֧��","������ֽ�","Ԥ��Ѻ��"
									,"POS�ֽ�"
					},itemName)){
				jzfsname="�ֽ�";
				
			}else if(strEqual(new String[]{"������","���ÿ�","���ÿ����лؿ�","���ÿ�������"
										  ,"POS-������"
					},itemName)){
				jzfsname="POS";
				
			}else if(strEqual(new String[]{"תӦ��","���й���","��ǰ̨��"
										  ,"�д�","����ס���Ա���"
										  ,"����ס����","����"
										  ,"Ӧ�յ���","POS��Ԥ��"
//										  ,"ת��̨��","ת�����̨��"	// ��� 2016��9��1��16:10:10  ��Ҧ���� ��� ȡ�������������⴦��
					},itemName)){
				jzfsname="���ѿͻ�������";
				
			}else if(strEqual(new String[]{"ת��֧Ʊ","ת��֧Ʊ"},itemName)){
				jzfsname="֧Ʊ";
				
			}else if(strEqual(new String[]{"��Ա��","һ��ͨ","һ��ͨ����","����������","�������","��������ʯ��"
										  ,"����","��","��ʯ��","��ֵ������"
					},itemName)){
				jzfsname="��Ա������";
				
			}else if(itemName!=null&&itemName.contains("���ת��")){
				jzfsname="���ת��";
				
			}else if(itemName!=null&&itemName.contains("���ת��")){
				jzfsname="���ת��";
				
			}else if(strEqual(new String[]{"����ȯ","��ѷ�ȯ","���ֳ������","COUPON"
										  ,"���ȯ","���ָ���"
										  ,"POS����ȯ"
					},itemName)){
				jzfsname="��ȯ";
			}
			
			else if( PuPubVO.getString_TrimZeroLenAsNull(itemName)==null
				  && "9".equals(itemCode)
			) // ���˱���Ϊ 9 �ģ�  ����û�ж�Ӧ�ĵ�����  ���� ��NCĬ��Ϊ  �ڲ�ת�ˡ�
			{
				jzfsname = "�ڲ�ת��";
			}
			/**
			 * תǰ̨  ɢ��Ѻ��-�ֹ�������HK-2019��2��2��15:26:04��
			 */
			else if(strEqual(new String[]{"תǰ̨"},itemName)){
				jzfsname="ɢ��Ѻ��-�ֹ�����";
			}
			/***END***/
			
			else{
				jzfsname=itemName;//  �ڲ����
			}
			rzmxBVO.setJzfs_name(jzfsname);//���˷�ʽname
			String pk_jzfs=jzfs.get(jzfsname)==null?null:jzfs.get(jzfsname).getPk_hk_srgk_hg_jzfs();
			rzmxBVO.setJzfs_id(pk_jzfs);//���˷�ʽid
			
			/**
			 * ������������� = ת�����̨��    �򽫿ͻ���ֵΪ  ������
			 * ���  2015��12��28��20:18:33
			 */
			if(strEqual(new String[]{"ת�����̨��"},itemName))
			{
				rzmxBVO.setKhmz("������");
			}
			/**END*/
		}
		/**
		 * ���
		 * 2016��3��20��14:52:48
		 */
		if( PuPubVO.getString_TrimZeroLenAsNull(rzmxBVO.getBm_id())==null )
		{// ���֮ǰû�� ��ֵ��  ��Ʒ����� Ĭ�ϲ��ţ� ����Ҫȡ  ҵ��ϵͳ��Ĭ�ϲ��š�  
			String[] bmid = bmxx.get(rzmxBVO.getBm_name())==null?new String[2]:bmxx.get(rzmxBVO.getBm_name());//
			rzmxBVO.setBm_id(bmid[0]);
			rzmxBVO.setBm_fid(bmid[1]);
		}
		/**END*/
		jzje=jzje.add(nullAsZero(rzmxBVO.getPayment()));//���˽��
		xfje=xfje.add(nullAsZero(rzmxBVO.getCharge()));//���ѽ��
	}
	
	hvo.setXfje(xfje);
	hvo.setJzje(jzje);
	return bodyvos.toArray(new RzmxBVO[]{});
}
/**
 * zhangjc
 * 2015-8-25����10:22:59
 * boolean
 * �ж�itemName�Ƿ������ strArray�У�����������򷵻�true
 *
 */
public boolean strEqual(String [] strArray,String itemName){
	for (String string : strArray) {
		if(string.equals(itemName))
			return true;
	}
	return false;
}	

	/**
	 * ������֯��ѯ��Ĭ��ϵͳ��Ϣ
	 * @param pk_org
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,String> getDefaultInfo(String pk_org) throws DAOException{
		String sql="select * from hk_srgk_hg_info where pk_org='"+pk_org+"'";
		HashMap<String,String> map=(HashMap<String,String>)getBaseDAO().executeQuery(sql, new MapProcessor());
		return map;
	}
	
	BaseDAO dao=null;
	public BaseDAO getBaseDAO(){
		if(dao==null)
			dao=new BaseDAO();
		return dao;
	}
	
	public String getTimeWhereField(){
		return "convert(varchar(10),trans.accdate,120)";
	}
	/**
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	protected String getTimeWhere(Object beginTime,Object endTime,String timeField){
		String defaultBeginTime=getCurrentTime().getDate().getDateBefore(1).toString().substring(0,10);
		String defaultEndTime=getCurrentTime().getDate().toString().substring(0,10);
		
		String where="";
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//��ʼ��������Ϊ��
			where="("+timeField+">='"+beginTime+"' and "+timeField+"<='"+endTime+"')" ;
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//��ʼ��Ϊ�գ�����Ϊ��
			where="("+timeField+">='"+beginTime+"' and "+timeField+"<'"+defaultEndTime+"')" ;
		}else{
//		if(((beginTime==null||beginTime.trim().length()==0)&&(endTime==null||endTime.trim().length()==0))//��ʼ������Ϊ��
//		  ||((beginTime==null||beginTime.trim().length()==0)&&(endTime!=null&&endTime.trim().length()>0))){//��ʼΪ�գ�������Ϊ��
		   where="("+timeField+"='"+defaultBeginTime+"')" ;
//			}
		}
		return where;
	}
	
	protected String[] getTimeDates(Object beginTime,Object endTime){
		UFDate beginDate=null;
		UFDate endDate=null;
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//��ʼ��������Ϊ��
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(endTime.toString());
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//��ʼ��Ϊ�գ�����Ϊ��
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(getCurrentTime().getDate().getDateBefore(1).toString().substring(0,10));
		}else{
			return new String[]{getCurrentTime().getDate().getDateBefore(1).toString().substring(0,10)};
		}
		
		
		ArrayList<String> datesList=new ArrayList<String>();
		for (int i = 0; i <=UFDate.getDaysBetween(beginDate, endDate); i++) {
			String dateStr=beginDate.getDateAfter(i).toString().substring(0,10);
			if(!datesList.contains(dateStr))
			datesList.add(dateStr);
		}
		
		return datesList.toArray(new String[]{});
	}
	
	public String getQuerySql(String where){
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("SELECT")
				 .append(" convert(varchar(10),trans.accdate,120) PK_HK_SRGK_JD_RZMX,")	//��������
				 .append(" rtrim(trans.cashier) syy_code,")	//����Աcode
				 .append(" rtrim(trans.category) jylb_code ,")//�������code
				 .append(" rtrim(jylb.cname) jylb_name ,") //�������name
				 .append(" rtrim(outlet.cname) bm_name,")  //����name
				 .append(" rtrim(trans.item) item_code,")	//��Ŀcode
				 .append(" rtrim(item.cname) item_name,")	//��Ŀname (ȥ�� �ұ߿ո�)
				 .append(" convert(varchar(19),trans.transdate,120) transdate,")//����ʱ��
				 .append(" rtrim(trans.refno) refno,")	 //refno
				 .append(" rtrim(trans.descript) descript,") //descript
				 .append(" rtrim(trans.accid) accid,")	 //accid
				 .append(" trans.charge ,")	//���ѽ��
				 .append(" trans.payment,") //���˽��
				 .append(" rtrim(gres.gstname) khmz,") //�ͻ�����
//				 .append(" case when item.cname='תӦ��' then trans.descript else gres.gstname END khmz,")	//�ͻ����֣������תӦ�� Ҫȡ�����ͻ������֣�
				 .append(" rtrim(gres.rmno) rmno,")		//�����
				 .append(" rtrim(trans.transid) transid,")  //transid  
				 .append(" rtrim(rmtype.cname) rmtype_name")	//��������
				 .append(" FROM  trans")
			.append(" left join gres  on trans.accid = gres.accid")		
			.append(" left join room on gres.rmno = room.code	")		//����
			.append(" left join rmtype on room.rmtype = rmtype.code")	//��������
			.append(" left join outlet on trans.outlet = outlet.code")  //���ţ�����㣩
			.append(" left join item on trans.item = item.code")		//��Ŀ
			.append(" left join codes jylb on trans.category = jylb.code and jylb.category='transcat'")  // �������
		    .append(" WHERE  (1=1)")
			.append(" and ( trans.voidflag=-1 or trans.voidpath=0 )") 
			.append(" and item.cname<>'תӦ��' ")	// ȥ��תӦ�յ�����
//			.append(" and convert(varchar(10),trans.accdate,120)='2015-07-25' ") 
			.append(" and "+where) 
			
			// ȡ תӦ�յ�����
			.append(" union all ")
				.append(" SELECT ")
				.append(" convert(varchar(10),a.t_date,120) PK_HK_SRGK_JD_RZMX ")	//��������
				.append(",null ")	//����Աcode
				.append(",null ")	//�������code
				.append(",null ")	//�������name
				.append(",'ǰ̨' ")	//����name
				.append(",null ")	//��Ŀcode
				.append(",'תӦ��' ")	//��Ŀname
				.append(",null ")	//����ʱ��
				.append(",null ")	//refno
				.append(",rtrim(a.t_par) ")//descript
				.append(",null ")	//accid
				.append(",null ")	//���ѽ��
				.append(",case a.t_class when 0 then a.t_amt else -1*t_amt end payment ")	//���˽��
				.append(",rtrim(b.c_name) khmz ")	//�ͻ�����
				.append(",null ")	//�����
				.append(",null ")	//transid
				.append(",null ")	//��������
				.append(" FROM ascbos7.dbo.artran a left join ascbos7.dbo.cust b on a.t_cust=b.c_code ")
				.append(" where (1=1) ")
				.append(" and "+ (where.replaceAll("trans.accdate","a.t_date")) ) 
			
			// ȡ����ת�����ݣ�HK-����7 2019��1��9��18:34:31��
			.append(" union all ")
				.append(" SELECT ")
				.append(" convert(varchar(10),a.t_date,120) PK_HK_SRGK_JD_RZMX ")	//��������
				.append(",null ")	//����Աcode
				.append(",null ")	//�������code
				.append(",null ")	//�������name
				.append(",'ǰ̨' ")	//����name
				.append(",t_item ")	//��Ŀcode
				.append(",c.name ")	//��Ŀname
				.append(",null ")	//����ʱ��
				.append(",null ")	//refno
				.append(",rtrim(a.t_par) ")//descript
				.append(",null ")	//accid
				.append(",null ")	//���ѽ��
				.append(",a.t_amt payment ")	//���˽��
				.append(",rtrim(b.c_name) khmz ")	//�ͻ�����
				.append(",null ")	//�����
				.append(",null ")	//transid
				.append(",null ")	//��������
				.append(" FROM ascbos7.dbo.artran a ")
				.append(" left join ascbos7.dbo.cust b on a.t_cust=b.c_code ")
				.append(" left join ascbos7.dbo.aritem c  on a.t_item=c.code ")
				.append("  where (1=1) and a.t_class=1 ")
				.append(" and a.t_item in ('0101','0103','0115','0122','0123','0124','0125','0126') ")
				.append(" and "+ (where.replaceAll("trans.accdate","a.t_date")) ) 
			
			.append(" ORDER BY rtrim(trans.cashier),rtrim(trans.category) ")  //,trans.item,trans.transdate
		//	 .append(" group by rmtype.cname")

		.append("");
		
		return sqlBuffer.toString();
	}
	
	
	/**
	 * �õ�NCϵͳ���в�����Ϣ
	 * ����pk���ϼ�����pk
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,String[]> getAllBmxxByPk_org(String pk_org) throws DAOException{

		HashMap<String,String[]> map=new HashMap<String, String[]>();
		String sql="select pk_org,def1,pk_dept,decode (pk_fatherorg,'~',pk_dept,pk_fatherorg) pk_fatherorg from org_dept where pk_org='"+pk_org+"' and nvl(dr,0)=0 ";
		Vector<Vector<String>> vector=(Vector<Vector<String>>)getBaseDAO().executeQuery(sql, new VectorProcessor());
		for (Vector<String> v : vector) {
			
			String key = v.elementAt(1);
			String[] keys = null;
			if(key!=null && key.indexOf("��")>-1)
			{	// ��� ���� �ָ�������˵�� �� ���ҵ���� ��Ӧһ��NC���š� ��Ҫ��ִ���
				keys = key.split("��");
			}
			else
			{
				keys = new String[]{key};
			}
			
			for(int i=0;keys!=null&&i<keys.length;i++)
			{
				map.put(keys[i], new String[]{v.elementAt(2),v.elementAt(3)});//�õ�����pk���ϼ�����pk
			}
		}
		
		return map;
		
	}
	
	/**
	 * �õ�NCϵͳ������Ʒ������Ϣ
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,SpflHVO> getAllSpflByPk_org(String pk_org) throws DAOException{
		HashMap<String,SpflHVO> map=new HashMap<String, SpflHVO>();
		String sql="select * from hk_srgk_hg_spfl where nvl(dr,0)=0 and pk_org='"+pk_org+"'";
		ArrayList<SpflHVO> list=(ArrayList<SpflHVO>)getBaseDAO().executeQuery(sql, new BeanListProcessor(SpflHVO.class));
		for (SpflHVO hvo : list) {
			map.put(hvo.getName().toLowerCase(), hvo);//�õ���Ʒ����VO
		}
		
		return map;
		
	}
	
	/**
	 * ������֯�õ�NCϵͳ���н��˷�ʽ��Ϣ
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,JzfsHVO> getAllJzfsByPk_org(String pk_org) throws DAOException{
		HashMap<String,JzfsHVO> map=new HashMap<String, JzfsHVO>();
		String sql="select * from hk_srgk_hg_jzfs where nvl(dr,0)=0 and nvl(dr,0)=0 and pk_org='"+pk_org+"'";
		ArrayList<JzfsHVO> list=(ArrayList<JzfsHVO>)getBaseDAO().executeQuery(sql, new BeanListProcessor(JzfsHVO.class));
		for (JzfsHVO hvo : list) {
			map.put(hvo.getName(), hvo);//�õ���Ʒ����VO
		}
		
		return map;
		
	}
	/**
	 * //�����ʣ�ƽ�����ۣ��ͷ����룬�ɳ��ⷿ����, REVPAR
	 * zhangjc
	 * 2015-8-11����9:28:24
	 * void
	 *  ������    rptitemgroup==null and rpitemname = '������'  ȡ rptincome                          
 	 *	ƽ������  rptitemgroup==null and rpitemname = 'ƽ������'  ȡ rptincome
 	 *	REVPAR  = �ͷ�����/�ɳ��ⷿ����
 	 *	�ͷ�����  rpitemname = '�ͷ�����'  ȡ  sum(rptincome)
 	 *	�ɳ��ⷿ���� select count(code) from room
	 *
	 */
	public HashMap<String,UFDouble[]>  getOtherData(JdbcSession session,Set<String> dates) throws BusinessException{
		try{
//		String sql="select rptdate,rptitemgroup,rptitemname,rptincome,rptkey from AppRpt where rtrim(rptItemName) in( 'ƽ������','������','�ͷ�����') and rptincome >0.00 and rptDate ='2015-07-01' ";
		StringBuffer sb=new StringBuffer();
		sb.append("select convert(varchar(10),rptdate,120) czldate, rptincome czl,'' pjfjdate,'' pjfj,'' kfsrdate,'' kfsr from AppRpt where (rptitemgroup is null or rtrim(rptitemgroup)='') and rtrim(rptItemName) = '������' and rptincome >0.00") 
		  .append("	UNION ALL")
		  .append(" select '' czldate,'' czl,convert(varchar(10),rptdate,120) pjfjdate,rptincome pjfj,'' kfsrdate,'' kfsr from AppRpt where (rptitemgroup is null or rtrim(rptitemgroup)='') and rtrim(rptItemName) = 'ƽ������'  and rptincome >0.00") 
		  .append(" UNION ALL")
		  .append("	select '' czldate, '' czl,'' pjfjdate,'' pjfj, convert(varchar(10),max(rptdate),120) kfsrdate,sum(rptincome) kfsr from AppRpt where rtrim(rptItemName) = '�ͷ�����' and rptincome >0.00   group by rptDate"); 
	String rooms="select count(code) from room";
		
	HashMap<String,UFDouble> czlmap=new HashMap<String, UFDouble>();
	HashMap<String,UFDouble> pjfjmap=new HashMap<String, UFDouble>();
	HashMap<String,UFDouble> kfsrmap=new HashMap<String, UFDouble>();
		Vector<Vector> vector=(Vector<Vector>)session.executeQuery(sb.toString(), new VectorProcessor());
		for (Vector v : vector) {
			UFDouble czl=nullAsZero(v.elementAt(1));
			if(!isZero(czl)){
				czlmap.put(v.elementAt(0).toString(), czl);//������(������)
			}
			UFDouble pjfj=nullAsZero(v.elementAt(3));
			if(!isZero(pjfj)){
				pjfjmap.put(v.elementAt(2).toString(), pjfj);//ƽ������
			}
			
			UFDouble kfsr=nullAsZero(v.elementAt(5));
			if(!isZero(kfsr)){
				kfsrmap.put(v.elementAt(4).toString(), kfsr);//�ͷ�����
			}
		}
		
		Object obj=session.executeQuery(rooms.toString(), new ColumnProcessor());
		UFDouble room=nullAsZero(obj);
		HashMap<String,UFDouble[]> resultMap=new HashMap<String, UFDouble[]>();
		for (String date :dates) {
			resultMap.put(date, new UFDouble[]{nullAsZero(czlmap.get(date)),nullAsZero(pjfjmap.get(date)),nullAsZero(kfsrmap.get(date)),room,isZero(room)?UFDouble.ZERO_DBL:(nullAsZero(nullAsZero(kfsrmap.get(date))).div(room))});//�����ʣ�ƽ�����ۣ��ͷ����룬�ɳ��ⷿ����, REVPAR  (�ͷ�����/�ɳ��ⷿ����)
		}
		return resultMap;
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * zhangjc
	 * 2015-8-6����10:37:30
	 * HashSet<String>
	 *�õ�NC���Ѿ��������������ϸ������Ϣ
	 */
	public HashSet<String> getNCBillsByPk_org(String pk_org) throws DAOException{
		String sql="select distinct substr(dbilldate,0,10) dbilldate from hk_srgk_jd_rzmx where nvl(dr,0)=0 and pk_org='"+pk_org+"'";
		ArrayList<String> datelist=(ArrayList<String>)getBaseDAO().executeQuery(sql, new ColumnListProcessor());
		HashSet<String> set=new HashSet<String>();
		for (String string : datelist) {
			set.add(string);
		}
		return set;
	}
	public UFDouble nullAsZero(UFDouble ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:ufDouble;
	}
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}
	public UFDateTime getCurrentTime() {
		return new UFDateTime(new Date(TimeService.getInstance().getTime()));
	}
}
