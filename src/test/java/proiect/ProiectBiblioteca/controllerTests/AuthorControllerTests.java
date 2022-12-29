package proiect.ProiectBiblioteca.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proiect.ProiectBiblioteca.controllers.AuthorController;
import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.entity.Author;
import proiect.ProiectBiblioteca.services.AuthorService;
import proiect.ProiectBiblioteca.utils.AuthorMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.AUTHOR_WAS_DELETED;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTests {

    @MockBean
    AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Author author;

    AuthorDTO authorDTO;

    @Test
    public void addAuthorTest() throws Exception {

        //GIVEN
        authorDTO = AuthorMocks.mockAuthorDTO();
        author = AuthorMocks.mockAuthor();

        //WHEN
        when(authorService.addAuthor(authorDTO)).thenReturn(authorDTO);

        //THEN
        mockMvc.perform(post("/authors/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(authorDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(authorDTO)));
    }

    @Test
    public void getOneAuthorTest() throws Exception {

        //GIVEN
        authorDTO = AuthorMocks.mockAuthorDTO();
        author = AuthorMocks.mockAuthor();

        //WHEN
        when(authorService.getAutor(author.getId())).thenReturn(Optional.ofNullable(author));

        //THEN
        mockMvc.perform(get("/authors/getAuthor/"+author.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(author)));
    }

    @Test
    public void getByNameAndSurnameTest() throws Exception {

        //GIVEN
        authorDTO = AuthorMocks.mockAuthorDTO();
        author = AuthorMocks.mockAuthor();

        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorDTOS.add(authorDTO);

        //WHEN
        when(authorService.getAuthorByNameAndSurname(author.getAuthor_name(),author.getAuthor_surname())).thenReturn(authorDTOS);

        //THEN
        mockMvc.perform(get("/authors/getByNameAndSurname/"+author.getAuthor_name()+"/"+author.getAuthor_surname()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(authorDTOS)));
    }

    @Test
    public void getAllAuthorsTest() throws Exception{

        //GIVEN
        author = AuthorMocks.mockAuthor();

        List<Author> authors = new ArrayList<>();
        authors.add(author);

        //WHEN
        when(authorService.getAllAuthors()).thenReturn(authors);

        //THEN
        mockMvc.perform(get("/authors/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(authors)));

    }

    @Test
    public void deleteAuthor() throws Exception{

        //GIVEN
        authorDTO = AuthorMocks.mockAuthorDTO();
        author = AuthorMocks.mockAuthor();

        //WHEN
        when(authorService.delete(author.getId())).thenReturn(true);

        //THEN
        mockMvc.perform(delete("/authors/delete/"+author.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format(AUTHOR_WAS_DELETED,author.getId())));
    }
}
