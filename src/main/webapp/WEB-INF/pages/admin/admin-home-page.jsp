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
	
		<div class="display-4 mt-3 text-center">Find tickets</div>				
	<div class="container-fluid ">
	<div class="row-fluid m-5">
	
			<div class="row p-2 mb-3 border border-primary rounded">

				<div class="col-lg-6 col-xs-12 text-white">
					<button type="submit"
						class="btn btn-outline-info btn-block text-center  mt-3 mb-3" 
						data-toggle="modal" data-target="#byUserEmail">
						<h3>
							By user email
						</h3>
					</button>
				</div>
				<div class="col-lg-6 col-xs-12 text-white">
					<button type="submit"
						class="btn btn-outline-info btn-block text-center  mt-3 mb-3" 
						data-toggle="modal" data-target="#byUserName">
						<h3>
							By user name
						</h3>
					</button>
				</div>
			</div>
			
			<div class="row p-2 mb-3 border border-danger rounded">

				<div class="col-12 text-white"><form
					action="${pageContext.request.contextPath}/admin-find-tickets"
					method="post">
					<input type="hidden" name="canceled" value="true" />;
					<button type="submit"
						class="btn btn-outline-success btn-block text-center  mt-3 mb-3">
						<h3>
							Tickets of canceled trains
						</h3>
					</button>
				</form>
				</div>
			</div>		
	</div>
	
			<c:if test="${message != null }">
			<div class="alert alert-warning text-center m-5" role="alert"><h3>${message}</h3></div>
			</c:if>
	
			<c:if test="${tickets != null}">
		<div class="container-fluid">
			<div class="row-fluid m-2">
				<div class="table-responsive ">
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
								<c:if test="${ticket.getRoute().isCanceled() == true}">
								<tr class=" text-center">
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
										<div class="h2 text-danger text-center">CANCELED</div>
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
											${ticket.getCreatedDateTime().toLocalTime()}</h5>
											<form action="${pageContext.request.contextPath}/admin-delete-ticket" method="post">
									<input type="hidden" name="ticketId" value="${ticket.getId()}"/>
									<button type="submit"
									class="btn btn-outline-danger btn-block">Delete</button>
								</form>	</td>
								</tr>
								</c:if>
								<c:if test="${ticket.getRoute().isCanceled() != true}">
								<tr class="bg-danger text-center text-white">
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
											${ticket.getCreatedDateTime().toLocalTime()}</h5>
											<form action="${pageContext.request.contextPath}/admin-delete-ticket" method="post">
									<input type="hidden" name="ticketId" value="${ticket.getId()}"/>
									<button type="submit"
									class="btn btn-outline-danger btn-block">Delete</button>
								</form>	
											
											</td>
								</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</c:if>
</div>
	<c:import url="/WEB-INF/pages/client/find-tickets-by-user-name-modal.jsp" />
	<c:import url="/WEB-INF/pages/client/find-tickets-by-user-email-modal.jsp" />
</jsp:body>
</tag:page>