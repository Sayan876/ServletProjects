<%@page import="com.entity.Doctor"%>
<%@page import="com.Dao.DoctorDao"%>
<%@page import="com.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.Dao.AppointmentDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin| Patient</title>
<style>
.point-card {
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.3);
	
}
</style>
<%@include file="../component/allcss.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
  <div class="col-md-12 patient">
    <div class="card point-card">
       <div class="card-body">
           <p class="fs-3 text-center">Patient Details</p>
           
           <table class="table">
             <thead>
                 <tr>
                   <th scope="col">Full Name</th>
                   <th scope="col">Gender</th>
                   <th scope="col">Age</th>
                   <th scope="col">Appointment</th>
                   <th scope="col">Email</th>
                   <th scope="col">Mob No</th>
                   <th scope="col">Diseases</th>
                   <th scope="col">Doctor Name</th>
                   <th scope="col">Address</th>
                   <th scope="col">Status</th>
                 
                 </tr>
             </thead>
             
             <tbody>
             
               <%
                AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
                DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
                List<Appointment> list = dao.getAlllAppointment();
                for(Appointment ap:list)
                	
                {
                	Doctor d = dao2.getDoctorById(ap.getDoctorId());
                	%>
                	 <tr>
                     <th><%=ap.getFullName()%></th>
                     <td><%=ap.getGender()%></td>
                     <td><%=ap.getAge()%></td>
                     <td><%=ap.getAppoinDate()%></td>
                     <td><%=ap.getEmail()%></td>
                     <td><%=ap.getPhNo()%></td>
                     <td><%=ap.getDiseases()%></td>
                     <td><%=d.getFullName()%></td>
                     <td><%=ap.getAddress()%></td>
                     <td><%=ap.getStatus()%></td>
                 </tr>
                	<%
                }
               
               %>
                
             </tbody>
           </table>
       </div>
    </div>
  </div>
  
  <%@include file="../component/footer.jsp" %>
</body>
</html>