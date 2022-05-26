package com.lins.comercial.exception;

import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProdutoNaoEncontradoException(Integer produtoId) {
		this(String.format("Não existe um cadastro de produto com id %d", produtoId));
	}
	
}