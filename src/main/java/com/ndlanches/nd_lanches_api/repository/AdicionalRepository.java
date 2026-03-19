package com.ndlanches.nd_lanches_api.repository;

import com.ndlanches.nd_lanches_api.entity.Adicional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdicionalRepository extends JpaRepository<Adicional, Long> {

    // Busca apenas adicionais ativos de uma loja (rota pública do cardápio)
    List<Adicional> findByLojaIdAndAtivoTrue(Long lojaId);
}