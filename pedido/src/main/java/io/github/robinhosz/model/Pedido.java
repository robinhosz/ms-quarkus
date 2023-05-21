package io.github.robinhosz.model;

import com.mongodb.client.model.Collation;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "pedidos", database = "pedido")
public class Pedido extends PanacheMongoEntity {

    private String cliente;

    private List<Prato> pratos;

    private Restaurante restaurante;

    private String entregador;

    private Localizacao localizacaoEntregador;

}
