<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld" %>

<div class="container-fluid ">
	<div class="row-fluid m-5">
		<form action="${pageContext.request.contextPath}/admin-find-routes"
			method="post">

			<div class="row p-2 mb-3 bg-success rounded">

				<div class="col-lg-3 col-xs-12 text-white ">
					<label for="departureStation"><fmt:message key="find.route.label.departure.station" /></label> <select
						class="form-control" name="departureStationId"
						id="departureStation" required>
						<option disabled selected><fmt:message key="find.route.label.choose.station" /></option>
						<c:forEach items="${applicationScope['contextStations']}"
							var="departureStation">
							<option value="${departureStation.getId()}">
								<t:stationName station="${departureStation}" locale="${lang}" contextLanguages="${applicationScope['contextLanguages']}"/>
							</option>
						</c:forEach>
					</select>
				</div>


				<div class="col-lg-3 col-xs-12 text-white ">
					<label for="arrivalStation"><fmt:message key="find.route.label.arrival.station" /></label> <select
						class="form-control" name="arrivalStationId" id="arrivalStation"
						required>
						<option disabled selected><fmt:message key="find.route.label.choose.station" /></option>
						<c:forEach items="${applicationScope['contextStations']}"
							var="arrivalStation">
							<option value="${arrivalStation.getId()}">
							<t:stationName station="${arrivalStation}" locale="${lang}" contextLanguages="${applicationScope['contextLanguages']}"/></option>
						</c:forEach>
					</select>
				</div>


				<div class="col-lg-3 col-xs-12 text-white">
					<label for="date"><fmt:message key="find.route.label.date" /></label> <input type="date" name="date"
						class="form-control" required>

				</div>
				<div class="col-lg-3 col-xs-12 text-white">
					<button type="submit"
						class="btn btn-info btn-block text-center  mt-3">
						<h3>
							<fmt:message key="button.find" />
						</h3>
					</button>
				</div>
			</div>
		</form>
	</div>
</div>