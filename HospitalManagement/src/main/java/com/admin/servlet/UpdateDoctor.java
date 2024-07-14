package com.admin.servlet;

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

@WebServlet("/updateDoctor")
public class UpdateDoctor extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullname = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String specialist = req.getParameter("specialist");
			String email = req.getParameter("email");
			String mobno = req.getParameter("mobno");
			String password = req.getParameter("password");
			
			int id = Integer.parseInt(req.getParameter("id"));
			
			Doctor d = new Doctor(id, fullname, dob, qualification, specialist, email, mobno, password);
			
			DoctorDao ddao = new DoctorDao(DBConnect.getConn());
			HttpSession session =req.getSession();
			
			if(ddao.updateDoctor(d)) {
				session.setAttribute("sucMsg", "Doctor Updated");
				resp.sendRedirect("admin/viewdoctor.jsp");
			}else {
				session.setAttribute("errorMsg", "Something wrong on the server");
				resp.sendRedirect("admin/viewdoctor.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
