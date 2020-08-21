package no.itminds.movielibrary.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class MovieNotFoundException extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1248110415254719845L;
	private Map<String, Object> extensions = new HashMap<>();
	
	public MovieNotFoundException(String message, Long invalidMovieId) {
		super(message);
		extensions.put("invalidMovieId", invalidMovieId);
	}
	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
