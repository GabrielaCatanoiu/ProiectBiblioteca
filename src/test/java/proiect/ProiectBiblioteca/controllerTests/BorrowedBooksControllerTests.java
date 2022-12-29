package proiect.ProiectBiblioteca.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proiect.ProiectBiblioteca.controllers.BorrowBooksController;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.dto.BorrowedBooksDTO;
import proiect.ProiectBiblioteca.entity.Book;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;
import proiect.ProiectBiblioteca.services.BorrowedBooksService;
import proiect.ProiectBiblioteca.utils.BookMocks;
import proiect.ProiectBiblioteca.utils.BorrowedBooksMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.BOOK_WAS_DELETED;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.BORROWED_BOOK_WAS_DELETED;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BorrowBooksController.class)
public class BorrowedBooksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    BorrowedBooksService borrowedBooksService;

    BorrowedBooks borrowedBooks;

    BorrowedBooksDTO borrowedBooksDTO;

    @Test
    public void addBorrowedBookTest() throws Exception {

        //GIVEN
        borrowedBooksDTO = BorrowedBooksMocks.mockBorrowedBooksDTO();
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        //WHEN
        when(borrowedBooksService.addBorrowedBook(borrowedBooksDTO)).thenReturn(borrowedBooksDTO);

        //THEN
        mockMvc.perform(post("/borrowed_books/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(borrowedBooksDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(borrowedBooksDTO)));
    }

    @Test
    public void getAllBorrowedBooksTest() throws Exception{

        //GIVEN
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        List<BorrowedBooks> borrowedBooks1 = new ArrayList<>();
        borrowedBooks1.add(borrowedBooks);

        //WHEN
        when(borrowedBooksService.getAllBorrowedBooks()).thenReturn(borrowedBooks1);

        //THEN
        mockMvc.perform(get("/borrowed_books/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(borrowedBooks1)));
    }

    @Test
    public void getOneBorrowedBookTest() throws Exception {

        //GIVEN
        borrowedBooksDTO = BorrowedBooksMocks.mockBorrowedBooksDTO();
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        //WHEN
        when(borrowedBooksService.getBorrowedBook(borrowedBooks.getId())).thenReturn(Optional.ofNullable(borrowedBooks));

        //THEN
        mockMvc.perform(get("/borrowed_books/getById/"+borrowedBooks.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(borrowedBooks)));
    }

    @Test
    public void getBorrowedBookByDate() throws Exception {

        //GIVEN
        borrowedBooksDTO = BorrowedBooksMocks.mockBorrowedBooksDTO();
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        List<BorrowedBooksDTO> borrowedBooksDTOS = new ArrayList<>();
        borrowedBooksDTOS.add(borrowedBooksDTO);

        //WHEN
        when(borrowedBooksService.getBorrowedBookByDate(borrowedBooks.getDate_due())).thenReturn(borrowedBooksDTOS);

        //THEN
        mockMvc.perform(get("/borrowed_books/getByDate/"+borrowedBooks.getDate_due()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(borrowedBooksDTOS)));
    }

    @Test
    public void deleteBorrowedBookTest() throws Exception{

        //GIVEN
        borrowedBooksDTO = BorrowedBooksMocks.mockBorrowedBooksDTO();
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        //WHEN
        when(borrowedBooksService.delete(borrowedBooks.getId())).thenReturn(true);

        //THEN
        mockMvc.perform(delete("/borrowed_books/delete/"+borrowedBooks.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format(BORROWED_BOOK_WAS_DELETED,borrowedBooks.getId())));
    }

    /*@Test
    public void updateBorrowedBooksTest() throws Exception{

        //GIVEN
        borrowedBooksDTO = BorrowedBooksMocks.mockBorrowedBooksDTO();
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        //WHEN
        when(borrowedBooksService.updateBorrowedBook(borrowedBooks.getId(),borrowedBooks.getDate_returned())).thenReturn(borrowedBooksDTO);

        //THEN
        mockMvc.perform(put("/borrowed_books/update/"+borrowedBooks.getId()+"/"+borrowedBooks.getDate_returned()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(borrowedBooks)));
    }*/
}
