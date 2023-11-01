DROP DATABASE IF EXISTS Bar;
CREATE DATABASE Bar;
Use Bar;


CREATE TABLE Datos(
id int primary key auto_increment,
Bebida varchar(30),
Sabor varchar(30),
Mezcla varchar(30),
Marca varchar(30),
Complemento varchar(30)

 );
 INSERT INTO Datos  VALUES
 (1,"Vodka", "Fresa","Limon", "Eristoff", "Rodaja limon"),
 (2,"Whisky","Caramelo","CocaCola","JB","Rodaja naranja"),
 (3,"Ginebra","Fresa","Tonica","Puerto de Indias", "Gominolas"),
 (4,"Ron","Añejo", "CocaCola","Barcelo","Rodaja naranja"),
 (5,"Licor","Melocotón","Naranja","Cointreau","Nada");

CREATE TABLE Pedido(
id int primary key auto_increment,
Bebida varchar(30),
Sabor varchar(30),
Mezcla varchar(30),
Marca varchar(30),
Complemento varchar(30)

 );

