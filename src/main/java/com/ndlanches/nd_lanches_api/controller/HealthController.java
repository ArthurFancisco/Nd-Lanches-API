package com.ndlanches.nd_lanches_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HealthController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        try {
            // Executa uma consulta extremamente leve no banco de dados
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return ResponseEntity.ok(Map.of(
                "status", "OK",
                "database", "connected"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(503).body(Map.of(
                "status", "ERROR",
                "database", "disconnected",
                "error", e.getMessage()
            ));
        }
    }
}