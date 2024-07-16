package com.alura.foro.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.alura.foro.exceptions.ResourceNotFoundException;
import com.alura.foro.persistence.entity.Usuario;
import com.alura.foro.persistence.repository.UsuarioRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UsuarioService {
	
	
	private UsuarioRepository usuarioRepository;

	
	public Usuario saveUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Usuario", "Id", id));
		
	}

	public Usuario updateUsuario(Usuario usuario, long id) {
			
			// we need to check whether Usuario with given id is exist in DB or not
		Usuario existingUsuario = usuarioRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Usuario", "Id", id)); 
			
			existingUsuario.setNombreusuario(usuario.getNombreusuario());
			existingUsuario.setContrasena(usuario.getContrasena());
			// save existing Usuario to DB
			usuarioRepository.save(existingUsuario);
			return existingUsuario;
		}
	
	public void deleteUsuario(long id) {
			
			// check whether a Usuario exist in a DB or not
			usuarioRepository.findById(id).orElseThrow(() -> 
									new ResourceNotFoundException("Usuario", "Id", id));
			usuarioRepository.deleteById(id);
		}
}
