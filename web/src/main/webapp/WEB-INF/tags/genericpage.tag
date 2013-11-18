<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ attribute name="googleMapScript" fragment="true" %>
<%@ attribute name="menu" fragment="true" %>
<%@ attribute name="css" fragment="true" %>
<html>
<head>
	<jsp:invoke fragment="css"/>
	<jsp:invoke fragment="googleMapScript" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ma-desy</title>
</head>
<body>
	<div id="pageheader">
		<jsp:invoke fragment="menu"/>
		<form method="POST" action="../logout">
			<input type="submit" value="Logout">
		</form>
	</div>
	<div id="body">
		<jsp:doBody />
	</div>
	<div id="pagefooter">
		
	</div>
</body>
</html>