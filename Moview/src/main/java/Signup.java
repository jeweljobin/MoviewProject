

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		ResultSet rs = null;
		
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection(
		 "jdbc:mysql://localhost:3306/moview", "root", "password");
		
		 //Step 4: implement the sql query using prepared statement (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
		 PreparedStatement ps = con.prepareStatement("insert into moview.account values(?,?,?,?,?)");
		
		 //Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
		 ps.setString(1, username);
		 ps.setString(2, password);
		 ps.setString(3, email);
		 ps.setString(4, contact);
		 ps.setString(5, address);
		
		 
		 PreparedStatement check = con.prepareStatement("select * from moview.account where username = ? OR email = ?");
		 check.setString(1, username);
		 check.setString(2, email);
		 rs = check.executeQuery();
		 
		 if(rs.next()) {
			 PrintWriter writer = response.getWriter();
 			writer.println("<h1>" + "There is an existing username or email in the database" + "</h1>");
 			writer.close();
		 }
		 else {

				// Step 6: perform the query on the database using the prepared statement
   	         int i = ps.executeUpdate();
   	       		 
   			 //Step 7: check if the query had been successfully execute, return “You are successfully registered” via the response,
   	       		if (i > 0){
   		   	       	PrintWriter writer = response.getWriter();  
   		   	       	response.setContentType("text/html");  
   		   	       	writer.println("<script type=\"text/javascript\">");  
   		   	       	writer.println("alert('You have successfully registered an account!');"); 
   		   	       	writer.println("location.replace('http://localhost:8080/Moview/Signin.jsp')");
   		   	       	writer.println("</script>");
   		   	       	writer.close();
   	       		}
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
