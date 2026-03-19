package com.ndlanches.nd_lanches_api.controller;

import com.ndlanches.nd_lanches_api.config.AdminKeyValidator;
import com.ndlanches.nd_lanches_api.entity.Loja;
import com.ndlanches.nd_lanches_api.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ✅ @CrossOrigin removido — agora está centralizado no WebConfig.
 */
@RestController
@RequestMapping("/api/loja")
public class LojaController {

    @Autowired
    private LojaService service;

    @Autowired
    private AdminKeyValidator adminKey; // ✅ injeta o validador

    @GetMapping("/{slug}/status")
    public ResponseEntity<Boolean> getStatus(@PathVariable String slug) {
        Loja loja = service.buscarPorSlug(slug);
        return ResponseEntity.ok(loja.getAberto());
    }

    @PutMapping("/status")
    public ResponseEntity<String> mudarStatus(
            @RequestHeader("Admin-Key") String key,
            @RequestBody boolean aberto) {

        if (adminKey.invalido(key)) return adminKey.negado(); // ✅ uma linha só

        service.atualizarStatus(1L, aberto);
        return ResponseEntity.ok("Status atualizado!");
    }
}