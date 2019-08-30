package nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action;

import hd.vo.pub.tools.PuPubVO;
import nc.itf.hkjt.HKJT_PUB;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class AceHg_sgshujuBeforeSaveAction extends ReWriteBaseAction{
	@Override
	public void execute(SgshujuBillVO aggvo) throws BusinessException {
		String dbilldate=aggvo.getParentVO().getDbilldate().toString().substring(0,10);
		SgshujuHVO[]hvo=(SgshujuHVO[])getSuperDMO().queryByWhereClause(
				SgshujuHVO .class, 
				" nvl(dr,0)=0 and substr(dbilldate,0,10)='"+dbilldate+"' " +
				" and pk_org='"+aggvo.getParentVO().getPk_org()+"' " +
				" and pk_hk_srgk_hg_sgshuju <>'"+aggvo.getParentVO().getPk_hk_srgk_hg_sgshuju()+"' " +
				" and nvl(replace(vdef10,'~',''),'N') = '"+aggvo.getParentVO().getVdef10()+"' "		// HK 2018��11��6��14:42:09 ���Ƿ�Ƶ� ȥ����
		);
		if(hvo!=null&&hvo.length>0){
			throw new BusinessException("ҵ������["+dbilldate+"]�ĵ����Ѵ��ڣ��������ظ����棡");
		}
		
		SgshujuBVO[] bvos=(SgshujuBVO[])aggvo.getChildrenVO();
		String err="";
		for (SgshujuBVO sgshujuBVO : bvos) {
			boolean group1=(sgshujuBVO.getTz_km_jzfs_1()!=null||sgshujuBVO.getTz_km_srxm_1()!=null)&&sgshujuBVO.getTz_km_data_1()==null;
			boolean group2=(sgshujuBVO.getTz_km_jzfs_2()!=null||sgshujuBVO.getTz_km_srxm_2()!=null)&&sgshujuBVO.getTz_km_data_2()==null;
			if(group1||group2){
				err+="�к�["+sgshujuBVO.getVrowno()+"]���� ����-"+(group1?" A ":"")+(group2?" B  ":"")+"���С����ݡ��ֶβ���Ϊ�գ�\r\n";
			}
		}
		
		SgshujuHVO hVO = aggvo.getParentVO();
		
//		/**
//		 * HK 2019��6��6��16:12:31
//		 * 1����� ������Ŀ ��ֵ���� ���� �� ���� �Ǳ��
//		 * 2����� �ǾƵ�  ���� ���� ���룬 ��ݵĻ� ����ν��
//		 */
//		{
//			String pk_org = hVO.getPk_org();	// ��֯
//			String vdef10 = hVO.getVdef10();	// �Զ�����-�Ƿ�Ƶ�
//			boolean isJD = false;				// �Ƿ�Ƶ�
//			if("Y".equals(vdef10)&&HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org)){
//				isJD = true;
//			}else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)) {
//				
//			}else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)) {
//				isJD = true;
//			}
//			
//			for (SgshujuBVO bVO : bvos) {
//				
//				String 		 vrowno = bVO.getVrowno();	// �к�
//				String 		  bm_pk = PuPubVO.getString_TrimZeroLenAsNull( bVO.getBm_pk() );		// ����
//				String tz_km_srxm_1 = PuPubVO.getString_TrimZeroLenAsNull( bVO.getTz_km_srxm_1() );	// ������Ŀ1
//				String tz_km_srxm_2 = PuPubVO.getString_TrimZeroLenAsNull( bVO.getTz_km_srxm_2() );	// ������Ŀ2
//				String tz_km_srxm_type1 = PuPubVO.getString_TrimZeroLenAsNull( bVO.getTz_km_srxm_type1() );	// ����1
//				String tz_km_srxm_type2 = PuPubVO.getString_TrimZeroLenAsNull( bVO.getTz_km_srxm_type1() );	// ����2
//				
//				if( 
//				   (tz_km_srxm_1!=null || tz_km_srxm_2!=null) 
//				&& (bm_pk==null)
//				){
//					err+="�к�["+vrowno+"]����������Ŀ�����ű��\r\n";
//				}
//				
//				if( 
//				   (tz_km_srxm_1!=null && tz_km_srxm_type1==null)
//				|| (tz_km_srxm_2!=null && tz_km_srxm_type2==null)
//				){
//					err+="�к�["+vrowno+"]����������Ŀ�������ͱ��\r\n";
//				}
//				
//				if(
//				   ( 
//					 (tz_km_srxm_type1!=null && !tz_km_srxm_type1.equals("sr"))
//				     ||
//				     (tz_km_srxm_type2!=null && !tz_km_srxm_type2.equals("sr"))
//				   )
//				&& (isJD)
//				){
//					err+="�к�["+vrowno+"]�Ƶ�����ͣ�ֻ����(����)��\r\n";
//				}
//			}
//		}
		
		/**
		 * 2019��8��2��18:02:52
		 * ����У��
		 * 1���Ƶ�ͻ�ݣ������������ֹ�����A�л���B�е������˷�ʽ�������ݲ�Ϊ���㡱ʱ�����Ͷ�����ѡ������������{���˷�ʽ}�����ɱ����ֹ����ݣ������޷����档
		 */
		{
			
			String pk_org = hVO.getPk_org();	// ��֯
			String vdef10 = hVO.getVdef10();	// �Զ�����-�Ƿ�Ƶ�
			boolean isJD = false;				// �Ƿ�Ƶ�
			if("Y".equals(vdef10)&&HKJT_PUB.PK_ORG_HUIGUAN_JIUDIAN_MAP.containsValue(pk_org)){
				isJD = true;
			}else if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)) {
				
			}else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)) {
				isJD = true;
			}
			
			for( int j=0;j<bvos.length;j++ )
			{
				SgshujuBVO bvo = bvos[j];
				
				String rowno = bvo.getVrowno();
				
				String jzfsA 	= bvo.getTz_km_jzfs_1();		// ���˷�ʽA
				String srxmA 	= bvo.getTz_km_srxm_1();		// ������ĿA
				UFDouble dataA 	= PuPubVO.getUFDouble_ZeroAsNull(bvo.getTz_km_data_1());		// ����A
				String typeA 	= bvo.getTz_km_srxm_type1();	// ����A
//				String infoA 	= PuPubVO.getString_TrimZeroLenAsNull( bvo.getTz_km_info_1() );	// ��ϸA
				
				String jzfsB 	= bvo.getTz_km_jzfs_2();		// ���˷�ʽB
				String srxmB 	= bvo.getTz_km_srxm_2();		// ������ĿB
				UFDouble dataB 	= PuPubVO.getUFDouble_ZeroAsNull(bvo.getTz_km_data_2());		// ����B
				String typeB 	= bvo.getTz_km_srxm_type2();	// ����B
//				String infoB 	= PuPubVO.getString_TrimZeroLenAsNull( bvo.getTz_km_info_2() );	// ��ϸB
				
				String bm_pk	= bvo.getBm_pk();	// ����
				
				/**
				 * ������ݲ�Ϊ��
				 * ���˷�ʽ �� ������Ŀ ����ͬʱ���ڣ�Ҳ����ͬʱΪ��
				 */
				if(dataA!=null)
				{
					if(jzfsA!=null && srxmA!=null)
					{
						throw new BusinessException("��"+rowno+"���У�{���˷�ʽA}��{������ĿA}����ͬʱ¼�롣");
					}
					if(jzfsA==null && srxmA==null)
					{
						throw new BusinessException("��"+rowno+"���У�{���˷�ʽA}��{������ĿA}������дһ�֡�");
					}
				}
				if(dataB!=null)
				{
					if(jzfsB!=null && srxmB!=null)
					{
						throw new BusinessException("��"+rowno+"���У�{���˷�ʽB}��{������ĿB}����ͬʱ¼�롣");
					}
					if(jzfsB==null && srxmB==null)
					{
						throw new BusinessException("��"+rowno+"���У�{���˷�ʽB}��{������ĿB}������дһ�֡�");
					}
				}
				
				/**
				 *  ���˷�ʽ ¼���˵� ��Ҫ�ж�
				 *  ���ݱ����У�����=���˷�ʽ
				 */
				if(jzfsA!=null)
				{
					if(dataA==null){
						throw new BusinessException("��"+rowno+"���У�{���˷�ʽA}�Ѿ�¼���ˣ����Ա���¼��{����A}��");
					}
					else if(!"jzfs".equals(typeA)){
						throw new BusinessException("��"+rowno+"���У�{���˷�ʽA}�Ѿ�¼���ˣ�����{����A}����Ϊ[���˷�ʽ]��");
					}
				}
				if(jzfsB!=null)
				{
					if(dataB==null){
						throw new BusinessException("��"+rowno+"���У�{���˷�ʽB}�Ѿ�¼���ˣ����Ա���¼��{����B}��");
					}
					else if(!"jzfs".equals(typeB)){
						throw new BusinessException("��"+rowno+"���У�{���˷�ʽB}�Ѿ�¼���ˣ�����{����B}����Ϊ[���˷�ʽ]��");
					}
				}
				
				/**
				 * ����ǾƵ꣬���� ������Ŀ�ǿգ�
				 * �� ����=����
				 */
				if(isJD)
				{
					if(srxmA!=null){
						if(!"sr".equals(typeA)){
							throw new BusinessException("��"+rowno+"���У����Ƶ꡿��{������ĿA}�Ѿ�¼���ˣ�����{����A}����Ϊ[����]��");
						}
					}
					if(srxmB!=null){
						if(!"sr".equals(typeB)){
							throw new BusinessException("��"+rowno+"���У����Ƶ꡿��{������ĿB}�Ѿ�¼���ˣ�����{����B}����Ϊ[����]��");
						}
					}
					
					if(srxmA!=null && "sr".equals(typeA)){
						if(bm_pk==null){
							throw new BusinessException("��"+rowno+"���У����Ƶ꡿��A�е���{����}������¼��{����}��");
						}
					}
				}
				
				/**
				 * ����ǻ�ݣ����� ��A�����������룬
				 * �� B ֻ�����Զ��Żݣ����Ҳ��ű��䡣
				 */
				if(!isJD)
				{
					if(srxmA!=null && "sr".equals(typeA)){
						if(bm_pk==null){
							throw new BusinessException("��"+rowno+"���У�����ݡ���A�е���{����}������¼��{����}��");
						}
						if(srxmB==null){
							throw new BusinessException("��"+rowno+"���У�����ݡ���A�е���{����}��B�б�����{������Ŀ}��");
						}
						if(!"zdyh".equals(typeB)){
							throw new BusinessException("��"+rowno+"���У�����ݡ���A�е���{����}��B�б����ǵ���{�Զ��Ż�} ��");
						}
					}
					
					if(srxmA!=null) {
						if(
							!"sr".equals(typeA)
						 && !"zdyh".equals(typeA)
						) {
							throw new BusinessException("��"+rowno+"���У�����ݡ���{������ĿA}�Ѿ�¼���ˣ�A�б����ǵ���{���롢�Զ��Ż�} ��");
						}
					}
					
					if(srxmB!=null) {
						if(
							!"sr".equals(typeB)
						 && !"zdyh".equals(typeB)
						) {
							throw new BusinessException("��"+rowno+"���У�����ݡ���{������ĿB}�Ѿ�¼���ˣ�B�б����ǵ���{���롢�Զ��Ż�} ��");
						}
					}
				}
			}
		}
		/***END***/
		
		if(err.length()>0){
			throw new BusinessException(err);
		}
	}

}
