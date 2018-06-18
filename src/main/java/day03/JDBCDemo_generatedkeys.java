package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import day01.DBUtil;

/**
 * �����Զ�����
 * @author NiCo
 *
 */
public class JDBCDemo_generatedkeys {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/*
			 * ����һ��������Ϣ
			 * ͬʱ����һ��Ա����Ϣ����Ա��Ϊ�����벿��Ա��
			 */
			String dsql="INSERT INTO dept "+
					   "(deptno,dname,loc) "+
					   "VALUES "+
					   "(seq_dept_1.NEXTVAL,?,?)";
			/*
			 * ����psʱָ���ڶ����������ò�����һ���ַ������飬
			 * �����е�ÿ��Ԫ��Ӧ����һ���ֶ���
			 * ����ͨ��psִ��DML�����󣬷��ظü�¼��Ӧ�ֶε�ֵ��
			 */
			PreparedStatement dps=conn.prepareStatement(dsql,new String[] {"deptno"});
			dps.setString(1, "IT");
			dps.setString(2, "SHANGHAI");
			dps.executeUpdate();
			/*
			 * ��ȡ�����������ָ���ֶε�ֵ
			 * ���ؽ����һ���������ʽ
			 */
			ResultSet rs=dps.getGeneratedKeys();//����ps��ָ���ֶ�ֵ
			rs.next();
			int deptno=rs.getInt(1);
			String esql="INSERT INTO emp "+
					    "(empno,ename,job,sal,deptno) "+
					    "VALUES "+
					    "(seq_emp_1.NEXTVAL,?,?,?,?) ";
			PreparedStatement eps=conn.prepareStatement(esql);
			eps.setString(1, "DDD");
			eps.setString(2, "Manager");
			eps.setDouble(3, 9000);
			eps.setInt(4, deptno);
			eps.executeUpdate();
			
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
