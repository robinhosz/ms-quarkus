package io.github.robinhosz.deserializer;

import io.github.robinhosz.dto.PedidoRealizadoDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PedidoDeserializer extends ObjectMapperDeserializer<PedidoRealizadoDTO> {


    public PedidoDeserializer(Class<PedidoRealizadoDTO> type) {
        super(PedidoRealizadoDTO.class);
    }
}
