<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>

<div class="modal fade" id="addStop" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"><fmt:message key="label.new.stop" /></h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<form
						action="${pageContext.request.contextPath}/admin-save-route-stop"
						method="post">
						<div class="form-group">
							<label for="station"><fmt:message key="table.station" /></label><select class="form-control"
								name="station" id="station" required>
								<c:forEach items="${applicationScope['contextStations']}"
									var="routeStation">
									<option value="${routeStation.getId()}"><t:stationName locale="${lang}" contextLanguages="${applicationScope['contextLanguages']}" station="${routeStation}"/></option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group w-100">
							<label for="arrivalDateTime"><fmt:message key="table.arrival" /></label>
							<div class="row w-100" id="arrivalDateTime">
								<div class="col-7">
									<label for="arrivalDate"><fmt:message key="find.route.label.date" /></label><input type="date"
										name="arrivalDate" id="arrivalDate" class="form-control"
										required value="${sessionScope.minDate}"
										min="${sessionScope.minDate}" max="${sessionScope.maxDate}"/>
								</div>
								<div class="col-5">
									<label for="arrivalTime"><fmt:message key="label.time" /></label><input type="time"
										name="arrivalTime" id="arrivalTime" class="form-control"
										required value="${sessionScope.minTime}"/>
								</div>
							</div>
						</div>

						<div class="form-group w-100">
							<label for="departureDateTime"><fmt:message key="table.departure" /></label>
							<div class="row w-100" id="departureDateTime">
								<div class="col-7">
									<label for="departureDate"><fmt:message key="find.route.label.date" /></label><input type="date"
										name="departureDate" id="departureDate" class="form-control"
										required value="${sessionScope.minDate}"
										min="${sessionScope.minDate}" max="${sessionScope.maxDate}"/>
								</div>


								<div class="col-5">
									<label for="departureTime"><fmt:message key="label.time" /></label><input type="time"
										name="departureTime" id="departureTime" class="form-control"
										required value="${sessionScope.minTime}"/>
								</div>
							</div>
						</div>

						<div class="form-group">

							<label for="price"><fmt:message key="label.price.to.next.station" /></label> <input
								type="number" name="price" id="price" step="0.01"
								class="form-control" min="0" value="0">
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-outline-primary btn-lg"><fmt:message key="button.save" /></button>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
