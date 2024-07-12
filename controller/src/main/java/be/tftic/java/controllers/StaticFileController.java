package be.tftic.java.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@CrossOrigin("*")
public class StaticFileController {

    @PostMapping("/image")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) {
        // The "public" directory is automatically used by Spring to serve static assets
        Path publicDirectory = Paths.get(".", "public").toAbsolutePath();
        Path filepath = Paths.get(publicDirectory.toString(), file.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(filepath)) {
            byte[] imageContent = file.getBytes();
            os.write(imageContent);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<FileSystemResource> getFile(@PathVariable String fileName) {
        Path publicDirectory = Paths.get(".", "public").toAbsolutePath();
        try {
            Path path = Paths.get(publicDirectory.toString(), fileName);
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
