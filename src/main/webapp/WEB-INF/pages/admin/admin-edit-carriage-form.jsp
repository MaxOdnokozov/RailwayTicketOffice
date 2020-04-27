<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/customtag.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>
	<div>
		<div class="container-fluid p-0">
			<img src="static/img/header/eurostar-train-add-carriage-form.jpg"
				alt="" class="d-block w-100">
		</div>
	</div>
	<div class="display-4 mt-3 text-center"><fmt:message key="button.edit"/></div>


	<form class="form-group" enctype="multipart/form-data" method="post"
		action="${pageContext.request.contextPath}/admin-save-edit-carriage">
		<div class="row w-100">
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		<div class="col-lg-10 col-md-8 col-xs-12 m-3">
			<div class="form-group mt-3 p-2">
				<input type="file" class="file" name="modelImageURL"
					accept="image/*,image/jpeg">
					<input type="hidden" name="carriageId" value="${carriageId}"/>
			</div>
		</div>
		<div class="col-lg-1 col-md-2 col-xs-12"></div>
		</div>
	</form>
	<div class="row w-100 mt-3">
		<div class="col-10"></div>
		<div class="col-2 mt-3">
			<form
				action="${pageContext.request.contextPath}/admin-view-all-carriages"
				method="post">
				<button type="submit" class="btn btn-danger btn-lg"><fmt:message key="button.cancel"/></button>
			</form>
		</div>
	</div>
	
</jsp:body>
</tag:page>