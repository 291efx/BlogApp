package com.cibertec.blogapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageService {
    private final Path rootLocation = Paths.get("uploads");

    public String store(MultipartFile file) {
        try {
            Files.createDirectories(rootLocation);
            String uuidFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filename = uuidFilename.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            Path destination = rootLocation.resolve(filename);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return filename; // <-- Retorna el nombre generado
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo: " + file.getOriginalFilename(), e);
        }
    }

}
