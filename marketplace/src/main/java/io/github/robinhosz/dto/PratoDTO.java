package io.github.robinhosz.dto;

import io.github.robinhosz.model.Restaurante;

import java.math.BigDecimal;
import io.vertx.mutiny.sqlclient.Row;


public class PratoDTO {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    public static PratoDTO from(Row row) {
        PratoDTO dto = new PratoDTO();
        dto.descricao = row.getString("descricao");
        dto.nome = row.getString("nome");
        dto.id = row.getLong("id");
        dto.preco = row.getBigDecimal("preco");
        return dto;
    }
}
