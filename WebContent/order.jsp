<%@include file="layout/_header.jspf"%>

<div class="container">
	<%@include file="layout/_topCart.jspf"%>
	<div class="row">
		<div class="col m2"></div>
		<div class="col m8">
			<table class="table">
				<tr>
					<th>Product ID</th>
					<th>Product Name</th>
					<th>Product Quantity</th>
					<th>Product Price</th>
				</tr>
				<t:printCartOrderTable/>
				<tr>
					<td><label>Total Price: </label>
					${ total }
					</td>
				</tr>
					
				
			</table>
			<a href="${contextPath }/logout" class="btn red">Logout</a>
		</div>
		<div class="col m2"></div>
	</div>
</div>

<%@include file="layout/_footer.jspf"%>