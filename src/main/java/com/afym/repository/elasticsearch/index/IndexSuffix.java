package com.afym.repository.elasticsearch.index;

public class IndexSuffix {
    public static final String JENKINS_SUFFIX = "jenkins-";

    public static String get(String index){
        return JENKINS_SUFFIX + index;
    }
}