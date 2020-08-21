package no.itminds.movielibrary.repository.resolver;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import no.itminds.movielibrary.exceptions.MovieSearchException;
import no.itminds.movielibrary.repository.MovieRepository;
import no.itminds.movielibrary.repository.PaginatedMovies;

@Component
public class MovieQuery implements GraphQLQueryResolver {
	private MovieRepository movieRepo;

	public MovieQuery(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
	}

	public PaginatedMovies findAllMovies(Integer size, String scrollId) throws MovieSearchException{
		try {
			return movieRepo.findAll(size, scrollId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Long countMovies() throws IOException {
		return movieRepo.count();
	}
}
