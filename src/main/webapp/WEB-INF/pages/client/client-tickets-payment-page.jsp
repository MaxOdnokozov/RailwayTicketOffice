<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<c:import url="/WEB-INF/pages/components/header.jsp" />

	<%--=======================================================================--%>
	<div class="container-fluid p-0">
		<img src="static/img/header/eurostar-train.jpg" alt=""
			class="d-block w-100">
	</div>

	<%--========================================================================--%>

	<div class="display-3 text-center">Tickets</div>
	<c:if test="${tickets != null}">
	<div class="container">
			<c:if test="${errorPayment != null }">
				<div class="alert alert-danger" role="alert">
					${errorPayment}</div>
			</c:if>
		</div>
		<div class="container-fluid">
			<div class="row-fluid m-2">
				<div class="table-responsive ">
				<form method="post" action="${pageContext.request.contextPath}/client-pay-tickets">
					<table class="table table-hover">
						<thead class="text-center">
							<tr class="bg-info text-white ">
								<th scope="col">Train</th>
								<th scope="col">Date</th>								
								<th scope="col">Departure</th>
								<th scope="col">Arrival</th>
								<th scope="col">Time in way</th>
								<th scope="col">Name</th>
								<th scope="col">Carriage №</th>
								<th scope="col">Seat №</th>
								<th scope="col">Price</th>
								<th scope="col">Date creation</th>
							</tr>
						</thead>
						<tbody class="table-striped">
							<c:forEach items="${tickets}" var="ticket">
							<input type="hidden" name="ticketsIds" value="${ticket.getId()}" />
								<tr class="text-center">
									<td>
										<h5 class="text-info">
											${ticket.getRoute().getCode()}
											<c:forEach items="${applicationScope['contextStations']}"
												var="station">
												<c:if
													test="${station.getId()== ticket.getRoute().getStops().get(0).getStationId()}">
															${station.getNames().get(1)}
														</c:if>
											</c:forEach>
											-
											<c:forEach items="${applicationScope['contextStations']}"
												var="station">
												<c:if
													test="${station.getId()== ticket.getRoute().getStops().get(ticket.getRoute().getStops().size()-1).getStationId()}">
															${station.getNames().get(1)}
														</c:if>
											</c:forEach>
										</h5>
									</td>
									<td>${ticket.getRoute().getDepartureStop().getDepartureDate()}</td>
									<td>
										<h5>${ticket.getRoute().getDepartureStop().getDepartureTime()}</h5>
										<c:forEach items="${applicationScope['contextStations']}"
											var="station">
											<c:if
												test="${station.getId()==ticket.getRoute().getDepartureStop().getStationId()}">
											${station.getNames().get(1)}
										</c:if>
										</c:forEach>
									</td>
									<td>
										<h5>${ticket.getRoute().getArrivalStop().getArrivalTime()}</h5>
										<c:forEach items="${applicationScope['contextStations']}"
											var="station">
											<c:if
												test="${station.getId()==ticket.getRoute().getArrivalStop().getStationId()}">
											${station.getNames().get(1)}
										</c:if>
										</c:forEach>
									</td>
									<td>
										<h5>${ticket.getRoute().getTimeInWay().getHour()}
											h
											<c:if
												test="${ticket.getRoute().getTimeInWay().getMinute() <= 9}">0${ticket.getRoute().getTimeInWay().getMinute()} min</c:if>
										</h5> <c:if
											test="${ticket.getRoute().getTimeInWay().getMinute() > 9}">${ticket.getRoute().getTimeInWay().getMinute()} min</c:if>
									</td>
									<td>${ticket.getUser().getFirstName()}
										${ticket.getUser().getLastName()}</td>
									<td>${ticket.getCarriageNumber()}</td>
									<td>${ticket.getSeatNumber()}</td>
									<td><h5>${ticket.getPrice()} hrn</h5></td>
									<td><h5>${ticket.getCreatedDateTime().toLocalDate()}
											${ticket.getCreatedDateTime().toLocalTime()}</h5></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="h3 text-right mr-5">Total price: ${totalPrice} hrn</div>
					<div class="row w-100 mt-5">
						<div class="col-5"></div>
						<div class="col-5"></div>
						<div class="col-2">
							<button type="submit" class="btn btn-primary btn-block">Pay</button>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
	</c:if>


	<div class="row-fluid h-100"></div>

	<%@ include file="/WEB-INF/pages/components/footer.jspf"%>
	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</html>