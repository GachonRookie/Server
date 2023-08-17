package gachon.rookie.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/health")
    public ResponseEntity<Boolean> healthCheck() {
        return ResponseEntity.ok(true);
    }
}
