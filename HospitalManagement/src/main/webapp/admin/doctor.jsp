<%@page import="com.entity.Doctor"%>
<%@page import="com.Dao.DoctorDao"%>
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
<style>
 .card-body{
  box-shadow: 0px 0px 10px 0 rgba(0,0,0,0.3);
 }
</style>
<title>Doctor page</title>
<style>
</style>
<%@include file="../component/allcss.jsp"%>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Add Doctor</p>
						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-3">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>

						<c:if test="${not empty sucMsg}">
							<p class="text-center text-success fs-5">${sucMsg}</p>
							<c:remove var="sucMsg" scope="session" />
						</c:if>
						<form action="../addDoctor" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									required name="fullname" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Date of Birth</label> <input
									type="date" required name="dob" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input
									type="text" required name="qualification" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Specialist</label> <select name="specialist"
									required class="form-control">
									<option>--Select--</option>
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
								<label class="form-label">Email</label> <input type="text"
									required name="email" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Mobile no</label> <input type="text"
									required name="mobno" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input
									type="password" required name="password" class="form-control">
							</div>

							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>

				</div>

			</div>


			<div class="col-md-8">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Doctor Details</p>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Full Name</th>
									<th scope="col">DOB</th>
									<th scope="col">Qualification</th>
									<th scope="col">Speicialist</th>
									<th scope="col">Email</th>
									<th scope="col">Mob no</th>
									<th scope="col">Action</th>
								</tr>
							</thead>

							<tbody>
							<%
							DoctorDao ddao = new DoctorDao(DBConnect.getConn());
							List<Doctor> list2 = ddao.getAllDoctor();
							for(Doctor d:list2)
							{%>
							<tr>
							     <td><%=d.getFullName() %></td>
							     <td><%=d.getDob() %></td>
							     <td><%=d.getQualification() %></td>
							     <td><%=d.getSpecialist() %></td>
							     <td><%=d.getEmail() %></td>
							     <td><%=d.getMobNo() %></td>
							     <td><a href="#" class="btn btn-sm btn-primary">Edit</a>
							     <a href="#" class="btn btn-sm btn-danger">Delete</a></td>
							</tr>
								
							<%}
							%>

							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div>


	
</body>
</html>