package com.lins.comercial.exception;

import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;

public class VendaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public VendaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public VendaNaoEncontradoException(Integer vendaId) {
		this(String.format("Não existe um cadastro de venda com código %d", vendaId));
	}
	
}