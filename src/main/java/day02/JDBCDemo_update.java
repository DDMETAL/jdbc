package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * 使用ps修改用户信息
 * @author NiCo
 *
 */
public class JDBCDemo_update {
	public static void main(String[] args) {
		Connection conn=null;
		/*
		 * 根据用户名修改用户信息
		 */
		Scanner scan=new Scanner(System.in);
		System.out.println("请输入要修改account的用户名：");
		String username=scan.nextLine();
		System.out.println("请输入修改的account");
		double account=Double.parseDouble(scan.nextLine());
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE userinfo_1 "+
					   "SET account=? "+
					   "WHERE username=? ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setDouble(1, account);
			ps.setString(2, username);
			int i=ps.executeUpdate();
			if(i>0) {
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败");
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
