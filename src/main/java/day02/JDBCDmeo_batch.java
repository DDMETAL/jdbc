package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import day01.DBUtil;

/**
 * ������
 * ����Ҫһ��ִ�ж���SQL���ʱ������ʹ����������
 * ����һ��SQL���һ���Է��������ݿ�ִ�У���������
 * ���ٿͻ��˵����ݿ�֮���������ô��������SQL����Ч�ʵȡ�
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
				ps.addBatch();//��ӵ���������
			}
			//һ���Խ������������ݷ��������ݿ�
			int [] date=ps.executeBatch();
			conn.commit();
			long end=System.currentTimeMillis();
			System.out.println("ִ�����,��ʱ��"+(end-start)+"ms");
			
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
