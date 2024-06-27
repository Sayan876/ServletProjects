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
@WebServlet("/deleteDoctor")
public class DeleteDoctor extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		DoctorDao ddao = new DoctorDao(DBConnect.getConn());
		HttpSession session =req.getSession();
		
		if(ddao.deleteDoctor(id)) {
			session.setAttribute("sucMsg", "Doctor Deleted");
			resp.sendRedirect("admin/viewdoctor.jsp");
		}else {
			session.setAttribute("errorMsg", "Something wrong on the server");
			resp.sendRedirect("admin/viewdoctor.jsp");
		}
	}

}
