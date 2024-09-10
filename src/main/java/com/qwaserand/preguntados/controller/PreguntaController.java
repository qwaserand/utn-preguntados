package com.qwaserand.preguntados.controller;

import com.qwaserand.preguntados.dto.request.PreguntaRequestDTO;
import com.qwaserand.preguntados.entity.Pregunta;
import com.qwaserand.preguntados.service.PreguntaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/preguntados")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Operation(summary = "Obtener todas las preguntas existentes")
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<Pregunta> preguntas = preguntaService.getAll(pageable);
        return new ResponseEntity<>(preguntas, HttpStatus.OK);
    }

    @Operation(summary = "Obtener una pregunta")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long idPregunta) {
        Pregunta pregunta = preguntaService.getOne(idPregunta);
            return new ResponseEntity<>(pregunta, HttpStatus.OK);
    }

    @Operation(summary = "Crear una pregunta") //Anotacion de Swagger, proporciona metadatos sobre un endpoint
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PreguntaRequestDTO preguntaRequestDTO) {
            Pregunta preguntaCreada = preguntaService.create(preguntaRequestDTO);
            return new ResponseEntity<>(preguntaCreada, HttpStatus.CREATED);
    }

    @Operation(summary = "Editar una pregunta")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody PreguntaRequestDTO preguntaRequestDTO,
                                    @PathVariable(name = "id") Long idPregunta) {
            Pregunta pregunta = preguntaService.update(preguntaRequestDTO, idPregunta);
            return new ResponseEntity<>(pregunta, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una pregunta")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long idPregunta) {
            preguntaService.delete(idPregunta);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
