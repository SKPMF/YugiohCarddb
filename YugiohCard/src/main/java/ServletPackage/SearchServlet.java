package ServletPackage;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClassesPackage.CardStructure;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
				System.out.println("[Search] Successfully connected to MYSQL!!!");

			//String createtable = "CREATE TABLE IF NOT EXISTS 'card' ('id' INT, 'name' varchar(255), 'attack' INT, 'defense' INT, 'type' varchar(255))";

			//Get request parameters category and value
			String category = request.getParameter("category");
			String value = request.getParameter("value");

			System.out.println("[Search] category : "+category);
			System.out.println("[Search] value : "+value);

			String query="";
			int valueint=-1;
			
			//if else condition to update query based on if value is int or not 
			if (category == "attack" || category == "defense" || category=="id")
			{
				valueint = Integer.parseInt(value);
				query = "select * from cards where "+category+" = "+value;
			}
			else
			{
				query = "select * from cards where "+category+" = '"+value+"'";
			}
			System.out.println("[Search] search query : "+query);
			
			//Execute search
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			String operation = "Search";
			
			//Data Structure to hold search items
			List<CardStructure> cards = new ArrayList<CardStructure>();
			
			//variable to check if records exist
			int recordcount = 0;

			while(rs.next())
			{
				System.out.println("[Search] Record found : ("+rs.getInt("id")+", "+rs.getString("name")+", "+rs.getInt("attack")+", "+rs.getInt("defense")+", "+rs.getString("cardtype")+")");
				cards.add(new CardStructure(rs.getInt("id"),rs.getString("name"),rs.getInt("attack"),rs.getInt("defense"),rs.getString("cardtype")));
				recordcount++;
			}

			if (recordcount==0)
			{
				//No records found
				System.out.println("[Search] No records found! Rendering \"failed.jsp\"");
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

				System.out.println("[Search] Search Operation Done! Rendering \"done.jsp\"");

				RequestDispatcher dispatcher = request.getRequestDispatcher("done.jsp");
				dispatcher.forward(request,response);
			}

		}
		catch(Exception e){
			System.out.println("[Search] [ERROR] : Not connected to MYSQL");
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
