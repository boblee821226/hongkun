package nc.pub.hkjt.srgk.tools.sqlserver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import nc.vo.pub.BusinessException;

/**
 * JDBC工具类
 * */
public class JDBCUtils {

	public static final String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SYBASE_DRIVER = "com.sybase.jdbc3.jdbc.SybDriver";
	public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	// 会馆公共库名称
	public static String HKJT_HG_URL = "127.0.0.1";
	public static String HKJT_HG_HOST = "1433";
	public static String HKJT_HG_DBNAME = "qcbdb";
	public static String HKJT_HG_USER = "sa";
	public static String HKJT_HG_PASSWORD = "ufsoft";

	public static String HKJT_HG = "HKJT_HG";// 会馆（三个会馆 同一个链接方式）
	public static String HKJT_JD_KFRXS = "HKJT_JD_KFRXS";// 酒店-康福瑞西山店
	
	public static String HKJT_LY = "HKJT_LY";	// 绿云（MySQL）

	String Prefix = null;
	
	public JDBCUtils(String prefix) throws BusinessException {
		try {
			this.Prefix = prefix;
			getDBConfig(prefix);
		} catch (IOException e) {
			throw new BusinessException("读取配置文件出错:" + e.toString());
		}
	}

	public Connection getConn(String systype) throws BusinessException {
		Connection conn = null;
		
		if( "hkjt_hg_kfrjd".equals(this.Prefix) )	// 会馆-康福瑞酒店 ( 连接sql server 2000 )
		{
			try {
				// String dbUrl = "jdbc:jtds:sqlserver://IP地址:1433/数据库名"
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
						+ HKJT_HG_URL + ":" + HKJT_HG_HOST + "/"
						+ HKJT_HG_DBNAME
						, HKJT_HG_USER, HKJT_HG_PASSWORD);
			} catch (ClassNotFoundException e) {
				throw new BusinessException(e.toString());
			} catch (SQLException e) {
				throw new BusinessException(e.toString());
			}
		}
		else if (HKJT_HG.equals(systype)) {// 宏昆集团——会馆
			try {
				Class.forName(SQLSERVER_DRIVER);
				conn = DriverManager.getConnection("jdbc:sqlserver://"
						+ HKJT_HG_URL + ":" + HKJT_HG_HOST + ";databaseName="
						+ HKJT_HG_DBNAME, HKJT_HG_USER, HKJT_HG_PASSWORD);
			} catch (ClassNotFoundException e) {
				throw new BusinessException(e.toString());
			} catch (SQLException e) {
				throw new BusinessException(e.toString());
			}
		}
		else if(HKJT_JD_KFRXS.equals(systype))
		 {	
			 try {
				 DriverManager.registerDriver( (Driver)Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance() );
				 conn = DriverManager.getConnection("jdbc:sybase:Tds:"
							+ HKJT_HG_URL + ":" + HKJT_HG_HOST + "/"
							+ HKJT_HG_DBNAME+"?user="+HKJT_HG_USER+"&password="+HKJT_HG_PASSWORD+"&charset=cp850");
			 }catch (ClassNotFoundException e) {
					throw new BusinessException(e.toString());
				} catch (SQLException e) {
					throw new BusinessException(e.toString());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		 }
		else if(HKJT_LY.equals(systype)) { // MySQL
			try {
//				Class.forName(MYSQL_DRIVER);
				DriverManager.registerDriver(
					(Driver)Class.forName("com.mysql.jdbc.Driver").newInstance()
				);
				conn = DriverManager.getConnection(
						"jdbc:mysql://" + 
						HKJT_HG_URL + ":" + HKJT_HG_HOST + "/" + HKJT_HG_DBNAME +
						"?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai"
						, HKJT_HG_USER
						, HKJT_HG_PASSWORD
				);
			} catch (Exception e) {
				throw new BusinessException(e.toString());
			}
		}
		return conn;
	}

	/**
	 * 针对U8数据库的连接
	 * 
	 * @throws BusinessException
	 * */
	public Connection getU8Conn()
			throws BusinessException {
		Connection conn = null;
		try {
			Class.forName(SQLSERVER_DRIVER);
			conn = DriverManager.getConnection("jdbc:sqlserver://"
					+ HKJT_HG_URL + ":" + HKJT_HG_HOST + ";databaseName="
					+ HKJT_HG_DBNAME, HKJT_HG_USER, HKJT_HG_PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new BusinessException(e.toString());
		} catch (SQLException e) {
			throw new BusinessException(e.toString());
		}
		return conn;
	}

	public void getDBConfig(String prefix) throws IOException {
		InputStream inStream = JDBCUtils.class
				.getClassLoader()
				.getResourceAsStream(
						"nc/pub/hkjt/srgk/tools/sqlserver/dbconfiginfo.properties");
		Properties p = new Properties();
		p.load(inStream);

		HKJT_HG_URL = p.getProperty(prefix + "_url");
		HKJT_HG_HOST = p.getProperty(prefix + "_host");
		HKJT_HG_DBNAME = p.getProperty(prefix + "_dbname");
		HKJT_HG_USER = p.getProperty(prefix + "_user");
		HKJT_HG_PASSWORD = p.getProperty(prefix + "_password");
	}

	public static void closeConn(Connection conn) throws BusinessException {
		if (conn != null) {
			try {
				if(!conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				throw new BusinessException(e.toString());
			}
		}
	}

	public void closeStat(Statement stat) throws BusinessException {
		if (null != stat) {
			try {
				stat.close();
			} catch (SQLException e) {
				throw new BusinessException(e.toString());
			}
		}
	}

	public void rollBack(Connection conn) throws BusinessException {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new BusinessException(e.toString());
			}
		}
	}
}
