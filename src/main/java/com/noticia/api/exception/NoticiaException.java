package com.noticia.api.exception;

public class NoticiaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public NoticiaException(String mensage) {
		super(mensage);

	}

	public NoticiaException(Long id) {
		this(String.format("Não existe um cadastro de noticia com código %d", id));
	}

}
