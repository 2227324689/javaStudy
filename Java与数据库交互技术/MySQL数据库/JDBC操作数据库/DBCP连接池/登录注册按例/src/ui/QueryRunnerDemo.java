package ui;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.naming.spi.Resolver;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import util.JDBCUtils;

public class QueryRunnerDemo {
	public static void main(String[] args) {
//		select();
		insert();
	}
	
	//QueryRunner�����,д�����Աλ��
	private static QueryRunner qr= new QueryRunner(JDBCUtils.getDataSource());
	//����2������,ʵ�����ݱ�����,���ݱ��ѯ
	
	//���ݱ��ѯ
	public static void select() {
		try {
			String sql = "SELECT * FROM users";
			List<Object[]> list =qr.query(sql, new ArrayListHandler());
			//�����������鼯��
			for(Object[] objs:list) {
				for(Object obj:objs) {
					System.out.print(obj);
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("���ݲ�ѯʧ��");
		}
	}
	
	//���ݱ��������
	public static void insert() {
		try {
			String sql="INSERT INTO users (username,PASSWORD) VALUES (?,?)";
			Object[] params = {"c","3"};
			int result=qr.update(sql, params);
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��������ʧ��");
		}
	}
	
}
