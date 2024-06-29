package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.AppointmentDao;
import com.Db.DBConnect;
import com.entity.Appointment;

@WebServlet("/addAppointment")
public class AppointmentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int userid = Integer.parseInt(req.getParameter("userid"));
			String fullname = req.getParameter("fullname");
			String gender = req.getParameter("gender");
			String age = req.getParameter("age");
			String appoint_date= req.getParameter("appoint_date");
			String email = req.getParameter("email");
			String phno = req.getParameter("phone");
			String diseases = req.getParameter("diseases");
			int doctor_id = Integer.parseInt(req.getParameter("doct"));
			String address = req.getParameter("address");
			
			Appointment app = new Appointment(userid,fullname,gender,age,appoint_date,email,phno,diseases,doctor_id,address,"Pending");
			
			AppointmentDao adao = new AppointmentDao(DBConnect.getConn());
			HttpSession session = req.getSession();
			
			if(adao.addAppointment(app)) {
				session.setAttribute("sucMsg", "Appointment Successfully");
				resp.sendRedirect("user_appointment.jsp");
			}
			else
			{
				session.setAttribute("errorMsg", "Appointment failed");
				resp.sendRedirect("user_appointment.jsp");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	

}
