<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/login.css"></link>
<title>Insert title here</title>
</head>
<body>
	<div id="loginForm" class="loginForm">
		<h1>Login on ma-desy</h1>
		<form method="POST" action="login" >
			<input type="text" name="username" id="username" placeholder="Username"><br/>
			<input type="password" name="password" placeholder="Password"><br/>
			<input type="submit" value="Login">
		</form>
	</div>
</body>
</html>