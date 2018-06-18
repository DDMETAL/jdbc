package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import day01.DBUtil;

/**
 * 批处理
 * 当需要一次执行多条SQL语句时，可以使用批操作，
 * 缓存一组SQL语句一次性发送至数据库执行，这样可以
 * 减少客户端到数据库之间的网络调用次数，提高SQL传输效率等。
 * @author NiCo
 *
 */
public class JDBCDmeo_batch {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="INSERT INTO userinfo_1 "+
					   "(id,username,password) "+
					   "VALUES "+
					   "(seq_userinfo_1.NEXTVAL,?,'123456')";
			System.out.println(sql);
			PreparedStatement ps=conn.prepareStatement(sql);
			long start=System.currentTimeMillis();
			for(int i=0;i<1000;i++) {
				ps.setString(1, "user"+i);
				ps.addBatch();//添加到批处理缓存
			}
			//一次性将批处理缓存内容发送至数据库
			int [] date=ps.executeBatch();
			conn.commit();
			long end=System.currentTimeMillis();
			System.out.println("执行完毕,耗时："+(end-start)+"ms");
			
		} catch (Exception e) {
			e.printStackTrace();
			if(conn!=null) {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
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
