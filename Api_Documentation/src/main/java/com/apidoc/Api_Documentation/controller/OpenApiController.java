package com.apidoc.Api_Documentation.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OpenApiController {

    private final ResourceLoader resourceLoader;

    public OpenApiController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/openapi-files")
    public List<String> getOpenApiFiles() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/openapi/");
        return Files.list(Paths.get(resource.getURI()))
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());
    }
}
