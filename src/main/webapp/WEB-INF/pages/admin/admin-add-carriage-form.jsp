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
	<div class="display-4 mt-3 text-center"><fmt:message key="label.add.new.model.carriage" /></div>
	<div class="row w-100 mt-5">
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		<div class="col-lg-10 col-md-8 col-xs-12 m-3">
			<form class="form-group" enctype="multipart/form-data" method="post"
				action="${pageContext.request.contextPath}/admin-add-carriage">
				<c:if test="${errorAddCarriage != null }">
					<div class="alert alert-danger" role="alert">
						${errorAddCarriage}</div>

					<div class="form-row">
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="modelName"><fmt:message key="label.model.name" /></label> <input type="text"
								name="modelName" class="form-control" id="modelName"
								aria-discribdby="modelNameHelp" minlenght="2" maxlength="20"
								required value="${modelName}"><small id="firstNameHelp"
								class="form-text text-muted"><fmt:message key="label.model.name.help" />
								</small>
						</div>

						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="totalSeats"><fmt:message key="label.total.seats" /></label> <input type="number"
								name="totalSeats" class="form-control" id="totalSeats"
								aria-discribdby="totalSeatsHelp" value="${totalSeats}" max="200"
								step="1" required> <small id="totalSeatsHelp"
								class="form-text text-muted"><fmt:message key="label.total.seats.help" /></small>
						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">

							<label for="comfortType"><fmt:message key="table.type" /></label> <select
								class="custom-select" id="comfortType"
								aria-discribdby="comfortTypeHelp" name="comfortType" required>
								<option value="COMPARTMENT"><fmt:message key="comfort.type.compartment" /></option>
								<option value="COUCHETTE"><fmt:message key="comfort.type.couchette" /></option>
								<option value="SITTING"><fmt:message key="comfort.type.sitting" /></option>
							</select> <small id="comfortTypeHelp" class="form-text text-muted"><fmt:message key="table.type.help" /></small>

						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="priceCoefficient"><fmt:message key="label.price.coefficient" /></label> <input
								type="number" name="priceCoefficient" class="form-control"
								id="priceCoefficient" aria-discribdby="priceCoefficientHelp"
								value="${priceCoefficient}" min="1.00" step="0.01" required>
							<small id="priceCoefficientHelp" class="form-text text-muted"><fmt:message key="label.price.coefficient" /></small>
						</div>
					</div>

					<div class="form-group mt-3">
						
							<input type="file" id="modelImage" name="modelImageURL"
								accept="image/*,image/jpeg">
						
					</div>
				</c:if>

				<c:if test="${errorAddCarriage == null }">

					<div class="form-row">
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="modelName"><fmt:message key="label.model.name" /></label> <input type="text"
								name="modelName" class="form-control" id="modelName"
								aria-discribdby="modelNameHelp" minlenght="2" maxlength="20"
								required ><small id="firstNameHelp"
								class="form-text text-muted"><fmt:message key="label.model.name.help" />
								</small>
						</div>

						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="totalSeats"><fmt:message key="label.total.seats" /></label> <input type="number"
								name="totalSeats" class="form-control" id="totalSeats"
								aria-discribdby="totalSeatsHelp" max="200"
								step="1" required> <small id="totalSeatsHelp"
								class="form-text text-muted"><fmt:message key="label.total.seats.help" /></small>
						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">

							<label for="comfortType"><fmt:message key="table.type" /></label> <select
								class="custom-select" id="comfortType"
								aria-discribdby="comfortTypeHelp" name="comfortType" required>
								<option value="COMPARTMENT"><fmt:message key="comfort.type.compartment" /></option>
								<option value="COUCHETTE"><fmt:message key="comfort.type.couchette" /></option>
								<option value="SITTING"><fmt:message key="comfort.type.sitting" /></option>
							</select> <small id="comfortTypeHelp" class="form-text text-muted"><fmt:message key="table.type.help" /></small>

						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="priceCoefficient"><fmt:message key="label.price.coefficient" /></label> <input
								type="number" name="priceCoefficient" class="form-control"
								id="priceCoefficient" aria-discribdby="priceCoefficientHelp"
								 min="1.00" step="0.01" required>
							<small id="priceCoefficientHelp" class="form-text text-muted"><fmt:message key="label.price.coefficient" /></small>
						</div>
					</div>

					<div class="form-group mt-3 p-2">

						<input type="file" class="file" name="modelImageURL"
							accept="image/*,image/jpeg">

					</div>
				</c:if>
			</form>
		</div>
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		<div class="row w-100 mt-3">
		<div class="col-10"></div>
		<div class="col-2 mt-3">
			<form
				action="${pageContext.request.contextPath}/admin-view-all-carriages"
				method="post">
				<button type="submit" class="btn btn-danger btn-lg"><fmt:message key="button.cancel" /></button>
			</form>
		</div>
	</div>
	</div>

</jsp:body>
</tag:page>