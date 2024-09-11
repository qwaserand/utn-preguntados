package com.qwaserand.preguntados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pregunta")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private long id;

    @Column(name = "statement", nullable = false)
    private String enunciado;

    /*----------------------------------------------------------------------------*/

    public Pregunta() {
    }

    /**
     * @param id
     * @param enunciado
     * */
    public Pregunta(long id, String enunciado) {
        this.id = id;
        this.enunciado = enunciado;
    }

    /*----------------------------------------------------------------------------*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void agregarRespuesta(Respuesta respuesta) {
    }
}
