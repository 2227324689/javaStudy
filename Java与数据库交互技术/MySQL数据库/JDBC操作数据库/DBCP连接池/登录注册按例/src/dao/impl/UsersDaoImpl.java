package dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.UsersDao;
import util.JDBCUtils;

/**
 * ���û�������ʵ����
 * @author Administrator
 *
 */
public class UsersDaoImpl implements UsersDao{
	
	//����QueryRunner��Ķ���,���췽����,���ݹ������л�ȡ������Դ
	private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
	
	/**
	 * ������¼����
	 *  �û���������,��Ϊ���ݱ�users�Ĳ�ѯ����,��ѯ������
	 */
	@Override
	public Object[] login(String username, String password) {
		try {
			String sql="SELECT * FROM users WHERE username=? AND PASSWORD=?";
			Object[] params = {username,password};
			Object[] result = queryRunner.query(sql, new ArrayHandler(), params);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��¼��ѯʧ��");
		}
	}
	
	/**
	 * ����ע�Ṧ��
	 * �����û��������û���������
	 * ����û����Ƿ�ռ
	 * 	  ���û�������û���,��Ϊusers���ѯ����
	 * 	����н����,˵���û����Ѿ�����
	 * ���û�н����,�û�������ʹ��, �û���������insert д�뵽���ݱ�
	 */
	@Override
	public boolean registr(String username, String password) {
		try {
			String sql = "SELECT username FROM users WHERE username=?";
			//����qr���󷽷�query��ѯ�����,ScalarHandler һ�������
			String user=queryRunner.query(sql, new ScalarHandler<String>(), username);
			if(user !=null)
				return false;
			//�û�������ʹ��
			//���û���������������ݿ�
			sql="INSERT INTO users (username,PASSWORD) VALUES (?,?)";
			Object[] params = {username,password};
			queryRunner.update(sql, params);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ע��ʧ��");
		}
	}
	
}
