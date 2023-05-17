package io.github.robinhosz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class AdicionarRestauranteDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String proprietario;

    @CNPJ
    private String cnpj;

    @Size(min = 3, max = 30)
    private String nome;
    private LocalizacaoDTO localizacaoDTo;

}
