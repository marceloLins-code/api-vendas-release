package com.lins.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.comercial.exception.ClienteNaoEncontradoException;
import com.lins.comercial.exceptionhandler.EntidadeEmUsoException;
import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;
import com.lins.comercial.model.Cliente;
import com.lins.comercial.repository.ClienteRepository;

@Service
public class ClienteService {

	private static final String MSG_CLIENTE_EM_USO = "Cliente de Id %d não pode ser removido, pois está em uso";

	private static final String MSG_CLIENTE_NAO_ENCONTRADO = "Não existe um cadastro de cliente com id %d";

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void excluir(Integer clienteId) {

		try {
			clienteRepository.deleteById(clienteId);

		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException(clienteId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CLIENTE_EM_USO, clienteId));
		}
	}

	public Cliente buscarOuFalhar(Integer clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_CLIENTE_NAO_ENCONTRADO, clienteId)));
	}

}
