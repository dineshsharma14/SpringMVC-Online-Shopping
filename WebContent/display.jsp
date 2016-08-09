<%@ include file="layout/_header.jspf"%>

<div class="container">
	<%@ include file="layout/_topCart.jspf" %>
	<div class="row">
		<div class="col m2"></div>
		<div class="col m8">
			<t:printCartItems />
			<p>
			<a class="btn blue pull-right" href="${ contextPath }/cart/order">Place Order</a>
		</div>
		<div class="col m2"></div>
	</div>
</div>

<%@ include file="layout/_footer.jspf"%>