<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="addCarriage" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content modal-lg">
			<div class="modal-header">
				<h5 class="modal-title">New stop</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container w-100">
						<c:forEach items="${applicationScope['contextCarriages']}"
							var="carriage">

							<div class="border border-primary pl-4 ml-2 mt-3 rounded">
								<div class="form-group">
									<div class="row w-100 p-3">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}${applicationScope['contextImagesDir']}/${carriage.getImage()}">
									</div>
								</div>

								<div class="form-group">
									<div class="row w-100 pl-3">
										
											<div class="row w-100"><h5>Model: ${carriage.getModel()}</h5></div>
											<div class="row w-100"><h5>Comfort type:
												${carriage.getComfortType()}</h5></div>
											<div class="row w-100"><h5>Total seats:
												${carriage.getTotalSeats()}</h5></div>
											<div class="row w-100"><h5>Price coefficient:
												${carriage.getPriceCoefficient()}</h5></div>							
										<div class="row mt-3">
											<form
												action="${pageContext.request.contextPath}/admin-save-route-carriage"
												method="post">
												<input type="hidden" name="carriageId"
													value="${carriage.getId()}">
												<button class="btn btn-primary bnt-lg">Select</button>
											</form>
										</div>							
								</div>
								</div>
							</div>
						</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
