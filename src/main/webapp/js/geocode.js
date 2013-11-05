var geocoder;
var map;
var infowindow = new google.maps.InfoWindow();
var marker;
var input;
var autocomplete;

function initialize() {
	geocoder = new google.maps.Geocoder();
	var myLatlng = new google.maps.LatLng(43.204623134706104,
			27.918065317790933);
	var mapOptions = {
		zoom : 15,
		center : myLatlng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	marker = new google.maps.Marker({
		position : myLatlng,
		map : map,
		title : 'Where I am',
		draggable : true
	});

	input = (document.getElementById('searchAddress'));
	autocomplete = autocomplete = new google.maps.places.Autocomplete(input);
	autocomplete.bindTo('bounds', map);

	bind();
};

function bind() {
	google.maps.event.addListener(marker, "mouseup", function(event) {
		codeLatLng(event.latLng);
	});
};

function codeLatLng(latLng) {
	var lat = latLng.lat();
	var lng = latLng.lng();
	var latlng = new google.maps.LatLng(lat, lng);

	geocoder.geocode({
		'latLng' : latlng
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if (results[1]) {
				infowindow.setContent(results[0].formatted_address);
				infowindow.open(map, marker);
			} else {
				alert('No results found');
			}
		} else {
			alert('Geocoder failed due to: ' + status);
		}
	});
};

function codeAddress() {
	var address = document.getElementById('searchAddress').value;
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