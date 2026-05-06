package com.ndlanches.nd_lanches_api.service;

import com.ndlanches.nd_lanches_api.DTO.CardapioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CardapioService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AdicionalService adicionalService;

    @Autowired
    private BannerService bannerService;

    @Cacheable(value = "cardapio", key = "#lojaId", unless = "#result == null")
    public CardapioDTO getCardapio(Long lojaId) {
        System.out.println("Buscando cardápio do banco de dados para loja " + lojaId); // log para depuração
        CardapioDTO dto = new CardapioDTO();
        dto.setProdutos(produtoService.listarAtivosPorLoja(lojaId));
        dto.setAdicionais(adicionalService.listarAtivosPorLoja(lojaId));
        dto.setBanners(bannerService.listarAtivosPorLoja(lojaId));
        return dto;
    }
}