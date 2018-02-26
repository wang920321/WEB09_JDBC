package cn.itheima.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 提供获取连接和释放资源的方法
 * @author Administrator
 *
 */
public class JDBCUtil_V3 {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static{
	
		try {
			//通过当前类获取类加载器
			ClassLoader classLoader = JDBCUtil_V3.class.getClassLoader();
			//通过类加载器的方法获得一个输入流
			InputStream is = classLoader.getResourceAsStream("db.properties");
			//3创建一个properties对象
			Properties props=new Properties();
			//4加载输入流
			props.load(is);
			//5获取相关参数的值
			driver=props.getProperty("driver");
			url=props.getProperty("url");
			username=props.getProperty("username");
			password=props.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    /**
     * 获取连接方法
     * @return
     */
    public static Connection getConnection(){
    	Connection conn=null;
    	try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username , password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return conn;
    	
    }
    
    /**
     * 关闭资源
     * @param conn
     * @param pst
     * @param rs
     */
    public static void release(Connection conn,PreparedStatement pst,ResultSet rs){
    	if(rs!=null){
    		try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(pst!=null){
    		try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(conn!=null){
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
