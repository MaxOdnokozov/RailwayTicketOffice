<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/"%>

<tag:page>
	<jsp:body>
	<div class="row mt-5">
		<div class="container mt-5">
		<div class="alert alert-danger text-center m-5" role="alert">
			<p class="h1 text-center text-fluid text-success"><fmt:message key="label.payment.not.ok" />Payment was canceled because ticket payment timed out<p>
		</div>
		</div>
	</div>

</jsp:body>
</tag:page>