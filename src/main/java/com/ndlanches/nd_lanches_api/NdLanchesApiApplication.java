package com.ndlanches.nd_lanches_api;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ndlanches.nd_lanches_api.entity.Loja;
import com.ndlanches.nd_lanches_api.entity.Produto;
import com.ndlanches.nd_lanches_api.repository.LojaRepository;
import com.ndlanches.nd_lanches_api.repository.ProdutoRepository;

@SpringBootApplication
public class NdLanchesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NdLanchesApiApplication.class, args);
	}

	@Bean
CommandLineRunner initDatabase(LojaRepository lojaRepo, ProdutoRepository prodRepo) {
    return args -> {
        if (lojaRepo.findBySlug("nd-lanches").isEmpty()) {
            Loja loja = new Loja();
            loja.setNome("ND Lanches");
            loja.setSlug("nd-lanches");
            loja.setWhatsapp("5517999999999");
            loja.setAberto(true);
            lojaRepo.save(loja);

            // Criando um lanche de teste
            Produto p1 = new Produto();
            p1.setEmoji("🍔");
            p1.setNome("ND Especial");
            p1.setDescricao("Pão, carne 180g, queijo, bacon e molho secreto.");
            p1.setPreco(new java.math.BigDecimal("25.00"));
            p1.setCategoria("LANCHE");
            p1.setTagTipo("destaque");
            p1.setTagTexto("O mais pedido");
            p1.setAtivo(true);
            p1.setLoja(loja); // Vincula o lanche à loja

            prodRepo.save(p1);
            System.out.println("✅ Loja e Produto de teste criados no Postgres!");
        }
    };
}
}