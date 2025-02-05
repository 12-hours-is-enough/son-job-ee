package com.sonjobee.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static {
		try {
			Class.forName(EnvConfig.get("DB_DRIVER"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("MySQL JDBC 드라이버를 찾을 수 없습니다!", e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(EnvConfig.get("DB_URL"),
											EnvConfig.get("DB_USERNAME"),
											EnvConfig.get("DB_PASSWORD"));
	}
	
	public static void close(Connection con, Statement stmt) {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
}
