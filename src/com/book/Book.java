package com.book;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sql.SQLmain;

public class Book {
	String order, Book_ID;

	public Book(String Book_ID, String order) {
		// TODO Auto-generated constructor stub
		this.Book_ID = Book_ID;
		this.order = order;// �ֶ�
	}

	private String book() {
		// TODO Auto-generated method stub
		Statement book = SQLmain.statement;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			rs = book.executeQuery("select * from books where book_type=" + Book_ID);
			rs.next();
			// 1�����ظ������ݣ�2���޸������Ӧ���ֶ�����
			int i = rs.getInt(order);
			// �޸��ֶ�����
			book.executeUpdate("update books set " + order + "=" + (i++) + " where classname=book_type=" + Book_ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
