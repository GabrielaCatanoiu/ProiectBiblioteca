package proiect.ProiectBiblioteca.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.entity.Author;
import proiect.ProiectBiblioteca.exceptions.AuthorNotFoundException;
import proiect.ProiectBiblioteca.mapper.AuthorMapper;
import proiect.ProiectBiblioteca.repositories.AuthorRepository;
import proiect.ProiectBiblioteca.services.AuthorService;
import proiect.ProiectBiblioteca.utils.AuthorMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.AUTHOR_ID_NOT_FOUND;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.AUTHOR_NOT_FOUND;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthorServiceTests {

    @InjectMocks
    AuthorService authorService;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    AuthorMapper authorMapper;

    Author author;

    AuthorDTO authorDTO;

    @Test
    public void testAddAuthor(){
        //GIVEN
        author = AuthorMocks.mockAuthor();
        authorDTO = AuthorMocks.mockAuthorDTO();

        //WHEN
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.mapToAuthorDTO(author)).thenReturn(authorDTO);
        when(authorMapper.mapToAuthor(authorDTO)).thenReturn(author);

        AuthorDTO result = authorService.addAuthor(authorDTO);

        //THEN
        assertTrue(result.getAuthor_name().equals(authorDTO.getAuthor_name()));
        assertThat(result.getDate_birth()).isNotNull();
        assertNotNull(result);
        verifyNoMoreInteractions(authorRepository,authorMapper);
    }

    @Test
    public void testGetAuthor(){

        //GIVEN
        author = AuthorMocks.mockAuthor();

        //WHEN
        when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));

        Optional<Author> result = authorService.getAutor(author.getId());

        //THEN
        assertEquals(result,Optional.ofNullable(author));
        assertThat(result).isNotNull();

    }

    @Test
    public void testAuthorException() {
        //GIVEN
        author = null;
        authorDTO = null;

        List<Author> authors = new ArrayList<>();

        List<AuthorDTO> authorDTOS = new ArrayList<>();

        //WHEN
        when(authorRepository.findAuthorByAuthor_nameAndAuthor_surname("Creanga","Ion"))
                .thenReturn(authors);

        //THEN
        AuthorNotFoundException authorNotFoundException = assertThrows(AuthorNotFoundException.class, () -> authorService.getAuthorByNameAndSurname("Creanga","Ion"));
        assertEquals(String.format(AUTHOR_NOT_FOUND, "Creanga"), authorNotFoundException.getMessage());
    }

    @Test
    public void testGetAuthorByNameAndSurname(){

        //GIVEN
        author = AuthorMocks.mockAuthor();
        authorDTO = AuthorMocks.mockAuthorDTO();

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorDTOS.add(authorDTO);

        //WHEN
        when(authorRepository.findAuthorByAuthor_nameAndAuthor_surname(author.getAuthor_name(),author.getAuthor_surname()))
                .thenReturn(authors);
        when(authorMapper.mapToAuthorDTO(author)).thenReturn(authorDTO);

        //THEN
        List<AuthorDTO> result = authorService.getAuthorByNameAndSurname(author.getAuthor_name(),author.getAuthor_surname());
        assertEquals(result,authorDTOS);
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetAllAuthors(){

        //GIVEN
        author = AuthorMocks.mockAuthor();

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        //WHEN
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAllAuthors();

        //THEN
        assertEquals(result,authors);
        assertThat(result).isNotNull();
    }

    @Test
    public void testDelete(){

        //GIVEN
        author = AuthorMocks.mockAuthor();

        //WHEN
        when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));

        boolean result = authorService.delete(author.getId());

        //THEN
        assertTrue(result);
    }

    @Test
    public void testDeleteException(){

        author = null;

        //WHEN
        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(author));

        //THEN
        AuthorNotFoundException authorNotFoundException = assertThrows(AuthorNotFoundException.class, () -> authorService.delete(1L));
        assertEquals(String.format(AUTHOR_ID_NOT_FOUND,1L ), authorNotFoundException.getMessage());
    }

}
