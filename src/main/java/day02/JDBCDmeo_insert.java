package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * java.sql.Statement����ִ��SQL��䣬����Statementֻ�ʺ�ִ�о�̬sql��䣬
 * ����SQL����в�����ƴ�Ӷ�̬���ݵĵط�
 * ��Ϊƴ��SQL���ᵼ��SQL���ĸ��Ӷ���ߣ����ҿ��ܳ���SQLע�빥��
 * 
 * java.sql.PreparedStatement��Statement�����࣬ר�����������������
 * PreparedStatement�ʺ�ִ�к��ж�̬��Ϣ��SQL���
 * ��ִ�е���Ԥ����SQL��䡣
 * @author NiCo
 *
 */
public class JDBCDmeo_insert {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Connection conn=null;
		try {
			System.out.println("��ӭע��");
			System.out.println("�������û�����");
			String username=scan.nextLine();
			System.out.println("���������룺");
			String password=scan.nextLine();
			System.out.println("���������䣺");
			String email=scan.nextLine();
			System.out.println("�������ǳƣ�");
			String nickname=scan.nextLine();
			conn=DBUtil.getConnection();
			/*
			 * Ԥ����SQL���
			 * ���ж�̬��Ϣ�á��������棬ֻ�ܴ���ֵ
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
				System.out.println("ע��ɹ�");
			}else {
				System.out.println("ע��ʧ��");
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
