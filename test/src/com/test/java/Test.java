package com.test.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
	
	public static void main(String[] args) throws SQLException {
		Connection conn =  open();
		System.out.println(conn.isClosed());
	}
	
	public static Connection open() {
		Connection conn = null;

		String url = "jdbc:oracle:thin:@125.241.245.222:1521:xe";
		String id = "project";
		String pw = "java1234";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, id, pw);
			// conn.setAutoCommit(false);

			return conn;
		} catch (Exception e) {
			System.out.println("DB.open");
			e.printStackTrace();
		}
		return null;
	}
}

