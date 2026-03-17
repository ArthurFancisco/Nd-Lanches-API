package com.ndlanches.nd_lanches_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String emoji; // Ex: 🍔, 🍟, 🥤

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false, length = 20)
    private String categoria; // LANCHE, PORCAO, BEBIDA, SOBREMESA

    @Column(name = "tag_tipo", length = 20)
    private String tagTipo; // destaque, novo, picante

    @Column(name = "tag_texto", length = 50)
    private String tagTexto; // "Mais pedido", "Vegetariano"

    @ElementCollection // Cria uma tabela auxiliar para a lista de strings
    @CollectionTable(name = "produto_removiveis", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "item")
    private List<String> removiveis; // Ex: ["Cebola", "Picles"]

    @Column(nullable = false)
    private Boolean ativo = true;

    private Integer ordem = 0; // Para ordenar o cardápio (ex: 1º o mais caro)

    @ManyToOne // Muitos produtos para uma loja
    @JoinColumn(name = "loja_id", nullable = false)
    private Loja loja;
}