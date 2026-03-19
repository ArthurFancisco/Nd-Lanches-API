package com.ndlanches.nd_lanches_api.controller;

import com.ndlanches.nd_lanches_api.entity.Adicional;
import com.ndlanches.nd_lanches_api.service.AdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adicionais")
@CrossOrigin("*")
public class AdicionalController {

    @Autowired
    private AdicionalService service;

    // GET /api/adicionais/loja/{lojaId} — público, usado pelo cardápio do cliente
    @GetMapping("/loja/{lojaId}")
    public List<Adicional> listarPorLoja(@PathVariable Long lojaId) {
        return service.listarAtivosPorLoja(lojaId);
    }

    // POST /api/adicionais — admin cadastra novo adicional
    @PostMapping
    public ResponseEntity<?> salvar(
            @RequestHeader("Admin-Key") String key,
            @RequestBody Adicional adicional) {

        if (!"arthur123".equals(key)) {
            return ResponseEntity.status(403).body("Acesso negado!");
        }
        return ResponseEntity.ok(service.salvar(adicional));
    }

    // PUT /api/adicionais/{id} — admin edita adicional
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id,
            @RequestBody Adicional dadosNovos) {

        if (!"arthur123".equals(key)) {
            return ResponseEntity.status(403).body("Acesso negado!");
        }
        try {
            return ResponseEntity.ok(service.editar(id, dadosNovos));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Adicional não encontrado");
        }
    }

    // DELETE /api/adicionais/{id} — admin remove adicional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id) {

        if (!"arthur123".equals(key)) {
            return ResponseEntity.status(403).body("Acesso negado!");
        }
        service.excluir(id);
        return ResponseEntity.ok("Adicional removido com sucesso!");
    }
}