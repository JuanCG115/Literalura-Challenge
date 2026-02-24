Catálogo de Libros - Literalura

Aplicación desarrollada con Spring Boot + JPA + PostgreSQL que permite consultar libros desde la API pública de Gutendex y almacenarlos en una base de datos local.

Funcionalidades

1. Buscar libro por título (evita duplicados)
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un año determinado
5. Listar libros por idioma (es, en, fr, pt)

La información se obtiene desde:
👉 https://gutendex.com/books

Vista previa
Menu principal
- <img width="499" height="201" alt="image" src="https://github.com/user-attachments/assets/9473d32e-4869-4c02-a182-11bd3dda9d42" />
Opcion 1:
- <img width="479" height="567" alt="image" src="https://github.com/user-attachments/assets/94a81337-ff12-4cd8-8732-f241615b2e36" />
- <img width="1376" height="376" alt="image" src="https://github.com/user-attachments/assets/664cd689-2086-4997-baf4-12ce8585e62e" />
- <img width="1371" height="347" alt="image" src="https://github.com/user-attachments/assets/a8970bb1-a534-4f3c-8a60-c03299acd84b" />
Opcion 2:
- <img width="840" height="679" alt="image" src="https://github.com/user-attachments/assets/121de5bc-5593-4bab-adad-8a3eb8c09a55" />
Opcion 3:
- <img width="1243" height="705" alt="image" src="https://github.com/user-attachments/assets/e3a8add1-09c1-4ca9-b5de-5fce6b19fbd9" />
Opcion 4:
- <img width="1006" height="331" alt="image" src="https://github.com/user-attachments/assets/3a98f9ad-ec56-483f-a890-e5680489d1a5" />
- <img width="1011" height="563" alt="image" src="https://github.com/user-attachments/assets/5e390c49-8901-407f-90ad-e5be8680b881" />
Opcion 5:
- <img width="753" height="514" alt="image" src="https://github.com/user-attachments/assets/79f7a9d7-f650-4246-aae0-6f274243d58f" />
- <img width="644" height="273" alt="image" src="https://github.com/user-attachments/assets/e1c2e74c-fc5b-46d3-89c1-67b21a4f47e0" />

Tecnologías utilizadas
Java 17+
Spring Boot
Spring Data JPA
PostgreSQL
Jackson
Maven

Configuración del proyecto
1️. Crear base de datos en pgAdmin 4
Antes de ejecutar el proyecto debes crear la base de datos manualmente.
En pgAdmin 4:
- Abrir pgAdmin
- Ir a Databases
- Click derecho → Create → Database
- Nombre: "catlogo_libro" o el que quieras usar y guarda

También puedes hacerlo con SQL

    CREATE DATABASE catalogo_libros;

2. Configurar variables de entorno
El proyecto usa variables de entorno para proteger credenciales.

En application.properties se usa:

    spring.datasource.url=jdbc:postgresql://${DB_HOST}/catalogo_libros
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    spring.datasource.driver-class-name=org.postgresql.Driver
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.format-sql=true
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

Configurar variables en Windows (PowerShell)
    
    setx DB_HOST "localhost:5432"
    setx DB_USER "postgres"
    setx DB_PASSWORD "tu_password"

Configurar variables en Linux / Mac
    
    export DB_HOST=localhost:5432
    export DB_USER=postgres
    export DB_PASSWORD=tu_password

Ejecuta el proyecto desde tu IDE

Creación automática de tablas
El proyecto usa:
    
    spring.jpa.hibernate.ddl-auto=update

Esto significa que:
- Si la base está vacía → crea las tablas automáticamente.
- Si ya existen → las actualiza sin borrar datos.
No necesitas crear tablas manualmente.

**Notas importantes**

- No se incluyen credenciales en el repositorio.
- Cada usuario debe configurar sus propias variables de entorno.
- Si la base de datos no existe, la aplicación no iniciará.

***Autor***
Juan Arturo Camarillo Gutiérrez
Ingeniero en Robótica
