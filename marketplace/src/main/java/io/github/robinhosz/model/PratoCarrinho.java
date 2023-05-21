package io.github.robinhosz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PratoCarrinho {

    private String usuario;

    private Long prato;
}
