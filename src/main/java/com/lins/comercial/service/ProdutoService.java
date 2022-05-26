package com.lins.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.comercial.exception.ProdutoNaoEncontradoException;
import com.lins.comercial.exceptionhandler.EntidadeEmUsoException;
import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;
import com.lins.comercial.model.Produto;
import com.lins.comercial.repository.ProdutoRepository;



@Service
public class ProdutoService {
	
	private static final String MSG_PRODUTO_NAO_ENCONTRADO
	= "Não existe um cadastro de produto com id %d";
	
	private static final String MSG_PRODUTO_EM_USO 
	= "Produto de Id %d não pode ser removido, pois está em uso";
	
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	
	public void excluir(Integer produtoId) {

		try {
			produtoRepository.deleteById(produtoId);

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(produtoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PRODUTO_EM_USO, produtoId));
		}
	}
	
	public Produto buscarOuFalhar(Integer produtoId) {
		return produtoRepository.findById(produtoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)));
}
}