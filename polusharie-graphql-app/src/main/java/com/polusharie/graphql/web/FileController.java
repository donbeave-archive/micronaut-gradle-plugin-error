package com.polusharie.graphql.web;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;

@Controller("/rest/file")
public class FileController {

    @Post("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public FileUploadResponse uploadFile(CompletedFileUpload file) {
        return new FileUploadResponse(123L, "http://test.com", "text");
    }

}
