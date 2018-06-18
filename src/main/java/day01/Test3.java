package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/**
 * �鿴ָ��Ա����Ϣ
 * Ҫ���û������Ա�����֣�Ȼ�󽫸�Ա����empno,ename,job,sal,deptno,dname,loc
 * �����
 * @author NiCo
 *
 */
public class Test3 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("������Ҫ��ѯ��Ա����:");
		String username=scan.nextLine();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			System.out.println("���������ݿ�");
			Statement state=conn.createStatement();
			String sql="SELECT e.empno,e.ename,e.job,e.sal,d.deptno,d.dname,d.loc "+
					   "FROM emp e,dept d "+
					   "WHERE e.deptno=d.deptno and e.ename='"+username+"'";
			ResultSet rs=state.executeQuery(sql);
			while(rs.next()) {
				int empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				double sal=rs.getShort("sal");
				int deptno=rs.getInt("deptno");
				String dname=rs.getString("dname");
				String loc=rs.getString("loc");
				System.out.println(empno+","+ename+","+job+","+sal+","+deptno+","+dname+","+loc);
			System.out.println("��ѯ�ɹ�");
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
