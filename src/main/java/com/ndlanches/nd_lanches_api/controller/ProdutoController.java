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

    // Listar produtos de uma loja específica
    @GetMapping("/loja/{lojaId}")
    public List<Produto> listarPorLoja(@PathVariable Long lojaId) {
        return service.listarAtivosPorLoja(lojaId);
    }

    // Mudar status da loja
    @PutMapping("/status")
    public ResponseEntity<String> mudarStatus(@RequestHeader("Admin-Key") String key, @RequestBody boolean aberto) {
        if (!"arthur123".equals(key)) {
            return ResponseEntity.status(403).body("Acesso negado!");
        }
        // Aqui deve ir a lógica para chamar o service e alterar o status no banco
        return ResponseEntity.ok("Status alterado!");
    }

    // NOVO: Cadastrar Produto com Segurança
    @PostMapping
    public ResponseEntity<?> salvar(@RequestHeader("Admin-Key") String key, @RequestBody Produto produto) {
        if (!"arthur123".equals(key)) {
            return ResponseEntity.status(403).body("Acesso negado!");
        }
        Produto salvo = service.salvar(produto); // Certifique-se que o service tem o método salvar
        return ResponseEntity.ok(salvo);
    }

    // NOVO: Excluir Produto com Segurança
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@RequestHeader("Admin-Key") String key, @PathVariable Long id) {
        if (!"arthur123".equals(key)) {
            return ResponseEntity.status(403).body("Acesso negado!");
        }
        service.excluir(id); // Certifique-se que o service tem o método excluir
        return ResponseEntity.ok("Produto removido com sucesso!");
    }

    @PutMapping("/{id}")
public ResponseEntity<?> editar(@RequestHeader("Admin-Key") String key, @PathVariable Long id, @RequestBody Produto produtoAtualizado) {
    if (!"arthur123".equals(key)) {
        return ResponseEntity.status(403).body("Acesso negado!");
    }
    
    try {
        Produto produto = service.editar(id, produtoAtualizado);
        return ResponseEntity.ok(produto);
    } catch (RuntimeException e) {
        return ResponseEntity.status(404).body("Produto não encontrado");
    }
}
}