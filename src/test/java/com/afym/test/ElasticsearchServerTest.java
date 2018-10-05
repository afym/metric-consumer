package com.afym.test;

import com.afym.connector.ElasticsearchConnector;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchServerTest {
    @Autowired
    private ElasticsearchConnector elasticsearch;
    @Test
    public void isValidConnector() {
        boolean available = false;
        try {
            RestHighLevelClient connector = elasticsearch.getConnector();
            available = connector.ping();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("elasticsearch:ping", true, available);
    }
}
