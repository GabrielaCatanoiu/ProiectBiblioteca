package proiect.ProiectBiblioteca.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proiect.ProiectBiblioteca.controllers.BookController;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.entity.Book;
import proiect.ProiectBiblioteca.services.BookService;
import proiect.ProiectBiblioteca.utils.BookMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.BOOK_WAS_DELETED;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.PUBLISHING_HOUSE_WAS_DELETED;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    Book book;

    BookDTO bookDTO;

    @Test
    public void addBookTest() throws Exception {

        //GIVEN
        bookDTO = BookMocks.mockBookDTO();
        book = BookMocks.mockBook();

        //WHEN
        when(bookService.addBook(bookDTO)).thenReturn(bookDTO);

        //THEN
        mockMvc.perform(post("/book/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(bookDTO)));
    }

    @Test
    public void getAllBooksTest() throws Exception{

        //GIVEN
        book = BookMocks.mockBook();

        List<Book> books = new ArrayList<>();
        books.add(book);

        //WHEN
        when(bookService.getAllBooks()).thenReturn(books);

        //THEN
        mockMvc.perform(get("/book/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(books)));
    }

    @Test
    public void getOnePublicationTest() throws Exception {

        //GIVEN
        bookDTO = BookMocks.mockBookDTO();
        book = BookMocks.mockBook();

        //WHEN
        when(bookService.getOneBook(book.getId())).thenReturn(Optional.ofNullable(book));

        //THEN
        mockMvc.perform(get("/book/getBookById/"+book.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(book)));
    }

    @Test
    public void getBookByTitleTest() throws Exception {

        //GIVEN
        bookDTO = BookMocks.mockBookDTO();
        book = BookMocks.mockBook();

        List<BookDTO> bookDTOS = new ArrayList<>();
        bookDTOS.add(bookDTO);

        //WHEN
        when(bookService.geByTitle(book.getTitle())).thenReturn(bookDTOS);

        //THEN
        mockMvc.perform(get("/book/getBookByTitle/"+book.getTitle()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(bookDTOS)));
    }

    @Test
    public void deleteBookTest() throws Exception{

        //GIVEN
        bookDTO = BookMocks.mockBookDTO();
        book = BookMocks.mockBook();

        //WHEN
        when(bookService.delete(book.getId())).thenReturn(true);

        //THEN
        mockMvc.perform(delete("/book/delete/"+book.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format(BOOK_WAS_DELETED,book.getId())));
    }

   /* @Test
    public void updateTitleBookTest() throws Exception{

        //GIVEN
        bookDTO = BookMocks.mockBookDTO();
        book = BookMocks.mockBook();

        //WHEN
        when(bookService.updateBookName(book.getId(),book.getTitle())).thenReturn(bookDTO);

        //THEN
        mockMvc.perform(put("/book/update/"+book.getId()+"/"+book.getTitle()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(book)));
    }*/
}
