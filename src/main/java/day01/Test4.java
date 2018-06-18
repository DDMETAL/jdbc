package day01;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 将给定用户名所对应记录删除
 * 
 * @author NiCo
 *
 */
public class Test4 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("请输入删除用户名：");
		String username=scan.nextLine();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			System.out.println("已连接数据库");
			Statement state=conn.createStatement();
			String sql="DELETE FROM userinfo_1 "+
					   "WHERE username='"+username+"'";
			int i=state.executeUpdate(sql);
			if(i>0) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
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
