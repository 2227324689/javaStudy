package com.canghe.test;

import java.sql.SQLException;

import com.canghe.util.AddData;

public class AddDate {
	public static void main(String[] args) {
		//Ϊuser�����50������
		try {
			AddData.add(50);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
