package io.github.robinhosz.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Localizacao {

    private Double latitude;
    private Double longitude;
}
