package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import day01.DBUtil;

/**
 * Statementֻ�ʺ�ִ�о�̬SQL���
 * PreparedStatement�ʺ�ִ�к��ж�̬��Ϣ��SQL
 * ps����ִ�ж�̬SQL��Statement��Ч�ʸ�
 * 
 * ������ִ��SQL���ʱ��Ӱ��Ч�ʵ���Ҫ����
 * 1.���Ǻ��ж�̬��Ϣʱ��ʹ��psЧ�ʱ�Statement��
 * 	 ����ִ�й��̵Ĳ������������ݿ�ѹ����ϵͳ����
 * 2.����Խ�࣬Ч��Խ��
 * 3.���η���SQL����������SQL���Ч�ʵ�
 * @author NiCo
 *
 */
public class JDBCDemo_1 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/*
			 * ��userinfo_1���в���1000������
			 */
//			Statement state=conn.createStatement();
//			for(int i=0;i<1000;i++) {
//				String sql="INSERT INTO userinfo_1 "+
//						   "(id,username,password "+
//						   "VALUES "+
//						   "(seq_userinfo_1.NEXTVAL,'user"+i+"','123456'";
//				state.executeUpdate(sql);
//				state.addBatch(sql);//Statement��������
//			}
			String sql="INSERT INTO userinfo_1 "+
					   "(id,username,password��"+
					   "VALUES "+
					   "(seq_userinfo_1.NEXTVAL,'?','123456'��";
			/*
			 * ��userinfo_1���в���1000������
			 * ����ps��ͬʱ��Ԥ����SQL��䴫�룬Connection�ͻ�
			 * ����SQL��䷢�������ݿ�������ִ�мƻ���ֻ�Ǹ�
			 * ִ�мƻ����������У���Ϊȱ�٣���Ӧ��ֵ��
			 */
			PreparedStatement ps=conn.prepareStatement(sql);
			for(int i=0;i<1000;i++) {
				/*
				 * ÿ���ƶ�����ֵ�Ժ�ִ��executeUpdateֻ�ǽ���ֵ���͹�ȥ��
				 * ��ͬһ��ִ�мƻ�ִ��1000�飬���Լ������ݿ�����ִ�мƻ������Ŀ���
				 */
				ps.setString(1, "user"+i);
				ps.executeUpdate();
			}
			System.out.println("�������");
			
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
