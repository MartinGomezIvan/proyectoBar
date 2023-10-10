DROP DATABASE IF EXISTS Bar;
CREATE DATABASE Bar;
Use Bar;


CREATE TABLE Datos(
Bebida varchar(30) primary key,
Sabor varchar(30),
Mezcla varchar(30),
Marca varchar(30),
Complemento varchar(30)

 );
 INSERT INTO Datos  VALUES
 ("Vodka", "Fresa","Limon", "Eristoff", "Rodaja limon"),
 ("Whisky","Caramelo","CocaCola","JB","Rodaja naranja"),
 ("Ginebra","Fresa","Tonica","Puerto de Indias", "Gominolas"),
 ("Ron","Añejo", "CocaCola","Barcelo","Rodaja naranja"),
 ("Licor","Hierbas","Naranja","Jagger","Rodaja naranja");
 


