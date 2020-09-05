package com.polusharie.graphql.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileUploadResponse {

    @JsonProperty("id")
    private final Long id;

    @JsonProperty("url")
    private final String url;

    @JsonProperty("contentType")
    private final String contentType;

    public FileUploadResponse(Long id, String url, String contentType) {
        this.id = id;
        this.url = url;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

}
