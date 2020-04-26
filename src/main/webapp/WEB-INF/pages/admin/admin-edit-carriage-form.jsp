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
	<div class="display-4 mt-3 text-center">Edit model of carriage</div>
	<div class="row w-100 mt-5">
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		<div class="col-lg-10 col-md-8 col-xs-12 m-3">
			<form class="form-group" method="post"
				action="${pageContext.request.contextPath}/admin-update-carriage-text">
				<c:if test="${errorUpdateCarriage != null }">
					<div class="alert alert-danger" role="alert">
						${errorUpdateCarriage}</div>
				</c:if>
				<div class="form-row">
					<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
						<label for="modelName">Model name</label> <input type="text"
							name="modelName" class="form-control" id="modelName"
							aria-discribdby="modelNameHelp" minlenght="2" maxlength="20"
							required value="${carriage.getModel()}"><small
							id="firstNameHelp" class="form-text text-muted">Enter a
							new model name of carriage. Max length of model name must be less
							than 20 characters</small>
					</div>

					<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
						<label for="totalSeats">Total seats</label> <input type="number"
							name="totalSeats" class="form-control" id="totalSeats"
							aria-discribdby="totalSeatsHelp"
							value="${carriage.getTotalSeats()}" max="200" step="1" required>
						<small id="totalSeatsHelp" class="form-text text-muted">Enter
							total seats of the carriage. Max total seats must be less than
							200 seats</small>
					</div>
					<div class="col-lg-3 col-md-6 col-xs-12 mb-3">

						<label for="comfortType">Comfort type</label> <select
							class="custom-select" id="comfortType"
							aria-discribdby="comfortTypeHelp" name="comfortType" required>
							<c:if
								test="${carriage.getComfortType().getName().equals('COMPARTMENT')}">
								<option value="COMPARTMENT" selected>Compartment</option>
							</c:if>
							<c:if
								test="${!carriage.getComfortType().getName().equals('COMPARTMENT')}">
								<option value="COMPARTMENT">Compartment</option>
							</c:if>
							<c:if
								test="${carriage.getComfortType().getName().equals('COUCHETTE')}">
								<option value="COUCHETTE" selected>Couchette</option>
							</c:if>
							<c:if
								test="${!carriage.getComfortType().getName().equals('COUCHETTE')}">
								<option value="COUCHETTE">Couchette</option>
							</c:if>
							<option value="SITTING">Sitting</option>
							<c:if
								test="${carriage.getComfortType().getName().equals('SITTING')}">
								<option value="SITTING" selected>Sitting</option>
							</c:if>
							<c:if
								test="${!carriage.getComfortType().getName().equals('SITTING')}">
								<option value="SITTING">Sitting</option>
							</c:if>
						</select> <small id="comfortTypeHelp" class="form-text text-muted">Choose
							comfort type of carriage</small>
					</div>
					<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
						<label for="priceCoefficient">Price coefficient</label> <input
							type="number" name="priceCoefficient" class="form-control"
							id="priceCoefficient" aria-discribdby="priceCoefficientHelp"
							value="${carriage.getPriceCoefficient()}" min="1.00" step="0.01"
							required> <small id="priceCoefficientHelp"
							class="form-text text-muted">Enter price coefficient</small>
					</div>
				</div>


			</form>
		</div>
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
	</div>

	<%-- =========================================================== --%>

	<div class="row-fluid h-75"></div>

	<%@ include file="/WEB-INF/pages/components/footer.jspf"%>
	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>