package cn.itheima.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.itheima.jdbc.JDBCUtil_V1;
import cn.itheima.jdbc.JDBCUtil_V2;
import cn.itheima.jdbc.JDBCUtil_V3;


/**
 * 测试工具类
 * @author Administrator
 *
 */
public class TestUtils {
	
	/**
	 * 根据id删除信息
	 */
	@Test
	public void testDeleteById(){

		Connection conn=null;
    	PreparedStatement pst=null;
    	try {
    		conn=JDBCUtil_V3.getConnection();
        	String sql="delete from user where uid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, 4);
			int row = pst.executeUpdate();
			if(row>0){
				System.out.println("删除成功");
			}else{
				System.out.println("失败");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil_V2.release(conn, pst, null);
		}
	
	}
	/**
	 * 根据id修改信息
	 */
	@Test
	public void testUpdateById(){

		Connection conn=null;
    	PreparedStatement pst=null;
    	try {
    		conn=JDBCUtil_V3.getConnection();
        	String sql="update user set upassword=? where uid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, "139");
			pst.setInt(2, 1);
			int row = pst.executeUpdate();
			if(row>0){
				System.out.println("修改成功");
			}else{
				System.out.println("失败");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil_V2.release(conn, pst, null);
		}
	
	}
	
	/**
	 * 添加用户信息
	 */
	@Test
	public void testAdd(){
		Connection conn=null;
    	PreparedStatement pst=null;
    	try {
    		conn=JDBCUtil_V2.getConnection();
        	String sql="insert into user values(null,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, "张三");
			pst.setString(2, "2342");
			int row = pst.executeUpdate();
			if(row>0){
				System.out.println("插入成功");
			}else{
				System.out.println("失败");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil_V2.release(conn, pst, null);
		}
	}
	
    /**
     * 根据id查询用户信息
     */
    @Test
    public void testFindUserById(){
    	Connection conn=null;
    	PreparedStatement pst=null;
    	ResultSet rs=null;
    	try {
    		//1获取连接
    		conn = JDBCUtil_V1.getConnection();
    		//2编写sql
        	String sql="select * from user where uid=?";
        	//3预处理
			pst = conn.prepareStatement(sql);
			//4设置参数
			pst.setInt(1, 2);
			//5执行查询操作
			rs = pst.executeQuery();
			//6处理结果集
			while(rs.next()){
				//rs结果集里面的东西？感觉像一个数组[2,"acc","123"],但是获取的时候和数组有点区别从1开始，getInt(n)取整数
				  //getString(n)取任何类型
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2)+"-----"+rs.getString("upassword"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源必须写在finally里面，因为必须要执行
			JDBCUtil_V1.release(conn, pst, rs);
		}
    }
}
