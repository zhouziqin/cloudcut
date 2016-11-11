package com.mainiway.cloudcut.elsearch.es.configuration;

import java.net.InetSocketAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Resource;

@Configuration
@PropertySource(value = "classpath:properties/elasticsearch.properties")
@EnableElasticsearchRepositories(basePackages = "com.es")
public class ElasticsearchConfiguration {
    @Resource
    private Environment environment;
    @Bean
    public Client client() {
    	Client client = TransportClient.builder().build()  
                .addTransportAddress(new InetSocketTransportAddress(
                		new InetSocketAddress(environment.getProperty("elasticsearch.host"), 
                				Integer.parseInt(environment.getProperty("elasticsearch.port")))));  
       
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }


}
