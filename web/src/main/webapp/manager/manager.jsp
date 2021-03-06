<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
	<jsp:attribute name="css">
		<link rel="stylesheet" href="dispatched-pickings.css">
	</jsp:attribute>
	<jsp:body>
		<b>Reports</b>
		<ul>
		<c:forEach var="report" items="${reports}">
			<li>
				<a href="report?id=${report.id}">
					<c:out value=" ${report}"/>
				</a>
			</li>
		</c:forEach>
		</ul>
		<br />
		<br />
		<form name="input" action="create-report" method="post"> 
			<input type="submit" value="Create Report">
		</form>
	</jsp:body>
</t:genericpage>