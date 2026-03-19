package com.ndlanches.nd_lanches_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * PASSO 2 — Componente reutilizável para validar a chave admin.
 *
 * Por que isso? Hoje você repete este bloco em TODOS os controllers:
 *   if (!"arthur123".equals(key)) return ResponseEntity.status(403)...
 *
 * Com esse componente, fica uma linha só:
 *   if (adminKey.invalido(key)) return adminKey.negado();
 */
@Component
public class AdminKeyValidator {

    @Value("${admin.key}")
    private String adminKey;

    /**
     * Retorna true se a chave enviada for INVÁLIDA (use para retornar 403 cedo).
     */
    public boolean invalido(String keyEnviada) {
        return keyEnviada == null || !adminKey.equals(keyEnviada);
    }

    /**
     * Resposta padrão de acesso negado.
     */
    public ResponseEntity<String> negado() {
        return ResponseEntity.status(403).body("Acesso negado!");
    }
}