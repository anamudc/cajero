# Cajero - Prueba técnica 
Sumlador de cajero para dos tipos de usuario
##### Administrador
  - Agregar billetes al inventario
##### Usuario
  - Realizar retiros a partir de un valor solicitado
  
### Proyecto
  - Proyecto MAVEN
  - Lenguaje JAVA - JDK 1.8
  - Framework Hibernate
  - Base de datos PostgreSQL
  - Pruebas unitarias JUnit
  - Servidor de aplicaciones Glassfish 4.1.0
  - IDE de desarrollo Netbeans

### Base de Datos
La tabla requerida para el proyecto se crea de la siguiente forma:
```sh
create table BILLETES (
  id serial PRIMARY KEY,
  DENOMINACION numeric NOT null unique,
  CANTIDAD numeric NOT NULL
 );
```
