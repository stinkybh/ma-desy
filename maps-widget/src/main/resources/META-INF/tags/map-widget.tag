<%@ tag pageEncoding="UTF-8" isELIgnored="false" %>
<%@ attribute name="widgetId" required="true" %>

<link type="text/css" rel="stylesheet" href="/maps-widget/css/style.css"></link>
<script type="text/javascript" src="/maps-widget/js/geocode.js"></script>

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


