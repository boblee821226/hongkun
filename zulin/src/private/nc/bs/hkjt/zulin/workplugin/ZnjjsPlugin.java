package nc.bs.hkjt.zulin.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

public class ZnjjsPlugin implements IBackgroundWorkPlugin {

	public static String Plugin_Key = "Plugin_HKJT_znjjs";	// ��̨����ı�ʶ
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{Plugin_Key});
		if(!lock){
			throw new BusinessException("�������ڴ�����,�����ظ�������");
		}
		
		try {
			String[] pk_orgs = context.getPk_orgs();
			UFDate bdate = PuPubVO.getUFDate( context.getKeyMap().get("bdate") );	// ��ʼ����
			UFDate edate = PuPubVO.getUFDate( context.getKeyMap().get("edate") );	// ��������
			
			// �������Ϊ�գ� ��Ĭ��Ϊ ��ǰ����
			if (bdate==null) bdate = new UFDate();
			if (edate==null) edate = new UFDate();
			
			// �������ɽ���㵥
			if (true) {
				this.genJnzjs(pk_orgs, bdate, edate);
			}
			
		} catch (Exception ex) {
			
		}
		
		return null;
	}
	
	/**
	 * ���ڴ������
	 * ��̨�����޷�ִ��
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		String[] pk_orgs = {
			"0001N510000000001SZ3"	// ͼ��
		};
		
		UFDate bdate = PuPubVO.getUFDate("2019-11-01");
		UFDate edate = PuPubVO.getUFDate("2019-11-01");
		
		try
		{
			if (bdate==null) bdate = new UFDate();
			if (edate==null) edate = new UFDate();
			this.genJnzjs(pk_orgs, bdate, edate);
			
		}catch(Exception ex)
		{
			throw new BusinessException(ex);
		}
		
		return null;
	}
	
	/**
	 * ���� ���ɽ���㵥
	 */
	private void genJnzjs(String[] pk_orgs,UFDate bdate,UFDate edate) throws Exception
	{
		BaseDAO dao = new BaseDAO();
		IHk_zulin_znjjsMaintain itf = (IHk_zulin_znjjsMaintain)NCLocator.getInstance().lookup(IHk_zulin_znjjsMaintain.class.getName());
		HashMap<String, String[]> orgInfo = getOrgInfo(pk_orgs, null);
		/** 
		 * ����ÿ���ҵ�����ݣ���˫��ѭ��
		 * ��ѭ��  ��  ��
		 * ��ѭ��  ��  ��
		 */
		for(int org_i = 0; org_i < pk_orgs.length; org_i++)
		{// ����  �� ѭ������
			
			ZnjjsHVO hVO = new ZnjjsHVO();
			ArrayList<ZnjjsBVO> bVoList = new ArrayList();
			
			String pk_org = pk_orgs[org_i];
			
			// ������ �� ѭ��
			UFDate dbilldate = bdate;
			while (dbilldate.compareTo(edate) <= 0) {
				
				String dbilldateStr = dbilldate.toString().substring(0, 10);
				/**
				 * �����жϣ�����֯ �����ڣ��Ƿ���ڵ��ݣ�������ڣ�������
				 */
				{
					StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" vbillcode ")
							.append(" from hk_zulin_znjjs ")
							.append(" where dr = 0 ")
							.append(" and pk_org = '").append(pk_org).append("' ")
							.append(" and substr(dbilldate,1,10) = '").append(dbilldateStr).append("' ")
					;
					ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayProcessor());
					if (list != null && list.size() > 0) {
						dbilldate = dbilldate.getDateAfter(1);	//  ����һ�죬��������
						continue;
					}
				}
				
				UFDouble yq_mny_total = UFDouble.ZERO_DBL;	// ��ͷ-���ɽ�ϼ�
				int addRowIndex = -1;	// ��ǰ����������
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
//								.append(" and ct.vbillcode = '201806151-2-10061-2-10071-3#1' ")	// ����-��ͬ��
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
							
							if(yq_num<=0) {	// �������� <=0 , ��������
								continue;
							}
								
							UFDouble  yq_bl = new UFDouble(5);				// ����(ǧ��֮��)
							UFDouble yq_mny = jf_mny.multiply(yq_bl).multiply(yq_num).div(1000.00)
									.setScale(2, UFDouble.ROUND_HALF_UP);	// ���ɽ�=Ӧ�ɷѽ�� * 5�� * ��������
							
							yq_mny_total = yq_mny_total.add(yq_mny);
							
							addRowIndex++;
							
							ZnjjsBVO bvo = new ZnjjsBVO();
							
							bvo.setPk_cust(pk_cust);
							bvo.setPk_area(pk_area);
							bvo.setPk_room(pk_room);
							bvo.setHt_code(ht_code);
							bvo.setHt_rowno(ht_rowno);
							bvo.setPk_sfxm(pk_sfxm);
							bvo.setJf_date(jf_date);
							bvo.setJf_mny(jf_mny);
							
							bvo.setYq_bl(yq_bl);
							bvo.setYq_num(new UFDouble(yq_num));
							bvo.setYq_mny(yq_mny);
							
							bvo.setHt_id(ht_id);
							bvo.setHt_bid(ht_bid);
							
							bvo.setVbmemo(vbmemo);	// �б�ע
							
							MAP_HT_INFO.put(ht_bid, null);	// ��ӵ������� �� ������ ʹ��
							
							bVoList.add(bvo);	// ��ӵ� ����List
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
//							.append(" and sk.billno = '' ")	// ����-�տ��
//							.append(" and ct.vbillcode = '201806151-2-10061-2-10071-3#1' ")	// ����-��ͬ��
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
							
							addRowIndex++;
							
							ZnjjsBVO bvo = new ZnjjsBVO();
							
							bvo.setPk_cust(pk_cust);
							bvo.setPk_area(pk_area);
							bvo.setPk_room(pk_room);
							bvo.setHt_code(ht_code);
							bvo.setHt_rowno(ht_rowno);
							bvo.setPk_sfxm(pk_sfxm);
							bvo.setJf_date(jf_date);
							bvo.setJf_mny(jf_mny);
							
							bvo.setYq_bl(yq_bl);
							bvo.setYq_num(new UFDouble(yq_num));
							bvo.setYq_mny(yq_mny);
							
							bvo.setHt_id(ht_id);
							bvo.setHt_bid(ht_bid);
							
							bvo.setVbmemo(vbmemo);	// �б�ע
							
							bvo.setVbdef03(skCode);	// �տ��
							bvo.setVbdef04(skBid);	// �տbid
							
							bVoList.add(bvo);	// ��ӵ� ����List
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
								
								addRowIndex++;
								
								ZnjjsBVO bvo = new ZnjjsBVO();
								
								bvo.setPk_cust(pk_cust);
								bvo.setPk_area(pk_area);
								bvo.setPk_room(pk_room);
								bvo.setHt_code(ht_code);
								bvo.setHt_rowno(ht_rowno);
								bvo.setPk_sfxm(pk_sfxm);
								bvo.setJf_date(jf_date);
								bvo.setJf_mny(jf_mny);
								
								bvo.setYq_bl(yq_bl);
								bvo.setYq_num(yq_num);
								bvo.setYq_mny(yq_mny);
								
								bvo.setHt_id(ht_id);
								bvo.setHt_bid(ht_bid);
								
								bvo.setVbmemo(vbmemo);
							
								bvo.setVbdef03(skCode);	// �տ��
								bvo.setVbdef04(skBid);	// �տbid
								
								bVoList.add(bvo);	// ��ӵ� ����List
							}
						}
					}
				}
				
				// ��ͷ��ֵ
				hVO.setYq_mny_total(yq_mny_total);
				hVO.setPk_group(AppContext.getInstance().getPkGroup());
				hVO.setPk_org(pk_org);
				hVO.setPk_org_v(orgInfo.get(pk_org)[0]);
				hVO.setDbilldate(dbilldate);
				hVO.setCreator(HKJT_PUB.MAKER);
				hVO.setCreationtime(new UFDateTime());
				hVO.setIbillstatus(-1);	// ����̬
				
				ZnjjsBVO[] bVOs = new ZnjjsBVO[bVoList.size()];
				bVOs = bVoList.toArray(bVOs);
				ZnjjsBillVO billVO = new ZnjjsBillVO();
				billVO.setParentVO(hVO);
				billVO.setChildrenVO(bVOs);
				
				// ��װbillVO�����ұ������ݡ�
				itf.insert(new ZnjjsBillVO[]{billVO}, null);
				
				dbilldate = dbilldate.getDateAfter(1);	//  ����һ�죬��������
			}
		}
	}
	
	/**
	 * ����pk_orgs ���pk_org_v ����Ϣ
	 * 0: pk_org_v
	 * @throws DAOException 
	 */
	public static HashMap<String,String[]> getOrgInfo(String[] pk_orgs, Object ohter) throws DAOException {
		HashMap<String, String[]> result = new HashMap<String, String[]>();
		
		StringBuffer whereSQL_org = new StringBuffer(" in ('null'");
		for(String pk_org : pk_orgs) {
			whereSQL_org.append(",'").append(pk_org).append("'");
		}
		whereSQL_org.append(") ");
		
		StringBuffer querySQL = 
		new StringBuffer("select distinct ")
				.append(" pk_org, pk_vid ")
				.append(" from org_orgs ")
				.append(" where dr = 0 ")
				.append(" and pk_org ").append(whereSQL_org)
		;
		
		ArrayList list = (ArrayList)new BaseDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				Object[] obj_value = (Object[])obj;
				String pk_org 	= PuPubVO.getString_TrimZeroLenAsNull(obj_value[0]);
				String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(obj_value[1]);
				
				result.put(pk_org, new String[]{pk_org_v});
			}
		}
		
		return result;
	}
}
