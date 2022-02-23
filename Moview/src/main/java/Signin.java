

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signin
 */
@WebServlet("/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		
		//Step 2: retrieve the four parameters from the request from the web form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String adminCode = request.getParameter("adminCode");
		ResultSet rs = null;
		
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection(
		 "jdbc:mysql://localhost:3306/moview", "root", "password");
		
		 //Step 4: implement the sql query using prepared statement (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
		 PreparedStatement ps = con.prepareStatement("select * from moview.account where username = ? AND password = ?");
		
		 //Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
		 ps.setString(1, username);
		 ps.setString(2, password);

		 rs = ps.executeQuery();
		 
		 if(rs.next()) {
			 
			 if (adminCode.equals("123")) {
				 request.setAttribute("username", username);
				 request.setAttribute("password", password);
				 RequestDispatcher rd = request.getRequestDispatcher("/MovieServlet/dashboard");
				 rd.forward(request,response);
			 }
			 else {
				 request.setAttribute("username", username);
				 request.setAttribute("password", password);
				 RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet");
				 rd.forward(request,response);
			 }

			 
		 }
		 else {
			 	//Step 7: check if the username and password is not in database had been successfully execute, return “Wrong user or password” via the response,   	       		
	   	       	PrintWriter writer = response.getWriter();  
	   	       	response.setContentType("text/html");  
	   	       	writer.println("<script type=\"text/javascript\">");  
	   	       	writer.println("alert('Wrong username or password, please try again');"); 
	   	       	writer.println("location.replace('http://localhost:8080/Moview/Signin.jsp')");
	   	       	writer.println("</script>");
	   	       	writer.close();
		 }
		 

		

		}
		//Step 8: catch and print out any exception
		catch (Exception exception) {
		 System.out.println(exception);
		 out.close();
		}
		
		doGet(request, response);
	}
}

