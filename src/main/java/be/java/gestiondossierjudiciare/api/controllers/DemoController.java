package be.java.gestiondossierjudiciare.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World !");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/hello")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello my master !");
    }

    @PreAuthorize("hasAnyAuthority('AGENT', 'ADMIN')")
    @GetMapping("/agent")
    public ResponseEntity<String> helloAgent(){
        return ResponseEntity.ok("Hello Agent 212");
    }

    @PreAuthorize("hasAnyAuthority('AVOCAT', 'ADMIN')")
    @GetMapping("/avocat")
    public ResponseEntity<String> helloAvocat(){
        return ResponseEntity.ok("Hello toi");
    }


}
