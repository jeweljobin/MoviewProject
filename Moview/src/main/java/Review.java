
public class Review{
protected String id;
protected String movie;
protected String rating;
protected String feedback;
/**
 * @param id
 * @param movie
 * @param rating
 * @param feedback
 */
public Review(String id, String movie, String rating, String feedback) {
	super();
	this.id = id;
	this.movie = movie;
	this.rating = rating;
	this.feedback = feedback;
}
/**
 * @return the id
 */
public String getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(String id) {
	this.id = id;
}
/**
 * @return the movie
 */
public String getMovie() {
	return movie;
}
/**
 * @param movie the movie to set
 */
public void setMovie(String movie) {
	this.movie = movie;
}
/**
 * @return the rating
 */
public String getRating() {
	return rating;
}
/**
 * @param rating the rating to set
 */
public void setRating(String rating) {
	this.rating = rating;
}
/**
 * @return the feedback
 */
public String getFeedback() {
	return feedback;
}
/**
 * @param feedback the feedback to set
 */
public void setFeedback(String feedback) {
	this.feedback = feedback;
}
}