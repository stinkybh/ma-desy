<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="maps" uri="http://ma-desy.com/maps"%>

<t:genericpage>
	<jsp:attribute name="script">
		<script
			src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
	</jsp:attribute>
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css"
			href="/resources/css/newPicking.css"></link>
		<link rel="stylesheet" type="text/css" href="/resources/css/style.css"></link>
	</jsp:attribute>
	<jsp:attribute name="menu">
		<div id="tabs" class="tabs">
			<ul id="menu" class="menu">
				<li class="active"><a href="new-picking"><span>New picking</span></a></li>
				<li class="last"><a href="view-pickings"><span>View pickings</span></a></li>
			</ul>
		</div>
	</jsp:attribute>

	<jsp:body>
		<h1>New picking</h1>
		<form method="POST" action="new-picking">
			<div id="clientInfo" class="clientInfo">
				<h3>Sender: </h3>
				<br/>
				<div class="sender_panel">
					<input type="text" name="senderName" id="senderName"
					placeholder="Sender name">
					<br>
					<maps:map-widget widgetId="sender" />
				</div>
				<h3>Receiver: </h3>
				<br/>
				<div class="receiver_panel">
					<input type="text" name="receiverName" id="receiverName"
					placeholder="Receiver name">
					<br>
					<maps:map-widget widgetId="receiver" />
				</div>
			</div>
			<div class="pickingInfo">
				<h3>Picking size:</h3>
				<br>
				<label for="picking_width">width:</label>
				<input type="text" name="pickingWidth" id="pickingWidth"> <br/>
				<label for="picking_length">length:</label>
				<input type="text" name="pickingLength" id="pickingLength"> <br/>
				<label for="picking_height">height:</label>
				<input type="text" name="pickingHeight" id="pickingHeight"> <br/>
			</div>
			<div class="save">
				<input type="submit" value="Save" id="saveBtn">
			</div>
		</form>
	</jsp:body>
</t:genericpage>