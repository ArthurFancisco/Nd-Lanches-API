package com.ndlanches.nd_lanches_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ndlanches.nd_lanches_api.entity.Loja;
import com.ndlanches.nd_lanches_api.service.LojaService;

@RestController
@RequestMapping("/api/loja")
public class LojaController {

    @Autowired
    private LojaService service;

    // Endpoint: GET http://localhost:8080/api/loja/nd-lanches/status
    @GetMapping("/{slug}/status")
    public ResponseEntity<Loja> getStatus(@PathVariable String slug) {
        return service.buscarPorSlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}