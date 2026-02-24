package com.alurachallenge.literalura.service;

import com.alurachallenge.literalura.dto.AutorData;
import com.alurachallenge.literalura.dto.BookData;
import com.alurachallenge.literalura.dto.GutendexResponse;
import com.alurachallenge.literalura.model.Autor;
import com.alurachallenge.literalura.model.Libro;
import com.alurachallenge.literalura.repository.AutorRepository;
import com.alurachallenge.literalura.repository.LibroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoService {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private ObjectMapper objectMapper;

    public void buscarYGuardarLibro(String titulo) {

        if (libroRepository.findByTituloIgnoreCase(titulo).isPresent()) {
            System.out.println("El libro ya existe en la base de datos.");
            return;
        }

        String json = gutendexService.buscarLibroJson(titulo);

        try {
            GutendexResponse response =
                    objectMapper.readValue(json, GutendexResponse.class);

            if (response.results().isEmpty()) {
                System.out.println("Libro no encontrado en la API.");
                return;
            }

            BookData data = response.results().get(0);

            AutorData authorData = data.authors().get(0);

            // Buscar si el autor ya existe
            Autor autor = autorRepository
                    .findByNombre(authorData.name())
                    .orElseGet(() -> {
                        Autor nuevoAutor = new Autor();
                        nuevoAutor.setNombre(authorData.name());
                        nuevoAutor.setFechaNacimiento(authorData.birth_year());
                        nuevoAutor.setFechaFallecimiento(authorData.death_year());
                        return autorRepository.save(nuevoAutor);
                    });

            Libro libro = new Libro();
            libro.setTitulo(data.title());
            libro.setIdioma(data.languages().get(0));
            libro.setNumeroDescargas(data.download_count());
            libro.setAutor(autor);

            libroRepository.save(libro);

            System.out.println("\nLibro guardado exitosamente:\n");

            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + autor.getNombreFormateado());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Número de descargas: " + libro.getNumeroDescargas());
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarLibros() {
        libroRepository.findAll().forEach(libro -> {

            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombreFormateado());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Número de descargas: " + libro.getNumeroDescargas());
            System.out.println("----------------------------------------");

        });
    }

    @Transactional(readOnly = true)
    public void listarAutores() {
        autorRepository.findAll().forEach(autor -> {

            System.out.println("Autor: " + autor.getNombreFormateado());
            System.out.println("Fecha de nacimiento: " + autor.getFechaNacimiento());
            System.out.println("Fecha de fallecimiento: " +
                    (autor.getFechaFallecimiento() != null
                            ? autor.getFechaFallecimiento()
                            : "Vivo"));

            // Obtener títulos de los libros
            List<String> titulos = autor.getLibros()
                    .stream()
                    .map(Libro::getTitulo)
                    .toList();

            System.out.println("Libros: " + titulos);
            System.out.println("----------------------------------------");
        });
    }

    @Transactional(readOnly = true)
    public void autoresVivosEnAnio(Integer anio) {
        List<Autor> autores = autorRepository.findAll();

        autores.stream()
                .filter(a ->
                        a.getFechaNacimiento() != null &&
                                a.getFechaNacimiento() <= anio &&
                                (a.getFechaFallecimiento() == null || a.getFechaFallecimiento() >= anio)
                )
                .forEach(a -> System.out.println(a.getNombre()));
    }

    public void listarLibrosPorIdioma(String idioma) {
        libroRepository.findByIdioma(idioma)
                .forEach(libro ->
                        System.out.println(libro.getTitulo()));
    }
}
