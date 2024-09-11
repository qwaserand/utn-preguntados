package com.qwaserand.preguntados.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "respuesta", uniqueConstraints = { @UniqueConstraint(columnNames = { "texto", "pregunta_id" }) })
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "respuesta_id")
	// @JsonIgnore
	private Long respuestaId;

	private String texto;

	@Column(name = "es_correcta")
	private boolean esCorrecta;

	@ManyToOne
	@JoinColumn(name = "pregunta_id", referencedColumnName = "pregunta_id")
	@JsonIgnore
	private Pregunta pregunta;

	public Long getRespuestaId() {
		return respuestaId;
	}

	public void setRespuestaId(Long respuestaId) {
		this.respuestaId = respuestaId;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
		this.pregunta.agregarRespuesta(this); // relacion bidireccional
	}
}
