package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

public class JDBCDemo_login {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("欢迎登陆");
		System.out.println("请输入用户名：");
		String username=scan.nextLine();
		System.out.println("请输入密码：");
		String password=scan.nextLine();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account "+
			           "FROM userinfo_1 "+
			           "WHERE username=? AND password=? ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			/*
			 * 由于使用ps不会将动态信息拼接到SQL语句中
			 * 这样就不会出现由于拼接了动态信息可能导致改变语义的情况
			 * 所以PS不会出现SQL注入攻击问题。
			 */
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int id=rs.getInt("id");
				username=rs.getString("username");
				String email  =rs.getString("email");
				String nickname=rs.getString("nickname");
				double account=rs.getDouble("account");
				System.out.println(id+","+username+","+email+","+nickname+","+account);
			}
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
