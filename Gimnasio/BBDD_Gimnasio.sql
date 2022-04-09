create database gimnasio;
use gimnasio;

create table clientes(
id int unsigned not null auto_increment primary key,
nombre varchar(45) not null,
DNI varchar(15) not null unique,
telefono varchar(15),
fecha_alta date not null,
cuota double not null,

INDEX index_nombreCliente (nombre)
);

create table clases(
id int unsigned not null auto_increment primary key,
nombre varchar(45) not null,
profesor varchar(45),
dia varchar(10) not null,
hora time not null,

INDEX index_nombreClase (nombre)
);

create table clientes_clases(
idCliente int unsigned not null,
nombreCliente varchar(45),
idClase int unsigned not null,
nombreClase varchar(45),

PRIMARY KEY (idCliente, idClase),
CONSTRAINT FK_IdCliente FOREIGN KEY (idCliente) REFERENCES clientes(id)
on delete cascade on update cascade,

CONSTRAINT FK_IdClases FOREIGN KEY (idClase) REFERENCES clases(id)
on delete cascade on update cascade,

CONSTRAINT FK_nombreClase FOREIGN KEY (nombreClase) REFERENCES clases(nombre)
on delete cascade on update cascade,

CONSTRAINT FK_nombreCliente FOREIGN KEY (nombreCliente) REFERENCES clientes(nombre)
on delete cascade on update cascade
)Engine=InnoDB;

select * from clientes;
select * from clases;
select * from clientes_clases;
select nombreClase ,count(idCliente) from clientes_clases  group by nombreClase order by count(idCliente) desc;

insert into clientes (nombre, DNI, telefono, fecha_alta, cuota) values	
('JOSUE VARGAS', '66666666D', '1234567890', '2022-01-15',	40),
('Sara Hurtado', '11111111V', '0987654321', '2021-10-12',	10),
('Jorge Ferrer', '22222222S', '6123456789', '2021-09-09',	15),
('Fernando Perez', '33333333H', '609876543', '2021-08-25',	20);

insert into clases (nombre, profesor, dia, hora) values
('Natacion', 'Ruben P�rez', 'Lunes', '10:00'),
('Crossfit', 'Marcos Lopez', 'Martes', '18:00'),
('Boxeo', 'Luis Perez', 'Miercoles', '16:00'),
('SPINNIN', 'Laia Requena', 'Viernes', '10:00');