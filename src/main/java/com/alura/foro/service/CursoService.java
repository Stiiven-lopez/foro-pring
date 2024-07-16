package com.alura.foro.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.alura.foro.exceptions.ResourceNotFoundException;
import com.alura.foro.persistence.entity.Curso;
import com.alura.foro.persistence.repository.CursoRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CursoService {
	
	
	private CursoRepository cursoRepository;
	
	
	public Curso saveCurso(Curso curso) {
		return cursoRepository.save(curso);
	}
	
	public List<Curso> getAllCursos() {
		return cursoRepository.findAll();
	}
	
	public Curso getCursoById(long id) {
//		Optional<Curso> curso = cursoRepository.findById(id);
//		if(curso.isPresent()) {
//			return curso.get();
//		}else {
//			throw new ResourceNotFoundException("curso", "Id", id);
//		}
		return cursoRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Curso", "Id", id));
		
	}

	public Curso updateCurso(Curso curso, long id) {
			
			// we need to check whether curso with given id is exist in DB or not
			Curso existingCurso = cursoRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Curso", "Id", id)); 
			
			existingCurso.setNombre(curso.getNombre());
			existingCurso.setCategoria(curso.getCategoria());
			// save existing curso to DB
			cursoRepository.save(existingCurso);
			return existingCurso;
		}
	
	public void deleteCurso(long id) {
			
			// check whether a employee exist in a DB or not
			cursoRepository.findById(id).orElseThrow(() -> 
									new ResourceNotFoundException("Curso", "Id", id));
			cursoRepository.deleteById(id);
		}
}
