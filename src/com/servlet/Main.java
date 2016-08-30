package com.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.Book;
import com.book.Books_Letter;
import com.book.No_Break;
import com.jsonDispose.JSONdispose;
import com.sql.SQLmain;
import com.users.Change_psd;
import com.users.Ring_up;
import com.users.Sign_in;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int i = 0, k = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	// HttpServlet九大内置对象：request、response、Session、Coolie、ServletContext、Application
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (SQLmain.statement == null) {
			System.out.println(234);
			SQLmain.One();
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		{
			HttpSession session = request.getSession();
			int time = session.getMaxInactiveInterval();
			Date T = new Date(session.getLastAccessedTime());
			System.out.println(session.getId() + "\n" + session.isNew() + "\n" + time + "\n" + T);
			if (session.isNew()) {
				System.out.println("这是新会话");
			} else {
				System.out.println("这是旧会话");
			}
		}
		String order = request.getParameter("order");// 接收任务主命令（ring_up：登录、）
		String content = request.getParameter("contents");// 接收任务内容
		String user = request.getParameter("user");// 访问来源
		String Book_category = request.getParameter("book_category");// 书籍操作子命令,字段名
		String Start = request.getParameter("start");// 起始查询条数
		String Quantity = request.getParameter("quantity");// 需要书本量
		String Book_ID = request.getParameter("book_id");// 单本书籍操作ID
		/**
		 * 将content内容存入JSON对象
		 */
		// 计算Web端和android访问次数
		if (user.equals("web")) {
			i++;
		} else if (user.equals("android")) {
			k++;
		}
		System.out.println("web端访问次数：" + i + "\nandroid端访问次数：" + k);
		OutputStream os = response.getOutputStream();
		ORDER(order, Book_category, Start, Quantity, Book_ID, content, os);// 调用信息处理总方法
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void ORDER(String order, String Book_category, String Start, String Quantity, String Book_ID, String content,
			OutputStream os) {
		try {
			switch (order) {
			case "verify":// 测试连接
				System.out.println(content);
				os.write(content.getBytes("UTF-8"));
				break;
			case "ring_up":// 用户登录
				Ring_up ring_up = new Ring_up(((JSONdispose) new JSONdispose()).JsonRing_up(content));
				System.out.println(ring_up.Ring());
				os.write(ring_up.Ring().getBytes("UTF-8"));
				break;
			case "sign_in":// 用户注册
				Sign_in sign_in = new Sign_in(((JSONdispose) new JSONdispose()).JsonRing_up(content));
				os.write(sign_in.Sign().getBytes("UTF-8"));
				break;
			case "change_pwd":// 修改密码
				Change_psd change_psd = new Change_psd(((JSONdispose) new JSONdispose()).JsonRing_up(content));
				os.write(change_psd.change_psd().getBytes("UTF-8"));
				break;
			case "no_break":// 畅销榜、新书榜、推荐榜（收藏量）
				No_Break no_break = new No_Break("book_record");
				os.write(no_break.no_break().getBytes("UTF-8"));
				break;
			case "books":// 分类分页按量查询
				Books_Letter book_letter = new Books_Letter(Book_category, Start, Quantity);
				System.out.println(book_letter.bool_letter());
				os.write(book_letter.bool_letter().getBytes("UTF-8"));
				break;
			case "book":// 书籍浏览、购买、收藏
				Book book = new Book(Book_ID, Book_category);
				break;
			default:
				os.write("order命令有误".getBytes("UTF-8"));
				break;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
