package com.ndlanches.nd_lanches_api.DTO;

import com.ndlanches.nd_lanches_api.entity.Adicional;
import com.ndlanches.nd_lanches_api.entity.Banner;
import com.ndlanches.nd_lanches_api.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardapioDTO {
    private List<Produto> produtos;
    private List<Adicional> adicionais;
    private List<Banner> banners;
}