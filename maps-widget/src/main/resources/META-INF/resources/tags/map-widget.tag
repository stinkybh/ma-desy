<%@ tag pageEncoding="UTF-8" isELIgnored="false" %>
<%@ attribute name="widgetId" required="true" %>
<%@ attribute name="mapId" required="true" %>
<%@ attribute name="textId" required="true" %>
<%@ attribute name="buttonId" required="true" %>
<%@ attribute name="hiddenId" required="true" %>

<link rel="stylesheet" type="text/css" href="css/style.css"></link>
<script src="js/geocode.js"></script>

<div id="${widgetId}">
	<div class="panel">
		<input id="${textId}" type="text" value="" />
		<input id="${buttonId}" type="button" value="Search" />
		<input id="${hiddenId}" type="hidden" />
	</div>
	<div id="${mapId}" style="opacity:0;" class="map-canvas"></div>
</div>

<script>
	new MapWidget("${mapId}", "${textId}", "${buttonId}", "${hiddenId}").initialize();
</script>


