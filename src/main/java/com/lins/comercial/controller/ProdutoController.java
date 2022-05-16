package com.lins.comercial.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.comercial.model.Produto;
import com.lins.comercial.repository.ProdutoRepository;



@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@GetMapping("/{produtoId}")
	public ResponseEntity<Produto> buscar(@PathVariable Integer produtoId) {
		return produtoRepository.findById(produtoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

	@PutMapping("/{produtoId}")
	public ResponseEntity<Produto> atualizar(@PathVariable Integer produtoId, @RequestBody Produto produto) {
		Produto produtoAtual = produtoRepository.getById(produtoId);

		if (produtoAtual != null) {
			BeanUtils.copyProperties(produto, produtoAtual, "id");

			produtoAtual = produtoRepository.save(produtoAtual);
			return ResponseEntity.ok(produtoAtual);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{produtoId}")
	public ResponseEntity<Void> remover(@PathVariable Integer produtoId) {
		if (!produtoRepository.existsById(produtoId)) {
			return ResponseEntity.notFound().build();
		}

		produtoRepository.deleteById(produtoId);

		return ResponseEntity.noContent().build();
	}

}