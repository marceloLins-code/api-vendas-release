create table cliente (
	id integer not null auto_increment,
    nome varchar(60) not null,
    cnpjCpf varchar(11) not null,

    primary key(id)
    ) engine=InnoDB default charset=utf8;