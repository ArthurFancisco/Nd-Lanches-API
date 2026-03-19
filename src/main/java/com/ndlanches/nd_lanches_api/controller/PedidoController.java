package com.ndlanches.nd_lanches_api.controller;

import com.ndlanches.nd_lanches_api.config.AdminKeyValidator;
import com.ndlanches.nd_lanches_api.entity.Pedido;
import com.ndlanches.nd_lanches_api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private AdminKeyValidator adminKey;

    // ✅ PÚBLICO — cliente envia o pedido (sem autenticação)
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Pedido pedido) {
        try {
            Pedido salvo = service.salvar(pedido);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar pedido: " + e.getMessage());
        }
    }

    // 🔒 ADMIN — lista todos os pedidos da loja
    @GetMapping("/loja/{lojaId}")
    public ResponseEntity<?> listarTodos(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long lojaId) {

        if (adminKey.invalido(key)) return adminKey.negado();
        return ResponseEntity.ok(service.listarPorLoja(lojaId));
    }

    // 🔒 ADMIN — lista apenas pedidos de hoje
    @GetMapping("/loja/{lojaId}/hoje")
    public ResponseEntity<?> listarHoje(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long lojaId) {

        if (adminKey.invalido(key)) return adminKey.negado();
        return ResponseEntity.ok(service.listarHoje(lojaId));
    }

    // 🔒 ADMIN — atualiza status do pedido
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        if (adminKey.invalido(key)) return adminKey.negado();
        try {
            String novoStatus = body.get("status");
            return ResponseEntity.ok(service.atualizarStatus(id, novoStatus));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Pedido não encontrado");
        }
    }

    // 🔒 ADMIN — remove pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @RequestHeader("Admin-Key") String key,
            @PathVariable Long id) {

        if (adminKey.invalido(key)) return adminKey.negado();
        service.excluir(id);
        return ResponseEntity.ok("Pedido removido!");
    }
}