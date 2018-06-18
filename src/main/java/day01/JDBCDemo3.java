package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ִ��DQL���
 * @author NiCo
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("���������ݿ�");
			Statement state=conn.createStatement();
			String sql="SELECT id,username,password, "+
			"email,nickname,account "+
			"FROM userinfo_1  ";
			System.out.println(sql);
			ResultSet rs = state.executeQuery(sql);
			/*
			 * ResultSet��ʾ��ѯ�Ľ����
			 * ����������Ĳ���Ϊ��
			 * 1.���жϽ�����Ƿ�����һ����¼
			 * 2.���У�����Ի�ȡ������¼���ֶε�ֵ��ֱ�����м�¼������ϡ�
			 * ����������
			 * boolean next() ��ָ��ָ����һ����¼��ʹResultSet��ʾ������¼��������ֵΪfalse��û�м�¼
			 * һ��getXXX(String cloName)����ȡ�ַ��������ֶ�ֵ��getString����ȡ������getInt,��ȡС����getDouble��
			 * 
			 */
			while(rs.next()) {
				int id=rs.getInt("id");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String email  =rs.getString("email");
				String nickname=rs.getString("nickname");
				double account=rs.getDouble("account");
//				int id=rs.getInt(1);
//				String username=rs.getString(2);
//				String password=rs.getString(3);
//				String email  =rs.getString(4);
//				String nickname=rs.getString(5);
//				double account=rs.getDouble(6);
				System.out.println(id+","+username+","+password+","+email+","+nickname+","+account);
			}
			System.out.println("��ѯ�ɹ�");
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
