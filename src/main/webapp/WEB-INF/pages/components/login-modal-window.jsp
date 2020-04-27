<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="modal fade" id="logIn" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="logInModalLable"><fmt:message key="nav.bar.label.login" /></h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" >
					<div class="container-fluid">
						<form method="post" action="${pageContext.request.contextPath}/login">
							<c:if test="${requestScope.errorAuthentication != null }">
								<div class="alert alert-danger" role="alert"> 
									${requestScope.errorAuthentication}
								</div>
							</c:if>
							<div class="form-group">
								<label for="email"><fmt:message key="label.email.address" /></label>
								<c:if test="${requestScope.email == null}">
									<input type="email"  name="email" class="form-control" id="email" aria-discribdby="emailHelp" placeholder="Example@domain.com" required>
									<small id="passHelp" class="form-text text-muted"><fmt:message key="label.email.address.help.short" /></small>	
								</c:if>
								<c:if test="${requestScope.email != null}">
									<input type="email" name="email" class="form-control" id="email" aria-discribdby="emailHelp" value="${email}">
								</c:if>
							</div>
							<div class="form-group">
								<label for="inputPassword"><fmt:message key="label.password" /></label>
								<input type="password" name="password" class="form-control" id="inputPassword" aria-discribdby="passHelp" placeholder="<fmt:message key="label.password" />" required>
								<small id="passHelp" class="form-text text-muted"><fmt:message key="label.password.help" /></small>
							</div>
							<div class="row">
						
									<button class="btn btn-primary mr-5 ml-3" type="submit"><fmt:message key="button.login" /></button>

							
							<a class="link ml-5" href="#"data-toggle="modal" data-target="#registration"><fmt:message key="nav.bar.label.registration" /></a>
							
							</div>
						
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	