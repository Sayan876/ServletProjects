package VeryFirstServlet;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/fss")
public class FirstHttpServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Fetch UI/From data
		String sid = req.getParameter("i");//"1"
		int id = Integer.parseInt(sid);
		String sname = req.getParameter("nm");//Drake
		String sdept = req.getParameter("dp");//Surge
		String perc = req.getParameter("pr");//29
		
		int sperc = Integer.parseInt(perc);
		
		//Presentation logic //Servlet- technology
		
		PrintWriter out = resp.getWriter();
		out.println("<html><body bgcolor = 'red'>"
				+ "<h1>Student name is " + sname + " from " + sdept +"</h1>"+"</body></html>");
		out.close();
		
		//Persistence Logic//JDBC Technology
		Connection con = null;
		PreparedStatement pstmt = null;
		String qry = "insert into btm.student values(?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=Sayan@1936");
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, id);
			pstmt.setString(2, sname);
			pstmt.setString(3, sdept);
			pstmt.setInt(4, sperc);
			//Execute the SQL Query
			pstmt.executeUpdate();
			System.out.println("Record has been inserted Succesfully!");
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
