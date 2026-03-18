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
    private com.ndlanches.nd_lanches_api.service.LojaService lojaService; // Você precisa injetar o serviço da loja

    @PutMapping("/status")
    public ResponseEntity<String> mudarStatus(@RequestHeader("Admin-Key") String key, @RequestBody boolean aberto) {
        if (!"arthur123".equals(key)) {
            return ResponseEntity.status(403).body("Acesso negado!");
        }
        
        // ESTA É A LINHA QUE ESTAVA FALTANDO:
        lojaService.atualizarStatus(1L, aberto); 
        
        return ResponseEntity.ok("Status alterado no banco de dados!");
    }
}