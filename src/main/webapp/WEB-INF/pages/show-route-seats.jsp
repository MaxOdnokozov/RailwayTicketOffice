<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<c:import url="/WEB-INF/pages/components/header.jsp" />

	<div class="row-fluid text-center display-4 mt-5">CHOOSE SEATS</div>
	<div class="row-fluid">
		<div class="border border-primary p-5 ml-2 mt-3 rounded">
			<c:forEach items="${applicationScope['contextCarriages']}"
				var="carriage">
				<c:if test="${carriage.getId() == routeCarriage.getCarriageId()}">
					<img class="img-fluid w-100"
						src="${pageContext.request.contextPath}${applicationScope['contextImagesDir']}/${carriage.getImage()}">
				</c:if>
			</c:forEach>
			<div class="row mt-5 mb-3">
				<h4 class="col-4">Carriage №${routeCarriage.getNumber()}</h4>
				<h4 class="col-4">Type : ${routeCarriage.getComfortType()}</h4>
				<h4 class="col-4">Price : ${routeCarriage.getPrice()} hrn</h4>
			</div>

			<form method="get" action="${pageContex.request.contextPath}/client-booking-seats">
			<input type="hidden" name="routeId" value="${routeId}" />
			<input type="hidden" name="routeCarriageNumber" value="${routeCarriage.getNumber()}" />
			<input type="hidden" name="departureStationId" value="${departureStationId}" />
			<input type="hidden" name="arrivalStationId" value="${arrivalStationId}" />
				<div class="border border-primary rounded">
					<div class="btn-group-toggle" data-toggle="buttons">
						<c:forEach items="${routeCarriage.getSeats()}" var="seat">
							<c:if test="${seat.isFree() == true}">
								<label class="btn btn-outline-primary m-3"> <input
									type="checkbox" name="selectedSeats"
									value="${seat.getId()}">${seat.getNumber()}
								</label>
							</c:if>
						</c:forEach>
					</div>
					<div class="row w-100 mt-3">
						<div class="col-5"></div>
						<div class="col-5"></div>
						<div class="col-2">
							<button type="submit" class="btn btn-success btn-block mb-3">Booking</button>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>




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


	<div class="row-fluid h-75"></div>

	<%@ include file="/WEB-INF/pages/components/footer.jspf"%>
	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>