package com.qwaserand.preguntados.dto.response;

import com.qwaserand.preguntados.dto.request.CategoriaRequestDTO;

import java.util.List;

public class CategoriaResponseDTO {

    private List<CategoriaRequestDTO> contenido;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    /*----------------------------------------------------------------------------*/

    public CategoriaResponseDTO() {
    }

    /*----------------------------------------------------------------------------*/

    public List<CategoriaRequestDTO> getContenido() {
        return contenido;
    }

    public void setContenido(List<CategoriaRequestDTO> contenido) {
        this.contenido = contenido;
    }

    public int getNumeroPagina() {
        return pageNo;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.pageNo = numeroPagina;
    }

    public int getMedidaPagina() {
        return pageSize;
    }

    public void setMedidaPagina(int medidaPagina) {
        this.pageSize = medidaPagina;
    }

    public long getTotalElementos() {
        return totalElements;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElements = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPages;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPages = totalPaginas;
    }

    public boolean isUltima() {
        return last;
    }

    public void setUltima(boolean ultima) {
        this.last = ultima;
    }
}
