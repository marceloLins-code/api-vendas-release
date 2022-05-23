package com.lins.comercial.exception;

import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;

public class ClienteNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public ClienteNaoEncontradaException(Integer clienteId) {
		this(String.format("Não existe um cadastro de venda com código %d", clienteId));
	}
	
}
