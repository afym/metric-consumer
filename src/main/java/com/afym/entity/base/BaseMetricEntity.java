package com.afym.entity.base;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseMetricEntity {
    protected String application;
    protected String environment;
    protected Date created;

    public BaseMetricEntity()
    {
        this.created = new Date();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("application: ")
                .append(this.application)
                .append("environment: ")
                .append(this.environment)
                .append("created: ")
                .append(formatter.format(this.created));

        return builder.toString();
    }
}
