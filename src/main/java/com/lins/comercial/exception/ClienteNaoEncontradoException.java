package com.lins.comercial.exception;

import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ClienteNaoEncontradoException(Integer clienteId) {
		this(String.format("Não existe um cadastro de cliente com id %d", clienteId));
	}
	
}