package no.itminds.movielibrary;

import java.util.logging.Logger;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	private final static Logger logger = Logger.getLogger(ApplicationConfig.class.getName());
	
	@Value("${elasticsearch.client1.url}")
	private String elasticClient1Url;
	
	@Value("${elasticsearch.client1.port}")
	private Integer elasticClient1Port;
	
	
	@Bean(destroyMethod="close")
	public RestHighLevelClient highLevelRestClient() {
		logger.info("Booting up elastic search RestHighLevelClient...");
		return new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost(elasticClient1Url, elasticClient1Port, "http")));
	}
}
