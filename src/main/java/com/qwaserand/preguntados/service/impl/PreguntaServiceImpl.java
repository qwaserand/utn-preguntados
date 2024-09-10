package com.qwaserand.preguntados.service.impl;

import com.github.sanchezih.aeropuertos.exceptions.custom.ResourceNotFoundException;
import com.qwaserand.preguntados.dto.request.PreguntaRequestDTO;
import com.qwaserand.preguntados.entity.Pregunta;
import com.qwaserand.preguntados.repository.PreguntaRepository;
import com.qwaserand.preguntados.service.PreguntaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public Pregunta create(PreguntaRequestDTO preguntaRequestDTO) {
        Pregunta preguntaACrear = mapPreguntaRequestDTOtoPregunta(preguntaRequestDTO);
        Pregunta preguntaCreada = preguntaRepository.save(preguntaACrear);
        return preguntaCreada;
    }

    @Override
    public Pregunta getOne(Long id) {
        Pregunta pregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta", "id", id));
        return pregunta;
    }

    @Override
    public Page<Pregunta> getAll(Pageable pageable) {
        return preguntaRepository.findAll(pageable);
    }

    @Override
    public Pregunta update(PreguntaRequestDTO preguntaRequestDTO, Long id) {

        Pregunta preguntaAActualizar = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta", "id", id));

        preguntaAActualizar.setEnunciado(preguntaRequestDTO.getEnunciado());

        Pregunta preguntaActualizada = preguntaRepository.save(preguntaAActualizar);
        return preguntaActualizada;
    }

    @Override
    public void delete(long id) {
        Pregunta preguntaAEliminar = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta", "id", id));

        preguntaRepository.delete(preguntaAEliminar);
    }

    private Pregunta mapPreguntaRequestDTOtoPregunta(PreguntaRequestDTO preguntaRequestDTO) {
        Pregunta pregunta = modelMapper.map(preguntaRequestDTO, Pregunta.class);
        return pregunta;
    }
}
