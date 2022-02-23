

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
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Step 1: Prepare list of variables used for database connections
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/moview";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	
	//Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	
	private static final String INSERT_REVIEW_SQL = "INSERT INTO Review" + " (movie, rating, feedback) VALUES " +
	" (?, ?, ?);";
	private static final String SELECT_REVIEW_BY_ID = "select * from Review where id =?";
	private static final String SELECT_ALL_REVIEW = "select * from Review ";
	private static final String DELETE_REVIEW_SQL = "delete from Review where id = ?;";
	private static final String UPDATE_REVIEW_SQL = "update Review set movie = ?, rating = ?,feedback= ? where id = ?;";
	
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
    public ReviewServlet() {
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
		case "/ReviewServlet/delete":
			deleteReview(request, response);
			break;
			case "/ReviewServlet/edit":
			showEditForm(request, response);
			break;
			case "/ReviewServlet/update":
			updateReview(request, response);
			break;
			case "/ReviewServlet/dashboard":
			listReview(request, response);
			break;
		}
		} catch (SQLException ex) {
		throw new ServletException(ex);
		}
	}
	
	//Step 5: listUsers function to connect to the database and retrieve all users records
	
	private void listReview(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException
	{
	List <Review> review = new ArrayList <>();
	try (Connection connection = getConnection();
			
	// Step 5.1: Create a statement using connection object
			
	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REVIEW);) {
		
	// Step 5.2: Execute the query or update query
		
	ResultSet rs = preparedStatement.executeQuery();
	
	// Step 5.3: Process the ResultSet object.
	
	while (rs.next()) {
	String id = rs.getString("id");
	String movie = rs.getString("movie");	
	String rating = rs.getString("rating");
	String feedback = rs.getString("feedback");
	review.add(new Review(id, movie, rating, feedback));
	}
	} catch (SQLException e) {
	System.out.println(e.getMessage());
	}
	
	// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
	
	request.setAttribute("listReview", review);
	request.getRequestDispatcher("/reviewManagement.jsp").forward(request, response);
	}

	//method to get parameter, query database for existing user data and redirect to user edit page
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String id = request.getParameter("id");
		Review existingReview = new Review("", "", "", "");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEW_BY_ID);) {
			preparedStatement.setString(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				id = rs.getString("id");
				String movie = rs.getString("movie");
				String rating = rs.getString("rating");
				String feedback = rs.getString("feedback");
				
				existingReview = new Review(id, movie, rating, feedback);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingMovie to request and serve up the movieEdit form
		request.setAttribute("review", existingReview);
		request.getRequestDispatcher("/reviewEdit.jsp").forward(request, response);
	}
	
	//method to update the user table base on the form data
	
	private void updateReview(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
		
	//Step 1: Retrieve value from the request
	String oriId = request.getParameter("oriId");
	String id = request.getParameter("id");
	String movie = request.getParameter("movie");
	String rating = request.getParameter("rating");
	String feedback = request.getParameter("feedback");

	
	//Step 2: Attempt connection with database and execute update user SQL query
	
	try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW_SQL);) {
	statement.setString(1, movie);
	statement.setString(2, rating);
	statement.setString(3, feedback);
	statement.setString(4, oriId);
	int i = statement.executeUpdate();
	}
	
	//Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
	
	response.sendRedirect("http://localhost:8080/Moview/ReviewServlet/dashboard");
	}
	
	//method to delete user
	
	private void deleteReview(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
		
	//Step 1: Retrieve value from the request
		
	String id = request.getParameter("id");
	
	//Step 2: Attempt connection with database and execute delete user SQL query
	
	try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW_SQL);) {
	statement.setString(1, id);
	int i = statement.executeUpdate();
	}
	
	//Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
	
	response.sendRedirect("http://localhost:8080/Moview/ReviewServlet/dashboard");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
