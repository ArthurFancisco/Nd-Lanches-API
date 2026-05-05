package com.ndlanches.nd_lanches_api.controller;

import com.ndlanches.nd_lanches_api.DTO.CardapioDTO;
import com.ndlanches.nd_lanches_api.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/public")
public class CardapioController {

    @Autowired
    private CardapioService cardapioService;

    /**
     * Endpoint público que retorna produtos, adicionais e banners em uma única chamada.
     * A resposta é cachead pelo navegador por 5 minutos (cache-control).
     */
    @GetMapping("/cardapio/{lojaId}")
    public ResponseEntity<CardapioDTO> getCardapio(@PathVariable Long lojaId) {
        CardapioDTO cardapio = cardapioService.getCardapio(lojaId);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(5, TimeUnit.MINUTES))
                .body(cardapio);
    }
}