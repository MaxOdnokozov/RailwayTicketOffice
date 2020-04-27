<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal fade " id="addCode" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content modal-md">
			<div class="modal-header">
				<h5 class="modal-title"><fmt:message key="label.route.code" /></h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<form
						action="${pageContext.request.contextPath}/admin-save-route-code"
						method="post">
						<div class="form-group">
							<label class="h1 mt-3 text-center" for="code"><fmt:message key="label.route.code" /></label> <input type="text" name="code"
								class="h1 form-control" id="code"
								aria-discribdby="codeHelp" minlenght="2" maxlength="10"
								required value="${sessionScope.code}"><small
								id="codeHelp" class="h6 form-text text-muted"><fmt:message key="label.route.code.help" /></small>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-outline-primary btn-lg"><fmt:message key="button.save" /></button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
