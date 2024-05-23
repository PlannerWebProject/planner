package com.planiverse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBUtil {
	private static HikariDataSource dataSource;
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:oracle:thin:@43.202.179.175:1521:xe");
		config.setUsername("planiverse");
		config.setPassword("java1234");
		config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		config.setMaximumPoolSize(10);
		dataSource = new HikariDataSource(config);
	}

	public static Connection open() {
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 * public static Connection open() { Connection conn = null;
	 * 
	 * String url = "jdbc:oracle:thin:@43.202.179.175:1521:xe"; String id =
	 * "planiverse"; String pw = "java1234";
	 * 
	 * try { Class.forName("oracle.jdbc.driver.OracleDriver");
	 * 
	 * conn = DriverManager.getConnection(url, id, pw); //
	 * conn.setAutoCommit(false);
	 * 
	 * return conn; } catch (Exception e) { System.out.println("DB.open");
	 * e.printStackTrace(); } return null; }
		}
	 */

}