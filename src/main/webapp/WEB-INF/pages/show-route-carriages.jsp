<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>

	<div class="row-fluid text-center mt-5">
		<h4><fmt:message key="label.choose.carriage" /></h4>

	</div>

	<div class="container-fluid mt-3">
		<div class="row-fluid m-2">
			<div class="table-responsive ">
				<table class="table table-hover">
					<thead class="text-center">
						<tr class="bg-info text-white ">
							<th scope="col"><fmt:message key="table.train" /></th>							
							<th scope="col"><fmt:message key="find.route.label.date" /></th>
							<th scope="col"><fmt:message key="table.departure" /></th>
							<th scope="col"><fmt:message key="table.arrival" /></th>
							<th scope="col"><fmt:message key="table.time.in.way" /></th>

						</tr>
					</thead>
					<tbody class="table-striped">
						
						<tr class="text-center">
							<td>
								<form method="post"
										action="${pageContext.request.contextPath}/show-route-stops">
									<input type="hidden" name="routeId" value="${route.getId()}" />
									<input type="hidden" name="routeCode"
											value="${route.getCode()}" />
									<button type="submit" class="btn btn-link">
										<h5 class="text-info">
											${route.getCode()}
											<c:forEach items="${applicationScope['contextStations']}"
													var="station">
												<c:if
														test="${station.getId()==route.getStops().get(0).getStationId()}">
															<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
														</c:if>
											</c:forEach>
											-
											<c:forEach items="${applicationScope['contextStations']}"
													var="station">
												<c:if
														test="${station.getId()==route.getStops().get(route.getStops().size()-1).getStationId()}">
															<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
														</c:if>
											</c:forEach>
										</h5>
									</button>
								</form>
							</td>
							<td><h5>${route.getDepartureStop().getDepartureDate()}</h5></td>
							<td>
								<h5>${route.getDepartureStop().getDepartureTime()}</h5> <c:forEach
										items="${applicationScope['contextStations']}" var="station">
									<c:if
											test="${station.getId()==route.getDepartureStop().getStationId()}">
											<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
										</c:if>
								</c:forEach>
							</td>
							<td>
								<h5>${route.getArrivalStop().getArrivalTime()}</h5> <c:forEach
										items="${applicationScope['contextStations']}" var="station">
									<c:if
											test="${station.getId()==route.getArrivalStop().getStationId()}">
											<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
										</c:if>
								</c:forEach>
							</td>
							<td>
								<h5>${route.getTimeInWay().getHour()}
									<fmt:message key="table.label.h" />
									<c:if test="${route.getTimeInWay().getMinute() <= 9}">0${route.getTimeInWay().getMinute()} <fmt:message key="table.label.min" /></c:if>
								 <c:if test="${route.getTimeInWay().getMinute() > 9}">${route.getTimeInWay().getMinute()} <fmt:message key="table.label.min" /></c:if>
								</h5>

							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>


	<div class="container-fluid mt-3">
		<div class="row-fluid m-2">
			<div class="table-responsive ">
				<table class="table table-hover">
					<thead class="text-center">
					</thead>
					<tbody class="table-striped">
						<c:forEach items="${route.getRouteCarriages()}"
								var="routeCarriage">
							<tr class="text-center">
								<td><fmt:message key="table.label.carriage.number" />${routeCarriage.getNumber()}</td>
								<td><fmt:message key="table.type" /> :
												<c:if test="${routeCarriage.getComfortType().getName().equals('COMPARTMENT')}"><fmt:message key="comfort.type.compartment" /> </c:if>
												<c:if test="${routeCarriage.getComfortType().getName().equals('SITTING')}"><fmt:message key="comfort.type.sitting" /> </c:if>	
												<c:if test="${routeCarriage.getComfortType().getName().equals('COUCHETTE')}"><fmt:message key="comfort.type.couchette" /></c:if>
								</td>
								<td><fmt:message key="table.free.seats" /> - ${routeCarriage.getTotalFreeSeats()}</td>
								<td><fmt:message key="table.price" /> : ${routeCarriage.getPrice()} <fmt:message key="table.label.hrn" /></td>
								<td><c:if test="${routeCarriage.getTotalFreeSeats() > 0}">
									<form method="post"
												action="${pageContext.request.contextPath}/show-route-seats">
									<input type="hidden" name="routeId" value="${route.getId()}" />
									<input type="hidden" name="routeCarriageNumber"
													value="${routeCarriage.getNumber()}" />
									<input type="hidden" name="departureStationId"
													value="${route.getDepartureStop().getStationId()}" />
									<input type="hidden" name="arrivalStationId"
													value="${route.getArrivalStop().getStationId()}" />
										<button type="submit"
													class="btn btn-outline-primary btn-block"><fmt:message key="button.choose.seats" /></button>
									</form>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
 	</jsp:body>
</tag:page>