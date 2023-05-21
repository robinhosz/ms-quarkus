package io.github.robinhosz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prato {

    private String nome;

    private String descricao;

    private Decimal128 preco;
}
