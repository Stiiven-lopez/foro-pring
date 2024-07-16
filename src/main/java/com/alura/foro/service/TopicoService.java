package com.alura.foro.service;


import java.util.List;


import org.springframework.stereotype.Service;

import com.alura.foro.exceptions.ResourceNotFoundException;
import com.alura.foro.persistence.entity.Curso;
import com.alura.foro.persistence.entity.Perfil;
import com.alura.foro.persistence.entity.Topico;
import com.alura.foro.persistence.repository.CursoRepository;
import com.alura.foro.persistence.repository.PerfilRepository;
import com.alura.foro.persistence.repository.TopicoRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TopicoService {
	
	
	private TopicoRepository topicoRepository;
	private CursoRepository cursoRepository; 
	private PerfilRepository perfilRepository; 

	
	public Topico saveTopico(Topico topico) {
		return topicoRepository.save(topico);
	}
	
	public List<Topico> getAllTopicos() {
		return topicoRepository.findAll();
	}
	
	public Topico getTopicoById(long id) {
		return topicoRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Topico", "Id", id));
		
	}

	public Topico getRespuestasTopicoById(long id) {
		return topicoRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Topico", "Id", id));
		
	}
	
	public Topico updateTopico(Topico topico, long id) {
			
			// we need to check whether Topico with given id is exist in DB or not
		Topico existingTopico = topicoRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Topico", "Id", id)); 
			
		
			existingTopico.setTitulo(topico.getTitulo());
			existingTopico.setMensaje(topico.getMensaje());
			existingTopico.setPerfil(topico.getPerfil());
			
			Perfil perfil = perfilRepository.
					getReferenceById(topico.getPerfil().getId());
			
			existingTopico.setPerfil(perfil);
						
			Curso curso = cursoRepository.
					getReferenceById(topico.getCurso().getId());
			
			existingTopico.setCurso(curso);
			
			//List<Respuesta> respuestas = new ArrayList<>();
			
			// save existing Topico to DB
			topicoRepository.save(existingTopico);
			return existingTopico;
		}
	
	public void deleteTopico(long id) {
			
			// check whether a employee exist in a DB or not
		topicoRepository.findById(id).orElseThrow(() -> 
									new ResourceNotFoundException("Topico", "Id", id));
		topicoRepository.deleteById(id);
		}
}
