package no.itminds.movielibrary.model;

import java.sql.Date;
import java.util.List;
import java.util.OptionalDouble;

public class Movie {

	private Long id;
	private String title;
	private String year;

	private List<Genre> genres;

	private List<Rating> ratings;
	
	private String contentRating;
	private String duration;

	private Date releaseDate;

	private double averageRating;
	private String orginalTitle;
	private String storyLine;

	private List<Actor> actors;
	private String imdbRating;
	private String posterUrl;
	private String plot;

	private Date created;

	private List<Comment> comments;

	private Rating userRating;

	public Movie() {
		averageRating = 0;
	}

	public Movie(Long id) {
		this.id = id;
		averageRating = 0;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public String getContentRating() {
		return contentRating;
	}

	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public String getOrginalTitle() {
		return orginalTitle;
	}

	public void setOrginalTitle(String orginalTitle) {
		this.orginalTitle = orginalTitle;
	}

	public String getStoryLine() {
		return storyLine;
	}

	public void setStoryLine(String storyLine) {
		this.storyLine = storyLine;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Rating getUserRating() {
		return userRating;
	}

	public void setUserRating(Rating userRating) {
		this.userRating = userRating;
	}

	public static Double calculateNewAverage(List<Rating> ratings) throws IllegalArgumentException {
		OptionalDouble avgOpt = ratings.stream().mapToDouble(rating -> rating.getRating()).average();

		if (avgOpt.isPresent()) {
			return avgOpt.getAsDouble();
		}
		throw new IllegalArgumentException("Illegal argument: unable to calculate new average");
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Movie))
			return false;
		Movie m = (Movie) obj;

		if (id != null && !m.getId().equals(id))
			return false;
		if (!m.getTitle().equals(title))
			return false;
		return true;
	}
}
