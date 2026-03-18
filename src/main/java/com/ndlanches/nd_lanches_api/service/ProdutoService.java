package com.ndlanches.nd_lanches_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndlanches.nd_lanches_api.entity.Produto;
import com.ndlanches.nd_lanches_api.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    // Busca produtos para o cardápio (ativos e ordenados)
    public List<Produto> listarAtivosPorLoja(Long lojaId) {
        return repository.findByLojaIdAndAtivoTrueOrderByOrdemAsc(lojaId);
    }

    // Salva ou atualiza um produto (usado pelo Admin)
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    // Deleta um produto
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // Edita um produto
    public Produto editar(Long id, Produto dadosNovos) {
    return repository.findById(id).map(produto -> {
        produto.setNome(dadosNovos.getNome());
        produto.setDescricao(dadosNovos.getDescricao());
        produto.setPreco(dadosNovos.getPreco());
        return repository.save(produto);
    }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
}
}