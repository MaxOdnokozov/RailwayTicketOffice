<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<c:import url="/WEB-INF/pages/components/header.jsp" />
	
	<div class="row mt-5">
		<div class="container mt-5">
		<div class="alert alert-danger text-center m-5" role="alert">
			<p class="h1 text-center text-fluid text-success">Payment was canceled because ticket payment timed out<p>
		</div>
		</div>
	</div>

	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>