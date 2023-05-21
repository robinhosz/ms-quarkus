package io.github.robinhosz.dto;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PratoPedidoDTO {

    public String nome;

    public String descricao;

    public BigDecimal preco;

}
