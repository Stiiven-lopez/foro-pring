package com.alura.foro.persistence.DTO;

public record ResponseRespuestas(
		Long ID,
		String mensaje,
		Long idtopico,
		boolean solucion,
		ResponsePerfil responsePerfil) {

}
