package com.lins.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.comercial.exception.ClienteNaoEncontradaException;
import com.lins.comercial.exceptionhandler.EntidadeEmUsoException;
import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;
import com.lins.comercial.model.Cliente;
import com.lins.comercial.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void excluir(Integer clienteId) {

		try {
			clienteRepository.deleteById(clienteId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cliente com código %d", clienteId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cliente de Id %d não pode ser removida, pois está em uso", clienteId));
		}
	}
	
	public Cliente buscarOuFalhar(Integer clienteId) {
		return clienteRepository.findById(clienteId)
			.orElseThrow(() -> new ClienteNaoEncontradaException(clienteId));
	}

}
