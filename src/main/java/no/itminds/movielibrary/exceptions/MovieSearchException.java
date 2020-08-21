package no.itminds.movielibrary.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class MovieSearchException extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5751293837659239606L;
	/**
	 * 
	 */

	private Map<String, Object> extensions = new HashMap<>();

	public MovieSearchException(String message, String invalidScrollId) {
		super(message);
		extensions.put("invalidScrollId", invalidScrollId);
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
