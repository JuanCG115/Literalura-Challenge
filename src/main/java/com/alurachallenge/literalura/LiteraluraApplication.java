package com.alurachallenge.literalura;

import com.alurachallenge.literalura.service.CatalogoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

    @Bean
    CommandLineRunner run(CatalogoService service) {
        return args -> {

            Scanner sc = new Scanner(System.in);
            int opcion = -1;

            do {
                System.out.println("""
                        ***** Bienvenido a tu libreria personal *****
                        1 - Buscar libro
                        2 - Listar libros
                        3 - Listar autores
                        4 - Autores vivos segun el año
                        5 - Listar libros por idioma
                        0 - Salir
                        """);

                String input = sc.nextLine();

                try {
                    opcion = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Opción inválida.");
                    continue;
                }

                switch (opcion) {
                    case 1 -> {
                        System.out.println("Ingrese titulo:");
                        String titulo = sc.nextLine();
                        service.buscarYGuardarLibro(titulo);
                    }
                    case 2 -> service.listarLibros();
                    case 3 -> service.listarAutores();
                    case 4 -> {
                        System.out.println("Ingrese año:");
                        try {
                            int anio = Integer.parseInt(sc.nextLine());
                            service.autoresVivosEnAnio(anio);
                        } catch (NumberFormatException e) {
                            System.out.println("Año inválido.");
                        }
                    }
                    case 5 -> {
                        System.out.println("Idioma (es, en, fr, pt):");
                        String idioma = sc.nextLine();
                        service.listarLibrosPorIdioma(idioma);
                    }
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }

            } while (opcion != 0);
        };
    }
}
