package com.ndlanches.nd_lanches_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ndlanches.nd_lanches_api.entity.Loja;
import com.ndlanches.nd_lanches_api.service.LojaService;

@RestController
@RequestMapping("/api/loja") // Define a base da URL
@CrossOrigin("*")
public class LojaController {

    @Autowired
    private LojaService service;

    // Rota que o JavaScript chama: /api/loja/nd-lanches/status
   @GetMapping("/{slug}/status") // Aqui o Java aceita String (nd-lanches)
public ResponseEntity<Boolean> getStatus(@PathVariable String slug) {
    Loja loja = service.buscarPorSlug(slug);
    return ResponseEntity.ok(loja.getAberto());
}

@PutMapping("/status") // Rota para o Admin mudar o status
public ResponseEntity<String> mudarStatus(@RequestHeader("Admin-Key") String key, @RequestBody boolean aberto) {
    if (!"arthur123".equals(key)) return ResponseEntity.status(403).body("Chave incorreta!");
    service.atualizarStatus(1L, aberto); 
    return ResponseEntity.ok("Status atualizado!");
}
}