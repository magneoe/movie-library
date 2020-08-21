package no.itminds.movielibrary.repository;

import java.util.List;

import no.itminds.movielibrary.model.Movie;

public class PaginatedMovies {
	private List<Movie> results;
	private String scrollId;
	private Long totHits;
	private Integer hits;
	private Long searchInMillis;
	private Long pages;
	private Boolean lastPage;

	public PaginatedMovies() {

	}

	public PaginatedMovies(List<Movie> results, String scrollId, Long totHits, Integer hits, long searchInMillis,
			long pages, boolean lastPage) {
		super();
		this.results = results;
		this.scrollId = scrollId;
		this.totHits = totHits;
		this.hits = hits;
		this.searchInMillis = searchInMillis;
		this.pages = pages;
		this.lastPage = lastPage;
	}

	public Long getTotHits() {
		return totHits;
	}

	public void setTotHits(Long totHits) {
		this.totHits = totHits;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Long getSearchInMillis() {
		return searchInMillis;
	}

	public void setSearchInMillis(Long searchInMillis) {
		this.searchInMillis = searchInMillis;
	}

	public Long getPages() {
		return pages;
	}

	public void setPages(Long pages) {
		this.pages = pages;
	}

	public String getScrollId() {
		return scrollId;
	}

	public void setScrollId(String scrollId) {
		this.scrollId = scrollId;
	}

	public List<Movie> getResults() {
		return results;
	}

	public void setResults(List<Movie> results) {
		this.results = results;
	}

	public Boolean getLastPage() {
		return lastPage;
	}

	public void setLastPage(Boolean lastPage) {
		this.lastPage = lastPage;
	}
}
