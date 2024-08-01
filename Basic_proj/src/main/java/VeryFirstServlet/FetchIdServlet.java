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
@WebServlet("/idserve")
public class FetchIdServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sid = req.getParameter("fid");//"1"
		int id = Integer.parseInt(sid);
		
		PrintWriter out = resp.getWriter();
		 resp.setContentType("text/html");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String qry = "select * from btm.student where id = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=Sayan@1936");
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int ssid = rs.getInt(1);
				String sname = rs.getString(2);
				String sdept = rs.getString(3);
				int sperc = rs.getInt(4);
				out.println("<html><body>"+"<h1> Student id is" + 
				ssid +"</h1>"+"<h1>Student name is "
						+sname+"</h1>" +"<h1>He/she is from "
				+sdept+"</h1>" +"<h1>Total Obtained marks "
						+sperc+"</h1>"+ "</body></html>");
			}else {
				out.println("<html><body><h1> No data found </h1></body></html>");
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
			
			if(pstmt!=null) {
				try {
					pstmt.close();
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
		}
		
		
	}

}
