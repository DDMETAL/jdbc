package day01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ģ����ɵ�½����
 * Ҫ���û������û���������
 * ��ʾ ��ӭ�㣡 nickname������ǰ���Ϊ��5000
 * ��������������ʾ�û������������
 * @author NiCo
 *
 */
public class Test5 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Connection conn=null;
		try {
			//��ȡ�û��������Ϣ
			System.out.println("��ӭ��½��");
			System.out.println("�������û�����");
			String username=scan.nextLine();
			System.out.println("���������룺");
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
				System.out.println("��ӭ��!"+nickname+"����ǰ���Ϊ��"+account);
			}else {
				System.out.println("�û������������");
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
