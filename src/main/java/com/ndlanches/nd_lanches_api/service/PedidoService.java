package com.ndlanches.nd_lanches_api.service;

import com.ndlanches.nd_lanches_api.entity.Pedido;
import com.ndlanches.nd_lanches_api.entity.Pedido.StatusPedido;
import com.ndlanches.nd_lanches_api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    // Cliente envia o pedido — salva e retorna com ID gerado
    public Pedido salvar(Pedido pedido) {
    pedido.setCriadoEm(LocalDateTime.now());
    pedido.setStatus(StatusPedido.RECEBIDO);   // ← setStatus, não setStatusPedido
    return repository.save(pedido);
    }


    // Admin vê todos os pedidos da loja
    public List<Pedido> listarPorLoja(Long lojaId) {
        return repository.findByLojaIdOrderByCriadoEmDesc(lojaId);
    }

    // Admin vê apenas os pedidos de hoje
    public List<Pedido> listarHoje(Long lojaId) {
        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fim    = inicio.plusDays(1);
        return repository.findByLojaIdAndCriadoEmBetweenOrderByCriadoEmDesc(lojaId, inicio, fim);
    }

    // Admin atualiza o status (RECEBIDO → PREPARO → PRONTO → ENTREGUE)
    public Pedido atualizarStatus(Long id, String novoStatus) {
    return repository.findById(id).map(p -> {
        p.setStatus(StatusPedido.valueOf(novoStatus));
        p.setAtualizadoEm(LocalDateTime.now());
        return repository.save(p);
    }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    // Admin cancela ou remove pedido
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}