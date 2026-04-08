package com.ndlanches.nd_lanches_api.service;

import com.ndlanches.nd_lanches_api.entity.Pedido;
import com.ndlanches.nd_lanches_api.entity.Pedido.StatusPedido;
import com.ndlanches.nd_lanches_api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoAutoStatusService {

    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Executa a cada 5 minutos (300000 ms)
     * Altera status de pedidos que estão há mais de 30 minutos no mesmo estágio.
     */
    @Scheduled(fixedDelay = 300000)
    @Transactional
    public void atualizarStatusAutomaticamente() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime limite = agora.minusMinutes(30);

        // 1. RECEBIDO → PREPARO
        List<Pedido> recebidosAntigos = pedidoRepository.findByStatusAndCriadoEmBefore(
            StatusPedido.RECEBIDO, limite);
        for (Pedido p : recebidosAntigos) {
            p.setStatus(StatusPedido.PREPARO);
            p.setAtualizadoEm(agora);
        }

        // 2. PREPARO → PRONTO
        List<Pedido> preparoAntigos = pedidoRepository.findByStatusAndCriadoEmBefore(StatusPedido.PREPARO, limite);
        for (Pedido p : preparoAntigos) {
            p.setStatus(StatusPedido.PRONTO);
            p.setAtualizadoEm(agora);
            adicionarObservacaoAuto(p, "Auto: PREPARO → PRONTO (30min)");
        }

        // Salva apenas se houver mudanças
        if (!recebidosAntigos.isEmpty()) {
            pedidoRepository.saveAll(recebidosAntigos);
            System.out.println("✅ " + recebidosAntigos.size() + " pedidos evoluíram de RECEBIDO para PREPARO");
        }
        if (!preparoAntigos.isEmpty()) {
            pedidoRepository.saveAll(preparoAntigos);
            System.out.println("✅ " + preparoAntigos.size() + " pedidos evoluíram de PREPARO para PRONTO");
        }
    }

    private void adicionarObservacaoAuto(Pedido p, String mensagem) {
        String obsAtual = p.getObservacao() == null ? "" : p.getObservacao();
        if (!obsAtual.contains(mensagem)) {
            p.setObservacao(obsAtual + (obsAtual.isEmpty() ? "" : " | ") + mensagem);
        }
    }
}