<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<title>Simple markers</title>
<link rel="stylesheet" href="css/style.css" />
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<script src="js/geocode.js"></script>
<script>
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<div class="panel">
		<input id="searchAddress" type="text" value="">
		<input id="seatchBtn" type="button"
			value="Search" onclick="codeAddress()">
	</div>
	<div id="map-canvas"></div>
</body>
</html>


