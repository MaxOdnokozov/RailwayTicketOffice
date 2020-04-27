<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>

<div class="modal fade" id="byUserEmail" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"><fmt:message key="label.find.by.email" /></h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<form
						action="${pageContext.request.contextPath}/admin-find-tickets"
						method="post">
							
						<div class="form-group">
								<label for="emailRegistration"><fmt:message key="label.email.address" /></label>
								
									<input type="email" name="email" class="form-control" id="email" aria-discribdby="emailHelp"  placeholder="Example@domain.com" required>
								
							</div>

						<div class="form-group">
							<button type="submit" class="btn btn-outline-primary btn-lg"><fmt:message key="button.find" /></button>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
