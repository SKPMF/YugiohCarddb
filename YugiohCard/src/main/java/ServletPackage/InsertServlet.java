package ServletPackage;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		
		//Establish connection with jdbc
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
				System.out.println("[Insert] Successfully connected to MYSQL!!!");

			//If no table exists, create one
			
			//String createtable = "CREATE TABLE IF NOT EXISTS 'card' ('id' INT, 'name' varchar(255), 'attack' INT, 'defense' INT, 'type' varchar(255))";
			String createtable = "CREATE TABLE IF NOT EXISTS cards (id INT, name varchar(255), attack INT, defense INT, cardtype varchar(255))";
			Statement stmt = conn.createStatement();

			stmt.executeUpdate(createtable);
			
			
			//Get form details
			int id = Integer.parseInt(request.getParameter("id"));
			int attack = Integer.parseInt(request.getParameter("attack"));
			int defense = Integer.parseInt(request.getParameter("defense"));
			String name = request.getParameter("name");
			String cardtype = request.getParameter("cardtype");

			//Check if id exists
			String checkid = "select * from cards where id=?";
			PreparedStatement ps = conn.prepareStatement(checkid);
			ps.setInt(1, id);
			ResultSet rs = null;
			rs = ps.executeQuery();

			String operation = "Insert";
			
			if(rs.next()==true)
			{
				//cannot insert as id exists, render failed.jsp
				System.out.println("[Insert] Failed as Id exists! Rendering \"failed.jsp\"");
				request.setAttribute("operation", operation);
				String reason = "id already exists!";
				request.setAttribute("reason", reason);
				RequestDispatcher dispatcher = request.getRequestDispatcher("failed.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				//Insert operation
				String insertquery = "insert into cards(id,name,attack,defense,cardtype) values(?,?,?,?,?)";
				PreparedStatement prepstmt = conn.prepareStatement(insertquery);
				prepstmt.setInt(1,id);
				prepstmt.setString(2,name);
				prepstmt.setInt(3, attack);
				prepstmt.setInt(4,defense);
				prepstmt.setString(5, cardtype);

				prepstmt.executeUpdate();

				//Data Structure to display
				List<CardStructure> cards = new ArrayList<CardStructure>();
				cards.add(new CardStructure(id,name,attack,defense,cardtype));
				
				request.setAttribute("cards", cards);
				request.setAttribute("operation", operation);

				System.out.println("[Insert] Successfully Inserted! Rendering \"done.jsp\"");

				RequestDispatcher dispatcher = request.getRequestDispatcher("done.jsp");
				dispatcher.forward(request,response);
			}

		}
		catch(Exception e){
			System.out.println("[Insert] [ERROR] : Not connected to MYSQL");
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
