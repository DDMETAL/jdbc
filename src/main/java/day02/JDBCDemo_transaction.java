package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * jdbc���������
 * jdbcĬ���Զ��ύ����
 * 
 * ����ʵ��Connection��ά���ģ���ʵ���ϵ��õĻ������ݿ���������
 * @author NiCo
 *
 */
public class JDBCDemo_transaction {
	public static void main(String[] args) {
		/*
		 * ����
		 * ���ת�˲���
		 * ����ת���˺��û�����������ת���˺��û������Լ�ת�˽����ɲ���
		 */
		Scanner scan=new Scanner(System.in);
		System.out.println("ת���ʺţ�");
		String userout=scan.nextLine();
		System.out.println("ת���˺ţ�");
		String userin=scan.nextLine();
		System.out.println("ת���");
		int num=Integer.parseInt(scan.nextLine());
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/*
			 * ����Ҫ�������������Ƚ��Զ��ύ����ر�
			 */
			conn.setAutoCommit(false);
			
			String outsql="UPDATE userinfo_1 "+
					   "SET account=account-? "+
					   "WHERE username=? ";
			String insql="UPDATE userinfo_1 "+
					   "SET account=account+? "+
					   "WHERE username=? ";
			PreparedStatement outps=conn.prepareStatement(outsql);
			outps.setInt(1, num);
			outps.setString(2,userout);
			int i=outps.executeUpdate();
			if(i==0) {
				System.out.println("ת���˺���Ϣ����");
				conn.rollback();
				return;
			}
			PreparedStatement inps=conn.prepareStatement(insql);
			inps.setInt(1, num);
			inps.setString(2, userin);
			int j=inps.executeUpdate();
			if(j>0) {
				System.out.println("ת�����");
				conn.commit();
			}else {
				System.out.println("ת���˺���Ϣ����");
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			if(conn!=null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
