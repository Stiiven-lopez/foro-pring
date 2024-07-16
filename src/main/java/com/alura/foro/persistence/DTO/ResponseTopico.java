package com.alura.foro.persistence.DTO;

import java.util.List;

public record ResponseTopico(
		Long id,
		String titulo, 
		String  mensaje,
		ResponsePerfil responsePerfil,
		List<ResponseRespuestas> respuestas
		) {	

}
