package proiect.ProiectBiblioteca.mapper;

import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.BorrowedBooksDTO;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;

@Component
public class BorrowedBooksMapper {

    public BorrowedBooks mapToBorrowedBooks(BorrowedBooksDTO borrowedBooksDTO)
    {
        return BorrowedBooks.builder()
                .id(borrowedBooksDTO.getId())
                .date_due(borrowedBooksDTO.getDate_due())
                .date_returned(borrowedBooksDTO.getDate_returned())
                .build();
    }

    public BorrowedBooksDTO mapToBorrowedBooksDTO(BorrowedBooks borrowedBooks)
    {
        return BorrowedBooksDTO.builder()
                .id(borrowedBooks.getId())
                .date_due(borrowedBooks.getDate_due())
                .date_returned(borrowedBooks.getDate_returned())
                .build();
    }
}
