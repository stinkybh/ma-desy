<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
	<jsp:attribute name="css">
		<link rel="stylesheet" href="dispatched-pickings.css">
	</jsp:attribute>
	<jsp:body>
		<b>Reports</b>
		<ul>
		<c:forEach var="report" items="${reports}">
			<li>
				<a href="report?id=${report.id}">
					<c:out value=" ${report}"/>
				</a>
			</li>
		</c:forEach>
		</ul>
		<br />
		<br />
		<a href="create-report">Create report</a>
	</jsp:body>
</t:genericpage>
=======
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
	<h1>Hello manager!</h1>
	<b>Reports</b>
	<ul>
		<c:forEach var="report" items="${reports}">
			<li><a href="report?id=${report.id}"> <c:out
						value=" ${report}" />
			</a></li>
		</c:forEach>
	</ul>
	<br />
	<br />
	
	<form name="input" action="create-report" method="post"> 
		<input type="submit" value="Create Report">
	</form>
	
</body>
</html>
>>>>>>> ce7b42af12fea1881ad2b9c05879a2a1e52ac6c3
