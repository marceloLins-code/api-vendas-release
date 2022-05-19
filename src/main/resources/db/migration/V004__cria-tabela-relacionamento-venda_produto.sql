create table vendas_x_produtos (
	venda_id integer not null,
	produto_id integer not null,
	
	primary key (venda_id, produto_id)
) engine=InnoDB default charset=utf8;