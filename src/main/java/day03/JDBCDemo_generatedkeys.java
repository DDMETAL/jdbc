package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import day01.DBUtil;

/**
 * 返回自动主键
 * @author NiCo
 *
 */
public class JDBCDemo_generatedkeys {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/*
			 * 插入一个部门信息
			 * 同时插入一个员工信息，该员工为所插入部门员工
			 */
			String dsql="INSERT INTO dept "+
					   "(deptno,dname,loc) "+
					   "VALUES "+
					   "(seq_dept_1.NEXTVAL,?,?)";
			/*
			 * 创建ps时指定第二个参数，该参数是一个字符串数组，
			 * 数组中的每个元素应当是一个字段名
			 * 表明通过ps执行DML操作后，返回该记录对应字段的值。
			 */
			PreparedStatement dps=conn.prepareStatement(dsql,new String[] {"deptno"});
			dps.setString(1, "IT");
			dps.setString(2, "SHANGHAI");
			dps.executeUpdate();
			/*
			 * 获取插入的数据中指定字段的值
			 * 返回结果是一个结果集形式
			 */
			ResultSet rs=dps.getGeneratedKeys();//返回ps中指定字段值
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
			
			System.out.println("插入完成");
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
