<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="courier/dispatched-pickings.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dispatched pickings</title>
</head>
<body>
	<h1>Dispatched pickings</h1>
	<ul>
		<c:forEach var="picking" items="${dispatchedPickings}">
			<li>
				<input type="hidden" id="pickingId" name="${picking.id}" value="${picking.id}">
				<span class="pickingId">Picking Id: ${picking.id}</span>
				<span class="pickingSize">Size: ${picking.size.width}x${picking.size.length}x${picking.size.height}</span>
				<a href="details?pickingId=${picking.id}" class="details">View details</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>