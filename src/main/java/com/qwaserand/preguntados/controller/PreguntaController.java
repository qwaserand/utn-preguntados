package com.qwaserand.preguntados.controller;

import com.qwaserand.preguntados.dto.request.PreguntaRequestDTO;
import com.qwaserand.preguntados.dto.response.PreguntaResponseDTO;
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

    private static final String NUMERO_DE_PAGINA_POR_DEFECTO = "0";
    private static final String MEDIDA_DE_PAGINA_POR_DEFECTO = "10";
    private static final String ORDENAR_POR_DEFECTO = "id";
    private static final String ORDENAR_DIRECCION_POR_DEFECTO = "asc";

    @Autowired
    private PreguntaService preguntaService;

    /*----------------------------------------------------------------------------*/
    @Operation(summary = "Crear una pregunta")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PreguntaRequestDTO preguntaRequestDTO) {
        Pregunta pregunta = preguntaService.create(preguntaRequestDTO);
        return new ResponseEntity<>(pregunta, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una pregunta")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long preguntaId) {
        Pregunta pregunta = preguntaService.getOne(preguntaId);
        return ResponseEntity.ok(pregunta);
    }

    @Operation(summary = "Obtiene todas las preguntas existentes al momento")
    @GetMapping
    public PreguntaResponseDTO getAll(

            @RequestParam(value = "pageSize", defaultValue = MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "pageNo", defaultValue = NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "sortBy", defaultValue = ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        return preguntaService.getAll(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }

    @Operation(summary = "Eliminar una pregunta")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        preguntaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar una pregunta")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody PreguntaRequestDTO preguntaServiceRequestDTO,
                                    @PathVariable(name = "id") Long idPregunta) {
        Pregunta res = preguntaService.update(preguntaServiceRequestDTO, idPregunta);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }
}
