package cn.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 提供获取连接和释放资源的方法
 * @author Administrator
 *
 */
public class JDBCUtil_V2 {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		driver=bundle.getString("driver");
		url=bundle.getString("url");
		username=bundle.getString("username");
		password=bundle.getString("password");	
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
