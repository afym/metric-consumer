package com.afym.repository.elasticsearch.index;

import com.afym.connector.ElasticsearchConnector;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

// https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.3/java-rest-high-create-index.html
// https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-types.html
public abstract class AbstractIndex {
    private static final int DEFAULT_SHARDS = 1;
    private static final int DEFAULT_REPLICAS = 1;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected String index;
    protected CreateIndexRequest createIndexRequest;

    @Autowired
    protected ElasticsearchConnector elasticsearch;

    public abstract XContentBuilder getMapping();

    public CreateIndexResponse createIndex()
    {
        this.createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", DEFAULT_SHARDS)
                .put("index.number_of_replicas", DEFAULT_REPLICAS)
        );

        return this.createGeneralIndex();
    }

    public CreateIndexResponse createIndex(Settings settings)
    {
        this.createIndexRequest.settings(settings);
        return this.createGeneralIndex();
    }

    public DeleteIndexResponse deleteIndex() {
        DeleteIndexRequest request = new DeleteIndexRequest(this.index);

        try {
            return  this.elasticsearch.getConnector().indices().delete(request);
        } catch (IOException e) {
            logger.error("Error in index deletion");
            logger.error(e.getMessage());
        }

        return null;
    }

    protected boolean isIndexCreated()
    {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(this.index);

        try {
            return this.elasticsearch.getConnector().indices().exists(request);
        } catch (IOException e) {
            logger.error("Error in index validation");
            logger.error(e.getMessage());
        }

        return false;
    }

    private CreateIndexResponse createGeneralIndex()
    {
        try {
            if (!this.isIndexCreated()) {
                return this.elasticsearch.getConnector().indices().create(this.createIndexRequest);
            }
        } catch (IOException e) {
            logger.error("Error in index validation");
            logger.error(e.getMessage());
        }

        return null;
    }
}
