package com.ndlanches.nd_lanches_api.controller;

import com.ndlanches.nd_lanches_api.config.AdminKeyValidator;
import com.ndlanches.nd_lanches_api.entity.Adicional;
import com.ndlanches.nd_lanches_api.service.AdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/adicionais")
public class AdicionalController {

    @Autowired
    private AdicionalService service;

    @Autowired
    private AdminKeyValidator adminKey;

    @GetMapping("/loja/{lojaId}")
    public List<Adicional> listarPorLoja(@PathVariable Long lojaId) {
        return service.listarAtivosPorLoja(lojaId);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestHeader("Admin-Key") String key, @RequestBody Adicional adicional) {
        if (adminKey.invalido(key)) return adminKey.negado();
        return ResponseEntity.ok(service.salvar(adicional));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestHeader("Admin-Key") String key, @PathVariable Long id, @RequestBody Adicional dadosNovos) {
        if (adminKey.invalido(key)) return adminKey.negado();
        try { return ResponseEntity.ok(service.editar(id, dadosNovos)); }
        catch (RuntimeException e) { return ResponseEntity.status(404).body("Adicional não encontrado"); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@RequestHeader("Admin-Key") String key, @PathVariable Long id) {
        if (adminKey.invalido(key)) return adminKey.negado();
        service.excluir(id);
        return ResponseEntity.ok("Adicional removido!");
    }
}