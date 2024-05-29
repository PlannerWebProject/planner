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
		config.setMaximumPoolSize(20);
		config.setIdleTimeout(300000); // 유휴 커넥션 타임아웃 (밀리초)
		config.setConnectionTimeout(30000); // 커넥션 대기 타임아웃 (밀리초)
		config.setLeakDetectionThreshold(2000); // 커넥션 누수 감지 시간 (밀리초)
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

}