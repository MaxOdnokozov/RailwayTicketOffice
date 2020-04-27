<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>

	<div class="row mt-5">
		<div class="col-5"></div>
		<div class="col-2">
			<img src="static/img/header/check-icon.png" alt="" class="d-block w-100">
		</div>
		<div class="col-5"></div>
	</div>
	
	<div class="row">
		<div class="container">
			<p class="h1 text-center text-fluid text-success"><fmt:message key="label.activation.ok" /><p>
		</div>
	</div>
</jsp:body>
</tag:page>