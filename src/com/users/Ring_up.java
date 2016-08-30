package com.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sql.SQLmain;

public class Ring_up {
	User_JSON ring_upJSON;
	String ring = null;
	boolean B = true;

	public Ring_up(User_JSON ring_upJSON) {
		// TODO Auto-generated constructor stub
		this.ring_upJSON = ring_upJSON;
	}

	public String Ring() {
		Statement statement = SQLmain.statement;
		ResultSet rs = null;
		// 获取整个数据库内容
		try {
			rs = statement.executeQuery("select * from users");
			while (rs.next() && B) {
				if (ring_upJSON.getContents().get(0).getContent().equals(rs.getString(2))) {
					if (ring_upJSON.getContents().get(1).getContent().equals(rs.getString(3))) {
						ring = "ring_OK";
						B = false;
					} else {
						ring = "pwd_error";
						B = false;
					}
				} else {
					ring = "username_error";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ring = "数据库异常";
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
