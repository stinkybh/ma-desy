<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
<jsp:body>
	<h1>New picking</h1>
	<form method="POST" action="new-picking">
		<div class="sender_panel">
			<div class="controls">
				<input type="text" name="senderName" id="senderName" placeholder="Sender">
				<br>
				<input type="text" name="senderAddress" id="senderAddress">
			</div>
			<div class="map">
			
			</div>
		</div>
		<div class="receiver_panel">
			<div class="controls">
				<input type="text" name="receiverName" id="receiverName" placeholder="Receiver">
				<br>
				<input type="text" name="receiverAddress" id="receiverAddress">
			</div>
			<div class="map">
			
			</div>
		</div>
		<div class="picking_info">
			<h3>Picking size:</h3>
			<br>
			<label for="picking_width">width:</label>
			<input type="text" name="pickingWidth" id="pickingWidth">
			<label for="picking_length">length:</label>
			<input type="text" name="pickingLength" id="pickingLength">
			<label for="picking_height">height:</label>
			<input type="text" name="pickingHeight" id="pickingHeight">
		</div>
		<input type="submit" value="Save">
	</form>
</jsp:body>
</t:genericpage>