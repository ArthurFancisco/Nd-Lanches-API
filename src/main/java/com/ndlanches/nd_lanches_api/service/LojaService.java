package com.ndlanches.nd_lanches_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndlanches.nd_lanches_api.entity.Loja;
import com.ndlanches.nd_lanches_api.repository.LojaRepository;

import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;


    public Loja buscarPorSlug(String slug) {
    return repository.findBySlug(slug)
        .orElseThrow(() -> new RuntimeException("Loja não encontrada!"));
        }

        public void atualizarStatus(Long id, boolean aberto) {
            Loja loja = repository.findById(id).orElseThrow();
            loja.setAberto(aberto);
            repository.save(loja); // Isso garante que o F5 mantenha a loja fechada
        }

    // Abre ou fecha a loja
    public Loja alternarStatus(String slug, boolean aberto) {
        Loja loja = repository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Loja não encontrada!"));
        
        loja.setAberto(aberto);
        return repository.save(loja);
    }

    // Salva ou atualiza os dados da loja (nome, whatsapp, etc)
    public Loja salvar(Loja loja) {
        return repository.save(loja);
    }

}