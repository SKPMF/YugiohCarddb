package ServletPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClassesPackage.CardStructure;

/**
 * Servlet implementation class VerifyidServlet
 */
@WebServlet("/VerifyidServlet")
public class VerifyidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyidServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		//Establish jdbc connection
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String db = "yugioh";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "root";
		String password = "password";
		//String f1,f2;

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+db,username,password);
			if (!conn.isClosed())
				System.out.println("[Verifyid] : Successfully connected to MYSQL!!!");

			//Get id
			int id = Integer.parseInt(request.getParameter("id"));
		
			//check if id exists
			String checkid = "select * from cards where id=?";
			PreparedStatement ps = conn.prepareStatement(checkid);
			ps.setInt(1, id);
			ResultSet rs = null;
			rs = ps.executeQuery();

			String operation = "Verifyid";
			
			if(rs.next()==false)
			{
				//No id exists
				System.out.println("[Verifyid] Failed as no such id exists! Rendering \"failed.jsp\"");
				request.setAttribute("operation", operation);
				String reason = "no such id exists!";
				request.setAttribute("reason", reason);
				RequestDispatcher dispatcher = request.getRequestDispatcher("failed.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				//id exists
				System.out.println("[Verifyid] id found! -  Rendering \"updateprocess.jsp\"");
				request.setAttribute("id", id);
				request.setAttribute("operation", operation);

				RequestDispatcher dispatcher = request.getRequestDispatcher("updateprocess.jsp");
				dispatcher.forward(request,response);
			}

		}
		catch(Exception e){
			System.out.println("[Verifyid] [ERROR] : Not connected to MYSQL");
		}
		finally {
			try {
				if (conn!=null)
				{
					conn.close();
				}
			}
			catch(SQLException e) {}
		}

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
