package com.ndlanches.nd_lanches_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quando o pedido foi feito
    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    // Nome do cliente (opcional — pode ser coletado no frontend)
    @Column(name = "nome_cliente", length = 100)
    private String nomeCliente;

    // Valor total calculado pelo frontend e confirmado pela API
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    // Status do pedido
   @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPedido status = StatusPedido.RECEBIDO;

    // Itens do pedido em JSON (mais simples que criar outra tabela agora)
    @Column(name = "itens_json", columnDefinition = "TEXT", nullable = false)
    private String itensJson; // "[{nome:'X-Burguer', qtd:2, preco:25.00}]"

    // Observação geral do pedido
    @Column(columnDefinition = "TEXT")
    private String observacao;

    // Qual loja recebeu o pedido
    @ManyToOne
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

   public enum StatusPedido {
    RECEBIDO, PREPARO, PRONTO, ENTREGUE, CANCELADO
}

}
