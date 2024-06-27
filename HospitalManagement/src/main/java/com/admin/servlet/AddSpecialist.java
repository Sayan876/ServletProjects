package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.SpecialistDao;
import com.Db.DBConnect;
import com.entity.User;


@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String specName = req.getParameter("specName");
		SpecialistDao sdao = new SpecialistDao(DBConnect.getConn());
		boolean f = sdao.addSpecialist(specName);
		
		HttpSession session = req.getSession();
		
		if(f) {
			session.setAttribute("sucMsg", "Speclialist Added");
			resp.sendRedirect("admin/index.jsp");
		}else {
			session.setAttribute("errorMsg", "Invalid email & password");
			resp.sendRedirect("admin_login.jsp");
		}
		
	}

}
