package com.sql;



import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SQLmain {
	public static Statement statement;

	
	public static void One() {
		Connection conn = null;
		String url = "jdbc:mysql://172.18.4.16:3306/book_city";
		String user = "user";
		String password = "mengmiao,.0.";
		try {
			System.out.println("----");
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			statement = (Statement) conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
