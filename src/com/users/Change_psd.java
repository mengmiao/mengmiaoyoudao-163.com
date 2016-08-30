package com.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sql.SQLmain;

public class Change_psd {
	String ring = null;
	User_JSON user_JSON;
	boolean B = true;

	public Change_psd(User_JSON user_JSON) {
		// TODO Auto-generated constructor stub
		this.user_JSON = user_JSON;
	}

	public String change_psd() {
		Statement statement_select = SQLmain.statement;
		Statement statement_update = SQLmain.statement;
		ResultSet rs = null;
		try {
			rs = statement_select.executeQuery("select * from users");
			while (rs.next() & B) {
				if (user_JSON.getContents().get(0).getContent().equals(rs.getString(2))) {
					if (user_JSON.getContents().get(1).getContent().equals(rs.getString(3))
							|| user_JSON.getContents().get(1).getContent().equals(rs.getString(4))
							|| user_JSON.getContents().get(1).getContent().equals(rs.getString(5))) {
						statement_update.executeUpdate("update users set pwd='"
								+ user_JSON.getContents().get(2).getContent() + "' where account_number='"
								+ user_JSON.getContents().get(0).getContent() + "';");
						// rs.close();
						ring = "change_OK";
						B = false;
					} else {
						// rs.close();
						ring = "info_error";
						B = false;
					}
				} else {
					ring = "username_error";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ring;
	}
}
