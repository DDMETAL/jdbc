package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * * 
 * ����һ������
 * seq_userinfo1
 * ��1��ʼ����Ϊ1
 * @author NiCo
 *
 */
 
public class Test1 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("���������ݿ�");
			Statement state=conn.createStatement();
			String sql="CREATE SEQUENCE seq_userinfo_1 "+ 
					"START WITH 1 "+ 
					"INCREMENT BY 1";
			System.out.println(sql);
			state.execute(sql);
			System.out.println("���д����ɹ�");
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
