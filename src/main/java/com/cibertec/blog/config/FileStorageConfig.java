package com.cibertec.blog.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Configuration
public class FileStorageConfig {

    @Value("${file.upload-dir}")
    private String uploadDir;
}

