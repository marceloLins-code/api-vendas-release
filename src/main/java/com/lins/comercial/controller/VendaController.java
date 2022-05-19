package com.lins.comercial.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lins.comercial.model.Venda;
import com.lins.comercial.repository.VendaRepository;


@RestController
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	private VendaRepository vendaRepository;

	@GetMapping
	public List<Venda> listar() {
		return vendaRepository.findAll();
	}

	@GetMapping("/{vendaId}")
	public ResponseEntity<Venda> buscar(@PathVariable Integer vendaId) {
		return vendaRepository.findById(vendaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Venda venda) {
		venda.setDataVenda(LocalDate.now());
		venda.setDataEntrega(venda.getDataVenda().plusDays(10));
		vendaRepository.save(venda);
		
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(venda);
	}

	@PutMapping("/{vendaId}")
	public ResponseEntity<Venda> atualizar(@PathVariable Integer vendaId, @RequestBody Venda venda) {
		Venda vendaAtual = vendaRepository.getById(vendaId);

		if (vendaAtual != null) {
			BeanUtils.copyProperties(venda, vendaAtual, "id", "produtos", "dataVenda");

			vendaAtual = vendaRepository.save(vendaAtual);
			return ResponseEntity.ok(vendaAtual);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{vendaId}")
	public ResponseEntity<Venda> remover(@PathVariable Integer vendaId) {
		try {
			Venda venda = vendaRepository.getById(vendaId);
			
			if (venda != null) {
				vendaRepository.delete(venda);
				
				return ResponseEntity.noContent().build();
			}
			
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}