

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
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
		 listHomeMovies(request, response);
		 break;
		 }
		 } catch (SQLException ex) {
		 throw new ServletException(ex);
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//Step 1: Prepare list of variables used for database connections
	 private String jdbcURL = "jdbc:mysql://localhost:3306/moview";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "password";
	 
	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
		// database
		private static final String INSERT_MOVIES_SQL = "INSERT INTO movie" + "(movieName,movieImage,genre,description,ageRating,trailer) values(?,?,?,?,?,?)";
		private static final String SELECT_MOVIE_BY_ID = "select * from movie where id =?";
		private static final String SELECT_ALL_MOVIES = "select * from movie";
		private static final String DELETE_MOVIES_SQL = "delete from movie where id = ?;";
		private static final String UPDATE_MOVIES_SQL = "update movie set movieName = ?,movieImage= ?, genre =?,description =?,ageRating =?,trailer =? where id = ?;";
		
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
		 
		// Step 5: listUsers function to connect to the database and retrieve all users
			// records
			private void listHomeMovies(HttpServletRequest request, HttpServletResponse response)
					throws SQLException, IOException, ServletException {
				List<Movie> movies = new ArrayList<>();
				String usernameCall = (String)request.getAttribute("username");
				String passwordCall = (String)request.getAttribute("password");
				String usernameGetParam = request.getParameter("username");
				String passwordGetParam = request.getParameter("password");
				try (Connection connection = getConnection();

						// Step 5.1: Create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES);) {
					// Step 5.2: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();
					// Step 5.3: Process the ResultSet object.
					while (rs.next()) {
						String id = rs.getString("id");
						String movieName = rs.getString("movieName");
						String movieImage = rs.getString("movieImage");
						String genre = rs.getString("genre");
						String description = rs.getString("description");
						String ageRating = rs.getString("ageRating");
						String trailer = rs.getString("trailer");
						movies.add(new Movie(id, movieName, movieImage, genre, description, ageRating, trailer));
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				// Step 5.4: Set the users list into the listUsers attribute to be pass to the
				// movieManagement.jsp
				if( usernameGetParam != null && passwordGetParam != null) {
					request.setAttribute("username", usernameGetParam);
					request.setAttribute("password", passwordGetParam);
					request.setAttribute("listHomeMovies", movies);
					request.getRequestDispatcher("/Home.jsp").forward(request, response);

				}
				else {
					request.setAttribute("username", usernameCall);
					request.setAttribute("password", passwordCall);
					request.setAttribute("listHomeMovies", movies);
					request.getRequestDispatcher("/Home.jsp").forward(request, response);
				}
			}
}

