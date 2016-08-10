$(document).ready(function () {
	
	setCartSize();
	function setCartSize() {
		var $cart = $("#cart-icon");
		$.get("/OnlineShopper/cart/size", function (data) {
			$cart.html(data);
			console.log(data);
		});
	}
	
	$("body").on("click", "#addCartBtn", function (e) {
		e.preventDefault();
		var url = $(this).attr("href");
		var $cart = $("#cart-icon");
		
		$.ajax({
			url: url,
			type: "GET",
			success: function (data) {
				$cart.html(data);
				Materialize.toast('Item added to the cart', 4000);
				console.log(data);
			},
			error: function (xhr, status, msg) {
//				console.log (xhr.responseText);
				Materialize.toast("Product Out Of Stock. Stop Clicking....", 5000);
			}
		});
	});
	
	$("body").on("click", "#removeCartBtn", function (e) {
		e.preventDefault();
		var url = $(this).attr("href");
		var $cart = $("#cart-icon");
		var parentList = $(this).parent().parent();
		
		$.ajax({
			url: url,
			type: "GET",
			success: function (data) {
//				$cart.html(data);
//				Materialize.toast('Item removed from the cart', 4000);
//				parentList.remove();
//				console.log(data);
				location.reload();
			},
			error: function (xhr, status, msg) {
				console.log (xhr.responseText);
			}
		});
	});
	
});