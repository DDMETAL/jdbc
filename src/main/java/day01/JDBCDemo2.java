package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ִ��DML���
 * @author NiCo
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("��ӭʹ��");
		System.out.println("�������û�����");
		String username=scanner.nextLine();
		System.out.println("���������룺");
		String password=scanner.nextLine();
		System.out.println("���������䣺");
		String email=scanner.nextLine();
		System.out.println("�������ǳƣ�");
		String nickname=scanner.nextLine();
		
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("���������ݿ�");
			Statement state=conn.createStatement();
			
			/*
			 * ��userinfo_1���в�������
			 * INSERT INTO userinfo_1
			 * (id,username,password,email,nickname,account)
			 * VALUES
			 * (1,'','','','',5000)
			 */
			String sql="INSERT INTO userinfo_1 "+ 
					"(id,username,password,email,nickname,account) "+ 
					"VALUES "+
					"(seq_userinfo_1.NEXTVAL,'"+username+"','"+password+"','"+email+"','"+nickname+"',5000)";
			System.out.println(sql);
			state.execute(sql);
			System.out.println("ע��ɹ�");
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
