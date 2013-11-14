<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
	<jsp:attribute name="css">
		<link rel="stylesheet" href="dispatched-pickings.css">
	</jsp:attribute>
	<jsp:body>
		<h1>Dispatched pickings</h1>
		<ol>
			<c:forEach var="picking" items="${dispatchedPickings}">
				<li>
					<input type="hidden" id="pickingId" name="${picking.id}" value="${picking.id}">
					<span class="pickingId">Picking Id: ${picking.id}</span>
					<span class="pickingSize">Size: ${picking.size.width}x${picking.size.length}x${picking.size.height}</span>
					<a href="details?pickingId=${picking.id}" class="details">View details</a>
				</li>
			</c:forEach>
		</ol>
	</jsp:body>
</t:genericpage>