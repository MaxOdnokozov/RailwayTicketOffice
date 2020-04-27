<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<c:set var="lang"
	value="${not empty lang ? lang : pageContext.request.locale.language}"
	scope="session" />
<fmt:setLocale value="${lang}" scope="session" />

<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<header>
		<c:import url="/WEB-INF/pages/components/header.jsp" />
	</header>
	<div>
		<div class="container-fluid p-0">
			<img src="static/img/header/station.jpg" alt="" class="d-block w-100">
		</div>
	</div>
	<div class="display-3 mt-3 text-center"><fmt:message key="label.new.route" /></div>

	<div class="container-fluid">
		<div class="h1 mt-5 text-center">

			<c:if test="${sessionScope.code == null}">
					<fmt:message key="label.route.code" />: ----
					</c:if>
			<c:if test="${sessionScope.code != null}">
					<fmt:message key="label.route.code" />: ${sessionScope.code}
					</c:if>
			<button class="btn btn-outline-primary btn-lg ml-3" href="#"
				data-toggle="modal" data-target="#addCode"><fmt:message key="button.edit" /></button>


		</div>
	</div>
	<div class="row w-100">
		<div class="col-lg-1 col-md-12 col-xs-12"></div>
		<div class="col-lg-10 col-md-12 col-xs-12">
			<div class="h4 mt-3 text-center"><fmt:message key="table.stops" /></div>
			<div class="h5 mt-3 ml-3"><fmt:message key="find.route.label.date" /> :
			<c:if test="${sessionScope.stops.size() != 0}">
				${sessionScope.stops.get(0).getArrivalDate()}
			</c:if>
				</div>
			<div class="table-responsive">
				<table class="table table-hover text-center p-2">
					<thead>
						<tr>
							<th scope="col">№</th>
							<th scope="col"><fmt:message key="table.station" /></th>
							<th scope="col"><fmt:message key="table.arrival.date" /></th>
							<th scope="col"><fmt:message key="table.arrival.time" /></th>
							<th scope="col"><fmt:message key="table.departure.date" /></th>
							<th scope="col"><fmt:message key="table.departure.time" /></th>
							<th scope="col"><fmt:message key="table.price" /></th>
							<th scope="col"><fmt:message key="button.edit" /></th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach items="${sessionScope.stops}" var="stop">
							<tr>
								<td>${stop.getStopNumber()}</td>
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
								<td>
								<c:if test="${stop.getStopNumber().equals(stops.size())}">
										<form
											action="${pageContext.request.contextPath}/admin-delete-route-stop"
											method="post">
											<input type="hidden" name="stopNumber"
												value="${sessionScope.stops.indexOf(stop)}" />
											<button type="submit"
												class="btn btn-outline-danger btn-block">
												<fmt:message key="button.delete" />
											</button>
										</form>
									</c:if>

								</td>
							</tr>
						</c:forEach>
					
					</tbody>
				</table>
			</div>
			<button href="#" data-toggle="modal" data-target="#addStop"
				class="btn btn-outline-primary btn-lg ml-3 mt-3"><fmt:message key="button.add.route.stop" /></button>
		</div>
		<div class="col-lg-1 col-md-12 col-xs-12"></div>
	</div>

	<div class="row w-100">
		<div class="col-lg-1 col-md-12 col-xs-12"></div>
		<div class="col-lg-10 col-md-12 col-xs-12">
			<div class="h4 mt-3 text-center"><fmt:message key="label.train.carriages" /></div>
			<div class="table-responsive">
				<table class="table table-hover text-center mt-2 p-2" id="carriages">
					<thead>
						<tr class="m-2">
							<th scope="col">№</th>
							<th scope="col"><fmt:message key="label.model" /></th>
							<th scope="col"><fmt:message key="table.type" /></th>
							<th scope="col"><fmt:message key="label.total.seats" /></th>
							<th scope="col"><fmt:message key="label.price.coefficient" /></th>
							<th scope="col"><fmt:message key="label.image" /></th>
							<th scope="col"><fmt:message key="button.edit" /></th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${sessionScope.routeCarriages != null}">
						<c:forEach items="${sessionScope.routeCarriages}"
							var="routeCarriage">
							<tr>
								<td>${routeCarriage.getNumber()}</td>
								<td>${routeCarriage.getCarriage().getModel()}</td>
								<td><c:if test="${routeCarriage.getCarriage().getComfortType().getName().equals('COMPARTMENT')}"><fmt:message key="comfort.type.compartment" /> </c:if>
												<c:if test="${routeCarriage.getCarriage().getComfortType().getName().equals('SITTING')}"><fmt:message key="comfort.type.sitting" /> </c:if>	
												<c:if test="${routeCarriage.getCarriage().getComfortType().getName().equals('COUCHETTE')}"><fmt:message key="comfort.type.couchette" /></c:if></td>
								<td>${routeCarriage.getCarriage().getTotalSeats()}</td>
								<td>${routeCarriage.getCarriage().getPriceCoefficient()}</td>
								<td><a class="fancybox"
									href="${pageContext.request.contextPath}${applicationScope['contextImagesDir']}/${routeCarriage.getCarriage().getImage()}">
										<img class="img-fluid h-25"
										src="${pageContext.request.contextPath}${applicationScope['contextImagesDir']}/${routeCarriage.getCarriage().getImage()}">
								</a></td>
								<td>
								<c:if test="${routeCarriage.getNumber().equals(routeCarriages.size())}">
											<form
												action="${pageContext.request.contextPath}/admin-delete-route-carriage"
												method="post">
												<input type="hidden" name="routeCarriageNumber"
													value="${routeCarriages.indexOf(routeCarriage)}" />
												<button type="submit"
													class="btn btn-outline-danger btn-block">
													<fmt:message key="button.delete" />
												</button>
											</form>

										</c:if>

									</td>
							</tr>
						</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>

			<button class="btn btn-outline-primary btn-lg ml-3" href="#"
				data-toggle="modal" data-target="#addCarriage"><fmt:message key="button.add.train.carriage" /></button>

		</div>
		<div class="col-lg-1 col-md-12 col-xs-12"></div>
	</div>


	<c:if test="${code != null}">
		<c:if test="${sessionScope.stops.size() > 1}">
			<c:if test="${sessionScope.routeCarriages.size() >= 2}">

				<div class="form-group">
				<div class="row w-100 mt-3">
					<div class="col-9"></div>
					<div class="col-2 mt-3 ml-3">
						<form action="${pageContext.request.contextPath}/admin-add-route"
							method="post">
							<button type="submit" class="btn btn-success "><fmt:message key="button.create.route" /></button>
						</form>
					</div>
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
				<button type="submit" class="btn btn-danger btn-lg"><fmt:message key="button.cancel" /></button>
			</form>
		</div>
	</div>
	<c:import
		url="/WEB-INF/pages/admin/admin-add-route-stop-modal-window.jsp" />
	<c:import
		url="/WEB-INF/pages/admin/admin-add-route-code-modal-window.jsp" />
	<c:import
		url="/WEB-INF/pages/admin/admin-add-route-carriage-modal-window.jsp" />

<c:import url="/WEB-INF/pages/components/error-modal-window.jsp" />
	<%--=========================================================================== 
	Login modal window. 
===========================================================================--%>

	<c:import url="/WEB-INF/pages/components/login-modal-window.jsp" />

	<%--=========================================================================== 
	Registration modal window. 

===========================================================================--%>
	<c:import
		url="/WEB-INF/pages/components/registration-account-modal-window.jsp" />

	<%--=========================================================================== 
	Errors modal window. 
===========================================================================--%>


	<div class="content">
		
	</div>
	<div class="row-fluid h-75"></div>
	<%@ include file="/WEB-INF/pages/components/footer.jspf"%>
	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>