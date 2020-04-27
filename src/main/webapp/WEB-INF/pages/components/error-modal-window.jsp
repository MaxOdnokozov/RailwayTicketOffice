<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
						<div class="display-1"><fmt:message key="label.oops" /></div>
						<h1 class="text-danger"><fmt:message key="label.404" /></h1>
					</div>
				</c:if>
				<c:if test="${error.equals('403')}">
					<div>
						<c:if test="${user == null}">
						<div class="display-1 text-center"><fmt:message key="label.oops" /></div>
							<h2 class="text-center text-danger"><fmt:message key="label.403" />
								</h2>
							<h1 class="text-center">
								<a href="#" data-toggle="modal" data-target="#logIn"><fmt:message key="nav.bar.label.login" /></a>
							</h1>
						</c:if>
						<c:if test="${user != null}">
						<c:if test="${user.getRole().getName().equals('ADMIN')}">
							<h2>
								<fmt:message key="label.403.role" /><b>:(</b>
							</h2>
							<a href="${pageContext.request.contextPath}/admin-home-page">
								<fmt:message key="label.to.home.page" /></a>
						</c:if>
						<c:if test="${user.getRole().getName().equals('CLIENT')}">
							<h2>
								<fmt:message key="label.403.role" /><b>:(</b>
							</h2>
							<a href="${pageContext.request.contextPath}/client-home-page">
								<fmt:message key="label.to.home.page" /></a>
						</c:if>
						</c:if>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

