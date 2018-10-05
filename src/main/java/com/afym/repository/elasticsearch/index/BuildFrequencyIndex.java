package com.afym.repository.elasticsearch.index;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BuildFrequencyIndex extends AbstractIndex {
    public static final String INDEX = IndexSuffix.get("build-frequency");

    public BuildFrequencyIndex() {
        this.index = INDEX;
        this.createIndexRequest = new CreateIndexRequest(this.index);
        this.createIndexRequest.mapping(this.index, getMapping());
    }

    @Override
    public XContentBuilder getMapping() {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject(this.index);
                {
                    builder.startObject("properties");
                    {
                        builder.startObject("application");
                        {
                            builder.field("type", "keyword");
                        }
                        builder.endObject();

                        builder.startObject("environment");
                        {
                            builder.field("type", "keyword");
                        }
                        builder.endObject();

                        builder.startObject("created");
                        {
                            builder.field("type", "date");
                        }
                        builder.endObject();
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();

            return builder;
        } catch (IOException e) {
            this.logger.error("Error in mapping definition for build frequency");
            this.logger.error(e.getMessage());
        }

        return null;
    }
}
