<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
	<jsp:body>
	<h1>Track picking ${picking.id}</h1>
    	<c:choose>
    		<c:when test="${picking != null}">
    	<b><c:out value="Picking id: ${picking.id}" /></b>
		<br />
    	<c:out value="Size: ${picking.size.width}:${picking.size.height}:${picking.size.length}" />
		<br />
		<c:out value="Sender: ${picking.sender.name}" /> <br />
		<c:out value="Sender address: ${picking.sender.address}" /> <br />
    	<c:out value="Receiver: ${picking.receiver.name}" />
		<br />
    	<c:out value="Receiver address: ${picking.receiver.address}" />
		<br />
		<c:if test="${picking.courierId != null}">
    		<c:out value="Courier id: ${picking.courierId}" />
    		<br />
    	</c:if>
    	<c:out value="Status: ${picking.pickingStatus}" />
    		</c:when>
    	<c:otherwise>
    		<b>Picking not found!</b>
    	</c:otherwise>
    	</c:choose>
</jsp:body>
</t:genericpage>