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
				<c:if test="${station.getId()==stops.get(0).getStationId()}">
															${station.getNames().get(1)}
														</c:if>
			</c:forEach>
			-
			<c:forEach items="${applicationScope['contextStations']}"
					var="station">
				<c:if
						test="${station.getId()==stops.get(stops.size()-1).getStationId()}">
															${station.getNames().get(1)}
														</c:if>
			</c:forEach>
		</h3>
	</div>
	
	</h5>
		</div>
	<div class="container-fluid mt-5">
		<div class="row-fluid m-2">
			<div class="table-responsive ">
				<table class="table table-hover">
					<thead class="text-center">
						<tr class="bg-info text-white ">
							<th scope="col">№</th>
							<th scope="col">Station</th>
							<th scope="col">Arrival date</th>
							<th scope="col">Arrival time</th>
							<th scope="col">Departure date</th>
							<th scope="col">Departure time</th>
							<th scope="col">Edit</th>
						</tr>
					</thead>
					<tbody class="table-striped">
						<c:forEach items="${stops}" var="stop">
						<c:if test="${editStopId == null}">
							<tr class="text-center">
								<td>${stop.getStopNumber()}</td>
								<td>
									<h5 class="text-info">
										<c:forEach items="${applicationScope['contextStations']}"
												var="station">
											<c:if test="${station.getId()== stop.getStationId()}">
												${station.getNames().get(1)}
											</c:if>
										</c:forEach>
									</h5>
								</td>
								<td>
									<h5>${stop.getArrivalDate()}</h5>
								</td>
								<td>
									<h5>${stop.getDepartureDate()}</h5>
								</td>
								<td>
									<h5>${stop.getArrivalTime()}</h5>
								</td>
								<td>
									<h5>${stop.getDepartureTime()}</h5>
								</td>
								<td>		
								<form action="${pageContext.request.contextPath}/admin-edit-route-form" method="post">
									<button type="submit" class="btn btn-outline-warning btn-block">Edit</button>
									<input type="hidden" name="routeCode" value="${routeCode}"/>
									<input type="hidden" name="routeId" value="${routeId}"/>
									<input type="hidden" name="stopId" value="${stop.getId()}"/>
								</form>
								</td>
								</tr>
							</c:if>
							<c:if test="${editStopId != null}">

							<c:if test="${editStopId.equals(stop.getId())}">								
								<form action="${pageContext.request.contextPath}/admin-save-edit-route" method="post">
							<tr class="text-center">
								<td>${stop.getStopNumber()}</td>
								<td>
									<h5 class="text-info">
										<c:forEach items="${applicationScope['contextStations']}"
												var="station">
											<c:if test="${station.getId()== stop.getStationId()}">
												${station.getNames().get(1)}
											</c:if>
										</c:forEach>
									</h5>
								</td>
								<td>
									<h5><input type="date" name="arrivalDate"  class="form-control" value="${stop.getArrivalDate()}"></h5>
								</td>
								<td>
									<h5><input type="date" name="departureDate"  class="form-control" value="${stop.getDepartureDate()}"></h5>
									
								</td>
								<td>
									<h5><input type="time" name="arrivalTime"  class="form-control" value="${stop.getArrivalTime()}"></h5>
								</td>
								<td>
									<h5><input type="time" name="departureTime"  class="form-control" value="${stop.getDepartureTime()}"></h5>
								</td>
								<td>		
									<button type="submit" class="btn btn-outline-success btn-block">Save</button>
									<input type="hidden" name="routeCode" value="${routeCode}"/>
									<input type="hidden" name="routeId" value="${routeId}"/>
									<input type="hidden" name="stopId" value="${stop.getId()}"/>
								</td>
								</tr>
								</form>
								</c:if>
								<c:if test="${!editStopId.equals(stop.getId())}">
									<tr class="text-center">
								<td>${stop.getStopNumber()}</td>
								<td>
									<h5 class="text-info">
										<c:forEach items="${applicationScope['contextStations']}"
												var="station">
											<c:if test="${station.getId()== stop.getStationId()}">
												${station.getNames().get(1)}
											</c:if>
										</c:forEach>
									</h5>
								</td>
								<td>
									<h5>${stop.getArrivalDate()}</h5>
								</td>
								<td>
									<h5>${stop.getDepartureDate()}</h5>
								</td>
								<td>
									<h5>${stop.getArrivalTime()}</h5>
								</td>
								<td>
									<h5>${stop.getDepartureTime()}</h5>
								</td>
								<td>		
								<form action="${pageContext.request.contextPath}/admin-edit-route-form" method="post">
									<button type="submit" class="btn btn-outline-warning btn-block">Edit</button>
									<input type="hidden" name="routeCode" value="${routeCode}"/>
									<input type="hidden" name="routeId" value="${routeId}"/>
									<input type="hidden" name="stopId" value="${stop.getId()}"/>
								</form>
								</td>
								</tr>
								</c:if>
							</c:if>
							
								
						</c:forEach>
					</tbody>
				</table>
				<form
						action="${pageContext.request.contextPath}/admin-cancel-edit-route"
						method="post">
		<div class="form-row m-3">
		<div class="row w-100">
			<div class="col-10"></div>
			<div class="col-2">
				<button type="submit" class="btn btn-outline-primary btn-block">Back</button>
			</div>
		</div>
		</div>
	</form>
					</td>
			</div>
		</div>
	</div>
	</jsp:body>
</tag:page>