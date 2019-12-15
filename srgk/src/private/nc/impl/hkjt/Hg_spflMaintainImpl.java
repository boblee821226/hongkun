package nc.impl.hkjt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pub.ace.AceHg_spflPubServiceImpl;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.srgk.huiguan.othersystem.SpflVO;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;

public class Hg_spflMaintainImpl extends AceHg_spflPubServiceImpl implements nc.itf.hkjt.IHg_spflMaintain {

      @Override
    public void delete(SpflHVO vos) throws BusinessException {
        super.deletetreeinfo(vos);
    }
  
      @Override
    public SpflHVO insert(SpflHVO vos) throws BusinessException {
        return super.inserttreeinfo(vos);
    }
    
      @Override
    public SpflHVO update(SpflHVO vos) throws BusinessException {
    	//后台设置修改时间
    	  vos.setModifiedtime(new UFDateTime());
        return super.updatetreeinfo(vos);    
    }
  
      @Override
    public SpflHVO[] query(String whereSql)
        throws BusinessException {
        return super.querytreeinfo(whereSql);
    }

	/* 
	 *同步商品分类VO
	 * @see nc.itf.hkjt.IHg_spflMaintain#tongBuSpfl(java.lang.String)
	 */
	@Override
	public void tongBuSpfl(String pk_org) throws BusinessException {
		HashMap<String,String> infoMap=getDefaultInfo(pk_org);//得到配置表信息
		Connection conn=null;
		JdbcSession session =null;
try {
	conn=new JDBCUtils(infoMap.get("db_name")).getConn(JDBCUtils.HKJT_HG);
	session = new JdbcSession(conn);
	execute(pk_org, session);
	execute(pk_org, session);
//	executeByTemTable(pk_org, session);
	
} catch (DbException e) {
	e.printStackTrace();
	throw new BusinessException(e.getMessage());
}finally{
	try {
		if(session==null){
			throw new BusinessException("SqlServer数据库访问异常,请检查配置表信息！");
		}
	session.closeAll();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new BusinessException(e.getMessage());
	}
}
	
	}

	private void execute(String pk_org, JdbcSession session)
			throws DbException, DAOException {
		String sql="select a.catalogid,a.parentid,b.nodename  parentnodename,a.nodename  from Dt_GoodCatalog a inner join Dt_GoodCatalog b on a.parentid=b.catalogid";
		ArrayList<SpflVO> list=(ArrayList<SpflVO>)session.executeQuery(sql, new BeanListProcessor(SpflVO.class));
		for (SpflVO spflVO : list) {
			if(!spflVO.getCatalogid().equals("0"))
			spflmaps.put(spflVO.getCatalogid(), spflVO);
		}
		
		String orclsql="select a.code,a.name,a.pk_parent,a.pk_hk_srgk_hg_spfl,a.pk_org from hk_srgk_hg_spfl a where nvl(a.dr,0)=0  and a.pk_org='"+pk_org+"'";
		ArrayList<SpflHVO> spflvo=(ArrayList<SpflHVO>)getBaseDAO().executeQuery(orclsql, new BeanListProcessor(SpflHVO.class));
		HashMap<String,SpflHVO> map0=new HashMap<String, SpflHVO>();//code,value
		HashMap<String,SpflHVO> map1=new HashMap<String, SpflHVO>();//code,value
		HashMap<String,SpflHVO> map2=new HashMap<String, SpflHVO>();//code+name+parentname,value
		for (SpflHVO spflHVO : spflvo) {
			map0.put(spflHVO.getPk_hk_srgk_hg_spfl(), spflHVO);
			map1.put(spflHVO.getCode(), spflHVO);
		}
		for (SpflHVO spflHVO : spflvo) {
			map2.put(spflHVO.getCode()+spflHVO.getName()+(map0.get(spflHVO.getPk_parent())==null?null:map0.get(spflHVO.getPk_parent()).getName()), spflHVO);
		}
		
		ArrayList<SpflHVO> addSpflVOs=new ArrayList<SpflHVO>();
		ArrayList<SpflHVO> updateSpflVOs=new ArrayList<SpflHVO>();
		for (SpflVO serverVO : list) {
			if(map1.containsKey(serverVO.getCatalogid())){//包含
				if(!map2.containsKey(serverVO.getCatalogid()+serverVO.getNodename()+serverVO.getParentnodename())){//有变动的vo
					SpflHVO oldvo=map1.get(serverVO.getCatalogid());
					oldvo.setName(serverVO.getNodename());
					if(map1.get(serverVO.getParentid())!=null)
					oldvo.setPk_parent(map1.get(serverVO.getParentid()).getPk_hk_srgk_hg_spfl());//父层id去找NC里对应的pk
					
					updateSpflVOs.add(oldvo);
				}
			}else{//新增的
				
				if(map1.containsKey(serverVO.getParentid())){//如果存在父级，则直接增加
					SpflHVO hvo=new SpflHVO();
					hvo.setCode(serverVO.getCatalogid());
					hvo.setName(serverVO.getNodename());
					hvo.setPrimaryKey(new SequenceGenerator().generate());
					hvo.setPk_parent(map1.get(serverVO.getParentid()).getPk_hk_srgk_hg_spfl());
					hvo.setPk_org(pk_org);
					hvo.setPk_group(AppContext.getInstance().getPkGroup());
					hvo.setCreator(AppContext.getInstance().getPkUser());
					hvo.setCreationtime(AppContext.getInstance().getServerTime());
					hvo.setAttributeValue("dr",0);
					addSpflVOs.add(hvo);
//				getBaseDAO().insertVO(hvo);
					map1.put(hvo.getCode(), hvo);
					map2.put(hvo.getCode()+hvo.getName()+map1.get(serverVO.getParentid()).getName(), hvo);
				}else{//先增加父级
					String pk =new SequenceGenerator().generate();	
					setParentValue(map1,map2,serverVO,pk_org,pk,addSpflVOs);
				}
			}
		}
		
		getBaseDAO().insertVOArrayWithPK(addSpflVOs.toArray(new SpflHVO[]{}));
		getBaseDAO().updateVOArray(updateSpflVOs.toArray(new SpflHVO[]{}), new String[]{SpflHVO.NAME,SpflHVO.PK_PARENT});//更新变动的VO
	}
	
