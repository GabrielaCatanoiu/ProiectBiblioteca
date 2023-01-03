package proiect.ProiectBiblioteca.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.dto.BorrowedBooksDTO;
import proiect.ProiectBiblioteca.entity.Book;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;
import proiect.ProiectBiblioteca.exceptions.BorrowedBookNotFoundException;
import proiect.ProiectBiblioteca.mapper.BorrowedBooksMapper;
import proiect.ProiectBiblioteca.repositories.BookRepository;
import proiect.ProiectBiblioteca.repositories.BorrowedBooksRepository;
import proiect.ProiectBiblioteca.repositories.MemberRepository;
import proiect.ProiectBiblioteca.services.BorrowedBooksService;
import proiect.ProiectBiblioteca.utils.BorrowedBooksMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BorrowedBooksServiceTests {

    @InjectMocks
    BorrowedBooksService borrowedBooksService;

    @Mock
    BorrowedBooksRepository borrowedBooksRepository;

    @Mock
    BorrowedBooksMapper borrowedBooksMapper;

    @Mock
    BookRepository bookRepository;

    @Mock
    MemberRepository memberRepository;

    BorrowedBooks borrowedBooks;
    BorrowedBooksDTO borrowedBooksDTO;
    BookDTO bookDTO;
    Book book;

    @Test
    public void testGetAllBorrowedBooks() {

        //GIVEN
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        List<BorrowedBooks> borrowedBooks1 = new ArrayList<>();
        borrowedBooks1.add(borrowedBooks);

        //WHEN
        when(borrowedBooksRepository.findAll()).thenReturn(borrowedBooks1);

        List<BorrowedBooks> result = borrowedBooksService.getAllBorrowedBooks();

        //THEN
        assertEquals(result, borrowedBooks1);
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetBorrowedBook() {

        //GIVEN
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        //WHEN
        when(borrowedBooksRepository.findById(borrowedBooks.getId())).thenReturn(Optional.ofNullable(borrowedBooks));

        Optional<BorrowedBooks> result = borrowedBooksService.getBorrowedBook(borrowedBooks.getId());

        //THEN
        assertEquals(result, Optional.ofNullable(borrowedBooks));
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetBorrowedBookByDate() {

        //GIVEN
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();
        borrowedBooksDTO = BorrowedBooksMocks.mockBorrowedBooksDTO();

        List<BorrowedBooks> borrowedBooks1 = new ArrayList<>();
        borrowedBooks1.add(borrowedBooks);

        List<BorrowedBooksDTO> borrowedBooksDTOS = new ArrayList<>();
        borrowedBooksDTOS.add(borrowedBooksDTO);

        //WHEN
        when(borrowedBooksRepository.findAllByDate_due(borrowedBooks.getDate_due())).thenReturn(borrowedBooks1);
        when(borrowedBooksMapper.mapToBorrowedBooksDTO(borrowedBooks)).thenReturn(borrowedBooksDTO);

        //THEN
        List<BorrowedBooksDTO> result = borrowedBooksService.getBorrowedBookByDate(borrowedBooks.getDate_due());
        assertEquals(result, borrowedBooksDTOS);
        assertThat(result).isNotNull();
    }

    @Test
    public void testDeleteBorrowedBook(){

        //GIVEN
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();

        //WHEN
        when(borrowedBooksRepository.findById(borrowedBooks.getId())).thenReturn(Optional.ofNullable(borrowedBooks));

        boolean result = borrowedBooksService.delete(borrowedBooks.getId());

        //THEN
        assertTrue(result);
    }

    @Test
    public void testUpdateBorrowedBook(){

        //GIVEN
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();
        borrowedBooksDTO = BorrowedBooksMocks.mockBorrowedBooksDTO();
        borrowedBooksDTO.setDate_returned("2021-12-03");

        //WHEN
        when(borrowedBooksRepository.getReferenceById(borrowedBooks.getId())).thenReturn(borrowedBooks);
        BorrowedBooks borrowedBooks1 = BorrowedBooksMocks.mockBorrowedBooks();
        borrowedBooks1.setDate_returned("2021-12-03");
        when(borrowedBooksMapper.mapToBorrowedBooksDTO(borrowedBooks1)).thenReturn(borrowedBooksDTO);
        when(borrowedBooksRepository.save(borrowedBooks)).thenReturn(borrowedBooks1);

        //THEN
        BorrowedBooksDTO result = borrowedBooksService.updateBorrowedBook(1L, "2021-12-03");
        assertThat(result).isNotNull();
        assertTrue(result.getDate_returned().equals("2021-12-03"));
    }

    @Test
    public void testUpdateBorrowedBookException() {
        //GIVEN
        borrowedBooks = null;
        borrowedBooksDTO = null;

        //WHEN
        when(borrowedBooksRepository.getReferenceById(1L)).thenReturn(borrowedBooks);

        //THEN
       BorrowedBookNotFoundException borrowedBookNotFoundException = assertThrows(BorrowedBookNotFoundException.class, () -> borrowedBooksService.updateBorrowedBook(1L, "2022-12-03"));
        assertEquals(String.format(BORROWED_BOOK_ID_NOT_FOUND, 1L), borrowedBookNotFoundException.getMessage());
    }

    @Test
    public void testAddBorrowedBooks(){

        //GIVEN
        borrowedBooks = BorrowedBooksMocks.mockBorrowedBooks();
        borrowedBooksDTO = BorrowedBooksMocks.mockBorrowedBooksDTO();

        //WHEN
        when(borrowedBooksMapper.mapToBorrowedBooks(borrowedBooksDTO)).thenReturn(borrowedBooks);
        when(bookRepository.findById(borrowedBooksDTO.getBookDTO().getId())).thenReturn(Optional.ofNullable(borrowedBooks.getBook()));
        when(memberRepository.findById(borrowedBooks.getMember().getId())).thenReturn(Optional.ofNullable(borrowedBooks.getMember()));

        when(borrowedBooksRepository.save(borrowedBooks)).thenReturn(borrowedBooks);
        when(borrowedBooksMapper.mapToBorrowedBooksDTO(borrowedBooks)).thenReturn(borrowedBooksDTO);
        when(borrowedBooksMapper.mapToBorrowedBooks(borrowedBooksDTO)).thenReturn(borrowedBooks);

        BorrowedBooksDTO result = borrowedBooksService.addBorrowedBook(borrowedBooksDTO);

        //THEN
        assertTrue(result.getMemberDTO().getEmail().equals(borrowedBooksDTO.getMemberDTO().getEmail()));
        assertThat(result.getMemberDTO().getPhone()).isNotNull();
        assertNotNull(result);
        verifyNoMoreInteractions(borrowedBooksRepository,borrowedBooksMapper);
    }
}
