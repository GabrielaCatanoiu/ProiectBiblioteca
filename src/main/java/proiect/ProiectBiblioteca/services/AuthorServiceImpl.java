package proiect.ProiectBiblioteca.services;

import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorServiceImpl {

    public AuthorDTO addAuthor(AuthorDTO authorDTO);
    public Optional<Author> getAutor(Long id);
    public List<AuthorDTO> getAuthorByNameAndSurname(String author_name, String author_surname);
    public List<Author> getAllAuthors();
}
