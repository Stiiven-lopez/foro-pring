package com.alura.foro.persistence.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.persistence.entity.Respuesta;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
	
}
