<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<c:import url="/WEB-INF/pages/components/header.jsp" />

	<div class="row mt-5">
		<div class="col-5"></div>
		<div class="col-2">
			<img src="static/img/header/check-icon.png" alt="" class="d-block w-100">
		</div>
		<div class="col-5"></div>
	</div>
	
	<div class="row">
		<div class="container">
			<p class="h1 text-center text-fluid text-success">Activation Successful!<p>
		</div>
	</div>

	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>