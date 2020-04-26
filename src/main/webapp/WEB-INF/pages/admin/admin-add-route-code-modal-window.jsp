<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade " id="addCode" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content modal-sm">
			<div class="modal-header">
				<h5 class="modal-title">Code</h5>
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
							<label class="h1 mt-3 text-center" for="code">Route
								code:</label> <input type="text" name="code"
								class="h1 form-control" id="code"
								aria-discribdby="codeHelp" minlenght="2" maxlength="10"
								required value="${sessionScope.code}"><small
								id="codeHelp" class="h6 form-text text-muted">Max
								length of route code must be less than 10 characters and consist
								only from A-Z, 0-9 characters.</small>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-outline-primary btn-lg">Save</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
