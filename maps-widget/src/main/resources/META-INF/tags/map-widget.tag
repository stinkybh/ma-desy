<%@ tag pageEncoding="UTF-8" isELIgnored="false" %>
<%@ attribute name="widgetId" required="true" %>

<style>
.map-canvas {
	position: relative;
	height: 200px;
	width: 40%;
	top: 30px;
	padding: 5px;
	margin-left: 450px;
}

.panel {
	float: left;
	position: relative;
	width: 430px;
	top: 30px;
	background-color: #fff;
	padding: 5px;
}

.panel input[type="text"] {
	width: 320px;
	height: 25px;
	outline: none;
}

.panel input[type="button"] {
	outline: none;
	-moz-box-shadow: inset 0px 1px 0px 0px #bbdaf7;
	-webkit-box-shadow: inset 0px 1px 0px 0px #bbdaf7;
	box-shadow: inset 0px 1px 0px 0px #bbdaf7;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #79bbff
		), color-stop(1, #378de5));
	background: -moz-linear-gradient(center top, #79bbff 5%, #378de5 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#79bbff',
		endColorstr='#378de5');
	background-color: #79bbff;
	-webkit-border-top-left-radius: 0px;
	-moz-border-radius-topleft: 0px;
	border-top-left-radius: 0px;
	-webkit-border-top-right-radius: 0px;
	-moz-border-radius-topright: 0px;
	border-top-right-radius: 0px;
	-webkit-border-bottom-right-radius: 0px;
	-moz-border-radius-bottomright: 0px;
	border-bottom-right-radius: 0px;
	-webkit-border-bottom-left-radius: 0px;
	-moz-border-radius-bottomleft: 0px;
	border-bottom-left-radius: 0px;
	text-indent: 0;
	border: 1px solid #84bbf3;
	display: inline-block;
	color: #ffffff;
	font-family: Arial;
	font-size: 15px;
	font-weight: bold;
	font-style: normal;
	height: 37px;
	line-height: 10px;
	width: 90px;
	text-decoration: none;
	text-align: center;
	text-shadow: 1px 1px 0px #528ecc;
}

.panel input[type="button"]:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #378de5
		), color-stop(1, #79bbff));
	background: -moz-linear-gradient(center top, #378de5 5%, #79bbff 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#378de5',
		endColorstr='#79bbff');
	background-color: #378de5;
}

.panel input[type="button"]:active {
	position: relative;
	top: 1px;
}
</style>

<script>
function MapWidget(content, text, button, hidden) {
	var geocoder;
	var map;
	var marker;
	var input;
	var autocomplete;
	var mapContent = content;
	var searchText = text;
	var searchButton = button;
	var searchHidden = hidden;
	var infowindow;

	this.initialize = function() {
		geocoder = new google.maps.Geocoder();
		infowindow = new google.maps.InfoWindow();
		
		var myLatlng = new google.maps.LatLng(43.204623134706104, 27.918065317790933);
		
		var mapOptions = {
			zoom : 15,
			center : myLatlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		
		map = new google.maps.Map(document.getElementById(mapContent), mapOptions);

		marker = new google.maps.Marker({
			position : myLatlng,
			map : map,
			title : 'Get address',
			draggable : true
		});

		input = (document.getElementById(searchText));
		var options = {
			componentRestrictions : {
				country : 'BG'
			}
		};
		autocomplete = new google.maps.places.Autocomplete(input, options);
		autocomplete.bindTo('bounds', map);

		bind();
	};
	
	function bind() {
		document.getElementById(searchButton).onclick = function() {
			codeAddress();
			document.getElementById(mapContent).style.opacity = "1";
		};
		
		google.maps.event.addListener(marker, "mouseup", function(event) {
			codeLatLng(event.latLng);
		});
	};

	function codeLatLng(latLng) {
		var lat = latLng.lat();
		var lng = latLng.lng();
		var latlng = new google.maps.LatLng(lat, lng);

		geocoder
				.geocode({
							'latLng' : latlng
						},
						function(results, status) {
							if (status == google.maps.GeocoderStatus.OK) {
								if (results[1]) {
									infowindow
											.setContent(results[0].formatted_address);
									infowindow.open(map, marker);
									document.getElementById(searchText).value = results[0].formatted_address;
									document.getElementById(searchHidden).value = results[0].geometry.location;
								} else {
									alert('No results found');
								}
							} else {
								alert('Geocoder failed due to: ' + status);
							}
						});
	};

	function codeAddress() {
		var address = document.getElementById(searchText).value;
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				var location = results[0].geometry.location;
				map.setCenter(location);
				marker.setPosition(location);
				codeLatLng(location);
			} else {
				alert('Geocode was not successful for the following reason: '
						+ status);
			}
		});
	};
}
</script>

<div id="${widgetId}">
	<div class="panel">
		<input id="${widgetId}Address" type="text" name="${widgetId}Address"/>
		<input id="${widgetId}Button" type="button" name="${widgetId}Button" value="Search" />
		<input id="${widgetId}Hidden" type="hidden" name="${widgetId}Hidden"/>
	</div>
	<div id="${widgetId}Map" style="opacity:0;" class="map-canvas"></div>
</div>

<script>
	new MapWidget("${widgetId}Map", "${widgetId}Address", "${widgetId}Button", "${widgetId}Hidden").initialize();
</script>


