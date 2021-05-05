package ServletPackage;

import javax.sql.*;

import ClassesPackage.CardStructure;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 * Servlet implementation class ListAllServlet
 */
@WebServlet("/ListAllServlet")
public class ListAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAllServlet() {
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
				System.out.println("[ListAll] Successfully connected to MYSQL!!!");

			//Select all query
			String query="select * from cards";
			System.out.println("[ListAll] search query : "+query);

			//Execute list all query
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			String operation = "ListAll";

			//Data Structure to hold search items
			List<CardStructure> cards = new ArrayList<CardStructure>();

			//variable to check if records exist
			int recordcount = 0;

			while(rs.next())
			{
				System.out.println("[ListAll] Record found : ("+rs.getInt("id")+", "+rs.getString("name")+", "+rs.getInt("attack")+", "+rs.getInt("defense")+", "+rs.getString("cardtype")+")");
				cards.add(new CardStructure(rs.getInt("id"),rs.getString("name"),rs.getInt("attack"),rs.getInt("defense"),rs.getString("cardtype")));
				recordcount++;
			}

			if (recordcount==0)
			{
				//No records found
				System.out.println("[ListAll] No records found! Rendering \"failed.jsp\"");
				request.setAttribute("operation", operation);
				String reason = "No records found!";
				request.setAttribute("reason", reason);
				RequestDispatcher dispatcher = request.getRequestDispatcher("failed.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				//Atleast 1 record found
				request.setAttribute("cards", cards);
				request.setAttribute("operation", operation);

				System.out.println("[ListAll] ListAll Operation Done! Rendering \"done.jsp\"");

				RequestDispatcher dispatcher = request.getRequestDispatcher("done.jsp");
				dispatcher.forward(request,response);
			}

		}
		catch(Exception e){
			System.out.println("[ListAll] [ERROR] : Not connected to MYSQL");
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
