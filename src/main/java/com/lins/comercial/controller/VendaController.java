package com.lins.comercial.controller;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.lins.comercial.model.Venda;
import com.lins.comercial.repository.VendaRepository;
import com.lins.comercial.service.VendaService;


@RestController
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping
	public Page<Venda> listar(Pageable pageable) {
		return vendaRepository.findAll(pageable);
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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer vendaId) {
		vendaService.excluir(vendaId);	
	}
	
	
}