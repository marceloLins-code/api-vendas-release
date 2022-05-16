package com.lins.comercial.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Venda  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	

	//@FutureOrPresentS
	@Column(nullable = false)
	private LocalDate dataVenda ;
	
	
	@Column(nullable = false)
	private LocalDate dataEntrega;	

	@ManyToOne
	private Cliente cliente;
	
	@ManyToMany
	@JoinTable(name = "vendas_x_produtos", joinColumns = @JoinColumn(name = "venda_id"),
	inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos = new ArrayList<>();
	

	


}