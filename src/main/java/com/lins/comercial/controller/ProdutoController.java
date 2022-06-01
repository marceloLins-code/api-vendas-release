package com.lins.comercial.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;
import com.lins.comercial.model.Produto;
import com.lins.comercial.repository.ProdutoRepository;
import com.lins.comercial.service.ProdutoService;



@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@GetMapping("/{produtoId}")
	public Produto buscar(@PathVariable Integer produtoId) {
		return produtoService.buscarOuFalhar(produtoId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

	@PutMapping("/{produtoId}")
	public Produto atualizar(@PathVariable Integer produtoId, @RequestBody Produto produto) {
		Produto produtoAtual = produtoService.buscarOuFalhar(produtoId);

			BeanUtils.copyProperties(produto, produtoAtual, "id");

			return produtoService.salvar(produtoAtual);
		
	}

	@DeleteMapping("/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer produtoId) {
		produtoService.excluir(produtoId);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		
	}

}