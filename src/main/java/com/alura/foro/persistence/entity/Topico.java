package com.alura.foro.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "topicos")
@Entity(name = "Topico")
@NoArgsConstructor
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String titulo;
	private String mensaje;
	private LocalDateTime fechacreacion = LocalDateTime.now();
	private StatusTopico statustopico = StatusTopico.NO_RESPONDIDO;

	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idtopico", referencedColumnName = "id")
    List < Respuesta > respuestas = new ArrayList < > ();
	
	@OneToOne
    @JoinColumn(name = "idperfil")
	private Perfil perfil;
	
	@OneToOne
    @JoinColumn(name = "idcurso")
	private Curso curso;
	
	public Topico(String titulo, String mensaje, Curso curso, Perfil perfil) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.curso = curso;
		this.perfil = perfil;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
