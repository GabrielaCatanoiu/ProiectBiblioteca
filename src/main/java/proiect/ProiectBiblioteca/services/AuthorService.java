package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.entity.Author;
import proiect.ProiectBiblioteca.entity.Book;
import proiect.ProiectBiblioteca.exceptions.AuthorNotFoundException;
import proiect.ProiectBiblioteca.exceptions.BookNotFoundException;
import proiect.ProiectBiblioteca.mapper.AuthorMapper;
import proiect.ProiectBiblioteca.repositories.AuthorRepository;
import proiect.ProiectBiblioteca.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@Service
@RequiredArgsConstructor
public class AuthorService implements AuthorServiceImpl, DeleteServiceImpl{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;


    @Override
    public AuthorDTO addAuthor(AuthorDTO authorDTO)
    {
        return authorMapper.mapToAuthorDTO(authorRepository.save(authorMapper.mapToAuthor(authorDTO)));
    }

    @Override
    public Optional<Author> getAutor(Long id)
    {
        return authorRepository.findById(id);
    }

    @Override
    public List<AuthorDTO> getAuthorByNameAndSurname(String author_name, String author_surname)
    {
        List<AuthorDTO> authorDTOS = authorRepository.findAuthorByAuthor_nameAndAuthor_surname(author_name,author_surname).stream()
                .map(autor -> authorMapper.mapToAuthorDTO(autor)).collect(Collectors.toList());
        if (authorDTOS.isEmpty())
        {
            throw new AuthorNotFoundException(String.format(AUTHOR_NOT_FOUND,author_name,author_surname));
        }
        return authorDTOS;
    }

    @Override
    public List<Author> getAllAuthors()
    {
        List<Author> authors = new ArrayList<>();
        for (int i=0; i<authorRepository.findAll().size();i++)
        {
            authors.add(authorRepository.findAll().get(i));
        }
        return authors;
    }

    @Override
    public void delete(Long id)
    {
        Optional<Author> authorFound = authorRepository.findById(id);
        if(authorFound.isPresent())
        {
            authorRepository.delete(authorFound.get());
        }
        else
        {
            throw new AuthorNotFoundException(String.format(AUTHOR_ID_NOT_FOUND,id));
        }
    }
}
