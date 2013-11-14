<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
	<jsp:attribute name="css">
		<link rel="stylesheet" href="dispatched-pickings.css">
	</jsp:attribute>
	<jsp:body>
		<h1>Report details</h1>
		<br />
		<b><c:out value="Report id: ${report.id}" /></b>
		<br />
		<br />
		<c:forEach var="entry" items="${report.courrierPickings}">
			<c:out value="Courier: ${entry.key} - total: ${entry.value}" />
			<br />
		</c:forEach>
		<br />
		<c:forEach var="val" items="${report.pickingsReport}">
			<c:out value="${val}" />
			<br />
		</c:forEach>
		<br />
		<a href="manager">Back</a>
	</jsp:body>
</t:genericpage>