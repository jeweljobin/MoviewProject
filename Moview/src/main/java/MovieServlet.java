
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
 * Servlet implementation class MovieServlet
 */
@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/moview";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/MovieServlet/delete":
				deleteMovie(request, response);
				break;
			case "/MovieServlet/edit":
				showEditForm(request, response);
				break;
			case "/MovieServlet/update":
				updateMovie(request, response);
				break;
			case "/MovieServlet/dashboard":
				listMovies(request, response);
				break;
//			default:
//				listMovies(request, response);
//				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_MOVIES_SQL = "INSERT INTO movie" + "(movieName,movieImage,genre,description,ageRating,trailer) values(?,?,?,?,?,?)";
	private static final String SELECT_MOVIE_BY_ID = "select * from movie where id =?";
	private static final String SELECT_ALL_MOVIES = "select * from movie";
	private static final String DELETE_MOVIES_SQL = "delete from movie where id = ?;";
	private static final String UPDATE_MOVIES_SQL = "update movie set movieName = ?,movieImage= ?, genre =?,description =?,ageRating =?,trailer =? where id = ?;";

	// Step 3: Implement the getConnection method which facilitates connection to
	// the database via JDBC
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
	private void listMovies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String usernameCall = (String)request.getAttribute("username");
		String passwordCall = (String)request.getAttribute("password");
		String usernameGetParam = request.getParameter("username");
		String passwordGetParam = request.getParameter("password");
		List<Movie> movies = new ArrayList<>();
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
			request.setAttribute("listMovies", movies);
			request.getRequestDispatcher("/movieManagement.jsp").forward(request, response);

		}
		else {
			request.setAttribute("username", usernameCall);
			request.setAttribute("password", passwordCall);
			request.setAttribute("listMovies", movies);
			request.getRequestDispatcher("/movieManagement.jsp").forward(request, response);
		}
	}

	// method to get parameter, query database for existing movie data and redirect
	// to movie edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String id = request.getParameter("id");
		Movie existingMovie = new Movie("", "", "", "", "", "", "");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID);) {
			preparedStatement.setString(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				id = rs.getString("id");
				String movieName = rs.getString("movieName");
				String movieImage = rs.getString("movieImage");
				String genre = rs.getString("genre");
				String description = rs.getString("description");
				String ageRating = rs.getString("ageRating");
				String trailer = rs.getString("trailer");
				existingMovie = new Movie(id, movieName, movieImage, genre, description, ageRating, trailer);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingMovie to request and serve up the movieEdit form
		request.setAttribute("movie", existingMovie);
		request.getRequestDispatcher("/movieEdit.jsp").forward(request, response);
	}

	// method to update the movie table base on the form data
	private void updateMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String oriId = request.getParameter("oriId");
		String id = request.getParameter("id");
		String movieName = request.getParameter("movieName");
		String movieImage = request.getParameter("movieImage");
		String genre = request.getParameter("genre");
		String description = request.getParameter("description");
		String ageRating = request.getParameter("ageRating");
		String trailer = request.getParameter("trailer");

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_MOVIES_SQL);) {
			//statement.setString(1, id);
			statement.setString(1, movieName);
			statement.setString(2, movieImage);
			statement.setString(3, genre);
			statement.setString(4, description);
			statement.setString(5, ageRating);
			statement.setString(6, trailer);
			statement.setString(7, oriId);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to MovieServlet (note: remember to change the url to
		// your project name)
		response.sendRedirect("http://localhost:8080/Moview/MovieServlet/dashboard");
	}

	// method to delete movie
	private void deleteMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String id = request.getParameter("id");
		// Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MOVIES_SQL);) {
			statement.setString(1, id);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserServlet dashboard (note: remember to change the
		// url to your project name)
		response.sendRedirect("http://localhost:8080/Moview/MovieServlet/dashboard");
	}

}
