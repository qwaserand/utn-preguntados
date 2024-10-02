package com.qwaserand.preguntados.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoriaRequestDTO {

    @NotEmpty(message = "El nombre de la categoria no debe ser vacio o nulo")
    private String nombre;

    @NotEmpty
    @Size(min = 10, message = "La descripcion de la categoria debe tener al menos 10 caracteres")
    private String descripcion;

    /*----------------------------------------------------------------------------*/

    public CategoriaRequestDTO() {
    }

    /*----------------------------------------------------------------------------*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
