package com.qwaserand.preguntados.dto.request;

import com.qwaserand.preguntados.entity.Respuesta;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class PreguntaRequestDTO {

    @NotEmpty
    private String enunciado;
    public List<Respuesta> opciones;
    public Long categoriaId;

    /*----------------------------------------------------------------------------*/

    public PreguntaRequestDTO() {
    }

    public PreguntaRequestDTO(String enunciado, List<Respuesta> opciones, Long categoriaId) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.categoriaId = categoriaId;
    }

    /*----------------------------------------------------------------------------*/

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Respuesta> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Respuesta> opciones) {
        this.opciones = opciones;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
