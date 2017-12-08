create database java_exemplo 
default character set utf8
default collate utf8_general_ci;

use java_exemplo;

create table categoria(
id int auto_increment,
descricao varchar(50),
constraint item_id_pk primary key(id)
)engine = InnoDB
default charset=utf8;

create table item (
id int auto_increment,
descricao varchar(50),
qtd int,
valor double,
categoria int,
constraint item_id_pk primary key(id),
constraint item_categoria_fk foreign key(categoria) 
references categoria(id)
)engine = InnoDB
default charset=utf8;