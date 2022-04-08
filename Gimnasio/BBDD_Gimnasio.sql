create database gimnasio;
use gimnasio;

create table clientes(
id int unsigned not null auto_increment primary key,
nombre varchar(45) not null,
DNI varchar(15) not null unique,
telefono varchar(15),
fecha_alta date not null,
cuota double not null 
);

create table clases(
id int unsigned not null auto_increment primary key,
nombre varchar(45) not null,
profesor varchar(45),
dia date not null,
hora time not null,
apuntados int unsigned default (0)
);

alter table clases modify dia varchar(10);
create table clientes_clases(
idCliente int unsigned not null,
nombreCliente varchar(45),
idClase int unsigned not null,
nombreClase varchar(45),

PRIMARY KEY (idCliente, idClase),
CONSTRAINT FK_IdCliente FOREIGN KEY (idCliente) REFERENCES clientes(id)
on delete cascade on update cascade,
CONSTRAINT FK_IdClases FOREIGN KEY (idClase) REFERENCES clases(id)
on delete cascade on update cascade
)Engine=InnoDB;

