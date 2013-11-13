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
	<h1>Dispatched pickings</h1>
	<form method="POST" action="deliver">
		<ul>
			<c:forEach var="picking" items="${dispatchedPickings}">
				<li>
					<input type="hidden" id="pickingId" value="${picking.id}">
					<span class="pickingId">Picking Id: ${picking.id}</span>
					<span class="senderName">${picking.sender.name}</span>
					<span class="receiverName">${picking.receiver.name}</span>
					<span class="receiverAddress">${picking.receiver.address}</span>
					<input type="submit" value="Deliver">
				</li>
			</c:forEach>
		</ul>
	</form>
</body>
</html>