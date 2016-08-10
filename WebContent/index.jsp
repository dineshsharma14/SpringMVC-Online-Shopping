<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="layout/_header.jspf"%>

<div class="container">
	<div class="row">
		<div class="col m2"></div>
		<div class="col m8">
			<h2>User Login</h2>

			<form:form commandName="user" method="POST" action="login">
				<table>
					<tr>
						<td>
							<span class="red-text text-darken-2">
								<c:if test="${ not empty error }">
									Invalid Credentials
								</c:if>
							</span>
						</td>
					</tr>
					<tr>
						<td><form:label path="username">Username</form:label></td>
						<td><form:input path="username" /></td>
						<td><form:errors cssClass="red-text text-darken-1" path="username" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Password</form:label></td>
						<td><form:input path="password" /></td>
						<td><form:errors cssClass="red-text text-darken-1" path="password" /></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2"><input class="btn blue" type="submit" value="Login" /></td>
					</tr>
				</table>
			</form:form>
		</div>
		<div class="col m2"></div>
	</div>
</div>

<%@include file="layout/_footer.jspf"%>
