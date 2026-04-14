package com.ndlanches.nd_lanches_api.controller;

import com.ndlanches.nd_lanches_api.config.AdminKeyValidator;
import com.ndlanches.nd_lanches_api.entity.Banner;
import com.ndlanches.nd_lanches_api.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerService service;

    @Autowired
    private AdminKeyValidator adminKey;

    // ✅ PÚBLICO — cardápio busca banners ativos
    @GetMapping("/loja/{lojaId}/ativos")
    public List<Banner> listarAtivos(@PathVariable Long lojaId) {
        return service.listarAtivosPorLoja(lojaId);
    }

    // 🔒 ADMIN — lista todos (ativos e inativos)
    @GetMapping("/loja/{lojaId}")
public ResponseEntity<List<Banner>> listarPorLoja(@PathVariable Long lojaId) {
    List<Banner> banners = service.listarPorLoja(lojaId);
    return ResponseEntity.ok(banners);
}

    // 🔒 ADMIN — criar banner
    @PostMapping
    public ResponseEntity<?> salvar(
            @RequestHeader("Admin-Key") String key,
            @RequestBody Banner banner) {
        if (adminKey.invalido(key)) return adminKey.negado();
        return ResponseEntity.ok(service.salvar(banner));
    }

    // 🔒 ADMIN — editar banner
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id,
            @RequestBody Banner dados) {
        if (adminKey.invalido(key)) return adminKey.negado();
        try { return ResponseEntity.ok(service.editar(id, dados)); }
        catch (RuntimeException e) { return ResponseEntity.status(404).body("Banner não encontrado"); }
    }

    // 🔒 ADMIN — ativar/desativar com 1 clique
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id) {
        if (adminKey.invalido(key)) return adminKey.negado();
        try { return ResponseEntity.ok(service.alternarAtivo(id)); }
        catch (RuntimeException e) { return ResponseEntity.status(404).body("Banner não encontrado"); }
    }

    // 🔒 ADMIN — remover banner
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id) {
        if (adminKey.invalido(key)) return adminKey.negado();
        service.excluir(id);
        return ResponseEntity.ok("Banner removido!");
    }
}