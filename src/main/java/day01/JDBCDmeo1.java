package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC java数据库连接
 * JDBC是java官方提供的一套接口，用于连接并操作数据库
 * 不同数据库都提供一套JDBC实现类，并以jar包形式发布，用于使程序
 * 可以通过这套实现类来操作数据库，这套jar成为“驱动”
 * 
 * JDBC接口：
 * 1.DriverManager是实现类，用于加载数据库驱动，并与数据库链接连接，
 *   成功建立后产生Connection
 * 2.Connection接口
 *   表示与数据库的链接，负责管理事务，创建Statement实例
 * 3.Statement接口
 * 	 负责执行SQL语句
 * 4.ResulSet接口
 * 	 表示查询结果集
 * @author NiCo
 *
 */
public class JDBCDmeo1 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			/*
			 * 链接数据库的步骤：
			 * 1.加载类库（驱动jar包）到JVM
			 * 2.通过DriverManager建立连接，这里会加载jar包中JDBC实现类与数据库建立连接
			 * 3.通过Connection创建Statement对象
			 * 4.通过Statement执行SQL语句
			 * 5.若执行的是DQL语句，则会得到查询结果集Result,遍历该结果集得到查询内容
			 * 6.关闭连接
			 */
			//1.加载驱动包.不同数据库，驱动包路径不同
			Class.forName("oracle.jdbc.driver.OracleDriver");
			/*
			 * 2.加载驱动建立连接
			 * 使用DriverManager的静态方法getConnection
			 * 该方法需传三个参数：数据库地址（不同数据库格式不同，用户名，密码）
			 */
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a12345");
			System.out.println("已连接数据库");
			
			/*
			 * 3.创建Statement
			 *   执行不同种类的SQL语句有相应的方法：
			 *   1.int executeUpdate（String sql)专门用来执行DML语句的方法，
			 *   	返回值是一个整数用来表示执行了DML语句后影响了表中多少条数据
			 *   2.ResultSet executeQuery(String sql)专门用来指定DQL语句的方法，
			 *   	返回值为查询结果集
			 *   3.booleam execute(String sql) 什么SQL语句都可以指定行，但由于DML,DQL有专门方法，
			 *   	所以一般用来执行DDL语句，返回值为true时说明执行后有返回值，
			 *   	但若执行不是DQL语句，其他类型语句执行后返回值都是false。
			 */
			Statement state=conn.createStatement();
			
			/*
			 * 建表
			 * 字段：
			 * id NUMBER(6)				主键
			 * username VARCHAR2(32)	用户名
			 * password VARCHAR2(32)	密码
			 * email    VARCHAR2(50)	邮箱
			 * nickname VARCHAR2(32)	昵称
			 * account  NUMBER(10,2)	账户余额
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
			System.out.println("表创建成功");
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
