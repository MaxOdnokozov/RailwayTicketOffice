<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="errorModal" role="dialog">
	<div class="modal-dialog modal-md" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="ErrorsModalLable">Error ${error}</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<c:if test="${error.equals('404')}">
					<div class="text-center">
						<div class="display-1">Oops!</div>
						<h1 class="text-danger">404 - The Page can't be found</h1>
					</div>
				</c:if>
				<c:if test="${error.equals('403')}">
					<div>
						<c:if test="${role == null}">
						<div class="display-1 text-center">Oops!</div>
							<h2 class="text-center text-danger">You must be logged in to perform
								this action.</h2>
							<h1 class="text-center">
								<a href="#" data-toggle="modal" data-target="#logIn">Login</a>
							</h1>
						</c:if>
						<c:if test="${role.equals('ADMIN')}">
							<h2>
								You do not have permissions to access this page<b>:(</b>
							</h2>
							<a href="${pageContext.request.contextPath}/admin-home-page">Go
								TO Home page</a>
						</c:if>
						<c:if test="${role.equals('CLIENT')}">
							<h2>
								You do not have permissions to access this page<b>:(</b>
							</h2>
							<a href="${pageContext.request.contextPath}/client-home-page">Go
								TO Home page</a>
						</c:if>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

