package com.ndlanches.nd_lanches_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "adicional")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String emoji; // Ex: 🥓, 🧀, 🍳

    @Column(nullable = false, length = 100)
    private String nome; // Ex: "Bacon extra", "Queijo duplo"

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco; // Preço adicional cobrado

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;
}