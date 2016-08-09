<%@ include file="layout/_header.jspf"%>

<div class="container">
<%@ include file="layout/_topCart.jspf" %>
	<div class="row">
		<div class="col m2"></div>
		<div class="col m8">
			<div class="product-container">
				<div class="product-container__header">
				${ prodName }
				</div>
				<div class="product-container__body">
				<label>Product Price: </label>${ prodPrice }<br/>
				<label>Product Supplier: </label>${ prodSupplier }<br/>
				<label>Product Stock: </label>${ prodStock }<br/>
				<a class="btn green" href="${ contextPath }/cart/add?pid=${ prodID }" id="addCartBtn">Add to Cart</a>
				</div>
			</div>
		</div>
		<div class="col m2"></div>
	</div>
</div>


<%@ include file="layout/_footer.jspf"%>