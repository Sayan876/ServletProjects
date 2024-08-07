<%@page import="com.entity.Doctor"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.Dao.DoctorDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Dashboard</title>
<style>
.point-card {
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3);
}
</style>
<%@include file="../component/allcss.jsp"%>
</head>
<body>
	<c:if test="${empty docObj}">
		<c:redirect url="../doctor_login.jsp"></c:redirect>
	</c:if>
	<%
	    Doctor d = (Doctor) session.getAttribute("docObj");
		DoctorDao dao = new DoctorDao(DBConnect.getConn()); 
		
		%>
	<%@include file="navbar.jsp"%>
	<p class="text-center fs-3">Doctor Dashboard</p>
	    
	
	<div class="container p-5">
		<div class="row">
		
			<div class="col-md-4 offset-md-2">
				<div class="card point-card">
					<div class="card-body text-center text-primary">
						<i class="fas fa-user-md fa-3x"></i>
						<p class="fs-4 text-center">
							Doctor<br><%=dao.countDoctor() %>
						</p>
					</div>
				</div>
			</div>


			<div class="col-md-4">
				<div class="card point-card">
					<div class="card-body text-center text-primary">
						<i class="fas fa-user-check fa-3x"></i>
						<p class="fs-4 text-center">
							Total Appointment<br><%=dao.countAppointmentByDoctorId(d.getId()) %>
						</p>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<%@include file="footer.jsp" %>
</body>
</html>