package no.itminds.movielibrary.model;


public class Rating {
	
	private Long id;
	
	private int rating;
	
	private User author;

	public Rating() {}
	
	public Rating(int rating, User author) {
		this.rating = rating;
		this.author = author;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
