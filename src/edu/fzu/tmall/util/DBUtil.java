package edu.fzu.tmall.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	static String ip = "127.0.0.1";
	static int port = 3306;
	static String database = "tmalldb";
	static String encoding = "UTF-8";
	static String loginName = "root";
	static String password = "1219max";

	static {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		String url2 = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=%s",ip,port,database,encoding);
		Connection conn=DriverManager.getConnection(url2,loginName,password);
		return conn;
	}

	/* 关闭连接的方法 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection conn = getConnection();
		System.out.println(conn);
		conn.close();
        System.out.println("数据库连接测试通过");
	}
}