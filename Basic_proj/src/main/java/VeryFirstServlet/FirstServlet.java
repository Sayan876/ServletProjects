package VeryFirstServlet;


 
import java.io.*;
import javax.servlet.annotation.*;

import javax.servlet.*;
@WebServlet("/fs")
public class FirstServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Getting the data from UI/
		String name = req.getParameter("nm"); //name 
		String place = req.getParameter("pl"); //place
		
		//Presentation// 
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body bgcolor = 'blue'>" + 
		            "<h1 style='text-align:center;'>Student name is " 
				      + name + " from " + place 
				      +"</h1>"+
				      "</body></html>");
		
		
		out.close();
	}

}

