package com.afym.connector;

import com.afym.properties.ElasticsearchProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ElasticsearchConnector implements AbstractConnector<RestHighLevelClient> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElasticsearchProperties properties;

    @Override
    public RestHighLevelClient getConnector() {
        RestHighLevelClient client = null;
        try {
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(this.properties.getHost(), this.properties.getPort(), "http")));
        } catch (Exception e) {
            logger.error("Error in elasticsearch connection");
            logger.error(e.getLocalizedMessage());
            logger.error(e.getMessage());
        }

        return client;
    }
}
