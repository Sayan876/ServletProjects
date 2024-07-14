package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.DoctorDao;
import com.Dao.UserDao;
import com.Db.DBConnect;

@WebServlet("/docPasswordChange")
public class DoctorPasswordChange extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int uid = Integer.parseInt(req.getParameter("uid"));
		String oldPassword = req.getParameter("oldpassword");
		String newpassword = req.getParameter("newpassword");
		
		DoctorDao dao = new DoctorDao(DBConnect.getConn());
		HttpSession session = req.getSession();
		
		if(dao.checkOldPassword(uid, oldPassword)) {
			
			if(dao.changePassword(uid, newpassword)) {
				session.setAttribute("sucMsg", "Password changed successfully");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}else {
				session.setAttribute("errorMsg", "Old password Incorrect");
				resp.sendRedirect("doctor/edit_profile.jsp");
				
			}
			
		}else {
			session.setAttribute("errorMsg", "Old password Incorrect");
			resp.sendRedirect("doctor/edit_profile.jsp");
		}
	}
}
