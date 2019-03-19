package com.canghe.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.canghe.domain.User;
import com.canghe.util.DataSourceUtils;

public class UserDaoImpl {
	/*����Ҫ�õ��Ĳ���*/
	//��Ӱ�������
	private int count;
	//dbutil�е�queryrunner����
	QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	//��user�ļ���
	List<User> userList = new ArrayList<>();
	
	/**
	 * ����
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public int add(User user) throws SQLException {
		String sql = "insert into user(username, password,gender,registeTime) values(?,?,?,?)";
		Object[] params = {user.getUserName(), user.getPassword(), user.getGender(), user.getRegisteTime()};
		count=qr.update(sql, params);
		return count;
	}
	/**
	 * �޸�
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public int modify(User user) throws SQLException {
		String sql = "UPDATE USER SET PASSWORD=? WHERE id=?";
		Object[] params= {user.getPassword(),user.getId()};
		count=qr.update(sql, params);
		return count;
		
	}
	/**
	 * ��ѯ������userϢ
	 * @return
	 * @throws SQLException 
	 */
	public List<User> queryAllUser() throws SQLException{
		String sql = "select * from user";
		Object[] params = {};
		userList=qr.query(sql,new BeanListHandler<User>(User.class));
		return userList;
	}
	
	/**
	 * �����û����������ѯ������Ϣ����¼��
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public User queryByNamePsd(String name,String password) throws SQLException {
		String sql = "SELECT * FROM user WHERE userName=? AND password=?";
		Object[] params = {name,password};
		User user =qr.query(sql, new BeanHandler<User>(User.class), params);
		return user;
	}
	/**
	 * ��ѯһ������������
	 * @return
	 * @throws SQLException
	 */
	public int queryCount() throws SQLException {
		String sql = "SELECT COUNT(1) FROM user";
		count=(Integer)(qr.query(sql, new ScalarHandler<Long>()).intValue());
		return count;
	}
	/**
	 * ��ҳ��ѯ
	 * @param start
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public List<User> queryByPage(int start,int pageSize) throws SQLException{
		String sql = "SELECT * FROM USER LIMIT ?,?";
		Object[] params= {start,pageSize};
		userList=qr.query(sql, new BeanListHandler<User>(User.class), params);
		return userList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
