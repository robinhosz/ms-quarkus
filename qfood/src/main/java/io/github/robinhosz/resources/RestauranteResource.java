package io.github.robinhosz.resources;

import io.github.robinhosz.dto.AdicionarRestauranteDTO;
import io.github.robinhosz.Prato;
import io.github.robinhosz.Restaurante;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.List;
import java.util.Optional;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
//@SecurityRequirement(name = "ifood-oauth")
public class RestauranteResource {

 /*   @Inject
    private ModelMapper mapper;
*/
    @Inject
    @Channel("restaurantes")
    Emitter<String> emitter;

    @Inject
    JsonWebToken jwt;

    @Inject
    @Claim(standard = Claims.sub)
    String sub;

    @GET
    @Counted(name = "Quantidade buscas restaurantes")
    @SimplyTimed(name = "Tempo simples de busca")
    @Timed(name = "Tempo completo de busca")
    //@Gauge()
    public List<Restaurante> listaTodosOsRestaurantes() {
        return Restaurante.listAll();
    }

    @GET
    @Path("/{idRestaurante}/pratos")
    public List<Restaurante> listaTodosOsRestaurantes(@PathParam("idRestaurante") Long id) {
        Optional<Restaurante> restauranteOptional = Restaurante.findByIdOptional(id);

        if(!restauranteOptional.isPresent()) {
            throw new NotFoundException();
        }

        return Restaurante.list("restaurante", restauranteOptional.get());
    }

    @POST
    @Transactional
    public Response adicionarRestaurante(@Valid AdicionarRestauranteDTO dto) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.getNome());
        restaurante.setCnpj(dto.getCnpj());
        restaurante.setProprietario(sub);


        restaurante.persist();

        Jsonb create = JsonbBuilder.create();
        String json = create.toJson(restaurante);

        emitter.send(json);

        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    public Response adicionarRestaurante(@PathParam("idRestaurante") Long id, Prato obj) {
        Optional<Restaurante> restauranteOptional = Restaurante.findByIdOptional(id);

        if(!restauranteOptional.isPresent()) {
            throw new NotFoundException();
        }

        Prato prato = new Prato();
        prato.setNome(obj.getNome());
        prato.setDescricao(obj.getDescricao());

        prato.setPreco(obj.getPreco());
        prato.setRestaurante(restauranteOptional.get());
        prato.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    public void atualizaRestaurante(@PathParam("idRestaurante") Long idRestaurante, Long id, Prato obj) {
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(id);

        if(!restaurante.isPresent()) {
            throw new NotFoundException();
        }

        Optional<Prato> pratoOp = Prato.findByIdOptional(id);

        if(pratoOp.isPresent()) {
            throw new NotFoundException("Prato existe");
        }

        Prato prato = new Prato();
        prato.setPreco(obj.getPreco());

        prato.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizaRestaurante(@PathParam("id") Long id, Restaurante dto) {
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(id);

        if(!restaurante.isPresent()) {
            throw new NotFoundException();
        }

        Restaurante restauranteEntity = restaurante.get();

        restauranteEntity.setNome(dto.getNome());

        restauranteEntity.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletaRestaurante(@PathParam("id") Long id) {
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(id);


        restaurante.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        });
    }

}
