<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<div id="pageheader">
		<ul>
			<li><a href="new-picking">New picking</a></li>
			<li><a href="view-pickings">View pickings</a></li>
		</ul>
		<form method="POST" action="logout">
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