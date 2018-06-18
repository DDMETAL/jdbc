package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * �鿴emp����Ա����Ϣ���鿴empno,ename,job,sal,deptno
 * ����sal��������ÿҳ��ʾ5�����鿴�ڶ�ҳ����
 */
public class Test2 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("���������ݿ�");
			Statement state=conn.createStatement();
			String sql="SELECT * FROM(SELECT ROWNUM rn,t.* "+
					"FROM(SELECT empno,ename,job,sal,deptno "+ 
					"FROM emp "+ 
					"ORDER BY sal DESC) t "+
					"WHERE ROWNUM<=10 ) "+
					"WHERE rn>=5";
			System.out.println(sql);
			ResultSet rs=state.executeQuery(sql);
			while(rs.next()) {
				int empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				double sal=rs.getDouble("sal");
				int deptno=rs.getInt("deptno");
				System.out.println(empno+","+ename+","+job+","+sal+","+deptno);
			}
			System.out.println("��ѯ�ɹ�");
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
