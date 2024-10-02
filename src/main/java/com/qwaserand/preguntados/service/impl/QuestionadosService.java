package com.qwaserand.preguntados.service.impl;

import com.qwaserand.preguntados.entity.Pregunta;
import com.qwaserand.preguntados.entity.Respuesta;
import com.qwaserand.preguntados.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

public class QuestionadosService implements com.qwaserand.preguntados.service.QuestionadosService {

    @Autowired
    PreguntaService preguntaService;

    @Override
    public Pregunta getPreguntaRandom() {
        Pregunta pregunta = null;
        List<Pregunta> preguntas = preguntaService.getAll();

        if (!preguntas.isEmpty()) {
            Random random = new Random();
            pregunta = preguntas.get(random.nextInt(preguntas.size()));
        }

        return pregunta;
    }

    @Override
    public boolean evaluarRespuesta(Long preguntaId, Long respuestaId) {
        Pregunta pregunta = preguntaService.getOne(preguntaId);

        for (Respuesta respuesta : pregunta.getOpciones()) {
            if (respuesta.getRespuestaId() == respuestaId) {
                return respuesta.isEsCorrecta();
            }
        }
        return false;
    }
}
