<%@page import="com.entity.Specialist"%>
<%@page import="java.util.List"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.Dao.SpecialistDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<style>
  .paint-card{
   box-shadow:0px 0px 10px 0px rgba(0,0,0,0.3);
  }
</style>

</head>
<%@include file="../component/allcss.jsp"%>
<body>
	<c:if test="${empty docObj}">
		<c:redirect url="../doctor_login.jsp"></c:redirect>
	</c:if>

	<%@include file="navbar.jsp"%>
	
	<div class="container p-4">
		<div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">Change Password</p>
						
						<c:if test="${not empty sucMsg}">
						 <p class="text-center text-success fs-3">${sucMsg}</p>
						 <c:remove var="sucMsg" scope="session"/>
						</c:if>
						
						<c:if test="${not empty errorMsg}">
						 <p class="text-center text-danger fs-5">${errorMsg}</p>
						 <c:remove var="errorMsg" scope="session"/>
						</c:if>

						<form action="../docPasswordChange" method="post">
							<div class="mb-3">
								<label class="form-Label">Enter Old Password</label> <input required
									name="oldpassword" type="text" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-Label ">Enter New Password</label> <input required
									name="newpassword" type="text" class="form-control">
							</div>
							
							<input type="hidden" value="${docObj.id}" name="uid" >
							<button type="submit" class="btn bg-primary text-white col-md-12">Change Password</button>
						</form>
					</div>
				</div>
			</div>
			
			<div class="col-md-5 offset-md-2">
			   <div class="card paint-card">
			      <div class="card-body">
			      <p class="text-center fs-3">Edit profile</p>
			      <c:if test="${not empty sucMsgd}">
						 <p class="text-center text-success fs-3">${sucMsgd}</p>
						 <c:remove var="sucMsg" scope="session"/>
						</c:if>
						
						<c:if test="${not empty errorMsgd}">
						 <p class="text-center text-danger fs-5">${errorMsgd}</p>
						 <c:remove var="errorMsg" scope="session"/>
						</c:if>
			        <form action="../docEditProfile" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									required name="fullname" class="form-control" value="${docObj.fullName}">
							</div>

							<div class="mb-3">
								<label class="form-label">Date of Birth</label> <input
									type="date" required name="dob" class="form-control" value="${docObj.dob}">
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input
									type="text" required name="qualification" class="form-control" value="${docObj.qualification}">
							</div>

							<div class="mb-3">
								<label class="form-label">Specialist</label> <select name="specialist"
									required class="form-control">
									<option>${docObj.specialist}</option>
									<%SpecialistDao sdao = new SpecialistDao(DBConnect.getConn()); 
									List<Specialist> list =sdao.getAllSpecialist();
									for(Specialist s:list)
									{%>
										<option><%=s.getSpecialistName() %></option>
									<%}
									%>
									
								</select>

							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input readonly type="text"
									required name="email" class="form-control" readonly value="${docObj.email}">
							</div>

							<div class="mb-3">
								<label class="form-label">Mobile no</label> <input type="text"
									required name="mobno" class="form-control" value="${docObj.mobNo }">
							</div>

							<input type="hidden" name="id" value="${docObj.id}">

							<button type="submit" class="btn btn-primary">Update</button>
						</form>
			      </div>
			   </div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>