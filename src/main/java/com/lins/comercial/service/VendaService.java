package com.lins.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lins.comercial.exception.VendaNaoEncontradoException;
import com.lins.comercial.exceptionhandler.EntidadeEmUsoException;
import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;
import com.lins.comercial.model.Cliente;
import com.lins.comercial.model.Venda;
import com.lins.comercial.repository.VendaRepository;

@Service
public class VendaService {

	private static final String MSG_VENDA_EM_USO = "venda de id %d não pode ser removida, pois está em uso";

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ClienteService clienteService;

	@Transactional
	public Venda salvar(Venda venda) {
		Integer clienteId = venda.getCliente().getId();

		Cliente cliente = clienteService.buscarOuFalhar(clienteId);

		venda.setCliente(cliente);

		return vendaRepository.save(venda);
	}

	public void excluir(Integer vendaId) {

		try {
			vendaRepository.deleteById(vendaId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de venda com código %d", vendaId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_VENDA_EM_USO, vendaId));
		}
	}

	public Venda buscarOuFalhar(Integer vendaId) {
		return vendaRepository.findById(vendaId).orElseThrow(() -> new VendaNaoEncontradoException(vendaId));
	}

}
