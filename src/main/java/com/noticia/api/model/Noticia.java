package com.noticia.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Noticia {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String url;

	@NotNull
	private String titulo;

	@NotNull
	private String subTitulo;

	@NotNull
	private String autor;

	@NotNull
	private String dataPublicacao;

	@NotNull
	private String conteudo;

}
