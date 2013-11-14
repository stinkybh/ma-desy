<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
	<jsp:attribute name="css">
		<link rel="stylesheet" href="picking-details.css">
	</jsp:attribute>
	<jsp:body>
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
			<a href="dispatched-pickings" class="back">Go back</a>
		</form>
	</div>
	</jsp:body>
</t:genericpage>