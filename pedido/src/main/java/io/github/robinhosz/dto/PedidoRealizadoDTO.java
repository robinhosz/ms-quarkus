package io.github.robinhosz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRealizadoDTO {

    public List<PratoPedidoDTO> pratos;

    public RestauranteDTO restaurante;

    public String cliente;
}