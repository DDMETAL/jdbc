package day01;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ģ�����ע�Ṧ��
 * @author NiCo
 *
 */
public class Test6 {
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
			Statement state=conn.createStatement();
			String sql="INSERT INTO userinfo_1 "+ 
					"(id,username,password,email,nickname,account) "+ 
					"VALUES "+
					"(seq_userinfo_1.NEXTVAL,'"+username+"','"+password+"','"+email+"','"+nickname+"',5000)";
			state.execute(sql);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
