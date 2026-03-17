package com.ndlanches.nd_lanches_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndlanches.nd_lanches_api.entity.Loja;

import java.util.Optional;

public interface LojaRepository extends JpaRepository<Loja, Long> {
    Optional<Loja> findBySlug(String slug);
}