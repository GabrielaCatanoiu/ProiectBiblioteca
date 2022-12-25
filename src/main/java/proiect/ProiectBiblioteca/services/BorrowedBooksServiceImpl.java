package proiect.ProiectBiblioteca.services;

import proiect.ProiectBiblioteca.dto.BorrowedBooksDTO;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BorrowedBooksServiceImpl {

    public List<BorrowedBooks> getAllBorrowedBooks();
    public Optional<BorrowedBooks> getBorrowedBook(Long id);
    public List<BorrowedBooksDTO> getBorrowedBookByDate(String date_due);
    public BorrowedBooksDTO addBorrowedBook(BorrowedBooksDTO borrowedBooksDTO);
    public void deleteBorrowedBook(Long id);
    public BorrowedBooksDTO updateBorrowedBook(Long id, String newDate_returned);
}
