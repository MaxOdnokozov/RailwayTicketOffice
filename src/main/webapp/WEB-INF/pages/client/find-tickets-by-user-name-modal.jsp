<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>

<div class="modal fade" id="byUserName" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Find by user name</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<form
						action="${pageContext.request.contextPath}/admin-find-tickets-by-user-name"
						method="post">
							
						
							<div class="form-group">
								<label for="firstName">First name</label>
								<input type="text" name="firstName" class="form-control" id="firstName" aria-discribdby="firstNameHelp" placeholder="First name" minlenght="2" required>
								
							</div>
							<div class="form-group">
								<label for="lastName">Last name</label>
								<input type="text" name="lastName" class="form-control" id="lastName" aria-discribdby="lastNameHelp" placeholder="Last name" minlenght="2" required>
								
							</div>

						<div class="form-group">
							<button type="submit" class="btn btn-outline-primary btn-lg">Find</button>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
