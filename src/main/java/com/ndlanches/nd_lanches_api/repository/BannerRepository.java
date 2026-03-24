package com.ndlanches.nd_lanches_api.repository;

import com.ndlanches.nd_lanches_api.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    // Todos os banners da loja (admin)
    List<Banner> findByLojaId(Long lojaId);

    // Apenas banners ativos (cardápio público)
    List<Banner> findByLojaIdAndAtivoTrue(Long lojaId);
}