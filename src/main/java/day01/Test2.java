package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 查看emp表中员工信息，查看empno,ename,job,sal,deptno
 * 根据sal降序排序，每页显示5条，查看第二页内容
 */
public class Test2 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("已连接数据库");
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
			System.out.println("查询成功");
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
