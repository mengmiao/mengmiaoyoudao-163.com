package com.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sql.SQLmain;

public class Sign_in {
	User_JSON user_JSON;
	String ring = null;
	boolean B = true;

	public Sign_in(User_JSON user_JSON) {
		// TODO Auto-generated constructor stub
		this.user_JSON = user_JSON;
	}

	public String Sign() {
		Statement statement = SQLmain.statement;
		ResultSet rs = null;
		try {
			rs = statement.executeQuery("select * from users");
			while (rs.next() && B) {
				if (user_JSON.getContents().get(0).getContent().equals(rs.getString(2))) {
					ring = "id_exist";
					B = false;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (B) {
			try {
				boolean i = statement.execute("insert into users(account_number, pwd,number,Email) values ('"
						+ user_JSON.getContents().get(0).getContent() + "','"
						+ user_JSON.getContents().get(1).getContent() + "','"
						+ user_JSON.getContents().get(2).getContent() + "','"
						+ user_JSON.getContents().get(3).getContent() + "')");
				ring = "sign_ok";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ring;
	}
}
