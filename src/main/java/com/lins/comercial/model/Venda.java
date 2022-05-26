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

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Venda  {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	

	//@FutureOrPresentS
	@Column(nullable = false)
	private LocalDate dataVenda ;
	
	
	@Column(nullable = false)
	private LocalDate dataEntrega;	

	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnoreProperties("hibernateLazyInitializer")
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	//@Column(name = "produto_id", nullable = false)
	@ManyToMany
	@JoinTable(name = "vendas_x_produtos", joinColumns = @JoinColumn(name = "venda_id"),
	inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos = new ArrayList<>();
		


}