package com.alura.foro.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alura.foro.persistence.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;

@Service
public class TokenService {

	@Value("${api.security.secret}")
	private String apiSecret;
	
	public String generarToken(Usuario usuario) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    return JWT.create()
		        .withIssuer("Foro Alura")
		        .withSubject(usuario.getNombreusuario())
		        .withClaim("id", usuario.getId())
		        .withExpiresAt(generarFechaExpiracion())
		        .sign(algorithm);	
		    
		} catch (JWTCreationException exception){
		    throw new RuntimeException();
		}
	}
	public String getSubject(String token) {
		
		if(token == null) {
			throw new RuntimeException("Token invalido");
		}
		DecodedJWT verifier = null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    verifier = JWT.require(algorithm)
		        .withIssuer("Foro Alura")
		        .build().verify(token);
		        
		} catch (JWTVerificationException exception){
		    System.out.println(exception.toString());
		}
		if ( verifier.getSubject() == null) {
			throw new RuntimeException("Verifier invalido");
		}
		return verifier.getSubject();
	}
	
	
	private Instant generarFechaExpiracion() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
	}
}
