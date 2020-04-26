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
	<div class="display-4 mt-3 text-center">Add new model of carriage</div>
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
							<label for="modelName">Model name</label> <input type="text"
								name="modelName" class="form-control" id="modelName"
								aria-discribdby="modelNameHelp" minlenght="2" maxlength="20"
								required value="${modelName}"><small id="firstNameHelp"
								class="form-text text-muted">Enter a new model name of
								carriage. Max length of model name must be less than 20
								characters</small>
						</div>

						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="totalSeats">Total seats</label> <input type="number"
								name="totalSeats" class="form-control" id="totalSeats"
								aria-discribdby="totalSeatsHelp" value="${totalSeats}" max="200"
								step="1" required> <small id="totalSeatsHelp"
								class="form-text text-muted">Enter total seats of the
								carriage. Max total seats must be less than 200 seats</small>
						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">

							<label for="comfortType">Comfort type</label> <select
								class="custom-select" id="comfortType"
								aria-discribdby="comfortTypeHelp" name="comfortType" required>
								<option value="COMPARTMENT">Compartment</option>
								<option value="COUCHETTE">Couchette</option>
								<option value="SITTING">Sitting</option>
							</select> <small id="comfortTypeHelp" class="form-text text-muted">Choose
								comfort type of carriage</small>

						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="priceCoefficient">Price coefficient</label> <input
								type="number" name="priceCoefficient" class="form-control"
								id="priceCoefficient" aria-discribdby="priceCoefficientHelp"
								value="${priceCoefficient}" min="1.00" step="0.01" required>
							<small id="priceCoefficientHelp" class="form-text text-muted">Enter
								price coefficient</small>
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
							<label for="modelName">Model name</label> <input type="text"
								name="modelName" class="form-control" id="modelName"
								aria-discribdby="modelNameHelp" placeholder="Model name"
								minlenght="2" maxlength="20" required> <small
								id="firstNameHelp" class="form-text text-muted">Enter a
								new model name of carriage. Max length of model name must be
								less than 20 characters</small>
						</div>

						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="totalSeats">Total seats</label> <input type="number"
								name="totalSeats" class="form-control" id="totalSeats"
								aria-discribdby="totalSeatsHelp" placeholder="Total seats"
								max="200" step="1" required> <small id="totalSeatsHelp"
								class="form-text text-muted">Enter total seats of the
								carriage. Max total seats must be less than 200 seats</small>
						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">

							<label for="comfortType">Comfort type</label> <select required
								class="custom-select" id="comfortType "
								aria-discribdby="comfortTypeHelp" name="comfortType">
								<option value="COMPARTMENT">Compartment</option>
								<option value="COUCHETTE">Couchette</option>
								<option value="SITTING">Sitting</option>
							</select> <small id="comfortTypeHelp" class="form-text text-muted">Choose
								comfort type of carriage</small>

						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 mb-3">
							<label for="priceCoefficient">Price coefficient</label> <input
								type="number" name="priceCoefficient" class="form-control"
								id="priceCoefficient" aria-discribdby="priceCoefficientHelp"
								value="1.00" min="1.00" step="0.01" required> <small
								id="priceCoefficientHelp" class="form-text text-muted">Enter
								price coefficient</small>
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
				<button type="submit" class="btn btn-danger btn-lg">Cancel</button>
			</form>
		</div>
	</div>
	</div>

</jsp:body>
</tag:page>