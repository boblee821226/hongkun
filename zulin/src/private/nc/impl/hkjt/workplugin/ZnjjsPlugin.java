package nc.impl.hkjt.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class ZnjjsPlugin implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		
		long startTime = System.currentTimeMillis();
		// ��֯Ϊ���䣬���Կ϶���ֵ�������֯���д���
		String[] pk_orgs = bgwc.getPk_orgs();
		// ��������
		UFDate date = PuPubVO.getUFDate(bgwc.getKeyMap().get("date"));
		
		boolean lock = PKLock.getInstance()
				.addBatchDynamicLock(
						new String[]{bgwc.getAlertTypeName() + bgwc.getPk_orgs()[0]});
		
		if(!lock){
			throw new BusinessException("�������ڴ�����,�����ظ�������");
		}
		
		this.doAction(pk_orgs, date, null);
		
		System.out.println("�������,����ʱ��"+(System.currentTimeMillis()-startTime)+"����");
	
		
		PreAlertObject res = new PreAlertObject();
		res.setReturnType(PreAlertReturnType.RETURNNOTHING);
		return res;
	}

	/**
	 * �¼�����
	 */
	private Object doAction(String[] pk_orgs, UFDate dbilldate, HashMap<String, Object> param) throws BusinessException {
		
		if (dbilldate == null) {
			dbilldate = new UFDate();
		}
		
		String dbilldateStr = dbilldate.toString().substring(0, 10);
		
		BaseDAO dao = new BaseDAO();
		
		ArrayList<ZnjjsBillVO> billVOlist = new ArrayList<ZnjjsBillVO>();
		
		/**
		 * ��ѯorg�İ汾
		 */
		HashMap<String, String> ORG_MAP = new HashMap<String, String>();
		{
			StringBuffer orgStr = new StringBuffer(" ('null'");
			for (String pk_org : pk_orgs) {
				orgStr.append(",'").append(pk_org).append("'");
			}
			orgStr.append(") ");
			StringBuffer querySQL = 
				new StringBuffer("select pk_salesorg, pk_vid from org_salesorg ")
						.append(" where pk_salesorg in ").append(orgStr)
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if (list != null && list.size() > 0) {
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String org   = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
					String org_v = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
					ORG_MAP.put(org, org_v);
				}
			}
		}
		/**
		 * ���д���
		 */
		for (String pk_org : pk_orgs) {
			
			// �Ȳ�ѯ��������� ������
			{
				StringBuffer checkSQL = 
				new StringBuffer("select ")
						.append(" pk_hk_zulin_znjjs ")
						.append(" from hk_zulin_znjjs ")
						.append(" where dr = 0 ")
						.append(" and pk_org = '").append(pk_org).append("' ")
						.append(" and substr(dbilldate, 1, 10) = '").append(dbilldateStr).append("' ")
				;
				ArrayList list = (ArrayList)dao.executeQuery(checkSQL.toString(), new ArrayListProcessor());
				if (list == null || list.size() == 0) {
					continue;
				}
			}
			
			ZnjjsBillVO billVO = new ZnjjsBillVO();
			ZnjjsHVO hVO = new ZnjjsHVO();
			ArrayList<ZnjjsBVO> bVOlist = new ArrayList<ZnjjsBVO>();
			
			UFDouble yq_mny_total = UFDouble.ZERO_DBL;	// ��ͷ-���ɽ�ϼ�
			int addRowIndex = -1;	// ��ǰ����������
			// key=htBid , value=null  ���ڿ���ͳ�� ��ͬbid �Ƿ���ڡ�
			HashMap<String,Object> MAP_HT_INFO = new HashMap<String,Object>();
			/**
			 * 1���� ��ͬ��ȡ����ȡ δ�ɷѵ�
			 */
			{
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" ct.pk_customer ")	// 0�ͻ�
							.append(",ct.vdef15 ")		// 1����
							.append(",ct.vdef16 ")		// 2����
							.append(",ct.vbillcode ")	// 3��ͬ��
							.append(",ctb.crowno ")		// 4��ͬ��
							.append(",ctb.vbdef1 ")		// 5�շ���Ŀ
							.append(",substr(ctb.vbdef3,1,10) ")	// 6��ʼ����
							.append(",ctb.norigtaxmny ")	// 7Ӧ�ɽ��
							.append(",ctb.noritotalgpmny ")	// 8ʵ�ɽ��
							.append(",ctb.pk_ct_sale ")		// 9��ͬ����pk
							.append(",ctb.pk_ct_sale_b ")	//10��ͬ�ӱ�pk
							.append(",ct.vdef19 ")			//11���ȷ�Ͻ�����
							.append(",substr(ctb.vbdef4,1,10) ")	//12��������
							.append(",jffs.name ")			//13�ɷѷ�ʽ
							.append(" from ct_sale ct ")
							.append(" inner join ct_sale_b ctb on ct.pk_ct_sale = ctb.pk_ct_sale ")
							.append(" left join bd_defdoc srxm on ctb.vbdef1 = srxm.pk_defdoc ")	// ������Ŀ
							.append(" left join bd_defdoc jffs on ct.vdef7 = jffs.pk_defdoc ")		// �ɷѷ�ʽ
							.append(" where ct.dr = 0 and ctb.dr = 0 ")
							.append(" and ct.blatest = 'Y' ")
							.append(" and ct.pk_org = '"+pk_org+"' ")
							.append(" and substr(ctb.vbdef3,1,10) <= '"+dbilldateStr+"' ")
							.append(" and (nvl(ctb.norigtaxmny,0)-nvl(ctb.noritotalgpmny,0))<>0 ")
							.append(" and srxm.name not like '%Ѻ��%' ")
							.append(" and srxm.name not like '%����%' ")
	//						.append(" and ct.vbillcode = '201806151-2-10061-2-10071-3#1' ")	// ����-��ͬ��
				;
				
				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				
				if(list!=null && list.size()>0)
				{
					for(int row=0;row<list.size();row++)
					{
						Object[] obj = (Object[])list.get(row);
						String	  pk_cust = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
						String 	  pk_area = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
						String 	  pk_room = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
						String 	  ht_code = PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
						String 	 ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
						String 	  pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
						UFDate    jf_date = PuPubVO.getUFDate(obj[6]);	// Ĭ��Ϊ ��ͬ��-��ʼ���� ����ɷѷ�ʽ=�󸶣���ֵΪ ��ͬ��-��������
						UFDouble   yj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
						UFDouble   sj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[8]);
						String 		ht_id = PuPubVO.getString_TrimZeroLenAsNull(obj[9]);
						String     ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[10]);
						UFDate	  zjqrjzr = PuPubVO.getUFDate(obj[11]);	// ���ȷ�Ͻ�������
						UFDouble   jf_mny = yj_mny.sub(sj_mny);			// Ӧ�ɷѽ�� = Ӧ�ɷ��� - ʵ�ɷ���
						UFDate	 end_date = PuPubVO.getUFDate(obj[12]);	// ��ͬ��-��������
						String       jffs = PuPubVO.getString_TrimZeroLenAsNull(obj[13]);	// �ɷѷ�ʽ
						
						if ("��".equals(jffs)) {
							jf_date = end_date;
						}
						
						UFDate jisuanDate = dbilldate;	// �������ڣ�������ȷ�Ͻ������ڣ�С�� ��ǰ���ڣ� �Ǽ�������Ӧ�õ������ȷ�Ͻ������ڣ�
						String vbmemo = null;			// �б�ע����������ȷ�Ͻ�������������ģ����ֵ��б�ע�ϣ�
						if(zjqrjzr!=null && zjqrjzr.compareTo(dbilldate)<0) {
							jisuanDate = zjqrjzr;
							vbmemo = "���ȷ�Ͻ�������"+zjqrjzr.toString().substring(0, 10);
							jisuanDate = jisuanDate.getDateAfter(1);	// ��������ȷ�Ͻ������ڣ���������һ��
						}
						
						Integer yq_num = jisuanDate.getDaysAfter(jf_date);	// �������� (19��9��27�գ�ȷ��Ϊ����һ)
						
						if(yq_num <=0 ) {	// �������� <=0 , ��������
							continue;
						}
							
						UFDouble  yq_bl = new UFDouble(5);				// ����(ǧ��֮��)
						UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
								.setScale(2, UFDouble.ROUND_HALF_UP);	// ���ɽ�=Ӧ�ɷѽ�� * 5�� * ��������
						
						yq_mny_total = yq_mny_total.add(yq_mny);
						
						ZnjjsBVO bVO = new ZnjjsBVO();
						bVO.setPk_cust(pk_cust);
						bVO.setPk_area(pk_area);
						bVO.setPk_room(pk_room);
						bVO.setHt_code(ht_code);
						bVO.setHt_rowno(ht_rowno);
						bVO.setPk_sfxm(pk_sfxm);
						bVO.setJf_date(jf_date);
						bVO.setJf_mny(jf_mny);
						bVO.setYq_bl(yq_bl);
						bVO.setYq_num(PuPubVO.getUFDouble_ValueAsValue(yq_num));
						bVO.setYq_mny(yq_mny);
						bVO.setHt_id(ht_id);
						bVO.setHt_bid(ht_bid);
						bVO.setVbmemo(vbmemo);
						
						bVOlist.add(bVO);
						
						MAP_HT_INFO.put(ht_bid, null);	// ��ӵ������� �� ������ ʹ��
					}
				}
			}
			
			/**
			 * 2��ȡ ���տ���� �����տ�� ���ݣ� ������ ֮ǰû��ȡ���ġ���ȷ��ֻȡһ�Σ���Ҫ�ظ�ȡ��
			 */
			{
				StringBuffer querySQL_2 = 
				new StringBuffer("select ")
						.append(" ct.pk_customer ")		// 0�ͻ�pk
						.append(",ct.vdef15 ")			// 1����pk
						.append(",ct.vdef16 ")			// 2����pk
						.append(",ct.vbillcode ")		// 3��ͬ��
						.append(",ctb.crowno ")			// 4��ͬ�к�
						.append(",skb.def1 ")			// 5������Ŀpk
						.append(",substr(skb.def3,1,10) ")	// 6��������
						.append(",skb.money_cr ")		// 7Ӧ�ɽ��
						.append(",0 ")					// 8ʵ�ɽ��
						.append(",ctb.pk_ct_sale ")		// 9��ͬ��pk
						.append(",ctb.pk_ct_sale_b ")	//10��ͬ��pk
						.append(",substr(skb.busidate,1,10) ")	//11�տ����� Ϊ ���ȷ�Ͻ�������
						.append(",sk.billno ")			//12�տ��
						.append(",skb.pk_gatherbill ")	//13�տbid
						.append(",substr(ctb.vbdef4,1,10) ")	//14��������
						.append(",jffs.name ")					//15�ɷѷ�ʽ
						.append(" from ar_gatherbill sk ")
						.append(" inner join ar_gatheritem skb on sk.pk_gatherbill = skb.pk_gatherbill ")
						.append(" inner join ct_sale_b ctb on skb.src_itemid = ctb.pk_ct_sale_b ")
						.append(" inner join ct_sale ct on ctb.pk_ct_sale = ct.pk_ct_sale ")
						.append(" left join bd_defdoc srxm on skb.def1 = srxm.pk_defdoc ")	// ������Ŀ
						.append(" left join bd_defdoc jffs on ct.vdef7 = jffs.pk_defdoc ")	// �ɷѷ�ʽ
						.append(" left join hk_zulin_znjjs_b jsb on (skb.pk_gatherbill = jsb.vbdef04 and jsb.dr = 0) ")
						.append(" where sk.dr = 0 and skb.dr = 0 ")
						.append(" and skb.src_tradetype = 'Z3-01' ")
						.append(" and srxm.name not like '%Ѻ��%' ")
						.append(" and srxm.name not like '%����%' ")
						.append(" and ct.pk_org = '"+pk_org+"' ")
						.append(" and jsb.pk_hk_zulin_znjjs_b is null ")
	//					.append(" and sk.billno = '' ")	// ����-�տ��
	//					.append(" and ct.vbillcode = '201806151-2-10061-2-10071-3#1' ")	// ����-��ͬ��
				;
				ArrayList list_2 = (ArrayList)dao.executeQuery(querySQL_2.toString(), new ArrayListProcessor());
				
				if(list_2!=null && list_2.size()>0)
				{
					for(int row=0;row<list_2.size();row++)
					{
						Object[] obj = (Object[])list_2.get(row);
						String	  pk_cust = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
						String 	  pk_area = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
						String 	  pk_room = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
						String 	  ht_code = PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
						String 	 ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
						String 	  pk_sfxm = PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
						UFDate    jf_date = PuPubVO.getUFDate(obj[6]);
						UFDouble   yj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
						UFDouble   sj_mny = PuPubVO.getUFDouble_ValueAsValue(obj[8]);
						String 		ht_id = PuPubVO.getString_TrimZeroLenAsNull(obj[9]);
						String     ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[10]);
						UFDate	  zjqrjzr = PuPubVO.getUFDate(obj[11]);	// ���ȷ�Ͻ�������
						UFDouble   jf_mny = yj_mny.sub(sj_mny);	// Ӧ�ɷѽ�� = Ӧ�ɷ��� - ʵ�ɷ���
						String     skCode = PuPubVO.getString_TrimZeroLenAsNull(obj[12]);	// �տ��
						String     skBid = PuPubVO.getString_TrimZeroLenAsNull(obj[13]);	// �տbid
						UFDate	 end_date = PuPubVO.getUFDate(obj[14]);						// ��ͬ��-��������
						String       jffs = PuPubVO.getString_TrimZeroLenAsNull(obj[15]);	// �ɷѷ�ʽ
						
						if ("��".equals(jffs)) {
							jf_date = end_date;
						}
						
						UFDate jisuanDate = dbilldate;	// �������ڣ�������ȷ�Ͻ������ڣ�С�� ��ǰ���ڣ� �Ǽ�������Ӧ�õ������ȷ�Ͻ������ڣ�
						String vbmemo = null;			// �б�ע����������ȷ�Ͻ�������������ģ����ֵ��б�ע�ϣ�
						if(zjqrjzr!=null && zjqrjzr.compareTo(dbilldate)<0) {
							jisuanDate = zjqrjzr;
							vbmemo = "�տ�����"+zjqrjzr.toString().substring(0, 10);
						}
						
						Integer yq_num = jisuanDate.getDaysAfter(jf_date);	// ��������
						
						if(yq_num<=0) {	// �������� <=0 , ��������
							continue;
						}
							
						UFDouble  yq_bl = new UFDouble(5);				// ����(ǧ��֮��)
						UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
								.setScale(2, UFDouble.ROUND_HALF_UP);	// ���ɽ�=Ӧ�ɷѽ�� * 5�� * ��������
						
						yq_mny_total = yq_mny_total.add(yq_mny);
						
						ZnjjsBVO bVO = new ZnjjsBVO();
						bVO.setPk_cust(pk_cust);
						bVO.setPk_area(pk_area);
						bVO.setPk_room(pk_room);
						bVO.setHt_code(ht_code);
						bVO.setHt_rowno(ht_rowno);
						bVO.setPk_sfxm(pk_sfxm);
						bVO.setJf_date(jf_date);
						bVO.setJf_mny(jf_mny);
						bVO.setYq_bl(yq_bl);
						bVO.setYq_num(PuPubVO.getUFDouble_ValueAsValue(yq_num));
						bVO.setYq_mny(yq_mny);
						bVO.setHt_id(ht_id);
						bVO.setHt_bid(ht_bid);
						bVO.setVbmemo(vbmemo);
						bVO.setVbdef03(skCode);
						bVO.setVbdef04(skBid);
						
						bVOlist.add(bVO);		
					}
				}
			}
			
			/**
			 * 3�������ڵ� ���㵥�� ȡ��
			 */
			{
				StringBuffer querySQL_3 = 
				new StringBuffer("select ")
						.append(" jsb.pk_cust ")	// 0
						.append(",jsb.pk_area ")	// 1
						.append(",jsb.pk_room ")	// 2
						.append(",jsb.ht_code ")	// 3
						.append(",jsb.ht_rowno ")	// 4
						.append(",jsb.pk_sfxm ")	// 5
						.append(",jsb.jf_date ")	// 6
						.append(",jsb.jf_mny - to_number(nvl( replace(jsb.vbdef01,'~',''),'0' )) ")		// 7  ���ɽ�-������
						.append(",jsb.yq_bl ")		// 8
						.append(",jsb.yq_num ")		// 9
						.append(",jsb.yq_mny ")		//10
						.append(",jsb.ht_id ")		//11
						.append(",jsb.ht_bid ")		//12
						.append(",jsb.vbmemo ")		//13
						.append(",jsb.vbdef03 ")	//14 �տ��
						.append(",jsb.vbdef04 ")	//15 �տbid
						.append(" from hk_zulin_znjjs js ")
						.append(" inner join ( ")
						.append("	select js.pk_org,max(js.dbilldate) dbilldate ")
						.append("	from hk_zulin_znjjs js ")
						.append("	where dr=0 ")
						.append("	and js.pk_org = '"+pk_org+"' ")
						.append("	and substr(js.dbilldate,1,10) < '"+dbilldateStr+"' ")
						.append("	group by js.pk_org  ")
						.append(" ) js_new on (js.pk_org=js_new.pk_org and js.dbilldate=js_new.dbilldate) ")
						.append(" inner join hk_zulin_znjjs_b jsb on (js.pk_hk_zulin_znjjs = jsb.pk_hk_zulin_znjjs) ")	// ����Ӧ�յ�
						.append(" left join ar_recitem ysb on (jsb.pk_hk_zulin_znjjs_b = ysb.def29 and ysb.dr=0) ")
						.append(" where js.dr=0 and jsb.dr=0 ")
						.append(" and ysb.pk_recitem is null ")	// ֻȡ δ����Ӧ�յ��ģ�����Ӧ�յ�������ζ�� ���ɽ�ģ�� �������
				;
				
				ArrayList list_3 = (ArrayList)dao.executeQuery(querySQL_3.toString(), new ArrayListProcessor());
				
				if(list_3!=null && list_3.size()>0)
				{
					for(int i=0;i<list_3.size();i++)
					{
						Object[] obj = (Object[])list_3.get(i);
						String ht_bid = PuPubVO.getString_TrimZeroLenAsNull(obj[12]);
						String  skBid = PuPubVO.getString_TrimZeroLenAsNull(obj[15]);	// �տbid
						// ����� �տbid��Ϊ�գ����� ���ٱ��ڵĵ�һ��������
						if(skBid!=null || !MAP_HT_INFO.containsKey(ht_bid)) {
							String pk_cust 	= PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
							String pk_area 	= PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
							String pk_room 	= PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
							String ht_code 	= PuPubVO.getString_TrimZeroLenAsNull(obj[3]);
							String ht_rowno = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);
							String pk_sfxm 	= PuPubVO.getString_TrimZeroLenAsNull(obj[5]);
							UFDate jf_date 	= PuPubVO.getUFDate((obj[6]));
							UFDouble jf_mny = PuPubVO.getUFDouble_ValueAsValue(obj[7]);
							UFDouble yq_bl 	= PuPubVO.getUFDouble_ValueAsValue(obj[8]);
							UFDouble yq_num = PuPubVO.getUFDouble_ValueAsValue(obj[9]);
							UFDouble yq_mny = PuPubVO.getUFDouble_ValueAsValue(obj[10]);
							String ht_id 	= PuPubVO.getString_TrimZeroLenAsNull(obj[11]);
							String vbmemo 	= PuPubVO.getString_TrimZeroLenAsNull(obj[13]);
							String   skCode = PuPubVO.getString_TrimZeroLenAsNull(obj[14]);	// �տ��
							
							if(skBid==null) {	// ֻ�в��� �����տ�ģ��Ž��� ��ע�Ĵ���
								if(vbmemo==null){
									vbmemo = "";
								}
								vbmemo += "��������"+dbilldateStr+"�������ѽ��壬ֻǷ���ɽ�";
							}
							
							yq_mny_total = yq_mny_total.add(yq_mny);
							
							ZnjjsBVO bVO = new ZnjjsBVO();
							bVO.setPk_cust(pk_cust);
							bVO.setPk_area(pk_area);
							bVO.setPk_room(pk_room);
							bVO.setHt_code(ht_code);
							bVO.setHt_rowno(ht_rowno);
							bVO.setPk_sfxm(pk_sfxm);
							bVO.setJf_date(jf_date);
							bVO.setJf_mny(jf_mny);
							bVO.setYq_bl(yq_bl);
							bVO.setYq_num(PuPubVO.getUFDouble_ValueAsValue(yq_num));
							bVO.setYq_mny(yq_mny);
							bVO.setHt_id(ht_id);
							bVO.setHt_bid(ht_bid);
							bVO.setVbmemo(vbmemo);
							bVO.setVbdef03(skCode);
							bVO.setVbdef04(skBid);
							
							bVOlist.add(bVO);
						}
					}
				}
			}
			
			// ��ͷ��ֵ
			hVO.setPk_group("0001N510000000000EGY");
			hVO.setPk_org(pk_org);
			hVO.setPk_org_v(ORG_MAP.get(pk_org));
			hVO.setVbilltypecode("HK41");
			hVO.setIbillstatus(-1);
			hVO.setDbilldate(dbilldate);
			hVO.setYq_mny_total(yq_mny_total);
			// ���崦��
			ZnjjsBVO[] bVOs = bVOlist.toArray(new ZnjjsBVO[0]);
			for (int i = 0; i < bVOs.length; i++) {
				bVOs[0].setVrowno("" + ((i+1)*10));
			}
			// �ۺ�VO
			billVO.setParentVO(hVO);
			billVO.setChildrenVO(bVOs);
			billVOlist.add(billVO);
		}
		// ���б���
		if (billVOlist.size() > 0) {
			IHk_zulin_znjjsMaintain itf = NCLocator.getInstance().lookup(IHk_zulin_znjjsMaintain.class);
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//�����Ƶ���
			ZnjjsBillVO[] billVOs = billVOlist.toArray(new ZnjjsBillVO[0]);
			ZnjjsBillVO[] result = itf.insert(billVOs, null);
		}
		
		return null;
	}
}
