<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<c:import url="/WEB-INF/pages/components/header.jsp" />

	<div class="row">
		<div class="col-5"></div>
		<div class="col-2">
			<img src="static/img/header/email-icon.png" alt="" class="d-block w-100">
		</div>
		<div class="col-5"></div>
	</div>
	<div class="row">
		<div class="container">
			<c:if test="${activationEmailError != null }">
				<div class="alert alert-danger" role="alert">
					${activationEmailError}</div>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="container">
			<p class="h1 text-center text-fluid m-3">Verification code was send
				into you email address. Please check and enter the verification code
				in the form below.
			<p>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-4 col-md-4 col-xs-0"></div>
		<div class="col-lg-4 col-md-4 col-xs-12 m-3">
			<form method="post"
				action="${pageContext.request.contextPath}/client-check-activation-code">
				<label for="activationCode">Verification code</label> <input
					type="text" name="activationCode"
					class="form-control form-control-lg " id="activationCode"
					aria-discribdby="activationCodeHelp" required> <small
					id="verificationCodeHelp" class="form-text text-muted">Enter
					you verification code from email</small>
				<div class="row">
					<button class="btn btn-outline-success btn-lg d-block mx-auto mt-3"
						type="submit">Activete</button>
				</div>
			</form>
		</div>
		<div class="col-lg-4 col-md-4 col-xs-0"></div>
	</div>

	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>