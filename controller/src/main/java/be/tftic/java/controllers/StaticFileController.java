package be.tftic.java.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class StaticFileController {

    private static final String FILE_DIRECTORY = "C:\\Users\\a.georis\\Desktop\\Spring\\gestion-dossier-judiciaire\\uploads";

    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<FileSystemResource> serveFile(@PathVariable String fileName) {
        try {
            Path path = Paths.get(FILE_DIRECTORY, fileName);
            FileSystemResource resource = new FileSystemResource(path);

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Assurez-vous du bon type MIME ici
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
