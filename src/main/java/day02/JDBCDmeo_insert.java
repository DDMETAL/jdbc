package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * java.sql.Statement用来执行SQL语句，但是Statement只适合执行静态sql语句，
 * 即：SQL语句中不含有拼接动态数据的地方
 * 因为拼接SQL语句会导致SQL语句的复杂度提高，并且可能出现SQL注入攻击
 * 
 * java.sql.PreparedStatement是Statement的子类，专门用来解决上述问题
 * PreparedStatement适合执行含有动态信息的SQL语句
 * 其执行的是预编译SQL语句。
 * @author NiCo
 *
 */
public class JDBCDmeo_insert {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Connection conn=null;
		try {
			System.out.println("欢迎注册");
			System.out.println("请输入用户名：");
			String username=scan.nextLine();
			System.out.println("请输入密码：");
			String password=scan.nextLine();
			System.out.println("请输入邮箱：");
			String email=scan.nextLine();
			System.out.println("请输入昵称：");
			String nickname=scan.nextLine();
			conn=DBUtil.getConnection();
			/*
			 * 预编译SQL语句
			 * 所有动态信息用“？”代替，只能代替值
			 */
			String sql="INSERT INTO userinfo_1 "+
					   "(id,username,password,email,nickname,account) "+
					   "VALUES "+
					   "(seq_userinfo_1.NEXTVAL,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, nickname);
			ps.setDouble(5, 5000);
			
			int i=ps.executeUpdate();
			if(i>0) {
				System.out.println("注册成功");
			}else {
				System.out.println("注册失败");
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
