package com.ndlanches.nd_lanches_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "banner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo; // Ex: "🔥 Promoção de hoje!"

    @Column(columnDefinition = "TEXT")
    private String descricao; // Ex: "X-Burguer por R$ 15,00 até meia-noite!"

    // Cor do banner: "amarelo", "verde", "vermelho", "azul"
    @Column(length = 20)
    private String cor = "amarelo";

    // Emoji decorativo opcional
    @Column(length = 10)
    private String emoji = "🔥";

    // Liga/desliga o banner — só aparece se ativo = true
    @Column(nullable = false)
    private Boolean ativo = false;

    @ManyToOne
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;
}