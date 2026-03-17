package com.ndlanches.nd_lanches_api.repository;

import com.ndlanches.nd_lanches_api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByLojaIdAndAtivoTrueOrderByOrdemAsc(Long lojaId);
}