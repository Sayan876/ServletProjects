package VeryFirstServlet;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sst")

public class FetchData extends HttpServlet {
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = resp.getWriter();
		 //resp.setContentType("text/html");
	
       ResultSet rs = null;
       Connection con = null;
       PreparedStatement pstmt = null;
       String qryf = "Select * from btm.student";
       try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=Sayan@1936");
		pstmt = con.prepareStatement(qryf);
		rs = pstmt.executeQuery();
		out.println("<html><body><h1>Student database</h1></body></html>");
		out.println("<html>"
				+ "<head>"
				+ "<style>"
				+ "table, th, td {"
				+ "  border: 1px solid;"
				+ "}"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+ "<table>"
				+ "  <tr>"
				+ "    <th style='width:200px;'>Id</th>"
				+ "    <th style='width:200px;'>Name</th>"
				+ "    <th style='width:200px;'>Department</th>"
				+ "    <th style='width:200px;'>Percentage</th>"
				+ "  </tr>"
				
				+ "</table>"
				
				+ "</body>"
				+ "</html>");;
		while(rs.next()) {
			int sid = rs.getInt(1);
			String sname = rs.getString(2);
			String Sdept = rs.getString(3);
			int sperc = rs.getInt(4);
			
			//out.println("<html><body>" + "<h1 style:'text-align:center;'>" +sid + " "+ sname+ " "+ Sdept + " " + sperc + "</h1>" + "</body></html>");
			out.println("<html>"
					+ "<head>"
					+ "<style>"
					+ "table, th, td {"
					+ "  border: 1px solid;"
					+ "}"
					+"table{"
					+ "width:100%"
					+ "}"
					+ "</style>"
					+ "</head>"
					+ "<body>"
					
					
					
					+ "<table>"
				
					+ "  <tr>"
					+ "    <td style='width:200px;'>"+sid +"</td>"
					+ "    <td style='width:200px;'>"+sname+"</td>"
					+ "    <td style='width:200px;'>"+Sdept+"</td>"
					+ "    <td style='width:200px;'>"+sperc+"</td>"
					+ "  </tr>"
					
					+ "</table>"
					
					+ "</body>"
					+ "</html>");
		}
		out.close();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       finally {
    	   if(con!=null) {
    		   try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
    	   
    	   if(rs!=null) {
    		   try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
    	   
    	   if(pstmt!=null) {
    		   try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
       }
	}
}
