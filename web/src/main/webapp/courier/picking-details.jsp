<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="courier/picking-details.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Picking details</title>
</head>
<body>
	<div class="container">
		<div class="receiverDetails">
			<span class="label">Receiver name: </span>
			<span class="personName">${dispatchedPicking.receiver.name}</span> <br>
			<span class="label">Receiver address: </span>
			<span class="personAddress">${dispatchedPicking.receiver.address}</span>
		</div>
		<div class="senderDetails">
			<span class="label">Sender name: </span>
			<span class="personName">${dispatchedPicking.sender.name}</span> <br>
			<span class="label">Sender address: </span>
			<span class="personAddress">${dispatchedPicking.sender.address}</span>
		</div>
		<div class="pickingDetails">
			<h3>Picking details:</h3>
			<div class="section">
				<span class="section-head">Size:</span>
				<span class="label">width:</span>
				<span class="item-content">${dispatchedPicking.size.width}</span>
				<span class="label">length:</span>
				<span class="item-content">${dispatchedPicking.size.length}</span>
				<span class="label">height:</span>
				<span class="item-content">${dispatchedPicking.size.height}</span>
			</div>
		</div>
		<form method="POST" action="deliver">
			<input type="hidden" name="pickingId" value="${dispatchedPicking.id}">
			<input type="submit" value="Deliver">
		</form>
	</div>
</body>
</html>