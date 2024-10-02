package com.qwaserand.preguntados.service.impl;

import com.github.sanchezih.aeropuertos.exceptions.custom.BadRequestException;
import com.github.sanchezih.aeropuertos.exceptions.custom.ResourceNotFoundException;
import com.qwaserand.preguntados.dto.request.CategoriaRequestDTO;
import com.qwaserand.preguntados.dto.response.CategoriaResponseDTO;
import com.qwaserand.preguntados.entity.Categoria;
import com.qwaserand.preguntados.repository.CategoriaRepository;
import com.qwaserand.preguntados.service.CategoriaService;
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
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /*----------------------------------------------------------------------------*/

    @Override
    public Categoria create(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = mapCategoriaRequestDTOToCategoria(categoriaRequestDTO);
        categoriaRepository.save(categoria);
        return categoria;
    }

    @Override
    public Categoria getOne(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("id invalido"));

        return categoria;
    }

    @Override
    public CategoriaResponseDTO getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        List<Categoria> listaDeCategorias = categorias.getContent();

        // Si bien aca habria que devolver objetos de tipo Categoria, se
        // opta por devolver objetos de tipo CategoriaRequestDTO para mostrar lo
        // siguiente...
        List<CategoriaRequestDTO> contenido = listaDeCategorias.stream()
                .map(categoria -> mapCategoriaToCategoriaRequestDTO(categoria)).collect(Collectors.toList());

        CategoriaResponseDTO res = new CategoriaResponseDTO();
        res.setContenido(contenido);
        res.setNumeroPagina(categorias.getNumber());
        res.setMedidaPagina(categorias.getSize());
        res.setTotalElementos(categorias.getTotalElements());
        res.setTotalPaginas(categorias.getTotalPages());
        res.setUltima(categorias.isLast());

        return res;
    }

    @Override
    public Categoria update(CategoriaRequestDTO categoriaRequestDTO, Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));

        categoria.setNombre(categoriaRequestDTO.getNombre());
        categoria.setDescripcion(categoriaRequestDTO.getDescripcion());

        categoriaRepository.save(categoria);
        return categoria;
    }

    @Override
    public void delete(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
        categoriaRepository.delete(categoria);

    }

    private CategoriaRequestDTO mapCategoriaToCategoriaRequestDTO(Categoria categoria) {
        CategoriaRequestDTO categoriaDTO = modelMapper.map(categoria, CategoriaRequestDTO.class);
        return categoriaDTO;
    }

    private Categoria mapCategoriaRequestDTOToCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = modelMapper.map(categoriaRequestDTO, Categoria.class);
        return categoria;
    }
}
