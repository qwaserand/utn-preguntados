package com.qwaserand.preguntados.service;

import com.qwaserand.preguntados.dto.request.PreguntaRequestDTO;
import com.qwaserand.preguntados.dto.response.PreguntaResponseDTO;
import com.qwaserand.preguntados.entity.Pregunta;


import java.util.List;

public interface PreguntaService {
    public Pregunta create(PreguntaRequestDTO preguntaRequestDTO);
    public Pregunta getOne(Long id);
    public PreguntaResponseDTO getAll(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
    public Pregunta update(PreguntaRequestDTO preguntaRequestDTO, Long id);
    public void delete(Long id);
    public List<Pregunta> getAll();
}
