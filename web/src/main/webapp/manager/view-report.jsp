<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Report details</h1>
	<br />
	<b><c:out value="Report id: ${report.id}" /></b>
	<br />
	<br />
	<c:forEach var="entry" items="${report.courrierPickings}">
		<c:out value="Courier: ${entry.key} - total: ${entry.value}" />
		<br />
	</c:forEach>
	<br />
	<c:forEach var="val" items="${report.pickingsReport}">
		<c:out value="${val}" />
		<br />
	</c:forEach>
	<br />
	<a href="manager">Back</a>
</body>
</html>