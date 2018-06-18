package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * jdbc的事务控制
 * jdbc默认自动提交事务。
 * 
 * 事物实在Connection中维护的，但实际上调用的还是数据库的事务机制
 * @author NiCo
 *
 */
public class JDBCDemo_transaction {
	public static void main(String[] args) {
		/*
		 * 事务
		 * 完成转账操作
		 * 输入转出账号用户名，再输入转入账号用户名，以及转账金额完成操作
		 */
		Scanner scan=new Scanner(System.in);
		System.out.println("转出帐号：");
		String userout=scan.nextLine();
		System.out.println("转入账号：");
		String userin=scan.nextLine();
		System.out.println("转入金额：");
		int num=Integer.parseInt(scan.nextLine());
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/*
			 * 若需要控制事务，则需先将自动提交事务关闭
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
				System.out.println("转出账号信息有误");
				conn.rollback();
				return;
			}
			PreparedStatement inps=conn.prepareStatement(insql);
			inps.setInt(1, num);
			inps.setString(2, userin);
			int j=inps.executeUpdate();
			if(j>0) {
				System.out.println("转账完毕");
				conn.commit();
			}else {
				System.out.println("转入账号信息有误");
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
