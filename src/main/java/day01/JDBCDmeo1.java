package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC java���ݿ�����
 * JDBC��java�ٷ��ṩ��һ�׽ӿڣ��������Ӳ��������ݿ�
 * ��ͬ���ݿⶼ�ṩһ��JDBCʵ���࣬����jar����ʽ����������ʹ����
 * ����ͨ������ʵ�������������ݿ⣬����jar��Ϊ��������
 * 
 * JDBC�ӿڣ�
 * 1.DriverManager��ʵ���࣬���ڼ������ݿ��������������ݿ��������ӣ�
 *   �ɹ����������Connection
 * 2.Connection�ӿ�
 *   ��ʾ�����ݿ�����ӣ�����������񣬴���Statementʵ��
 * 3.Statement�ӿ�
 * 	 ����ִ��SQL���
 * 4.ResulSet�ӿ�
 * 	 ��ʾ��ѯ�����
 * @author NiCo
 *
 */
public class JDBCDmeo1 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			/*
			 * �������ݿ�Ĳ��裺
			 * 1.������⣨����jar������JVM
			 * 2.ͨ��DriverManager�������ӣ���������jar����JDBCʵ���������ݿ⽨������
			 * 3.ͨ��Connection����Statement����
			 * 4.ͨ��Statementִ��SQL���
			 * 5.��ִ�е���DQL��䣬���õ���ѯ�����Result,�����ý�����õ���ѯ����
			 * 6.�ر�����
			 */
			//1.����������.��ͬ���ݿ⣬������·����ͬ
			Class.forName("oracle.jdbc.driver.OracleDriver");
			/*
			 * 2.����������������
			 * ʹ��DriverManager�ľ�̬����getConnection
			 * �÷����贫�������������ݿ��ַ����ͬ���ݿ��ʽ��ͬ���û��������룩
			 */
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("���������ݿ�");
			
			/*
			 * 3.����Statement
			 *   ִ�в�ͬ�����SQL�������Ӧ�ķ�����
			 *   1.int executeUpdate��String sql)ר������ִ��DML���ķ�����
			 *   	����ֵ��һ������������ʾִ����DML����Ӱ���˱��ж���������
			 *   2.ResultSet executeQuery(String sql)ר������ָ��DQL���ķ�����
			 *   	����ֵΪ��ѯ�����
			 *   3.booleam execute(String sql) ʲôSQL��䶼����ָ���У�������DML,DQL��ר�ŷ�����
			 *   	����һ������ִ��DDL��䣬����ֵΪtrueʱ˵��ִ�к��з���ֵ��
			 *   	����ִ�в���DQL��䣬�����������ִ�к󷵻�ֵ����false��
			 */
			Statement state=conn.createStatement();
			
			/*
			 * ����
			 * �ֶΣ�
			 * id NUMBER(6)				����
			 * username VARCHAR2(32)	�û���
			 * password VARCHAR2(32)	����
			 * email    VARCHAR2(50)	����
			 * nickname VARCHAR2(32)	�ǳ�
			 * account  NUMBER(10,2)	�˻����
			 * 
			 * CREATER TABLE userinfo(
			 * 	id NUMBER(6),
			 * username VARCHAR2(32),
			 * password VARCHAR2(32),
			 * email    VARCHAR2(50),
			 * nickname VARCHAR2(32),
			 * account  NUMBER(10,2))
			 */
			String sql="CREATE TABLE userinfo_1("+
					   "id NUMBER(6), "+
					   "username VARCHAR2(32), "+
					   "password VARCHAR2(32), "+
					   "email VARCHAR2(50), "+
					   "nickname VARCHAR2(32), "+
					   "account NUMBER(10,2)" +
					   ")";
			System.out.println(sql);
			state.execute(sql);
			System.out.println("�����ɹ�");
		}catch(Exception e) {
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
