package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day01.DBUtil;

/*
 * DAO 数据访问对象
 * 是一个层次所有类的统称
 * 是夹在业务逻辑层与数据库之间的层次
 * 是将对数据库的所有操作从业务逻辑层中抽离出来，
 * 使得业务逻辑层的代码只关注业务，不再关注数据的维护操作。
 * 而且DAO也可以让业务逻辑层的代码对数据的操作完全对象化
 * 
 * 
 * userinfoDAO
 * 针对数据库中userinfo_1表的操作
 */
public class userinfoDAO {
	/**
	 * 查询所有用户信息
	 * 返回的集合中每一个实例为表中一条记录
	 * @return
	 * @throws Exception 
	 */
	public List<userinfo> findAll() throws Exception{
		List<userinfo> list=new ArrayList<userinfo>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account "+
					   "FROM userinfo_1 ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				userinfo userinfo =new userinfo();
					userinfo.setId(rs.getInt("id"));
					userinfo.setUsername(rs.getString("username"));
					userinfo.setPassword(rs.getString("password"));
					userinfo.setEmail(rs.getString("email"));
					userinfo.setNickname(rs.getString("nickname"));
					userinfo.setAccount(rs.getDouble("account"));
					list.add(userinfo);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(conn!=null) {
				conn.close();
				}
		}
		return list;
	}
	
	/**
	 * 修改用户信息，将给定对象中该用户的信息
	 * 修改userinfo表中对应数据
	 * 该对象中id和用户名不能变
	 * UPDATE userinfo
	 * SET password=?,email=?,nickname=?,account=?
	 * WHERE username=?
	 * @param userinfo
	 * @return 修改成功返回true，失败返回false
	 * @throws Exception 
	 */
	public boolean update(userinfo userinfo) throws Exception {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE userinfo_1 "+
					   "SET password=?,email=?,nickname=?,account=? "+
					   "WHERE username=?" ;
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(5, userinfo.getUsername());
			ps.setString(1, userinfo.getPassword());
			ps.setString(2, userinfo.getEmail());
			ps.setString(3, userinfo.getNickname());
			ps.setDouble(4, userinfo.getAccount());
			int i=ps.executeUpdate();
			if(i>0) {
				return true;
			}else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	/**
	 * 根据给定用户名删除该用户
	 * @param username
	 * @return删除成功返回true，失败返回false
	 */
	public boolean deleteByUserName(String username) {
		return false;
	}
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	public userinfo findByUserName(String username) throws Exception {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account "+
					   "FROM  userinfo_1 "+
					   "WHERE username=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int id=rs.getInt("id");
				username=rs.getString("username");
				String password=rs.getString("password");
				String email=rs.getString("email");
				String nickname=rs.getString("nickname");
				double account=rs.getDouble("account");
				
				userinfo userinfo =new userinfo(id,username,password,email,nickname,account);
				return userinfo;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
	/**
	 * 将给定的userinfo对象存入数据库
	 * @param userinfo
	 * @throws Exception 
	 */
	public void save(userinfo userinfo) throws Exception {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="INSERT INTO userinfo_1 "+
					   "(id,username,password,email,nickname,account) "+
					   "VALUES "+
					   "(seq_userinfo_1.NEXTVAL,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userinfo.getUsername());
			ps.setString(2, userinfo.getPassword());
			ps.setString(3, userinfo.getEmail());
			ps.setString(4, userinfo.getNickname());
			ps.setDouble(5, userinfo.getAccount());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
}
