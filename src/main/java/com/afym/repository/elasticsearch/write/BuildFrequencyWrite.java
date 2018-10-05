package com.afym.repository.elasticsearch.write;

import com.afym.connector.TransportElasticsearchConnector;
import com.afym.entity.BuildFrequencyEntity;
import com.afym.repository.elasticsearch.index.BuildFrequencyIndex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BuildFrequencyWrite {

    @Autowired
    private TransportElasticsearchConnector elasticsearch;

    public int insert(BuildFrequencyEntity build) {
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("application", build.getApplication());
        mapping.put("environment", build.getEnvironment());
        mapping.put("created", build.getCreated());
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String json = gson.toJson(mapping);

        IndexResponse response = this.elasticsearch.getConnector().prepareIndex(BuildFrequencyIndex.INDEX, BuildFrequencyIndex.INDEX)
                .setSource(json, XContentType.JSON)
                .get();

        return response.status().getStatus();
    }
}
