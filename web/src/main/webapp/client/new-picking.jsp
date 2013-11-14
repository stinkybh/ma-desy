<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="maps" uri="http://ma-desy.com/maps" %>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<t:genericpage>
	<jsp:attribute name="menu">
		<ul>
			<li><a href="new-picking">New picking</a></li>
			<li><a href="view-pickings">View pickings</a></li>
		</ul>
	</jsp:attribute>
	<jsp:body>
		<h1>New picking</h1>
		<form method="POST" action="new-picking">
			<div class="sender_panel">
				<div class="controls">
					<input type="text" name="senderName" id="senderName" placeholder="Sender">
					<br>
					<maps:map-widget widgetId = "sender" mapId="senderMap" textId="senderAddress"
						buttonId="senderSearchBtn" hiddenId="senderHidden" />
				</div>

			</div>
			<div class="receiver_panel">
				<div class="controls">
					<input type="text" name="receiverName" id="receiverName" placeholder="Receiver">
					<br>
					<maps:map-widget widgetId = "receiver" mapId="recvMap" textId="receiverAddress"
					buttonId="recvSearchBtn" hiddenId="recvHidden" />
				</div>

			</div>
			<div class="picking_info">
				<h3>Picking size:</h3>
				<br>

			</div>
			<div class="map">
			
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