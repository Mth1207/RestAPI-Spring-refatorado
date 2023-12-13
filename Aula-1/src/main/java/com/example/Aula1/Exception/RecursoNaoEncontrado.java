package com.example.Aula1.Exception;

public class RecursoNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontrado(String msg) {
		super(msg);
	}
}
