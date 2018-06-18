package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 执行DML语句
 * @author NiCo
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("欢迎使用");
		System.out.println("请输入用户名：");
		String username=scanner.nextLine();
		System.out.println("请输入密码：");
		String password=scanner.nextLine();
		System.out.println("请输入邮箱：");
		String email=scanner.nextLine();
		System.out.println("请输入昵称：");
		String nickname=scanner.nextLine();
		
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("已连接数据库");
			Statement state=conn.createStatement();
			
			/*
			 * 向userinfo_1表中插入数据
			 * INSERT INTO userinfo_1
			 * (id,username,password,email,nickname,account)
			 * VALUES
			 * (1,'','','','',5000)
			 */
			String sql="INSERT INTO userinfo_1 "+ 
					"(id,username,password,email,nickname,account) "+ 
					"VALUES "+
					"(seq_userinfo_1.NEXTVAL,'"+username+"','"+password+"','"+email+"','"+nickname+"',5000)";
			System.out.println(sql);
			state.execute(sql);
			System.out.println("注册成功");
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
