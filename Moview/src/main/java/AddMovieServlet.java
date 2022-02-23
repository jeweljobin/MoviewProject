
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddMovieServlet
 */
@WebServlet("/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMovieServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		response.setContentType("text/html");
		// Step 1: Initialize a PrintWriter object to return the html values via the
		// response
		PrintWriter out = response.getWriter();

		// Step 2: retrieve the six parameters from the request from the web form
		String movieName = request.getParameter("movieName");
		String movieImage = request.getParameter("movieImage");
		String trailer = request.getParameter("trailer");
		String description = request.getParameter("description");
		String genre = request.getParameter("genre");
		String ageRating = request.getParameter("ageRating");

		// Step 3: attempt connection to database using JDBC, you can change the
		// username and password accordingly using the phpMyAdmin > User Account
		// dashboard
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moview", "root", "password");

			// Step 4: implement the sql query using prepared statement
			PreparedStatement ps = con.prepareStatement("insert into MOVIE(movieName,movieImage,genre,description,ageRating,trailer) values(?,?,?,?,?,?)");

			// Step 5: parse in the data retrieved from the web form request into the
			// prepared statement accordingly
			ps.setString(1, movieName);
			ps.setString(2, movieImage);
			ps.setString(3, genre);
			ps.setString(4, description);
			ps.setString(5, ageRating);
			ps.setString(6, trailer);
			
			
			// Step 6: perform the query on the database using the prepared statement
			int i = ps.executeUpdate();

			// Step 7: check if the query had been successfully execute, return “You are
			// successfully registered” via the response,
			if (i > 0) {
				response.sendRedirect("http://localhost:8080/Moview/MovieServlet/dashboard");
			}
		}

		// Step 8: catch and print out any exception
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
		doGet(request, response);

	}

}
