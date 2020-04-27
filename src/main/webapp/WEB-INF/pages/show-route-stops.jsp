<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>

	<div class="row-fluid text-center mt-5">
		<h3>
			${routeCode}
			<c:forEach items="${applicationScope['contextStations']}"
				var="station">
				<c:if
					test="${station.getId()==stops.get(0).getStationId()}">
															<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
														</c:if>
			</c:forEach>
			-
			<c:forEach items="${applicationScope['contextStations']}"
				var="station">
				<c:if
					test="${station.getId()==stops.get(stops.size()-1).getStationId()}">
															<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
														</c:if>
			</c:forEach>
		</h3>
	</div>
	
	<div class="row-fluid mt-5 ml-5"> <h5><fmt:message key="find.route.label.date" /> : ${stops.get(0).getArrivalDate()}</h5></div>
	<div class="container-fluid mt-1">
		<div class="row-fluid m-2">
			<div class="table-responsive ">
				<table class="table table-hover">
					<thead class="text-center">
						<tr class="bg-info text-white ">
							<th scope="col">№</th>
							<th scope="col"><fmt:message key="table.station" /></th>
							<th scope="col"><fmt:message key="table.arrival" /></th>
							<th scope="col"><fmt:message key="table.departure" /></th>
						</tr>
					</thead>
					<tbody class="table-striped">
						<c:forEach items="${stops}" var="stop">
							<tr class="text-center">
								<td>${stop.getStopNumber()}</td>
								<td>
									<h5 class="text-info">
										<c:forEach items="${applicationScope['contextStations']}"
											var="station">
											<c:if test="${station.getId()== stop.getStationId()}">
															<t:stationName station="${station}" locale="${lang}"
																	contextLanguages="${applicationScope['contextLanguages']}" />
														</c:if>
										</c:forEach>
									</h5>
								</td>
								<td>
									<h5>${stop.getArrivalTime()}</h5>
								</td>
								<td>
									<h5>${stop.getDepartureTime()}</h5>
								</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</jsp:body>
	</tag:page>