package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day01.DBUtil;

/*
 * DAO ���ݷ��ʶ���
 * ��һ������������ͳ��
 * �Ǽ���ҵ���߼��������ݿ�֮��Ĳ��
 * �ǽ������ݿ�����в�����ҵ���߼����г��������
 * ʹ��ҵ���߼���Ĵ���ֻ��עҵ�񣬲��ٹ�ע���ݵ�ά��������
 * ����DAOҲ������ҵ���߼���Ĵ�������ݵĲ�����ȫ����
 * 
 * 
 * userinfoDAO
 * ������ݿ���userinfo_1��Ĳ���
 */
public class userinfoDAO {
	/**
	 * ��ѯ�����û���Ϣ
	 * ���صļ�����ÿһ��ʵ��Ϊ����һ����¼
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
	 * �޸��û���Ϣ�������������и��û�����Ϣ
	 * �޸�userinfo���ж�Ӧ����
	 * �ö�����id���û������ܱ�
	 * UPDATE userinfo
	 * SET password=?,email=?,nickname=?,account=?
	 * WHERE username=?
	 * @param userinfo
	 * @return �޸ĳɹ�����true��ʧ�ܷ���false
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
	 * ���ݸ����û���ɾ�����û�
	 * @param username
	 * @returnɾ���ɹ�����true��ʧ�ܷ���false
	 */
	public boolean deleteByUserName(String username) {
		return false;
	}
	/**
	 * �����û�����ѯ�û���Ϣ
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
	 * ��������userinfo����������ݿ�
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
