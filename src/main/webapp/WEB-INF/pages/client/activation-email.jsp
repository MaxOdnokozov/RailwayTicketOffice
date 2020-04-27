<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>
	
	<div class="row">
		<div class="col-5"></div>
		<div class="col-2">
			<img src="static/img/header/email-icon.png" alt=""
					class="d-block w-100">
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
			<p class="h1 text-center text-fluid m-3">
					<fmt:message key="label.activation.help" />
			
				<p>
		
			</div>
	</div>
	<div class="row">
		<div class="col-lg-4 col-md-4 col-xs-0"></div>
		<div class="col-lg-4 col-md-4 col-xs-12 m-3">
			<form method="post"
					action="${pageContext.request.contextPath}/client-check-activation-code">
				<label for="activationCode"><fmt:message
							key="label.verification.code" /></label> <input type="text"
						name="activationCode" class="form-control form-control-lg "
						id="activationCode" aria-discribdby="activationCodeHelp" required> <small
						id="verificationCodeHelp" class="form-text text-muted"><fmt:message
							key="label.verification.code.help" /></small>
				<div class="row">
					<button class="btn btn-outline-success btn-lg d-block mx-auto mt-3"
							type="submit">
							<fmt:message key="button.activete" />
						</button>
				</div>
			</form>
		</div>
		<div class="col-lg-4 col-md-4 col-xs-0"></div>
	</div>

	</jsp:body>
</tag:page>