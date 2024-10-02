package com.qwaserand.preguntados.service.impl;

import com.github.sanchezih.aeropuertos.exceptions.custom.BadRequestException;
import com.github.sanchezih.aeropuertos.exceptions.custom.ResourceNotFoundException;
import com.qwaserand.preguntados.dto.request.PreguntaRequestDTO;
import com.qwaserand.preguntados.dto.response.PreguntaResponseDTO;
import com.qwaserand.preguntados.entity.Categoria;
import com.qwaserand.preguntados.entity.Pregunta;
import com.qwaserand.preguntados.entity.Respuesta;
import com.qwaserand.preguntados.repository.PreguntaRepository;
import com.qwaserand.preguntados.service.CategoriaService;
import com.qwaserand.preguntados.service.PreguntaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private CategoriaService categoriaService;

    /*----------------------------------------------------------------------------*/

    @Override
    public Pregunta create(PreguntaRequestDTO preguntaRequestDTO) {

        Pregunta pregunta = new Pregunta();
        pregunta.setEnunciado(preguntaRequestDTO.getEnunciado());

        Categoria categoria = categoriaService.getOne(preguntaRequestDTO.getCategoriaId());
        pregunta.setCategoria(categoria);

        for (Respuesta respuesta : preguntaRequestDTO.getOpciones()) { // por cada respuesta, agregarla a la pregunta
            respuesta.setPregunta(pregunta);
        }

        preguntaRepository.save(pregunta);
        return pregunta;
    }

    @Override
    public Pregunta getOne(Long id) {
        Pregunta pregunta = preguntaRepository.findById(id).orElseThrow(() -> new BadRequestException("id invalido"));
        return pregunta;
    }

    @Override
    public PreguntaResponseDTO getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Pregunta> preguntas = preguntaRepository.findAll(pageable);
        List<Pregunta> listaDeCategorias = preguntas.getContent();

        // Si bien aca habria que devolver objetos de tipo Pregunta, se
        // opta por devolver objetos de tipo PreguntaRequestDTO para mostrar lo
        // siguiente...
        List<PreguntaRequestDTO> contenido = listaDeCategorias.stream()
                .map(categoria -> mapPreguntaToPreguntaRequestDTO(categoria)).collect(Collectors.toList());

        PreguntaResponseDTO res = new PreguntaResponseDTO();
        res.setContenido(contenido);
        res.setNumeroPagina(preguntas.getNumber());
        res.setMedidaPagina(preguntas.getSize());
        res.setTotalElementos(preguntas.getTotalElements());
        res.setTotalPaginas(preguntas.getTotalPages());
        res.setUltima(preguntas.isLast());

        return res;
    }

    @Override
    public Pregunta update(PreguntaRequestDTO preguntaRequestDTO, Long id) {
        Pregunta pregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta", "id", id));

        pregunta.setEnunciado(preguntaRequestDTO.getEnunciado());

        preguntaRepository.save(pregunta);
        return pregunta;
    }

    @Override
    public void delete(Long id) {
        Pregunta categoria = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta", "id", id));
        preguntaRepository.delete(categoria);

    }

    private PreguntaRequestDTO mapPreguntaToPreguntaRequestDTO(Pregunta categoria) {
        PreguntaRequestDTO preguntaDTO = modelMapper.map(categoria, PreguntaRequestDTO.class);
        return preguntaDTO;
    }

    private Pregunta mapPreguntaRequestDTOToPregunta(PreguntaRequestDTO categoriaRequestDTO) {
        Pregunta pregunta = modelMapper.map(categoriaRequestDTO, Pregunta.class);
        return pregunta;
    }

    public List<Pregunta> getAll() {
        return preguntaRepository.findAll();
    }
}