	private void executeByTemTable(String pk_org, JdbcSession session)
			throws DbException, DAOException {
//		"select b.*, level  from hk_srgk_hg_spfl b\n" + 
//				" --start with orgcode = '50000000'\n" + 
//				" --and parentorg = '00000000'\n" + 
//				"connect by prior b.pk_hk_srgk_hg_spfl = b.pk_parent";

		String sql="select a.catalogid code,a.parentid pk_parent,a.nodename name,2 dr,'temp' pk_org  from Dt_GoodCatalog a";
		ArrayList<SpflHVO> list=(ArrayList<SpflHVO>)session.executeQuery(sql, new BeanListProcessor(SpflHVO.class));
		String pks[]=getBaseDAO().insertVOArray(list.toArray(new SpflHVO[]{}));
		
		
		String treesql="select b.code,b.pk_parent,b.name,level from hk_srgk_hg_spfl b where b.pk_org='temp' and dr=2  start with code ='00' connect by prior b.code = b.pk_parent";
		
		System.out.println();
		getBaseDAO().deleteByPKs(SpflHVO.class, pks);
	}
	
	HashMap<String,SpflVO> spflmaps=new HashMap<String,SpflVO>();//code,vo
	public void setParentValue(HashMap<String,SpflHVO> map1,HashMap<String,SpflHVO> map2,SpflVO serverVO,String pk_org,String pk,ArrayList<SpflHVO> addSpflVOs) throws DAOException{
			String parentPk =new SequenceGenerator().generate();	
			SpflHVO hvo=new SpflHVO();
				hvo.setCode(serverVO.getCatalogid());
				hvo.setName(serverVO.getNodename());
				hvo.setPrimaryKey(pk);
				hvo.setPk_parent(parentPk);
				hvo.setPk_org(pk_org);
				hvo.setPk_group(AppContext.getInstance().getPkGroup());
				hvo.setCreator(AppContext.getInstance().getPkUser());
				hvo.setCreationtime(AppContext.getInstance().getServerTime());
				hvo.setAttributeValue("dr",0);
//				getBaseDAO().insertVOWithPK(hvo);
				addSpflVOs.add(hvo);
				map1.put(hvo.getCode(), hvo);
				if(spflmaps.get(serverVO.getParentid())!=null){//如果有父级
					map2.put(hvo.getCode()+hvo.getName()+serverVO.getParentnodename(), hvo);
			SpflVO parentVo=spflmaps.get(serverVO.getParentid());
			setParentValue(map1,map2,parentVo,pk_org,parentPk,addSpflVOs);
			}
		
	}

	/**
	 * 根据组织查询出默认系统信息
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
	
}
