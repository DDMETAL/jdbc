package day01;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * �������û�������Ӧ��¼ɾ��
 * 
 * @author NiCo
 *
 */
public class Test4 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("������ɾ���û�����");
		String username=scan.nextLine();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			System.out.println("���������ݿ�");
			Statement state=conn.createStatement();
			String sql="DELETE FROM userinfo_1 "+
					   "WHERE username='"+username+"'";
			int i=state.executeUpdate(sql);
			if(i>0) {
				System.out.println("ɾ���ɹ�");
			}else {
				System.out.println("ɾ��ʧ��");
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
