package io.github.robinhosz.listener;

import io.github.robinhosz.dto.PedidoRealizadoDTO;
import io.github.robinhosz.dto.PratoPedidoDTO;
import io.github.robinhosz.model.Pedido;
import io.github.robinhosz.model.Prato;
import io.github.robinhosz.model.Restaurante;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.Decimal128;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.ArrayList;

@ApplicationScoped
public class PedidoRealizadoListener {

    @Incoming("pedidos")
    public void lerPedidos(PedidoRealizadoDTO pedidoRealizadoDTO) {
        System.out.println(pedidoRealizadoDTO);

        Pedido p = new Pedido();

        //Em breve irei refatorar isso para utilizar o mapstruct ;)

        p.setCliente(pedidoRealizadoDTO.getCliente());
        pedidoRealizadoDTO.getPratos().forEach(prato -> p.getPratos().add(from(prato)));
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(pedidoRealizadoDTO.getRestaurante().getNome());
        p.setRestaurante(restaurante);
        p.persist();
    }

    public Prato from(PratoPedidoDTO pratoPedidoDTO) {
        Prato p = new Prato();
        p.setDescricao(pratoPedidoDTO.getDescricao());
        p.setPreco(new Decimal128(pratoPedidoDTO.getPreco()));
        p.setNome(pratoPedidoDTO.getNome());

        return p;

    }
}
