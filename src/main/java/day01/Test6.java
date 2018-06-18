package day01;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 模拟完成注册功能
 * @author NiCo
 *
 */
public class Test6 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Connection conn=null;
		try {
			System.out.println("欢迎注册");
			System.out.println("请输入用户名：");
			String username=scan.nextLine();
			System.out.println("请输入密码：");
			String password=scan.nextLine();
			System.out.println("请输入邮箱：");
			String email=scan.nextLine();
			System.out.println("请输入昵称：");
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
