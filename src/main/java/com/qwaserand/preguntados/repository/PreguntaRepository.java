package com.qwaserand.preguntados.repository;

import com.qwaserand.preguntados.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

}
