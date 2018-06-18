package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 执行DQL语句
 * @author NiCo
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("已连接数据库");
			Statement state=conn.createStatement();
			String sql="SELECT id,username,password, "+
			"email,nickname,account "+
			"FROM userinfo_1  ";
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);
			/*
			 * ResultSet表示查询的结果集
			 * 遍历结果集的步骤为：
			 * 1.先判断结果集是否有下一条记录
			 * 2.若有，则可以获取该条记录各字段的值，直到所有记录遍历完毕。
			 * 遍历方法：
			 * boolean next() 让指针指向下一条记录并使ResultSet表示该条记录，若返回值为false则没有记录
			 * 一组getXXX(String cloName)，获取字符串类型字段值用getString，获取整数用getInt,获取小数用getDouble等
			 * 
			 */
			while(rs.next()) {
				int id=rs.getInt("id");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String email  =rs.getString("email");
				String nickname=rs.getString("nickname");
				double account=rs.getDouble("account");
//				int id=rs.getInt(1);
//				String username=rs.getString(2);
//				String password=rs.getString(3);
//				String email  =rs.getString(4);
//				String nickname=rs.getString(5);
//				double account=rs.getDouble(6);
				System.out.println(id+","+username+","+password+","+email+","+nickname+","+account);
			}
			System.out.println("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
	}
}
