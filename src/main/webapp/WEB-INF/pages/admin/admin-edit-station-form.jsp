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

<c:if test="${edit!=null}">

	<form
		action="${pageContext.request.contextPath}/admin-save-edit-station"
		method="post">
		<div class="form-row m-3">
			<c:forEach items="${applicationScope['contextLanguages']}"
				var="language">
				<div class="col-lg-4 col-md-4 col-xs-12 mb-3">
					<label for="stationName">${language.getLabel().toUpperCase()}</label>
					<c:forEach items="${applicationScope['contextStations']}"
						var="station">
						<c:if test="${station.getId() == stationId}">
							<input type="text" name="stationNames" class="form-control"
								id="stationNames" aria-discribdby="stationNameHelp"
								minlenght="2" maxlength="100" required
								value="${station.getNames().get(language.getId())}">
							<input type="hidden" name="stationId" value="${station.getId()}" />
						</c:if>
					</c:forEach>
				</div>
			</c:forEach>
			
			
			<div class="row w-100">
				<div class="col-10"></div>
				<div class="col-2">
					<button type="submit" class="btn btn-outline-success btn-block">Save</button>
				</div>
			</div>
		</div>
	</form>
	<form action="${pageContext.request.contextPath}/admin-cancel-station"
		method="post">
		<div class="form-row m-3">
		<div class="row w-100">
			<div class="col-10"></div>
			<div class="col-2">
				<button type="submit" class="btn btn-outline-danger btn-block">Cancel</button>
			</div>
		</div>
		</div>
	</form>
</c:if>

<c:if test="${add!=null}">
	<form action="${pageContext.request.contextPath}/admin-add-station"
		method="post">
		<input type="hidden" name="stationId" value="${station.getId()}" />
		<div class="form-row m-3">
			<c:forEach items="${applicationScope['contextLanguages']}"
				var="language">
				<div class="col-lg-4 col-md-4 col-xs-12 mb-3">
					<label for="stationName">${language.getLabel().toUpperCase()}</label>					
							<input type="text" name="stationNames" class="form-control"
								id="stationNames" aria-discribdby="stationNameHelp"
								minlenght="2" maxlength="100" required>
				</div>
			</c:forEach>

			
			
			<div class="row w-100">
				<div class="col-10"></div>
				<div class="col-2">
					<button type="submit" class="btn btn-outline-primary btn-block">Add</button>
				</div>
			</div>
		</div>
	</form>
	<form action="${pageContext.request.contextPath}/admin-cancel-station"
		method="post">
		<div class="form-row m-3">
		<div class="row w-100">
			<div class="col-10"></div>
			<div class="col-2">
				<button type="submit" class="btn btn-outline-danger btn-block">Cancel</button>
			</div>
		</div>
		</div>
	</form>
</c:if>
	</jsp:body>
</tag:page>