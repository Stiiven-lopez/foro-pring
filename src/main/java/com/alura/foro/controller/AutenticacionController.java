package com.alura.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.foro.persistence.DTO.DatosAutenticacionUsuario;
import com.alura.foro.persistence.DTO.DatosJWTToken;
import com.alura.foro.persistence.entity.Usuario;
import com.alura.foro.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/foro")
public class AutenticacionController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity atenticarusuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
		Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.nombreusuario(), datosAutenticacionUsuario.contrasena());
		
		var usuarioAutentidado = authenticationManager.authenticate(authToken);
	    
	    var JWTtoken = tokenService.generarToken((Usuario) usuarioAutentidado.getPrincipal());
		
		return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
		
	}
}
