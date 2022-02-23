import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ReviewMovies
 */
@WebServlet("/ReviewMovies")
public class ReviewMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Step 1: Prepare list of variables used for database connections
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/moview";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	//Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	
		private static final String INSERT_REVIEW_SQL = "INSERT INTO review" + " (movie, rating, feedback) VALUES " +
		" (?, ?, ?);";
		private static final String SELECT_REVIEW_BY_ID = "select movie, rating,feedback from review where id =?";
		private static final String SELECT_ALL_REVIEW = "select * from review ";
		private static final String DELETE_REVIEW_SQL = "delete from review where id = ?;";
		private static final String UPDATE_REVIEW_SQL = "update review set movie = ?, rating = ?,feedback= ? where id = ?;";
	
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
    public ReviewMovies() {
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
		case "/insert":
		break;
		case "/delete":
		break;
		case "/edit":
		break;
		case "/update":
		break;
		default:
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
	request.getRequestDispatcher("/review.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doGet(request, response);
	}

}

