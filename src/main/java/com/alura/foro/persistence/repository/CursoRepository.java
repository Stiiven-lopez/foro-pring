package com.alura.foro.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.persistence.entity.Curso;


public interface CursoRepository extends JpaRepository<Curso, Long> {
	

}
