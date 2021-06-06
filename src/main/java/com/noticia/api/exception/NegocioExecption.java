package com.noticia.api.exception;

public class NegocioExecption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioExecption(String mensagem) {
		super(mensagem);
	}

}
