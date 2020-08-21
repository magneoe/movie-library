package no.itminds.movielibrary.repository.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import no.itminds.movielibrary.model.Movie;
import no.itminds.movielibrary.repository.MovieRepository;

@Component
public class MovieMutation implements GraphQLMutationResolver {
	
	@Autowired
	private MovieRepository movieRepo;
	
	public MovieMutation(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
	}
	
	public Movie createMovie() {
		return new Movie();
	} 
	
	public boolean deleteMovie(Long id) {
		movieRepo.deleteById(id);
		return true;
	}
}
