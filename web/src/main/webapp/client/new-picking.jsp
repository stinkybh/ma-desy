<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="maps" uri="http://ma-desy.com/maps" %>

<t:genericpage>
	<jsp:attribute name="googleMapScript">
		<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	</jsp:attribute>
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="/resources/css/style.css"></link>
	</jsp:attribute>
	<jsp:attribute name="menu">
		<ul id="menu" class="menu">
			<li><a href="new-picking"><span>New picking</span></a></li>
			<li><a href="view-pickings"><span>View pickings</span></a></li>
		</ul>
	</jsp:attribute>
	
	<jsp:body>
		<h1>New picking</h1>
		<form method="POST" action="new-picking">
			<div class="sender_panel">
				<div class="controls">
					<input type="text" name="senderName" id="senderName" placeholder="Sender">
					<br>
					<maps:map-widget widgetId = "sender" />
				</div>

			</div>
			<div class="receiver_panel">
				<div class="controls">
					<input type="text" name="receiverName" id="receiverName" placeholder="Receiver">
					<br>
					<maps:map-widget widgetId = "receiver" />
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