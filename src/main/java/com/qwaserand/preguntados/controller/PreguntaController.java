package com.qwaserand.preguntados.controller;

import com.qwaserand.preguntados.entity.Pregunta;
import com.qwaserand.preguntados.repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/preguntados")
public class PreguntaController {

    @Autowired
    PreguntaRepository preguntaRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Pregunta> preguntas = preguntaRepository.findAll();

        String result = preguntas.stream()
                .map(Pregunta::getEnunciado)
                .collect(Collectors.joining("\n"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        Optional<Pregunta> preguntaOptional = preguntaRepository.findById(id);

        if (preguntaOptional.isPresent()) {
            return new ResponseEntity<>(preguntaOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pregunta pregunta) {
        try {
            Pregunta preguntaCreada = preguntaRepository.save(new Pregunta(
                    pregunta.getId(),
                    pregunta.getEnunciado()
                )
            );

            return new ResponseEntity<>(preguntaCreada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Pregunta> update(@PathVariable("id") Long id, @RequestBody Pregunta pregunta) {

        Optional<Pregunta> preguntaOptional = preguntaRepository.findById(id);

        if (preguntaOptional.isPresent()) {

            Pregunta updatedPregunta = preguntaOptional.get();

            updatedPregunta.setEnunciado(pregunta.getEnunciado());

            return new ResponseEntity<>(preguntaRepository.save(updatedPregunta), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        try{
            preguntaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
