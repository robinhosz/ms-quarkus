package io.github.robinhosz.resources;

import io.github.robinhosz.dto.PedidoRealizadoDTO;
import io.github.robinhosz.dto.PratoDTO;
import io.github.robinhosz.dto.RestauranteDTO;
import io.github.robinhosz.model.Prato;
import io.github.robinhosz.model.Restaurante;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @Inject
    PgPool pgPool;

    @Inject
    @Channel("pedidos")
    Emitter<PedidoRealizadoDTO> pedidoRealizadoDTOEmitter;


    @GET
    @Path("/{restauranteId}")
    public Multi<PratoDTO> buscarPratos(Long restauranteId) {
        return Prato.findAll(pgPool, restauranteId);
    }


    //É apenas uma POC de como realiza operações de pub/sub no kafka pelo quarkus, nada muito complexo, apenas para servir com exemplo
    @POST
    public void realizaPedido(PedidoRealizadoDTO pedidoRealizadoDTO) {

        pedidoRealizadoDTOEmitter.send(pedidoRealizadoDTO);

    }

}
