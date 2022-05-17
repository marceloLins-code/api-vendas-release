create table Produto (
	id integer not null auto_increment,
    nome varchar(60) not null,
    valor double not null,

    primary key(id)
    ) engine=InnoDB default charset=utf8;