<%@page import="com.entity.User"%>
<%@page import="com.entity.Doctor"%>
<%@page import="java.util.List"%>
<%@page import="com.Dao.DoctorDao"%>
<%@page import="com.Db.DBConnect"%>
<%@page import="com.Dao.AppointmentDao"%>
<%@page import="com.entity.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Appointment</title>
<style>
  .backimg {
	background: url("img/image4.jpg");
	height: 20vh;
	width: 100%;
	background-size: cover;
	background-repeat: no-repeat;
}
</style>
<%@include file="component/allcss.jsp"%>
</head>
<body>
<%@include file="component/navbar.jsp"%>
  
  <div class="container-fulid backimg p-5">
     <p class="text-center fs-2 text-white" ></p>
  </div>
  
  <div class="container p-3">
    <div class="row">
       <div class="col-md-9">
         <div class="card paint-card">
           <div class="card-body">
            <p class="fs-4 fw-bold text-center text-success">Appointment List</p>
            
            <table class="table">
              <thead>
               <tr>
                 <th scope="col">Full Name</th>
                 <th scope="col">Gender</th>
                 <th scope="col">Age</th>
                 <th scope="col">Appoint Date</th>
                 <th scope="col">Diseases</th>
                 <th scope="col">Doctor Name</th>
                 <th scope="col">Status</th>
               </tr>
              </thead>
              
              <tbody>
              
              <%
               User user = (User) session.getAttribute("userObj");
               AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
               DoctorDao dao2 = new DoctorDao(DBConnect.getConn());
               List<Appointment> list = dao.getAllAppointment(user.getId());
               for(Appointment ap:list){
            	   Doctor d = dao2.getDoctorById(ap.getDoctorId());
            	   %>
            	   <tr>
                   <th><%=ap.getFullName() %></th>
                   <td><%=ap.getGender() %></td>
                   <td><%=ap.getAge() %></td>
                   <td><%=ap.getAppoinDate() %></td>
                   <td><%=ap.getDiseases() %></td>
                   <td><%=d.getFullName() %></td>
                   <td>
                    <%
                      if("Pending".equals(ap.getStatus())){
                    	  %>
                    	  <a href="#" class="btn btn-sm btn-warning">Pending</a>
                      <%}else{
                    	  %>
                    	  <%=ap.getStatus() %>
                    	  <% 
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
       
       <div class="col-md-3 p-3">
           <img alt="" class="frth" src="img/image5.jpg">
       </div>
    </div>
  </div>
  <%@include file="component/footer.jsp" %>
</body>
</html>