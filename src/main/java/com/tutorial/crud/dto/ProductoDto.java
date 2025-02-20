package com.tutorial.crud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductoDto {

    @NotBlank
    private String nombre;

    @Min(0)
    private Float precio;

    public ProductoDto(@NotBlank String nombre, @Min(0)float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }


}
