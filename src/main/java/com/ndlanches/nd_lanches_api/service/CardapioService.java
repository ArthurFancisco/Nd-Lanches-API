package com.ndlanches.nd_lanches_api.service;

import com.ndlanches.nd_lanches_api.DTO.CardapioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardapioService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AdicionalService adicionalService;

    @Autowired
    private BannerService bannerService;

    public CardapioDTO getCardapio(Long lojaId) {
        CardapioDTO dto = new CardapioDTO();
        dto.setProdutos(produtoService.listarAtivosPorLoja(lojaId));
        dto.setAdicionais(adicionalService.listarAtivosPorLoja(lojaId));
        dto.setBanners(bannerService.listarAtivosPorLoja(lojaId));
        return dto;
    }
}