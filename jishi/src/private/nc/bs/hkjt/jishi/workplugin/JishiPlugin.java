package nc.bs.hkjt.jishi.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.TimeService;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHy_kainfoMaintain;
import nc.itf.hkjt.IJs_shoudanMaintain;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.huiyuan.kadangan.KadanganTempVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanBVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanHVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanTempBVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanTempHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;

public class JishiPlugin implements IBackgroundWorkPlugin {

	HashMap<String,String> MAP_dian_corp = new HashMap<String,String>();	// �� ��Ӧ pk_corp
	HashMap<String,String> MAP_corp_dian = new HashMap<String,String>();	// pk_corp  ��Ӧ  ��
	HashMap<String,String> MAP_dian_flag = new HashMap<String,String>();	// �� ��Ӧ flag
	HashMap<String,String> MAP_corp_flag = new HashMap<String,String>();	// pk_corp  ��Ӧ  flag
	HashMap<String,String> MAP_dian_db   = new HashMap<String,String>();	// ��  ��Ӧ  ���ݿ�
	
	public JishiPlugin()
	{
		MAP_dian_corp.put("����", "0001N510000000001SXV");	// ���ʻ��
		MAP_dian_corp.put("�Ƶ�", "0001N510000000001SY1");	// ������Ƶ�
		MAP_dian_corp.put("������", "0001N510000000001SY3");	// �����ȾƵ� 
		MAP_dian_corp.put("ĵ��", "0001N510000000001SXX");	// ���¥
		MAP_dian_corp.put("��ɽ", "0001N510000000001SY7");	// ��ɽ��Ȫ
		MAP_dian_corp.put("������", "0001N510000000001SY5");	// ��������ɽ
		
		MAP_corp_dian.put("0001N510000000001SXV", "����");
		MAP_corp_dian.put("0001N510000000001SXX", "ĵ��");
		MAP_corp_dian.put("0001N510000000001SY7", "��ɽ");
		MAP_corp_dian.put("0001N510000000001SY1", "�Ƶ�");
		MAP_corp_dian.put("0001N510000000001SY3", "������");
		MAP_corp_dian.put("0001N510000000001SY5", "������");
		
		MAP_dian_flag.put("ĵ��", "-01");
		MAP_dian_flag.put("����", "-02");
		MAP_dian_flag.put("��ɽ", "-06");
		MAP_dian_flag.put("�Ƶ�", "-04");
		MAP_dian_flag.put("������", "-07");
		MAP_dian_flag.put("������", "-08");
		
		MAP_corp_flag.put("0001N510000000001SXX", "-01");
		MAP_corp_flag.put("0001N510000000001SXV", "-02");
		MAP_corp_flag.put("0001N510000000001SY7", "-06");
		MAP_corp_flag.put("0001N510000000001SY1", "-04");
		MAP_corp_flag.put("0001N510000000001SY3", "-07");
		MAP_corp_flag.put("0001N510000000001SY5", "-08");
		
		MAP_dian_db.put("ĵ��",  "L01.jgmd.dbo.");
		MAP_dian_db.put("����",  "L02.jggj.dbo.");
		MAP_dian_db.put("��ɽ",  "L06.jgxs.dbo.");
		MAP_dian_db.put("�Ƶ�",  "L04.lmt.dbo.");
		MAP_dian_db.put("������", "L07.jgllz.dbo.");
		MAP_dian_db.put("������", "L08.jgkfr.dbo.");
	}
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{context.getAlertTypeName()+"_HKJT_jishi"});
		if(!lock){
			throw new BusinessException("�������ڴ�����,�����ظ�������");
		}
		
		try
		{
			
			String[] pk_orgs = context.getPk_orgs();
			UFDate bdate = PuPubVO.getUFDate( context.getKeyMap().get("beginDate") );	// ��ʼ����
			UFDate edate = PuPubVO.getUFDate( context.getKeyMap().get("endDate") );		// ��������
			
			// �������Ϊ�գ� ��Ĭ��Ϊ ��ǰ���ڵ� ǰһ��
			if(bdate==null) bdate = new UFDate().getDateBefore(1);
			if(edate==null) edate = new UFDate().getDateBefore(1);
			
			if(pk_orgs==null || pk_orgs.length<=0)
			{
				pk_orgs = new String[]{
						 "0001N510000000001SXV"	//����
						,"0001N510000000001SXX"	//ĵ��
						,"0001N510000000001SY7"	//��ɽ
						,"0001N510000000001SY1" //�Ƶ�
						,"0001N510000000001SY3" //������
						,"0001N510000000001SY5" //������
				};
			}
			
			this.importShoudan_info(pk_orgs, bdate, edate);
			
			
		}catch(Exception ex)
		{ throw new BusinessException(ex);}
		
		return null;
	}
	
	/**
	 * ȡ �ֵ�����
	 */
	private void importShoudan_info(String[] pk_orgs,UFDate bdate,UFDate edate) throws Exception
	{
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		IJs_shoudanMaintain itf = NCLocator.getInstance().lookup(IJs_shoudanMaintain.class);
		String pk_group = AppContext.getInstance().getPkGroup();
		
		ArrayList<String> list_date = this.getTimeDates(bdate,edate);
		/** 
		 * ��ÿ���ҵ�����ݣ���˫��ѭ��
		 * ��ѭ��  ��  ��
		 * ��ѭ��  ��  ��
		 */
		for(int org_i=0;org_i<pk_orgs.length;org_i++)
		{// ����  �� ѭ������
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
			String flag = MAP_dian_flag.get(dian);
			String db_str = MAP_dian_db.get(dian);	// ����Դ ��ʶ
			
			for (String yw_date : list_date) 
			{// ���� �� ѭ������
				
				// ����ҵ������  ���� ����ʱ�� ����ʼ��������
				String[] yw_time = this.getYwDate(PuPubVO.getUFDate(yw_date), flag);
				
				if( yw_time==null || yw_time.length<2 ) continue;	// �Ҳ��� ҵ��ʱ�� ���˳�
				
				// 1�� ����ʱ��  �� ��ͷ��Ϣ ���ܳ��� ����ʦcode��   �ŵ���ʱ����
				//   ��ʱ �� endTime�� ��Ϊʱ������
				/**
				 * select cr.jishicode,MAX(js.JishiName)
					from Sn_ClockRoom cr
					left join Sn_JiShi js on js.jishicode = cr.jishicode
					where CONVERT(varchar, cr.endtime, 120) between '2016-05-10 00:00:00' and '2016-05-10 23:59:59'
					and cr.type in ('����','����','����','�˵�','����')
					group by cr.jishicode 
				 */
				StringBuffer querySQL_1 = 
					new StringBuffer("select ")
							.append(" cr.jishicode ")
							.append(",MAX(js.JishiName) JishiName ")
							.append(",'"+corp+"' pk_org ")
							.append(",'"+yw_date+"' dbilldate ")
							.append(" from "+db_str+"Sn_ClockRoom cr ")
							.append(" left join "+db_str+"Sn_JiShi js on js.jishicode = cr.jishicode ")
							.append(" where (1=1) ")
							.append(" and cr.type in ('����','����','����','�˵�','����') ")
							.append(" and CONVERT(varchar, cr.endtime, 120) between '"+yw_time[0]+"' and '"+yw_time[1]+"' ")
//							.append(" and cr.jishicode = '1108' ")	// ����
							.append(" group by cr.jishicode ")
				;
				
				ArrayList<ShoudanTempHVO> list_hvo_temp_query = (ArrayList<ShoudanTempHVO>)hkjt_hg_pub_session.executeQuery(querySQL_1.toString(), new BeanListProcessor(ShoudanTempHVO.class));
				insertVOS(list_hvo_temp_query);//�� �ֵ���ͷ ���뵽NC��ʱ��
				
				// 2�� ����ʱ��  �� ���� ��Ϣȡ����    �ŵ���ʱ����
				//   ��ʱ �� endTime�� ��Ϊʱ������
				StringBuffer querySQL_2 = 
					new StringBuffer("select ")
							.append(" cr.jishicode ")	// ��ʦcode
							.append(",cr.handnumber ")
							.append(",cr.handerid ")
							.append(",cr.roomid ")
							.append(",cr.goodsid ")
							.append(",cr.goodsname ")
							.append(",CONVERT(varchar, cr.starttime, 120) starttime ")
							.append(",CONVERT(varchar, cr.endtime, 120) endtime ")
							.append(",cr.remark ")
							.append(",case when cr.type in ('����','����','����') then cr.Number end num_add ")
							.append(",case when cr.type in ('�˵�','����') then cr.Number end num_sub ")
							.append(",cr.type typename ")
							.append(",cr.operatorname ")
							.append(",cr.checkname ")
							.append(",CONVERT(varchar, cr.checktime, 120) checktime ")
							.append(",cr.machinename ")
							.append(",cr.waternum ")
							.append(",cr.mainid ")
							.append(",cl.BillId ")
							.append(",cl.Price ")
							.append(",cl.discountprice ")
							.append(",cl.realmoney ")
							.append(" from "+db_str+"Sn_ClockRoom cr ")
							.append(" left join "+db_str+"Sn_Consumesellog cl on cr.WaterNum = cl.WaterNum ")
							.append(" where (1=1) ")
							.append(" and cr.type in ('����','����','����','�˵�','����') ")
							.append(" and CONVERT(varchar, cr.endtime, 120) between '"+yw_time[0]+"' and '"+yw_time[1]+"' ")
				;
				ArrayList<ShoudanTempBVO> list_bvo_temp_query = (ArrayList<ShoudanTempBVO>)hkjt_hg_pub_session.executeQuery(querySQL_2.toString(), new BeanListProcessor(ShoudanTempBVO.class));
				insertVOS(list_bvo_temp_query);//�� �ֵ����� ���뵽NC��ʱ��
				
				/**
				 * 3�����˳� δ�����  �ֵ���ͷ����
				 */
				StringBuffer querySQL_3 = 
					new StringBuffer("select ")
							.append(" sd_t.jishicode ")
							.append(",sd_t.jishiname ")
							.append(",sd_t.dbilldate ")
							.append(",sd_t.pk_org ")				// ��֯
//							.append(",'"+pk_group+"' pk_group ")	// ����
//							.append(",'HK52' vbilltypecode ")		// ��������
//							.append(",'"+HKJT_PUB.MAKER+"' creator ")			// ������
//							.append(",'"+new UFDateTime()+"' creationtime ")	// ��������
//							.append(",-1 ibillstatus ")		// ����״̬
							.append(" from HK_JISHI_SHOUDAN_TEMP sd_t ")
							.append(" left join HK_JISHI_SHOUDAN sd on ( sd.dr=0 and sd_t.pk_org=sd.pk_org and sd_t.jishicode=sd.jishicode and sd_t.dbilldate=sd.dbilldate ) ")	// ��˾����ʦ������
							.append(" where (1=1) ")
							.append(" and sd.PK_HK_JISHI_SHOUDAN is null ")
				;
//				ArrayList<ShoudanHVO> list_hvo_query = (ArrayList<ShoudanHVO>)this.getBaseDAO().executeQuery(querySQL_3.toString(), new BeanListProcessor(ShoudanHVO.class));
				ArrayList<ShoudanTempHVO> list_hvo_query = (ArrayList<ShoudanTempHVO>)this.getBaseDAO().executeQuery(querySQL_3.toString(), new BeanListProcessor(ShoudanTempHVO.class));
				
				System.out.println("=="+list_hvo_query);
				
				/**
				 * 4�����ݱ�ͷ ѭ�� ���д���
				 */
				for( ShoudanTempHVO tempHVO : list_hvo_query )
				{
					String jishiCode = tempHVO.getJishicode();
					
					StringBuffer querySQL_4 = 
						new StringBuffer("select ")
								.append(" sdb_t.handnumber ")
								.append(",sdb_t.handerid ")
								.append(",sdb_t.roomid ")
								.append(",sdb_t.goodsid ")
								.append(",sdb_t.goodsname ")
								.append(",sdb_t.starttime ")
								.append(",sdb_t.endtime ")
								.append(",sdb_t.remark ")
								.append(",sdb_t.num_add ")
								.append(",sdb_t.num_sub ")
								.append(",sdb_t.typename ")
								.append(",sdb_t.operatorname ")
								.append(",sdb_t.checkname ")
								.append(",sdb_t.checktime ")
								.append(",sdb_t.machinename ")
								.append(",sdb_t.waternum ")
								.append(",sdb_t.mainid ")
								.append(",sdb_t.BillId ")
								.append(",sdb_t.Price ")
								.append(",sdb_t.discountprice ")
								.append(",sdb_t.realmoney ")
								.append(" from HK_JISHI_SHOUDAN_B_TEMP sdb_t ")
								.append(" where (1=1) ")
								.append(" and sdb_t.jishicode = '"+jishiCode+"' ")
					;
					
					ArrayList<ShoudanTempBVO> list_bvo_query = (ArrayList<ShoudanTempBVO>)this.getBaseDAO().executeQuery(querySQL_4.toString(), new BeanListProcessor(ShoudanTempBVO.class));

					ShoudanBillVO billVO = new ShoudanBillVO();
					ShoudanHVO HVO = new ShoudanHVO();
					HVO.setAttributeValue("pk_org" , tempHVO.getPk_org() );
					HVO.setAttributeValue("pk_group" , pk_group );
					HVO.setAttributeValue("jishicode" , tempHVO.getJishicode() );
					HVO.setAttributeValue("jishiname" , tempHVO.getJishiname() );
					HVO.setAttributeValue("dbilldate" , PuPubVO.getUFDate(tempHVO.getDbilldate()) );
					HVO.setAttributeValue("creator" , HKJT_PUB.MAKER );
					HVO.setAttributeValue("creationtime" , new UFDateTime() );
					HVO.setAttributeValue("vbilltypecode" ,"HK52");
					HVO.setAttributeValue("ibillstatus" ,-1);
					billVO.setParentVO(HVO);
					
					ShoudanBVO[] BVOs = new ShoudanBVO[list_bvo_query.size()];
					for( int bvo_i=0;bvo_i<list_bvo_query.size();bvo_i++ )
					{
						BVOs[bvo_i] = new ShoudanBVO();
						BVOs[bvo_i].setAttributeValue("handerid" , list_bvo_query.get(bvo_i).getHanderid() );
						BVOs[bvo_i].setAttributeValue("handnumber" , list_bvo_query.get(bvo_i).getHandnumber() );
						BVOs[bvo_i].setAttributeValue("roomid" , list_bvo_query.get(bvo_i).getRoomid() );
						BVOs[bvo_i].setAttributeValue("goodsid" , list_bvo_query.get(bvo_i).getGoodsid() );
						BVOs[bvo_i].setAttributeValue("goodsname" , list_bvo_query.get(bvo_i).getGoodsname() );
						BVOs[bvo_i].setAttributeValue("starttime" , list_bvo_query.get(bvo_i).getStarttime() );
						BVOs[bvo_i].setAttributeValue("endtime" , list_bvo_query.get(bvo_i).getEndtime() );
						BVOs[bvo_i].setAttributeValue("remark" , list_bvo_query.get(bvo_i).getRemark() );
						BVOs[bvo_i].setAttributeValue("num_add" , list_bvo_query.get(bvo_i).getNum_add() );
						BVOs[bvo_i].setAttributeValue("num_sub" , list_bvo_query.get(bvo_i).getNum_sub() );
						BVOs[bvo_i].setAttributeValue("typename" , list_bvo_query.get(bvo_i).getTypename() );
						BVOs[bvo_i].setAttributeValue("operatorname" , list_bvo_query.get(bvo_i).getOperatorname() );
						BVOs[bvo_i].setAttributeValue("checkname" , list_bvo_query.get(bvo_i).getCheckname() );
						BVOs[bvo_i].setAttributeValue("checktime" , list_bvo_query.get(bvo_i).getChecktime() );
						BVOs[bvo_i].setAttributeValue("machinename" , list_bvo_query.get(bvo_i).getMachinename() );
						BVOs[bvo_i].setAttributeValue("waternum" , list_bvo_query.get(bvo_i).getWaternum() );
						BVOs[bvo_i].setAttributeValue("mainid" , list_bvo_query.get(bvo_i).getMainid() );
						BVOs[bvo_i].setAttributeValue("billid" , list_bvo_query.get(bvo_i).getBillid() );
						BVOs[bvo_i].setAttributeValue("price" , list_bvo_query.get(bvo_i).getPrice() );
						BVOs[bvo_i].setAttributeValue("discountprice" , list_bvo_query.get(bvo_i).getDiscountprice() );
						BVOs[bvo_i].setAttributeValue("realmoney" , list_bvo_query.get(bvo_i).getRealmoney() );
					}
					
					billVO.setChildrenVO( BVOs );
					
					itf.insert(new ShoudanBillVO[]{billVO}, null);
					
				}
				
				// 5��������һ��ѭ����  ��� ��ʱ�� ����
				getBaseDAO().deleteByClause(ShoudanTempBVO.class, " 1=1 ");
				getBaseDAO().deleteByClause(ShoudanTempHVO.class, " 1=1 ");
				
				
//				/**
//				 * test
//				 */
//				StringBuffer query_test = new StringBuffer("select * from HK_JISHI_SHOUDAN_TEMP");
//				ArrayList<ShoudanTempHVO> list_hvo_test = (ArrayList<ShoudanTempHVO>)this.getBaseDAO().executeQuery(query_test.toString(), new BeanListProcessor(ShoudanTempHVO.class));
//				System.out.println("==="+list_hvo_test);
//				/**END*/
			}
		
		}
		
	}
	
	/**
	 * ���ڵĴ���, ���� ��ʼ��������, ���� Ҫÿһ��������б�
	 */
	public ArrayList<String> getTimeDates(Object beginTime,Object endTime){
		UFDate beginDate=null;
		UFDate endDate=null;
		
		UFDateTime CurrentTime = new UFDateTime(new Date(TimeService.getInstance().getTime()));
		
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//��ʼ��������Ϊ��
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(endTime.toString());
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//��ʼ��Ϊ�գ�����Ϊ��
			beginDate=new UFDate(beginTime.toString());
			endDate=CurrentTime.getDate().getDateBefore(1);
		}else{//��ʼ������Ϊ��//��ʼΪ�գ�������Ϊ��
			beginDate=CurrentTime.getDate().getDateBefore(1);
			endDate=beginDate;
		}
		ArrayList<String> datesList=new ArrayList<String>();
		for (int i = 0; i <=UFDate.getDaysBetween(beginDate, endDate); i++) {
			String dateStr=beginDate.getDateAfter(i).toString().substring(0,10);
			if(!datesList.contains(dateStr))
			datesList.add(dateStr);
		}
		
		return datesList;
	}

	/**
	 * ��� ҵ��ʱ���
	 * Ŀǰ��  ���� ������   00:00:00 ��  23:59:59
	 */
	private String[] getYwDate(UFDate date,String flag) throws Exception
	{
		String[] result = new String[2];
		
		result[0] = date.toString().substring(0,10) + " 00:00:00";	// ��ʼʱ��
		result[1] = date.toString().substring(0,10) + " 23:59:59";	// ����ʱ��
		
		return result;
	}
	
	public void insertVOS(ArrayList vos)throws BusinessException{
		getBaseDAO().insertVOList(vos);
	}
	BaseDAO dao=null;
	public BaseDAO getBaseDAO(){
		if(dao==null)
			dao=new BaseDAO();
		return dao;
	}
	
}
