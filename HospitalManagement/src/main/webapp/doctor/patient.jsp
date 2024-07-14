<%@page import="com.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.Dao.AppointmentDao"%>
<%@page import="com.entity.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Page</title>
<style>
.paint-card {
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3);
	margin-top: 10px;
}
</style>
<%@include file="../component/allcss.jsp"%>
</head>
<body>
	<c:if test="${empty docObj}">
		<c:redirect url="../doctor_login.jsp"></c:redirect>
	</c:if>
	<%@include file="navbar.jsp"%>

	<div class="container p3">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Patient Detials</p>
						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-3">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>

						<c:if test="${not empty sucMsg}">
							<p class="text-center text-success fs-5">${sucMsg}</p>
							<c:remove var="sucMsg" scope="session" />
						</c:if>

						<table class="table">
							<thead>
								<tr>
									<th scope="col">Full Name</th>
									<th scope="col">Gender</th>
									<th scope="col">Age</th>
									<th scope="col">Appointment Date</th>
									<th scope="col">Email</th>
									<th scope="col">Mob NO</th>
									<th scope="col">Diseases</th>
									<th scope="col">Status</th>
									<th scope="col">Action</th>
								</tr>
							</thead>

							<tbody>

								<%
								Doctor d = (Doctor) session.getAttribute("docObj");
								AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
								List<Appointment> list = dao.getAllAppointmentByDoctorLogin(d.getId());
								for (Appointment ap : list) 
								{
								%>
								<tr>
									<th><%=ap.getFullName() %></th>
									<td><%=ap.getGender() %></td>
									<td><%=ap.getAge() %></td>
									<td><%=ap.getAppoinDate() %></td>
									<td><%=ap.getEmail() %></td>
									<td><%=ap.getPhNo() %></td>
									<td><%=ap.getDiseases()%></td>
									<td><%=ap.getStatus() %></td>
									<td>
										<%
									if("Pending".equals(ap.getStatus())){
										%> <a href="comment.jsp?id=<%=ap.getId() %>"
										class="btn btn-primary btn-sm"><i
											class="fa-regular fa-comment"> </i> Comment</a> <%
										
									}else{
										%> <a href="#" class="btn btn-success btn-sm bg-danger"><i
											class="fa-regular fa-comment"> </i> Comment</a> <% 
									}
									%>




									</td>
								</tr>
								<%
								}
								
							%>

							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
   <%@include file="footer.jsp" %>
</body>
</html>