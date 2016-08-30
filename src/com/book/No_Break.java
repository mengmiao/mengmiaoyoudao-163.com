package com.book;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sql.SQLmain;

public class No_Break {
	String order = null;

	public No_Break(String order) {
		// TODO Auto-generated constructor stub
		this.order = order;
	}

	public String no_break() {
		Statement no_break = SQLmain.statement;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			rs = no_break.executeQuery("select * from books order by " + order + " desc");
			rs.next();
			sb.append("{\"contents\":[{\"book_id\":" + rs.getString(1) + ",\"book_name\":\"" + rs.getString(2)
					+ "\",\"book_url\":\"" + rs.getString(10) + "\"}");
			int i = 0;
			while (rs.next() && i < 3) {
				sb.append(",{\"book_id\":\"" + rs.getString(1) + "\",\"book_name\":\"" + rs.getString(2)
						+ "\",\"book_url\":\"" + rs.getString(10) + "\"}");
				i++;
			}
			sb.append("]}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
