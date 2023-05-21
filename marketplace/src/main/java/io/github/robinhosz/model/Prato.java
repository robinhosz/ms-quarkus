package io.github.robinhosz.model;

import io.github.robinhosz.dto.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import java.util.stream.StreamSupport;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prato {

    private Long id;

    private String nome;

    private String descricao;

    private Restaurante restaurante;

    private BigDecimal preco;


    public static Multi<PratoDTO> findAll(PgPool client, Long idRestaurante) {
        Uni<RowSet<Row>> preparedQuery = client
                .preparedQuery("SELECT * FROM prato where prato.restaurante_id = $1 ORDER BY nome ASC").execute(
                        Tuple.of(idRestaurante));
        return (Multi<PratoDTO>) preparedQuery;
    }

    public static Uni<PratoDTO> findById(PgPool client, Long id) {
        return client.preparedQuery("SELECT * FROM prato WHERE id = $1").execute(Tuple.of(id))
                .map(RowSet::iterator)
                .map(iterator -> iterator.hasNext() ? PratoDTO.from(iterator.next()) : null);
    }
}
