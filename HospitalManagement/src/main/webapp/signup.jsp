<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	 <%@page isELIgnored="false"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<style>
.card-body {
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3);
}
</style>
<%@include file="component/allcss.jsp"%>
</head>
<body>
	<%@include file="component/navbar.jsp"%>

	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">Register User</p>
						<c:if test="${not empty sucMsg}">
						 <p class="text-center text-success fs-3">${sucMsg}</p>
						 <c:remove var="sucMsg" scope="session"/>
						</c:if>
						
						<c:if test="${not empty errorMsg}">
						 <p class="text-center text-danger fs-3">${errorMsg}</p>
						 <c:remove var="errorMsg" scope="session"/>
						</c:if>
						<form action="user_register" method="post">
						<div class="mb-3">
								<label class="form-Label">Full Name</label> <input required
									name="fullname" type="text" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-Label">Email</label> <input required
									name="email" type="email" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-Label ">Password</label> <input required
									name="password" type="password" class="form-control">
							</div>
							<button type="submit" class="btn bg-success text-white col-md-12">Register</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="component/footer.jsp" %>
</body>
</html>