package io.github.robinhosz.listener;

import io.github.robinhosz.model.Restaurante;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class Listener {

    @Inject
    PgPool pgPool;

    @Incoming("restaurantes")
    public void receberRestaurantes(String json) {
        Jsonb create = JsonbBuilder.create();
        Restaurante restaurante = create.fromJson(json, Restaurante.class);

        System.out.println("------------------");
        System.out.println(json);
        System.out.println("------------------");

        restaurante.persist(pgPool);
    }
}
