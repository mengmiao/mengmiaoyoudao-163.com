package com.book;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sql.SQLmain;

public class Books_Letter {
	String book_order, Start, Quantity;
	String[] book_orders = { "", "文学", "法律", "艺术", "传记", "生活", "心理" };
	int k = 0;

	public Books_Letter(String book_order, String Start, String Quantity) {
		// TODO Auto-generated constructor stub
		this.book_order = book_order;
		this.Start = Start;
		this.Quantity = Quantity;
	}

	public String bool_letter() {
		switch (book_order) {
		case "book_letter":
			k = 1;
			break;
		case "book_law":
			k = 2;
			break;
		case "book_art":
			k = 3;
			break;
		case "book_biography":
			k = 4;
			break;
		case "book_life":
			k = 5;
			break;
		case "book_mentality":
			k = 6;
			break;
		}
		if (k == 0) {
			return "book_category命令有误";
		} else {
			Statement books_letter = SQLmain.statement;
			ResultSet rs = null;
			StringBuffer sb = new StringBuffer();
			try {
				rs = books_letter.executeQuery("select * from books where book_type='" + book_orders[k]
						+ "' order by book_type asc limit " + Start + "," + Quantity);
				rs.next();
				sb.append("{\"contents\":[{\"book_id\":" + rs.getString(1) + ",\"book_name\":\"" + rs.getString(2)
						+ "\",\"book_writer\":\"" + rs.getString(3) + "\",\"book_money\":" + rs.getString(4)
						+ ",\"book_type\":\"" + rs.getString(5) + "\",\"book_context_b\":\"" + rs.getString(8)
						+ "\",\"book_url\":\"" + rs.getString(10) + "\"}");
				int i = 0;
				while (rs.next()) {
					sb.append(",{\"book_id\":" + rs.getString(1) + ",\"book_name\":\"" + rs.getString(2)
							+ "\",\"book_writer\":\"" + rs.getString(3) + "\",\"book_money\":" + rs.getString(4)
							+ ",\"book_type\":\"" + rs.getString(5) + "\",\"book_context_b\":\"" + rs.getString(8)
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
}
