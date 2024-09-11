package com.qwaserand.preguntados.dto.request;

import jakarta.validation.constraints.NotEmpty;

public class PreguntaRequestDTO {

    @NotEmpty
    private String enunciado;

    /*----------------------------------------------------------------------------*/

    public PreguntaRequestDTO() {
    }

    /*----------------------------------------------------------------------------*/

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
