package com.ndlanches.nd_lanches_api.service;

import com.ndlanches.nd_lanches_api.entity.Banner;
import com.ndlanches.nd_lanches_api.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BannerService {

    @Autowired
    private BannerRepository repository;

    // Cardápio público — só banners ativos
    public List<Banner> listarAtivosPorLoja(Long lojaId) {
        return repository.findByLojaIdAndAtivoTrue(lojaId);
    }

    // Admin — todos os banners
    public List<Banner> listarTodosPorLoja(Long lojaId) {
        return repository.findByLojaId(lojaId);
    }

    // Admin — criar banner
    public Banner salvar(Banner banner) {
        return repository.save(banner);
    }

    // Admin — editar banner
    public Banner editar(Long id, Banner dados) {
        return repository.findById(id).map(b -> {
            b.setTitulo(dados.getTitulo());
            b.setDescricao(dados.getDescricao());
            b.setCor(dados.getCor());
            b.setEmoji(dados.getEmoji());
            b.setAtivo(dados.getAtivo());
            return repository.save(b);
        }).orElseThrow(() -> new RuntimeException("Banner não encontrado"));
    }

    // Admin — ativar/desativar rapidamente
    public Banner alternarAtivo(Long id) {
        return repository.findById(id).map(b -> {
            b.setAtivo(!b.getAtivo());
            return repository.save(b);
        }).orElseThrow(() -> new RuntimeException("Banner não encontrado"));
    }

    // Admin — remover banner
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}