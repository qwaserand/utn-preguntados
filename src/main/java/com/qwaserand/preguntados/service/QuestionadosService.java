package com.qwaserand.preguntados.service;

import com.qwaserand.preguntados.entity.Pregunta;

public interface QuestionadosService {
    Pregunta getPreguntaRandom();

    boolean evaluarRespuesta(Long preguntaId, Long respuestaId);
}
