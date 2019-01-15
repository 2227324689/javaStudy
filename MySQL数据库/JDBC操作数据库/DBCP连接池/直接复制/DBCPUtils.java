package com.canghe.utils;
/**
 * DBCP���ӳع�����
 * @author Administrator
 *
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtils {
	// ������BasicDataSource�����
	private static BasicDataSource datasource = new BasicDataSource();
	// ��̬�����,����BasicDataSource�����е�����,�Զ���
	static {
		// ���ݿ�������Ϣ,�����
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://106.13.43.205:3306/test");
		datasource.setUsername("root");
		datasource.setPassword("aaaaa123");

		// �������ӳ��е�������������,��ѡ��
		datasource.setInitialSize(10);// ��ʼ����������
		datasource.setMaxActive(8);// �����������
		datasource.setMaxIdle(5);// ��������
		datasource.setMinIdle(1);// ��С����
	}

	// ���徲̬����,����BasicDataSource��Ķ���
	public static DataSource getDataSource() {
		return datasource;
	}
}