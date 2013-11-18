function MapWidget(content, text, button, hidden) {
	var geocoder = null;
	var map = null;
	var marker = null;
	var input = null;
	var autocomplete = null;
	var mapContent = content;
	var searchText = text;
	var searchButton = button;
	var searchHidden = hidden;
	var infowindow = null;

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
									infowindow.setContent(results[0].formatted_address);
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