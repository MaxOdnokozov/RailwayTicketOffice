<%@ tag isELIgnored="false" description="Overall Page template"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="lang"
	value="${not empty lang ? lang : pageContext.request.locale.language}"
	scope="session" />
<fmt:setLocale value="${lang}" scope="session" />

<html>
<c:import url="/WEB-INF/pages/components/head.jsp" />
<body>
	<header>
		<c:import url="/WEB-INF/pages/components/header.jsp" />
	</header>



	<c:import url="/WEB-INF/pages/components/error-modal-window.jsp" />
	<%--=========================================================================== 
	Login modal window. 
===========================================================================--%>

	<c:import url="/WEB-INF/pages/components/login-modal-window.jsp" />

	<%--=========================================================================== 
	Registration modal window. 

===========================================================================--%>
	<c:import
		url="/WEB-INF/pages/components/registration-account-modal-window.jsp" />

	<%--=========================================================================== 
	Errors modal window. 
===========================================================================--%>


	<div class="content">
		<jsp:doBody />
	</div>
	<div class="row-fluid h-75"></div>
	<%@ include file="/WEB-INF/pages/components/footer.jspf"%>
	<c:import url="/WEB-INF/pages/components/scripts.jsp" />
</body>
</html>