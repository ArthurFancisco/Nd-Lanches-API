package com.ndlanches.nd_lanches_api.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ndlanches.nd_lanches_api.entity.Produto;
import com.ndlanches.nd_lanches_api.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    // Endpoint: GET http://localhost:8080/api/produtos/loja/1
    @GetMapping("/loja/{lojaId}")
    public List<Produto> listarPorLoja(@PathVariable Long lojaId) {
        return service.listarAtivosPorLoja(lojaId);
    }

    @PutMapping("/status")
public ResponseEntity<String> mudarStatus(@RequestHeader("Admin-Key") String key, @RequestBody boolean aberto) {
    if (!"arthur123".equals(key)) {
        return ResponseEntity.status(403).body("Acesso negado!");
    }
    // lógica para mudar status...
    return ResponseEntity.ok("Status alterado!");
}
}