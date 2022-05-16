package com.lins.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.comercial.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
}