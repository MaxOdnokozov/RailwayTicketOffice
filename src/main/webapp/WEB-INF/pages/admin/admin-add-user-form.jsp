<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<c:import url="/WEB-INF/pages/components/header.jsp" />
	<div>
		<div class="container-fluid p-0">
			<img src="static/img/header/new-user-icon-png-1.png" alt=""
				class="d-block w-100">
		</div>
	</div>
	<div class="row w-100 mt-3">
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		<div class="col-lg-9 col-md-8 col-xs-12">

			<div class="container-fluid">
				<form method="post"
					action="${pageContext.request.contextPath}/admin-add-user">
					<c:if test="${errorRegistrationMessage != null }">
						<div class="alert alert-danger" role="alert">
							${errorRegistrationMessage}</div>
					</c:if>
					<div class="form-group">
						<label for="emailRegistration">Email address</label>
						<c:if test="${requestScope.email == null}">
							<input type="email" name="emailRegistration" class="form-control"
								id="emailRegistration" aria-discribdby="emailHelp"
								placeholder="Example@domain.com" required>
						</c:if>
						<c:if test="${requestScope.email != null}">
							<input type="email" name="emailRegistration" class="form-control"
								id="emailRegistration" aria-discribdby="emailHelp"
								value="${email}" required>
						</c:if>
						<small id="emailHelp" class="form-text text-muted">To
							verify the data provided we have sent you a confirmation email.</small>
					</div>


					<div class="form-group">
						<label for="firstName">First name</label> <input type="text"
							name="firstName" class="form-control" id="firstName"
							aria-discribdby="firstNameHelp" placeholder="First name"
							minlenght="2" required> <small id="firstNameHelp"
							class="form-text text-muted">Enter you first name</small>
					</div>
					<div class="form-group">
						<label for="lastName">Last name</label> <input type="text"
							name="lastName" class="form-control" id="lastName"
							aria-discribdby="lastNameHelp" placeholder="Last name"
							minlenght="2" required> <small id="lastNameHelp"
							class="form-text text-muted">Enter you last name</small>
					</div>

					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							name="password" class="form-control" id="password"
							aria-discribdby="passwordHelp" placeholder="Password"
							minlenght="8" required> <small id="passHelp"
							class="form-text text-muted">Enter you password</small>
					</div>
					<div class="form-group">
						<label for="repeatedPassword">Password</label> <input
							type="password" name="repeatedPassword" class="form-control"
							id="repeatedPassword" aria-discribdby="repeatedPasswordHelp"
							placeholder="Password" minlenght="8" required> <small
							id="repeatPasswordHelp" class="form-text text-muted">Repeat
							you password</small>
					</div>
					<button class="btn btn-primary" type="submit">Registrate</button>
				</form>
			</div>
		</div>
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
	</div>

<div class="row-fluid h-50"></div>

	<%@ include file="/WEB-INF/pages/components/footer.jspf"%>
	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>