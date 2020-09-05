package com.polusharie.graphql;

import io.micronaut.runtime.Micronaut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.util.TimeZone;

public class GraphQlApplication {

    private static final Logger log = LoggerFactory.getLogger(GraphQlApplication.class);

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");

        log.debug("Setting UTC time zone by default");
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));

        Micronaut.run(GraphQlApplication.class, args);
    }

}
