package com.afym.connector;

import com.afym.properties.JenkinsProperties;
import com.offbytwo.jenkins.JenkinsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class JenkinsConnector implements AbstractConnector<JenkinsServer> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JenkinsProperties properties;

    @Override
    public JenkinsServer getConnector() {
        JenkinsServer jenkins = null;
        try {
            jenkins = new JenkinsServer(new URI(properties.getServer()), properties.getUser(), properties.getPassword());
        } catch (URISyntaxException e) {
            logger.error("Error in jenkins api rest connection");
            logger.error(e.getReason());
            logger.error(e.getMessage());
        }

        return jenkins;
    }
}
