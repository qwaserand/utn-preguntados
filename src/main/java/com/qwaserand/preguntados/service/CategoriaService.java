package com.qwaserand.preguntados.service;

import com.qwaserand.preguntados.dto.request.CategoriaRequestDTO;
import com.qwaserand.preguntados.dto.response.CategoriaResponseDTO;
import com.qwaserand.preguntados.entity.Categoria;

public interface CategoriaService {
    Categoria create(CategoriaRequestDTO categoriaRequestDTO);

    Categoria getOne(Long id);

    CategoriaResponseDTO getAll(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

    Categoria update(CategoriaRequestDTO categoriaRequestDTO, Long id);

    void delete(Long id);
}
