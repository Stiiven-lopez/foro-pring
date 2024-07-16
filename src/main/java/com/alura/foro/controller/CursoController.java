package com.alura.foro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.foro.persistence.entity.Curso;
import com.alura.foro.service.CursoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/foro")
@AllArgsConstructor
public class CursoController {
		
	private CursoService cursoService;
	

	/*********************************** 
	 * REST API POST
	 * Registrar nuevo Curso  
	 * END POINT :
	 * http://localhost:8080/foro/curso
	*************************************/
	@PostMapping("/curso")
	public ResponseEntity<Curso> saveCurso(@RequestBody Curso curso){
		return new ResponseEntity<Curso>(cursoService.saveCurso(curso), HttpStatus.CREATED);
	}
	
	/************************************** 
	 * REST API GET
	 * Obtener todos los Curso 
	 * END POINT :
	 * http://localhost:8080/foro/cursos
	***************************************/
	@GetMapping("/cursos")
	public List<Curso> getAllCursos(){
		return cursoService.getAllCursos();
	}
	
	/******************************************* 
	 * REST API GET
	 * Obtener un Curso pasando el id 
	 * END POINT :
	 * http://localhost:8080/foro/curso/1
	********************************************/
	@GetMapping("/curso/{id}")
	public ResponseEntity<Curso> getCursoById(@PathVariable("id") long id){
		return new ResponseEntity<Curso>(cursoService.getCursoById(id), HttpStatus.OK);
	}
	
	/************************************************
	 * REST API PUT 
	 * Actualizar un Curso por id
	 * END POINT :
	 * http://localhost:8080/foro/curso/1
	*************************************************/
	@PutMapping("/curso/{id}")
	public ResponseEntity<Curso> updateCurso(@PathVariable("id") long id
												  ,@RequestBody Curso curso){
		return new ResponseEntity<Curso>(cursoService.updateCurso(curso, id), HttpStatus.OK);
	}
	
	/************************************************
	 * REST API DELETE 
	 * borrar un Curso por id
	 * END POINT :
	 * http://localhost:8080/foro/curso/1
	*************************************************/
	@DeleteMapping("/curso/{id}")
	public ResponseEntity<String> deleteCurso(@PathVariable("id") long id){
		
		// delete curso from DB
		cursoService.deleteCurso(id);
		
		return new ResponseEntity<String>("Curso deleted successfully!.", HttpStatus.OK);
	}
	
	
}
