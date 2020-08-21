package no.itminds.movielibrary.repository;

import java.io.IOException;

public interface MovieRepository {

	public void deleteById(Long id);

	public PaginatedMovies findAll(Integer size, String scrollId) throws IOException;

	public Long count() throws IOException;
	
}
