<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>
	<div class="container-fluid p-0">
		<img src="static/img/header/eurostar-train.jpg" alt=""
			class="d-block w-100">
	</div>

	<div class="display-3 text-center"><fmt:message key="label.tickets" /></div>
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
								<th scope="col"><fmt:message key="table.train" /></th>
								<th scope="col"><fmt:message key="find.route.label.date" /></th>								
								<th scope="col"><fmt:message key="table.departure" /></th>
								<th scope="col"><fmt:message key="table.arrival" /></th>
								<th scope="col"><fmt:message key="table.time.in.way" /></th>
								<th scope="col"><fmt:message key="table.name" /></th>
								<th scope="col"><fmt:message key="table.label.carriage.number" /></th>
								<th scope="col"><fmt:message key="table.label.seat.number" /></th>
								<th scope="col"><fmt:message key="table.price" /></th>
								<th scope="col"><fmt:message key="table.label.date.creation" /></th>
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
															<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
														</c:if>
											</c:forEach>
											-
											<c:forEach items="${applicationScope['contextStations']}"
												var="station">
												<c:if
													test="${station.getId()== ticket.getRoute().getStops().get(ticket.getRoute().getStops().size()-1).getStationId()}">
															<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
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
											<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
										</c:if>
										</c:forEach>
									</td>
									<td>
										<h5>${ticket.getRoute().getArrivalStop().getArrivalTime()}</h5>
										<c:forEach items="${applicationScope['contextStations']}"
											var="station">
											<c:if
												test="${station.getId()==ticket.getRoute().getArrivalStop().getStationId()}">
											<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
										</c:if>
										</c:forEach>
									</td>
									<td>
										<h5>${ticket.getRoute().getTimeInWay().getHour()}
											<fmt:message key="table.label.h" />
											<c:if
												test="${ticket.getRoute().getTimeInWay().getMinute() <= 9}">0${ticket.getRoute().getTimeInWay().getMinute()} <fmt:message key="table.label.min"></fmt:message></c:if>
										<c:if
											test="${ticket.getRoute().getTimeInWay().getMinute() > 9}">${ticket.getRoute().getTimeInWay().getMinute()} <fmt:message key="table.label.min"></fmt:message></c:if>
										</h5> 
									</td>
									<td>${ticket.getUser().getFirstName()}
										${ticket.getUser().getLastName()}</td>
									<td>${ticket.getCarriageNumber()}</td>
									<td>${ticket.getSeatNumber()}</td>
									<td><h5>${ticket.getPrice()} <fmt:message key="table.label.hrn" /></h5></td>
									<td><h5>${ticket.getCreatedDateTime().toLocalDate()}
											${ticket.getCreatedDateTime().toLocalTime()}</h5></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="h3 text-right mr-5"><fmt:message key="label.total.price" />: ${totalPrice} <fmt:message key="table.label.hrn" /></div>
					<div class="row w-100 mt-5">
						<div class="col-5"></div>
						<div class="col-5"></div>
						<div class="col-2">
							<button type="submit" class="btn btn-primary btn-block"><fmt:message key="button.pay" /></button>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
	</c:if>
</jsp:body>
</tag:page>