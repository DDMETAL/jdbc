package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import day01.DBUtil;

/**
 * Statement只适合执行静态SQL语句
 * PreparedStatement适合执行含有动态信息的SQL
 * ps批量执行动态SQL比Statement的效率高
 * 
 * 当批量执行SQL语句时，影响效率的主要因素
 * 1.若是含有动态信息时，使用ps效率比Statement好
 * 	 减少执行过程的产生，降低数据库压力和系统开销
 * 2.事物越多，效率越差
 * 3.单次发送SQL比批量发送SQL语句效率低
 * @author NiCo
 *
 */
public class JDBCDemo_1 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/*
			 * 向userinfo_1表中插入1000条数据
			 */
//			Statement state=conn.createStatement();
//			for(int i=0;i<1000;i++) {
//				String sql="INSERT INTO userinfo_1 "+
//						   "(id,username,password "+
//						   "VALUES "+
//						   "(seq_userinfo_1.NEXTVAL,'user"+i+"','123456'";
//				state.executeUpdate(sql);
//				state.addBatch(sql);//Statement的批处理
//			}
			String sql="INSERT INTO userinfo_1 "+
					   "(id,username,password）"+
					   "VALUES "+
					   "(seq_userinfo_1.NEXTVAL,'?','123456'）";
			/*
			 * 向userinfo_1表中插入1000条数据
			 * 创建ps的同时将预编译SQL语句传入，Connection就会
			 * 将该SQL语句发送至数据库来生成执行计划，只是该
			 * 执行计划还不能运行，因为缺少？对应的值。
			 */
			PreparedStatement ps=conn.prepareStatement(sql);
			for(int i=0;i<1000;i++) {
				/*
				 * 每次制定？的值以后执行executeUpdate只是将该值发送过去，
				 * 用同一个执行计划执行1000遍，可以减少数据库生成执行计划带来的开销
				 */
				ps.setString(1, "user"+i);
				ps.executeUpdate();
			}
			System.out.println("插入完毕");
			
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
