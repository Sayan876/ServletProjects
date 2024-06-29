package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.UserDao;
import com.Db.DBConnect;
import com.entity.User;


@WebServlet("/user_register")
public class UserRegister extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			User u = new User(fullname,email,password);
			
			UserDao udao = new UserDao(DBConnect.getConn());
			
			HttpSession session = req.getSession();
			boolean f=udao.userRegister(u);
			if(f) {
				session.setAttribute("sucMsg", "Registration Successful");
				resp.sendRedirect("signup.jsp");
				System.out.println("Data inserted");
			}else {
				session.setAttribute("errorMsg","Failed to Register");
				resp.sendRedirect("signup.jsp");
				System.err.println("Cannot perform the task");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
