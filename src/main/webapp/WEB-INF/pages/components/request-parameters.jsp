<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${routes != null}">
	<input type="hidden" name="arrivalStationId"
		value="${routes.get(0).getArrivalStop().getStationId()}">
	<input type="hidden" name="departureStationId"
		value="${routes.get(0).getDepartureStop().getStationId()}">
	<input type="hidden" name="date"
		value="${routes.get(0).getDepartureStop().getDepartureDate()}">
</c:if>

<c:if test="${route != null}">
	<input type="hidden" name="arrivalStationId"
		value="${route.getArrivalStop().getStationId()}">
	<input type="hidden" name="departureStationId"
		value="${route.getDepartureStop().getStationId()}">
	<input type="hidden" name="routeId" value="${route.getId()}">
	<input type="hidden" name="routeCarriageNumber"
		value="${routeCarriage.getNumber()}" />
</c:if>

<c:if test="${arrivalStationId != null}">
	<input type="hidden" name="arrivalStationId"
		value="${arrivalStationId}">
</c:if>

<c:if test="${departureStationId != null}">
	<input type="hidden" name="departureStationId"
		value="${departureStationId}">
</c:if>

<c:if test="${carriageId != null}">
	<input type="hidden" name="carriageId"
		value="${carriageId}">
</c:if>

<c:if test="${routeId != null}">
	<input type="hidden" name="routeId" value="${routeId}">
</c:if>

<c:if test="${routeCarriage != null}">
	<input type="hidden" name="routeCarriageNumber"
		value="${routeCarriage.getNumber()}" />
</c:if>

<c:if test="${departureStationId != null}">
	<input type="hidden" name="departureStationId"
		value="${departureStationId}">
</c:if>

<c:if test="${arrivalStationId != null}">
	<input type="hidden" name="arrivalStationId"
		value="${arrivalStationId}">
</c:if>

<c:if test="${routeCode != null}">
	<input type="hidden" name="routeCode" value="${routeCode}">
</c:if>

<c:if test="${stationId != null}">
	<input type="hidden" name="stationId" value="${stationId}">
</c:if>