<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>
	<c:import url="/WEB-INF/pages/components/admin-find-route-form.jsp" />

	<c:if test="${message != null}">
		<div class="alert alert-danger text-center m-5" role="alert">
			${message}</div>
	</c:if>
	<c:if test="${routes != null}">
		<div class="display-3 text-center">Results</div>
		<div class="text-left ml-5 h4">Date :
			${routes.get(0).getDepartureStop().getDepartureDate()}</div>
		<div class="container-fluid">
			<div class="row-fluid m-2">
				<div class="table-responsive ">
					<table class="table table-hover">
						<thead class="text-center">
							<tr class="bg-info text-white ">
								<th scope="col">Train</th>
								<th scope="col">Departure</th>
								<th scope="col">Arrival</th>
								<th scope="col">Time in way</th>
								<th scope="col">Free seats</th>
								<th scope="col">Price</th>
								<th scope="col">Edit</th>
							</tr>
						</thead>
						<tbody class="table-striped">
							<c:forEach items="${routes}" var="route">

							<c:if test="${route.isCanceled() == true}">
								<tr class="bg-danger text-center text-white">
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
											<div class="h2 text-white text-center">CANCELED</div>
										</form>
									</td>
									<td>
										<h5>${route.getDepartureStop().getDepartureTime()}</h5> <c:forEach
													items="${applicationScope['contextStations']}"
													var="station">
											<c:if
														test="${station.getId()==route.getDepartureStop().getStationId()}">
												<t:stationName station="${station}" locale="${lang}"
															contextLanguages="${applicationScope['contextLanguages']}" />
											</c:if>
										</c:forEach>
									</td>
									<td>
										<h5>${route.getArrivalStop().getArrivalTime()}</h5> <c:forEach
													items="${applicationScope['contextStations']}"
													var="station">
											<c:if
														test="${station.getId()==route.getArrivalStop().getStationId()}">
												<t:stationName station="${station}" locale="${lang}"
															contextLanguages="${applicationScope['contextLanguages']}" />
											</c:if>
										</c:forEach>
									</td>
									<td>
										<h5>${route.getTimeInWay().getHour()}
											h
											<c:if test="${route.getTimeInWay().getMinute() <= 9}">0${route.getTimeInWay().getMinute()} min</c:if>
										</h5> <c:if test="${route.getTimeInWay().getMinute() > 9}">${route.getTimeInWay().getMinute()} min</c:if>

									</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</c:if>
							<c:if test="${route.isCanceled() != true}">
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
											h
											<c:if test="${route.getTimeInWay().getMinute() <= 9}">0${route.getTimeInWay().getMinute()} min</c:if>
										</h5> <c:if test="${route.getTimeInWay().getMinute() > 9}">${route.getTimeInWay().getMinute()} min</c:if>

									</td>
									<td><c:forEach items="${route.getFreeSeats()}" var="entry">
											<c:if test="${entry.value > 0}">
												<h5>${entry.key.getName().toLowerCase()}:${entry.value}</h5>
											</c:if>
										</c:forEach>
										</td>

									<td>from
										<h5>${route.getMinPrice()}hrn</h5>
									</td>
									<td><form
												action="${pageContext.request.contextPath}/admin-edit-route-form"
												method="post">
											 <input type="hidden" name="routeId"
													value="${route.getId()}" />
													<input type="hidden" name="routeCode"
													value="${route.getCode()}" />
											<button type="submit"
													class="btn btn-outline-warning btn-block">Edit</button>
										</form>
										<form
												action="${pageContext.request.contextPath}/admin-delete-route"
												method="post">
											<button type="submit"
													class="btn btn-outline-danger btn-block">Delete</button>
												<input type="hidden" name="routeId" value="${route.getId()}">
										</form></td>
								</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</c:if>
</jsp:body>
</tag:page>