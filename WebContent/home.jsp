<%@ include file="layout/_header.jspf" %>

<div class="container">
	<div class="row">
		<div class="col m4"></div>
		<div class="col m4"></div>
		<div class="col m4">
			<div class="cart-display">
			<a href="${ contextPath }/cart/display">
			Cart <i class="fa fa-shopping-cart fa-2x" id="cart-icon">0</i>
			</a> 
			</div>
		</div>
	</div>
	<div class="row">
		<%-- <t:printProducts/> --%>
		${ productsList }
	</div>
</div>

<%@ include file="layout/_footer.jspf" %>