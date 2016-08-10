<%@ include file="layout/_header.jspf" %>

<div class="container">
	<%@ include file="layout/_topCart.jspf" %>
	<div class="row">
		<%-- <t:printProducts/> --%>
		${ productsList }
	</div>
</div>

<%@ include file="layout/_footer.jspf" %>