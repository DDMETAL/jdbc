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
		System.out.println("��ӭ��½");
		System.out.println("�������û�����");
		String username=scan.nextLine();
		System.out.println("���������룺");
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
			 * ����ʹ��ps���Ὣ��̬��Ϣƴ�ӵ�SQL�����
			 * �����Ͳ����������ƴ���˶�̬��Ϣ���ܵ��¸ı���������
			 * ����PS�������SQLע�빥�����⡣
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
