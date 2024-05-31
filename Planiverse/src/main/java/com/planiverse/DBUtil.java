package com.planiverse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/**
 * DB 연결 유틸리티 클래스입니다.
 * HikariCP 커넥션 풀을 사용하여 DB 연결을 관리합니다.
 */
public class DBUtil {
	private static HikariDataSource dataSource;
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:oracle:thin:@43.202.179.175:1521:xe");
		config.setUsername("planiverse");
		config.setPassword("java1234");
		config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		config.setMaximumPoolSize(20);
		dataSource = new HikariDataSource(config);
	}
	 /**
     * DB 연결을 반환합니다.
     * @return DB 연결 객체, 실패 시 null
     */
	public static Connection open() {
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}