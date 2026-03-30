package com.ndlanches.nd_lanches_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loja")
@Data // Magia do Lombok: cria Getters, Setters, toString, equals e hashCode
@NoArgsConstructor // Cria um construtor vazio (exigência do JPA)
@AllArgsConstructor // Cria um construtor com todos os campos
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String slug;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 20)
    private String whatsapp;

    @Column(nullable = false)
    private Boolean aberto = true; // Por padrão, a loja nasce aberta

    @Column(name = "mensagem_fechado", length = 200)
    private String mensagemFechado = "Voltamos em breve!";

    // NOVO CAMPO: horário de funcionamento (texto livre)
    @Column(name = "horario_funcionamento", length = 200)
    private String horarioFuncionamento = "Segunda a sábado, 18h às 23h";
}