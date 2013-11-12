<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
<jsp:body>
	<h1>New picking</h1>
	<form method="POST" action="new-picking">
		<div class="sender_panel">
			<div class="controls">
				<input type="text" name="sender" id="senderName" placeholder="Sender">
				<br>
				<input type="text" name="address" id="senderAddress">
			</div>
			<div class="map">
			
			</div>
		</div>
		<div class="receiver_panel">
			<div class="controls">
				<input type="text" name="receiver" id="receiverName" placeholder="Receiver">
				<br>
				<input type="text" name="address" id="receiverAddress">
			</div>
			<div class="map">
			
			</div>
		</div>
		<div class="picking_info">
			<h3>Picking size:</h3>
			<br>
			<label for="picking_width">width:</label>
			<input type="text" id="pickingWidth">
			<label for="picking_length">length:</label>
			<input type="text" id="pickingLength">
			<label for="picking_height">height:</label>
			<input type="text" id="pickingHeight">
		</div>
		<input type="submit" value="Save">
	</form>
</jsp:body>
</t:genericpage>