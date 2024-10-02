package com.qwaserand.preguntados.dto.request;

import jakarta.validation.constraints.NotNull;

public class RespuestaAEvaluarDTO {

    @NotNull
    public Long respuestaId;

    @NotNull
    public Long preguntaId;

    public RespuestaAEvaluarDTO(Long respuestaId, Long preguntaId) {
        this.respuestaId = respuestaId;
        this.preguntaId = preguntaId;
    }
}
