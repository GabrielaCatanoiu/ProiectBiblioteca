package proiect.ProiectBiblioteca.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.entity.*;
import proiect.ProiectBiblioteca.exceptions.BookNotFoundException;
import proiect.ProiectBiblioteca.mapper.BookMapper;
import proiect.ProiectBiblioteca.repositories.AuthorRepository;
import proiect.ProiectBiblioteca.repositories.BookRepository;
import proiect.ProiectBiblioteca.repositories.CategoryRepository;
import proiect.ProiectBiblioteca.repositories.PublishingHouseRepository;
import proiect.ProiectBiblioteca.services.BookService;
import proiect.ProiectBiblioteca.utils.BookMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookServiceTests {

    @InjectMocks
    BookService bookService;
    @Mock
    BookRepository bookRepository;
    @Mock
    BookMapper bookMapper;
    @Mock
    AuthorRepository authorRepository;
    @Mock
    PublishingHouseRepository publishingHouseRepository;
    @Mock
    CategoryRepository categoryRepository;

    Book book;
    BookDTO bookDTO;
    Author author;
    AuthorDTO authorDTO;
    PublishingHouse publishingHouse;
    Category category;

    @Test
    public void testGetAllBooks(){

        //GIVEN
        book = BookMocks.mockBook();

        List<Book> books = new ArrayList<>();
        books.add(book);

        //WHEN
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        //THEN
        assertEquals(result,books);
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetBook(){

        //GIVEN
        book = BookMocks.mockBook();

        //WHEN
        when(bookRepository.findById(book.getId())).thenReturn(Optional.ofNullable(book));

        Optional<Book> result = bookService.getOneBook(book.getId());

        //THEN
        assertEquals(result,Optional.ofNullable(book));
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetBookByTitle(){

        //GIVEN
        book = BookMocks.mockBook();
        bookDTO = BookMocks.mockBookDTO();

        List<Book> books = new ArrayList<>();
        books.add(book);

        List<BookDTO> bookDTOS = new ArrayList<>();
        bookDTOS.add(bookDTO);

        //WHEN
        when(bookRepository.findBookByTitle(book.getTitle())).thenReturn(books);
        when(bookMapper.mapToBookDTO(book)).thenReturn(bookDTO);

        //THEN
        List<BookDTO> result = bookService.geByTitle(book.getTitle());
        assertEquals(result,bookDTOS);
        assertThat(result).isNotNull();
    }

    @Test
    public void testBookException() {
        //GIVEN
        book = null;

        List<Book> books = new ArrayList<>();

        //WHEN
        when(bookRepository.findBookByTitle("Luceafarul")).thenReturn(books);

        //THEN
        BookNotFoundException bookNotFoundException = assertThrows(BookNotFoundException.class, () -> bookService.geByTitle("Luceafarul"));
        assertEquals(String.format(BOOK_NOT_FOUND, "Luceafarul"), bookNotFoundException.getMessage());
    }

    @Test
    public void testDeleteBook(){

        //GIVEN
        book = BookMocks.mockBook();

        //WHEN
        when(bookRepository.findById(book.getId())).thenReturn(Optional.ofNullable(book));

        boolean result = bookService.delete(book.getId());

        //THEN
        assertTrue(result);
    }

    @Test
    public void testUpdateBook(){

        //GIVEN
        book = BookMocks.mockBook();
        bookDTO = BookMocks.mockBookDTO();
        bookDTO.setTitle("Romeo si Julieta");

        //WHEN
        when(bookRepository.getReferenceById(book.getId())).thenReturn(book);
        Book book1 = BookMocks.mockBook();
        book1.setTitle("Romeo si Julieta");
        when(bookMapper.mapToBookDTO(book1)).thenReturn(bookDTO);
        when(bookRepository.save(book)).thenReturn(book1);

        //THEN
        BookDTO result = bookService.updateBookName(1L, "Romeo si Julieta");
        assertThat(result).isNotNull();
        assertTrue(result.getTitle().equals("Romeo si Julieta"));
    }

    @Test
    public void testUpdateBookException() {
        //GIVEN
        book = null;
        bookDTO = null;

        //WHEN
        when(bookRepository.getReferenceById(1L)).thenReturn(book);

        //THEN
        BookNotFoundException bookNotFoundException = assertThrows(BookNotFoundException.class, () -> bookService.updateBookName(1L, "Romeo si Julieta"));
        assertEquals(String.format(BOOK_ID_NOT_FOUND, 1L), bookNotFoundException.getMessage());
    }

    /*@Test
    public void testAddBook(){

        //GIVEN
        book = BookMocks.mockBook();
        bookDTO = BookMocks.mockBookDTO();

        //WHEN
        when(bookMapper.mapToBook(bookDTO)).thenReturn(book);
        when(authorRepository.findById(bookDTO.getAuthorDTO().getId())).thenReturn(Optional.ofNullable(author));
        when(publishingHouseRepository.findById(bookDTO.getPublishingHouseDTO().getId())).thenReturn(Optional.ofNullable(publishingHouse));
        when(categoryRepository.findById(bookDTO.getCategoryDTO().getId())).thenReturn(Optional.ofNullable(category));

        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.mapToBookDTO(book)).thenReturn(bookDTO);
        when(bookMapper.mapToBook(bookDTO)).thenReturn(book);

        BookDTO result = bookService.addBook(bookDTO);

        //THEN
        assertTrue(result.getTitle().equals(bookDTO.getTitle()));
        assertThat(result.getTitle()).isNotNull();
        assertNotNull(result);
        verifyNoMoreInteractions(bookRepository,bookMapper);
    }*/
}
