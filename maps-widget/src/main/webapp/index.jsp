<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="maps" uri="http://ma-desy.com/maps" %>

<!DOCTYPE HTML>
<html>
<head>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>

<title>Maps</title>

</head>
<body>
	<div id="sender">
		<label>Sender</label>
		<maps:map-widget widgetId = "sender" mapId="senderMap" textId="senderSearchAddress"
			buttonId="senderSearchBtn" hiddenId="senderHidden" />
	</div>
	<div id="recv">
		<label>Receiver</label>
		<maps:map-widget widgetId = "receiver" mapId="recvMap" textId="recvSearchAddress"
			buttonId="recvSearchBtn" hiddenId="recvHidden" />
	</div>
</body>
</html>