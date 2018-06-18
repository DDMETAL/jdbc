package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * ʹ��ps�޸��û���Ϣ
 * @author NiCo
 *
 */
public class JDBCDemo_update {
	public static void main(String[] args) {
		Connection conn=null;
		/*
		 * �����û����޸��û���Ϣ
		 */
		Scanner scan=new Scanner(System.in);
		System.out.println("������Ҫ�޸�account���û�����");
		String username=scan.nextLine();
		System.out.println("�������޸ĵ�account");
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
				System.out.println("�޸ĳɹ�");
			}else {
				System.out.println("�޸�ʧ��");
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
