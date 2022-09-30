package org.orakzai.lab.shop.web.config;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories("org.orakzai.lab.shop.domain.search")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
	
	@Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

	
	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		
		RestClientBuilder builder = RestClient
				.builder(new HttpHost(host, port, null))
				.setDefaultHeaders(compatibilityHeaders());
		return new RestHighLevelClient(builder);
//		final ClientConfiguration clientConf = ClientConfiguration
//				.builder()
//				.connectedTo("localhost:9200")
//				.build();
//		return RestClients.create(clientConf).rest();
	}


	private Header[] compatibilityHeaders() {
		return new Header[] {new BasicHeader(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7"), new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7")};
	}
	
	

	
}
