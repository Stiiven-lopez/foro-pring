package com.alura.foro.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.alura.foro.exceptions.ResourceNotFoundException;
import com.alura.foro.persistence.entity.Perfil;
import com.alura.foro.persistence.entity.Usuario;
import com.alura.foro.persistence.repository.PerfilRepository;
import com.alura.foro.persistence.repository.UsuarioRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class PerfilService {
	
	
	private UsuarioRepository usuarioRepository;
	private PerfilRepository perfilRepository;

	
	public Perfil savePerfil(Perfil perfil) {
		Usuario usuario = usuarioRepository.save(perfil.getUsuario());
		perfil.setUsuario(usuario);
		return perfilRepository.save(perfil);
	}
	
	public List<Perfil> getAllPerfiles() {
		return perfilRepository.findAll();
	}
	
	public Perfil getPerfilById(long id) {
		return perfilRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Perfil", "Id", id));
		
	}

	public Perfil updatePerfil(Perfil perfil, long id) {
			
			// we need to check whether Perfil with given id is exist in DB or not
		Perfil existingPerfil = perfilRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Perfil", "Id", id)); 
			
		existingPerfil.setNombrecompleto(perfil.getNombrecompleto());
		existingPerfil.setEmail(perfil.getEmail());
		
		// save existing Perfil to DB
		perfilRepository.save(existingPerfil);
			return existingPerfil;
		}
	
	public void deletePerfil(long id) {
			
			// check whether a Perfil exist in a DB or not
			perfilRepository.findById(id).orElseThrow(() -> 
										new ResourceNotFoundException("Perfil", "Id", id));
			perfilRepository.deleteById(id);
		}
}
