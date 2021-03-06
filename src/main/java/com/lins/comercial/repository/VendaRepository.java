package com.lins.comercial.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.comercial.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>{

	Venda save(Pageable pageable);

	Venda findById(Venda vendaId);

}
