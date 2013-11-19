<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericpage>

	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="/resources/css/style.css"></link>
	</jsp:attribute>
	<jsp:attribute name="menu">
		<div id="tabs" class="tabs">
			<ul id="menu" class="menu">
				<li class="last"><a href="new-picking"><span>New picking</span></a></li>
				<li class="active"><a href="view-pickings"><span>View pickings</span></a></li>
			</ul>
		</div>
	</jsp:attribute>
	
	<jsp:body>
	<h1>View pickings</h1>
	<ul>
	<c:forEach var="picking" items="${pickings}">
    	<li>
    	<c:out value="Picking id: ${picking.id}, " />
    	<c:out value="Size: ${picking.size.width}:${picking.size.height}:${picking.size.length}"/>
    	<c:out value="Receiver: ${picking.receiver.name}, Receiver address: ${picking.receiver.address}"/>
    	<a href="track-picking?id=${picking.id}">Track</a>
    	</li>
	</c:forEach>
	</ul>
</jsp:body>
</t:genericpage>