package com.afym.test;

import com.afym.connector.JenkinsConnector;
import com.offbytwo.jenkins.model.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JenkinsServerTest {
    @Autowired
    private JenkinsConnector jenkins;
    @Test
    public void isValidConnector() {
        boolean empty = true;
        try {
            Map<String,Job> jobs = jenkins.getConnector().getJobs();
            empty = jobs.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("jenkins:jobs", false, empty);
    }
}
