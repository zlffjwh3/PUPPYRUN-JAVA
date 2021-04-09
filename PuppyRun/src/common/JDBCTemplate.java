package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	private static JDBCTemplate instance;
	
	public JDBCTemplate() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 싱글톤 패턴 - JDBCTemplate 객체는 딱 한번만 생성되도록
	public static JDBCTemplate getConnection() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	public Connection createConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@puppyrun.cai5f6zm7las.ap-northeast-2.rds.amazonaws.com:1521:puppyrun";
		String user = "admin";
		String password = "admin1234";
		Connection conn = DriverManager.getConnection(url, user, password);
		
		conn.setAutoCommit(false); // 자동커밋 해제
		
		return conn;
	}
	
	// close 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// commit / rollback
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
