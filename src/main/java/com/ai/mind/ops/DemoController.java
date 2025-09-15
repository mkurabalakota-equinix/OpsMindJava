package com.ai.mind.ops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public String login(@RequestParam String username, @RequestParam String password) {
        log.info("Login attempt with username: {}", username);

        String risky = null;
        try {
            return risky.toString();
        } catch (NullPointerException e) {
            log.error("Simulated NPE during login for user {}", username, e);
            throw e;
        }
    }
}
