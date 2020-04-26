<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--=========================================================================== 
Header (top area). 
===========================================================================--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="welcome.jsp"><fmt:message
			key="label.project.name" /></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<c:if test="${user.getRole() == 'ADMIN'}">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item"><form method="post"
						action="${pageContext.request.contextPath}/admin-home-page">
						<button type="submit" class="btn btn-outline-light border-0">Home</button>
					</form></li>

				<li class="nav-item">
					<div class="dropdown">
						<button class="btn btn-outline-light dropdown-toggle border-0"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Users</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<form method="post"
								action="${pageContext.request.contextPath}/admin-view-all-users">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">View
									all users</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/admin-add-user-form">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">Add a
									new user</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/admin-find-user">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">Find
									user</button>
							</form>

						</div>
					</div>

				</li>
				<li class="nav-item">
					<div class="dropdown">
						<button class="btn btn-outline-light dropdown-toggle border-0"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Stations</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<form method="post"
								action="${pageContext.request.contextPath}/admin-view-all-stations">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">View
									all stations</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/admin-add-station-form">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">Add a
									new station</button>
							</form>

						</div>
					</div>
				</li>
				<li class="nav-item">
					<div class="dropdown">
						<button class="btn btn-outline-light dropdown-toggle border-0"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Carriages</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<form method="post"
								action="${pageContext.request.contextPath}/admin-view-all-carriages">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">View
									all models of carriages</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/admin-add-carraige-form">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">Add a
									new model of carriage</button>
							</form>

						</div>
					</div>
				</li>
				<li class="nav-item">
					<div class="dropdown">
						<button class="btn btn-outline-light dropdown-toggle border-0"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Routes</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<form method="post"
								action="${pageContext.request.contextPath}/admin-view-routes">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">View
									 routes</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/admin-add-route-form">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">Add a
									new rotes</button>
							</form>

						</div>
					</div>
				</li>

				<li class="nav-item">
					<div class="dropdown">
						<button class="btn btn-outline-light dropdown-toggle border-0"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Settings</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<form method="post"
								action="${pageContext.request.contextPath}/admin-profile-settings">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">Profile
									settings</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/admin-system-settings">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">Systems
									settings</button>
							</form>

						</div>
					</div>
				</li>

			</ul>
		</c:if>
		<c:if test="${user.getRole() == 'CLIENT'}">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item"><form method="post"
						action="${pageContext.request.contextPath}/client-home-page">
						<button type="submit" class="btn btn-outline-light border-0">Home</button>
					</form></li>

				<li class="nav-item">
					<div class="dropdown">
						<button class="btn btn-outline-light dropdown-toggle border-0"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Settings</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<form method="post"
								action="${pageContext.request.contextPath}/admin-profile-settings">
								<button type="submit"
									class="btn btn-outline-light border-0 text-dark">Profile
									settings</button>
							</form>
						</div>
					</div>
				</li>
			</ul>

		</c:if>

		<c:if test="${user == null}">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link m-1" href="#"
					data-toggle="modal" data-target="#logIn"><fmt:message
							key="nav.bar.label.login" /></a></li>
				<li class="nav-item"><a class="nav-link m-1" href="#"
					data-toggle="modal" data-target="#registration"><fmt:message
							key="nav.bar.label.registration" /></a></li>
			</ul>
		</c:if>

		<c:forEach items="${applicationScope['contextLanguages']}"
			var="contextlanguage">
			<form class="form-inline my-2 my-lg-0">
				<c:if test="${contextlanguage.getLabel().equals(lang)}">
					<button class="btn btn-outline-success my-2 my-sm-0">${contextlanguage.getLabel().toUpperCase()}</button>
					<input type="hidden" name="lang"
						value="${contextlanguage.getLabel()}">
				</c:if>
				<c:if test="${!contextlanguage.getLabel().equals(lang)}">
					<button class="btn btn-outline-light m-1 border-0">${contextlanguage.getLabel().toUpperCase()}</button>
					<input type="hidden" name="lang"
						value="${contextlanguage.getLabel()}">
				</c:if>
				<c:import url="/WEB-INF/pages/components/request-parameters.jsp"/>
			</form>
		</c:forEach>


		<c:if test="${user != null}">
			<div class="ml-3">
				<form class="form-inline my-2 my-lg-0" method="post"
					action="${pageContext.request.contextPath}/logout">
					<button type="submit" class="btn btn-outline-danger">Logout</button>
				</form>
			</div>
		</c:if>

	</div>
</nav>