package com.ndlanches.nd_lanches_api.controller;

import com.ndlanches.nd_lanches_api.config.AdminKeyValidator;
import com.ndlanches.nd_lanches_api.entity.Loja;
import com.ndlanches.nd_lanches_api.service.LojaService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loja")
public class LojaController {

    @Autowired
    private LojaService service;

    @Autowired
    private AdminKeyValidator adminKey;

    @GetMapping("/{slug}")
    public ResponseEntity<Loja> getLoja(@PathVariable String slug) {
        Loja loja = service.buscarPorSlug(slug);
        return ResponseEntity.ok(loja);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<?> atualizarLoja(
            @RequestHeader("Admin-Key") String key,
            @PathVariable String slug,
            @RequestBody Map<String, String> dados) {

        if (adminKey.invalido(key)) return adminKey.negado();

        Loja loja = service.buscarPorSlug(slug);
        if (dados.containsKey("whatsapp")) {
            loja.setWhatsapp(dados.get("whatsapp"));
        }
        if (dados.containsKey("mensagemFechado")) {
            loja.setMensagemFechado(dados.get("mensagemFechado"));
        }
        // NOVO: horário de funcionamento
        if (dados.containsKey("horarioFuncionamento")) {
            loja.setHorarioFuncionamento(dados.get("horarioFuncionamento"));
        }
        // Opcional: permitir editar nome
        if (dados.containsKey("nome")) {
            loja.setNome(dados.get("nome"));
        }

        service.salvar(loja);
        return ResponseEntity.ok(loja);
    }

    @GetMapping("/{slug}/status")
    public ResponseEntity<Boolean> getStatus(@PathVariable String slug) {
        Loja loja = service.buscarPorSlug(slug);
        return ResponseEntity.ok(loja.getAberto());
    }

    @PutMapping("/status")
    public ResponseEntity<String> mudarStatus(
            @RequestHeader("Admin-Key") String key,
            @RequestBody boolean aberto) {

        if (adminKey.invalido(key)) return adminKey.negado();
        service.atualizarStatus(1L, aberto);
        return ResponseEntity.ok("Status atualizado!");
    }
}