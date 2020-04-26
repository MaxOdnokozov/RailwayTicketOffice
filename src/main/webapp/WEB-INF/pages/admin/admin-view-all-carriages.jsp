<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<c:import url="/WEB-INF/pages/components/header.jsp" />

	<%-- =========================================================== --%>
	<div>
		<div class="container-fluid p-0">
			<img src="static/img/header/eurostar-train-add-carriage-form.jpg"
				alt="" class="d-block w-100">
		</div>
	</div>
	<div class="display-4 mt-3 text-center">View all models of
		carriages</div>

	<c:if test="${errorViewAllCarriage != null }">
		<div class="alert alert-danger mt-3" role="alert">
			${errorViewAllCarriage}</div>
	</c:if>
	<c:if test="${errorDelete != null }">
		<div class="alert alert-danger mt-3" role="alert">
			${errorDelete}</div>
	</c:if>
	<c:if test="${errorNotFoundUpdate != null }">
		<div class="alert alert-danger mt-3" role="alert">
			${errorNotFoundUpdate}</div>
	</c:if>
	<div class="row w-100 mt-5">
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		<div class="col-lg-10 col-md-8 col-xs-12">

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Model</th>
						<th scope="col">Comfort type</th>
						<th scope="col">Total seats</th>
						<th scope="col">Price coefficient</th>
						<th scope="col">Model image</th>
						<th scope="col">Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${applicationScope['contextCarriages']}" var="carriage">
						<tr>
							<td>${carriage.getModel()}</td>
							<td>${carriage.getComfortType()}</td>
							<td>${carriage.getTotalSeats()}</td>
							<td>${carriage.getPriceCoefficient()}</td>
							<td><a class="fancybox"
								href="${pageContext.request.contextPath}${contextImagesDir}/${carriage.getImage()}">
									<img class="img-fluid h-25"
									src="${pageContext.request.contextPath}${applicationScope['contextImagesDir']}/${carriage.getImage()}"></a>
							</td>
							<td>
								<form action="${pageContext.request.contextPath}/admin-edit-carriage-form" method="post">
									<input type="hidden" name="carriageId" value="${carriage.getId()}"/>
									<button type="submit"
										class="btn btn-outline-warning btn-block"
										>Edit</button>
								</form>
								<form action="${pageContext.request.contextPath}/admin-delete-carriage" method="post">
									<input type="hidden" name="carriageId" value="${carriage.getId()}"/>
									<input type="hidden" name="modelImageName" value="${carriage.getImage()}"/>
									<button type="submit"
									class="btn btn-outline-danger btn-block">Delete</button>
								</form>									
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</div>
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
	</div>

	<%-- =========================================================== --%>
	<div class="row-fluid h-75"></div>

	<%@ include file="/WEB-INF/pages/components/footer.jspf"%>
	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>