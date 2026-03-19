package com.ndlanches.nd_lanches_api.controller;

import com.ndlanches.nd_lanches_api.config.AdminKeyValidator;
import com.ndlanches.nd_lanches_api.entity.Produto;
import com.ndlanches.nd_lanches_api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ✅ @CrossOrigin removido — centralizado no WebConfig.
 */
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private AdminKeyValidator adminKey; // ✅ injeta o validador

    @GetMapping("/loja/{lojaId}")
    public List<Produto> listarPorLoja(@PathVariable Long lojaId) {
        return service.listarAtivosPorLoja(lojaId);
    }

    @PostMapping
    public ResponseEntity<?> salvar(
            @RequestHeader("Admin-Key") String key,
            @RequestBody Produto produto) {

        if (adminKey.invalido(key)) return adminKey.negado();

        return ResponseEntity.ok(service.salvar(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado) {

        if (adminKey.invalido(key)) return adminKey.negado();

        try {
            return ResponseEntity.ok(service.editar(id, produtoAtualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Produto não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id) {

        if (adminKey.invalido(key)) return adminKey.negado();

        service.excluir(id);
        return ResponseEntity.ok("Produto removido com sucesso!");
    }
}