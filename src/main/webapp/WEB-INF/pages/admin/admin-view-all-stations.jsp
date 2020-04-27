<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>
	<div>
		<div class="container-fluid p-0">
			<img src="static/img/header/eurostar-train-add-carriage-form.jpg"
					alt="" class="d-block w-100">
		</div>
	</div>
	<div class="display-4 mt-3 text-center"><fmt:message key="nav.bar.label.stations" /></div>
	
	

		<div class="row w-100 mt-2">
			<div class="col-9"></div>
			<div class="col-2">
				<form
					action="${pageContext.request.contextPath}/admin-add-station-form"
					method="post">
					<button type="submit" class="btn btn-outline-primary btn-block"><fmt:message key="button.add" /></button>
				</form>
			</div>
			<div class="col-1"></div>
		</div>
		
	<c:if test="${errorUpdate != null }">
		<div class="alert alert-danger mt-3" role="alert">
			${errorUpdate}</div>
	</c:if>
	<c:if test="${errorCreate != null }">
		<div class="alert alert-danger mt-3" role="alert">
			${errorCreate}</div>
	</c:if>
	<c:if test="${errorDelete != null }">
		<div class="alert alert-danger mt-3" role="alert">
			${errorDelete}</div>
	</c:if>
	<div class="row w-100 mt-2">
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		<div class="col-lg-10 col-md-8 col-xs-12">

			<table class="table table-hover">
				<thead>
					<tr class="text-center">
						<th scope="col">№</th>
						<c:forEach items="${applicationScope['contextLanguages']}"
								var="language">
							<th>${language.getLabel().toUpperCase()}</th>
						</c:forEach>
						<th scope="col"><fmt:message key="button.edit" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${applicationScope['contextStations']}"
							var="station">
						<tr class="text-center">
							<td>${applicationScope['contextStations'].indexOf(station)+1}</td>
							<c:forEach items="${applicationScope['contextLanguages']}"
									var="language">
								<td>${station.getNames().get(language.getId())}</td>
							</c:forEach>
							<td>
								<div class="form-inline">
										<form
											action="${pageContext.request.contextPath}/admin-edit-station-form"
											method="post">
											 <input type="hidden" name="stationId"
												value="${station.getId()}" />
											<button type="submit"
												class="btn btn-outline-warning btn-block"><fmt:message key="button.edit" /></button>
										</form>
										<form
											action="${pageContext.request.contextPath}/admin-delete-station"
											method="post">
											<input type="hidden" name="stationId"
												value="${station.getId()}" />
											<button type="submit"
												class="btn btn-outline-danger btn-block ml-1"><fmt:message key="button.delete" /></button>
										</form>
								</div>
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