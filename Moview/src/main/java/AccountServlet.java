

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Step 1: Prepare list of variables used for database connections
	 private String jdbcURL = "jdbc:mysql://localhost:3306/moview";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "password";
	 
	 
	 //Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	 private static final String INSERT_USERS_SQL = "INSERT INTO moview.account" + " (username, password, email, contact, address) VALUES " + " (?, ?, ?, ?, ?);";
	 private static final String SELECT_USER_BY_ID = "select * from moview.account where username = ? AND password = ?";
	 private static final String SELECT_ALL_USERS = "select * from moview.account";
	 private static final String DELETE_USERS_SQL = "delete from moview.account where username = ?;";
	 private static final String UPDATE_USERS_SQL = "update moview.account set username = ?,password= ?, email =?,contact =?, address = ? where username = ?;";
	 
	 
	 //Step 3: Implement the getConnection method which facilitates connection to the database via JDBC
	 protected Connection getConnection() {
	 Connection connection = null;
	 try {
	 Class.forName("com.mysql.jdbc.Driver");
	 connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } catch (ClassNotFoundException e) {
	 e.printStackTrace();
	 }
	 return connection;
	 }
	 
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step 4: Depending on the request servlet path, determine the function to invoke using the follow switch statement.
		String action = request.getServletPath();
		 try {
		 switch (action) {
		 case "/AccountServlet/delete":
		 deleteUser(request, response);
		 break;
		 case "/AccountServlet/edit":
		 showEditForm(request, response);
		 break;
		 case "/AccountServlet/update":
		 updateUser(request, response);
		 break;
		 case "/AccountServlet/dashboard":
		 listUsers(request, response);
		 break;
		 }
		 } catch (SQLException ex) {
		 throw new ServletException(ex);
		 } 
		 
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
			List <Account> accounts = new ArrayList <>();
			String usernameCall = (String)request.getAttribute("username");
			String passwordCall = (String)request.getAttribute("password");
			String usernameGetParam = request.getParameter("username");
			String passwordGetParam = request.getParameter("password");
			 try (
			Connection connection = getConnection();
			 // Step 5.1: Create a statement using connection object
			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
					 ){
			if(usernameGetParam != null && passwordGetParam != null){
				preparedStatement.setString(1, usernameGetParam);
				preparedStatement.setString(2, passwordGetParam);
			}
			else {
				preparedStatement.setString(1, usernameCall);
				preparedStatement.setString(2, passwordCall);
			}
			
			 // Step 5.2: Execute the query or update query
			 ResultSet rs = preparedStatement.executeQuery();
			 // Step 5.3: Process the ResultSet object.
			 while (rs.next()) {
			 String username = rs.getString("username");
			 String password = rs.getString("password");
			 String email = rs.getString("email");
			 String contact = rs.getString("contact");
			 String address = rs.getString("address");
			 accounts.add(new Account(username, password, email, contact, address));
			 }
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }
			// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
			request.setAttribute("listUsers", accounts);
			request.getRequestDispatcher("/accountManagement.jsp").forward(request, response);
			}
	
	//method to get parameter, query database for existing user data and redirect to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, ServletException, IOException {
	//get parameter passed in the URL
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	Account accounts = new Account("", "", "", "","");
	
	// Step 1: Establishing a Connection
	try (Connection connection = getConnection();
			
	// Step 2:Create a statement using connection object
	PreparedStatement preparedStatement =
	connection.prepareStatement(SELECT_USER_BY_ID);) {
	preparedStatement.setString(1, username);
	preparedStatement.setString(2, password);
	// Step 3: Execute the query or update query
	ResultSet rs = preparedStatement.executeQuery();
	
	// Step 4: Process the ResultSet object
	while (rs.next()) {
	username = rs.getString("username");
	password = rs.getString("password");
	String email = rs.getString("email");
	String contact = rs.getString("contact");
	String address = rs.getString("address");
	accounts = new Account(username, password, email, contact, address);
	}
	} catch (SQLException e) {
	System.out.println(e.getMessage());
	}
	
		//Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("accounts", accounts);
		request.getRequestDispatcher("/accountEdit.jsp").forward(request, response);
	}
	
	//method to update the user table base on the form data
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	String oriName = request.getParameter("oriName");
	 String username = request.getParameter("username");
	 String password = request.getParameter("password");
	 String email = request.getParameter("email");
	 String contact = request.getParameter("contact");
	 String address = request.getParameter("address");

	 //Step 2: Attempt connection with database and execute update user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(UPDATE_USERS_SQL);) {
	 statement.setString(1, username);
	 statement.setString(2, password);
	 statement.setString(3, email);
	 statement.setString(4, contact);
	 statement.setString(5, address);
	 statement.setString(6, oriName);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8080/Moview/AccountServlet/dashboard?username="+username+"&password="+password+"");

	}
	
	
	//method to delete user
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	 String username = request.getParameter("username");
	 //Step 2: Attempt connection with database and execute delete user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(DELETE_USERS_SQL);) {
	 statement.setString(1, username);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8080/Moview/Signin.jsp");
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
