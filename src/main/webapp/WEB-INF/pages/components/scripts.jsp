<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--=========================================================================== 
Scripts for Bootstrap-4.
===========================================================================--%>
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/popper.js/1.14.3/popper.min.js"></script>
<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="static/script/fileinput.min.js" type="text/javascript"></script>

<c:if test="${requestScope.error != null }">
	<script>
		$(document).ready(function() {
			$('#errorModal').modal('show');
		});
	</script>
</c:if>


<c:if test="${requestScope.errorAuthentication != null }">
	<script>
		$(document).ready(function() {
			$('#logIn').modal('show');
		});
	</script>
</c:if>
<c:if test="${requestScope.errorRegistrationMessage != null }">
	<script>
		$(document).ready(function() {
			$('#registration').modal('show');
		});
	</script>

	<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>

<script>
    $(function() {
        setTimeout(function(){
            $('body').removeClass('loading');
        }, 1000);
    });
</script>

</c:if>
