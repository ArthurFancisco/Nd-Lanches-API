package com.ndlanches.nd_lanches_api.service;

import com.ndlanches.nd_lanches_api.entity.Adicional;
import com.ndlanches.nd_lanches_api.repository.AdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdicionalService {

    @Autowired
    private AdicionalRepository repository;

    // Cardápio público: só ativos
    public List<Adicional> listarAtivosPorLoja(Long lojaId) {
        return repository.findByLojaIdAndAtivoTrue(lojaId);
    }

    // Admin: cadastrar novo adicional
    public Adicional salvar(Adicional adicional) {
        return repository.save(adicional);
    }

    // Admin: editar adicional existente
    public Adicional editar(Long id, Adicional dadosNovos) {
        return repository.findById(id).map(a -> {
            a.setNome(dadosNovos.getNome());
            a.setEmoji(dadosNovos.getEmoji());
            a.setPreco(dadosNovos.getPreco());
            a.setAtivo(dadosNovos.getAtivo());
            return repository.save(a);
        }).orElseThrow(() -> new RuntimeException("Adicional não encontrado"));
    }

    // Admin: remover adicional
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}