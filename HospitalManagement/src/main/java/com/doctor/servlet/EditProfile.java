package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.DoctorDao;
import com.Db.DBConnect;
import com.entity.Doctor;

@WebServlet("/docEditProfile")
public class EditProfile extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullname = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String specialist = req.getParameter("specialist");
			String email = req.getParameter("email");
			String mobno = req.getParameter("mobno");
			
			
			int id = Integer.parseInt(req.getParameter("id"));
			
			Doctor d = new Doctor(id, fullname, dob, qualification, specialist, email, mobno, "");
			
			DoctorDao ddao = new DoctorDao(DBConnect.getConn());
			HttpSession session =req.getSession();
			
			if(ddao.editDoctorProfile(d)) {
				Doctor updateDoctor=ddao.getDoctorById(id);
				session.setAttribute("sucMsgd", "Doctor Profile Updated");
				session.setAttribute("docObj", updateDoctor);
				resp.sendRedirect("doctor/edit_profile.jsp");
			}else {
				session.setAttribute("errorMsgd", "Something wrong on the server");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
