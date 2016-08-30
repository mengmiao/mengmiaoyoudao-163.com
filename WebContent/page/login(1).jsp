<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
<link type="text/css" rel="stylesheet" href="../css/login.css" />
<script type="text/javascript" src="../js/jquery-3.0.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#conn")
				.click(
						function() {
							var order = $("#order").val();
							var book_category = $("#book_category").val();
							var start = $("#start").val();
							var quantity = $("#quantity").val();
							$
									.post(
											"http://192.168.1.104:8080/Book_Servlet/Main.do",
											{
												order : order,
												book_category : book_category,
												start : start,
												quantity : quantity,
												contents : "{\"contents\":[{\"content\":\"mengmiao\"},{\"content\":\"mengmiao\"}]}",
												user : "web"
											},
											function(data, status) {
												alert("Data:" + data
														+ "\nStatus:" + status);
											});
						});
	});

	$(function() {
		$("#conna").click(function() {
			alert(123);
		});
	});
	$(function() {
		$("#login").click(function() {
			var user = $("#name").val();
			$.post("http://172.18.4.16:8080/Book_Servlet/Verify.do", {
				ID : "verify",
				name : user
			}, function(data, status) {
				alert("Data:" + data + "\nStatus:" + status);
			});
		});
	});
</script>
</head>
<body>
	order
	<input type="text" id="order" name="user" />
	<br /> book_category
	<input type="text" id="book_category" name="user" />
	<br /> start
	<input type="text" id="start" name="user" />
	<br /> quantity
	<input type="text" id="quantity" name="user" />
	<br />
	<input type="button" value="查询" id="conn" />
	<input type="button" value="登录" id="login" />
	<input type="button" value="登录a" id="conna" />
</body>
</html>