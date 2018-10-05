package com.afym.connector;

import com.afym.properties.ElasticsearchProperties;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class TransportElasticsearchConnector implements AbstractConnector<TransportClient> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElasticsearchProperties properties;

    @Override
    public TransportClient getConnector() {
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", this.properties.getCluster())
                    //.put("client.transport.sniff", true)
                    //.put("client.transport.ignore_cluster_name", false)
                    .build();

            return new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(this.properties.getHost()), properties.getTransport()));
        } catch (UnknownHostException e) {
            logger.error("Error in elasticsearch transport connection");
            logger.error(e.getLocalizedMessage());
            logger.error(e.getMessage());
        }

        return null;
    }
}