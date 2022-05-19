create table venda (
		id integer not null auto_increment,
		data_venda date not null,
		data_entrega date not null,
		cliente_id integer (20) not null,
	
		primary key (id)
) engine=InnoDB default charset=utf8;