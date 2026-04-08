package com.ndlanches.nd_lanches_api.repository;

import com.ndlanches.nd_lanches_api.entity.Pedido;
import com.ndlanches.nd_lanches_api.entity.Pedido.StatusPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Todos os pedidos de uma loja, mais recentes primeiro
    List<Pedido> findByLojaIdOrderByCriadoEmDesc(Long lojaId);

    // Pedidos de uma loja num intervalo de data (para filtrar "hoje")
    List<Pedido> findByLojaIdAndCriadoEmBetweenOrderByCriadoEmDesc(
        Long lojaId,
        LocalDateTime inicio,
        LocalDateTime fim
    );
    List<Pedido> findByStatusAndCriadoEmBefore(StatusPedido status, LocalDateTime dataLimite);
}