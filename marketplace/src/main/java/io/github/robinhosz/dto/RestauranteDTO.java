package io.github.robinhosz.dto;

import io.github.robinhosz.model.Localizacao;
import lombok.Data;

@Data
public class RestauranteDTO {

    private Long id;
    private String nome;

    private Localizacao localizacao;
}
