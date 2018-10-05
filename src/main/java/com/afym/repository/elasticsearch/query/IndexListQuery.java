package com.afym.repository.elasticsearch.query;

import com.afym.connector.TransportElasticsearchConnector;
import com.afym.repository.elasticsearch.index.IndexSuffix;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexListQuery {
    @Autowired
    private TransportElasticsearchConnector elasticsearch;

    public String[] getIndexes() {
        return elasticsearch.getConnector().admin().indices()
                .getIndex(new GetIndexRequest().indices(IndexSuffix.JENKINS_SUFFIX + "*"))
                .actionGet().getIndices();
    }
}