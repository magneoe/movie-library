package no.itminds.movielibrary.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.itminds.movielibrary.exceptions.MovieSearchException;
import no.itminds.movielibrary.model.Movie;

@Repository
public class MoveRepositoryImpl implements MovieRepository {

	private static final Logger logger = Logger.getLogger(MovieRepository.class.getName());
	
	private static final Integer DEFAULT_SEARCH_SIZE = 10;

	@Autowired
	private RestHighLevelClient client;
	
	private final Scroll scroller = new Scroll(TimeValue.timeValueSeconds(180));
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PaginatedMovies findAll(Integer size, String scrollId) throws IOException {
		if(scrollId != null) {
			return getScrollableMovies(scrollId);
		}
		else {
			return getMovies(size);
		}
		
	}


	@Override
	public Long count() throws IOException {
		CountRequest countReq = new CountRequest("movies");
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		
		countReq.source(searchSourceBuilder);
		CountResponse res = client.count(countReq, RequestOptions.DEFAULT);
		
		return res.getCount();
		
	}
	
	
	/*
	 * Helper methods
	 */
	private PaginatedMovies getMovies(Integer size) throws IOException {
		if(size == null)
			size = DEFAULT_SEARCH_SIZE;
		SearchRequest req = new SearchRequest("movies");
		req.scroll(scroller);
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		if(size != null) searchSourceBuilder.size(size); //Default is 10.
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		req.source(searchSourceBuilder);
		
		SearchResponse res = client.search(req, RequestOptions.DEFAULT);
		//Process hits
		String scrollId = res.getScrollId();
		SearchHits hits = res.getHits();

		List<Movie> movies = new ArrayList<Movie>(hits.getHits().length);
		if(hits != null && hits.getHits().length > 0) {
			for(SearchHit hit : hits.getHits()) {
				Movie m = objectMapper.readValue(hit.getSourceAsString(), Movie.class);
				movies.add(m);
			}
		} 
		long pages = (long) Math.ceil((double)hits.getTotalHits().value / size);
		boolean lastPage = pages == 1;
		return new PaginatedMovies(movies, scrollId, hits.getTotalHits().value, hits.getHits().length, res.getTook().getMillis(), pages, lastPage);
	}
	
	private PaginatedMovies getScrollableMovies(String scrollId) throws IOException, MovieSearchException {
		boolean lastPage = false;
		
		try {
		SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId); 
	    scrollRequest.scroll(scroller);
	    SearchResponse searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT); 
	    String newScrollId = searchResponse.getScrollId();
	    SearchHits hits = searchResponse.getHits();

	    List<Movie> movies = new ArrayList<Movie>(hits.getHits().length);
		if(hits != null && hits.getHits().length > 0) {
			for(SearchHit hit : hits.getHits()) {
				Movie m = objectMapper.readValue(hit.getSourceAsString(), Movie.class);
				movies.add(m);
			}
		}
		if(movies.size() == 0 || newScrollId.equals(scrollId)) {
			clearScrollSearch(newScrollId);
			lastPage = true;
			newScrollId = null;
		}
		
		long pages = (long) Math.ceil((double)hits.getTotalHits().value / hits.getHits().length); 
		return new PaginatedMovies(movies, newScrollId, hits.getTotalHits().value, hits.getHits().length, searchResponse.getTook().getMillis(), pages, lastPage);
		
		}
		catch(ElasticsearchException esex) {
			logger.warning(esex.getMessage());
			throw new MovieSearchException("Invalid scrollset - could have timed out backend :(", scrollId);
		}
	}
	
	private boolean clearScrollSearch(String scrollId) throws IOException {
		
		ClearScrollRequest clearScrollRequest = new ClearScrollRequest(); 
		clearScrollRequest.addScrollId(scrollId);
		ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
		return clearScrollResponse.isSucceeded();
	}
}
