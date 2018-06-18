package day01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 模拟完成登陆功能
 * 要求用户输入用户名和密码
 * 显示 欢迎你！ nickname，您当前余额为：5000
 * 若输入有误则显示用户名或密码错误
 * @author NiCo
 *
 */
public class Test5 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Connection conn=null;
		try {
			//获取用户输入的信息
			System.out.println("欢迎登陆！");
			System.out.println("请输入用户名：");
			String username=scan.nextLine();
			System.out.println("请输入密码：");
			String password=scan.nextLine();
			conn=DBUtil.getConnection();
			Statement state=conn.createStatement();
			String sql="SELECT username,password,nickname,account "+
					   "FROM userinfo_1 "+
					   "WHERE username='"+username+"' and password='"+password+"'";
			ResultSet rs=state.executeQuery(sql);
			if(rs.next()) {
				username=rs.getString("username");
				password=rs.getString("password");
				String nickname=rs.getString("nickname");
				Double account =rs.getDouble("account");
				System.out.println("欢迎你!"+nickname+"您当前余额为："+account);
			}else {
				System.out.println("用户名或密码错误");
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
