<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>
	<div>
		<div class="container-fluid p-0">
			<img src="static/img/header/eurostar-train-add-carriage-form.jpg"
					alt="" class="d-block w-100">
		</div>
	</div>
	<div class="display-4 mt-3 text-center"><fmt:message key="nav.bar.label.carriages" /></div>

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
			<div class="col-9"></div>
			<div class="col-2">
				<form
					action="${pageContext.request.contextPath}/admin-add-carriage-form"
					method="post">
					<button type="submit" class="btn btn-outline-primary btn-block"><fmt:message key="button.add" /></button>
				</form>
			</div>
			<div class="col-1"></div>
		</div>
	 
	<div class="row w-100 mt-2">
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		<div class="col-lg-10 col-md-8 col-xs-12">

			<table class="table table-hover">
				<thead>
					<tr class="text-center">
						<th scope="col"><fmt:message key="label.model" /></th>
						<th scope="col"><fmt:message key="table.type" /></th>
						<th scope="col"><fmt:message key="label.total.seats" /></th>
						<th scope="col"><fmt:message key="label.price.coefficient" /></th>
						<th scope="col"><fmt:message key="label.image" /></th>
						<th scope="col"><fmt:message key="button.edit" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${applicationScope['contextCarriages']}"
							var="carriage">
						<tr>
							<td>${carriage.getModel()}</td>
							<td><c:if test="${carriage.getComfortType().getName().equals('COMPARTMENT')}"><fmt:message key="comfort.type.compartment" /> </c:if>
												<c:if test="${carriage.getComfortType().getName().equals('SITTING')}"><fmt:message key="comfort.type.sitting" /> </c:if>	
												<c:if test="${carriage.getComfortType().getName().equals('COUCHETTE')}"><fmt:message key="comfort.type.couchette" /></c:if></td>
							<td>${carriage.getTotalSeats()}</td>
							<td>${carriage.getPriceCoefficient()}</td>
							<td><a class="fancybox"
									href="${pageContext.request.contextPath}${contextImagesDir}/${carriage.getImage()}">
									<img class="img-fluid h-25"
										src="${pageContext.request.contextPath}${applicationScope['contextImagesDir']}/${carriage.getImage()}">
								</a>
							</td>
							<td>
								<form
										action="${pageContext.request.contextPath}/admin-edit-carriage-form"
										method="post">
									<input type="hidden" name="carriageId"
											value="${carriage.getId()}" />
									<button type="submit" class="btn btn-outline-warning btn-block"><fmt:message key="button.edit" /></button>
								</form>
								<form
										action="${pageContext.request.contextPath}/admin-delete-carriage"
										method="post">
									<input type="hidden" name="carriageId"
											value="${carriage.getId()}" />
									<input type="hidden" name="modelImageName"
											value="${carriage.getImage()}" />
									<button type="submit" class="btn btn-outline-danger btn-block"><fmt:message key="button.delete" /></button>
								</form>									
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</div>
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
	</div>

	</jsp:body>
</tag:page>