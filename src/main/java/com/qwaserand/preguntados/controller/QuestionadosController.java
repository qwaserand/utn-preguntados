package com.qwaserand.preguntados.controller;

import com.qwaserand.preguntados.dto.request.RespuestaAEvaluarDTO;
import com.qwaserand.preguntados.dto.response.RespuestaEvaluadaDTO;
import com.qwaserand.preguntados.entity.Pregunta;
import com.qwaserand.preguntados.service.QuestionadosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questionados")
public class QuestionadosController {

    @Autowired
    QuestionadosService questionadosService;

    /*----------------------------------------------------------------------------*/

    /**
     *
     * @return
     */
    @GetMapping("/pregunta-random")
    public ResponseEntity<?> getPreguntaRandom() {
        Pregunta pregunta = questionadosService.getPreguntaRandom();
        if (pregunta == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pregunta, HttpStatus.OK);
    }

    /**
     *
     * @param respuestaAEvaluar
     * @return
     */
    @PostMapping("/evaluar-respuesta")
    public ResponseEntity<?> evaluarRespuesta(@Valid @RequestBody RespuestaAEvaluarDTO respuestaAEvaluar) {

        RespuestaEvaluadaDTO respuestaEvaluada = new RespuestaEvaluadaDTO();

        if (questionadosService.evaluarRespuesta(respuestaAEvaluar.preguntaId, respuestaAEvaluar.respuestaId)) {
            respuestaEvaluada.setEsCorrecta(true);
        } else {
            respuestaEvaluada.setEsCorrecta(false);
        }
        return new ResponseEntity<>(respuestaEvaluada, HttpStatus.OK);
    }
}
