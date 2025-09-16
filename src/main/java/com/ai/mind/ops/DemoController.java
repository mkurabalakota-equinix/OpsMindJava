package com.ai.mind.ops;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class DemoController {
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);


    @GetMapping("/hello")
    public String hello() {
        log.error("/api/hello called");

        try {
            String s = null;
            // This will throw a NullPointerException
            s.length();
        } catch (NullPointerException e) {
            log.error("Caught NullPointerException in /hello endpoint", e);
            throw e; // rethrow so that it’s still visible as an error
        }

        return "Hello from Spring Boot!";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam String username,
            @RequestParam String password) {

        log.info("Login attempt with username: {}", username);

        String risky = null;
        Map<String, Object> response = new HashMap<>();

        try {
            // Simulate risky operation
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", risky.toString()
            ));
        } catch (NullPointerException e) {
            log.error("Simulated NPE during login for user {}", username, e);

            response.put("status", "error");
            response.put("message", "NullPointerException occurred");
            response.put("details", e.toString()); // you can also add stacktrace if needed

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
