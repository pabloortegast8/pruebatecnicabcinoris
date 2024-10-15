package com.noris.prueba.tec.model;


import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable  // Esto indica que la clase es un componente embebido
public class PhoneModel {

    private String number;
    private String citycode;
    private String countrycode;
}
