
public class Movie {
	
	protected String id;

	protected String movieName;

	protected String movieImage;
	
	protected String genre; 

	protected String description;

	protected String ageRating;

	protected String trailer;
	
	public Movie (String id, String movieName, String movieImage, String genre, String description, String ageRating, String trailer) {
		super(); 
		this.id = id;
		this.movieName = movieName;
		this.movieImage = movieImage;
		this.genre = genre;
		this.description = description;
		this.ageRating = ageRating;
		this.trailer = trailer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieImage() {
		return movieImage;
	}

	public void setMovieImage(String movieImage) {
		this.movieImage = movieImage;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(String ageRating) {
		this.ageRating = ageRating;
	}

	

}
