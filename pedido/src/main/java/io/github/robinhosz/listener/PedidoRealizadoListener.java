package io.github.robinhosz.listener;

import io.github.robinhosz.dto.PedidoRealizadoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PedidoRealizadoListener {

    @Incoming("pedidos")
    public void lerPedidos(PedidoRealizadoDTO pedidoRealizadoDTO) {
        System.out.println(pedidoRealizadoDTO);
    }
}
