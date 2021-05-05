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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//Establish Connection with jdbc
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
				System.out.println("[Update] Successfully connected to MYSQL!!!");
			
			//initialise variables
			int id=-1,attack=-1,defense=-1;
			String name="",cardtype="";
			
			try {
				//Get parameters from updateprocess.jsp
				//id = Integer.parseInt(request.getSession().getAttribute("id").toString());	
				id = Integer.parseInt(request.getParameter("id"));
				attack = Integer.parseInt(request.getParameter("attack"));
				defense = Integer.parseInt(request.getParameter("defense"));
				name = request.getParameter("name");
				cardtype = request.getParameter("cardtype");
				
				System.out.println("[Update] Record to update : id = "+id + "attack = "+attack+" defense = "+defense+" name = "+name+" cardtype = "+cardtype);
			}
			catch(Exception f)
			{
				//error in params
				System.out.println("[Update] Error in params");
			}

			//Update operation
			String updatequery = "update cards set name='"+name+"', cardtype='"+cardtype+"',attack="+Integer.toString(attack)+", defense="+Integer.toString(defense)+" where id = "+Integer.toString(id);
			System.out.println("[Update] Update query = "+updatequery);
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(updatequery);

			//Data Structure to view updated record
			List<CardStructure> cards = new ArrayList<CardStructure>();
			cards.add(new CardStructure(id,name,attack,defense,cardtype));

			String operation = "Update";
			request.setAttribute("cards", cards);
			request.setAttribute("operation", operation);

			System.out.println("[Update] Successfully Updated! Rendering \"done.jsp\"");

			RequestDispatcher dispatcher = request.getRequestDispatcher("done.jsp");
			dispatcher.forward(request,response);


		}
		catch(Exception e){
			System.out.println("[Update] [ERROR] : Not connected to MYSQL");
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
