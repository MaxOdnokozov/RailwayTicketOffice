<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>
	<div>
		<div class="container-fluid p-0">
			<img src="static/img/header/station.jpg" alt="" class="d-block w-100">
		</div>
	</div>
	<div class="display-3 mt-3 text-center">Adding a new route</div>

	<div class="container-fluid">
		<div class="h1 mt-3 text-center">

			<c:if test="${sessionScope.code == null}">
					Route code: ----
					</c:if>
			<c:if test="${sessionScope.code != null}">
					Route code: ${sessionScope.code}
					</c:if>
			<button class="btn btn-outline-primary btn-lg ml-3" href="#"
				data-toggle="modal" data-target="#addCode">Edit</button>


		</div>
	</div>
	<div class="row w-100">
		<div class="col-lg-1 col-md-12 col-xs-12"></div>
		<div class="col-lg-10 col-md-12 col-xs-12">
			<div class="h4 mt-3 text-center">Stops</div>
			<div class="h5 mt-3 ml-3">Date :
				${sessionScope.stops.get(0).getArrivalDate()}</div>
			<div class="table-responsive">
				<table class="table table-hover text-center p-2">
					<thead>
						<tr>
							<th scope="col">№</th>
							<th scope="col">Station</th>
							<th scope="col">Arrival date</th>
							<th scope="col">Arrival time</th>
							<th scope="col">Departure date</th>
							<th scope="col">Departure time</th>
							<th scope="col">Price</th>
							<th scope="col">Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessionScope.stops}" var="stop">
							<tr>
								<td>${sessionScope.stops.indexOf(stop)+1}</td>
								<td><c:forEach
										items="${applicationScope['contextStations']}" var="station">
										<c:if test="${station.getId() == stop.getStationId()}">
									<t:stationName locale="${lang}" contextLanguages="${applicationScope['contextLanguages']}" station="${station}"/>
								</c:if>
									</c:forEach></td>
								<td>${stop.getArrivalDate()}</td>
								<td>${stop.getArrivalTime()}</td>
								<td>${stop.getDepartureDate()}</td>
								<td>${stop.getDepartureTime()}</td>
								<td>${stop.getPrice()}</td>
								<td><form action="${pageContext.request.contextPath}/admin-delete-route-stop" method="post">
									<input type="hidden" name="stopNumber" value="${sessionScope.stops.indexOf(stop)}"/>
									<button type="submit"
									class="btn btn-outline-danger btn-block">Delete</button>
								</form>	</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<button href="#" data-toggle="modal" data-target="#addStop"
				class="btn btn-outline-primary btn-lg ml-3 mt-3">Add route
				stop</button>
		</div>
		<div class="col-lg-1 col-md-12 col-xs-12"></div>
	</div>

	<div class="row w-100">
		<div class="col-lg-1 col-md-12 col-xs-12"></div>
		<div class="col-lg-10 col-md-12 col-xs-12">
			<div class="h4 mt-3 text-center">Train carriages</div>
			<div class="table-responsive">
				<table class="table table-hover text-center mt-2 p-2" id="carriages">
					<thead>
						<tr class="m-2">
							<th scope="col">№</th>
							<th scope="col">Model</th>
							<th scope="col">Type</th>
							<th scope="col">Total seats</th>
							<th scope="col">Price coefficient</th>
							<th scope="col">Image</th>
							<th scope="col">Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessionScope.routeCarriages}"
							var="routeCarriage">
							<tr>
								<td>${routeCarriages.indexOf(routeCarriage) + 1}</td>
								<td>${routeCarriage.getCarriage().getModel()}</td>
								<td>${routeCarriage.getCarriage().getComfortType()}</td>
								<td>${routeCarriage.getCarriage().getTotalSeats()}</td>
								<td>${routeCarriage.getCarriage().getPriceCoefficient()}</td>
								<td><a class="fancybox"
									href="${pageContext.request.contextPath}${applicationScope['contextImagesDir']}/${routeCarriage.getCarriage().getImage()}">
										<img class="img-fluid h-25"
										src="${pageContext.request.contextPath}${applicationScope['contextImagesDir']}/${routeCarriage.getCarriage().getImage()}">
								</a></td>
								<td><form action="${pageContext.request.contextPath}/admin-delete-route-carriage" method="post">
									<input type="hidden" name="routeCarriageNumber" value="${routeCarriages.indexOf(routeCarriage)}"/>
									<button type="submit"
									class="btn btn-outline-danger btn-block">Delete</button>
								</form>		</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<button class="btn btn-outline-primary btn-lg ml-3" href="#"
				data-toggle="modal" data-target="#addCarriage">Add train
				carriage</button>

		</div>
		<div class="col-lg-1 col-md-12 col-xs-12"></div>
	</div>


	<c:if test="${code != null}">
		<c:if test="${sessionScope.stops.size() > 1}">
			<c:if test="${sessionScope.routeCarriages.size() >= 2}">

				<div class="row w-100 mt-3">
					<div class="col-10"></div>
					<div class="col-2 mt-3">
						<form action="${pageContext.request.contextPath}/admin-add-route"
							method="post">
							<button type="submit" class="btn btn-success btn-lg">Create
								route</button>
						</form>
					</div>
				</div>
			</c:if>
		</c:if>
	</c:if>
	<div class="row w-100 mt-3">
		<div class="col-10"></div>
		<div class="col-2 mt-3">
			<form
				action="${pageContext.request.contextPath}/admin-cancel-add-route"
				method="post">
				<button type="submit" class="btn btn-danger btn-lg">Cancel</button>
			</form>
		</div>
	</div>
	<c:import
		url="/WEB-INF/pages/admin/admin-add-route-stop-modal-window.jsp" />
	<c:import
		url="/WEB-INF/pages/admin/admin-add-route-code-modal-window.jsp" />
	<c:import
		url="/WEB-INF/pages/admin/admin-add-route-carriage-modal-window.jsp" />
	<c:import url="/WEB-INF/pages/components/scripts.jsp" />

</jsp:body>
</tag:page>